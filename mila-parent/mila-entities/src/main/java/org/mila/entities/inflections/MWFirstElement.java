package org.mila.entities.inflections;

import javax.persistence.Basic;
import javax.persistence.Entity;

@Entity(name = "MWFirstElement")
public class MWFirstElement extends MWElement implements PersistableInflection {
    private static final long serialVersionUID = 1L;

    String pos;
    String type;

    @Basic
    public String getPos() {
	return pos;
    }

    @Basic
    public String getType() {
	return type;
    }

    public void setPos(String pos) {
	this.pos = pos;
    }

    public void setType(String type) {
	this.type = type;
    }

    @Override
    public int hashCode() {
	final int prime = 31;
	int result = super.hashCode();
	result = prime * result + ((pos == null) ? 0 : pos.hashCode());
	result = prime * result + ((type == null) ? 0 : type.hashCode());
	return result;
    }

    @Override
    public boolean equals(Object obj) {
	if (this == obj)
	    return true;
	if (!super.equals(obj))
	    return false;
	if (!(obj instanceof MWFirstElement))
	    return false;
	MWFirstElement other = (MWFirstElement) obj;
	if (pos == null) {
	    if (other.pos != null)
		return false;
	} else if (!pos.equals(other.pos))
	    return false;
	if (type == null) {
	    if (other.type != null)
		return false;
	} else if (!type.equals(other.type))
	    return false;
	return true;
    }
}
