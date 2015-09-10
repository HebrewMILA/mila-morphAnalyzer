package features;

import static ner.baseline.Dictionary.dictionary;
import static ner.baseline.Dictionary.nouns;
import static ner.baseline.Dictionary.nounsMinus1;
import static ner.baseline.Dictionary.nounsPlus1;
import features.WordFeature;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;
import java.util.function.BiPredicate;
import java.util.function.Predicate;
import java.util.stream.Stream;

import ner.Entity;
import tagging.Token;
import utils.Gram5;
import utils.TriPredicate;
/**
 * A class that classify Hebrew long regular expressions(1-5 words)<br>
 * Contains binary methods to identify organization, location, date, time, money, and percent expressions. <br>
 */
public class Expression {
	private final String ppw, pw, w, nw, nnw;
	private final Gram5<Token> gram;
	private final Gram5<String> surface;

	public Expression(Gram5<Token> tgram) {
		surface = tgram.map(p->p.surface);
		this.gram = tgram;
		this.ppw = surface.ppw;
		this.pw = surface.pw;
		this.w = surface.w;
		this.nw = surface.nw;
		this.nnw = surface.nnw;
	}

	private Stream<String> subtermsWithoutPrefixes() {
		List<String> res = new LinkedList<>(gram.subtermsByLength());
		for (List<String> r : surface.rawSubtermsByLength()) {
			for (int i = 0; i < r.size(); i++) {
				if (r.get(i).length() > 1 && "בכלמה".indexOf(r.get(i).charAt(0)) != -1) {
					// Note: in-place mutation
					r.set(i, r.get(i).substring(r.get(i).charAt(1) == '-' ? 2 : 1));
					res.add(String.join(" ", r));
				}
			}
		}
		return res.stream();
	}

	/**
	 * Identify organization expression.
	 *
	 * @param wf
	 *            - word feature
	 */
	public boolean isOrganization(WordFeature wf) {
		if (isMilaEntity("organization"))
			return true;
		TriPredicate<String, String, String> isOrg =
				(w,nw,nnw)->nounsMinus1.contains(Entity.ORG, w) &&
				  (dictionary.contains(Entity.LOC, nw)
				|| dictionary.contains(Entity.LOC, nw, nnw)
				|| dictionary.contains(Entity.PERS, nw));
		return isOrg.test(ppw, pw, w) || isOrg.test(pw, w, nw) || isOrg.test(w, nw, nnw)
				|| Entity.ORG == dictionary.getLongestMatch(surface)
				|| nounsPlus1.contains(Entity.ORG, nw)
				|| dictionary.contains(Entity.ORG, w) || wf == WordFeature.LOAZI;
	}

	public boolean isLocation() {
		if (isMilaEntity("town", "country", "location"))
			return true; 
		BiPredicate<String, String> isLoc = (w, nw)-> nounsMinus1.contains(Entity.LOC, w)
													&& dictionary.contains(Entity.PERS, nw);
		return isLoc.test(pw, w) || isLoc.test(w, nw)
				|| Entity.LOC == dictionary.getLongestMatch(surface)
				|| dictionary.contains(Entity.LOC, w);
	}

	private boolean isMilaEntity(String ... ss) {
		return Arrays.stream(ss).anyMatch(s->gram.w.entity.contains(s));
	}
	private static List<String> months = Arrays.asList("ינואר", "פברואר", "מרץ", "אפריל", "מאי", "יוני", "יולי", "אוגוסט", "ספטמבר", "אוקטובר", "נובמבר", "דצמבר");
	public boolean isDate() {
		if (isMilaEntity("dateTime"))
			return true;
		class A {

			private boolean isYear(String s) {
				return isNumberInRange(s, 1800, 2200);
			}
			
			private boolean isMonth(String word) {
				if (word.isEmpty())
					return false;
				if ("בל".indexOf(word.charAt(0)) != -1)
					word = word.substring(1);
				return months.contains(word);
			}

			private boolean isDate(String w) {
				if (w.length() < 6 || w.length() > 10)
					return false;
				String[] s = w.split("\\.");
				if (s.length == 1)
					s = w.split("-");
				return s.length == 3 && isNumberInRange(s[0], 0, 32) && isNumberInRange(s[1], 0, 13)
						&& ((s[2].length() == 2 && isNumberInRange(s[2], -1, 100)) || isYear(s[2]));
			}

			private boolean isLongDate(String w1, String w2, String w3) {
				return isLongDate(w1, w2) || isLongDate(w2, w3);
			}

			private boolean isLongDate(String w1, String w2) {
				// TODO: check actual number > 32
				return isNumber(w1) && isMonth(w2) || isMonth(w1) && isYear(w2);
			}
		}

		A a = new A();
		if (subtermsWithoutPrefixes().anyMatch(sub->(dictionary.contains(Entity.DATE, sub)
														|| nouns.contains(Entity.DATE, sub))))
				return true;
		return isNumberInRange(w, 1800, 2200) || a.isDate(w)
				|| a.isLongDate(ppw, pw, w) || a.isLongDate(pw, w, nw)
				|| a.isLongDate(w, nw, nnw) || a.isLongDate(pw, w)
				|| a.isLongDate(w, nw);
	}

