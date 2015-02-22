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
 *         TODO To change the template for this generated type comment go to
 *         Window - Preferences - Java - Code Style - Code Templates
 */
public class Translate {

    private static HashMap<String, String> hebToEng;

    private static HashMap<String, String> engToHeb;

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
	return hebToEng.get(heb);
    }

    public static String getEngToHeb(String eng) {
	if (engToHeb == null) {
	    initEngToHeb();
	}
	return engToHeb.get(eng);
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
	StringBuffer result = new StringBuffer();
	String hebString = "";
	String encodedHeb = "";
	for (int i = 0; i < engString.length(); i++) {
	    hebString = Translate.getEngToHeb(String.valueOf(engString
		    .charAt(i)));
	    try {
		encodedHeb = URLDecoder.decode(hebString, "UTF-8");
		if (((encodedHeb.equals("�") && i < (engString.length() - 1) && engString
			.charAt(i + 1) != '-'))
			|| ((encodedHeb.equals("�") && (i == engString.length() - 1)) && (engString
				.charAt(engString.length() - 2) == '"')))
		    encodedHeb = "�";
		if (((encodedHeb.equals("�") && i < (engString.length() - 1) && engString
			.charAt(i + 1) != '-'))
			|| ((encodedHeb.equals("�") && (i == engString.length() - 1)) && (engString
				.charAt(engString.length() - 2) == '"')))
		    encodedHeb = "�";
		if (((encodedHeb.equals("�") && i < (engString.length() - 1) && engString
			.charAt(i + 1) != '-'))
			|| ((encodedHeb.equals("�") && (i == engString.length() - 1)) && (engString
				.charAt(engString.length() - 2) == '"')))
		    encodedHeb = "�";
		if (((encodedHeb.equals("�") && i < (engString.length() - 1) && engString
			.charAt(i + 1) != '-'))
			|| ((encodedHeb.equals("�") && (i == engString.length() - 1)) && (engString
				.charAt(engString.length() - 2) == '"')))
		    encodedHeb = "�";
		if (((encodedHeb.equals("�") && i < (engString.length() - 1) && engString
			.charAt(i + 1) != '-'))
			|| ((encodedHeb.equals("�") && (i == engString.length() - 1)) && (engString
				.charAt(engString.length() - 2) == '"')))
		    encodedHeb = "�";
	    } catch (Exception e) {
	    }
	    result.append(encodedHeb);
	}
	return result.toString();
    }

    public static String Heb2Eng(String hebStr) {
	StringBuffer transliterated = new StringBuffer();
	String transliteratedStr = "";
	char curChar;
	char transChar;
	for (int i = 0; i < hebStr.length(); i++) {
	    curChar = hebStr.charAt(i);
	    if ((curChar >= '�') && (curChar <= '�')) {
		String charStr = (new Character(curChar)).toString();
		transChar = getHebToEng(charStr).charAt(0);
		transliterated.append(transChar);
	    }
	    if ((curChar == '-') || (curChar == ',') || (curChar == ';')
		    || (curChar == '.') || (curChar == ':') || (curChar == '�')
		    || (curChar == '(') || (curChar == ')') || (curChar == ']')
		    || (curChar == '[') || (curChar == '\"')
		    || (curChar == '?') || (curChar == '*')
		    || (curChar == '\'') || (curChar == '!')
		    || (curChar == '@') || (curChar == '#') || (curChar == '$')
		    || (curChar == '%') || (curChar == '^') || (curChar == '&')
		    || (curChar == '_') || (curChar == '=') || (curChar == '+')
		    || (curChar == '/') || (curChar == '\'')
		    || (curChar == ';') || (curChar == ',') || (curChar == '{')
		    || (curChar == '}') || (curChar == '\"')
		    || (curChar == '<') || (curChar == '>')) {
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
