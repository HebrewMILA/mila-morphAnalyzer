//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v1.0.5-b16-fcs 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2009.06.14 at 03:41:34 PM IDT 
//

package generated;

/**
 * ���� ����� ����� ��� ������� ������ �������� ������ �����. ����, ����� �����
 * ������.
 * 
 * Java content class for TransliterationType complex type.
 * <p>
 * The following schema fragment specifies the expected content contained within
 * this java content object. (defined at
 * file:/C:/Sun/jwsdp-1.6/jaxb/lib/hebrew_MWcorpus.xsd line 88)
 * <p>
 * 
 * <pre>
 * &lt;complexType name="TransliterationType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="string" maxOccurs="unbounded">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;attribute name="hebrew" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
 *                 &lt;attribute name="latin" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *       &lt;/sequence>
 *       &lt;attribute name="from" use="required" type="{http://www.w3.org/2001/XMLSchema}language" />
 *       &lt;attribute name="to" use="required" type="{http://www.w3.org/2001/XMLSchema}language" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 */
public interface TransliterationType {

	/**
	 * Java content class for anonymous complex type.
	 * <p>
	 * The following schema fragment specifies the expected content contained
	 * within this java content object. (defined at
	 * file:/C:/Sun/jwsdp-1.6/jaxb/lib/hebrew_MWcorpus.xsd line 96)
	 * <p>
	 * 
	 * <pre>
	 * &lt;complexType>
	 *   &lt;complexContent>
	 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
	 *       &lt;attribute name="hebrew" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
	 *       &lt;attribute name="latin" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
	 *     &lt;/restriction>
	 *   &lt;/complexContent>
	 * &lt;/complexType>
	 * </pre>
	 * 
	 */
	public interface StringType {

		/**
		 * Gets the value of the hebrew property.
		 * 
		 * @return possible object is {@link java.lang.String}
		 */
		java.lang.String getHebrew();

		/**
		 * Gets the value of the latin property.
		 * 
		 * @return possible object is {@link java.lang.String}
		 */
		java.lang.String getLatin();

		/**
		 * Sets the value of the hebrew property.
		 * 
		 * @param value
		 *            allowed object is {@link java.lang.String}
		 */
		void setHebrew(java.lang.String value);

		/**
		 * Sets the value of the latin property.
		 * 
		 * @param value
		 *            allowed object is {@link java.lang.String}
		 */
		void setLatin(java.lang.String value);

	}

	/**
	 * Gets the value of the from property.
	 * 
	 * @return possible object is {@link java.lang.String}
	 */
	java.lang.String getFrom();

	/**
	 * Gets the value of the String property.
	 * 
	 * <p>
	 * This accessor method returns a reference to the live list, not a
	 * snapshot. Therefore any modification you make to the returned list will
	 * be present inside the JAXB object. This is why there is not a
	 * <CODE>set</CODE> method for the String property.
	 * 
	 * <p>
	 * For example, to add a new item, do as follows:
	 * 
	 * <pre>
	 * getString().add(newItem);
	 * </pre>
	 * 
	 * 
	 * <p>
	 * Objects of the following type(s) are allowed in the list
	 * {@link generated.TransliterationType.StringType}
	 * 
	 */
	java.util.List getString();

	/**
	 * Gets the value of the to property.
	 * 
	 * @return possible object is {@link java.lang.String}
	 */
	java.lang.String getTo();

	/**
	 * Sets the value of the from property.
	 * 
	 * @param value
	 *            allowed object is {@link java.lang.String}
	 */
	void setFrom(java.lang.String value);

	/**
	 * Sets the value of the to property.
	 * 
	 * @param value
	 *            allowed object is {@link java.lang.String}
	 */
	void setTo(java.lang.String value);

}
