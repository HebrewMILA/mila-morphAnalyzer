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
import lexicon.contents.exception_types.AcronymExceptionType;

/**
 * @author daliabo
 * 
 *         TODO To change the template for this generated type comment go to
 *         Window - Preferences - Java - Code Style - Code Templates
 */
public class AcronymLexiconType extends Content implements
		lexicon.jaxb.AcronymLexiconType {

	lexicon.jaxb.AcronymLexiconType content;

	public AcronymLexiconType(lexicon.jaxb.AcronymLexiconType content) {
		this.content = content;
		TABLE = "acronym";
		IDNAME = "id";
	}

	public AcronymLexiconType() {
		content = new lexicon.jaxb.impl.AcronymLexiconTypeImpl();
		TABLE = "acronym";
		IDNAME = "id";
	}

	public lexicon.jaxb.impl.AcronymLexiconTypeImpl getImpl() {
		return (lexicon.jaxb.impl.AcronymLexiconTypeImpl) content;
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
		String meaning = getMeaning();
		if (meaning == null) {
			meaning = "";
		}
		try {
			meaning = URLEncoder.encode(meaning, Content.ADD_ENCODING);
		} catch (Exception e) {
		}
		sql += ", '" + meaning + "'";
		sql += ", '" + getPos() + "'";
		sql += ", '" + getGender() + "'";
		sql += ", '" + getNumber() + "'";
		sql += ", '" + getFeminine() + "'";
		sql += ", '" + getPlural() + "'";
		sql += ", '" + getProperNameType() + "'";
		sql += ", '" + getConjunctionType() + "'";
		sql += ", '" + getDefiniteness() + "'";
		sql += ", " + (isInflectConstructS() ? 1 : 0);
		sql += ", " + (isInflectConstructP() ? 1 : 0);
		sql += ", '" + (getNoInvertedCommas()) + "'";
		sql += ", " + (isGenerateAdjective() ? 1 : 0);
		sql += ", '" + getNiqLexicalLink() + "'";
		sql += ", '" + getIstLexicalLink() + "'";
		sql += ", '" + getAdjectiveLexicalLink() + "'" + ")";
		System.out.println("sql=====================" + sql);
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
		sql += " pos='" + getPos() + "'";
		sql += " ,gender='" + getGender() + "'";
		sql += ", number='" + getNumber() + "'";
		sql += ", feminine='" + getFeminine() + "'";
		sql += ", plural='" + getPlural() + "'";
		sql += ", properNameType='" + getProperNameType() + "'";
		sql += ", conjunctionType='" + getConjunctionType() + "'";
		sql += ", definiteness='" + getDefiniteness() + "'";
		sql += ", inflectConstructS=" + (isInflectConstructS() ? 1 : 0);
		sql += ", inflectConstructP=" + (isInflectConstructP() ? 1 : 0);
		sql += ", noInvertedCommas='" + getNoInvertedCommas() + "'";
		sql += ", generateAdjective=" + (isGenerateAdjective() ? 1 : 0);
		sql += ", niqLexicalLink='" + getNiqLexicalLink() + "'";
		sql += ", istLexicalLink='" + getIstLexicalLink() + "'";
		sql += ", adjectiveLexicalLink='" + getAdjectiveLexicalLink() + "'";
		String meaning = getMeaning();
		if (meaning == null) {
			meaning = "";
		}
		try {
			meaning = URLEncoder.encode(meaning, Content.ADD_ENCODING);
		} catch (Exception e) {
		}
		sql += ", meaning='" + meaning + "'";
		sql += " WHERE id=" + getID();
		int feedback = execute(sql);
		return 1;
	}

	public void load() {
		List actions = getActions();
		getAddOrReplaceOrRemove().clear();
		getAddOrReplaceOrRemove().addAll(actions);
		setPos(getString("pos"));
		setMeaning(getString("meaning"));
		setGender(getString("gender"));
		setNumber(getString("number"));
		setFeminine(getString("feminine"));
		setPlural(getString("plural"));
		setProperNameType(getString("properNameType"));
		setConjunctionType(getString("conjunctionType"));
		setDefiniteness(getString("definiteness"));
		setInflectConstructS(getInt("inflectConstructS") == 1);
		setInflectConstructP(getInt("inflectConstructP") == 1);
		setNoInvertedCommas(getString("noInvertedCommas"));
		setGenerateAdjective(getInt("generateAdjective") == 1);
		setNiqLexicalLink(getString("niqLexicalLink"));
		setIstLexicalLink(getString("istLexicalLink"));
		setAdjectiveLexicalLink(getString("adjectivelexicalLink"));
	}

	public int remove() {
		removeActions(id);
		int result = super.remove();
		return result;
	}

	protected int addActions(int id) {
		int result = 0;
		for (int i = 0; i < getAddOrReplaceOrRemove().size(); i++) {
			AcronymExceptionType exceptionType = new AcronymExceptionType(
					(lexicon.jaxb.impl.AcronymExceptionTypeImpl) getAddOrReplaceOrRemove()
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
			AcronymExceptionType exceptionType = new AcronymExceptionType(
					(lexicon.jaxb.impl.AcronymExceptionTypeImpl) getAddOrReplaceOrRemove()
							.get(i));
			exceptionType
					.setAction(getAction(getAddOrReplaceOrRemove().get(i)));
			result += exceptionType.update();
		}
		return result;
	}

	protected int removeActions(int id) {
		return removeContents("noun_exception_type", "id", "aid", id);
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
		List actions = getContents("acronym_exception_type", "id", id);
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
	 * Gets the value of the root property.
	 * 
	 * @return possible object is {@link java.lang.String}
	 */
	public java.lang.String getMeaning() {
		if (content.getMeaning() == null) {
			return "";
		}
		return content.getMeaning();
	}

	/**
	 * Sets the value of the root property.
	 * 
	 * @param value
	 *            allowed object is {@link java.lang.String}
	 */
	public void setMeaning(java.lang.String value) {
		content.setMeaning(value);
	}

	public static void main(String[] args) {
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see lexicon.jaxb.AcronymLexiconType#getFeminine()
	 */
	public String getFeminine() {
		return content.getFeminine();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see lexicon.jaxb.AcronymLexiconType#setFeminine(java.lang.String)
	 */
	public void setFeminine(java.lang.String value) {
		content.setFeminine(value);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see lexicon.jaxb.AcronymLexiconType#isInflectConstructS()
	 */
	public boolean isInflectConstructS() {
		return content.isInflectConstructS();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see lexicon.jaxb.AcronymLexiconType#setInflectConstructS(boolean)
	 */
	public void setInflectConstructS(boolean value) {
		content.setInflectConstructS(value);

	}

	public String getNoInvertedCommas() {
		return content.getNoInvertedCommas();
	}

	public void setNoInvertedCommas(java.lang.String value) {
		content.setNoInvertedCommas(value);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see lexicon.jaxb.AcronymLexiconType#getPos()
	 */
	public String getPos() {
		return content.getPos();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see lexicon.jaxb.AcronymLexiconType#setPos(java.lang.String)
	 */
	public void setPos(String value) {
		content.setPos(value);

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see lexicon.jaxb.AcronymLexiconType#getPos()
	 */
	public String getConjunctionType() {
		return content.getConjunctionType();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see lexicon.jaxb.AcronymLexiconType#setPos(java.lang.String)
	 */
	public void setConjunctionType(String value) {
		content.setConjunctionType(value);

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see lexicon.jaxb.AcronymLexiconType#getPlural()
	 */
	public String getPlural() {
		return content.getPlural();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see lexicon.jaxb.AcronymLexiconType#setPlural(java.lang.String)
	 */
	public void setPlural(String value) {
		content.setPlural(value);

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see lexicon.jaxb.AcronymLexiconType#getPlural()
	 */
	public String getProperNameType() {
		return content.getProperNameType();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see lexicon.jaxb.AcronymLexiconType#setPlural(java.lang.String)
	 */
	public void setProperNameType(String value) {
		content.setProperNameType(value);

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see lexicon.jaxb.AcronymLexiconType#isInflectConstructP()
	 */
	public boolean isInflectConstructP() {
		return content.isInflectConstructP();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see lexicon.jaxb.AcronymLexiconType#setInflectConstructP(boolean)
	 */
	public void setInflectConstructP(boolean value) {
		content.setInflectConstructP(value);

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see lexicon.jaxb.AcronymLexiconType#isGenerateAdjective()
	 */
	public boolean isGenerateAdjective() {
		return content.isGenerateAdjective();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see lexicon.jaxb.AcronymLexiconType#setGenerateAdjective(boolean)
	 */
	public void setGenerateAdjective(boolean value) {
		content.setGenerateAdjective(value);

	}

	public java.lang.String getNiqLexicalLink() {
		return content.getNiqLexicalLink();
	}

	public void setNiqLexicalLink(java.lang.String value) {
		content.setNiqLexicalLink(value);
	}

	public java.lang.String getIstLexicalLink() {
		return content.getIstLexicalLink();
	}

	public void setIstLexicalLink(java.lang.String value) {
		content.setIstLexicalLink(value);
	}

	public java.lang.String getAdjectiveLexicalLink() {
		return content.getAdjectiveLexicalLink();
	}

	public void setAdjectiveLexicalLink(java.lang.String value) {
		content.setAdjectiveLexicalLink(value);
	}

	public java.util.List getAddOrReplaceOrRemove() {
		return content.getAddOrReplaceOrRemove();
	}

	public class Add extends AcronymExceptionType implements
			lexicon.jaxb.AcronymLexiconType.Add {

		public Add() {
			super();
			content = new lexicon.jaxb.impl.AcronymLexiconTypeImpl.AddImpl();
		}

		public Add(lexicon.jaxb.AcronymExceptionType content) {
			super(content);
		}

		public lexicon.jaxb.impl.AcronymLexiconTypeImpl.AddImpl getActionImpl() {
			return (lexicon.jaxb.impl.AcronymLexiconTypeImpl.AddImpl) content;
		}
	}

	public class Remove extends AcronymExceptionType implements
			lexicon.jaxb.AcronymLexiconType.Remove {
		public Remove(lexicon.jaxb.AcronymExceptionType content) {
			super(content);
		}

		public Remove() {
			super();
			content = new lexicon.jaxb.impl.AcronymLexiconTypeImpl.RemoveImpl();
		}

		public lexicon.jaxb.impl.AcronymLexiconTypeImpl.RemoveImpl getActionImpl() {
			return (lexicon.jaxb.impl.AcronymLexiconTypeImpl.RemoveImpl) content;
		}
	}

	public class Replace extends AcronymExceptionType implements
			lexicon.jaxb.AcronymLexiconType.Replace {
		public Replace(lexicon.jaxb.AcronymExceptionType content) {
			super(content);
		}

		public Replace() {
			super();
			content = new lexicon.jaxb.impl.AcronymLexiconTypeImpl.ReplaceImpl();
		}

		public lexicon.jaxb.impl.AcronymLexiconTypeImpl.ReplaceImpl getActionImpl() {
			return (lexicon.jaxb.impl.AcronymLexiconTypeImpl.ReplaceImpl) content;
		}
	}
}
