//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v1.0.5-b16-fcs 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2010.12.23 at 10:34:08 AM IST 
//


package lexicon.jaxb;


/**
 * ����
 * Java content class for CopulaLexiconType complex type.
 * <p>The following schema fragment specifies the expected content contained within this java content object. (defined at file:/home/syjacob/automaticCode/jwsdp-1.6/jaxb/lib/hebrew_MWlexicon.xsd line 219)
 * <p>
 * <pre>
 * &lt;complexType name="CopulaLexiconType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;choice maxOccurs="unbounded" minOccurs="0">
 *           &lt;element name="add" type="{}CopulaExceptionType" maxOccurs="unbounded" minOccurs="0"/>
 *           &lt;element name="replace" type="{}CopulaExceptionType" maxOccurs="unbounded" minOccurs="0"/>
 *           &lt;element name="remove" type="{}CopulaExceptionType" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;/choice>
 *       &lt;/sequence>
 *       &lt;attribute name="gender" type="{}GenderType" default="unspecified" />
 *       &lt;attribute name="number" type="{}NumberType" default="unspecified" />
 *       &lt;attribute name="person" type="{}PersonType" default="unspecified" />
 *       &lt;attribute name="polarity" type="{}PolarityType" default="unspecified" />
 *       &lt;attribute name="tense" type="{}TenseType" default="unspecified" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 */
public interface CopulaLexiconType {


    /**
     * Gets the value of the tense property.
     * 
     * @return
     *     possible object is
     *     {@link java.lang.String}
     */
    java.lang.String getTense();

    /**
     * Sets the value of the tense property.
     * 
     * @param value
     *     allowed object is
     *     {@link java.lang.String}
     */
    void setTense(java.lang.String value);

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
     * {@link lexicon.jaxb.CopulaLexiconType.Add}
     * {@link lexicon.jaxb.CopulaLexiconType.Replace}
     * {@link lexicon.jaxb.CopulaLexiconType.Remove}
     * 
     */
    java.util.List getAddOrReplaceOrRemove();

    /**
     * Gets the value of the polarity property.
     * 
     * @return
     *     possible object is
     *     {@link java.lang.String}
     */
    java.lang.String getPolarity();

    /**
     * Sets the value of the polarity property.
     * 
     * @param value
     *     allowed object is
     *     {@link java.lang.String}
     */
    void setPolarity(java.lang.String value);

    /**
     * Gets the value of the person property.
     * 
     * @return
     *     possible object is
     *     {@link java.lang.String}
     */
    java.lang.String getPerson();

    /**
     * Sets the value of the person property.
     * 
     * @param value
     *     allowed object is
     *     {@link java.lang.String}
     */
    void setPerson(java.lang.String value);


    /**
     * Java content class for add element declaration.
     * <p>The following schema fragment specifies the expected content contained within this java content object. (defined at file:/home/syjacob/automaticCode/jwsdp-1.6/jaxb/lib/hebrew_MWlexicon.xsd line 225)
     * <p>
     * <pre>
     * &lt;element name="add" type="{}CopulaExceptionType"/>
     * </pre>
     * 
     */
    public interface Add
        extends javax.xml.bind.Element, lexicon.jaxb.CopulaExceptionType
    {


    }


    /**
     * Java content class for remove element declaration.
     * <p>The following schema fragment specifies the expected content contained within this java content object. (defined at file:/home/syjacob/automaticCode/jwsdp-1.6/jaxb/lib/hebrew_MWlexicon.xsd line 227)
     * <p>
     * <pre>
     * &lt;element name="remove" type="{}CopulaExceptionType"/>
     * </pre>
     * 
     */
    public interface Remove
        extends javax.xml.bind.Element, lexicon.jaxb.CopulaExceptionType
    {


    }


    /**
     * Java content class for replace element declaration.
     * <p>The following schema fragment specifies the expected content contained within this java content object. (defined at file:/home/syjacob/automaticCode/jwsdp-1.6/jaxb/lib/hebrew_MWlexicon.xsd line 226)
     * <p>
     * <pre>
     * &lt;element name="replace" type="{}CopulaExceptionType"/>
     * </pre>
     * 
     */
    public interface Replace
        extends javax.xml.bind.Element, lexicon.jaxb.CopulaExceptionType
    {


    }

}
