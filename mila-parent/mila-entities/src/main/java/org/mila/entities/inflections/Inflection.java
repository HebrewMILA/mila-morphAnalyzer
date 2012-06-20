package org.mila.entities.inflections;

import java.io.Serializable;

import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.mila.entities.corpus.BinyanType;
import org.mila.entities.corpus.DefinitenessType;
import org.mila.entities.corpus.GenderType;
import org.mila.entities.corpus.PrefixTransliteratedType;
import org.mila.entities.corpus.RegisterType;
import org.mila.entities.corpus.SuffixFunctionType;
import org.mila.entities.corpus.TenseType;
import org.mila.entities.corpus.TriStateType;

@Entity(name = "Inflection")
@Table(name = "INFLECTIONS")
public class Inflection implements Serializable {
    private static final long serialVersionUID = 1L;

    protected long id;

    /* Maybe move all these guys to a different (embeddable?) class */
    protected DefinitenessType baseDefiniteness;
    protected GenderType baseGender;
    protected long baseLexiconPointer;
    protected String baseNumber;
    protected String basePerson;
    protected String basePos;
    protected String baseTransliteratedLItem;
    protected String baseUndottedLItem;

    protected BinyanType binyan;
    protected String dottedLexiconItem;
    protected boolean hebForeign;
    protected String PGN;
    protected boolean polarity;
    protected PrefixTransliteratedType prefix;
    protected RegisterType register;
    protected String root;
    protected String spelling;
    protected SuffixFunctionType suffixFunction;
    protected TriStateType suffixStatus;
    protected String surface;
    protected TenseType tense;
    protected String transliterated;
    protected String type;
    protected String value;

    @Override
    public boolean equals(Object obj) {
	if (this == obj)
	    return true;
	if (obj == null)
	    return false;
	if (getClass() != obj.getClass())
	    return false;
	Inflection other = (Inflection) obj;
	if (PGN == null) {
	    if (other.PGN != null)
		return false;
	} else if (!PGN.equals(other.PGN))
	    return false;
	if (baseDefiniteness != other.baseDefiniteness)
	    return false;
	if (baseGender != other.baseGender)
	    return false;
	if (baseLexiconPointer != other.baseLexiconPointer)
	    return false;
	if (baseNumber == null) {
	    if (other.baseNumber != null)
		return false;
	} else if (!baseNumber.equals(other.baseNumber))
	    return false;
	if (basePerson == null) {
	    if (other.basePerson != null)
		return false;
	} else if (!basePerson.equals(other.basePerson))
	    return false;
	if (basePos == null) {
	    if (other.basePos != null)
		return false;
	} else if (!basePos.equals(other.basePos))
	    return false;
	if (baseTransliteratedLItem == null) {
	    if (other.baseTransliteratedLItem != null)
		return false;
	} else if (!baseTransliteratedLItem
		.equals(other.baseTransliteratedLItem))
	    return false;
	if (baseUndottedLItem == null) {
	    if (other.baseUndottedLItem != null)
		return false;
	} else if (!baseUndottedLItem.equals(other.baseUndottedLItem))
	    return false;
	if (binyan == null) {
	    if (other.binyan != null)
		return false;
	} else if (!binyan.equals(other.binyan))
	    return false;
	if (dottedLexiconItem == null) {
	    if (other.dottedLexiconItem != null)
		return false;
	} else if (!dottedLexiconItem.equals(other.dottedLexiconItem))
	    return false;
	if (hebForeign != other.hebForeign)
	    return false;
	if (id != other.id)
	    return false;
	if (polarity != other.polarity)
	    return false;
	if (prefix != other.prefix)
	    return false;
	if (register != other.register)
	    return false;
	if (root == null) {
	    if (other.root != null)
		return false;
	} else if (!root.equals(other.root))
	    return false;
	if (spelling == null) {
	    if (other.spelling != null)
		return false;
	} else if (!spelling.equals(other.spelling))
	    return false;
	if (suffixFunction != other.suffixFunction)
	    return false;
	if (suffixStatus != other.suffixStatus)
	    return false;
	if (surface == null) {
	    if (other.surface != null)
		return false;
	} else if (!surface.equals(other.surface))
	    return false;
	if (tense != other.tense)
	    return false;
	if (transliterated == null) {
	    if (other.transliterated != null)
		return false;
	} else if (!transliterated.equals(other.transliterated))
	    return false;
	if (type == null) {
	    if (other.type != null)
		return false;
	} else if (!type.equals(other.type))
	    return false;
	if (value == null) {
	    if (other.value != null)
		return false;
	} else if (!value.equals(other.value))
	    return false;
	return true;
    }

    @Basic
    public DefinitenessType getBaseDefiniteness() {
	return baseDefiniteness;
    }

    @Basic
    public GenderType getBaseGender() {
	return baseGender;
    }

    @Basic
    public long getBaseLexiconPointer() {
	return baseLexiconPointer;
    }

    @Basic
    public String getBaseNumber() {
	return baseNumber;
    }

    @Basic
    public String getBasePerson() {
	return basePerson;
    }

    @Basic
    public String getBasePos() {
	return basePos;
    }

    @Basic
    public String getBaseTransliteratedLItem() {
	return baseTransliteratedLItem;
    }

    @Basic
    public String getBaseUndottedLItem() {
	return baseUndottedLItem;
    }

    @Basic
    public BinyanType getBinyan() {
	return binyan;
    }

