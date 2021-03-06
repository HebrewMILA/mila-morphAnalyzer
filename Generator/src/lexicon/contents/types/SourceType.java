//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v1.0.4-b16-fcs 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2005.02.17 at 12:10:17 GMT+02:00 
//

package lexicon.contents.types;

import java.net.URLEncoder;

import lexicon.contents.Content;

/**
 * ���� ����� ���� ���� ������
 * ����� ������. �� �����, ����
 * �� �����, ������ (���� �����).
 * 
 * Java content class for SourceType complex type.
 * <p>
 * The following schema fragment specifies the expected content contained within
 * this java content object. (defined at file:/C:/hebrew_lexicon.xsd line 64)
 * <p>
 * 
 * <pre>
 * &lt;complexType name="SourceType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;attribute name="name" use="required" type="{http://www.w3.org/2001/XMLSchema}token" />
 *       &lt;attribute name="url" type="{http://www.w3.org/2001/XMLSchema}token" default="" />
 *       &lt;attribute name="version" type="{http://www.w3.org/2001/XMLSchema}token" default="" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 */
public class SourceType extends Content implements lexicon.jaxb.SourceType {
	lexicon.jaxb.SourceType content;

	public SourceType(lexicon.jaxb.SourceType content) {
		this.content = content;
		TABLE = "source";
		IDNAME = "source_id";
	}

	public SourceType() {
		content = new lexicon.jaxb.impl.SourceTypeImpl();
		TABLE = "source";
		IDNAME = "source_id";
	}

	public lexicon.jaxb.impl.SourceTypeImpl getImpl() {
		return (lexicon.jaxb.impl.SourceTypeImpl) content;
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
	public int add(int mid) {
		String sql = "INSERT INTO " + getTableName() + " VALUES (";
		sql += "0";
		sql += ", " + mid;
		String name = getName();
		if (name == null) {
			name = "";
		}
		try {
			name = URLEncoder.encode(name, Content.ADD_ENCODING);
		} catch (Exception e) {
		}
		sql += ", '" + name + "'";
		sql += ", '" + getVersion() + "'";
		sql += ", '" + getUrl() + "')";
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
		// sql += ", metadata_id="+getInt("metadata_id");
		sql += ", name='" + getName() + "'";
		sql += ", version='" + getVersion() + "'";
		sql += ", url='" + getUrl() + "'";
		int feedback = execute(sql);
		return feedback;
	}

	public void load() {
		setName(getString("name"));
		setUrl(getString("url"));
		setVersion(getString("version"));
	}

	/**
	 * Gets the value of the version property.
	 * 
	 * @return possible object is {@link java.lang.String}
	 */
	public java.lang.String getVersion() {
		return content.getVersion();
	}

	/**
	 * Sets the value of the version property.
	 * 
	 * @param value
	 *           allowed object is {@link java.lang.String}
	 */
	public void setVersion(java.lang.String value) {
		content.setVersion(value);
	}

	/**
	 * Gets the value of the url property.
	 * 
	 * @return possible object is {@link java.lang.String}
	 */
	public java.lang.String getUrl() {
		return content.getUrl();
	}

	/**
	 * Sets the value of the url property.
	 * 
	 * @param value
	 *           allowed object is {@link java.lang.String}
	 */
	public void setUrl(java.lang.String value) {
		content.setUrl(value);
	}

	/**
	 * Gets the value of the name property.
	 * 
	 * @return possible object is {@link java.lang.String}
	 */
	public java.lang.String getName() {
		return content.getName();
	}

	/**
	 * Sets the value of the name property.
	 * 
	 * @param value
	 *           allowed object is {@link java.lang.String}
	 */
	public void setName(java.lang.String value) {
		content.setName(value);
	}

}
