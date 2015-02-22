package lexicon;

import java.net.URLDecoder;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ShowNounInflections {

	String lexiconId="";

	static Connection connection=null;

	static ResultSet rs= null;

	static {
		try {

			connection = null;

			Class.forName("org.mariadb.jdbc.Driver").newInstance();
			//connection = DriverManager.getConnection(
		//			"jdbc:mariadb://yeda.cs.technion.ac.il:3306/generatorTest",
		//			"dandy", "yachuF6baqetREJa");
//			connection = DriverManager.getConnection(
//					"jdbc:mariadb://yeda.cs.technion.ac.il:3306/generator",
// 					"dummy1", "health&happiness");
		} catch (Exception e) {
			e.printStackTrace();
			System.exit(0);
		}
	}

	public ShowNounInflections(String lexiconId){
		this.lexiconId = lexiconId;

	}
	public String getDual() {
		String dual = "";
		try {

			connection = DriverManager.getConnection(
					"jdbc:mariadb://yeda.cs.technion.ac.il:3306/generator",
 					"dummy1", "health&happiness");
			PreparedStatement statement = null;
			statement = connection.prepareStatement
			("SELECT *  FROM inflections where register='formal' and spelling = 'standard'  and baseLexiconPointer= ? and (baseNumber ='dual' or baseNumber='dual and plural') and PGN='unspecified' and baseDefinitness ='tf'" );
			statement.setObject(1, lexiconId);
			rs = statement.executeQuery();
			while (rs.next()) {
				String surface = rs.getString("surface");
				if(!dual.equals("")) {
					dual  = dual + ", " + URLDecoder.decode(surface,"UTF-8");
				} else {
					dual =  URLDecoder.decode(surface,"UTF-8");
				}
				}
			rs.close();
			statement.close();
			connection.close();

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		return dual;
	}



	public String getMasculineSingularConstruct() {
		String masculineSingularConstruct="";
		try {
			connection = DriverManager.getConnection(
					"jdbc:mariadb://yeda.cs.technion.ac.il:3306/generator",
 					"dummy1", "health&happiness");
			PreparedStatement statement = null;
			statement = connection
					.prepareStatement("SELECT *  FROM inflections where register='formal' and spelling = 'standard'  and baseLexiconPointer=  ?   and baseDefinitness!='tt' and PGN='unspecified'  and baseGender='masculine' and baseNumber ='singular'" );;
			statement.setObject(1, lexiconId);
			rs = statement.executeQuery();
			while (rs.next()) {
				String surface = rs.getString("surface");
				if(!masculineSingularConstruct.equals("")) {
					masculineSingularConstruct  = masculineSingularConstruct + ", " + URLDecoder.decode(surface,"UTF-8");
				} else {
					masculineSingularConstruct =  URLDecoder.decode(surface,"UTF-8");
				}
			}
			rs.close();
			statement.close();
			connection.close();

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return masculineSingularConstruct;
	}



	public String getMasculinePluralConstruct() {
		String masculinePluralConstruct="";
		try {
			connection = DriverManager.getConnection(
					"jdbc:mariadb://yeda.cs.technion.ac.il:3306/generator",
 					"dummy1", "health&happiness");
			PreparedStatement statement = null;
			statement = connection
					.prepareStatement("SELECT *  FROM inflections where register='formal' and spelling = 'standard'  and baseLexiconPointer=  ? and baseDefinitness!='tt' and PGN='unspecified'  and baseGender='masculine' and baseNumber !='dual' and  baseNumber !='singular'" );
			statement.setObject(1, lexiconId);
			rs = statement.executeQuery();
			while (rs.next()) {
				String surface = rs.getString("surface");
				if(! masculinePluralConstruct.equals("")) {
					masculinePluralConstruct  =  masculinePluralConstruct + ", " + URLDecoder.decode(surface,"UTF-8");
				} else {
					masculinePluralConstruct =  URLDecoder.decode(surface,"UTF-8");
				}
			}
			rs.close();
			statement.close();
			connection.close();

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return  masculinePluralConstruct;
	}




	public String getFeminineSingularConstruct() {
		String feminineSingularConstruct="";
		try {
			connection = DriverManager.getConnection(
					"jdbc:mariadb://yeda.cs.technion.ac.il:3306/generator",
 					"dummy1", "health&happiness");
			PreparedStatement statement = null;
			statement = connection
					.prepareStatement("SELECT *  FROM inflections where register='formal' and spelling = 'standard'  and baseLexiconPointer=  ?   and PGN='unspecified' and baseDefinitness!='tt' and baseGender='feminine' and baseNumber='singular'"  );
			statement.setObject(1, lexiconId);
			rs = statement.executeQuery();
			while (rs.next()) {
				String surface = rs.getString("surface");
				if(! feminineSingularConstruct.equals("")) {
					feminineSingularConstruct  =  feminineSingularConstruct + ", " + URLDecoder.decode(surface,"UTF-8");
				} else {
					feminineSingularConstruct =  URLDecoder.decode(surface,"UTF-8");
				}
			}
			rs.close();
			statement.close();
			connection.close();

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return feminineSingularConstruct;
	}




	public String getFemininePluralConstruct() {
		String femininePluralConstruct="";
		try {
			connection = DriverManager.getConnection(
					"jdbc:mariadb://yeda.cs.technion.ac.il:3306/generator",
 					"dummy1", "health&happiness");
			PreparedStatement statement = null;
			statement = connection
					.prepareStatement("SELECT *  FROM inflections where register='formal' and spelling = 'standard'  and   baseLexiconPointer= ?   and PGN='unspecified' and baseDefinitness!='tt' and baseGender='feminine' and baseNumber!='dual' and baseNumber !='singular'"  );
			statement.setObject(1, lexiconId);
			rs = statement.executeQuery();
			while (rs.next()) {
				String surface = rs.getString("surface");
				if(!femininePluralConstruct.equals("")) {
					femininePluralConstruct =  femininePluralConstruct + ", " + URLDecoder.decode(surface,"UTF-8");
				} else {
					femininePluralConstruct =  URLDecoder.decode(surface,"UTF-8");
				}
			}
			rs.close();
			statement.close();
			connection.close();

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return femininePluralConstruct;
	}






	public String getPGNMasculineSingular() {
		String PGNMasculineSingular="";
		try {
			connection = DriverManager.getConnection(
					"jdbc:mariadb://yeda.cs.technion.ac.il:3306/generator",
 					"dummy1", "health&happiness");
			PreparedStatement statement = null;
			statement = connection
					.prepareStatement("SELECT *  FROM inflections where register='formal' and spelling = 'standard'  and baseLexiconPointer= ?  and PGN!='unspecified'  and baseNumber='singular' and baseGender='masculine'");
			statement.setObject(1, lexiconId);
			rs = statement.executeQuery();
			while (rs.next()) {
				String surface = rs.getString("surface");
				if(!PGNMasculineSingular.equals("")) {
					PGNMasculineSingular =  PGNMasculineSingular + ", " + URLDecoder.decode(surface,"UTF-8");
				} else {
					PGNMasculineSingular =  URLDecoder.decode(surface,"UTF-8");
				}
			}
			rs.close();
			statement.close();
			connection.close();

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return PGNMasculineSingular;
	}





	public String getPGNFeminineSingular() {
		String PGNFeminineSingular="";
		try {
			connection = DriverManager.getConnection(
					"jdbc:mariadb://yeda.cs.technion.ac.il:3306/generator",
 					"dummy1", "health&happiness");
			PreparedStatement statement = null;
			statement = connection
					.prepareStatement("SELECT *  FROM inflections where register='formal' and spelling = 'standard'  and baseLexiconPointer= ? and PGN!='unspecified' and baseNumber='singular' and baseGender='feminine'");
			statement.setObject(1, lexiconId);
			rs = statement.executeQuery();
			while (rs.next()) {
				String surface = rs.getString("surface");
				if(!PGNFeminineSingular.equals("")) {
					PGNFeminineSingular =  PGNFeminineSingular + ", " + URLDecoder.decode(surface,"UTF-8");
				} else {
					PGNFeminineSingular =  URLDecoder.decode(surface,"UTF-8");
				}
			}
			rs.close();
			statement.close();
			connection.close();

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return PGNFeminineSingular;
	}



	public String getPGNMasculinePlural() {
		String PGNMasculinePlural="";
		try {
			connection = DriverManager.getConnection(
					"jdbc:mariadb://yeda.cs.technion.ac.il:3306/generator",
 					"dummy1", "health&happiness");
			PreparedStatement statement = null;
			statement = connection
					.prepareStatement("SELECT *  FROM inflections where register='formal' and spelling = 'standard'  and baseLexiconPointer= ?   and PGN!='unspecified' and (baseNumber='plural' or baseNumber='dual and plural')and baseGender='masculine'" );
			statement.setObject(1, lexiconId);
			rs = statement.executeQuery();
			while (rs.next()) {
				String surface = rs.getString("surface");
				if(!PGNMasculinePlural.equals("")) {
					PGNMasculinePlural =  PGNMasculinePlural + ", " + URLDecoder.decode(surface,"UTF-8");
				} else {
					PGNMasculinePlural =  URLDecoder.decode(surface,"UTF-8");
				}
			}
			rs.close();
			statement.close();
			connection.close();

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return PGNMasculinePlural;
	}


	public String getPGNFemininePlural() {
		String PGNFemininePlural="";
		try {
			connection = DriverManager.getConnection(
					"jdbc:mariadb://yeda.cs.technion.ac.il:3306/generator",
 					"dummy1", "health&happiness");
			PreparedStatement statement = null;
			statement = connection
					.prepareStatement("SELECT *  FROM inflections where register='formal' and spelling = 'standard'  and baseLexiconPointer= ? and PGN!='unspecified' and baseNumber='plural'  and baseGender='feminine' " );
			statement.setObject(1, lexiconId);
			rs = statement.executeQuery();
			while (rs.next()) {
				String surface = rs.getString("surface");
				if(!PGNFemininePlural.equals("")) {
					PGNFemininePlural=  PGNFemininePlural + ", " + URLDecoder.decode(surface,"UTF-8");
				} else {
					PGNFemininePlural=  URLDecoder.decode(surface,"UTF-8");
				}
			}
			rs.close();
			statement.close();
			connection.close();

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return PGNFemininePlural;
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
