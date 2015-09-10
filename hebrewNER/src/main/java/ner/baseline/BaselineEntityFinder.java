package ner.baseline;

import java.util.ArrayList;
import java.util.List;

import ner.Config;
import ner.Entity;
import ner.EntityFinder;
import tagging.Sentence;
import tagging.Token;
import utils.Gram5;
import features.Expression;

/**
 * Baseline finds entities based solely on the surface.<br>
 *
 * <p>
 * Regular expression identify date, time, money and percent expressions. A
 * token gets its tag by its lexicon entry.
 * </p>
 */
public class BaselineEntityFinder implements EntityFinder {
	static String modelFile = "baseline.model";

	@Override
	public List<String> tag(Sentence sentence) {
		List<Entity> tags = new ArrayList<>();
		List<Boolean> firsts = new ArrayList<>();
		for (int i = 0; i < sentence.size(); i++) {
			boolean first = true;
			Gram5<Token> gram5Token = sentence.getGram5Token(i);
			Entity res = new Expression(gram5Token).getLongestMatch();
			if (res == Entity.O) {
				res = Dictionary.baseline.getLongestMatch(gram5Token.map(x->x.lemma));
				first = isFirst(i == 0 ? Entity.O : tags.get(i - 1), res);
			}
			firsts.add(first);
			tags.add(res);
		}
		return zip(firsts, tags);
	}

	private static List<String> zip(List<Boolean> firsts, List<Entity> tags) {
		assert firsts.size() == tags.size();
		List<String> res = new ArrayList<>();
		for (int i = 0; i < firsts.size(); i++) {
			String pref = "";
			if (tags.get(i) != Entity.O)
				pref = firsts.get(i) ? Config.START : Config.MIDDLE;
			res.add(pref + tags.get(i));
		}
		return res;
	}

	private static boolean isFirst(Entity prevTag, Entity curTag) {
		return curTag == Entity.O || prevTag == Entity.O || prevTag != curTag;
	}

	public static void train(String[] args) throws Exception {
		BaselineTrainer.main(args);
	}
}
