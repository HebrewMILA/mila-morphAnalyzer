//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v1.0.4-b16-fcs 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2005.02.17 at 12:10:17 GMT+02:00 
//

package lexicon.contents.pos;

import lexicon.contents.Content;

import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import lexicon.contents.exception_types.NounExceptionType;
;
/**
 * ���� �� ����� ��� ������ ���� "�� ����".
 * ���� ���� ����� ��� ������ ����.
 * ���� ����� ��� ���� ��� ���� ����.
 * ����� ����� ���� ����� ���� ���� (���� �/�/��) ��� ���� ���� ��� ����� ����� ���� ���� ������.
 * ��� ������ �� ����� ������ ������ ����.
 * ����� ����� ������� �������.
 * ����� ��� �"� ���� '��'.
 * ����� ���� �"� ���� '��''.
 * ������ (add) ������ ����� ����� ������ �� �����, ����� ����� (����, ����) ���'.
 * ����� (replace) ����� ������ ������ ������� �"� ���� ����� ����. ���� ����� �� ������ ����� ���� ������ �� �� �"� ����� � inflectConstruct, inflectPossessive �- base, ��� ������ ������.
 * �����:
 * ======
 * �� ���� (gender) ��� ���� (feminine), ��� ��� ������ ������ �- feminine (���� ����) ���� �� ����� ����� �- feminine (���� ����) ���� ��� ������ ��� (gender) ��� ���� (feminine).
 * �����:
 * ======
 * �� ������ (number) ��� ���� (plural), ��� ��� ������ ������ �- plural (���� �����) ���� �� ����� ����� �-  plural (���� �����) ���� ��� ������ ����� (number) ��� ���� (plural).
 * �����:
 * ======
 * root ������ ��"� �- 3 ������. ���� ������ ��� 2 ������ ��� ���� ��� 4 �- 5. ������ ������� ���� ���� ����� ����� ������ ����/���� ����� -- ���� �� "����� ������ �����" ����� �� ���� ����� "����� �������" ��� 6.
 * �����:
 * ======
 * �� ������ ����� ���� (root) ��� ����� �� ����� ���� (pattern). �"� ������� root �- pattern ������ ������ ��� �� �� ������ ���. ��� ��� ��� ����� �� ������ �� ���� ����� ��� ��� ������ ����� �� ����� ����� ���� ���� ��� ���� ���� ���� ��� ��� ����. ����� ����, ����� ��� ���� ����� ���� ���� ������ ���� ����� ���� ���� ������. ���� ���� �� �� ����� �� ���� ����� "����� �������" ��� 2 ���� 2.3 �"� 28-32.
 * �����:
 * ======
 * inflectionBase ����� �� �� ��� ���� ������ ����. inflectionBase �� ����� ���.
 * 
 * Java content class for AdjectiveLexiconType complex type.
 * <p>The following schema fragment specifies the expected content contained within this java content object. (defined at file:/C:/hebrew_lexicon.xsd line 260)
 * <p>
 * <pre>
 * &lt;complexType name="AdjectiveLexiconType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;choice maxOccurs="unbounded" minOccurs="0">
 *           &lt;element name="add" type="{}NounExceptionType" maxOccurs="unbounded" minOccurs="0"/>
 *           &lt;element name="replace" type="{}NounExceptionType" maxOccurs="unbounded" minOccurs="0"/>
 *           &lt;element name="remove" type="{}NounExceptionType" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;/choice>
 *       &lt;/sequence>
 *       &lt;attribute name="acronym" type="{}TriStateType" default="unspecified" />
 *       &lt;attribute name="feminine" type="{}FeminineType" default="h" />
 *       &lt;attribute name="gender" type="{}GenderType" default="masculine" />
 *       &lt;attribute name="inflectionBase" type="{http://www.w3.org/2001/XMLSchema}token" default="" />
 *       &lt;attribute name="inflectionPattern" type="{http://www.w3.org/2001/XMLSchema}token" default="" />
 *       &lt;attribute name="ipSource" type="{http://www.w3.org/2001/XMLSchema}token" default="" />
 *       &lt;attribute name="number" type="{}NumberType" default="singular" />
 *       &lt;attribute name="pattern" type="{http://www.w3.org/2001/XMLSchema}token" default="" />
 *       &lt;attribute name="plural" type="{}PluralType" default="im" />
 *       &lt;attribute name="root" type="{http://www.w3.org/2001/XMLSchema}token" default="" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 */
