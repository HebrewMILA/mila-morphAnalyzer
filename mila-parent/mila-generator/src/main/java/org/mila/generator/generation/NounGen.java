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
import org.mila.entities.inflections.PersistableInflection;
import org.mila.entities.lexicon.Feminine;
import org.mila.entities.lexicon.Gender;
import org.mila.entities.lexicon.Item;
import org.mila.entities.lexicon.NounException;
import org.mila.entities.lexicon.NounExceptionAdd;
import org.mila.entities.lexicon.NounExceptionRemove;
import org.mila.entities.lexicon.NounExceptionReplace;
import org.mila.entities.lexicon.NounLexicon;
import org.mila.entities.lexicon.Number;
import org.mila.entities.lexicon.Plural;
import org.mila.entities.lexicon.ThreeState;
import org.mila.generator.utils.Transliteration;

/**
 * @author dalia bojan Created on 13/07/2005 This class handles Noun Generation <br>
 *         Generation is performed for: <br>
 *         Lexicon item of pos noun <br>
 *         Lexicon item exception action = add (if exist) <br>
 *         For each generated lexicon item (not add exception type) we need to
 *         check <br>
 *         where there exist a remove or replace exception lexicon item <br>
 *         Generation activities: <br>
 *         original lexicon item <br>
 *         h+ original lexicon item <br>
 *         construct form of the original lexicon item <br>
 *         feminine form of the original lexicon item <br>
 *         h+ feminine form of the original lexicon item <br>
 *         plural form of the original lexicon item <br>
 *         h+ plural form of the original lexicon item <br>
 *         possessive form of the original lexicon item <br>
 *         construct form of feminine form <br>
 *         plural form of feminine form <br>
 *         possessive form of feminine form <br>
 *         construct form of plural form <br>
 *         plural form of plural form <br>
 *         possessive form of plural form <br>
 * 
 *         There are few cases on which the possessive is not done from the
 *         construct form <br>
 *         but from an attribute named inflectionBase <br>
 *         The generation done based on rules (rules1 table on generator
 *         database) <br>
 * 
 *         Noun generation is very similar to adjective generation with the
 *         following exceptions: <br>
 *         Possessive form is not created for adjective For Adjective lexicon
 *         items ends with i - we are not generating the construct form
 * 
 */
public class NounGen extends ItemGen {
	public NounGen(Item item, EntityManager lexicon, EntityManager generator,
			EntityManager inflections) {
		super(item, lexicon, generator, inflections);
		this.noun = (NounLexicon) item.getSubitem();
	}

	boolean doebleVavHandling = false;
	private String feminineBase = "";
	private String pluralBase = "";
	private boolean inflectExceptionConstructS = false;
	private boolean inflectExceptionPossessiveS = false;
	private boolean inflectExceptionConstructP = false;
	private boolean inflectExceptionPossessiveP = false;
	private boolean inflectConstructS = true;
	private boolean inflectPossessiveS = true;
	private boolean inflectConstructP = true;
	private boolean inflectPossessiveP = true;
	private boolean isDual = false;

	private String pluralFeminineBase = "";
	private NounLexicon noun;

