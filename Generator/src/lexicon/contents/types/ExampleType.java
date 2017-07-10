package lexicon.contents.types;

import java.net.URLEncoder;

import lexicon.contents.Content;

/**
 * @author Danny Shacham
 */
public class ExampleType extends Content implements lexicon.jaxb.ExampleType {
	protected lexicon.jaxb.ExampleType content;

	public ExampleType() {
		content = new lexicon.jaxb.impl.ExampleTypeImpl();
		TABLE = "example";
		IDNAME = "eid";
	}

	public ExampleType(lexicon.jaxb.ExampleType content) {
		this.content = content;
		id = Integer.parseInt(content.getId());
		TABLE = "example";
		IDNAME = "eid";
	}

	public int add() {
		return 0;
	}

	/**
	 * An empty implementation to the method add() methods in the different
	 * subclasses of Content. Connects to the DB, commits the different SQL
	 * statements and return feedback.
	 * 
	 * @param sql
	 *           - The SQL statement to be executed.
	 * @return Number of rows affected (0, if nothing happened, 1 if one row added).
	 */
	public int add(int sense_id) {
		String sql = "INSERT INTO " + getTableName() + " VALUES (";
		sql += getId();
		sql += ", " + sense_id;
		String phrase = getPhrase();
		try {
			phrase = URLEncoder.encode(phrase, Content.ADD_ENCODING);
		} catch (Exception e) {
		}
		sql += ", '" + phrase + "')";
		int feedback = execute(sql);
		id = getCurrentID(getTableName(), getIDName());
		return id;
	}

	/**
	 * Updates the current record in the DB, so it would resemble the current object
	 * state. The method uses ResultSet.updateRow method in order to implement the
	 * generic update process. The method finds the record of the current object,
	 * generates the meta data (the names and types of the columns) , Runs on the
	 * columns and updateing each one, according with the column type. After these
	 * stages, the method calls <code>ResultSet.updateRow</code> in order to execute
	 * the update in the DB.
	 * 
	 * @see #info
	 * @see ResultSet#updateRow
	 * @see #openRS
	 * @return The number of rows that were affected from the action. If 0, then
	 *         nothing happened
	 */
	public int update() {
		String sql = "UPDATE " + getTableName() + " SET";
		String phrase = getPhrase();
		try {
			phrase = URLEncoder.encode(phrase, Content.UPDATE_ENCODING);
		} catch (Exception e) {
		}
		sql += " phrase='" + phrase + "' WHERE eid=" + Integer.parseInt(getId());
		int feedback = execute(sql);
		return feedback;
	}

	public void load() {
		setId(Integer.toString(getInt("eid")));
		setPhrase(getString("phrase"));
	}

	public lexicon.jaxb.impl.ExampleTypeImpl getImpl() {
		return (lexicon.jaxb.impl.ExampleTypeImpl) content;
	}

	public void setId(String value) {
		content.setId(value);
	}

	public String getId() {
		if (content.getId() == null) {
			return "0";
		}
		return content.getId();
	}

	public void setPhrase(String value) {
		content.setPhrase(value);
	}

	public String getPhrase() {
		if (content.getPhrase() == null) {
			return "";
		}
		return content.getPhrase();
	}

}
