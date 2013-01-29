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
import org.mila.entities.corpus.SuffixFunctionType;
import org.mila.entities.corpus.TenseType;
import org.mila.entities.inflections.PersistableInflection;
import org.mila.entities.lexicon.Definitness;
import org.mila.entities.lexicon.ExistentialException;
import org.mila.entities.lexicon.ExistentialExceptionAdd;
import org.mila.entities.lexicon.ExistentialLexicon;
import org.mila.entities.lexicon.Item;

/**
 * @author daliabo
 * 
 *         TODO To change the template for this generated type comment go to
 *         Window - Preferences - Java - Code Style - Code Templates
 */
public class ExistentialGen extends ItemGen {
	private ExistentialLexicon existential;

	public ExistentialGen(Item item, EntityManager lexicon,
			EntityManager generator, EntityManager inflections) {
		super(item, lexicon, generator, inflections);
		existential = (ExistentialLexicon) item.getSubitem();
	}

	protected void addException() {
		analyseAddExceptionList(filter(
				Matchers.instanceOf(ExistentialExceptionAdd.class),
				existential.getExceptions()));
	}

	private void analyseAddExceptionList(List<ExistentialException> exceptions) {
		for (ExistentialException ex : exceptions) {
			inflectedItem = ex.getTransliterated();
			surface = ex.getUndotted();
			spelling = SpellingType.fromValue(ex.getSpelling().value());
			register = RegisterType.fromValue(ex.getRegister().value());
			PGN = ex.getPossessive();
			gender = GenderType.fromValue(ex.getGender().value());
			number = NumberType.fromValue(ex.getNumber().value());
			tense = TenseType.fromValue(ex.getTense().value());
			if (definitness == Definitness.PROHIBITED)
				definitnessVal = DefinitenessType.FALSE;
			else
				definitnessVal = DefinitenessType.FALSE;
			if (!PGN.equals("unspecified")) {
				suffixFunction = SuffixFunctionType.PRONOMIAL;
			} else
				suffixFunction = SuffixFunctionType.UNSPECIFIED;
			populateDatabase();
			if (definitness != Definitness.PROHIBITED) {
				// הישנם , הישנן
				addH();
			}
		}
	}

	private void analyse() {
		analyseItem();
		gender = GenderType.fromValue(existential.getGender().value());
		number = NumberType.fromValue(existential.getNumber().value());
		tense = TenseType.fromValue(existential.getTense().value());
		definitness = existential.getDefiniteness();
		if (definitness == Definitness.PROHIBITED)
			definitnessVal = DefinitenessType.FALSE;
		else
			definitnessVal = DefinitenessType.FALSE;

		suffixFunction = SuffixFunctionType.UNSPECIFIED;

		PGN = existential.getPossessive();
		if (!PGN.equals("unspecified"))
			suffixFunction = SuffixFunctionType.PRONOMIAL;

		root = existential.getRoot();
		polarityVal = PolarityType
				.fromValue(existential.getPolarity().value());
	}

	protected void addH() {
		if (definitness != Definitness.PROHIBITED) {
			definitnessVal = DefinitenessType.TRUE;
			super.addH();
		}
	}

	public List<PersistableInflection> inflect() {

		analyse();
		inflectedItem = transliterated;
		surface = undot;
		populateDatabase();
		if (definitness != Definitness.PROHIBITED)
			addH();
		addException();
		return this.getGeneratedInflections();

	}

}
