package lexicon;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.sql.SQLException;
import java.util.Vector;

import sql.SimpleQuerier;

public class YahooSearch {
	private final String lexiconId;

	public YahooSearch(String lexiconId) {
		this.lexiconId = lexiconId;
	}

	public void getSurfaceList(Vector<String> decodedSurfaceVec,
			Vector<String> surfaceVec) throws SQLException {
		assertIsInt(lexiconId);
		String query = "SELECT distinct(surface),baseLexiconPointer "
				+ " FROM inflections "
				+ " WHERE (baseDefinitness not like '%t') and baseLexiconPointer= ? ";
		try (SimpleQuerier conn = new SimpleQuerier(lexiconId)) {
			conn.querySurface(query, surfaceVec);
		}
		for (String surface : surfaceVec) {
			try {
				decodedSurfaceVec.add(URLEncoder.encode(surface, "UTF-8"));
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
		}
	}

	private static void assertIsInt(String lexiconId) {
		Integer.parseInt(lexiconId);
	}

	public static void releaseConnection() {
	}

	public static void main(String[] args) throws SQLException {
		YahooSearch s = new YahooSearch("84*07");
		Vector<String> decodedSurfaceVec = new Vector<>();
		Vector<String> surfaceVec = new Vector<>();
		s.getSurfaceList(decodedSurfaceVec, surfaceVec);
	}

}
