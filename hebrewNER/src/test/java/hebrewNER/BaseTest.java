package hebrewNER;

import static org.junit.Assert.*;

import java.io.File;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import static tagging.Config.verbose;
import tagging.Config;
import tagging.OldMweNameFinder;
import tagging.Sentence;
import utils.IO;

public class BaseTest {

	@BeforeClass
	public static void before() {
		Config.getTagger().getClass();
	}

	static enum FinderStats {
		MERGE(new MergeEntityFinder()),
		BASELINE(((MergeEntityFinder)MERGE.nf).base),
		HMM(((MergeEntityFinder)MERGE.nf).hmm),
		MAXENT(((MergeEntityFinder)MERGE.nf).maxent),
		OLD_MILA(new OldMweNameFinder());

		int total=0, correct=0, falsepos=0, falseneg=0, totalEntities=0, correctEntities=0;
		EntityFinder nf;

		FinderStats(EntityFinder nf) {
			this.nf = nf;
		}

		public void printStat() {
			System.out.print(String.format("Stats for %-8s: ", name()));
			System.out.println(String.format("%s, %s, %s, %s",
					getPercent("correct", total, correct),
					getPercent("false positive", total, falsepos),
					getPercent("false negative", total, falseneg),
					getPercent("recognized", totalEntities, correctEntities))
				);
		}
	}

	private void chatter(String str) {
		if (verbose)
			System.out.println(str);
	}

	static int total = 0;
	static int totalEntities=0, correctEntities=0;

	private void evaluate(Sentence sent, FinderStats fs, String[] explist) {
		List<String> actuallist = fs.nf.tag(sent);
		Iterator<String> iter_exp = Arrays.asList(explist).iterator();
		Iterator<String> iter_actual = actuallist.iterator();
		do {
			String actual = "";
			while (actual.isEmpty() && iter_actual.hasNext())
				actual = iter_actual.next();

			String exp = "";
			while (exp.isEmpty() && iter_exp.hasNext())
				exp = iter_exp.next();

			if (exp.isEmpty() && actual.isEmpty())
				continue;

			//total++;
			fs.total++;
			if (!exp.equals("O"))  {
				totalEntities++;
				fs.totalEntities++;
			}
			if (exp.equals(actual)) {
				fs.correct++;
				if (!exp.equals("O"))  {
					correctEntities++;
					fs.correctEntities++;
				}
				continue;
			}
			chatter(exp + "!=" + actual);
			if (exp.equals("O")) {
				fs.falsepos++;
			} else {
				fs.falseneg++;
			}
		} while (iter_actual.hasNext() && iter_exp.hasNext());
	}

	protected void run(String filename) {
		System.out.print("running on " + filename + "... ");
		System.out.flush();
		String infile = Config.TAGGED_DIR + filename + ".tagged";
		//String outfile = Config.DEFAULT_OUTPUT_DIR + filename + ".tsv";
		String expectedfile = Config.EXPECTED_DIR + filename + ".tsv";
		List<String[]> expectedList = IO.readTsvValuesFromFile(expectedfile);
		List<Sentence> sents = Sentence.readFromFile(infile);
		for (int i=0; i < sents.size(); i++) {
			// System.out.println("sentence no " + i);
			total += sents.get(i).size();
			for (FinderStats fs : FinderStats.values()) {
				evaluate(sents.get(i), fs, expectedList.get(i));
			}
		}
		System.out.println(" done");
	}

	void runDir(String dirname, int max) {
		File folder = new File(Config.PLAIN_TEXT + dirname + "/");
		System.out.println(folder.getPath());
		for (File f : folder.listFiles()) {
			if (f.isDirectory())
				continue;
			if (--max == 0)
				break;
			run(dirname + "/" + f.getName().split("\\.")[0]);
		}
	}

	@AfterClass
	public static void printStat() {
		System.out.println("total = "+total);
		System.out.println("total entities = "+totalEntities/4);
		for (FinderStats fs : FinderStats.values())
			fs.printStat();
	}

	private static String getPercent(String string, int total, int val) {
		return String.format("%s=%-3d (%.2f%%)", string, val, val*100.0/total);
	}
}
