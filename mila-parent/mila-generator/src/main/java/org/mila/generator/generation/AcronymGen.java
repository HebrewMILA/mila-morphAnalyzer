package org.mila.generator.generation;

import java.util.List;

import javax.persistence.EntityManager;

import org.mila.entities.corpus.DefinitenessType;
import org.mila.entities.corpus.GenderType;
import org.mila.entities.corpus.NumberType;
import org.mila.entities.corpus.RegisterType;
import org.mila.entities.corpus.SpellingType;
import org.mila.entities.corpus.SuffixFunctionType;
import org.mila.entities.corpus.TriStateType;
import org.mila.entities.inflections.PersistableInflection;
import org.mila.entities.lexicon.AcronymDefiniteness;
import org.mila.entities.lexicon.AcronymException;
import org.mila.entities.lexicon.AcronymExceptionAdd;
import org.mila.entities.lexicon.AcronymLexicon;
import org.mila.entities.lexicon.Feminine;
import org.mila.entities.lexicon.Gender;
import org.mila.entities.lexicon.Item;
import org.mila.entities.lexicon.NamedEntity;
import org.mila.entities.lexicon.NoInvertedCommas;
import org.mila.entities.lexicon.Number;
import org.mila.entities.lexicon.Plural;
import org.mila.generator.utils.Transliteration;

public class AcronymGen extends ItemGen {
	/*
	 * String lexiconFemininePlural = "";
	 * 
	 * String lexiconMasculinePluralConstruct = "";
	 * 
	 * String lexiconFeminineSingularConstruct = "";
	 * 
	 * String lexiconMasculineSingularConstruct = "";
	 * 
	 * String lexiconFemininePluralConstruct = "";
	 */

	private String pluralBase = "";

	private boolean inflectConstructS = true;

	private boolean inflectConstructP = true;

	private AcronymDefiniteness definiteness = AcronymDefiniteness.NONE;

	protected AcronymLexicon acronym = null;

	public AcronymGen(Item item, EntityManager lexicon,
			EntityManager generator, EntityManager inflections) {
		super(item, lexicon, generator, inflections);
		acronym = (AcronymLexicon) item.getSubitem();
	}

	/**
	 * This method gets the lexicon item attributes values
	 */
	private void analyzeAcronym() {
		super.analyseItem();
		plural = acronym.getPlural();
		System.out.println("plural=" + plural);
		gender = GenderType.fromValue((lexiconGender = acronym.getGender())
				.value());
		feminine = acronym.getFeminine();
		System.out.println("feminine=" + feminine);
		number = NumberType.fromValue((lexiconNumber = acronym.getNumber())
				.value());
		System.out.println("number=" + number);
		value = acronym.getMeaning();
		suffixFunction = SuffixFunctionType.UNSPECIFIED;
		basePos = acronym.getPos().value();
		surface = undot;
		inflectedItem = transliterated;
		construct = TriStateType.FALSE;
		definiteness = acronym.getDefiniteness();
		if (definiteness == AcronymDefiniteness.EXTERNALLY_DEFINITED)
			definitnessVal = DefinitenessType.FALSE;
		PGN = "unspecified";
		inflectConstructS = acronym.isInflectConstructS();
		inflectConstructP = acronym.isInflectConstructP();
		if (basePos.equals("title")) {
			inflectConstructS = false;
			inflectConstructP = false;
		}
		if (basePos.equals("conjunction"))
			type = acronym.getConjunctionType().value();
		else if (basePos.equals("propername"))
			type = acronym.getProperNameType().value();
		else
			type = "unspecified";

	}

