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
import javax.xml.bind.Unmarshaller;

import mila.generated.ArticleType;
import mila.generated.Corpus;
import mila.lexicon.analyse.Constants;
import mila.tools.api.MilaException;

/**
 * This shows how to use JAXB to unmarshal an xml file Then display the
 * information from the content tree
 */

public class CorpusAnalysisReader {
	/** Types defined in the automatically generated code of jaxb */
	public final static JAXBContext jc = acquireJAXBContext();
	private List<ArticleType> articleTypeList;
	private String xmlFile;
	private Corpus collection;

	// -------------------------------------------------------------------------------------------------------------------------------
	public CorpusAnalysisReader() {
	}

	// -------------------------------------------------------------------------------------------------------------------------------
	public CorpusAnalysisReader(String xmlFile) {
		this.xmlFile = xmlFile;
		parse();
	}

	// -------------------------------------------------------------------------------------------------------------------------------
	public List<ArticleType> getArticle() {
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
	public static Corpus parse(InputStream input) {
		try {
			Unmarshaller unmarshaller = jc.createUnmarshaller();
			unmarshaller.setValidating(false);
			return (Corpus) unmarshaller.unmarshal(input);
		} catch (JAXBException e) {
			throw new MilaException(e);
		}
	}

	public void save(PrintWriter pw) {
		try {
			Marshaller marshaller = jc.createMarshaller();
			marshaller.marshal(collection, pw);

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public String save(String whereTo) {
		try {
			Marshaller marshaller = jc.createMarshaller();
			marshaller.marshal(collection, new FileOutputStream(whereTo));
			return whereTo;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "";
	}

	private static JAXBContext acquireJAXBContext(){
		try {
			return JAXBContext.newInstance(Constants.JAXB_PACKAGE);
		} catch (JAXBException e) {
			e.printStackTrace();
			throw new MilaException(e);
		}
	}
}
