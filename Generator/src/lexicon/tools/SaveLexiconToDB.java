package lexicon.tools;

/**
 * @author Danny Shacham
 */

import lexicon.tools.LexiconReader;
import lexicon.jaxb.LexiconType;
import lexicon.contents.types.*;

public class SaveLexiconToDB {

	public static void main(String[] args) {
		/*
		 * 
		 * try { System.out.println(URLEncoder.encode("�?י�? הגדרה", "utf-8")); } catch
		 * (Exception e) {}
		 * 
		 * try { String sql = "SELECT id FROM item"; Content content = new Content();
		 * List list = content.getContents(sql, "id"); ArrayList items = new
		 * ArrayList(); for (int i=0; i< list.size(); i++) { if ((i)%500 == 0 && i!=0) {
		 * System.out.print(i+","); } if ((i+1)%5000 == 0 && i!=0) {
		 * System.out.println(); } ItemType item = new ItemType();
		 * item.open(((Integer)list.get(i)).intValue()); items.add(item.getImpl()); }
		 * LexiconTypeImpl lex = new LexiconTypeImpl(); MetadataType metadata = new
		 * MetadataType(); metadata.open(1); lex.setMetadata(metadata.getImpl());
		 * lex.getItem().addAll(items); LexiconImpl lexImpl = new LexiconImpl();
		 * lexImpl.setMetadata(metadata.getImpl()); lexImpl.getItem().addAll(items);
		 * JAXBContext jc = JAXBContext.newInstance(JAXB_PACKAGE); Marshaller marshaller
		 * = jc.createMarshaller(); marshaller.marshal(lexImpl, new
		 * FileOutputStream("c:\\hebrew_lexicon_from_db.xml"));
		 * System.out.println("finished"); } catch (Exception e) { e.printStackTrace();
		 * }
		 */
		try {
			LexiconReader lexicon = new LexiconReader("c:\\lexicon\\hebrew_lexicon.xml");
			LexiconType lexType = lexicon.getLexiconType();
			System.out.println("Saving the items (" + lexType.getItem().size() + ")");
			for (int i = 0; i < lexType.getItem().size(); i++) {
				if ((i) % 500 == 0 && i != 0) {
					System.out.print(i + ",");
				}
				if ((i + 1) % 5000 == 0 && i != 0) {
					System.out.println();
				}
				ItemType item = new ItemType((lexicon.jaxb.ItemType) lexType.getItem().get(i));
				item.add();
				// item.update();
			}
			System.out.println();
			System.out.println("Saving the metadata");
			MetadataType metadata = new MetadataType(lexType.getMetadata());
			metadata.add();
			System.out.println("finished");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