	private boolean generateConstruct() {
		// System.out.println("|||||||||||||||||||||||||||||||||||||");
		// System.out.println("||||||||||generateConstruct||||||||||||");
		boolean returnVal = false;

		System.out.println(number);
		System.out.println(gender);
		if (inflectConstructP && number == NumberType.PLURAL) {
			findRule(inflectedItem, "", "constructMasculinePlural" + basePos, 2);
			// handle גדי
			if (gender == GenderType.MASCULINE
					&& baseTransliteratedItem.endsWith("i")
					&& !baseTransliteratedItem.endsWith("ai")
					// handle שה
					|| (baseTransliteratedItem.endsWith("h") && baseTransliteratedItem
							.length() == 2))
				inflectedItem = inflectedItem + "i";
			if (inflectedItem.equals(transliterated))
				surface = undot;
			else
				surface = Transliteration.toHebrew(inflectedItem);
			returnVal = true;
			populateDatabase();

		} else if (inflectConstructS
				&& (gender == GenderType.FEMININE || gender == GenderType.MASCULINE_AND_FEMININE)
				&& number == NumberType.SINGULAR) {
			findRule(inflectedItem, "", "constructFeminineSingular" + basePos,
					2);

			// //deny problems of translation for אמ"נ and אמ"ן פילטרופ
			// סירופ
			if (inflectedItem.endsWith("n") || inflectedItem.endsWith("p")
					|| inflectedItem.endsWith("c")
					&& inflectedItem.equals(transliterated))
				surface = undot;
			else
				surface = Transliteration.toHebrew(inflectedItem);
			returnVal = true;
			populateDatabase();
		} else if (inflectConstructS && gender == GenderType.MASCULINE
				&& number == NumberType.SINGULAR) {
			// //deny problems of translation for אמ"נ and אמ"ן פילטרופ
			// סירופ
			if ((inflectedItem.endsWith("n") || inflectedItem.endsWith("p") || inflectedItem
					.endsWith("c")) && inflectedItem.equals(transliterated))
				surface = undot;
			else
				surface = Transliteration.toHebrew(inflectedItem);
			returnVal = true;
			populateDatabase();
		}
		return returnVal;
	}

	private void generatePlural(Plural plural) {
		// System.out.println("|||||||||||||||||||||||||||||||||||||");
		// System.out.println("||||||||||generatePlural||||||||||||");
		// boolean returnVal = true;
		String action = "";

		action = identifyPluralAction(plural);
		if (!action.equals("")) {
			// System.out.println("plural base = " + inflectedItem);
			if (inflectedItem.endsWith("th"))
				findRule(inflectedItem, "th", action, pluralSuffixMaxLength);
			else
				findRule(inflectedItem, plural.value(), action,
						pluralSuffixMaxLength);
			// System.out.println("plural inflectedItem = " + inflectedItem);
			surface = Transliteration.toHebrew(inflectedItem);
			// if (gender.equals("feminine")
			// && !lexiconFemininePlural.equals("")
			// && lexiconFemininePlural.equals(inflectedItem))
			// returnVal = false;
			// else {
			// if (gender.equals("feminine")
			// && lexiconFemininePlural.equals(""))
			// lexiconFemininePlural = inflectedItem;
			populateDatabase();
			// }
			if (pluralBase.equals(""))
				pluralBase = inflectedItem;
		}

		// return returnVal;

	}

	private void generateFeminine() {
		String action = identifyFeminineAction();
		findRule(inflectedItem, feminine.value(), action, 1);
		surface = Transliteration.toHebrew(inflectedItem);
		populateDatabase();

	}

	/**
	 * This method gets the exception item list of action add for the lexicon
	 * item
	 * 
	 * @throws Exception
	 */
	protected void inflectAddExceptions() {
		analyseExceptionList(acronym.getExceptions());
	}

	/**
	 * This method is used for handling exceptions for which action = add <br>
	 * This method scan the add exception list - gets the attributes and
	 * populate the databse Each item from the add exception is inflected <br>
	 * That is - we generate the feminine, plural construct, possessive forms
	 * for it
	 * 
	 * @param exceptionList
	 * @throws Exception
	 */
	private void analyseExceptionList(List<AcronymException> exceptionList) {
		for (AcronymException exception : exceptionList) {
			if (!(exception instanceof AcronymExceptionAdd)) {
				continue;
			}
			inflectedItem = exception.getTransliterated();
			// inflectedItem = inflectedItem.replaceAll("&#39;", "'");
			surface = exception.getUndotted();
			gender = GenderType.fromValue((lexiconGender = exception
					.getGender()).value());
			number = NumberType.fromValue((lexiconNumber = exception
					.getNumber()).value());
			register = RegisterType.fromValue(exception.getRegister().value());
			spelling = SpellingType.fromValue(exception.getSpelling().value());
			type = exception.getProperNameType().value();
			if (exception.getProperNameType() == NamedEntity.UNSPECIFIED) {
				type = exception.getConjunctionType().value();
			}
			definitnessVal = DefinitenessType.FALSE;
			construct = TriStateType.FALSE;

			populateDatabase();

			String exceptionBase = inflectedItem;
			if (definiteness == AcronymDefiniteness.EXTERNAL
					|| definiteness == AcronymDefiniteness.INTERNAL_AND_EXTERNAL) {
				setAttributes(lexiconGender, lexiconNumber, TriStateType.FALSE,
						DefinitenessType.TRUE, exceptionBase);
				addH();
			}
		}
	}

