package lexicon.contents;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import snaq.db.ConnectionPool;

/**
 * The Connected class represent an object which has Database connectivity abilities,
 * through the JDBC standard and using a Connection Pool. Extending the class will result
 * in immidiate Database connectivity, based on a common (static) configuration which
 * is loaded during the creation of the first instance of the object. Further
 * instanciations would not result in reloading of the configuration file. In order
 * to take into account updates in the configuration file, the system needs to be
 * rebooted, in order to create (again) the first <code>Connected</code> instance.<p>
 * In order to learn more about the connection pool configuration, please seek
 * <b>bitmechanic</b> and find out.<p>
 *
 * @author		Danny Shacham
 * @version	1.0
 */
public class Connected {
	static {
		try {
			Class.forName("com.mysql.jdbc.Driver").newInstance();
		}catch (Exception e) {}
	}
     //private static String url = "jdbc:mysql://localhost:3306/lexicon4";
	
     /* update 24.10.10 by yossi 
      *  replaced the folowing 2 lines with the one after this comment 
      *  private static String url = "jdbc:mysql://yeda.cs.technion.ac.il:3306/lexiconP";
      *	 private static final ConnectionPool pool = new ConnectionPool("mysqlLexiocn",10, 20, 180000, url, "paul", "health08*");
      * */
	private static ConnectionPool pool = new ConnectionPool("mysqlLexiocn", 10, 20, 180000, "jdbc:mysql://yeda.cs.technion.ac.il:3306/lexiconP",
 			"tommy", "tammy2010!)");
    
	public static DataSource cpds;
	/**
	 * A Connection object used by the object and it's extentions in order to
	 * connect to the DB. The connection is created and released by the
	 * <code>ConnectionManager</code> object.
	 * @see  ConnectionManager
	 */
	protected Connection conn = null;
	
	/**
	 * A Statement object used to commit a DB command or to retrieve data from
	 * the DB. The object must be closed after the execution of the DB
	 * transaction, using <code>stmt.close()</code>. The object is created
	 * by the <code>Connection</code> object.
	 * @see     java.sql.Connection
	 * @see     #conn
	 */
	protected Statement stmt = null;
	
	/**
	 * A flag indicating if the properties were loaded or not.
	 */
	private boolean isLoaded = false;
	
	/**
	 * Commit a SELECT statement and returns a <code>ResultSet</code> containing
	 * the query output. Calls <code>connect()</code> in order to connect with the DB and commit
	 * the statement using <code>Statement.executeQuery</code>.
	 * There might be a performence problem becuase the method does not close
	 * the <code>ResultSet</code>, <code>Statement</code> and
	 * <code>Connection</code> objects.
	 *@param		sql		The SQL statement to be executed
	 *@return		The ResultSet recieved from the DB
	 */
	public ResultSet getData(String sql) 
	{
		ResultSet rs = null;
		try {
			prepareConnection();
			stmt = conn.createStatement();
			stmt.execute(sql);
			rs = stmt.getResultSet();
		}
		catch (SQLException E) {
			System.out.println("Lexicon Message: Content.getData Error");
			E.printStackTrace();
		}
		return rs;
	}
	
	/**
	 * Opens the <code>conn</code> Connection object. The method default opening
	 * method is using the <code>DriverManager.getConnection</code> method,
	 * using a connection pool to retrieve an open conenction from. If the
	 * variable <code>directConnection</code> is used, then the method would
	 * open a conenction directly, using the <code>prepareDirectConnection</code>
	 * method.
	 * @see     DriverManager#getConnection
	 * @see     #conn
	 * @see     #prepareDirectConnection
	 */
	protected void prepareConnection() throws SQLException {
		/*if (cpds == null) {
		 Context ctx = null;
		 try {
		 System.out.println("*****   Starting the DB connection!   ****");
		 ctx = new InitialContext();
		 if(ctx == null ) 
		 System.out.println("Boom - no cotext");
		 Context envCtx = (Context) ctx.lookup("java:comp/env");
		 DataSource ds = (DataSource) envCtx.lookup("jdbc/mysqlDBlexicon");
		 Connected.setCPDS(ds);
		 
		 } catch (NamingException ne) {
		 System.out.println("Exception in creating the DataSource!");
		 ne.printStackTrace();
		 } 
		 }
		 conn = getCPDS().getConnection();*/
		long timeout = 2000;  // 2 second timeout
		conn = pool.getConnection(timeout);
	}
	public static void setCPDS(DataSource cpds) {
		Connected.cpds = cpds;
	}
	public static synchronized DataSource getCPDS() {
		return cpds;
	}
	/**
	 *Serves as a super method for DB actions that require an "execute" mode, such as INSERT
	 *,UPDATE or DELETE.
	 *@param		sql     The SQL statement to be executed.
	 *@return	Number of rows affected (0, if nothing happened, 1 if
	 *one row added, ..., -1 if the statement is a SELECT statement).
	 */
	protected int execute(String sql) 
	{
		if (sql == null)
			return 0;
		int feedback = 0;
		try 
		{
			prepareConnection();
			stmt = conn.createStatement();
			feedback = stmt.executeUpdate(sql);
		}
		catch (SQLException E) 
		{
			System.out.println(sql);
			E.printStackTrace();
		}
		finally 
		{
			releaseConnection();
		}
		return feedback;
	}
	
	/**
	 * The method release a connection, used by the current Content object.
	 * The releasing is done by calling <code>conn.close()</code>. Please note
	 * that the use of some conenction pooling devices may cause the connection
	 * not to be actually closed, even if this action is performed.
	 * @see     Connection#close
	 * @see     #prepareConenction
	 */
	public void releaseConnection() {
		try {
			if (stmt != null) {
				stmt.close();
				stmt = null;
			}
		} catch (SQLException E) {
			E.printStackTrace();
		}
		try {
			if (conn != null &&!conn.isClosed()) {
				conn.close();
				conn = null;
			}
		} catch (SQLException E) {
			E.printStackTrace();
		}
	}
	
	public static void releasePool() {
		pool.release(); 
	}
	
}
