package lexicon.contents.exception_types;

import java.net.URLEncoder;
import lexicon.contents.Content;
import lexicon.tools.LexiconUtils;

public class AdjectiveExceptionType extends Content implements
		lexicon.jaxb.NounExceptionType {
	protected lexicon.jaxb.NounExceptionType content;

	public AdjectiveExceptionType(lexicon.jaxb.NounExceptionType content) {
		this.content = content;
		TABLE = "noun_exception_type";
		IDNAME = "aid";
	}

	public AdjectiveExceptionType() {
		content = new lexicon.jaxb.impl.NounExceptionTypeImpl();
		TABLE = "noun_exception_type";
		IDNAME = "aid";
	}

	public lexicon.jaxb.impl.NounExceptionTypeImpl getImpl() {
		return (lexicon.jaxb.impl.NounExceptionTypeImpl) content;
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
		sql += ", '" + getGender() + "'";
		sql += ", '" + getNumber() + "'";
		sql += ", '" + getFeminine() + "'";
		sql += ", '" + getPlural() + "'";
		sql += ", '" + getPossessive() + "'";
		sql += ", '" + getConstruct() + "'";
		sql += ", '" + getDual() + "'";
		sql += ", " + (isInflectConstructS() ? 1 : 0);
		sql += ", " + (isInflectPossessiveS() ? 1 : 0);
		sql += ", " + (isInflectConstructP() ? 1 : 0);
		sql += ", " + (isInflectPossessiveP() ? 1 : 0);
		sql += ", '" + getAction() + "')";
		// int feedback = execute(sql);
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
		sql += ", gender='" + getGender() + "'";
		sql += ", number='" + getNumber() + "'";
		sql += ", feminine='" + getFeminine() + "'";
		sql += ", plural='" + getPlural() + "'";
		sql += ", possessive='" + getPossessive() + "'";
		sql += ", construct='" + getConstruct() + "'";
		sql += ", is_dual='" + getDual() + "'";
		sql += ", inflectConstructS=" + (isInflectConstructS() ? 1 : 0);
		sql += ", inflectPossessiveS=" + (isInflectPossessiveS() ? 1 : 0);
		sql += ", inflectConstructP=" + (isInflectConstructP() ? 1 : 0);
		sql += ", inflectPossessiveP=" + (isInflectPossessiveP() ? 1 : 0);
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
		setPlural(getString("plural"));
		setFeminine(getString("feminine"));
		setDual(getString("is_dual"));
		setConstruct(getString("construct"));
		setNumber(getString("number"));
		setGender(getString("gender"));
		setPossessive(getString("possessive"));
		setInflectConstructS(getInt("inflectConstructS") == 1);
		setInflectPossessiveS(getInt("inflectPossessiveS") == 1);
		setInflectConstructP(getInt("inflectConstructP") == 1);
		setInflectPossessiveP(getInt("inflectPossessiveP") == 1);
	}

	/**
	 * Gets the value of the feminine property.
	 * 
	 * @return possible object is {@link java.lang.String}
	 */
	public java.lang.String getFeminine() {
		return content.getFeminine();
	}

	/**
	 * Sets the value of the feminine property.
	 * 
	 * @param value
	 *            allowed object is {@link java.lang.String}
	 */
	public void setFeminine(java.lang.String value) {
		content.setFeminine(value);
	}

	/**
	 * Gets the value of the possessive property.
	 * 
	 * @return possible object is {@link java.lang.String}
	 */
	public java.lang.String getPossessive() {
		return content.getPossessive();
	}

	/**
	 * Sets the value of the possessive property.
	 * 
	 * @param value
	 *            allowed object is {@link java.lang.String}
	 */
	public void setPossessive(java.lang.String value) {
		content.setPossessive(value);
	}

	/**
	 * Gets the value of the gender property.
	 * 
	 * @return possible object is {@link java.lang.String}
	 */
	public java.lang.String getGender() {
		return content.getGender();
	}

	/**
	 * Sets the value of the gender property.
	 * 
	 * @param value
	 *            allowed object is {@link java.lang.String}
	 */
	public void setGender(java.lang.String value) {
		content.setGender(value);
	}

	/**
	 * Gets the value of the plural property.
	 * 
	 * @return possible object is {@link java.lang.String}
	 */
	public java.lang.String getPlural() {
		return content.getPlural();
	}

	/**
	 * Sets the value of the plural property.
	 * 
	 * @param value
	 *            allowed object is {@link java.lang.String}
	 */
	public void setPlural(java.lang.String value) {
		content.setPlural(value);
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
	 *            allowed object is {@link java.lang.String}
	 */
	public void setValue(java.lang.String value) {
		content.setValue(value);
	}

	/**
	 * Gets the value of the construct property.
	 * 
	 * @return possible object is {@link java.lang.String}
	 */
	public java.lang.String getConstruct() {
		return content.getConstruct();
	}

	/**
	 * Sets the value of the construct property.
	 * 
	 * @param value
	 *            allowed object is {@link java.lang.String}
	 */
	public void setConstruct(java.lang.String value) {
		content.setConstruct(value);
	}

	/**
	 * Gets the value of the dual property.
	 * 
	 * @return possible object is {@link java.lang.String}
	 */
	public java.lang.String getDual() {
		return content.getDual();
	}

	/**
	 * Sets the value of the dual property.
	 * 
	 * @param value
	 *            allowed object is {@link java.lang.String}
	 */
	public void setDual(java.lang.String value) {
		content.setDual(value);
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
	 *            allowed object is {@link java.lang.String}
	 */
	public void setNumber(java.lang.String value) {
		content.setNumber(value);
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
	 *            allowed object is {@link java.lang.String}
	 */
	public void setUndotted(java.lang.String value) {
		content.setUndotted(value);
	}

	/**
	 * Gets the value of the inflectConstruct property.
	 * 
	 */

	public boolean isInflectConstructS() {
		return content.isInflectConstructS();
	}

	/**
	 * Sets the value of the inflectConstruct property.
	 * 
	 */
	public void setInflectConstructS(boolean value) {
		content.setInflectConstructS(value);
	}

	public boolean isInflectPossessiveS() {
		return content.isInflectPossessiveS();
	}

	/**
	 * Sets the value of the inflectConstruct property.
	 * 
	 */
	public void setInflectPossessiveS(boolean value) {
		content.setInflectPossessiveS(value);
	}

	/**
	 * Gets the value of the inflectConstruct property.
	 * 
	 */

	public boolean isInflectConstructP() {
		return content.isInflectConstructP();
	}

	/**
	 * Sets the value of the inflectConstruct property.
	 * 
	 */
	public void setInflectConstructP(boolean value) {
		content.setInflectConstructP(value);
	}

	public boolean isInflectPossessiveP() {
		return content.isInflectPossessiveP();
	}

	/**
	 * Sets the value of the inflectConstruct property.
	 * 
	 */
	public void setInflectPossessiveP(boolean value) {
		content.setInflectPossessiveP(value);
	}

	// /-----------------------------------------------------------------------------------------------------------------------------------

	// /-----------------------------------------------------------------------------------------------------------------------------------
	public String GetExceptionHtml() {
		/*
		 * this function is used to create the html for the exception form it is
		 * not the best way to accomplish this but because there was too much
		 * code on the jsp file i had to move some into a java class.
		 */
		String html = null;

		String add_sel = null, replace_sel = null, remove_sel = null;
		if (getAction().equals("add")) // get the right action to be selected
			add_sel = "SELECTED";
		else if (getAction().equals("replace"))
			replace_sel = "SELECTED";
		else
			remove_sel = "SELECTED";

		html = "<tr><td>פעולה:</td>"
				+ "<td><select name=\'adjective_exception_action\'>"
				+ "<option value='add' " + add_sel + " >הוספה</option>"
				+ "<option value='replace' " + replace_sel + " >החלפה</option>"
				+ "<option value='remove' " + remove_sel + ">הסרה</option>"
				+ "</select></td></tr>";

		html += "<tr><td>צורה לא מנוקדת:</td>"
				+ "<td><input type='text' name='adjective_exception_undotted' size=20 value='"
				+ getUndotted().trim() + "'></td></tr>";

		html += "<tr><td>צורת תעתיק:</td>" + "<td>" + getTransliterated()
				+ "</td></tr>";

		String formal_sel = null, archaic_sel = null, informal_sel = null;
		if (getRegister().equals("formal")) // register
			formal_sel = "SELECTED";
		else if (getRegister().equals("archaic"))
			archaic_sel = "SELECTED";
		else
			informal_sel = "SELECTED";
		html += "<tr><td>צורה מנוקדת:</td>"
				+ "<td><input type='text' name='adjective_exception_dotted' size=20 value='"
				+ getDotted().trim() + "'></td></tr>"
				+ "<tr><td>משלב:</td><td>"
				+ "<select name='adjective_exception_register'>"
				+ "<option value='formal' " + formal_sel + ">תקני</option>"
				+ "<option value='archaic' " + archaic_sel + ">ארכאי</option>"
				+ "<option value='informal' " + informal_sel
				+ " >תת-תקני</option>" + "</select></td></tr>";

		String standard_sel = null, irregular_sel = null; // spelling
		if (getSpelling().equals("irregular")) // spelling
			irregular_sel = "SELECTED";
		else
			standard_sel = "SELECTED";
		html += "<tr><td>כתיב:</td>"
				+ "<td><select name='adjective_exception_spelling'>"
				+ "<option value='standard' " + standard_sel + ">תקני</option>"
				+ "<option value='irregular' " + irregular_sel
				+ ">תת-תקני</option>" + "</select></td></tr>";

		// GENDER
		String masculine_sel = null, feminine_sel = null, unspecified_gender_sel = null, masculine_and_feminine_sel = null;
		String gender = getGender();
		if (gender.equals("masculine")) {
			masculine_sel = "SELECTED";
		} else if (gender.equals("feminine")) {
			feminine_sel = "SELECTED";
		} else if (gender.equals("masculine and feminine")) {
			masculine_and_feminine_sel = "SELECTED";
		} else {
			unspecified_gender_sel = "SELECTED";
		}
		html += "<tr><td>מין:</td>"
				+ "<td><select name='adjective_exception_gender'>"
				+ "<option value='masculine' " + masculine_sel
				+ ">זכר</option>" + "<option value='feminine' " + feminine_sel
				+ ">נקבה</option>" + "<option value='masculine and feminine' "
				+ masculine_and_feminine_sel + ">גם זכר וגם נקבה</option>"
				+ "<option value='unspecified' " + unspecified_gender_sel
				+ ">לא ידוע</option>" + "</select></td></tr>";

		// NUMBER
		String singular_sel = null, plural_sel = null, unspecified_number_sel = null, dual_sel = null, dual_and_plural_sel = null, singular_and_plural_sel = null;
		String number = getNumber();
		if (number.equals("singular")) {
			singular_sel = "SELECTED";
		} else if (number.equals("plural")) {
			plural_sel = "SELECTED";
		} else if (number.equals("dual")) {
			dual_sel = "SELECTED";
		} else if (number.equals("dual and plural")) {
			dual_and_plural_sel = "SELECTED";
		} else if (number.equals("singular and plural")) {
			singular_and_plural_sel = "SELECTED";
		} else {
			unspecified_number_sel = "SELECTED";
		}
		html += "<tr><td>מספר:</td>"
				+ "<td><select name='adjective_exception_number'>"
				+ "<option value='singular' " + singular_sel + ">יחיד</option>"
				+ "<option value='plural' " + plural_sel + ">רבים</option>"
				+ "<option value='dual' " + dual_sel + ">זוגי</option>"
				+ "<option value='dual and plural' " + dual_and_plural_sel
				+ ">גם זוגי וגם רבים</option>"
				+ "<option value='singular and plural' "
				+ singular_and_plural_sel + ">גם יחיד וגם רבים</option>"
				+ "<option value='unspecified' " + unspecified_number_sel
				+ ">לא ידוע</option>" + "</select></td></tr>";

		String feminine_t_sel = null, feminine_unspecified_sel = null;
		if (getFeminine().equals("t")) {
			feminine_t_sel = "SELECTED";
		} else {
			feminine_unspecified_sel = "SELECTED";
		}
		html += "<tr><td>צורן נקבה:</td>"
				+ "<td><select name='adjective_exception_feminine'>"
				+ "<option value='t' " + feminine_t_sel + ">ת</option>"
				+ "<option value='unspecified' " + feminine_unspecified_sel
				+ ">לא מוגדר</option>" + "</select></td></tr>";

		String plural_m_sel = null, plural_im_sel = null, plural_iim_sel = null, plural_wt_sel = null, plural_awt_sel = null, plural_iwt_sel = null;
		String plural_imandwt_sel = null, plural_unspecified_sel = null;
		if (getPlural().equals("m"))
			plural_m_sel = "SELECTED";
		else if (getPlural().equals("im"))
			plural_im_sel = "SELECTED";
		else if (getPlural().equals("iim"))
			plural_iim_sel = "SELECTED";
		else if (getPlural().equals("wt"))
			plural_wt_sel = "SELECTED";
		else if (getPlural().equals("awt"))
			plural_awt_sel = "SELECTED";
		else if (getPlural().equals("iwt"))
			plural_iwt_sel = "SELECTED";
		else if (getPlural().equals("im and wt"))
			plural_imandwt_sel = "SELECTED";
		else
			plural_unspecified_sel = "SELECTED";
		html += "<tr><td>צורן ריבוי:</td>"
				+ "<td><select name='adjective_exception_plural'>"
				+ "<option value='m' "
				+ plural_m_sel
				+ ">ם</option>"
				+ "<option value='im' "
				+ plural_im_sel
				+ ">ים</option>"
				+ "<option value='iim' "
				+ plural_iim_sel
				+ ">יים</option>"
				+ "<option value='wt' "
				+ plural_wt_sel
				+ " >ות</option>"
				+ "<option value='awt' "
				+ plural_awt_sel
				+ ">אות</option>"
				+ "<option value='iwt' "
				+ plural_iwt_sel
				+ ">יות</option>"
				+ "<option value='im and wt' "
				+ plural_imandwt_sel
				+ ">ים וגם ות</option>"
				+ "<option value='unspecified' "
				+ plural_unspecified_sel
				+ ">לא מוגדר</option>"
				+ "</select></td></tr>";

		// dual form
		String dual_unspecified_sel = null, dual_true_sel = null, dual_false_sel = null;
		if (getDual().equals("unspecified"))
			dual_unspecified_sel = "SELECTED";
		else if (getDual().equals("true"))
			dual_true_sel = "SELECTED";
		else
			dual_false_sel = "SELECTED";

		html += "<tr><td>האם צורת זוגי?</td>"
				+ "<td><select name='adjective_exception_dual'>"
				+ "<option value='unspecified' " + dual_unspecified_sel
				+ ">לא מוגדר</option>" + "<option value='true' "
				+ dual_true_sel + ">כן</option>" + "<option value='false' "
				+ dual_false_sel + ">לא</option>" + "</select></td></tr>";

		// --------- construct
		String construct_false_sell = null, construct_true_sell = null, construct_unspecified_sell = null;
		if (getConstruct().equals("false"))
			construct_false_sell = "SELECTED";
		else if (getConstruct().equals("true"))
			construct_true_sell = "SELECTED";
		else
			construct_unspecified_sell = "SELECTED";

		html += "<tr><td>האם צורת נסמך?</td>"
				+ "<td><select name='adjective_exception_construct'>"
				+ "<option value='false' " + construct_false_sell
				+ ">לא</option>" + "<option value='true' "
				+ construct_true_sell + ">כן</option>"
				+ "<option value='unspecified' " + construct_unspecified_sell
				+ ">לא מוגדר</option>" + "</select></td></tr>";

		// construct singlular
		String isInflectConstructS_yes_sel = null, isInflectConstructS_no_sel = null;
		if (isInflectConstructS())
			isInflectConstructS_yes_sel = "SELECTED";
		else
			isInflectConstructS_no_sel = "SELECTED";

		html += "<tr><td>האם לייצר הטיית נסמך ליחיד?</td>"
				+ "<td><select name='adjective_exception_inflectConstructS'>"
				+ "<option value='1' " + isInflectConstructS_yes_sel
				+ " >כן</option>" + "<option value='0' "
				+ isInflectConstructS_no_sel + ">לא</option>"
				+ "</select></td></tr>";

		// construct plural
		String isInflectConstructP_yes_sel = null, isInflectConstructP_no_sel = null;
		if (isInflectConstructP())
			isInflectConstructP_yes_sel = "SELECTED";
		else
			isInflectConstructP_no_sel = "SELECTED";

		html += "<tr><td>האם לייצר הטיית נסמך לרבים?</td>"
				+ "<td><select name='adjective_exception_inflectConstructP'>"
				+ "<option value='1' " + isInflectConstructP_yes_sel
				+ " >כן</option>" + "<option value='0' "
				+ isInflectConstructP_no_sel + ">לא</option>"
				+ "</select></td></tr>";

		return html;
	}

}
