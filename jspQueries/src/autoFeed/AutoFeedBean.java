package autoFeed;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import sql.SqlDB;

public class AutoFeedBean {
	private Connection dbCon;

	public Connection connect() {
		return dbCon = SqlDB.lexiconP.getConnection(SqlDB.User.paul);
	}

	public void close() throws SQLException {
		dbCon.close();
	}

	public ResultSet execSQL(String sql) throws SQLException {
		return dbCon.createStatement().executeQuery(sql);
	}

	public int updateSQL(String sql) throws SQLException {
		return dbCon.createStatement().executeUpdate(sql);
	}

}
