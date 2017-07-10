/*
 * Created on 2/01/2011
 * by yossi jacob
 * 
 */
package lexicon.generate;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.StringTokenizer;

import lexicon.contents.Connected;
import lexicon.contents.exception_types.MultiWordFrozenExceptionType;
import lexicon.contents.exception_types.MultiWordNounAdjectiveExceptionType;
import lexicon.contents.exception_types.NounExceptionType;

import lexicon.contents.types.ItemType;
import lexicon.stringUtils.Translate;

/**
 * @author daliabo
 * 
 *         TODO To change the template for this generated type comment go to
 *         Window - Preferences - Java - Code Style - Code Templates
 */
public class MultiWordNounAdjectiveGen extends ItemGen {
	static Connection connection = null;

	String definiteness;
	String mwPos = "";
	String exceptionNoun = "";
	String exceptionAdjective = "";
	String feminineNoun = "";
	String feminineAdjective = "";
	String pluralMaleNoun = "";
	String pluralMaleAdjective = "";
	String pluralFemaleNoun = "";
	String pluralFemaleAdjective = "";
	String exceptionTransliterated = "";
	String undottedLexiconItem = "";
	String surfaceOrg = "";
	String registerOrg = "";
	String spellingOrg = "";
	String inflectedItemOrg = "";
	String genderOrg = "";
	String numberOrg = "";
	String PGN_Org = "";
	String definitenessOrg = "";
	String dottedOrg = "";
	String undottedOrg = "";
	String feminineOrg = "";
	String pluralOrg = "";
	boolean inflectPossessiveSOrg = false;
	boolean inflectPossessivePOrg = false;
	String transliteratedOrg = "";

	PopulateMWE popualteMWE = new PopulateMWE();

	private boolean inflectPossessiveP;

	private boolean inflectPossessiveS;

	// -------------------------------------------------------------------------------------------------------------------------------------
	public MultiWordNounAdjectiveGen(ItemType item) {
		super(item);
	}

	// -------------------------------------------------------------------------------------------------------------------------------------
	/**
	 * This method gets the exception item list of action add for the lexicon item
	 * 
	 * @throws Exception
	 */
	private void inflectAddExceptions() throws Exception {
		super.getException("add");
		if (addExceptionList.size() > 0) {
			// System.out.println("EXCEPTIONS FOUND");
			analyseExceptionList();
		}
	}

