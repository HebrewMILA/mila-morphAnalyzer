/*
 * Created on 04/07/2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package lexicon.stringUtils;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.HashMap;

import lexicon.tools.Names;

/**
 * @author daliabo
 * 
 *         TODO To change the template for this generated type comment go to
 *         Window - Preferences - Java - Code Style - Code Templates
 */
public class Translate {

	private static HashMap hebToEng;

	private static void initHebToEng() {
		hebToEng = new HashMap();
		hebToEng.put("�", "a");
		hebToEng.put("�", "b");
		hebToEng.put("�", "g");
		hebToEng.put("�", "d");
		hebToEng.put("�", "h");
		hebToEng.put("�", "w");
		hebToEng.put("�", "z");
		hebToEng.put("�", "x");
		hebToEng.put("�", "v");
		hebToEng.put("�", "i");
		hebToEng.put("�", "k");
		hebToEng.put("�", "k");
		hebToEng.put("�", "l");
		hebToEng.put("�", "m");
		hebToEng.put("�", "m");
		hebToEng.put("�", "n");
		hebToEng.put("�", "n");
		hebToEng.put("�", "s");
		hebToEng.put("�", "y");
		hebToEng.put("�", "p");
		hebToEng.put("�", "p");
		hebToEng.put("�", "c");
		hebToEng.put("�", "c");
		hebToEng.put("�", "q");
		hebToEng.put("�", "r");
		hebToEng.put("�", "e");
		hebToEng.put("�", "t");
	}

	public static String getHebToEng(String heb) {
		if (hebToEng == null) {
			initHebToEng();
		}
		return (String) hebToEng.get(heb);
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
					System.out.println("encodedStr = " + encodedStr);
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

	public static String Eng2Heb(String engString) {
		StringBuffer result = new StringBuffer();
		String hebString = "";
		String encodedHeb = "";
		int index = 0;
		for (int i = 0; i < engString.length(); i++) {

			hebString = Names.getEngToHeb(String.valueOf(engString.charAt(i)));
			try {
				encodedHeb = URLDecoder.decode(hebString, "UTF-8");
				if (((encodedHeb.equals("�") && i < (engString.length() - 1) && engString.charAt(i + 1) != '-'))
						|| ((encodedHeb.equals("�") && (i == engString.length() - 1))
								&& (engString.charAt(engString.length() - 2) == '"')))
					encodedHeb = "�";
				if (((encodedHeb.equals("�") && i < (engString.length() - 1) && engString.charAt(i + 1) != '-'))
						|| ((encodedHeb.equals("�") && (i == engString.length() - 1))
								&& (engString.charAt(engString.length() - 2) == '"')))
					encodedHeb = "�";
				if (((encodedHeb.equals("�") && i < (engString.length() - 1) && engString.charAt(i + 1) != '-'))
						|| ((encodedHeb.equals("�") && (i == engString.length() - 1))
								&& (engString.charAt(engString.length() - 2) == '"')))
					encodedHeb = "�";
				if (((encodedHeb.equals("�") && i < (engString.length() - 1) && engString.charAt(i + 1) != '-'))
						|| ((encodedHeb.equals("�") && (i == engString.length() - 1))
								&& (engString.charAt(engString.length() - 2) == '"')))
					encodedHeb = "�";
				if (((encodedHeb.equals("�") && i < (engString.length() - 1) && engString.charAt(i + 1) != '-'))
						|| ((encodedHeb.equals("�") && (i == engString.length() - 1))
								&& (engString.charAt(engString.length() - 2) == '"')))
					encodedHeb = "�";
			} catch (Exception e) {
			}
			result.append(encodedHeb);
		}
		return result.toString();
	}

	public static String saebEng2Heb(String engString) {
		engString = engString.replaceAll("U", "\\\"");
		engString = engString.toLowerCase();
		StringBuffer result = new StringBuffer();
		String hebString = "";
		String encodedHeb = "";
		int index = 0;
		for (int i = 0; i < engString.length(); i++) {

			hebString = Names.getEngToHeb(String.valueOf(engString.charAt(i)));
			try {
				encodedHeb = URLDecoder.decode(hebString, "UTF-8");
				if (((encodedHeb.equals("�") && i < (engString.length() - 1) && engString.charAt(i + 1) != '-'))
						|| ((encodedHeb.equals("�") && (i == engString.length() - 1))
								&& (engString.charAt(engString.length() - 2) == '"')))
					encodedHeb = "�";
				if (((encodedHeb.equals("�") && i < (engString.length() - 1) && engString.charAt(i + 1) != '-'))
						|| ((encodedHeb.equals("�") && (i == engString.length() - 1))
								&& (engString.charAt(engString.length() - 2) == '"')))
					encodedHeb = "�";
				if (((encodedHeb.equals("�") && i < (engString.length() - 1) && engString.charAt(i + 1) != '-'))
						|| ((encodedHeb.equals("�") && (i == engString.length() - 1))
								&& (engString.charAt(engString.length() - 2) == '"')))
					encodedHeb = "�";
				if (((encodedHeb.equals("�") && i < (engString.length() - 1) && engString.charAt(i + 1) != '-'))
						|| ((encodedHeb.equals("�") && (i == engString.length() - 1))
								&& (engString.charAt(engString.length() - 2) == '"')))
					encodedHeb = "�";
				if (((encodedHeb.equals("�") && i < (engString.length() - 1) && engString.charAt(i + 1) != '-'))
						|| ((encodedHeb.equals("�") && (i == engString.length() - 1))
								&& (engString.charAt(engString.length() - 2) == '"')))
					encodedHeb = "�";
			} catch (Exception e) {
			}
			result.append(encodedHeb);
		}
		return result.toString();
	}

	public static String Heb2Eng(String hebStr) {
		String transliteratedStr = "";
		StringBuffer transliterated = new StringBuffer();
		char curChar;
		char transChar;
		for (int i = 0; i < hebStr.length(); i++) {
			curChar = hebStr.charAt(i);
			if ((curChar >= '�') && (curChar <= '�')) {
				String charStr = (new Character(curChar)).toString();
				transChar = getHebToEng(charStr).charAt(0);
				transliterated.append(transChar);
			} else
				transliterated.append(curChar);
		}

		transliteratedStr = transliterated.toString();
		return transliteratedStr;

	}

	public static void main(String[] args) {

		String str = "[+preposition]%D7%9C";
		String newStr = MixedHebEng(str);
		System.out.println("newStr = " + newStr);
	}

}
