package org.mila.generator.generation;

import static ch.lambdaj.Lambda.filter;

import java.util.Collections;
import java.util.List;

import javax.persistence.EntityManager;

import org.hamcrest.Matchers;
import org.mila.entities.corpus.DefinitenessType;
import org.mila.entities.corpus.RegisterType;
import org.mila.entities.corpus.SpellingType;
import org.mila.entities.inflections.Inflection;
import org.mila.entities.lexicon.AcronymDefiniteness;
import org.mila.entities.lexicon.Item;
import org.mila.entities.lexicon.MultiWordFrozenException;
import org.mila.entities.lexicon.MultiWordFrozenExceptionAdd;
import org.mila.entities.lexicon.MultiWordFrozenExceptionRemove;
import org.mila.entities.lexicon.MultiWordFrozenExceptionReplace;
import org.mila.entities.lexicon.MultiWordFrozenLexicon;
import org.mila.entities.lexicon.Pos;

public class MultiWordFrozenGen extends ItemGen {
	private AcronymDefiniteness definiteness;
	private Pos mwPos = Pos.UNSPECIFIED;
	private String undottedLexiconItem = "";
	private boolean prefix = false;
	private boolean acceptDefiniteness = false;
	private MultiWordFrozenLexicon mw;

	public MultiWordFrozenGen(Item item, EntityManager lexicon,
			EntityManager generator, EntityManager inflections) {
		super(item, lexicon, generator, inflections);
		this.mw = (MultiWordFrozenLexicon) item.getSubitem();
	}

	protected List<MultiWordFrozenException> getRemoveExceptions() {
		return filter(
				Matchers.instanceOf(MultiWordFrozenExceptionRemove.class),
				mw.getExceptions());
	}

	protected List<MultiWordFrozenException> getReplaceExceptions() {
		return filter(
				Matchers.instanceOf(MultiWordFrozenExceptionReplace.class),
				mw.getExceptions());
	}

	protected List<MultiWordFrozenException> getAddExceptions() {
		return filter(Matchers.instanceOf(MultiWordFrozenExceptionAdd.class),
				mw.getExceptions());
	}

	private void analyse() {
		analyseItem();
		mwPos = mw.getMwPos();
		this.basePos = mwPos.value();
		undottedLexiconItem = undot;
		type = mw.getType();
		prefix = mw.isPrefix();
		definiteness = mw.getDefiniteness();
		if (definiteness == AcronymDefiniteness.INTERNAL
				|| definiteness == AcronymDefiniteness.INTERNAL_AND_EXTERNAL)
			definitnessVal = DefinitenessType.TRUE;
		else
			definitnessVal = DefinitenessType.FALSE;

		acceptDefiniteness = (definiteness == AcronymDefiniteness.EXTERNAL || definiteness == AcronymDefiniteness.INTERNAL_AND_EXTERNAL);
	}

	public List<Inflection> inflect() {
		System.out.println("MultiWordFrozenGen:inflect()");
		analyse();
		PopulateMWE popMWE = new PopulateMWE(this.inflections);
		popMWE.populateMWETables(this, prefix);

		if (acceptDefiniteness) {
			definitnessVal = DefinitenessType.TRUE;
			register = RegisterType.SPOKEN;
			String mwUndotted = " ה" + undottedLexiconItem;
			String mwTransliterated = "h" + transliterated;
			popMWE.populateMWETables(this, prefix, mwTransliterated, mwUndotted);
		}

		for (MultiWordFrozenException ex : getAddExceptions()) {
			transliterated = ex.getTransliterated();
			surface = ex.getUndotted();
			register = RegisterType.fromValue(ex.getRegister().value());
			spelling = SpellingType.fromValue(ex.getSpelling().value());
			definitnessVal = DefinitenessType.FALSE;
			popMWE.populateMWETables(this, prefix);

			if (ex.getDefiniteness() == AcronymDefiniteness.EXTERNAL
					|| ex.getDefiniteness() == AcronymDefiniteness.INTERNAL_AND_EXTERNAL) {
				definitnessVal = DefinitenessType.TRUE;
				register = RegisterType.SPOKEN;
				String mwUndotted = " ה" + surface;
				String mwTransliterated = "h" + transliterated;
				popMWE.populateMWETables(this, prefix, mwTransliterated,
						mwUndotted);
			}
		}
		
		return Collections.emptyList();
	}

}
