package ner.baseline;

import com.google.common.collect.Multimap;
import com.google.common.collect.HashMultimap;

import ner.Config;
import ner.Entity;

import tagging.Token;
import utils.Gram5;
import utils.IO;

import java.util.Collection;
import java.util.List;

/**
 * Dictionary holds several volumes of a dictionary.<br>
 * each volume is held as a hash table and has only one instance.
 */
public enum Dictionary {
	dictionary("Dictionary.txt"),
	FWLTag("FWLPerTag.txt"),
	UBIMinus1("UBI-1.txt"),
	UBIMinus2_1("UBI-2-1.txt"),
	UBIPlus1("UBI+1.txt"),
	nouns("Nouns.txt"),
	nounsMinus1("nouns-1.txt"),
	nounsPlus1("nouns+1.txt"),

	baseline(Config.Models.baseline, true);

	private final Multimap<String, Entity> vocabulary;

	public static final String KEY_VALUE_SEPERATOR = "\t";

	private Dictionary(String dictName) {
		this.vocabulary = loadDict(Config.LISTS_DIR + dictName);
	}

	private Dictionary(String dictName, boolean full) {
		this.vocabulary = loadDict(dictName);
	}

	/**
	 * loads a volume from a UTF8 text file.
	 *
	 * @param dictName
	 *            Filename for the dictionary
	 * @return set of keys in the dictionary
	 */
	private static Multimap<String, Entity> loadDict(String dictName) {
		List<String> lines = IO.readLinesFromFile(dictName);
		Multimap<String, Entity> vocabulary = HashMultimap.create(lines.size(), 1);
		for (String line : IO.readLinesFromFile(dictName)) {
			int i = line.indexOf("\t");
			vocabulary.put(line.substring(i+1), Entity.valueOf(line.substring(0, i)));
		}
		return vocabulary;
	}

	/**
	 * looks for the dictionary entry of the longest expression in a window of
	 * +-2 around current word.
	 *
	 * @return an Entity. Entity.O if not found
	 */
	public Entity getLongestMatch(Gram5<String> gram) {
		for (String opt : gram.subtermsByLength()) {
			Entity ans = get(opt);
			if (ans != Entity.O)
				return ans;
		}
		return Entity.O;
	}

	/**
	 * looks for the dictionary entry of a single word
	 *
	 * @param word
	 *            current word
	 * @return an Entity - the first that match
	 */
	public Entity get(String word) {
		Collection<Entity> res = this.vocabulary.get(word);
		if (res.isEmpty())
			return Entity.O;
		return res.iterator().next();
	}

	/**
	 * checks if dictionary entry of a word if tag.
	 */
	public boolean contains(Entity tag, String ... words) {
		return this.contains(tag, String.join(" ", words));
	}

	/**
	 * looks for the dictionary entry of the word without its prefixes
	 * @param word
	 */
	public boolean contains(Entity tag, String word) {
		//debug("dictionary contains");
		return Token.getNoPrefixesStream(word).stream().sequential().anyMatch(w->hasTag(tag, w));
	}
	
	private boolean hasTag(Entity tag, String word) {
		//debug("hasTag");
		return vocabulary.get(word).contains(tag);
	}
}
