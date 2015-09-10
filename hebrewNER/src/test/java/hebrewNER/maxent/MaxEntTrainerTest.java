package hebrewNER.maxent;

import tagging.Config;
import hebrewNER.NerModelTrainer;
import hebrewNER.TrainerTest;

public class MaxEntTrainerTest extends TrainerTest {

	@Override
	protected String getOutfile() {
		return Config.MAXENT_TRAIN_OUT;
	}

	@Override
	protected NerModelTrainer getModelTrainer() {
		return new MaxEntTrainer();
	}
}
