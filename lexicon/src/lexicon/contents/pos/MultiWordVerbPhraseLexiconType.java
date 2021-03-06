//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v1.0.4-b16-fcs 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2005.02.17 at 12:10:17 GMT+02:00 
//

package lexicon.contents.pos;

import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import lexicon.contents.exception_types.MultiWordVerbPhraseExceptionType;
import lexicon.contents.Content;

public class MultiWordVerbPhraseLexiconType extends Content implements
		lexicon.jaxb.MultiWordVerbPhraseLexiconType {
	lexicon.jaxb.MultiWordVerbPhraseLexiconType content;

	public MultiWordVerbPhraseLexiconType(
			lexicon.jaxb.MultiWordVerbPhraseLexiconType content) {
		this.content = content;
		TABLE = "multiWordVerbPhrase";
		IDNAME = "id";
	}

	public MultiWordVerbPhraseLexiconType() {
		content = new lexicon.jaxb.impl.MultiWordVerbPhraseLexiconTypeImpl();
		TABLE = "multiWordVerbPhrase";
		IDNAME = "id";
	}

	public lexicon.jaxb.impl.MultiWordVerbPhraseLexiconTypeImpl getImpl() {
		return (lexicon.jaxb.impl.MultiWordVerbPhraseLexiconTypeImpl) content;
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

		sql += ", " + (isInflectOrigin() ? 1 : 0);
		sql += ", " + (isInflectInfinitive() ? 1 : 0);
		sql += ", " + (isInflectInfinitivel() ? 1 : 0);
		sql += ", " + (isInflectInfinitiveb() ? 1 : 0);
		sql += ", " + (isInflectPast() ? 1 : 0);
		sql += ", " + (isInflectBeinoni() ? 1 : 0);
		sql += ", " + (isInflectFuture() ? 1 : 0);
		sql += ", " + (isInflectImperative() ? 1 : 0);

		sql += ", '" + getFeminine() + "'";
		sql += ", '" + getValence() + "'";
		String root = getRoot();
		if (root == null) {
			root = "";
		}
		try {
			root = URLEncoder.encode(root, Content.ADD_ENCODING);
		} catch (Exception e) {
		}
		sql += ", '" + root + "'";
		String type = getType();
		String binyan = getBinyan();
		String temp = binyan;
		binyan = binyan.replaceAll("'", "\\\\'");
		sql += ", '" + binyan + "'";
		sql += ", " + (isForeign() ? 1 : 0);
		sql += ", '" + getInflectionPattern() + "'";
		String ipSource = getIpSource();
		ipSource = ipSource.replaceAll("'", "\\\\'");
		sql += ", '" + ipSource;
		sql += "', " + (isInflectBeinoniConstruct() ? 1 : 0);
		sql += ", " + (isInflectBeinoniPossessive() ? 1 : 0);
		sql += ", " + (isInflectInfinitiveIndependent() ? 1 : 0);
		sql += ", '" + type + "'";
		sql += ")";
		System.out.println("Executing query: " + sql);
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
		String sql = "UPDATE " + getTableName() + " SET   ";

		sql += "hebForeign=" + (isForeign() ? 1 : 0);
		sql += ", inflectInfinitive=" + (isInflectInfinitive() ? 1 : 0);
		sql += ", inflectOrigin=" + (isInflectOrigin() ? 1 : 0);
		sql += ", inflectInfinitivel=" + (isInflectInfinitivel() ? 1 : 0);
		sql += ", inflectInfinitiveb=" + (isInflectInfinitiveb() ? 1 : 0);
		sql += ", inflectPast=" + (isInflectPast() ? 1 : 0);
		sql += ", inflectBeinoni=" + (isInflectBeinoni() ? 1 : 0);
		sql += ", inflectFuture=" + (isInflectFuture() ? 1 : 0);
		sql += ", inflectImperative=" + (isInflectImperative() ? 1 : 0);
		sql += ", inflectBeinoniConstruct="
				+ (isInflectBeinoniConstruct() ? 1 : 0);
		sql += ", inflectBeinoniPossessive="
				+ (isInflectBeinoniPossessive() ? 1 : 0);
		sql += ", inflectInfinitiveIndependent="
				+ (isInflectInfinitiveIndependent() ? 1 : 0);

		sql += ", feminine='" + getFeminine() + "'";
		sql += ", valence='" + getValence() + "'";
		String root = getRoot();
		if (root == null) {
			root = "";
		}
		try {
			root = URLEncoder.encode(root, Content.UPDATE_ENCODING);
		} catch (Exception e) {
		}
		sql += ", root='" + root + "'";
		String type = getType();
		String binyan = getBinyan();
		String temp = binyan;
		binyan = binyan.replaceAll("'", "\\\\'");
		sql += ", binyan='" + binyan + "'";
		sql += ", type='" + type + "'";
		sql += ", inflectionPattern='" + getInflectionPattern() + "'";
		String ipSource = getIpSource();
		ipSource = ipSource.replaceAll("'", "\\\\'");
		sql += ", ipSource='" + ipSource + "' WHERE id=" + getID();
		int feedback = execute(sql);
		// updateActions();
		return feedback;
	}

	protected int addActions(int id) {
		int result = 0;
		for (int i = 0; i < getAddOrReplaceOrRemove().size(); i++) {
			MultiWordVerbPhraseExceptionType exceptionType = new MultiWordVerbPhraseExceptionType(
					(lexicon.jaxb.MultiWordVerbPhraseExceptionType) getAddOrReplaceOrRemove()
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
			MultiWordVerbPhraseExceptionType exceptionType = new MultiWordVerbPhraseExceptionType(
					(lexicon.jaxb.MultiWordVerbPhraseExceptionType) getAddOrReplaceOrRemove()
							.get(i));
			exceptionType
					.setAction(getAction(getAddOrReplaceOrRemove().get(i)));
			result += exceptionType.update();
		}
		return result;
	}

	protected int removeActions(int id) {
		return removeContents("multiWordVerbPhrase_exception_type", "id",
				"aid", id);
	}

	public int remove() {
		removeActions(id);
		int result = super.remove();
		return result;
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

		setType(getString("type"));
		setBinyan(getString("binyan"));
		setForeign(getInt("hebForeign") == 1);
		setFeminine(getString("feminine"));
		setValence(getString("valence"));
		setRoot(getString("root"));
		setInflectionPattern(getString("inflectionPattern"));
		setIpSource(getString("ipSource"));
		setInflectOrigin(getInt("inflectOrigin") == 1);
		setInflectInfinitive(getInt("inflectInfinitive") == 1);
		setInflectInfinitivel(getInt("inflectInfinitivel") == 1);
		setInflectInfinitiveb(getInt("inflectInfinitiveb") == 1);
		setInflectPast(getInt("inflectPast") == 1);
		setInflectBeinoni(getInt("inflectBeinoni") == 1);
		setInflectFuture(getInt("inflectFuture") == 1);
		setInflectImperative(getInt("inflectImperative") == 1);
		setInflectBeinoniConstruct(getInt("inflectBeinoniConstruct") == 1);
		setInflectBeinoniPossessive(getInt("inflectBeinoniPossessive") == 1);
		setInflectInfinitiveIndependent(getInt("inflectInfinitiveIndependent") == 1);
	}

	public java.util.List getActions() {
		List actions = getContents("multiWordVerbPhrase_exception_type", "id",
				id);
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
	 * Gets the value of the feminine property.
	 * 
	 * @return possible object is {@link java.lang.String}
	 */
	public java.lang.String getFeminine() {
		return content.getFeminine();
	}

	/**
	 * Sets the value of the feminine property.
	 * 
	 * @param value
	 *            allowed object is {@link java.lang.String}
	 */
	public void setFeminine(java.lang.String value) {
		content.setFeminine(value);
	}

	/**
	 * Gets the value of the ipSource property.
	 * 
	 * @return possible object is {@link java.lang.String}
	 */
	public java.lang.String getIpSource() {
		return content.getIpSource();
	}

	/**
	 * Sets the value of the ipSource property.
	 * 
	 * @param value
	 *            allowed object is {@link java.lang.String}
	 */
	public void setIpSource(java.lang.String value) {
		content.setIpSource(value);
	}

	/**
	 * Gets the value of the inflectionPattern property.
	 * 
	 * @return possible object is {@link java.lang.String}
	 */
	public java.lang.String getInflectionPattern() {
		if (content.getInflectionPattern() == null) {
			return "";
		}
		return content.getInflectionPattern();
	}

	/**
	 * Sets the value of the inflectionPattern property.
	 * 
	 * @param value
	 *            allowed object is {@link java.lang.String}
	 */
	public void setInflectionPattern(java.lang.String value) {
		content.setInflectionPattern(value);
	}

	/**
	 * Gets the value of the valence property.
	 * 
	 * @return possible object is {@link java.lang.String}
	 */
	public java.lang.String getValence() {
		return content.getValence();
	}

	/**
	 * Sets the value of the valence property.
	 * 
	 * @param value
	 *            allowed object is {@link java.lang.String}
	 */
	public void setValence(java.lang.String value) {
		content.setValence(value);
	}

	/**
	 * Gets the value of the root property.
	 * 
	 * @return possible object is {@link java.lang.String}
	 */
	public java.lang.String getRoot() {
		if (content.getRoot() == null) {
			return "";
		}
		return content.getRoot();
	}

	/**
	 * Sets the value of the root property.
	 * 
	 * @param value
	 *            allowed object is {@link java.lang.String}
	 */
	public void setRoot(java.lang.String value) {
		content.setRoot(value);
	}

	/**
	 * Gets the value of the AddOrReplaceOrRemove property.
	 * 
	 * <p>
	 * This accessor method returns a reference to the live list, not a
	 * snapshot. Therefore any modification you make to the returned list will
	 * be present inside the JAXB object. This is why there is not a
	 * <CODE>set</CODE> method for the AddOrReplaceOrRemove property.
	 * 
	 * <p>
	 * For example, to add a new item, do as follows:
	 * 
	 * <pre>
	 * getAddOrReplaceOrRemove().add(newItem);
	 * </pre>
	 * 
	 * 
	 * <p>
	 * Objects of the following type(s) are allowed in the list
	 * {@link lexicon.jaxb.VerbLexiconType.Remove}
	 * {@link lexicon.jaxb.VerbLexiconType.Add}
	 * {@link lexicon.jaxb.VerbLexiconType.Replace}
	 * 
	 */
	public java.util.List getAddOrReplaceOrRemove() {
		return content.getAddOrReplaceOrRemove();
	}

	/**
	 * Gets the value of the binyan property.
	 * 
	 * @return possible object is {@link java.lang.String}
	 */
	public java.lang.String getBinyan() {
		return content.getBinyan();
	}

	/**
	 * Sets the value of the binyan property.
	 * 
	 * @param value
	 *            allowed object is {@link java.lang.String}
	 */
	public void setBinyan(java.lang.String value) {
		content.setBinyan(value);
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
	 * Gets the value of the inflectConstruct property.
	 * 
	 */

	public boolean isInflectOrigin() {
		return content.isInflectOrigin();
	}

	/**
	 * Sets the value of the inflectConstruct property.
	 * 
	 */
	public void setInflectOrigin(boolean value) {
		content.setInflectOrigin(value);
	}

	/**
	 * Gets the value of the inflectConstruct property.
	 * 
	 */

	public boolean isInflectInfinitive() {
		return content.isInflectInfinitive();
	}

	/**
	 * Sets the value of the inflectConstruct property.
	 * 
	 */
	public void setInflectInfinitive(boolean value) {
		content.setInflectInfinitive(value);
	}

	/**
	 * Gets the value of the inflectConstruct property.
	 * 
	 */

	public boolean isInflectInfinitivel() {
		return content.isInflectInfinitivel();
	}

	/**
	 * Sets the value of the inflectConstruct property.
	 * 
	 */
	public void setInflectInfinitivel(boolean value) {
		content.setInflectInfinitivel(value);
	}

	/**
	 * Gets the value of the inflectConstruct property.
	 * 
	 */

	public boolean isInflectInfinitiveb() {
		return content.isInflectInfinitiveb();
	}

	/**
	 * Sets the value of the inflectConstruct property.
	 * 
	 */
	public void setInflectInfinitiveb(boolean value) {
		content.setInflectInfinitiveb(value);
	}

	/**
	 * Gets the value of the inflectConstruct property.
	 * 
	 */

	public boolean isInflectPast() {
		return content.isInflectPast();
	}

	/**
	 * Sets the value of the inflectConstruct property.
	 * 
	 */
	public void setInflectPast(boolean value) {
		content.setInflectPast(value);
	}

	/**
	 * Gets the value of the inflectConstruct property.
	 * 
	 */

	public boolean isInflectBeinoni() {
		return content.isInflectBeinoni();
	}

	/**
	 * Sets the value of the inflectConstruct property.
	 * 
	 */
	public void setInflectBeinoni(boolean value) {
		content.setInflectBeinoni(value);
	}

	/**
	 * Gets the value of the inflectConstruct property.
	 * 
	 */

	public boolean isInflectFuture() {
		return content.isInflectFuture();
	}

	/**
	 * Sets the value of the inflectConstruct property.
	 * 
	 */
	public void setInflectFuture(boolean value) {
		content.setInflectFuture(value);
	}

	/**
	 * Gets the value of the inflectConstruct property.
	 * 
	 */

	public boolean isInflectImperative() {
		return content.isInflectImperative();
	}

	/**
	 * Sets the value of the inflectConstruct property.
	 * 
	 */
	public void setInflectImperative(boolean value) {
		content.setInflectImperative(value);
	}

	/**
	 * Gets the value of the inflectConstruct property.
	 * 
	 */

	public boolean isInflectBeinoniConstruct() {
		return content.isInflectBeinoniConstruct();
	}

	/**
	 * Sets the value of the inflectConstruct property.
	 * 
	 */
	public void setInflectBeinoniConstruct(boolean value) {
		content.setInflectBeinoniConstruct(value);
	}

	/**
	 * Gets the value of the inflectConstruct property.
	 * 
	 */

	public boolean isForeign() {
		return content.isForeign();
	}

	/**
	 * Sets the value of the inflectConstruct property.
	 * 
	 */
	public void setForeign(boolean value) {
		content.setForeign(value);
	}

	/**
	 * Gets the value of the inflectConstruct property.
	 * 
	 */

	public boolean isInflectInfinitiveIndependent() {
		return content.isInflectInfinitiveIndependent();
	}

	/**
	 * Sets the value of the inflectConstruct property.
	 * 
	 */
	public void setInflectInfinitiveIndependent(boolean value) {
		content.setInflectInfinitiveIndependent(value);
	}

	/**
	 * Gets the value of the inflectConstruct property.
	 * 
	 */

	public boolean isInflectBeinoniPossessive() {
		return content.isInflectBeinoniPossessive();
	}

	/**
	 * Sets the value of the inflectConstruct property.
	 * 
	 */
	public void setInflectBeinoniPossessive(boolean value) {
		content.setInflectBeinoniPossessive(value);
	}

	public class Add extends MultiWordVerbPhraseExceptionType implements
			lexicon.jaxb.MultiWordVerbPhraseLexiconType.Add {
		public Add() {
			super();
			content = new lexicon.jaxb.impl.MultiWordVerbPhraseLexiconTypeImpl.AddImpl();
		}

		public Add(lexicon.jaxb.MultiWordVerbPhraseExceptionType content) {
			super(content);
		}

		public lexicon.jaxb.impl.MultiWordVerbPhraseLexiconTypeImpl.AddImpl getActionImpl() {
			return (lexicon.jaxb.impl.MultiWordVerbPhraseLexiconTypeImpl.AddImpl) content;
		}
	}

	/**
	 * ���� �� ���� ����� ����� ������ ����� �������, ���� ���� ����� �����
	 * ����� ����� ��������, ��� ����� (���� ������ �� ������ ����, �� ��� �����
	 * ���). ������ ��� ����� ��� ����� ��� ���� ��� ����� ���� ������, �� �����
	 * �� �� ����� ����� �� ������ ��� �� ���, ����� �����, �� ����� �� ������.
	 * ����� ����, �� ������ ����� ��� �- add ��- replace ��� ������ �- remove,
	 * ��� ���� �� ������ ���� ����� ������ ���� ����� ��������.
	 * 
	 * Java content class for remove element declaration.
	 * <p>
	 * The following schema fragment specifies the expected content contained
	 * within this java content object. (defined at
	 * file:/C:/files/hebrew_lexicon_new.xsd line 249)
	 * <p>
	 * 
	 * <pre>
	 * &lt;element name="remove" type="{}VerbExceptionType"/>
	 * </pre>
	 * 
	 */
	public class Remove extends MultiWordVerbPhraseExceptionType implements
			lexicon.jaxb.MultiWordVerbPhraseLexiconType.Remove {
		public Remove(lexicon.jaxb.MultiWordVerbPhraseExceptionType content) {
			super(content);
		}

		public Remove() {
			super();
			content = new lexicon.jaxb.impl.MultiWordVerbPhraseLexiconTypeImpl.RemoveImpl();
		}

		public lexicon.jaxb.impl.MultiWordVerbPhraseLexiconTypeImpl.RemoveImpl getActionImpl() {
			return (lexicon.jaxb.impl.MultiWordVerbPhraseLexiconTypeImpl.RemoveImpl) content;
		}
	}

	/**
	 * ���� �� ���� ������ ����� ������ ������. ���� -- ����� ���� ����, ������
	 * ��'. ������ ����, �� ������ ���� ��� ��� �������� ����� ���� ������
	 * (����, ���� ��� ���� �����, �� ���� �����, �� ���� �����), ��� ����� ��
	 * ����� ������, ��� ������ �� ������ ������� ������. ��� ���� ������ ��� ��
	 * ������ ������� inflectPossessive="false" �-inflectConstruct="false".
	 * 
	 * Java content class for replace element declaration.
	 * <p>
	 * The following schema fragment specifies the expected content contained
	 * within this java content object. (defined at
	 * file:/C:/files/hebrew_lexicon_new.xsd line 240)
	 * <p>
	 * 
	 * <pre>
	 * &lt;element name="replace" type="{}VerbExceptionType"/>
	 * </pre>
	 * 
	 */
	public class Replace extends MultiWordVerbPhraseExceptionType implements
			lexicon.jaxb.MultiWordVerbPhraseLexiconType.Replace {
		public Replace(lexicon.jaxb.MultiWordVerbPhraseExceptionType content) {
			super(content);
		}

		public Replace() {
			super();
			content = new lexicon.jaxb.impl.MultiWordVerbPhraseLexiconTypeImpl.ReplaceImpl();
		}

		public lexicon.jaxb.impl.MultiWordVerbPhraseLexiconTypeImpl.ReplaceImpl getActionImpl() {
			return (lexicon.jaxb.impl.MultiWordVerbPhraseLexiconTypeImpl.ReplaceImpl) content;
		}
	}

}
