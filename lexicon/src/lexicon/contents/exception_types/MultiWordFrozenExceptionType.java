//
//This file was generated by the JavaTM Architecture for XML Binding(JAXB)
//Reference Implementation, v1.0.4-b16-fcs
//See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a>
//Any modifications to this file will be lost upon recompilation of the source
//schema.
//Generated on: 2005.02.17 at 12:10:17 GMT+02:00
//
package lexicon.contents.exception_types;

import java.net.URLEncoder;

import lexicon.contents.Content;
import lexicon.tools.LexiconUtils;

public class MultiWordFrozenExceptionType extends Content implements
		lexicon.jaxb.MultiWordFrozenExceptionType {
	protected lexicon.jaxb.MultiWordFrozenExceptionType content;

	public MultiWordFrozenExceptionType(
			lexicon.jaxb.MultiWordFrozenExceptionType content) {
		this.content = content;
		TABLE = "multiWordFrozen_exception_type";
		IDNAME = "aid";
	}

	public MultiWordFrozenExceptionType() {
		content = new lexicon.jaxb.impl.MultiWordFrozenExceptionTypeImpl();
		TABLE = "multiWordFrozen_exception_type";
		IDNAME = "aid";
	}

	public lexicon.jaxb.impl.MultiWordFrozenExceptionTypeImpl getImpl() {
		return (lexicon.jaxb.impl.MultiWordFrozenExceptionTypeImpl) content;
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
			transliterated = URLEncoder.encode(transliterated,
					Content.ADD_ENCODING);
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
		sql += ", " + (isDefiniteness() ? 1 : 0);
		sql += ", " + (isAcceptDefiniteness() ? 1 : 0);
		sql += ", '" + getAction() + "')";
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
		String undotted = getUndotted();
		try {
			undotted = URLEncoder.encode(undotted, Content.UPDATE_ENCODING);
		} catch (Exception e) {
		}
		sql += " undotted='" + undotted + "'";
		String transliterated = LexiconUtils.getTransliteration(undotted);
		try {
			transliterated = URLEncoder.encode(transliterated,
					Content.UPDATE_ENCODING);
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
		sql += ", definiteness=" + (isDefiniteness() ? 1 : 0);
		sql += ", acceptDefiniteness=" + (isAcceptDefiniteness() ? 1 : 0);
		sql += ", action='" + getAction() + "' WHERE aid=" + getID();
		int feedback = execute(sql);
		return feedback;
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

	public void load() {
		setValue("");
		setDotted(getString("dotted"));
		setUndotted(getString("undotted"));
		setRegister(getString("register"));
		setSpelling(getString("spelling"));
		setTransliterated(getString("transliterated"));
		setDefiniteness(getInt("definiteness") == 1);
		setAcceptDefiniteness(getInt("acceptDefiniteness") == 1);
		// setValue(getString("value"));
	}

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
	 *            allowed object is {@link java.lang.String}
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
	 *            allowed object is {@link java.lang.String}
	 */
	public void setDotted(java.lang.String value) {
		content.setDotted(value);
	}

	/**
	 * Gets the value of the acronym property.
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
	 *            allowed object is {@link java.lang.String}
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
	 *            allowed object is {@link java.lang.String}
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
	 *            allowed object is {@link java.lang.String}
	 */
	public void setTransliterated(java.lang.String value) {
		content.setTransliterated(value);
	}

	public java.lang.String getValue() {
		return content.getValue();
	}

	public void setValue(java.lang.String value) {
		content.setValue(value);
	}

	public boolean isDefiniteness() {
		return content.isDefiniteness();
	}

	public void setDefiniteness(boolean value) {
		content.setDefiniteness(value);
	}

	public boolean isAcceptDefiniteness() {
		return content.isAcceptDefiniteness();
	}

	public void setAcceptDefiniteness(boolean value) {
		content.setAcceptDefiniteness(value);
	}

	// /-----------------------------------------------------------------------------------------------------------------------------------
	public String GetExceptionHtml() { // not the best solution but in order to
										// take some of the load from the jsp
										// file doitem.jsp whcih has reached his
										// 64k limit
										// i am using java to output the the
										// html , using a servelt would have
										// been better but i am trying to avoid
										// the change of the
										// the entire GUI (yossi 31.10.10)
		String html = null;
		String add_sel = null, replace_sel = null, remove_sel = null;
		String formal_sel = null, archaic_sel = null, informal_sel = null;
		String standard_sel = null, irregular_sel = null; // spelling
		String definiteness_sel = null, no_definiteness_sel = null;
		String accept_definiteness_sel = null, no_accept_definiteness_sel = null;

		if (getAction().equals("add")) // get the right action to be selected
			add_sel = "SELECTED";
		else if (getAction().equals("replace"))
			replace_sel = "SELECTED";
		else
			remove_sel = "SELECTED";

		if (getRegister().equals("formal"))
			formal_sel = "SELECTED";
		else if (getRegister().equals("archaic"))
			archaic_sel = "SELECTED";
		else
			informal_sel = "SELECTED";

		if (getSpelling().equals("irregular"))
			irregular_sel = "SELECTED";
		else
			standard_sel = "SELECTED";

		if (!isDefiniteness())
			no_definiteness_sel = "SELECTED";
		else
			definiteness_sel = "SELECTED";

		if (!isAcceptDefiniteness())
			no_accept_definiteness_sel = "SELECTED";
		else
			accept_definiteness_sel = "SELECTED";

		html = "<tr><td>פעולה:</td>"
				+ "<td><select name=\'multiWordFrozen_exception_action\'>"
				+ "<option value='add' "
				+ add_sel
				+ " >הוספה</option>"
				+ "<option value='replace' "
				+ replace_sel
				+ " >החלפה</option>"
				+ "<option value='remove' "
				+ remove_sel
				+ ">הסרה</option>"
				+ "</select></td></tr>"
				+ "<tr><td>צורה לא מנוקדת:</td>"
				+ "<td><input type='text' name='multiWordFrozen_exception_undotted' size=20 value='"
				+ getUndotted().trim()
				+ "'></td></tr>"
				+ "<tr><td>צורת תעתיק:</td>"
				+ "<td>"
				+ getTransliterated()
				+ "</td></tr>"
				+ "<tr><td>צורה מנוקדת:</td>"
				+ "<td><input type='text' name='multiWordFrozen_exception_dotted' size=20 value='"
				+ getDotted().trim()
				+ "'></td></tr>"
				+ "<tr><td>משלב:</td><td>"
				+ "<select name='multiWordFrozen_exception_register'>"
				+ "<option value='formal' "
				+ formal_sel
				+ ">תקני</option>"
				+ "<option value='archaic' "
				+ archaic_sel
				+ ">ארכאי</option>"
				+ "<option value='informal' "
				+ informal_sel
				+ " >תת-תקני</option>"
				+ "</select></td></tr>"
				+ "<tr><td>כתיב:</td>"
				+ "<td><select name='multiWordFrozen_exception_spelling'>"
				+ "<option value='standard' "
				+ standard_sel
				+ ">תקני</option>"
				+ "<option value='irregular' "
				+ irregular_sel
				+ ">תת-תקני</option>"
				+ "</select></td></tr>"
				+ "<tr><td>מיודע:</td>"
				+ "<td><select name='multiWordFrozen_exception_definiteness'>"
				+ "<option value='0' "
				+ no_definiteness_sel
				+ ">לא</option>"
				+ "<option value='1' "
				+ definiteness_sel
				+ ">כן</option>"
				+ "</select></td></tr>"
				+ "<tr><td>האם מקבל יידוע:</td>"
				+ "<td><select name='multiWordFrozen_exception_acceptDefiniteness'>"
				+ "<option value='0' "
				+ no_accept_definiteness_sel
				+ ">לא</option>"
				+ "<option value='1' "
				+ accept_definiteness_sel
				+ ">כן</option>"
				+ "</select></td></tr>";
		return html;
	}

}