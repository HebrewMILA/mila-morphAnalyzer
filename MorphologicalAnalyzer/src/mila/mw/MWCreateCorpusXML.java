/*
 * Created on 08/01/2006
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package mila.mw;

import java.net.InetAddress;
import java.util.Calendar;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;

import mila.corpus.CreateCorpusXML;
import mila.generated.BaseType;
import mila.generated.MWEType;
import mila.generated.ObjectFactory;

/**
 * @author daliabo
 * 
 *         TODO To change the template for this generated type comment go to
 *         Window - Preferences - Java - Code Style - Code Templates
 */
public class MWCreateCorpusXML extends CreateCorpusXML {
	public MWCreateCorpusXML() {
	}

	public MWCreateCorpusXML(String outputFile) {
		this.outputFile = outputFile;
	}

	public void createMWEAnalysis(String description,
			String transliteratedLexiocnItem, String hebWord,
			String lexiconPointer, String pos, String mweid, String type,
			boolean prefix, String definiteness) {
		analysisCounter++;
		try {
			analysis = objFactory.createAnalysisType();
		} catch (JAXBException e) {
			System.out
					.println("CreateCorpusXML:createImpersonalAnalysis Exception while creating analysis for hebWord="
							+ hebWord);
			e.printStackTrace();
		}
		analysis.setId(String.valueOf(analysisCounter));
		BaseType base = null;
		try {
			base = objFactory.createBaseType();
		} catch (JAXBException e1) {
			System.out
					.println("CreateCorpusXML:createImpersonalAnalysis Exception while creating base for hebWord="
							+ hebWord);
			e1.printStackTrace();
		}
		if (!description.equals(""))
			setPrefix(description);

		setBase(base, transliteratedLexiocnItem, hebWord, "", hebWord);
		MWEType mwe1 = null;
		try {
			mwe1 = objFactory.createMWEType();
		} catch (JAXBException e2) {
			System.out
					.println("CreateCorpusXML:createImpersonalAnalysis Exception while creating ImpersonalType for hebWord="
							+ hebWord);
			e2.printStackTrace();
		}
		mwe1.setConsecutive("true");
		if (mweid.length() == 1 && mweid.charAt(0) == '1')
			mwe1.setPos(pos);
		mwe1.setId(mweid);
		if (!definiteness.equals("unspecified"))
			mwe1.setDefiniteness(definiteness);
		mwe1.setMultiWordPrefixExist(prefix);
		if (pos.equalsIgnoreCase("propername") && type.charAt(0) != 'u')
			mwe1.setType(type);
		base.setMWE(mwe1);
		analysis.setBase(base);
		token.getAnalysis().add(analysis);
	}

	@Override
	public void createXMLDoc() {
		// System.out.println("(F) createXMLdOC() ");
		try {
			jc = JAXBContext.newInstance("mila.generated");
		} catch (JAXBException e) {
			System.out
					.println("CreateCorpusXML:createXMLdOC Exception while creating JAXBContext");
			e.printStackTrace();
		}
		objFactory = new ObjectFactory();
		try {
			corpus = objFactory.createCorpus();
		} catch (JAXBException e1) {
			System.out
					.println("CreateCorpusXML:createXMLdOC Exception while creating corpus");
			e1.printStackTrace();
		}
		String hostname = "unknown.unknown";
		try {
			hostname = InetAddress.getLocalHost().getHostName();
		} catch (Throwable t) {

		}
		corpus.setName("Analysis Results (" + hostname + ") @ "
				+ Calendar.getInstance().getTime());
		corpus.setMaintainer("Matan Peled");
		corpus.setEmail("mila@cs.technion.ac.il");

		corpus.setComment("versions info: lexicon: 13/03/2013;  morphologicalAnalyzer: 1.8 (13/03/2013); "
				+ "corpus schema 16/06/2009; lexicon schema 16/06/2009");
		try {
			validator = jc.createValidator();
		} catch (JAXBException e2) {
			System.out
					.println("CreateCorpusXML:createXMLdOC Exception while creating Validator");
			e2.printStackTrace();
		}
	}

	protected void setBase(BaseType base, String transliteratedLexiocnItem,
			String lexiconItem, String lexiconPointer, String dottedLexiconItem) {
		base.setTransliteratedLexiconItem(transliteratedLexiocnItem);
		base.setLexiconItem(lexiconItem);
		if (lexiconPointer.length() > 0)
			base.setLexiconPointer(lexiconPointer);
		base.setDottedLexiconItem(null);
	}

}
