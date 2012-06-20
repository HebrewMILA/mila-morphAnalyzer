package org.mila;

import javax.xml.bind.JAXBException;

import junit.framework.Assert;

import org.junit.Test;
import org.mila.lexicon.XMLReader;
import org.xml.sax.SAXException;

public class XMLImportTest {
    final String XMLFilename = "lexicon_20120515_fixed.xml"; 

    @Test
    public void test() throws SAXException {
	XMLReader rdr = null;
	try {
	    rdr = new XMLReader(this.getClass().getClassLoader().getResourceAsStream(XMLFilename));
	} catch (JAXBException e) {
	    // TODO Auto-generated catch block
	    throw new RuntimeException(e);
	}
	Assert.assertNotNull(rdr.getItems());
	
    }

}
