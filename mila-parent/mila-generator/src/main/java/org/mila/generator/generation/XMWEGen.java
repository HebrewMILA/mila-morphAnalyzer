package org.mila.generator.generation;

import java.util.List;

import javax.persistence.EntityManager;

import org.mila.entities.inflections.Inflection;
import org.mila.entities.lexicon.Item;
import org.mila.entities.lexicon.XMWELexicon;

public class XMWEGen extends ItemGen {

	private XMWELexicon xmwe;

	public XMWEGen(Item item, EntityManager lexicon, EntityManager generator,
			EntityManager inflections) {
		super(item, lexicon, generator, inflections);
		this.xmwe = (XMWELexicon) item.getSubitem();
	}

	@Override
	public List<Inflection> inflect() {
		return null;
	}

}
