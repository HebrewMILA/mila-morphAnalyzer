/*
 * Created on 28/01/2007
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package lexicon.tools;

/**
 * @author daliabo
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */


import java.io.FileOutputStream;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;

import java.util.Date;
import java.util.List;
import java.util.ArrayList;

import lexicon.contents.Connected;
import lexicon.contents.EmptyContent;
import lexicon.contents.types.*;
import lexicon.jaxb.impl.LexiconImpl;
import lexicon.jaxb.impl.LexiconTypeImpl;
import javax.xml.bind.Validator;
/**
 * @author Danny Shacham
 */
public class CreateXML {
	
	private static final String JAXB_PACKAGE = "lexicon.jaxb";
	
	public static void main(String[] args) {
		if (args.length != 1) {
			System.out.println("This Class should get one argument [xml_file_name]");
			System.exit(1);
		}
		createXML(args[0]);
	}
	public static String createXML(String fileName) {
		try {
			Date starting = new Date();
			String sql = "SELECT id FROM item where deleted=0";
			EmptyContent content = new EmptyContent();
			List list = content.getContents(sql, "id");
			ArrayList items = new ArrayList();
			System.out.println("Starting to create the XML file ("+fileName+")");
		//	for (int i=0; i< list.size(); i++) {
				for (int i=0; i< 100; i++) {
				//if ((i)%2000 == 0 && i!=0) {
				//	System.out.print(i+",");
				//}
				//if ((i+1)%10000 == 0 && i!=0) {
				//	System.out.println();
				//}
				ItemType item = new ItemType();
				item.open(((Integer)list.get(i)).intValue());
				JAXBContext jc = null;
				
				items.add(item.getImpl());
				
			}
			LexiconTypeImpl lex = new LexiconTypeImpl();
			MetadataType metadata = new MetadataType();
			metadata.open(1);
			lex.setMetadata(metadata.getImpl());
			lex.getItem().addAll(items);
			LexiconImpl lexImpl = new LexiconImpl();
			lexImpl.setMetadata(metadata.getImpl());
			lexImpl.getItem().addAll(items);
			JAXBContext jc = JAXBContext.newInstance(JAXB_PACKAGE);
			Marshaller marshaller = jc.createMarshaller();
			marshaller.marshal(lexImpl, new FileOutputStream(fileName));
			Validator validator = jc.createValidator();
			if (!validator.validate(lexImpl)) {
				System.err.println("Corpus Not valid !!!");
			}
			Date ending = new Date();
			long time = (ending.getTime() - starting.getTime())/1000/60;
			System.out.println("It took "+time+" minutes");
		} catch (Exception e) {
			e.printStackTrace();
		} 
		finally {
			Connected.releasePool(); 
		}
		return "File: "+fileName+" was created";
	}


	
}
