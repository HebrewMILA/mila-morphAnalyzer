package xml;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import tagging.Token;

class Analysis {
	boolean update = true;
	Att prefix, base, pos, suffix;
	double score = Double.NEGATIVE_INFINITY;

	private static String get(Att att, String key) {
		if (att == null)
			return "-";
		String s = att.getValue(key);
		return s == null ? "-" : s;
	}

	private String getHMMPos() {
		String tag = get(pos, "pos");
		if ("participle".equals(tag))
			tag = get(pos, "type");
		if ("-".equals(tag))
			return "[]";
		if ("verb copula modal".contains(tag))
			return "{verb} []";
		if ("pronoun".equals(tag) || "quantifier".equals(tag))
			return "{" + tag + "} []";
		if (tag.equals("noun")) {
			AttImpl newP = new AttImpl();
			for (String k : new String[] { "definiteness", "gender", "number", "status", "foreign" })
				newP.addAttribute(k, get(pos, k));
			pos = newP;
		}
		List<String> res = new ArrayList<>();
		for (int i = 0; i < pos.getLength(); i++) {
			final List<String> dismiss = Arrays
					.asList("id pos root expansion multiWordUndotted value multiWordTransliterated spelling register type"
							.split(" "));
			String key = pos.getQName(i);
			if (!dismiss.contains(key)) {
				String v = pos.getValue(i);
				if (!v.isEmpty() && !"-".equals(v))
					res.add(String.format("(%s: %s)", key, pos.getValue(i)));
			}
		}
		Collections.sort(res);
		return String.format("{%s} [%s]", tag, String.join(",", res));
	}

	Token analysisToToken(Att token) {
		return new Token(Integer.parseInt(token.getValue("id")), get(token, "surface"),
				get(base, "lexiconItem"), get(prefix, "function"),
				toDash(get(pos, "definiteness")),
				get(pos, "status"), get(pos, "pos"), toDash(get(pos, "type")), getHMMPos());
	}
	
	private static String toDash(String s) {
		return s.isEmpty()?"-":s;
	}
}

interface Att {
	String getQName(int i);
	int getLength();
	String getValue(int i);
	String getValue(String key);
}