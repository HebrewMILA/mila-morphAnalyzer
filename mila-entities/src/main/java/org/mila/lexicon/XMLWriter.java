package org.mila.lexicon;

import java.io.OutputStream;
import java.util.List;

import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

import org.mila.entities.lexicon.Item;
import org.mila.entities.lexicon.Lexicon;
import org.mila.entities.lexicon.Metadata;
import org.mila.entities.lexicon.ObjectFactory;

public class XMLWriter {

    private Metadata md;

    public XMLWriter(Metadata md) {
	this.md = md;
    }

    public void setMd(Metadata md) {
        this.md = md;
    }

    public void write(OutputStream os, List<Item> items) throws JAXBException {
	final ObjectFactory of = new ObjectFactory();
	Lexicon lex = of.createLexicon();
	lex.setMetadata(md);
	lex.setItem(items);
	Marshaller marsh = JAXHelper.getJAXContext().createMarshaller();
	marsh.marshal(of.createLexicon(lex), os);
    }

}
