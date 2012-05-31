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
package org.mila.generator;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.List;

import org.mila.generator.generation.ItemGen;

import lexicon.contents.EmptyContent;
import lexicon.contents.types.ItemType;

public class Generation {
    public void getItem(String sql) {
	System.out.println("Generation:getItem()");
	int i;
	try {
	    EmptyContent eContent = new EmptyContent();
	    List list = eContent.getContents(sql, "id");
	    int listSize = list.size();
	    for (i = 0; i < listSize; i++) {
		// System.out.println(" ---lexicon Item count =" + i + "----");

		ItemType item = new ItemType();
		item.open(((Integer) list.get(i)).intValue());

		String pos = item.getPos();

		// Using reflection for avoiding multy dispatch and for generic
		// considerations
		StringBuffer className = new StringBuffer()
			.append("org.mila.generator.generation.")
			.append(pos.substring(0, 1).toUpperCase())
			.append(pos.substring(1)).append("Gen");

		ItemGen itemGen;
		Class genClassDef;
		Class[] itemArgsClass = new Class[] { ItemType.class };
		Object[] itemArgs = new Object[] { item };
		Constructor itemArgsConstructor;

		String classNameStr = className.toString();
		// System.out.println("Generation:getItem() classNameStr="+
		// classNameStr);
		// if(classNameStr.startsWith("org.mila.generator.generation.Multi"))
		// ;
		// else{
		genClassDef = Class.forName(classNameStr);
		itemArgsConstructor = genClassDef.getConstructor(itemArgsClass);
		itemGen = (ItemGen) createObject(itemArgsConstructor, itemArgs);
		itemGen.inflect();
		// }
	    }
	    System.out.println("finished generating");
	    System.out.println(" ---Total lexicon items used for generating ="
		    + i + "----");
	} catch (Exception e) {
	    System.out.println("Exiting with Error: " + e.getMessage());
	    e.printStackTrace();
	    // System.exit(1);
	}
    }

 
}