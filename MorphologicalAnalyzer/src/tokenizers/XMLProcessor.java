/*

 * Created on 18/07/2005

 *

 * TODO To change the template for this generated file go to

 * Window - Preferences - Java - Code Style - Code Templates

 */

package tokenizers;

import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.text.DateFormat;
import java.util.Date;
import java.util.HashMap;

import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

/**
 * 
 * 
 * 
 * XMLProcessor.java Purpose: build the XML of the output of the tokenizer using
 * j4dom
 * 
 * 
 * 
 * @author Dalia Bojan
 * 
 * @version %G%
 * 
 */

public class XMLProcessor {

	/**
	 * 
	 * @param heb
	 * 
	 * @return
	 * 
	 */

	public static String getHebToEng(String heb) {

		if (hebToEng == null) {

			initHebToEng();

		}

		return (String) hebToEng.get(heb);

	}

	/**
	 * 
	 * @param hebStr
	 * 
	 * @return
	 * 
	 */

	private static String translateHebEng(String hebStr) {

		StringBuffer transliterated = new StringBuffer();

		String transliteratedStr = "";

		char curChar;

		char transChar;

		for (int i = 0; i < hebStr.length(); i++) {

			curChar = hebStr.charAt(i);

			if ((curChar >= 'א') && (curChar <= 'ת')) {

				String charStr = (new Character(curChar)).toString();

				transChar = getHebToEng(charStr).charAt(0);

				transliterated.append(transChar);

			}

			else

				transliterated.append(curChar);

		}

		transliteratedStr = transliterated.toString();

		return transliteratedStr;

	}

	Document document;

	int countSentence = 0;

	int countParagraph = 0;

	int countToken = 0;

	Element root;

	Element article;

	Element paragraph;

	Element sentence;

	Element token;

	int globalTokenCounter = 0;

	private static HashMap hebToEng;

	private static void initHebToEng() {

		hebToEng = new HashMap();

		hebToEng.put("א", "a");

		hebToEng.put("ב", "b");

		hebToEng.put("ג", "g");

		hebToEng.put("ד", "d");

		hebToEng.put("ה", "h");

		hebToEng.put("ו", "w");

		hebToEng.put("ז", "z");

		hebToEng.put("ח", "x");

		hebToEng.put("ט", "v");

		hebToEng.put("י", "i");

		hebToEng.put("כ", "k");

		hebToEng.put("ך", "k");

		hebToEng.put("ל", "l");

		hebToEng.put("מ", "m");

		hebToEng.put("ם", "m");

		hebToEng.put("נ", "n");

		hebToEng.put("ן", "n");

		hebToEng.put("ס", "s");

		hebToEng.put("ע", "y");

		hebToEng.put("פ", "p");

		hebToEng.put("ף", "p");

		hebToEng.put("צ", "c");

		hebToEng.put("ץ", "c");

		hebToEng.put("ק", "q");

		hebToEng.put("ר", "r");

		hebToEng.put("ש", "e");

		hebToEng.put("ת", "t");

	}

	public void createArticle() {

		article = root.addElement("article");

		article.addAttribute("id", "1");

	}

	/**
	 * 
	 * 
	 * 
	 */

