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

	public void setPos(String pos) {
		this.pos = pos;
	}

	public String getPos() {
		return pos;
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
