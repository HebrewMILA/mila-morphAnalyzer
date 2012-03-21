package lexicon.contents;

import java.net.URLEncoder;
import java.util.List;

/**
 * @author Danny Shacham
 */
public class User extends Content {

	public User() {
		this.TABLE = "users";
		this.IDNAME = "uid";
	}

	/**
	 * An empty implementation to the method add() methods in the different
	 * subclasses of Content. Connects to the DB, commits the different SQL
	 * statements and return feedback.
	 * 
	 * @param sql
	 *            - The SQL statement to be executed.
	 * @return Number of rows affected (0, if nothing happened, 1 if one row
	 *         added).
	 */
	@Override
	public int add() {
		String sql = "INSERT INTO " + this.getTableName() + " VALUES (";
		sql += "0";
		String firstName = this.getString("first_name");
		try {
			firstName = URLEncoder.encode(firstName, Content.ADD_ENCODING);
		} catch (final Exception e) {
		}
		sql += ", '" + firstName + "'";
		String lastName = this.getString("last_name");
		try {
			lastName = URLEncoder.encode(lastName, Content.ADD_ENCODING);
		} catch (final Exception e) {
		}
		sql += ", '" + lastName + "'";
		String username = this.getString("username");
		try {
			username = URLEncoder.encode(username, Content.ADD_ENCODING);
		} catch (final Exception e) {
		}
		sql += ", '" + username + "'";
		String password = this.getString("password");
		try {
			password = URLEncoder.encode(password, Content.ADD_ENCODING);
		} catch (final Exception e) {
		}
		sql += ", '" + password + "'";
		final String email = this.getString("email");
		sql += ", '" + email + "'";
		final int isEditor = this.getInt("is_editor");
		sql += ", " + isEditor;
		final int isAdmin = this.getInt("is_admin");
		sql += ", " + isAdmin + ")";
		this.execute(sql);
		this.id = this.getCurrentID(this.getTableName(), this.getIDName());
		return this.id;
	}

	@Override
	public int add(int i) {
		return 0;
	}

	public String getName() {
		String result = "";
		final String firstName = this.getString("first_name");
		final String lastName = this.getString("last_name");
		if (firstName != null) {
			result = firstName + " " + lastName;
		}
		return result;
	}

	/**
	 * Updates the current record in the DB, so it would resemble the current
	 * object state. The method uses ResultSet.updateRow method in order to
	 * implement the generic update process. The method finds the record of the
	 * current object, generates the meta data (the names and types of the
	 * columns) , Runs on the columns and updateing each one, according with the
	 * column type. After these stages, the method calls
	 * <code>ResultSet.updateRow</code> in order to execute the update in the
	 * DB.
	 * 
	 * @see #info
	 * @see ResultSet#updateRow
	 * @see #openRS
	 * @return The number of rows that were affected from the action. If 0, then
	 *         nothing happened
	 */
	@Override
	public int update() {
		String sql = "UPDATE " + this.getTableName() + " SET";
		String firstName = this.getString("first_name");
		try {
			firstName = URLEncoder.encode(firstName, Content.ADD_ENCODING);
		} catch (final Exception e) {
		}
		sql += " first_name='" + firstName + "'";
		String lastName = this.getString("last_name");
		try {
			lastName = URLEncoder.encode(lastName, Content.ADD_ENCODING);
		} catch (final Exception e) {
		}
		sql += ", last_name='" + lastName + "'";
		String username = this.getString("username");
		try {
			username = URLEncoder.encode(username, Content.ADD_ENCODING);
		} catch (final Exception e) {
		}
		sql += ", username='" + username + "'";
		String password = this.getString("password");
		try {
			password = URLEncoder.encode(password, Content.ADD_ENCODING);
		} catch (final Exception e) {
		}
		sql += ", password='" + password + "'";
		final String email = this.getString("email");
		sql += ", email='" + email + "'";
		final int isEditor = this.getInt("is_editor");
		sql += ", is_editor=" + isEditor;
		final int isAdmin = this.getInt("is_admin");
		sql += ", is_admin=" + isAdmin;
		sql += " WHERE uid=" + this.id;
		final int feedback = this.execute(sql);
		return feedback;
	}

	/**
	 * A login method that validates that a user exsist in the system and has a
	 * matching password. If the user has the password, the method does not open
	 * the user's record.
	 * 
	 * @param tryUsername
	 *            - The username of the user.
	 * @param tryPassword
	 *            - The password of the user.
	 * @return The id of the user, or 0 if the validation wasn't succesfull.
	 * @see validateAndOpen
	 * @version 1.2 validation has 2 options, only to validate or to validate
	 *          and open the User
	 * @auther spektory
	 */
	public int validate(String tryUsername, String tryPassword) {
		String sql = "SELECT * FROM users WHERE username='" + tryUsername;
		sql += "' AND password='" + tryPassword + "'";
		final List<Integer> tempUser = this.getContents(sql, "uid");
		int userID = 0;
		if ((tempUser != null) && (tempUser.size() > 0)) {
			userID = ((Integer) tempUser.get(0)).intValue();
		}
		return userID;
	}

	/**
	 * A login method that validates that a user exsist in the system and has a
	 * matching password. If the user has the password, the method opens the
	 * user's record.
	 * 
	 * @param tryUsername
	 *            - The username of the user.
	 * @param tryPassword
	 *            - The password of the user.
	 * @return The id of the user, or 0 if the validation wasn't succesfull.
	 */
	public int validateAndOpen(String tryUsername, String tryPassword) {
		int userID = 0;
		userID = this.validate(tryUsername, tryPassword);
		if (userID > 0) {
			this.open(userID);
		}
		return userID;// id;
	}
}
