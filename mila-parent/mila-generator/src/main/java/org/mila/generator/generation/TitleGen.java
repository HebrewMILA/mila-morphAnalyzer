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
import org.mila.entities.corpus.GenderType;
import org.mila.entities.corpus.NumberType;
import org.mila.entities.inflections.PersistableInflection;
import org.mila.entities.lexicon.Item;
import org.mila.entities.lexicon.TitleLexicon;


public class TitleGen extends ItemGen {
	private TitleLexicon title;

	public TitleGen(Item item, EntityManager lexicon, EntityManager generator,
			EntityManager inflections) {
		super(item, lexicon, generator, inflections);
		title = (TitleLexicon) item.getSubitem();
	}

	boolean isDefinite;

	private void analyse() {
		analyseItem();
		gender = GenderType.fromValue(title.getGender().value());
		number = NumberType.fromValue(title.getNumber().value());
		isDefinite = title.isDefiniteness();
		if (isDefinite)
			definitnessVal = DefinitenessType.TRUE;
		else
			definitnessVal = DefinitenessType.FALSE;
		inflectedItem = transliterated;
		surface = undot;
	}

	public List<PersistableInflection> inflect() {
		analyse();
		populateDatabase();
		return this.getGeneratedInflections();
	}

}
