package ner.hmm;

import tagging.TokenEntity;
import utils.IO;
import xml.XmlReader;

import java.util.Collection;

import ner.Config;
import ner.ModelTrainer;

/**
 * A class for training HMM from a corpus in one training file. Calculates the
 * transition and emission probabilities.<br>
 * The HMM nodes are defined by the NodeIndexer and the vocabulary is the set of
 * contexts created by the context generator using the event stream.<br>
 * The HMM created is saved in a file.
 */
final class HmmTrainer extends ModelTrainer {
	@Override
	protected String getFilename() {
		return Config.Models.hmm;
	}

	public static void main(String[] args) throws Exception {
		// tagging.Config.cross_validation = true;
		new HmmTrainer().doTraining(args);
	}

	@Override
	protected void train(Iterable<Collection<TokenEntity>> iterable, String filename){
		IO.updateSet(XmlReader.pos_list, Config.POS_LIST);
		HmmModel.create(new HmmEventStream(iterable)).writeToFile(filename);
	}

}
