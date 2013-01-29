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
import org.mila.entities.corpus.PrefixTransliteratedType;
import org.mila.entities.corpus.RegisterType;
import org.mila.entities.corpus.SpellingType;
import org.mila.entities.corpus.SuffixFunctionType;
import org.mila.entities.inflections.PersistableInflection;
import org.mila.entities.lexicon.Definitness;
import org.mila.entities.lexicon.Item;
import org.mila.entities.lexicon.PronounException;
import org.mila.entities.lexicon.PronounExceptionAdd;
import org.mila.entities.lexicon.PronounExceptionRemove;
import org.mila.entities.lexicon.PronounLexicon;
import org.mila.generator.utils.Transliteration;

public class PronounGen extends ItemGen {
	private PronounLexicon pronoun;

	public PronounGen(Item item, EntityManager lexicon,
			EntityManager generator, EntityManager inflections) {
		super(item, lexicon, generator, inflections);
		pronoun = (PronounLexicon) item.getSubitem();
	}

	boolean isDefinite = false;

	private void analyseAddExceptionList(List<PronounException> exceptions) {
		for (PronounException ex : exceptions) {
			inflectedItem = ex.getTransliterated();
			surface = ex.getUndotted();
			spelling = SpellingType.fromValue(ex.getSpelling().value());
			register = RegisterType.fromValue(ex.getRegister().value());
			PGN = ex.getPossessive();
			basePerson = ex.getPerson();
			gender = GenderType.fromValue(ex.getGender().value());
			number = NumberType.fromValue(ex.getNumber().value());
			type = pronoun.getType().value();
			if (!PGN.equals("unspecified")) {
				suffixFunction = SuffixFunctionType.PRONOMIAL;
				basePerson = "unspecified";
				definitnessVal = DefinitenessType.UNSPECIFIED;
			} else
				suffixFunction = SuffixFunctionType.UNSPECIFIED;
			prefix = PrefixTransliteratedType.UNSPECIFIED;
			handlePronounType();
			handleDefiniteness(inflectedItem);
			generateBKLMForms(ex.getTransliterated());
		}
	}

	protected void addException() {
		analyseAddExceptionList(filter(
				Matchers.instanceOf(PronounExceptionAdd.class),
				pronoun.getExceptions()));
	}

	protected void generateBKLMForms(String transliterated) {
		if (pronoun.getType().value().indexOf("with bklm") != -1) {

			inflectedItem = "b" + transliterated;
			surface = Transliteration.toHebrew(inflectedItem);
			prefix = PrefixTransliteratedType.B;
			if (pronoun.getType().value().indexOf("with definiteness") != -1) {
				handlePronounType();
				handlebklmDefiniteness(inflectedItem);
			} else {
				definitnessVal = DefinitenessType.FALSE;
				handlePronounType();
				populateDatabase();

			}

			inflectedItem = "k" + transliterated;
			surface = Transliteration.toHebrew(inflectedItem);
			prefix = PrefixTransliteratedType.K;
			handlePronounType();
			if (pronoun.getType().value().indexOf("with definiteness") != -1) {
				handlePronounType();
				handlebklmDefiniteness(inflectedItem);
			} else {
				definitnessVal = DefinitenessType.FALSE;
				handlePronounType();
				populateDatabase();

			}

			inflectedItem = "l" + transliterated;
			surface = Transliteration.toHebrew(inflectedItem);
			prefix = PrefixTransliteratedType.L;
			handlePronounType();
			if (pronoun.getType().value().indexOf("with definiteness") != -1) {
				handlePronounType();
				handlebklmDefiniteness(inflectedItem);
			} else {
				definitnessVal = DefinitenessType.FALSE;
				handlePronounType();
				populateDatabase();

			}

			inflectedItem = "m" + transliterated;
			surface = Transliteration.toHebrew(inflectedItem);
			prefix = PrefixTransliteratedType.M;
			handlePronounType();
			if (pronoun.getType().value().indexOf("with definiteness") != -1) {
				handlePronounType();
				handlebklmDefiniteness(inflectedItem);
			} else {
				definitnessVal = DefinitenessType.FALSE;
				handlePronounType();
				populateDatabase();

			}

		} else
			handlePronounType();

	}

	protected void handlePronounType() {
		if (type.endsWith("with bklm without definiteness"))
			type = type.replaceAll("with bklm without definiteness", "");
		else if (type.endsWith("with bklm with definiteness"))
			type = type.replaceAll("with bklm with definiteness", "");
		else if (type.endsWith("with bklm"))
			type = type.replaceAll("with bklm", "");
		else if (type.endsWith("no bklm"))
			type = type.replaceAll("no bklm", "");
	}

