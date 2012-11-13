package org.mila.generator.generation;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;

import org.mila.entities.corpus.DefinitenessType;
import org.mila.entities.corpus.GenderType;
import org.mila.entities.corpus.NumberType;
import org.mila.entities.corpus.RegisterType;
import org.mila.entities.corpus.SpellingType;
import org.mila.entities.corpus.SuffixFunctionType;
import org.mila.entities.corpus.TriStateType;
import org.mila.entities.inflections.Inflection;
import org.mila.entities.lexicon.AdjectiveLexicon;
import org.mila.entities.lexicon.Feminine;
import org.mila.entities.lexicon.Gender;
import org.mila.entities.lexicon.Item;
import org.mila.entities.lexicon.NounException;
import org.mila.entities.lexicon.NounExceptionAdd;
import org.mila.entities.lexicon.Number;
import org.mila.entities.lexicon.Plural;
import org.mila.entities.lexicon.ThreeState;
import org.mila.generator.utils.Transliteration;

public class AdjectiveGen extends ItemGen {
	/**
	 * @author dalia bojan Created on 13/07/2005 This class handles Adjective
	 *         Generation <br>
	 *         Generation is performed for: <br>
	 *         Lexicon item of pos adjective <br>
	 *         Lexicon item exception action = add (if exist) <br>
	 * 
	 *         For each generated lexicon item (not thos items generated from
	 *         add exception items) we need to check <br>
	 *         whethere there exist a remove or replace exception lexicon item <br>
	 * 
	 *         Generation activities: <br>
	 *         original lexicon item <br>
	 *         h+ original lexicon item <br>
	 *         construct form of the original lexicon item <br>
	 *         feminine form of the original lexicon item <br>
	 *         h+ feminine form of the original lexicon item <br>
	 *         plural form of the original lexicon item <br>
	 *         h+ plural form of the original lexicon item <br>
	 *         construct form of feminine form <br>
	 *         plural form of feminine form <br>
	 *         construct form of plural form <br>
	 *         plural form of plural form <br>
	 * 
	 *         The generation done based on rules (rules1 table on generator
	 *         databse) <br>
	 * 
	 */

	// private int pluralSuffixMaxLength;
	private String feminineBase = "";

	private String pluralBase = "";

	private boolean exceptionInflectConstructS = false;

	private boolean exceptionInflectConstructP = false;

	boolean doebleVavHandling = false;

	private boolean inflectConstructS = true;

	private boolean inflectConstructP = true;

	private AdjectiveLexicon adjective;

	public AdjectiveGen(Item item, EntityManager lexicon,
			EntityManager generator, EntityManager inflections) {
		super(item, lexicon, generator, inflections);
		this.adjective = (AdjectiveLexicon) item.getSubitem();
	}

	/**
	 * This method gets the exception item list of action add for the lexicon
	 * item
	 * 
	 * @
	 */
	protected void inflectAddExceptions() {
		AdjectiveLexicon adj = (AdjectiveLexicon) this.item.getSubitem();
		List<NounExceptionAdd> list = new ArrayList<NounExceptionAdd>();
		for (NounException ex : adj.getExceptions()) {
			if (ex instanceof NounExceptionAdd) {
				list.add((NounExceptionAdd) ex);
			}
		}
		if (list.size() > 0) {
			analyseExceptionList(list);
		}
	}

