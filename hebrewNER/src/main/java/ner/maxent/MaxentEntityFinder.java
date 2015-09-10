package ner.maxent;

import java.io.File;
import java.io.IOException;
import java.util.List;

import ner.Config;
import ner.EntityFinder;
import opennlp.tools.ml.maxent.io.SuffixSensitiveGISModelReader;
import opennlp.tools.ml.model.AbstractModel;
import opennlp.tools.util.BeamSearch;
import tagging.Sentence;

/**
 * Finds entities using the statistical models.<br>
 * The search for the best tag sequence is done by a beam search.
 */
public class MaxentEntityFinder implements EntityFinder {

	private final BeamSearch<String> beam = new BeamSearch<>(10,
			new MaxentContextGenerator(), readModel());

	private static AbstractModel readModel() {
		try {
			return new SuffixSensitiveGISModelReader(new File(
					Config.Models.maxent)).getModel();
		} catch (IOException e) {
			e.printStackTrace();
			System.exit(1);
		}
		return null;
	}

	@Override
	public List<String> tag(Sentence sentence) {
		return beam.bestSequence(sentence.getSurfaces().toArray(new String[0]),
				new Object[] { sentence, true }).getOutcomes();
	}

	public static void train(String[] args) throws Exception {
		MaxentTrainer.main(args);
	}
}
