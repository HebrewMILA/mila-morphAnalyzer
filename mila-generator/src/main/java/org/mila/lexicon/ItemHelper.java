package org.mila.lexicon;

import org.mila.entities.lexicon.Item;

public class ItemHelper {
    public static SubItem getSubItem(Item item) {
	switch (ItemHelper.getSubItemEnum(item)) {
	case ACRONYM:
	    return item.getAcronym();
	case ADJECTIVE:
	    return item.getAdjective();
	case ADVERB:
	    return item.getAdverb();
	case CONJUCTION:
	    return item.getConjunction();
	case COPULA:
	    return item.getCopula();
	case EXISTENTIAL:
	    return item.getExistential();
	case IMPERSONAL:
	    return item.getImpersonal();
	case INTERJECTION:
	    return item.getInterjection();
	case INTERROGATIVE:
	    return item.getInterrogative();
	case MODAL:
	    return item.getModal();
	case MULTI_WORD_FROZEN:
	    return item.getMultiWordFrozen();
	case MULTI_WORD_NOUN:
	    return item.getMultiWordNoun();
	case MULTI_WORD_NOUN_ADJECTIVE:
	    return item.getMultiWordNounAdjective();
	case MULTI_WORD_PREPOSITION:
	    return item.getMultiWordPreposition();
	case MULTI_WORD_VERB_PHRASE:
	    return item.getMultiWordVerbPhrase();
	case NEGATION:
	    return item.getNegation();
	case NOUN:
	    return item.getNoun();
	case NUMERAL:
	    return item.getNumeral();
	case PREPOSITION:
	    return item.getPreposition();
	case PRONOUN:
	    return item.getPronoun();
	case PROPER_NAME:
	    return item.getProperName();
	case QUANTIFIER:
	    return item.getQuantifier();
	case TITLE:
	    return item.getTitle();
	case VERB:
	    return item.getVerb();
	case W_PREFIX:
	    return item.getWPrefix();
	default:
	    throw new SwitchException();
	}

    }

    public static SubItemEnum getSubItemEnum(Item item) {
	/* Assumption - Only one of these can be true at any given time. */
	if (item.isSetAcronym())
	    return SubItemEnum.ACRONYM;
	if (item.isSetAdjective())
	    return SubItemEnum.ADJECTIVE;
	if (item.isSetAdverb())
	    return SubItemEnum.ADVERB;
	if (item.isSetConjunction())
	    return SubItemEnum.CONJUCTION;
	if (item.isSetCopula())
	    return SubItemEnum.COPULA;
	if (item.isSetExistential())
	    return SubItemEnum.EXISTENTIAL;
	if (item.isSetImpersonal())
	    return SubItemEnum.IMPERSONAL;
	if (item.isSetInterjection())
	    return SubItemEnum.INTERJECTION;
	if (item.isSetInterrogative())
	    return SubItemEnum.INTERROGATIVE;
	if (item.isSetModal())
	    return SubItemEnum.MODAL;
	if (item.isSetMultiWordFrozen())
	    return SubItemEnum.MULTI_WORD_FROZEN;
	if (item.isSetMultiWordNoun())
	    return SubItemEnum.MULTI_WORD_NOUN;
	if (item.isSetMultiWordNounAdjective())
	    return SubItemEnum.MULTI_WORD_NOUN_ADJECTIVE;
	if (item.isSetMultiWordPreposition())
	    return SubItemEnum.MULTI_WORD_PREPOSITION;
	if (item.isSetMultiWordVerbPhrase())
	    return SubItemEnum.MULTI_WORD_VERB_PHRASE;
	if (item.isSetNegation())
	    return SubItemEnum.NEGATION;
	if (item.isSetNoun())
	    return SubItemEnum.NOUN;
	if (item.isSetNumeral())
	    return SubItemEnum.NUMERAL;
	if (item.isSetPreposition())
	    return SubItemEnum.PREPOSITION;
	if (item.isSetPronoun())
	    return SubItemEnum.PRONOUN;
	if (item.isSetProperName())
	    return SubItemEnum.PROPER_NAME;
	if (item.isSetQuantifier())
	    return SubItemEnum.QUANTIFIER;
	if (item.isSetTitle())
	    return SubItemEnum.TITLE;
	if (item.isSetVerb())
	    return SubItemEnum.VERB;
	if (item.isSetWPrefix())
	    return SubItemEnum.W_PREFIX;

	throw new SwitchException();
    }
}

class SwitchException extends RuntimeException {
    private static final long serialVersionUID = -8704399381177684939L;
}