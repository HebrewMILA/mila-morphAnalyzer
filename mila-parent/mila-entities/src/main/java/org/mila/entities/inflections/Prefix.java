package org.mila.entities.inflections;

import java.io.Serializable;

import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity(name = "Prefix")
@Table(name = "PREFIXES")
public class Prefix implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    protected long id;
    
    protected String prefix;
    protected String description;
    protected boolean definiteArticleTag;
    protected boolean defArtHE;
    protected boolean relHE;
    protected boolean adverbKAF;
    protected boolean subConOrRelSHIN;
    protected boolean tempSubConKAFSHIN;
    protected boolean tempSubConMEMSHIN;
    protected boolean tempSubConLAMEDKAFSHIN;
    protected boolean relativizerTag;
    protected boolean temporalSubConjTag;
    protected boolean subordinatingConjunctionTag;
    protected boolean prefPartUnit;
    protected boolean prepBET;
    protected boolean prepKAF;
    protected boolean prepLAMED;
    protected boolean prepMEM;
    protected boolean prepositionTag;
    protected boolean conjunctionTag;
    
    @Basic
    public String getPrefix() {
	return prefix;
    }

    @Basic
    public String getDescription() {
	return description;
    }

    @Basic
    public boolean isDefiniteArticleTag() {
	return definiteArticleTag;
    }

    @Basic
    public boolean isDefArtHE() {
	return defArtHE;
    }

    @Basic
    public boolean isRelHE() {
	return relHE;
    }

    @Basic
    public boolean isAdverbKAF() {
	return adverbKAF;
    }

    @Basic
    public boolean isSubConOrRelSHIN() {
	return subConOrRelSHIN;
    }

    @Basic
    public boolean isTempSubConKAFSHIN() {
	return tempSubConKAFSHIN;
    }

    @Basic
    public boolean isTempSubConMEMSHIN() {
	return tempSubConMEMSHIN;
    }

    @Basic
    public boolean isTempSubConLAMEDKAFSHIN() {
	return tempSubConLAMEDKAFSHIN;
    }

    @Basic
    public boolean isRelativizerTag() {
	return relativizerTag;
    }

    @Basic
    public boolean isTemporalSubConjTag() {
	return temporalSubConjTag;
    }

    @Basic
    public boolean isSubordinatingConjunctionTag() {
	return subordinatingConjunctionTag;
    }

    @Basic
    public boolean isPrefPartUnit() {
	return prefPartUnit;
    }

    @Basic
    public boolean isPrepBET() {
	return prepBET;
    }

    @Basic
    public boolean isPrepKAF() {
	return prepKAF;
    }

    @Basic
    public boolean isPrepLAMED() {
	return prepLAMED;
    }

    @Basic
    public boolean isPrepMEM() {
	return prepMEM;
    }

    @Basic
    public boolean isPrepositionTag() {
	return prepositionTag;
    }

    @Basic
    public boolean isConjunctionTag() {
	return conjunctionTag;
    }

    public void setPrefix(String prefix) {
	this.prefix = prefix;
    }

    public void setDescription(String description) {
	this.description = description;
    }

    public void setDefiniteArticleTag(boolean definiteArticleTag) {
	this.definiteArticleTag = definiteArticleTag;
    }

    public void setDefArtHE(boolean defArtHE) {
	this.defArtHE = defArtHE;
    }

    public void setRelHE(boolean relHE) {
	this.relHE = relHE;
    }

    public void setAdverbKAF(boolean adverbKAF) {
	this.adverbKAF = adverbKAF;
    }

    public void setSubConOrRelSHIN(boolean subConOrRelSHIN) {
	this.subConOrRelSHIN = subConOrRelSHIN;
    }

    public void setTempSubConKAFSHIN(boolean tempSubConKAFSHIN) {
	this.tempSubConKAFSHIN = tempSubConKAFSHIN;
    }

    public void setTempSubConMEMSHIN(boolean tempSubConMEMSHIN) {
	this.tempSubConMEMSHIN = tempSubConMEMSHIN;
    }

    public void setTempSubConLAMEDKAFSHIN(boolean tempSubConLAMEDKAFSHIN) {
	this.tempSubConLAMEDKAFSHIN = tempSubConLAMEDKAFSHIN;
    }

    public void setRelativizerTag(boolean relativizerTag) {
	this.relativizerTag = relativizerTag;
    }

    public void setTemporalSubConjTag(boolean temporalSubConjTag) {
	this.temporalSubConjTag = temporalSubConjTag;
    }

    public void setSubordinatingConjunctionTag(
	    boolean subordinatingConjunctionTag) {
	this.subordinatingConjunctionTag = subordinatingConjunctionTag;
    }

    public void setPrefPartUnit(boolean prefPartUnit) {
	this.prefPartUnit = prefPartUnit;
    }

    public void setPrepBET(boolean prepBET) {
	this.prepBET = prepBET;
    }

    public void setPrepKAF(boolean prepKAF) {
	this.prepKAF = prepKAF;
    }

    public void setPrepLAMED(boolean prepLAMED) {
	this.prepLAMED = prepLAMED;
    }

    public void setPrepMEM(boolean prepMEM) {
	this.prepMEM = prepMEM;
    }

    public void setPrepositionTag(boolean prepositionTag) {
	this.prepositionTag = prepositionTag;
    }

    public void setConjunctionTag(boolean conjunctionTag) {
	this.conjunctionTag = conjunctionTag;
    }

    @Override
    @Transient
    public int hashCode() {
	final int prime = 31;
	int result = 1;
	result = prime * result + (adverbKAF ? 1231 : 1237);
	result = prime * result + (conjunctionTag ? 1231 : 1237);
	result = prime * result + (defArtHE ? 1231 : 1237);
	result = prime * result + (definiteArticleTag ? 1231 : 1237);
	result = prime * result
		+ ((description == null) ? 0 : description.hashCode());
	result = prime * result + (prefPartUnit ? 1231 : 1237);
	result = prime * result + ((prefix == null) ? 0 : prefix.hashCode());
	result = prime * result + (prepBET ? 1231 : 1237);
	result = prime * result + (prepKAF ? 1231 : 1237);
	result = prime * result + (prepLAMED ? 1231 : 1237);
	result = prime * result + (prepMEM ? 1231 : 1237);
	result = prime * result + (prepositionTag ? 1231 : 1237);
	result = prime * result + (relHE ? 1231 : 1237);
	result = prime * result + (relativizerTag ? 1231 : 1237);
	result = prime * result + (subConOrRelSHIN ? 1231 : 1237);
	result = prime * result + (subordinatingConjunctionTag ? 1231 : 1237);
	result = prime * result + (tempSubConKAFSHIN ? 1231 : 1237);
	result = prime * result + (tempSubConLAMEDKAFSHIN ? 1231 : 1237);
	result = prime * result + (tempSubConMEMSHIN ? 1231 : 1237);
	result = prime * result + (temporalSubConjTag ? 1231 : 1237);
	return result;
    }

    @Override
    @Transient
    public boolean equals(Object obj) {
	if (this == obj)
	    return true;
	if (obj == null)
	    return false;
	if (getClass() != obj.getClass())
	    return false;
	Prefix other = (Prefix) obj;
	if (adverbKAF != other.adverbKAF)
	    return false;
	if (conjunctionTag != other.conjunctionTag)
	    return false;
	if (defArtHE != other.defArtHE)
	    return false;
	if (definiteArticleTag != other.definiteArticleTag)
	    return false;
	if (description == null) {
	    if (other.description != null)
		return false;
	} else if (!description.equals(other.description))
	    return false;
	if (prefPartUnit != other.prefPartUnit)
	    return false;
	if (prefix == null) {
	    if (other.prefix != null)
		return false;
	} else if (!prefix.equals(other.prefix))
	    return false;
	if (prepBET != other.prepBET)
	    return false;
	if (prepKAF != other.prepKAF)
	    return false;
	if (prepLAMED != other.prepLAMED)
	    return false;
	if (prepMEM != other.prepMEM)
	    return false;
	if (prepositionTag != other.prepositionTag)
	    return false;
	if (relHE != other.relHE)
	    return false;
	if (relativizerTag != other.relativizerTag)
	    return false;
	if (subConOrRelSHIN != other.subConOrRelSHIN)
	    return false;
	if (subordinatingConjunctionTag != other.subordinatingConjunctionTag)
	    return false;
	if (tempSubConKAFSHIN != other.tempSubConKAFSHIN)
	    return false;
	if (tempSubConLAMEDKAFSHIN != other.tempSubConLAMEDKAFSHIN)
	    return false;
	if (tempSubConMEMSHIN != other.tempSubConMEMSHIN)
	    return false;
	if (temporalSubConjTag != other.temporalSubConjTag)
	    return false;
	return true;
    }
}
