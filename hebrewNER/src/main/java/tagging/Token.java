package tagging;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import static ner.Config.debug;
/**
 * The basic unit of information about a specific token that is needed for the
 * analysis: <br>
 * [surface, prefix, construct, lemma, part-of-speech]<br>
 * The information in this class is pre-Named-Entity analysis.
 *
 * @see Sentence Sentence (A list of Tokens)
 * @see tagging.SentenceEntity SentenceEntity (Named-Entity information about a token)
 * @see TokenEntity TokenEntity
 */
@SuppressWarnings("unused")
public class Token {
	public Token(int id, String surface, String lemma, String prefix,
				  String definiteness, String cons, String pos, String type,
				  String hMMPos) {
		this.id = id;
		this.surface = surface;
		this.lemma = lemma;
		this.prefix = prefix;
		this.definiteness = definiteness;
		this.construct = cons;
		this.pos = pos;
		this.entity = type;
		this.HMMPos = hMMPos;
	}

	Token(Token t) {
		this(t.id, t.surface, t.lemma, t.prefix, t.definiteness, t.construct,
				t.pos, t.entity, t.HMMPos);
	}

	// 0 1 2 3 4 5 6 7 8 9 10
	// ['par', 'sent', 'id', 'surf', 'lemma', 'pref', 'def', 'cons', 'pos', 'type', 'hmm_pos']
	
	// assume *.tagged or *.full file type
	public Token(String[] a) {
		this(Integer.valueOf(a[2]), a[3], a[4], a[5], a[6], a[7], a[8], a[9], a[10]);
	}

	public final int id;

	/** The word as appears in the text */
	public final String surface;

	/** The base form of the surface */
	public final String lemma;

	/**
	 * Some unique representation for the type of the prefix. If there is no
	 * prefix it is an empty string
	 */
	private final String prefix;

	private final String definiteness;

	/** "construct" means נסמך. true = nismach. false = absolute */
	public final String construct;

	/** Part of speech */
	public final String pos;

	public final String entity;
	public final String HMMPos;

	@Override
	public String toString() {
		return String.format(
		"Token(id=%d, surface=%s, lemma=%s, prefix=%s, definiteness=%s, construct=%s, pos=%s, entity=%s, HMMPos=%s)",
			   id,    surface,    lemma,    prefix,    definiteness,    construct,    pos,    entity,    HMMPos);
	}

	public String toTsv() {
		return String.format(
		"%d	%s	%s	%s	%s	%s	%s	%s	%s",
		id,    surface,    lemma,    prefix,    definiteness,    construct,    pos,    entity,    HMMPos);
	}
	
	public String getPrefixOrDefault(String def) {
		return "-".equals(prefix) ? def : prefix;
	}


	public static int hashCode(Object... xs) {
		int result = 1;
		for (Object x: xs)
			result = 31 * result + x.hashCode();
		return result;
	}

	@Override
	public int hashCode() {
		return hashCode(HMMPos, construct,  definiteness, entity,  lemma,  pos,  prefix,  surface);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		return equals((Token) obj);
	}

	private boolean equals(Token other) {
		return HMMPos.equals(other.HMMPos)
			&& construct.equals(other.construct)
			&& definiteness.equals(other.definiteness)
			&& entity.equals(other.entity)
			&& lemma.equals(other.lemma)
			&& pos.equals(other.pos)
			&& prefix.equals(other.prefix)
			&& surface.equals(other.surface);
	}

	public static List<String> getNoPrefixesStream(String w) {
		List<String> res = new ArrayList<>();
		res.add(w);
		for (int i : new int[]{1,2,3}) {
			if (w.length() > i && ("הוש".indexOf(w.charAt(i)) != -1 || "בכלמ".indexOf(w.charAt(i)) != -1))
				res.add(w.substring(i));
			else
				break;
		}
		return res;
	}

	public static List<? extends Token> fromCollection(Collection<String> coll) {
		return coll.stream().map(x->new Token(x.split("\t"))).collect(Collectors.toList());
	}

	/** The empty/null instance. Represents a token before/after the sentence. */
	static final public Token EMPTY = new Token(0, "", "", "", "", "", "", "", "");

	public boolean isConstruct() {
		return "T".equals(construct);
	}

}
