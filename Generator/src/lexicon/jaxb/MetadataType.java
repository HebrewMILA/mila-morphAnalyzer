//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v1.0.5-b16-fcs 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2010.01.10 at 01:33:09 PM IST 
//


package lexicon.jaxb;


/**
 * �� ��������, ����, ���� ������, ����� ����� �����, �� ����� �����, ���"� �� ����� �����, ����� ������, ����� �����, ����� ������� ������ �����.
 * 
 * Java content class for MetadataType complex type.
 * <p>The following schema fragment specifies the expected content contained within this java content object. (defined at file:/home/gtabajah/automaticCode/jwsdp-1.6/jaxb/lib/hebrew_lexicon_10_01_2010.xsd line 51)
 * <p>
 * <pre>
 * &lt;complexType name="MetadataType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="name" type="{http://www.w3.org/2001/XMLSchema}token"/>
 *         &lt;element name="version" type="{http://www.w3.org/2001/XMLSchema}float"/>
 *         &lt;element name="revision" type="{http://www.w3.org/2001/XMLSchema}token"/>
 *         &lt;element name="date" type="{http://www.w3.org/2001/XMLSchema}token"/>
 *         &lt;element name="maintainer" type="{http://www.w3.org/2001/XMLSchema}token"/>
 *         &lt;element name="email" type="{http://www.w3.org/2001/XMLSchema}token"/>
 *         &lt;element name="source" type="{}SourceType" maxOccurs="unbounded"/>
 *         &lt;element name="license" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="transliteration" type="{}TransliterationType"/>
 *         &lt;element name="comment" type="{http://www.w3.org/2001/XMLSchema}token" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 */
public interface MetadataType {


    /**
     * Gets the value of the comment property.
     * 
     * @return
     *     possible object is
     *     {@link java.lang.String}
     */
    java.lang.String getComment();

    /**
     * Sets the value of the comment property.
     * 
     * @param value
     *     allowed object is
     *     {@link java.lang.String}
     */
    void setComment(java.lang.String value);

    /**
     * Gets the value of the email property.
     * 
     * @return
     *     possible object is
     *     {@link java.lang.String}
     */
    java.lang.String getEmail();

    /**
     * Sets the value of the email property.
     * 
     * @param value
     *     allowed object is
     *     {@link java.lang.String}
     */
    void setEmail(java.lang.String value);

    /**
     * Gets the value of the version property.
     * 
     */
    float getVersion();

    /**
     * Sets the value of the version property.
     * 
     */
    void setVersion(float value);

    /**
     * Gets the value of the license property.
     * 
     * @return
     *     possible object is
     *     {@link java.lang.String}
     */
    java.lang.String getLicense();

    /**
     * Sets the value of the license property.
     * 
     * @param value
     *     allowed object is
     *     {@link java.lang.String}
     */
    void setLicense(java.lang.String value);

    /**
     * Gets the value of the date property.
     * 
     * @return
     *     possible object is
     *     {@link java.lang.String}
     */
    java.lang.String getDate();

    /**
     * Sets the value of the date property.
     * 
     * @param value
     *     allowed object is
     *     {@link java.lang.String}
     */
    void setDate(java.lang.String value);

    /**
     * Gets the value of the transliteration property.
     * 
     * @return
     *     possible object is
     *     {@link lexicon.jaxb.TransliterationType}
     */
    lexicon.jaxb.TransliterationType getTransliteration();

    /**
     * Sets the value of the transliteration property.
     * 
     * @param value
     *     allowed object is
     *     {@link lexicon.jaxb.TransliterationType}
     */
    void setTransliteration(lexicon.jaxb.TransliterationType value);

    /**
     * Gets the value of the maintainer property.
     * 
     * @return
     *     possible object is
     *     {@link java.lang.String}
     */
    java.lang.String getMaintainer();

    /**
     * Sets the value of the maintainer property.
     * 
     * @param value
     *     allowed object is
     *     {@link java.lang.String}
     */
    void setMaintainer(java.lang.String value);

    /**
     * Gets the value of the Source property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the Source property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getSource().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link lexicon.jaxb.SourceType}
     * 
     */
    java.util.List getSource();

    /**
     * Gets the value of the name property.
     * 
     * @return
     *     possible object is
     *     {@link java.lang.String}
     */
    java.lang.String getName();

    /**
     * Sets the value of the name property.
     * 
     * @param value
     *     allowed object is
     *     {@link java.lang.String}
     */
    void setName(java.lang.String value);

    /**
     * Gets the value of the revision property.
     * 
     * @return
     *     possible object is
     *     {@link java.lang.String}
     */
    java.lang.String getRevision();

    /**
     * Sets the value of the revision property.
     * 
     * @param value
     *     allowed object is
     *     {@link java.lang.String}
     */
    void setRevision(java.lang.String value);

}