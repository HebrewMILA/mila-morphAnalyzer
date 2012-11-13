/*
 * Created on 14/11/2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package org.mila.generator.generation;

import java.util.List;

import javax.persistence.EntityManager;

import org.mila.entities.corpus.DefinitenessType;
import org.mila.entities.inflections.Inflection;
import org.mila.entities.lexicon.Item;

public class NegationGen extends ItemGen {

	public NegationGen(Item item, EntityManager lexicon,
			EntityManager generator, EntityManager inflections) {
		super(item, lexicon, generator, inflections);
	}

	private void analyse() {
		analyseItem();
	}

	public List<Inflection> inflect() {
		analyse();
		definitnessVal = DefinitenessType.FALSE;
		inflectedItem = transliterated;
		surface = undot;
		populateDatabase();
		definitnessVal = DefinitenessType.TRUE;
		addH();
		return this.getGeneratedInflections();
	}

}
