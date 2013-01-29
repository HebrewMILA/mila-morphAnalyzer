/*
 * Created on 19/09/2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package org.mila.generator.generation;

import java.util.List;

import javax.persistence.EntityManager;

import org.mila.entities.corpus.SuffixFunctionType;
import org.mila.entities.inflections.PersistableInflection;
import org.mila.entities.lexicon.ConjunctionLexicon;
import org.mila.entities.lexicon.Item;

/**
 * @author daliabo
 * 
 *         TODO To change the template for this generated type comment go to
 *         Window - Preferences - Java - Code Style - Code Templates
 */
public class ConjunctionGen extends ItemGen {
	private ConjunctionLexicon conjuction;

	public ConjunctionGen(Item item, EntityManager lexicon,
			EntityManager generator, EntityManager inflections) {
		super(item, lexicon, generator, inflections);
		this.conjuction = (ConjunctionLexicon) item.getSubitem();
	}

	private void analyse() {
		analyseItem();
		/* XXX: I don't like the toString */
		type = this.conjuction.getType().toString();
		suffixFunction = SuffixFunctionType.UNSPECIFIED;
	}

	public List<PersistableInflection> inflect() {
		analyse();
		inflectedItem = transliterated;
		surface = undot;
		populateDatabase();
		return this.getGeneratedInflections();
	}

}
