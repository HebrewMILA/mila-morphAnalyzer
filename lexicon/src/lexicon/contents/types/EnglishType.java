package lexicon.contents.types;

import java.net.URLEncoder;

import lexicon.contents.Content;

/**
 * @author Danny Shacham
 */
public class EnglishType extends Content implements lexicon.jaxb.EnglishType {
	protected lexicon.jaxb.EnglishType content;

	public EnglishType() {
		content = new lexicon.jaxb.impl.EnglishTypeImpl();
		TABLE = "english";
		IDNAME = "eid";
	}

	public EnglishType(lexicon.jaxb.EnglishType content) {
		this.content = content;
		id = Integer.parseInt(content.getId());
		TABLE = "english";
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
	 *            - The SQL statement to be executed.
	 * @return Number of rows affected (0, if nothing happened, 1 if one row
	 *         added).
	 */
	public int add(int sense_id) {
		String sql = "INSERT INTO " + getTableName() + " VALUES (";
		sql += getId();
		sql += ", " + sense_id;
		String te = getTe();
		try {
			te = URLEncoder.encode(te, Content.ADD_ENCODING);
		} catch (Exception e) {
		}
		sql += ", '" + te + "'";
		sql += ", '" + getWeight() + "')";
		int feedback = execute(sql);
		id = getCurrentID(getTableName(), getIDName());
		return id;
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
	public int update() {
		String sql = "UPDATE " + getTableName() + " SET";
		String te = getTe();
		try {
			te = URLEncoder.encode(te, Content.UPDATE_ENCODING);
		} catch (Exception e) {
		}
		sql += " te='" + te + "'";
		sql += ", weight=" + getWeight() + " WHERE eid="
				+ Integer.parseInt(getId());
		int feedback = execute(sql);
		return feedback;
	}

	public void load() {
		setId(Integer.toString(getInt("eid")));
		setTe(getString("te"));
		setWeight(getString("weight"));
	}

	public lexicon.jaxb.impl.EnglishTypeImpl getImpl() {
		return (lexicon.jaxb.impl.EnglishTypeImpl) content;
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

	public void setTe(String value) {
		content.setTe(value);
	}

	public String getTe() {
		if (content.getTe() == null) {
			return "";
		}
		return content.getTe();
	}

	public void setWeight(String value) {
		content.setWeight(value);
	}

	public String getWeight() {
		if (content.getWeight() == null) {
			return "1";
		}
		if (content.getWeight().equals("")) {
			return "0";
		}
		return content.getWeight();
	}

}
