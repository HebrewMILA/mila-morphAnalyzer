package lexicon;

import lexicon.contents.EmptyContent;
import lexicon.tools.LexiconUtils;

/**
 * @author syjacob
 * class for getting statictis from out lexicon
 */
public class LexiconStatistic
{
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
    //-------------------------------------------------------------------------------------------------
    public LexiconStatistic()
    {
        nounCount = GetPosCount("noun");
        acronymCount = GetPosCount("acronym");
        adjectiveCount = GetPosCount("adjective");
        verbCount = GetPosCount("verb");
        propernameCount = GetPosCount("propername");
        existentialCount = GetPosCount("existential");
        interjectionCount = GetPosCount("interjection");
        interrogativeCount = GetPosCount("interrogative");
        modalCount = GetPosCount("modal");
        numeralCount = GetPosCount("numeral");
        quantifierCount = GetPosCount("quantifier");
        pronounCount = GetPosCount("pronoun");
        prepositionCount = GetPosCount("preposition");
        titleCount = GetPosCount("title");
        copulaCount = GetPosCount("copula");
        negationCount = GetPosCount("negation");
        wPrefixCount = GetPosCount("wPrefix");
        adverbCount = GetPosCount("adverb");
        conjunctionCount = GetPosCount("conjunction");
        
        noun_exception_typeCount = GetExceptionCount("noun_exception_type");
        adjective_exception_typeCount = GetExceptionCount("acronym_exception_type");
        adverb_exception_typeCount = GetExceptionCount("adverb_exception_type");
        interjection_exception_typeCount = GetExceptionCount("interjection_exception_type");
        interrogative_exception_typeCount = GetExceptionCount("interrogative_exception_type");
        modal_exception_typeCount = GetExceptionCount("modal_exception_type");
        numeral_exception_typeCount = GetExceptionCount("numeral_exception_type");
        preposition_exception_typeCount = GetExceptionCount("preposition_exception_type");
        pronoun_exception_typeCount = GetExceptionCount("pronoun_exception_type");
        propername_exception_typeCount = GetExceptionCount("propername_exception_type");
        quantifier_exception_typeCount = GetExceptionCount("quantifier_exception_type");
        verb_exception_typeCount = GetExceptionCount("verb_exception_type");
    }
    //-------------------------------------------------------------------------------------------------
    private int GetExceptionCount(String tableName)
    {
        EmptyContent content = new EmptyContent();
        String sql = "SELECT * FROM "+tableName;
        return content.GetQueryCount(sql); 
    }
    //-------------------------------------------------------------------------------------------------
    private int GetPosCount(String pos)
    {
        EmptyContent content = new EmptyContent();
        String sql = "SELECT * FROM item WHERE pos ='"+pos+"'";
        return content.GetQueryCount(sql); 
    }
    //-------------------------------------------------------------------------------------------------
    public void setNounCount(int nounCount)
    {
        this.nounCount = nounCount;
    }
    //-------------------------------------------------------------------------------------------------
    public int getNounCount()
    {
        return nounCount;
    }
    //-------------------------------------------------------------------------------------------------
    public void setAdjectiveCount(int adjectiveCount)
    {
        this.adjectiveCount = adjectiveCount;
    }
    //-------------------------------------------------------------------------------------------------
    public int getAdjectiveCount()
    {
        return adjectiveCount;
    }
    //-------------------------------------------------------------------------------------------------
    public void setVerbCount(int verbCount)
    {
        this.verbCount = verbCount;
    }
    //-------------------------------------------------------------------------------------------------
    public int getVerbCount()
    {
        return verbCount;
    }
    //-------------------------------------------------------------------------------------------------
    public void setPropernameCount(int propernameCount)
    {
        this.propernameCount = propernameCount;
    }
    //-------------------------------------------------------------------------------------------------
    public int getPropernameCount()
    {
        return propernameCount;
    }
    //-------------------------------------------------------------------------------------------------
    public void setExistentialCount(int existentialCount)
    {
        this.existentialCount = existentialCount;
    }
    //-------------------------------------------------------------------------------------------------
    public int getExistentialCount()
    {
        return existentialCount;
    }
    //-------------------------------------------------------------------------------------------------
    public void setInterjectionCount(int interjectionCount)
    {
        this.interjectionCount = interjectionCount;
    }
    //-------------------------------------------------------------------------------------------------
    public int getInterjectionCount()
    {
        return interjectionCount;
    }
    //-------------------------------------------------------------------------------------------------
    public void setInterrogativeCount(int interrogativeCount)
    {
        this.interrogativeCount = interrogativeCount;
    }
    //-------------------------------------------------------------------------------------------------
    public int getInterrogativeCount()
    {
        return interrogativeCount;
    }
    //-------------------------------------------------------------------------------------------------
    public void setModalCount(int modalCount)
    {
        this.modalCount = modalCount;
    }
    //-------------------------------------------------------------------------------------------------
    public int getModalCount()
    {
        return modalCount;
    }
    //-------------------------------------------------------------------------------------------------
    public void setNumeralCount(int numeralCount)
    {
        this.numeralCount = numeralCount;
    }
    //-------------------------------------------------------------------------------------------------
    public int getNumeralCount()
    { 
        return numeralCount;
    }
    //-------------------------------------------------------------------------------------------------
    public void setQuantifierCount(int quantifierCount)
    {
        this.quantifierCount = quantifierCount;
    }
    //-------------------------------------------------------------------------------------------------
    public int getQuantifierCount()
    {
        return quantifierCount;
    }
    //-------------------------------------------------------------------------------------------------
    public void setPronounCount(int pronounCount)
    {
        this.pronounCount = pronounCount;
    }
    //-------------------------------------------------------------------------------------------------
    public int getPronounCount()
    {
        return pronounCount;
    }
    //-------------------------------------------------------------------------------------------------
    public void setPrepositionCount(int prepositionCount)
    {
        this.prepositionCount = prepositionCount;
    }
    //-------------------------------------------------------------------------------------------------
    public int getPrepositionCount()
    {
        return prepositionCount;
    }
    //-------------------------------------------------------------------------------------------------
    public void setTitleCount(int titleCount)
    {
        this.titleCount = titleCount;
    }
    //-------------------------------------------------------------------------------------------------
    public int getTitleCount()
    {
        return titleCount;
    }
    //-------------------------------------------------------------------------------------------------
    public void setCopulaCount(int copulaCount)
    {
        this.copulaCount = copulaCount;
    }
    //-------------------------------------------------------------------------------------------------
    public int getCopulaCount()
    {
        return copulaCount;
    }
    //-------------------------------------------------------------------------------------------------
    public void setNegationCount(int negationCount)
    {
        this.negationCount = negationCount;
    }
    //-------------------------------------------------------------------------------------------------
    public int getNegationCount()
    {
        return negationCount;
    }
    //-------------------------------------------------------------------------------------------------
    public void setwPrefixCount(int wPrefixCount)
    {
        this.wPrefixCount = wPrefixCount;
    }
    //-------------------------------------------------------------------------------------------------
    public int getwPrefixCount()
    {
        return wPrefixCount;
    }
    //-------------------------------------------------------------------------------------------------
    public void setAdverbCount(int adverbCount)
    {
        this.adverbCount = adverbCount;
    }
    //-------------------------------------------------------------------------------------------------
    public int getAdverbCount()
    {
        return adverbCount;
    }
    //-------------------------------------------------------------------------------------------------
    public void setConjunctionCount(int conjunctionCount)
    {
        this.conjunctionCount = conjunctionCount;
    }
    //-------------------------------------------------------------------------------------------------
    public int getConjunctionCount()
    {
        return conjunctionCount;
    }
    //-------------------------------------------------------------------------------------------------
    public void setNoun_exception_typeCount(int noun_exception_typeCount)
    {
        this.noun_exception_typeCount = noun_exception_typeCount;
    }
    //-------------------------------------------------------------------------------------------------
    public int getNoun_exception_typeCount()
    {
        return noun_exception_typeCount;
    }
    //-------------------------------------------------------------------------------------------------
    public void setAdjective_exception_typeCount(int adjective_exception_typeCount)
    {
        this.adjective_exception_typeCount = adjective_exception_typeCount;
    }
    //-------------------------------------------------------------------------------------------------
    public int getAdjective_exception_typeCount()
    {
        return adjective_exception_typeCount;
    }
    //-------------------------------------------------------------------------------------------------
    public void setAdverb_exception_typeCount(int adverb_exception_typeCount)
    {
        this.adverb_exception_typeCount = adverb_exception_typeCount;
    }
    //-------------------------------------------------------------------------------------------------
    public int getAdverb_exception_typeCount()
    {
        return adverb_exception_typeCount;
    }
    //-------------------------------------------------------------------------------------------------
    public void setInterjection_exception_typeCount(int interjection_exception_typeCount)
    {
        this.interjection_exception_typeCount = interjection_exception_typeCount;
    }
    //-------------------------------------------------------------------------------------------------
    public int getInterjection_exception_typeCount()
    {
        return interjection_exception_typeCount;
    }
    //-------------------------------------------------------------------------------------------------
    public void setInterrogative_exception_typeCount(int interrogative_exception_typeCount)
    {
        this.interrogative_exception_typeCount = interrogative_exception_typeCount;
    }
    //-------------------------------------------------------------------------------------------------
    public int getInterrogative_exception_typeCount()
    {
        return interrogative_exception_typeCount;
    }
    //-------------------------------------------------------------------------------------------------
    public void setModal_exception_typeCount(int modal_exception_typeCount)
    {
        this.modal_exception_typeCount = modal_exception_typeCount;
    }
    //-------------------------------------------------------------------------------------------------
    public int getModal_exception_typeCount()
    {
        return modal_exception_typeCount;
    }
    //-------------------------------------------------------------------------------------------------
    public void setNumeral_exception_typeCount(int numeral_exception_typeCount)
    {
        this.numeral_exception_typeCount = numeral_exception_typeCount;
    }
    //-------------------------------------------------------------------------------------------------
    public int getNumeral_exception_typeCount()
    {
        return numeral_exception_typeCount;
    }
    //-------------------------------------------------------------------------------------------------
    public void setPreposition_exception_typeCount(int preposition_exception_typeCount)
    {
        this.preposition_exception_typeCount = preposition_exception_typeCount;
    }
    //-------------------------------------------------------------------------------------------------
    public int getPreposition_exception_typeCount()
    {
        return preposition_exception_typeCount;
    }
    //-------------------------------------------------------------------------------------------------
    public void setPronoun_exception_typeCount(int pronoun_exception_typeCount)
    {
        this.pronoun_exception_typeCount = pronoun_exception_typeCount;
    }
    //-------------------------------------------------------------------------------------------------
    public int getPronoun_exception_typeCount()
    {
        return pronoun_exception_typeCount;
    }
    //-------------------------------------------------------------------------------------------------
    public void setPropername_exception_typeCount(int propername_exception_typeCount)
    {
        this.propername_exception_typeCount = propername_exception_typeCount;
    }
    //-------------------------------------------------------------------------------------------------
    public int getPropername_exception_typeCount()
    {
        return propername_exception_typeCount;
    }
    //-------------------------------------------------------------------------------------------------
    public void setQuantifier_exception_typeCount(int quantifier_exception_typeCount)
    {
        this.quantifier_exception_typeCount = quantifier_exception_typeCount;
    }
    //-------------------------------------------------------------------------------------------------
    public int getQuantifier_exception_typeCount()
    {
        return quantifier_exception_typeCount;
    }
    //-------------------------------------------------------------------------------------------------
    public void setVerb_exception_typeCount(int verb_exception_typeCount)
    {
        this.verb_exception_typeCount = verb_exception_typeCount;
    }
    //-------------------------------------------------------------------------------------------------
    public int getVerb_exception_typeCount()
    {
        return verb_exception_typeCount;
    }
    //-------------------------------------------------------------------------------------------------
    public static void main(String[] args) 
    {
        LexiconStatistic statistics = new LexiconStatistic();
        System.out.println(statistics.getNounCount());
    }
    //-------------------------------------------------------------------------------------------------
    void setAcronymCount(int acronymCount)
    {
        this.acronymCount = acronymCount;
    }
    //-------------------------------------------------------------------------------------------------
    int getAcronymCount()
    {
        return acronymCount;
    }
    void setAcronym_exception_typeCount(int acronym_exception_typeCount)
    {
        this.acronym_exception_typeCount = acronym_exception_typeCount;
    }
    int getAcronym_exception_typeCount()
    {
        return acronym_exception_typeCount;
    }
}
