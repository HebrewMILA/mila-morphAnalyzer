//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v1.0.5-b16-fcs 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2010.12.23 at 10:34:08 AM IST 
//


package lexicon.jaxb;


/**
 * Java content class for NumeralLexiconType complex type.
 * <p>The following schema fragment specifies the expected content contained within this java content object. (defined at file:/home/syjacob/automaticCode/jwsdp-1.6/jaxb/lib/hebrew_MWlexicon.xsd line 1666)
 * <p>
 * <pre>
 * &lt;complexType name="NumeralLexiconType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;choice maxOccurs="unbounded" minOccurs="0">
 *           &lt;element name="add" type="{}NumeralExceptionType" maxOccurs="unbounded" minOccurs="0"/>
 *           &lt;element name="replace" type="{}NumeralExceptionType" maxOccurs="unbounded" minOccurs="0"/>
 *           &lt;element name="remove" type="{}NumeralExceptionType" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;/choice>
 *       &lt;/sequence>
 *       &lt;attribute name="definiteness" type="{}DefinitnessType" default="optional" />
 *       &lt;attribute name="gender" type="{}GenderType" default="masculine" />
 *       &lt;attribute name="inflect" type="{http://www.w3.org/2001/XMLSchema}boolean" default="false" />
 *       &lt;attribute name="number" type="{}NumberType" default="singular" />
 *       &lt;attribute name="type" type="{}NumeralType" default="unspecified" />
 *       &lt;attribute name="value" type="{http://www.w3.org/2001/XMLSchema}token" default="" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 */
public interface NumeralLexiconType {


    /**
     * Gets the value of the type property.
     * 
     * @return
     *     possible object is
     *     {@link java.lang.String}
     */
    java.lang.String getType();

    /**
     * Sets the value of the type property.
     * 
     * @param value
     *     allowed object is
     *     {@link java.lang.String}
     */
    void setType(java.lang.String value);

    /**
     * Gets the value of the value property.
     * 
     * @return
     *     possible object is
     *     {@link java.lang.String}
     */
    java.lang.String getValue();

    /**
     * Sets the value of the value property.
     * 
     * @param value
     *     allowed object is
     *     {@link java.lang.String}
     */
    void setValue(java.lang.String value);

    /**
     * Gets the value of the gender property.
     * 
     * @return
     *     possible object is
     *     {@link java.lang.String}
     */
    java.lang.String getGender();

    /**
     * Sets the value of the gender property.
     * 
     * @param value
     *     allowed object is
     *     {@link java.lang.String}
     */
    void setGender(java.lang.String value);

    /**
     * Gets the value of the number property.
     * 
     * @return
     *     possible object is
     *     {@link java.lang.String}
     */
    java.lang.String getNumber();

    /**
     * Sets the value of the number property.
     * 
     * @param value
     *     allowed object is
     *     {@link java.lang.String}
     */
    void setNumber(java.lang.String value);

    /**
     * Gets the value of the inflect property.
     * 
     */
    boolean isInflect();

    /**
     * Sets the value of the inflect property.
     * 
     */
    void setInflect(boolean value);

    /**
     * Gets the value of the AddOrReplaceOrRemove property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the AddOrReplaceOrRemove property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getAddOrReplaceOrRemove().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link lexicon.jaxb.NumeralLexiconType.Remove}
     * {@link lexicon.jaxb.NumeralLexiconType.Add}
     * {@link lexicon.jaxb.NumeralLexiconType.Replace}
     * 
     */
    java.util.List getAddOrReplaceOrRemove();

    /**
     * Gets the value of the definiteness property.
     * 
     * @return
     *     possible object is
     *     {@link java.lang.String}
     */
    java.lang.String getDefiniteness();

    /**
     * Sets the value of the definiteness property.
     * 
     * @param value
     *     allowed object is
     *     {@link java.lang.String}
     */
    void setDefiniteness(java.lang.String value);


    /**
     * Java content class for add element declaration.
     * <p>The following schema fragment specifies the expected content contained within this java content object. (defined at file:/home/syjacob/automaticCode/jwsdp-1.6/jaxb/lib/hebrew_MWlexicon.xsd line 1669)
     * <p>
     * <pre>
     * &lt;element name="add" type="{}NumeralExceptionType"/>
     * </pre>
     * 
     */
    public interface Add
        extends javax.xml.bind.Element, lexicon.jaxb.NumeralExceptionType
    {


    }


    /**
     * Java content class for remove element declaration.
     * <p>The following schema fragment specifies the expected content contained within this java content object. (defined at file:/home/syjacob/automaticCode/jwsdp-1.6/jaxb/lib/hebrew_MWlexicon.xsd line 1671)
     * <p>
     * <pre>
     * &lt;element name="remove" type="{}NumeralExceptionType"/>
     * </pre>
     * 
     */
    public interface Remove
        extends javax.xml.bind.Element, lexicon.jaxb.NumeralExceptionType
    {


    }


    /**
     * Java content class for replace element declaration.
     * <p>The following schema fragment specifies the expected content contained within this java content object. (defined at file:/home/syjacob/automaticCode/jwsdp-1.6/jaxb/lib/hebrew_MWlexicon.xsd line 1670)
     * <p>
     * <pre>
     * &lt;element name="replace" type="{}NumeralExceptionType"/>
     * </pre>
     * 
     */
    public interface Replace
        extends javax.xml.bind.Element, lexicon.jaxb.NumeralExceptionType
    {


    }

}
