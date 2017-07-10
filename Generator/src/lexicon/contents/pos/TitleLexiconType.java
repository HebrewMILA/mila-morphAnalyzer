/*
 * Created on 22/02/2006
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package lexicon.contents.pos;

import java.net.URLEncoder;

import lexicon.contents.Content;

/**
 * @author daliabo
 *
 *         TODO To change the template for this generated type comment go to
 *         Window - Preferences - Java - Code Style - Code Templates
 */
public class TitleLexiconType extends Content implements lexicon.jaxb.TitleLexiconType {

	lexicon.jaxb.TitleLexiconType content;

	public TitleLexiconType(lexicon.jaxb.TitleLexiconType content) {
		this.content = content;
		TABLE = "title";
		IDNAME = "id";
	}

	public TitleLexiconType() {
		content = new lexicon.jaxb.impl.TitleLexiconTypeImpl();
		TABLE = "title";
		IDNAME = "id";
	}

	public lexicon.jaxb.impl.TitleLexiconTypeImpl getImpl() {
		return (lexicon.jaxb.impl.TitleLexiconTypeImpl) content;
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
	public int add(int id) {
		String sql = "INSERT INTO " + getTableName() + " VALUES (";
		sql += id;
		sql += ", '" + getGender() + "'";
		sql += ", '" + getNumber() + "'";
		sql += ", definiteness=" + (isDefiniteness() ? 1 : 0) + ")";
		int feedback = execute(sql);
		id = getCurrentID(getTableName(), getIDName());
		return feedback;
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
		sql += " gender='" + getGender() + "'";
		sql += ", number='" + getNumber() + "'";
		sql += ", definiteness=" + (isDefiniteness() ? 1 : 0);
		sql += " WHERE id=" + getID();
		int feedback = execute(sql);
		return 1;
	}

	public void load() {
		setGender(getString("gender"));
		setNumber(getString("number"));
		setDefiniteness(getInt("definiteness") == 1);
	}

	/**
	 * Gets the value of the number property.
	 * 
	 * @return possible object is {@link java.lang.String}
	 */
	public java.lang.String getNumber() {
		return content.getNumber();
	}

	/**
	 * Sets the value of the number property.
	 * 
	 * @param value
	 *           allowed object is {@link java.lang.String}
	 */
	public void setNumber(java.lang.String value) {
		content.setNumber(value);
	}

	public java.lang.String getGender() {
		return content.getGender();
	}

	public void setGender(java.lang.String value) {
		content.setGender(value);
	}

	public boolean isDefiniteness() {
		return content.isDefiniteness();
	}

	public void setDefiniteness(boolean value) {
		content.setDefiniteness(value);
	}

}
