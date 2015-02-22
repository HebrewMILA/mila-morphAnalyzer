package lexicon;

import java.net.URLDecoder;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ShowNumeralInflections {

	String lexiconId="";

	static Connection connection=null;

	static ResultSet rs= null;

	static {
		try {

			connection = null;

			Class.forName("org.mariadb.jdbc.Driver").newInstance();
//			connection = DriverManager.getConnection(
//					"jdbc:mariadb://yeda.cs.technion.ac.il:3306/generatorTest",
//					"dandy", "yachuF6baqetREJa");
//			connection = DriverManager.getConnection(
//					"jdbc:mariadb://yeda.cs.technion.ac.il:3306/generator",
// 					"dummy1", "health&happiness");
		} catch (Exception e) {
			e.printStackTrace();
			System.exit(0);
		}
	}

	public ShowNumeralInflections(String lexiconId){
		this.lexiconId = lexiconId;

	}
	public String getConstruct() {
		String construct = "";
		try {

			connection = DriverManager.getConnection(
					"jdbc:mariadb://yeda.cs.technion.ac.il:3306/generator",
 					"dummy1", "health&happiness");
			PreparedStatement statement = null;
			statement = connection.prepareStatement
			("SELECT *  FROM inflections where register='formal' and spelling = 'standard' and baseLexiconPointer=  ?   and suffixStatus='true' ");
			statement.setObject(1, lexiconId);
			rs = statement.executeQuery();
			while (rs.next()) {
				String surface = rs.getString("surface");
				if(!construct.equals("")) {
					construct  = construct + ", " + URLDecoder.decode(surface,"UTF-8");
				} else {
					construct =  URLDecoder.decode(surface,"UTF-8");
				}
				}
			rs.close();
			statement.close();
			connection.close();

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		return construct;
	}



	public String getBase() {
		String base="";
		try {
			connection = DriverManager.getConnection(
					"jdbc:mariadb://yeda.cs.technion.ac.il:3306/generator",
 					"dummy1", "health&happiness");
			PreparedStatement statement = null;
			statement = connection
					.prepareStatement("SELECT *  FROM inflections where register='formal' and spelling = 'standard'  and baseLexiconPointer= ?  and suffixStatus='false' and baseDefinitness='tf' and PGN='unspecified'");
			statement.setObject(1, lexiconId);
			rs = statement.executeQuery();
			while (rs.next()) {
				String surface = rs.getString("surface");
				if(!base.equals("")) {
					base  = base + ", " + URLDecoder.decode(surface,"UTF-8");
				} else {
					base =  URLDecoder.decode(surface,"UTF-8");
				}
			}
			rs.close();
			statement.close();
			connection.close();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return base;
	}



	public String getPGNSingular() {
		String PGNSingular ="";
		try {
			connection = DriverManager.getConnection(
					"jdbc:mariadb://yeda.cs.technion.ac.il:3306/generator",
 					"dummy1", "health&happiness");
			PreparedStatement statement = null;
			statement = connection
					.prepareStatement("SELECT *  FROM inflections where register='formal' and spelling = 'standard'  and baseLexiconPointer= ?  and   PGN!='unspecified' and baseNumber='singular'");
			statement.setObject(1, lexiconId);
			rs = statement.executeQuery();
			while (rs.next()) {
				String surface = rs.getString("surface");
				if(! PGNSingular.equals("")) {
					PGNSingular  =  PGNSingular  + ", " + URLDecoder.decode(surface,"UTF-8");
				} else {
					PGNSingular  =  URLDecoder.decode(surface,"UTF-8");
				}
			}
			rs.close();
			statement.close();
			connection.close();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return  PGNSingular;
	}




	public String getPGNPlural() {
		String PGNPlural="";
		try {
			connection = DriverManager.getConnection(
					"jdbc:mariadb://yeda.cs.technion.ac.il:3306/generator",
 					"dummy1", "health&happiness");
			PreparedStatement statement = null;
			statement = connection
					.prepareStatement("SELECT *  FROM inflections where register='formal' and spelling = 'standard' and baseLexiconPointer=  ?   and   PGN!='unspecified' and baseNumber='plural'");
			rs = statement.executeQuery();
			while (rs.next()) {
				String surface = rs.getString("surface");
				if(! PGNPlural.equals("")) {
					PGNPlural  =  PGNPlural + ", " + URLDecoder.decode(surface,"UTF-8");
				} else {
					PGNPlural =  URLDecoder.decode(surface,"UTF-8");
				}
			}
			rs.close();
			statement.close();
			connection.close();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return PGNPlural;
	}





 public static void releaseConnection() {

		 try {
			rs.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	 }


	public static void main(String[] args) {

//		ShowInflections s = new ShowInflections();
//		Vector infinitiveVec = new Vector();
//		s.getBeinoniInflections("4241", infinitiveVec);
//		System.out.println();
	}

}
