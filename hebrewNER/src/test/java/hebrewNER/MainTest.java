package hebrewNER;

import static org.junit.Assert.*;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import tagging.Config;
import utils.IO;

public class MainTest {

	@BeforeClass
	public static void before() {
		Config.getTagger().getClass();
	}

	private void run(String filename) {
		String infile = Config.PLAIN_TEXT + filename + ".txt";
		//String outfile = Config.DEFAULT_OUTPUT_DIR + filename + ".tsv";
		String expectedfile = Config.EXPECTED_DIR + filename + ".tsv";

		List<String> explist = IO.readLinesFromFile(expectedfile);
		List<String> actual = Recognizer.recognize(IO.readFileToString(infile), infile);

		checkStats(explist, actual);
	}

	private void checkStats(List<String> explist, List<String> actual) {
		int total=0, correct=0, falsepos=0, falseneg=0;
		outer: for (int i = 0; i < actual.size() && i < explist.size(); i++) {
			while (actual.get(i).isEmpty()) {
				actual.remove(i);
				if (i >= actual.size())
					break outer;
			}
			while (explist.get(i).isEmpty()) {
				explist.remove(i);
				if (i >= explist.size())
					break outer;
			}
			total++;
			String exp = explist.get(i);
			if (exp.equals(actual.get(i)))
				correct++;
			else {
				System.out.println(explist.get(i) + "!=" + actual.get(i));
				if (exp.split("\t")[1].trim().equals("O")) {
					falseneg++;
				} else {
					falsepos++;
				}
			}
		}

		System.out.println("total=" + total);
		showPercent("correct", total, correct);
		showPercent("false positive", total, falsepos);
		showPercent("false negative", total, falseneg);
	}

	private void showPercent(String string, int total, int val) {
		System.out.format("%s=%d (%.2f%%)%n", string, val, val*100.0/total);
	}


	@Test
	public void test500() throws IOException {
		run("original/500");
	}

	@Test
	public void testNrg() throws IOException {
		run("original/nrg-3");
	}

	@Test
	public void testTest() throws IOException {
		try {
			run("original/test");
		} catch (IllegalArgumentException ex) {
			System.out.println(ex);
			throw ex;
		}
	}

	@Test
	public void testHaaretz1() throws IOException {
		run("haaretz/1");
	}

	@Test
	public void testHaaretzLimited() throws IOException {
		File folder = new File(Config.EXPECTED_DIR + "haaretz/");
		int i=0;
		for (File f : folder.listFiles()) {
			if (i++ > 10)
				break;
			String name = "haaretz/" + f.getName().split("\\.")[0];
			System.out.println(name);
			run(name);
		}
	}

	@Test
	public void testHaaretz() throws IOException {
		File folder = new File(Config.EXPECTED_DIR + "haaretz/");
		for (File f : folder.listFiles())
			run("haaretz/" + f.getName().split("\\.")[0]);
	}

}
