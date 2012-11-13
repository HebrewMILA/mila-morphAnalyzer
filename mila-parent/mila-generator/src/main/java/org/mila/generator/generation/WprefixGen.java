/*
 * Created on 14/11/2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package org.mila.generator.generation;

import static ch.lambdaj.Lambda.filter;

import java.util.List;

import javax.persistence.EntityManager;

import org.hamcrest.Matchers;
import org.mila.entities.corpus.DefinitenessType;
import org.mila.entities.corpus.GenderType;
import org.mila.entities.corpus.NumberType;
import org.mila.entities.corpus.PolarityType;
import org.mila.entities.corpus.RegisterType;
import org.mila.entities.corpus.SpellingType;
import org.mila.entities.inflections.Inflection;
import org.mila.entities.lexicon.Item;
import org.mila.entities.lexicon.WprefixException;
import org.mila.entities.lexicon.WprefixExceptionAdd;
import org.mila.entities.lexicon.WprefixLexicon;

public class WprefixGen extends ItemGen {
	WprefixLexicon wPrefix;

	public WprefixGen(Item item, EntityManager lexicon,
			EntityManager generator, EntityManager inflections) {
		super(item, lexicon, generator, inflections);
		wPrefix = (WprefixLexicon) item.getSubitem();
	}

	protected void addException() {
		analyseAddExceptionList(filter(
				Matchers.instanceOf(WprefixExceptionAdd.class),
				wPrefix.getExceptions()));
	}

	private void analyseAddExceptionList(List<WprefixException> exceptions) {
		for (WprefixException ex : exceptions) {
			definitnessVal = DefinitenessType.FALSE;
			inflectedItem = ex.getTransliterated();
			surface = ex.getUndotted();
			spelling = SpellingType.fromValue(ex.getSpelling().value());
			register = RegisterType.fromValue(ex.getRegister().value());
			gender = GenderType.fromValue(ex.getGender().value());
			number = NumberType.fromValue(ex.getNumber().value());
			populateDatabase();
			definitnessVal = DefinitenessType.TRUE;
			addH();
		}
	}

	private void analyse() {
		analyseItem();
		gender = GenderType.fromValue(wPrefix.getGender().value());
		number = NumberType.fromValue(wPrefix.getNumber().value());
		polarityVal = PolarityType.fromValue(wPrefix.getPolarity().value());
	}

	public List<Inflection> inflect() {
		analyse();
		definitnessVal = DefinitenessType.FALSE;
		inflectedItem = transliterated;
		surface = undot;
		populateDatabase();
		definitnessVal = DefinitenessType.TRUE;
		addH();
		addException();
		return this.getGeneratedInflections();
	}

}
