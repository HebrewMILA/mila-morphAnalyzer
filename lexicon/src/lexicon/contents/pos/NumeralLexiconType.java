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
import lexicon.contents.exception_types.NumeralExceptionType;

/**
 * Java content class for NumeralLexiconType complex type.
 * <p>
 * The following schema fragment specifies the expected content contained within
 * this java content object. (defined at file:/C:/hebrew_lexicon.xsd line 644)
 * <p>
 * 
 * <pre>
 * &lt;complexType name="NumeralLexiconType">
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
public class NumeralLexiconType extends Content implements
		lexicon.jaxb.NumeralLexiconType {
	lexicon.jaxb.NumeralLexiconType content;

	public NumeralLexiconType(lexicon.jaxb.NumeralLexiconType content) {
		this.content = content;
		TABLE = "numeral";
		IDNAME = "id";
	}

	public NumeralLexiconType() {
		content = new lexicon.jaxb.impl.NumeralLexiconTypeImpl();
		TABLE = "numeral";
		IDNAME = "id";
	}

	public lexicon.jaxb.impl.NumeralLexiconTypeImpl getImpl() {
		return (lexicon.jaxb.impl.NumeralLexiconTypeImpl) content;
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
		sql += ", '" + getValue() + "'";
		sql += ", '" + getGender() + "'";
		sql += ", '" + getNumber() + "'";
		sql += ", '" + getType() + "'";
		sql += ", '" + getDefiniteness() + "'";
		sql += ", " + (isInflect() ? 1 : 0);
		sql += ")";
		System.out.println("sql=" + sql);
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
		sql += ", value='" + getValue() + "'";
		sql += ", inflect=" + (isInflect() ? 1 : 0);
		sql += " WHERE id=" + getID();
		System.out.println("sql=" + sql);
		int feedback = execute(sql);
		return feedback;
	}

	protected int addActions(int id) {
		int result = 0;
		for (int i = 0; i < getAddOrReplaceOrRemove().size(); i++) {
			NumeralExceptionType exceptionType = new NumeralExceptionType(
					(lexicon.jaxb.NumeralExceptionType) getAddOrReplaceOrRemove()
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
			NumeralExceptionType exceptionType = new NumeralExceptionType(
					(lexicon.jaxb.NumeralExceptionType) getAddOrReplaceOrRemove()
							.get(i));
			exceptionType
					.setAction(getAction(getAddOrReplaceOrRemove().get(i)));
			result += exceptionType.update();
		}
		return result;
	}

	protected int removeActions(int id) {
		return removeContents("numeral_exception_type", "id", "aid", id);
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
		setValue(getString("value"));
		setDefiniteness(getString("definiteness"));
		setInflect(getInt("inflect") == 1);
	}

	public java.util.List getActions() {
		List actions = getContents("numeral_exception_type", "id", id);
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
	 * Gets the value of the number property.
	 * 
	 * @return possible object is {@link java.lang.String}
	 */
	public java.lang.String getValue() {
		return content.getValue();
	}

	/**
	 * Sets the value of the number property.
	 * 
	 * @param value
	 *            allowed object is {@link java.lang.String}
	 */
	public void setValue(java.lang.String value) {
		content.setValue(value);
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

	/**
	 * Gets the value of the inflect property.
	 * 
	 */
	public boolean isInflect() {
		return content.isInflect();
	}

	/**
	 * Sets the value of the inflect property.
	 * 
	 */
	public void setInflect(boolean value) {
		content.setInflect(value);
	}

	public java.util.List getAddOrReplaceOrRemove() {
		return content.getAddOrReplaceOrRemove();
	}

	public class Add extends NumeralExceptionType implements
			lexicon.jaxb.NumeralLexiconType.Add {

		public Add() {
			super();
			content = new lexicon.jaxb.impl.NumeralLexiconTypeImpl.AddImpl();
		}

		public Add(lexicon.jaxb.NumeralExceptionType content) {
			super(content);
		}

		public lexicon.jaxb.impl.NumeralLexiconTypeImpl.AddImpl getActionImpl() {
			return (lexicon.jaxb.impl.NumeralLexiconTypeImpl.AddImpl) content;
		}
	}

	public class Remove extends NumeralExceptionType implements
			lexicon.jaxb.NumeralLexiconType.Remove {
		public Remove(lexicon.jaxb.NumeralExceptionType content) {
			super(content);
		}

		public Remove() {
			super();
			content = new lexicon.jaxb.impl.NumeralLexiconTypeImpl.RemoveImpl();
		}

		public lexicon.jaxb.impl.NumeralLexiconTypeImpl.RemoveImpl getActionImpl() {
			return (lexicon.jaxb.impl.NumeralLexiconTypeImpl.RemoveImpl) content;
		}
	}

	public class Replace extends NumeralExceptionType implements
			lexicon.jaxb.NumeralLexiconType.Replace {
		public Replace(lexicon.jaxb.NumeralExceptionType content) {
			super(content);
		}

		public Replace() {
			super();
			content = new lexicon.jaxb.impl.NumeralLexiconTypeImpl.ReplaceImpl();
		}

		public lexicon.jaxb.impl.NumeralLexiconTypeImpl.ReplaceImpl getActionImpl() {
			return (lexicon.jaxb.impl.NumeralLexiconTypeImpl.ReplaceImpl) content;
		}
	}

}