public class AdjectiveLexiconType  extends Content implements lexicon.jaxb.AdjectiveLexiconType{
	lexicon.jaxb.AdjectiveLexiconType content;
	
	public AdjectiveLexiconType(lexicon.jaxb.AdjectiveLexiconType content) {
		this.content = content;
		TABLE = "adjective";
		IDNAME = "id"; 
	}
	public AdjectiveLexiconType() {
		content = new lexicon.jaxb.impl.AdjectiveLexiconTypeImpl();
		TABLE = "adjective";
		IDNAME = "id"; 
	}
	public lexicon.jaxb.impl.AdjectiveLexiconTypeImpl getImpl() {
		return (lexicon.jaxb.impl.AdjectiveLexiconTypeImpl)content;
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
		sql += id;
		sql += ", '"+getGender()+"'";
		sql += ", '"+ getNumber() +"'";
		sql += ", '"+ getFeminine() +"'";
		sql += ", '"+ getPlural() +"'";
		String root = getRoot();
		if (root == null) {
			root = "";
		} 
		try {
			root = URLEncoder.encode(root, Content.ADD_ENCODING); 
		}catch (Exception e) {}
		sql += ", '"+ root +"'";
		String pattern = getPattern();
		if (pattern == null) {
			pattern = "";
		} 
		try {
			pattern = URLEncoder.encode(pattern, Content.ADD_ENCODING); 
		}catch (Exception e) {}
		sql += ", '"+pattern+"'";
		sql += ", '"+ getInflectionPattern() +"'";
		String ipSource = getIpSource();
		//ipSource = ipSource.replaceAll("'", "\\\\'");
		sql += ", '"+ ipSource +"'";
		sql += ", '"+ getAcronym() +"'";
		sql += ", "+ (isForeign()?1:0);
		String inflectionBase = getInflectionBase();
		if (inflectionBase == null) {
			inflectionBase = "";
		} 
		try {
			inflectionBase = URLEncoder.encode(inflectionBase, Content.ADD_ENCODING); 
		}catch (Exception e) {}
		sql += ", '"+ inflectionBase +"'";
		sql += ", "+(isInflectConstructS()?1:0);
		sql += ", "+(isInflectConstructP()?1:0);
		String dottedPlural = getDottedPlural();
		if (dottedPlural == null) {
			dottedPlural = "";
		} 
		try {
			dottedPlural = URLEncoder.encode(dottedPlural, Content.ADD_ENCODING); 
		}catch (Exception e) {}
		sql += ", '"+dottedPlural+"'";
		sql += ")";
		int feedback = execute(sql);
		id = getCurrentID(getTableName(), getIDName());  
		addActions(id);
		return feedback;
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
		String sql = "UPDATE "+ getTableName() + " SET"; 
		sql += " gender='"+getGender()+"'";
		sql += ", number='"+ getNumber() +"'";
		sql += ", feminine='"+ getFeminine() +"'";
		sql += ", plural='"+ getPlural() +"'";
		String root = getRoot();
		if (root == null) {
			root = "";
		} 
		try {
			root = URLEncoder.encode(root, Content.UPDATE_ENCODING); 
		}catch (Exception e) {}
		sql += ", root='"+ root +"'";
		String pattern = getPattern();
		if (pattern == null) {
			pattern = "";
		} 
		try {
			pattern = URLEncoder.encode(pattern, Content.UPDATE_ENCODING); 
		}catch (Exception e) {}
		sql += ", pattern='"+pattern+"'"; 
		sql += ", inflectionPattern='"+ getInflectionPattern() +"'";
		String ipSource = getIpSource();
		//ipSource = ipSource.replaceAll("'", "\\\\'");
		sql += ", ipSource='"+ ipSource +"'";
		sql += ", acronym='"+ getAcronym() +"'";
		sql += ", hebForeign="+(isForeign()?1:0);
		sql += ", inflectConstructS="+(isInflectConstructS()?1:0);
		sql += ", inflectConstructP="+(isInflectConstructP()?1:0);
		String dottedPlural = getDottedPlural();
		if (dottedPlural == null) {
			dottedPlural = "";
		} 
		try {
			dottedPlural = URLEncoder.encode(dottedPlural, Content.UPDATE_ENCODING); 
		}catch (Exception e) {}
		sql += ", dottedPlural='"+ dottedPlural +"'";
		String inflectionBase = getInflectionBase();
		if (inflectionBase == null) {
			inflectionBase = "";
		}
		try {
			inflectionBase = URLEncoder.encode(inflectionBase, Content.UPDATE_ENCODING);			
		}catch (Exception e) {} 
		sql += ", inflectionBase='"+ getInflectionBase() ;
		sql += "' WHERE id="+getID();
		int feedback = execute(sql); 
		//feedback += updateActions();
		return feedback;
	}
	public int remove() {
		removeActions(id);
		int result = super.remove();
		return result;
	}
	protected int addActions(int id) {
		int result = 0;
		for (int i=0; i< getAddOrReplaceOrRemove().size(); i++) {
			NounExceptionType exceptionType = new NounExceptionType((lexicon.jaxb.impl.NounExceptionTypeImpl)getAddOrReplaceOrRemove().get(i));
			exceptionType.setAction(getAction(getAddOrReplaceOrRemove().get(i)));
			result += exceptionType.add(id);
		}
		return result;
	}
	protected int updateActions() {
		int result = 0;
		for (int i=0; i< getAddOrReplaceOrRemove().size(); i++) {
			NounExceptionType exceptionType = new NounExceptionType((lexicon.jaxb.impl.NounExceptionTypeImpl)getAddOrReplaceOrRemove().get(i));
			exceptionType.setAction(getAction(getAddOrReplaceOrRemove().get(i)));
			result += exceptionType.update();
		}
		return result;
	} 
	protected int removeActions(int id) {
		return removeContents("noun_exception_type", "id", "aid", id);
	}
	protected String getAction(Object obj) {
		String name = obj.getClass().getName();
		if (name.indexOf("Add") >=0) {
			return "add";
		}
		if (name.indexOf("Replace") >=0) {
			return "replace";
		}
		if (name.indexOf("Remove") >=0) {
			return "remove";
		}
		return "";
	}
	public void load() {
		List actions = getActions();
		getAddOrReplaceOrRemove().clear();
		getAddOrReplaceOrRemove().addAll(actions);
		setGender(getString("gender"));
		setNumber(getString("number"));
		setFeminine(getString("feminine"));
		setPlural(getString("plural"));
		setPattern(getString("pattern"));
		setRoot(getString("root"));
		setDottedPlural(getString("dottedPlural"));
		setInflectionPattern(getString("inflectionPattern"));
		setIpSource(getString("ipSource"));
		setAcronym(getString("acronym"));
		setForeign(getInt("hebForeign")==1);
		setInflectionBase(getString("inflectionBase"));
		setInflectConstructS(getInt("inflectConstructS")==1);
		setInflectConstructP(getInt("inflectConstructP")==1);
	}
	public java.util.List getActions() {
		List actions = getContents("noun_exception_type", "id", id);
		ArrayList result = new ArrayList();
		for (int i=0; i< actions.size(); i++) {
			Content content = (Content)actions.get(i);
			if (content.getString("action").equals("add")) {
				Add action = new Add();
				action.open(content.getInt("aid"));
				result.add(action.getActionImpl());
			}
			if (content.getString("action").equals("remove")) {
				Remove action = new Remove();
				action.open(content.getInt("aid"));
				result.add(action.getActionImpl());
			}
			if (content.getString("action").equals("replace")) {
				Replace action = new Replace();
				action.open(content.getInt("aid"));
				result.add(action.getActionImpl());
			}
		}
		return result;
	}	
    /**
     * Gets the value of the feminine property.
     * 
     * @return
     *     possible object is
     *     {@link java.lang.String}
     */
	public java.lang.String getFeminine() {
    	return content.getFeminine();
    }

