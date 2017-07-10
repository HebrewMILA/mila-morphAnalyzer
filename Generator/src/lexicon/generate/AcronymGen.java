package lexicon.generate;

import java.util.List;
import java.util.StringTokenizer;

import lexicon.contents.exception_types.AcronymExceptionType;
import lexicon.contents.exception_types.NounExceptionType;
import lexicon.contents.types.ItemType;
import lexicon.stringUtils.Translate;

public class AcronymGen extends ItemGen {
	String lexiconFemininePlural = "";

	String lexiconMasculinePluralConstruct = "";

	String lexiconFeminineSingularConstruct = "";

	String lexiconMasculineSingularConstruct = "";

	String lexiconFemininePluralConstruct = "";

	private String feminineBase = "";

	private String masculineBase = "";

	private String pluralBase = "";

	private boolean inflectConstructS = true;

	private boolean inflectConstructP = true;

	private String noInvertedCommas = "none";

	private String definiteness = "";

	private String pluralFeminineBase = "";

	private String lexiconSurface = "";

	public AcronymGen(ItemType item) {
		super(item);
	}

	/**
	 * This method gets the lexicon item attributes values
	 */
	private void analyzeAcronym() {
		super.analyseItem();
		plural = item.getAcronym().getPlural();
		System.out.println("plural=" + plural);
		lexiconGender = gender = item.getAcronym().getGender();
		feminine = item.getAcronym().getFeminine();
		System.out.println("feminine=" + feminine);
		lexiconNumber = number = item.getAcronym().getNumber();
		System.out.println("number=" + number);
		value = item.getAcronym().getMeaning();
		noInvertedCommas = item.getAcronym().getNoInvertedCommas();
		suffixFunction = "unspecified";
		basePos = item.getAcronym().getPos();
		surface = undot;
		inflectedItem = transliterated;
		construct = "false";
		definiteness = item.getAcronym().getDefiniteness();
		if (definiteness.equals("externallyDefinited"))
			definitnessVal = "tt";
		PGN = "unspecified";
		inflectConstructS = item.getAcronym().isInflectConstructS();
		inflectConstructP = item.getAcronym().isInflectConstructP();
		if (basePos.equals("title")) {
			inflectConstructS = false;
			inflectConstructP = false;
		}
		lexiconSurface = surface;
		if (basePos.equals("conjunction"))
			type = item.getAcronym().getConjunctionType();
		else if (basePos.equals("propername"))
			type = item.getAcronym().getProperNameType();
		else
			type = "unspecified";

	}

	private boolean generateConstruct() throws Exception {
		// System.out.println("|||||||||||||||||||||||||||||||||||||");
		// System.out.println("||||||||||generateConstruct||||||||||||");
		boolean returnVal = false;

		System.out.println(number);
		System.out.println(gender);
		if (inflectConstructP && number.equals("plural")) {
			findRule(inflectedItem, "", "constructMasculinePlural" + basePos, 2);
			// handle גדי
			if (gender.equals("masculine") && baseTransliteratedItem.endsWith("i")
					&& !baseTransliteratedItem.endsWith("ai")
					// handle שה
					|| (baseTransliteratedItem.endsWith("h") && baseTransliteratedItem.length() == 2))
				inflectedItem = inflectedItem + "i";
			if (inflectedItem.equals(transliterated))
				surface = undot;
			else
				surface = Translate.Eng2Heb(inflectedItem);
			returnVal = true;
			populateDatabase();

		} else if (inflectConstructS && (gender.equals("feminine") || gender.equals("masculine and feminine"))
				&& number.equals("singular")) {
			findRule(inflectedItem, "", "constructFeminineSingular" + basePos, 2);

			// //deny problems of translation for אמ"נ and אמ"ן פילטרופ
			// סירופ
			if (inflectedItem.endsWith("n") || inflectedItem.endsWith("p")
					|| inflectedItem.endsWith("c") && inflectedItem.equals(transliterated))
				surface = undot;
			else
				surface = Translate.Eng2Heb(inflectedItem);
			returnVal = true;
			populateDatabase();
		} else if (inflectConstructS && gender.equals("masculine") && number.equals("singular")) {
			// //deny problems of translation for אמ"נ and אמ"ן פילטרופ
			// סירופ
			if ((inflectedItem.endsWith("n") || inflectedItem.endsWith("p") || inflectedItem.endsWith("c"))
					&& inflectedItem.equals(transliterated))
				surface = undot;
			else
				surface = Translate.Eng2Heb(inflectedItem);
			returnVal = true;
			populateDatabase();

		}

		return returnVal;
	}

