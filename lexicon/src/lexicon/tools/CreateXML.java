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
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Validator;

import lexicon.contents.Connected;
import lexicon.contents.EmptyContent;
import lexicon.contents.types.ItemType;
import lexicon.contents.types.MetadataType;
import lexicon.jaxb.impl.ItemTypeImpl;
import lexicon.jaxb.impl.LexiconImpl;
import lexicon.jaxb.impl.LexiconTypeImpl;

/**
 * @author Danny Shacham
 */
public class CreateXML {

	private static final String JAXB_PACKAGE = "lexicon.jaxb";

	public static String createXML(String fileName) {
		try {
			final Date starting = new Date();
			final String sql = "SELECT id FROM item where deleted=0";
			final EmptyContent content = new EmptyContent();
			final List<Integer> list = content.getContents(sql, "id");
			final ArrayList<ItemTypeImpl> items = new ArrayList<ItemTypeImpl>();
			System.out.println("Starting to create the XML file (" + fileName
					+ ")");
			// for (int i=0; i< list.size(); i++) {
			for (int i = 0; i < 100; i++) {
				// if ((i)%2000 == 0 && i!=0) {
				// System.out.print(i+",");
				// }
				// if ((i+1)%10000 == 0 && i!=0) {
				// System.out.println();
				// }
				final ItemType item = new ItemType();
				item.open(list.get(i).intValue());
				items.add(item.getImpl());

			}
			final LexiconTypeImpl lex = new LexiconTypeImpl();
			final MetadataType metadata = new MetadataType();
			metadata.open(1);
			lex.setMetadata(metadata.getImpl());
			lex.getItem().addAll(items);
			final LexiconImpl lexImpl = new LexiconImpl();
			lexImpl.setMetadata(metadata.getImpl());
			lexImpl.getItem().addAll(items);
			final JAXBContext jc = JAXBContext
					.newInstance(CreateXML.JAXB_PACKAGE);
			final Marshaller marshaller = jc.createMarshaller();
			marshaller.marshal(lexImpl, new FileOutputStream(fileName));
			final Validator validator = jc.createValidator();
			if (!validator.validate(lexImpl)) {
				System.err.println("Corpus Not valid !!!");
			}
			final Date ending = new Date();
			final long time = (ending.getTime() - starting.getTime()) / 1000 / 60;
			System.out.println("It took " + time + " minutes");
		} catch (final Exception e) {
			e.printStackTrace();
		} finally {
			Connected.releasePool();
		}
		return "File: " + fileName + " was created";
	}

	public static void main(String[] args) {
		if (args.length != 1) {
			System.out
					.println("This Class should get one argument [xml_file_name]");
			System.exit(1);
		}
		CreateXML.createXML(args[0]);
	}

}
