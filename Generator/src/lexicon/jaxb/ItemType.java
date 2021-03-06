//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v1.0.5-b16-fcs 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2010.12.23 at 10:34:08 AM IST 
//


package lexicon.jaxb;


/**
 * ������ ���� ����� �� ����� ��������.
 * �� ��� ������ ����� ����� ���� �"� ���� �����, id.
 * ����� ������ �� �� ��� ���� �� ������ �� ��� ���� ����� ����� �������, ��� ������ ������� ��� ������ ������� ���� ����� ���� ������� ���� ��� ����� ��� ���� ���. �� id ����� ������ ��� �� ������ id �� ��� item ��� ���� �����.
 * ���� ���� ���� ���� ���� ����� �� �������� �� item/@id ������, ���� ���� �� ����� �� ������ ����� ����� �������.
 * ��� undotted ���� ������ ���� ���� ������� ����� ��� ����� ����� �� �� ���� ������� ����� �����.
 * ��� transliterated ���� ����� ���� ����� ����� ��� undotted, ��� ���� �� ������ ����� ��� ����� ���� ������� ��� metadata/transliteration.
 * �����:
 * ======
 * ������� undotted �- transliterated ������ ����� ����� �� ���� ������� �� ��� ������ (��� ����� ������ �- /lexicon/metadata/transliteration).
 * 
 * Java content class for ItemType complex type.
 * <p>The following schema fragment specifies the expected content contained within this java content object. (defined at file:/home/syjacob/automaticCode/jwsdp-1.6/jaxb/lib/hebrew_MWlexicon.xsd line 99)
 * <p>
 * <pre>
 * &lt;complexType name="ItemType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="comment" type="{http://www.w3.org/2001/XMLSchema}token" minOccurs="0"/>
 *         &lt;choice>
 *           &lt;element name="adjective" type="{}AdjectiveLexiconType"/>
 *           &lt;element name="adverb" type="{}AdverbLexiconType"/>
 *           &lt;element name="conjunction" type="{}ConjunctionLexiconType"/>
 *           &lt;element name="interjection" type="{}InterjectionLexiconType"/>
 *           &lt;element name="interrogative" type="{}InterrogativeLexiconType"/>
 *           &lt;element name="negation" type="{}NegationLexiconType"/>
 *           &lt;element name="title" type="{}TitleLexiconType"/>
 *           &lt;element name="noun" type="{}NounLexiconType"/>
 *           &lt;element name="preposition" type="{}PrepositionLexiconType"/>
 *           &lt;element name="pronoun" type="{}PronounLexiconType"/>
 *           &lt;element name="properName" type="{}ProperNameLexiconType"/>
 *           &lt;element name="modal" type="{}ModalLexiconType"/>
 *           &lt;element name="quantifier" type="{}QuantifierLexiconType"/>
 *           &lt;element name="verb" type="{}VerbLexiconType"/>
 *           &lt;element name="existential" type="{}ExistentialLexiconType"/>
 *           &lt;element name="impersonal" type="{}ImpersonalLexiconType"/>
 *           &lt;element name="wPrefix" type="{}WprefixLexiconType"/>
 *           &lt;element name="copula" type="{}CopulaLexiconType"/>
 *           &lt;element name="numeral" type="{}NumeralLexiconType"/>
 *           &lt;element name="acronym" type="{}AcronymLexiconType"/>
 *           &lt;element name="multiWordFrozen" type="{}MultiWordFrozenLexiconType"/>
 *           &lt;element name="multiWordPreposition" type="{}MultiWordPrepositionLexiconType"/>
 *           &lt;element name="multiWordNoun" type="{}MultiWordNounLexiconType"/>
 *           &lt;element name="multiWordNounAdjective" type="{}MultiWordNounAdjectiveLexiconType"/>
 *         &lt;/choice>
 *         &lt;element name="sense" type="{}SenseType" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *       &lt;attribute name="dotted" type="{http://www.w3.org/2001/XMLSchema}token" default="" />
 *       &lt;attribute name="id" use="required" type="{http://www.w3.org/2001/XMLSchema}token" />
 *       &lt;attribute name="register" type="{}RegisterType" default="formal" />
 *       &lt;attribute name="spelling" type="{}SpellingType" default="standard" />
 *       &lt;attribute name="transliterated" use="required" type="{http://www.w3.org/2001/XMLSchema}token" />
 *       &lt;attribute name="undotted" use="required" type="{http://www.w3.org/2001/XMLSchema}token" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 */
