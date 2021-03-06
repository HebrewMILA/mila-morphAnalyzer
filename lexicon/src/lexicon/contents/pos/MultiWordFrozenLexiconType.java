/*
 * Created on 23/02/2006
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package lexicon.contents.pos;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import lexicon.contents.Content;
import lexicon.contents.exception_types.MultiWordFrozenExceptionType;

/**
 * @author daliabo
 * 
 *         TODO To change the template for this generated type comment go to
 *         Window - Preferences - Java - Code Style - Code Templates
 */
public class MultiWordFrozenLexiconType extends Content implements
		lexicon.jaxb.MultiWordFrozenLexiconType {
	lexicon.jaxb.MultiWordFrozenLexiconType content;

	public MultiWordFrozenLexiconType(
			lexicon.jaxb.MultiWordFrozenLexiconType content) {
		this.content = content;
		TABLE = "multiWord";
		IDNAME = "id";
	}

	public MultiWordFrozenLexiconType() {
		content = new lexicon.jaxb.impl.MultiWordFrozenLexiconTypeImpl();
		TABLE = "multiWord";
		IDNAME = "id";
	}

	public lexicon.jaxb.impl.MultiWordFrozenLexiconTypeImpl getImpl() {
		return (lexicon.jaxb.impl.MultiWordFrozenLexiconTypeImpl) content;
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
		sql += ", '" + getMwPos() + "'";
		sql += ", '" + getType() + "'";
		sql += ", '" + getNumber() + "'";
		sql += ", " + (isConsecutive() ? 1 : 0);
		sql += ", " + (isPrefix() ? 1 : 0);
		sql += ", " + (isBase2() ? 1 : 0);
		sql += ", " + (isConstruct1() ? 1 : 0);
		sql += ", " + (isPossessive2() ? 1 : 0);
		sql += ", " + (isDefiniteness() ? 1 : 0);
		sql += ", " + (isAcceptDefiniteness() ? 1 : 0);
		sql += ")";
		System.out.println("this is the sql" + sql);
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
		sql += " type='" + getType() + "'";
		sql += ", pos='" + getMwPos() + "'";
		sql += ", consecutive=" + (isConsecutive() ? 1 : 0);
		sql += ", definiteness=" + (isDefiniteness() ? 1 : 0);
		sql += ", acceptDefiniteness=" + (isAcceptDefiniteness() ? 1 : 0);
		sql += ", prefix=" + (isPrefix() ? 1 : 0);
		sql += " WHERE id=" + getID();
		int feedback = execute(sql);
		return feedback;
	}

	public void load() {
		List actions = getActions();
		getAddOrReplaceOrRemove().clear();
		getAddOrReplaceOrRemove().addAll(actions);

		setConsecutive(getInt("consecutive") == 1);
		setPrefix(getInt("prefix") == 1);
		setBase2(getInt("base2") == 1);
		setConstruct1(getInt("construct1") == 1);
		setPossessive2(getInt("possessive2") == 1);
		setType(getString("type"));
		setNumber(getString("number"));
		setDefiniteness(getInt("definiteness") == 1);
		setAcceptDefiniteness(getInt("acceptDefiniteness") == 1);
		setMwPos(getString("pos"));
	}

	protected int addActions(int id) {
		int result = 0;
		for (int i = 0; i < getAddOrReplaceOrRemove().size(); i++) {
			MultiWordFrozenExceptionType exceptionType = new MultiWordFrozenExceptionType(
					(lexicon.jaxb.MultiWordFrozenExceptionType) getAddOrReplaceOrRemove()
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
			MultiWordFrozenExceptionType exceptionType = new MultiWordFrozenExceptionType(
					(lexicon.jaxb.MultiWordFrozenExceptionType) getAddOrReplaceOrRemove()
							.get(i));
			exceptionType
					.setAction(getAction(getAddOrReplaceOrRemove().get(i)));
			result += exceptionType.update();
		}
		return result;
	}

	protected int removeActions(int id) {
		return removeContents("multiWordFrozen_exception_type", "id", "aid", id);
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
		List actions = getContents("multiWordFrozen_exception_type", "id", id);
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

	public static void main(String[] args) {
	}

	public boolean isConsecutive() {
		return content.isConsecutive();
	}

	public void setConsecutive(boolean value) {
		content.setConsecutive(value);
	}

	public boolean isPrefix() {
		return content.isPrefix();
	}

	public void setPrefix(boolean value) {
		content.setPrefix(value);
	}

	public boolean isBase2() {
		return content.isBase2();
	}

	public void setBase2(boolean value) {
		content.setBase2(value);
	}

	public boolean isConstruct1() {
		return content.isConstruct1();
	}

	public void setConstruct1(boolean value) {
		content.setConstruct1(value);
	}

	public boolean isDefiniteness() {
		return content.isDefiniteness();
	}

	public void setDefiniteness(boolean value) {
		content.setDefiniteness(value);
	}

	public boolean isAcceptDefiniteness() {
		return content.isAcceptDefiniteness();
	}

	public void setAcceptDefiniteness(boolean value) {
		content.setAcceptDefiniteness(value);
	}

	public boolean isPossessive2() {
		return content.isPossessive2();
	}

	public void setPossessive2(boolean value) {
		content.setPossessive2(value);
	}

	public java.lang.String getType() {
		return content.getType();
	}

	public void setType(java.lang.String value) {
		content.setType(value);
	}

	public java.lang.String getNumber() {
		return content.getNumber();
	}

	public void setNumber(java.lang.String value) {
		content.setNumber(value);
	}

	public java.lang.String getMwPos() {
		return content.getMwPos();
	}

	public void setMwPos(java.lang.String value) {
		content.setMwPos(value);
	}

	public java.util.List getAddOrReplaceOrRemove() {
		return content.getAddOrReplaceOrRemove();
	}

	public class Add extends MultiWordFrozenExceptionType implements
			lexicon.jaxb.MultiWordFrozenLexiconType.Add {
		public Add() {
			super();
			content = new lexicon.jaxb.impl.MultiWordFrozenLexiconTypeImpl.AddImpl();
		}

		public Add(lexicon.jaxb.MultiWordFrozenExceptionType content) {
			super(content);
		}

		public lexicon.jaxb.impl.MultiWordFrozenLexiconTypeImpl.AddImpl getActionImpl() {
			return (lexicon.jaxb.impl.MultiWordFrozenLexiconTypeImpl.AddImpl) content;
		}
	}

	public class Remove extends MultiWordFrozenExceptionType implements
			lexicon.jaxb.MultiWordFrozenLexiconType.Remove {
		public Remove(lexicon.jaxb.MultiWordFrozenExceptionType content) {
			super(content);
		}

		public Remove() {
			super();
			content = new lexicon.jaxb.impl.MultiWordFrozenLexiconTypeImpl.RemoveImpl();
		}

		public lexicon.jaxb.impl.MultiWordFrozenLexiconTypeImpl.RemoveImpl getActionImpl() {
			return (lexicon.jaxb.impl.MultiWordFrozenLexiconTypeImpl.RemoveImpl) content;
		}
	}

	public class Replace extends MultiWordFrozenExceptionType implements
			lexicon.jaxb.MultiWordFrozenLexiconType.Replace {
		public Replace(lexicon.jaxb.MultiWordFrozenExceptionType content) {
			super(content);
		}

		public Replace() {
			super();
			content = new lexicon.jaxb.impl.MultiWordFrozenLexiconTypeImpl.ReplaceImpl();
		}

		public lexicon.jaxb.impl.MultiWordFrozenLexiconTypeImpl.ReplaceImpl getActionImpl() {
			return (lexicon.jaxb.impl.MultiWordFrozenLexiconTypeImpl.ReplaceImpl) content;
		}
	}

}
