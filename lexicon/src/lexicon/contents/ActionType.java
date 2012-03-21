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
	public void setDotted(String dotted) {
		this.dotted = dotted;
	}
	public void setUndotted(String undotted) {
		this.undotted = undotted;
	}
	public void setTransliterated(String transliterated) {
		this.transliterated = transliterated;
	}
	public String getTransliterated() {
		return transliterated;
	}
	public void setAction(String action) {
		this.action = action;
	}
	public String getAction() {
		return action;
	}
	public String getDotted() {
		return dotted;
	}
	public String getUndotted() {
		return undotted;
	}
	public int getID() {
		return id;
	}
}
