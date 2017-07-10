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
import lexicon.contents.exception_types.WprefixExceptionType;

/**
 * @author daliabo
 *
 *         TODO To change the template for this generated type comment go to
 *         Window - Preferences - Java - Code Style - Code Templates
 */
public class WprefixLexiconType extends Content implements lexicon.jaxb.WprefixLexiconType {

	lexicon.jaxb.WprefixLexiconType content;

	public WprefixLexiconType(lexicon.jaxb.WprefixLexiconType content) {
		this.content = content;
		TABLE = "wPrefix";
		IDNAME = "id";
	}

	public WprefixLexiconType() {
		content = new lexicon.jaxb.impl.WprefixLexiconTypeImpl();
		TABLE = "wPrefix";
		IDNAME = "id";
	}

	public lexicon.jaxb.impl.WprefixLexiconTypeImpl getImpl() {
		return (lexicon.jaxb.impl.WprefixLexiconTypeImpl) content;
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
	public int add(int id) {
		String sql = "INSERT INTO " + getTableName() + " VALUES (";
		sql += id;
		sql += ", '" + getGender() + "' ";
		sql += ", '" + getNumber() + "' ";
		sql += ", '" + getPolarity() + "')";
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
		sql += " polarity='" + getPolarity() + "'";
		sql += " , gender='" + getGender() + "'";
		sql += " , number='" + getNumber() + "'";
		sql += " WHERE id=" + getID();
		int feedback = execute(sql);
		// System.out.println(sql);
		return 1;
	}

	public void load() {
		List actions = getActions();
		getAddOrReplaceOrRemove().clear();
		getAddOrReplaceOrRemove().addAll(actions);

		setPolarity(getString("polarity"));
		setGender(getString("gender"));
		setNumber(getString("number"));
	}

	/**
	 * Gets the value of the number property.
	 * 
	 * @return possible object is {@link java.lang.String}
	 */
	public java.lang.String getPolarity() {
		return content.getPolarity();
	}

	/**
	 * Sets the value of the number property.
	 * 
	 * @param value
	 *           allowed object is {@link java.lang.String}
	 */
	public void setPolarity(java.lang.String value) {
		content.setPolarity(value);
	}

	/**
	 * Gets the value of the number property.
	 * 
	 * @return possible object is {@link java.lang.String}
	 */
	public java.lang.String getGender() {
		return content.getGender();
	}

	/**
	 * Sets the value of the number property.
	 * 
	 * @param value
	 *           allowed object is {@link java.lang.String}
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
	 *           allowed object is {@link java.lang.String}
	 */
	public void setNumber(java.lang.String value) {
		content.setNumber(value);
	}

	protected int addActions(int id) {
		int result = 0;
		for (int i = 0; i < getAddOrReplaceOrRemove().size(); i++) {
			WprefixExceptionType exceptionType = new WprefixExceptionType(
					(lexicon.jaxb.WprefixExceptionType) getAddOrReplaceOrRemove().get(i));
			exceptionType.setAction(getAction(getAddOrReplaceOrRemove().get(i)));
			result += exceptionType.add(id);
		}
		return result;
	}

	protected int updateActions() {
		int result = 0;
		for (int i = 0; i < getAddOrReplaceOrRemove().size(); i++) {
			WprefixExceptionType exceptionType = new WprefixExceptionType(
					(lexicon.jaxb.WprefixExceptionType) getAddOrReplaceOrRemove().get(i));
			exceptionType.setAction(getAction(getAddOrReplaceOrRemove().get(i)));
			result += exceptionType.update();
		}
		return result;
	}

	protected int removeActions(int id) {
		return removeContents("wprefix_exception_type", "id", "aid", id);
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

	public java.util.List getActions() {
		List actions = getContents("wprefix_exception_type", "id", id);
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

	public java.util.List getAddOrReplaceOrRemove() {
		return content.getAddOrReplaceOrRemove();
	}

	public class Add extends WprefixExceptionType implements lexicon.jaxb.WprefixLexiconType.Add {

		public Add() {
			super();
			content = new lexicon.jaxb.impl.WprefixLexiconTypeImpl.AddImpl();
		}

		public Add(lexicon.jaxb.WprefixExceptionType content) {
			super(content);
		}

		public lexicon.jaxb.impl.WprefixLexiconTypeImpl.AddImpl getActionImpl() {
			return (lexicon.jaxb.impl.WprefixLexiconTypeImpl.AddImpl) content;
		}
	}

	public class Remove extends WprefixExceptionType implements lexicon.jaxb.WprefixLexiconType.Remove {
		public Remove(lexicon.jaxb.WprefixExceptionType content) {
			super(content);
		}

		public Remove() {
			super();
			content = new lexicon.jaxb.impl.WprefixLexiconTypeImpl.RemoveImpl();
		}

		public lexicon.jaxb.impl.WprefixLexiconTypeImpl.RemoveImpl getActionImpl() {
			return (lexicon.jaxb.impl.WprefixLexiconTypeImpl.RemoveImpl) content;
		}
	}

	public class Replace extends WprefixExceptionType implements lexicon.jaxb.WprefixLexiconType.Replace {
		public Replace(lexicon.jaxb.WprefixExceptionType content) {
			super(content);
		}

		public Replace() {
			super();
			content = new lexicon.jaxb.impl.WprefixLexiconTypeImpl.ReplaceImpl();
		}

		public lexicon.jaxb.impl.WprefixLexiconTypeImpl.ReplaceImpl getActionImpl() {
			return (lexicon.jaxb.impl.WprefixLexiconTypeImpl.ReplaceImpl) content;
		}
	}

}
