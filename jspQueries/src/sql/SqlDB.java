package sql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import snaq.db.ConnectionPool;

public enum SqlDB {
	lexiconTest("lexiconTest"),
	lexiconP("lexiconP"),
	corpus("corpus"),
	generatorTest("generatorTest"),
	;

	private final String name;

	SqlDB(String name) {
		this.name = name;
	}
	
	final static String dbDriver = "org.mariadb.jdbc.Driver";

	private static String dbURL(String s) {
		return "jdbc:mariadb://yeda.cs.technion.ac.il:3306/" + s;
	}

	static {
		try {
			Class.forName(dbDriver).newInstance();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	private static ConnectionPool generatorPool = new ConnectionPool("generatorTestPool", 1000,
			10000, 900, dbURL("" + generatorTest), User.dummy1.username, User.dummy1.password);

	public static Connection getGeneratorPoolConnection() {
		try {
			return generatorPool.getConnection();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public Connection getConnection(User user) {
		try {
			return DriverManager.getConnection(dbURL(name),
					user.username, user.password);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	public enum User {
		nur("nur", "vu+h#T7p82swUwr"),
		paul("paul", "health08*"),
		tmir("tmir", "bRustuj52az5kuf"),
		dandy("dandy", "yachuF6baqetREJa"),
		maya("maya", "@b^F,Mq2y[j"),
		maital("maital", "AnaXAd3Ke@aJ8F"),
		dummy1("dummy1", "health&happiness"),
		;
		
		final String password;
		final String username;

		User(String username, String password) {
			this.password = password;
			this.username = username;
		}
	}

}
