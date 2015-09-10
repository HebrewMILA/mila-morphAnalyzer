package ner;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import tagging.Token;
import tagging.TokenEntity;
import utils.IO;
import xml.CorpusAdapter;
import ner.baseline.BaselineEntityFinder;
import ner.hmm.HmmEntityFinder;
import ner.maxent.MaxentEntityFinder;
/**
 * A base class for model trainers.
 *
 * <p>
 * The <code>main()</code> method trains all the models together.
 * </p>
 */
public abstract class ModelTrainer {

	/**
	 * Create training file with this name
	 */
	protected abstract void train(Iterable<Collection<TokenEntity>> iterable,
								  String trainFilename);

	protected abstract String getFilename();

	protected final void doTraining(String[] args) {
		String dirName = args.length > 0 ? args[0] : Config.TRAINSET_DIR;
		String trainFilename = args.length > 1 ? args[1] : getFilename();
		System.out.println("Training model from " + dirName);
		System.out.println("Writing model to " + trainFilename);
		Iterable<Collection<TokenEntity>> items = chainFilesInDirectory(dirName);
		//IO.updateSet(XmlReader.pos_list, Config.POS_LIST);
		train(items, trainFilename);
		System.out.println("Done.");
	}

	private static Iterable<Collection<TokenEntity>> chainFilesInDirectory(String dirname) {
		List<Collection<TokenEntity>> res = new ArrayList<>();
		IO.listDir(dirname).forEach(path -> 
			//res.add(TokenEntity.readFromFile(path))
			res.add(xmlToFull(
					path.getParent().resolveSibling("xml").resolve(path.getFileName()).toString(),
					path.getParent().resolveSibling("entities").resolve(path.getFileName()).toString()))
		);
		return res;
	}

	private static List<TokenEntity> xmlToFull(String xmlFile, String entitiesFile) {
		xmlFile = xmlFile.split("\\.")[0] + ".xml";
		entitiesFile = entitiesFile.split("\\.")[0] + ".entities";
		Iterator<String> b = IO.readLinesFromFile(entitiesFile).stream()
				.filter(x->x.indexOf('\t')>-1)
				.map(x->x.split("\t")[1]).iterator();
		List<Token> tokens = new ArrayList<>();
		CorpusAdapter.read(xmlFile).iterSentences().forEach(s->tokens.addAll(s.getTokens()));
		Iterator<Token> a = tokens.iterator();
		List<TokenEntity> res = new ArrayList<>();
		while (a.hasNext() && b.hasNext())
			res.add(new TokenEntity(a.next(), b.next()));
		return res;
	}

	public static void main(String[] args) throws Exception {
		assert args.length == 0;
		System.out.println("Training: default");
		train(args);
	}

	private static void train(String[] args) throws Exception {
		BaselineEntityFinder.train(args);
		MaxentEntityFinder.train(args);
		HmmEntityFinder.train(args);
	}
}
