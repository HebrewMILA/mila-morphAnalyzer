package mila.dataStructures;

/**
 * 
 * DBInflectionsRecord.java Purpose: A class used as a data structure (record)
 * to hold an entry from the inflections table/data file
 * 
 * @author Dalia Bojan
 * @version %G%
 */

public class DBInflectionsRecord {
	/** transliterated form of the inflected form */
	String transliterated = "";
	/** inflected form in Hebrew */
	String surface = "";
	/** The part of speech of the inflected form */
	String basePos = "";
	/**
	 * The lexicon id of the lexicon entry from which the inflected form was
	 * generated
	 */
	String baseLexiconPointer = "";
	/** The lexicon item from which the inflected form was generated */
	String baseUndottedLItem = "";
	/**
	 * The transliterated form of the lexicon item from which the inflected form
	 * was generated
	 */
	String baseTransliteratedLItem = "";
	/** The register of the inflected form */
	String register = "";
	/** The spelling of the inflected form */
	String spelling = "";
	/** The startus (absolute/construct/unspecified) of the inflected form */
	String status = "";
	/**
	 * The suffix type of the inflected form - can be possessive/accusative or
	 * nominative/pronomial
	 */
	String suffixFunction = "";
	/** person/gender/number of the inflected form as appears in the suffix */
	String PGN = "";
	/**
	 * The binyan of the inflected form - relevant only for verb, participle etc
	 */
	String binyan = "";
	/**
	 * The tense of the inflected form - relevant only for verb, participle etc
	 */
	String tense = "";
	/**
	 * The root of the inflected form - relevant only for verb, participle etc
	 */
	String root = "";
	/** The number of the inflected form */
	String baseNumber = "";
	/** The gender of the inflected form */
	String baseGender = "";
	/** The person of the inflected form */
	String basePerson = "";
	/**
	 * The type of the inflected form - it is relevant for several part of
	 * speech
	 */
	String type = "";
	/**
	 * The h value of the inflected form - can be definited or not, can be
	 * subcoordinated for participle and modals
	 */
	String hAttribute = "";
	/** The dotted form of the lexicon item relevant to the inflected form */
	String dottedLexiconItem = "";
	/**
	 * The polarity of the inflected form - relevant only for several part of
	 * speech such as copula
	 */
	String polarity = "";
	/**
	 * The value of the inflected form - relevant in case of acronym - holds the
	 * expansion, in case of numeral it will hold the value of the numeral for
	 * example yer-> 10
	 */
	String value = "";
	/**
	 * The mood is relevant only for participle - in case of passive it is true
	 */
	String mood = "";
	/**
	 * The prefix per entry is relevant only for pronouns - it enables to define
	 * per lexicon entry which entry can be added cklm
	 */
	char prefixPerEntry = 'u';
	/**
	 * nouns and adjectives which are originated from foreign source like
	 * ainplcih are indicated
	 */
	int foreign = -1;

	/**
	 * @return
	 */
	public String getBaseGender() {
		return baseGender;
	}

	/**
	 * @return
	 */
	public String getBaseLexiconPointer() {
		return baseLexiconPointer;
	}

	/**
	 * @return
	 */
	public String getBaseNumber() {
		return baseNumber;
	}

	/**
	 * @return
	 */
	public String getBasePerson() {
		return basePerson;
	}

	/**
	 * @return
	 */
	public String getBasePos() {
		return basePos;
	}

	/**
	 * @return
	 */
	public String getBaseTransliteratedLItem() {
		return baseTransliteratedLItem;
	}

	/**
	 * @return
	 */
	public String getBaseUndottedLItem() {
		return baseUndottedLItem;
	}

	/**
	 * @return
	 */
	public String getBinyan() {
		return binyan;
	}

	/**
	 * @return
	 */
	public String getDottedLexiconItem() {
		return dottedLexiconItem;
	}

	/**
	 * @return
	 */
	public int getForeign() {
		return foreign;
	}

	/**
	 * @return
	 */
	public String getHAttribute() {
		return hAttribute;
	}

	/**
	 * @return
	 */
	public String getMood() {
		return mood;
	}

	/**
	 * @return
	 */
	public String getPGN() {
		return PGN;
	}

	/**
	 * @return
	 */
	public String getPolarity() {
		return polarity;
	}

