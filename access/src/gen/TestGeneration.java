package gen;

import java.net.URLDecoder;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class TestGeneration {

	public void deleteInflections() {
		try {

			Connection connection = null;
			PreparedStatement statement = null;

			Class.forName("org.mariadb.jdbc.Driver").newInstance();
			connection = DriverManager.getConnection(
					"jdbc:mariadb://yeda.cs.technion.ac.il:3306/lexiconTest",
					"maital", "AnaXAd3Ke@aJ8F");
			statement = connection.prepareStatement("delete from inflections");
			statement.execute();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	public String showInflections(String lexiconId) {
		ResultSet rs = null;
		String output = "";

		try {

			Connection connection = null;
			PreparedStatement statement = null;

			Class.forName("org.mariadb.jdbc.Driver").newInstance();
			// connection = DriverManager.getConnection(
			// "jdbc:mariadb://yeda.cs.technion.ac.il:3306/generatorTest",
			// "dandy", "yachuF6baqetREJa");
			connection = DriverManager.getConnection(
					"jdbc:mariadb://yeda.cs.technion.ac.il:3306/lexiconTest",
					"maital", "AnaXAd3Ke@aJ8F");
			statement = connection
					.prepareStatement("SELECT * FROM inflections WHERE baseLexiconPointer= ?");
			statement.setObject(1, lexiconId);
			rs = statement.executeQuery();

			output = "<tr><th>inflection</th>" + "<th>transliterated</th>"
					+ "<th>pos</th>" + "<th>lexicon id</th>"
					+ "<th>lexicon item</th>" + "<th>inflection number</th>"
					+ "<th>inflection gender</th>"
					+ "<th>inflection person</th>"
					+ "<th>PGN</th>"
					+ "<th>suffixFunction</th>"
					+ "<th>construct</th>" + "<th>binyan</th>"
					+ "<th>root</th>" + "<th>tense</th>"
					+ "<th>definitness</th>"
					+ "<th>register</th>"
					+ "<th>spelling</th>"
					+ "<th>polarity</th>"
					+ "<th>prefix</th>"
					+ "<th>type</th>"
					 + "</tr>";

			String decodedRoot = "";
			while (rs.next()) {
				String surface = rs.getString("surface");
				String decodedSurface = URLDecoder.decode(surface, "UTF-8");
				String root = rs.getString("root");
				if (!root.equals("") && root != null) {
					decodedRoot = URLDecoder.decode(root, "UTF-8");
				}
				String tense = rs.getString("tense");
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

				output = output + "<td align=\"center\"><font size=5>"
						+ decodedSurface + "</font></td>" + "<td>"
						+ rs.getString("transliterated") + "</td>" + "<td>"
						+ rs.getString("basePos") + "</td>" + "<td>"
						+ rs.getString("baseLexiconPointer") + "</td>" + "<td>"
						+ rs.getString("baseTransliteratedLItem") + "</td>"
						+ "<td>" + rs.getString("baseNumber") + "</td>"
						+ "<td>" + rs.getString("baseGender") + "</td>"
						+ "<td>" + rs.getString("basePerson") + "</td>"
						+ "<td>" + rs.getString("PGN") + "</td>"
						+ "<td>" + rs.getString("suffixFunction") + "</td>"
						+ "<td>"
						+ rs.getString("suffixStatus") + "</td>" + "<td>"
						+ rs.getString("binyan") + "</td>" + "<td>"
						+ decodedRoot + "</td>"
						+ "<td align=\"center\" ><font color=\"blue\" size=5>"
						+ rs.getString("tense") + "</font></td>" + "<td>"
						+ rs.getString("baseDefinitness") + "</td>"
						+ "<td>"+ rs.getString("register") + "</td>" +
						"<td>"+ rs.getString("spelling") + "</td>" +
						"<td>" + rs.getString("polarity") + "</td>" +
						"<td>" + rs.getString("prefix") + "</td>"+
						"<td>" + rs.getString("type") + "</td>"
						+ "</tr>";

			}
			rs.close();

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return output;
	}

}
