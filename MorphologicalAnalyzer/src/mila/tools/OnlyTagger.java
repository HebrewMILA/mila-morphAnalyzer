package mila.tools;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.CharBuffer;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;

import javax.xml.bind.JAXBException;

import org.apache.commons.io.FileUtils;

import mila.HMM.HMM2Morph;
import mila.HMM.MorphMult2TaggerFormat;
import mila.lexicon.analyse.Data;

public class OnlyTagger implements Tool {
	private String hmmTaggerDir = System.getProperty("user.dir")
			+ File.separator;
	private String royTaggerDir = hmmTaggerDir + File.separator + "royTagger";
	private String taggerLOFDir = hmmTaggerDir + File.separator;
	private String dprefixesFile = "dataFiles/dprefixes.data";

	public static void main(String args[]) throws IOException, JAXBException {
		String input_name = args[0];
		String output_name = args[1];

		File input = new File(input_name);
		File output = new File(output_name);

		Tool tool = new OnlyTagger();

		tool.processFile(input, output);

		System.exit(0);
	}

	public void processDirectory(File input, File output) {
		// TODO Auto-generated method stub

	}

	public void processFile(File input, File output) throws IOException,
			JAXBException {
		String processedXML = readFile(input).toString();
		File tempDir = createTempDir();

		try {
			Data.webFlag = Processor.WEB_FLAG;
			String taggerFormat = goRoyTag(processedXML, tempDir);
			goConvertToXML(output, processedXML, taggerFormat);
		} finally {
			FileUtils.deleteDirectory(tempDir);
		}
	}

	private void goConvertToXML(File output, String processedXML,
			String taggerFormat) throws IOException, FileNotFoundException {
		HMM2Morph h = new HMM2Morph();
		String fileName = taggerFormat.substring(1);
		String homeDirectoy = new java.io.File(".").getCanonicalPath();
		StringBuilder sb = new StringBuilder();
		sb.append(homeDirectoy).append(File.separator).append("royTagger")
				.append(File.separator).append("workdir")
				.append(File.separator).append("tagging-").append(fileName);
		String taggedFile = sb.toString();

		PrintWriter pw = new PrintWriter(new FileOutputStream(output));

		System.err.println("Start output to file");
		try {
			Data.webFlag = Processor.WEB_FLAG;
			h.process(processedXML, taggedFile, pw, dprefixesFile);
		} catch (Exception e) {
			throw new IOException(e);
		}
		System.err.println("End output to file");
	}

	private String goRoyTag(String processedXML, File tempDir)
			throws JAXBException, IOException {
		Data.webFlag = Processor.WEB_FLAG;
		MorphMult2TaggerFormat mm2tf = new MorphMult2TaggerFormat();
		String taggerFormat = mm2tf.myMorp2Tagger(processedXML,
				tempDir.getAbsolutePath());
		String cmdline = createPerlCommand(tempDir.toString() + taggerFormat);
		System.err.println("Running command: " + cmdline);

		Process proc = Runtime.getRuntime().exec(cmdline);
		boolean flag = true;
		int rc = -1;
		while (flag) {
			try {
				rc = proc.waitFor();
				flag = false;
			} catch (InterruptedException e) {
				/* Do nothing -- flag is true so we'll continue waiting */
				System.err.println("Waiting for tagger, go kill that if stuck");
			}
		}

		System.err.println("Tagger process terminated with return code "
				+ Integer.toString(rc));

		return taggerFormat;
	}

	public StringBuilder readFile(File f) throws IOException {
		FileInputStream fis = new FileInputStream(f);
		FileChannel fc = fis.getChannel();

		int sz = (int) fc.size();
		MappedByteBuffer bb = fc.map(FileChannel.MapMode.READ_ONLY, 0, sz);

		Charset utf8 = Charset.forName("UTF-8");
		CharsetDecoder decoder = utf8.newDecoder();
		CharBuffer cb = decoder.decode(bb);

		StringBuilder outBuffer = new StringBuilder(cb);
		fc.close();
		fis.close();
		return outBuffer;
	}

	private String createPerlCommand(String taggerInput) {
		StringBuilder sb = new StringBuilder();
		sb.append("perl -I").append(royTaggerDir).append(" ")
				.append(royTaggerDir).append(File.separator)
				.append("MTTest.pl -dir ").append(royTaggerDir)
				.append(File.separator).append("workdir -rmtmp ")
				.append(taggerInput).append(" ").append(taggerLOFDir)
				.append(File.separator).append("corpus.lm ")
				.append(taggerLOFDir).append(File.separator)
				.append("corpus.lex.prob");
		return sb.toString();
	}

	private File createTempDir() throws IOException {
		StringBuilder sb = new StringBuilder();
		sb.append(File.separator).append(System.getProperty("user.dir"))
				.append(File.separator).append("/morphanalyzer_tmp");
		File tempParentDir = new File(sb.toString());
		File tempDir = null;
		try {
			tempDir = File.createTempFile("mydir", "ClientServerHMMTagger",
					tempParentDir);
			if (!tempDir.delete())
				throw new IOException();
			// tempFile.mkdir();
			if (!tempDir.mkdir())
				throw new IOException();
		} catch (IOException ex) {
			System.err
					.println("Cannot create temp file ("
							+ tempParentDir.getAbsolutePath() + "): "
							+ ex.getMessage());
			throw ex;
		}
		return tempDir;
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

	public void setDprefixesFile(String dprefixesFile) {
		this.dprefixesFile = dprefixesFile;
	}
}