	private boolean addExceptionListHandling(NounException ex) {
		gender = GenderType.fromValue((lexiconGender = ex.getGender()).value());
		number = NumberType.fromValue((lexiconNumber = ex.getNumber()).value());
		plural = ex.getPlural();

		if (!(ex.getTransliterated().startsWith("w") && inflectedItem
				.startsWith("ww")))
			spelling = SpellingType.fromValue(ex.getSpelling().value());
		register = RegisterType.fromValue(ex.getRegister().value());
		spelling = SpellingType.fromValue(ex.getSpelling().value());
		feminine = ex.getFeminine();
		PGN = ex.getPossessive();
		definitnessVal = DefinitenessType.TRUE;
		if (!PGN.equals("unspecified")) {
			suffixFunction = SuffixFunctionType.POSSESSIVE;
			construct = TriStateType.FALSE;

			populateDatabase();

			return false;
		} else
			suffixFunction = SuffixFunctionType.UNSPECIFIED;
		construct = TriStateType.fromValue(ex.getConstruct().value());
		if (construct == TriStateType.UNSPECIFIED)
			construct = TriStateType.FALSE;

		populateDatabase();

		String exceptionBase = inflectedItem;

		// shlomo doesn't inflect it and should be looked more carefully
		// if (lexiconNumber.equals("dual"))
		// return false;

		// setting flags: should we generate construct for the SINGULAR
		// exception
		// lexicon item
		inflectExceptionConstructS = ex.isInflectConstructS();

		// setting flags: should we generate possessive for the SINGULAR
		// exception
		// lexicon item
		inflectExceptionPossessiveS = ex.isInflectPossessiveS();

		// setting flags: should we generate construct for the PLURAL
		// exception
		// lexicon item
		inflectExceptionConstructP = ex.isInflectConstructP();

		// setting flags: should we generate possessive for the PLURAL
		// exception
		// lexicon item
		inflectExceptionPossessiveP = ex.isInflectPossessiveP();

		// We use the same generation methods for the lexicon item and for
		// the exception lexicon item
		// replaceExceptionList is not relevant for the exception type
		// lexicon item (action add)
		inflectAddException(exceptionBase);
		return true;
	}

	protected List<NounException> getRemoveExceptions() {
		return filter(Matchers.instanceOf(NounExceptionRemove.class),
				noun.getExceptions());
	}

	protected List<NounException> getReplaceExceptions() {
		return filter(Matchers.instanceOf(NounExceptionReplace.class),
				noun.getExceptions());
	}

	protected List<NounException> getAddExceptions() {
		return filter(Matchers.instanceOf(NounExceptionAdd.class),
				noun.getExceptions());
	}

