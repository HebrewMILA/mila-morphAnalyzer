//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB)
// Reference Implementation, v1.0.4-b16-fcs
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a>
// Any modifications to this file will be lost upon recompilation of the source
// schema.
// Generated on: 2005.02.17 at 12:10:17 GMT+02:00
//
package lexicon.contents.exception_types;

import java.net.URLEncoder;

import lexicon.contents.Content;
import lexicon.tools.LexiconUtils;

public class MultiWordNounAdjectiveExceptionType extends Content
		implements lexicon.jaxb.MultiWordNounAdjectiveExceptionType {
	protected lexicon.jaxb.MultiWordNounAdjectiveExceptionType content;

	public MultiWordNounAdjectiveExceptionType(lexicon.jaxb.MultiWordNounAdjectiveExceptionType content) {
		this.content = content;
		TABLE = "multiWordNounAdjective_exception_type";
		IDNAME = "aid";
	}

	public MultiWordNounAdjectiveExceptionType() {
		content = new lexicon.jaxb.impl.MultiWordNounAdjectiveExceptionTypeImpl();
		TABLE = "multiWordNounAdjective_exception_type";
		IDNAME = "aid";
	}

	public lexicon.jaxb.impl.MultiWordNounAdjectiveExceptionTypeImpl getImpl() {
		return (lexicon.jaxb.impl.MultiWordNounAdjectiveExceptionTypeImpl) content;
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
		sql += ", '" + getFeminine() + "'";
		sql += ", '" + getPlural() + "'";
		sql += ", '" + getDefiniteness() + "'";
		sql += ", '" + getPossessive() + "'";
		sql += ", " + (isInflectPossessiveS() ? 1 : 0);
		sql += ", " + (isInflectPossessiveP() ? 1 : 0);
		sql += ", '" + getAction() + "')";
		execute(sql);
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
		sql += " ,gender='" + getGender() + "'";
		sql += ", number='" + getNumber() + "'";
		sql += ", feminine='" + getFeminine() + "'";
		sql += ", plural='" + getPlural() + "'";
		sql += ", definiteness='" + getDefiniteness() + "'";
		sql += ", possessive='" + getPossessive() + "'";
		sql += ", inflectPossessiveS=" + (isInflectPossessiveS() ? 1 : 0);
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
		setInflectPossessiveS(getInt("inflectPossessiveS") == 1);
		setInflectPossessiveP(getInt("inflectPossessiveP") == 1);
		setDefiniteness(getString("definiteness"));
		setNumber(getString("number"));
		setGender(getString("gender"));
		setPlural(getString("plural"));
		setPossessive(getString("possessive"));
		setFeminine(getString("feminine"));
		setValue(getString("value"));
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

	public boolean isInflectPossessiveS() {
		return content.isInflectPossessiveS();
	}

	public void setInflectPossessiveS(boolean value) {
		content.setInflectPossessiveS(value);
	}

	public boolean isInflectPossessiveP() {
		return content.isInflectPossessiveP();
	}

	public void setInflectPossessiveP(boolean value) {
		content.setInflectPossessiveP(value);
	}

	public java.lang.String getDefiniteness() {
		return content.getDefiniteness();
	}

	public void setDefiniteness(java.lang.String value) {
		content.setDefiniteness(value);
	}

	public java.lang.String getGender() {
		return content.getGender();
	}

	public void setGender(java.lang.String value) {
		content.setGender(value);
	}

	public java.lang.String getNumber() {
		return content.getNumber();
	}

	public void setNumber(java.lang.String value) {
		content.setNumber(value);
	}

	public java.lang.String getPlural() {
		return content.getPlural();
	}

	public void setPlural(java.lang.String value) {
		content.setPlural(value);
	}

	public java.lang.String getFeminine() {
		return content.getFeminine();
	}

	public void setFeminine(java.lang.String value) {
		content.setFeminine(value);
	}

	public java.lang.String getValue() {
		return content.getValue();
	}

	public void setValue(java.lang.String value) {
		content.setValue(value);
	}

	public java.lang.String getPossessive() {
		return content.getPossessive();
	}

	public void setPossessive(java.lang.String value) {
		content.setPossessive(value);
	}

	/// -----------------------------------------------------------------------------------------------------------------------------------
	public String GetExceptionHtml() {
		/*
		 * this function is used to create the html for the exception form it is not the
		 * best way to accomplish this but because there was too much code on the jsp
		 * file i had to move some into a java class.
		 */
		String html = null;
		String add_sel = null, replace_sel = null, remove_sel = null;
		String formal_sel = null, archaic_sel = null, informal_sel = null;
		String standard_sel = null, irregular_sel = null; // spelling
		String masculine_sel = null, feminine_sel = null, unspecified_gender_sel = null;
		String singular_sel = null, plural_sel = null, unspecified_number_sel = null;
		String feminine_t_sel = null, feminine_unspecified_sel = null;
		String plural_m_sel = null, plural_im_sel = null, plural_iim_sel = null, plural_wt_sel = null,
				plural_awt_sel = null, plural_iwt_sel = null;
		String plural_imandwt_sel = null, plural_unspecified_sel = null;
		String definiteness_external_sell = null, definiteness_internal_sell = null, definiteness_both_sell = null,
				definiteness_none_sell = null, definiteness_externallyDefinited_sell = null;

		if (getAction().equals("add")) // get the right action to be selected
			add_sel = "SELECTED";
		else if (getAction().equals("replace"))
			replace_sel = "SELECTED";
		else
			remove_sel = "SELECTED";

		if (getRegister().equals("formal")) // register
			formal_sel = "SELECTED";
		else if (getRegister().equals("archaic"))
			archaic_sel = "SELECTED";
		else
			informal_sel = "SELECTED";

		if (getSpelling().equals("irregular")) // spelling
			irregular_sel = "SELECTED";
		else
			standard_sel = "SELECTED";

		if (getGender().equals("masculine")) {
			masculine_sel = "SELECTED";
		} else if (getGender().equals("feminine")) {
			feminine_sel = "SELECTED";
		} else {
			unspecified_gender_sel = "SELECTED";
		}

		if (getNumber().equals("singular")) {
			singular_sel = "SELECTED";
		} else if (getNumber().equals("plural")) {
			plural_sel = "SELECTED";
		} else {
			unspecified_number_sel = "SELECTED";
		}

		if (getFeminine().equals("t")) {
			feminine_t_sel = "SELECTED";
		} else {
			feminine_unspecified_sel = "SELECTED";
		}

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

		if (getDefiniteness().equals("external"))
			definiteness_external_sell = "SELECTED";
		else if (getDefiniteness().equals("internal"))
			definiteness_internal_sell = "SELECTED";
		else if (getDefiniteness().equals("internal and external"))
			definiteness_both_sell = "SELECTED";
		else if (getDefiniteness().equals("none"))
			definiteness_none_sell = "SELECTED";
		else
			definiteness_externallyDefinited_sell = "SELECTED";

		html = "<tr><td>פעולה:</td>" + "<td><select name=\'multiWordNounAdjective_exception_action\'>"
				+ "<option value='add' " + add_sel + " >הוספה</option>" + "<option value='replace' " + replace_sel
				+ " >החלפה</option>" + "<option value='remove' " + remove_sel + ">הסרה</option>" + "</select></td></tr>";
		html += "<tr><td>צורה לא מנוקדת:</td>"
				+ "<td><input type='text' name='multiWordNounAdjective_exception_undotted' size=20 value='"
				+ getUndotted().trim() + "'></td></tr>" + "<tr><td>צורת תעתיק:</td>" + "<td>" + getTransliterated()
				+ "</td></tr>" + "<tr><td>צורה מנוקדת:</td>"
				+ "<td><input type='text' name='multiWordNounAdjective_exception_dotted' size=20 value='"
				+ getDotted().trim() + "'></td></tr>" + "<tr><td>משלב:</td><td>"
				+ "<select name='multiWordNounAdjective_exception_register'>" + "<option value='formal' " + formal_sel
				+ ">תקני</option>" + "<option value='archaic' " + archaic_sel + ">ארכאי</option>"
				+ "<option value='informal' " + informal_sel + " >תת-תקני</option>" + "</select></td></tr>"
				+ "<tr><td>כתיב:</td>" + "<td><select name='multiWordNounAdjective_exception_spelling'>"
				+ "<option value='standard' " + standard_sel + ">תקני</option>" + "<option value='irregular' "
				+ irregular_sel + ">תת-תקני</option>" + "</select></td></tr>";
		html += "<tr><td>מין:</td>" + "<td><select name='multiWordNounAdjective_exception_gender'>"
				+ "<option value='masculine' " + masculine_sel + ">זכר</option>" + "<option value='feminine' "
				+ feminine_sel + ">נקבה</option>" + "<option value='unspecified' " + unspecified_gender_sel
				+ ">לא ידוע</option>" + "</select></td></tr>";
		html += "<tr><td>מספר:</td>" + "<td><select name='multiWordNounAdjective_exception_number'>"
				+ "<option value='singular' " + singular_sel + ">יחיד</option>" + "<option value='plural' " + plural_sel
				+ ">רבים</option>" + "<option value='unspecified' " + unspecified_number_sel + ">לא ידוע</option>"
				+ "</select></td></tr>";
		html += "<tr><td>צורן נקבה:</td>" + "<td><select name='multiWordNounAdjective_exception_feminine'>"
				+ "<option value='t' " + feminine_t_sel + ">ת</option>" + "<option value='unspecified' "
				+ feminine_unspecified_sel + ">לא מוגדר</option>" + "</select></td></tr>";
		html += "<tr><td>צורן ריבוי:</td>" + "<td><select name='multiWordNounAdjective_exception_plural'>"
				+ "<option value='m' " + plural_m_sel + ">ם</option>" + "<option value='im' " + plural_im_sel
				+ ">ים</option>" + "<option value='iim' " + plural_iim_sel + ">יים</option>" + "<option value='wt' "
				+ plural_wt_sel + " >ות</option>" + "<option value='awt' " + plural_awt_sel + ">אות</option>"
				+ "<option value='iwt' " + plural_iwt_sel + ">יות</option>" + "<option value='im and wt' "
				+ plural_imandwt_sel + ">ים וגם ות</option>" + "<option value='unspecified' " + plural_unspecified_sel
				+ ">לא מוגדר</option>" + "</select></td></tr>";
		html += "<tr><td>יידוע:</td>" + "<td><select name='multiWordNounAdjective_exception_definiteness'>"
				+ "<option value='external' " + definiteness_external_sell + ">ייצר יידוע חיצוני</option>"
				+ "<option value='internal' " + definiteness_internal_sell + ">ייצר יידוע פנימי</option>"
				+ "<option value='internal and external' " + definiteness_both_sell
				+ ">ייצר יידוע פנימי וגם חיצוני</option>" + "<option value='none' " + definiteness_none_sell
				+ ">אל תייצר שום יידוע</option>" + "<option value='externallyDefinited' "
				+ definiteness_externallyDefinited_sell + ">קיימת הצורה החיצונית מיודעת בלבד</option>"
				+ "</select></td></tr>";

		// possessive drop down
		String possessive_unspecified_sel = null, possessive_1p_MF_Sg_sel = null, possessive_2p_M_Sg_sel = null,
				possessive_2p_F_Sg_sel = null;
		String possessive_3p_M_Sg_sel = null, possessive_3p_F_Sg_sel = null, possessive_1p_MF_Pl_sel = null;
		String possessive_2p_M_Pl_sel = null, possessive_2p_F_Pl_sel = null, possessive_3p_M_Pl_sel = null,
				possessive_3p_F_Pl_sel = null;
		String possessive_123p_M_Sg_sel = null, possessive_123p_F_Sg_sel = null;
		String possessive_123p_M_Pl_sel = null, possessive_123p_F_Pl_sel = null;

		if (getPossessive().equals("unspecified"))
			possessive_unspecified_sel = "SELECTED";
		else if (getPossessive().equals("1p/MF/Sg"))
			possessive_1p_MF_Sg_sel = "SELECTED";
		else if (getPossessive().equals("2p/M/Sg"))
			possessive_2p_M_Sg_sel = "SELECTED";
		else if (getPossessive().equals("2p/F/Sg"))
			possessive_2p_F_Sg_sel = "SELECTED";
		else if (getPossessive().equals("3p/M/Sg"))
			possessive_3p_M_Sg_sel = "SELECTED";
		else if (getPossessive().equals("3p/F/Sg"))
			possessive_3p_F_Sg_sel = "SELECTED";
		else if (getPossessive().equals("1p/MF/Pl"))
			possessive_1p_MF_Pl_sel = "SELECTED";
		else if (getPossessive().equals("2p/M/Pl"))
			possessive_2p_M_Pl_sel = "SELECTED";
		else if (getPossessive().equals("2p/F/Pl"))
			possessive_2p_F_Pl_sel = "SELECTED";
		else if (getPossessive().equals("3p/M/Pl"))
			possessive_3p_M_Pl_sel = "SELECTED";
		else if (getPossessive().equals("3p/F/Pl"))
			possessive_3p_F_Pl_sel = "SELECTED";
		else if (getPossessive().equals("123p/M/Sg"))
			possessive_123p_M_Sg_sel = "SELECTED";
		else if (getPossessive().equals("123p/F/Sg"))
			possessive_123p_F_Sg_sel = "SELECTED";
		else if (getPossessive().equals("123p/M/Pl"))
			possessive_123p_M_Pl_sel = "SELECTED";
		else
			possessive_123p_F_Pl_sel = "SELECTED";

		html += "<tr><td>כינוי קניין חבור?</td>" + "<td><select name='multiWordNounAdjective_exception_possessive'>"
				+ "<option value='unspecified' " + possessive_unspecified_sel + ">לא ידוע</option>"
				+ "<option value='1p/MF/Sg' " + possessive_1p_MF_Sg_sel + ">גוף ראשון / זכר וגם נקבה / יחיד</option>"
				+ "<option value='2p/M/Sg' " + possessive_2p_M_Sg_sel + ">גוף שני   / זכר          / יחיד</option>"
				+ "<option value='2p/F/Sg' " + possessive_2p_F_Sg_sel + ">גוף שני   / נקבה         / יחיד</option>"
				+ "<option value='3p/M/Sg' " + possessive_3p_M_Sg_sel + ">גוף שלישי / זכר          / יחיד</option>"
				+ "<option value='3p/F/Sg' " + possessive_3p_F_Sg_sel + ">גוף שלישי / נקבה         / יחיד</option>"
				+ "<option value='1p/MF/Pl' " + possessive_1p_MF_Pl_sel + ">גוף ראשון / זכר וגם נקבה / רבים</option>"
				+ "<option value='2p/M/Pl' " + possessive_2p_M_Pl_sel + ">גוף שני   / זכר          / רבים</option>"
				+ "<option value='2p/F/Pl' " + possessive_2p_F_Pl_sel + ">גוף שני   / נקבה         / רבים</option>"
				+ "<option value='3p/M/Pl' " + possessive_3p_M_Pl_sel + ">גוף שלישי / זכר          / רבים</option>"
				+ "<option value='3p/F/Pl' " + possessive_3p_F_Pl_sel + ">גוף שלישי / נקבה         / רבים</option>"
				+ "<option value='123p/M/Sg' " + possessive_123p_M_Sg_sel + ">גוף כלשהו / זכר / יחיד</option>"
				+ "<option value='123p/F/Sg' " + possessive_123p_F_Sg_sel + ">גוף כלשהו / נקבה / יחיד</option>"
				+ "<option value='123p/M/Pl' " + possessive_123p_M_Pl_sel + ">גוף כלשהו / זכר / יחיד</option>"
				+ "<option value='123p/F/Pl' " + possessive_123p_F_Pl_sel + ">גוף כלשהו / נקבה / יחיד</option>"
				+ "</select></td></tr>";

		String isInflectPossessiveS_yes_sel = null, isInflectPossessiveS_no_sel = null;

		if (isInflectPossessiveS())
			isInflectPossessiveS_yes_sel = "SELECTED";
		else
			isInflectPossessiveS_no_sel = "SELECTED";

		html += "<tr><td>האם לייצר קניין לצורת היחיד?</td>"
				+ "<td><select name='multiWordNounAdjective_exception_inflectPossessiveS'>" + "<option value='0' "
				+ isInflectPossessiveS_no_sel + ">לא</option>" + "<option value='1' " + isInflectPossessiveS_yes_sel
				+ ">כן</option>" + "</select></td></tr>";

		String isInflectPossessiveP_yes_sel = null, isInflectPossessiveP_no_sel = null;

		if (isInflectPossessiveP())
			isInflectPossessiveP_yes_sel = "SELECTED";
		else
			isInflectPossessiveP_no_sel = "SELECTED";

		html += "<tr><td>האם לייצר קניין לצורת הרבים?</td>"
				+ "<td><select name='multiWordNounAdjective_exception_inflectPossessiveP'>" + "<option value='0' "
				+ isInflectPossessiveP_no_sel + ">לא</option>" + "<option value='1' " + isInflectPossessiveP_yes_sel
				+ ">כן</option>" + "</select></td></tr>";

		return html;
	}

}
