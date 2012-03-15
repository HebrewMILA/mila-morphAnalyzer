//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v1.0.5-b16-fcs 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2008.12.01 at 02:23:12 PM IST 
//

package generated;

/**
 * Java content class for ProperNameLexiconType complex type.
 * <p>
 * The following schema fragment specifies the expected content contained within
 * this java content object. (defined at
 * file:/C:/Sun/jwsdp-1.6/jaxb/lib/hebrew_MWlexicon_12_11_2008.xsd line 1013)
 * <p>
 * 
 * <pre>
 * &lt;complexType name="ProperNameLexiconType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;choice maxOccurs="unbounded" minOccurs="0">
 *           &lt;element name="add" type="{}ProperNameExceptionType" maxOccurs="unbounded" minOccurs="0"/>
 *           &lt;element name="replace" type="{}ProperNameExceptionType" maxOccurs="unbounded" minOccurs="0"/>
 *           &lt;element name="remove" type="{}ProperNameExceptionType" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;/choice>
 *       &lt;/sequence>
 *       &lt;attribute name="definiteness" type="{}DefinitnessType" default="prohibited" />
 *       &lt;attribute name="direction" type="{}ThreeStateType" default="unspecified" />
 *       &lt;attribute name="gender" type="{}GenderType" default="masculine" />
 *       &lt;attribute name="number" type="{}NumberType" default="singular" />
 *       &lt;attribute name="type" type="{}NamedEntityType" default="person" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 */
public interface ProperNameLexiconType {

	/**
	 * Java content class for add element declaration.
	 * <p>
	 * The following schema fragment specifies the expected content contained
	 * within this java content object. (defined at
	 * file:/C:/Sun/jwsdp-1.6/jaxb/lib/hebrew_MWlexicon_12_11_2008.xsd line
	 * 1016)
	 * <p>
	 * 
	 * <pre>
	 * &lt;element name="add" type="{}ProperNameExceptionType"/>
	 * </pre>
	 * 
	 */
	public interface Add extends javax.xml.bind.Element,
			generated.ProperNameExceptionType {

	}

	/**
	 * Java content class for remove element declaration.
	 * <p>
	 * The following schema fragment specifies the expected content contained
	 * within this java content object. (defined at
	 * file:/C:/Sun/jwsdp-1.6/jaxb/lib/hebrew_MWlexicon_12_11_2008.xsd line
	 * 1018)
	 * <p>
	 * 
	 * <pre>
	 * &lt;element name="remove" type="{}ProperNameExceptionType"/>
	 * </pre>
	 * 
	 */
	public interface Remove extends javax.xml.bind.Element,
			generated.ProperNameExceptionType {

	}

	/**
	 * Java content class for replace element declaration.
	 * <p>
	 * The following schema fragment specifies the expected content contained
	 * within this java content object. (defined at
	 * file:/C:/Sun/jwsdp-1.6/jaxb/lib/hebrew_MWlexicon_12_11_2008.xsd line
	 * 1017)
	 * <p>
	 * 
	 * <pre>
	 * &lt;element name="replace" type="{}ProperNameExceptionType"/>
	 * </pre>
	 * 
	 */
	public interface Replace extends javax.xml.bind.Element,
			generated.ProperNameExceptionType {

	}

	/**
	 * Gets the value of the AddOrReplaceOrRemove property.
	 * 
	 * <p>
	 * This accessor method returns a reference to the live list, not a
	 * snapshot. Therefore any modification you make to the returned list will
	 * be present inside the JAXB object. This is why there is not a
	 * <CODE>set</CODE> method for the AddOrReplaceOrRemove property.
	 * 
	 * <p>
	 * For example, to add a new item, do as follows:
	 * 
	 * <pre>
	 * getAddOrReplaceOrRemove().add(newItem);
	 * </pre>
	 * 
	 * 
	 * <p>
	 * Objects of the following type(s) are allowed in the list
	 * {@link generated.ProperNameLexiconType.Replace}
	 * {@link generated.ProperNameLexiconType.Remove}
	 * {@link generated.ProperNameLexiconType.Add}
	 * 
	 */
	java.util.List getAddOrReplaceOrRemove();

	/**
	 * Gets the value of the definiteness property.
	 * 
	 * @return possible object is {@link java.lang.String}
	 */
	java.lang.String getDefiniteness();

	/**
	 * Gets the value of the direction property.
	 * 
	 * @return possible object is {@link java.lang.String}
	 */
	java.lang.String getDirection();

	/**
	 * Gets the value of the gender property.
	 * 
	 * @return possible object is {@link java.lang.String}
	 */
	java.lang.String getGender();

	/**
	 * Gets the value of the number property.
	 * 
	 * @return possible object is {@link java.lang.String}
	 */
	java.lang.String getNumber();

	/**
	 * Gets the value of the type property.
	 * 
	 * @return possible object is {@link java.lang.String}
	 */
	java.lang.String getType();

	/**
	 * Sets the value of the definiteness property.
	 * 
	 * @param value
	 *            allowed object is {@link java.lang.String}
	 */
	void setDefiniteness(java.lang.String value);

	/**
	 * Sets the value of the direction property.
	 * 
	 * @param value
	 *            allowed object is {@link java.lang.String}
	 */
	void setDirection(java.lang.String value);

	/**
	 * Sets the value of the gender property.
	 * 
	 * @param value
	 *            allowed object is {@link java.lang.String}
	 */
	void setGender(java.lang.String value);

	/**
	 * Sets the value of the number property.
	 * 
	 * @param value
	 *            allowed object is {@link java.lang.String}
	 */
	void setNumber(java.lang.String value);

	/**
	 * Sets the value of the type property.
	 * 
	 * @param value
	 *            allowed object is {@link java.lang.String}
	 */
	void setType(java.lang.String value);

}
