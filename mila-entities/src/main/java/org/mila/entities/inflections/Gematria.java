package org.mila.entities.inflections;

import java.io.Serializable;

import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity(name = "Gematria")
@Table(name = "GEMATRIA")
public class Gematria implements Serializable {
    private static final long serialVersionUID = 1L;

    protected long id;
    protected String transliterated;
    protected long value;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "GEMATRIA_GEN")
    public long getId() {
	return id;
    }

    @Basic
    public String getTransliterated() {
	return transliterated;
    }

    @Basic
    public long getValue() {
	return value;
    }

    public void setId(long id) {
	this.id = id;
    }

    public void setTransliterated(String transliterated) {
	this.transliterated = transliterated;
    }

    public void setValue(long value) {
	this.value = value;
    }

    @Override
    public int hashCode() {
	final int prime = 31;
	int result = 1;
	result = prime * result + (int) (id ^ (id >>> 32));
	result = prime * result
		+ ((transliterated == null) ? 0 : transliterated.hashCode());
	result = prime * result + (int) (value ^ (value >>> 32));
	return result;
    }

    @Override
    public boolean equals(Object obj) {
	if (this == obj)
	    return true;
	if (obj == null)
	    return false;
	if (getClass() != obj.getClass())
	    return false;
	Gematria other = (Gematria) obj;
	if (id != other.id)
	    return false;
	if (transliterated == null) {
	    if (other.transliterated != null)
		return false;
	} else if (!transliterated.equals(other.transliterated))
	    return false;
	if (value != other.value)
	    return false;
	return true;
    }

}
