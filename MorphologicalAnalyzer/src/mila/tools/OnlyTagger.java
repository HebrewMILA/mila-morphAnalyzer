package mila.tools;

import java.io.BufferedOutputStream;
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

import mila.HMM.HMM2Morph;
import mila.HMM.MorphMult2TaggerFormat;

public class OnlyTagger {
	private static String HMMTaggerDir = System.getProperty("user.dir")
			+ File.separator;
	private static String RoyTaggerDir = HMMTaggerDir + File.separator
			+ "royTagger";
	private static String TaggerLOFDir = HMMTaggerDir + File.separator;
	private static String dprefixesFile = "dataFiles/dprefixes.data";

	public static void main(String args[]) throws IOException, JAXBException {
		String input = args[0];
		String output = args[1];

		String processedXML = readFile(new File(input)).toString();
		File tempDir = createTempDir();

		String taggerFormat = goRoyTag(processedXML, tempDir);
		goConvertToXML(output, processedXML, taggerFormat);
	}

	private static void goConvertToXML(String output, String processedXML,
			String taggerFormat) throws IOException, FileNotFoundException {
		HMM2Morph h = new HMM2Morph();
		String fileName = taggerFormat.substring(1);
		String homeDirectoy = new java.io.File(".").getCanonicalPath();
		StringBuilder sb = new StringBuilder();
		sb.append(homeDirectoy).append(File.separator).append("royTagger")
				.append(File.separator).append("workdir")
				.append(File.separator).append("tagging-").append(fileName);
		String taggedFile = sb.toString();

		PrintWriter pw = new PrintWriter(new BufferedOutputStream(
				new FileOutputStream(new File(output))));

		try {
			h.process(processedXML, taggedFile, pw, dprefixesFile);
		} catch (Exception e) {
			throw new IOException(e);
		}
	}

	private static String goRoyTag(String processedXML, File tempDir)
			throws JAXBException, IOException {
		MorphMult2TaggerFormat mm2tf = new MorphMult2TaggerFormat();
		String taggerFormat = mm2tf.myMorp2Tagger(processedXML,
				tempDir.getAbsolutePath());

		Process proc = Runtime.getRuntime().exec(
				createPerlCommand(tempDir.toString() + taggerFormat));
		boolean flag = true;
		while (flag) {
			try {
				proc.waitFor();
				flag = false;
			} catch (InterruptedException e) {
				/* Do nothing -- flag is true so we'll continue waiting */
				System.err.println("Waiting for tagger, go kill that if stuck");
			}
		}
		return taggerFormat;
	}

	public static StringBuilder readFile(File f) throws IOException {
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

	private static String createPerlCommand(String taggerInput) {
		StringBuilder sb = new StringBuilder();
		sb.append("perl -I").append(RoyTaggerDir).append(" ")
				.append(RoyTaggerDir).append(File.separator)
				.append("MTTest.pl -dir ").append(RoyTaggerDir)
				.append(File.separator).append("workdir -rmtmp ")
				.append(taggerInput).append(" ").append(TaggerLOFDir)
				.append(File.separator).append("corpus.lm ")
				.append(TaggerLOFDir).append(File.separator)
				.append("corpus.lex.prob");
		return sb.toString();
	}

	private static File createTempDir() throws IOException {
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
			System.err.println("Cannot create temp file: " + ex.getMessage());
			throw ex;
		}
		return tempDir;
	}
}
