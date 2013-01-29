package org.mila.entities.inflections;

import java.io.Serializable;

import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.eclipse.persistence.annotations.Cache;
import org.eclipse.persistence.annotations.CacheType;
import org.mila.entities.corpus.BinyanType;
import org.mila.entities.corpus.DefinitenessType;
import org.mila.entities.corpus.GenderType;
import org.mila.entities.corpus.NumberType;
import org.mila.entities.corpus.PolarityType;
import org.mila.entities.corpus.PrefixTransliteratedType;
import org.mila.entities.corpus.RegisterType;
import org.mila.entities.corpus.SpellingType;
import org.mila.entities.corpus.SuffixFunctionType;
import org.mila.entities.corpus.TenseType;
import org.mila.entities.corpus.TriStateType;

@Entity(name = "Inflection")
@Table(name = "INFLECTIONS")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@Cache(type = CacheType.SOFT_WEAK)
public class Inflection implements Serializable, PersistableInflection {
    private static final long serialVersionUID = 1L;

    protected long id;

    /* Maybe move all these guys to a different (embeddable?) class */
    protected DefinitenessType baseDefiniteness;
    protected GenderType baseGender;
    protected String baseLexiconPointer;
    protected NumberType baseNumber;
    protected String basePerson;
    protected String basePos;
    protected String baseTransliteratedLItem;
    protected String baseUndottedLItem;

    protected BinyanType binyan;
    protected String dottedLexiconItem;
    protected boolean hebForeign;
    protected String PGN;
    protected PolarityType polarity;
    protected PrefixTransliteratedType prefix;
    protected RegisterType register;
    protected String root;
    protected SpellingType spelling;
    protected SuffixFunctionType suffixFunction;
    protected TriStateType suffixStatus;
    protected String surface;
    protected TenseType tense;
    protected String transliterated;
    protected String type;
    protected String value;

    @Basic
    public DefinitenessType getBaseDefiniteness() {
	return baseDefiniteness;
    }

    @Basic
    public GenderType getBaseGender() {
	return baseGender;
    }

    @Basic
    public String getBaseLexiconPointer() {
	return baseLexiconPointer;
    }

    @Basic
    public NumberType getBaseNumber() {
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

    @Basic
    public PrefixTransliteratedType getPrefix() {
	return prefix;
    }

    @Basic
    public RegisterType getRegister() {
	return register;
    }

    @Basic
    public String getRoot() {
	return root;
    }

    @Basic
    public SpellingType getSpelling() {
	return spelling;
    }

    @Basic
    public SuffixFunctionType getSuffixFunction() {
	return suffixFunction;
    }

    @Basic
    public TriStateType getSuffixStatus() {
	return suffixStatus;
    }

    @Basic
    public String getSurface() {
	return surface;
    }

    @Basic
    public TenseType getTense() {
	return tense;
    }

    @Basic
    public String getTransliterated() {
	return transliterated;
    }

    @Basic
    public String getType() {
	return type;
    }

    @Basic
    public String getValue() {
	return value;
    }

    @Basic
    public boolean isHebForeign() {
	return hebForeign;
    }

    @Basic
    public PolarityType isPolarity() {
	return polarity;
    }

    public void setBaseDefiniteness(DefinitenessType baseDefiniteness) {
	this.baseDefiniteness = baseDefiniteness;
    }

    public void setBaseGender(GenderType baseGender) {
	this.baseGender = baseGender;
    }

    public void setBaseLexiconPointer(String baseLexiconPointer) {
	this.baseLexiconPointer = baseLexiconPointer;
    }

    public void setBaseNumber(NumberType baseNumber) {
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

    public void setPolarity(PolarityType polarity) {
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

    public void setSpelling(SpellingType spelling) {
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
	result = prime
		* result
		+ ((baseLexiconPointer == null) ? 0 : baseLexiconPointer
			.hashCode());
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
	result = prime * result
		+ ((polarity == null) ? 0 : polarity.hashCode());
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

    @Override
    @Transient
    public boolean equals(Object obj) {
	if (this == obj)
	    return true;
	if (obj == null)
	    return false;
	if (!(obj instanceof Inflection))
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
	if (baseLexiconPointer == null) {
	    if (other.baseLexiconPointer != null)
		return false;
	} else if (!baseLexiconPointer.equals(other.baseLexiconPointer))
	    return false;
	if (baseNumber != other.baseNumber)
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
	if (binyan != other.binyan)
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
	if (spelling != other.spelling)
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
}
