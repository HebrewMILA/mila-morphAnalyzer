//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v1.0.5-b16-fcs 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2010.12.23 at 10:34:08 AM IST 
//

package lexicon.jaxb;

/**
 * ���� �� ���� ���� �����
 * 
 * Java content class for AcronymLexiconType complex type.
 * <p>
 * The following schema fragment specifies the expected content contained within
 * this java content object. (defined at
 * file:/home/syjacob/automaticCode/jwsdp-1.6/jaxb/lib/hebrew_MWlexicon.xsd line
 * 901)
 * <p>
 * 
 * <pre>
 * &lt;complexType name="AcronymLexiconType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;choice maxOccurs="unbounded" minOccurs="0">
 *           &lt;element name="add" type="{}AcronymExceptionType" maxOccurs="unbounded" minOccurs="0"/>
 *           &lt;element name="replace" type="{}AcronymExceptionType" maxOccurs="unbounded" minOccurs="0"/>
 *           &lt;element name="remove" type="{}AcronymExceptionType" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;/choice>
 *       &lt;/sequence>
 *       &lt;attribute name="adjectiveLexicalLink" type="{http://www.w3.org/2001/XMLSchema}token" default="" />
 *       &lt;attribute name="conjunctionType" type="{}ConjunctionType" default="unspecified" />
 *       &lt;attribute name="definiteness" type="{}AcronymDefinitenessType" default="external" />
 *       &lt;attribute name="feminine" type="{}FeminineType" default="unspecified" />
 *       &lt;attribute name="gender" type="{}GenderType" default="masculine" />
 *       &lt;attribute name="generateAdjective" type="{http://www.w3.org/2001/XMLSchema}boolean" default="false" />
 *       &lt;attribute name="inflectConstructP" type="{http://www.w3.org/2001/XMLSchema}boolean" default="true" />
 *       &lt;attribute name="inflectConstructS" type="{http://www.w3.org/2001/XMLSchema}boolean" default="true" />
 *       &lt;attribute name="istLexicalLink" type="{http://www.w3.org/2001/XMLSchema}token" default="" />
 *       &lt;attribute name="meaning" type="{http://www.w3.org/2001/XMLSchema}token" default="" />
 *       &lt;attribute name="niqLexicalLink" type="{http://www.w3.org/2001/XMLSchema}token" default="" />
 *       &lt;attribute name="noInvertedCommas" type="{}noInvertedCommasType" default="none" />
 *       &lt;attribute name="number" type="{}NumberType" default="singular" />
 *       &lt;attribute name="plural" type="{}PluralType" default="unspecified" />
 *       &lt;attribute name="pos" type="{}PosType" default="noun" />
 *       &lt;attribute name="properNameType" type="{}NamedEntityType" default="unspecified" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 */
public interface AcronymLexiconType {

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
	 * Gets the value of the properNameType property.
	 * 
	 * @return possible object is {@link java.lang.String}
	 */
	java.lang.String getProperNameType();

	/**
	 * Sets the value of the properNameType property.
	 * 
	 * @param value
	 *            allowed object is {@link java.lang.String}
	 */
	void setProperNameType(java.lang.String value);

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
	 * Gets the value of the pos property.
	 * 
	 * @return possible object is {@link java.lang.String}
	 */
	java.lang.String getPos();

	/**
	 * Sets the value of the pos property.
	 * 
	 * @param value
	 *            allowed object is {@link java.lang.String}
	 */
	void setPos(java.lang.String value);

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
	 * Gets the value of the noInvertedCommas property.
	 * 
	 * @return possible object is {@link java.lang.String}
	 */
	java.lang.String getNoInvertedCommas();

	/**
	 * Sets the value of the noInvertedCommas property.
	 * 
	 * @param value
	 *            allowed object is {@link java.lang.String}
	 */
	void setNoInvertedCommas(java.lang.String value);

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
	 * Gets the value of the istLexicalLink property.
	 * 
	 * @return possible object is {@link java.lang.String}
	 */
	java.lang.String getIstLexicalLink();

	/**
	 * Sets the value of the istLexicalLink property.
	 * 
	 * @param value
	 *            allowed object is {@link java.lang.String}
	 */
	void setIstLexicalLink(java.lang.String value);

