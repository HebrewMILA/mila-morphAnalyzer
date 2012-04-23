//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v1.0.5-b16-fcs 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2010.01.10 at 01:33:09 PM IST 
//


package lexicon.jaxb;


/**
 * Java content class for MultiWordNounExceptionType complex type.
 * <p>The following schema fragment specifies the expected content contained within this java content object. (defined at file:/home/gtabajah/automaticCode/jwsdp-1.6/jaxb/lib/hebrew_lexicon_10_01_2010.xsd line 1942)
 * <p>
 * <pre>
 * &lt;complexType name="MultiWordNounExceptionType">
 *   &lt;simpleContent>
 *     &lt;extension base="&lt;http://www.w3.org/2001/XMLSchema>token">
 *       &lt;attribute name="definiteness" type="{}AcronymDefinitenessType" default="external" />
 *       &lt;attribute name="dotted" type="{http://www.w3.org/2001/XMLSchema}token" default="" />
 *       &lt;attribute name="feminine" type="{}FeminineType" default="unspecified" />
 *       &lt;attribute name="gender" type="{}GenderType" default="unspecified" />
 *       &lt;attribute name="inflectPossessiveP" type="{http://www.w3.org/2001/XMLSchema}boolean" default="false" />
 *       &lt;attribute name="inflectPossessiveS" type="{http://www.w3.org/2001/XMLSchema}boolean" default="false" />
 *       &lt;attribute name="number" type="{}NumberType" default="unspecified" />
 *       &lt;attribute name="plural" type="{}PluralType" default="im" />
 *       &lt;attribute name="possessive" type="{}PGNType" default="unspecified" />
 *       &lt;attribute name="register" type="{}RegisterType" default="formal" />
 *       &lt;attribute name="spelling" type="{}SpellingType" default="standard" />
 *       &lt;attribute name="transliterated" use="required" type="{http://www.w3.org/2001/XMLSchema}token" />
 *       &lt;attribute name="undotted" use="required" type="{http://www.w3.org/2001/XMLSchema}token" />
 *     &lt;/extension>
 *   &lt;/simpleContent>
 * &lt;/complexType>
 * </pre>
 * 
 */
public interface MultiWordNounExceptionType {


    /**
     * Gets the value of the feminine property.
     * 
     * @return
     *     possible object is
     *     {@link java.lang.String}
     */
    java.lang.String getFeminine();

    /**
     * Sets the value of the feminine property.
     * 
     * @param value
     *     allowed object is
     *     {@link java.lang.String}
     */
    void setFeminine(java.lang.String value);

    /**
     * Gets the value of the inflectPossessiveS property.
     * 
     */
    boolean isInflectPossessiveS();

    /**
     * Sets the value of the inflectPossessiveS property.
     * 
     */
    void setInflectPossessiveS(boolean value);

    /**
     * Gets the value of the possessive property.
     * 
     * @return
     *     possible object is
     *     {@link java.lang.String}
     */
    java.lang.String getPossessive();

    /**
     * Sets the value of the possessive property.
     * 
     * @param value
     *     allowed object is
     *     {@link java.lang.String}
     */
    void setPossessive(java.lang.String value);

    /**
     * Gets the value of the register property.
     * 
     * @return
     *     possible object is
     *     {@link java.lang.String}
     */
    java.lang.String getRegister();

    /**
     * Sets the value of the register property.
     * 
     * @param value
     *     allowed object is
     *     {@link java.lang.String}
     */
    void setRegister(java.lang.String value);

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
     * Gets the value of the plural property.
     * 
     * @return
     *     possible object is
     *     {@link java.lang.String}
     */
    java.lang.String getPlural();

    /**
     * Sets the value of the plural property.
     * 
     * @param value
     *     allowed object is
     *     {@link java.lang.String}
     */
    void setPlural(java.lang.String value);

    /**
     * Gets the value of the spelling property.
     * 
     * @return
     *     possible object is
     *     {@link java.lang.String}
     */
    java.lang.String getSpelling();

    /**
     * Sets the value of the spelling property.
     * 
     * @param value
     *     allowed object is
     *     {@link java.lang.String}
     */
    void setSpelling(java.lang.String value);

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
     * Gets the value of the transliterated property.
     * 
     * @return
     *     possible object is
     *     {@link java.lang.String}
     */
    java.lang.String getTransliterated();

    /**
     * Sets the value of the transliterated property.
     * 
     * @param value
     *     allowed object is
     *     {@link java.lang.String}
     */
    void setTransliterated(java.lang.String value);

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
     * Gets the value of the dotted property.
     * 
     * @return
     *     possible object is
     *     {@link java.lang.String}
     */
    java.lang.String getDotted();

    /**
     * Sets the value of the dotted property.
     * 
     * @param value
     *     allowed object is
     *     {@link java.lang.String}
     */
    void setDotted(java.lang.String value);

    /**
     * Gets the value of the undotted property.
     * 
     * @return
     *     possible object is
     *     {@link java.lang.String}
     */
    java.lang.String getUndotted();

    /**
     * Sets the value of the undotted property.
     * 
     * @param value
     *     allowed object is
     *     {@link java.lang.String}
     */
    void setUndotted(java.lang.String value);

    /**
     * Gets the value of the inflectPossessiveP property.
     * 
     */
    boolean isInflectPossessiveP();

    /**
     * Sets the value of the inflectPossessiveP property.
     * 
     */
    void setInflectPossessiveP(boolean value);

}