    @Basic
    public String getDottedLexiconItem() {
	return dottedLexiconItem;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public long getId() {
	return id;
    }

    @Basic
    public String getPGN() {
	return PGN;
    }

    public PrefixTransliteratedType getPrefix() {
	return prefix;
    }

    public RegisterType getRegister() {
	return register;
    }

    public String getRoot() {
	return root;
    }

    public String getSpelling() {
	return spelling;
    }

    public SuffixFunctionType getSuffixFunction() {
	return suffixFunction;
    }

    public TriStateType getSuffixStatus() {
	return suffixStatus;
    }

    public String getSurface() {
	return surface;
    }

    public TenseType getTense() {
	return tense;
    }

    public String getTransliterated() {
	return transliterated;
    }

    public String getType() {
	return type;
    }

    public String getValue() {
	return value;
    }

    @Override
    @Transient
    public int hashCode() {
	final int prime = 31;
	int result = 1;
	result = prime * result + ((PGN == null) ? 0 : PGN.hashCode());
	result = prime
		* result
		+ ((baseDefiniteness == null) ? 0 : baseDefiniteness.hashCode());
	result = prime * result
		+ ((baseGender == null) ? 0 : baseGender.hashCode());
	result = prime * result
		+ (int) (baseLexiconPointer ^ (baseLexiconPointer >>> 32));
	result = prime * result
		+ ((baseNumber == null) ? 0 : baseNumber.hashCode());
	result = prime * result
		+ ((basePerson == null) ? 0 : basePerson.hashCode());
	result = prime * result + ((basePos == null) ? 0 : basePos.hashCode());
	result = prime
		* result
		+ ((baseTransliteratedLItem == null) ? 0
			: baseTransliteratedLItem.hashCode());
	result = prime
		* result
		+ ((baseUndottedLItem == null) ? 0 : baseUndottedLItem
			.hashCode());
	result = prime * result + ((binyan == null) ? 0 : binyan.hashCode());
	result = prime
		* result
		+ ((dottedLexiconItem == null) ? 0 : dottedLexiconItem
			.hashCode());
	result = prime * result + (hebForeign ? 1231 : 1237);
	result = prime * result + (int) (id ^ (id >>> 32));
	result = prime * result + (polarity ? 1231 : 1237);
	result = prime * result + ((prefix == null) ? 0 : prefix.hashCode());
	result = prime * result
		+ ((register == null) ? 0 : register.hashCode());
	result = prime * result + ((root == null) ? 0 : root.hashCode());
	result = prime * result
		+ ((spelling == null) ? 0 : spelling.hashCode());
	result = prime * result
		+ ((suffixFunction == null) ? 0 : suffixFunction.hashCode());
	result = prime * result
		+ ((suffixStatus == null) ? 0 : suffixStatus.hashCode());
	result = prime * result + ((surface == null) ? 0 : surface.hashCode());
	result = prime * result + ((tense == null) ? 0 : tense.hashCode());
	result = prime * result
		+ ((transliterated == null) ? 0 : transliterated.hashCode());
	result = prime * result + ((type == null) ? 0 : type.hashCode());
	result = prime * result + ((value == null) ? 0 : value.hashCode());
	return result;
    }

    @Basic
    public boolean isHebForeign() {
	return hebForeign;
    }

    @Basic
    public boolean isPolarity() {
	return polarity;
    }

    public void setBaseDefiniteness(DefinitenessType baseDefiniteness) {
	this.baseDefiniteness = baseDefiniteness;
    }

    public void setBaseGender(GenderType baseGender) {
	this.baseGender = baseGender;
    }

    public void setBaseLexiconPointer(long baseLexiconPointer) {
	this.baseLexiconPointer = baseLexiconPointer;
    }

    public void setBaseNumber(String baseNumber) {
	this.baseNumber = baseNumber;
    }

    public void setBasePerson(String basePerson) {
	this.basePerson = basePerson;
    }

    public void setBasePos(String basePos) {
	this.basePos = basePos;
    }

    public void setBaseTransliteratedLItem(String baseTransliteratedLItem) {
	this.baseTransliteratedLItem = baseTransliteratedLItem;
    }

    public void setBaseUndottedLItem(String baseUndottedLItem) {
	this.baseUndottedLItem = baseUndottedLItem;
    }

    public void setBinyan(BinyanType binyan) {
	this.binyan = binyan;
    }

    public void setDottedLexiconItem(String dottedLexiconItem) {
	this.dottedLexiconItem = dottedLexiconItem;
    }

    public void setHebForeign(boolean hebForeign) {
	this.hebForeign = hebForeign;
    }

    public void setId(long id) {
	this.id = id;
    }

    public void setPGN(String pGN) {
	PGN = pGN;
    }

    public void setPolarity(boolean polarity) {
	this.polarity = polarity;
    }

    public void setPrefix(PrefixTransliteratedType prefix) {
	this.prefix = prefix;
    }

    public void setRegister(RegisterType register) {
	this.register = register;
    }

    public void setRoot(String root) {
	this.root = root;
    }

    public void setSpelling(String spelling) {
	this.spelling = spelling;
    }

    public void setSuffixFunction(SuffixFunctionType suffixFunction) {
	this.suffixFunction = suffixFunction;
    }

    public void setSuffixStatus(TriStateType suffixStatus) {
	this.suffixStatus = suffixStatus;
    }

    public void setSurface(String surface) {
	this.surface = surface;
    }

    public void setTense(TenseType tense) {
	this.tense = tense;
    }

    public void setTransliterated(String transliterated) {
	this.transliterated = transliterated;
    }

    public void setType(String type) {
	this.type = type;
    }

    public void setValue(String value) {
	this.value = value;
    }
}
