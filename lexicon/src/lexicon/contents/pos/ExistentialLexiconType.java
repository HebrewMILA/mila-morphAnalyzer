/*
 * Created on 22/02/2006
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package lexicon.contents.pos;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import lexicon.contents.Content;
import lexicon.contents.exception_types.ExistentialExceptionType;


/**
 * @author daliabo
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class ExistentialLexiconType extends Content implements lexicon.jaxb.ExistentialLexiconType{
	
	lexicon.jaxb.ExistentialLexiconType content;
	
	public ExistentialLexiconType(lexicon.jaxb.ExistentialLexiconType content) {
		this.content = content;
		TABLE = "existential";
		IDNAME = "id"; 
	}
	
	public ExistentialLexiconType() {
		content = new lexicon.jaxb.impl.ExistentialLexiconTypeImpl();
		TABLE = "existential";
		IDNAME = "id"; 
	}
	public lexicon.jaxb.impl.ExistentialLexiconTypeImpl getImpl() {
		return (lexicon.jaxb.impl.ExistentialLexiconTypeImpl)content;
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
		String root = getRoot();
		if (root == null) {
			root = "";
		} 
		try {
			root = URLEncoder.encode(root, Content.ADD_ENCODING); 
		}catch (Exception e) {}
		sql += ", '"+ root +"'";
		sql += ", '"+ getTense() +"'";
		sql += ", '"+ getPgn() +"'";
		sql += ", '"+ getPolarity() +"'";
		sql += ", '"+ getDefiniteness() +"')";
		int feedback = execute(sql);
		id = getCurrentID(getTableName(), getIDName()); 
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
		sql += ", polarity='"+ getPolarity() +"'";
		String root = getRoot();
		if (root == null) {
			root = "";
		} 
		try {
			root = URLEncoder.encode(root, Content.UPDATE_ENCODING); 
		}catch (Exception e) {}
		sql += ", root='"+ root +"'";
		sql += ", tense='"+ getTense() +"'";
		sql += ", pgn='"+ getPgn() +"'";
		sql += ", definiteness='"+ getDefiniteness() +"'";
		sql += " WHERE id="+getID();
		int feedback = execute(sql); 
		return 1;
	}
	public void load() {
		List actions = getActions();
		getAddOrReplaceOrRemove().clear();
		getAddOrReplaceOrRemove().addAll(actions);
		setGender(getString("gender"));
		setTense(getString("tense"));
		setNumber(getString("number"));
		setPgn(getString("pgn"));
		setPolarity(getString("polarity"));
		setRoot(getString("root"));
		setDefiniteness(getString("definiteness"));
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

	public java.util.List getActions() {
		List actions = getContents("existential_exception_type", "id", id);
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
	 * Gets the value of the number property.
	 * 
	 * @return
	 *     possible object is
	 *     {@link java.lang.String}
	 */
	public java.lang.String getNumber() { return content.getNumber(); }
	
	/**
	 * Sets the value of the number property.
	 * 
	 * @param value
	 *     allowed object is
	 *     {@link java.lang.String}
	 */
	public void setNumber(java.lang.String value) { content.setNumber(value); }
	
	/**
	 * Gets the value of the definiteness property.
	 * 
	 * @return
	 *     possible object is
	 *     {@link java.lang.String}
	 */
	public java.lang.String getDefiniteness() { return content.getDefiniteness(); }
	
	/**
	 * Sets the value of the definiteness property.
	 * 
	 * @param value
	 *     allowed object is
	 *     {@link java.lang.String}
	 */
	public void setDefiniteness(java.lang.String value) { content.setDefiniteness(value);}
	
	/**
	 * Gets the value of the gender property.
	 * 
	 * @return
	 *     possible object is
	 *     {@link java.lang.String}
	 */
	public java.lang.String getGender() { return content.getGender(); }
	
	/**
	 * Sets the value of the gender property.
	 * 
	 * @param value
	 *     allowed object is
	 *     {@link java.lang.String}
	 */
	
	public void setGender(java.lang.String value) { content.setGender(value); }
	
	/**
	 * Gets the value of the tense property.
	 * 
	 * @return
	 *     possible object is
	 *     {@link java.lang.String}
	 */
	public java.lang.String getTense() { return content.getTense(); }
	
	/**
	 * Sets the value of the tense property.
	 * 
	 * @param value
	 *     allowed object is
	 *     {@link java.lang.String}
	 */
	public void setTense(java.lang.String value) {
		content.setTense(value); 
	}
	
	 /**
     * Gets the value of the person property.
     * 
     * @return
     *     possible object is
     *     {@link java.lang.String}
     */
	public java.lang.String getPgn() {
    	return content.getPgn();
    }

    /**
     * Sets the value of the person property.
     * 
     * @param value
     *     allowed object is
     *     {@link java.lang.String}
     */
	public void setPgn(java.lang.String value) {
    	content.setPgn(value);
    }

	/* (non-Javadoc)
	 * @see lexicon.jaxb.CopulaLexiconType#getPolarity()
	 */
	public java.lang.String getPolarity() {
		 return content.getPolarity();
	}
	/* (non-Javadoc)
	 * @see lexicon.jaxb.CopulaLexiconType#setPolarity(java.lang.String)
	 */
	public void setPolarity(java.lang.String value) {
		content.setPolarity(value);
		
	}
	
	/**
     * Gets the value of the root property.
     * 
     * @return
     *     possible object is
     *     {@link java.lang.String}
     */
    public java.lang.String getRoot() { 
    	if (content.getRoot() == null) {
			return "";
		}
    	return content.getRoot(); }

    /**
     * Sets the value of the root property.
     * 
     * @param value
     *     allowed object is
     *     {@link java.lang.String}
     */
    public void setRoot(java.lang.String value) { content.setRoot(value); }
    
    public java.util.List getAddOrReplaceOrRemove() {
    	return content.getAddOrReplaceOrRemove();
    }
	
	 public class Add extends ExistentialExceptionType implements lexicon.jaxb.ExistentialLexiconType.Add{
    	
    	public Add() {
    		super();
    		content = new lexicon.jaxb.impl.ExistentialLexiconTypeImpl.AddImpl();
    	}
    	public Add(lexicon.jaxb.ExistentialExceptionType content) {
    		super (content);
    	}
    	public lexicon.jaxb.impl.ExistentialLexiconTypeImpl.AddImpl getActionImpl() {
    		return (lexicon.jaxb.impl.ExistentialLexiconTypeImpl.AddImpl)content;
    	}
    }
	 
	 public class Remove extends ExistentialExceptionType implements lexicon.jaxb.ExistentialLexiconType.Remove  {
    	public Remove(lexicon.jaxb.ExistentialExceptionType content) {
    		super (content);
    	}
    	public Remove() {
    		super();
    		content = new lexicon.jaxb.impl.ExistentialLexiconTypeImpl.RemoveImpl();
    	}
    	public lexicon.jaxb.impl.ExistentialLexiconTypeImpl.RemoveImpl getActionImpl() {
    		return (lexicon.jaxb.impl.ExistentialLexiconTypeImpl.RemoveImpl)content;
    	}
    }
	 
	 public class Replace extends ExistentialExceptionType implements lexicon.jaxb.ExistentialLexiconType.Replace {
    	public Replace(lexicon.jaxb.ExistentialExceptionType content) {
    		super (content);
    	}
    	public Replace() {
    		super();
    		content = new lexicon.jaxb.impl.ExistentialLexiconTypeImpl.ReplaceImpl();
    	}
    	public lexicon.jaxb.impl.ExistentialLexiconTypeImpl.ReplaceImpl getActionImpl() {
    		return (lexicon.jaxb.impl.ExistentialLexiconTypeImpl.ReplaceImpl)content;
    	}
    }


	public static void main(String[] args) {
	}
}
