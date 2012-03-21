package lexicon;

import lexicon.contents.EmptyContent;

/**
 * @author syjacob class for getting statictis from out lexicon
 */
public class LexiconStatistic {
	// -------------------------------------------------------------------------------------------------
	public static void main(String[] args) {
		final LexiconStatistic statistics = new LexiconStatistic();
		System.out.println(statistics.getNounCount());
	}

	private int nounCount = 0;
	private int acronymCount = 0;
	private int adjectiveCount = 0;
	private int verbCount = 0;
	private int propernameCount = 0;
	private int existentialCount = 0;
	private int interjectionCount = 0;
	private int interrogativeCount = 0;
	private int modalCount = 0;
	private int numeralCount = 0;
	private int quantifierCount = 0;
	private int pronounCount = 0;
	private int prepositionCount = 0;
	private int titleCount = 0;
	private int copulaCount = 0;
	private int negationCount = 0;
	private int wPrefixCount = 0;
	private int adverbCount = 0;
	private int conjunctionCount = 0;
	private int noun_exception_typeCount = 0;
	private int acronym_exception_typeCount = 0;
	private int adjective_exception_typeCount = 0;
	private int adverb_exception_typeCount = 0;
	private int interjection_exception_typeCount = 0;
	private int interrogative_exception_typeCount = 0;
	private int modal_exception_typeCount = 0;
	private int numeral_exception_typeCount = 0;
	private int preposition_exception_typeCount = 0;
	private int pronoun_exception_typeCount = 0;
	private int propername_exception_typeCount = 0;
	private int quantifier_exception_typeCount = 0;

	private int verb_exception_typeCount = 0;

	// -------------------------------------------------------------------------------------------------
	public LexiconStatistic() {
		this.nounCount = this.GetPosCount("noun");
		this.acronymCount = this.GetPosCount("acronym");
		this.adjectiveCount = this.GetPosCount("adjective");
		this.verbCount = this.GetPosCount("verb");
		this.propernameCount = this.GetPosCount("propername");
		this.existentialCount = this.GetPosCount("existential");
		this.interjectionCount = this.GetPosCount("interjection");
		this.interrogativeCount = this.GetPosCount("interrogative");
		this.modalCount = this.GetPosCount("modal");
		this.numeralCount = this.GetPosCount("numeral");
		this.quantifierCount = this.GetPosCount("quantifier");
		this.pronounCount = this.GetPosCount("pronoun");
		this.prepositionCount = this.GetPosCount("preposition");
		this.titleCount = this.GetPosCount("title");
		this.copulaCount = this.GetPosCount("copula");
		this.negationCount = this.GetPosCount("negation");
		this.wPrefixCount = this.GetPosCount("wPrefix");
		this.adverbCount = this.GetPosCount("adverb");
		this.conjunctionCount = this.GetPosCount("conjunction");

		this.noun_exception_typeCount = this
				.GetExceptionCount("noun_exception_type");
		this.adjective_exception_typeCount = this
				.GetExceptionCount("acronym_exception_type");
		this.adverb_exception_typeCount = this
				.GetExceptionCount("adverb_exception_type");
		this.interjection_exception_typeCount = this
				.GetExceptionCount("interjection_exception_type");
		this.interrogative_exception_typeCount = this
				.GetExceptionCount("interrogative_exception_type");
		this.modal_exception_typeCount = this
				.GetExceptionCount("modal_exception_type");
		this.numeral_exception_typeCount = this
				.GetExceptionCount("numeral_exception_type");
		this.preposition_exception_typeCount = this
				.GetExceptionCount("preposition_exception_type");
		this.pronoun_exception_typeCount = this
				.GetExceptionCount("pronoun_exception_type");
		this.propername_exception_typeCount = this
				.GetExceptionCount("propername_exception_type");
		this.quantifier_exception_typeCount = this
				.GetExceptionCount("quantifier_exception_type");
		this.verb_exception_typeCount = this
				.GetExceptionCount("verb_exception_type");
	}

	int getAcronym_exception_typeCount() {
		return this.acronym_exception_typeCount;
	}

	// -------------------------------------------------------------------------------------------------
	int getAcronymCount() {
		return this.acronymCount;
	}

	// -------------------------------------------------------------------------------------------------
	public int getAdjective_exception_typeCount() {
		return this.adjective_exception_typeCount;
	}

	// -------------------------------------------------------------------------------------------------
	public int getAdjectiveCount() {
		return this.adjectiveCount;
	}

	// -------------------------------------------------------------------------------------------------
	public int getAdverb_exception_typeCount() {
		return this.adverb_exception_typeCount;
	}

	// -------------------------------------------------------------------------------------------------
	public int getAdverbCount() {
		return this.adverbCount;
	}