public interface ItemType {


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
     * Gets the value of the multiWordNounAdjective property.
     * 
     * @return
     *     possible object is
     *     {@link lexicon.jaxb.MultiWordNounAdjectiveLexiconType}
     */
    lexicon.jaxb.MultiWordNounAdjectiveLexiconType getMultiWordNounAdjective();

    /**
     * Sets the value of the multiWordNounAdjective property.
     * 
     * @param value
     *     allowed object is
     *     {@link lexicon.jaxb.MultiWordNounAdjectiveLexiconType}
     */
    void setMultiWordNounAdjective(lexicon.jaxb.MultiWordNounAdjectiveLexiconType value);

    /**
     * Gets the value of the Sense property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the Sense property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getSense().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link lexicon.jaxb.SenseType}
     * 
     */
    java.util.List getSense();

    /**
     * Gets the value of the adverb property.
     * 
     * @return
     *     possible object is
     *     {@link lexicon.jaxb.AdverbLexiconType}
     */
    lexicon.jaxb.AdverbLexiconType getAdverb();

    /**
     * Sets the value of the adverb property.
     * 
     * @param value
     *     allowed object is
     *     {@link lexicon.jaxb.AdverbLexiconType}
     */
    void setAdverb(lexicon.jaxb.AdverbLexiconType value);

    /**
     * Gets the value of the multiWordFrozen property.
     * 
     * @return
     *     possible object is
     *     {@link lexicon.jaxb.MultiWordFrozenLexiconType}
     */
    lexicon.jaxb.MultiWordFrozenLexiconType getMultiWordFrozen();

    /**
     * Sets the value of the multiWordFrozen property.
     * 
     * @param value
     *     allowed object is
     *     {@link lexicon.jaxb.MultiWordFrozenLexiconType}
     */
    void setMultiWordFrozen(lexicon.jaxb.MultiWordFrozenLexiconType value);

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
     * Gets the value of the wPrefix property.
     * 
     * @return
     *     possible object is
     *     {@link lexicon.jaxb.WprefixLexiconType}
     */
    lexicon.jaxb.WprefixLexiconType getWPrefix();

    /**
     * Sets the value of the wPrefix property.
     * 
     * @param value
     *     allowed object is
     *     {@link lexicon.jaxb.WprefixLexiconType}
     */
    void setWPrefix(lexicon.jaxb.WprefixLexiconType value);

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
     * Gets the value of the acronym property.
     * 
     * @return
     *     possible object is
     *     {@link lexicon.jaxb.AcronymLexiconType}
     */
    lexicon.jaxb.AcronymLexiconType getAcronym();

    /**
     * Sets the value of the acronym property.
     * 
     * @param value
     *     allowed object is
     *     {@link lexicon.jaxb.AcronymLexiconType}
     */
    void setAcronym(lexicon.jaxb.AcronymLexiconType value);

    /**
     * Gets the value of the properName property.
     * 
     * @return
     *     possible object is
     *     {@link lexicon.jaxb.ProperNameLexiconType}
     */
    lexicon.jaxb.ProperNameLexiconType getProperName();

    /**
     * Sets the value of the properName property.
     * 
     * @param value
     *     allowed object is
     *     {@link lexicon.jaxb.ProperNameLexiconType}
     */
    void setProperName(lexicon.jaxb.ProperNameLexiconType value);

    /**
     * Gets the value of the impersonal property.
     * 
     * @return
     *     possible object is
     *     {@link lexicon.jaxb.ImpersonalLexiconType}
     */
    lexicon.jaxb.ImpersonalLexiconType getImpersonal();

    /**
     * Sets the value of the impersonal property.
     * 
     * @param value
     *     allowed object is
     *     {@link lexicon.jaxb.ImpersonalLexiconType}
     */
    void setImpersonal(lexicon.jaxb.ImpersonalLexiconType value);

    /**
     * Gets the value of the conjunction property.
     * 
     * @return
     *     possible object is
     *     {@link lexicon.jaxb.ConjunctionLexiconType}
     */
    lexicon.jaxb.ConjunctionLexiconType getConjunction();

