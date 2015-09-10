package lexicon;

import sql.InflectionTable;
import static sql.InflectionFilters.*;
import static sql.InflectionFilters.Number.*;

public class ShowNumeralInflections {
	private final String construct;
	private final String base;
	private final String PGNsingular;
	private final String PGNPlural;
	
	public ShowNumeralInflections(String lexiconId) {
		InflectionTable qbase = InflectionTable.fetch(lexiconId);
		construct = qbase.where(suffix(true)).joinSurface();
		base = qbase.where(PGN(false), suffix(false), definit(true, false)).joinSurface();
		PGNsingular = qbase.where(PGN(true), number(singular)).joinSurface();
		PGNPlural = qbase.where(PGN(true), number(plural)).joinSurface();
	}
	
	public String getConstruct() {
		return construct;
	}

	public String getBase() {
		return base;
	}

	public String getPGNSingular() {
		return PGNsingular;
	}

	public String getPGNPlural() {
		return PGNPlural;
	}

	public static void releaseConnection() {
	}

}
