//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v1.0.5-b16-fcs 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2008.12.01 at 02:23:12 PM IST 
//

package generated;

/**
 * ���� �� ���� ������ ������ ����� ���� ����� ������� ��� ���� ������, ��� ����
 * ����� ����������. ����, ���� �� ���� �� ������ ����� (���� �� ������ ���)
 * ����� �����.
 * 
 * Java content class for AdverbExceptionType complex type.
 * <p>
 * The following schema fragment specifies the expected content contained within
 * this java content object. (defined at
 * file:/C:/Sun/jwsdp-1.6/jaxb/lib/hebrew_MWlexicon_12_11_2008.xsd line 1322)
 * <p>
 * 
 * <pre>
 * &lt;complexType name="AdverbExceptionType">
 *   &lt;simpleContent>
 *     &lt;extension base="&lt;http://www.w3.org/2001/XMLSchema>token">
 *       &lt;attribute name="dotted" type="{http://www.w3.org/2001/XMLSchema}token" default="" />
 *       &lt;attribute name="possessiveSuffix" type="{}PGNType" default="unspecified" />
 *       &lt;attribute name="script" type="{}ScriptType" default="formal" />
 *       &lt;attribute name="transliterated" use="required" type="{http://www.w3.org/2001/XMLSchema}token" />
 *       &lt;attribute name="undotted" use="required" type="{http://www.w3.org/2001/XMLSchema}token" />
 *     &lt;/extension>
 *   &lt;/simpleContent>
 * &lt;/complexType>
 * </pre>
 * 
 */
public interface AdverbExceptionType {

	/**
	 * Gets the value of the dotted property.
	 * 
	 * @return possible object is {@link java.lang.String}
	 */
	java.lang.String getDotted();

	/**
	 * Gets the value of the possessiveSuffix property.
	 * 
	 * @return possible object is {@link java.lang.String}
	 */
	java.lang.String getPossessiveSuffix();

	/**
	 * Gets the value of the script property.
	 * 
	 * @return possible object is {@link java.lang.String}
	 */
	java.lang.String getScript();

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
	 * Gets the value of the value property.
	 * 
	 * @return possible object is {@link java.lang.String}
	 */
	java.lang.String getValue();

	/**
	 * Sets the value of the dotted property.
	 * 
	 * @param value
	 *            allowed object is {@link java.lang.String}
	 */
	void setDotted(java.lang.String value);

	/**
	 * Sets the value of the possessiveSuffix property.
	 * 
	 * @param value
	 *            allowed object is {@link java.lang.String}
	 */
	void setPossessiveSuffix(java.lang.String value);

	/**
	 * Sets the value of the script property.
	 * 
	 * @param value
	 *            allowed object is {@link java.lang.String}
	 */
	void setScript(java.lang.String value);

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
	 * Sets the value of the value property.
	 * 
	 * @param value
	 *            allowed object is {@link java.lang.String}
	 */
	void setValue(java.lang.String value);

}
