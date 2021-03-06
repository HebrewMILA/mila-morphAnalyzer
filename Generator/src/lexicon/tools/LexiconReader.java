package lexicon.tools;

import lexicon.jaxb.ItemType;
import lexicon.jaxb.LexiconType;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.List;
import java.util.HashMap;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import lexicon.exceptions.LexiconParserException;
import lexicon.exceptions.NullItemListException;

/**
 * @author Danny Shacham
 */
public class LexiconReader {
	private List itemTypeList;
	private String xmlFile;
	private LexiconType collection;
	private HashMap lexicon;
	private static final String JAXB_PACKAGE = "lexicon.jaxb";

	public LexiconReader(String xmlFile) throws LexiconParserException {
		this.xmlFile = xmlFile;
		parse();
	}

	public void parse(InputStream input) throws LexiconParserException {
		try {
			JAXBContext jc = JAXBContext.newInstance(JAXB_PACKAGE);
			Unmarshaller unmarshaller = jc.createUnmarshaller();
			unmarshaller.setValidating(false);
			collection = (LexiconType) unmarshaller.unmarshal(input);
			itemTypeList = collection.getItem();
		} catch (JAXBException jaxbe) {
			jaxbe.printStackTrace();
			throw new LexiconParserException(jaxbe.getMessage());
		}
	}

	public void parse() throws LexiconParserException {
		try {
			parse(new FileInputStream(xmlFile));
			lexicon = new HashMap();
			insertToHash();
		} catch (FileNotFoundException fnfe) {
			fnfe.printStackTrace();
		}
	}

	private void insertToHash() {
		if (itemTypeList == null) {
			return;
		}
		for (int i = 0; i < itemTypeList.size(); i++) {
			ItemType temp = (ItemType) itemTypeList.get(i);
			lexicon.put(temp.getId(), temp);
		}
	}

	public List getItems() throws NullItemListException {
		if (itemTypeList == null) {
			throw new NullItemListException("The ItemType list is NULL");
		}
		return itemTypeList;
	}

	public ItemType getItem(String index) throws NullItemListException {
		return (ItemType) lexicon.get(index);
	}

	public LexiconType getLexiconType() {
		return collection;
	}

	public static void main(String[] args) {
	}
}
