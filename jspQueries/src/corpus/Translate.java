/*
 * @author dalia bojan
 * @version 1.0
 * 
 */
package corpus;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.HashMap;

/**
 * @author daliabo
 * 
 * TODO To change the template for this generated type comment go to Window -
 * Preferences - Java - Code Style - Code Templates
 */
public class Translate {

	private static HashMap<String, String> hebToEng;

	private static HashMap<String, String> engToHeb;

	private static void initEngToHeb() {
		engToHeb = new HashMap<>();
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
		hebToEng = new HashMap<>();
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

	public static String getHebToEng(String heb) {
		if (hebToEng == null) {
			initHebToEng();
		}
		return hebToEng.get(heb);
	}

	public static String getEngToHeb(String eng) {
		if (engToHeb == null) {
			initEngToHeb();
		}
		return engToHeb.get(eng);
	}

	

	public static String MixedHebEng(String inStr) {
		StringBuilder result = new StringBuilder();

		String encodedStr = "";
		for (int i = 0; i < inStr.length(); i++) {
			char curChar = inStr.charAt(i);

			if (curChar == '%') {
				StringBuilder tempStr = new StringBuilder();
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
			} else if ((curChar >= 'a' && curChar <= 'z')
					|| ((curChar >= 'A' && curChar <= 'Z'))) {
				result.append(String.valueOf(curChar));
			} else if ((curChar == '[') || (curChar == ']') || (curChar == '+')
					|| (curChar == '/')) {
				result.append(String.valueOf(curChar));
			}
		}
		return result.toString();
	}

	public static String Eng2Heb(String engString) {
		StringBuilder result = new StringBuilder();
		String hebString;
		String encodedHeb = "";
		for (int i = 0; i < engString.length(); i++) {

			hebString = Translate.getEngToHeb(String.valueOf(engString
					.charAt(i)));
			try {
				encodedHeb = URLDecoder.decode(hebString, "UTF-8");
				if (((encodedHeb.equals("ך") && i < (engString.length() - 1) && engString
						.charAt(i + 1) != '-'))
						|| ((encodedHeb.equals("ך") && (i == engString.length() - 1)) && (engString
								.charAt(engString.length() - 2) == '"')))
					encodedHeb = "כ";
				if (((encodedHeb.equals("ם") && i < (engString.length() - 1) && engString
						.charAt(i + 1) != '-'))
						|| ((encodedHeb.equals("ם") && (i == engString.length() - 1)) && (engString
								.charAt(engString.length() - 2) == '"')))
					encodedHeb = "מ";
				if (((encodedHeb.equals("ן") && i < (engString.length() - 1) && engString
						.charAt(i + 1) != '-'))
						|| ((encodedHeb.equals("ן") && (i == engString.length() - 1)) && (engString
								.charAt(engString.length() - 2) == '"')))
					encodedHeb = "נ";
				if (((encodedHeb.equals("ף") && i < (engString.length() - 1) && engString
						.charAt(i + 1) != '-'))
						|| ((encodedHeb.equals("ף") && (i == engString.length() - 1)) && (engString
								.charAt(engString.length() - 2) == '"')))
					encodedHeb = "פ";
				if (((encodedHeb.equals("ץ") && i < (engString.length() - 1) && engString
						.charAt(i + 1) != '-'))
						|| ((encodedHeb.equals("ץ") && (i == engString.length() - 1)) && (engString
								.charAt(engString.length() - 2) == '"')))
					encodedHeb = "צ";
			} catch (Exception e) {
			}
			result.append(encodedHeb);
		}
		return result.toString();
	}

	public static String Heb2Eng(String hebStr) {
		StringBuilder transliterated = new StringBuilder();
		String transliteratedStr;
		char curChar;
		char transChar;
		for (int i = 0; i < hebStr.length(); i++) {
			curChar = hebStr.charAt(i);
			if ((curChar >= 'א') && (curChar <= 'ת')) {
				String charStr = (new Character(curChar)).toString();
				transChar = getHebToEng(charStr).charAt(0);
				transliterated.append(transChar);
			}
			if ((curChar == '-') || (curChar == ',') || (curChar == ';') || (curChar == '.') || (curChar == ':') || (curChar == '–') || (curChar == '(') || (curChar == ')') || (curChar == ']') || (curChar == '[') || (curChar == '\"') || (curChar == '?') || (curChar == '*') || (curChar == '\'') || (curChar == '!') || (curChar == '@') || (curChar == '#') || (curChar == '$') || (curChar == '%') || (curChar == '^') || (curChar == '&') || (curChar == '_') || (curChar == '=') || (curChar == '+') || (curChar == '/') || (curChar == '{') || (curChar == '}') || (curChar == '<') || (curChar == '>')) {
				transliterated.append(curChar);
			}
			if ((curChar >= '0') && (curChar <= '9')) {
				transliterated.append(curChar);
			}
			if ((curChar >= 'A') && (curChar <= 'Z')) {
				transliterated.append(curChar);
			}
			if ((curChar >= 'a') && (curChar <= 'z')) {
				transliterated.append(curChar);
			}
		}
		transliteratedStr = transliterated.toString();
		return transliteratedStr;

	}

	
}
