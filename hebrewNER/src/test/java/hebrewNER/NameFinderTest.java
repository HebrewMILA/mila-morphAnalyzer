package hebrewNER;

import static org.junit.Assert.*;

import java.io.IOException;

import org.junit.Test;

public class NameFinderTest extends BaseTest {

	@Test
	public void testHaaretz1() throws IOException {
		run("haaretz/1");
	}

	@Test
	public void testOriginal() {
		runDir("original", 0);
	}

	@Test
	public void testHaaretzLimited() throws IOException {
		runDir("haaretz", 10);
	}

	@Test
	public void testHaaretz() throws IOException {
		runDir("haaretz", 0);
	}

	@Test
	public void testGold() throws IOException {
		runDir("gold", 10);
	}
}
