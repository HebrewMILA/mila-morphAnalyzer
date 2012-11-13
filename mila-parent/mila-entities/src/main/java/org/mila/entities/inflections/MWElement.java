package org.mila.entities.inflections;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity(name = "MWElement")
@Table(name = "MWE")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public abstract class MWElement implements Serializable {
    private static final long serialVersionUID = 1L;

    protected long id;
    protected String surface;
    protected String transliterated;
    protected boolean consecutive;
    protected long sequence;

    protected Set<MWOtherElement> consecutiveElements;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "MWE_GEN")
    public long getId() {
	return id;
    }

    @Basic
    public String getSurface() {
	return surface;
    }

    @Basic
    public String getTransliterated() {
	return transliterated;
    }

    @Basic
    public boolean isConsecutive() {
	return consecutive;
    }

    @Basic
    public long getSequence() {
	return sequence;
    }

    @OneToMany(mappedBy = "formerElement", cascade = CascadeType.ALL)
    @JoinColumn(nullable = false)
    public Set<MWOtherElement> getConsecutiveElements() {
	return consecutiveElements;
    }

    public void setId(long id) {
	this.id = id;
    }

    public void setSurface(String surface) {
	this.surface = surface;
    }

    public void setTransliterated(String transliterated) {
	this.transliterated = transliterated;
    }

    public void setConsecutive(boolean consecutive) {
	this.consecutive = consecutive;
    }

    public void setSequence(long sequence) {
	this.sequence = sequence;
    }

    public void setConsecutiveElements(Set<MWOtherElement> elements) {
	consecutiveElements = elements;
    }

    @Override
    @Transient
    public int hashCode() {
	final int prime = 31;
	int result = 1;
	result = prime * result + (consecutive ? 1231 : 1237);
	result = prime * result + (int) (id ^ (id >>> 32));
	result = prime * result + ((surface == null) ? 0 : surface.hashCode());
	result = prime * result
		+ ((transliterated == null) ? 0 : transliterated.hashCode());
	return result;
    }

    @Override
    @Transient
    public boolean equals(Object obj) {
	if (this == obj)
	    return true;
	if (obj == null)
	    return false;
	if (!(obj instanceof MWElement))
	    return false;
	MWElement other = (MWElement) obj;
	if (consecutive != other.consecutive)
	    return false;
	if (id != other.id)
	    return false;
	if (surface == null) {
	    if (other.surface != null)
		return false;
	} else if (!surface.equals(other.surface))
	    return false;
	if (transliterated == null) {
	    if (other.transliterated != null)
		return false;
	} else if (!transliterated.equals(other.transliterated))
	    return false;
	return true;
    }
}
