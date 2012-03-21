package lexicon.tools;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import lexicon.exceptions.LexiconParserException;
import lexicon.exceptions.NullItemListException;
import lexicon.jaxb.ItemType;
import lexicon.jaxb.LexiconType;

/**
 * @author Danny Shacham
 */
public class LexiconReader {
	public static void main(String[] args) {
		try {
			new LexiconReader(
					"C:\\Documents and Settings\\daliabo\\Desktop\\lexicon.xml");
		} catch (final LexiconParserException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	private List itemTypeList;
	private final String xmlFile;
	private LexiconType collection;
	private HashMap<String, ItemType> lexicon;

	private static final String JAXB_PACKAGE = "lexicon.jaxb";

	public LexiconReader(String xmlFile) throws LexiconParserException {
		this.xmlFile = xmlFile;
		this.parse();
	}

	public ItemType getItem(String index) throws NullItemListException {
		return this.lexicon.get(index);
	}

	public List getItems() throws NullItemListException {
		if (this.itemTypeList == null)
			throw new NullItemListException("The ItemType list is NULL");
		return this.itemTypeList;
	}

	public LexiconType getLexiconType() {
		return this.collection;
	}

	private void insertToHash() {
		if (this.itemTypeList == null)
			return;
		for (int i = 0; i < this.itemTypeList.size(); i++) {
			final ItemType temp = (ItemType) this.itemTypeList.get(i);
			this.lexicon.put(temp.getId(), temp);
		}
	}

	public void parse() throws LexiconParserException {
		try {
			this.parse(new FileInputStream(this.xmlFile));
			this.lexicon = new HashMap<String, ItemType>();
			this.insertToHash();
		} catch (final FileNotFoundException fnfe) {
			fnfe.printStackTrace();
		}
	}

	public void parse(InputStream input) throws LexiconParserException {
		try {
			final JAXBContext jc = JAXBContext
					.newInstance(LexiconReader.JAXB_PACKAGE);
			final Unmarshaller unmarshaller = jc.createUnmarshaller();
			unmarshaller.setValidating(false);
			this.collection = (LexiconType) unmarshaller.unmarshal(input);
			this.itemTypeList = this.collection.getItem();
		} catch (final JAXBException jaxbe) {
			jaxbe.printStackTrace();
			throw new LexiconParserException(jaxbe.getMessage());
		}
	}
}
