package hebrewNER;

import static org.junit.Assert.*;

import java.io.IOException;

import org.junit.BeforeClass;
import org.junit.Test;

import tagging.Config;

public class CrossValidationTest extends BaseTest {
	@BeforeClass
	public static void before() {
		Config.cross_validation = true;
		Config.getTagger().getClass();
	}

	@Test
	public void test() throws IOException {
		runDir(Config.CROSS_VALIDATION_LEAF, 0);
	}
}
