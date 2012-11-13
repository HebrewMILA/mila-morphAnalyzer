package org.mila.entities.generator;

import java.io.Serializable;

import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity(name = "InflectionRule")
@Table(name = "INFLECTIONRULES")
@Inheritance(strategy = InheritanceType.JOINED)
public class InflectionRule implements Serializable {
    private static final long serialVersionUID = 1L;

    long id;
    String inputPattern; /* could be an enum? */
    long inputSuffixLen;
    String inputCondition;
    String inflectedPattern;
    String comment;
    String action;

    @Id
    public long getId() {
	return id;
    }

    @Basic
    public String getInputPattern() {
	return inputPattern;
    }

    @Basic
    public long getInputSuffixLen() {
	return inputSuffixLen;
    }

    @Basic
    public String getInputCondition() {
	return inputCondition;
    }

    @Basic
    public String getInflectedPattern() {
	return inflectedPattern;
    }

    @Basic
    public String getComment() {
	return comment;
    }

    @Basic
    public String getAction() {
	return action;
    }

    public void setId(long id) {
	this.id = id;
    }

    public void setInputPattern(String inputPattern) {
	this.inputPattern = inputPattern;
    }

    public void setInputSuffixLen(long inputSuffixLen) {
	this.inputSuffixLen = inputSuffixLen;
    }

    public void setInputCondition(String inputCondition) {
	this.inputCondition = inputCondition;
    }

    public void setInflectedPattern(String inflectedPattern) {
	this.inflectedPattern = inflectedPattern;
    }

    public void setComment(String comment) {
	this.comment = comment;
    }

    public void setAction(String action) {
	this.action = action;
    }

    @Override
    @Transient
    public int hashCode() {
	final int prime = 31;
	int result = 1;
	result = prime * result + ((action == null) ? 0 : action.hashCode());
	result = prime * result + ((comment == null) ? 0 : comment.hashCode());
	result = prime * result + (int) (id ^ (id >>> 32));
	result = prime
		* result
		+ ((inflectedPattern == null) ? 0 : inflectedPattern.hashCode());
	result = prime * result
		+ ((inputCondition == null) ? 0 : inputCondition.hashCode());
	result = prime * result
		+ ((inputPattern == null) ? 0 : inputPattern.hashCode());
	result = prime * result
		+ (int) (inputSuffixLen ^ (inputSuffixLen >>> 32));
	return result;
    }

    @Override
    @Transient
    public boolean equals(Object obj) {
	if (this == obj)
	    return true;
	if (obj == null)
	    return false;
	if (!(obj instanceof InflectionRule))
	    return false;
	InflectionRule other = (InflectionRule) obj;
	if (action == null) {
	    if (other.action != null)
		return false;
	} else if (!action.equals(other.action))
	    return false;
	if (comment == null) {
	    if (other.comment != null)
		return false;
	} else if (!comment.equals(other.comment))
	    return false;
	if (id != other.id)
	    return false;
	if (inflectedPattern == null) {
	    if (other.inflectedPattern != null)
		return false;
	} else if (!inflectedPattern.equals(other.inflectedPattern))
	    return false;
	if (inputCondition == null) {
	    if (other.inputCondition != null)
		return false;
	} else if (!inputCondition.equals(other.inputCondition))
	    return false;
	if (inputPattern == null) {
	    if (other.inputPattern != null)
		return false;
	} else if (!inputPattern.equals(other.inputPattern))
	    return false;
	if (inputSuffixLen != other.inputSuffixLen)
	    return false;
	return true;
    }

}
