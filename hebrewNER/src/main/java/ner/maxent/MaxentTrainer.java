package ner.maxent;

import java.io.File;
import java.io.IOException;
import java.util.Collection;

import ner.Config;
import ner.ModelTrainer;
import opennlp.tools.ml.maxent.GIS;
import opennlp.tools.ml.maxent.GISModel;
import opennlp.tools.ml.maxent.io.SuffixSensitiveGISModelWriter;
import opennlp.tools.ml.model.TwoPassDataIndexer;
import tagging.TokenEntity;

/**
 * A class for training maxent model from a corpus in a directory. The model
 * created is saved in a file.
 */
class MaxentTrainer extends ModelTrainer {
	@Override
	protected String getFilename() {
		return Config.Models.maxent;
	}

	/**
	 * main method for training maxent.
	 */
	public static void main(String[] args) throws Exception {
		new MaxentTrainer().doTraining(args);
	}

	@Override
	protected void train(Iterable<Collection<TokenEntity>> iterable, String filename) {
		try {
			GISModel mod = GIS.trainModel(100, new TwoPassDataIndexer(
					new MaxentEventStream(iterable), 5));
			new SuffixSensitiveGISModelWriter(mod, new File(filename)).persist();
		} catch (IOException e) {
			throw new RuntimeException(e.getMessage());
		}
	}
}