	public void createDocument() {

		document = DocumentHelper.createDocument();

		root = document.addElement("corpus");

		root.addAttribute("name", "Demo Hebrew tokenized text in XML");

		root.addAttribute("version", "1.6.0").addAttribute("maintainer",

		"Yossi Jacob").addAttribute("email", "mila@cs.technion.ac.il");

		Element metadata = root.addElement("metadata");

		metadata.addElement("name")

		.addText("Demo Hebrew tokenized text in XML");

		metadata.addElement("version").addText("1.0");

		Date now = new Date();

		metadata.addElement("date").addText(

		DateFormat.getDateTimeInstance(DateFormat.SHORT,

		DateFormat.SHORT).format(now));

		metadata.addElement("maintainer").addText("Yossi Jacob");

		metadata.addElement("email").addText("mila@cs.technion.ac.il");

		metadata

		.addElement("license")

				.addText(

						"Tokenization and XML representation: Copyright (C) 2009 Mila.\n"

								+

								"This resource is released to the public licensed under the GNU Free Documentation License\n."

								+

								"Note that all software, data files and documentation are licensed under the FDL.\n"

								+

								"There is no warranty of any kind for the contents of this distribution.");
		/*
		 * Element transliteration =
		 * metadata.addElement("transliteration").addAttribute("from", "he");
		 * 
		 * transliteration.addElement("string ").addAttribute("hebrew",
		 * "א").addAttribute("latin", "a");
		 * 
		 * transliteration.addElement("string ").addAttribute("hebrew",
		 * "ב").addAttribute("latin", "b");
		 * 
		 * transliteration.addElement("string ").addAttribute("hebrew",
		 * "ג").addAttribute("latin", "g");
		 * 
		 * transliteration.addElement("string ").addAttribute("hebrew",
		 * "ד").addAttribute("latin", "d");
		 * 
		 * transliteration.addElement("string ").addAttribute("hebrew",
		 * "ה").addAttribute("latin", "h");
		 * 
		 * transliteration.addElement("string ").addAttribute("hebrew",
		 * "ו").addAttribute("latin", "w");
		 * 
		 * transliteration.addElement("string ").addAttribute("hebrew",
		 * "ז").addAttribute("latin", "z");
		 * 
		 * transliteration.addElement("string ").addAttribute("hebrew",
		 * "ח").addAttribute("latin", "x");
		 * 
		 * transliteration.addElement("string ").addAttribute("hebrew",
		 * "ט").addAttribute("latin", "v");
		 * 
		 * transliteration.addElement("string ").addAttribute("hebrew",
		 * "י").addAttribute("latin", "i");
		 * 
		 * transliteration.addElement("string ").addAttribute("hebrew",
		 * "כ").addAttribute("latin", "k");
		 * 
		 * transliteration.addElement("string ").addAttribute("hebrew",
		 * "ך").addAttribute("latin", "k");
		 * 
		 * transliteration.addElement("string ").addAttribute("hebrew",
		 * "ל").addAttribute("latin", "l");
		 * 
		 * transliteration.addElement("string ").addAttribute("hebrew",
		 * "מ").addAttribute("latin", "m");
		 * 
		 * transliteration.addElement("string ").addAttribute("hebrew",
		 * "ם").addAttribute("latin", "m");
		 * 
		 * transliteration.addElement("string ").addAttribute("hebrew",
		 * "נ").addAttribute("latin", "n");
		 * 
		 * transliteration.addElement("string ").addAttribute("hebrew",
		 * "ן").addAttribute("latin", "n");
		 * 
		 * transliteration.addElement("string ").addAttribute("hebrew",
		 * "ס").addAttribute("latin", "s");
		 * 
		 * transliteration.addElement("string ").addAttribute("hebrew",
		 * "ע").addAttribute("latin", "y");
		 * 
		 * transliteration.addElement("string ").addAttribute("hebrew",
		 * "פ").addAttribute("latin", "p");
		 * 
		 * transliteration.addElement("string ").addAttribute("hebrew",
		 * "ף").addAttribute("latin", "p");
		 * 
		 * transliteration.addElement("string ").addAttribute("hebrew",
		 * "צ").addAttribute("latin", "c");
		 * 
		 * transliteration.addElement("string ").addAttribute("hebrew",
		 * "ץ").addAttribute("latin", "c");
		 * 
		 * transliteration.addElement("string ").addAttribute("hebrew",
		 * "ק").addAttribute("latin", "q");
		 * 
		 * transliteration.addElement("string ").addAttribute("hebrew",
		 * "ר").addAttribute("latin", "r");
		 * 
		 * transliteration.addElement("string ").addAttribute("hebrew",
		 * "ש").addAttribute("latin", "e");
		 * 
		 * transliteration.addElement("string ").addAttribute("hebrew",
		 * "ת").addAttribute("latin", "t");
		 */
		metadata.addElement("comment")
				.addText(
						"Tokenized corpuses and the segmentation program are available at http://mila.cs.technion.ac.il/");
	}

	public void createParapraphes() {

		if ((countToken == 0) && (countParagraph > 0))

			paragraph.remove(sentence);

		countToken = 0;

		countSentence = 0;

		countParagraph++;

		String countst = String.valueOf(countParagraph);

		paragraph = article.addElement("paragraph").addAttribute("id", countst);

	}

	public void createSentences() {

		countToken = 0;

		countSentence++;

		String countst = String.valueOf(countSentence);

		sentence = paragraph.addElement("sentence").addAttribute("id", countst);

	}

	/**
	 * 
	 * @param word
	 * 
	 */

	public void createTokens(String word) {

		countToken++;

		globalTokenCounter++;

		String counttk = String.valueOf(countToken);

		String transliterated = translateHebEng(word);

		try {

			token = sentence.addElement("token").addAttribute("id", counttk)

			.addAttribute("surface", word).addAttribute(

			"transliterated", transliterated);

			// System.out.println(word);

		} catch (Exception e) {

			// TODO Auto-generated catch block

			e.printStackTrace();

		}

	}

	public void finalizeDoc() {

		if ((countToken == 0) && (countParagraph > 0))

			paragraph.remove(sentence);

	}

	/**
	 * 
	 * @return Returns the document.
	 * 
	 */

	public Document getDocument() {

		return document;

	}

	/**
	 * 
	 * @return Returns the globalTokenCounter.
	 * 
	 */

	public int getGlobalTokenCounter() {

		return globalTokenCounter;

	}

	public String printDoc() {

		if ((countToken == 0) && (countParagraph > 0))

			paragraph.remove(sentence);

		return document.asXML();

	}

	/**
	 * 
	 * @param outputFileName
	 * 
	 */

	public void printDoc(String outputFileName) {

		if ((countToken == 0) && (countParagraph > 0))

			paragraph.remove(sentence);

		String text = document.asXML();

		FileOutputStream out = null;

		try {

			out = new FileOutputStream(outputFileName);

		} catch (FileNotFoundException e) {

			System.out

			.println("XMLProcessor: printDoc - FileNotFoundException");

			e.printStackTrace();

		}

		OutputStreamWriter pOut = null;

		try {

			pOut = new OutputStreamWriter(out, "UTF8");

		} catch (UnsupportedEncodingException e4) {

			System.out

			.println("XMLProcessor: printDoc - UnsupportedEncodingException");

			e4.printStackTrace();

		}

		BufferedWriter bw = new BufferedWriter(pOut);

		try {

			bw.write(text);

			bw.close();

			pOut.close();

		} catch (IOException e3) {

			System.out

					.println("XMLProcessor: printDoc - Exception while writing/closing buffered writer");

			e3.printStackTrace();

		}

	}

	/**
	 * 
	 * @param document
	 * 
	 *            The document to set.
	 * 
	 */

	public void setDocument(Document document) {

		this.document = document;

	}

}
