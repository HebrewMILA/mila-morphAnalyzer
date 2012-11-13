package org.mila.generator.utils;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang.text.StrBuilder;
import org.apache.commons.lang.text.StrTokenizer;
import org.apache.commons.lang.StringUtils;

public class Transliteration {
	private static Map<String, String> eng2heb;
	static {
		final HashMap<String, String> m = new HashMap<String, String>();
		m.put("a", "א");
		m.put("b", "ב");
		m.put("g", "ג");
		m.put("d", "ד");
		m.put("h", "ה");
		m.put("w", "ו");
		m.put("z", "ז");
		m.put("x", "ח");
		m.put("v", "ט");
		m.put("i", "י");
		m.put("k", "כ");
		m.put("l", "ל");
		m.put("m", "מ");
		m.put("n", "נ");
		m.put("s", "ס");
		m.put("y", "ע");
		m.put("p", "פ");
		m.put("c", "צ");
		m.put("q", "ק");
		m.put("r", "ר");
		m.put("e", "ש");
		m.put("t", "ת");
		eng2heb = Collections.unmodifiableMap(m);
	}

	private static Map<String, String> heb2eng;
	static {
		final HashMap<String, String> m = new HashMap<String, String>();
		m.put("א", "a");
		m.put("ב", "b");
		m.put("ג", "g");
		m.put("ד", "d");
		m.put("ה", "h");
		m.put("ו", "w");
		m.put("ז", "z");
		m.put("ח", "x");
		m.put("ט", "v");
		m.put("י", "i");
		m.put("ך", "k");
		m.put("כ", "k");
		m.put("ל", "l");
		m.put("ם", "m");
		m.put("מ", "m");
		m.put("ן", "n");
		m.put("נ", "n");
		m.put("ס", "s");
		m.put("ע", "y");
		m.put("ף", "p");
		m.put("פ", "p");
		m.put("ץ", "c");
		m.put("צ", "c");
		m.put("ק", "q");
		m.put("ר", "r");
		m.put("ש", "e");
		m.put("ת", "t");
		heb2eng = Collections.unmodifiableMap(m);
	}

	private static Map<String, String> regular2final;
	static {
		final HashMap<String, String> m = new HashMap<String, String>();
		m.put("כ", "ך");
		m.put("מ", "ם");
		m.put("נ", "ן");
		m.put("פ", "ף");
		m.put("ץ", "צ");
		regular2final = Collections.unmodifiableMap(m);
	}

	private static String doWord(String str, Map<String, String> m) {
		StrBuilder sb = new StrBuilder();
		for (char c : str.toCharArray()) {
			if (m.containsKey(String.valueOf(c))) {
				sb.append(m.get(String.valueOf(c)));
			} else {
				sb.append(c);
			}
		}
		return sb.toString();
	}

	public static String toHebrew(String english) {
		StrBuilder sb = new StrBuilder();
		StrTokenizer st = new StrTokenizer(english, " ");
		while (st.hasNext()) {
			final String word = doWord(st.nextToken(), eng2heb);
			if (regular2final.containsKey(StringUtils.right(word, 1))) {
				sb.append(StringUtils.substring(word, 0, -1));
				sb.append(regular2final.get(StringUtils.right(word, 1)));
			} else {
				sb.append(word);
			}
			if (st.hasNext()) {
				sb.append(" ");
			}
		}
		return sb.toString();
	}

	public static String toEnglish(String hebrew) {
		StrBuilder sb = new StrBuilder();
		for (char c : hebrew.toCharArray()) {
			if (heb2eng.containsKey(String.valueOf(c))) {
				sb.append(heb2eng.get(String.valueOf(c)));
			} else {
				sb.append(c);
			}
		}
		return sb.toString();
	}
}
