package ner.maxent;

import features.Expression;
import features.WordFeature;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import ner.Config;
import ner.Entity;
import opennlp.tools.util.BeamSearchContextGenerator;
import tagging.Sentence;
import tagging.Token;
import utils.Gram5;
import utils.IO;
import static ner.baseline.Dictionary.*;

/**
 * Generates context to a token, given the sequence of sentence tokens and
 * additional context: POS tags and previous tags.<br>
 * Features: dictionary, word lists, regular expression, pos tags, lemma,
 * prefix, suffix, construct ("smichut"). Features are taken in a window of +-2
 * around current token.<br>
 *
 * @see MaxentEventStream
 * @see MaxentEntityFinder
 */
class MaxentContextGenerator implements BeamSearchContextGenerator<String> {
	private static final Set<String> FWL = new HashSet<>(
			IO.readLinesFromFile(Config.LISTS_DIR + "FrequentWords.txt"));

	private static List<String> getStaticFeatures(final Gram5<Token> t, boolean inCommas) {
		final Gram5<String> surface = t.map(p->p.surface);
		final Gram5<String> pos = t.map(p->p.pos);
		final Entity wd = dictionary.get(surface.w);
		final WordFeature wf = WordFeature.get(surface.w);
		
		final List<String> feats = new ArrayList<>(Arrays.asList(
				"def", //? BUG?
				"w=" + surface.w,
				"wf=" + wf,
				"wd=" + wd,
				"wRare=" + FWL.contains(surface.w),
				"wNoun=" + nouns.get(surface.w),
				// features taken from the pos tag for current word
				"wpos=" + pos.w,
				"w=" + surface.w + ",wpos=" + pos.w,
				"wlemma=" + t.w.lemma,
				"wpos=" + pos.w + ",wlemma=" + t.w.lemma,
				"wprefix=" + t.w.getPrefixOrDefault("#f")
				)
		);


		if (surface.ppw.isEmpty()) {
			feats.addAll(Arrays.asList(
				"ppw=" + surface.ppw,
				"ppwf=" + WordFeature.get(surface.ppw),
				"ppwpos=" + pos.w));
		} else {
			feats.add("ppw=BOS");
		}

		if (surface.pw.isEmpty()) {
			feats.add("df=it");
			feats.add("pw=BOS");
		} else {
			feats.addAll(Arrays.asList(
				"pw=" + surface.pw,
				"pwf=" + WordFeature.get(surface.pw),
				"pw=" + surface.pw + ",w=" + surface.w,
				"wpwd=" + dictionary.get(surface.pw + " " + surface.w),
				"pwUBI=" + UBIMinus1.get(surface.pw),
				"pwNoun=" + nounsMinus1.get(surface.pw),
				"pwpos=" + pos.pw,
				"w=" + surface.w + ",pwpos=" + pos.pw,
				"wpos=" + pos.w + ",pwpos=" + pos.pw)
				);
		}

		// next word
		if (surface.nw.isEmpty()) {
			feats.add("nw=EOS");
		} else {
			Entity wnwd = dictionary.get(surface.w + " " + surface.nw);
			if (isFalse(wnwd))
				wnwd = dictionary.get(surface.pw + " " + surface.w + " " + surface.nw);
			
			feats.addAll(Arrays.asList(
				"nw=" + surface.nw,
				"nwf=" + WordFeature.get(surface.nw),
				"w=" + surface.w + ",nw=" + surface.nw,
				"wnwd=" + wnwd,
				"nwUBI=" + UBIPlus1.get(surface.nw),
				"nwNoun=" + nounsPlus1.get(surface.nw),
				"smichut=" + t.w.construct + ",nw=" + surface.nw,
				"nwpos=" + pos.nw,
				"smichut=" + t.w.construct + ",nwpos=" + pos.nw,
				"w=" + surface.w + ",nwpos=" + pos.nw,
				"wpos=" + pos.w + ",nwpos=" + pos.nw)
				);
		}

		if (surface.nnw.isEmpty()) {
			feats.add("nnw=EOS");
		} else {
			feats.add("nnw=" + surface.nnw);
			feats.add("nnwf=" + WordFeature.get(surface.nnw));
			feats.add("nnwpos=" + pos.nnw);
		}
		Expression r = new Expression(t);
		// checking for binary regular expressions
		boolean isOrg = r.isOrganization(wf);
		boolean isLoc = r.isLocation();
		boolean date = r.isDate();
		boolean percent = r.isPercent();
		boolean time = r.isTime();
		boolean money = r.isMoney();
		feats.addAll(Arrays.asList(
				"inCommas=" + inCommas,
				"isOrg=" + isOrg,
				"isOrg=" + isOrg + ",wpos=" + pos.w,
				"isLoc=" + isLoc,
				"isLoc=" + isLoc + ",wpos=" + pos.w,
				"date=" + date,
				"wf=" + wf + ",date=" + date,
				"percent=" + percent,
				"wf=" + wf + ",percent=" + percent,
				"wd=" + wd + ",percent=" + percent,
				"time=" + time,
				"wf=" + wf + ",time=" + time,
				"money=" + money,
				"wd=" + wd + ",money=" + money)
				);
		// the dictionary entry of a long expression
		feats.add("expDict=" + dictionary.getLongestMatch(surface));
		return feats;
	}

	private static boolean isFalse(Entity wnwd) {
		return wnwd == Entity.O;
	}

	@Override
	public String[] getContext(int i, String[] toks, String[] preds,
			Object[] additionalContext) {
		if (additionalContext.length != 2) {
			System.out
					.println("MaxEntContextGenerator: wrong additional context");
			System.exit(2);
		}
		Sentence posAnal = (Sentence) additionalContext[0];
		Boolean isStart = (Boolean) additionalContext[1];
		return getContext(i, toks, preds, posAnal, isStart);
	}

	/**
	 * Generate context for a sentence.
	 *
	 * @param i
	 *            index of current word
	 * @param toks
	 *            sentence tokens
	 * @param preds
	 *            previously predicted tags in the same sentence
	 * @param posAnal
	 *            pos analysis
	 * @param isStart
	 *            is this the start of a sentence
	 * @return The array of corresponding contexts generated by this context
	 *         generator
	 */
	public static String[] getContext(int i, String[] toks, String[] preds,
			Sentence posAnal, boolean isStart) {
		List<String> feats = getStaticFeatures(posAnal.getGram5Token(i),
				Expression.inQuotes(Arrays.asList(toks), i));
		feats.add("startFile=" + isStart);
		// predicted tags of ppw and pw
		String po = "null";
		String ppo = "null";
		try {
			boolean pwSmichut = posAnal.get(i).isConstruct();
			feats.add("pwSmichut=" + pwSmichut);
			po = preds[i - 1];
			feats.add("pwSmichut=" + pwSmichut + ",po=" + po);
			ppo = preds[i - 2];
		} catch (IndexOutOfBoundsException ignored) {

		}

		feats.add("po=" + po);
		feats.add("ppo=" + ppo);
		return feats.toArray(new String[feats.size()]);
	}
}
