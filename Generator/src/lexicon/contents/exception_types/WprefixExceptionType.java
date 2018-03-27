/*
 * Created on 24/02/2006
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package lexicon.contents.exception_types;

import java.net.URLEncoder;
import lexicon.contents.Content;
import lexicon.tools.LexiconUtils;

/**
 * @author daliabo
 *
 *         TODO To change the template for this generated type comment go to
 *         Window - Preferences - Java - Code Style - Code Templates
 */
public class WprefixExceptionType extends Content implements lexicon.jaxb.WprefixExceptionType {
	protected lexicon.jaxb.WprefixExceptionType content;

	public WprefixExceptionType(lexicon.jaxb.WprefixExceptionType content) {
		this.content = content;
		TABLE = "wprefix_exception_type";
		IDNAME = "aid";
	}

	public WprefixExceptionType() {
		content = new lexicon.jaxb.impl.WprefixExceptionTypeImpl();
		TABLE = "wprefix_exception_type";
		IDNAME = "aid";
	}

	public lexicon.jaxb.impl.WprefixExceptionTypeImpl getImpl() {
		return (lexicon.jaxb.impl.WprefixExceptionTypeImpl) content;
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
		sql += "0, ";
		sql += id;
		String undotted = getUndotted();
		try {
			undotted = URLEncoder.encode(undotted, Content.ADD_ENCODING);
		} catch (Exception e) {
		}
		sql += ", '" + undotted + "'";
		String transliterated = LexiconUtils.getTransliteration(undotted);
		try {
			transliterated = URLEncoder.encode(transliterated, Content.ADD_ENCODING);
		} catch (Exception e) {
		}
		sql += ", '" + transliterated + "'";
		String dotted = getDotted();
		try {
			dotted = URLEncoder.encode(dotted, Content.ADD_ENCODING);

		} catch (Exception e) {
		}
		sql += ", '" + dotted + "'";
		sql += ", '" + getRegister() + "'";
		sql += ", '" + getSpelling() + "'";
		sql += ", '" + getGender() + "'";
		sql += ", '" + getNumber() + "'";
		sql += ", '" + getAction() + "')";
		execute(sql);
		id = getCurrentID(getTableName(), getIDName());
		return id;
	}

	public void setAction(String action) {
		set("action", action);
	}

	public String getAction() {
		if (getString("action") == null) {
			return "";
		}
		return getString("action");
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
		String sql = "UPDATE " + getTableName() + " SET ";
		String undotted = getUndotted();
		try {
			undotted = URLEncoder.encode(undotted, Content.UPDATE_ENCODING);
		} catch (Exception e) {
		}
		sql += " undotted='" + undotted + "'";
		String transliterated = LexiconUtils.getTransliteration(undotted);
		try {
			transliterated = URLEncoder.encode(transliterated, Content.UPDATE_ENCODING);
		} catch (Exception e) {
		}
		sql += ", transliterated='" + transliterated + "'";
		String dotted = getDotted();
		try {
			dotted = URLEncoder.encode(dotted, Content.UPDATE_ENCODING);
		} catch (Exception e) {
		}
		sql += ", dotted='" + dotted + "'";
		sql += ", register='" + getRegister() + "'";
		sql += ", spelling='" + getSpelling() + "'";
		sql += ", gender='" + getGender() + "'";
		sql += ", number='" + getNumber() + "'";
		sql += ", action='" + getAction() + "' WHERE aid=" + getID();
		int feedback = execute(sql);
		return feedback;
	}

	public void load() {
		setValue("");
		setDotted(getString("dotted"));
		setUndotted(getString("undotted"));
		setValue(getString("value"));
		setRegister(getString("register"));
		setSpelling(getString("spelling"));
		setGender(getString("gender"));
		setNumber(getString("number"));
		setTransliterated(getString("transliterated"));

	}

	/**
	 * Gets the value of the value property.
	 * 
	 * @return possible object is {@link java.lang.String}
	 */
	public java.lang.String getValue() {
		return content.getValue();
	}

	/**
	 * Sets the value of the value property.
	 * 
	 * @param value
	 *           allowed object is {@link java.lang.String}
	 */
	public void setValue(java.lang.String value) {
		content.setValue(value);
	}

	/**
	 * Gets the value of the undotted property.
	 * 
	 * @return possible object is {@link java.lang.String}
	 */
	public java.lang.String getUndotted() {
		if (content.getUndotted() == null) {
			return "";
		}
		return content.getUndotted();
	}

	/**
	 * Sets the value of the undotted property.
	 * 
	 * @param value
	 *           allowed object is {@link java.lang.String}
	 */
	public void setUndotted(java.lang.String value) {
		content.setUndotted(value);
	}

	/**
	 * Gets the value of the dotted property.
	 * 
	 * @return possible object is {@link java.lang.String}
	 */
	public java.lang.String getDotted() {
		if (content.getDotted() == null) {
			return "";
		}
		return content.getDotted();
	}

	/**
	 * Sets the value of the dotted property.
	 * 
	 * @param value
	 *           allowed object is {@link java.lang.String}
	 */
	public void setDotted(java.lang.String value) {
		content.setDotted(value);
	}

	/**
	 * Gets the value of the script property.
	 * 
	 * @return possible object is {@link java.lang.String}
	 */
	public java.lang.String getRegister() {
		return content.getRegister();
	}

	/**
	 * Sets the value of the script property.
	 * 
	 * @param value
	 *           allowed object is {@link java.lang.String}
	 */
	public void setRegister(java.lang.String value) {
		content.setRegister(value);
	}

	/**
	 * Gets the value of the script property.
	 * 
	 * @return possible object is {@link java.lang.String}
	 */
	public java.lang.String getSpelling() {
		return content.getSpelling();
	}

	/**
	 * Sets the value of the script property.
	 * 
	 * @param value
	 *           allowed object is {@link java.lang.String}
	 */
	public void setSpelling(java.lang.String value) {
		content.setSpelling(value);
	}

	/**
	 * Gets the value of the transliterated property.
	 * 
	 * @return possible object is {@link java.lang.String}
	 */
	public java.lang.String getTransliterated() {
		if (content.getTransliterated() == null) {
			return "";
		}
		return content.getTransliterated();
	}

	/**
	 * Sets the value of the transliterated property.
	 * 
	 * @param value
	 *           allowed object is {@link java.lang.String}
	 */
	public void setTransliterated(java.lang.String value) {
		content.setTransliterated(value);
	}

	public java.lang.String getGender() {
		return content.getGender();
	}

	/**
	 * Sets the value of the pgn property.
	 * 
	 * @param value
	 *           allowed object is {@link java.lang.String}
	 */
	public void setGender(java.lang.String value) {
		content.setGender(value);
	}

	/**
	 * Gets the value of the pgn property.
	 * 
	 * @return possible object is {@link java.lang.String}
	 */
	public java.lang.String getNumber() {
		return content.getNumber();
	}

	/**
	 * Sets the value of the pgn property.
	 * 
	 * @param value
	 *           allowed object is {@link java.lang.String}
	 */
	public void setNumber(java.lang.String value) {
		content.setNumber(value);
	}

	public static void main(String[] args) {
	}
}
