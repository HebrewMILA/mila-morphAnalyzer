//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v1.0.4-b16-fcs 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2005.02.17 at 12:10:17 GMT+02:00 
//
package lexicon.contents.exception_types;

import java.net.URLEncoder;

import lexicon.contents.Content;
import lexicon.tools.LexiconUtils;

/**
 * ���� �� ���� ������ ������ ����� ���� ����� ������� ��� ���� ������, ��� ���� ����� ����������.
 * ����, ���� �� ���� �� ������ ����� (���� �� ������ ���) ����� �����.
 * 
 * Java content class for InterjectionExceptionType complex type.
 * <p>The following schema fragment specifies the expected content contained within this java content object. (defined at file:/C:/hebrew_lexicon.xsd line 1169)
 * <p>
 * <pre>
 * &lt;complexType name="InterjectionExceptionType">
 *   &lt;simpleContent>
 *     &lt;extension base="&lt;http://www.w3.org/2001/XMLSchema>token">
 *       &lt;attribute name="dotted" type="{http://www.w3.org/2001/XMLSchema}token" default="" />
 *       &lt;attribute name="personGenderNumber" type="{}PGNType" default="unspecified" />
 *       &lt;attribute name="script" type="{}ScriptType" default="formal" />
 *       &lt;attribute name="transliterated" use="required" type="{http://www.w3.org/2001/XMLSchema}token" />
 *       &lt;attribute name="undotted" use="required" type="{http://www.w3.org/2001/XMLSchema}token" />
 *     &lt;/extension>
 *   &lt;/simpleContent>
 * &lt;/complexType>
 * </pre>
 * 
 */
public class InterjectionExceptionType  extends Content implements lexicon.jaxb.InterjectionExceptionType{
	protected lexicon.jaxb.InterjectionExceptionType content; 
	
	public InterjectionExceptionType(lexicon.jaxb.InterjectionExceptionType content) {
		this.content = content;
		TABLE = "interjection_exception_type";
		IDNAME = "aid"; 
	}
	public InterjectionExceptionType( ) {
		content = new lexicon.jaxb.impl.InterjectionExceptionTypeImpl();
		TABLE = "interjection_exception_type";
		IDNAME = "aid";  
	}
	public lexicon.jaxb.impl.InterjectionExceptionTypeImpl getImpl() {
		return (lexicon.jaxb.impl.InterjectionExceptionTypeImpl)content;
	}
	public int add() {
		return 0;
	}
	/**
	*An empty implementation to the method add() methods in the different subclasses of Content.
	*Connects to the DB, commits the different SQL statements and return feedback.
	*@param		sql - The SQL statement to be executed.
	*@return	Number of rows affected (0, if nothing happened, 1 if one row added).
	*/
	public int add(int id) {
		String sql = "INSERT INTO "+ getTableName() + " VALUES (";
		sql += "0, ";
		sql += id;
		String undotted = getUndotted(); 
		try {
			undotted = URLEncoder.encode(undotted, Content.ADD_ENCODING);
		}catch (Exception e) {}
		sql += ", '"+undotted+"'";
		String transliterated = LexiconUtils.getTransliteration(undotted);
		try {
			transliterated = URLEncoder.encode(transliterated, Content.ADD_ENCODING);			
		}catch (Exception e) {} 
		sql += ", '"+transliterated+"'";
		String dotted = getDotted(); 
		try {
			dotted = URLEncoder.encode(dotted, Content.ADD_ENCODING);
 
		}catch (Exception e) {}
		sql += ", '"+dotted+"'";
		sql += ", '"+ getRegister() +"'";
		sql += ", '"+ getSpelling() +"'";
		sql += ", '"+getPersonGenderNumber()+"'";
		sql += ", '"+getAction()+"')";
		int feedback = execute(sql);
		id = getCurrentID(getTableName(), getIDName());  
		return id;
	}
	/**
	*Updates the current record in the DB, so it would resemble the current object state.
	*The method uses ResultSet.updateRow method in order to implement the generic update process.
	*The method finds the record of the current object, generates the meta data (the names and types of the columns)
	*, Runs on the columns and updateing each one, according with the column type. After these stages, the
	*method calls <code>ResultSet.updateRow</code> in order to execute the update in the DB.
	*@see   #info
	*@see   ResultSet#updateRow
	*@see   #openRS
	*@return	The number of rows that were affected from the action. If 0, then nothing happened
	*/
	public int update() {
		String sql = "UPDATE "+ getTableName() + " SET ";
		String undotted = getUndotted(); 
		try {
			undotted = URLEncoder.encode(undotted, Content.UPDATE_ENCODING);
		}catch (Exception e) {}
		sql += " undotted='"+ undotted +"'";
		String transliterated = LexiconUtils.getTransliteration(undotted);
		try {
			transliterated = URLEncoder.encode(transliterated, Content.UPDATE_ENCODING);			
		}catch (Exception e) {} 
		sql += ", transliterated='"+ transliterated +"'";
		String dotted = getDotted();
		try {
			dotted = URLEncoder.encode(dotted, Content.UPDATE_ENCODING); 
		}catch (Exception e) {}
		sql += ", dotted='"+ dotted +"'";
		sql += ", register='"+ getRegister() +"'";
		sql += ", spelling='"+ getSpelling() +"'";
		sql += ", personGenderNumber='"+getPersonGenderNumber()+"'";
		sql += ", action='"+getAction()+"' WHERE aid="+getID();
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
		setValue(getString("value"));
		setPersonGenderNumber(getString("personGenderNumber"));
		setRegister(getString("register"));
		setSpelling(getString("spelling"));
		setTransliterated(getString("transliterated"));
	}
    /**
     * Gets the value of the value property.
     * 
     * @return
     *     possible object is
     *     {@link java.lang.String}
     */
    public java.lang.String getValue() { return content.getValue(); }

