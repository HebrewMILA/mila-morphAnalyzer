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
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class ImpersonalGen extends ItemGen {
	
	public ImpersonalGen(ItemType item) {
		super(item);
		
	}


	private void analyse() {
		analyseItem();
	}

	public void generateInflects() throws Exception {
		analyse();
		inflectedItem = transliterated;
		surface = undot;
		populateDatabase();
	}


}
