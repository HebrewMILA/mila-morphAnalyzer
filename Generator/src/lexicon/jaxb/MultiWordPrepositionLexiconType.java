//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v1.0.5-b16-fcs 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2010.01.10 at 01:33:09 PM IST 
//


package lexicon.jaxb;


/**
 * Java content class for MultiWordPrepositionLexiconType complex type.
 * <p>The following schema fragment specifies the expected content contained within this java content object. (defined at file:/home/gtabajah/automaticCode/jwsdp-1.6/jaxb/lib/hebrew_lexicon_10_01_2010.xsd line 1912)
 * <p>
 * <pre>
 * &lt;complexType name="MultiWordPrepositionLexiconType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;attribute name="base1" type="{http://www.w3.org/2001/XMLSchema}boolean" default="false" />
 *       &lt;attribute name="base2" type="{http://www.w3.org/2001/XMLSchema}boolean" default="false" />
 *       &lt;attribute name="consecutive" type="{http://www.w3.org/2001/XMLSchema}boolean" default="true" />
 *       &lt;attribute name="construct1" type="{http://www.w3.org/2001/XMLSchema}boolean" default="false" />
 *       &lt;attribute name="mwPos" type="{}PosType" />
 *       &lt;attribute name="number" type="{}NumberType" default="unspecified" />
 *       &lt;attribute name="possessive2" type="{http://www.w3.org/2001/XMLSchema}boolean" default="false" />
 *       &lt;attribute name="type" type="{http://www.w3.org/2001/XMLSchema}token" default="unspecified" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 */
public interface MultiWordPrepositionLexiconType {


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
     * Gets the value of the construct1 property.
     * 
     */
    boolean isConstruct1();

    /**
     * Sets the value of the construct1 property.
     * 
     */
    void setConstruct1(boolean value);

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
     * Gets the value of the consecutive property.
     * 
     */
    boolean isConsecutive();

    /**
     * Sets the value of the consecutive property.
     * 
     */
    void setConsecutive(boolean value);

    /**
     * Gets the value of the base2 property.
     * 
     */
    boolean isBase2();

    /**
     * Sets the value of the base2 property.
     * 
     */
    void setBase2(boolean value);

    /**
     * Gets the value of the mwPos property.
     * 
     * @return
     *     possible object is
     *     {@link java.lang.String}
     */
    java.lang.String getMwPos();

    /**
     * Sets the value of the mwPos property.
     * 
     * @param value
     *     allowed object is
     *     {@link java.lang.String}
     */
    void setMwPos(java.lang.String value);

    /**
     * Gets the value of the possessive2 property.
     * 
     */
    boolean isPossessive2();

    /**
     * Sets the value of the possessive2 property.
     * 
     */
    void setPossessive2(boolean value);

    /**
     * Gets the value of the base1 property.
     * 
     */
    boolean isBase1();

    /**
     * Sets the value of the base1 property.
     * 
     */
    void setBase1(boolean value);

}
