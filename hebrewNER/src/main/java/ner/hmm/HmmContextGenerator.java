package ner.hmm;

import static ner.baseline.Dictionary.*;
import features.Expression;
import features.WordFeature;
import ner.Entity;

import tagging.Sentence;
import utils.Gram5;

/**
 * Context generator to be used by the event stream and the name finder.<br>
 * Context is created for a token by its surrounding (words in a window of +-2).
 * A context of a token is a union of its dictionary entry, regular expressions
 * and token information.
 */
abstract class HmmContextGenerator {

	private static final char FEATURE_SEPARATOR = '_';

	/** 
	 * Generate context for a single token.
	 *
	 * @param sentence
	 *            the array of sentence tokens
	 * @param i
	 *            the index of the current token
	 * @return the context - a string representing the union of its features,
	 *         the features are separated by @FEATURE_SEPARATOR
	 */
	public final static String getContext(Sentence sentence, int i) {
		Gram5<String> gram = sentence.getGram5(i);
		Expression r = new Expression(sentence.getGram5Token(i));
		return String.join("" + FEATURE_SEPARATOR,
				""+getDictionary(gram),
				""+FWLTag.getLongestMatch(gram),
				""+getUBI(gram.ppw, gram.pw),
				""+getFromNextExpression(gram.nw),
				""+Expression.inQuotes(sentence.getSurfaces(), i),
				""+r.isDate(), ""+r.isPercent(), ""+r.isTime(), ""+r.isMoney(), ""+r.isLocation(),
				""+r.isOrganization(WordFeature.get(gram.w))
			);
	}

	private static Entity anyOf(Entity... es) {
		for (Entity e : es)
			if (e != Entity.O)
				return e;
		return Entity.O;
	}
	private static Entity getFromNextExpression(String nw) {
		return anyOf(UBIPlus1.get(nw),
					nounsPlus1.get(nw));
	}

	private static Entity getDictionary(Gram5<String> gram) {
		return anyOf(dictionary.getLongestMatch(gram),
					nouns.get(gram.w));
	}

	private static Entity getUBI(String ppw, String pw) {
		return anyOf(UBIMinus2_1.get(ppw + " " + pw),
					UBIMinus1.get(pw),
					nounsMinus1.get(pw));
	}
}