	// -------------------------------------------------------------------------------------------------
	public int getConjunctionCount() {
		return this.conjunctionCount;
	}

	// -------------------------------------------------------------------------------------------------
	public int getCopulaCount() {
		return this.copulaCount;
	}

	// -------------------------------------------------------------------------------------------------
	private int GetExceptionCount(String tableName) {
		final EmptyContent content = new EmptyContent();
		final String sql = "SELECT * FROM " + tableName;
		return content.GetQueryCount(sql);
	}

	// -------------------------------------------------------------------------------------------------
	public int getExistentialCount() {
		return this.existentialCount;
	}

	// -------------------------------------------------------------------------------------------------
	public int getInterjection_exception_typeCount() {
		return this.interjection_exception_typeCount;
	}

	// -------------------------------------------------------------------------------------------------
	public int getInterjectionCount() {
		return this.interjectionCount;
	}

	// -------------------------------------------------------------------------------------------------
	public int getInterrogative_exception_typeCount() {
		return this.interrogative_exception_typeCount;
	}

	// -------------------------------------------------------------------------------------------------
	public int getInterrogativeCount() {
		return this.interrogativeCount;
	}

	// -------------------------------------------------------------------------------------------------
	public int getModal_exception_typeCount() {
		return this.modal_exception_typeCount;
	}

	// -------------------------------------------------------------------------------------------------
	public int getModalCount() {
		return this.modalCount;
	}

	// -------------------------------------------------------------------------------------------------
	public int getNegationCount() {
		return this.negationCount;
	}

	// -------------------------------------------------------------------------------------------------
	public int getNoun_exception_typeCount() {
		return this.noun_exception_typeCount;
	}

	// -------------------------------------------------------------------------------------------------
	public int getNounCount() {
		return this.nounCount;
	}

	// -------------------------------------------------------------------------------------------------
	public int getNumeral_exception_typeCount() {
		return this.numeral_exception_typeCount;
	}

	// -------------------------------------------------------------------------------------------------
	public int getNumeralCount() {
		return this.numeralCount;
	}

	// -------------------------------------------------------------------------------------------------
	private int GetPosCount(String pos) {
		final EmptyContent content = new EmptyContent();
		final String sql = "SELECT * FROM item WHERE pos ='" + pos + "'";
		return content.GetQueryCount(sql);
	}

	// -------------------------------------------------------------------------------------------------
	public int getPreposition_exception_typeCount() {
		return this.preposition_exception_typeCount;
	}

	// -------------------------------------------------------------------------------------------------
	public int getPrepositionCount() {
		return this.prepositionCount;
	}

	// -------------------------------------------------------------------------------------------------
	public int getPronoun_exception_typeCount() {
		return this.pronoun_exception_typeCount;
	}

	// -------------------------------------------------------------------------------------------------
	public int getPronounCount() {
		return this.pronounCount;
	}

	// -------------------------------------------------------------------------------------------------
	public int getPropername_exception_typeCount() {
		return this.propername_exception_typeCount;
	}

	// -------------------------------------------------------------------------------------------------
	public int getPropernameCount() {
		return this.propernameCount;
	}

	// -------------------------------------------------------------------------------------------------
	public int getQuantifier_exception_typeCount() {
		return this.quantifier_exception_typeCount;
	}

	// -------------------------------------------------------------------------------------------------
	public int getQuantifierCount() {
		return this.quantifierCount;
	}

	// -------------------------------------------------------------------------------------------------
	public int getTitleCount() {
		return this.titleCount;
	}

	// -------------------------------------------------------------------------------------------------
	public int getVerb_exception_typeCount() {
		return this.verb_exception_typeCount;
	}

	// -------------------------------------------------------------------------------------------------
	public int getVerbCount() {
		return this.verbCount;
	}

	// -------------------------------------------------------------------------------------------------
	public int getwPrefixCount() {
		return this.wPrefixCount;
	}

	void setAcronym_exception_typeCount(int acronym_exception_typeCount) {
		this.acronym_exception_typeCount = acronym_exception_typeCount;
	}

	// -------------------------------------------------------------------------------------------------
	void setAcronymCount(int acronymCount) {
		this.acronymCount = acronymCount;
	}

	// -------------------------------------------------------------------------------------------------
	public void setAdjective_exception_typeCount(
			int adjective_exception_typeCount) {
		this.adjective_exception_typeCount = adjective_exception_typeCount;
	}

	// -------------------------------------------------------------------------------------------------
	public void setAdjectiveCount(int adjectiveCount) {
		this.adjectiveCount = adjectiveCount;
	}

	// -------------------------------------------------------------------------------------------------
	public void setAdverb_exception_typeCount(int adverb_exception_typeCount) {
		this.adverb_exception_typeCount = adverb_exception_typeCount;
	}

