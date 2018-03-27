/*
 * Created on 14/11/2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package lexicon.generate;

import lexicon.contents.types.ItemType;

/**
 * @author daliabo
 *
 *         TODO To change the template for this generated type comment go to
 *         Window - Preferences - Java - Code Style - Code Templates
 */
public class TitleGen extends ItemGen {
	boolean definiteness;

	public TitleGen(ItemType item) {
		super(item);

	}

	private void analyse() {
		analyseItem();
		gender = item.getTitle().getGender();
		number = item.getTitle().getNumber();
		definiteness = item.getTitle().isDefiniteness();
		System.out.println(definiteness);
		if (definiteness)
			definitnessVal = "tt";
		else
			definitnessVal = "tf";
		inflectedItem = transliterated;
		surface = undot;
	}

	public void generateInflects() throws Exception {
		analyse();
		populateDatabase();
	}

}
