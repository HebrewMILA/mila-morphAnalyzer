/*
 * Created on 14/11/2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package org.mila.generator.generation;

import java.util.Collections;
import java.util.List;

import javax.persistence.EntityManager;

import org.mila.entities.inflections.Inflection;
import org.mila.entities.lexicon.Item;


/**
 * @author daliabo
 * 
 * TODO To change the template for this generated type comment go to Window -
 * Preferences - Java - Code Style - Code Templates
 */
public class MultiWordNounGen extends ItemGen {

	public MultiWordNounGen(Item item, EntityManager lexicon,
			EntityManager generator, EntityManager inflections) {
		super(item, lexicon, generator, inflections);
	}

	@Override
	public List<Inflection> inflect() {
		// TODO: Port this from the original.
		return Collections.emptyList();		
	}

}
