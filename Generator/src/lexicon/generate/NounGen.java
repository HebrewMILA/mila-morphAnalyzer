package lexicon.generate;

import java.util.List;
import java.util.StringTokenizer;
import lexicon.contents.exception_types.NounExceptionType;
import lexicon.contents.exception_types.PrepositionExceptionType;
import lexicon.contents.types.ItemType;
import lexicon.stringUtils.Translate;

/**
 * @author dalia bojan Created on 13/07/2005 This class handles Noun Generation
 *         <br>
 *         Generation is performed for: <br>
 *         Lexicon item of pos noun <br>
 *         Lexicon item exception action = add (if exist) <br>
 *         For each generated lexicon item (not add exception type) we need to
 *         check <br>
 *         whethere there exist a remove or replace exception lexicon item <br>
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
 * There are few cases on which the possessive is not done from the construct
 * form <br>
 * but from an attribute named inflectionBase <br>
 * The generation done based on rules (rules1 table on generator databse) <br>
 * 
 * Noun generation is very simmilar to adjective generation with the following
 * exceptions: <br>
 * Possessive form is not created for adjective For Adjective lexicon items ends
 * with i - we are not generating the construct form
 *  
 */
public class NounGen extends ItemGen 
{
	String lexiconFemininePlural = "";
	String lexiconMasculinePluralConstruct = "";
	String lexiconFeminineSingularConstruct = "";
	String lexiconMasculineSingularConstruct = "";
	String lexiconFemininePluralConstruct = "";
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

	//private String colloquialPluralBase = "";

	private String pluralFeminineBase = "";

	public NounGen(ItemType item) 
	{
		super(item);
	}

	/**
	 * This method gets the exception item list of action add for the lexicon
	 * item
	 * 
	 * @throws Exception
	 */
	private void inflectAddExceptions() throws Exception 
	{
		super.getException("add");
		if (addExceptionList.size() > 0) 
		{
			analyseExceptionList();
		}
	}

