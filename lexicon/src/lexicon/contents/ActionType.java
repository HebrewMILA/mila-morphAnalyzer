package lexicon.contents;

/**
 * @author Danny Shacham
 */
public class ActionType {
	protected int id;
	protected String dotted;
	protected String undotted;
	protected String transliterated;
	protected String action;

	public ActionType(int id) {
		this.id = id;
	}

	public String getAction() {
		return this.action;
	}

	public String getDotted() {
		return this.dotted;
	}

	public int getID() {
		return this.id;
	}

	public String getTransliterated() {
		return this.transliterated;
	}

	public String getUndotted() {
		return this.undotted;
	}

	public void setAction(String action) {
		this.action = action;
	}

	public void setDotted(String dotted) {
		this.dotted = dotted;
	}

	public void setTransliterated(String transliterated) {
		this.transliterated = transliterated;
	}

	public void setUndotted(String undotted) {
		this.undotted = undotted;
	}
}
