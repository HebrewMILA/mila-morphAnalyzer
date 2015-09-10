package ner;

import tagging.Sentence;

import java.util.List;

/**
 * The main interface: classes that implement NameFinder do the actual work.
 * <p>
 * A Sentence is basically a list of Tokens
 * </p>
 * <p>
 * Token is the basic unit of information: token + attributes
 * </p>
 *
 * @see Sentence
 * @see tagging.Token
 */
public interface EntityFinder {
	/**
	 * @param sentence
	 *            the part of speech analysis
	 * @return A list of corresponding named entity tags
	 */
	public List<String> tag(Sentence sentence);
}
