/*
 * Created on 19/09/2005
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
import org.mila.entities.corpus.RegisterType;
import org.mila.entities.corpus.SpellingType;
import org.mila.entities.corpus.SuffixFunctionType;
import org.mila.entities.inflections.Inflection;
import org.mila.entities.lexicon.Item;
import org.mila.entities.lexicon.ProperNameException;
import org.mila.entities.lexicon.ProperNameExceptionAdd;
import org.mila.entities.lexicon.ProperNameLexicon;
import org.mila.generator.utils.Transliteration;

/**
 * @author daliabo
 * 
 *         TODO To change the template for this generated type comment go to
 *         Window - Preferences - Java - Code Style - Code Templates
 */
public class ProperNameGen extends ItemGen {
	ProperNameLexicon properName;

	public ProperNameGen(Item item, EntityManager lexicon,
			EntityManager generator, EntityManager inflections) {
		super(item, lexicon, generator, inflections);
		properName = (ProperNameLexicon) item.getSubitem();
	}

	private void analyse() {
		analyseItem();

		gender = GenderType.fromValue(properName.getGender().value());
		number = NumberType.fromValue(properName.getNumber().value());
		type = properName.getType().value();
		definitness = properName.getDefiniteness();
		/* Again, this seems useless */
		direction = properName.getDirection().value();
		surface = undot;
		inflectedItem = transliterated;
		suffixFunction = SuffixFunctionType.UNSPECIFIED;
		basePos = "propername";
	}

	private void addExceptions() {
		analyseAddExceptionList(filter(
				Matchers.instanceOf(ProperNameExceptionAdd.class),
				properName.getExceptions()));
	}

	private void analyseAddExceptionList(List<ProperNameException> exceptions) {
		for (ProperNameException ex : exceptions) {
			inflectedItem = ex.getTransliterated();
			surface = ex.getUndotted();

			if (ex.getTransliterated().startsWith("w")
					&& !ex.getTransliterated().startsWith("ww")) {
				inflectedItem = "w" + ex.getTransliterated();
				surface = "ו" + ex.getUndotted();
			}

			gender = GenderType.fromValue((lexiconGender = ex.getGender())
					.value());
			number = NumberType.fromValue((lexiconNumber = ex.getNumber())
					.value());
			spelling = SpellingType.fromValue(ex.getSpelling().value());
			register = RegisterType.fromValue(ex.getRegister().value());
			type = ex.getType().value();
			definitness = ex.getDefiniteness();
			handleDefiniteness(inflectedItem);
		}
	}

	private void handleDefiniteness(String item) {
		switch (definitness) {
		case REQUIRED:
			definitnessVal = DefinitenessType.TRUE;
			populateDatabase();

			definitnessVal = DefinitenessType.TRUE;
			inflectedItem = "h" + item;
			surface = Transliteration.toHebrew(inflectedItem);
			populateDatabase();
			break;
		case OPTIONAL:
			definitnessVal = DefinitenessType.FALSE;
			populateDatabase();
			definitnessVal = DefinitenessType.TRUE;
			inflectedItem = "h" + item;
			surface = Transliteration.toHebrew(inflectedItem);
			populateDatabase();
			break;
		default:
			definitnessVal = DefinitenessType.FALSE;
			populateDatabase();
			break;
		}
	}

	public List<Inflection> inflect() {
		analyse();
		handleDefiniteness(inflectedItem);
		// טיפול בשמות שמתחילים ב-ו כמו וינה שיזהה גם בווינה
		if (transliterated.startsWith("w") && !transliterated.startsWith("ww")) {
			inflectedItem = "w" + transliterated;
			surface = Transliteration.toHebrew(inflectedItem);
			handleDefiniteness(inflectedItem);
		}
		addExceptions();
		return this.getGeneratedInflections();
	}
}
