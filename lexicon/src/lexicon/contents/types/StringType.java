package lexicon.contents.types;

import java.net.URLEncoder;

import lexicon.contents.Content;
import lexicon.jaxb.impl.TransliterationTypeImpl;

/**
 * Java content class for anonymous complex type.
 * <p>
 * The following schema fragment specifies the expected content contained within
 * this java content object. (defined at file:/C:/hebrew_lexicon.xsd line 84)
 * <p>
 * 
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;attribute name="hebrew" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="latin" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 */
public class StringType extends Content implements
		lexicon.jaxb.TransliterationType.StringType {
	lexicon.jaxb.TransliterationType.StringType content;

	public StringType(lexicon.jaxb.TransliterationType.StringType content) {
		this.content = content;
		TABLE = "transliteration_string";
		IDNAME = "string_id";
	}

	public StringType() {
		content = new TransliterationTypeImpl.StringTypeImpl();
		TABLE = "transliteration_string";
		IDNAME = "string_id";
	}

	public lexicon.jaxb.impl.TransliterationTypeImpl.StringTypeImpl getImpl() {
		return (lexicon.jaxb.impl.TransliterationTypeImpl.StringTypeImpl) content;
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
	public int add(int tid) {
		String sql = "INSERT INTO " + getTableName() + " VALUES (";
		sql += "0";
		sql += ", " + tid;
		String hebrew = getHebrew();
		if (hebrew == null) {
			hebrew = "";
		}
		try {
			hebrew = URLEncoder.encode(hebrew, Content.ADD_ENCODING);
		} catch (Exception e) {
		}
		sql += ", '" + hebrew + "'";
		sql += ", '" + getLatin() + "')";
		int feedback = execute(sql);
		id = getCurrentID(getTableName(), getIDName());
		return feedback;
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
		// sql += "transliteration_id="+getInt("transliteration_id");
		sql += ", hebrew='" + getHebrew() + "'";
		sql += ", latin='" + getLatin() + "'";
		int feedback = execute(sql);
		return feedback;
	}

	public void load() {
		setHebrew(getString("hebrew"));
		setLatin(getString("latin"));
	}

	/**
	 * Gets the value of the latin property.
	 * 
	 * @return possible object is {@link java.lang.String}
	 */
	public java.lang.String getLatin() {
		return content.getLatin();
	}

	/**
	 * Sets the value of the latin property.
	 * 
	 * @param value
	 *            allowed object is {@link java.lang.String}
	 */
	public void setLatin(java.lang.String value) {
		content.setLatin(value);
	}

	/**
	 * Gets the value of the hebrew property.
	 * 
	 * @return possible object is {@link java.lang.String}
	 */
	public java.lang.String getHebrew() {
		return content.getHebrew();
	}

	/**
	 * Sets the value of the hebrew property.
	 * 
	 * @param value
	 *            allowed object is {@link java.lang.String}
	 */
	public void setHebrew(java.lang.String value) {
		content.setHebrew(value);
	}

}