    /**
     * Sets the value of the value property.
     * 
     * @param value
     *     allowed object is
     *     {@link java.lang.String}
     */
    public void setValue(java.lang.String value) { content.setValue(value); }

    /**
     * Gets the value of the personGenderNumber property.
     * 
     * @return
     *     possible object is
     *     {@link java.lang.String}
     */
    public java.lang.String getPersonGenderNumber() { return content.getPersonGenderNumber(); }

    /**
     * Sets the value of the personGenderNumber property.
     * 
     * @param value
     *     allowed object is
     *     {@link java.lang.String}
     */
    public void setPersonGenderNumber(java.lang.String value) { content.setPersonGenderNumber(value); }

    /**
     * Gets the value of the undotted property.
     * 
     * @return
     *     possible object is
     *     {@link java.lang.String}
     */
    public java.lang.String getUndotted() { 
    	if (content.getUndotted() == null) {
    		return "";
    	}
    	return content.getUndotted(); }

    /**
     * Sets the value of the undotted property.
     * 
     * @param value
     *     allowed object is
     *     {@link java.lang.String}
     */
    public void setUndotted(java.lang.String value) { content.setUndotted(value); }

    /**
     * Gets the value of the dotted property.
     * 
     * @return
     *     possible object is
     *     {@link java.lang.String}
     */
    public java.lang.String getDotted() { 
    	if (content.getDotted() == null) {
    		return "";
    	}
    	return content.getDotted(); }

    /**
     * Sets the value of the dotted property.
     * 
     * @param value
     *     allowed object is
     *     {@link java.lang.String}
     */
    public void setDotted(java.lang.String value) { content.setDotted(value); }

    /**
     * Gets the value of the script property.
     * 
     * @return
     *     possible object is
     *     {@link java.lang.String}
     */
    public java.lang.String getRegister() { 
    	return content.getRegister(); }

    /**
     * Sets the value of the script property.
     * 
     * @param value
     *     allowed object is
     *     {@link java.lang.String}
     */
    public void setRegister(java.lang.String value) { content.setRegister(value); }
    
    /**
     * Gets the value of the script property.
     * 
     * @return
     *     possible object is
     *     {@link java.lang.String}
     */
    public java.lang.String getSpelling() { 
    	return content.getSpelling(); }

    /**
     * Sets the value of the script property.
     * 
     * @param value
     *     allowed object is
     *     {@link java.lang.String}
     */
    public void setSpelling(java.lang.String value) { content.setSpelling(value); }


    /**
     * Gets the value of the transliterated property.
     * 
     * @return
     *     possible object is
     *     {@link java.lang.String}
     */
    public java.lang.String getTransliterated() { 
    	if (content.getTransliterated() == null) {
    		return "";
    	}
    	return content.getTransliterated(); }

    /**
     * Sets the value of the transliterated property.
     * 
     * @param value
     *     allowed object is
     *     {@link java.lang.String}
     */
    public void setTransliterated(java.lang.String value) { content.setTransliterated(value); }

}
