/*
 * Created on 14/11/2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package org.mila.generator.generation;

import static ch.lambdaj.Lambda.filter;

import java.util.List;
import java.util.StringTokenizer;

import javax.persistence.EntityManager;

import org.hamcrest.Matchers;
import org.mila.entities.corpus.DefinitenessType;
import org.mila.entities.corpus.GenderType;
import org.mila.entities.corpus.NumberType;
import org.mila.entities.corpus.RegisterType;
import org.mila.entities.corpus.SpellingType;
import org.mila.entities.corpus.TenseType;
import org.mila.entities.inflections.Inflection;
import org.mila.entities.lexicon.Item;
import org.mila.entities.lexicon.ModalException;
import org.mila.entities.lexicon.ModalExceptionAdd;
import org.mila.entities.lexicon.ModalExceptionReplace;
import org.mila.entities.lexicon.ModalLexicon;
import org.mila.generator.utils.Transliteration;

/**
 * @author daliabo
 * 
 *         TODO To change the template for this generated type comment go to
 *         Window - Preferences - Java - Code Style - Code Templates
 */
public class ModalGen extends ItemGen {
	ModalLexicon modal;

	public ModalGen(Item item, EntityManager lexicon, EntityManager generator,
			EntityManager inflections) {
		super(item, lexicon, generator, inflections);
		modal = (ModalLexicon) item.getSubitem();
	}

	static final String suffixList = "h,im,wt";
	static final String genderList = "feminine,masculine,feminine";
	static final String numberList = "singular,plural,plural";
	String inflectionType = "";

	protected void addException() {
		analyseAddExceptionList(filter(
				Matchers.instanceOf(ModalExceptionAdd.class),
				modal.getExceptions()));
	}

	private void analyseAddExceptionList(List<ModalException> exceptions) {
		for (ModalException ex : exceptions) {
			inflectedItem = ex.getTransliterated();
			surface = ex.getUndotted();
			register = RegisterType.fromValue(ex.getRegister().value());
			spelling = SpellingType.fromValue(ex.getSpelling().value());
			gender = GenderType.fromValue(ex.getGender().value());
			number = NumberType.fromValue(ex.getNumber().value());
			tense = TenseType.fromValue(ex.getTense().value());

			basePerson = ex.getPerson();
			populateDatabase();
			if (tense == TenseType.BEINONI) {

				inflectedItem = "h" + inflectedItem;
				surface = Transliteration.toHebrew(inflectedItem);
				/* XXX: It was "s" before, not unspecified */
				definitnessVal = DefinitenessType.UNSPECIFIED;
				populateDatabase();
			}

			definitnessVal = DefinitenessType.UNSPECIFIED;
		}
	}

	protected List<ModalException> getReplaceExceptions() {
		return filter(Matchers.instanceOf(ModalExceptionReplace.class),
				modal.getExceptions());
	}

	protected boolean replaceExceptionExist() {
		for (ModalException ex : getReplaceExceptions()) {
			GenderType exGender = GenderType.fromValue(ex.getGender().value());
			NumberType exNumber = NumberType.fromValue(ex.getNumber().value());
			String exPerson = ex.getPerson();
			/* XXX: Tense isn't used here -- is that OK? */
			@SuppressWarnings("unused")
			TenseType exTense = TenseType.fromValue(ex.getTense().value());
			if (exGender.equals(gender) && exNumber.equals(number)
					&& exPerson.equals(basePerson)) {
				
				inflectedItem = ex.getTransliterated();
				surface = ex.getUndotted();
				register = RegisterType.fromValue(ex.getRegister().value());
				populateDatabase();
				return true;
			}
		}
		return false;
	}

	private void analyse() {
		analyseItem();
		definitnessVal = DefinitenessType.UNSPECIFIED;
	}

	public List<Inflection> inflect() {
		/* XXX: I don't see remove functionality here */
		analyse();
		inflectedItem = transliterated;
		surface = undot;
		gender = GenderType.fromValue(modal.getGender().value());
		number = NumberType.fromValue(modal.getNumber().value());
		inflectionType = modal.getInflectionType().value();
		tense = TenseType.fromValue(modal.getTense().value());
		if (inflectionType.equals("genderNumberTime"))
			basePerson = "3";
		else if (inflectionType.equals("genderNumber"))
			basePerson = "any";
		else
			basePerson = "unspecified";
		populateDatabase();
		if (tense == TenseType.BEINONI) {
			inflectedItem = "h" + inflectedItem;
			surface = Transliteration.toHebrew(inflectedItem);
			/* XXX: It was "s" before, not unspecified */
			definitnessVal = DefinitenessType.UNSPECIFIED;
			populateDatabase();
		}

		definitnessVal = DefinitenessType.UNSPECIFIED;

		if (inflectionType.equals("genderNumber")) {
			StringTokenizer genderSt = new StringTokenizer(genderList, ",");
			StringTokenizer numberSt = new StringTokenizer(numberList, ",");
			StringTokenizer suffixSt = new StringTokenizer(suffixList, ",");
			while (suffixSt.hasMoreTokens()) {
				String suffix = suffixSt.nextToken();
				gender = GenderType.fromValue(genderSt.nextToken());
				number = NumberType.fromValue(numberSt.nextToken());
				basePerson = "any";
				inflectedItem = transliterated + suffix;
				surface = Transliteration.toHebrew(inflectedItem);
				if (!replaceExceptionExist())
					populateDatabase();
			}

		}

		addException();
		return this.getGeneratedInflections();
	}

}
