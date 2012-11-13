package org.mila.generator.generation;

import java.util.Collections;
import java.util.List;

import javax.persistence.EntityManager;

import org.mila.entities.inflections.Inflection;
import org.mila.entities.lexicon.Item;

public class MultiWordNounAdjectiveGen extends ItemGen 
{

	public MultiWordNounAdjectiveGen(Item item, EntityManager lexicon,
			EntityManager generator, EntityManager inflections) {
		super(item, lexicon, generator, inflections);
	}

	@Override
	public List<Inflection> inflect() {
		// TODO: Port this from the original.
		return Collections.emptyList();
	}	

}
