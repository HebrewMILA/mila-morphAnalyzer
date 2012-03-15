package lexicon.dbUtils;

public class MWE1record {
	private String id;
	private String surface;
	private String transliterated;
	private String consecutive;
	private String pos;
	private String type;

	public String getConsecutive() {
		return consecutive;
	}

	public String getId() {
		return id;
	}

	public String getPos() {
		return pos;
	}

	public String getSurface() {
		return surface;
	}

	public String getTransliterated() {
		return transliterated;
	}

	public String getType() {
		return type;
	}

	public void setConsecutive(String consecutive) {
		this.consecutive = consecutive;
	}

	public void setId(String id) {
		this.id = id;
	}

	public void setPos(String pos) {
		this.pos = pos;
	}

	public void setSurface(String surface) {
		this.surface = surface;
	}

	public void setTransliterated(String transliterated) {
		this.transliterated = transliterated;
	}

	public void setType(String type) {
		this.type = type;
	}

}
