//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v1.0.5-b16-fcs 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2009.06.14 at 03:41:34 PM IDT 
//

package generated.impl.runtime;

/**
 * Generated classes have to implement this interface for it to be
 * unmarshallable.
 * 
 * @author Kohsuke KAWAGUCHI
 */
public interface UnmarshallableObject {
	/**
	 * Creates an unmarshaller that will unmarshall this object.
	 */
	UnmarshallingEventHandler createUnmarshaller(UnmarshallingContext context);
}
