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
import lexicon.contents.exception_types.PronounExceptionType;
import lexicon.contents.pos.PronounLexiconType.Add;
import lexicon.contents.pos.PronounLexiconType.Remove;
import lexicon.contents.pos.PronounLexiconType.Replace;

/**
 * ���� �� ���� ������ ��� �� ������ ������ (��� �����).
 * 
 * Java content class for PronounLexiconType complex type.
 * <p>
 * The following schema fragment specifies the expected content contained within
 * this java content object. (defined at file:/C:/hebrew_lexicon.xsd line 834)
 * <p>
 * 
 * <pre>
 * &lt;complexType name="PronounLexiconType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;attribute name="gender" type="{}GenderType" default="unspecified" />
 *       &lt;attribute name="number" type="{}NumberType" default="unspecified" />
 *       &lt;attribute name="person" type="{}PersonType" default="unspecified" />
 *       &lt;attribute name="type" use="required" type="{}PronounType" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 */
public class PronounLexiconType extends Content implements
		lexicon.jaxb.PronounLexiconType {
	lexicon.jaxb.PronounLexiconType content;

	public PronounLexiconType(lexicon.jaxb.PronounLexiconType content) {
		this.content = content;
		TABLE = "pronoun";
		IDNAME = "id";
	}

	public PronounLexiconType() {
		content = new lexicon.jaxb.impl.PronounLexiconTypeImpl();
		TABLE = "pronoun";
		IDNAME = "id";
	}

	public lexicon.jaxb.impl.PronounLexiconTypeImpl getImpl() {
		return (lexicon.jaxb.impl.PronounLexiconTypeImpl) content;
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
		sql += ", '" + getPerson() + "'";
		sql += ", '" + getPgn() + "'";
		sql += ", '" + getType() + "'";
		sql += ", '" + getDefiniteness() + "')";
		// System.out.println("add sql="+sql);
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
		String sql = "UPDATE " + getTableName() + " SET";
		sql += " gender='" + getGender() + "'";
		sql += ", number='" + getNumber() + "'";
		sql += ", person='" + getPerson() + "'";
		sql += ", pgn='" + getPgn() + "'";
		sql += ", definiteness='" + getDefiniteness() + "'";
		sql += ", type='" + getType() + "' WHERE id=" + getID();
		// System.out.println(sql);
		int feedback = execute(sql);
		return feedback;
	}

	protected int addActions(int id) {
		int result = 0;
		for (int i = 0; i < getAddOrReplaceOrRemove().size(); i++) {
			PronounExceptionType exceptionType = new PronounExceptionType(
					(lexicon.jaxb.PronounExceptionType) getAddOrReplaceOrRemove()
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
			PronounExceptionType exceptionType = new PronounExceptionType(
					(lexicon.jaxb.PronounExceptionType) getAddOrReplaceOrRemove()
							.get(i));
			exceptionType
					.setAction(getAction(getAddOrReplaceOrRemove().get(i)));
			result += exceptionType.update();
		}
		return result;
	}

	protected int removeActions(int id) {
		return removeContents("pronoun_exception_type", "id", "aid", id);
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
		setPerson(getString("person"));
		setPgn(getString("pgn"));
		setNumber(getString("number"));
		setType(getString("type"));
		setDefiniteness(getString("definiteness"));
	}

	public java.util.List getActions() {
		List actions = getContents("pronoun_exception_type", "id", id);
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
		if (content.getType() == null) {
			return "";
		}
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
	 * Gets the value of the person property.
	 * 
	 * @return possible object is {@link java.lang.String}
	 */
	public java.lang.String getPerson() {
		return content.getPerson();
	}

	/**
	 * Sets the value of the person property.
	 * 
	 * @param value
	 *            allowed object is {@link java.lang.String}
	 */
	public void setPerson(java.lang.String value) {
		content.setPerson(value);
	}

	/**
	 * Gets the value of the person property.
	 * 
	 * @return possible object is {@link java.lang.String}
	 */
	public java.lang.String getPgn() {
		return content.getPgn();
	}

	/**
	 * Sets the value of the person property.
	 * 
	 * @param value
	 *            allowed object is {@link java.lang.String}
	 */
	public void setPgn(java.lang.String value) {
		content.setPgn(value);
	}

	/**
	 * Gets the value of the interrogative property.
	 * 
	 */
	public java.lang.String getDefiniteness() {
		return content.getDefiniteness();
	}

	/**
	 * Sets the value of the interrogative property.
	 * 
	 */
	public void setDefiniteness(java.lang.String value) {
		content.setDefiniteness(value);
	}

	public java.util.List getAddOrReplaceOrRemove() {
		return content.getAddOrReplaceOrRemove();
	}

	public class Add extends PronounExceptionType implements
			lexicon.jaxb.PronounLexiconType.Add {

		public Add() {
			super();
			content = new lexicon.jaxb.impl.PronounLexiconTypeImpl.AddImpl();
		}

		public Add(lexicon.jaxb.PronounExceptionType content) {
			super(content);
		}

		public lexicon.jaxb.impl.PronounLexiconTypeImpl.AddImpl getActionImpl() {
			return (lexicon.jaxb.impl.PronounLexiconTypeImpl.AddImpl) content;
		}
	}

	public class Remove extends PronounExceptionType implements
			lexicon.jaxb.PronounLexiconType.Remove {
		public Remove(lexicon.jaxb.PronounExceptionType content) {
			super(content);
		}

		public Remove() {
			super();
			content = new lexicon.jaxb.impl.PronounLexiconTypeImpl.RemoveImpl();
		}

		public lexicon.jaxb.impl.PronounLexiconTypeImpl.RemoveImpl getActionImpl() {
			return (lexicon.jaxb.impl.PronounLexiconTypeImpl.RemoveImpl) content;
		}
	}

	public class Replace extends PronounExceptionType implements
			lexicon.jaxb.PronounLexiconType.Replace {
		public Replace(lexicon.jaxb.PronounExceptionType content) {
			super(content);
		}

		public Replace() {
			super();
			content = new lexicon.jaxb.impl.PronounLexiconTypeImpl.ReplaceImpl();
		}

		public lexicon.jaxb.impl.PronounLexiconTypeImpl.ReplaceImpl getActionImpl() {
			return (lexicon.jaxb.impl.PronounLexiconTypeImpl.ReplaceImpl) content;
		}
	}

}
