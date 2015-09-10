package gen;

import java.net.URLDecoder;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.stream.Collectors;

import sql.SqlDB;

class TestGeneration {

	public void deleteInflections() {
		try (Connection connection = SqlDB.lexiconTest.getConnection(SqlDB.User.maital);
				PreparedStatement statement = connection
						.prepareStatement("delete from inflections")) {
			statement.execute();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

	}
	private String getString(ResultSet rs, String field) {
		try {
			return rs.getString(field);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return "";
	}
	private String fieldsToCells(ResultSet rs, String ... fields) {
		return Arrays.asList(fields).stream().map(s->"<td>"+getString(rs, s)+"</td>").collect(Collectors.joining(""));
	}
	
	public String showInflections(String lexiconId) {
		String output = "";

		try (Connection connection = SqlDB.lexiconTest.getConnection(SqlDB.User.maital);
				PreparedStatement statement = connection
						.prepareStatement("SELECT * FROM inflections WHERE baseLexiconPointer= ?")) {
			statement.setObject(1, lexiconId);

			output = "<tr><th>inflection</th><th>transliterated</th><th>pos</th><th>lexicon id</th><th>lexicon item</th>"
					+ "<th>inflection number</th><th>inflection gender</th><th>inflection person</th><th>PGN</th>"
					+ "<th>suffixFunction</th><th>construct</th><th>binyan</th><th>root</th><th>tense</th><th>definitness</th>"
					+ "<th>register</th><th>spelling</th><th>polarity</th><th>prefix</th><th>type</th></tr>";

			try (ResultSet rs = statement.executeQuery()) {
				while (rs.next()) {
					final String surface = rs.getString("surface");
					final String decodedSurface = URLDecoder.decode(surface, "UTF-8");
					final String root = rs.getString("root");
					final String decodedRoot = root.equals("") ? "" : URLDecoder.decode(root, "UTF-8");
					final String tense = rs.getString("tense");
					if (tense.equals("bareInfinitive")) {
						output += "<tr bgcolor=\"AliceBlue\">"
								+ "<% } else if (tense.equals(\"infinitive\")) { %>"
								+ "<tr bgcolor=\"cornSilk\">"
								+ "<% } else if (tense.equals(\"past\")) { %>"
								+ "<tr bgcolor=\"pink\">"
								+ "<% } else if (tense.equals(\"beinoni\")) { %>"
								+ "<tr bgcolor=\"Moccasin\">"
								+ "<% } else if (tense.equals(\"future\")) { %>"
								+ "<tr bgcolor=\"whiteSmoke\">";

					} else {
						output += "<tr>";
					}
					output += "<td align=\"center\"><font size=5>" + decodedSurface + "</font></td>"
							+ fieldsToCells(rs, "transliterated", "basePos",
									"baseLexiconPointer",
									"baseTransliteratedLItem", "baseNumber",
									"baseGender", "basePerson", "PGN",
									"suffixFunction", "suffixStatus", "binyan",
									decodedRoot)
							+ "<td align=\"center\" ><font color=\"blue\" size=5>" + rs.getString("tense") + "</font></td>"
							+ fieldsToCells(rs, "baseDefinitness", "register", "spelling", "polarity", "prefix", "type")
							+ "</tr>";

				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return output;
	}

}
