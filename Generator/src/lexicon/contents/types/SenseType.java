package lexicon.contents.types;

import lexicon.contents.Content;

import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Danny Shacham
 */
public class SenseType extends Content implements lexicon.jaxb.SenseType {
	protected lexicon.jaxb.SenseType content;

	public SenseType() {
		content = new lexicon.jaxb.impl.SenseTypeImpl();
		TABLE = "sense";
		IDNAME = "sid";
	}

	public SenseType(lexicon.jaxb.SenseType content) {
		this.content = content;
		id = Integer.parseInt(content.getId());
		TABLE = "sense";
		IDNAME = "sid";
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
	public int add(int itemID) {
		String sql = "INSERT INTO " + getTableName() + " VALUES (";
		sql += getId();
		sql += ", " + itemID;
		String definition = getDefinition();
		try {
			definition = URLEncoder.encode(definition, Content.ADD_ENCODING);
		} catch (Exception e) {
		}
		sql += ", '" + definition + "'";
		sql += ", '" + getWeight() + "')";
		int feedback = execute(sql);
		id = getCurrentID(getTableName(), getIDName());
		addEnglish();
		addExample();
		addSynset();
		return id;
	}

	public int addEnglish() {
		int result = 0;
		for (int i = 0; i < getEnglish().size(); i++) {
			EnglishType source = new EnglishType((lexicon.jaxb.EnglishType) getEnglish().get(i));
			result += source.add(id);
		}
		return result;
	}

	public int addSynset() {
		int result = 0;
		for (int i = 0; i < getSynset().size(); i++) {
			SynsetType source = new SynsetType((lexicon.jaxb.SynsetType) getSynset().get(i));
			result += source.add(id);
		}
		return result;
	}

	public int addExample() {
		int result = 0;
		for (int i = 0; i < getExample().size(); i++) {
			ExampleType source = new ExampleType((lexicon.jaxb.ExampleType) getExample().get(i));
			result += source.add(id);
		}
		return result;
	}

	public int updateEnglish() {
		int result = 0;
		for (int i = 0; i < getEnglish().size(); i++) {
			EnglishType source = new EnglishType((lexicon.jaxb.EnglishType) getEnglish().get(i));
			result += source.add(id);
		}
		return result;
	}

	public int updateSynset() {
		int result = 0;
		for (int i = 0; i < getSynset().size(); i++) {
			SynsetType source = new SynsetType((lexicon.jaxb.SynsetType) getSynset().get(i));
			result += source.add(id);
		}
		return result;
	}

	public int updateExample() {
		int result = 0;
		for (int i = 0; i < getExample().size(); i++) {
			ExampleType source = new ExampleType((lexicon.jaxb.ExampleType) getExample().get(i));
			result += source.update();
		}
		return result;
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
		String definition = getDefinition();
		try {
			definition = URLEncoder.encode(definition, Content.ADD_ENCODING);
		} catch (Exception e) {
		}
		sql += " definition='" + definition + "'";
		sql += ", weight=" + getWeight() + " WHERE sid=" + getId();
		int feedback = execute(sql);
		// updateEnglish();
		// updateExample();
		// updateSynset();
		return feedback;
	}

	public int remove() {
		removeEnglish();
		removeExample();
		removeSynset();
		int result = super.remove();
		return result;
	}

	protected int removeEnglish() {
		return removeContents("english", "sid", "eid", id);
	}

	protected int removeExample() {
		return removeContents("example", "sid", "eid", id);
	}

	protected int removeSynset() {
		return removeContents("synset", "sid", "synset_id", id);
	}

	public void load() {
		setId(Integer.toString(getInt("sid")));
		setDefinition(getString("definition"));
		setWeight(getString("weight"));
		List englishes = getEnglishes();
		getEnglish().clear();
		getEnglish().addAll(englishes);
		// List examples = getExample(getID());
		// getExample().clear();
		// getExample().addAll(examples);
		// List synsets = getSynset(getID());
		// getSynset().clear();
		// getSynset().addAll(synsets);
	}

	protected java.util.List getEnglishes() {
		String sql = "SELECT eid from english WHERE sid=" + id;
		List sources = getContents(sql, "eid");
		ArrayList result = new ArrayList();
		for (int i = 0; i < sources.size(); i++) {
			EnglishType english = new EnglishType();
			english.open(((Integer) sources.get(i)).intValue());
			result.add(english.getImpl());
		}
		return result;
	}

	protected java.util.List getExamples() {
		String sql = "SELECT eid from example WHERE sid=" + id;
		List sources = getContents(sql, "eid");
		ArrayList result = new ArrayList();
		for (int i = 0; i < sources.size(); i++) {
			ExampleType source = new ExampleType();
			source.open(((Integer) sources.get(i)).intValue());
			result.add(source.getImpl());
		}
		return result;
	}

	protected java.util.List getSynsets() {
		String sql = "SELECT synset_id from synset WHERE sid=" + id;
		List sources = getContents(sql, "synset_id");
		ArrayList result = new ArrayList();
		for (int i = 0; i < sources.size(); i++) {
			SynsetType source = new SynsetType();
			source.open(((Integer) sources.get(i)).intValue());
			result.add(source.getImpl());
		}
		return result;
	}

	public lexicon.jaxb.impl.SenseTypeImpl getImpl() {
		return (lexicon.jaxb.impl.SenseTypeImpl) content;
	}

	public void setId(String value) {
		content.setId(value);
	}

	public String getId() {
		if (content.getId() == null) {
			return "0";
		}
		return content.getId();
	}

	public void setDefinition(String value) {
		content.setDefinition(value);
	}

	public String getDefinition() {
		if (content.getDefinition() == null) {
			return "";
		}
		return content.getDefinition();
	}

	public void setWeight(String value) {
		content.setWeight(value);
	}

	public String getWeight() {
		if (content.getWeight() == null) {
			return "1";
		}
		if (content.getWeight().equals("")) {
			return "0";
		}
		return content.getWeight();
	}

	public List getExample() {
		return content.getExample();
	}

	public List getEnglish() {
		return content.getEnglish();
	}

	public List getSynset() {
		return content.getSynset();
	}

}
