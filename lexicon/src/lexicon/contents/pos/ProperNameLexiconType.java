//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v1.0.4-b16-fcs 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2005.02.17 at 12:10:17 GMT+02:00 
//

package lexicon.contents.pos;

import java.util.ArrayList;
import java.util.List;

import lexicon.contents.Content;
import lexicon.contents.exception_types.ProperNameExceptionType;

/**
 * Java content class for ProperNameLexiconType complex type.
 * <p>
 * The following schema fragment specifies the expected content contained within
 * this java content object. (defined at file:/C:/hebrew_lexicon.xsd line 644)
 * <p>
 * 
 * <pre>
 * &lt;complexType name="ProperNameLexiconType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;attribute name="definiteness" type="{}TriStateType" default="false" />
 *       &lt;attribute name="direction" type="{}TriStateType" default="unspecified" />
 *       &lt;attribute name="gender" type="{}GenderType" default="masculine" />
 *       &lt;attribute name="number" type="{}NumberType" default="singular" />
 *       &lt;attribute name="type" type="{}NamedEntityType" default="person" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 */
public class ProperNameLexiconType extends Content implements
		lexicon.jaxb.ProperNameLexiconType {
	lexicon.jaxb.ProperNameLexiconType content;

	public ProperNameLexiconType(lexicon.jaxb.ProperNameLexiconType content) {
		this.content = content;
		TABLE = "propername";
		IDNAME = "id";
	}

	public ProperNameLexiconType() {
		content = new lexicon.jaxb.impl.ProperNameLexiconTypeImpl();
		TABLE = "propername";
		IDNAME = "id";
	}

	public lexicon.jaxb.impl.ProperNameLexiconTypeImpl getImpl() {
		return (lexicon.jaxb.impl.ProperNameLexiconTypeImpl) content;
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
		sql += id;
		sql += ", '" + getGender() + "'";
		sql += ", '" + getNumber() + "'";
		sql += ", '" + getType() + "'";
		sql += ", '" + getDefiniteness() + "'";
		sql += ", '" + getDirection() + "')";
		int feedback = execute(sql);
		id = getCurrentID(getTableName(), getIDName());
		addActions(id);
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
		String sql = "UPDATE " + getTableName() + " SET";
		sql += " gender='" + getGender() + "'";
		sql += ", number='" + getNumber() + "'";
		sql += ", type='" + getType() + "'";
		sql += ", definiteness='" + getDefiniteness() + "'";
		sql += ", direction='" + getDirection() + "' WHERE id=" + getID();
		int feedback = execute(sql);
		return feedback;
	}

	protected int addActions(int id) {
		int result = 0;
		for (int i = 0; i < getAddOrReplaceOrRemove().size(); i++) {
			ProperNameExceptionType exceptionType = new ProperNameExceptionType(
					(lexicon.jaxb.ProperNameExceptionType) getAddOrReplaceOrRemove()
							.get(i));
			exceptionType
					.setAction(getAction(getAddOrReplaceOrRemove().get(i)));
			result += exceptionType.add(id);
		}
		return result;
	}

	protected int updateActions() {
		int result = 0;
		for (int i = 0; i < getAddOrReplaceOrRemove().size(); i++) {
			ProperNameExceptionType exceptionType = new ProperNameExceptionType(
					(lexicon.jaxb.ProperNameExceptionType) getAddOrReplaceOrRemove()
							.get(i));
			exceptionType
					.setAction(getAction(getAddOrReplaceOrRemove().get(i)));
			result += exceptionType.update();
		}
		return result;
	}

	protected int removeActions(int id) {
		return removeContents("propername_exception_type", "id", "aid", id);
	}

	protected String getAction(Object obj) {
		String name = obj.getClass().getName();
		if (name.indexOf("Add") >= 0) {
			return "add";
		}
		if (name.indexOf("Replace") >= 0) {
			return "replace";
		}
		if (name.indexOf("Remove") >= 0) {
			return "remove";
		}
		return "";
	}

	public void load() {
		List actions = getActions();
		getAddOrReplaceOrRemove().clear();
		getAddOrReplaceOrRemove().addAll(actions);
		setGender(getString("gender"));
		setType(getString("type"));
		setNumber(getString("number"));
		setDefiniteness(getString("definiteness"));
		setDirection(getString("direction"));
	}

	public java.util.List getActions() {
		List actions = getContents("propername_exception_type", "id", id);
		ArrayList result = new ArrayList();
		for (int i = 0; i < actions.size(); i++) {
			Content content = (Content) actions.get(i);
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
	 * Gets the value of the type property.
	 * 
	 * @return possible object is {@link java.lang.String}
	 */
	public java.lang.String getType() {
		return content.getType();
	}

	/**
	 * Sets the value of the type property.
	 * 
	 * @param value
	 *            allowed object is {@link java.lang.String}
	 */
	public void setType(java.lang.String value) {
		content.setType(value);
	}

	/**
	 * Gets the value of the direction property.
	 * 
	 * @return possible object is {@link java.lang.String}
	 */
	public java.lang.String getDirection() {
		return content.getDirection();
	}

	/**
	 * Sets the value of the direction property.
	 * 
	 * @param value
	 *            allowed object is {@link java.lang.String}
	 */
	public void setDirection(java.lang.String value) {
		content.setDirection(value);
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
	 * Gets the value of the definiteness property.
	 * 
	 * @return possible object is {@link java.lang.String}
	 */
	public java.lang.String getDefiniteness() {
		return content.getDefiniteness();
	}

	/**
	 * Sets the value of the definiteness property.
	 * 
	 * @param value
	 *            allowed object is {@link java.lang.String}
	 */
	public void setDefiniteness(java.lang.String value) {
		content.setDefiniteness(value);
	}

	public java.util.List getAddOrReplaceOrRemove() {
		return content.getAddOrReplaceOrRemove();
	}

	public class Add extends ProperNameExceptionType implements
			lexicon.jaxb.ProperNameLexiconType.Add {

		public Add() {
			super();
			content = new lexicon.jaxb.impl.ProperNameLexiconTypeImpl.AddImpl();
		}

		public Add(lexicon.jaxb.ProperNameExceptionType content) {
			super(content);
		}

		public lexicon.jaxb.impl.ProperNameLexiconTypeImpl.AddImpl getActionImpl() {
			return (lexicon.jaxb.impl.ProperNameLexiconTypeImpl.AddImpl) content;
		}
	}

	public class Remove extends ProperNameExceptionType implements
			lexicon.jaxb.ProperNameLexiconType.Remove {
		public Remove(lexicon.jaxb.ProperNameExceptionType content) {
			super(content);
		}

		public Remove() {
			super();
			content = new lexicon.jaxb.impl.ProperNameLexiconTypeImpl.RemoveImpl();
		}

		public lexicon.jaxb.impl.ProperNameLexiconTypeImpl.RemoveImpl getActionImpl() {
			return (lexicon.jaxb.impl.ProperNameLexiconTypeImpl.RemoveImpl) content;
		}
	}

	public class Replace extends ProperNameExceptionType implements
			lexicon.jaxb.ProperNameLexiconType.Replace {
		public Replace(lexicon.jaxb.ProperNameExceptionType content) {
			super(content);
		}

		public Replace() {
			super();
			content = new lexicon.jaxb.impl.ProperNameLexiconTypeImpl.ReplaceImpl();
		}

		public lexicon.jaxb.impl.ProperNameLexiconTypeImpl.ReplaceImpl getActionImpl() {
			return (lexicon.jaxb.impl.ProperNameLexiconTypeImpl.ReplaceImpl) content;
		}
	}

}
