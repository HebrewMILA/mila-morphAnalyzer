package tagging;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

import utils.IO;

/**
 * A [token, entity] pair. Represents tokens after both named-entity analysis
 * and pos analysis.<br>
 * There is no special behavior.
 *
 * @see Token Token - pre-analysis token information
 * @see Sentence Sentence - pre-analysis sentence information
 */

public final class TokenEntity extends Token {
	public final String entity;

	public TokenEntity(Token token, String entity) {
		super(token);
		this.entity = entity;
	}

	/** The empty/null instance. Represents a token before/after the sentence. */
	static final public TokenEntity EMPTY = new TokenEntity(Token.EMPTY, "");

	public static List<TokenEntity> readFromFile(Path path) {
		List<TokenEntity> res = new ArrayList<>();
		for (String line : IO.readLinesFromPath(path)) {
			String[] pieces = line.split("\t");
			res.add(new TokenEntity(new Token(pieces), pieces[pieces.length - 1]));
		}
		return res;
	}
}
