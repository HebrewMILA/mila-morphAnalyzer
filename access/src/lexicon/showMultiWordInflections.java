package lexicon;

import java.net.URLDecoder;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class showMultiWordInflections {
    String lexiconId = "";

    static Connection connection = null;

    static ResultSet rs = null;

    static {
	try {

	    connection = null;

	    Class.forName("org.mariadb.jdbc.Driver").newInstance();
	    // connection = DriverManager.getConnection(
	    // "jdbc:mariadb://yeda.cs.technion.ac.il:3306/generatorTest",
	    // "dandy", "yachuF6baqetREJa");
	    // connection = DriverManager.getConnection(
	    // "jdbc:mariadb://yeda.cs.technion.ac.il:3306/generator",
	    // "dummy1", "health&happiness");
	} catch (Exception e) {
	    e.printStackTrace();
	    System.exit(0);
	}
    }

    public showMultiWordInflections(String lexiconId) {
	this.lexiconId = lexiconId;

    }

    /*
     * public String getDual() { String dual = ""; try {
     *
     * connection = DriverManager.getConnection(
     * "jdbc:mariadb://yeda.cs.technion.ac.il:3306/generator", "dummy1",
     * "health&happiness"); PreparedStatement statement = null; statement =
     * connection.prepareStatement (
     * "SELECT *  FROM inflections where register='formal' and spelling = 'standard'  and baseLexiconPointer= ? and (baseNumber ='dual' or baseNumber='dual and plural') and PGN='unspecified' and baseDefinitness ='tf'"
     * ); statement.setObject(1, lexiconId); rs = statement.executeQuery();
     * while (rs.next()) { String surface = rs.getString("surface");
     * if(!dual.equals("")) dual = dual + ", " +
     * URLDecoder.decode(surface,"UTF-8"); else dual =
     * URLDecoder.decode(surface,"UTF-8"); } rs.close(); statement.close();
     * connection.close();
     *
     * } catch (Exception e) { System.out.println(e.getMessage()); }
     *
     * return dual; }
     */

    public String getMasculineSingularConstruct(int tableNum) {
	String masculineSingularConstruct = "";
	try {
	    connection = DriverManager.getConnection(
		    "jdbc:mariadb://yeda.cs.technion.ac.il:3306/generatorTest",
		    "dummy1", "health&happiness");
	    PreparedStatement statement = null;
	    statement = connection
		    .prepareStatement("SELECT *  FROM mwe? where register='formal' and spelling = 'standard'  and lexiconId=  ?   and definiteness!='tt' and PGN='unspecified'  and gender='masculine' and number='singular'");
	    statement.setObject(1, tableNum);
	    statement.setObject(2, lexiconId);
	    rs = statement.executeQuery();
	    while (rs.next()) {
		String mwUndotted = rs.getString("mwUndotted");
		if (!masculineSingularConstruct.equals("")) {
			masculineSingularConstruct = masculineSingularConstruct
			    + ", " + URLDecoder.decode(mwUndotted, "UTF-8");
		} else {
			masculineSingularConstruct = URLDecoder.decode(mwUndotted,
			    "UTF-8");
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

    public String getMasculinePluralConstruct(int tableNum) {
	String masculinePluralConstruct = "";
	try {
	    connection = DriverManager.getConnection(
		    "jdbc:mariadb://yeda.cs.technion.ac.il:3306/generatorTest",
		    "dummy1", "health&happiness");
	    PreparedStatement statement = null;
	    statement = connection
		    .prepareStatement("SELECT *  FROM mwe? where register='formal' and spelling = 'standard'  and lexiconId=?   and definiteness!='tt' and PGN='unspecified'  and gender='masculine' and number='plural'");
	    statement.setObject(1, tableNum);
	    statement.setObject(2, lexiconId);
	    rs = statement.executeQuery();
	    while (rs.next()) {
		String mwUndotted = rs.getString("mwUndotted");
		if (!masculinePluralConstruct.equals("")) {
			masculinePluralConstruct = masculinePluralConstruct + ", "
			    + URLDecoder.decode(mwUndotted, "UTF-8");
		} else {
			masculinePluralConstruct = URLDecoder.decode(mwUndotted,
			    "UTF-8");
		}
	    }
	    rs.close();
	    statement.close();
	    connection.close();

	} catch (Exception e) {
	    System.out.println(e.getMessage());
	}
	return masculinePluralConstruct;
    }

    public String getFeminineSingularConstruct(int tableNum) {
	String feminineSingularConstruct = "";
	try {
	    connection = DriverManager.getConnection(
		    "jdbc:mariadb://yeda.cs.technion.ac.il:3306/generatorTest",
		    "dummy1", "health&happiness");
	    PreparedStatement statement = null;
	    statement = connection
		    .prepareStatement("SELECT *  FROM mwe? where register='formal' and spelling = 'standard'  and lexiconId=  ?   and definiteness!='tt' and PGN='unspecified'  and gender='feminine' and number='singular'");
	    statement.setObject(1, tableNum);
	    statement.setObject(2, lexiconId);

	    rs = statement.executeQuery();
	    while (rs.next()) {
		String mwUndotted = rs.getString("mwUndotted");
		if (!feminineSingularConstruct.equals("")) {
			feminineSingularConstruct = feminineSingularConstruct
			    + ", " + URLDecoder.decode(mwUndotted, "UTF-8");
		} else {
			feminineSingularConstruct = URLDecoder.decode(mwUndotted,
			    "UTF-8");
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

    public String getFemininePluralConstruct(int tableNum) {
	String femininePluralConstruct = "";
	try {
	    connection = DriverManager.getConnection(
		    "jdbc:mariadb://yeda.cs.technion.ac.il:3306/generatorTest",
		    "dummy1", "health&happiness");
	    PreparedStatement statement = null;
	    statement = connection
		    .prepareStatement("SELECT *  FROM mwe? where register='formal' and spelling = 'standard'  and lexiconId=  ?   and definiteness!='tt' and PGN='unspecified'  and gender='feminine' and number='plural'");
	    statement.setObject(1, tableNum);
	    statement.setObject(2, lexiconId);
	    rs = statement.executeQuery();
	    while (rs.next()) {
		String mwUndotted = rs.getString("mwUndotted");
		if (!femininePluralConstruct.equals("")) {
			femininePluralConstruct = femininePluralConstruct + ", "
			    + URLDecoder.decode(mwUndotted, "UTF-8");
		} else {
			femininePluralConstruct = URLDecoder.decode(mwUndotted,
			    "UTF-8");
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

    public String getPGNMasculineSingular(int tableNum) {
	String PGNMasculineSingular = "";
	try {
	    connection = DriverManager.getConnection(
		    "jdbc:mariadb://yeda.cs.technion.ac.il:3306/generatorTest",
		    "dummy1", "health&happiness");
	    PreparedStatement statement = null;
	    statement = connection
		    .prepareStatement("SELECT *  FROM mwe? where register='formal' and spelling = 'standard'  and lexiconId= ?  and PGN!='unspecified'  and number='singular' and gender='masculine'");
	    statement.setObject(1, tableNum);
	    statement.setObject(2, lexiconId);
	    rs = statement.executeQuery();
	    while (rs.next()) {
		String mwUndotted = rs.getString("mwUndotted");
		if (!PGNMasculineSingular.equals("")) {
			PGNMasculineSingular = PGNMasculineSingular + ", "
			    + URLDecoder.decode(mwUndotted, "UTF-8");
		} else {
			PGNMasculineSingular = URLDecoder.decode(mwUndotted,
			    "UTF-8");
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

    public String getPGNFeminineSingular(int tableNum) {
	String PGNFeminineSingular = "";
	try {
	    connection = DriverManager.getConnection(
		    "jdbc:mariadb://yeda.cs.technion.ac.il:3306/generatorTest",
		    "dummy1", "health&happiness");
	    PreparedStatement statement = null;
	    statement = connection
		    .prepareStatement("SELECT *  FROM mwe? where register='formal' and spelling = 'standard'  and lexiconId= ?  and PGN!='unspecified'  and number='singular' and gender='feminine'");
	    statement.setObject(1, tableNum);
	    statement.setObject(2, lexiconId);
	    rs = statement.executeQuery();
	    while (rs.next()) {
		String mwUndotted = rs.getString("mwUndotted");
		if (!PGNFeminineSingular.equals("")) {
			PGNFeminineSingular = PGNFeminineSingular + ", "
			    + URLDecoder.decode(mwUndotted, "UTF-8");
		} else {
			PGNFeminineSingular = URLDecoder
			    .decode(mwUndotted, "UTF-8");
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

    public String getPGNMasculinePlural(int tableNum) {
	String PGNMasculinePlural = "";
	try {
	    connection = DriverManager.getConnection(
		    "jdbc:mariadb://yeda.cs.technion.ac.il:3306/generatorTest",
		    "dummy1", "health&happiness");
	    PreparedStatement statement = null;
	    statement = connection
		    .prepareStatement("SELECT *  FROM mwe? where register='formal' and spelling = 'standard'  and lexiconId= ?  and PGN!='unspecified'  and (number='plural' or number='dual and plural') and gender='masculine'");
	    statement.setObject(1, tableNum);
	    statement.setObject(2, lexiconId);
	    rs = statement.executeQuery();
	    while (rs.next()) {
		String mwUndotted = rs.getString("mwUndotted");
		if (!PGNMasculinePlural.equals("")) {
			PGNMasculinePlural = PGNMasculinePlural + ", "
			    + URLDecoder.decode(mwUndotted, "UTF-8");
		} else {
			PGNMasculinePlural = URLDecoder.decode(mwUndotted, "UTF-8");
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

    public String getPGNFemininePlural(int tableNum) {
	String PGNFemininePlural = "";
	try {
	    connection = DriverManager.getConnection(
		    "jdbc:mariadb://yeda.cs.technion.ac.il:3306/generatorTest",
		    "dummy1", "health&happiness");
	    PreparedStatement statement = null;
	    statement = connection
		    .prepareStatement("SELECT *  FROM mwe? where register='formal' and spelling = 'standard'  and lexiconId= ?  and PGN!='unspecified'  and number='plural' and gender='feminine'");
	    statement.setObject(1, tableNum);
	    statement.setObject(2, lexiconId);
	    rs = statement.executeQuery();
	    while (rs.next()) {
		String mwUndotted = rs.getString("mwUndotted");
		if (!PGNFemininePlural.equals("")) {
			PGNFemininePlural = PGNFemininePlural + ", "
			    + URLDecoder.decode(mwUndotted, "UTF-8");
		} else {
			PGNFemininePlural = URLDecoder.decode(mwUndotted, "UTF-8");
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

}
