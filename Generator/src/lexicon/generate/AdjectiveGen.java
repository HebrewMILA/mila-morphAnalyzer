package lexicon.generate;

import java.util.List;

import lexicon.contents.exception_types.NounExceptionType;
import lexicon.contents.types.ItemType;
import lexicon.stringUtils.Translate;

public class AdjectiveGen extends ItemGen {
	/**
	 * @author dalia bojan Created on 13/07/2005 This class handles Adjective
	 *         Generation <br>
	 *         Generation is performed for: <br>
	 *         Lexicon item of pos adjective <br>
	 *         Lexicon item exception action = add (if exist) <br>
	 * 
	 * For each generated lexicon item (not thos items generated from add
	 * exception items) we need to check <br>
	 * whethere there exist a remove or replace exception lexicon item <br>
	 * 
	 * Generation activities: <br>
	 * original lexicon item <br>
	 * h+ original lexicon item <br>
	 * construct form of the original lexicon item <br>
	 * feminine form of the original lexicon item <br>
	 * h+ feminine form of the original lexicon item <br>
	 * plural form of the original lexicon item <br>
	 * h+ plural form of the original lexicon item <br>
	 * construct form of feminine form <br>
	 * plural form of feminine form <br>
	 * construct form of plural form <br>
	 * plural form of plural form <br>
	 * 
	 * The generation done based on rules (rules1 table on generator databse)
	 * <br>
	 *  
	 */

	//private int pluralSuffixMaxLength;
	private String feminineBase = "";

	private String pluralBase = "";

	private boolean exceptionInflectConstructS = false;

	private boolean exceptionInflectConstructP = false;

	boolean doebleVavHandling = false;

	private boolean inflectConstructS = true;

	private boolean inflectConstructP = true;

	

	public AdjectiveGen(ItemType item) {
		super(item);
	}

