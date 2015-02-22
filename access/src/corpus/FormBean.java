package corpus;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Hashtable;

import lexicon.SecHandle;

public class FormBean
{
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
	private Hashtable<String, String> errors;
	boolean allOk = true;


	/**
	 * @return true if user details were ok data was insert
	 * ,apperantly this function not only validates but alos make the user insertion into the db (yossi 13.4.11)
	 * @throws InstantiationException
	 * @throws IllegalAccessException
	 * @throws ClassNotFoundException
	 */
	public boolean validate() throws InstantiationException, IllegalAccessException, ClassNotFoundException
	{
		ResultSet rs = null;
		Connection connection = null;
		PreparedStatement statement = null;
		boolean emailExist = false;
		try
		{
			System.out.println("connecting...");
			Class.forName("org.mariadb.jdbc.Driver").newInstance();
			connection = DriverManager.getConnection("jdbc:mariadb://yeda.cs.technion.ac.il:3306/corpus", "nur","vu+h#T7p82swUwr");

			statement = null;
			statement = connection.prepareStatement("SELECT *  FROM userp where mail = ? ");
			statement.setObject(1, email);
			System.out.println("the query " + statement.toString());
			rs = statement.executeQuery();


			if (rs.next())
			{	// if this email already exist in the db then give an error msg
				emailExist = true;
				System.out.println("the email already exist");
			}
			/*while (rs.next())
			{
				statement = connection.prepareStatement("delete FROM userp where mail = ? ");
				statement.setObject(1, email);
				rs = statement.executeQuery();
			}*/

			statement.close();
			connection.close();
		}
		catch (SQLException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// This means that the input data must contain at least an @ sign and a
		// dot (.).
		// Also, the @ must not be the first character of the email address, and
		// the last dot
		// must at least be one character after the @ sign:

		int apos = email.indexOf("@");
		int dotpos = email.lastIndexOf(".");
		if (apos < 1 || dotpos - apos < 2)
		{
			errors.put("email", "Please enter a valid email address");
			email = "";
			allOk = false;
			System.out.println("Please enter a valid email address");
		}
		if (firstName.equals(""))
		{
			errors.put("firstName", "Please enter your first name");
			firstName = "";
			allOk = false;
			System.out.println("Please enter your first name");
		}
		if (lastName.equals(""))
		{
			errors.put("lastName", "Please enter your last name");
			lastName = "";
			allOk = false;
			System.out.println("Please enter your last name");
		}
		if (emailExist)
		{
			errors.put("email", "Email entered is already in use");
			email = "";
			allOk = false;
			System.out.println("the email already exist");
		}
		else if (email.equals("") || email.indexOf('@') == -1)
		{
			errors.put("email", "Please enter a valid email address");
			email = "";
			allOk = false;
			System.out.println("Please enter a valid email address");
		}

		/*if (userName.equals(""))
		{
			errors.put("userName", "Please enter a username");
			userName = "";
			allOk = false;
			System.out.println("Please enter a username");
		}*/
		if (university.equals(""))
		{
			errors.put("university", "Please enter a valid university/firm");
			university = "";
			allOk = false;
			System.out.println("Please enter a valid university/firm");
		}
		if (country.equals(""))
		{
			errors.put("country", "Please enter a valid country");
			country = "";
			allOk = false;
			System.out.println("Please enter a valid country");
		}
		// if (phoneNum.equals("")) {
		// errors.put("phoneNum", "Please enter a valid phone number");
		// phoneNum = "";
		// allOk = false;
		// }

		if (purpose.equals(""))
		{
			errors.put("purpose", "Please enter a valid purpose");
			purpose = "";
			allOk = false;
			System.out.println("Please enter a valid purpose");
		}

		if (allOk)
		{
			statement = null;
			int commercial;
			if (notify1.equals("No")) {
				commercial = 1;
			} else {
				commercial = 0;
			}

			try
			{
				// statement = connection
				// .prepareStatement("INSERT INTO
				// userp(mail,first,last,university,phone,purpose,commercial)
				// VALUES("
				// +"'"+ email+ "','"+ firstName + "','"+ lastName + "','"+
				// university
				// + "','"+ phoneNum+ "','"+ purpose + "'," +1 +")");

				SecHandle sec = new SecHandle();
				String password = sec.generatePassword();  // create password

				connection = DriverManager.getConnection("jdbc:mariadb://yeda.cs.technion.ac.il:3306/website", "nur","vu+h#T7p82swUwr");

				statement = connection.prepareStatement("INSERT INTO userp(mail,first,last,university,phone,purpose,country,commercial,password) VALUES("
								+ "?,?,?,?,?,?,?,?,SHA1(?))");
				statement.setString(1, email);
				statement.setString(2, firstName);
				statement.setString(3, lastName);
				statement.setString(4, university);
				statement.setString(5, phoneNum);
				statement.setString(6, purpose);
				statement.setString(7, country);
				statement.setInt(8, commercial);
				statement.setString(9, password);
				System.out.println("the query " + statement.toString());
				statement.execute();

				statement.close();
				connection.close();

				/**
				 * the function sec.sendMail does not only send the mail with the password but it also put the user name and password in the .htpasswd.* files
				 * for use by the tomcat mechanism for password protecting directories
				 */
				sec.sendMail(password, firstName, lastName,email, userName, country, purpose, phoneNum, university, commercial);
			}
			catch (SQLException e)
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return allOk;
	}

	public String getErrorMsg(String s)
	{
		String errorMsg = errors.get(s.trim());
		return errorMsg == null ? "" : errorMsg;
	}

	public FormBean()
	{
		firstName = "";
		lastName = "";
		email = "";
		userName = "";
		university = "";
		phoneNum = "";
		purpose = "";
		notify1 = "";
		notify2 = "";
		country="";
		errors = new Hashtable<String, String>();
	}

	public String getFirstName()
	{
		return firstName;
	}

	public String getLastName()
	{
		return lastName;
	}

	public String getEmail()
	{
		// SecHandle s = new SecHandle();
		// String password = s.generatePassword();
		// s.sendMail(password,firstName);
		return email;
	}

	public String getUserName()
	{
		return userName;
	}

	public String getUniversity()
	{
		return university;
	}

	public String getCountry()
	{
		return country;
	}


	public String getPurpose()
	{
		return purpose;
	}

	public String getPhoneNum()
	{
		return phoneNum;
	}

	public String getNotify1()
	{
		return notify1;
	}

	public String getNotify2()
	{
		return notify2;
	}

	public String isRbSelected1(String s)
	{
		return notify1.equals(s) ? "checked" : "";
	}

	public String isRbSelected2(String s)
	{

		return notify2.equals(s) ? "checked" : "";
	}

	public void setFirstName(String fname)
	{
		firstName = fname;
	}

	public void setLastName(String lname)
	{
		lastName = lname;
	}

	public void setEmail(String eml)
	{
		email = eml;
	}

	public void setUserName(String u)
	{
		userName = u;
	}

	public void setUniversity(String uni)
	{
		university = uni;
	}

	public void setCountry(String c)
	{
		country = c;
	}

	public void setPurpose(String pur)
	{
		purpose = pur;
	}

	public void setPhoneNum(String pho)
	{
		phoneNum = pho;
	}

	public void setErrors(String key, String msg)
	{
		errors.put(key, msg);
	}

	public void setNotify1(String n)
	{
		notify1 = n;
	}

	public void setNotify2(String n)
	{
		notify2 = n;
	}

	public static void main(String[] args) throws SQLException
	{
		FormBean formBean = new FormBean();
		formBean.setCountry("il");
		formBean.setEmail("yossijacob@yahoo.com");
		formBean.setFirstName("joe");
		formBean.setLastName("doe");
		formBean.setPurpose("no");
		formBean.setUniversity("technion");
		//formBean.setUserName("ddd");
		try
		{
			formBean.validate();
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
}