    /**
     * Sets the value of the conjunction property.
     * 
     * @param value
     *     allowed object is
     *     {@link lexicon.jaxb.ConjunctionLexiconType}
     */
    void setConjunction(lexicon.jaxb.ConjunctionLexiconType value);

    /**
     * Gets the value of the adjective property.
     * 
     * @return
     *     possible object is
     *     {@link lexicon.jaxb.AdjectiveLexiconType}
     */
    lexicon.jaxb.AdjectiveLexiconType getAdjective();

    /**
     * Sets the value of the adjective property.
     * 
     * @param value
     *     allowed object is
     *     {@link lexicon.jaxb.AdjectiveLexiconType}
     */
    void setAdjective(lexicon.jaxb.AdjectiveLexiconType value);

    /**
     * Gets the value of the copula property.
     * 
     * @return
     *     possible object is
     *     {@link lexicon.jaxb.CopulaLexiconType}
     */
    lexicon.jaxb.CopulaLexiconType getCopula();

    /**
     * Sets the value of the copula property.
     * 
     * @param value
     *     allowed object is
     *     {@link lexicon.jaxb.CopulaLexiconType}
     */
    void setCopula(lexicon.jaxb.CopulaLexiconType value);

    /**
     * Gets the value of the interjection property.
     * 
     * @return
     *     possible object is
     *     {@link lexicon.jaxb.InterjectionLexiconType}
     */
    lexicon.jaxb.InterjectionLexiconType getInterjection();

    /**
     * Sets the value of the interjection property.
     * 
     * @param value
     *     allowed object is
     *     {@link lexicon.jaxb.InterjectionLexiconType}
     */
    void setInterjection(lexicon.jaxb.InterjectionLexiconType value);

    /**
     * Gets the value of the multiWordPreposition property.
     * 
     * @return
     *     possible object is
     *     {@link lexicon.jaxb.MultiWordPrepositionLexiconType}
     */
    lexicon.jaxb.MultiWordPrepositionLexiconType getMultiWordPreposition();

    /**
     * Sets the value of the multiWordPreposition property.
     * 
     * @param value
     *     allowed object is
     *     {@link lexicon.jaxb.MultiWordPrepositionLexiconType}
     */
    void setMultiWordPreposition(lexicon.jaxb.MultiWordPrepositionLexiconType value);

    /**
     * Gets the value of the modal property.
     * 
     * @return
     *     possible object is
     *     {@link lexicon.jaxb.ModalLexiconType}
     */
    lexicon.jaxb.ModalLexiconType getModal();

    /**
     * Sets the value of the modal property.
     * 
     * @param value
     *     allowed object is
     *     {@link lexicon.jaxb.ModalLexiconType}
     */
    void setModal(lexicon.jaxb.ModalLexiconType value);

    /**
     * Gets the value of the numeral property.
     * 
     * @return
     *     possible object is
     *     {@link lexicon.jaxb.NumeralLexiconType}
     */
    lexicon.jaxb.NumeralLexiconType getNumeral();

    /**
     * Sets the value of the numeral property.
     * 
     * @param value
     *     allowed object is
     *     {@link lexicon.jaxb.NumeralLexiconType}
     */
    void setNumeral(lexicon.jaxb.NumeralLexiconType value);

    /**
     * Gets the value of the preposition property.
     * 
     * @return
     *     possible object is
     *     {@link lexicon.jaxb.PrepositionLexiconType}
     */
    lexicon.jaxb.PrepositionLexiconType getPreposition();

    /**
     * Sets the value of the preposition property.
     * 
     * @param value
     *     allowed object is
     *     {@link lexicon.jaxb.PrepositionLexiconType}
     */
    void setPreposition(lexicon.jaxb.PrepositionLexiconType value);

    /**
     * Gets the value of the quantifier property.
     * 
     * @return
     *     possible object is
     *     {@link lexicon.jaxb.QuantifierLexiconType}
     */
    lexicon.jaxb.QuantifierLexiconType getQuantifier();

    /**
     * Sets the value of the quantifier property.
     * 
     * @param value
     *     allowed object is
     *     {@link lexicon.jaxb.QuantifierLexiconType}
     */
    void setQuantifier(lexicon.jaxb.QuantifierLexiconType value);

