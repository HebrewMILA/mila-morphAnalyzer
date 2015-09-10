package hebrewNER.hmm;

import tagging.Config;
import hebrewNER.NerModelTrainer;
import hebrewNER.TrainerTest;

public class HmmTrainerTest extends TrainerTest {

	@Override
	protected String getOutfile() {
		return Config.HMM_TRAIN_OUT;
	}

	@Override
	protected NerModelTrainer getModelTrainer() {
		return new HmmTrainer();
	}
}