	public List<PersistableInflection> inflect() {
		analyzeAcronym();
		inflectLexiconItem();
		inflectAddExceptions();
		return this.getGeneratedInflections();
	}

	/**
	 * This is a service method which is called before every generation
	 * operation. The attributes are changed for each generation
	 * 
	 * @param gender
	 *            - the gender attribute value of the generated item
	 * @param number
	 *            - the number attribute value of the generated item
	 * @param construct
	 *            - true/false/unspecified value for the generated item
	 * @param definitnessVal
	 *            - This attribute value is a combination of <br>
	 *            the lexicon item baseDefinitness and the generated item base
	 *            definitness <br>
	 *            All nouns can be added h - so the lexiocn item base
	 *            definitness id t(true) <br>
	 *            The generated item can appear with added h (t) and without (f) <br>
	 *            The relevant values are : tt, tf
	 * @param inflectedItem
	 *            - The base form on which the next generation action will work <br>
	 *            For example - it is important that creating possessive will
	 *            work on the construct form
	 * @param PGN
	 *            - person/gender/number value of the generated item
	 * @param suffixFunction
	 *            -A flag indication whether a possessive actin is done
	 */
	private void setAttributes(Gender gender, Number number,
			TriStateType construct, DefinitenessType definitnessVal,
			String inflectedItem) {
		this.inflectedItem = inflectedItem;
		this.number = NumberType.fromValue(number.value());
		this.gender = GenderType.fromValue(gender.value());
		this.construct = construct;
		this.definitnessVal = definitnessVal;
		/*
		 * this.PGN = PGN; this.suffixFunction = suffixFunction;
		 */
	}

	/**
	 * This method is used by noun, adjective, quantifier,pronoun <br>
	 * It is used to generate h+inflectedItem because of special handling for
	 * nouns starts with ww - we don't use the function from itemGen
	 * 
	 * @throws Exception
	 */
	protected void addH() {

		String origInflectedItem = inflectedItem;
		// System.out.println("|||||||||||||||||||||||||||||||||||||");
		// System.out.println("||||||||||generateHForm||||||||||||");

		inflectedItem = "h" + inflectedItem;
		surface = "ה" + surface;

		populateDatabase();
		removeInvertedCommas();

		inflectedItem = origInflectedItem;

	}

	protected void generateInternalDefiniteness() {
		inflectedItem = inflectedItem.replaceAll("\"", "h\"");
		surface = Transliteration.toHebrew(inflectedItem);
		value = value.replaceAll(" ", " ה");
		populateDatabase();
		inflectedItem = inflectedItem.replaceAll("h\"", "\"");
		surface = Transliteration.toHebrew(inflectedItem);
		value = value.replaceAll(" ה", " ");
	}

	protected void removeInvertedCommas() {
		NoInvertedCommas noInvertedCommas = acronym.getNoInvertedCommas();
		if (noInvertedCommas == NoInvertedCommas.ALL
				|| noInvertedCommas == NoInvertedCommas.MASCULINE_SINGULAR
				&& gender == GenderType.MASCULINE
				&& number == NumberType.SINGULAR
				|| noInvertedCommas == NoInvertedCommas.MASCULINE_PLURAL
				&& gender == GenderType.MASCULINE
				&& number == NumberType.PLURAL
				|| noInvertedCommas == NoInvertedCommas.FEMININE_PLURAL
				&& gender == GenderType.FEMININE && number == NumberType.PLURAL
				|| noInvertedCommas == NoInvertedCommas.FEMININE_SINGULAR
				&& gender == GenderType.FEMININE
				&& number == NumberType.SINGULAR
				|| noInvertedCommas == NoInvertedCommas.ALL_SINGULAR
				&& number == NumberType.SINGULAR
				|| noInvertedCommas == NoInvertedCommas.ALL_PLURALL
				&& number == NumberType.PLURAL) {
			String origInflectedItem = inflectedItem;
			String origSurface = surface;
			inflectedItem = inflectedItem.replaceAll("\"", "");
			inflectedItem = inflectedItem.replaceAll("'", "");
			surface = surface.replaceAll("\"", "");
			surface = surface.replaceAll("'", "");
			populateDatabase();
			inflectedItem = origInflectedItem;
			surface = origSurface;
		}
	}

