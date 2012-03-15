//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v1.0.5-b16-fcs 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2008.12.01 at 02:23:12 PM IST 
//

package generated;

/**
 * ���� �� ���� ������ ���� ����. ����� ���� ���� ���� �����, ���� ���� ��� ���
 * ����� ������ ���/���/����.
 * 
 * Java content class for InterjectionLexiconType complex type.
 * <p>
 * The following schema fragment specifies the expected content contained within
 * this java content object. (defined at
 * file:/C:/Sun/jwsdp-1.6/jaxb/lib/hebrew_MWlexicon_12_11_2008.xsd line 1782)
 * <p>
 * 
 * <pre>
 * &lt;complexType name="InterjectionLexiconType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;choice maxOccurs="unbounded" minOccurs="0">
 *           &lt;element name="add" type="{}InterjectionExceptionType" maxOccurs="unbounded" minOccurs="0"/>
 *           &lt;element name="replace" type="{}InterjectionExceptionType" maxOccurs="unbounded" minOccurs="0"/>
 *           &lt;element name="remove" type="{}InterjectionExceptionType" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;/choice>
 *       &lt;/sequence>
 *       &lt;attribute name="inflect" type="{http://www.w3.org/2001/XMLSchema}boolean" default="false" />
 *       &lt;attribute name="inflectionBase" type="{http://www.w3.org/2001/XMLSchema}token" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 */
public interface InterjectionLexiconType {

	/**
	 * ���� �� ���� ������ ����� ������. ���� -- ���� ����, ����� ������ ����
	 * ������ ����. ������ ���� *��* ����� ����� ������ ������ ����� �����!
	 * 
	 * Java content class for add element declaration.
	 * <p>
	 * The following schema fragment specifies the expected content contained
	 * within this java content object. (defined at
	 * file:/C:/Sun/jwsdp-1.6/jaxb/lib/hebrew_MWlexicon_12_11_2008.xsd line
	 * 1791)
	 * <p>
	 * 
	 * <pre>
	 * &lt;element name="add" type="{}InterjectionExceptionType"/>
	 * </pre>
	 * 
	 */
	public interface Add extends javax.xml.bind.Element,
			generated.InterjectionExceptionType {

	}

	/**
	 * ���� �� ���� ����� ����� ������ ����� �������, ���� ���� ����� �����
	 * ����� ����� ��������, ��� ����� (���� ������ �� ������ ����, �� ��� �����
	 * ���). ������ ���� *��* ������ ����� ������ ������ ����� �����! �����
	 * ����, �� ������ ����� ��� �- add ��- replace ��� ������ �- remove, ���
	 * ���� �� ������ ���� ����� ������ ���� ����� ��������.
	 * 
	 * Java content class for remove element declaration.
	 * <p>
	 * The following schema fragment specifies the expected content contained
	 * within this java content object. (defined at
	 * file:/C:/Sun/jwsdp-1.6/jaxb/lib/hebrew_MWlexicon_12_11_2008.xsd line
	 * 1808)
	 * <p>
	 * 
	 * <pre>
	 * &lt;element name="remove" type="{}InterjectionExceptionType"/>
	 * </pre>
	 * 
	 */
	public interface Remove extends javax.xml.bind.Element,
			generated.InterjectionExceptionType {

	}

	/**
	 * ���� �� ���� ������ ����� ������ ������. ������ ���� *��* ����� �����
	 * ������ ������ ����� �����!
	 * 
	 * Java content class for replace element declaration.
	 * <p>
	 * The following schema fragment specifies the expected content contained
	 * within this java content object. (defined at
	 * file:/C:/Sun/jwsdp-1.6/jaxb/lib/hebrew_MWlexicon_12_11_2008.xsd line
	 * 1800)
	 * <p>
	 * 
	 * <pre>
	 * &lt;element name="replace" type="{}InterjectionExceptionType"/>
	 * </pre>
	 * 
	 */
	public interface Replace extends javax.xml.bind.Element,
			generated.InterjectionExceptionType {

	}

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
	 * {@link generated.InterjectionLexiconType.Add}
	 * {@link generated.InterjectionLexiconType.Remove}
	 * {@link generated.InterjectionLexiconType.Replace}
	 * 
	 */
	java.util.List getAddOrReplaceOrRemove();

	/**
	 * Gets the value of the inflectionBase property.
	 * 
	 * @return possible object is {@link java.lang.String}
	 */
	java.lang.String getInflectionBase();

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
	 * Sets the value of the inflectionBase property.
	 * 
	 * @param value
	 *            allowed object is {@link java.lang.String}
	 */
	void setInflectionBase(java.lang.String value);

}