    /**
     * Sets the value of the feminine property.
     * 
     * @param value
     *     allowed object is
     *     {@link java.lang.String}
     */
	public void setFeminine(java.lang.String value) {
		content.setFeminine(value);
	}
	
	 /**
     * Gets the value of the feminine property.
     * 
     * @return
     *     possible object is
     *     {@link java.lang.String}
     */
	public java.lang.String getDottedPlural() {
    	return content.getDottedPlural();
    }

    /**
     * Sets the value of the feminine property.
     * 
     * @param value
     *     allowed object is
     *     {@link java.lang.String}
     */
	public void setDottedPlural(java.lang.String value) {
		content.setDottedPlural(value);
	}

    /**
     * Gets the value of the ipSource property.
     * 
     * @return
     *     possible object is
     *     {@link java.lang.String}
     */
	public java.lang.String getIpSource(){
    	return content.getIpSource();
    }

    /**
     * Sets the value of the ipSource property.
     * 
     * @param value
     *     allowed object is
     *     {@link java.lang.String}
     */
	public void setIpSource(java.lang.String value) {
		content.setIpSource(value);
	}

    /**
     * Gets the value of the inflectionPattern property.
     * 
     * @return
     *     possible object is
     *     {@link java.lang.String}
     */
	public java.lang.String getInflectionPattern() {
    	return content.getInflectionPattern();
    }