	private void inflectLexiconItem() {
		String feminineBase = "";
		populateDatabase();

		removeInvertedCommas();

		switch (definiteness) {
		case INTERNAL:

		case INTERNAL_AND_EXTERNAL:
			setAttributes(lexiconGender, lexiconNumber, TriStateType.FALSE,
					DefinitenessType.TRUE, transliterated);
			generateInternalDefiniteness();
			if (definiteness == AcronymDefiniteness.INTERNAL)
				break;

		case EXTERNAL:
			setAttributes(lexiconGender, lexiconNumber, TriStateType.FALSE,
					DefinitenessType.TRUE, transliterated);

			addH();
		default:
			break;
		}

		// Set attributes before generating the construct form

		setAttributes(lexiconGender, lexiconNumber, TriStateType.TRUE,
				DefinitenessType.FALSE, transliterated);
		if (generateConstruct())
			removeInvertedCommas();

		// The lexicon item can have any attributes values
		// If the lexicon item is already feminine or there is no feminine
		// form
		// we will not generate the feminine form - so the attributes need
		// to be
		// checked
		if (lexiconGender == Gender.MASCULINE
				&& !(feminine == Feminine.UNSPECIFIED)) {
			setAttributes(Gender.FEMININE, lexiconNumber, TriStateType.FALSE,
					DefinitenessType.FALSE, transliterated);
			generateFeminine();
			removeInvertedCommas();
			feminineBase = inflectedItem;
		}

		// The lexicon item can have any attributes values
		// If the lexicon item is plural or there is no plural form of it
		// we will not generate the plural form - so the attributes need to
		// be
		// checked
		if (!(plural == Plural.UNSPECIFIED)
				&& (lexiconNumber == Number.SINGULAR)) {
			setAttributes(lexiconGender, Number.PLURAL, TriStateType.FALSE,
					DefinitenessType.FALSE, transliterated);
			generatePlural(plural);
			removeInvertedCommas();
		}

		// ///////////////////////////////////////////////////////////
		// Feminine form inflections: plural,construct,possessive
		// ///////////////////////////////////////////////////////////

		// Inflecting the single feminine form
		if (!feminineBase.equals("")) {
			// System.out.println("**************************************");
			// System.out.println("**********feminine form inflections**********");

			surface = Transliteration.toHebrew(feminineBase);

			if (definiteness == AcronymDefiniteness.EXTERNAL
					|| definiteness == AcronymDefiniteness.INTERNAL_AND_EXTERNAL) {
				setAttributes(Gender.FEMININE, lexiconNumber,
						TriStateType.FALSE, DefinitenessType.TRUE, feminineBase);
				addH();
			}

			setAttributes(Gender.FEMININE, Number.SINGULAR, TriStateType.TRUE,
					DefinitenessType.FALSE, feminineBase);
			if (generateConstruct())
				removeInvertedCommas();

			// ///////////////////////////////////////////////////////////////////////////

			surface = Transliteration.toHebrew(feminineBase);
			setAttributes(Gender.FEMININE, Number.PLURAL, TriStateType.FALSE,
					DefinitenessType.FALSE, feminineBase);
			generatePlural(Plural.WT);
			removeInvertedCommas();

			String pluralFeminineBase = inflectedItem;
			surface = Transliteration.toHebrew(pluralFeminineBase);

			if (definiteness == AcronymDefiniteness.EXTERNAL
					|| definiteness == AcronymDefiniteness.INTERNAL_AND_EXTERNAL) {
				setAttributes(Gender.FEMININE, Number.PLURAL,
						TriStateType.FALSE, DefinitenessType.TRUE,
						pluralFeminineBase);
				addH();
			}

			setAttributes(Gender.FEMININE, Number.PLURAL, TriStateType.TRUE,
					DefinitenessType.FALSE, pluralFeminineBase);
			if (generateConstruct())
				removeInvertedCommas();

		}

		// ///////////////////////////////////////////////////////////
		// plural form inflections: construct,possessive
		// ///////////////////////////////////////////////////////////

		// Inflecting the singular form
		if (!pluralBase.equals("") && !(plural == Plural.UNSPECIFIED)
				&& !(lexiconNumber == Number.PLURAL)) {
			// System.out.println("**************************************");
			// System.out.println("**********plural form inflections**********");
			// System.out.println("pluralBase = " + pluralBase);

			surface = Transliteration.toHebrew(pluralBase);
			// System.out.println("surface = " + surface);

			if (definiteness == AcronymDefiniteness.EXTERNAL
					|| definiteness == AcronymDefiniteness.INTERNAL_AND_EXTERNAL) {
				setAttributes(lexiconGender, Number.PLURAL, TriStateType.FALSE,
						DefinitenessType.TRUE, pluralBase);
				addH();
			}

			setAttributes(lexiconGender, Number.PLURAL, TriStateType.TRUE,
					DefinitenessType.FALSE, pluralBase);
			if (generateConstruct())
				removeInvertedCommas();

		}

	}
}
