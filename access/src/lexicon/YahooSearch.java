package lexicon;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

public class YahooSearch {

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
	    connection = DriverManager.getConnection(
		    "jdbc:mariadb://yeda.cs.technion.ac.il:3306/generator",
		    "dummy1", "health&happiness");
	} catch (Exception e) {
	    e.printStackTrace();
	    System.exit(0);
	}
    }

    public YahooSearch(String lexiconId) {
	this.lexiconId = lexiconId;

    }

    public void getSurfaceList(Vector<String> decodedSurfaceVec, Vector<String> surfaceVec)
	    throws SQLException {
	Integer.parseInt(lexiconId);
	PreparedStatement statement = null;
	statement = connection
		.prepareStatement("SELECT distinct(surface),baseLexiconPointer  "
			+ "FROM inflections where (baseDefinitness not like '%t') and baseLexiconPointer= ? ");
	statement.setObject(1, lexiconId);
	rs = statement.executeQuery();
	while (rs.next()) {
	    String surface = rs.getString("surface");
	    String decodedSurface = "";
	    try {
		decodedSurface = URLDecoder.decode(surface, "UTF-8");
	    } catch (UnsupportedEncodingException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	    }
	    decodedSurfaceVec.add(decodedSurface);
	    surfaceVec.add(surface);

	}
    }

    public static void releaseConnection() {

	try {
	    rs.close();
	} catch (SQLException e) {
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	}
    }

    public static void main(String[] args) throws SQLException {
	YahooSearch s = new YahooSearch("84*07");
	Vector<String> decodedSurfaceVec = new Vector<String>();
	Vector<String> surfaceVec = new Vector<String>();
	s.getSurfaceList(decodedSurfaceVec, surfaceVec);

    }

}
