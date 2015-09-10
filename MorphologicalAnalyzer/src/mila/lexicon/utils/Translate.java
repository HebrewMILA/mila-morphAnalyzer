/*
 * @author dalia bojan
 * @version 1.0
 * 
 */
package mila.lexicon.utils;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * @author daliabo
 * 
 *         TODO To change the template for this generated type comment go to
 *         Window - Preferences - Java - Code Style - Code Templates
 */
public class Translate {
	private static HashMap<String, String> hebToEng;
	private static HashMap<String, String> engToHeb;

	public static List<PrefixRec> analyzeMixedHebEng(String inStr) throws UnsupportedEncodingException {
		PrefixRec prefixRec = null;
		StringBuffer result = null;
		StringBuffer tempStr = null;
		char tempChar;
		String encodedStr = "";
		ArrayList<PrefixRec> prefixList = new ArrayList<PrefixRec>();
		int i = 0;
		int len = inStr.length();
		while (i < len) {
			// get function
			result = new StringBuffer();
			prefixRec = new PrefixRec();
			for (int j = i + 2; (tempChar = inStr.charAt(j)) != ']' && j < len - 1; j++) {
				result.append(tempChar);
				i++;
			}
			prefixRec.setFunction(result.toString());
			// get surface
			char curChar;
			tempStr = new StringBuffer();
			i = i + 3;
			while ((i < len) && ((curChar = inStr.charAt(i)) != '[')) {
				tempStr.append(String.valueOf(curChar));
				i++;
			}
			encodedStr = URLDecoder.decode(tempStr.toString(), "UTF-8");
			prefixRec.setSurface(encodedStr);
			prefixList.add(prefixRec);
		}
		return prefixList;
	}

	public static String Eng2Heb(String engString) {
		// System.out.println("(F) Eng2Heb("+engString+")");
		StringBuffer result = new StringBuffer();
		String hebString = "";
		String encodedHeb = "";

		for (int i = 0; i < engString.length(); i++) {

			hebString = Translate.getEngToHeb(String.valueOf(engString.charAt(i)));
			try {
				encodedHeb = URLDecoder.decode(hebString, "UTF-8");
				if (((encodedHeb.equals("ך") && i < (engString.length() - 1) && engString.charAt(i + 1) != '-'))
						|| ((encodedHeb.equals("ך") && (i == engString.length() - 1))
								&& (engString.charAt(engString.length() - 2) == '"')))
					encodedHeb = "כ";
				if (((encodedHeb.equals("ם") && i < (engString.length() - 1) && engString.charAt(i + 1) != '-'))
						|| ((encodedHeb.equals("ם") && (i == engString.length() - 1))
								&& (engString.charAt(engString.length() - 2) == '"')))
					encodedHeb = "מ";
				if (((encodedHeb.equals("ן") && i < (engString.length() - 1) && engString.charAt(i + 1) != '-'))
						|| ((encodedHeb.equals("ן") && (i == engString.length() - 1))
								&& (engString.charAt(engString.length() - 2) == '"')))
					encodedHeb = "נ";
				if (((encodedHeb.equals("ף") && i < (engString.length() - 1) && engString.charAt(i + 1) != '-'))
						|| ((encodedHeb.equals("ף") && (i == engString.length() - 1))
								&& (engString.charAt(engString.length() - 2) == '"')))
					encodedHeb = "פ";
				if (((encodedHeb.equals("ץ") && i < (engString.length() - 1) && engString.charAt(i + 1) != '-'))
						|| ((encodedHeb.equals("ץ") && (i == engString.length() - 1))
								&& (engString.charAt(engString.length() - 2) == '"')))
					encodedHeb = "צ";
			} catch (Exception e) {
			}
			result.append(encodedHeb);
		}
		return result.toString();
	}

	public static String getEngToHeb(String eng) {
		// System.out.println("(F) getEngToHeb("+eng+")");
		if (engToHeb == null) {
			initEngToHeb();
		}
		return (String) engToHeb.get(eng);
	}

	public static String getHebToEng(String heb) {
		// System.out.println("(F) getHebToEng("+heb+")");
		if (hebToEng == null) {
			initHebToEng();
		}
		return (String) hebToEng.get(heb);
	}

	public static String Heb2Eng(String hebStr) {
		// System.out.println("(F) Heb2Eng("+hebStr+")");
		String transliteratedStr = "";
		StringBuffer transliterated = new StringBuffer();
		char curChar;
		char transChar;
		for (int i = 0; i < hebStr.length(); i++) {
			curChar = hebStr.charAt(i);
			if ((curChar >= 'א') && (curChar <= 'ת')) {
				String charStr = (new Character(curChar)).toString();
				transChar = getHebToEng(charStr).charAt(0);
				transliterated.append(transChar);
			} else
				transliterated.append(curChar);
		}
		transliteratedStr = transliterated.toString();
		return transliteratedStr;
	}

