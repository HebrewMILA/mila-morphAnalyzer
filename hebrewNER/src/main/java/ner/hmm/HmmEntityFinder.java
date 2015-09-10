package ner.hmm;

import java.util.ArrayList;
import java.util.List;

import ner.Config;
import ner.EntityFinder;
import tagging.Sentence;

/**
 * The HMM entity finder tags token using the statistical model read from a file.<br>
 * The search for the best tag sequence is done by Viterbi search.
 */
public final class HmmEntityFinder implements EntityFinder {
	private final HmmModel hmm = HmmModel.readFromFile(Config.Models.hmm);

	@Override
	public List<String> tag(Sentence sentence) {
		try {
			List<String> contexts = new ArrayList<>();
			for (int i = 0; i < sentence.size(); i++)
				contexts.add(HmmContextGenerator.getContext(sentence, i));
			return HmmStateIndexer.getSequenceTags(viterbi(contexts, sentence));
		} catch (ArrayIndexOutOfBoundsException ex) {
			throw new BadIndexing("try training the HMM model.");
		}
	}

	/**
	 * Performs a Viterbi search on a sentence using its context and pos
	 * analysis
	 *
	 * @param context
	 *            the array of context (observations) for the sentence tokens
	 * @param sentence
	 *            the part of speech analysis
	 * @return The array is representing the nodes of the best sequence
	 */
	private int[] viterbi(List<String> context, Sentence sentence) {

		int numOfStates = HmmStateIndexer.getNumOfStates();
		int[][] nodePointers = new int[context.size()][numOfStates];
		double[] prevProbs = new double[numOfStates + 1];
		prevProbs[HmmStateIndexer.getSOSIndex()] = 1;

		for (int i = 0; i < context.size(); i++) {
			double[] curProbs = new double[numOfStates];
			String wpos = sentence.get(i).HMMPos;
			for (int y = 1; y < numOfStates; y++) {
				// the transition to another node is possible only if it matches
				// the pos tag of this token
				if (HmmStateIndexer.isPosNode(wpos, y)) {
					int maxNode = getMax(y, prevProbs);
					nodePointers[i][y] = maxNode;
					curProbs[y] = mult(hmm.getEmissionP(context.get(i), y)
								, prevProbs[maxNode]
								, hmm.getTransitionP(maxNode, y));
				}
			}
			prevProbs = curProbs;
		}

		return getMaxPath(nodePointers, prevProbs);
	}

	private static double mult(double... elems) {
		//XXX: Always zero :(
		double sum = 0;
		for (double d : elems) {
			if (Double.isNaN(d))
				throw new RuntimeException("NaN!");
			sum += Math.log(d);
		}
		if (sum > 0)
			System.err.println("sum: " + sum);
		return Math.exp(sum);
	}

	/**
	 * Finds the node with the maximum probability so far
	 *
	 * @param toNode
	 * @param prevProbs
	 * @return
	 */
	private int getMax(int toNode, double[] prevProbs) {
		double max = Double.NEGATIVE_INFINITY;
		int maxNode = -1;
		for (int i = 0; i < HmmStateIndexer.getNumOfStates(); i++) {
			double val = prevProbs[i] * hmm.getTransitionP(i, toNode);
			if (val >= max && HmmStateIndexer.validSequence(i, toNode)) {
				max = val;
				maxNode = i;
			}
		}
		return maxNode;
	}

	/**
	 * find the maximum probability path in the matrix created by the search
	 *
	 * @param nodePointers
	 * @param prevProbs
	 * @return maximum probability path
	 */
	private int[] getMaxPath(int[][] nodePointers, double[] prevProbs) {
		int[] nodePath = new int[nodePointers.length];
		int lastNode = getMax(HmmStateIndexer.getEOSIndex(), prevProbs);
		for (int i = nodePointers.length - 1; i >= 0; i--) {
			nodePath[i] = lastNode;
			lastNode = nodePointers[i][lastNode];
		}
		return nodePath;
	}

	public static void train(String[] args) throws Exception {
		HmmTrainer.main(args);
	}
}


final class BadIndexing extends RuntimeException {
	public BadIndexing(String string) {
		super(string);
	}
	private static final long serialVersionUID = -8463751626518174529L;
}