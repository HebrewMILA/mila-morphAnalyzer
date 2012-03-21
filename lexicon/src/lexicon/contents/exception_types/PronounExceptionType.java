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
public class PronounExceptionType extends Content implements
		lexicon.jaxb.PronounExceptionType {
	protected lexicon.jaxb.PronounExceptionType content;

	public PronounExceptionType(lexicon.jaxb.PronounExceptionType content) {
		this.content = content;
		TABLE = "pronoun_exception_type";
		IDNAME = "aid";
	}

	public PronounExceptionType() {
		content = new lexicon.jaxb.impl.PronounExceptionTypeImpl();
		TABLE = "pronoun_exception_type";
		IDNAME = "aid";
	}

	public lexicon.jaxb.impl.PronounExceptionTypeImpl getImpl() {
		return (lexicon.jaxb.impl.PronounExceptionTypeImpl) content;
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
		sql += ", '" + getPerson() + "'";
		sql += ", '" + getPgn() + "'";
		sql += ", '" + getAction() + "')";
		int feedback = execute(sql);
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
		String sql = "UPDATE " + getTableName() + " SET ";
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
		sql += ", pgn='" + getPgn() + "'";
		sql += ", person='" + getPerson() + "'";
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
		setPerson(getString("person"));
		setTransliterated(getString("transliterated"));
		setPgn(getString("pgn"));
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
	 * Gets the value of the pgn property.
	 * 
	 * @return possible object is {@link java.lang.String}
	 */
	public java.lang.String getPgn() {
		return content.getPgn();
	}

	/**
	 * Sets the value of the pgn property.
	 * 
	 * @param value
	 *            allowed object is {@link java.lang.String}
	 */
	public void setPgn(java.lang.String value) {
		content.setPgn(value);
	}

	/**
	 * Gets the value of the pgn property.
	 * 
	 * @return possible object is {@link java.lang.String}
	 */
	public java.lang.String getPerson() {
		return content.getPerson();
	}

	/**
	 * Sets the value of the pgn property.
	 * 
	 * @param value
	 *            allowed object is {@link java.lang.String}
	 */
	public void setPerson(java.lang.String value) {
		content.setPerson(value);
	}

	/**
	 * Gets the value of the pgn property.
	 * 
	 * @return possible object is {@link java.lang.String}
	 */
	public java.lang.String getGender() {
		return content.getGender();
	}

	/**
	 * Sets the value of the pgn property.
	 * 
	 * @param value
	 *            allowed object is {@link java.lang.String}
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
	 *            allowed object is {@link java.lang.String}
	 */
	public void setNumber(java.lang.String value) {
		content.setNumber(value);
	}

	public static void main(String[] args) {
	}

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
				+ "<td><select name=\'pronoun_exception_action\'>"
				+ "<option value='add' " + add_sel + " >הוספה</option>"
				+ "<option value='replace' " + replace_sel + " >החלפה</option>"
				+ "<option value='remove' " + remove_sel + ">הסרה</option>"
				+ "</select></td></tr>";

		html += "<tr><td>צורה לא מנוקדת:</td>"
				+ "<td><input type='text' name='pronoun_exception_undotted' size=20 value='"
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
				+ "<td><input type='text' name='pronoun_exception_dotted' size=20 value='"
				+ getDotted().trim() + "'></td></tr>"
				+ "<tr><td>משלב:</td><td>"
				+ "<select name='pronoun_exception_register'>"
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
				+ "<td><select name='pronoun_exception_spelling'>"
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
				+ "<td><select name='pronoun_exception_gender'>"
				+ "<option value='masculine' " + masculine_sel
				+ ">זכר</option>" + "<option value='feminine' " + feminine_sel
				+ ">נקבה</option>" + "<option value='masculine and feminine' "
				+ masculine_and_feminine_sel + ">גם זכר וגם נקבה</option>"
				+ "<option value='unspecified' " + unspecified_gender_sel
				+ ">לא ידוע</option>" + "</select></td></tr>";

		// NUMBER
		String singular_sel = null, plural_sel = null, unspecified_number_sel = null;
		if (getNumber().equals("singular")) {
			singular_sel = "SELECTED";
		} else if (getNumber().equals("plural")) {
			plural_sel = "SELECTED";
		} else {
			unspecified_number_sel = "SELECTED";
		}
		html += "<tr><td>מספר:</td>"
				+ "<td><select name='pronoun_exception_number'>"
				+ "<option value='singular' " + singular_sel + ">יחיד</option>"
				+ "<option value='plural' " + plural_sel + ">רבים</option>"
				+ "<option value='unspecified' " + unspecified_number_sel
				+ ">לא ידוע</option>" + "</select></td></tr>";

		// PERSON
		String person_unspecified_sel = null, person_1_sel = null, person_2_sel = null, person_3_sel = null, person_any_sel = null;
		if (getPerson().equals("unspecified")) {
			person_unspecified_sel = "SELECTED";
		} else if (getPerson().equals("1")) {
			person_1_sel = "SELECTED";
		} else if (getPerson().equals("2")) {
			person_2_sel = "SELECTED";
		} else if (getPerson().equals("3")) {
			person_3_sel = "SELECTED";
		} else {
			person_any_sel = "SELECTED";
		}

		html += "<tr><td>גוף:</td>"
				+ "<td><select name='pronoun_exception_person'>"
				+ "<option value='unspecified' " + person_unspecified_sel
				+ ">לא ידוע</option>" + "<option value='1' " + person_1_sel
				+ ">גוף ראשון</option>" + "<option value='2' " + person_2_sel
				+ ">גוף שני</option>" + "<option value='3' " + person_3_sel
				+ ">גוף שלישי</option>" + "<option value='any' "
				+ person_any_sel + ">גוף כלשהו</option>"
				+ "</select></td></tr>";

		// person Gender Number drop down
		String personGenderNumber_unspecified_sel = null, personGenderNumber_1p_MF_Sg_sel = null, personGenderNumber_2p_M_Sg_sel = null, personGenderNumber_2p_F_Sg_sel = null;
		String personGenderNumber_3p_M_Sg_sel = null, personGenderNumber_3p_F_Sg_sel = null, personGenderNumber_1p_MF_Pl_sel = null;
		String personGenderNumber_2p_M_Pl_sel = null, personGenderNumber_2p_F_Pl_sel = null, personGenderNumber_3p_M_Pl_sel = null, personGenderNumber_3p_F_Pl_sel = null;
		String personGenderNumber_3p_MF_Pl_sel = null;

		String pgn = getPgn();
		if (pgn.equals("unspecified"))
			personGenderNumber_unspecified_sel = "SELECTED";
		else if (pgn.equals("1p/MF/Sg"))
			personGenderNumber_1p_MF_Sg_sel = "SELECTED";
		else if (pgn.equals("2p/M/Sg"))
			personGenderNumber_2p_M_Sg_sel = "SELECTED";
		else if (pgn.equals("2p/F/Sg"))
			personGenderNumber_2p_F_Sg_sel = "SELECTED";
		else if (pgn.equals("3p/M/Sg"))
			personGenderNumber_3p_M_Sg_sel = "SELECTED";
		else if (pgn.equals("3p/F/Sg"))
			personGenderNumber_3p_F_Sg_sel = "SELECTED";
		else if (pgn.equals("1p/MF/Pl"))
			personGenderNumber_1p_MF_Pl_sel = "SELECTED";
		else if (pgn.equals("2p/M/Pl"))
			personGenderNumber_2p_M_Pl_sel = "SELECTED";
		else if (pgn.equals("2p/F/Pl"))
			personGenderNumber_2p_F_Pl_sel = "SELECTED";
		else if (pgn.equals("3p/M/Pl"))
			personGenderNumber_3p_M_Pl_sel = "SELECTED";
		else if (pgn.equals("3p/MF/Pl"))
			personGenderNumber_3p_MF_Pl_sel = "SELECTED";
		else
			personGenderNumber_3p_F_Pl_sel = "SELECTED";

		html += "<tr><td>נטיית שייכות חבורה:</td>"
				+ "<td><select name='pronoun_exception_pgn'>"
				+ "<option value='unspecified' "
				+ personGenderNumber_unspecified_sel
				+ ">לא ידוע</option>"
				+ "<option value='1p/MF/Sg' "
				+ personGenderNumber_1p_MF_Sg_sel
				+ ">גוף ראשון / זכר וגם נקבה / יחיד</option>"
				+ "<option value='2p/M/Sg' "
				+ personGenderNumber_2p_M_Sg_sel
				+ ">גוף שני   / זכר          / יחיד</option>"
				+ "<option value='2p/F/Sg' "
				+ personGenderNumber_2p_F_Sg_sel
				+ ">גוף שני   / נקבה         / יחיד</option>"
				+ "<option value='3p/M/Sg' "
				+ personGenderNumber_3p_M_Sg_sel
				+ ">גוף שלישי / זכר          / יחיד</option>"
				+ "<option value='3p/F/Sg' "
				+ personGenderNumber_3p_F_Sg_sel
				+ ">גוף שלישי / נקבה         / יחיד</option>"
				+ "<option value='1p/MF/Pl' "
				+ personGenderNumber_1p_MF_Pl_sel
				+ ">גוף ראשון / זכר וגם נקבה / רבים</option>"
				+ "<option value='2p/M/Pl' "
				+ personGenderNumber_2p_M_Pl_sel
				+ ">גוף שני   / זכר          / רבים</option>"
				+ "<option value='2p/F/Pl' "
				+ personGenderNumber_2p_F_Pl_sel
				+ ">גוף שני   / נקבה         / רבים</option>"
				+ "<option value='3p/M/Pl' "
				+ personGenderNumber_3p_M_Pl_sel
				+ ">גוף שלישי / זכר          / רבים</option>"
				+ "<option value='3p/F/Pl' "
				+ personGenderNumber_3p_F_Pl_sel
				+ ">גוף שלישי / נקבה         / רבים</option>"
				+ "<option value='3p/MF/Pl' "
				+ personGenderNumber_3p_MF_Pl_sel
				+ ">גוף שלישי / זכר וגם נקבה          / רבים</option>"
				+ "</select></td></tr>";

		return html;
	}
}
