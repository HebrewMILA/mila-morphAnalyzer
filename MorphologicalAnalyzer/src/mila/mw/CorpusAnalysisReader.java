package mila.mw;


import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.UnmarshalException;
import javax.xml.bind.Unmarshaller;

import mila.generated.Corpus;

/**
 * This shows how to use JAXB to unmarshal an xml file Then display the
 * information from the content tree
 */

public class CorpusAnalysisReader {
	private List articleTypeList;
	private String xmlFile;
	private Corpus collection;
	private static final String JAXB_PACKAGE = "generated";
	JAXBContext jc;

	// -------------------------------------------------------------------------------------------------------------------------------
	public CorpusAnalysisReader() {
	}

	// -------------------------------------------------------------------------------------------------------------------------------
	public CorpusAnalysisReader(String xmlFile) {
		this.xmlFile = xmlFile;
		parse();
	}

	// -------------------------------------------------------------------------------------------------------------------------------
	public List getArticle() {
		if (articleTypeList == null) {
			System.out.println("The articleType list is NULL :(");
			// System.exit(-1); // UPDATE 21.11.10 (yossi)
		}
		return articleTypeList;
	}

	// -------------------------------------------------------------------------------------------------------------------------------
	public void parse() {
		try {
			parse(new FileInputStream(xmlFile));
		} catch (FileNotFoundException fnfe) {
		}
	}

	// -------------------------------------------------------------------------------------------------------------------------------
	public void parse(InputStream input) {
		try {
			jc = JAXBContext.newInstance(JAXB_PACKAGE);
			Unmarshaller unmarshaller = jc.createUnmarshaller();
			unmarshaller.setValidating(false);
			collection = (Corpus) unmarshaller.unmarshal(input);
			articleTypeList = collection.getArticle();
		} catch (UnmarshalException e) {
			e.printStackTrace();
		} catch (JAXBException e) {
			e.printStackTrace();
		}
	}

	public void save(PrintWriter pw) {
		try {
			JAXBContext jc = JAXBContext.newInstance(JAXB_PACKAGE);
			Marshaller marshaller = jc.createMarshaller();
			marshaller.marshal(collection, pw);

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public String save(String whereTo) {
		try {
			JAXBContext jc = JAXBContext.newInstance(JAXB_PACKAGE);
			Marshaller marshaller = jc.createMarshaller();
			marshaller.marshal(collection, new FileOutputStream(whereTo));
			return whereTo;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "";
	}
}
