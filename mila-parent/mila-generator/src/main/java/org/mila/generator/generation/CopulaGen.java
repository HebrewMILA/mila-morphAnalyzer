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
import org.mila.entities.corpus.GenderType;
import org.mila.entities.corpus.NumberType;
import org.mila.entities.corpus.PolarityType;
import org.mila.entities.corpus.RegisterType;
import org.mila.entities.corpus.SpellingType;
import org.mila.entities.corpus.SuffixFunctionType;
import org.mila.entities.corpus.TenseType;
import org.mila.entities.inflections.Inflection;
import org.mila.entities.lexicon.CopulaException;
import org.mila.entities.lexicon.CopulaExceptionAdd;
import org.mila.entities.lexicon.CopulaLexicon;
import org.mila.entities.lexicon.Item;
import org.mila.generator.utils.Transliteration;

public class CopulaGen extends ItemGen {
	private CopulaLexicon copula;

	public CopulaGen(Item item, EntityManager lexicon, EntityManager generator,
			EntityManager inflections) {
		super(item, lexicon, generator, inflections);
		this.copula = (CopulaLexicon) item.getSubitem();
	}

	String definiteness = "";

	protected void addException() {
		analyseAddExceptionList(filter(
				Matchers.instanceOf(CopulaExceptionAdd.class),
				copula.getExceptions()));
	}

	private void analyseAddExceptionList(List<CopulaException> exceptions) {
		for (CopulaException ex : exceptions) {
			inflectedItem = ex.getTransliterated();
			surface = ex.getUndotted();
			spelling = SpellingType.fromValue(ex.getSpelling().value());
			register = RegisterType.fromValue(ex.getRegister().value());
			gender = GenderType.fromValue(ex.getGender().value());
			number = NumberType.fromValue(ex.getNumber().value());
			basePerson = ex.getPerson();
			tense = TenseType.fromValue(ex.getTense().value());
			populateDatabase();
			if (tense == TenseType.BARE_INFINITIVE) {
				String base = inflectedItem;
				generatebklInfinitive(base);
				generateInflectedInfinitive(base);
			}
		}
	}

	public String generatePGN(String gender, String number, String person) {
		String pgn = "";
		if (gender.equals("masculine"))
			this.gender = GenderType.MASCULINE;
		else if (gender.equals("feminine"))
			this.gender = GenderType.FEMININE;
		else if (gender.equals("masculine and feminine"))
			this.gender = GenderType.MASCULINE_AND_FEMININE;
		if (number.equals("singular"))
			this.number = NumberType.SINGULAR;
		else if (number.equals("plural"))
			this.number = NumberType.PLURAL;
		pgn = person + "p/" + gender + "/" + number;
		return pgn;
	}

	public void generateInflectedInfinitive(String base) {
		String suffix = "";
		tense = TenseType.INFINITIVE;
		suffixFunction = SuffixFunctionType.ACCUSATIVE_OR_NOMINATIVE;
		/* TODO: What we *really* want here is a list of tuples, no? */
		StringTokenizer stPerson = new StringTokenizer(personTokens10, ",");
		StringTokenizer stNumber = new StringTokenizer(numberTokens10, ",");
		StringTokenizer stGender = new StringTokenizer(genderTokens10, ",");
		StringTokenizer stSuffix = new StringTokenizer(
				"i,k,k,w,h,nw,km,kn,m,n,", ",");
		while (stSuffix.hasMoreTokens()) {
			String number = stNumber.nextToken();
			String person = stPerson.nextToken();
			String gender = stGender.nextToken();
			PGN = generatePGN(gender, number, person);
			suffix = stSuffix.nextToken();
			inflectedItem = "l" + base + suffix;
			surface = Transliteration.toHebrew(inflectedItem);
			populateDatabase();
			inflectedItem = "b" + base + suffix;
			surface = Transliteration.toHebrew(inflectedItem);
			populateDatabase();
			inflectedItem = "k" + base + suffix;
			surface = Transliteration.toHebrew(inflectedItem);
			populateDatabase();
			inflectedItem = "m" + base + suffix;
			surface = Transliteration.toHebrew(inflectedItem);
			populateDatabase();
		}
		suffixFunction = SuffixFunctionType.UNSPECIFIED;
		PGN = "unspecified";
	}

	public void generatebklInfinitive(String base) {
		tense = TenseType.INFINITIVE;
		inflectedItem = "l" + base;
		surface = Transliteration.toHebrew(inflectedItem);
		populateDatabase();
		inflectedItem = "b" + base;
		surface = Transliteration.toHebrew(inflectedItem);
		populateDatabase();
		inflectedItem = "k" + base;
		surface = Transliteration.toHebrew(inflectedItem);
		populateDatabase();
		inflectedItem = "m" + base;
		surface = Transliteration.toHebrew(inflectedItem);
	}

	public void inflectItem() {
		if (tense == TenseType.BARE_INFINITIVE) {
			String base = inflectedItem;
			generatebklInfinitive(base);
			generateInflectedInfinitive(base);
		}
	}

	private void analyse() {
		analyseItem();
		gender = GenderType.fromValue(copula.getGender().value());
		number = NumberType.fromValue(copula.getNumber().value());
		basePerson = copula.getPerson();
		tense = TenseType.fromValue(copula.getTense().value());
		PGN = "unspecified";

		polarityVal = PolarityType.fromValue(copula.getPolarity().value());
	}

	public List<Inflection> inflect() {
		analyse();
		inflectedItem = transliterated;
		surface = undot;
		populateDatabase();
		inflectItem();
		addException();
		return this.getGeneratedInflections();
	}

}
