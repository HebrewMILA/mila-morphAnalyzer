package sql;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.function.Predicate;

final public class Inflection {
	public final String surface;
	public final String baseNumber;
	public final String baseGender;
	public final String basePerson;
	public final String tense;
	public final String baseTransliteratedLItem;
	public final String baseDefinitness;
	public final String transliterated;
	public final String PGN;
	public final String suffixStatus;
	public final String type;
	public final String basePos;
	public final String suffixFunction;
	
	Inflection(ResultSet rs) throws UnsupportedEncodingException, SQLException {
		surface = read(rs, "surface");
		baseNumber = read(rs, "baseNumber");
		baseGender = read(rs, "baseGender");
		basePerson = read(rs, "basePerson");
		tense = read(rs, "tense");
		baseTransliteratedLItem = read(rs, "baseTransliteratedLItem");
		baseDefinitness = read(rs, "baseDefinitness");
		transliterated = read(rs, "transliterated");
		PGN = read(rs, "PGN");
		suffixStatus = read(rs, "suffixStatus");
		type = read(rs, "type");
		basePos = read(rs, "basePos");
		suffixFunction = read(rs, "suffixFunction");
	}


	private String read(ResultSet rs, String field) throws UnsupportedEncodingException,
			SQLException {
		return URLDecoder.decode(rs.getString(field),"UTF-8");
	}
	
	enum Gender { M, F, MF; }
	enum Number { S, P, SP; }
	

	public interface Where extends Predicate<Inflection> {
		static Where and(Where a, Where b) {
			return x->a.test(x) && b.test(x);
		}
		static Where or(Where a, Where b) {
			return x->a.test(x) || b.test(x);
		}
		static Where not(Where a) {
			return x->!a.test(x);
		}
	}
}