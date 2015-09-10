package corpus;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

import sql.SqlDB;
import sql.SqlDB.User;
import lexicon.SecHandle;

public class FormBean {
	private String firstName;
	private String lastName;
	private String email;
	private String userName;
	private String phoneNum;
	private String university;
	private String purpose;
	private String notify1;
	private String notify2;
	private String country;
	private final Hashtable<String, String> errors;
	boolean allOk = true;

	public boolean validate() {
		if (isUserExists(email)) {
			errors.put("email",
			"The user already exists in System - Please apply Mila by mail");
			email = "";
			return false;
		}
			
		// This means that the input data must contain at least an @ sign and a dot (.).
		// Also, the @ must not be the first character of the email address, and the last dot
		// must at least be one character after the @ sign:

		int apos = email.indexOf("@");
		int dotpos = email.lastIndexOf(".");
		if (apos < 1 || dotpos - apos < 2) {
			errors.put("email", "Please enter a valid email address");
			email = "";
			allOk = false;
		}

		if (firstName.equals("")) {
			errors.put("firstName", "Please enter your first name");
			firstName = "";
			allOk = false;
		}
		if (lastName.equals("")) {
			errors.put("lastName", "Please enter your last name");
			lastName = "";
			allOk = false;
		}
		if (email.equals("") || (email.indexOf('@') == -1)) {
			errors.put("email", "Please enter a valid email address");
			email = "";
			allOk = false;
		}
		if (userName.equals("")) {
			errors.put("userName", "Please enter a username");
			userName = "";
			allOk = false;
		}
		if (university.equals("")) {
			errors.put("university", "Please enter a valid university/firm");
			university = "";
			allOk = false;
		}
		if (country.equals("")) {
			errors.put("country", "Please enter a valid country");
			country = "";
			allOk = false;
		}
		// if (phoneNum.equals("")) {
		// errors.put("phoneNum", "Please enter a valid phone number");
		// phoneNum = "";
		// allOk = false;
		// }

		if (purpose.equals("")) {
			errors.put("purpose", "Please enter a valid purpose");
			purpose = "";
			allOk = false;
		}
		if (!allOk)
			return false;
		
		int commercial = notify1.equals("No") ? 1 : 0;

		if (insert(commercial)) {
			SecHandle sec = new SecHandle();
			sec.sendMail(sec.generatePassword(), firstName, lastName, email, userName,country, purpose, phoneNum, university, commercial);
			return true;
		}
		return false;
	}

	private boolean insert(int commercial) {
		try (Connection connection = SqlDB.corpus.getConnection(User.nur);
				PreparedStatement statement = connection
						.prepareStatement("INSERT INTO userp(mail,first,last,university,phone,purpose,country,commercial) VALUES("
								+ "?,?,?,?,?,?,?,?)")) {
			statement.setString(1, email);
			statement.setString(2, firstName);
			statement.setString(3, lastName);
			statement.setString(4, university);
			statement.setString(5, phoneNum);
			statement.setString(6, purpose);
			statement.setString(7, country);
			statement.setInt(8, commercial);
			statement.execute();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	private static boolean isUserExists(String email) {
		try (Connection connection = SqlDB.corpus.getConnection(SqlDB.User.nur);
				PreparedStatement statement = connection
						.prepareStatement("SELECT *  FROM userp where mail = ? ")) {
			statement.setObject(1, email);
			try (ResultSet rs = statement.executeQuery()) {
				return rs.next();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return true;
	}

	public String getErrorMsg(String s) {
		String errorMsg = errors.get(s.trim());
		return (errorMsg == null) ? "" : errorMsg;
	}

	public FormBean() {
		firstName = "";
		lastName = "";
		email = "";
		userName = "";
		university = "";
		phoneNum = "";
		purpose = "";
		notify1 = "";
		notify2 = "";
		country = "";
		errors = new Hashtable<>();
	}

	public String getFirstName() {
		return firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public String getEmail() {
		return email;
	}

	public String getUserName() {
		return userName;
	}

	public String getUniversity() {
		return university;
	}

	public String getCountry() {
		return country;
	}

	public String getPurpose() {
		return purpose;
	}

	public String getPhoneNum() {
		return phoneNum;
	}

	public String getNotify1() {
		return notify1;
	}

	public String getNotify2() {
		return notify2;
	}

	public String isRbSelected1(String s) {
		return (notify1.equals(s)) ? "checked" : "";
	}

	public String isRbSelected2(String s) {

		return (notify2.equals(s)) ? "checked" : "";
	}

	public void setFirstName(String fname) {
		firstName = fname;
	}

	public void setLastName(String lname) {
		lastName = lname;
	}

	public void setEmail(String eml) {
		email = eml;
	}

	public void setUserName(String u) {
		userName = u;
	}

	public void setUniversity(String uni) {
		university = uni;
	}

	public void setCountry(String c) {
		country = c;
	}

	public void setPurpose(String pur) {
		purpose = pur;
	}

	public void setPhoneNum(String pho) {
		phoneNum = pho;
	}

	public void setErrors(String key, String msg) {
		errors.put(key, msg);
	}

	public void setNotify1(String n) {
		notify1 = n;
	}

	public void setNotify2(String n) {
		notify2 = n;
	}
}
