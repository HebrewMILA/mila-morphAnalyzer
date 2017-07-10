/*
 * Created on 19/09/2005
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
public class ConjunctionGen extends ItemGen {

	public ConjunctionGen(ItemType item) {
		super(item);
	}

	private void analyse() {
		analyseItem();
		type = item.getConjunction().getType();
		suffixFunction = "unspecified";
	}

	@Override
	public void generateInflects() throws Exception {
		analyse();
		inflectedItem = transliterated;
		surface = undot;
		populateDatabase();

	}

}
