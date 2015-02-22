package lexicon;
import java.net.URLDecoder;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
public class GeneratorViewer {

	public String showInflections(String lexiconId) {
		ResultSet rs = null;
		String output="";

		try {

			Connection connection = null;
			PreparedStatement statement = null;

			Class.forName("org.mariadb.jdbc.Driver").newInstance();
//			connection = DriverManager.getConnection(
//					"jdbc:mariadb://yeda.cs.technion.ac.il:3306/generatorTest",
//					"dandy", "yachuF6baqetREJa");
			connection = DriverManager.getConnection(
					"jdbc:mariadb://yeda.cs.technion.ac.il:3306/generator",
					"dummy1", "health&happiness");
			statement = connection
					.prepareStatement("SELECT * FROM inflections WHERE baseLexiconPointer= ?");
			statement.setObject(1, lexiconId);
			rs = statement.executeQuery();

			output =
"<tr><th>inflection</th>"
+"<th>transliterated</th>"
+"<th>pos</th>"
+"<th>lexicon id</th>"
+"<th>lexicon item</th>"
+"<th>inflection number</th>"
+"<th>inflection gender</th>"
+"<th>inflection person</th>"
+"<th>PGN</th>"
+"<th>suffixFunction</th>"
+"<th>construct</th>"
+"<th>binyan</th>"
+"<th>root</th>"
+"<th>tense</th>"
+"<th>possessive gender</th>"
+"<th>possessive number</th>"
+"<th>possessive Person</th>"
+"<th>definitness</th>"
+"<th>script</th>"
+"<th>a/n PGN</th>"
+"<th>a/n person</th>"
+"<th>a/n gender</th>"
+"<th>a/n number</th>"
+"</tr>";

			String decodedRoot="";
			while (rs.next()) {
			String surface = rs.getString("surface");
			String decodedSurface =URLDecoder.decode(surface,"UTF-8");
			String root = rs.getString("root");
			if (!root.equals("") && root !=null) {
				decodedRoot = URLDecoder.decode(root,"UTF-8");
			}
			String tense =rs.getString("tense");
			if(tense.equals("bareInfinitive")){
			output = output +
			"<tr bgcolor=\"AliceBlue\">"+
			"<% } else if (tense.equals(\"infinitive\")) { %>"+
			"<tr bgcolor=\"cornSilk\">"+
			"<% } else if (tense.equals(\"past\")) { %>"+
			"<tr bgcolor=\"pink\">"+
			"<% } else if (tense.equals(\"beinoni\")) { %>"+
			"<tr bgcolor=\"Moccasin\">"+
			"<% } else if (tense.equals(\"future\")) { %>"+
			"<tr bgcolor=\"whiteSmoke\">";

			} else{
				output = output + "<tr>";
			}

			output = output +
			"<td align=\"center\"><font size=5>"+ decodedSurface +"</font></td>"+
			"<td>"+ rs.getString("transliterated")+"</td>"+
			"<td>"+ rs.getString("basePos")+"</td>"+
			"<td>"+ rs.getString("baseLexiconPointer")+"</td>"+
			"<td>"+ rs.getString("baseTransliteratedLItem")+"</td>"+
			"<td>"+ rs.getString("baseNumber") +"</td>"+
			"<td>"+ rs.getString("baseGender") +"</td>"+
			"<td>"+ rs.getString("basePerson") +"</td>"+
			"<td>"+ rs.getString("PGN") +"</td>"+
			"<td>"+ rs.getString("suffixFunction") +"</td>"+
			"<td>"+ rs.getString("suffixStatus") +"</td>"+
			"<td>"+ rs.getString("binyan") +"</td>"+
			"<td>"+ decodedRoot +"</td>"+
			"<td align=\"center\" ><font color=\"blue\" size=5>"+ rs.getString("tense") +"</font></td>"+
			"<td>"+ rs.getString("suffixGender") +"</td>"+
			"<td>"+ rs.getString("suffixNumber") +"</td>"+
			"<td>"+ rs.getString("suffixPerson") +"</td>"+
			"<td>"+ rs.getString("baseDefinitness") +"</td>"+
			"<td>"+ rs.getString("script") +"</td>"+
			"<td>"+ rs.getString("accusativeNominativePGN") +"</td>"+
			"<td>"+ rs.getString("accusativeNominativePerson") +"</td>"+
			"<td>"+ rs.getString("accusativeNominativeGender") +"</td>"+
			"<td>"+ rs.getString("accusativeNominativeNumber") +"</td>"+
			"</tr>";

			}
			rs.close();

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return output;
	}

}
