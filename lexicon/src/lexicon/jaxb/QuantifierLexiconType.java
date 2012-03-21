//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v1.0.5-b16-fcs 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2010.12.23 at 10:34:08 AM IST 
//

package lexicon.jaxb;

/**
 * ���� �� ����� ����� ������� ����� ����. ������� �� �"� Glinert "The grammar
 * of Modern Hebrew". ����� ������� ����� ���� ��� ����� ���� ����. ����, �����
 * ����� ���� �� ���� �����. ���� ���� ������� �: * ������ ������ * ������ �����
 * * ������ ������ (�����) ��� ���� ������ �������� ������ ���������. ����� ����
 * �� ��� ���� ���� ���� ����� ������, ���� ���� ���� ����� ����. ��� ��������
 * ����� ��� ���� ��� �� ���/��� ����� ��� ���� ������� ����� ��� �� ��� ��
 * ������� ������ ��� ������. �� ���� �� �� �� ���� ��� ������� ��� �����
 * acronym ���� true.
 * 
 * Java content class for QuantifierLexiconType complex type.
 * <p>
 * The following schema fragment specifies the expected content contained within
 * this java content object. (defined at
 * file:/home/syjacob/automaticCode/jwsdp-1.6/jaxb/lib/hebrew_MWlexicon.xsd line
 * 1738)
 * <p>
 * 
 * <pre>
 * &lt;complexType name="QuantifierLexiconType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;choice maxOccurs="unbounded" minOccurs="0">
 *           &lt;element name="add" type="{}QuantifierExceptionType" maxOccurs="unbounded" minOccurs="0"/>
 *           &lt;element name="replace" type="{}QuantifierExceptionType" maxOccurs="unbounded" minOccurs="0"/>
 *           &lt;element name="remove" type="{}QuantifierExceptionType" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;/choice>
 *       &lt;/sequence>
 *       &lt;attribute name="definiteness" type="{}DefinitnessType" default="prohibited" />
 *       &lt;attribute name="inflect" type="{http://www.w3.org/2001/XMLSchema}boolean" default="false" />
 *       &lt;attribute name="inflectionBase" type="{http://www.w3.org/2001/XMLSchema}token" default="" />
 *       &lt;attribute name="type" type="{}QuantifierTypeType" default="unspecified" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 */
public interface QuantifierLexiconType {

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
	 * Gets the value of the inflect property.
	 * 
	 */
	boolean isInflect();

	/**
	 * Sets the value of the inflect property.
	 * 
	 */
	void setInflect(boolean value);

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
	 * {@link lexicon.jaxb.QuantifierLexiconType.Remove}
	 * {@link lexicon.jaxb.QuantifierLexiconType.Replace}
	 * {@link lexicon.jaxb.QuantifierLexiconType.Add}
	 * 
	 */
	java.util.List getAddOrReplaceOrRemove();

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
	 * line 1758)
	 * <p>
	 * 
	 * <pre>
	 * &lt;element name="add" type="{}QuantifierExceptionType"/>
	 * </pre>
	 * 
	 */
	public interface Add extends javax.xml.bind.Element,
			lexicon.jaxb.QuantifierExceptionType {

	}

	/**
	 * Java content class for remove element declaration.
	 * <p>
	 * The following schema fragment specifies the expected content contained
	 * within this java content object. (defined at
	 * file:/home/syjacob/automaticCode/jwsdp-1.6/jaxb/lib/hebrew_MWlexicon.xsd
	 * line 1760)
	 * <p>
	 * 
	 * <pre>
	 * &lt;element name="remove" type="{}QuantifierExceptionType"/>
	 * </pre>
	 * 
	 */
	public interface Remove extends javax.xml.bind.Element,
			lexicon.jaxb.QuantifierExceptionType {

	}

	/**
	 * Java content class for replace element declaration.
	 * <p>
	 * The following schema fragment specifies the expected content contained
	 * within this java content object. (defined at
	 * file:/home/syjacob/automaticCode/jwsdp-1.6/jaxb/lib/hebrew_MWlexicon.xsd
	 * line 1759)
	 * <p>
	 * 
	 * <pre>
	 * &lt;element name="replace" type="{}QuantifierExceptionType"/>
	 * </pre>
	 * 
	 */
	public interface Replace extends javax.xml.bind.Element,
			lexicon.jaxb.QuantifierExceptionType {

	}

}
