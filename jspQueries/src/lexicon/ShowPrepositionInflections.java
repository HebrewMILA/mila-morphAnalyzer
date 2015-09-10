package lexicon;

import sql.InflectionTable;
import static sql.InflectionFilters.suffix;

public class ShowPrepositionInflections {
	private final String pgn;
	
	public ShowPrepositionInflections(String lexiconId) {
		pgn = InflectionTable.fetch(lexiconId).where(suffix(null)).joinSurface();
	}

	public String getPGN() {
		return pgn;
	}

	public static void releaseConnection() {
	}
}
