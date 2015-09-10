package tagging;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import utils.Gram5;

/**
 * A Sentence is an analysed sentence - basically a list of <u>Token</u>s. The
 * main difference between a Sentence and a list of Tokens is that an
 * out-of-bounds access returns the empty token.
 *
 * <p>
 * <u>This class is immutable</u>. This is important in order to
 * allow @SentenceEntity to subclass it correctly.
 * </p>
 *
 * @see Token
 * @see ner.hmm.HmmEntityFinder
 * @see SentenceEntity
 */
public class Sentence {
	private final List<? extends Token> tokens;
	private final List<String> surfaces;

	public Sentence(List<? extends Token> wets) {
		this.tokens = Collections.unmodifiableList(wets);
		this.surfaces = wets.stream().map(t -> t.surface).collect(Collectors.toList());
	}

	public final List<String> getSurfaces() {
		List<String> res = new ArrayList<>();
		res.addAll(surfaces);
		return surfaces;
	}

	public List<? extends Token> getTokens() {
		return new ArrayList<>(tokens);
	}
	public Token get(int i) {
		return 0 <= i && i < tokens.size() ? tokens.get(i) : TokenEntity.EMPTY;
	}

	public Gram5<String> getGram5(int i) {
		return getGram5Token(i).map(p -> p.surface);
	}

	public Gram5<Token> getGram5Token(int i) {
		return new Gram5<>(get(i - 2), get(i - 1), get(i), get(i + 1), get(i + 2));
	}

	public int size() {
		return tokens.size();
	}

	public static List<Sentence> fromIterable(Collection<String> filename) {
		List<Token> sentence = new ArrayList<>();
		List<Sentence> sentenceList = new ArrayList<>();
		for (Token t : Token.fromCollection(filename)) {
			if (t.id == 1 && sentence.size() > 0) {
				sentenceList.add(new Sentence(sentence));
				sentence.clear();
			}
			sentence.add(t);
		}
		sentenceList.add(new Sentence(sentence));
		return sentenceList;
	}
}