	public static String HMMHeb2Eng(String hebStr) {
		StringBuffer transliterated = new StringBuffer();
		String transliteratedStr = "";
		char curChar;
		char transChar = 0;
		int hebStrLen = hebStr.length();
		for (int i = 0; i < hebStrLen; i++) {
			curChar = hebStr.charAt(i);
			if ((curChar >= 'א') && (curChar <= 'ת')) {
				String charStr = (new Character(curChar)).toString();
				transChar = getHebToEng(charStr).charAt(0);
				if (i == hebStrLen - 1 && transChar == 'm') {
					transChar = 'M';
				}
				if (i == hebStrLen - 1 && transChar == 'n') {
					transChar = 'N';
				}
				if (i == hebStrLen - 1 && transChar == 'k') {
					transChar = 'K';
				}
				if (i == hebStrLen - 1 && transChar == 'p') {
					transChar = 'P';
				}
				if (i == hebStrLen - 1 && transChar == 'c') {
					transChar = 'C';
				}
				transliterated.append(transChar);
			} else
				transliterated.append(curChar);
		}
		transliteratedStr = transliterated.toString();
		return transliteratedStr;
	}

	private static void initEngToHeb() {
		engToHeb = new HashMap<String, String>();
		engToHeb.put("a", "%D7%90");
		engToHeb.put("b", "%D7%91");
		engToHeb.put("g", "%D7%92");
		engToHeb.put("d", "%D7%93");
		engToHeb.put("h", "%D7%94");
		engToHeb.put("w", "%D7%95");
		engToHeb.put("z", "%D7%96");
		engToHeb.put("x", "%D7%97");
		engToHeb.put("v", "%D7%98");
		engToHeb.put("i", "%D7%99");
		engToHeb.put("k", "%D7%9B");
		engToHeb.put("l", "%D7%9C");
		engToHeb.put("m", "%D7%9E");
		engToHeb.put("n", "%D7%A0");
		engToHeb.put("s", "%D7%A1");
		engToHeb.put("y", "%D7%A2");
		engToHeb.put("p", "%D7%A4");
		engToHeb.put("c", "%D7%A6");
		engToHeb.put("q", "%D7%A7");
		engToHeb.put("r", "%D7%A8");
		engToHeb.put("e", "%D7%A9");
		engToHeb.put("t", "%D7%AA");

		// Sofiot
		engToHeb.put("k", "%D7%9A");
		engToHeb.put("m", "%D7%9D");
		engToHeb.put("n", "%D7%9F");
		engToHeb.put("p", "%D7%A3");
		engToHeb.put("c", "%D7%A5");

		// Punctuation marks
		engToHeb.put("-", "-");
		engToHeb.put("'", "%27");
		engToHeb.put("\"", "%22");

	}

	private static void initHebToEng() {
		hebToEng = new HashMap<String, String>();
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
		hebToEng.put("ך", "k");
		hebToEng.put("כ", "k");
		hebToEng.put("ל", "l");
		hebToEng.put("ם", "m");
		hebToEng.put("מ", "m");
		hebToEng.put("ן", "n");
		hebToEng.put("נ", "n");
		hebToEng.put("ס", "s");
		hebToEng.put("ע", "y");
		hebToEng.put("ף", "p");
		hebToEng.put("פ", "p");
		hebToEng.put("ץ", "c");
		hebToEng.put("צ", "c");
		hebToEng.put("ק", "q");
		hebToEng.put("ר", "r");
		hebToEng.put("ש", "e");
		hebToEng.put("ת", "t");
	}

	public static void main(String[] args) throws UnsupportedEncodingException {
		analyzeMixedHebEng("[+conjunction]%D7%95[+preposition]%D7%91[+definiteArticle]");
	}

	public static String MixedHebEng(String inStr) {
		StringBuffer result = new StringBuffer();
		String encodedStr = "";
		for (int i = 0; i < inStr.length(); i++) {
			char curChar = inStr.charAt(i);

			if (curChar == '%') {
				StringBuffer tempStr = new StringBuffer();
				while ((i < inStr.length()) && (curChar != '[')) {
					curChar = inStr.charAt(i);
					i++;
					tempStr.append(String.valueOf(curChar));
				}
				try {
					encodedStr = URLDecoder.decode(tempStr.toString(), "UTF-8");
				} catch (UnsupportedEncodingException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				result.append(encodedStr);
			} else if ((curChar >= 'a' && curChar <= 'z') || ((curChar >= 'A' && curChar <= 'Z'))) {
				result.append(String.valueOf(curChar));
			} else if ((curChar == '[') || (curChar == ']') || (curChar == '+') || (curChar == '/')) {
				result.append(String.valueOf(curChar));
			}
		}
		return result.toString();
	}
}