	/**
	 * This method is used for handling exceptions for which action = add <br>
	 * This method scan the add exception list - gets the attributes and
	 * populate the databse Each item from the add exception is inflected <br>
	 * That is - we generate the feminine, plural construct, possessive forms
	 * for it
	 * 
	 * @param exceptionList
	 *            @
	 */
	private void analyseExceptionList(List<NounExceptionAdd> exceptionList) {
		for (NounExceptionAdd ex : exceptionList) {
			inflectedItem = ex.getTransliterated();
			// inflectedItem = inflectedItem.replaceAll("&#39;", "'");
			surface = ex.getUndotted();
			gender = GenderType.fromValue((lexiconGender = ex.getGender())
					.value());
			plural = ex.getPlural();
			number = NumberType.fromValue((lexiconNumber = ex.getNumber())
					.value());
			register = RegisterType.fromValue(ex.getRegister().value());
			spelling = SpellingType.fromValue(ex.getSpelling().value());
			feminine = ex.getFeminine();
			construct = TriStateType.fromValue(ex.getConstruct().value());
			definitnessVal = DefinitenessType.FALSE;
			// populate the databse with the exception (add) lexicon item
			populateDatabase();
			String exceptionBase = inflectedItem;

			// shlomo doesn't inflect it and should be looked more carefully
			if (lexiconNumber == Number.DUAL)
				return;

			// setting flags: should we generate construct for the exception
			// lexicon item
			exceptionInflectConstructS = ex.isInflectConstructS();
			exceptionInflectConstructP = ex.isInflectConstructP();

			// We use the same generation methods for the lexicon item and for
			// the exception lexicon item
			// replaceExceptionList is not relevant for the exception type
			// lexicon item (action add)
			inflectAddException(exceptionBase);
			if (exceptionBase.startsWith("w")
					&& !exceptionBase.startsWith("ww")) {
				exceptionBase = "w" + exceptionBase;
				surface = "ו" + ex.getUndotted();
				inflectAddException(exceptionBase);
			}

		}
	}

	private void inflectAddException(String exceptionBase) {

		String exceptionFeminineBase = "";
		String exceptionPluralBase = "";

		if (!(construct == TriStateType.TRUE) && exceptionPluralBase.equals("")) {
			setAttributes(lexiconGender, lexiconNumber, TriStateType.FALSE,
					DefinitenessType.TRUE, exceptionBase);
			addH();
		}
		// exception type lexicon item has two attributes relevant for construct
		// generation
		// 1) a flag indicating whether there is a construct form for this item
		// 2) construct attribute pointing out if the current excpetion item
		// is the construct form

		// 17/01/06 - if singular adjective ends with i - don't generate
		// construct
		if (exceptionInflectConstructS && construct == TriStateType.FALSE
				&& (exceptionBase.charAt(exceptionBase.length() - 1) != 'i')
				&& lexiconNumber == Number.SINGULAR) {
			setAttributes(lexiconGender, lexiconNumber, TriStateType.TRUE,
					DefinitenessType.FALSE, exceptionBase);
			generateConstruct();
		}

		if (lexiconGender == Gender.MASCULINE
				&& feminine != Feminine.UNSPECIFIED) {
			setAttributes(Gender.FEMININE, lexiconNumber, TriStateType.FALSE,
					DefinitenessType.FALSE, exceptionBase);
			generateFeminine();
			exceptionFeminineBase = inflectedItem;
			setAttributes(Gender.FEMININE, lexiconNumber, TriStateType.FALSE,
					DefinitenessType.TRUE, exceptionFeminineBase);
			addH();
		}

		if (plural != Plural.UNSPECIFIED) {
			setAttributes(lexiconGender, Number.SINGULAR, TriStateType.FALSE,
					DefinitenessType.FALSE, exceptionBase);
			generatePlural(plural);
			exceptionPluralBase = inflectedItem;
			setAttributes(lexiconGender, Number.SINGULAR, TriStateType.FALSE,
					DefinitenessType.TRUE, exceptionPluralBase);
			addH();
		}

		// ///////////////////////////////////////////////////////////
		// Feminine form inflections: plural,construct,possessive
		// ///////////////////////////////////////////////////////////

		if (!exceptionFeminineBase.equals("")) {

			if (exceptionInflectConstructS) {
				setAttributes(Gender.FEMININE, Number.SINGULAR,
						TriStateType.TRUE, DefinitenessType.FALSE,
						exceptionFeminineBase);
				generateConstruct();
			}

			setAttributes(Gender.FEMININE, Number.SINGULAR, TriStateType.FALSE,
					DefinitenessType.FALSE, exceptionFeminineBase);
			generatePlural(Plural.WT);
			String exceptionPluralFeminineBase = inflectedItem;

			setAttributes(Gender.FEMININE, Number.SINGULAR, TriStateType.FALSE,
					DefinitenessType.TRUE, exceptionPluralFeminineBase);
			addH();

			if (exceptionInflectConstructP) {
				setAttributes(Gender.FEMININE, Number.SINGULAR,
						TriStateType.TRUE, DefinitenessType.FALSE,
						exceptionPluralFeminineBase);
				generateConstruct();
			}

		}

		// //////////////////////////////////////////////////////////
		// pluralBase inflections: construct, plural
		// /////////////////////////////////////////////////////////
		if (!exceptionPluralBase.equals("")) {
			if (number == NumberType.SINGULAR && exceptionInflectConstructP) {
				setAttributes(lexiconGender, Number.SINGULAR,
						TriStateType.TRUE, DefinitenessType.FALSE,
						exceptionPluralBase);
				generateConstruct();
			}
		}
	}

