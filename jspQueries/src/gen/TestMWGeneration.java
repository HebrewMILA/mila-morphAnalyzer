package gen;

import java.net.URLDecoder;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Arrays;

import sql.SqlDB;

class TestMWGeneration {

	public void deleteMWInflections() {
		try (Connection connection = SqlDB.lexiconTest.getConnection(SqlDB.User.maital)) {
			for (String table : Arrays.asList("mweinflections", "mwe1", "mwe2", "mwe3", "mwe4"))
				connection.prepareStatement("delete from " + table).execute();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private String showInflections(String mwe, String lexiconId) {
		try (Connection connection = SqlDB.lexiconTest.getConnection(SqlDB.User.maital);
			PreparedStatement statement = connection
					.prepareStatement("SELECT * FROM " + mwe + " WHERE lexiconId= ?")) {
			statement.setObject(1, lexiconId);
			try (ResultSet rs = statement.executeQuery()) {
				String output = "<tr><th>inflection</th><th>transliterated</th>th>multiword transliterated</th><th>PGN</th></tr>";
				while (rs.next()) {
					String decodedSurface = URLDecoder.decode(rs.getString("surface"), "UTF-8");
					output += String.format("<tr><td align=\"center\"><font size=5>%s</font></td><td>%s</td><td>%s</td><td>%s</td></tr>",
							decodedSurface, rs.getString("transliterated"), rs.getString("mwTransliterated"), rs.getString("PGN"));
				}
				return output;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "";
	}
	
	public String showMW2Inflections(String lexiconId) {
		return showInflections("mwe2", lexiconId);
	}
	
	public String showMW3Inflections(String lexiconId) {
		return showInflections("mwe3", lexiconId);
	}
	
	public String showMW4Inflections(String lexiconId) {
		return showInflections("mwe4", lexiconId);
	}
}
