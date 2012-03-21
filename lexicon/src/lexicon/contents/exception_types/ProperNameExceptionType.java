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
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class ProperNameExceptionType extends Content implements lexicon.jaxb.ProperNameExceptionType{
	protected lexicon.jaxb.ProperNameExceptionType content; 
	
	public ProperNameExceptionType(lexicon.jaxb.ProperNameExceptionType content) {
		this.content = content;
		TABLE = "propername_exception_type";
		IDNAME = "aid"; 
	}
	public ProperNameExceptionType() {
		content = new lexicon.jaxb.impl.ProperNameExceptionTypeImpl();
		TABLE = "propername_exception_type";
		IDNAME = "aid"; 
	}
	public lexicon.jaxb.impl.ProperNameExceptionTypeImpl getImpl() {
		return (lexicon.jaxb.impl.ProperNameExceptionTypeImpl)content;
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
		sql += ", '"+getGender()+"'";
		sql += ", '"+ getNumber() +"'";
		sql += ", '"+ getType() +"'";
		sql += ", '"+ getDefiniteness() +"'";
		sql += ", '"+ getDirection() +"'";
		sql += ", '"+ getAction()+"')";
		//int feedback = execute(sql);
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
		sql += ", gender='"+getGender()+"'";
		sql += ", number='"+ getNumber() +"'";
		sql += ", type='"+ getType() +"'";
		sql += ", definiteness='"+ getDefiniteness() +"'";
		sql += ", direction='"+ getDirection() +"'";
		sql += ", action='"+getAction()+"' WHERE aid="+getID();
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
		setTransliterated(getString("transliterated"));
		setGender(getString("gender"));
		setType(getString("type"));
		setNumber(getString("number"));
		setDefiniteness(getString("definiteness"));
		setDirection(getString("direction"));
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
    public java.lang.String getRegister() {   return content.getRegister(); }

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
    public java.lang.String getSpelling() {   return content.getSpelling(); }

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

    /**
	 * Gets the value of the type property.
	 * 
	 * @return
	 *     possible object is
	 *     {@link java.lang.String}
	 */
	public java.lang.String getType() {
		return content.getType();
	}
	
	/**
	 * Sets the value of the type property.
	 * 
	 * @param value
	 *     allowed object is
	 *     {@link java.lang.String}
	 */
	public void setType(java.lang.String value) {
		content.setType(value);
	}
	
	/**
	 * Gets the value of the direction property.
	 * 
	 * @return
	 *     possible object is
	 *     {@link java.lang.String}
	 */
	public java.lang.String getDirection() {
		return content.getDirection();
	}
	
	/**
	 * Sets the value of the direction property.
	 * 
	 * @param value
	 *     allowed object is
	 *     {@link java.lang.String}
	 */
	public void setDirection(java.lang.String value) {
		content.setDirection(value);
	}
	/**
	 * Gets the value of the gender property.
	 * 
	 * @return
	 *     possible object is
	 *     {@link java.lang.String}
	 */
	public java.lang.String getGender() {
		return content.getGender();
	}
	
	/**
	 * Sets the value of the gender property.
	 * 
	 * @param value
	 *     allowed object is
	 *     {@link java.lang.String}
	 */
	public void setGender(java.lang.String value) {
		content.setGender(value);
	}
	
	/**
	 * Gets the value of the number property.
	 * 
	 * @return
	 *     possible object is
	 *     {@link java.lang.String}
	 */
	public java.lang.String getNumber() {
		return content.getNumber();
	}
	
	/**
	 * Sets the value of the number property.
	 * 
	 * @param value
	 *     allowed object is
	 *     {@link java.lang.String}
	 */
	public void setNumber(java.lang.String value) {
		content.setNumber(value);
	}
	
	/**
	 * Gets the value of the definiteness property.
	 * 
	 * @return
	 *     possible object is
	 *     {@link java.lang.String}
	 */
	public java.lang.String getDefiniteness() {
		return content.getDefiniteness();
	}
	
	/**
	 * Sets the value of the definiteness property.
	 * 
	 * @param value
	 *     allowed object is
	 *     {@link java.lang.String}
	 */
	public void setDefiniteness(java.lang.String value) {
		content.setDefiniteness(value);
	}
	    
	///-----------------------------------------------------------------------------------------------------------------------------------
	public String GetExceptionHtml()
	{
		/*
		 * this function is used to create the html for the exception form
		 * it is not the best way to accomplish this but because there was too much code on the jsp file
		 * i had to move some into a java class.
		 */
		String html = null;
		
		String add_sel=null,replace_sel =null ,remove_sel=null;
		if (getAction().equals("add"))  // get the right action to be selected
			add_sel = "SELECTED";
		else if (getAction().equals("replace"))
			replace_sel = "SELECTED";
		else remove_sel = "SELECTED";
		
		html = "<tr><td>פעולה:</td>"
			+ "<td><select name=\'properName_exception_action\'>"
			+ "<option value='add' "+ add_sel +" >הוספה</option>"
			+ "<option value='replace' "+replace_sel +" >החלפה</option>"
			+ "<option value='remove' " +remove_sel +">הסרה</option>"
			+ "</select></td></tr>";
		
		html += "<tr><td>צורה לא מנוקדת:</td>"
			+ "<td><input type='text' name='properName_exception_undotted' size=20 value='"+getUndotted().trim()+"'></td></tr>";
		
		html += "<tr><td>צורת תעתיק:</td>"
			+ "<td>"+getTransliterated() + "</td></tr>";
		
		String formal_sel = null, archaic_sel = null , informal_sel = null;
		if (getRegister().equals("formal"))  // register 
			formal_sel = "SELECTED";
		else if (getRegister().equals("archaic"))
			archaic_sel = "SELECTED";
		else informal_sel = "SELECTED";
		html += "<tr><td>צורה מנוקדת:</td>"
			+ "<td><input type='text' name='properName_exception_dotted' size=20 value='"+getDotted().trim()+"'></td></tr>"
			+ "<tr><td>משלב:</td><td>"
			+ "<select name='properName_exception_register'>"
			+ "<option value='formal' "+ formal_sel +">תקני</option>"
			+ "<option value='archaic' " + archaic_sel + ">ארכאי</option>"
			+ "<option value='informal' " + informal_sel + " >תת-תקני</option>"
			+ "</select></td></tr>";
		
		String standard_sel = null, irregular_sel = null; // spelling
		if (getSpelling().equals("irregular")) // spelling
			irregular_sel = "SELECTED";
		else 
			standard_sel = "SELECTED";
		html += "<tr><td>כתיב:</td>"
			+ "<td><select name='properName_exception_spelling'>"
	        + "<option value='standard' "+ standard_sel +">תקני</option>"
	        + "<option value='irregular' " + irregular_sel +">תת-תקני</option>"
	        + "</select></td></tr>";
		
		
		// GENDER
		String masculine_sel = null, feminine_sel= null , unspecified_gender_sel = null,masculine_and_feminine_sel =null;
		String gender = getGender();
		if (gender.equals("masculine"))
			{masculine_sel = "SELECTED";}
		else if (gender.equals("feminine"))
			{feminine_sel = "SELECTED";}
		else if (gender.equals("masculine and feminine"))
			{masculine_and_feminine_sel = "SELECTED";}
		else
		{unspecified_gender_sel = "SELECTED";}
		html +="<tr><td>מין:</td>"
			+ "<td><select name='properName_exception_gender'>"
            + "<option value='masculine' "+masculine_sel+">זכר</option>"
            + "<option value='feminine' "+feminine_sel+">נקבה</option>"
            + "<option value='masculine and feminine' "+masculine_and_feminine_sel+">גם זכר וגם נקבה</option>"
            + "<option value='unspecified' "+unspecified_gender_sel +">לא ידוע</option>"
            + "</select></td></tr>";
		
		// NUMBER
		String singular_sel= null, plural_sel=null,unspecified_number_sel =null,dual_sel=null,dual_and_plural_sel =null,singular_and_plural_sel=null;
		String number = getNumber();
		if (number.equals("singular"))
			{singular_sel = "SELECTED";}
		else if (number.equals("plural"))
			{plural_sel = "SELECTED";}
		else if (number.equals("dual"))
		{dual_sel = "SELECTED";}
		else if (number.equals("dual and plural"))
		{dual_and_plural_sel = "SELECTED";}
		else if (number.equals("singular and plural"))
		{singular_and_plural_sel = "SELECTED";}
		else
		{unspecified_number_sel = "SELECTED";}
		html +="<tr><td>מספר:</td>" +
			"<td><select name='properName_exception_number'>" +
			"<option value='singular' "+singular_sel+">יחיד</option>" +
			"<option value='plural' "+plural_sel+">רבים</option>" +
			"<option value='dual' "+dual_sel+">זוגי</option>" +
			"<option value='dual and plural' "+dual_and_plural_sel+">גם זוגי וגם רבים</option>" +
			"<option value='singular and plural' "+singular_and_plural_sel+">גם יחיד וגם רבים</option>" +
			"<option value='unspecified' "+unspecified_number_sel+">לא ידוע</option>" +
			"</select></td></tr>";
		
		// TYPE
		String type_person_sel= null,type_location_sel= null,type_town_sel= null,type_country_sel= null,type_language_sel= null;
		String type_symbol_sel= null,type_coin_sel= null,type_organization_sel= null;
		String type_product_sel= null,type_dateTime_sel= null,type_other_sel= null,type_unspecified_sel= null;
		if (getType().equals("person"))
			{type_person_sel="SELECTED";}
		else if (getType().equals("location"))
			{type_location_sel="SELECTED";}
		else if (getType().equals("town"))
			{type_town_sel="SELECTED";}
		else if (getType().equals("country"))
			{type_country_sel="SELECTED";}
		else if (getType().equals("language"))
			{type_language_sel="SELECTED";}
		else if (getType().equals("symbol"))
			{type_symbol_sel="SELECTED";}
		else if (getType().equals("coin"))
			{type_coin_sel="SELECTED";}
		else if (getType().equals("organization"))
			{type_organization_sel="SELECTED";}
		else if (getType().equals("product"))
			{type_product_sel="SELECTED";}
		else if (getType().equals("dateTime"))
			{type_dateTime_sel="SELECTED";}
		else if (getType().equals("other"))
			{type_other_sel="SELECTED";}
		else 
			{type_unspecified_sel="SELECTED";}
		
		html +="<tr><td>סוג:</td>" +
			"<td><select name='properName_exception_type'>" +
			"<option value='person' "+type_person_sel+">שם של אדם</option>" +
			"<option value='location' "+type_location_sel+">שם של מקום</option>" +
			"<option value='town' "+type_town_sel+">שם של יישוב</option>" +
			"<option value='country' "+type_country_sel+">שם של ארץ</option>" +
			"<option value='language' "+type_language_sel+">שם של שפה</option>" +
			"<option value='symbol' "+type_symbol_sel+">שם של אות או תו</option>" +
			"<!--option value='coin' "+type_coin_sel+">שם של מטבע</option-->" +
			"<option value='organization' "+type_organization_sel+">שם של ארגון</option>" +
			"<option value='product' "+type_product_sel+" >שם של מוצר</option>" +
			"<option value='dateTime' "+type_dateTime_sel+">תאריך</option>" +
			"<option value='other' "+type_other_sel+">אף אחד מהאפשרויות שלעיל</option>" +
			"<option value='unspecified' "+type_unspecified_sel+">הקטגוריה אינה ידועה</option>" +
			"</select></td></tr>";
		
		// definiteness
		String definiteness_optional_sel=null,definiteness_prohibited_sel=null,definiteness_required_sel=null;
		if (getDefiniteness().equals("optional"))
			{definiteness_optional_sel="SELECTED";}
		else if (getDefiniteness().equals("prohibited"))
			{definiteness_prohibited_sel="SELECTED";}
		else
			{definiteness_required_sel="SELECTED";}
		
		html +="<tr><td>האם מקבל ה' הידיעה?</td>" +
			"<td><select name='properName_exception_definiteness'>" +
			"<option value='optional' "+definiteness_optional_sel+">מותר</option>" +
			"<option value='prohibited' "+definiteness_prohibited_sel+">אסור</option>" +
			"<option value='required' "+definiteness_required_sel+">חובה</option>" +
			"</select></td></tr>";
		
		//DIRECTION
		String direction_unspecified_sel=null,direction_true_sel=null,direction_false_sel=null;
		if (getDirection().equals("unspecified"))
			{direction_unspecified_sel="SELECTED";}
		else if (getDirection().equals("true"))
			{direction_true_sel="SELECTED";}
		else
			{direction_false_sel="SELECTED";}
		
		html +="<tr><td>האם מכיל ה' המגמה?</td>" +
			"<td><select name='properName_exception_direction'>" +
			"<option value='unspecified' "+direction_unspecified_sel+">לא מוגדר</option>" +
			"<option value='true' "+direction_true_sel+">כן</option>" +
			"<option value='false' "+direction_false_sel+">לא</option>" +
			"</select></td></tr>";
		return html;
	}
}