    /**
     * Gets the value of the pronoun property.
     * 
     * @return
     *     possible object is
     *     {@link lexicon.jaxb.PronounLexiconType}
     */
    lexicon.jaxb.PronounLexiconType getPronoun();

    /**
     * Sets the value of the pronoun property.
     * 
     * @param value
     *     allowed object is
     *     {@link lexicon.jaxb.PronounLexiconType}
     */
    void setPronoun(lexicon.jaxb.PronounLexiconType value);

    /**
     * Gets the value of the verb property.
     * 
     * @return
     *     possible object is
     *     {@link lexicon.jaxb.VerbLexiconType}
     */
    lexicon.jaxb.VerbLexiconType getVerb();

    /**
     * Sets the value of the verb property.
     * 
     * @param value
     *     allowed object is
     *     {@link lexicon.jaxb.VerbLexiconType}
     */
    void setVerb(lexicon.jaxb.VerbLexiconType value);

    /**
     * Gets the value of the multiWordNoun property.
     * 
     * @return
     *     possible object is
     *     {@link lexicon.jaxb.MultiWordNounLexiconType}
     */
    lexicon.jaxb.MultiWordNounLexiconType getMultiWordNoun();

    /**
     * Sets the value of the multiWordNoun property.
     * 
     * @param value
     *     allowed object is
     *     {@link lexicon.jaxb.MultiWordNounLexiconType}
     */
    void setMultiWordNoun(lexicon.jaxb.MultiWordNounLexiconType value);

    /**
     * Gets the value of the noun property.
     * 
     * @return
     *     possible object is
     *     {@link lexicon.jaxb.NounLexiconType}
     */
    lexicon.jaxb.NounLexiconType getNoun();

    /**
     * Sets the value of the noun property.
     * 
     * @param value
     *     allowed object is
     *     {@link lexicon.jaxb.NounLexiconType}
     */
    void setNoun(lexicon.jaxb.NounLexiconType value);

    /**
     * Gets the value of the negation property.
     * 
     * @return
     *     possible object is
     *     {@link lexicon.jaxb.NegationLexiconType}
     */
    lexicon.jaxb.NegationLexiconType getNegation();

    /**
     * Sets the value of the negation property.
     * 
     * @param value
     *     allowed object is
     *     {@link lexicon.jaxb.NegationLexiconType}
     */
    void setNegation(lexicon.jaxb.NegationLexiconType value);

    /**
     * Gets the value of the existential property.
     * 
     * @return
     *     possible object is
     *     {@link lexicon.jaxb.ExistentialLexiconType}
     */
    lexicon.jaxb.ExistentialLexiconType getExistential();

    /**
     * Sets the value of the existential property.
     * 
     * @param value
     *     allowed object is
     *     {@link lexicon.jaxb.ExistentialLexiconType}
     */
    void setExistential(lexicon.jaxb.ExistentialLexiconType value);

    /**
     * Gets the value of the interrogative property.
     * 
     * @return
     *     possible object is
     *     {@link lexicon.jaxb.InterrogativeLexiconType}
     */
    lexicon.jaxb.InterrogativeLexiconType getInterrogative();

    /**
     * Sets the value of the interrogative property.
     * 
     * @param value
     *     allowed object is
     *     {@link lexicon.jaxb.InterrogativeLexiconType}
     */
    void setInterrogative(lexicon.jaxb.InterrogativeLexiconType value);

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
     * Gets the value of the title property.
     * 
     * @return
     *     possible object is
     *     {@link lexicon.jaxb.TitleLexiconType}
     */
    lexicon.jaxb.TitleLexiconType getTitle();

    /**
     * Sets the value of the title property.
     * 
     * @param value
     *     allowed object is
     *     {@link lexicon.jaxb.TitleLexiconType}
     */
    void setTitle(lexicon.jaxb.TitleLexiconType value);

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
    
    /**
     * Gets the value of the alternate id property.
     * 
     * @return
     *     possible object is
     *     {@link java.lang.String}
     */
    java.lang.String getAlternateId();

    /**
     * Sets the value of the alternate id property.
     * 
     * @param value
     *     allowed object is
     *     {@link java.lang.String}
     */
    void setAlternateId(java.lang.String value);

}
