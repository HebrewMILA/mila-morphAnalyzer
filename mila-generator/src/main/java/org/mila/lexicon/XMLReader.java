package org.mila.lexicon;

import java.io.InputStream;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import org.mila.entities.lexicon.Item;
import org.mila.entities.lexicon.Lexicon;

public class XMLReader {
    private InputStream is;
    private List<Item> items;
    
    public XMLReader(InputStream is) throws JAXBException {
	this.is = is;
	this.parse();
    }
    
    public List<Item> getItems() {
	return this.items;
    }
    
    private void parse() throws JAXBException {
	final JAXBContext c = JAXHelper.getJAXContext();
	final Unmarshaller unmarsh = c.createUnmarshaller();
	final Lexicon lex = (Lexicon) unmarsh.unmarshal(this.is);
	this.items = lex.getItems();
    }
}