	private boolean addExceptionListHandling(NounExceptionType nounExceptionType) 
	{
		gender = lexiconGender = nounExceptionType.getGender();
		plural = nounExceptionType.getPlural();
		number = lexiconNumber = nounExceptionType.getNumber();
		if (!(nounExceptionType.getTransliterated().startsWith("w") && inflectedItem.startsWith("ww")))
			spelling = nounExceptionType.getSpelling();
		register = nounExceptionType.getRegister();
		spelling = nounExceptionType.getSpelling();
		feminine = nounExceptionType.getFeminine();
		PGN = nounExceptionType.getPossessive();
		definitnessVal = "tf";
		if (!PGN.equals("unspecified")) 
		{
			suffixFunction = "possessive";
			construct = "false";
			StringTokenizer st = new StringTokenizer(PGN, "/");

			//			String currentToken = st.nextToken();
			//			suffixPerson = String.valueOf(currentToken.charAt(0));
			//			currentToken = st.nextToken();
			//			if (currentToken.startsWith("F"))
			//				suffixGender = "feminine";
			//			else if (currentToken.equals("MF"))
			//				suffixGender = "masculine and feminine";
			//			else if (currentToken.equals("M"))
			//				suffixGender = "masculine";
			//			currentToken = st.nextToken();
			//			if (currentToken.startsWith("P"))
			//				suffixNumber = "plural";
			//			else
			//				suffixNumber = "singular";

			try 
			{
				populateDatabase();
			} 
			catch (Exception e2) 
			{
				// TODO Auto-generated catch block
				e2.printStackTrace();
			}
			return false;
		} 
		else
			suffixFunction = "unspecified";
		construct = nounExceptionType.getConstruct();
		if (construct.equals("unspecified"))
			construct = "false";

		try 
		{
			//populate the databse with the exception (add) lexicon item
			populateDatabase();
		} 
		catch (Exception e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		String exceptionBase = inflectedItem;

		//shlomo doesn't inflect it and should be looked more carefully
		//if (lexiconNumber.equals("dual"))
		//	return false;

		//setting flags: should we generate construct for the SINGULAR
		// exception
		// lexicon item
		inflectExceptionConstructS = nounExceptionType.isInflectConstructS();

		//setting flags: should we generate possessive for the SINGULAR
		// exception
		// lexicon item
		inflectExceptionPossessiveS = nounExceptionType.isInflectPossessiveS();

		//			setting flags: should we generate construct for the PLURAL
		// exception
		// lexicon item
		inflectExceptionConstructP = nounExceptionType.isInflectConstructP();

		//setting flags: should we generate possessive for the PLURAL
		// exception
		// lexicon item
		inflectExceptionPossessiveP = nounExceptionType.isInflectPossessiveP();

		//We use the same generation methods for the lexicon item and for
		// the exception lexicon item
		//replaceExceptionList is not relevant for the exception type
		// lexicon item (action add)
		replaceExceptionList = null;
		removeExceptionList = null;
		try 
		{
			inflectAddException(exceptionBase);
		} 
		catch (Exception e1) 
		{
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		return true;
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
	private void analyseExceptionList() throws Exception 
	{
		int size = addExceptionList.size();
		//System.out.println("addExceptionList size" + size);
		for (int i = 0; i < size; i++) 
		{
			NounExceptionType nounExceptionType = new NounExceptionType();
			nounExceptionType.open(((Integer) addExceptionList.get(i)).intValue());
			inflectedItem = nounExceptionType.getTransliterated();
			//inflectedItem = inflectedItem.replaceAll("&#39;", "'");
			inflectedItem = inflectedItem.replaceAll("&#60;", "`");
			//System.out.println("inflectedItem=" + inflectedItem);
			//System.out.println("i=" + i);
			surface = nounExceptionType.getUndotted();
			//suffixGender = nounExceptionType.getGender();
			addExceptionListHandling(nounExceptionType);

			if (nounExceptionType.getTransliterated().startsWith("w") && !nounExceptionType.getTransliterated().startsWith("ww")) 
			{
				inflectedItem = "w" + nounExceptionType.getTransliterated();
				//inflectedItem = inflectedItem.replaceAll("&#39;", "'");
				inflectedItem = inflectedItem.replaceAll("&#60;", "`");
				surface = "ו" + nounExceptionType.getUndotted();
				spelling = IRREGULAR_SPELLING;
				addExceptionListHandling(nounExceptionType);
			}
		}
	}

	/**
	 * This method is very similar to the inflectLexiconItem method <br>
	 * Still is has certain flags uniqe to exception type
	 * 
	 * @param exceptionBase -
	 *            lexicon exception item of action add
	 * @param exceptionGender -
	 *            gender value for lexicon exception item of action add
	 * @throws Exception
	 */
	private void inflectAddException(String exceptionBase) throws Exception 
	{
		String exceptionFeminineBase = "";
		String exceptionPluralBase = "";
		//boolean returnVal = true;

		//if (number.equals("plural"))
		//	exceptionPluralBase = inflectedItem;

		if (!construct.equals("true") && PGN.equals("unspecified")) 
		{
			setAttributes(lexiconGender, lexiconNumber, "false", "tt",exceptionBase, "unspecified", "unspecified");
			addH();
		}
		//exception type lexicon item has two attributes relevant for
		// construct
		// generation
		//1) a flag indicating whether there is a construct form for this
		// item
		//2) construct attribute pointing out if the current excpetion item
		//is the construct form

		if (inflectExceptionConstructS && construct.equals("false") && PGN.equals("unspecified")) 
		{
			setAttributes(lexiconGender, lexiconNumber, "true", "unspecified",exceptionBase, "unspecified", "unspecified");
			generateConstruct();
		}
		
		if (inflectExceptionConstructP && construct.equals("false") && PGN.equals("unspecified")) 
		{
			setAttributes(lexiconGender, "plural", "true", "unspecified",exceptionBase, "unspecified", "unspecified");
			generateConstruct();
		}

		//exception type lexicon item has a flag indicating wether to
		// generate
		// the possessive forms

		//צורת הבסיס לקניין היא צורת הנסמך
		if (inflectExceptionConstructS && inflectExceptionPossessiveS && lexiconNumber.equals("singular")) 
		{
			setAttributes(lexiconGender, lexiconNumber, "false", "tf",inflectedItem, "unspecified", "possessive");
			generatePossessive();
		}

		if (inflectExceptionPossessiveP && lexiconNumber.equals("plural")) 
		{
			setAttributes(lexiconGender, lexiconNumber, "false", "tf",inflectedItem, "unspecified", "possessive");
			generatePossessive();
		}

		if (lexiconGender.equals("masculine") && !feminine.equals("unspecified")) 
		{
			setAttributes("feminine", lexiconNumber, "false", "tf",exceptionBase, "unspecified", "unspecified");
			generateFeminine();
			exceptionFeminineBase = inflectedItem;
		}

		if (!plural.equals("unspecified")) 
		{
			setAttributes(lexiconGender, "plural", "false", "tf",exceptionBase, "unspecified", "unspecified");
			//returnVal = generatePlural(plural);
			generatePlural(plural);
			exceptionPluralBase = inflectedItem;
			//לא ייצר מידע ליוצא דופן רבים למשל יכטה
			addH();
		}
		/////////////////////////////////////////////////////////////
		// Feminine form inflections: plural,construct,possessive
		/////////////////////////////////////////////////////////////

		if (!exceptionFeminineBase.equals("")) 
		{
			setAttributes("feminine", lexiconNumber, "false", "tt",exceptionFeminineBase, "unspecified", "unspecified");
			addH();

			if (inflectExceptionConstructS) 
			{
				setAttributes("feminine", "singular", "true", "unspecified",exceptionFeminineBase, "unspecified", "unspecified");
				generateConstruct();
			}

			if (inflectExceptionPossessiveS) 
			{
				setAttributes("feminine", "singular", "false", "tf",exceptionFeminineBase, "unspecified", "possessive");
				generatePossessive();
			}

			setAttributes("feminine", "plural", "false", "tf",exceptionFeminineBase, "unspecified", "unspecified");
			generatePlural("wt");
			//returnVal = generatePlural("wt");

			String exceptionPluralFeminineBase = inflectedItem;

			if (inflectExceptionConstructP) 
			{
				setAttributes("feminine", "plural", "true", "unspecified",exceptionPluralFeminineBase, "unspecified","unspecified");
				generateConstruct();
			}

			if (inflectExceptionPossessiveP) 
			{
				setAttributes("feminine", "plural", "false", "tf",exceptionPluralFeminineBase, "unspecified","possessive");
				generatePossessive();
			}
		}
		//     עיולים לבדיקה כשמשנים - יכטה, נוסחה
		// צוות , שדרוג
		////////////////////////////////////////////////////////////
		//pluralBase inflections: construct, plural
		///////////////////////////////////////////////////////////
		if (!exceptionPluralBase.equals("")) 
		{
			//אם הצורה שהוכנסה ביוצאי הדופן אינה רבים ואנחנו ייצרנו אותה
			if (plural.equals("unspecifed")) 
			{
				setAttributes(lexiconGender, "plural", "false", "tt",exceptionPluralBase, "unspecified", "unspecified");
				addH();
			}

			if (inflectExceptionConstructP) 
			{
				setAttributes(lexiconGender, "plural", "true", "unspecified",exceptionPluralBase, "unspecified", "unspecified");
				generateConstruct();
			}
			if (inflectExceptionPossessiveP) 
			{
				setAttributes(lexiconGender, "plural", "false", "tf",inflectedItem, "unspecified", "possessive");
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
	 * @param PGN -
	 *            person/gender/number - of lexicon item
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
	private boolean replaceRemoveException(String PGN, String gender,String number, String construct, List exceptionList, String action) throws Exception 
	{
		boolean match = false;
		if (exceptionList != null) 
		{
			for (int i = 0; i < exceptionList.size(); i++) 
			{
				NounExceptionType nounExceptionType = new NounExceptionType();
				nounExceptionType.open(((Integer) exceptionList.get(i)).intValue());
				String exceptionGender = nounExceptionType.getGender();
				String exceptionNumber = nounExceptionType.getNumber();
				String exceptionConstruct = nounExceptionType.getConstruct();
				String exceptionPGN = nounExceptionType.getPossessive();
				String exceptionSpelling = nounExceptionType.getSpelling();
				String exceptionRegister = nounExceptionType.getRegister();
				/*System.out.println("----------------------------");
				System.out.println(action + " handling");
				System.out.println("exceptionGender =" + exceptionGender);
				System.out.println("gender =" + gender);
				System.out.println("exceptionNumber =" + exceptionNumber);
				System.out.println("number =" + number);
				System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!exceptionConstruct  ="+ exceptionConstruct);
				System.out.println("construct  =" + construct);
				System.out.println("exceptionPGN  =" + exceptionPGN);
				System.out.println("PGN  =" + PGN);*/
				if (exceptionGender.equals(gender)
						&& exceptionNumber.equals(number)
						&& (exceptionConstruct.equals(construct)
								|| (exceptionConstruct.equals("false") && construct
										.equals("unspecified")) || (exceptionConstruct
								.equals("unspecified") && construct
								.equals("false"))) && exceptionPGN.equals(PGN)) 
				{
					if (action.equals("replace")) 
					{
						//System.out.println("exception action = replace ");
						if (doebleVavHandling && !nounExceptionType.getTransliterated().startsWith("ww"))
							inflectedItem = "w" + nounExceptionType.getTransliterated();
						else
							inflectedItem = nounExceptionType.getTransliterated();
						surface = Translate.Eng2Heb(inflectedItem);
						register = exceptionRegister;
						spelling = exceptionSpelling;
						//System.out.println("exception transliterated  =" + inflectedItem);
						populateDatabase();
						match = true;
						//System.out.println("----------------------------");
						return match;
					}
					else if (action.equals("remove")) 
					{
						inflectedItem = "";
						surface = "";
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
	private void analyzeNoun() 
	{
		super.analyseItem();
		plural = item.getNoun().getPlural();
		lexiconGender = gender = item.getNoun().getGender();
		feminine = item.getNoun().getFeminine();
		lexiconNumber = number = item.getNoun().getNumber();
		inflectionBase = item.getNoun().getInflectionBase();
		suffixFunction = "unspecified";
		basePos = item.getPos();
		surface = undot;
		inflectedItem = transliterated;
		construct = "false";
		definitnessVal = "tf";
		PGN = "unspecified";
		inflectConstructS = item.getNoun().isInflectConstructS();
		inflectPossessiveS = item.getNoun().isInflectPossessiveS();
		inflectConstructP = item.getNoun().isInflectConstructP();
		inflectPossessiveP = item.getNoun().isInflectPossessiveP();
		isDual = item.getNoun().isDual();
		hebForeign = item.getNoun().isForeign();
	}

	private boolean generateConstruct() throws Exception 
	{
		//System.out.println("----------------------------------------------------");
		//System.out.println("----------------- generateConstruct ----------------");
		boolean returnVal = true;
		
		boolean matchRemove = replaceRemoveException("unspecified", gender,number, "true", removeExceptionList, "remove");
		boolean matchReplace = replaceRemoveException("unspecified", gender,number, "true", replaceExceptionList, "replace");

		if (!matchReplace && !matchRemove) 
		{
			//System.out.println("No exception handling");
			//System.out.println(number);
			//System.out.println(gender);
			if (inflectConstructP && (number.equals("plural") || number.equals("dual") || number.equals("dual and plural"))) 
			{
				// טיפול בגדי,חי,חיים,שה
				//					//טיפול ביד
				//					
				//System.out.println("(F) generateConstruct() trying to find a rule");
				findRule(inflectedItem, "", "constructMasculinePlural" + basePos, 2);
				//						handle גדי
				if (gender.equals("masculine")
						&& baseTransliteratedItem.endsWith("i")
						&& !baseTransliteratedItem.endsWith("ai")
						//handle שה
						|| (baseTransliteratedItem.endsWith("h") && baseTransliteratedItem.length() == 2))
					inflectedItem = inflectedItem + "i";
				if (inflectedItem.equals(transliterated))
					surface = undot;
				else
					surface = Translate.Eng2Heb(inflectedItem);
				
				//System.out.println("(F) generateConstruct() : inflectedItem = "  + inflectedItem);
				populateDatabase();

			} 
			else if (inflectConstructS && (gender.equals("feminine") || gender.equals("masculine and feminine"))) 
			{
				findRule(inflectedItem, "", "constructFeminineSingular" + basePos, 2);
				//				//deny problems of translation for אמ"נ and אמ"ן פילטרופ
				// סירופ
				if (inflectedItem.endsWith("n") || inflectedItem.endsWith("p")
						|| inflectedItem.endsWith("c")
						&& inflectedItem.equals(transliterated))
					{surface = undot;}
				else
					{surface = Translate.Eng2Heb(inflectedItem);}
				
				// טיפול בקריה יש צורך ליצור קריית
				if (inflectedItem.endsWith("ih"))
				{
					//System.out.println("(F) generateConstruct() : GOING TO CREATE DOUBLE i");
					//System.out.println("(F) generateConstruct() : surface = "  + surface);
				}
				
				//System.out.println("(F)(1) generateConstruct() : surface = "  + surface);
				populateDatabase();
			} 
			else if (inflectConstructS && gender.equals("masculine") && number.equals("singular")) 
			{
				//				//deny problems of translation for אמ"נ and אמ"ן פילטרופ
				// סירופ
				if ((inflectedItem.endsWith("n") || inflectedItem.endsWith("p") || inflectedItem.endsWith("c"))
						&& inflectedItem.equals(transliterated))
					{surface = undot;}
				else
					{surface = Translate.Eng2Heb(inflectedItem);}
				
				//System.out.println("(F) generateConstruct() : surface = "  + surface);
				populateDatabase();
			}
			else if (gender.equals("feminine"))
			{	// טיפול בקריה יש צורך ליצור קריית
				if (inflectedItem.endsWith("ih"))
				{
					//System.out.println("(F) generateConstruct() : GOING TO CREATE DOUBLE i");
					//System.out.println("(F) generateConstruct() : surface = "  + surface);
				}
			}
		}
		return returnVal;
	}
	
	

	protected void generatePossessive() throws Exception 
	{
		if (inflectPossessiveP || inflectPossessiveS) 
		{
			boolean iiSuffix = false;

			//System.out.println("----------------------------------------");
			//System.out.println("----------generatePossessive------------");

			//when the base for possessive generation is not construct - the
			// base
			// is inflections base
			//System.out.println("inflectedItem sent to rule handling =" + inflectedItem);
			if (inflectedItem.substring(inflectedItem.length() - 2).equals("ii"))
				iiSuffix = true;
			if (!inflectionBase.equals("")) 
			{
				inflectionBaseHandling();
				inflectedItem = inflectionBase;
			}
			final String colloquialPluralBase = inflectedItem;

			if ((inflectPossessiveP && (number.equals("plural") || number.equals("dual and plural")) && iiSuffix))
				findRule(inflectedItem, "ii", "possessivePlural" + basePos, 2);

			else if (inflectPossessiveP && (number.equals("plural") || number.equals("dual and plural")))

				findRule(inflectedItem, "", "possessivePlural" + basePos, 2);
			else if (inflectPossessiveS) 
			{
				findRule(inflectedItem, "", "possessiveSingular" + basePos, 2);
			}
			//System.out.println("inflectedItem after rule handling=" + inflectedItem);
			if ((number.equals("singular") && inflectPossessiveS)
					|| ((number.equals("plural") && inflectPossessiveP))
					|| ((number.equals("dual and plural") && inflectPossessiveP))) 
			{
				StringTokenizer stPossessive = new StringTokenizer(inflectedItem, ",");
				StringTokenizer stPGN = new StringTokenizer(PGNTokens10, ",");
				StringTokenizer stPerson = new StringTokenizer(personTokens10,",");
				StringTokenizer stNumber = new StringTokenizer(numberTokens10,",");
				StringTokenizer stGender = new StringTokenizer(genderTokens10,",");
				while (stPGN.hasMoreTokens()) 
				{
					PGN = stPGN.nextToken();
					inflectedItem = stPossessive.nextToken();
					if (!replaceRemoveException(PGN, gender, number, "false",
							removeExceptionList, "remove") && !replaceRemoveException(PGN, gender, number, "false",replaceExceptionList, "replace")) 
					{
						//In case of inflectionBase - there is a need for
						// fixing
						// the
						// generated item by rules
						//System.out.println();
						//System.out.println("inflectedItem =" + inflectedItem);
						surface = Translate.Eng2Heb(inflectedItem);
						//System.out.println("surface =" + surface);
						//System.out.println();
						populateDatabase();
						if (!iiSuffix)
							generatePossessivecolloquial(colloquialPluralBase);
					}
				}
			}
		}
	}

	private void generatePossessivecolloquial(String colloquialPluralBase) throws Exception 
	{
		String originalSpelling = spelling;
		//    	handling exceptions for feminine, plural
		if (number.equals("plural")) 
		{
			if (PGN.equals("1p/MF/Sg")) 
			{
				//System.out.println("colloquialPluralBase=" + colloquialPluralBase);
				if (colloquialPluralBase.charAt(colloquialPluralBase.length() - 1) != 'i')
					inflectedItem = colloquialPluralBase + "i";
				else
					inflectedItem = colloquialPluralBase;
				spelling = IRREGULAR_SPELLING;
				surface = Translate.Eng2Heb(inflectedItem);
				populateDatabase();
			} 
			else if (PGN.equals("2p/F/Sg")) 
			{
				if (colloquialPluralBase.charAt(colloquialPluralBase.length() - 1) != 'i')
					inflectedItem = colloquialPluralBase + "ik";
				else
					inflectedItem = colloquialPluralBase + "k";
				spelling = IRREGULAR_SPELLING;
				surface = Translate.Eng2Heb(inflectedItem);
				populateDatabase();
			}
			spelling = originalSpelling;
		}
	}

	private void generatePlural(String pluralSuffix) throws Exception 
	{
		//System.out.println("|||||||||||||||||||||||||||||||||||||");
		//System.out.println("||||||||||generatePlural||||||||||||");
		//boolean returnVal = true;
		String action = "";
		if (!replaceRemoveException("unspecified", gender, "plural",
				"false", removeExceptionList, "remove") && !replaceRemoveException("unspecified", gender, "plural", "false",
				replaceExceptionList, "replace")) 
		{
			action = identifyPluralAction(pluralSuffix);
			if (!action.equals("")) 
			{
				//System.out.println("plural base = " + inflectedItem);
				//comment because of משתה - משתאות
				//צריך לוודא שכפיתה לא מתקלקל - מתנהגים שונה כי המין שונה
				if (inflectedItem.endsWith("th") && (gender.equals("feminine")))
					findRule(inflectedItem, "th", action, pluralSuffixMaxLength);
				else
					findRule(inflectedItem, pluralSuffix, action,pluralSuffixMaxLength);
				//System.out.println("plural inflectedItem = " + inflectedItem);
				surface = Translate.Eng2Heb(inflectedItem);
				//if (gender.equals("feminine")
				//		&& !lexiconFemininePlural.equals("")
				//		&& lexiconFemininePlural.equals(inflectedItem))
				//	returnVal = false;
				//else {
				//	if (gender.equals("feminine")
				//			&& lexiconFemininePlural.equals(""))
				//		lexiconFemininePlural = inflectedItem;
				populateDatabase();
				//}
				if (pluralBase.equals(""))
					pluralBase = inflectedItem;
			}
		} 
		else 
		{
			if (pluralBase.equals(""))
				pluralBase = inflectedItem;
		}
		//return returnVal;

	}

	private void generateFeminine() throws Exception 
	{
		//System.out.println("|||||||||||||||||||||||||||||||||||||");
		//System.out.println("||||||||||generateFeminine||||||||||||");
		if (!replaceRemoveException("unspecified", "feminine", number,
				"unspecified", removeExceptionList, "remove") && !replaceRemoveException("unspecified", "feminine", number,
				"unspecified", replaceExceptionList, "replace")) 
		{
			String action = identifyFeminineAction();
			findRule(inflectedItem, feminine, action, 1);
			surface = Translate.Eng2Heb(inflectedItem);
			populateDatabase();
		}
	}
	//--------------------------------------------------------------------
	public void generateInflects() throws Exception 
	{
		//Get lexicon Item (for which pos = noun ) attributes
		//The lexicon item can have any attributes for example
		//single masculine/single feminine/ plural feminine etc
		analyzeNoun();

		//Get list of exceptions of type replace for this lexicon Item
		getException("replace");
		//Get list of exceptions of type remove for this lexicon Item
		getException("remove");

		handleDoublingVavForNounsStartsWithVav();
		//inflect lexicon item
		//inflectLexiconItem();

		//inflect exception type items of action add - if exist
		inflectAddExceptions();
	}
    //--------------------------------------------------------------------
	public void generateDual() throws Exception 
	{
		//System.out.println("|||||||||||||||||||||||||||||||||||||");
		//System.out.println("||||||||||generateDual||||||||||||");
		inflectedItem = inflectedItem + "iim";
		surface = Translate.Eng2Heb(inflectedItem);
		populateDatabase();
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
	 * @param PGN -
	 *            person/gender/number value of the generated item
	 * @param suffixFunction
	 *            -A flag indication whether a possessive actin is done
	 */
	private void setAttributes(String gender, String number, String construct,String definitnessVal, String inflectedItem, String PGN,String suffixFunction) 
	{
		this.inflectedItem = inflectedItem;
		this.number = number;
		this.gender = gender;
		this.construct = construct;
		this.definitnessVal = definitnessVal;
		this.PGN = PGN;
		this.suffixFunction = suffixFunction;
	}

	//in case prefix accompanied noun which starts with a single vav the vava
	// need to be doubled
	private void handleDoublingVavForNounsStartsWithVav() throws Exception 
	{
		if (transliterated.startsWith("w") && !transliterated.startsWith("ww")) 
		{
			//System.out.println("|||||||||||||||||||||||||||||||||||||");
			//System.out.println("||||||||||double VAV for nouns starts with single VAV||||||||||||");

			inflectLexiconItem();
			//double VAV
			analyzeNoun();
			transliterated = "w" + transliterated;
			surface = Translate.Eng2Heb(transliterated);
			inflectedItem = transliterated;
			//ווילון - הכפלת וו ללו ת
			spelling = IRREGULAR_SPELLING;
			pluralBase = "";
			doebleVavHandling = true;
			inflectLexiconItem();
		} 
		else
			inflectLexiconItem();
	}

	/**
	 * This method is used by noun, adjective, quantifier,pronoun <br>
	 * It is used to generate h+inflectedItem because of special handling for
	 * nouns starts with ww - we don't use the function from itemGen
	 * 
	 * @throws Exception
	 */
	protected void addH() throws Exception 
	{
		String originalSpelling = spelling;
		String origInflectedItem = inflectedItem;
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

		inflectedItem = origInflectedItem;
		spelling = originalSpelling;
	}

	private void inflectLexiconItem() throws Exception 
	{
		//Populate database with the original lexicon Item
		//מנגנון שמאפשר לאכלס בצורת הנסמך ללא צורת הבסיס עבור צורות שיופיעו רק
		// בנמך למשל שאט
		if (!replaceRemoveException("unspecified", lexiconGender,lexiconNumber, "false", removeExceptionList, "remove")) 
		{
			populateDatabase();

			//Set attributes before generating the h+lexicon Item
			setAttributes(lexiconGender, lexiconNumber, "false", "tt",transliterated, "unspecified", "unspecified");
			addH();
		}

		if (isDual) 
		{
			setAttributes(lexiconGender, "dual", "false", "tf", transliterated,"unspecified", "unspecified");
			generateDual();
			setAttributes(lexiconGender, "dual", "false", "tt", inflectedItem,"unspecified", "unspecified");
			addH();
		}

		//shlomo doesn't inflect dual - should look at it carefully
		//In future we may inflect dual items
		if (lexiconNumber.equals("dual"))
			return;

		//Set attributes before generating the construct form

		setAttributes(lexiconGender, lexiconNumber, "true", "tf",transliterated, "unspecified", "unspecified");
		generateConstruct();

		//Set attributes before generating the possessive form
		//Possessive are created from the construct
		//The order of generating possessive from construct - is important
		setAttributes(lexiconGender, lexiconNumber, "false", "tf",inflectedItem, "unspecified", "possessive");
		generatePossessive();

		//The lexicon item can have any attributes values
		//If the lexicon item is already feminine or there is no feminine
		// form
		//we will not generate the feminine form - so the attributes need
		// to be
		// checked
		if (lexiconGender.equals("masculine") && !feminine.equals("unspecified")) 
		{
			setAttributes("feminine", lexiconNumber, "false", "tf",transliterated, "unspecified", "unspecified");
			generateFeminine();
			feminineBase = inflectedItem;
		}

		//The lexicon item can have any attributes values
		//If the lexicon item is plural or there is no plural form of it
		//we will not generate the plural form - so the attributes need to
		// be
		// checked
		if (!plural.equals("unspecified") && lexiconNumber.equals("singular")) 
		{
			setAttributes(lexiconGender, "plural", "false", "tf",transliterated, "unspecified", "unspecified");
			generatePlural(plural);
		}

		/////////////////////////////////////////////////////////////
		// Feminine form inflections: plural,construct,possessive
		/////////////////////////////////////////////////////////////

		//Inflecting the single feminine form
		if (!feminineBase.equals("")) 
		{
			//System.out.println("**************************************");
			//System.out.println("**********feminine form inflections**********");

			surface = Translate.Eng2Heb(feminineBase);
			setAttributes("feminine", lexiconNumber, "false", "tt",feminineBase, "unspecified", "unspecified");
			addH();

			setAttributes("feminine", "singular", "true", "tf", feminineBase,"unspecified", "unspecified");
			boolean returnVal = generateConstruct();

			if (returnVal) 
			{
				surface = Translate.Eng2Heb(inflectedItem);
				setAttributes("feminine", "singular", "false", "tf",inflectedItem, "unspecified", "possessive");
				generatePossessive();
			}

			surface = Translate.Eng2Heb(feminineBase);
			setAttributes("feminine", "plural", "false", "tf", feminineBase,"unspecified", "unspecified");
			generatePlural("wt");

			pluralFeminineBase = inflectedItem;
			if (pluralFeminineBase.length() > 0) 
			{
				surface = Translate.Eng2Heb(pluralFeminineBase);
				setAttributes("feminine", "plural", "false", "tt",pluralFeminineBase, "unspecified", "unspecified");
				addH();

				setAttributes("feminine", "plural", "true", "tf",pluralFeminineBase, "unspecified", "unspecified");
				generateConstruct();

				surface = Translate.Eng2Heb(inflectedItem);
				setAttributes("feminine", "plural", "false", "tf",inflectedItem, "unspecified", "possessive");
				generatePossessive();
			}
		}

		/////////////////////////////////////////////////////////////
		// plural form inflections: construct,possessive
		/////////////////////////////////////////////////////////////

		//Inflecting the singular form
		if (!pluralBase.equals("") && !plural.equals("unspecified") && !lexiconNumber.equals("plural")) 
		{
			//System.out.println("**************************************");
			//System.out.println("**********plural form inflections**********");
			//System.out.println("pluralBase = " + pluralBase);

			surface = Translate.Eng2Heb(pluralBase);
			setAttributes(lexiconGender, "plural", "false", "tt", pluralBase,"unspecified", "unspecified");
			addH();

			setAttributes(lexiconGender, "plural", "true", "tf", pluralBase,"unspecified", "unspecified");
			generateConstruct();

			inflectionBase = "";
			surface = Translate.Eng2Heb(inflectedItem);
			setAttributes(lexiconGender, "plural", "false", "tf",inflectedItem, "unspecified", "possessive");
			generatePossessive();
		}
	}
}
