/*
 * Created on 23/02/2006
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package lexicon.contents.pos;

import java.util.List;
import lexicon.contents.Content;

/**
 * @author daliabo
 *
 *         TODO To change the template for this generated type comment go to
 *         Window - Preferences - Java - Code Style - Code Templates
 */
public class MultiWordPrepositionLexiconType extends Content implements lexicon.jaxb.MultiWordPrepositionLexiconType {
	lexicon.jaxb.MultiWordPrepositionLexiconType content;

	public MultiWordPrepositionLexiconType(lexicon.jaxb.MultiWordPrepositionLexiconType content) {
		this.content = content;
		TABLE = "multiWordPreposition";
		IDNAME = "id";
	}

	public MultiWordPrepositionLexiconType() {
		content = new lexicon.jaxb.impl.MultiWordPrepositionLexiconTypeImpl();
		TABLE = "multiWordPreposition";
		IDNAME = "id";
	}

	public lexicon.jaxb.impl.MultiWordPrepositionLexiconTypeImpl getImpl() {
		return (lexicon.jaxb.impl.MultiWordPrepositionLexiconTypeImpl) content;
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
		sql += ", '" + getMwPos() + "'";
		sql += ", '" + getType() + "'";
		sql += ", '" + getNumber() + "'";
		sql += ", " + (isConsecutive() ? 1 : 0);
		sql += ", " + (isBase1() ? 1 : 0);
		sql += ", " + (isBase2() ? 1 : 0);
		sql += ", " + (isConstruct1() ? 1 : 0);
		sql += ", " + (isPossessive2() ? 1 : 0);
		sql += ")";
		// System.out.println("this is the sql"+ sql);
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
		sql += " type='" + getType() + "'";
		sql += ", number='" + getNumber() + "'";
		sql += ", pos='" + getMwPos() + "'";
		sql += ", consecutive=" + (isConsecutive() ? 1 : 0);
		sql += ", base1=" + (isBase1() ? 1 : 0);
		sql += ", base2=" + (isBase2() ? 1 : 0);
		sql += ", construct1=" + (isConstruct1() ? 1 : 0);
		sql += ", possessive2=" + (isPossessive2() ? 1 : 0);
		sql += " WHERE id=" + getID();
		int feedback = execute(sql);
		return feedback;
	}

	public void load() {
		setConsecutive(getInt("consecutive") == 1);
		setBase1(getInt("base1") == 1);
		setBase2(getInt("base2") == 1);
		setConstruct1(getInt("construct1") == 1);
		setPossessive2(getInt("possessive2") == 1);
		setType(getString("type"));
		setNumber(getString("number"));
		setMwPos(getString("pos"));
		;
	}

	public static void main(String[] args) {
	}

	public boolean isConsecutive() {
		return content.isConsecutive();
	}

	public void setConsecutive(boolean value) {
		content.setConsecutive(value);
	}

	public boolean isBase1() {
		return content.isBase1();
	}

	public void setBase1(boolean value) {
		content.setBase1(value);
	}

	public boolean isBase2() {
		return content.isBase2();
	}

	public void setBase2(boolean value) {
		content.setBase2(value);
	}

	public boolean isConstruct1() {
		return content.isConstruct1();
	}

	public void setConstruct1(boolean value) {
		content.setConstruct1(value);
	}

	public boolean isPossessive2() {
		return content.isPossessive2();
	}

	public void setPossessive2(boolean value) {
		content.setPossessive2(value);
	}

	public java.lang.String getType() {
		return content.getType();
	}

	public void setType(java.lang.String value) {
		content.setType(value);
	}

	public java.lang.String getNumber() {
		return content.getNumber();
	}

	public void setNumber(java.lang.String value) {
		content.setNumber(value);
	}

	public java.lang.String getMwPos() {
		return content.getMwPos();
	}

	public void setMwPos(java.lang.String value) {
		content.setMwPos(value);
	}

}
