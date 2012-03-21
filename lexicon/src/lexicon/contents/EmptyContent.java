package lexicon.contents;

import java.util.HashMap;

/**
 * @author Danny Shacham
 */
public class EmptyContent extends Content {
	/**
	 * Sole constructor for the class. Init all the variables to default values:
	 * sets the id to 0, and creates a new <code>info</code> HashMap, ready to
	 * store the Content's record fields. If the static
	 * <code>basicConfiguration</code> attribute was not initialize yet, then
	 * the method loades it using <code>loadBasicConfiguration</code>.
	 * 
	 * @see #info
	 * @see #loadBasicConfiguration
	 */
	public EmptyContent() {
		this.id = 0;
		this.info = new HashMap<String, Object>(); // The dataset holding the DB record values
		this.TABLE = "no_table";
		this.IDNAME = "no_id";
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
		return 0;
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
	public int add(int id) {
		return 0;
	}

	/**
	 * Updates the current record in the DB, so it would resemble the current
	 * object state. The method updates all the fields of the Content.
	 * 
	 * @see #info
	 * @see ResultSet#updateRow
	 * @see #openRS
	 * @return The number of rows that were affected from the action. If 0, then
	 *         nothing happened
	 */
	@Override
	public int update() {
		return 0;
	}

	/**
	 * Updates the current record in the DB, so it would resemble the current
	 * object state. The method updates all the fields of the Content.
	 * 
	 * @see #info
	 * @see ResultSet#updateRow
	 * @see #openRS
	 * @return The number of rows that were affected from the action. If 0, then
	 *         nothing happened
	 */
	public int update(int id) {
		return 0;
	}

}
