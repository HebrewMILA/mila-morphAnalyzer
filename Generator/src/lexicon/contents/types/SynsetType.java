package lexicon.contents.types;

import lexicon.contents.Content;

/**
 * @author Danny Shacham
 */
public class SynsetType extends Content implements lexicon.jaxb.SynsetType {
	protected lexicon.jaxb.SynsetType content;

	public SynsetType() {
		content = new lexicon.jaxb.impl.SynsetTypeImpl();
		TABLE = "synset";
		IDNAME = "synset_id";
	}

	public SynsetType(lexicon.jaxb.SynsetType content) {
		this.content = content;
		id = Integer.parseInt(content.getId());
		TABLE = "synset";
		IDNAME = "synset_id";
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
	public int add(int sneseID) {
		String sql = "INSERT INTO " + getTableName() + " VALUES (";
		sql += getId();
		sql += ", " + sneseID + ")";
		execute(sql);
		id = getCurrentID(getTableName(), getIDName());
		return id;
	}

	public int add() {
		return 0;
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
		sql += " synset_id='" + getId() + "' WHERE synset_id=" + Integer.parseInt(getId());
		int feedback = execute(sql);
		return feedback;
	}

	public void load() {
		setId(Integer.toString(getInt("synset_id")));
	}

	public lexicon.jaxb.impl.SynsetTypeImpl getImpl() {
		return (lexicon.jaxb.impl.SynsetTypeImpl) content;
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

}