    /**
     * Sets the value of the inflectionPattern property.
     * 
     * @param value
     *     allowed object is
     *     {@link java.lang.String}
     */
	public void setInflectionPattern(java.lang.String value) {
		content.setInflectionPattern(value);
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
     * Gets the value of the plural property.
     * 
     * @return
     *     possible object is
     *     {@link java.lang.String}
     */
	public java.lang.String getPlural() {
    	return content.getPlural();
    }

    /**
     * Sets the value of the plural property.
     * 
     * @param value
     *     allowed object is
     *     {@link java.lang.String}
     */
	public void setPlural(java.lang.String value) {
		content.setPlural(value);
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
     * Gets the value of the root property.
     * 
     * @return
     *     possible object is
     *     {@link java.lang.String}
     */
	public java.lang.String getRoot() {
    	return content.getRoot();
    }

    /**
     * Sets the value of the root property.
     * 
     * @param value
     *     allowed object is
     *     {@link java.lang.String}
     */
	public void setRoot(java.lang.String value) {
		content.setRoot(value);
	}

    /**
     * Gets the value of the inflectionBase property.
     * 
     * @return
     *     possible object is
     *     {@link java.lang.String}
     */
	public java.lang.String getInflectionBase() {
    	return content.getInflectionBase();
    }

    /**
     * Sets the value of the inflectionBase property.
     * 
     * @param value
     *     allowed object is
     *     {@link java.lang.String}
     */
	public void setInflectionBase(java.lang.String value) {
		content.setInflectionBase(value);
	}

    /**
     * Gets the value of the acronym property.
     * 
     * @return
     *     possible object is
     *     {@link java.lang.String}
     */
	public java.lang.String getAcronym() {
    	return content.getAcronym();
    }

    /**
     * Sets the value of the acronym property.
     * 
     * @param value
     *     allowed object is
     *     {@link java.lang.String}
     */
	public void setAcronym(java.lang.String value) {
		content.setAcronym(value);
	}
	

	/**
	 * Sets the value of the acronym property.
	 * 
	 * @param value
	 *     allowed object is
	 *     {@link java.lang.String}
	 */
	public void setForeign(boolean value) { content.setForeign(value); }
	
	/**
	 * Gets the value of the inflectionBase property.
	 * 
	 * @return
	 *     possible object is
	 *     {@link java.lang.String}
	 */
	public boolean isForeign() { return content.isForeign(); }
	

    /**
     * Gets the value of the AddOrReplaceOrRemove property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the AddOrReplaceOrRemove property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getAddOrReplaceOrRemove().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link lexicon.jaxb.AdjectiveLexiconType.Remove}
     * {@link lexicon.jaxb.AdjectiveLexiconType.Add}
     * {@link lexicon.jaxb.AdjectiveLexiconType.Replace}
     * 
     */
	public java.util.List getAddOrReplaceOrRemove() {
    	return content.getAddOrReplaceOrRemove();
    }

    /**
     * Gets the value of the pattern property.
     * 
     * @return
     *     possible object is
     *     {@link java.lang.String}
     */
	public java.lang.String getPattern() {
    	return content.getPattern();
    }

    /**
     * Sets the value of the pattern property.
     * 
     * @param value
     *     allowed object is
     *     {@link java.lang.String}
     */
	public void setPattern(java.lang.String value) {
		content.setPattern(value);
	}
	
	 /**
     * Gets the value of the inflectConstruct property.
     * 
     */
    public boolean isInflectConstructS() { return content.isInflectConstructS(); }

    /**
     * Sets the value of the inflectConstruct property.
     * 
     */
    public void setInflectConstructS(boolean value) { content.setInflectConstructS(value); }

    /**
     * Gets the value of the inflectConstruct property.
     * 
     */
    public boolean isInflectConstructP() { return content.isInflectConstructP(); }

    /**
     * Sets the value of the inflectConstruct property.
     * 
     */
    public void setInflectConstructP(boolean value) { content.setInflectConstructP(value); }
    
    


    /**
     * ���� �� ���� ������ ����� ������.
     * ���� -- ���� ����, ����� ����, �������� ������ ������ �������.
     * ������ ����, �� ����� ���� ��� ��� �������� ����� ���� ������ (����, ���� ��� ���� �����, �� ���� �����, �� ���� �����), ��� ����� �� ����� ������. ��� ���� ������ ��� �� ������ ������� inflectPossessive="false" �-inflectConstruct="false".
     * 
     * Java content class for add element declaration.
     * <p>The following schema fragment specifies the expected content contained within this java content object. (defined at file:/C:/files/hebrew_lexicon_new.xsd line 231)
     * <p>
     * <pre>
     * &lt;element name="add" type="{}NounExceptionType"/>
     * </pre>
     * 
     */
    public class Add extends lexicon.contents.exception_types.NounExceptionType implements lexicon.jaxb.NounLexiconType.Add {
    	public Add() {
    		super ();
    		content = new lexicon.jaxb.impl.AdjectiveLexiconTypeImpl.AddImpl();
    	}
    	public Add(lexicon.jaxb.NounExceptionType content) { 
    		super (content);
    	}
    	public lexicon.jaxb.impl.AdjectiveLexiconTypeImpl.AddImpl getActionImpl() {
    		return (lexicon.jaxb.impl.AdjectiveLexiconTypeImpl.AddImpl)content;
    	}
    }


    /**
     * ���� �� ���� ����� ����� ������ ����� �������, ���� ���� ����� ����� ����� ����� ��������, ��� ����� (���� ������ �� ������ ����, �� ��� ����� ���).
     * ������ ��� ����� ��� ����� ��� ���� ��� ����� ���� ������, �� ����� �� �� ����� ����� �� ������ ��� �� ���, ����� �����, �� ����� �� ������.
     * ����� ����, �� ������ ����� ��� �- add ��- replace ��� ������ �- remove, ��� ���� �� ������ ���� ����� ������ ���� ����� ��������.
     * 
     * Java content class for remove element declaration.
     * <p>The following schema fragment specifies the expected content contained within this java content object. (defined at file:/C:/files/hebrew_lexicon_new.xsd line 249)
     * <p>
     * <pre>
     * &lt;element name="remove" type="{}NounExceptionType"/>
     * </pre>
     * 
     */
    public class Remove extends NounExceptionType implements lexicon.jaxb.NounLexiconType.Remove {
    	public Remove(lexicon.jaxb.NounExceptionType content) {
    		super (content);
    	}
    	public Remove() {
    		super ();
    		content = new lexicon.jaxb.impl.AdjectiveLexiconTypeImpl.RemoveImpl();
    	}
    	public lexicon.jaxb.impl.AdjectiveLexiconTypeImpl.RemoveImpl getActionImpl() {
    		return (lexicon.jaxb.impl.AdjectiveLexiconTypeImpl.RemoveImpl)content;
    	}
    }


    /**
     * ���� �� ���� ������ ����� ������ ������.
     * ���� -- ����� ���� ����, ������ ��'.
     * ������ ����, �� ������ ���� ��� ��� �������� ����� ���� ������ (����, ���� ��� ���� �����, �� ���� �����, �� ���� �����), ��� ����� �� ����� ������, ��� ������ �� ������ ������� ������. ��� ���� ������ ��� �� ������ ������� inflectPossessive="false" �-inflectConstruct="false".
     * 
     * Java content class for replace element declaration.
     * <p>The following schema fragment specifies the expected content contained within this java content object. (defined at file:/C:/files/hebrew_lexicon_new.xsd line 240)
     * <p>
     * <pre>
     * &lt;element name="replace" type="{}NounExceptionType"/>
     * </pre>
     * 
     */
    public class Replace extends NounExceptionType implements lexicon.jaxb.NounLexiconType.Replace{
    	public Replace(lexicon.jaxb.NounExceptionType content) {
    		super (content);
    	}
    	public Replace() {
    		super ();
    		content = new lexicon.jaxb.impl.AdjectiveLexiconTypeImpl.ReplaceImpl();
    	}
    	public lexicon.jaxb.impl.AdjectiveLexiconTypeImpl.ReplaceImpl getActionImpl() {
    		return (lexicon.jaxb.impl.AdjectiveLexiconTypeImpl.ReplaceImpl)content;
    	}
    }




}
