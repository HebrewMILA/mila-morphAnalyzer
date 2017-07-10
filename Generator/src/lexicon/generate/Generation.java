/**
 * This class is the main class of the generation program <br>
 * For running it follow the listed instructions: <br>
 * Create two properties file (one for the input database and the other to the
 * output database) and put them in the same directory of generator.jar <br>
 * See generator.properties and lexicon.properties as an example. <br>
 * run the program by : java -jar generator.jar options <br>
 * <p>
 * Each pos has it's own generation class <br>
 * All the pos generation classes are inherited from itemGen
 * 
 * @author daliabo
 * @version 1.0
 */
package lexicon.generate;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.List;
import lexicon.contents.EmptyContent;
import lexicon.contents.types.ItemType;

public class Generation {
	// --------------------------------------------------------------------------------------------------------------------------
	public static Object createObject(Constructor constructor, Object[] arguments)
			throws IllegalArgumentException, InstantiationException, IllegalAccessException, InvocationTargetException {
		Object object = null;
		object = constructor.newInstance(arguments);
		return object;
	}

	// --------------------------------------------------------------------------------------------------------------------------
	public void getItem(String sql) {
		System.out.println("Generation:getItem()");
		int i;
		try {
			EmptyContent eContent = new EmptyContent();
			List list = eContent.getContents(sql, "id");
			int listSize = list.size();
			for (i = 0; i < listSize; i++) {

				ItemType item = new ItemType();
				item.open(((Integer) list.get(i)).intValue());
				System.out
						.println(" ---lexicon Item count =" + i + " Item transliterated = " + item.getUndotted() + "----");
				String pos = item.getPos();

				// Using reflection for avoiding multy dispatch and for generic
				// considerations
				StringBuffer className = new StringBuffer().append("lexicon.generate.")
						.append(pos.substring(0, 1).toUpperCase()).append(pos.substring(1)).append("Gen");

				ItemGen itemGen;
				Class genClassDef;
				Class[] itemArgsClass = new Class[] { ItemType.class };
				Object[] itemArgs = new Object[] { item };
				Constructor itemArgsConstructor;

				String classNameStr = className.toString();
				// System.out.println("Generation:getItem() classNameStr="+
				// classNameStr);
				// if(classNameStr.startsWith("lexicon.generate.Multi"))
				// ;
				// else{
				genClassDef = Class.forName(classNameStr);
				itemArgsConstructor = genClassDef.getConstructor(itemArgsClass);
				itemGen = (ItemGen) createObject(itemArgsConstructor, itemArgs);
				itemGen.inflect();
				String alternateId = item.getAlternateId();
				if (!alternateId.equals("0")) {
					String oldId = item.getId();
					item.open(Integer.parseInt(alternateId));
					itemArgs = new Object[] { item };
					pos = item.getPos();
					className = new StringBuffer().append("lexicon.generate.").append(pos.substring(0, 1).toUpperCase())
							.append(pos.substring(1)).append("Gen");
					itemArgs = new Object[] { item };
					classNameStr = className.toString();
					genClassDef = Class.forName(classNameStr);
					itemArgsConstructor = genClassDef.getConstructor(itemArgsClass);
					itemGen = (ItemGen) createObject(itemArgsConstructor, itemArgs);
					itemGen.generateInflects();
					itemGen.switchBaseLexicalPointerAndInsert(oldId);
				}
				// }
			}
			System.out.println("finished generating");
			System.out.println(" ---Total lexicon items used for generating =" + i + "----");
		} catch (Exception e) {
			System.out.println("Exiting with Error: " + e.getMessage());
			e.printStackTrace();
			// System.exit(1);
		}
	}

	// --------------------------------------------------------------------------------------------------------------------------
	public String commandLineHandling(String[] args) {
		// System.out.println("(F) Generation:commandLineHandling()");
		String sql = "";
		String param = args[0];

		switch (args.length) {
		case 0:
			System.out.println("Generator: missing command line arguments");
			System.out.println("Running generator by pos: java -jar generator.jar -pos <pos>");
			System.out.println(
					"pos={adjective,adverb,conjunction,interjection,interrogative,noun,preposition,pronoun,properName,quantifier,verb,negation}");
			System.out.println("Running generator by lexicon id: java -id generator.jar -id <id>");
			System.out.println("Running generator for all lexicon items : java -jar generator.jar all");
			System.out
					.println("Running generator for a provided transliterated : java -jar -transliterated <transliterated>");
			// System.exit(1);
		case 1:
			if (param.equals("-help")) {
				System.out.println("Generator: help");
				System.out.println("Running generator by POS: java -jar generator.jar -pos <pos>");
				System.out.println(
						"pos={adjective,adverb,conjunction,interjection,interrogative,noun,preposition,pronoun,properName,quantifier,verb,negation}");
				System.out.println("Running generator by lexicon id: java -id generator.jar -id <id>");
				System.out.println("Running generator for all lexicon items : java -jar generator.jar -all");
				System.out.println(
						"Running generator for a provided transliterated : java -jar -transliterated <transliterated>");
				// System.exit(1);
			} else if (param.equals("-all"))
				sql = "SELECT id FROM item where deleted = 0";
			break;

		case 2:
			if (param.equals("-id"))
				sql = "SELECT id FROM item where id=" + args[1] + " and deleted = 0";
			else if (param.equals("-pos"))
				sql = "SELECT id FROM item where pos='" + args[1] + "' and deleted = 0";
			else if (param.equals("-transliterated"))
				sql = "SELECT id FROM item where transliterated='" + args[1] + "' and deleted = 0";
			else {
				System.out.println("Wrong/missing command line arguments, run java -jar generator.jar -help");
				// System.exit(1);
			}
			break;
		default:
			System.out.println("Wrong/missing command line arguments, run java -jar generator.jar -help");
			// System.exit(1);
			break;
		}
		return sql;
	}

	// --------------------------------------------------------------------------------------------------------------------------
	public void generate(String[] args) throws ClassNotFoundException, InvocationTargetException, IllegalAccessException,
			InstantiationException, NoSuchMethodException {
		String sql = commandLineHandling(args);
		if (sql.isEmpty()) {
			return;
		}
		getItem(sql);
	}

	// --------------------------------------------------------------------------------------------------------------------------
	public void testGenerate(String lexiconId) throws ClassNotFoundException, InvocationTargetException,
			IllegalAccessException, InstantiationException, NoSuchMethodException {
		String sql = "SELECT id FROM item where id=" + lexiconId + " and deleted = 0";
		getItem(sql);
	}

	// --------------------------------------------------------------------------------------------------------------------------
	public static void main(String[] args) {
		long startTime = System.currentTimeMillis();
		Generation gn = new Generation();
		try {
			gn.generate(args);
		} catch (ClassNotFoundException e) {
			System.out.println(e);
		} catch (InvocationTargetException e) {
			System.out.println(e);
		} catch (IllegalAccessException e) {
			System.out.println(e);
		} catch (InstantiationException e) {
			System.out.println(e);
		} catch (NoSuchMethodException e) {
			System.out.println(e);
		}
		long elapsedTime = System.currentTimeMillis() - startTime;
		System.out.println("Elapsed time = " + elapsedTime + " ms");
		System.exit(0);
	}
}