package hebrewNER.baseline;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

import org.junit.After;

import tagging.Config;
import hebrewNER.*;
import hebrewNER.io.Utils;
import static org.junit.Assert.*;

public class BaselineTrainerTest extends TrainerTest {

	@Override
	protected String getOutfile() {
		return Config.BASELINE_TRAIN_OUT;
	}

	@Override
	protected NerModelTrainer getModelTrainer() {
		return new BaselineTrainer();
	}

	private Set<String> getSet(String path) throws FileNotFoundException, IOException {
		Set<String> expected = new HashSet<>();
		for (String s : Utils.readLinesFromFile(path))
			expected.add(s);
		return expected;
	}

	@After
	public void checkNotChanged() throws FileNotFoundException, IOException {
		Set<String> expected = getSet(Config.MODEL_BASELINE);
		Set<String> actual = getSet(Config.BASELINE_TRAIN_OUT);
		expected.removeAll(actual);
		//assertEquals(expected, new HashSet<>());
		//assertEquals(expected, actual);
	}
}