	protected void addExceptions() {
		analyseAddExceptionList(this.getAddExceptions());
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
	private void analyseAddExceptionList(List<NounException> exceptions) {
		for (NounException ex : exceptions) {
			inflectedItem = ex.getTransliterated();
			// inflectedItem = inflectedItem.replaceAll("&#39;", "'");
			inflectedItem = inflectedItem.replaceAll("&#60;", "`");
			// System.out.println("inflectedItem=" + inflectedItem);
			// System.out.println("i=" + i);
			surface = ex.getUndotted();
			// suffixGender = nounExceptionType.getGender();
			addExceptionListHandling(ex);

			if (ex.getTransliterated().startsWith("w")
					&& !ex.getTransliterated().startsWith("ww")) {
				inflectedItem = "w" + ex.getTransliterated();
				// inflectedItem = inflectedItem.replaceAll("&#39;", "'");
				inflectedItem = inflectedItem.replaceAll("&#60;", "`");
				surface = "ו" + ex.getUndotted();
				spelling = SpellingType.IRREGULAR;
				addExceptionListHandling(ex);
			}
		}
	}

	/**
	 * This method is very similar to the inflectLexiconItem method <br>
	 * Still is has certain flags uniqe to exception type
	 * 
	 * @param exceptionBase
	 *            - lexicon exception item of action add
	 * @param exceptionGender
	 *            - gender value for lexicon exception item of action add
	 * @throws Exception
	 */

	private void inflectAddException(String exceptionBase) {
		String exceptionFeminineBase = "";
		String exceptionPluralBase = "";

		if (construct != TriStateType.TRUE && PGN.equals("unspecified")) {
			setAttributes(lexiconGender, lexiconNumber, ThreeState.FALSE,
					DefinitenessType.TRUE, exceptionBase, "unspecified",
					SuffixFunctionType.UNSPECIFIED);
			addH();
		}
		// exception type lexicon item has two attributes relevant for
		// construct
		// generation
		// 1) a flag indicating whether there is a construct form for this
		// item
		// 2) construct attribute pointing out if the current excpetion item
		// is the construct form

		if (inflectExceptionConstructS && construct == TriStateType.FALSE
				&& PGN.equals("unspecified")) {
			setAttributes(lexiconGender, lexiconNumber, ThreeState.TRUE,
					DefinitenessType.UNSPECIFIED, exceptionBase, "unspecified",
					SuffixFunctionType.UNSPECIFIED);
			generateConstruct();
		}

		if (inflectExceptionConstructP && construct == TriStateType.FALSE
				&& PGN.equals("unspecified")) {
			setAttributes(lexiconGender, Number.PLURAL, ThreeState.TRUE,
					DefinitenessType.UNSPECIFIED, exceptionBase, "unspecified",
					SuffixFunctionType.UNSPECIFIED);
			generateConstruct();
		}

		// exception type lexicon item has a flag indicating wether to
		// generate
		// the possessive forms

		// צורת הבסיס לקניין היא צורת הנסמך
		if (inflectExceptionConstructS && inflectExceptionPossessiveS
				&& lexiconNumber == Number.SINGULAR) {
			setAttributes(lexiconGender, lexiconNumber, ThreeState.FALSE,
					DefinitenessType.FALSE, inflectedItem, "unspecified",
					SuffixFunctionType.POSSESSIVE);
			generatePossessive();
		}

		if (inflectExceptionPossessiveP && lexiconNumber == Number.PLURAL) {
			setAttributes(lexiconGender, lexiconNumber, ThreeState.FALSE,
					DefinitenessType.FALSE, inflectedItem, "unspecified",
					SuffixFunctionType.POSSESSIVE);
			generatePossessive();
		}

		if (lexiconGender == Gender.MASCULINE
				&& feminine != Feminine.UNSPECIFIED) {
			setAttributes(Gender.FEMININE, lexiconNumber, ThreeState.FALSE,
					DefinitenessType.FALSE, exceptionBase, "unspecified",
					SuffixFunctionType.UNSPECIFIED);
			generateFeminine();
			exceptionFeminineBase = inflectedItem;
		}

		if (plural != Plural.UNSPECIFIED) {
			setAttributes(lexiconGender, Number.PLURAL, ThreeState.FALSE,
					DefinitenessType.FALSE, exceptionBase, "unspecified",
					SuffixFunctionType.UNSPECIFIED);
			generatePlural(plural);
			exceptionPluralBase = inflectedItem;
			// לא ייצר מידע ליוצא דופן רבים למשל יכטה
			addH();
		}
		// ///////////////////////////////////////////////////////////
		// Feminine form inflections: plural,construct,possessive
		// ///////////////////////////////////////////////////////////

		if (!exceptionFeminineBase.equals("")) {
			setAttributes(Gender.FEMININE, lexiconNumber, ThreeState.FALSE,
					DefinitenessType.TRUE, exceptionFeminineBase,
					"unspecified", SuffixFunctionType.UNSPECIFIED);
			addH();

			if (inflectExceptionConstructS) {
				setAttributes(Gender.FEMININE, Number.SINGULAR,
						ThreeState.TRUE, DefinitenessType.UNSPECIFIED,
						exceptionFeminineBase, "unspecified",
						SuffixFunctionType.UNSPECIFIED);
				generateConstruct();
			}

			if (inflectExceptionPossessiveS) {
				setAttributes(Gender.FEMININE, Number.SINGULAR,
						ThreeState.FALSE, DefinitenessType.FALSE,
						exceptionFeminineBase, "unspecified",
						SuffixFunctionType.POSSESSIVE);
				generatePossessive();
			}

			setAttributes(Gender.FEMININE, Number.PLURAL, ThreeState.FALSE,
					DefinitenessType.FALSE, exceptionFeminineBase,
					"unspecified", SuffixFunctionType.UNSPECIFIED);
			generatePlural(Plural.WT);
			// returnVal = generatePlural("wt");

			String exceptionPluralFeminineBase = inflectedItem;

			if (inflectExceptionConstructP) {
				setAttributes(Gender.FEMININE, Number.PLURAL, ThreeState.TRUE,
						DefinitenessType.UNSPECIFIED,
						exceptionPluralFeminineBase, "unspecified",
						SuffixFunctionType.UNSPECIFIED);
				generateConstruct();
			}

			if (inflectExceptionPossessiveP) {
				setAttributes(Gender.FEMININE, Number.PLURAL, ThreeState.FALSE,
						DefinitenessType.FALSE, exceptionPluralFeminineBase,
						"unspecified", SuffixFunctionType.POSSESSIVE);
				generatePossessive();
			}
		}
		// עיולים לבדיקה כשמשנים - יכטה, נוסחה
		// צוות , שדרוג
		// //////////////////////////////////////////////////////////
		// pluralBase inflections: construct, plural
		// /////////////////////////////////////////////////////////
		if (!exceptionPluralBase.equals("")) {
			// אם הצורה שהוכנסה ביוצאי הדופן אינה רבים ואנחנו ייצרנו אותה
			if (plural == Plural.UNSPECIFIED) {
				setAttributes(lexiconGender, Number.PLURAL, ThreeState.FALSE,
						DefinitenessType.TRUE, exceptionPluralBase,
						"unspecified", SuffixFunctionType.UNSPECIFIED);
				addH();
			}

			if (inflectExceptionConstructP) {
				setAttributes(lexiconGender, Number.PLURAL, ThreeState.TRUE,
						DefinitenessType.UNSPECIFIED, exceptionPluralBase,
						"unspecified", SuffixFunctionType.UNSPECIFIED);
				generateConstruct();
			}
			if (inflectExceptionPossessiveP) {
				setAttributes(lexiconGender, Number.PLURAL, ThreeState.FALSE,
						DefinitenessType.FALSE, inflectedItem, "unspecified",
						SuffixFunctionType.POSSESSIVE);
				generatePossessive();
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
	 * The method compares the remove/replace exception item PGN,gender and
	 * number with <br>
	 * the generated item PGN,gender,number - if there is an equality <br>
	 * the generated item is replaced/removed
	 * 
	 * @param PGN
	 *            - person/gender/number - of lexicon item
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
	 * @return
	 * @throws Exception
	 */
	private boolean replaceRemoveException(String PGN, GenderType gender,
			NumberType number, TriStateType construct,
			List<NounException> exceptions, String action) {
		boolean match = false;
		for (NounException ex : exceptions) {
			GenderType exceptionGender = GenderType.fromValue(ex.getGender()
					.value());
			NumberType exceptionNumber = NumberType.fromValue(ex.getNumber()
					.value());
			TriStateType exceptionConstruct = TriStateType.fromValue(ex
					.getConstruct().value());
			String exceptionPGN = ex.getPossessive();
			SpellingType exceptionSpelling = SpellingType.fromValue(ex
					.getSpelling().value());
			RegisterType exceptionRegister = RegisterType.fromValue(ex
					.getRegister().value());
			if (exceptionGender == gender
					&& exceptionNumber.equals(number)
					&& (exceptionConstruct == construct)
					|| (exceptionConstruct == TriStateType.FALSE
							&& construct == TriStateType.UNSPECIFIED || (exceptionConstruct == TriStateType.UNSPECIFIED && construct == TriStateType.FALSE))
					&& exceptionPGN.equals(PGN)) {
				if (action.equals("replace")) {
					if (doebleVavHandling
							&& !ex.getTransliterated().startsWith("ww"))
						inflectedItem = "w" + ex.getTransliterated();
					else
						inflectedItem = ex.getTransliterated();
					surface = Transliteration.toHebrew(inflectedItem);
					register = exceptionRegister;
					spelling = exceptionSpelling;
					populateDatabase();
					match = true;
					return match;
				} else if (action.equals("remove")) {
					inflectedItem = "";
					surface = "";
					match = true;
					return match;
				}
			}
		}
		return match;
	}

	/**
	 * This method gets the lexicon item attributes values
	 */
	private void analyzeNoun() {
		super.analyseItem();
		plural = noun.getPlural();
		gender = GenderType.fromValue((lexiconGender = noun.getGender())
				.value());
		number = NumberType.fromValue((lexiconNumber = noun.getNumber())
				.value());
		feminine = noun.getFeminine();
		inflectionBase = noun.getInflectionBase();
		suffixFunction = SuffixFunctionType.UNSPECIFIED;
		basePos = "noun";
		surface = undot;
		inflectedItem = transliterated;
		construct = TriStateType.FALSE;
		definitnessVal = DefinitenessType.FALSE;
		PGN = "unspecified";
		inflectConstructS = noun.isInflectConstructS();
		inflectPossessiveS = noun.isInflectPossessiveS();
		inflectConstructP = noun.isInflectConstructP();
		inflectPossessiveP = noun.isInflectPossessiveP();
		isDual = noun.isDual();
		hebForeign = noun.isForeignSource();
	}

	private boolean generateConstruct() {
		boolean returnVal = true;

		boolean matchRemove = replaceRemoveException("unspecified", gender,
				number, TriStateType.TRUE, getRemoveExceptions(), "remove");
		boolean matchReplace = replaceRemoveException("unspecified", gender,
				number, TriStateType.TRUE, getReplaceExceptions(), "replace");

		if (!matchReplace && !matchRemove) {
			if (inflectConstructP
					&& (number == NumberType.PLURAL
							|| number == NumberType.DUAL || number == NumberType.DUAL_AND_PLURAL)) {
				// טיפול בגדי,חי,חיים,שה
				// //טיפול ביד
				//
				findRule(inflectedItem, "", "constructMasculinePlural"
						+ basePos, 2);
				assert inflectedItem.length() != 0;
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

				populateDatabase();

			} else if (inflectConstructS
					&& (gender == GenderType.FEMININE || gender == GenderType.MASCULINE_AND_FEMININE)) {
				findRule(inflectedItem, "", "constructFeminineSingular"
						+ basePos, 2);
				// //deny problems of translation for אמ"נ and אמ"ן פילטרופ
				// סירופ
				if (inflectedItem.endsWith("n") || inflectedItem.endsWith("p")
						|| inflectedItem.endsWith("c")
						&& inflectedItem.equals(transliterated)) {
					surface = undot;
				} else {
					surface = Transliteration.toHebrew(inflectedItem);
				}

				// טיפול בקריה יש צורך ליצור קריית
				if (inflectedItem.endsWith("ih")) {
					// System.out.println("(F) generateConstruct() : GOING TO CREATE DOUBLE i");
					// System.out.println("(F) generateConstruct() : surface = "
					// + surface);
				}

				// System.out.println("(F)(1) generateConstruct() : surface = "
				// + surface);
				populateDatabase();
			} else if (inflectConstructS && gender == GenderType.MASCULINE
					&& number == NumberType.SINGULAR) {
				// //deny problems of translation for אמ"נ and אמ"ן פילטרופ
				// סירופ
				if ((inflectedItem.endsWith("n") || inflectedItem.endsWith("p") || inflectedItem
						.endsWith("c")) && inflectedItem.equals(transliterated)) {
					surface = undot;
				} else {
					surface = Transliteration.toHebrew(inflectedItem);
				}

				// System.out.println("(F) generateConstruct() : surface = " +
				// surface);
				populateDatabase();
			} else if (gender == GenderType.FEMININE) { // טיפול בקריה יש צורך
														// ליצור
				// קריית
				if (inflectedItem.endsWith("ih")) {
					// System.out.println("(F) generateConstruct() : GOING TO CREATE DOUBLE i");
					// System.out.println("(F) generateConstruct() : surface = "
					// + surface);
				}
			}
		}
		return returnVal;
	}

	protected void generatePossessive() {
		if (inflectPossessiveP || inflectPossessiveS) {
			boolean iiSuffix = false;

			if (inflectedItem.substring(inflectedItem.length() - 2)
					.equals("ii"))
				iiSuffix = true;
			if (!inflectionBase.equals("")) {
				inflectedItem = inflectionBase;
			}
			final String colloquialPluralBase = inflectedItem;

			if ((inflectPossessiveP
					&& (number == NumberType.PLURAL || number == NumberType.DUAL_AND_PLURAL) && iiSuffix))
				findRule(inflectedItem, "ii", "possessivePlural" + basePos, 2);

			else if (inflectPossessiveP
					&& (number == NumberType.PLURAL || number == NumberType.DUAL_AND_PLURAL))

				findRule(inflectedItem, "", "possessivePlural" + basePos, 2);
			else if (inflectPossessiveS) {
				findRule(inflectedItem, "", "possessiveSingular" + basePos, 2);
			}

			if ((number == NumberType.SINGULAR && inflectPossessiveS)
					|| ((number == NumberType.PLURAL && inflectPossessiveP))
					|| ((number == NumberType.DUAL_AND_PLURAL && inflectPossessiveP))) {
				StringTokenizer stPossessive = new StringTokenizer(
						inflectedItem, ",");
				StringTokenizer stPGN = new StringTokenizer(PGNTokens10, ",");
				while (stPGN.hasMoreTokens()) {
					PGN = stPGN.nextToken();
					inflectedItem = stPossessive.nextToken();
					if (!replaceRemoveException(PGN, gender, number,
							TriStateType.FALSE, getRemoveExceptions(), "remove")
							&& !replaceRemoveException(PGN, gender, number,
									TriStateType.FALSE, getReplaceExceptions(),
									"replace")) {
						// In case of inflectionBase - there is a need for
						// fixing
						// the
						// generated item by rules

						surface = Transliteration.toHebrew(inflectedItem);

						populateDatabase();
						if (!iiSuffix)
							generatePossessivecolloquial(colloquialPluralBase);
					}
				}
			}
		}
	}

	private void generatePossessivecolloquial(String colloquialPluralBase) {
		SpellingType originalSpelling = spelling;
		// handling exceptions for feminine, plural
		if (number == NumberType.PLURAL) {
			if (PGN.equals("1p/MF/Sg")) {
				// System.out.println("colloquialPluralBase=" +
				// colloquialPluralBase);
				if (colloquialPluralBase
						.charAt(colloquialPluralBase.length() - 1) != 'i')
					inflectedItem = colloquialPluralBase + "i";
				else
					inflectedItem = colloquialPluralBase;
				spelling = SpellingType.IRREGULAR;
				surface = Transliteration.toHebrew(inflectedItem);
				populateDatabase();
			} else if (PGN.equals("2p/F/Sg")) {
				if (colloquialPluralBase
						.charAt(colloquialPluralBase.length() - 1) != 'i')
					inflectedItem = colloquialPluralBase + "ik";
				else
					inflectedItem = colloquialPluralBase + "k";
				spelling = SpellingType.IRREGULAR;
				surface = Transliteration.toHebrew(inflectedItem);
				populateDatabase();
			}
			spelling = originalSpelling;
		}
	}

	private void generatePlural(Plural pluralSuffix) {
		String action = "";
		if (!replaceRemoveException("unspecified", gender, NumberType.PLURAL,
				TriStateType.FALSE, getRemoveExceptions(), "remove")
				&& !replaceRemoveException("unspecified", gender,
						NumberType.PLURAL, TriStateType.FALSE,
						getReplaceExceptions(), "replace")) {
			action = identifyPluralAction(pluralSuffix);
			if (!action.equals("")) {
				// comment because of משתה - משתאות
				// צריך לוודא שכפיתה לא מתקלקל - מתנהגים שונה כי המין שונה
				if (inflectedItem.endsWith("th")
						&& (gender == GenderType.FEMININE))
					findRule(inflectedItem, "th", action, pluralSuffixMaxLength);
				else
					findRule(inflectedItem, pluralSuffix.value(), action,
							pluralSuffixMaxLength);
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
		if (!replaceRemoveException("unspecified", GenderType.FEMININE, number,
				TriStateType.UNSPECIFIED, getRemoveExceptions(), "remove")
				&& !replaceRemoveException("unspecified", GenderType.FEMININE,
						number, TriStateType.UNSPECIFIED,
						getReplaceExceptions(), "replace")) {
			String action = identifyFeminineAction();
			findRule(inflectedItem, feminine.value(), action, 1);
			surface = Transliteration.toHebrew(inflectedItem);
			populateDatabase();
		}
	}

	public List<PersistableInflection> inflect() {
		analyzeNoun();
		handleDoublingVavForNounsStartsWithVav();

		addExceptions();
		return this.getGeneratedInflections();
	}

	public void generateDual() {
		inflectedItem = inflectedItem + "iim";
		surface = Transliteration.toHebrew(inflectedItem);
		populateDatabase();
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
	private void setAttributes(GenderType gender, NumberType number,
			TriStateType construct, DefinitenessType definitnessVal,
			String inflectedItem, String PGN, SuffixFunctionType suffixFunction) {
		this.inflectedItem = inflectedItem;
		this.number = number;
		this.gender = gender;
		this.construct = construct;
		this.definitnessVal = definitnessVal;
		this.PGN = PGN;
		this.suffixFunction = suffixFunction;
	}

	private void setAttributes(Gender gender,
			org.mila.entities.lexicon.Number number, ThreeState construct,
			DefinitenessType definitnessVal, String inflectedItem, String PGN,
			SuffixFunctionType suffixFunction) {
		setAttributes(GenderType.fromValue(gender.value()),
				NumberType.fromValue(number.value()),
				TriStateType.fromValue(construct.value()), definitnessVal,
				inflectedItem, PGN, suffixFunction);
	}

	// in case prefix accompanied noun which starts with a single vav the vava
	// need to be doubled
	private void handleDoublingVavForNounsStartsWithVav() {
		if (transliterated.startsWith("w") && !transliterated.startsWith("ww")) {
			// System.out.println("|||||||||||||||||||||||||||||||||||||");
			// System.out.println("||||||||||double VAV for nouns starts with single VAV||||||||||||");

			inflectLexiconItem();
			// double VAV
			analyzeNoun();
			transliterated = "w" + transliterated;
			surface = Transliteration.toHebrew(transliterated);
			inflectedItem = transliterated;
			// ווילון - הכפלת וו ללו ת
			spelling = SpellingType.IRREGULAR;
			pluralBase = "";
			doebleVavHandling = true;
			inflectLexiconItem();
		} else
			inflectLexiconItem();
	}

	/**
	 * This method is used by noun, adjective, quantifier,pronoun <br>
	 * It is used to generate h+inflectedItem because of special handling for
	 * nouns starts with ww - we don't use the function from itemGen
	 * 
	 * @throws Exception
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
		// Populate database with the original lexicon Item
		// מנגנון שמאפשר לאכלס בצורת הנסמך ללא צורת הבסיס עבור צורות שיופיעו רק
		// בנמך למשל שאט
		if (!replaceRemoveException("unspecified",
				GenderType.fromValue(lexiconGender.value()),
				NumberType.fromValue(lexiconNumber.value()),
				TriStateType.FALSE, getRemoveExceptions(), "remove")) {
			populateDatabase();

			// Set attributes before generating the h+lexicon Item
			setAttributes(lexiconGender, lexiconNumber, ThreeState.FALSE,
					DefinitenessType.TRUE, transliterated, "unspecified",
					SuffixFunctionType.UNSPECIFIED);
			addH();
		}

		if (isDual) {
			setAttributes(lexiconGender, Number.DUAL, ThreeState.FALSE,
					DefinitenessType.FALSE, transliterated, "unspecified",
					SuffixFunctionType.UNSPECIFIED);
			generateDual();
			setAttributes(lexiconGender, Number.DUAL, ThreeState.FALSE,
					DefinitenessType.TRUE, inflectedItem, "unspecified",
					SuffixFunctionType.UNSPECIFIED);
			addH();
		}

		// shlomo doesn't inflect dual - should look at it carefully
		// In future we may inflect dual items
		if (lexiconNumber == Number.DUAL)
			return;

		// Set attributes before generating the construct form

		setAttributes(lexiconGender, lexiconNumber, ThreeState.TRUE,
				DefinitenessType.FALSE, transliterated, "unspecified",
				SuffixFunctionType.UNSPECIFIED);
		generateConstruct();

		// Set attributes before generating the possessive form
		// Possessive are created from the construct
		// The order of generating possessive from construct - is important
		setAttributes(lexiconGender, lexiconNumber, ThreeState.FALSE,
				DefinitenessType.FALSE, inflectedItem, "unspecified",
				SuffixFunctionType.POSSESSIVE);
		generatePossessive();

		// The lexicon item can have any attributes values
		// If the lexicon item is already feminine or there is no feminine
		// form
		// we will not generate the feminine form - so the attributes need
		// to be
		// checked
		if (lexiconGender == Gender.MASCULINE
				&& feminine != Feminine.UNSPECIFIED) {
			setAttributes(Gender.FEMININE, lexiconNumber, ThreeState.FALSE,
					DefinitenessType.FALSE, transliterated, "unspecified",
					SuffixFunctionType.UNSPECIFIED);
			generateFeminine();
			feminineBase = inflectedItem;
		}

		// The lexicon item can have any attributes values
		// If the lexicon item is plural or there is no plural form of it
		// we will not generate the plural form - so the attributes need to
		// be
		// checked
		if (!(plural == Plural.UNSPECIFIED)
				&& (lexiconNumber == Number.SINGULAR)) {
			setAttributes(lexiconGender, Number.PLURAL, ThreeState.FALSE,
					DefinitenessType.FALSE, transliterated, "unspecified",
					SuffixFunctionType.UNSPECIFIED);
			generatePlural(plural);
		}

		// ///////////////////////////////////////////////////////////
		// Feminine form inflections: plural,construct,possessive
		// ///////////////////////////////////////////////////////////

		// Inflecting the single feminine form
		if (!feminineBase.equals("")) {
			// System.out.println("**************************************");
			// System.out.println("**********feminine form inflections**********");

			surface = Transliteration.toHebrew(feminineBase);
			setAttributes(Gender.FEMININE, lexiconNumber, ThreeState.FALSE,
					DefinitenessType.TRUE, feminineBase, "unspecified",
					SuffixFunctionType.UNSPECIFIED);
			addH();

			setAttributes(GenderType.FEMININE, NumberType.SINGULAR,
					TriStateType.TRUE, DefinitenessType.FALSE, feminineBase,
					"unspecified", SuffixFunctionType.UNSPECIFIED);
			boolean returnVal = generateConstruct();

			if (returnVal) {
				surface = Transliteration.toHebrew(inflectedItem);
				setAttributes(GenderType.FEMININE, NumberType.SINGULAR,
						TriStateType.FALSE, DefinitenessType.FALSE,
						inflectedItem, "unspecified",
						SuffixFunctionType.POSSESSIVE);
				generatePossessive();
			}

			surface = Transliteration.toHebrew(feminineBase);
			setAttributes(GenderType.FEMININE, NumberType.PLURAL,
					TriStateType.FALSE, DefinitenessType.FALSE, feminineBase,
					"unspecified", SuffixFunctionType.UNSPECIFIED);
			generatePlural(Plural.WT);

			pluralFeminineBase = inflectedItem;
			if (pluralFeminineBase.length() > 0) {
				surface = Transliteration.toHebrew(pluralFeminineBase);
				setAttributes(GenderType.FEMININE, NumberType.PLURAL,
						TriStateType.FALSE, DefinitenessType.TRUE,
						pluralFeminineBase, "unspecified",
						SuffixFunctionType.UNSPECIFIED);
				addH();

				setAttributes(GenderType.FEMININE, NumberType.PLURAL,
						TriStateType.TRUE, DefinitenessType.FALSE,
						pluralFeminineBase, "unspecified",
						SuffixFunctionType.UNSPECIFIED);
				generateConstruct();

				surface = Transliteration.toHebrew(inflectedItem);
				setAttributes(GenderType.FEMININE, NumberType.PLURAL,
						TriStateType.FALSE, DefinitenessType.FALSE,
						inflectedItem, "unspecified",
						SuffixFunctionType.POSSESSIVE);
				generatePossessive();
			}
		}

		// ///////////////////////////////////////////////////////////
		// plural form inflections: construct,possessive
		// ///////////////////////////////////////////////////////////

		// Inflecting the singular form
		if (!pluralBase.equals("") && plural != Plural.UNSPECIFIED
				&& lexiconNumber != Number.PLURAL) {
			// System.out.println("**************************************");
			// System.out.println("**********plural form inflections**********");
			// System.out.println("pluralBase = " + pluralBase);

			surface = Transliteration.toHebrew(pluralBase);
			setAttributes(lexiconGender, Number.PLURAL, ThreeState.FALSE,
					DefinitenessType.TRUE, pluralBase, "unspecified",
					SuffixFunctionType.UNSPECIFIED);
			addH();

			setAttributes(lexiconGender, Number.PLURAL, ThreeState.TRUE,
					DefinitenessType.FALSE, pluralBase, "unspecified",
					SuffixFunctionType.UNSPECIFIED);
			generateConstruct();

			inflectionBase = "";
			surface = Transliteration.toHebrew(inflectedItem);
			setAttributes(lexiconGender, Number.PLURAL, ThreeState.FALSE,
					DefinitenessType.FALSE, inflectedItem, "unspecified",
					SuffixFunctionType.POSSESSIVE);
			generatePossessive();
		}
	}
}
