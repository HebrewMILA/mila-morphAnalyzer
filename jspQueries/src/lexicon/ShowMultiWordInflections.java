package lexicon;

import general.SimpleQuerier;

public final class ShowMultiWordInflections {
	private final String inflections;

	public ShowMultiWordInflections(String lexiconId, int num) {
		try (final SimpleQuerier conn = new SimpleQuerier(lexiconId)) {
			inflections = conn.querySurface("SELECT surface FROM mweinflections where mwePointer= ? ");
		}
	}
	
	public String getInflections() {
		return inflections;
	}
	
	public static void releaseConnection() {
	}
}
