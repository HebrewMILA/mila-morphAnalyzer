package lexicon;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

import sql.SqlDB;

public class GeneratorViewer implements AutoCloseable {
	Connection connection = SqlDB.lexiconTest.getConnection(SqlDB.User.dummy1);
	public String showInflections(String lexiconId) {
		String output =  "<tr><th>inflection</th>" + "<th>transliterated</th>"
				+ "<th>pos</th>" + "<th>lexicon id</th>"
				+ "<th>lexicon item</th>"
				+ "<th>inflection number</th>"
				+ "<th>inflection gender</th>"
				+ "<th>inflection person</th>" + "<th>PGN</th>"
				+ "<th>suffixFunction</th>" + "<th>construct</th>"
				+ "<th>binyan</th>" + "<th>root</th>"
				+ "<th>tense</th>" + "<th>possessive gender</th>"
				+ "<th>possessive number</th>"
				+ "<th>possessive Person</th>" + "<th>definitness</th>"
				+ "<th>script</th>" + "<th>a/n PGN</th>"
				+ "<th>a/n person</th>" + "<th>a/n gender</th>"
				+ "<th>a/n number</th>" + "</tr>";
		String query = "SELECT * FROM inflections WHERE baseLexiconPointer= ?";
		try (PreparedStatement statement = connection.prepareStatement(query)) {
			statement.setObject(1, lexiconId);
			try (ResultSet rs = statement.executeQuery()) {
				while (rs.next()) {
					String tense = rs.getString("tense");
					String decodedSurface = URLDecoder.decode(rs.getString("surface"), "UTF-8");
					String decodedRoot = URLDecoder.decode(rs.getString("root"), "UTF-8");
					if (tense.equals("bareInfinitive")) {
						output = output
								+ "<tr bgcolor=\"AliceBlue\">"
								+ "<% } else if (tense.equals(\"infinitive\")) { %>"
								+ "<tr bgcolor=\"cornSilk\">"
								+ "<% } else if (tense.equals(\"past\")) { %>"
								+ "<tr bgcolor=\"pink\">"
								+ "<% } else if (tense.equals(\"beinoni\")) { %>"
								+ "<tr bgcolor=\"Moccasin\">"
								+ "<% } else if (tense.equals(\"future\")) { %>"
								+ "<tr bgcolor=\"whiteSmoke\">";

					} else {
						output = output + "<tr>";
					}
					output = output
							+ "<td align=\"center\"><font size=5>"
							+ decodedSurface
							+ "</font></td>"
							+ "<td>"
							+ rs.getString("transliterated")
							+ "</td>"
							+ "<td>"
							+ rs.getString("basePos")
							+ "</td>"
							+ "<td>"
							+ rs.getString("baseLexiconPointer")
							+ "</td>"
							+ "<td>"
							+ rs.getString("baseTransliteratedLItem")
							+ "</td>"
							+ "<td>"
							+ rs.getString("baseNumber")
							+ "</td>"
							+ "<td>"
							+ rs.getString("baseGender")
							+ "</td>"
							+ "<td>"
							+ rs.getString("basePerson")
							+ "</td>"
							+ "<td>"
							+ rs.getString("PGN")
							+ "</td>"
							+ "<td>"
							+ rs.getString("suffixFunction")
							+ "</td>"
							+ "<td>"
							+ rs.getString("suffixStatus")
							+ "</td>"
							+ "<td>"
							+ rs.getString("binyan")
							+ "</td>"
							+ "<td>"
							+ decodedRoot
							+ "</td>"
							+ "<td align=\"center\" ><font color=\"blue\" size=5>"
							+ rs.getString("tense") + "</font></td>" + "<td>"
							+ rs.getString("suffixGender") + "</td>" + "<td>"
							+ rs.getString("suffixNumber") + "</td>" + "<td>"
							+ rs.getString("suffixPerson") + "</td>" + "<td>"
							+ rs.getString("baseDefinitness") + "</td>"
							+ "<td>" + rs.getString("script") + "</td>"
							+ "<td>" + rs.getString("accusativeNominativePGN")
							+ "</td>" + "<td>"
							+ rs.getString("accusativeNominativePerson")
							+ "</td>" + "<td>"
							+ rs.getString("accusativeNominativeGender")
							+ "</td>" + "<td>"
							+ rs.getString("accusativeNominativeNumber")
							+ "</td>" + "</tr>";

				}
			}
		} catch (SQLException | UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return output;
	}
	@Override
	public void close() throws Exception {
		connection.close();
	}
}
