//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v1.0.5-b16-fcs 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2010.12.23 at 10:34:08 AM IST 
//

package lexicon.jaxb;

/**
 * Java content class for MultiWordNounLexiconType complex type.
 * <p>
 * The following schema fragment specifies the expected content contained within
 * this java content object. (defined at
 * file:/home/syjacob/automaticCode/jwsdp-1.6/jaxb/lib/hebrew_MWlexicon.xsd line
 * 1922)
 * <p>
 * 
 * <pre>
 * &lt;complexType name="MultiWordNounLexiconType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;choice maxOccurs="unbounded" minOccurs="0">
 *           &lt;element name="add" type="{}MultiWordNounExceptionType" maxOccurs="unbounded" minOccurs="0"/>
 *           &lt;element name="replace" type="{}MultiWordNounExceptionType" maxOccurs="unbounded" minOccurs="0"/>
 *           &lt;element name="remove" type="{}MultiWordNounExceptionType" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;/choice>
 *       &lt;/sequence>
 *       &lt;attribute name="consecutive" type="{http://www.w3.org/2001/XMLSchema}boolean" default="true" />
 *       &lt;attribute name="definiteness" type="{}AcronymDefinitenessType" default="external" />
 *       &lt;attribute name="feminine" type="{}FeminineType" default="unspecified" />
 *       &lt;attribute name="gender" type="{}GenderType" default="unspecified" />
 *       &lt;attribute name="inflectPossessiveP" type="{http://www.w3.org/2001/XMLSchema}boolean" default="false" />
 *       &lt;attribute name="inflectPossessiveS" type="{http://www.w3.org/2001/XMLSchema}boolean" default="false" />
 *       &lt;attribute name="inflectionBase" type="{http://www.w3.org/2001/XMLSchema}token" default="" />
 *       &lt;attribute name="mwPos" type="{}PosType" default="noun" />
 *       &lt;attribute name="number" type="{}NumberType" default="unspecified" />
 *       &lt;attribute name="plural" type="{}PluralType" default="im" />
 *       &lt;attribute name="type" type="{http://www.w3.org/2001/XMLSchema}token" default="NN" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 */
public interface MultiWordNounLexiconType {

	/**
	 * Gets the value of the type property.
	 * 
	 * @return possible object is {@link java.lang.String}
	 */
	java.lang.String getType();

	/**
	 * Sets the value of the type property.
	 * 
	 * @param value
	 *            allowed object is {@link java.lang.String}
	 */
	void setType(java.lang.String value);

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
	 * Gets the value of the feminine property.
	 * 
	 * @return possible object is {@link java.lang.String}
	 */
	java.lang.String getFeminine();

	/**
	 * Sets the value of the feminine property.
	 * 
	 * @param value
	 *            allowed object is {@link java.lang.String}
	 */
	void setFeminine(java.lang.String value);

	/**
	 * Gets the value of the gender property.
	 * 
	 * @return possible object is {@link java.lang.String}
	 */
	java.lang.String getGender();

	/**
	 * Sets the value of the gender property.
	 * 
	 * @param value
	 *            allowed object is {@link java.lang.String}
	 */
	void setGender(java.lang.String value);

	/**
	 * Gets the value of the plural property.
	 * 
	 * @return possible object is {@link java.lang.String}
	 */
	java.lang.String getPlural();

	/**
	 * Sets the value of the plural property.
	 * 
	 * @param value
	 *            allowed object is {@link java.lang.String}
	 */
	void setPlural(java.lang.String value);

	/**
	 * Gets the value of the number property.
	 * 
	 * @return possible object is {@link java.lang.String}
	 */
	java.lang.String getNumber();

	/**
	 * Sets the value of the number property.
	 * 
	 * @param value
	 *            allowed object is {@link java.lang.String}
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
	 * Gets the value of the inflectionBase property.
	 * 
	 * @return possible object is {@link java.lang.String}
	 */
	java.lang.String getInflectionBase();

	/**
	 * Sets the value of the inflectionBase property.
	 * 
	 * @param value
	 *            allowed object is {@link java.lang.String}
	 */
	void setInflectionBase(java.lang.String value);

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
	 * {@link lexicon.jaxb.MultiWordNounLexiconType.Add}
	 * {@link lexicon.jaxb.MultiWordNounLexiconType.Replace}
	 * {@link lexicon.jaxb.MultiWordNounLexiconType.Remove}
	 * 
	 */
	java.util.List getAddOrReplaceOrRemove();

	/**
	 * Gets the value of the mwPos property.
	 * 
	 * @return possible object is {@link java.lang.String}
	 */
	java.lang.String getMwPos();

	/**
	 * Sets the value of the mwPos property.
	 * 
	 * @param value
	 *            allowed object is {@link java.lang.String}
	 */
	void setMwPos(java.lang.String value);

	/**
	 * Gets the value of the definiteness property.
	 * 
	 * @return possible object is {@link java.lang.String}
	 */
	java.lang.String getDefiniteness();

	/**
	 * Sets the value of the definiteness property.
	 * 
	 * @param value
	 *            allowed object is {@link java.lang.String}
	 */
	void setDefiniteness(java.lang.String value);

	/**
	 * Java content class for add element declaration.
	 * <p>
	 * The following schema fragment specifies the expected content contained
	 * within this java content object. (defined at
	 * file:/home/syjacob/automaticCode/jwsdp-1.6/jaxb/lib/hebrew_MWlexicon.xsd
	 * line 1925)
	 * <p>
	 * 
	 * <pre>
	 * &lt;element name="add" type="{}MultiWordNounExceptionType"/>
	 * </pre>
	 * 
	 */
	public interface Add extends javax.xml.bind.Element,
			lexicon.jaxb.MultiWordNounExceptionType {

	}

	/**
	 * Java content class for remove element declaration.
	 * <p>
	 * The following schema fragment specifies the expected content contained
	 * within this java content object. (defined at
	 * file:/home/syjacob/automaticCode/jwsdp-1.6/jaxb/lib/hebrew_MWlexicon.xsd
	 * line 1927)
	 * <p>
	 * 
	 * <pre>
	 * &lt;element name="remove" type="{}MultiWordNounExceptionType"/>
	 * </pre>
	 * 
	 */
	public interface Remove extends javax.xml.bind.Element,
			lexicon.jaxb.MultiWordNounExceptionType {

	}

	/**
	 * Java content class for replace element declaration.
	 * <p>
	 * The following schema fragment specifies the expected content contained
	 * within this java content object. (defined at
	 * file:/home/syjacob/automaticCode/jwsdp-1.6/jaxb/lib/hebrew_MWlexicon.xsd
	 * line 1926)
	 * <p>
	 * 
	 * <pre>
	 * &lt;element name="replace" type="{}MultiWordNounExceptionType"/>
	 * </pre>
	 * 
	 */
	public interface Replace extends javax.xml.bind.Element,
			lexicon.jaxb.MultiWordNounExceptionType {

	}

}
