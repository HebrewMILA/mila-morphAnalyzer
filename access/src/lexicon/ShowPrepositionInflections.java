package lexicon;

import java.net.URLDecoder;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ShowPrepositionInflections {

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
//					"dummy1", "health&happiness");
		} catch (Exception e) {
			e.printStackTrace();
			System.exit(0);
		}
	}

	public ShowPrepositionInflections(String lexiconId){
		this.lexiconId = lexiconId;

	}






	public String getPGN() {
		String MasculineSingularConstruct="";
		try {
			connection = DriverManager.getConnection(
					"jdbc:mariadb://yeda.cs.technion.ac.il:3306/generator",
 					"dummy1", "health&happiness");
			PreparedStatement statement = null;
			statement = connection
					.prepareStatement("SELECT *  FROM inflections where register='formal' and spelling = 'standard'  and baseLexiconPointer= ?  and suffixFunction!='unspecified' ");
					//order by accusativeNominativeNumber DESC , accusativeNominativePerson ASC");
			statement.setObject(1, lexiconId);
			rs = statement.executeQuery();
			while (rs.next()) {
				String surface = rs.getString("surface");
				if(! MasculineSingularConstruct.equals("")) {
					MasculineSingularConstruct  =  MasculineSingularConstruct + ", " + URLDecoder.decode(surface,"UTF-8");
				} else {
					MasculineSingularConstruct =  URLDecoder.decode(surface,"UTF-8");
				}
			}
			rs.close();
			statement.close();
			connection.close();

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return  MasculineSingularConstruct;
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