	/**
	 * This method gets the exception item list of action add for the lexicon
	 * item
	 * 
	 * @throws Exception
	 */
	protected void inflectAddExceptions() throws Exception {
		super.getException("add");
		if (addExceptionList.size() > 0) {
			analyseExceptionList(addExceptionList);
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
	 * @throws Exception
	 */
	private void analyseExceptionList(List exceptionList) throws Exception {
		for (int i = 0; i < exceptionList.size(); i++) {
			NounExceptionType nounExceptionType = new NounExceptionType();
			nounExceptionType.open(((Integer) exceptionList.get(i)).intValue());
			inflectedItem = nounExceptionType.getTransliterated();
			//inflectedItem = inflectedItem.replaceAll("&#39;", "'");
			surface = nounExceptionType.getUndotted();
			lexiconGender = gender = nounExceptionType.getGender();
			plural = nounExceptionType.getPlural();
			number = lexiconNumber = nounExceptionType.getNumber();
			register = nounExceptionType.getRegister();
			spelling = nounExceptionType.getSpelling();
			feminine = nounExceptionType.getFeminine();
			construct = nounExceptionType.getConstruct();
			definitnessVal = "tf";
			//populate the databse with the exception (add) lexicon item
			populateDatabase();
			String exceptionBase = inflectedItem;

			//shlomo doesn't inflect it and should be looked more carefully
			if (lexiconNumber.equals("dual"))
				return;

			//setting flags: should we generate construct for the exception
			// lexicon item
			exceptionInflectConstructS = nounExceptionType
					.isInflectConstructS();
			exceptionInflectConstructP = nounExceptionType
					.isInflectConstructP();

			//We use the same generation methods for the lexicon item and for
			// the exception lexicon item
			//replaceExceptionList is not relevant for the exception type
			// lexicon item (action add)
			replaceExceptionList = null;
			inflectAddException("add", exceptionBase);
			if (exceptionBase.startsWith("w")
					&& !exceptionBase.startsWith("ww")) {
				exceptionBase = "w" + exceptionBase;
				surface = "ו" + nounExceptionType.getUndotted();
				inflectAddException("add", exceptionBase);
			}

		}
	}

	private void inflectAddException(String action, String exceptionBase)
			throws Exception {

		String exceptionFeminineBase = "";
		String exceptionPluralBase = "";

		if (!construct.equals("true") 
				&& exceptionPluralBase.equals("")) {
			setAttributes(lexiconGender, lexiconNumber, "false", "tt",
					exceptionBase);
			addH();
		}
		//exception type lexicon item has two attributes relevant for construct
		// generation
		//1) a flag indicating whether there is a construct form for this item
		//2) construct attribute pointing out if the current excpetion item
		//is the construct form

		//17/01/06 - if singular adjective ends with i - don't generate
		// construct
		if (exceptionInflectConstructS && construct.equals("false")
				&& (exceptionBase.charAt(exceptionBase.length() - 1) != 'i')
				&& lexiconNumber.equals("singular")) {
			setAttributes(lexiconGender, lexiconNumber, "true", "tf",
					exceptionBase);
			generateConstruct();
		}
		
		

		if (lexiconGender.equals("masculine")
				&& !feminine.equals("unspecified")) {
			setAttributes("feminine", lexiconNumber, "false", "tf",
					exceptionBase);
			generateFeminine();
			exceptionFeminineBase = inflectedItem;
			setAttributes("feminine", lexiconNumber, "false", "tt",
					exceptionFeminineBase);
			addH();
		}

		if (!plural.equals("unspecified")) {
			setAttributes(lexiconGender, "plural", "false", "tf",
					exceptionBase);
			generatePlural(plural);
			exceptionPluralBase = inflectedItem;
			setAttributes(lexiconGender, "plural", "false", "tt",
					exceptionPluralBase);
			addH();
		}

		/////////////////////////////////////////////////////////////
		// Feminine form inflections: plural,construct,possessive
		/////////////////////////////////////////////////////////////

		if (!exceptionFeminineBase.equals("")) {

			

			if (exceptionInflectConstructS) {
				setAttributes("feminine", "singular", "true", "tf",
						exceptionFeminineBase);
				generateConstruct();
			}

			setAttributes("feminine", "plural", "false", "tf",
					exceptionFeminineBase);
			generatePlural("wt");
			String exceptionPluralFeminineBase = inflectedItem;
			
			setAttributes("feminine", "plural", "false", "tt",
					exceptionPluralFeminineBase);
			addH();


			if (exceptionInflectConstructP) {
				setAttributes("feminine", "plural", "true", "tf",
						exceptionPluralFeminineBase);
				generateConstruct();
			}

		}

		////////////////////////////////////////////////////////////
		//pluralBase inflections: construct, plural
		///////////////////////////////////////////////////////////
		if (!exceptionPluralBase.equals("")) {
			if (number.equals("plural")  && exceptionInflectConstructP) {
				setAttributes(lexiconGender, "plural", "true", "tf",
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
	 * The method compares the remove/replace exception item gender and
	 * number with <br>
	 * the generated item gender,number - if there is an equality <br>
	 * the generated item is replaced/removed
	 * 
	 * @param gender -
	 *            gender value of lexicon item
	 * @param number -
	 *            number value of lexicon item
	 * @param construct -
	 *            construct value of lexicon item
	 * @param exceptionList -
	 *            remove/replace exception list
	 * @param action -
	 *            remove/replace
	 * @return
	 * @throws Exception
	 */
	private boolean replaceRemoveException(String gender,
			String number, String construct, List exceptionList, String action)
			throws Exception {
		boolean match = false;
		if (exceptionList != null) {
			for (int i = 0; i < exceptionList.size(); i++) {
				NounExceptionType nounExceptionType = new NounExceptionType();
				nounExceptionType.open(((Integer) exceptionList.get(i))
						.intValue());
				String exceptionGender = nounExceptionType.getGender();
				String exceptionNumber = nounExceptionType.getNumber();
				String exceptionConstruct = nounExceptionType.getConstruct();
				/*
				System.out.println("----------------------------");
				System.out.println(action + " handling");
				System.out.println("exceptionGender =" + exceptionGender);
				System.out.println("gender =" + gender);
				System.out.println("exceptionNumber =" + exceptionNumber);
				System.out.println("number =" + number);
				System.out.println("exceptionConstruct  =" + exceptionConstruct);
				System.out.println("construct  =" + construct);
				System.out.println("PGN  =" + PGN);*/
				if (exceptionGender.equals(gender)
						&& exceptionNumber.equals(number)
						&& (exceptionConstruct.equals(construct)
								|| (exceptionConstruct.equals("false") && construct
										.equals("unspecified")) || (exceptionConstruct
								.equals("unspecified") && construct
								.equals("false"))) ) {
					if (action.equals("replace")) {
						//System.out.println("exception action = replace ");
						if (doebleVavHandling
								&& !nounExceptionType.getTransliterated()
										.startsWith("ww"))
							inflectedItem = "w"
									+ nounExceptionType.getTransliterated();
						else
							inflectedItem = nounExceptionType
									.getTransliterated();
						surface = Translate.Eng2Heb(inflectedItem);
						//System.out.println("exception transliterated  ="+ inflectedItem);
						populateDatabase();
						match = true;
						//System.out.println("----------------------------");
						return match;
					} else if (action.equals("remove")) {
						//System.out.println("exception action = remove ");
						match = true;
						//System.out.println("----------------------------");
						return match;
					}
				}
			}
		}
		//System.out.println("----------------------------");
		return match;
	}

	/**
	 * This method gets the lexicon item attributes values
	 */
	private void analyzeAdjective() {
		super.analyseItem();
		plural = item.getAdjective().getPlural();
		lexiconGender = gender = item.getAdjective().getGender();
		feminine = item.getAdjective().getFeminine();
		lexiconNumber = number = item.getAdjective().getNumber();
		suffixFunction = "unspecified";
		basePos = item.getPos();
		surface = undot;
		inflectedItem = transliterated;
		construct = "false";
		definitnessVal = "tf";
		PGN = "unspecified";
		inflectConstructS = item.getAdjective().isInflectConstructS();
		inflectConstructP = item.getAdjective().isInflectConstructP();
		hebForeign = item.getAdjective().isForeign();
	}

	private void handleDoublingVavForAdjectivesStartsWithVav() throws Exception 
	{
		if (transliterated.startsWith("w") && !transliterated.startsWith("ww")) 
		{
			//System.out.println("|||||||||||||||||||||||||||||||||||||");
			//System.out.println("||||||||||double VAV for nouns starts with single VAV||||||||||||");

			inflectLexiconItem();

			//double VAV
			analyzeAdjective();
			transliterated = "w" + transliterated;
			surface = Translate.Eng2Heb(transliterated);
			inflectedItem = transliterated;
			register = FORMAL_REGISTER;
			spelling=STANDARD_SPELLING;
			pluralBase = "";
			doebleVavHandling = true;
			inflectLexiconItem();
		} else
			inflectLexiconItem();
	}

	private void generateConstruct() throws Exception 
	{
		//System.out.println("|||||||||||||||||||||||||||||||||||||");
		//System.out.println("||||||||||generateConstruct||||||||||||");
		
			if (!replaceRemoveException(lexiconGender,
					lexiconNumber, "true", replaceExceptionList, "replace")) 
			{
				//System.out.println("No exception handling");
				if (number.equals("plural"))
					// in noun rules file it refers to feminine as well - it's a
					// mistake
					findRule(inflectedItem, "", "constructMasculinePlural"
							+ basePos, 2);
				else if (gender.equals("feminine")
						|| gender.equals("masculine and feminine"))
					findRule(inflectedItem, "", "constructFeminineSingular"
							+ basePos, 2);
				//deny problems of translation for אמ"נ and אמ"ן פילטרופ סירופ
				if (inflectedItem.endsWith("n") || inflectedItem.endsWith("p") || inflectedItem.endsWith("c")
						&&  
						inflectedItem.equals(transliterated))
					surface = undot;
				else
					surface = Translate.Eng2Heb(inflectedItem);
				populateDatabase();
			
		}
	}

	private void generatePlural(String pluralSuffix) throws Exception 
	{
		//System.out.println("|||||||||||||||||||||||||||||||||||||");
		//System.out.println("||||||||||generatePlural||||||||||||");
		String action = "";

		if (!replaceRemoveException(gender, "plural",
				"unspecified", replaceExceptionList, "replace")) 
		{
			action = identifyPluralAction(pluralSuffix);
			if (!action.equals("")) 
			{
				//System.out.println("plural base = " + inflectedItem);
				findRule(inflectedItem, pluralSuffix, action,pluralSuffixMaxLength);
				//System.out.println("plural inflectedItem = " + inflectedItem);
				surface = Translate.Eng2Heb(inflectedItem);
				populateDatabase();
				if (pluralBase.equals(""))
					pluralBase = inflectedItem;
			}
		} else {
			if (pluralBase.equals(""))
				pluralBase = inflectedItem;
		}
	}

	private void generateFeminine() throws Exception 
	{
		//System.out.println("|||||||||||||||||||||||||||||||||||||");
		//System.out.println("||||||||||generateFeminine||||||||||||");
		if (!replaceRemoveException("feminine", number,
				"unspecified", replaceExceptionList, "replace")) {
			String action = identifyFeminineAction();
			findRule(inflectedItem, feminine, action, 1);
			surface = Translate.Eng2Heb(inflectedItem);
			populateDatabase();
		}
	}

	public void inflect() throws Exception 
	{
		//Get lexicon Item (for which pos = adjective ) attributes
		//The lexicon item can have any attributes for example
		//single masculine/single feminine/ plural feminine etc
		analyzeAdjective();
		//Get list of exceptions of type replace for this lexicon Item
		getException("replace");
		//Get list of exceptions of type remove for this lexicon Item
		getException("remove");

		handleDoublingVavForAdjectivesStartsWithVav();

		//inflect exception type items of action add - if exist
		inflectAddExceptions();
	}

	/**
	 * This is a service method which is called before every generation
	 * operation. The attributes are changed for each generation
	 * 
	 * @param gender -
	 *            the gender attribute value of the generated item
	 * @param number -
	 *            the number attribute value of the generated item
	 * @param construct -
	 *            true/false/unspecified value for the generated item
	 * @param definitnessVal -
	 *            This attribute value is a combination of <br>
	 *            the lexicon item baseDefinitness and the generated item base
	 *            definitness <br>
	 *            All nouns can be added h - so the lexiocn item base
	 *            definitness id t(true) <br>
	 *            The generated item can appear with added h (t) and without (f)
	 *            <br>
	 *            The relevant values are : tt, tf
	 * @param inflectedItem -
	 *            The base form on which the next generation action will work
	 *            <br>
	 *            For example - it is important that creating possessive will
	 *            work on the construct form
	 * @param suffixFunction
	 *            -A flag indication whether a possessive actin is done
	 */
	private void setAttributes(String gender, String number, String construct,
			String definitnessVal, String inflectedItem) {
		this.inflectedItem = inflectedItem;
		this.number = number;
		this.gender = gender;
		this.construct = construct;
		this.definitnessVal = definitnessVal;
		this.PGN = "unspecified";
		this.suffixFunction = "unspecified";
	}

	
	
	/**
	 * This method is used by noun, adjective, quantifier,pronoun <br>
	 * It is used to generate h+inflectedItem because of special handling for
	 * adjective starts with ww - we don't use the function from itemGen
	 * 
	 * @throws Exception
	 */
	protected void addH() throws Exception 
	{
		String originalSpelling=spelling;
		String origInflectedItem=inflectedItem;
		//System.out.println("|||||||||||||||||||||||||||||||||||||");
		//System.out.println("||||||||||generateHForm||||||||||||");
		///////////////////////////////////////////////////////////////////
		if (inflectedItem.startsWith("w") && !inflectedItem.startsWith("ww"))
			spelling = IRREGULAR_SPELLING;
		else if (inflectedItem.startsWith("ww"))
			spelling = STANDARD_SPELLING;
		//////////////////////////////////////////////////////////////////
		inflectedItem = "h" + inflectedItem;
		surface = "ה" + surface;

	
		populateDatabase();
	
		inflectedItem=origInflectedItem;
		spelling=originalSpelling;
	}
	
	private void inflectLexiconItem() throws Exception 
	{
		populateDatabase();

		setAttributes(lexiconGender, lexiconNumber, "false", "tt",
				transliterated);
		addH();

		//17/01/06 - if singular adjective ends with i - don't generate
		// construct
		if ((lexiconNumber.equals("singular") && inflectConstructS)
				&& ((transliterated.charAt(transliterated.length() - 1)) != 'i')) {
			setAttributes(lexiconGender, lexiconNumber, "true", "unspecified",
					transliterated);
			generateConstruct();
		}

		if (lexiconGender.equals("masculine")
				&& !feminine.equals("unspecified")) {
			setAttributes("feminine", lexiconNumber, "false", "tf",
					transliterated);
			generateFeminine();
			feminineBase = inflectedItem;
			setAttributes("feminine", lexiconNumber, "false", "tt",
					feminineBase);
			addH();
		}

		if (!plural.equals("unspecified") && !lexiconNumber.equals("plural")) {
			setAttributes(lexiconGender, "plural", "false", "tf",
					transliterated);
			generatePlural(plural);
			setAttributes(lexiconGender, "plural", "false", "tt",
					inflectedItem);
			addH();
		}

		/////////////////////////////////////////////////////////////
		// Feminine form inflections: plural,construct
		/////////////////////////////////////////////////////////////

		if (!feminineBase.equals("")) 
		{
			//System.out.println("**************************************");
			//System.out.println("**********feminine form inflections**********");

			if(inflectConstructS){
			setAttributes("feminine", "singular", "true", "unspecified", feminineBase);
			generateConstruct();
			}
			
			setAttributes("feminine", "plural", "false", "tf", feminineBase);
			generatePlural("wt");

			String pluralFeminineBase = inflectedItem;

			setAttributes("feminine", "plural", "false", "tt",
					pluralFeminineBase);
			addH();
			
			if(inflectConstructP){
			setAttributes("feminine", "plural", "true", "unspecified",
					pluralFeminineBase);
			generateConstruct();
			}
		}

		/////////////////////////////////////////////////////////////
		// plural form inflections: construct
		/////////////////////////////////////////////////////////////

		if (!pluralBase.equals("") &&  inflectConstructP) 
		{
			//System.out.println("**************************************");
			//System.out.println("**********plural form inflections**********");
			//System.out.println("pluralBase = " + pluralBase);

			setAttributes(lexiconGender, "plural", "true", "unspecified", pluralBase);
			generateConstruct();
		}

	}

}
