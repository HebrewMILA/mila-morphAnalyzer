package org.mila.lexicon;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;

import org.mila.entities.lexicon.ObjectFactory;

public class JAXHelper {
    public static JAXBContext getJAXContext() throws JAXBException {
	return JAXBContext.newInstance(ObjectFactory.class.getPackage().getName());
    }
}
