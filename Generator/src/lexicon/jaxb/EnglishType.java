//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v1.0.5-b16-fcs 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2010.01.10 at 01:33:09 PM IST 
//


package lexicon.jaxb;


/**
 * English translation equivalents, each with an associated
 * weight reflecting its frequency.
 * Java content class for EnglishType complex type.
 * <p>The following schema fragment specifies the expected content contained within this java content object. (defined at file:/home/gtabajah/automaticCode/jwsdp-1.6/jaxb/lib/hebrew_lexicon_10_01_2010.xsd line 195)
 * <p>
 * <pre>
 * &lt;complexType name="EnglishType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;attribute name="id" use="required" type="{http://www.w3.org/2001/XMLSchema}token" />
 *       &lt;attribute name="te" use="required" type="{http://www.w3.org/2001/XMLSchema}token" />
 *       &lt;attribute name="weight" type="{http://www.w3.org/2001/XMLSchema}token" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 */
public interface EnglishType {


    /**
     * Gets the value of the weight property.
     * 
     * @return
     *     possible object is
     *     {@link java.lang.String}
     */
    java.lang.String getWeight();

    /**
     * Sets the value of the weight property.
     * 
     * @param value
     *     allowed object is
     *     {@link java.lang.String}
     */
    void setWeight(java.lang.String value);

    /**
     * Gets the value of the te property.
     * 
     * @return
     *     possible object is
     *     {@link java.lang.String}
     */
    java.lang.String getTe();

    /**
     * Sets the value of the te property.
     * 
     * @param value
     *     allowed object is
     *     {@link java.lang.String}
     */
    void setTe(java.lang.String value);

    /**
     * Gets the value of the id property.
     * 
     * @return
     *     possible object is
     *     {@link java.lang.String}
     */
    java.lang.String getId();

    /**
     * Sets the value of the id property.
     * 
     * @param value
     *     allowed object is
     *     {@link java.lang.String}
     */
    void setId(java.lang.String value);

}