	protected List<PronounException> getRemoveExceptions() {
		return filter(Matchers.instanceOf(PronounExceptionRemove.class),
				pronoun.getExceptions());
	}

	protected boolean removeExceptionExist() {
		for (PronounException ex : getRemoveExceptions())
			if (ex.getPossessive().equals(PGN))
				return true;

		return false;
	}

	protected void handleBaseForm() {
		if (!removeExceptionExist()) {
			if (!PGN.equals("unspecified")) {
				suffixFunction = SuffixFunctionType.PRONOMIAL;
				basePerson = "unspecified";
				definitnessVal = DefinitenessType.UNSPECIFIED;
			} else
				suffixFunction = SuffixFunctionType.UNSPECIFIED;
			handlePronounType();
			handleDefiniteness(inflectedItem);
			generateBKLMForms(transliterated);
		}
	}

	private void analyse() {
		analyseItem();
		gender = GenderType.fromValue(pronoun.getGender().value());
		basePerson = pronoun.getPerson();
		number = NumberType.fromValue(pronoun.getNumber().value());
		type = pronoun.getType().value();
		inflectedItem = transliterated;
		surface = undot;
		definitness = pronoun.getDefiniteness();
		suffixFunction = SuffixFunctionType.UNSPECIFIED;
		PGN = pronoun.getPossessive();
		prefix = PrefixTransliteratedType.UNSPECIFIED;
	}

	private void handleDefiniteness(String item) {
		try {
			/* TODO: This should be a switch */
			if (definitness == Definitness.REQUIRED) {
				definitnessVal = DefinitenessType.TRUE;
				populateDatabase();

				definitnessVal = DefinitenessType.TRUE;
				inflectedItem = "h" + item;
				surface = Transliteration.toHebrew(inflectedItem);
				populateDatabase();

			} else if (definitness == Definitness.OPTIONAL) {
				definitnessVal = DefinitenessType.FALSE;
				populateDatabase();
				definitnessVal = DefinitenessType.TRUE;
				inflectedItem = "h" + item;
				surface = Transliteration.toHebrew(inflectedItem);
				populateDatabase();
			} else if (definitness == Definitness.PROHIBITED) {
				definitnessVal = DefinitenessType.FALSE;
				populateDatabase();
			} else if (definitness == Definitness.DEFINITED) {
				definitnessVal = DefinitenessType.TRUE;
				populateDatabase();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void handlebklmDefiniteness(String item) {
		if (item.charAt(0) != 'm') {
			if (definitness == Definitness.REQUIRED) {
				definitnessVal = DefinitenessType.TRUE;
				populateDatabase();

			} else if (definitness == Definitness.OPTIONAL) {
				definitnessVal = DefinitenessType.FALSE;
				populateDatabase();
				definitnessVal = DefinitenessType.TRUE;
				populateDatabase();
			} else if (definitness == Definitness.DEFINITED) {
				inflectedItem = item.charAt(0) + item.substring(2);
				surface = Transliteration.toHebrew(inflectedItem);
				definitnessVal = DefinitenessType.TRUE;
				populateDatabase();
			} else {
				definitnessVal = DefinitenessType.FALSE;
				populateDatabase();

			}
		} else {
			item = item.substring(1);
			if (definitness == Definitness.REQUIRED) {
				inflectedItem = "mh" + item;
				surface = Transliteration.toHebrew(inflectedItem);
				definitnessVal = DefinitenessType.TRUE;
				populateDatabase();

			} else if (definitness == Definitness.OPTIONAL) {
				inflectedItem = "m" + item;
				surface = Transliteration.toHebrew(inflectedItem);
				definitnessVal = DefinitenessType.FALSE;
				populateDatabase();
				inflectedItem = "mh" + item;
				surface = Transliteration.toHebrew(inflectedItem);
				definitnessVal = DefinitenessType.TRUE;
				populateDatabase();
			} else if (definitness == Definitness.DEFINITED) {
				inflectedItem = "m" + item;
				surface = Transliteration.toHebrew(inflectedItem);
				definitnessVal = DefinitenessType.TRUE;
				populateDatabase();
			} else {
				inflectedItem = "m" + item;
				surface = Transliteration.toHebrew(inflectedItem);
				definitnessVal = DefinitenessType.FALSE;
				populateDatabase();
			}
		}
	}

	public List<PersistableInflection> inflect() {
		analyse();
		handleBaseForm();
		addException();
		return this.getGeneratedInflections();
	}
}
