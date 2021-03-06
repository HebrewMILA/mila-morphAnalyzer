//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v1.0.5-b16-fcs 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2010.01.10 at 01:33:09 PM IST 
//


package lexicon.jaxb;


/**
 * ���� �� ���� ������ ������ ����� ���� ����� ������� ��� ���� ������, ��� ���� ����� ����������.
 * ����, ���� �� ���� �� ������ ����� (���� �� ������ ���) ����� �����.
 * 
 * Java content class for VerbExceptionType complex type.
 * <p>The following schema fragment specifies the expected content contained within this java content object. (defined at file:/home/gtabajah/automaticCode/jwsdp-1.6/jaxb/lib/hebrew_lexicon_10_01_2010.xsd line 1189)
 * <p>
 * <pre>
 * &lt;complexType name="VerbExceptionType">
 *   &lt;simpleContent>
 *     &lt;extension base="&lt;http://www.w3.org/2001/XMLSchema>token">
 *       &lt;attribute name="beinoniConstruct" type="{http://www.w3.org/2001/XMLSchema}boolean" default="false" />
 *       &lt;attribute name="beinoniDefiniteness" type="{http://www.w3.org/2001/XMLSchema}boolean" default="false" />
 *       &lt;attribute name="dotted" type="{http://www.w3.org/2001/XMLSchema}token" default="" />
 *       &lt;attribute name="gender" type="{}GenderType" default="unspecified" />
 *       &lt;attribute name="inflectBeinoniPossessive" type="{http://www.w3.org/2001/XMLSchema}boolean" default="false" />
 *       &lt;attribute name="number" type="{}NumberType" default="unspecified" />
 *       &lt;attribute name="person" type="{}PersonType" default="unspecified" />
 *       &lt;attribute name="pgn" use="required" type="{}PGNType" />
 *       &lt;attribute name="register" type="{}RegisterType" default="formal" />
 *       &lt;attribute name="spelling" type="{}SpellingType" default="standard" />
 *       &lt;attribute name="tense" use="required" type="{}TenseType" />
 *       &lt;attribute name="transliterated" use="required" type="{http://www.w3.org/2001/XMLSchema}token" />
 *       &lt;attribute name="undotted" use="required" type="{http://www.w3.org/2001/XMLSchema}token" />
 *     &lt;/extension>
 *   &lt;/simpleContent>
 * &lt;/complexType>
 * </pre>
 * 
 */
public interface VerbExceptionType {


    /**
     * Gets the value of the pgn property.
     * 
     * @return
     *     possible object is
     *     {@link java.lang.String}
     */
    java.lang.String getPgn();

    /**
     * Sets the value of the pgn property.
     * 
     * @param value
     *     allowed object is
     *     {@link java.lang.String}
     */
    void setPgn(java.lang.String value);

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
     * Gets the value of the beinoniDefiniteness property.
     * 
     */
    boolean isBeinoniDefiniteness();

    /**
     * Sets the value of the beinoniDefiniteness property.
     * 
     */
    void setBeinoniDefiniteness(boolean value);

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
     * Gets the value of the inflectBeinoniPossessive property.
     * 
     */
    boolean isInflectBeinoniPossessive();

    /**
     * Sets the value of the inflectBeinoniPossessive property.
     * 
     */
    void setInflectBeinoniPossessive(boolean value);

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
     * Gets the value of the beinoniConstruct property.
     * 
     */
    boolean isBeinoniConstruct();

    /**
     * Sets the value of the beinoniConstruct property.
     * 
     */
    void setBeinoniConstruct(boolean value);

}
