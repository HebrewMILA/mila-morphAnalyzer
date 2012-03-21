package lexicon.tools;

import java.util.HashMap;

/**
 * @author Danny Shacham
 */
public class Names {

	private static HashMap<String, String> pos;
	private static HashMap<String, String> actions;
	private static HashMap<String, String> hebToEng;
	private static HashMap<String, String> engToHeb;

	public static String getAction(String eng) {
		if (Names.actions == null) {
			Names.initActions();
		}
		return (String) Names.actions.get(eng);
	}

	public static String getEngToHeb(String eng) {
		if (Names.engToHeb == null) {
			Names.initEngToHeb();
		}
		return (String) Names.engToHeb.get(eng);
	}

	public static String getHebToEng(String heb) {
		if (Names.hebToEng == null) {
			Names.initHebToEng();
		}
		return (String) Names.hebToEng.get(heb);
	}

	public static String getPos(String eng) {
		if (Names.pos == null) {
			Names.initPos();
		}
		return (String) Names.pos.get(eng);
	}

	private static void initActions() {
		Names.actions = new HashMap<String, String>();
		Names.actions.put("add", "הוספה");
		Names.actions.put("replace", "החלפה");
		Names.actions.put("remove", "הסרה");
	}

	private static void initEngToHeb() {
		Names.engToHeb = new HashMap<String, String>();
		Names.engToHeb.put("a", "%D7%90");
		Names.engToHeb.put("b", "%D7%91");
		Names.engToHeb.put("g", "%D7%92");
		Names.engToHeb.put("d", "%D7%93");
		Names.engToHeb.put("h", "%D7%94");
		Names.engToHeb.put("w", "%D7%95");
		Names.engToHeb.put("z", "%D7%96");
		Names.engToHeb.put("x", "%D7%97");
		Names.engToHeb.put("v", "%D7%98");
		Names.engToHeb.put("i", "%D7%99");
		Names.engToHeb.put("k", "%D7%9B");
		Names.engToHeb.put("l", "%D7%9C");
		Names.engToHeb.put("m", "%D7%9E");
		Names.engToHeb.put("n", "%D7%A0");
		Names.engToHeb.put("s", "%D7%A1");
		Names.engToHeb.put("y", "%D7%A2");
		Names.engToHeb.put("p", "%D7%A4");
		Names.engToHeb.put("c", "%D7%A6");
		Names.engToHeb.put("q", "%D7%A7");
		Names.engToHeb.put("r", "%D7%A8");
		Names.engToHeb.put("e", "%D7%A9");
		Names.engToHeb.put("t", "%D7%AA");

		// Sofiot
		Names.engToHeb.put("k", "%D7%9A");
		Names.engToHeb.put("m", "%D7%9D");
		Names.engToHeb.put("n", "%D7%9F");
		Names.engToHeb.put("p", "%D7%A3");
		Names.engToHeb.put("c", "%D7%A5");

		// Punctuation marks
		Names.hebToEng.put("-", "-");
		Names.hebToEng.put("'", "%27");
		Names.hebToEng.put("`", "%60");
		Names.hebToEng.put("\"", "%22");
	}

	private static void initHebToEng() {
		Names.hebToEng = new HashMap<String, String>();
		Names.hebToEng.put("%D7%90", "a");
		Names.hebToEng.put("%D7%91", "b");
		Names.hebToEng.put("%D7%92", "g");
		Names.hebToEng.put("%D7%93", "d");
		Names.hebToEng.put("%D7%94", "h");
		Names.hebToEng.put("%D7%95", "w");
		Names.hebToEng.put("%D7%96", "z");
		Names.hebToEng.put("%D7%97", "x");
		Names.hebToEng.put("%D7%98", "v");
		Names.hebToEng.put("%D7%99", "i");
		Names.hebToEng.put("%D7%9B", "k");
		Names.hebToEng.put("%D7%9C", "l");
		Names.hebToEng.put("%D7%9E", "m");
		Names.hebToEng.put("%D7%A0", "n");
		Names.hebToEng.put("%D7%A1", "s");
		Names.hebToEng.put("%D7%A2", "y");
		Names.hebToEng.put("%D7%A4", "p");
		Names.hebToEng.put("%D7%A6", "c");
		Names.hebToEng.put("%D7%A7", "q");
		Names.hebToEng.put("%D7%A8", "r");
		Names.hebToEng.put("%D7%A9", "e");
		Names.hebToEng.put("%D7%AA", "t");

		// Sofiot
		Names.hebToEng.put("%D7%9A", "k");
		Names.hebToEng.put("%D7%9D", "m");
		Names.hebToEng.put("%D7%9F", "n");
		Names.hebToEng.put("%D7%A3", "p");
		Names.hebToEng.put("%D7%A5", "c");

		// Punctuation marks
		Names.hebToEng.put("-", "-");
		Names.hebToEng.put("%27", "'");
		Names.hebToEng.put("%22", "\"");
		Names.hebToEng.put("%60", "`");
		Names.hebToEng.put(".", ".");
		Names.hebToEng.put("'", "'");
	}

	private static void initPos() {
		Names.pos = new HashMap<String, String>();
		Names.pos.put("adjective", "שם תואר");
		Names.pos.put("adverb", "תואר הפועל");
		Names.pos.put("conjunction", "מילת חיבור");
		Names.pos.put("interjection", "מילת קריאה");
		Names.pos.put("interrogative", "מילת שאלה");
		Names.pos.put("negation", "מילת שלילה");
		Names.pos.put("noun", "שם עצם");
		Names.pos.put("preposition", "מילת יחס");
		Names.pos.put("pronoun", "כינוי גוף");
		Names.pos.put("properName", "שם פרטי");
		Names.pos.put("quantifier", "כמת");
		Names.pos.put("verb", "פועל");
		Names.pos.put("existential", "כמת ישיי");
		Names.pos.put("impersonal", "חסר גוף ומשקל");
		Names.pos.put("wPrefix", "תחילית");
		Names.pos.put("modal", "מודאל");
		Names.pos.put("copula", "אוגד");
		Names.pos.put("numeral", "שם מספר");
		Names.pos.put("title", "תואר");
		Names.pos.put("acronym", "ראשי תיבות");
		Names.pos.put("multiWordFrozen", "ביטוי קפוא");
		Names.pos.put("multiWordPreposition", "ביטוי נוטה");
		Names.pos.put("multiWordNoun", "ביטוי נסמך סומך");
		Names.pos.put("multiWordNounAdjective", "צרוף שם ותוארו");
		Names.pos.put("multiWordVerbPhrase", "ביטוי פועלי");
	}
}
