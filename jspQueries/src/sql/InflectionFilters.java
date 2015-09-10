package sql;

public enum InflectionFilters { ;
	public enum Tense {
		past, beinoni, future, imperative, infinitive, bareInfinitive, 
	}
	public enum Gender {
		masculine,  feminine
	}
	public enum Number {
		plural,  singular, dual
	}
	public enum Type {
		verb
	}
	public enum Pos {
		participle, passiveParticiple
	}
	public static Inflection.Where pos(Pos t) {
		return x->x.basePos.equals(t.toString());
	}
	public static Inflection.Where type(Type t) {
		return x->x.type.equals(t.toString());
	}
	public static Inflection.Where gender(Gender g) {
		return x->x.baseGender.equals(g.toString());
	}
	public static Inflection.Where number(Number g) {
		return x->x.baseNumber.equals(g.toString());
	}
	public static Inflection.Where tense(Tense t) {
		return x->x.tense.equals(t.toString());
	}
	public static Inflection.Where PGN(boolean t) {
		return x->t != x.PGN.equals("unspecified");
	}
	public static Inflection.Where suffix(Boolean t) {
		return x->x.suffixStatus.equals(t == null ? "unspecified" : (t ? "true" : "false"));
	}
	public static Inflection.Where definit(boolean t1, boolean t2) {
		return x->(x.baseDefinitness.charAt(0)=='t') == t1 && (x.baseDefinitness.charAt(1)=='t') == t2;
	}
}
