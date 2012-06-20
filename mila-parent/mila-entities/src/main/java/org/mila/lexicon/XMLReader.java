package org.mila.lexicon;

import java.io.InputStream;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;

import org.mila.entities.lexicon.Item;
import org.mila.entities.lexicon.Lexicon;
import org.mila.entities.lexicon.Metadata;
import org.xml.sax.SAXException;

public class XMLReader {
    private InputStream is;
    private List<Item> items;
    private Metadata metadata;

    public Metadata getMetadata() {
	return metadata;
    }

    public XMLReader(InputStream is) throws JAXBException, SAXException {
	this.is = is;
	this.parse();
    }

    public List<Item> getItems() {
	return this.items;
    }

    private void parse() throws JAXBException, SAXException {
	final JAXBContext c = JAXHelper.getJAXContext();
	final Unmarshaller unmarsh = c.createUnmarshaller();
	SchemaFactory sf = SchemaFactory
		.newInstance(javax.xml.XMLConstants.W3C_XML_SCHEMA_NS_URI);
	Schema s = sf.newSchema(new StreamSource(this.getClass().getClassLoader().getResourceAsStream("hebrew_MWlexicon.xsd")));
	unmarsh.setSchema(s);
	@SuppressWarnings("unchecked")
	/* I'm pretty sure it's OK... */
	final Lexicon lex = ((JAXBElement<Lexicon>) unmarsh.unmarshal(this.is))
		.getValue();
	this.metadata = lex.getMetadata();
	this.items = lex.getItem();
    }
}
