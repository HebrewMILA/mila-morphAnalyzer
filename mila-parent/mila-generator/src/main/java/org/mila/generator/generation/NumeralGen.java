/*
 * Created on 18/09/2005
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
import org.mila.entities.corpus.SuffixFunctionType;
import org.mila.entities.corpus.TriStateType;
import org.mila.entities.inflections.Inflection;
import org.mila.entities.lexicon.Definitness;
import org.mila.entities.lexicon.Item;
import org.mila.entities.lexicon.Numeral;
import org.mila.entities.lexicon.NumeralException;
import org.mila.entities.lexicon.NumeralExceptionAdd;
import org.mila.entities.lexicon.NumeralExceptionRemove;
import org.mila.entities.lexicon.NumeralExceptionReplace;
import org.mila.entities.lexicon.NumeralLexicon;
import org.mila.generator.utils.Transliteration;

public class NumeralGen extends ItemGen {
	private final NumeralLexicon numeral;

	String personTokens = "";

	String numberTokens = "";

	String genderTokens = "";

	public NumeralGen(final Item item, final EntityManager lexicon,
			final EntityManager generator, final EntityManager inflections) {
		super(item, lexicon, generator, inflections);
		this.numeral = (NumeralLexicon) item.getSubitem();
	}

	protected void addException() {
		this.analyseAddExceptionList(filter(
				Matchers.instanceOf(NumeralExceptionAdd.class),
				this.numeral.getExceptions()));
	}

	@Override
	protected void addH() {
		if (!(this.definitness == Definitness.PROHIBITED)) {
			super.addH();
		}
	}

	private void analyse() {
		this.analyseItem();
		this.PGN = "unspecified";
		this.type = this.numeral.getType().value();
		this.gender = GenderType.fromValue(this.numeral.getGender().value());
		this.number = NumberType.fromValue(this.numeral.getNumber().value());
		this.inflect = this.numeral.isInflect();
		this.inflectedItem = this.transliterated;
		this.surface = this.undot;
		this.definitness = this.numeral.getDefiniteness();
		if (this.definitness == Definitness.PROHIBITED) {
			this.definitnessVal = DefinitenessType.FALSE;
		} else {
			this.definitnessVal = DefinitenessType.FALSE;
		}
		this.construct = TriStateType.FALSE;
		this.value = this.numeral.getValue();
	}

	private void analyseAddExceptionList(final List<NumeralException> exceptions) {
		for (final NumeralException ex : exceptions) {
			this.inflectedItem = ex.getTransliterated();
			this.surface = ex.getUndotted();
			this.register = RegisterType.fromValue(ex.getRegister().value());
			this.spelling = SpellingType.fromValue(ex.getSpelling().value());
			this.PGN = ex.getPossessive();
			this.construct = TriStateType.fromValue(ex.getConstruct().value());
			this.gender = GenderType.fromValue(ex.getGender().value());
			this.number = NumberType.fromValue(ex.getNumber().value());
			this.definitness = this.numeral.getDefiniteness();
			this.suffixFunction = SuffixFunctionType.UNSPECIFIED;

			if (this.definitness == Definitness.PROHIBITED) {
				this.definitnessVal = DefinitenessType.FALSE;
			} else {
				this.definitnessVal = DefinitenessType.FALSE;
			}
			this.populateDatabase();
			if (this.construct == TriStateType.FALSE
					&& (this.PGN.equals("unspecified") || this.PGN.equals(""))) {
				this.setAttributes(this.gender, NumberType.PLURAL,
						TriStateType.FALSE, DefinitenessType.TRUE,
						this.inflectedItem, "unspecified",
						SuffixFunctionType.UNSPECIFIED);

				this.addH();

			}
		}
	}

	protected void generateConstruct() {
		if (this.numeral.getType() == Numeral.NUMERAL_FRACTIONAL) {
			if (this.number == NumberType.PLURAL
					&& this.gender == GenderType.MASCULINE) {
				this.inflectedItem = this.inflectedItem.substring(0,
						this.inflectedItem.length() - 1);
			} else if (this.number == NumberType.PLURAL
					&& this.gender == GenderType.FEMININE) {
				this.inflectedItem = this.inflectedItem.substring(0,
						this.inflectedItem.length());
			}
			this.surface = Transliteration.toHebrew(this.inflectedItem);
			if (!this.replaceExceptionExist()) {
				this.populateDatabase();
			}
		} else if (this.numeral.getType() == Numeral.NUMERAL_CARDINAL
				&& !this.inflectedItem.equals("axd")
				&& !this.inflectedItem.equals("eniim")) {
			/* XXX: What is this, hard coded exceptions? why? */
			if (this.gender == GenderType.MASCULINE) {
				this.inflectedItem = this.inflectedItem.substring(0,
						this.inflectedItem.length() - 1) + "t";
			}
			this.surface = Transliteration.toHebrew(this.inflectedItem);
			if (!this.replaceExceptionExist()) {
				this.populateDatabase();
			}
		}
	}

	protected void generateFeminine() {
		/* XXX: The logic here seems wrong */
		if (this.getReplaceExceptions().size() <= 0) {
			this.inflectedItem = this.inflectedItem + "t";
			this.surface = Transliteration.toHebrew(this.inflectedItem);
			this.populateDatabase();
		} else {
			this.replaceExceptionExist();
		}

	}

	protected void generatePlural() {
		// generate masculine plural
		if (this.numeral.getType() == Numeral.NUMERAL_ORDINAL) {
			if (this.gender == GenderType.MASCULINE) {
				this.inflectedItem = this.transliterated + "im";
			} else if (this.gender == GenderType.FEMININE) {
				this.inflectedItem = this.transliterated + "wt";
			}
			this.surface = Transliteration.toHebrew(this.inflectedItem);
			this.populateDatabase();

		} else if (this.numeral.getType() == Numeral.NUMERAL_FRACTIONAL) {
			if (this.gender == GenderType.MASCULINE) {
				this.inflectedItem = this.inflectedItem + "im";
				this.surface = Transliteration.toHebrew(this.inflectedItem);
			} else if (this.gender == GenderType.FEMININE) {
				this.inflectedItem = this.inflectedItem.substring(0,
						this.inflectedItem.length() - 1) + "wt";
				this.surface = Transliteration.toHebrew(this.inflectedItem);
			}
			if (!this.replaceExceptionExist()) {
				this.populateDatabase();
			}
		}

	}

	private void generatePossessive() {
		String suffixes = "";
		final String base = this.inflectedItem;
		String suffix = "";
		if (this.type.equals("numeral fractional") && this.inflect) {
			if (this.number == NumberType.SINGULAR) {
				suffixes = "i,k,k,w,h,nw,km,kn,m,n";
			} else if (this.number == NumberType.PLURAL
					&& this.gender == GenderType.FEMININE) {
				suffixes = "ii,ik,iik,iw,ih,inw,ikm,ikn,ihm,ihn";
			} else if (this.number == NumberType.PLURAL
					&& this.gender == GenderType.MASCULINE) {
				suffixes = "i,k,ik,w,h,nw,km,kn,hm,hn";
			}
			new StringTokenizer(this.inflectedItem, ",");
			final StringTokenizer stPGN = new StringTokenizer(PGNTokens10, ",");
			final StringTokenizer stSuffix = new StringTokenizer(suffixes, ",");
			while (stPGN.hasMoreTokens()) {
				this.PGN = stPGN.nextToken();
				suffix = stSuffix.nextToken();
				this.inflectedItem = base + suffix;
				this.surface = Transliteration.toHebrew(this.inflectedItem);
				this.populateDatabase();
			}
		}
		this.inflectedItem = base;
	}

	protected List<NumeralException> getRemoveExceptions() {
		return filter(Matchers.instanceOf(NumeralExceptionRemove.class),
				this.numeral.getExceptions());
	}

	protected List<NumeralException> getReplaceExceptions() {
		return filter(Matchers.instanceOf(NumeralExceptionReplace.class),
				this.numeral.getExceptions());
	}

	@Override
	public List<Inflection> inflect() {
		String feminineBase = "";
		String pluralBase = "";
		this.analyse();
		this.populateDatabase();

		this.setAttributes(this.gender, this.number, this.construct,
				DefinitenessType.TRUE, this.inflectedItem, "unspecified",
				SuffixFunctionType.UNSPECIFIED);

		this.addH();

		this.setAttributes(this.gender, this.number, TriStateType.TRUE,
				DefinitenessType.UNSPECIFIED, this.transliterated,
				"unspecified", SuffixFunctionType.UNSPECIFIED);

		this.generateConstruct();

		this.setAttributes(this.gender, this.number, TriStateType.FALSE,
				DefinitenessType.FALSE, this.transliterated, "unspecified",
				SuffixFunctionType.POSSESSIVE);

		this.generatePossessive();

		if (this.gender != GenderType.FEMININE
				&& this.numeral.getType() != Numeral.NUMERAL_ORDINAL) {

			this.setAttributes(GenderType.FEMININE, NumberType.SINGULAR,
					TriStateType.FALSE, DefinitenessType.FALSE,
					this.transliterated, "unspecified",
					SuffixFunctionType.UNSPECIFIED);

			this.generateFeminine();

			feminineBase = this.inflectedItem;

		}

		if (this.type.equals("numeral ordinal")
				|| this.type.equals("numeral fractional"))
			if (!(this.number == NumberType.PLURAL)) {

				this.setAttributes(
						GenderType.fromValue(this.numeral.getGender().value()),
						NumberType.PLURAL, TriStateType.FALSE,
						DefinitenessType.FALSE, this.transliterated,
						"unspecified", SuffixFunctionType.UNSPECIFIED);

				this.generatePlural();

				pluralBase = this.inflectedItem;
			}

		if (!feminineBase.equals("")) {

			this.number = NumberType
					.fromValue(this.numeral.getNumber().value());

			this.setAttributes(GenderType.FEMININE, this.number,
					TriStateType.FALSE, DefinitenessType.TRUE, feminineBase,
					"unspecified", SuffixFunctionType.UNSPECIFIED);

			this.addH();

			this.setAttributes(GenderType.FEMININE, NumberType.PLURAL,
					TriStateType.FALSE, DefinitenessType.FALSE, feminineBase,
					"unspecified", SuffixFunctionType.UNSPECIFIED);

			this.generatePlural();

			this.setAttributes(GenderType.FEMININE, NumberType.PLURAL,
					TriStateType.FALSE, DefinitenessType.TRUE,
					this.inflectedItem, "unspecified",
					SuffixFunctionType.UNSPECIFIED);

			this.addH();

			this.setAttributes(GenderType.FEMININE, NumberType.PLURAL,
					TriStateType.FALSE, DefinitenessType.UNSPECIFIED,
					feminineBase, "unspecified", SuffixFunctionType.UNSPECIFIED);

			this.generateConstruct();

			this.setAttributes(GenderType.FEMININE, NumberType.PLURAL,
					TriStateType.FALSE, DefinitenessType.FALSE, feminineBase,
					"unspecified", SuffixFunctionType.POSSESSIVE);

			this.generatePossessive();
		}

		if (!pluralBase.equals("")) {
			this.gender = GenderType
					.fromValue(this.numeral.getGender().value());

			this.setAttributes(this.gender, NumberType.PLURAL,
					TriStateType.FALSE, DefinitenessType.TRUE, pluralBase,
					"unspecified", SuffixFunctionType.UNSPECIFIED);

			this.addH();

			this.setAttributes(this.gender, NumberType.PLURAL,
					TriStateType.TRUE, DefinitenessType.UNSPECIFIED,
					pluralBase, "unspecified", SuffixFunctionType.UNSPECIFIED);

			this.generateConstruct();

			this.setAttributes(this.gender, NumberType.PLURAL,
					TriStateType.FALSE, DefinitenessType.FALSE,
					this.inflectedItem, "unspecified",
					SuffixFunctionType.POSSESSIVE);

			this.generatePossessive();

		}
		this.addException();
		return this.getGeneratedInflections();
	}

	@Override
	protected boolean replaceExceptionExist() {
		boolean match = false;
		for (final NumeralException ex : this.getReplaceExceptions()) {
			final GenderType exGender = GenderType.fromValue(ex.getGender()
					.value());
			final TriStateType exConstruct = TriStateType.fromValue(ex
					.getConstruct().value());
			final NumberType exNumber = NumberType.fromValue(ex.getNumber()
					.value());
			if (exGender.equals(this.gender)
					&& exConstruct.equals(this.construct)
					&& exNumber.equals(this.number)) {
				this.inflectedItem = ex.getTransliterated();
				this.surface = ex.getUndotted();
				this.register = RegisterType
						.fromValue(ex.getRegister().value());
				this.spelling = SpellingType
						.fromValue(ex.getSpelling().value());
				this.populateDatabase();
				match = true;
				break;
			}
		}
		return match;
	}

	private void setAttributes(final GenderType gender,
			final NumberType number, final TriStateType construct,
			final DefinitenessType definitnessVal, final String inflectedItem,
			final String PGN, final SuffixFunctionType suffixFunction) {
		this.inflectedItem = inflectedItem;
		this.number = number;
		this.gender = gender;
		this.construct = construct;
		this.definitnessVal = definitnessVal;
		this.PGN = PGN;
		this.suffixFunction = suffixFunction;
	}
}
