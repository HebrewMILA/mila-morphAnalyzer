package hebrewNER;

import hebrewNER.NerModelTrainer;

import org.junit.Test;

import tagging.Config;

public abstract class TrainerTest {

	protected abstract String getOutfile();
	protected abstract NerModelTrainer getModelTrainer();

	@Test
	public void testTrain() throws Exception {
		String infile = Config.TRAIN_SENTENCES;
		//String expectedfile = Messages.getString("DEFAULT_EXPECTED") + "/"	+ filename + ".tsv";
		//String expected = Main.readAll(new FileInputStream(expectedfile));

		NerModelTrainer x = getModelTrainer();
		x.train(infile);
		x.saveModelToFile(getOutfile());

		//assertEquals(expected, actual);
	}

}