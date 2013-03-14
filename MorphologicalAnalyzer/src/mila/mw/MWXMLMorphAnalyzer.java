package mila.mw;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.sql.SQLException;
import java.util.Arrays;

import javax.xml.bind.JAXBException;

import mila.lexicon.analyse.Data;
import mila.lexicon.analyse.XMLMorphAnalyzer;
import mila.lexicon.utils.StringUtils;
import mila.lexicon.utils.Translate;

/**
 * 
 * <p>
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GPL
 * 
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE.
 * </p>
 * 
 * 
 * @author Dalia Bojan
 * @version 1.0
 */

public class MWXMLMorphAnalyzer extends XMLMorphAnalyzer {

	/**
	 * database mode working
	 */
	public static void dataLoad() {
		// System.out.println("(F) dataLoad() ");
		Data.webFlag = true;
		Data.dinflectionsFile = "";
		Data.dmwinflectionsFile = "";
		Data.dprefixesFile = "";
		Data.gimatriaFile = "";
		Data.dmwe1File = "";
		Data.dmwe2File = "";
		Data.dmwe3File = "";
		Data.dmwe4File = "";
		Data.init(false);
	}

	/**
	 * data files mode working
	 * 
	 * @param dinflectionsFile
	 *            dinflections input data file path
	 * @param dprefixesFile
	 *            dprefixes data file path
	 * @param gimatriasFile
	 *            gimatrias data file path
	 */
	public static void dataLoad(String dinflectionsFile, String dprefixesFile,
			String gimatriasFile, String dmwinflectionsFile, String mwe1File,
			String mwe2File, String mwe3File, String mwe4File) {
		long startTime = System.currentTimeMillis();
		Data.webFlag = false;
		Data.dinflectionsFile = dinflectionsFile;
		Data.dmwinflectionsFile = dmwinflectionsFile;
		Data.dprefixesFile = dprefixesFile;
		Data.gimatriaFile = gimatriasFile;
		Data.dmwe1File = mwe1File;
		Data.dmwe2File = mwe2File;
		Data.dmwe3File = mwe3File;
		Data.dmwe4File = mwe4File;
		// MWData.dinflectionsFile = dinflectionsFile;
		Data.init(false);
		printTimesHandling(startTime);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see lexicon.analyse.MorphAnalyzer#main(java.lang.String[])
	 */
	public static void main(String[] args) {
		MWXMLMorphAnalyzer a = new MWXMLMorphAnalyzer();

		// 1) working with input / output file/directories

		int argc = args.length;

		boolean webFlag = false;
		String dinflectionsFile = "";
		String dmwinflectionsFile = "";
		String dprefixesFile = "";
		String gimartiasFile = "";
		String dmwe1File = "";
		String dmwe2File = "";
		String dmwe3File = "";
		String dmwe4File = "";
		String input = "";
		String output = "";
		String configFileName = "morphanalyzer.cfg";

		File f = new File(configFileName);
		if (!f.exists()) {
			System.out.println("can not find configuration file "
					+ configFileName);
			System.out.println("creating deafult configurarion file ");
			a.CreateConfigurationFile(configFileName);
		}

		String[] files = null;
		try {
			files = a.LoadConfigurationFile(configFileName); // load config file
		} catch (Exception e) {
			System.out.println("ERROR");
			System.out
					.println("bad configuration file " + configFileName + " ");
			System.out.println("delete file " + configFileName
					+ " to force deafult configuration file creation");
			e.printStackTrace();
			System.exit(1);
		}

		switch (argc) {
		case 3:
			webFlag = (new Boolean(args[0]).booleanValue());
			System.out.println("webFlag=" + webFlag);
			input = args[1];
			System.out.println("input =" + input);
			output = args[2];
			System.out.println("output=" + output);
			/*
			 * dinflectionsFile = args[3];
			 * System.out.println("dinflections File=" + dinflectionsFile);
			 * dprefixesFile = args[4]; System.out.println("dprefixes File=" +
			 * dprefixesFile); gimartiasFile = args[5];
			 * System.out.println("gimatrias File=" + gimartiasFile);
			 */
			for (String filename : files) {
				if ((filename == null) || (filename.length() < 3)) {
					System.out.println("bad configuration file "
							+ configFileName);
					System.out.println("delete file " + configFileName
							+ " to force deafult configuration file creation");
					System.exit(0);
				}
			}

			dinflectionsFile = files[0];
			dprefixesFile = files[1];
			gimartiasFile = files[2];
			dmwinflectionsFile = files[3];
			dmwe1File = files[4];
			dmwe2File = files[5];
			dmwe3File = files[6];
			dmwe4File = files[7];
			System.out.println("dinflections File=" + dinflectionsFile);
			System.out.println("dmwinflections File=" + dmwinflectionsFile);
			System.out.println("dprefixes File=" + dprefixesFile);
			System.out.println("gimatrias File=" + gimartiasFile);
			System.out.println("mwe1 File=" + dmwe1File);
			System.out.println("mwe2 File=" + dmwe2File);
			System.out.println("mwe3 File=" + dmwe3File);
			System.out.println("mwe4 File=" + dmwe4File);
			File in = new File(input);
			File out = new File(output);
			if (in.isDirectory() && out.isDirectory()) {
				if (webFlag) {
					a.processDirectory(input, output);
				} else {
					a.processDirectory(input, output, dinflectionsFile,
							dprefixesFile, gimartiasFile, dmwinflectionsFile,
							dmwe1File, dmwe2File, dmwe3File, dmwe4File);
				}
			} else if (!in.isDirectory() && !out.isDirectory()) {
				if (webFlag) {
					a.processSingleFile(input, output);
				} else {
					a.processSingleFile(input, output, dinflectionsFile,
							dprefixesFile, gimartiasFile, dmwinflectionsFile,
							dmwe1File, dmwe2File, dmwe3File, dmwe4File);
				}
			} else
				System.out
						.println("input and output should both be files or should both be existing directories");

			break;

		default:
			System.out.println("Morpological Analyzer");
			System.out.println("wrong number of parameters");
			System.out.println("USAGE:");
			System.out
					.println("java -jar morphAnalyzer.jar [database flag] [input] [output]");
			System.out
					.println("database flag = true for using the mysql database or false for using the external .data files");
			System.out
					.println("external .data files names are in the configuration file : "
							+ configFileName);
			System.out.println("example:");
			System.out
					.println("java -jar morphAnalyzer.jar false input/ output/");
		}

		// /////////////////////////////////////////////////////
		// 2) working with input single token via web
		// StringWriter sw = new StringWriter();
		// PrintWriter pw = new PrintWriter(sw);
		// String inputString = "ילד";
		//
		// dataLoad();
		// a.analyzeSingleToken(pw, inputString);
		//
		// String result = sw.toString();
		// result = result.trim();P
		// System.out.println(result);
		// System.exit(0);

		// /////////////////////////////////////////////////////
		// 2) working with input single token via files
		// StringWriter sw = new StringWriter();
		// PrintWriter pw = new PrintWriter(sw);
		// String inputString = "גן";
		//
		// boolean webFlag = (new Boolean(args[0]).booleanValue());
		// System.out.println("webFlag=" + webFlag);
		//
		// String dinflectionsFile = args[3];
		// System.out.println("dinflections File=" + dinflectionsFile);
		// String dprefixesFile = args[4];
		// System.out.println("dprefixes File=" + dprefixesFile);
		// String gimatriasFile = args[5];
		// System.out.println("gimatrias File=" + gimatriasFile);
		// dataLoad(dinflectionsFile, dprefixesFile, gimatriasFile);
		// a.analyzeSingleToken(pw, inputString);
		// String result = sw.toString();
		// result = result.trim();
		// System.out.println(result);

		System.exit(0);
	}

	protected static long printTimesHandling(long startTime) {
		long afterLoadTime = System.currentTimeMillis();
		long load2MemoryElapsedTime = afterLoadTime - startTime;
		System.out.println("Finished loading data into memory, Elapsed time = "
				+ load2MemoryElapsedTime + " ms");
		return afterLoadTime;
	}

	private void analyzeDirectory(File inputDirectory, String outputDirectory,
			final int pos) {
		System.out.println("(F) MWXMLMorphAnalyzer: analyzeDirectory()");
		if (inputDirectory.isDirectory()) {
			// create correspond directory for xml

			String out = outputDirectory
					+ inputDirectory.getAbsolutePath().substring(pos);
			// System.out.println(out);
			if (!(new File(out)).exists()) {
				if ((new File(out)).mkdir())
					System.out.println("Success creating directory: " + out);
				else
					System.out
							.println("Error in creation of directory: " + out);
			}
			// call for analysis of each file/dir under the currect directory
			File[] files = inputDirectory.listFiles();
			Arrays.sort(files);
			for (int i = 0; i < files.length; i++)
				analyzeDirectory(files[i], outputDirectory, pos);
		} else { // file to be analyzed
			String inputFile = inputDirectory.getAbsolutePath();
			String outputFile = outputDirectory + inputFile.substring(pos);
			try {

				// System.out.println(outputFile);
				analyzeFile(inputFile, outputFile);
				PostProcessor1 postProcessor = new PostProcessor1(!Data.webFlag);

				try {
					postProcessor.process(outputFile, outputFile);
				} catch (UnsupportedEncodingException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (JAXBException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * This method is called when using input/output files
	 */
	private void analyzeFile(String inputFile, String outputFile) {
		System.out.println("(F) MWXMLMorphAnalyzer: analyzeFile()");
		try {
			long afterLoadTime = System.currentTimeMillis();
			// ///////////////////////////////////////
			ReadXMLFile(inputFile, outputFile);
			// //////////////////////////////////////
			long elapsedTime = System.currentTimeMillis() - afterLoadTime;
			System.out.println("analyze elapsed time = " + elapsedTime + "ms");
			// System.out.println("tokens count = " + tokensCount);

			// createXML.printDoc();
		} catch (Exception e) {
			System.out
					.println("An error occured make sure you have tokenized the input file, if error still existes send the developer the input file");
			e.printStackTrace();
		} finally {
			// System.exit(0);
		}
	}

	/**
	 * This method is used when the input is a single token - we skip the
	 * tokenizer and get an analysis.<br>
	 * This method should be used in cases when we have the text already
	 * tokenized. It is quicker than any other way.<br>
	 * 
	 * @param pw
	 *            output XML format according to Mila standards
	 * @param inputSt
	 *            input Hebrew single token string
	 */
	@Override
	public void analyzeSingleToken(PrintWriter pw, String inputSt) {
		System.out.println("inputSt=" + inputSt);
		String hebWord = "";
		try {
			hebWord = URLDecoder.decode(inputSt, "UTF-8");
		} catch (UnsupportedEncodingException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}

		MWCreateCorpusXML mwcreateXML = new MWCreateCorpusXML();
		try {
			mwcreateXML.createXMLDoc();
		} catch (Exception e) {
			System.out
					.println("XMLMorphAnalyzer:processXMLOutput while createXMLdOC - Exception");
			e.printStackTrace();
		}
		mwcreateXML.createArticle();
		mwcreateXML.createParagraph();
		mwcreateXML.createSentence();
		mwcreateXML.createToken(hebWord);

		MWTokenizationParser mwtokenizationParser = new MWTokenizationParser(
				mwcreateXML);
		if (!checkKitzur(hebWord, mwtokenizationParser)) {
			try {
				mwtokenizationParser.readInput(hebWord);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		mwcreateXML.finalizeToken();
		mwcreateXML.finalizeSentence();
		mwcreateXML.finalizeParagraph();
		mwcreateXML.printDoc(pw);
	}

	/**
	 * This method is used only when input is a single token it simulates
	 * tokenizer kitzurim handling
	 * 
	 * @param hebWord
	 * @return
	 */
	private boolean checkKitzur(String hebWord,
			MWTokenizationParser mwtokenizationParser) {
		boolean isKitzur = false;
		if (hebWord.endsWith("'")) {
			// System.out.println("analyzeStringInput : hebWord=" + hebWord);
			// token length
			int indexKitzur = 0;
			if (((indexKitzur = hebWord.indexOf("וכד'")) != -1)
					|| ((indexKitzur = hebWord.indexOf("מס'")) != -1)
					|| ((indexKitzur = hebWord.indexOf("מע'")) != -1)
					|| ((indexKitzur = hebWord.indexOf("מח'")) != -1)
					|| ((indexKitzur = hebWord.indexOf("וכו'")) != -1)
					|| ((indexKitzur = hebWord.indexOf("רח'")) != -1)
					|| ((indexKitzur = hebWord.indexOf("טל'")) != -1)
					|| ((indexKitzur = hebWord.indexOf("שכ'")) != -1)
					|| ((indexKitzur = hebWord.indexOf("שד'")) != -1)
					|| ((indexKitzur = hebWord.indexOf("פרופ'")) != -1)
					|| ((indexKitzur = hebWord.indexOf("עמ'")) != -1)
					|| ((indexKitzur = hebWord.indexOf("אונ'")) != -1)
					|| ((indexKitzur = hebWord.indexOf("וגו'")) != -1)
					|| ((indexKitzur = hebWord.indexOf("גב'")) != -1)
					|| ((indexKitzur = hebWord.indexOf("ש\"ס")) != -1)
					|| ((indexKitzur = hebWord.indexOf("ש''ס")) != -1)
					|| ((indexKitzur = hebWord.indexOf("ק\"ג")) != -1))
				if (indexKitzur > 0) {
					isKitzur = true;

					String checkedPrefix = hebWord.substring(0, indexKitzur);
					String hebBase = hebWord.substring(indexKitzur);
					if (checkedPrefix.endsWith("ה")) {
						checkedPrefix = (checkedPrefix.replace('ה', ' '))
								.trim();
						hebBase = "ה" + hebBase;
					}
					if (StringUtils.moshevkaleb(Translate
							.Heb2Eng(checkedPrefix))) {
						mwtokenizationParser.analyzeBase(
								Translate.Heb2Eng(hebBase),
								Translate.Heb2Eng(checkedPrefix),
								Translate.Heb2Eng(hebWord), hebWord);
					}
				}
		}
		return isKitzur;

	}

	// -------------------------------------------------------------------------------------------------------
	/**
	 * create a configuration file which contain the external data files names
	 */
	private void CreateConfigurationFile(String filename) {
		try {
			FileWriter outFile = new FileWriter(filename);
			PrintWriter out = new PrintWriter(outFile);

			try {
				out.println("dinflections.data");
				out.println("dprefixes.data");
				out.println("gimatria.data");
				out.println("dmwinflections.data");
				out.println("dmwe1.data");
				out.println("dmwe2.data");
				out.println("dmwe3.data");
				out.println("dmwe4.data");
			} catch (Exception e) {
				// System.out.println("bad configuration file " + filename + " "
				// );
				// System.out.println("delete file " + filename +
				// " to force deafult configuration file creation" );
			}

			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	// -------------------------------------------------------------------------------------------------------
	/**
	 * create a configuration file which contain the external data files names
	 * 
	 * @throws IOException
	 */
	private String[] LoadConfigurationFile(String filename) throws Exception {
		String line[] = { "", "", "", "", "", "", "", "" };
		/*
		 * try {
		 */
		System.out.print("Loading Configuration file...");
		FileReader input = new FileReader(filename);
		BufferedReader bufRead = new BufferedReader(input);

		line[0] = bufRead.readLine(); // dinflections.data
		if (line[0] == null)
			System.out
					.println("Error in configuration file - missing entry for dinflections.data file");
		line[1] = bufRead.readLine(); // dprefixes.data
		if (line[1] == null)
			System.out
					.println("Error in configuration file - missing entry for dprefixes.data file");
		line[2] = bufRead.readLine(); // gimatria.data
		if (line[2] == null)
			System.out
					.println("Error in configuration file - missing entry for gimatria.data file");
		line[3] = bufRead.readLine(); // dmwinflections.data
		if (line[3] == null)
			System.out
					.println("Error in configuration file - missing entry for dmwinflections.data file");
		line[4] = bufRead.readLine(); // dmwe1.data
		if (line[4] == null)
			System.out
					.println("Error in configuration file - missing entry for dmwe1.data file");
		line[5] = bufRead.readLine(); // dmwe2.data
		if (line[5] == null)
			System.out
					.println("Error in configuration file - missing entry for dmwe2.data file");
		line[6] = bufRead.readLine(); // dmwe3.data
		if (line[6] == null)
			System.out
					.println("Error in configuration file - missing entry for dmwe3.data file");
		line[7] = bufRead.readLine(); // dmwe4.data
		if (line[7] == null)
			System.out
					.println("Error in configuration file - missing entry for dmwe4.data file");

		bufRead.close();
		System.out.println("OK");
		/* } */
		/*
		 * catch (Exception e) { System.out.println("ERROR");
		 * System.out.println("bad configuration file " + filename + " " );
		 * System.out.println("delete file " + filename +
		 * " to force deafult configuration file creation" );
		 * e.printStackTrace(); }
		 */
		return line;
	}

	/**
	 * In this method we assume that the data files are already loaded We use it
	 * in Client server architecture.
	 * 
	 * @param pw
	 * @param tokenizationOutputStr
	 */
	@Override
	public void morphologicalAnalyzer(PrintWriter pw,
			String tokenizationOutputStr) {
		// System.out.println("(F) morphologicalAnalyzer ");
		// dataLoad(); // COMMENTED BY YOSSI 21.8.11
		// ///////////////////////////////////////
		ReadXMLFile(tokenizationOutputStr, pw);
		// //////////////////////////////////////
	}

	/**
	 * This method accepts XML String of tokenized text (the output of Mila
	 * Tokenizer) and produce analyzed text in XML format according to Mila
	 * standards
	 * 
	 * @param tokenizationOutputStr
	 *            input string containing tokenized Hebrew in XML format - the
	 *            output of Mila tokenizer
	 * @param pw
	 *            output XML analysis according to Mila standards
	 * @param dinflectionsFile
	 *            input data inflections file
	 * @param dprefixesFile
	 *            input data prefixes file
	 * @param gimatriasFile
	 *            input data gimatria file
	 */
	@Override
	public void morphologicalAnalyzer(String tokenizationOutputStr,
			PrintWriter pw, String dinflectionsFile, String dprefixesFile,
			String gimatriasFile) {
		dataLoad(dinflectionsFile, dprefixesFile, gimatriasFile);
		// ///////////////////////////////////////
		ReadXMLFile(tokenizationOutputStr, pw);
		// //////////////////////////////////////
	}

	/**
	 * In this method we assume that the data files are already loaded We use it
	 * in Client server architecture.
	 * 
	 * @param pw
	 * @param tokenizationOutputStr
	 */
	@Override
	public void morphologicalAnalyzerNoDataLoad(PrintWriter pw,
			String tokenizationOutputStr) {
		// ///////////////////////////////////////
		ReadXMLFile(tokenizationOutputStr, pw);
		// //////////////////////////////////////
	}

	/**
	 * This method is used for mass processing in database mode working- It can
	 * process directory of files/directories. It is especially useful for
	 * processing corpora.
	 * 
	 * @param inputDirectory
	 * @param outputDirectory
	 */
	@Override
	public void processDirectory(String inputDirectory, String outputDirectory) {
		System.out.println("(F) MWXMLMorphAnalyzer: processDirectory()");
		File in = new File(inputDirectory);
		int pos = (in.isDirectory() ? in.getAbsolutePath().length() : in
				.getParent().length());
		dataLoad();
		analyzeDirectory(in, outputDirectory, pos);
	}

	/**
	 * This method is used for mass processing in data files mode working- It
	 * can process a directory of files/directories. It is especially useful for
	 * processing corpora.
	 * 
	 * @param inputDirectory
	 *            path of directory which contains either xml tokenized files or
	 *            directories of xml tokenized file
	 * @param outputDirectory
	 *            path to the output created files. This directory will contain
	 *            output file with the same structure of the input files
	 * @param dinflectionsFile
	 *            inflections data file path
	 * @param dprefixesFile
	 *            prefixes data file path
	 * @param gimartiasFile
	 *            gimatrias data file path
	 */
	public void processDirectory(String inputDirectory, String outputDirectory,
			String dinflectionsFile, String dprefixesFile,
			String gimartiasFile, String dmwinflectionsFile, String mwe1File,
			String mwe2File, String mwe3File, String mwe4File) {
		System.out
				.println("(F) MWXMLMorphAnalyzer: processDirectory(datafiles..)");
		File in = new File(inputDirectory);
		int pos = (in.isDirectory() ? in.getAbsolutePath().length() : in
				.getParent().length());
		dataLoad(dinflectionsFile, dprefixesFile, gimartiasFile,
				dmwinflectionsFile, mwe1File, mwe2File, mwe3File, mwe4File);
		analyzeDirectory(in, outputDirectory, pos);
	}

	/**
	 * This method is used for mass processing without dataLoading- It can
	 * process directory of files/directories. It is especially useful for
	 * processing corpora.
	 * 
	 * @param inputDirectory
	 * @param outputDirectory
	 */
	@Override
	public void processDirectoryNoDataLoad(String inputDirectory,
			String outputDirectory) {
		File in = new File(inputDirectory);
		int pos = (in.isDirectory() ? in.getAbsolutePath().length() : in
				.getParent().length());
		analyzeDirectory(in, outputDirectory, pos);
	}

	/**
	 * This method is used for processing a single xml already tokenized file in
	 * database mode working. This input file is prepared with Mila tokenizer.
	 * 
	 * @param inputFile
	 *            - xml tokenized file
	 * @param outputFile
	 *            - xml output morphololgically analyzed file
	 */
	@Override
	public void processSingleFile(String inputFile, String outputFile) {
		System.out.println("(F)MWXMLMorphAnalyzer:processSingleFile()");
		dataLoad();
		analyzeFile(inputFile, outputFile);
		PostProcessor1 postProcessor = new PostProcessor1(!Data.webFlag);
		try {
			postProcessor.process(outputFile, outputFile);
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JAXBException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	// -------------------------------------------------------------------------------------------------------
	/**
	 * This method is used for processing a single xml already tokenized file in
	 * data files mode working. This input file is prepared with Mila tokenizer.
	 * 
	 * @param inputFile
	 *            xml tokenized file to be morphologically analyzed
	 * @param outputFile
	 *            morphologically analyzed xml file to be created path and name
	 * @param dinflectionsFile
	 *            inflections data file path
	 * @param dprefixesFile
	 *            prefixes data file path
	 * @param gimartiasFile
	 *            gimatrias data file path
	 */
	public void processSingleFile(String inputFile, String outputFile,
			String dinflectionsFile, String dprefixesFile,
			String gimartiasFile, String dmwinflectionsFile, String mwe1File,
			String mwe2File, String mwe3File, String mwe4File) {
		dataLoad(dinflectionsFile, dprefixesFile, gimartiasFile,
				dmwinflectionsFile, mwe1File, mwe2File, mwe3File, mwe4File);
		analyzeFile(inputFile, outputFile);
		PostProcessor1 postProcessor = new PostProcessor1(!Data.webFlag);
		try {
			postProcessor.process(outputFile, outputFile);
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JAXBException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * 
	 */
	private void ReadXMLFile(String tokenizationOutputStr, PrintWriter pw) {
		// System.out.println("(F) ReadXMLFile() ");
		InputStream in = null;
		try {
			in = new ByteArrayInputStream(
					tokenizationOutputStr.getBytes("UTF-8"));
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		MWCreateCorpusXML mwcreateXML = new MWCreateCorpusXML();
		mwcreateXML.createXMLDoc();
		mwcreateXML.createArticle();

		MWTokenizationParser mwtokenizationParser = new MWTokenizationParser(
				mwcreateXML);
		mwtokenizationParser.parse(in);
		mwcreateXML.printDoc(pw);
	}

	/**
	 * 
	 */
	private void ReadXMLFile(String inputFile, String outputFile) {
		InputStream in = null;
		try {
			in = new FileInputStream(new File(inputFile));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// Date now = new Date();
		// System.out.println("XMLAnalyzer: processXMLOutput Starts At"
		// + DateFormat.getDateTimeInstance(DateFormat.SHORT,
		// DateFormat.SHORT).format(now));
		// System.out.println("ReadXMLFile: "+inputFile);
		MWCreateCorpusXML mwcreateXML = new MWCreateCorpusXML(outputFile);
		mwcreateXML.createXMLDoc();
		mwcreateXML.createArticle();

		MWTokenizationParser mwtokenizationParser = new MWTokenizationParser(
				mwcreateXML);
		mwtokenizationParser.parse(in); // in gets input from input file
		mwcreateXML.printDoc();
	}

}
