package gen;

import java.net.URLDecoder;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class TestMWGeneration 
{
	public void deleteMWInflections() 
	{
		try 
		{
			Connection connection = null;
			PreparedStatement statement = null;

			Class.forName("com.mysql.jdbc.Driver").newInstance();
			connection = DriverManager.getConnection("jdbc:mysql://yeda.cs.technion.ac.il:3306/lexiconTest","maital", "AnaXAd3Ke@aJ8F");
			statement = connection.prepareStatement("delete from mweinflections");
			statement.execute();
			statement = connection.prepareStatement("delete from mwe1");
			statement.execute();
			statement = connection.prepareStatement("delete from mwe2");
			statement.execute();
			statement = connection.prepareStatement("delete from mwe3");
			statement.execute();
			statement = connection.prepareStatement("delete from mwe4");
			statement.execute();
		} 
		catch (Exception e) 
		{
			System.out.println(e.getMessage());
		}
	}

	public String showMW2Inflections(String lexiconId) 
	{
		ResultSet rs = null;
		String output = "";
		try 
		{
			Connection connection = null;
			PreparedStatement statement = null;

			Class.forName("com.mysql.jdbc.Driver").newInstance();		
			connection = DriverManager.getConnection("jdbc:mysql://yeda.cs.technion.ac.il:3306/lexiconTest","maital", "AnaXAd3Ke@aJ8F");
			statement = connection.prepareStatement("SELECT * FROM mwe2 WHERE lexiconId= ?");
			statement.setObject(1, lexiconId);
			rs = statement.executeQuery();

			output = "<tr><th>lexiconId</th>" 
					+ "<th>transliteratedLexiconItem</th>"
					+ "<th>undottedLexiconItem</th>" 
					+ "<th>dottedLexiconItem</th>"
					+ "<th>mwTransliterated</th>"
					+ "<th>mwUndotted</th>"
					+ "<th>PGN</th>"
					+ "<th>spelling</th>"
					+ "<th>register</th>"
					+ "<th>gender</th>"
					+ "<th>number</th>"
					+ "<th>definiteness</th>"					
					 + "</tr>";
			
			while (rs.next()) 
			{				
				String undottedLexiconItem = rs.getString("undottedLexiconItem");
				String decodedUndottedLexiconItem = URLDecoder.decode(undottedLexiconItem, "UTF-8");
				
				String dottedLexiconItem = rs.getString("dottedLexiconItem");
				String decodedDottedLexiconItem = URLDecoder.decode(dottedLexiconItem, "UTF-8");
				
				String mwUndotted = rs.getString("mwUndotted");
				String decodedMwUndotted = URLDecoder.decode(mwUndotted, "UTF-8");
					
				output = output + "<tr>";				
				output = output + "<td align=\"center\">"
				        + lexiconId + "</td>"
				        + "<td>" + rs.getString("transliteratedLexiconItem") + "</td>"
				        + "<td>" + decodedUndottedLexiconItem + "</td>"
				        + "<td>" + decodedDottedLexiconItem + "</td>"
				        + "<td>" + rs.getString("mwTransliterated") + "</td>"
				        + "<td><font color=\"blue\">" + decodedMwUndotted + "</font></td>"
				        + "<td>" + rs.getString("PGN") + "</td>"
				        + "<td>" + rs.getString("spelling") + "</td>"
				        + "<td>" + rs.getString("register") + "</td>"
				        + "<td>" + rs.getString("gender") + "</td>"
				        + "<td>" + rs.getString("number") + "</td>"
				        + "<td>" + rs.getString("definiteness") + "</td>" 
				        + "</tr>";				        				       				   
			}
			rs.close();

		} 
		catch (Exception e) 
		{
			System.out.println(e.getMessage());
		}
		return output;
	}

	public String showMW3Inflections(String lexiconId) 
	{
		ResultSet rs = null;
		String output = "";

		try 
		{

			Connection connection = null;
			PreparedStatement statement = null;

			Class.forName("com.mysql.jdbc.Driver").newInstance();		
			connection = DriverManager.getConnection("jdbc:mysql://yeda.cs.technion.ac.il:3306/lexiconTest","maital", "AnaXAd3Ke@aJ8F");
			statement = connection.prepareStatement("SELECT * FROM mwe3 WHERE lexiconId= ?");
			statement.setObject(1, lexiconId);
			rs = statement.executeQuery();

			output = "<tr><th>lexiconId</th>" 
					+ "<th>transliteratedLexiconItem</th>"
					+ "<th>undottedLexiconItem</th>" 
					+ "<th>dottedLexiconItem</th>"
					+ "<th>mwTransliterated</th>"
					+ "<th>mwUndotted</th>"
					+ "<th>PGN</th>"
					+ "<th>spelling</th>"
					+ "<th>register</th>"
					+ "<th>gender</th>"
					+ "<th>number</th>"
					+ "<th>definiteness</th>"					
					 + "</tr>";
			
			while (rs.next()) 
			{						
				String undottedLexiconItem = rs.getString("undottedLexiconItem");
				String decodedUndottedLexiconItem = URLDecoder.decode(undottedLexiconItem, "UTF-8");
				
				String dottedLexiconItem = rs.getString("dottedLexiconItem");
				String decodedDottedLexiconItem = URLDecoder.decode(dottedLexiconItem, "UTF-8");
								
				String mwUndotted = rs.getString("mwUndotted");
				String decodedMwUndotted = URLDecoder.decode(mwUndotted, "UTF-8");
				
				output = output + "<tr>";
				
				output = output + "<td align=\"center\">"
		        + lexiconId + "</td>"
		        + "<td>" + rs.getString("transliteratedLexiconItem") + "</td>"
		        + "<td>" + decodedUndottedLexiconItem + "</td>"
		        + "<td>" + decodedDottedLexiconItem + "</td>"
		        + "<td>" + rs.getString("mwTransliterated") + "</td>"
		        + "<td><font color=\"blue\">" + decodedMwUndotted + "</font></td>"
		        + "<td>" + rs.getString("PGN") + "</td>"
		        + "<td>" + rs.getString("spelling") + "</td>"
		        + "<td>" + rs.getString("register") + "</td>"
		        + "<td>" + rs.getString("gender") + "</td>"
		        + "<td>" + rs.getString("number") + "</td>"
		        + "<td>" + rs.getString("definiteness") + "</td>" 
		        + "</tr>";					        				       				   
			}
			rs.close();

		} 
		catch (Exception e) 
		{
			System.out.println(e.getMessage());
		}
		return output;
	}

	public String showMW4Inflections(String lexiconId) 
	{
		ResultSet rs = null;
		String output = "";
		try 
		{
			Connection connection = null;
			PreparedStatement statement = null;

			Class.forName("com.mysql.jdbc.Driver").newInstance();	
			connection = DriverManager.getConnection("jdbc:mysql://yeda.cs.technion.ac.il:3306/lexiconTest","maital", "AnaXAd3Ke@aJ8F");
			statement = connection.prepareStatement("SELECT * FROM mwe4 WHERE lexiconId= ?");
			statement.setObject(1, lexiconId);
			rs = statement.executeQuery();

			output = "<tr><th>lexiconId</th>" 
					+ "<th>transliteratedLexiconItem</th>"
					+ "<th>undottedLexiconItem</th>" 
					+ "<th>dottedLexiconItem</th>"
					+ "<th>mwTransliterated</th>"
					+ "<th>mwUndotted</th>"
					+ "<th>PGN</th>"
					+ "<th>spelling</th>"
					+ "<th>register</th>"
					+ "<th>gender</th>"
					+ "<th>number</th>"
					+ "<th>definiteness</th>"					
					 + "</tr>";
			
			while (rs.next()) 
			{					
				String undottedLexiconItem = rs.getString("undottedLexiconItem");
				String decodedUndottedLexiconItem = URLDecoder.decode(undottedLexiconItem, "UTF-8");
				
				String dottedLexiconItem = rs.getString("dottedLexiconItem");
				String decodedDottedLexiconItem = URLDecoder.decode(dottedLexiconItem, "UTF-8");
					
				String mwUndotted = rs.getString("mwUndotted");
				String decodedMwUndotted = URLDecoder.decode(mwUndotted, "UTF-8");
				
				output = output + "<tr>";
				output = output + "<td align=\"center\">"
		        + lexiconId + "</td>"
		        + "<td>" + rs.getString("transliteratedLexiconItem") + "</td>"
		        + "<td>" + decodedUndottedLexiconItem + "</td>"
		        + "<td>" + decodedDottedLexiconItem + "</td>"
		        + "<td>" + rs.getString("mwTransliterated") + "</td>"
		        + "<td><font color=\"blue\">" + decodedMwUndotted + "</font></td>"
		        + "<td>" + rs.getString("PGN") + "</td>"
		        + "<td>" + rs.getString("spelling") + "</td>"
		        + "<td>" + rs.getString("register") + "</td>"
		        + "<td>" + rs.getString("gender") + "</td>"
		        + "<td>" + rs.getString("number") + "</td>"
		        + "<td>" + rs.getString("definiteness") + "</td>" 
		        + "</tr>";				        				       				   
			}
			rs.close();
		} 
		catch (Exception e) 
		{
			System.out.println(e.getMessage());
		}
		return output;
	}	
}
