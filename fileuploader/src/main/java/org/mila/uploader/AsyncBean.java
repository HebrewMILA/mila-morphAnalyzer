package org.mila.uploader;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PipedReader;
import java.io.PipedWriter;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.zip.GZIPOutputStream;

import mila.HMM.HMM2Morph;
import mila.HMM.MorphMult2TaggerFormat;
import mila.mw.MWXMLTokenizer;
import mila.mw.PostProcessor1;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.apache.commons.io.input.ReaderInputStream;
import org.apache.log4j.Logger;
import org.mila.uploader.entities.TagRequest;
import org.mila.uploader.entities.TagRequestState;
import org.mila.uploader.services.TagRequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@Component
public class AsyncBean {
	private static Logger logger = Logger.getLogger(AsyncBean.class);

	private String hmmTaggerDir;
	private String royTaggerDir;
	private String taggerLOFDir;
	private String tempDir;
	private TagRequestService tagRequestService;

	@Async
	public void handle(File input, TagRequest tagreq) {
		try {
			tag(input, tagreq);
		} catch (TagException e) {
			tagreq.setState(TagRequestState.ERROR);
			tagRequestService.save(tagreq);
		} finally {
			/* ??? */
		}

	}

	public void tag(final File input, TagRequest tagreq) throws TagException {
		final PrintWriter output;
		final FileOutputStream foutput;
		final GZIPOutputStream goutput;
		final String outputFilename;
		final String tempPath;
		try {
			final File _tempDir;
			final File outputF;
			outputF = File.createTempFile("UP_output", ".xml.gz", new File(
					tempDir));
			outputFilename = outputF.getAbsolutePath();
			_tempDir = File.createTempFile("UP_temp", "", new File(tempDir));
			if (!_tempDir.delete())
				throw new IOException();
			if (!_tempDir.mkdir())
				throw new IOException();
			tempPath = _tempDir.getAbsolutePath();
			_tempDir.deleteOnExit();
			foutput = new FileOutputStream(outputF);
			goutput = new GZIPOutputStream(foutput);
			output = new PrintWriter(goutput);
		} catch (IOException e) {
			logger.error(
					"Failed to create temporary files or output file, giving up on tagging",
					e);
			throw new TagException();
		}

		try {
			tagreq.setState(TagRequestState.PROCESSING);
			tagreq.setLocalCorpusFilename(input.getAbsolutePath());
			tagreq.setTempDirectoryPath(tempPath);
			tagRequestService.save(tagreq);
			doTagging(input, output, tempPath);
			tagreq.setFilename(outputFilename);
			tagreq.setState(TagRequestState.FINISHED);
			tagRequestService.save(tagreq);
			try {
				FileUtils.deleteDirectory(new File(tempPath));
			} catch (IOException e) {
				logger.warn("[" + Long.toString(tagreq.getId())
						+ "] failed to delete temporary directory at: "
						+ tempPath, e);
			}
		} finally {
			IOUtils.closeQuietly(output);
			IOUtils.closeQuietly(goutput);
			IOUtils.closeQuietly(foutput);
		}

	}

	/**
	 * @param input
	 * @param output
	 * @param tempPath
	 * @throws TagException
	 */
	private void doTagging(final File input, final PrintWriter output,
			final String tempPath) throws TagException {
		final PipedReader in = new PipedReader();
		final PipedWriter out;
		try {
			out = new PipedWriter(in);
		} catch (IOException e1) {
			logger.error("Failed to create pipe, giving up on tagging", e1);
			throw new TagException();
		}
		final MWXMLTokenizer tokenizer = new MWXMLTokenizer();

		new Thread(new Runnable() {

			@Override
			public void run() {
				try {
					PrintWriter pw = new PrintWriter(out);
					tokenizer
							.tokenizeAndAnalyze(new FileInputStream(input), pw);
					pw.close();
				} catch (FileNotFoundException e) {
					logger.error(
							"Input file disappeared on us, closing stream and hoping for the best",
							e);
					/* not much else you can do here */
				} finally {
					IOUtils.closeQuietly(out);
				}
			}
		}).start();

		PostProcessor1 p = new PostProcessor1(false);
		StringWriter ppSW = new StringWriter();
		PrintWriter ppPW = new PrintWriter(ppSW);
		try {
			p.process(new ReaderInputStream(in), ppPW);
		} catch (Exception e) {
			logger.error("Post-processing barfed, giving up on tagging", e);
			throw new TagException();
		}
		String pp = ppSW.toString().trim();

		MorphMult2TaggerFormat mm = new MorphMult2TaggerFormat();
		String taggerFormat;
		try {
			taggerFormat = mm.myMorp2Tagger(pp, tempPath);
		} catch (Exception e) {
			logger.error(
					"Failed converting to HMM format, giving up on tagging", e);
			throw new TagException();
		}
		String taggerInputFile = tempPath + taggerFormat;

		try {
			String command = "perl -I" + royTaggerDir + " " + royTaggerDir
					+ File.separator + "MTTest.pl -dir " + royTaggerDir
					+ File.separator + "workdir  -rmtmp " + taggerInputFile
					+ " " + taggerLOFDir + File.separator + "corpus.lm "
					+ taggerLOFDir + File.separator + "corpus.lex.prob";

			Process process = Runtime.getRuntime().exec(command); // pearl
			process.waitFor();
		} catch (Exception e) {
			logger.error("Failed running tagger, giving up on tagging", e);
			throw new TagException();
		}

		String taggedFile = royTaggerDir + "/workdir" + "/tagging-"
				+ taggerFormat.substring(1);
		HMM2Morph h = new HMM2Morph();
		try {
			h.process(pp, taggedFile, output);
		} catch (Exception e) {
			logger.error("Failed converting back to tagger format", e);
			throw new TagException();
		}
	}

	public String getHmmTaggerDir() {
		return hmmTaggerDir;
	}

	public String getRoyTaggerDir() {
		return royTaggerDir;
	}

	public String getTaggerLOFDir() {
		return taggerLOFDir;
	}

	public void setHmmTaggerDir(String hmmTaggerDir) {
		this.hmmTaggerDir = hmmTaggerDir;
	}

	public void setRoyTaggerDir(String royTaggerDir) {
		this.royTaggerDir = royTaggerDir;
	}

	public void setTaggerLOFDir(String taggerLOFDir) {
		this.taggerLOFDir = taggerLOFDir;
	}

	public String getTempDir() {
		return tempDir;
	}

	public void setTempDir(String tempDir) {
		this.tempDir = tempDir;
	}

	@Autowired
	public void setTagRequestService(TagRequestService tagRequestService) {
		this.tagRequestService = tagRequestService;
	}
}

final class TagException extends Exception {
	private static final long serialVersionUID = 4883545205651825032L;
}