	/**
	 * This method is called before populating the database with a generated
	 * item <br>
	 * It check whether there is a remove/replace exception item for the current
	 * lexicon item <br>
	 * If there is a replace item - it will populate the database instead of the
	 * generated item <br>
	 * If there is a remove item - the generated item will not be inserted to
	 * the database <br>
	 * The method compares the remove/replace exception item gender and number
	 * with <br>
	 * the generated item gender,number - if there is an equality <br>
	 * the generated item is replaced/removed
	 * 
	 * @param gender
	 *            - gender value of lexicon item
	 * @param number
	 *            - number value of lexicon item
	 * @param construct
	 *            - construct value of lexicon item
	 * @param exceptionList
	 *            - remove/replace exception list
	 * @param action
	 *            - remove/replace
	 * @return @
	 */
	private boolean replaceRemoveException(GenderType gender_,
			NumberType number_, TriStateType construct_,
			List<NounException> exceptionList, String action) {
		boolean match = false;
		Gender gender = Gender.fromValue(gender_.value());
		Number number = Number.fromValue(number_.value());
		ThreeState construct = ThreeState.fromValue(construct_.value());
		if (exceptionList != null) {
			for (NounException ex : exceptionList) {
				Gender exceptionGender = ex.getGender();
				Number exceptionNumber = ex.getNumber();
				ThreeState exceptionConstruct = ex.getConstruct();

				if ((exceptionGender == gender)
						&& (exceptionNumber == number)
						&& (exceptionConstruct == construct
								|| (exceptionConstruct == ThreeState.FALSE && construct == ThreeState.UNSPECIFIED) || (exceptionConstruct == ThreeState.UNSPECIFIED && construct == ThreeState.FALSE))) {
					if (action.equals("replace")) {
						// System.out.println("exception action = replace ");
						if (doebleVavHandling
								&& !ex.getTransliterated().startsWith("ww"))
							inflectedItem = "w" + ex.getTransliterated();
						else
							inflectedItem = ex.getTransliterated();
						surface = Transliteration.toHebrew(inflectedItem);
						// System.out.println("exception transliterated  ="+
						// inflectedItem);
						populateDatabase();
						match = true;
						// System.out.println("----------------------------");
						return match;
					} else if (action.equals("remove")) {
						// System.out.println("exception action = remove ");
						match = true;
						// System.out.println("----------------------------");
						return match;
					}
				}
			}
		}
		// System.out.println("----------------------------");
		return match;
	}

	/**
	 * This method gets the lexicon item attributes values
	 */
	private void analyzeAdjective() {
		super.analyseItem();
		AdjectiveLexicon adj = (AdjectiveLexicon) item.getSubitem();
		plural = adj.getPlural();
		gender = GenderType
				.fromValue((lexiconGender = adj.getGender()).value());
		feminine = adj.getFeminine();
		number = NumberType
				.fromValue((lexiconNumber = adj.getNumber()).value());
		suffixFunction = SuffixFunctionType.UNSPECIFIED;
		basePos = "adjective";
		surface = undot;
		inflectedItem = transliterated;
		construct = TriStateType.FALSE;
		definitnessVal = DefinitenessType.FALSE;
		PGN = "unspecified";
		inflectConstructS = adj.isInflectConstructS();
		inflectConstructP = adj.isInflectConstructP();
		hebForeign = adj.isForeignSource();
	}

