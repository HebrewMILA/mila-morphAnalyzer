package lexicon.contents;

/**
 * @author Danny Shacham
 */
public class SearchItem {
	protected int id;
	protected String dotted;
	protected String undotted;
	protected String transliterated;
	protected String pos;

	public SearchItem(int id) {
		this.id = id;
	}

	public String getDotted() {
		return this.dotted;
	}

	public int getID() {
		return this.id;
	}

	public String getPos() {
		return this.pos;
	}

	public String getTransliterated() {
		return this.transliterated;
	}

	public String getUndotted() {
		return this.undotted;
	}

	public void setDotted(String dotted) {
		this.dotted = dotted;
	}

	public void setPos(String pos) {
		this.pos = pos;
	}

	public void setTransliterated(String transliterated) {
		this.transliterated = transliterated;
	}

	public void setUndotted(String undotted) {
		this.undotted = undotted;
	}
}
