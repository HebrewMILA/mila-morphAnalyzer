/*
 * Created on 23/02/2006
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package lexicon.contents.pos;

import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import lexicon.contents.Content;
import lexicon.contents.exception_types.MultiWordNounAdjectiveExceptionType;

/**
 * @author daliabo
 * 
 *         TODO To change the template for this generated type comment go to
 *         Window - Preferences - Java - Code Style - Code Templates
 */
public class MultiWordNounAdjectiveLexiconType extends Content implements
		lexicon.jaxb.MultiWordNounAdjectiveLexiconType {
	lexicon.jaxb.MultiWordNounAdjectiveLexiconType content;

	public MultiWordNounAdjectiveLexiconType(
			lexicon.jaxb.MultiWordNounAdjectiveLexiconType content) {
		this.content = content;
		TABLE = "multiWordNounAdjective";
		IDNAME = "id";
	}

	public MultiWordNounAdjectiveLexiconType() {
		content = new lexicon.jaxb.impl.MultiWordNounAdjectiveLexiconTypeImpl();
		TABLE = "multiWordNounAdjective";
		IDNAME = "id";
	}

	public lexicon.jaxb.impl.MultiWordNounAdjectiveLexiconTypeImpl getImpl() {
		return (lexicon.jaxb.impl.MultiWordNounAdjectiveLexiconTypeImpl) content;
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
		sql += ", '" + getGender() + "'";
		sql += ", '" + getNumber() + "'";
		sql += ", '" + getFeminineNoun() + "'";
		sql += ", '" + getFeminineAdjective() + "'";
		sql += ", " + (isConsecutive() ? 1 : 0);
		sql += ", '" + getDefiniteness() + "'";
		sql += ", " + (isInflectPossessiveS() ? 1 : 0);
		sql += ", " + (isInflectPossessiveP() ? 1 : 0);
		String inflectionBase = getInflectionBase();
		if (inflectionBase == null) {
			inflectionBase = "";
		}
		try {
			inflectionBase = URLEncoder.encode(inflectionBase,
					Content.ADD_ENCODING);
		} catch (Exception e) {
		}
		sql += ", '" + inflectionBase + "'";
		sql += ", '" + getType() + "'";
		sql += ", '" + getPluralFemaleNoun() + "'";
		sql += ", '" + getPluralFemaleAdjective() + "'";
		sql += ", '" + getPluralMaleNoun() + "'";
		sql += ", '" + getPluralMaleAdjective() + "'";
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
		sql += " gender='" + getGender() + "'";
		sql += ", number='" + getNumber() + "'";
		sql += ", feminine_noun='" + getFeminineNoun() + "'";
		sql += ", feminine_adjective='" + getFeminineAdjective() + "'";
		sql += ", type='" + getType() + "'";
		sql += ", plural_feminine_noun='" + getPluralFemaleNoun() + "'";
		sql += ", plural_feminine_adjective='" + getPluralFemaleAdjective()
				+ "'";
		sql += ", plural_masculine_noun='" + getPluralMaleNoun() + "'";
		sql += ", plural_masculine_adjective='" + getPluralMaleAdjective()
				+ "'";
		sql += ", definiteness='" + getDefiniteness() + "'";
		sql += ", pos='" + getMwPos() + "'";
		sql += ", consecutive=" + (isConsecutive() ? 1 : 0);
		sql += ", inflectPossessiveS=" + (isInflectPossessiveS() ? 1 : 0);
		sql += ", inflectPossessiveP=" + (isInflectPossessiveP() ? 1 : 0);
		String inflectionBase = getInflectionBase();
		if (inflectionBase == null) {
			inflectionBase = "";
		}
		try {
			inflectionBase = URLEncoder.encode(inflectionBase,
					Content.UPDATE_ENCODING);
		} catch (Exception e) {
		}
		sql += ", inflectionBase='" + inflectionBase + "'";
		sql += " WHERE id=" + getID();
		int feedback = execute(sql);
		return feedback;
	}

	public void load() {
		List actions = getActions();
		getAddOrReplaceOrRemove().clear();
		getAddOrReplaceOrRemove().addAll(actions);

		setConsecutive(getInt("consecutive") == 1);
		setInflectPossessiveS(getInt("inflectPossessiveS") == 1);
		setInflectPossessiveP(getInt("inflectPossessiveP") == 1);
		setDefiniteness(getString("definiteness"));
		setNumber(getString("number"));
		setGender(getString("gender"));
		setPluralMaleNoun(getString("plural_masculine_noun"));
		setPluralMaleAdjective(getString("plural_masculine_adjective"));
		setPluralFemaleNoun(getString("plural_feminine_noun"));
		setPluralFemaleAdjective(getString("plural_feminine_adjective"));
		setFeminineNoun(getString("feminine_noun"));
		setFeminineAdjective(getString("feminine_adjective"));
		setType(getString("type"));
		setMwPos(getString("pos"));
		setInflectionBase(getString("inflectionBase"));
	}

	protected int addActions(int id) {
		int result = 0;
		for (int i = 0; i < getAddOrReplaceOrRemove().size(); i++) {
			MultiWordNounAdjectiveExceptionType exceptionType = new MultiWordNounAdjectiveExceptionType(
					(lexicon.jaxb.MultiWordNounAdjectiveExceptionType) getAddOrReplaceOrRemove()
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
			MultiWordNounAdjectiveExceptionType exceptionType = new MultiWordNounAdjectiveExceptionType(
					(lexicon.jaxb.MultiWordNounAdjectiveExceptionType) getAddOrReplaceOrRemove()
							.get(i));
			exceptionType
					.setAction(getAction(getAddOrReplaceOrRemove().get(i)));
			result += exceptionType.update();
		}
		return result;
	}

	protected int removeActions(int id) {
		return removeContents("multiWordNounAdjective_exception_type", "id",
				"aid", id);
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
		List actions = getContents("multiWordNounAdjective_exception_type",
				"id", id);
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

	public boolean isInflectPossessiveS() {
		return content.isInflectPossessiveS();
	}

	public void setInflectPossessiveS(boolean value) {
		content.setInflectPossessiveS(value);
	}

	public boolean isInflectPossessiveP() {
		return content.isInflectPossessiveP();
	}

	public void setInflectPossessiveP(boolean value) {
		content.setInflectPossessiveP(value);
	}

	public java.lang.String getDefiniteness() {
		return content.getDefiniteness();
	}

	public void setDefiniteness(java.lang.String value) {
		content.setDefiniteness(value);
	}

	public java.lang.String getGender() {
		return content.getGender();
	}

	public void setGender(java.lang.String value) {
		content.setGender(value);
	}

	public java.lang.String getNumber() {
		return content.getNumber();
	}

	public void setNumber(java.lang.String value) {
		content.setNumber(value);
	}

	public java.lang.String getPluralMaleNoun() {
		return content.getPluralMaleNoun();
	}

	public void setPluralMaleNoun(java.lang.String value) {
		content.setPluralMaleNoun(value);
	}

	public java.lang.String getPluralMaleAdjective() {
		return content.getPluralMaleAdjective();
	}

	public void setPluralMaleAdjective(java.lang.String value) {
		content.setPluralMaleAdjective(value);
	}

	public java.lang.String getPluralFemaleNoun() {
		return content.getPluralFemaleNoun();
	}

	public void setPluralFemaleNoun(java.lang.String value) {
		content.setPluralFemaleNoun(value);
	}

	public java.lang.String getPluralFemaleAdjective() {
		return content.getPluralFemaleAdjective();
	}

	public void setPluralFemaleAdjective(java.lang.String value) {
		content.setPluralFemaleAdjective(value);
	}

	public java.lang.String getFeminineNoun() {
		return content.getFeminineNoun();
	}

	public void setFeminineNoun(java.lang.String value) {
		content.setFeminineNoun(value);
	}

	public java.lang.String getFeminineAdjective() {
		return content.getFeminineAdjective();
	}

	public void setFeminineAdjective(java.lang.String value) {
		content.setFeminineAdjective(value);
	}

	public java.lang.String getMwPos() {
		return content.getMwPos();
	}

	public void setMwPos(java.lang.String value) {
		content.setMwPos(value);
	}

	public java.lang.String getType() {
		return content.getType();
	}

	public void setType(java.lang.String value) {
		content.setType(value);
	}

	public void setInflectionBase(String value) {
		content.setInflectionBase(value);
	}

	public java.lang.String getInflectionBase() {
		return content.getInflectionBase();
	}

	public java.util.List getAddOrReplaceOrRemove() {
		return content.getAddOrReplaceOrRemove();
	}

	public class Add extends MultiWordNounAdjectiveExceptionType implements
			lexicon.jaxb.MultiWordNounAdjectiveLexiconType.Add {
		public Add() {
			super();
			content = new lexicon.jaxb.impl.MultiWordNounAdjectiveLexiconTypeImpl.AddImpl();
		}

		public Add(lexicon.jaxb.MultiWordNounAdjectiveExceptionType content) {
			super(content);
		}

		public lexicon.jaxb.impl.MultiWordNounAdjectiveLexiconTypeImpl.AddImpl getActionImpl() {
			return (lexicon.jaxb.impl.MultiWordNounAdjectiveLexiconTypeImpl.AddImpl) content;
		}
	}

	public class Remove extends MultiWordNounAdjectiveExceptionType implements
			lexicon.jaxb.MultiWordNounAdjectiveLexiconType.Remove {
		public Remove(lexicon.jaxb.MultiWordNounAdjectiveExceptionType content) {
			super(content);
		}

		public Remove() {
			super();
			content = new lexicon.jaxb.impl.MultiWordNounAdjectiveLexiconTypeImpl.RemoveImpl();
		}

		public lexicon.jaxb.impl.MultiWordNounAdjectiveLexiconTypeImpl.RemoveImpl getActionImpl() {
			return (lexicon.jaxb.impl.MultiWordNounAdjectiveLexiconTypeImpl.RemoveImpl) content;
		}
	}

	public class Replace extends MultiWordNounAdjectiveExceptionType implements
			lexicon.jaxb.MultiWordNounAdjectiveLexiconType.Replace {
		public Replace(lexicon.jaxb.MultiWordNounAdjectiveExceptionType content) {
			super(content);
		}

		public Replace() {
			super();
			content = new lexicon.jaxb.impl.MultiWordNounAdjectiveLexiconTypeImpl.ReplaceImpl();
		}

		public lexicon.jaxb.impl.MultiWordNounAdjectiveLexiconTypeImpl.ReplaceImpl getActionImpl() {
			return (lexicon.jaxb.impl.MultiWordNounAdjectiveLexiconTypeImpl.ReplaceImpl) content;
		}
	}

}