	private void handleDoublingVavForAdjectivesStartsWithVav() {
		analyzeAdjective();
		if (transliterated.startsWith("w") && !transliterated.startsWith("ww")) {
			// System.out.println("|||||||||||||||||||||||||||||||||||||");
			// System.out.println("||||||||||double VAV for nouns starts with single VAV||||||||||||");

			inflectLexiconItem();
			analyzeAdjective();
			transliterated = "w" + transliterated;
			surface = Transliteration.toHebrew(transliterated);
			inflectedItem = transliterated;
			register = RegisterType.FORMAL;
			spelling = SpellingType.STANDARD;
			pluralBase = "";
			doebleVavHandling = true;
			inflectLexiconItem();
		} else
			inflectLexiconItem();
	}

	private void generateConstruct() {
		// System.out.println("|||||||||||||||||||||||||||||||||||||");
		// System.out.println("||||||||||generateConstruct||||||||||||");

		if (!replaceRemoveException(gender, number, TriStateType.TRUE,
				adjective.getExceptions(), "replace")) {
			// System.out.println("No exception handling");
			if (number == NumberType.SINGULAR)
				// in noun rules file it refers to feminine as well - it's a
				// mistake
				findRule(inflectedItem, "", "constructMasculinePlural"
						+ basePos, 2);
			else if (gender == GenderType.FEMININE
					|| gender == GenderType.MASCULINE_AND_FEMININE)
				findRule(inflectedItem, "", "constructFeminineSingular"
						+ basePos, 2);
			// deny problems of translation for אמ"נ and אמ"ן פילטרופ סירופ
			if (inflectedItem.endsWith("n") || inflectedItem.endsWith("p")
					|| inflectedItem.endsWith("c")
					&& inflectedItem.equals(transliterated))
				surface = undot;
			else
				surface = Transliteration.toHebrew(inflectedItem);
			populateDatabase();

		}
	}

	private void generatePlural(Plural plural) {
		// System.out.println("|||||||||||||||||||||||||||||||||||||");
		// System.out.println("||||||||||generatePlural||||||||||||");
		String action = "";

		if (!replaceRemoveException(gender, NumberType.PLURAL,
				TriStateType.UNSPECIFIED, adjective.getExceptions(), "replace")) {
			action = identifyPluralAction(plural);
			if (!action.equals("")) {
				// System.out.println("plural base = " + inflectedItem);
				findRule(inflectedItem, plural.value(), action,
						pluralSuffixMaxLength);
				// System.out.println("plural inflectedItem = " +
				// inflectedItem);
				surface = Transliteration.toHebrew(inflectedItem);
				populateDatabase();
				if (pluralBase.equals(""))
					pluralBase = inflectedItem;
			}
		} else {
			if (pluralBase.equals(""))
				pluralBase = inflectedItem;
		}
	}

	private void generateFeminine() {
		if (!replaceRemoveException(GenderType.FEMININE, number,
				TriStateType.UNSPECIFIED, adjective.getExceptions(), "replace")) {
			String action = identifyFeminineAction();
			findRule(inflectedItem, feminine.value(), action, 1);
			surface = Transliteration.toHebrew(inflectedItem);
			populateDatabase();
		}
	}

