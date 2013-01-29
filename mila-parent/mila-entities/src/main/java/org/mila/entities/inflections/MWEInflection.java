package org.mila.entities.inflections;

import java.io.Serializable;

import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

@Entity(name = "MWEInflection")
public class MWEInflection implements Serializable, PersistableInflection {
    private static final long serialVersionUID = -7092895993694412830L;

    protected long id;
    protected MWElement element;
    protected String surface;
    protected String transliterated;
    protected String pos;
    protected boolean consecutive;
    protected String type;
    protected boolean prefix;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "MWEINF_GEN")
    public long getId() {
	return id;
    }

    @OneToOne(optional = false)
    @JoinColumn(name="MWElement_ID", unique=true, nullable=false, updatable=false)
    public MWElement getElement() {
	return element;
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
    public String getPos() {
	return pos;
    }

    @Basic
    public boolean isConsecutive() {
	return consecutive;
    }

    @Basic
    public String getType() {
	return type;
    }

    @Basic
    public boolean isPrefix() {
	return prefix;
    }

    public void setId(long id) {
	this.id = id;
    }

    public void setElement(MWElement element) {
	this.element = element;
    }

    public void setSurface(String surface) {
	this.surface = surface;
    }

    public void setTransliterated(String transliterated) {
	this.transliterated = transliterated;
    }

    public void setPos(String pos) {
	this.pos = pos;
    }

    public void setConsecutive(boolean consecutive) {
	this.consecutive = consecutive;
    }

    public void setType(String type) {
	this.type = type;
    }

    public void setPrefix(boolean prefix) {
	this.prefix = prefix;
    }

    @Override
    public int hashCode() {
	final int prime = 31;
	int result = 1;
	result = prime * result + (consecutive ? 1231 : 1237);
	result = prime * result + ((element == null) ? 0 : element.hashCode());
	result = prime * result + (int) (id ^ (id >>> 32));
	result = prime * result + ((pos == null) ? 0 : pos.hashCode());
	result = prime * result + (prefix ? 1231 : 1237);
	result = prime * result + ((surface == null) ? 0 : surface.hashCode());
	result = prime * result
		+ ((transliterated == null) ? 0 : transliterated.hashCode());
	result = prime * result + ((type == null) ? 0 : type.hashCode());
	return result;
    }

    @Override
    public boolean equals(Object obj) {
	if (this == obj)
	    return true;
	if (obj == null)
	    return false;
	if (!(obj instanceof MWEInflection))
	    return false;
	MWEInflection other = (MWEInflection) obj;
	if (consecutive != other.consecutive)
	    return false;
	if (element == null) {
	    if (other.element != null)
		return false;
	} else if (!element.equals(other.element))
	    return false;
	if (id != other.id)
	    return false;
	if (pos == null) {
	    if (other.pos != null)
		return false;
	} else if (!pos.equals(other.pos))
	    return false;
	if (prefix != other.prefix)
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
	if (type == null) {
	    if (other.type != null)
		return false;
	} else if (!type.equals(other.type))
	    return false;
	return true;
    }

}
