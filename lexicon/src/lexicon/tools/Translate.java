/*
 * Created on 04/07/2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package lexicon.tools;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.HashMap;

/**
 * @author daliabo
 * 
 *         TODO To change the template for this generated type comment go to
 *         Window - Preferences - Java - Code Style - Code Templates
 */
public class Translate {

	private static HashMap hebToEng;

	public static String Eng2Heb(String engString) {
		final StringBuffer result = new StringBuffer();
		String hebString = "";
		String encodedHeb = "";
		for (int i = 0; i < engString.length(); i++) {

			hebString = Names.getEngToHeb(String.valueOf(engString.charAt(i)));
			try {
				encodedHeb = URLDecoder.decode(hebString, "UTF-8");
				if (((encodedHeb.equals("ך") && (i < (engString.length() - 1)) && (engString
						.charAt(i + 1) != '-')))
						|| ((encodedHeb.equals("ך") && (i == (engString
								.length() - 1))) && (engString.charAt(engString
								.length() - 2) == '"'))) {
					encodedHeb = "כ";
				}
				if (((encodedHeb.equals("ם") && (i < (engString.length() - 1)) && (engString
						.charAt(i + 1) != '-')))
						|| ((encodedHeb.equals("ם") && (i == (engString
								.length() - 1))) && (engString.charAt(engString
								.length() - 2) == '"'))) {
					encodedHeb = "מ";
				}
				if (((encodedHeb.equals("ן") && (i < (engString.length() - 1)) && (engString
						.charAt(i + 1) != '-')))
						|| ((encodedHeb.equals("ן") && (i == (engString
								.length() - 1))) && (engString.charAt(engString
								.length() - 2) == '"'))) {
					encodedHeb = "נ";
				}
				if (((encodedHeb.equals("ף") && (i < (engString.length() - 1)) && (engString
						.charAt(i + 1) != '-')))
						|| ((encodedHeb.equals("ף") && (i == (engString
								.length() - 1))) && (engString.charAt(engString
								.length() - 2) == '"'))) {
					encodedHeb = "פ";
				}
				if (((encodedHeb.equals("ץ") && (i < (engString.length() - 1)) && (engString
						.charAt(i + 1) != '-')))
						|| ((encodedHeb.equals("ץ") && (i == (engString
								.length() - 1))) && (engString.charAt(engString
								.length() - 2) == '"'))) {
					encodedHeb = "צ";
				}
			} catch (final Exception e) {
			}
			result.append(encodedHeb);
		}
		return result.toString();
	}

	public static String getHebToEng(String heb) {
		if (Translate.hebToEng == null) {
			Translate.initHebToEng();
		}
		return (String) Translate.hebToEng.get(heb);
	}

	public static String Heb2Eng(String hebStr) {
		String transliteratedStr = "";
		final StringBuffer transliterated = new StringBuffer();
		char curChar;
		char transChar;
		for (int i = 0; i < hebStr.length(); i++) {
			curChar = hebStr.charAt(i);
			if ((curChar >= 'א') && (curChar <= 'ת')) {
				final String charStr = (new Character(curChar)).toString();
				transChar = Translate.getHebToEng(charStr).charAt(0);
				transliterated.append(transChar);
			} else {
				transliterated.append(curChar);
			}
		}

		transliteratedStr = transliterated.toString();
		// System.out.println("transliteratedStr ="+ transliteratedStr );
		return transliteratedStr;

	}

	private static void initHebToEng() {
		Translate.hebToEng = new HashMap();
		Translate.hebToEng.put("א", "a");
		Translate.hebToEng.put("ב", "b");
		Translate.hebToEng.put("ג", "g");
		Translate.hebToEng.put("ד", "d");
		Translate.hebToEng.put("ה", "h");
		Translate.hebToEng.put("ו", "w");
		Translate.hebToEng.put("ז", "z");
		Translate.hebToEng.put("ח", "x");
		Translate.hebToEng.put("ט", "v");
		Translate.hebToEng.put("י", "i");
		Translate.hebToEng.put("ך", "k");
		Translate.hebToEng.put("כ", "k");
		Translate.hebToEng.put("ל", "l");
		Translate.hebToEng.put("ם", "m");
		Translate.hebToEng.put("מ", "m");
		Translate.hebToEng.put("ן", "n");
		Translate.hebToEng.put("נ", "n");
		Translate.hebToEng.put("ס", "s");
		Translate.hebToEng.put("ע", "y");
		Translate.hebToEng.put("ף", "p");
		Translate.hebToEng.put("פ", "p");
		Translate.hebToEng.put("ץ", "c");
		Translate.hebToEng.put("צ", "c");
		Translate.hebToEng.put("ק", "q");
		Translate.hebToEng.put("ר", "r");
		Translate.hebToEng.put("ש", "e");
		Translate.hebToEng.put("ת", "t");
	}

	public static void main(String[] args) {

		final String hebStr = "י-ם";
		final String newStr = Translate.Heb2Eng(hebStr);
		System.out.println("newStr = " + newStr);
	}

	public static String MixedHebEng(String inStr) {
		final StringBuffer result = new StringBuffer();

		String encodedStr = "";
		for (int i = 0; i < inStr.length(); i++) {
			char curChar = inStr.charAt(i);

			if (curChar == '%') {
				final StringBuffer tempStr = new StringBuffer();
				while ((i < inStr.length()) && (curChar != '[')) {
					curChar = inStr.charAt(i);
					i++;
					tempStr.append(String.valueOf(curChar));
				}
				try {
					encodedStr = URLDecoder.decode(tempStr.toString(), "UTF-8");
					System.out.println("encodedStr = " + encodedStr);
				} catch (final UnsupportedEncodingException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				result.append(encodedStr);
			} else if (((curChar >= 'a') && (curChar <= 'z'))
					|| (((curChar >= 'A') && (curChar <= 'Z')))) {
				result.append(String.valueOf(curChar));
			} else if ((curChar == '[') || (curChar == ']') || (curChar == '+')
					|| (curChar == '/')) {
				result.append(String.valueOf(curChar));
			}
		}
		return result.toString();
	}

}