	/**
	 * Gets the value of the generateAdjective property.
	 * 
	 */
	boolean isGenerateAdjective();

	/**
	 * Sets the value of the generateAdjective property.
	 * 
	 */
	void setGenerateAdjective(boolean value);

	/**
	 * Gets the value of the niqLexicalLink property.
	 * 
	 * @return possible object is {@link java.lang.String}
	 */
	java.lang.String getNiqLexicalLink();

	/**
	 * Sets the value of the niqLexicalLink property.
	 * 
	 * @param value
	 *            allowed object is {@link java.lang.String}
	 */
	void setNiqLexicalLink(java.lang.String value);

	/**
	 * Gets the value of the inflectConstructS property.
	 * 
	 */
	boolean isInflectConstructS();

	/**
	 * Sets the value of the inflectConstructS property.
	 * 
	 */
	void setInflectConstructS(boolean value);

	/**
	 * Gets the value of the meaning property.
	 * 
	 * @return possible object is {@link java.lang.String}
	 */
	java.lang.String getMeaning();

	/**
	 * Sets the value of the meaning property.
	 * 
	 * @param value
	 *            allowed object is {@link java.lang.String}
	 */
	void setMeaning(java.lang.String value);

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
	 * Gets the value of the conjunctionType property.
	 * 
	 * @return possible object is {@link java.lang.String}
	 */
	java.lang.String getConjunctionType();

	/**
	 * Sets the value of the conjunctionType property.
	 * 
	 * @param value
	 *            allowed object is {@link java.lang.String}
	 */
	void setConjunctionType(java.lang.String value);

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
	 * {@link lexicon.jaxb.AcronymLexiconType.Remove}
	 * {@link lexicon.jaxb.AcronymLexiconType.Add}
	 * {@link lexicon.jaxb.AcronymLexiconType.Replace}
	 * 
	 */
	java.util.List getAddOrReplaceOrRemove();

	/**
	 * Gets the value of the inflectConstructP property.
	 * 
	 */
	boolean isInflectConstructP();

	/**
	 * Sets the value of the inflectConstructP property.
	 * 
	 */
	void setInflectConstructP(boolean value);

	/**
	 * Gets the value of the adjectiveLexicalLink property.
	 * 
	 * @return possible object is {@link java.lang.String}
	 */
	java.lang.String getAdjectiveLexicalLink();

	/**
	 * Sets the value of the adjectiveLexicalLink property.
	 * 
	 * @param value
	 *            allowed object is {@link java.lang.String}
	 */
	void setAdjectiveLexicalLink(java.lang.String value);

	/**
	 * Java content class for add element declaration.
	 * <p>
	 * The following schema fragment specifies the expected content contained
	 * within this java content object. (defined at
	 * file:/home/syjacob/automaticCode/jwsdp-1.6/jaxb/lib/hebrew_MWlexicon.xsd
	 * line 909)
	 * <p>
	 * 
	 * <pre>
	 * &lt;element name="add" type="{}AcronymExceptionType"/>
	 * </pre>
	 * 
	 */
	public interface Add extends javax.xml.bind.Element,
			lexicon.jaxb.AcronymExceptionType {

	}

	/**
	 * Java content class for remove element declaration.
	 * <p>
	 * The following schema fragment specifies the expected content contained
	 * within this java content object. (defined at
	 * file:/home/syjacob/automaticCode/jwsdp-1.6/jaxb/lib/hebrew_MWlexicon.xsd
	 * line 911)
	 * <p>
	 * 
	 * <pre>
	 * &lt;element name="remove" type="{}AcronymExceptionType"/>
	 * </pre>
	 * 
	 */
	public interface Remove extends javax.xml.bind.Element,
			lexicon.jaxb.AcronymExceptionType {

	}

	/**
	 * Java content class for replace element declaration.
	 * <p>
	 * The following schema fragment specifies the expected content contained
	 * within this java content object. (defined at
	 * file:/home/syjacob/automaticCode/jwsdp-1.6/jaxb/lib/hebrew_MWlexicon.xsd
	 * line 910)
	 * <p>
	 * 
	 * <pre>
	 * &lt;element name="replace" type="{}AcronymExceptionType"/>
	 * </pre>
	 * 
	 */
	public interface Replace extends javax.xml.bind.Element,
			lexicon.jaxb.AcronymExceptionType {

	}

}