	private void generatePlural(String pluralSuffix) throws Exception {
		// System.out.println("|||||||||||||||||||||||||||||||||||||");
		// System.out.println("||||||||||generatePlural||||||||||||");
		// boolean returnVal = true;
		String action = "";

		action = identifyPluralAction(pluralSuffix);
		if (!action.equals("")) {
			// System.out.println("plural base = " + inflectedItem);
			if (inflectedItem.endsWith("th"))
				findRule(inflectedItem, "th", action, pluralSuffixMaxLength);
			else
				findRule(inflectedItem, pluralSuffix, action, pluralSuffixMaxLength);
			// System.out.println("plural inflectedItem = " + inflectedItem);
			surface = Translate.Eng2Heb(inflectedItem);
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

	private void generateFeminine() throws Exception {
		// System.out.println("|||||||||||||||||||||||||||||||||||||");
		// System.out.println("||||||||||generateFeminine||||||||||||");

		String action = identifyFeminineAction();
		findRule(inflectedItem, feminine, action, 1);
		surface = Translate.Eng2Heb(inflectedItem);
		populateDatabase();

	}

	/**
	 * This method gets the exception item list of action add for the lexicon item
	 * 
	 * @throws Exception
	 */
	protected void inflectAddExceptions() throws Exception {
		String originalBasePos = basePos;
		basePos = "acronym";
		super.getException("add");
		basePos = originalBasePos;
		if (addExceptionList.size() > 0) {
			analyseExceptionList(addExceptionList);
		}

	}

	/**
	 * This method is used for handling exceptions for which action = add <br>
	 * This method scan the add exception list - gets the attributes and populate
	 * the databse Each item from the add exception is inflected <br>
	 * That is - we generate the feminine, plural construct, possessive forms for it
	 * 
	 * @param exceptionList
	 * @throws Exception
	 */
	private void analyseExceptionList(List exceptionList) throws Exception {
		for (int i = 0; i < exceptionList.size(); i++) {
			AcronymExceptionType acronymExceptionType = new AcronymExceptionType();
			acronymExceptionType.open(((Integer) exceptionList.get(i)).intValue());
			inflectedItem = acronymExceptionType.getTransliterated();
			// inflectedItem = inflectedItem.replaceAll("&#39;", "'");
			surface = acronymExceptionType.getUndotted();
			lexiconGender = gender = acronymExceptionType.getGender();
			number = lexiconNumber = acronymExceptionType.getNumber();
			register = acronymExceptionType.getRegister();
			spelling = acronymExceptionType.getSpelling();
			type = acronymExceptionType.getProperNameType();
			if (type.equals("unspecified"))
				type = acronymExceptionType.getConjunctionType();
			definitnessVal = "f";
			construct = "false";
			// populate the databse with the exception (add) lexicon item
			populateDatabase();
			String exceptionBase = inflectedItem;
			// We use the same generation methods for the lexicon item and for
			// the exception lexicon item
			// replaceExceptionList is not relevant for the exception type
			// lexicon item (action add)
			replaceExceptionList = null;
			if (definiteness.equals("external") || definiteness.equals("internal and external")) {
				setAttributes(lexiconGender, lexiconNumber, "false", "tt", exceptionBase);

				addH();
			}

		}
	}

	@Override
	public void generateInflects() throws Exception {
		analyzeAcronym();
		inflectLexiconItem();
		inflectAddExceptions();
	}

	/**
	 * This is a service method which is called before every generation operation.
	 * The attributes are changed for each generation
	 * 
	 * @param gender
	 *           - the gender attribute value of the generated item
	 * @param number
	 *           - the number attribute value of the generated item
	 * @param construct
	 *           - true/false/unspecified value for the generated item
	 * @param definitnessVal
	 *           - This attribute value is a combination of <br>
	 *           the lexicon item baseDefinitness and the generated item base
	 *           definitness <br>
	 *           All nouns can be added h - so the lexiocn item base definitness id
	 *           t(true) <br>
	 *           The generated item can appear with added h (t) and without (f) <br>
	 *           The relevant values are : tt, tf
	 * @param inflectedItem
	 *           - The base form on which the next generation action will work <br>
	 *           For example - it is important that creating possessive will work on
	 *           the construct form
	 * @param PGN
	 *           - person/gender/number value of the generated item
	 * @param suffixFunction
	 *           -A flag indication whether a possessive actin is done
	 */
	private void setAttributes(String gender, String number, String construct, String definitnessVal,
			String inflectedItem) {
		this.inflectedItem = inflectedItem;
		this.number = number;
		this.gender = gender;
		this.construct = construct;
		this.definitnessVal = definitnessVal;
		this.PGN = PGN;
		this.suffixFunction = suffixFunction;
	}

	/**
	 * This method is used by noun, adjective, quantifier,pronoun <br>
	 * It is used to generate h+inflectedItem because of special handling for nouns
	 * starts with ww - we don't use the function from itemGen
	 * 
	 * @throws Exception
	 */
	protected void addH() throws Exception {

		String origInflectedItem = inflectedItem;
		// System.out.println("|||||||||||||||||||||||||||||||||||||");
		// System.out.println("||||||||||generateHForm||||||||||||");

		inflectedItem = "h" + inflectedItem;
		surface = "ה" + surface;

		populateDatabase();
		removeInvertedCommas();

		inflectedItem = origInflectedItem;

	}

	protected void generateInternalDefiniteness() throws Exception {
		inflectedItem = inflectedItem.replaceAll("\"", "h\"");
		surface = Translate.Eng2Heb(inflectedItem);
		value = value.replaceAll(" ", " ה");
		populateDatabase();
		inflectedItem = inflectedItem.replaceAll("h\"", "\"");
		surface = Translate.Eng2Heb(inflectedItem);
		value = value.replaceAll(" ה", " ");
	}

	protected void removeInvertedCommas() throws Exception {

		if (noInvertedCommas.equals("all")
				|| noInvertedCommas.equals("masculine singular") && gender.equals("masculine") && number.equals("singular")
				|| noInvertedCommas.equals("masculine plural") && gender.equals("masculine") && number.equals("plural")
				|| noInvertedCommas.equals("feminine plural") && gender.equals("feminine") && number.equals("plural")
				|| noInvertedCommas.equals("feminine singular") && gender.equals("feminine") && number.equals("singular")
				|| noInvertedCommas.equals("all singular") && number.equals("singular")
				|| noInvertedCommas.equals("all plural") && number.equals("plural")) {
			String origInflectedItem = inflectedItem;
			String origSurface = surface;
			setAttributes(gender, number, construct, definitnessVal, inflectedItem);
			inflectedItem = inflectedItem.replaceAll("\"", "");
			inflectedItem = inflectedItem.replaceAll("'", "");
			surface = surface.replaceAll("\"", "");
			surface = surface.replaceAll("'", "");
			populateDatabase();
			inflectedItem = origInflectedItem;
			surface = origSurface;
		}
	}

	private void inflectLexiconItem() throws Exception {

		masculineBase = inflectedItem;
		populateDatabase();

		removeInvertedCommas();

		if (definiteness.equals("internal") || definiteness.equals("internal and external")) {
			setAttributes(lexiconGender, lexiconNumber, "false", "tt", transliterated);
			generateInternalDefiniteness();
		}

		if (definiteness.equals("external") || definiteness.equals("internal and external")) {
			setAttributes(lexiconGender, lexiconNumber, "false", "tt", transliterated);

			addH();
		}

		// Set attributes before generating the construct form

		setAttributes(lexiconGender, lexiconNumber, "true", "tf", transliterated);
		if (generateConstruct())
			removeInvertedCommas();

		// The lexicon item can have any attributes values
		// If the lexicon item is already feminine or there is no feminine
		// form
		// we will not generate the feminine form - so the attributes need
		// to be
		// checked
		if (lexiconGender.equals("masculine") && !feminine.equals("unspecified")) {
			setAttributes("feminine", lexiconNumber, "false", "tf", transliterated);
			generateFeminine();
			removeInvertedCommas();
			feminineBase = inflectedItem;
		}

		// The lexicon item can have any attributes values
		// If the lexicon item is plural or there is no plural form of it
		// we will not generate the plural form - so the attributes need to
		// be
		// checked
		if (!plural.equals("unspecified") && lexiconNumber.equals("singular")) {
			setAttributes(lexiconGender, "plural", "false", "tf", transliterated);
			generatePlural(plural);
			removeInvertedCommas();
		}

		/////////////////////////////////////////////////////////////
		// Feminine form inflections: plural,construct,possessive
		/////////////////////////////////////////////////////////////

		// Inflecting the single feminine form
		if (!feminineBase.equals("")) {
			// System.out.println("**************************************");
			// System.out.println("**********feminine form inflections**********");

			surface = Translate.Eng2Heb(feminineBase);

			if (definiteness.equals("external") || definiteness.equals("internal and external")) {
				setAttributes("feminine", lexiconNumber, "false", "tt", feminineBase);
				addH();
			}

			setAttributes("feminine", "singular", "true", "tf", feminineBase);
			if (generateConstruct())
				removeInvertedCommas();

			/////////////////////////////////////////////////////////////////////////////

			surface = Translate.Eng2Heb(feminineBase);
			setAttributes("feminine", "plural", "false", "tf", feminineBase);
			generatePlural("wt");
			removeInvertedCommas();

			pluralFeminineBase = inflectedItem;
			surface = Translate.Eng2Heb(pluralFeminineBase);

			if (definiteness.equals("external") || definiteness.equals("internal and external")) {
				setAttributes("feminine", "plural", "false", "tt", pluralFeminineBase);
				addH();
			}

			setAttributes("feminine", "plural", "true", "tf", pluralFeminineBase);
			if (generateConstruct())
				removeInvertedCommas();

		}

		/////////////////////////////////////////////////////////////
		// plural form inflections: construct,possessive
		/////////////////////////////////////////////////////////////

		// Inflecting the singular form
		if (!pluralBase.equals("") && !plural.equals("unspecified") && !lexiconNumber.equals("plural")) {
			// System.out.println("**************************************");
			// System.out.println("**********plural form inflections**********");
			// System.out.println("pluralBase = " + pluralBase);

			surface = Translate.Eng2Heb(pluralBase);
			// System.out.println("surface = " + surface);

			if (definiteness.equals("external") || definiteness.equals("internal and external")) {
				setAttributes(lexiconGender, "plural", "false", "tt", pluralBase);
				addH();
			}

			setAttributes(lexiconGender, "plural", "true", "tf", pluralBase);
			if (generateConstruct())
				removeInvertedCommas();

		}

	}
}
