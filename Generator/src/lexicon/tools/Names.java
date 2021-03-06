package lexicon.tools;

import java.util.HashMap;

/**
 * @author Danny Shacham
 */
public class Names {

	private static HashMap pos;
	private static HashMap actions;
	private static HashMap hebToEng;
	private static HashMap engToHeb;

	public static String getPos(String eng) {
		if (pos == null) {
			initPos();
		}
		return (String) pos.get(eng);
	}

	private static void initPos() {
		pos = new HashMap();
		pos.put("adjective", "שם תואר");
		pos.put("adverb", "תואר הפועל");
		pos.put("conjunction", "מילת חיבור");
		pos.put("interjection", "מילת קריאה");
		pos.put("interrogative", "מילת שאלה");
		pos.put("negation", "מילת שלילה");
		pos.put("noun", "שם עצם");
		pos.put("preposition", "מילת יחס");
		pos.put("pronoun", "כינוי גוף");
		pos.put("properName", "שם פרטי");
		pos.put("quantifier", "כמת");
		pos.put("verb", "פועל");
	}

	private static void initEngToHeb() {
		engToHeb = new HashMap();
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
		engToHeb.put("`", "%60");
		engToHeb.put("\"", "%22");

	}

	private static void initHebToEng() {
		hebToEng = new HashMap();
		hebToEng.put("%D7%90", "a");
		hebToEng.put("%D7%91", "b");
		hebToEng.put("%D7%92", "g");
		hebToEng.put("%D7%93", "d");
		hebToEng.put("%D7%94", "h");
		hebToEng.put("%D7%95", "w");
		hebToEng.put("%D7%96", "z");
		hebToEng.put("%D7%97", "x");
		hebToEng.put("%D7%98", "v");
		hebToEng.put("%D7%99", "i");
		hebToEng.put("%D7%9B", "k");
		hebToEng.put("%D7%9C", "l");
		hebToEng.put("%D7%9E", "m");
		hebToEng.put("%D7%A0", "n");
		hebToEng.put("%D7%A1", "s");
		hebToEng.put("%D7%A2", "y");
		hebToEng.put("%D7%A4", "p");
		hebToEng.put("%D7%A6", "c");
		hebToEng.put("%D7%A7", "q");
		hebToEng.put("%D7%A8", "r");
		hebToEng.put("%D7%A9", "e");
		hebToEng.put("%D7%AA", "t");

		// Sofiot
		hebToEng.put("%D7%9A", "k");
		hebToEng.put("%D7%9D", "m");
		hebToEng.put("%D7%9F", "n");
		hebToEng.put("%D7%A3", "p");
		hebToEng.put("%D7%A5", "c");

		// Punctuation marks
		hebToEng.put("-", "-");
		hebToEng.put("%27", "'");
		hebToEng.put("%22", "\"");
		hebToEng.put("%60", "`");

	}

	public static String getEngToHeb(String eng) {
		if (engToHeb == null) {
			initEngToHeb();
		}
		return (String) engToHeb.get(eng);
	}

	public static String getHebToEng(String heb) {
		if (hebToEng == null) {
			initHebToEng();
		}
		return (String) hebToEng.get(heb);
	}

	public static String getAction(String eng) {
		if (actions == null) {
			initActions();
		}
		return (String) actions.get(eng);
	}

	private static void initActions() {
		actions = new HashMap();
		actions.put("add", "הוספה");
		actions.put("replace", "החלפה");
		actions.put("remove", "הסרה");
	}
}
