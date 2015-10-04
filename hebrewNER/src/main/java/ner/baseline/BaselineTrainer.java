package ner.baseline;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import ner.Config;
import ner.Entity;
import ner.ModelTrainer;
import tagging.TokenEntity;
import utils.IO;

/**
 * Trains the baseline model and saves it to file.
 */
class BaselineTrainer extends ModelTrainer {

	@Override
	protected String getFilename() {
		return Config.Models.baseline;
	}

	public static void main(String[] args) throws Exception {
		new BaselineTrainer().doTraining(args);
	}

	@Override
	protected void train(Iterable<Collection<TokenEntity>> iterable, String filename) {
		Set<String> vocabulary = new HashSet<>();

		String prevExp = "";
		Entity prevEntity = Entity.O;
		for (Collection<TokenEntity> tes : iterable)
			for (TokenEntity te : tes) {
				Entity entity = Entity.fromPrefixed(te.entity);
				if (entity == prevEntity && entity != Entity.O) {
					// inside an expression
					prevExp += " " + te.lemma;
					continue;
				}
				// a new expression
				if (prevEntity != Entity.O)
					if (!prevExp.isEmpty())
						vocabulary.add(prevEntity + Dictionary.KEY_VALUE_SEPERATOR
							+ prevExp);
				prevExp = (entity == Entity.O) ? "" : te.lemma;
				prevEntity = entity;
			}

		IO.writeLines(vocabulary, filename);
	}
}