	public boolean isPercent() {
		//debug("enter A");
		class A {
			private boolean isPercent(String withpref) {
				//debug("withpref = " + withpref);
				return Token.getNoPrefixesStream(withpref).stream().sequential().anyMatch(w->
					(dictionary.contains(Entity.PERCENT, w)
					|| WordFeature.NUM_PERCENT.match(w) && isNumberInRange(w.substring(0, w.length() - 1), 0, 101)
					|| WordFeature.PERCENT_NUM.match(w)	&& isNumberInRange(w.substring(1, w.length()), 0, 101))
				);
			}

			private boolean isPercent(String pw, String w) {
				//debug("pw, w = " + pw + ", " + w);
				return percentHelper(w, pw) || percentHelper(pw, w);
			}

			private boolean percentHelper(String w1, String w2) {
				//debug("w1, w2 = " + w1 + ", " + w2);
				return (isNumberInRange(w2, 0, 101) || dictionary.contains(Entity.NUM, w2))
					&& (WordFeature.PERCENT.match(w1) || dictionary.contains(Entity.PERCENT, w1));
			}

		}
		A a = new A();
		boolean res = a.isPercent(w) || a.isPercent(pw, w) || a.isPercent(w, nw);
		//debug("exit A");
		return res;
	}

	private static Locale loc = new Locale("iw_IL");
	private static SimpleDateFormat[] fmts = new SimpleDateFormat[] { 
			new SimpleDateFormat("hh:mm:ss", loc),
			new SimpleDateFormat("hh:mm", loc),
			new SimpleDateFormat("hh.mm", loc)};
	private static boolean isTimeFormat(String word) {
		for (SimpleDateFormat fmt : fmts) {
			try {
				fmt.parse(word);
				return true;
			} catch (ParseException ignored) {
			}
		}
		return false;
	}
	
	public boolean isTime() {
		if (isMilaEntity("dateTime"))
			return true;
		if (subtermsWithoutPrefixes().anyMatch(sub->nouns.contains(Entity.TIME, sub)))
			return true;
		Predicate<String> isTimeFormat = w->Token.getNoPrefixesStream(w).stream().anyMatch(Expression::isTimeFormat);
		BiPredicate<String, String> isTime = (w, nw) -> nounsMinus1.contains(Entity.TIME, w)
													&& isTimeFormat.test(nw);
		return isTime.test(pw, w) || isTime.test(w, nw) || isTimeFormat(w) || isTimeFormat.test(w);
	}

	public boolean is(Entity e) {
		switch (e) {
		case PERCENT:
			//debug("e = PERCENT");
			return isPercent();
		case MONEY:
			//debug("e = MONEY");
			return isMoney();
		case TIME:
			//debug("e = TIME");
			return isTime();
		case DATE:
			//debug("e = DATE");
			return isDate();
		case ORG:
			//debug("e = ORG");
			return isOrganization(WordFeature.NONE);
		case LOC:
			//debug("e = LOC");
			return isLocation();
		case MISC_AFF:
		case MISC_ENT:
			//debug("e = MISC_AFF");
			return dictionary.contains(e, w);
		case MISC_EVENT:
			//debug("e = MISC_EVENT");
			return isMilaEntity("event") || dictionary.contains(e, w);
		case NUM:
			//debug("e = NUM");
			return gram.w.pos.contains("numeral");
		case O:
		default:
			//debug("e = O");
			return false;
		}
	}

	public Entity getLongestMatch() {
		Entity[] es = new Entity[] { Entity.TIME, Entity.DATE, Entity.PERCENT, Entity.MONEY };
		for (Entity e : es) 
			if (is(e)) 
				return e;
		return Entity.O;
	}

	public boolean isMoney() {
		return  isMilaEntity("money")
				|| isNumber(w)
				|| dictionary.contains(Entity.MONEY, w)
				|| dictionary.contains(Entity.MONEY, pw, w) 
				|| dictionary.contains(Entity.MONEY, w, nw)
				|| dictionary.contains(Entity.MONEY, nw, nnw)
				&& (  dictionary.contains(Entity.MONEY, pw)
				   || dictionary.contains(Entity.MONEY, nw)
				   || dictionary.contains(Entity.NUM, nw)
						&& dictionary.contains(Entity.MONEY, nnw))
				|| isNumber(ppw) && dictionary.contains(Entity.NUM, pw)	&& dictionary.contains(Entity.MONEY, w);
	}

	/**
	 * Identify quoted expression.
	 *
	 * @param toks
	 *            - an array of strings representing a sentence,
	 * @param wIndex
	 *            - index of the current word
	 */
	public static boolean inQuotes(List<String> toks, int wIndex) {
		int start = Math.max(0, wIndex - 5);
		int end = Math.min(toks.size(), wIndex + 6);
		return oddQuotes(toks, start, wIndex)
				&& oddQuotes(toks, wIndex + 1, end);
	}

	private static boolean oddQuotes(List<String> toks, int start, int end) {
		int count = 0;
		for (int i = start; i < end; i++) {
			if ("\"".equals(toks.get(i)))
				count++;
		}
		return count % 2 != 0;
	}

	private static boolean isNumber(String w) {
		return dictionary.contains(Entity.NUM, w) || !Double.isNaN(tryParseNumber(w));
	}


	private static double tryParseNumber(String w) {
		try {
			return Double.parseDouble(w.replaceAll(",", ""));
		} catch (NumberFormatException nfe) {
			return Double.NaN;
		}
	}
	private static boolean intervallContains(double n, int low, int high) {
	    return n >= low && n <= high;
	}
	private static boolean isNumberInRange(String with_pref, int low, int high) {
		return Token.getNoPrefixesStream(with_pref).stream().anyMatch(w->intervallContains(tryParseNumber(w), low, high));
	}
}