	// -------------------------------------------------------------------------------------------------------------------------------------
	private boolean addExceptionListHandling(MultiWordNounAdjectiveExceptionType multiWordNounAdjectiveExceptionType)
			throws Exception {
		try {
			String exceptionGender = multiWordNounAdjectiveExceptionType.getGender();
			String exceptionNumber = multiWordNounAdjectiveExceptionType.getNumber();
			String exceptionPGN = multiWordNounAdjectiveExceptionType.getPossessive();
			String exceptionSpelling = multiWordNounAdjectiveExceptionType.getSpelling();
			String exceptionRegister = multiWordNounAdjectiveExceptionType.getRegister();
			exceptionTransliterated = multiWordNounAdjectiveExceptionType.getTransliterated();
			exceptionTransliterated = exceptionTransliterated.replaceAll("&#39;", "'");
			exceptionTransliterated = exceptionTransliterated.replaceAll("&#60;", "`");
			String exceptionDefiniteness = multiWordNounAdjectiveExceptionType.getDefiniteness();
			String exceptionDotted = multiWordNounAdjectiveExceptionType.getDotted();
			String exceptionUndotted = multiWordNounAdjectiveExceptionType.getUndotted();
			String exceptionFeminine = multiWordNounAdjectiveExceptionType.getFeminine();
			String exceptionPlural = multiWordNounAdjectiveExceptionType.getPlural();
			boolean exceptionInflectPossessiveS = multiWordNounAdjectiveExceptionType.isInflectPossessiveS();
			boolean exceptionInflectPossessiveP = multiWordNounAdjectiveExceptionType.isInflectPossessiveP();

			// System.out.println("exception action = add ");
			// System.out.println("exception transliterated ="+ exceptionTransliterated);

			StringTokenizer st = new StringTokenizer(exceptionTransliterated);
			String transliteratedPart1 = st.nextToken();
			String transliteratedPart2 = "";
			if (st.hasMoreTokens()) {
				transliteratedPart2 = st.nextToken();
			}

			String inflectedItemOrg = transliteratedPart1;

			if (transliteratedPart2.equals("")) {
				StringTokenizer st1 = new StringTokenizer(transliteratedOrg);
				String part2 = st1.nextToken();
				if (st1.hasMoreTokens()) {
					part2 = st1.nextToken();
					exceptionTransliterated = exceptionTransliterated + " " + part2;
					transliteratedPart2 = part2;
				}

				st1 = new StringTokenizer(surfaceOrg);
				part2 = st1.nextToken();
				if (st1.hasMoreTokens()) {
					part2 = st1.nextToken();
					exceptionUndotted = exceptionUndotted + " " + part2;
				}

				st1 = new StringTokenizer(dottedOrg);
				part2 = st1.nextToken();
				if (st1.hasMoreTokens()) {
					part2 = st1.nextToken();
					exceptionDotted = exceptionDotted + " " + part2;
				}
			}

			setAttributes(exceptionUndotted, exceptionRegister, exceptionSpelling, transliteratedPart1, exceptionGender,
					exceptionNumber, exceptionPGN, exceptionDefiniteness, exceptionDotted,
					URLEncoder.encode(exceptionUndotted, "UTF-8"), exceptionFeminine, exceptionPlural,
					exceptionInflectPossessiveS, exceptionInflectPossessiveP, exceptionTransliterated);

			String restTransliterated = transliteratedPart2;
			String restSurface = Translate.Eng2Heb(transliteratedPart2);
			while (st.hasMoreTokens()) {
				String currentToken = st.nextToken();
				restTransliterated += " " + currentToken;
				restSurface += " " + Translate.Eng2Heb(currentToken);
			}

			// We use the same generation methods for the lexicon item and for
			// the exception lexicon item
			// replaceExceptionList is not relevant for the exception type
			// lexicon item (action add)
			replaceExceptionList = null;
			removeExceptionList = null;

			// Populate database with the original lexicon Item
			if (!replaceRemoveException("unspecified", lexiconGender, lexiconNumber, "false", removeExceptionList,
					"remove")) {
				replaceRemoveException("unspecified", lexiconGender, lexiconNumber, "false", replaceExceptionList,
						"replace");
				popualteMWE.popualteMWETables(baseTransliteratedItem, undottedLexiconItem, '1', mwPos, dottedLexiconItem,
						id, "unspecified", PGN, definitnessVal, gender, number, spelling, register, transliterated, surface);
				setDefinitenessAndInflectPossessive(inflectedItem, restTransliterated, restSurface, transliteratedPart2);
			}

			if ((gender.equals("masculine")) && (!feminine.equals("unspecified"))) {
				gender = "feminine";
				generateFeminine(restTransliterated, restSurface);
				generateConstruct(restTransliterated, restSurface);
				setDefinitenessAndInflectPossessive(inflectedItem, restTransliterated, restSurface, transliteratedPart2);
				gender = "masculine";
				inflectedItem = inflectedItemOrg;
			}

			if (type.equals("NNA") && gender.equals("masculine")) {
				restTransliterated += "im";
				restSurface += "ים";
			}

			if (!plural.equals("unspecified") && lexiconNumber.equals("singular")) {
				inflectedItem = inflectedItemOrg;
				number = "plural";
				generatePlural(plural, restTransliterated, restSurface);
				generateConstruct(restTransliterated, restSurface);
				setDefinitenessAndInflectPossessive(inflectedItem, restTransliterated, restSurface, transliteratedPart2);
			}

			if ((gender.equals("masculine")) && (!feminine.equals("unspecified"))) {
				inflectedItem = inflectedItemOrg;
				gender = "feminine";
				generatePlural("wt", restTransliterated, restSurface);
				generateConstruct(restTransliterated, restSurface);
				setDefinitenessAndInflectPossessive(inflectedItem, restTransliterated, restSurface, transliteratedPart2);
				gender = "masculine";
			}

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return true;
	}

	// -------------------------------------------------------------------------------------------------------------------------------------
	/**
	 * This method is used for handling exceptions for which action = add <br>
	 * This method scan the add exception list - gets the attributes and populate
	 * the databse Each item from the add exception is inflected <br>
	 * That is - we generate the feminine, plural construct, possessive forms for it
	 * 
	 * @param exceptionList
	 * @throws Exception
	 */
	private void analyseExceptionList() throws Exception {
		int size = addExceptionList.size();
		// System.out.println("addExceptionList size" + size);
		for (int i = 0; i < size; i++) {
			MultiWordNounAdjectiveExceptionType multiWordNounAdjectiveExceptionType = new MultiWordNounAdjectiveExceptionType();
			multiWordNounAdjectiveExceptionType.open(((Integer) addExceptionList.get(i)).intValue());

			// System.out.println("i=" + i);
			addExceptionListHandling(multiWordNounAdjectiveExceptionType);
			/*
			 * if (exceptionTransliterated.startsWith("w") &&
			 * !exceptionTransliterated.startsWith("ww")) { inflectedItem = "w" +
			 * nounExceptionType.getTransliterated(); inflectedItem =
			 * inflectedItem.replaceAll("&#39;", "'"); inflectedItem =
			 * inflectedItem.replaceAll("&#60;", "`"); surface = "ו" +
			 * nounExceptionType.getUndotted(); spelling = IRREGULAR_SPELLING;
			 * addExceptionListHandling(MultiWordNounExceptionType); }
			 */
		}
	}

	// -------------------------------------------------------------------------------------------------------------------------------------
	private boolean replaceRemoveException(String PGN, String gender, String number, String construct,
			List exceptionList, String action) throws Exception {
		boolean match = false;
		if (exceptionList != null) {
			for (int i = 0; i < exceptionList.size(); i++) {
				MultiWordNounAdjectiveExceptionType multiWordNounAdjectiveExceptionType = new MultiWordNounAdjectiveExceptionType();
				multiWordNounAdjectiveExceptionType.open(((Integer) exceptionList.get(i)).intValue());
				String exceptionGender = multiWordNounAdjectiveExceptionType.getGender();
				String exceptionNumber = multiWordNounAdjectiveExceptionType.getNumber();
				String exceptionPGN = multiWordNounAdjectiveExceptionType.getPossessive();
				String exceptionSpelling = multiWordNounAdjectiveExceptionType.getSpelling();
				String exceptionRegister = multiWordNounAdjectiveExceptionType.getRegister();
				exceptionTransliterated = multiWordNounAdjectiveExceptionType.getTransliterated();

				// exception fields for add
				String exceptionDefiniteness = multiWordNounAdjectiveExceptionType.getDefiniteness();
				String exceptionDotted = multiWordNounAdjectiveExceptionType.getDotted();
				String exceptionUndotted = multiWordNounAdjectiveExceptionType.getUndotted();
				String exceptionFeminine = multiWordNounAdjectiveExceptionType.getFeminine();
				String exceptionPlural = multiWordNounAdjectiveExceptionType.getPlural();
				boolean exceptionInflectPossessiveS = multiWordNounAdjectiveExceptionType.isInflectPossessiveS();
				boolean exceptionInflectPossessiveP = multiWordNounAdjectiveExceptionType.isInflectPossessiveP();

				/*
				 * System.out.println("----------------------------"); System.out.println(action
				 * + " handling"); System.out.println("exceptionGender =" + exceptionGender);
				 * System.out.println("gender =" + gender);
				 * System.out.println("exceptionNumber =" + exceptionNumber);
				 * System.out.println("number =" + number); System.out.println("exceptionPGN ="
				 * + exceptionPGN); System.out.println("PGN =" + PGN);
				 */
				if (exceptionGender.equals(gender) && exceptionNumber.equals(number) && exceptionPGN.equals(PGN)) {
					if (action.equals("replace")) {
						// System.out.println("exception action = replace ");
						surface = Translate.Eng2Heb(exceptionTransliterated);
						register = exceptionRegister;
						spelling = exceptionSpelling;
						// System.out.println("exception transliterated ="+ exceptionTransliterated);
						inflectedItem = exceptionTransliterated;

						StringTokenizer st = new StringTokenizer(inflectedItem);
						exceptionNoun = st.nextToken();
						exceptionAdjective = st.nextToken();

						match = true;
						// System.out.println("----------------------------");
						return match;
					} else if (action.equals("remove")) {
						inflectedItem = "";
						surface = "";
						// System.out.println("exception action = remove ");
						match = true;
						// System.out.println("----------------------------");
						return match;
					}
					/*
					 * }else if (action.equals("add")) {
					 * System.out.println("exception action = add "); //surface =
					 * Translate.Eng2Heb(exceptionTransliterated); //register = exceptionRegister;
					 * //spelling = exceptionSpelling;
					 * System.out.println("exception transliterated  =" + exceptionTransliterated);
					 * //inflectedItem = exceptionTransliterated;
					 * 
					 * StringTokenizer st = new StringTokenizer(exceptionTransliterated); String
					 * transliteratedPart1 = st.nextToken(); String transliteratedPart2 =
					 * st.nextToken();
					 * 
					 * setAttributes(exceptionUndotted, exceptionRegister, exceptionSpelling,
					 * transliteratedPart1, exceptionGender, exceptionNumber, exceptionPGN,
					 * exceptionDefiniteness, exceptionDotted, URLEncoder.encode(exceptionUndotted,
					 * "UTF-8"), exceptionFeminine, exceptionPlural, exceptionInflectPossessiveS,
					 * exceptionInflectPossessiveP);
					 * 
					 * match = true; System.out.println("----------------------------"); return
					 * match; }
					 */
				}
			}
		}
		// System.out.println("----------------------------");
		return match;
	}

	// -------------------------------------------------------------------------------------------------------------------------------------
	private void generatePlural(String pluralSuffix, String restTransliterated, String restSurface) throws Exception {
		// System.out.println("|||||||||||||||||||||||||||||||||||||");
		// System.out.println("||||||||||generatePlural||||||||||||");

		String action = "";
		// String pluralBase = "";
		if (!replaceRemoveException("unspecified", gender, "plural", "false", removeExceptionList, "remove")
				&& !replaceRemoveException("unspecified", gender, "plural", "false", replaceExceptionList, "replace")) {
			action = identifyPluralAction(pluralSuffix);
			action = action.replace("multiWord", "");
			if (!action.equals("")) {
				// System.out.println("plural base = " + inflectedItem);
				// comment because of משתה - משתאות
				// צריך לוודא שכפיתה לא מתקלקל - מתנהגים שונה כי המין שונה
				if (inflectedItem.endsWith("th") && (gender.equals("feminine"))) {
					findRule(inflectedItem, "th", action, pluralSuffixMaxLength);
				} else {
					findRule(inflectedItem, pluralSuffix, action, pluralSuffixMaxLength); // removed by yossi - beacause it
																													// doesnt work 01.01.2011
					// inflectedItem += "im";
				}
				// System.out.println("plural inflectedItem = " + inflectedItem);
				surface = Translate.Eng2Heb(inflectedItem);
			}
		} else
			inflectedItem = exceptionTransliterated;
		// generateConstruct(restTransliterated, restSurface);
	}

	// -------------------------------------------------------------------------------------------------------------------------------------
	private void generateFeminine(String restTransliterated, String restSurface) throws Exception {
		// System.out.println("|||||||||||||||||||||||||||||||||||||");
		// System.out.println("||||||||||generateFeminine||||||||||||");
		if (!replaceRemoveException("unspecified", "feminine", number, "unspecified", removeExceptionList, "remove")
				&& !replaceRemoveException("unspecified", "feminine", number, "unspecified", replaceExceptionList,
						"replace")) {
			String action = identifyFeminineAction();
			action = action.replace("multiWord", "");
			action = action.replace("Noun", mwPos);
			findRule(inflectedItem, feminine, action, 1);
			surface = Translate.Eng2Heb(inflectedItem);
			// generateConstruct(restTransliterated, restSurface);
		}
	}

	// -------------------------------------------------------------------------------------------------------------------------------------
	private void generateConstruct(String restTransliterated, String restSurface) throws Exception {
		boolean matchRemove = replaceRemoveException("unspecified", gender, number, "true", removeExceptionList,
				"remove");

		if (!matchRemove) {
			inflectConstruct();
			definitnessVal = "tf";

			String mwUndotted = Translate.Eng2Heb(inflectedItem) + " " + restSurface;
			String mwTransliterated = inflectedItem + " " + restTransliterated;

			popualteMWE.popualteMWETables(baseTransliteratedItem, undottedLexiconItem, '1', mwPos, dottedLexiconItem, id,
					"unspecified", PGN, definitnessVal, gender, number, spelling, register, mwTransliterated, mwUndotted);
		}
	}

	// -------------------------------------------------------------------------------------------------------------------------------------
	private void analyse() {
		super.analyseItem();
		type = item.getMultiWordNounAdjective().getType();
		mwPos = item.getMultiWordNounAdjective().getMwPos();

		suffixFunction = "unspecified";
		surface = undot;
		lexiconNumber = number = item.getMultiWordNounAdjective().getNumber();
		lexiconGender = gender = item.getMultiWordNounAdjective().getGender();
		/*
		 * feminine = item.getMultiWordNounAdjective().getFeminine(); plural =
		 * item.getMultiWordNounAdjective().getPlural();
		 */

		feminineNoun = item.getMultiWordNounAdjective().getFeminineNoun();
		feminineAdjective = item.getMultiWordNounAdjective().getFeminineAdjective();

		pluralMaleNoun = item.getMultiWordNounAdjective().getPluralMaleNoun();
		pluralMaleAdjective = item.getMultiWordNounAdjective().getPluralMaleAdjective();

		pluralFemaleNoun = item.getMultiWordNounAdjective().getPluralFemaleNoun();
		pluralFemaleAdjective = item.getMultiWordNounAdjective().getPluralFemaleAdjective();

		construct = "absolute";
		PGN = "unspecified";
		basePos = item.getPos();
		inflectionBase = item.getMultiWordNounAdjective().getInflectionBase();
		if (!inflectionBase.equals("")) {
			try {
				inflectionBaseHandling();
			} catch (UnsupportedEncodingException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		inflectPossessiveP = item.getMultiWordNounAdjective().isInflectPossessiveP();
		inflectPossessiveS = item.getMultiWordNounAdjective().isInflectPossessiveS();
		definitnessVal = "tf";
		definiteness = item.getMultiWordNounAdjective().getDefiniteness();
		if (definiteness.equals("externallyDefinited"))
			definitnessVal = "tt";
		try {
			undottedLexiconItem = URLEncoder.encode(item.getUndotted(), "UTF-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	// -------------------------------------------------------------------------------------------------------------------------------------
	protected void inflectConstruct() throws Exception {
		try {
			// System.out.println("|||||||||||||||||||||||||||||||||||||");
			// System.out.println("||||||||||generateConstruct||||||||||||");
			construct = "construct";

			StringTokenizer st = new StringTokenizer(baseTransliteratedItem);
			String baseTransliteratedItem1 = st.nextToken();

			/*
			 * boolean matchRemove = replaceRemoveException("unspecified", gender, number,
			 * "true", removeExceptionList, "remove"); boolean matchReplace =
			 * replaceRemoveException("unspecified", gender, number, "true",
			 * replaceExceptionList, "replace");
			 */

			// if (!matchReplace && !matchRemove) {
			if ((number.equals("plural") || number.equals("dual") || number.equals("dual and plural"))) {
				findRule(inflectedItem, "", "constructMasculinePlural" + mwPos, 2);

				// handle גדי
				if (gender.equals("masculine") && baseTransliteratedItem1.endsWith("i")
						&& !baseTransliteratedItem1.endsWith("ai")
						// handle שה
						|| (baseTransliteratedItem1.endsWith("h") && baseTransliteratedItem1.length() == 2))
					inflectedItem = inflectedItem + "i";
				if (inflectedItem.equals(transliterated))
					surface = undot;
				else
					surface = Translate.Eng2Heb(inflectedItem);

			} else if ((gender.equals("feminine") || gender.equals("masculine and feminine"))) {
				findRule(inflectedItem, "", "constructFeminineSingular" + mwPos, 2);
				if (inflectedItem.endsWith("n") || inflectedItem.endsWith("p")
						|| inflectedItem.endsWith("c") && inflectedItem.equals(transliterated))
					surface = undot;
				else
					surface = Translate.Eng2Heb(inflectedItem);
			} else if (gender.equals("masculine") && number.equals("singular")) {
				if ((inflectedItem.endsWith("n") || inflectedItem.endsWith("p") || inflectedItem.endsWith("c"))
						&& inflectedItem.equals(transliterated))
					surface = undot;
				else
					surface = Translate.Eng2Heb(inflectedItem);
			}
			// }

		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		construct = "absolute";
	}

	// protected void inflectPlural(String transliterated2, String
	// transliterated1){
	// try {
	// construct="absolute";
	// transliterated1=transliterated1+plural;
	// transliterated = transliterated1 + " " + transliterated2;
	// popualteMWE.popualteMWETables(transliterated, surface, '1',
	// "noun", "unspecified", id, "unspecified", PGN, construct, spelling,
	// register);
	// } catch (UnsupportedEncodingException e) {
	// // TODO Auto-generated catch block
	// e.printStackTrace();
	// } catch (SQLException e) {
	// // TODO Auto-generated catch block
	// e.printStackTrace();
	// }
	// }

	// -------------------------------------------------------------------------------------------------------------------------------------
	protected void inflectPossessiveNew(String transliterated2) throws UnsupportedEncodingException, Exception {
		ResultSet rs = null;

		inflectConstruct();
		String transliterated1 = inflectedItem;

		/*
		 * String surface1 = surface; inflectedItem = transliterated2; if
		 * (!inflectionBase.equals("")) { inflectedItem = inflectionBase; }
		 */

		// String masculineSingularConstruct="";
		try {
			connection = DriverManager.getConnection("jdbc:mysql://yeda.cs.technion.ac.il:3306/generatorTest", "dummy1",
					"health&happiness");
			PreparedStatement statement = null;
			statement = connection.prepareStatement(
					"SELECT *  FROM inflections where baseTransliteratedLItem=? and PGN!= 'unspecified'  and basePos='noun'");
			statement.setObject(1, transliterated2);
			// statement.setObject(2, lexiconId);
			rs = statement.executeQuery();
			while (rs.next()) {
				String transliterated = rs.getString("transliterated");
				String mwTransliterated = transliterated1 + " " + transliterated;
				String mwSurface = Translate.Eng2Heb(transliterated1) + " " + Translate.Eng2Heb(transliterated);

				popualteMWE.popualteMWETables(baseTransliteratedItem, undottedLexiconItem, '1', "noun", dottedLexiconItem,
						id, "unspecified", PGN, definitnessVal, gender, number, spelling, register, mwTransliterated,
						mwSurface);
				/*
				 * if(!masculineSingularConstruct.equals("")) masculineSingularConstruct =
				 * masculineSingularConstruct + ", " + URLDecoder.decode(mwUndotted,"UTF-8");
				 * else masculineSingularConstruct = URLDecoder.decode(mwUndotted,"UTF-8");
				 */
			}
			rs.close();
			statement.close();
			connection.close();

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		// return masculineSingularConstruct;

	}

	// ----------------------------------------------------------------------------------------------------------------------
	protected void inflectPossessive(String transliterated2) throws UnsupportedEncodingException, Exception {
		inflectConstruct();

		boolean iiSuffix = false;
		String transliterated1 = inflectedItem;
		inflectedItem = transliterated2;

		// System.out.println("|||||||||||||||||||||||||||||||||||||");
		// System.out.println("||||||||||generatePossessive||||||||||||");
		// System.out.println("PARAMETER = " + transliterated2);
		// when the base for possessive generation is not construct - the
		// base
		// is inflections base
		// System.out.println("inflectedItem sent to rule handling ="+ inflectedItem);
		if (inflectedItem.substring(inflectedItem.length() - 2).equals("ii"))
			iiSuffix = true;
		if (!inflectionBase.equals("")) {
			inflectionBaseHandling();
			inflectedItem = inflectionBase;
		}
		// final String colloquialPluralBase = inflectedItem;

		/*
		 * if ((inflectPossessiveP && (number.equals("plural") || number
		 * .equals("dual and plural")) && iiSuffix)) findRule(inflectedItem, "ii",
		 * "possessivePlural" + mwPos, 2);
		 * 
		 * else if (inflectPossessiveP && (number.equals("plural") || number
		 * .equals("dual and plural")))
		 * 
		 * findRule(inflectedItem, "", "possessivePlural" + mwPos, 2); else if
		 * (inflectPossessiveS && number.equals("singular")) { findRule(inflectedItem,
		 * "", "possessiveSingular" + mwPos, 2); }
		 * System.out.println("inflectedItem after rule handling=" + inflectedItem);
		 */

		// String suffixFunction = "possessive";

		/*
		 * String action = ""; if(number.equals("singular")) action
		 * ="possessiveSingularNoun"; else if(! number.equals("singular")) action
		 * ="possessivePluralNoun"; else
		 * System.out.println("inflectPossessive: no rule for number="+number);
		 * 
		 * findRule(inflectedItem, "", action, 2);
		 */
		findRule(inflectedItem, "", "possessiveSingularNoun", 2);

		if ((number.equals("singular") && inflectPossessiveS) || ((number.equals("plural") && inflectPossessiveP))
				|| ((number.equals("dual and plural") && inflectPossessiveP))) {
			StringTokenizer stPossessive = new StringTokenizer(inflectedItem, ",");
			StringTokenizer stPGN = new StringTokenizer(PGNTokens10, ",");

			while (stPGN.hasMoreTokens()) {
				PGN = stPGN.nextToken();
				inflectedItem = stPossessive.nextToken();
				// if (!replaceRemoveException(PGN, gender, lexiconNumber, "false",
				// replaceExceptionList, "replace")) {
				// System.out.println();
				// System.out.println("inflectedItem =" + inflectedItem);
				surface = Translate.Eng2Heb(inflectedItem);
				// System.out.println("multiWordNounAdjective:inflectPossessive: surface ="+
				// surface);
				// System.out.println();
				// }

				String mwTransliterated = transliterated1 + " " + inflectedItem;
				String mwSurface = Translate.Eng2Heb(transliterated1) + " " + surface;
				// System.out.println("************************************");
				/*
				 * System.out.println("popualteMWE.popualteMWETables("+
				 * baseTransliteratedItem+","+ undottedLexiconItem+",1,noun," +
				 * dottedLexiconItem +","+ id +",unspecified," + PGN +"," + definitnessVal +","
				 * + gender +","+ number +","+spelling+","+ register+","+ mwTransliterated +","+
				 * mwSurface);
				 */

				popualteMWE.popualteMWETables(baseTransliteratedItem, undottedLexiconItem, '1', "noun", dottedLexiconItem,
						id, "unspecified", PGN, definitnessVal, gender, number, spelling, register, mwTransliterated,
						mwSurface);
			}
		}
		inflectedItem = transliterated1;
	}

	// ---------------------------------------------------------------------------------------------------------
	protected void generateInternalDefiniteness(String inflectedItem, String restTransliterated, String restSurface)
			throws Exception {
		String mwUndotted = Translate.Eng2Heb(inflectedItem) + " ה" + restSurface.replaceFirst(" ", " ה");
		String mwTransliterated = inflectedItem + " h" + restTransliterated.replaceFirst(" ", " h");
		popualteMWE.popualteMWETables(baseTransliteratedItem, undottedLexiconItem, '1', mwPos, dottedLexiconItem, id,
				"unspecified", PGN, definitnessVal, gender, number, spelling, register, mwTransliterated, mwUndotted);

	}

	// ---------------------------------------------------------------------------------------------------------
	protected void generateInternalAndExternalDefiniteness(String inflectedItem, String restTransliterated,
			String restSurface) throws Exception {
		String mwUndotted = " ה" + Translate.Eng2Heb(inflectedItem) + " ה" + restSurface.replaceFirst(" ", " ה");
		String mwTransliterated = "h" + inflectedItem + " h" + restTransliterated.replaceFirst(" ", " h");
		popualteMWE.popualteMWETables(baseTransliteratedItem, undottedLexiconItem, '1', mwPos, dottedLexiconItem, id,
				"unspecified", PGN, definitnessVal, gender, number, spelling, register, mwTransliterated, mwUndotted);

	}

	// -------------------------------------------------------------------------------------------------------------------------------------
	protected void generateExternalDefiniteness(String inflectedItem, String restTransliterated, String restSurface)
			throws Exception {
		String mwUndotted = " ה" + Translate.Eng2Heb(inflectedItem) + " " + restSurface;
		String mwTransliterated = "h" + inflectedItem + " " + restTransliterated;

		popualteMWE.popualteMWETables(baseTransliteratedItem, undottedLexiconItem, '1', mwPos, dottedLexiconItem, id,
				"unspecified", PGN, definitnessVal, gender, number, spelling, register, mwTransliterated, mwUndotted);
	}

	// -------------------------------------------------------------------------------------------------------------------------------------
	public void setDefinitenessAndInflectPossessive(String translitrated, String restTransliterated, String restSurface,
			String transliterated2) throws Exception {
		if (!replaceRemoveException("unspecified", gender, number, "false", removeExceptionList, "remove")) {
			if (definiteness.equals("internal and external")) {
				// System.out.println("Generating internal and external definitness");
				definitnessVal = "tt";
				construct = "unspecified";
				register = "informal";
				generateInternalAndExternalDefiniteness(translitrated, restTransliterated, restSurface);
			}

			// if (definiteness.equals("external")|| definiteness.equals("internal and
			// external"))
			if (definiteness.equals("external")) {
				// System.out.println("Generating external definitness");
				definitnessVal = "tt";
				construct = "unspecified";
				register = "informal";
				generateExternalDefiniteness(translitrated, restTransliterated, restSurface);
			}

			// if (definiteness.equals("internal")|| definiteness.equals("internal and
			// external"))
			if (definiteness.equals("internal")) {
				// System.out.println("Generating internal definitness");
				definitnessVal = "tt";
				construct = "unspecified";
				register = "formal";
				generateInternalDefiniteness(translitrated, restTransliterated, restSurface);

				/*
				 * if (inflectPossessiveS || inflectPossessiveP) { definitnessVal =
				 * "unspecified"; //inflectPossessiveNew(transliterated2); //read from DataBase
				 * inflectPossessive(transliterated2); //generate from the input fields PGN =
				 * "unspecified"; }
				 */
			}
		}
	}

	// -------------------------------------------------------------------------------------------------------------------------------------
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
	private void setAttributes(String surface, String register, String spelling, String inflectedItem, String gender,
			String number, String PGN, String definiteness, String dottedLexiconItem, String undottedLexiconItem,
			String feminine, String plural, boolean inflectPossessiveS, boolean inflectPossessiveP,
			String transliterated) {
		this.undot = this.surface = surface;
		this.register = register;
		this.spelling = spelling;
		this.inflectedItem = inflectedItem;
		this.gender = gender;
		this.number = number;
		this.PGN = PGN;
		this.definiteness = definiteness;
		definitnessVal = "tf";
		definiteness = item.getMultiWordNounAdjective().getDefiniteness();
		if (definiteness.equals("externallyDefinited"))
			definitnessVal = "tt";
		// this.definitnessVal = definitnessVal;
		this.dottedLexiconItem = dottedLexiconItem;
		this.undottedLexiconItem = undottedLexiconItem;
		// this.suffixFunction = suffixFunction;
		this.feminine = feminine;
		this.plural = plural;
		this.inflectPossessiveS = inflectPossessiveS;
		this.inflectPossessiveP = inflectPossessiveP;
		this.transliterated = this.baseTransliteratedItem = transliterated;
	}

	// -------------------------------------------------------------------------------------------------------------------------------------
	public void generateInflects() throws Exception {
		try {
			analyse();
			// Get list of exceptions of type replace for this lexicon Item
			getException("replace");
			// Get list of exceptions of type remove for this lexicon Item
			getException("remove");

			// Get list of exceptions of type add for this lexicon Item
			// getException("add");

			StringTokenizer st = new StringTokenizer(transliterated);
			inflectedItem = st.nextToken();

			surfaceOrg = surface;
			registerOrg = register;
			spellingOrg = spelling;
			inflectedItemOrg = inflectedItem;
			genderOrg = gender;
			numberOrg = number;
			PGN_Org = PGN;
			definitenessOrg = definiteness;
			dottedOrg = dottedLexiconItem;
			undottedOrg = undottedLexiconItem;
			feminineOrg = feminine;
			pluralOrg = plural;
			inflectPossessiveSOrg = inflectPossessiveS;
			inflectPossessivePOrg = inflectPossessiveP;
			transliteratedOrg = transliterated;

			String transliterated_noun = "";
			String transliterated_adjective = "";
			String transliterated_noun_surface = "";
			String transliterated_adjective_surface = "";

			String transliteratedNoun = inflectedItem;
			String transliteratedAdjective = st.nextToken();
			String restTransliterated = transliteratedAdjective;
			String restSurface = Translate.Eng2Heb(transliteratedAdjective);
			while (st.hasMoreTokens()) {
				String currentToken = st.nextToken();
				restTransliterated += " " + currentToken;
				restSurface += " " + Translate.Eng2Heb(currentToken);
			}

			// Populate database with the original lexicon Item
			if (!replaceRemoveException("unspecified", lexiconGender, lexiconNumber, "false", removeExceptionList,
					"remove")) {

				replaceRemoveException("unspecified", lexiconGender, lexiconNumber, "false", replaceExceptionList,
						"replace");
				popualteMWE.popualteMWETables(baseTransliteratedItem, undottedLexiconItem, '1', mwPos, dottedLexiconItem,
						id, "unspecified", PGN, definitnessVal, gender, number, spelling, register, transliterated, surface);

				setDefinitenessAndInflectPossessive(transliteratedNoun, restTransliterated, restSurface,
						transliteratedAdjective); // inflect definitness
			}

			// *************************************************
			// create faminine if needed (base form is masculine and faminine form is
			// specified)
			if ((gender.equals("masculine"))
					&& ((!feminineNoun.equals("unspecified") || !feminineAdjective.equals("unspecified")))) {
				if (!replaceRemoveException("unspecified", "feminine", "singular", "false", replaceExceptionList,
						"replace")) { // no replace exception so get original
					transliterated_noun = transliteratedNoun + (feminineNoun.equals("unspecified") ? "" : feminineNoun); // if
																																							// there
																																							// is
																																							// plural
																																							// for
																																							// male
																																							// noun
																																							// part
																																							// then
																																							// add
																																							// it
					transliterated_adjective = transliteratedAdjective
							+ (feminineAdjective.equals("unspecified") ? "" : feminineAdjective); // if there is plural for
																															// male for adjective part
																															// then add it
				} else // we got a replecment exception so let replace
				{
					transliterated_noun = exceptionNoun + (feminineNoun.equals("unspecified") ? "" : feminineNoun); // if
																																					// there
																																					// is
																																					// plural
																																					// for
																																					// male
																																					// noun
																																					// part
																																					// then
																																					// add it
					transliterated_adjective = exceptionAdjective
							+ (feminineAdjective.equals("unspecified") ? "" : feminineAdjective); // if there is plural for
																															// male for adjective part
																															// then add it
				}
				gender = "feminine";
				// transliterated_noun = transliteratedNoun +
				// (feminineNoun.equals("unspecified") ? "" : feminineNoun); // if there is
				// feminine for noun part then add it
				// transliterated_adjective = transliteratedAdjective +
				// (feminineAdjective.equals("unspecified") ? "" : feminineAdjective); // if
				// there is feminine for djective part then add it
				transliterated_noun_surface = Translate.Eng2Heb(transliterated_noun);
				transliterated_adjective_surface = Translate.Eng2Heb(transliterated_adjective);
				String feminineTransliterated = transliterated_noun + " " + transliterated_adjective;
				String feminineTransliteratedSurface = transliterated_noun_surface + " " + transliterated_adjective_surface;
				popualteMWE.popualteMWETables(baseTransliteratedItem, undottedLexiconItem, '1', mwPos, dottedLexiconItem,
						id, "NA", PGN, /* definitnessVal */ "tf", gender, "singular", spelling, register,
						feminineTransliterated, feminineTransliteratedSurface);
				setDefinitenessAndInflectPossessive(transliterated_noun, transliterated_adjective,
						transliterated_adjective_surface, transliteratedAdjective); // inflect definitness
			}

			// *************************************************
			// now we create the plural male
			if ((!pluralMaleNoun.equals("unspecified")) || (!pluralMaleAdjective.equals("unspecified"))) {
				if (!replaceRemoveException("unspecified", "masculine", "plural", "false", replaceExceptionList,
						"replace")) { // no replace exception so get original
					transliterated_noun = transliteratedNoun; // get the noun
					if (transliterated_noun.endsWith("h"))
						transliterated_noun = transliterated_noun.substring(0, transliterated_noun.length() - 1); // if h in
																																				// the end
																																				// so cut it
					transliterated_noun += (pluralMaleNoun.equals("unspecified") ? "" : pluralMaleNoun); // if there is
																																		// plural for male
																																		// noun part then
																																		// add it
					transliterated_adjective = transliteratedAdjective; // get the adjective
					if (transliterated_adjective.endsWith("h"))
						transliterated_adjective = transliterated_adjective.substring(0,
								transliterated_adjective.length() - 1); // if h in the end so cut it
					transliterated_adjective += (pluralMaleAdjective.equals("unspecified") ? "" : pluralMaleAdjective); // if
																																							// there
																																							// is
																																							// plural
																																							// for
																																							// male
																																							// for
																																							// adjective
																																							// part
																																							// then
																																							// add
																																							// it
				} else // we got a replecment exception so let replace
				{
					transliterated_noun = exceptionNoun + (pluralMaleNoun.equals("unspecified") ? "" : pluralMaleNoun); // if
																																							// there
																																							// is
																																							// plural
																																							// for
																																							// male
																																							// noun
																																							// part
																																							// then
																																							// add
																																							// it
					transliterated_adjective = exceptionAdjective
							+ (pluralMaleAdjective.equals("unspecified") ? "" : pluralMaleAdjective); // if there is plural for
																																// male for adjective
																																// part then add it
				}
				gender = "musculine";
				transliterated_noun_surface = Translate.Eng2Heb(transliterated_noun);
				transliterated_adjective_surface = Translate.Eng2Heb(transliterated_adjective);
				String pluralMaleTransliterated = transliterated_noun + " " + transliterated_adjective;
				String PluralMaleTransliteratedSurface = transliterated_noun_surface + " "
						+ transliterated_adjective_surface;
				popualteMWE.popualteMWETables(baseTransliteratedItem, undottedLexiconItem, '1', mwPos, dottedLexiconItem,
						id, "NA", PGN, /* definitnessVal */ "tf", gender, "plural", spelling, register,
						pluralMaleTransliterated, PluralMaleTransliteratedSurface);
				setDefinitenessAndInflectPossessive(transliterated_noun, transliterated_adjective,
						transliterated_adjective_surface, transliteratedAdjective); // inflect definitness
			}

			// *************************************************
			// now we create the plural feminine
			if ((!pluralFemaleNoun.equals("unspecified")) || (!pluralFemaleAdjective.equals("unspecified"))) {
				if (!replaceRemoveException("unspecified", "feminine", "plural", "false", replaceExceptionList,
						"replace")) { // no replace exception so get original
					transliterated_noun = transliteratedNoun;
					if (transliterated_noun.endsWith("h"))
						transliterated_noun = transliterated_noun.substring(0, transliterated_noun.length() - 1); // if h in
																																				// the end
																																				// so cut it
					transliterated_noun += (pluralFemaleNoun.equals("unspecified") ? "" : pluralFemaleNoun); // if there is
																																			// plural for
																																			// male noun
																																			// part then
																																			// add it
					transliterated_adjective = transliteratedAdjective; // get the adjective
					if (transliterated_adjective.endsWith("h"))
						transliterated_adjective = transliterated_adjective.substring(0,
								transliterated_adjective.length() - 1); // if h in the end so cut it
					transliterated_adjective += (pluralFemaleAdjective.equals("unspecified") ? "" : pluralFemaleAdjective); // if
																																								// there
																																								// is
																																								// plural
																																								// for
																																								// male
																																								// for
																																								// adjective
																																								// part
																																								// then
																																								// add
																																								// it
				} else // we got a replecment exception so let replace
				{
					transliterated_noun = exceptionNoun + (pluralFemaleNoun.equals("unspecified") ? "" : pluralFemaleNoun); // if
																																								// there
																																								// is
																																								// plural
																																								// for
																																								// male
																																								// noun
																																								// part
																																								// then
																																								// add
																																								// it
					transliterated_adjective = exceptionAdjective
							+ (pluralFemaleAdjective.equals("unspecified") ? "" : pluralFemaleAdjective); // if there is plural
																																	// for male for
																																	// adjective part
																																	// then add it
				}
				gender = "feminine";
				// transliterated_noun = transliteratedNoun +
				// (pluralFemaleNoun.equals("unspecified") ? "" : pluralFemaleNoun); // if there
				// is plural for male noun part then add it
				// transliterated_adjective = transliteratedAdjective +
				// (pluralFemaleAdjective.equals("unspecified") ? "" : pluralFemaleAdjective);
				// // if there is plural for male for adjective part then add it
				transliterated_noun_surface = Translate.Eng2Heb(transliterated_noun);
				transliterated_adjective_surface = Translate.Eng2Heb(transliterated_adjective);
				String pluralFemaleTransliterated = transliterated_noun + " " + transliterated_adjective;
				String PluralFemaleTransliteratedSurface = transliterated_noun_surface + " "
						+ transliterated_adjective_surface;
				popualteMWE.popualteMWETables(baseTransliteratedItem, undottedLexiconItem, '1', mwPos, dottedLexiconItem,
						id, "NA", PGN, /* definitnessVal */ "tf", gender, "plural", spelling, register,
						pluralFemaleTransliterated, PluralFemaleTransliteratedSurface);
				setDefinitenessAndInflectPossessive(transliterated_noun, transliterated_adjective,
						transliterated_adjective_surface, transliteratedAdjective); // inflect definitness
			}

			/*
			 * if((gender.equals("masculine")) && (!feminine.equals("unspecified"))) {
			 * System.out.println("GENDER = muscline FEMININE = unspecufied"); gender =
			 * "feminine"; generateFeminine(restTransliterated, restSurface);
			 * //generateConstruct(restTransliterated, restSurface); // no need - yossi
			 * 01.01.2011 setDefinitenessAndInflectPossessive( restTransliterated,
			 * restSurface, transliterated2);
			 * 
			 * gender = "masculine"; inflectedItem = inflectedItemOrg; }
			 * 
			 * 
			 * if (type.equals("NNA") && gender.equals("masculine")) { restTransliterated +=
			 * "im"; restSurface += "ים"; }
			 * 
			 * if (!plural.equals("unspecified") && lexiconNumber.equals("singular")) {
			 * System.out.println("PLURAL = unsepcified LEXICONNMBER = singlular");
			 * inflectedItem = inflectedItemOrg; number = "plural"; generatePlural(plural,
			 * restTransliterated, restSurface); //generateConstruct(restTransliterated,
			 * restSurface); // no need - yossi 01.01.2011
			 * setDefinitenessAndInflectPossessive( restTransliterated, restSurface,
			 * transliterated2); }
			 * 
			 * 
			 * if((gender.equals("masculine")) && (!feminine.equals("unspecified"))) {
			 * System.out.println("GENDER = muscline FEMININE = unspecufied"); inflectedItem
			 * = inflectedItemOrg; gender="feminine"; generatePlural("wt",
			 * restTransliterated, restSurface); //generateConstruct(restTransliterated,
			 * restSurface); // no need - yossi 01.01.2011
			 * setDefinitenessAndInflectPossessive( restTransliterated, restSurface,
			 * transliterated2);
			 * 
			 * gender = "masculine"; }
			 */
			/*
			 * setAttributes(surfaceOrg, registerOrg, spellingOrg, inflectedItemOrg,
			 * genderOrg, numberOrg, PGN_Org, definitenessOrg, dottedOrg, undottedOrg,
			 * feminineOrg, pluralOrg, inflectPossessiveSOrg, inflectPossessivePOrg,
			 * transliteratedOrg);
			 */

			// inflect exception type items of action add - if exist
			inflectAddExceptions();

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

	}

}
