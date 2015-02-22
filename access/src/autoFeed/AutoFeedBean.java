package autoFeed;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class AutoFeedBean {

	String dbURL = "jdbc:mariadb://yeda.cs.technion.ac.il:3306/lexiconP";
	String dbDriver = "org.mariadb.jdbc.Driver";
	String user="paul";
	String pass="health08*";
	private Connection dbCon;

	public Connection connect() throws ClassNotFoundException, SQLException {
		Class.forName(dbDriver);
		dbCon = DriverManager.getConnection(dbURL,user,pass);
		return dbCon;
	}

	public void close() throws SQLException {
		dbCon.close();
	}

	public ResultSet execSQL(String sql) throws SQLException {

		Statement s = dbCon.createStatement();
		ResultSet r = s.executeQuery(sql);
		return r == null ? null : r;
	}

	public int updateSQL(String sql) throws SQLException {
		Statement s = dbCon.createStatement();
		int r = s.executeUpdate(sql);
		return r == 0 ? 0 : r;
	}

}
