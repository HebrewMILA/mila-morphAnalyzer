/*
 * Created on 23/02/2006
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package lexicon.contents.pos;

import java.util.ArrayList;
import java.util.List;
import lexicon.contents.Content;
import lexicon.contents.EmptyContent;
import lexicon.contents.exception_types.CopulaExceptionType;
import lexicon.jaxb.impl.CopulaExceptionTypeImpl;

/**
 * @author daliabo
 *
 *         TODO To change the template for this generated type comment go to
 *         Window - Preferences - Java - Code Style - Code Templates
 */
public class CopulaLexiconType extends Content implements lexicon.jaxb.CopulaLexiconType {
	lexicon.jaxb.CopulaLexiconType content;

	public CopulaLexiconType(lexicon.jaxb.CopulaLexiconType content) {
		this.content = content;
		TABLE = "copula";
		IDNAME = "id";
	}

	public CopulaLexiconType() {
		content = new lexicon.jaxb.impl.CopulaLexiconTypeImpl();
		TABLE = "copula";
		IDNAME = "id";
	}

	public lexicon.jaxb.impl.CopulaLexiconTypeImpl getImpl() {
		return (lexicon.jaxb.impl.CopulaLexiconTypeImpl) content;
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
		sql += ", '" + getGender() + "'";
		sql += ", '" + getNumber() + "'";
		sql += ", '" + getPerson() + "'";
		sql += ", '" + getPolarity() + "'";
		sql += ", '" + getTense() + "')";
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
		sql += " gender='" + getGender() + "'";
		sql += ", number='" + getNumber() + "'";
		sql += ", person='" + getPerson() + "'";
		sql += ", polarity='" + getPolarity() + "'";
		sql += ", tense='" + getTense() + "'";
		sql += " WHERE id=" + getID();
		int feedback = execute(sql);
		return feedback;
	}

	public void load() {
		List<CopulaExceptionTypeImpl> actions = getActions();
		getAddOrReplaceOrRemove().clear();
		getAddOrReplaceOrRemove().addAll(actions);
		setGender(getString("gender"));
		setNumber(getString("number"));
		setPerson(getString("person"));
		setPolarity(getString("polarity"));
		setTense(getString("tense"));
	}

	protected int addActions(int id) {
		int result = 0;
		for (int i = 0; i < getAddOrReplaceOrRemove().size(); i++) {
			CopulaExceptionType exceptionType = new CopulaExceptionType(
					getAddOrReplaceOrRemove().get(i));
			exceptionType.setAction(getAction(getAddOrReplaceOrRemove().get(i)));
			result += exceptionType.add(id);
		}
		return result;
	}

	protected int updateActions() {
		int result = 0;
		for (int i = 0; i < getAddOrReplaceOrRemove().size(); i++) {
			CopulaExceptionType exceptionType = new CopulaExceptionType(
					getAddOrReplaceOrRemove().get(i));
			exceptionType.setAction(getAction(getAddOrReplaceOrRemove().get(i)));
			result += exceptionType.update();
		}
		return result;
	}

	protected int removeActions(int id) {
		return removeContents("copula_exception_type", "id", "aid", id);
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

	public java.util.List<CopulaExceptionTypeImpl> getActions() {
		List<EmptyContent> actions = getContents("copula_exception_type", "id", id);
		ArrayList<CopulaExceptionTypeImpl> result = new ArrayList<CopulaExceptionTypeImpl>();
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

	/*
	 * (non-Javadoc)
	 * 
	 * @see lexicon.jaxb.CopulaLexiconType#getRoot()
	 */
	/*
	 * (non-Javadoc)
	 * 
	 * @see lexicon.jaxb.CopulaLexiconType#getGender()
	 */
	public java.lang.String getGender() {
		return content.getGender();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see lexicon.jaxb.CopulaLexiconType#setGender(java.lang.String)
	 */
	public void setGender(String value) {
		content.setGender(value);

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see lexicon.jaxb.CopulaLexiconType#getNumber()
	 */
	public java.lang.String getNumber() {
		return content.getNumber();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see lexicon.jaxb.CopulaLexiconType#setNumber(java.lang.String)
	 */
	public void setNumber(java.lang.String value) {
		content.setNumber(value);

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see lexicon.jaxb.CopulaLexiconType#getPerson()
	 */
	public java.lang.String getPerson() {
		return content.getPerson();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see lexicon.jaxb.CopulaLexiconType#setPerson(java.lang.String)
	 */
	public void setPerson(java.lang.String value) {
		content.setPerson(value);

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see lexicon.jaxb.CopulaLexiconType#getPerson()
	 */
	public java.lang.String getTense() {
		return content.getTense();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see lexicon.jaxb.CopulaLexiconType#setPerson(java.lang.String)
	 */
	public void setTense(java.lang.String value) {
		content.setTense(value);

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see lexicon.jaxb.CopulaLexiconType#getPerson()
	 */
	public java.lang.String getPolarity() {
		return content.getPolarity();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see lexicon.jaxb.CopulaLexiconType#setPerson(java.lang.String)
	 */
	public void setPolarity(java.lang.String value) {
		content.setPolarity(value);

	}

	public java.util.List<CopulaExceptionTypeImpl> getAddOrReplaceOrRemove() {
		return content.getAddOrReplaceOrRemove();
	}

	public class Add extends CopulaExceptionType implements lexicon.jaxb.CopulaLexiconType.Add {
		public Add() {
			super();
			content = new lexicon.jaxb.impl.CopulaLexiconTypeImpl.AddImpl();
		}

		public Add(lexicon.jaxb.CopulaExceptionType content) {
			super(content);
		}

		public lexicon.jaxb.impl.CopulaLexiconTypeImpl.AddImpl getActionImpl() {
			return (lexicon.jaxb.impl.CopulaLexiconTypeImpl.AddImpl) content;
		}
	}

	public class Remove extends CopulaExceptionType implements lexicon.jaxb.CopulaLexiconType.Remove {
		public Remove(lexicon.jaxb.CopulaExceptionType content) {
			super(content);
		}

		public Remove() {
			super();
			content = new lexicon.jaxb.impl.CopulaLexiconTypeImpl.RemoveImpl();
		}

		public lexicon.jaxb.impl.CopulaLexiconTypeImpl.RemoveImpl getActionImpl() {
			return (lexicon.jaxb.impl.CopulaLexiconTypeImpl.RemoveImpl) content;
		}
	}

	public class Replace extends CopulaExceptionType implements lexicon.jaxb.CopulaLexiconType.Replace {
		public Replace(lexicon.jaxb.CopulaExceptionType content) {
			super(content);
		}

		public Replace() {
			super();
			content = new lexicon.jaxb.impl.CopulaLexiconTypeImpl.ReplaceImpl();
		}

		public lexicon.jaxb.impl.CopulaLexiconTypeImpl.ReplaceImpl getActionImpl() {
			return (lexicon.jaxb.impl.CopulaLexiconTypeImpl.ReplaceImpl) content;
		}
	}
}