	public List<Inflection> inflect() {
		// Get lexicon Item (for which pos = adjective ) attributes
		// The lexicon item can have any attributes for example
		// single masculine/single feminine/ plural feminine etc
		analyzeAdjective();
		handleDoublingVavForAdjectivesStartsWithVav();

		// inflect exception type items of action add - if exist
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
	 * @param suffixFunction
	 *            -A flag indication whether a possessive actin is done
	 */
	private void setAttributes(Gender gender,
			org.mila.entities.lexicon.Number number, TriStateType construct,
			DefinitenessType definitnessVal, String inflectedItem) {
		this.inflectedItem = inflectedItem;
		this.number = NumberType.fromValue(number.value());
		this.gender = GenderType.fromValue(gender.value());
		this.construct = construct;
		this.definitnessVal = definitnessVal;
		this.PGN = "unspecified";
		this.suffixFunction = SuffixFunctionType.UNSPECIFIED;
	}

	/**
	 * This method is used by noun, adjective, quantifier,pronoun <br>
	 * It is used to generate h+inflectedItem because of special handling for
	 * adjective starts with ww - we don't use the function from itemGen
	 * 
	 * @
	 */
	protected void addH() {
		SpellingType originalSpelling = spelling;
		String origInflectedItem = inflectedItem;
		// System.out.println("|||||||||||||||||||||||||||||||||||||");
		// System.out.println("||||||||||generateHForm||||||||||||");
		// /////////////////////////////////////////////////////////////////
		if (inflectedItem.startsWith("w") && !inflectedItem.startsWith("ww"))
			spelling = SpellingType.IRREGULAR;
		else if (inflectedItem.startsWith("ww"))
			spelling = SpellingType.STANDARD;
		// ////////////////////////////////////////////////////////////////
		inflectedItem = "h" + inflectedItem;
		surface = "ה" + surface;

		populateDatabase();

		inflectedItem = origInflectedItem;
		spelling = originalSpelling;
	}

	private void inflectLexiconItem() {
		populateDatabase();

		setAttributes(lexiconGender, lexiconNumber, TriStateType.FALSE,
				DefinitenessType.TRUE, transliterated);
		addH();

		// 17/01/06 - if singular adjective ends with i - don't generate
		// construct
		if ((lexiconNumber == Number.SINGULAR && inflectConstructS)
				&& ((transliterated.charAt(transliterated.length() - 1)) != 'i')) {
			setAttributes(lexiconGender, lexiconNumber, TriStateType.TRUE,
					DefinitenessType.UNSPECIFIED, transliterated);
			generateConstruct();
		}

		if (lexiconGender == Gender.MASCULINE
				&& feminine != Feminine.UNSPECIFIED) {
			setAttributes(Gender.FEMININE, lexiconNumber, TriStateType.FALSE,
					DefinitenessType.FALSE, transliterated);
			generateFeminine();
			feminineBase = inflectedItem;
			setAttributes(Gender.FEMININE, lexiconNumber, TriStateType.FALSE,
					DefinitenessType.TRUE, feminineBase);
			addH();
		}

		if (plural != Plural.UNSPECIFIED && lexiconNumber != Number.PLURAL) {
			setAttributes(lexiconGender, Number.SINGULAR, TriStateType.FALSE,
					DefinitenessType.FALSE, transliterated);
			generatePlural(plural);
			setAttributes(lexiconGender, Number.SINGULAR, TriStateType.FALSE,
					DefinitenessType.TRUE, inflectedItem);
			addH();
		}

		/* Feminine form inflections: plural,construct */

		if (!feminineBase.equals("")) {
			if (inflectConstructS) {
				setAttributes(Gender.FEMININE, Number.SINGULAR,
						TriStateType.TRUE, DefinitenessType.UNSPECIFIED,
						feminineBase);
				generateConstruct();
			}

			setAttributes(Gender.FEMININE, Number.SINGULAR, TriStateType.FALSE,
					DefinitenessType.FALSE, feminineBase);
			generatePlural(Plural.WT);

			String pluralFeminineBase = inflectedItem;

			setAttributes(Gender.FEMININE, Number.PLURAL, TriStateType.FALSE,
					DefinitenessType.TRUE, pluralFeminineBase);
			addH();

			if (inflectConstructP) {
				setAttributes(Gender.FEMININE, Number.PLURAL,
						TriStateType.TRUE, DefinitenessType.UNSPECIFIED,
						pluralFeminineBase);
				generateConstruct();
			}
		}

		/* plural form inflections: construct */

		if (!pluralBase.equals("") && inflectConstructP) {
			setAttributes(lexiconGender,
					org.mila.entities.lexicon.Number.PLURAL, TriStateType.TRUE,
					DefinitenessType.UNSPECIFIED, pluralBase);
			generateConstruct();
		}

	}

}
