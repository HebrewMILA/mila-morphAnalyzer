/*
 * Created on 14/08/2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package lexicon.dbUtils;

/**
 * @author daliabo
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class InflectRecord {
	String transliterated ="";
	String surface ="";
	String basePos = "";
	String baseLexiconPointer ="";
	String baseUndottedLItem ="";
	String baseTransliteratedLItem ="";
	String suffixPerson="";
	String suffixGender="";
	String suffixNumber ="";
	String register ="";
	String spelling ="";
	String suffixStatus ="";
	String suffixFunction ="";
	String PGN = "";
	String binyan ="";
	String tense ="";
	String root = "";
	String baseNumber ="";
	String baseGender="";
	String baseType ="";
	String baseDefinitness ="";
	String basePerson="";
	String accusativeSuffix="";
	String accusativeNominativeNumber="";
	String accusativeNominativeGender="";
	String accusativeNominativePerson="";
    String accusativeNominativePGN="";
    String dottedLexiconItem ="";
    String polarity ="";
    String type="";
    
    boolean consecutive=true;
    int multiWordLen;
    int subId=1;
    

	/**
	 * @return Returns the accusativeSuffix.
	 */
	public String getAccusativeSuffix() {
		return accusativeSuffix;
	}
	/**
	 * @param accusativeSuffix The accusativeSuffix to set.
	 */
	public void setAccusativeSuffix(String accusativeSuffix) {
		this.accusativeSuffix = accusativeSuffix;
	}
	/**
	 * @return Returns the basePerson.
	 */
	public String getBasePerson() {
		return basePerson;
	}
	/**
	 * @param basePerson The basePerson to set.
	 */
	public void setBasePerson(String basePerson) {
		this.basePerson = basePerson;
	}
	
	/**
	 * @return Returns the baseDefinitness.
	 */
	public String getBaseDefinitness() {
		return baseDefinitness;
	}
	/**
	 * @param baseDefinitness The baseDefinitness to set.
	 */
	public void setBaseDefinitness(String baseDefinitness) {
		this.baseDefinitness = baseDefinitness;
	}
	public static void main(String[] args) {
	}
	/**
	 * @return Returns the baseLexiconPointer.
	 */
	public String getBaseLexiconPointer() {
		return baseLexiconPointer;
	}
	/**
	 * @param baseLexiconPointer The baseLexiconPointer to set.
	 */
	public void setBaseLexiconPointer(String baseLexiconPointer) {
		this.baseLexiconPointer = baseLexiconPointer;
	}
	/**
	 * @return Returns the basePos.
	 */
	public String getBasePos() {
		return basePos;
	}
	/**
	 * @param basePos The basePos to set.
	 */
	public void setBasePos(String basePos) {
		this.basePos = basePos;
	}
	/**
	 * @return Returns the baseTransliteratedLItem.
	 */
	public String getBaseTransliteratedLItem() {
		return baseTransliteratedLItem;
	}
	/**
	 * @param baseTransliteratedLItem The baseTransliteratedLItem to set.
	 */
	public void setBaseTransliteratedLItem(String baseTransliteratedLItem) {
		this.baseTransliteratedLItem = baseTransliteratedLItem;
	}
	/**
	 * @return Returns the baseUndottedLItem.
	 */
	public String getBaseUndottedLItem() {
		return baseUndottedLItem;
	}
	/**
	 * @param baseUndottedLItem The baseUndottedLItem to set.
	 */
	public void setBaseUndottedLItem(String baseUndottedLItem) {
		this.baseUndottedLItem = baseUndottedLItem;
	}
	/**
	 * @return Returns the script.
	 */
	public String getRegsietr() {
		return register;
	}
	/**
	 * @param script The script to set.
	 */
	public void setRegister(String Register) {
		this.register = register;
	}
	/**
	 * @return Returns the script.
	 */
	public String getSpelling() {
		return spelling;
	}
	/**
	 * @param script The script to set.
	 */
	public void setSpelling(String spelling) {
		this.spelling = spelling;
	}
	/**
	 * @return Returns the suffixFunction.
	 */
	public String getSuffixFunction() {
		return suffixFunction;
	}
	/**
	 * @param suffixFunction The suffixFunction to set.
	 */
	public void setSuffixFunction(String suffixFunction) {
		this.suffixFunction = suffixFunction;
	}
	/**
	 * @return Returns the suffixGender.
	 */
	public String getSuffixGender() {
		return suffixGender;
	}
	/**
	 * @param suffixGender The suffixGender to set.
	 */
	public void setSuffixGender(String suffixGender) {
		this.suffixGender = suffixGender;
	}
	/**
	 * @return Returns the suffixNumber.
	 */
	public String getSuffixNumber() {
		return suffixNumber;
	}
	/**
	 * @param suffixNumber The suffixNumber to set.
	 */
	public void setSuffixNumber(String suffixNumber) {
		this.suffixNumber = suffixNumber;
	}
	/**
	 * @return Returns the suffixStatus.
	 */
	public String getSuffixStatus() {
		return suffixStatus;
	}
	/**
	 * @param suffixStatus The suffixStatus to set.
	 */
	public void setSuffixStatus(String suffixStatus) {
		this.suffixStatus = suffixStatus;
	}
	/**
	 * @return Returns the surface.
	 */
	public String getSurface() {
		return surface;
	}
	/**
	 * @param surface The surface to set.
	 */
	public void setSurface(String surface) {
		this.surface = surface;
	}
	/**
	 * @return Returns the transliterated.
	 */
	public String getTransliterated() {
		return transliterated;
	}
	/**
	 * @param transliterated The transliterated to set.
	 */
	public void setTransliterated(String transliterated) {
		this.transliterated = transliterated;
	}
	/**
	 * @return Returns the pGN.
	 */
	public String getPGN() {
		return PGN;
	}
	/**
	 * @param pgn The pGN to set.
	 */
	public void setPGN(String pgn) {
		PGN = pgn;
	}
	/**
	 * @return Returns the binyan.
	 */
	public String getBinyan() {
		return binyan;
	}
	/**
	 * @return Returns the root.
	 */
	public String getRoot() {
		return root;
	}
	/**
	 * @return Returns the tense.
	 */
	public String getTense() {
		return tense;
	}
	/**
	 * @param binyan The binyan to set.
	 */
	public void setBinyan(String binyan) {
		this.binyan = binyan;
	}
	/**
	 * @param root The root to set.
	 */
	public void setRoot(String root) {
		this.root = root;
	}
	/**
	 * @param tense The tense to set.
	 */
	public void setTense(String tense) {
		this.tense = tense;
	}
	/**
	 * @param baseGender The baseGender to set.
	 */
	public void setBaseGender(String baseGender) {
		this.baseGender = baseGender;
	}
	/**
	 * @param baseNumber The baseNumber to set.
	 */
	public void setBaseNumber(String baseNumber) {
		this.baseNumber = baseNumber;
	}
	
	
	public String getBaseGender() {
		return baseGender;
	}
	/**
	 * @return Returns the baseNumber.
	 */
	public String getBaseNumber() {
		return baseNumber;
	}
	/**
	 * @return Returns the baseType.
	 */
	
	
	/**
	 * @return Returns the suffixPerson.
	 */
	public String getSuffixPerson() {
		return suffixPerson;
	}
	/**
	 * @param suffixPerson The suffixPerson to set.
	 */
	public void setSuffixPerson(String suffixPerson) {
		this.suffixPerson = suffixPerson;
	}
	/**
	 * @return Returns the accusativeNominativeGender.
	 */
	public String getAccusativeNominativeGender() {
		return accusativeNominativeGender;
	}
	/**
	 * @param accusativeNominativeGender The accusativeNominativeGender to set.
	 */
	public void setAccusativeNominativeGender(String accusativeNominativeGender) {
		this.accusativeNominativeGender = accusativeNominativeGender;
	}
	/**
	 * @return Returns the accusativeNominativeNumber.
	 */
	public String getAccusativeNominativeNumber() {
		return accusativeNominativeNumber;
	}
	/**
	 * @param accusativeNominativeNumber The accusativeNominativeNumber to set.
	 */
	public void setAccusativeNominativeNumber(String accusativeNominativeNumber) {
		this.accusativeNominativeNumber = accusativeNominativeNumber;
	}
	/**
	 * @return Returns the accusativeNominativePerson.
	 */
	public String getAccusativeNominativePerson() {
		return accusativeNominativePerson;
	}
	/**
	 * @param accusativeNominativePerson The accusativeNominativePerson to set.
	 */
	public void setAccusativeNominativePerson(String accusativeNominativePerson) {
		this.accusativeNominativePerson = accusativeNominativePerson;
	}
	/**
	 * @return Returns the accusativeNominativePGN.
	 */
	public String getAccusativeNominativePGN() {
		return accusativeNominativePGN;
	}
	/**
	 * @param accusativeNominativePGN The accusativeNominativePGN to set.
	 */
	public void setAccusativeNominativePGN(String accusativeNominativePGN) {
		this.accusativeNominativePGN = accusativeNominativePGN;
	}
	/**
	 * @return Returns the dottedLexiconItem.
	 */
	public String getDottedLexiconItem() {
		return dottedLexiconItem;
	}
	/**
	 * @param dottedLexiconItem The dottedLexiconItem to set.
	 */
	public void setDottedLexiconItem(String dottedLexiconItem) {
		this.dottedLexiconItem = dottedLexiconItem;
	}
	/**
	 * @return Returns the polarity.
	 */
	public String getPolarity() {
		return polarity;
	}
	/**
	 * @param polarity The polarity to set.
	 */
	public void setPolarity(String polarity) {
		this.polarity = polarity;
	}
	
	/**
	 * @return Returns the consecutive.
	 */
	public boolean isConsecutive() {
		return consecutive;
	}
	/**
	 * @param consecutive The consecutive to set.
	 */
	public void setConsecutive(boolean consecutive) {
		this.consecutive = consecutive;
	}
	
	/**
	 * @return Returns the subId.
	 */
	public int getSubId() {
		return subId;
	}
	/**
	 * @param subId The subId to set.
	 */
	public void setSubId(int subId) {
		this.subId = subId;
	}
	/**
	 * @return Returns the multiWordLen.
	 */
	public int getMultiWordLen() {
		return multiWordLen;
	}
	/**
	 * @param multiWordLen The multiWordLen to set.
	 */
	public void setMultiWordLen(int multiWordLen) {
		this.multiWordLen = multiWordLen;
	}
	
	/**
	 * @return Returns the type.
	 */
	public String getType() {
		return type;
	}
	/**
	 * @param type The type to set.
	 */
	public void setType(String type) {
		this.type = type;
	}
}
