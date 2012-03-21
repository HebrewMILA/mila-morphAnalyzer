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

import lexicon.tools.Names;

/**
 * @author daliabo
 * 
 * TODO To change the template for this generated type comment go to Window -
 * Preferences - Java - Code Style - Code Templates
 */
public class Translate {

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
		return (String) hebToEng.get(heb);
	}

	public static String MixedHebEng(String inStr) {
		StringBuffer result = new StringBuffer();
		
		String encodedStr = "";
		for (int i = 0; i < inStr.length(); i++) {
			char curChar = inStr.charAt(i);
			
			if (curChar == '%'){
				StringBuffer tempStr = new StringBuffer();
				while ((i< inStr.length()) && (curChar != '[')){
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
			}
			else if ((curChar >= 'a' && curChar <= 'z')
					|| ((curChar >= 'A' && curChar <= 'Z'))){
				result.append(String.valueOf(curChar));
			}
			else if ((curChar == '[') || (curChar == ']') || (curChar == '+')
					|| (curChar == '/')){			
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
		//System.out.println("transliteratedStr ="+ transliteratedStr );
		return transliteratedStr;

	}

	public static void main(String[] args) {

		String hebStr = "י-ם";
		String newStr = Heb2Eng(hebStr);
		System.out.println("newStr = " + newStr);
	}

}
