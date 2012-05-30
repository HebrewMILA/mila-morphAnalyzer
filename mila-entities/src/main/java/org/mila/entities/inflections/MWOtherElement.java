package org.mila.entities.inflections;

import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.mila.entities.corpus.DefinitenessType;
import org.mila.entities.corpus.GenderType;
import org.mila.entities.corpus.NumberType;
import org.mila.entities.corpus.RegisterType;
import org.mila.entities.corpus.SpellingType;

@Entity(name = "MWOtherElement")
public class MWOtherElement extends MWElement {
    private static final long serialVersionUID = 1L;

    MWElement formerElement;
    long lexiconId;
    String transliteratedLexiconItem;
    String dottedLexiconItem;
    String undottedLexiconItem;
    String mwTransliterated;
    String mwUndotted;
    String possessive;
    SpellingType spelling;
    RegisterType register;
    GenderType gender;
    NumberType number;
    DefinitenessType definiteness;

    @ManyToOne
    @JoinColumn(nullable = false)
    public MWElement getFormerElement() {
	return formerElement;
    }

    @Basic
    public long getLexiconId() {
	return lexiconId;
    }

    @Basic
    public String getTransliteratedLexiconItem() {
	return transliteratedLexiconItem;
    }

    @Basic
    public String getDottedLexiconItem() {
	return dottedLexiconItem;
    }

    @Basic
    public String getUndottedLexiconItem() {
	return undottedLexiconItem;
    }

    @Basic
    public String getMwTransliterated() {
	return mwTransliterated;
    }

    @Basic
    public String getMwUndotted() {
	return mwUndotted;
    }

    @Basic
    public String getPossessive() {
	return possessive;
    }

    @Basic
    public SpellingType getSpelling() {
	return spelling;
    }

    @Basic
    public RegisterType getRegister() {
	return register;
    }

    public GenderType getGender() {
	return gender;
    }

    public NumberType getNumber() {
	return number;
    }

    public DefinitenessType getDefiniteness() {
	return definiteness;
    }

    public void setFormerElement(MWElement formerElement) {
	this.formerElement = formerElement;
    }

    public void setLexiconId(long lexiconId) {
	this.lexiconId = lexiconId;
    }

    public void setTransliteratedLexiconItem(String transliteratedLexiconItem) {
	this.transliteratedLexiconItem = transliteratedLexiconItem;
    }

    public void setDottedLexiconItem(String dottedLexiconItem) {
	this.dottedLexiconItem = dottedLexiconItem;
    }

    public void setUndottedLexiconItem(String undottedLexiconItem) {
	this.undottedLexiconItem = undottedLexiconItem;
    }

    public void setMwTransliterated(String mwTransliterated) {
	this.mwTransliterated = mwTransliterated;
    }

    public void setMwUndotted(String mwUndotted) {
	this.mwUndotted = mwUndotted;
    }

    public void setPossessive(String possessive) {
	this.possessive = possessive;
    }

    public void setSpelling(SpellingType spelling) {
	this.spelling = spelling;
    }

    public void setRegister(RegisterType register) {
	this.register = register;
    }

    public void setGender(GenderType gender) {
	this.gender = gender;
    }

    public void setNumber(NumberType number) {
	this.number = number;
    }

    public void setDefiniteness(DefinitenessType definiteness) {
	this.definiteness = definiteness;
    }

    @Override
    public int hashCode() {
	final int prime = 31;
	int result = super.hashCode();
	result = prime * result
		+ ((definiteness == null) ? 0 : definiteness.hashCode());
	result = prime
		* result
		+ ((dottedLexiconItem == null) ? 0 : dottedLexiconItem
			.hashCode());
	result = prime * result
		+ ((formerElement == null) ? 0 : formerElement.hashCode());
	result = prime * result + ((gender == null) ? 0 : gender.hashCode());
	result = prime * result + (int) (lexiconId ^ (lexiconId >>> 32));
	result = prime
		* result
		+ ((mwTransliterated == null) ? 0 : mwTransliterated.hashCode());
	result = prime * result
		+ ((mwUndotted == null) ? 0 : mwUndotted.hashCode());
	result = prime * result + ((number == null) ? 0 : number.hashCode());
	result = prime * result
		+ ((possessive == null) ? 0 : possessive.hashCode());
	result = prime * result
		+ ((register == null) ? 0 : register.hashCode());
	result = prime * result
		+ ((spelling == null) ? 0 : spelling.hashCode());
	result = prime
		* result
		+ ((transliteratedLexiconItem == null) ? 0
			: transliteratedLexiconItem.hashCode());
	result = prime
		* result
		+ ((undottedLexiconItem == null) ? 0 : undottedLexiconItem
			.hashCode());
	return result;
    }

    @Override
    public boolean equals(Object obj) {
	if (this == obj)
	    return true;
	if (!super.equals(obj))
	    return false;
	if (!(obj instanceof MWOtherElement))
	    return false;
	MWOtherElement other = (MWOtherElement) obj;
	if (definiteness != other.definiteness)
	    return false;
	if (dottedLexiconItem == null) {
	    if (other.dottedLexiconItem != null)
		return false;
	} else if (!dottedLexiconItem.equals(other.dottedLexiconItem))
	    return false;
	if (formerElement == null) {
	    if (other.formerElement != null)
		return false;
	} else if (!formerElement.equals(other.formerElement))
	    return false;
	if (gender != other.gender)
	    return false;
	if (lexiconId != other.lexiconId)
	    return false;
	if (mwTransliterated == null) {
	    if (other.mwTransliterated != null)
		return false;
	} else if (!mwTransliterated.equals(other.mwTransliterated))
	    return false;
	if (mwUndotted == null) {
	    if (other.mwUndotted != null)
		return false;
	} else if (!mwUndotted.equals(other.mwUndotted))
	    return false;
	if (number != other.number)
	    return false;
	if (possessive == null) {
	    if (other.possessive != null)
		return false;
	} else if (!possessive.equals(other.possessive))
	    return false;
	if (register != other.register)
	    return false;
	if (spelling != other.spelling)
	    return false;
	if (transliteratedLexiconItem == null) {
	    if (other.transliteratedLexiconItem != null)
		return false;
	} else if (!transliteratedLexiconItem
		.equals(other.transliteratedLexiconItem))
	    return false;
	if (undottedLexiconItem == null) {
	    if (other.undottedLexiconItem != null)
		return false;
	} else if (!undottedLexiconItem.equals(other.undottedLexiconItem))
	    return false;
	return true;
    }

}