	// -------------------------------------------------------------------------------------------------
	public void setAdverbCount(int adverbCount) {
		this.adverbCount = adverbCount;
	}

	// -------------------------------------------------------------------------------------------------
	public void setConjunctionCount(int conjunctionCount) {
		this.conjunctionCount = conjunctionCount;
	}

	// -------------------------------------------------------------------------------------------------
	public void setCopulaCount(int copulaCount) {
		this.copulaCount = copulaCount;
	}

	// -------------------------------------------------------------------------------------------------
	public void setExistentialCount(int existentialCount) {
		this.existentialCount = existentialCount;
	}

	// -------------------------------------------------------------------------------------------------
	public void setInterjection_exception_typeCount(
			int interjection_exception_typeCount) {
		this.interjection_exception_typeCount = interjection_exception_typeCount;
	}

	// -------------------------------------------------------------------------------------------------
	public void setInterjectionCount(int interjectionCount) {
		this.interjectionCount = interjectionCount;
	}

	// -------------------------------------------------------------------------------------------------
	public void setInterrogative_exception_typeCount(
			int interrogative_exception_typeCount) {
		this.interrogative_exception_typeCount = interrogative_exception_typeCount;
	}

	// -------------------------------------------------------------------------------------------------
	public void setInterrogativeCount(int interrogativeCount) {
		this.interrogativeCount = interrogativeCount;
	}

	// -------------------------------------------------------------------------------------------------
	public void setModal_exception_typeCount(int modal_exception_typeCount) {
		this.modal_exception_typeCount = modal_exception_typeCount;
	}

	// -------------------------------------------------------------------------------------------------
	public void setModalCount(int modalCount) {
		this.modalCount = modalCount;
	}

	// -------------------------------------------------------------------------------------------------
	public void setNegationCount(int negationCount) {
		this.negationCount = negationCount;
	}

	// -------------------------------------------------------------------------------------------------
	public void setNoun_exception_typeCount(int noun_exception_typeCount) {
		this.noun_exception_typeCount = noun_exception_typeCount;
	}

	// -------------------------------------------------------------------------------------------------
	public void setNounCount(int nounCount) {
		this.nounCount = nounCount;
	}

	// -------------------------------------------------------------------------------------------------
	public void setNumeral_exception_typeCount(int numeral_exception_typeCount) {
		this.numeral_exception_typeCount = numeral_exception_typeCount;
	}

	// -------------------------------------------------------------------------------------------------
	public void setNumeralCount(int numeralCount) {
		this.numeralCount = numeralCount;
	}

	// -------------------------------------------------------------------------------------------------
	public void setPreposition_exception_typeCount(
			int preposition_exception_typeCount) {
		this.preposition_exception_typeCount = preposition_exception_typeCount;
	}

	// -------------------------------------------------------------------------------------------------
	public void setPrepositionCount(int prepositionCount) {
		this.prepositionCount = prepositionCount;
	}

	// -------------------------------------------------------------------------------------------------
	public void setPronoun_exception_typeCount(int pronoun_exception_typeCount) {
		this.pronoun_exception_typeCount = pronoun_exception_typeCount;
	}

	// -------------------------------------------------------------------------------------------------
	public void setPronounCount(int pronounCount) {
		this.pronounCount = pronounCount;
	}

	// -------------------------------------------------------------------------------------------------
	public void setPropername_exception_typeCount(
			int propername_exception_typeCount) {
		this.propername_exception_typeCount = propername_exception_typeCount;
	}

	// -------------------------------------------------------------------------------------------------
	public void setPropernameCount(int propernameCount) {
		this.propernameCount = propernameCount;
	}

	// -------------------------------------------------------------------------------------------------
	public void setQuantifier_exception_typeCount(
			int quantifier_exception_typeCount) {
		this.quantifier_exception_typeCount = quantifier_exception_typeCount;
	}

	// -------------------------------------------------------------------------------------------------
	public void setQuantifierCount(int quantifierCount) {
		this.quantifierCount = quantifierCount;
	}

	// -------------------------------------------------------------------------------------------------
	public void setTitleCount(int titleCount) {
		this.titleCount = titleCount;
	}

	// -------------------------------------------------------------------------------------------------
	public void setVerb_exception_typeCount(int verb_exception_typeCount) {
		this.verb_exception_typeCount = verb_exception_typeCount;
	}

	// -------------------------------------------------------------------------------------------------
	public void setVerbCount(int verbCount) {
		this.verbCount = verbCount;
	}

	// -------------------------------------------------------------------------------------------------
	public void setwPrefixCount(int wPrefixCount) {
		this.wPrefixCount = wPrefixCount;
	}
}