	/**
	 * @return
	 */
	public char getPrefixPerEntry() {
		return prefixPerEntry;
	}

	/**
	 * @return
	 */
	public String getRegister() {
		return register;
	}

	/**
	 * @return
	 */
	public String getRoot() {
		return root;
	}

	/**
	 * @return
	 */
	public String getSpelling() {
		return spelling;
	}

	/**
	 * @return
	 */
	public String getStatus() {
		return status;
	}

	/**
	 * @return
	 */
	public String getSuffixFunction() {
		return suffixFunction;
	}

	/**
	 * @return
	 */
	public String getSurface() {
		return surface;
	}

	/**
	 * @return
	 */
	public String getTense() {
		return tense;
	}

	/**
	 * @return
	 */
	public String getTransliterated() {
		return transliterated;
	}

	/**
	 * @return
	 */
	public String getType() {
		return type;
	}

	/**
	 * @return
	 */
	public String getValue() {
		return value;
	}

	/**
	 * @param baseGender
	 */
	public void setBaseGender(String baseGender) {
		this.baseGender = baseGender;
	}

	/**
	 * @param baseLexiconPointer
	 */
	public void setBaseLexiconPointer(String baseLexiconPointer) {
		this.baseLexiconPointer = baseLexiconPointer;
	}

	/**
	 * @param baseNumber
	 */
	public void setBaseNumber(String baseNumber) {
		this.baseNumber = baseNumber;
	}

	/**
	 * @param basePerson
	 */
	public void setBasePerson(String basePerson) {
		this.basePerson = basePerson;
	}

	/**
	 * @param basePos
	 */
	public void setBasePos(String basePos) {
		this.basePos = basePos;
	}

	/**
	 * @param baseTransliteratedLItem
	 */
	public void setBaseTransliteratedLItem(String baseTransliteratedLItem) {
		this.baseTransliteratedLItem = baseTransliteratedLItem;
	}

	/**
	 * @param baseUndottedLItem
	 */
	public void setBaseUndottedLItem(String baseUndottedLItem) {
		this.baseUndottedLItem = baseUndottedLItem;
	}

	/**
	 * @param binyan
	 */
	public void setBinyan(String binyan) {
		this.binyan = binyan;
	}

	/**
	 * @param dottedLexiconItem
	 */
	public void setDottedLexiconItem(String dottedLexiconItem) {
		this.dottedLexiconItem = dottedLexiconItem;
	}

	/**
	 * @param foreign
	 */
	public void setForeign(int foreign) {
		this.foreign = foreign;
	}

	/**
	 * @param attribute
	 */
	public void setHAttribute(String attribute) {
		hAttribute = attribute;
	}

	/**
	 * @param mood
	 */
	public void setMood(String mood) {
		this.mood = mood;
	}

	/**
	 * @param pgn
	 */
	public void setPGN(String pgn) {
		PGN = pgn;
	}

	/**
	 * @param polarity
	 */
	public void setPolarity(String polarity) {
		this.polarity = polarity;
	}

	/**
	 * @param prefixPerEntry
	 */
	public void setPrefixPerEntry(char prefixPerEntry) {
		this.prefixPerEntry = prefixPerEntry;
	}

	/**
	 * @param register
	 */
	public void setRegister(String register) {
		this.register = register;
	}

	/**
	 * @param root
	 */
	public void setRoot(String root) {
		this.root = root;
	}

	/**
	 * @param spelling
	 */
	public void setSpelling(String spelling) {
		this.spelling = spelling;
	}

	/**
	 * @param status
	 */
	public void setStatus(String status) {
		this.status = status;
	}

	/**
	 * @param suffixFunction
	 */
	public void setSuffixFunction(String suffixFunction) {
		this.suffixFunction = suffixFunction;
	}

	/**
	 * @param surface
	 */
	public void setSurface(String surface) {
		this.surface = surface;
	}

	/**
	 * @param tense
	 */
	public void setTense(String tense) {
		this.tense = tense;
	}

	/**
	 * @param transliterated
	 */
	public void setTransliterated(String transliterated) {
		this.transliterated = transliterated;
	}

	/**
	 * @param type
	 */
	public void setType(String type) {
		this.type = type;
	}

	/**
	 * @param value
	 */
	public void setValue(String value) {
		this.value = value;
	}

}
