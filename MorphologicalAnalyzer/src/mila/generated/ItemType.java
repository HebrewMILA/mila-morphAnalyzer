//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v1.0.5-b16-fcs 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2008.12.01 at 02:23:12 PM IST 
//

package mila.generated;

/**
 * ������ ���� ����� �� ����� ��������. �� ��� ������ ����� ����� ���� �"� ����
 * �����, id. ����� ������ �� �� ��� ���� �� ������ �� ��� ���� ����� �����
 * �������, ��� ������ ������� ��� ������ ������� ���� ����� ���� ������� ����
 * ��� ����� ��� ���� ���. �� id ����� ������ ��� �� ������ id �� ��� item ���
 * ���� �����. ���� ���� ���� ���� ���� ����� �� �������� �� item/@id ������,
 * ���� ���� �� ����� �� ������ ����� ����� �������. ��� undotted ���� ������
 * ���� ���� ������� ����� ��� ����� ����� �� �� ���� ������� ����� �����. ���
 * transliterated ���� ����� ���� ����� ����� ��� undotted, ��� ���� �� ������
 * ����� ��� ����� ���� ������� ��� metadata/transliteration. �����: ======
 * ������� undotted �- transliterated ������ ����� ����� �� ���� ������� �� ���
 * ������ (��� ����� ������ �- /lexicon/metadata/transliteration).
 * 
 * Java content class for ItemType complex type.
 * <p>
 * The following schema fragment specifies the expected content contained within
 * this java content object. (defined at
 * file:/C:/Sun/jwsdp-1.6/jaxb/lib/hebrew_MWlexicon_12_11_2008.xsd line 99)
 * <p>
 * 
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
 *           &lt;element name="multiWord" type="{}MultiWordLexiconType"/>
 *         &lt;/choice>
 *         &lt;element name="sense" type="{}SenseType" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *       &lt;attribute name="dotted" type="{http://www.w3.org/2001/XMLSchema}token" default="" />
 *       &lt;attribute name="id" use="required" type="{http://www.w3.org/2001/XMLSchema}token" />
 *       &lt;attribute name="script" type="{}ScriptType" default="formal" />
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
	 * Gets the value of the acronym property.
	 * 
	 * @return possible object is {@link mila.generated.AcronymLexiconType}
	 */
	mila.generated.AcronymLexiconType getAcronym();

	/**
	 * Gets the value of the adjective property.
	 * 
	 * @return possible object is {@link mila.generated.AdjectiveLexiconType}
	 */
	mila.generated.AdjectiveLexiconType getAdjective();

	/**
	 * Gets the value of the adverb property.
	 * 
	 * @return possible object is {@link mila.generated.AdverbLexiconType}
	 */
	mila.generated.AdverbLexiconType getAdverb();

	/**
	 * Gets the value of the comment property.
	 * 
	 * @return possible object is {@link java.lang.String}
	 */
	java.lang.String getComment();

	/**
	 * Gets the value of the conjunction property.
	 * 
	 * @return possible object is {@link mila.generated.ConjunctionLexiconType}
	 */
	mila.generated.ConjunctionLexiconType getConjunction();

	/**
	 * Gets the value of the copula property.
	 * 
	 * @return possible object is {@link mila.generated.CopulaLexiconType}
	 */
	mila.generated.CopulaLexiconType getCopula();

	/**
	 * Gets the value of the dotted property.
	 * 
	 * @return possible object is {@link java.lang.String}
	 */
	java.lang.String getDotted();

	/**
	 * Gets the value of the existential property.
	 * 
	 * @return possible object is {@link mila.generated.ExistentialLexiconType}
	 */
	mila.generated.ExistentialLexiconType getExistential();

	/**
	 * Gets the value of the id property.
	 * 
	 * @return possible object is {@link java.lang.String}
	 */
	java.lang.String getId();

	/**
	 * Gets the value of the impersonal property.
	 * 
	 * @return possible object is {@link mila.generated.ImpersonalLexiconType}
	 */
	mila.generated.ImpersonalLexiconType getImpersonal();

	/**
	 * Gets the value of the interjection property.
	 * 
	 * @return possible object is {@link mila.generated.InterjectionLexiconType}
	 */
	mila.generated.InterjectionLexiconType getInterjection();

	/**
	 * Gets the value of the interrogative property.
	 * 
	 * @return possible object is {@link mila.generated.InterrogativeLexiconType}
	 */
	mila.generated.InterrogativeLexiconType getInterrogative();

	/**
	 * Gets the value of the modal property.
	 * 
	 * @return possible object is {@link mila.generated.ModalLexiconType}
	 */
	mila.generated.ModalLexiconType getModal();

	/**
	 * Gets the value of the multiWord property.
	 * 
	 * @return possible object is {@link mila.generated.MultiWordLexiconType}
	 */
	mila.generated.MultiWordLexiconType getMultiWord();

	/**
	 * Gets the value of the negation property.
	 * 
	 * @return possible object is {@link mila.generated.NegationLexiconType}
	 */
	mila.generated.NegationLexiconType getNegation();

	/**
	 * Gets the value of the noun property.
	 * 
	 * @return possible object is {@link mila.generated.NounLexiconType}
	 */
	mila.generated.NounLexiconType getNoun();

	/**
	 * Gets the value of the numeral property.
	 * 
	 * @return possible object is {@link mila.generated.NumeralLexiconType}
	 */
	mila.generated.NumeralLexiconType getNumeral();

	/**
	 * Gets the value of the preposition property.
	 * 
	 * @return possible object is {@link mila.generated.PrepositionLexiconType}
	 */
	mila.generated.PrepositionLexiconType getPreposition();

	/**
	 * Gets the value of the pronoun property.
	 * 
	 * @return possible object is {@link mila.generated.PronounLexiconType}
	 */
	mila.generated.PronounLexiconType getPronoun();

	/**
	 * Gets the value of the properName property.
	 * 
	 * @return possible object is {@link mila.generated.ProperNameLexiconType}
	 */
	mila.generated.ProperNameLexiconType getProperName();

	/**
	 * Gets the value of the quantifier property.
	 * 
	 * @return possible object is {@link mila.generated.QuantifierLexiconType}
	 */
	mila.generated.QuantifierLexiconType getQuantifier();

	/**
	 * Gets the value of the script property.
	 * 
	 * @return possible object is {@link java.lang.String}
	 */
	java.lang.String getScript();

	/**
	 * Gets the value of the Sense property.
	 * 
	 * <p>
	 * This accessor method returns a reference to the live list, not a
	 * snapshot. Therefore any modification you make to the returned list will
	 * be present inside the JAXB object. This is why there is not a
	 * <CODE>set</CODE> method for the Sense property.
	 * 
	 * <p>
	 * For example, to add a new item, do as follows:
	 * 
	 * <pre>
	 * getSense().add(newItem);
	 * </pre>
	 * 
	 * 
	 * <p>
	 * Objects of the following type(s) are allowed in the list
	 * {@link mila.generated.SenseType}
	 * 
	 */
	java.util.List getSense();

	/**
	 * Gets the value of the title property.
	 * 
	 * @return possible object is {@link mila.generated.TitleLexiconType}
	 */
	mila.generated.TitleLexiconType getTitle();

	/**
	 * Gets the value of the transliterated property.
	 * 
	 * @return possible object is {@link java.lang.String}
	 */
	java.lang.String getTransliterated();

	/**
	 * Gets the value of the undotted property.
	 * 
	 * @return possible object is {@link java.lang.String}
	 */
	java.lang.String getUndotted();

	/**
	 * Gets the value of the verb property.
	 * 
	 * @return possible object is {@link mila.generated.VerbLexiconType}
	 */
	mila.generated.VerbLexiconType getVerb();

	/**
	 * Gets the value of the wPrefix property.
	 * 
	 * @return possible object is {@link mila.generated.WprefixLexiconType}
	 */
	mila.generated.WprefixLexiconType getWPrefix();

	/**
	 * Sets the value of the acronym property.
	 * 
	 * @param value
	 *            allowed object is {@link mila.generated.AcronymLexiconType}
	 */
	void setAcronym(mila.generated.AcronymLexiconType value);

	/**
	 * Sets the value of the adjective property.
	 * 
	 * @param value
	 *            allowed object is {@link mila.generated.AdjectiveLexiconType}
	 */
	void setAdjective(mila.generated.AdjectiveLexiconType value);

	/**
	 * Sets the value of the adverb property.
	 * 
	 * @param value
	 *            allowed object is {@link mila.generated.AdverbLexiconType}
	 */
	void setAdverb(mila.generated.AdverbLexiconType value);

	/**
	 * Sets the value of the comment property.
	 * 
	 * @param value
	 *            allowed object is {@link java.lang.String}
	 */
	void setComment(java.lang.String value);

	/**
	 * Sets the value of the conjunction property.
	 * 
	 * @param value
	 *            allowed object is {@link mila.generated.ConjunctionLexiconType}
	 */
	void setConjunction(mila.generated.ConjunctionLexiconType value);

	/**
	 * Sets the value of the copula property.
	 * 
	 * @param value
	 *            allowed object is {@link mila.generated.CopulaLexiconType}
	 */
	void setCopula(mila.generated.CopulaLexiconType value);

	/**
	 * Sets the value of the dotted property.
	 * 
	 * @param value
	 *            allowed object is {@link java.lang.String}
	 */
	void setDotted(java.lang.String value);

	/**
	 * Sets the value of the existential property.
	 * 
	 * @param value
	 *            allowed object is {@link mila.generated.ExistentialLexiconType}
	 */
	void setExistential(mila.generated.ExistentialLexiconType value);

	/**
	 * Sets the value of the id property.
	 * 
	 * @param value
	 *            allowed object is {@link java.lang.String}
	 */
	void setId(java.lang.String value);

	/**
	 * Sets the value of the impersonal property.
	 * 
	 * @param value
	 *            allowed object is {@link mila.generated.ImpersonalLexiconType}
	 */
	void setImpersonal(mila.generated.ImpersonalLexiconType value);

	/**
	 * Sets the value of the interjection property.
	 * 
	 * @param value
	 *            allowed object is {@link mila.generated.InterjectionLexiconType}
	 */
	void setInterjection(mila.generated.InterjectionLexiconType value);

	/**
	 * Sets the value of the interrogative property.
	 * 
	 * @param value
	 *            allowed object is {@link mila.generated.InterrogativeLexiconType}
	 */
	void setInterrogative(mila.generated.InterrogativeLexiconType value);

	/**
	 * Sets the value of the modal property.
	 * 
	 * @param value
	 *            allowed object is {@link mila.generated.ModalLexiconType}
	 */
	void setModal(mila.generated.ModalLexiconType value);

	/**
	 * Sets the value of the multiWord property.
	 * 
	 * @param value
	 *            allowed object is {@link mila.generated.MultiWordLexiconType}
	 */
	void setMultiWord(mila.generated.MultiWordLexiconType value);

	/**
	 * Sets the value of the negation property.
	 * 
	 * @param value
	 *            allowed object is {@link mila.generated.NegationLexiconType}
	 */
	void setNegation(mila.generated.NegationLexiconType value);

	/**
	 * Sets the value of the noun property.
	 * 
	 * @param value
	 *            allowed object is {@link mila.generated.NounLexiconType}
	 */
	void setNoun(mila.generated.NounLexiconType value);

	/**
	 * Sets the value of the numeral property.
	 * 
	 * @param value
	 *            allowed object is {@link mila.generated.NumeralLexiconType}
	 */
	void setNumeral(mila.generated.NumeralLexiconType value);

	/**
	 * Sets the value of the preposition property.
	 * 
	 * @param value
	 *            allowed object is {@link mila.generated.PrepositionLexiconType}
	 */
	void setPreposition(mila.generated.PrepositionLexiconType value);

	/**
	 * Sets the value of the pronoun property.
	 * 
	 * @param value
	 *            allowed object is {@link mila.generated.PronounLexiconType}
	 */
	void setPronoun(mila.generated.PronounLexiconType value);

	/**
	 * Sets the value of the properName property.
	 * 
	 * @param value
	 *            allowed object is {@link mila.generated.ProperNameLexiconType}
	 */
	void setProperName(mila.generated.ProperNameLexiconType value);

	/**
	 * Sets the value of the quantifier property.
	 * 
	 * @param value
	 *            allowed object is {@link mila.generated.QuantifierLexiconType}
	 */
	void setQuantifier(mila.generated.QuantifierLexiconType value);

	/**
	 * Sets the value of the script property.
	 * 
	 * @param value
	 *            allowed object is {@link java.lang.String}
	 */
	void setScript(java.lang.String value);

	/**
	 * Sets the value of the title property.
	 * 
	 * @param value
	 *            allowed object is {@link mila.generated.TitleLexiconType}
	 */
	void setTitle(mila.generated.TitleLexiconType value);

	/**
	 * Sets the value of the transliterated property.
	 * 
	 * @param value
	 *            allowed object is {@link java.lang.String}
	 */
	void setTransliterated(java.lang.String value);

	/**
	 * Sets the value of the undotted property.
	 * 
	 * @param value
	 *            allowed object is {@link java.lang.String}
	 */
	void setUndotted(java.lang.String value);

	/**
	 * Sets the value of the verb property.
	 * 
	 * @param value
	 *            allowed object is {@link mila.generated.VerbLexiconType}
	 */
	void setVerb(mila.generated.VerbLexiconType value);

	/**
	 * Sets the value of the wPrefix property.
	 * 
	 * @param value
	 *            allowed object is {@link mila.generated.WprefixLexiconType}
	 */
	void setWPrefix(mila.generated.WprefixLexiconType value);

}