package login;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Formatter;

import lexicon.SecHandle;
import sun.rmi.transport.Transport;

import com.sun.xml.internal.messaging.saaj.packaging.mime.MessagingException;

/***
 *
 * @author syjacob this class is for authenticating users who want to download
 *         content from our website. when initalized it gets an email and
 *         password, it then check to see that the email and password are
 *         correct is so it returns true
 *
 */

public class LoginBean {
	private String email = "";
	private String password = "";
	private String referer = "";
	private String forgot_pass = "";

	public LoginBean() {
		// empty constructor
	}

	// ---------------------------------------------------------------------------------------------------------
	/**
	 * used to check email and password against the database
	 *
	 * @return true if authentication successfull
	 * @throws AddressException
	 */
	public int Authenticate() throws AddressException, MessagingException {
		int result = -1; // false
		ResultSet rs = null;
		Connection connection = null;
		PreparedStatement statement = null;

		try { // connecting to database
			System.out.println("connecting...");
			Class.forName("org.mariadb.jdbc.Driver").newInstance();
			connection = DriverManager.getConnection(
					"jdbc:mariadb://yeda.cs.technion.ac.il:3306/website", "nur",
					"vu+h#T7p82swUwr");

			statement = null; // creating the query
			statement = connection
					.prepareStatement("SELECT * FROM userp where mail = ? ");
			statement.setObject(1, email);
			System.out.println("the query " + statement.toString());
			rs = statement.executeQuery();
			String pass = null;
			if (rs.next()) {
				pass = rs.getString("password");
				// if requested to resend the password
				if (forgot_pass != null && forgot_pass.equals("checked")) {
					SecHandle sec = new SecHandle();
					try {
						pass = UpdatePassword(email);
					} catch (SQLException e) {
						e.printStackTrace();
						/* failure -- showing same message is a security feature */
						return 0;
					}
					MimeMessage message = new MimeMessage(sec.session);
					message.setRecipient(Message.RecipientType.TO,
							new InternetAddress(email));
					message.setFrom(new InternetAddress(
							"mila@cs.technion.ac.il"));
					message.setRecipients(Message.RecipientType.CC,
							"mila@cs.technion.ac.il");
					message.setSubject("Access To Mila Knowledge Center - your password");
					message.setText("Your Password for the mila knowledge has been reset to: "
							+ pass);
					Transport.send(message);
					return 0; // resent password
				} else if (pass.equals(SHAsum(password.getBytes())))
				 {
					return 1; // true
				}
			} else {
				return 0;
			}

			rs.close();
			statement.close();

			/*
			 * OK lets try using the DB SHA-1 implementation, because I don't
			 * trust my own...
			 */
			statement = connection
					.prepareStatement("SELECT *  FROM userp where mail = ? AND password = SHA1(?)");
			statement.setObject(1, email);
			statement.setObject(2, password);
			System.out.println("the query " + statement.toString());
			rs = statement.executeQuery();
			if (rs.next()) {
				return 1;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch blockorpus
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchAlgorithmException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} finally {
			try {
				rs.close();
				statement.close();
				connection.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (NullPointerException e) {
				/* fuck it */
			}

		}

		return result;
	}

	private String UpdatePassword(String _email) throws SQLException {

		Connection connection = null;
		PreparedStatement statement = null;

		SecHandle sec = new SecHandle();
		String password = sec.generatePassword(); // create password

		connection = DriverManager.getConnection(
				"jdbc:mariadb://yeda.cs.technion.ac.il:3306/website", "nur",
				"vu+h#T7p82swUwr");

		statement = connection
				.prepareStatement("UPDATE userp SET password=SHA1(?) WHERE mail=?");
		statement.setString(1, password);
		statement.setString(2, _email);
		System.out.println("the query " + statement.toString());
		statement.execute();

		statement.close();
		connection.close();
		return password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setReferer(String referer) {
		this.referer = referer;
	}

	public String getReferer() {
		return referer;
	}

	public void setForgot_pass(String forgot_pass) {
		this.forgot_pass = forgot_pass;
	}

	public String getForgot_pass() {
		return forgot_pass;
	}

	public static String SHAsum(byte[] convertme)
			throws NoSuchAlgorithmException {
		MessageDigest md = MessageDigest.getInstance("SHA-1");
		return byteArray2Hex(md.digest(convertme));
	}

	private static String byteArray2Hex(final byte[] hash) {
		Formatter formatter = new Formatter();
		for (byte b : hash) {
			formatter.format("%02x", b);
		}
		String ret = formatter.toString();
		formatter.close();
		return ret;
	}

}
