/*
 * Created on 14/11/2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
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

import lexicon.contents.exception_types.MultiWordNounExceptionType;
import lexicon.contents.exception_types.NounExceptionType;

import lexicon.contents.types.ItemType;
import lexicon.stringUtils.Translate;

/**
 * @author daliabo
 * 
 * TODO To change the template for this generated type comment go to Window -
 * Preferences - Java - Code Style - Code Templates
 */
public class MultiWordNounGen extends ItemGen {
	
	static Connection connection=null;
	
	String definiteness;

	String mwPos = "";

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
	public MultiWordNounGen(ItemType item) {
		super(item);

	}
	
	/**
	 * This method gets the exception item list of action add for the lexicon
	 * item
	 * 
	 * @throws Exception
	 */
	private void inflectAddExceptions() throws Exception {
		super.getException("add");
		if (addExceptionList.size() > 0) {
			analyseExceptionList();
		}
	}
	
	
	private boolean addExceptionListHandling(MultiWordNounExceptionType multiWordNounExceptionType) throws Exception {
		
		try{
			String exceptionGender = multiWordNounExceptionType.getGender();
			String exceptionNumber = multiWordNounExceptionType.getNumber();
			String exceptionPGN = multiWordNounExceptionType.getPossessive();
			String exceptionSpelling = multiWordNounExceptionType.getSpelling();
			String exceptionRegister = multiWordNounExceptionType.getRegister();
			exceptionTransliterated = multiWordNounExceptionType.getTransliterated();
			exceptionTransliterated = exceptionTransliterated.replaceAll("&#39;", "'");
			exceptionTransliterated = exceptionTransliterated.replaceAll("&#60;", "`");				
			String exceptionDefiniteness = multiWordNounExceptionType.getDefiniteness();
			String exceptionDotted = multiWordNounExceptionType.getDotted();
			String exceptionUndotted = multiWordNounExceptionType.getUndotted();
			String exceptionFeminine = multiWordNounExceptionType.getFeminine();
			String exceptionPlural = multiWordNounExceptionType.getPlural();
			boolean exceptionInflectPossessiveS = multiWordNounExceptionType.isInflectPossessiveS();
		    boolean exceptionInflectPossessiveP = multiWordNounExceptionType.isInflectPossessiveP();
		    
			//System.out.println("exception action = add ");	
			//System.out.println("exception transliterated  ="+ exceptionTransliterated);
			
			StringTokenizer st = new StringTokenizer(exceptionTransliterated);
			String transliteratedPart1 = st.nextToken();
			String transliteratedPart2 = "";
			if (st.hasMoreTokens()) {
				transliteratedPart2 = st.nextToken();
			}								
		
			String inflectedItemOrg = transliteratedPart1;		
			
			
			if(transliteratedPart2.equals("")){
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
			
		    setAttributes(exceptionUndotted, exceptionRegister, exceptionSpelling, transliteratedPart1,
					exceptionGender, exceptionNumber, exceptionPGN, exceptionDefiniteness, exceptionDotted, URLEncoder.encode(exceptionUndotted, "UTF-8"),
					exceptionFeminine, exceptionPlural,	exceptionInflectPossessiveS, exceptionInflectPossessiveP, exceptionTransliterated);
									
			String restTransliterated = transliteratedPart2;
			String restSurface = Translate.Eng2Heb(transliteratedPart2);
			while (st.hasMoreTokens()) {
				String currentToken = st.nextToken();
				restTransliterated += " " + currentToken;
				restSurface += " " + Translate.Eng2Heb(currentToken);
			}
			
			//We use the same generation methods for the lexicon item and for
			//the exception lexicon item
			//replaceExceptionList is not relevant for the exception type
			// lexicon item (action add)
			replaceExceptionList = null;
			removeExceptionList = null;
			
			//Populate database with the original lexicon Item		
			if (!replaceRemoveException("unspecified", lexiconGender,
					lexiconNumber, "false", removeExceptionList, "remove")) {
				
				replaceRemoveException("unspecified", lexiconGender, lexiconNumber, "false",
						replaceExceptionList, "replace");
				popualteMWE.popualteMWETables(baseTransliteratedItem,
						undottedLexiconItem, '1', mwPos, dottedLexiconItem, id, "unspecified",
						PGN, definitnessVal, gender, number, spelling, register,
						transliterated, surface);
				
				setDefinitenessAndInflectPossessive( restTransliterated, restSurface, transliteratedPart2);
			}
			
			
			if((gender.equals("masculine")) && (!feminine.equals("unspecified"))){
				gender = "feminine";
				generateFeminine(restTransliterated, restSurface);
				generateConstruct(restTransliterated, restSurface);		
				setDefinitenessAndInflectPossessive( restTransliterated, restSurface, transliteratedPart2);
				
				gender = "masculine";
				inflectedItem = inflectedItemOrg;
			}	
	
		
			if (type.equals("NNA") &&  gender.equals("masculine")) {
				restTransliterated += "im";
				restSurface += "׳™׳�";
			}
			
			if (!plural.equals("unspecified") && lexiconNumber.equals("singular")) {
				inflectedItem = inflectedItemOrg;
				number = "plural";
				generatePlural(plural, restTransliterated, restSurface);
				generateConstruct(restTransliterated, restSurface);
				setDefinitenessAndInflectPossessive( restTransliterated, restSurface, transliteratedPart2);						
			}			
				
			
			if((gender.equals("masculine")) && (!feminine.equals("unspecified"))){
				inflectedItem = inflectedItemOrg;			
				gender="feminine";
				generatePlural("wt", restTransliterated, restSurface);
				generateConstruct(restTransliterated, restSurface);		
				setDefinitenessAndInflectPossessive( restTransliterated, restSurface, transliteratedPart2);							
				
				gender = "masculine";
			}
	
		} catch (Exception e) {
			System.out.println(e.getMessage());
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
	private void analyseExceptionList() throws Exception {
		int size = addExceptionList.size();
		//System.out.println("addExceptionList size" + size);
		for (int i = 0; i < size; i++) {
			MultiWordNounExceptionType multiWordNounExceptionType = new MultiWordNounExceptionType();
			multiWordNounExceptionType.open(((Integer) addExceptionList.get(i)).intValue());			
		    		
			//System.out.println("i=" + i);				
			addExceptionListHandling(multiWordNounExceptionType);
/*
			if (exceptionTransliterated.startsWith("w")
					&& !exceptionTransliterated.startsWith("ww")) {
				inflectedItem = "w" + nounExceptionType.getTransliterated();
				inflectedItem = inflectedItem.replaceAll("&#39;", "'");
				inflectedItem = inflectedItem.replaceAll("&#60;", "`");
				surface = "׳•" + nounExceptionType.getUndotted();
				spelling = IRREGULAR_SPELLING;
				addExceptionListHandling(MultiWordNounExceptionType);
			}
*/			
		}
	}

	private boolean replaceRemoveException(String PGN, String gender,
			String number, String construct, List exceptionList, String action)
			throws Exception {
		boolean match = false;
		if (exceptionList != null) {
			for (int i = 0; i < exceptionList.size(); i++) {
				MultiWordNounExceptionType multiWordNounExceptionType = new MultiWordNounExceptionType();
				multiWordNounExceptionType
						.open(((Integer) exceptionList.get(i)).intValue());
				String exceptionGender = multiWordNounExceptionType.getGender();
				String exceptionNumber = multiWordNounExceptionType.getNumber();
				String exceptionPGN = multiWordNounExceptionType.getPossessive();
				String exceptionSpelling = multiWordNounExceptionType.getSpelling();
				String exceptionRegister = multiWordNounExceptionType.getRegister();
				exceptionTransliterated = multiWordNounExceptionType.getTransliterated();
				
				//exception fields for add
				String exceptionDefiniteness = multiWordNounExceptionType.getDefiniteness();
				String exceptionDotted = multiWordNounExceptionType.getDotted();
				String exceptionUndotted = multiWordNounExceptionType.getUndotted();
				String exceptionFeminine = multiWordNounExceptionType.getFeminine();
				String exceptionPlural = multiWordNounExceptionType.getPlural();
				boolean exceptionInflectPossessiveS = multiWordNounExceptionType.isInflectPossessiveS();
			    boolean exceptionInflectPossessiveP = multiWordNounExceptionType.isInflectPossessiveP();
				
				
				/*System.out.println("----------------------------");
				System.out.println(action + " handling");
				System.out.println("exceptionGender =" + exceptionGender);
				System.out.println("gender =" + gender);
				System.out.println("exceptionNumber =" + exceptionNumber);
				System.out.println("number =" + number);
				System.out.println("exceptionPGN =" + exceptionPGN);
				System.out.println("PGN =" + PGN);*/
				if (exceptionGender.equals(gender) && exceptionNumber.equals(number) && exceptionPGN.equals(PGN)){			
					if (action.equals("replace")) {
						//System.out.println("exception action = replace ");
						surface = Translate.Eng2Heb(exceptionTransliterated);
						register = exceptionRegister;
						spelling = exceptionSpelling;
						//System.out.println("exception transliterated  ="+ exceptionTransliterated);
						inflectedItem = exceptionTransliterated;

						match = true;
						System.out.println("----------------------------");
						return match;
					} else if (action.equals("remove")) {
						inflectedItem = "";
						surface = "";
						//System.out.println("exception action = remove ");
						match = true;
						//System.out.println("----------------------------");
						return match;
					}
/*					
					}else if (action.equals("add")) {
						System.out.println("exception action = add ");
						//surface = Translate.Eng2Heb(exceptionTransliterated);
						//register = exceptionRegister;
						//spelling = exceptionSpelling;
						System.out.println("exception transliterated  ="
								+ exceptionTransliterated);
						//inflectedItem = exceptionTransliterated;
						
						StringTokenizer st = new StringTokenizer(exceptionTransliterated);
						String transliteratedPart1 = st.nextToken();
						String transliteratedPart2 = st.nextToken();					
						
						setAttributes(exceptionUndotted, exceptionRegister, exceptionSpelling, transliteratedPart1,
								exceptionGender, exceptionNumber, exceptionPGN, exceptionDefiniteness, exceptionDotted, URLEncoder.encode(exceptionUndotted, "UTF-8"),
								exceptionFeminine, exceptionPlural,	exceptionInflectPossessiveS, exceptionInflectPossessiveP);

						match = true;
						System.out.println("----------------------------");
						return match;
					}
*/					
				}
			}
		}
		//System.out.println("----------------------------");
		return match;
	}

	private void generatePlural(String pluralSuffix, String restTransliterated,
			String restSurface) throws Exception {
		//System.out.println("|||||||||||||||||||||||||||||||||||||");
		//System.out.println("||||||||||generatePlural||||||||||||");
				
		String action = "";
		//String pluralBase = "";		
		if (!replaceRemoveException("unspecified", gender, "plural","false", removeExceptionList, "remove") 
			&& !replaceRemoveException("unspecified", gender, "plural", "false",replaceExceptionList, "replace")) 
		{		
			action = identifyPluralAction(pluralSuffix);
			action = action.replace("multiWord","");
			if (!action.equals("")) {
				//System.out.println("plural base = " + inflectedItem);
				//comment because of ׳�׳©׳×׳” - ׳�׳©׳×׳�׳•׳×
				//׳¦׳¨׳™׳� ׳�׳•׳•׳“׳� ׳©׳›׳₪׳™׳×׳” ׳�׳� ׳�׳×׳§׳�׳§׳� - ׳�׳×׳ ׳”׳’׳™׳� ׳©׳•׳ ׳” ׳›׳™ ׳”׳�׳™׳� ׳©׳•׳ ׳”
				if (inflectedItem.endsWith("th") && (gender.equals("feminine")))
					findRule(inflectedItem, "th", action, pluralSuffixMaxLength);
				else
					findRule(inflectedItem, pluralSuffix, action,pluralSuffixMaxLength);
				//System.out.println("plural inflectedItem = " + inflectedItem);
				surface = Translate.Eng2Heb(inflectedItem);										
										
			}
		} else
			inflectedItem = exceptionTransliterated;
			//generateConstruct(restTransliterated, restSurface);			
	}
	
	private void generateFeminine(String restTransliterated,String restSurface) throws Exception 
	{
		//System.out.println("|||||||||||||||||||||||||||||||||||||");
		//System.out.println("||||||||||generateFeminine||||||||||||");
		if (!replaceRemoveException("unspecified", "feminine", number,
				"unspecified", removeExceptionList, "remove") && !replaceRemoveException("unspecified", "feminine", number,
				"unspecified", replaceExceptionList, "replace")) {			
			String action = identifyFeminineAction();				
			action = action.replace("multiWord","");
			action = action.replace("Noun",mwPos);
			findRule(inflectedItem, feminine, action, 1);
			surface = Translate.Eng2Heb(inflectedItem);
			//generateConstruct(restTransliterated, restSurface);			
		}
	}
	
	
	private void generateConstruct(String restTransliterated,String restSurface) throws Exception 
	{		
		    boolean matchRemove = replaceRemoveException("unspecified", gender,
				number, "true", removeExceptionList, "remove");
		
		    if(!matchRemove){
				inflectConstruct();
				definitnessVal = "tf";			
				
				String mwUndotted = Translate.Eng2Heb(inflectedItem) + " "
						+ restSurface;
				String mwTransliterated = inflectedItem + " "
						+ restTransliterated;
				
				popualteMWE.popualteMWETables(baseTransliteratedItem,
						undottedLexiconItem, '1', mwPos, dottedLexiconItem, id, "unspecified",
						PGN, definitnessVal, gender, number, spelling, register,
						mwTransliterated, mwUndotted);
		    }
	}
	
	private void analyse() 
	{
		super.analyseItem();
		type = item.getMultiWordNoun().getType();		
		mwPos = item.getMultiWordNoun().getMwPos();
		
		suffixFunction = "unspecified";		
		surface = undot;
		lexiconNumber = number = item.getMultiWordNoun().getNumber();
		lexiconGender = gender = item.getMultiWordNoun().getGender();
		feminine = item.getMultiWordNoun().getFeminine();
		plural = item.getMultiWordNoun().getPlural();
		construct = "absolute";
		PGN = "unspecified";
		basePos = item.getPos();
		inflectionBase = item.getMultiWordNoun().getInflectionBase();
		if (!inflectionBase.equals("")) {
			try {
				inflectionBaseHandling();
			} catch (UnsupportedEncodingException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		inflectPossessiveP = item.getMultiWordNoun().isInflectPossessiveP();
		inflectPossessiveS = item.getMultiWordNoun().isInflectPossessiveS();
		definitnessVal = "tf";
		definiteness = item.getMultiWordNoun().getDefiniteness();
		if (definiteness.equals("externallyDefinited"))
			definitnessVal = "tt";
		try {
			undottedLexiconItem = URLEncoder
					.encode(item.getUndotted(), "UTF-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	protected void inflectConstruct() throws Exception {
		try 
		{
			//System.out.println("|||||||||||||||||||||||||||||||||||||");
			//System.out.println("||||||||||generateConstruct||||||||||||");
			construct = "construct";
			
			StringTokenizer st = new StringTokenizer(baseTransliteratedItem);
			String baseTransliteratedItem1 = st.nextToken();
			
		/*	
			boolean matchRemove = replaceRemoveException("unspecified", gender,
					number, "true", removeExceptionList, "remove");			
			boolean matchReplace = replaceRemoveException("unspecified", gender,
					number, "true", replaceExceptionList, "replace");
		*/

		//	if (!matchReplace && !matchRemove) {
				if ((number.equals("plural") || number.equals("dual") || number
						.equals("dual and plural"))) {
			
					findRule(inflectedItem, "", "constructMasculinePlural"
							+ mwPos, 2);
					
					//handle ׳’׳“׳™
					if (gender.equals("masculine")
							&& baseTransliteratedItem1.endsWith("i")
							&& !baseTransliteratedItem1.endsWith("ai")
							//handle ׳©׳”
							|| (baseTransliteratedItem1.endsWith("h") && baseTransliteratedItem1
									.length() == 2))
						inflectedItem = inflectedItem + "i";
					if (inflectedItem.equals(transliterated))
						surface = undot;
					else
						surface = Translate.Eng2Heb(inflectedItem);
	
				} else if ((gender.equals("feminine") || gender
						.equals("masculine and feminine"))) {
					findRule(inflectedItem, "", "constructFeminineSingular"
							+ mwPos, 2);						
					if (inflectedItem.endsWith("n") || inflectedItem.endsWith("p")
							|| inflectedItem.endsWith("c")
							&& inflectedItem.equals(transliterated))
						surface = undot;
					else
						surface = Translate.Eng2Heb(inflectedItem);					
				} else if (gender.equals("masculine") && number.equals("singular")) {				
					if ((inflectedItem.endsWith("n") || inflectedItem.endsWith("p") || inflectedItem
							.endsWith("c"))
							&& inflectedItem.equals(transliterated))
						surface = undot;
					else
						surface = Translate.Eng2Heb(inflectedItem);					
				}
		//	}

		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		construct = "absolute";
	}
	


	//	protected void inflectPlural(String transliterated2, String
	// transliterated1){
	//		try {
	//			construct="absolute";
	//			transliterated1=transliterated1+plural;
	//			transliterated = transliterated1 + " " + transliterated2;
	//			popualteMWE.popualteMWETables(transliterated, surface, '1',
	//					"noun", "unspecified", id, "unspecified", PGN, construct, spelling,
	// register);
	//		} catch (UnsupportedEncodingException e) {
	//			// TODO Auto-generated catch block
	//			e.printStackTrace();
	//		} catch (SQLException e) {
	//			// TODO Auto-generated catch block
	//			e.printStackTrace();
	//		}
	//	}

	protected void inflectPossessiveNew(String transliterated2)
	throws UnsupportedEncodingException, Exception {
		ResultSet rs = null;
		
		inflectConstruct();
		String transliterated1 = inflectedItem;
		
		/*
		String surface1 = surface;
		inflectedItem = transliterated2;
		if (!inflectionBase.equals("")) {
			inflectedItem = inflectionBase;
		}
		*/
		
		//String masculineSingularConstruct="";
		try {
//			connection = DriverManager.getConnection(
//					"jdbc:mysql://yeda.cs.technion.ac.il:3306/generatorTest",
// 					"dummy1", "health&happiness");
			connection = DriverManager.getConnection(
					"jdbc:mariadb://yeda.cs.technion.ac.il:3306/playground_generatorTest",
 					"dummy1", "health&happiness");
 			
			PreparedStatement statement = null;
			statement = connection
					.prepareStatement("SELECT *  FROM inflections where baseTransliteratedLItem=? and PGN!= 'unspecified'  and basePos='noun'" );									  
			statement.setObject(1, transliterated2);
			//statement.setObject(2, lexiconId);
			rs = statement.executeQuery();
			while (rs.next()) {
				String transliterated  = rs.getString("transliterated");
				String mwTransliterated = transliterated1 + " " + transliterated;
				String mwSurface = Translate.Eng2Heb(transliterated1)+ " " + Translate.Eng2Heb(transliterated);			
				
				popualteMWE.popualteMWETables(baseTransliteratedItem,
						undottedLexiconItem, '1', "noun", dottedLexiconItem, id,
						"unspecified", PGN, definitnessVal, gender, number,
						spelling, register, mwTransliterated,
						mwSurface);
				/*
				if(!masculineSingularConstruct.equals(""))
					masculineSingularConstruct  = masculineSingularConstruct + ", " + URLDecoder.decode(mwUndotted,"UTF-8");
				else
					masculineSingularConstruct =  URLDecoder.decode(mwUndotted,"UTF-8");
				*/
			}
			rs.close();
			statement.close();
			connection.close();
			
		} catch (Exception e) 
		{
			System.out.println(e.getMessage());
		}
		//return masculineSingularConstruct;
		
	}
//----------------------------------------------------------------------------------------------------------------------
	protected void inflectPossessive(String transliterated2)
			throws UnsupportedEncodingException, Exception {
		inflectConstruct();
		
		boolean iiSuffix = false;
		String transliterated1 = inflectedItem;	
		inflectedItem = transliterated2;

		//System.out.println("|||||||||||||||||||||||||||||||||||||");
		//System.out.println("||||||||||generatePossessive||||||||||||");
		//System.out.println("PARAMETER = " + transliterated2);
		//when the base for possessive generation is not construct - the
		// base
		// is inflections base
		//System.out.println("inflectedItem sent to rule handling ="+ inflectedItem);
		if (inflectedItem.substring(inflectedItem.length() - 2).equals("ii"))
			iiSuffix = true;
		if (!inflectionBase.equals("")) 
		{
			inflectionBaseHandling();
			inflectedItem = inflectionBase;
		}
		//final String colloquialPluralBase = inflectedItem;
		
/*		
		if ((inflectPossessiveP
				&& (number.equals("plural") || number
						.equals("dual and plural")) && iiSuffix))
			findRule(inflectedItem, "ii", "possessivePlural" + mwPos, 2);

		else if (inflectPossessiveP
				&& (number.equals("plural") || number
						.equals("dual and plural")))

			findRule(inflectedItem, "", "possessivePlural" + mwPos, 2);
		else if (inflectPossessiveS && number.equals("singular")) {
			findRule(inflectedItem, "", "possessiveSingular" + mwPos, 2);
		}
		System.out.println("inflectedItem after rule handling="
				+ inflectedItem);
*/				

		//String suffixFunction = "possessive";

/*		
		String action = "";
		if(number.equals("singular"))
		action ="possessiveSingularNoun";
		else if(! number.equals("singular"))
			action ="possessivePluralNoun";	
		else
			System.out.println("inflectPossessive: no rule for number="+number);

		findRule(inflectedItem, "", action, 2);
*/
		findRule(inflectedItem, "", "possessiveSingularNoun", 2);
		
		if ((number.equals("singular") && inflectPossessiveS)
				|| ((number.equals("plural") && inflectPossessiveP))
				|| ((number.equals("dual and plural") && inflectPossessiveP))) {
			StringTokenizer stPossessive = new StringTokenizer(inflectedItem, ",");
			StringTokenizer stPGN = new StringTokenizer(PGNTokens10, ",");
	
			while (stPGN.hasMoreTokens()) {
				PGN = stPGN.nextToken();
				inflectedItem = stPossessive.nextToken();
	//			if (!replaceRemoveException(PGN, gender, lexiconNumber, "false",
	//					replaceExceptionList, "replace")) {
					//System.out.println();
					//System.out.println("inflectedItem =" + inflectedItem);
					surface = Translate.Eng2Heb(inflectedItem);
					//System.out.println("multiWordNoun:inflectPossessive: surface ="+ surface);
					//System.out.println();
	//			}
				
				String mwTransliterated = transliterated1 + " " + inflectedItem;
				String mwSurface = Translate.Eng2Heb(transliterated1)+ " " + surface;			
				//System.out.println("************************************");
				/*System.out.println("popualteMWE.popualteMWETables("+ baseTransliteratedItem+","+
						undottedLexiconItem+",1,noun," + dottedLexiconItem +","+ id +",unspecified," + PGN +"," + 
						definitnessVal +"," + gender +","+ number +","+spelling+","+ register+","+ 
						mwTransliterated +","+ mwSurface);*/
				
				popualteMWE.popualteMWETables(baseTransliteratedItem,
						undottedLexiconItem, '1', "noun", dottedLexiconItem, id,
						"unspecified", PGN, definitnessVal, gender, number,
						spelling, register, mwTransliterated,
						mwSurface);						
			}
		}
		inflectedItem = transliterated1;	
	}

//---------------------------------------------------------------------------------------------------------
	protected void generateInternalDefiniteness(String inflectedItem,String restTransliterated, String restSurface) throws Exception 
	{
		String mwUndotted = Translate.Eng2Heb(inflectedItem) + " ׳”"
				+ restSurface.replaceFirst(" ", " ׳”");
		String mwTransliterated = inflectedItem + " h"
				+ restTransliterated.replaceFirst(" ", " h");
		popualteMWE.popualteMWETables(baseTransliteratedItem,
				undottedLexiconItem, '1', mwPos, dottedLexiconItem, id, "unspecified",
				PGN, definitnessVal, gender, number, spelling, register,
				mwTransliterated, mwUndotted);

	}

	protected void generateExternalDefiniteness(String inflectedItem,String restTransliterated, String restSurface) throws Exception 
	{
		String mwUndotted = " ׳”" + Translate.Eng2Heb(inflectedItem) + " " + restSurface;
		String mwTransliterated = "h" + inflectedItem + " " + restTransliterated;				
		
		popualteMWE.popualteMWETables(baseTransliteratedItem,
				undottedLexiconItem, '1', mwPos, dottedLexiconItem, id, "unspecified",
				PGN, definitnessVal, gender, number, spelling, register,
				mwTransliterated, mwUndotted);

	}
	
	public void setDefinitenessAndInflectPossessive(String restTransliterated, String restSurface,
			String transliterated2) throws Exception{
		
		if (!replaceRemoveException("unspecified", gender,number, "false", removeExceptionList, "remove"))
		{
			if (definiteness.equals("external") || definiteness.equals("internal and external")) 
			{
				definitnessVal = "tt";
				construct = "unspecified";
				register = "informal";
				generateExternalDefiniteness(inflectedItem, restTransliterated,
						restSurface);
			}
	
			if (definiteness.equals("internal") || definiteness.equals("internal and external")) 
			{
				definitnessVal = "tt";
				construct = "unspecified";
				register = "formal";
				generateInternalDefiniteness(inflectedItem, restTransliterated,restSurface);
				
				if (inflectPossessiveS || inflectPossessiveP) 
				{
					definitnessVal = "unspecified";
					//inflectPossessiveNew(transliterated2); //read from DataBase
					inflectPossessive(transliterated2);   //generate from the input fields
					PGN = "unspecified";
				}
			}	
		}
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
	private void setAttributes(String surface, String register, String spelling, String inflectedItem, String gender, String number, 
			String PGN, String definiteness, String dottedLexiconItem, String undottedLexiconItem, String feminine, String plural,
			boolean inflectPossessiveS, boolean inflectPossessiveP, String transliterated) {
		this.undot = this.surface = surface;
		this.register = register;
		this.spelling = spelling; 
		this.inflectedItem = inflectedItem;		
		this.gender = gender;
		this.number = number;
		this.PGN = PGN;
		this.definiteness = definiteness;
		definitnessVal = "tf";
		definiteness = item.getMultiWordNoun().getDefiniteness();
		if (definiteness.equals("externallyDefinited"))
			definitnessVal = "tt";		
		//this.definitnessVal = definitnessVal;
		this.dottedLexiconItem = dottedLexiconItem;
		this.undottedLexiconItem = undottedLexiconItem;
		//this.suffixFunction = suffixFunction;
		this.feminine = feminine;
		this.plural = plural;
		this.inflectPossessiveS = inflectPossessiveS;
		this.inflectPossessiveP = inflectPossessiveP; 
		this.transliterated = this.baseTransliteratedItem = transliterated;
	}	
	
	public void generateInflects() throws Exception {

		try{
			analyse();
			//Get list of exceptions of type replace for this lexicon Item
			getException("replace");
			//Get list of exceptions of type remove for this lexicon Item
			getException("remove");		
	
					
			//Get list of exceptions of type add for this lexicon Item
			//getException("add");
	
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
							
			
			String transliterated2 = st.nextToken();
			String restTransliterated = transliterated2;
			String restSurface = Translate.Eng2Heb(transliterated2);
			while (st.hasMoreTokens()) 
			{
				String currentToken = st.nextToken();
				restTransliterated += " " + currentToken;
				restSurface += " " + Translate.Eng2Heb(currentToken);
			}
			
			//Populate database with the original lexicon Item		
			if (!replaceRemoveException("unspecified", lexiconGender,
					lexiconNumber, "false", removeExceptionList, "remove")) 
			{
				
				replaceRemoveException("unspecified", lexiconGender, lexiconNumber, "false",replaceExceptionList, "replace");
				popualteMWE.popualteMWETables(baseTransliteratedItem,
						undottedLexiconItem, '1', mwPos, dottedLexiconItem, id, "unspecified",
						PGN, definitnessVal, gender, number, spelling, register,
						transliterated, surface);
				
				setDefinitenessAndInflectPossessive( restTransliterated, restSurface, transliterated2);
			}
	
	
			if((gender.equals("masculine")) && (!feminine.equals("unspecified"))){
				gender = "feminine";
				generateFeminine(restTransliterated, restSurface);
				generateConstruct(restTransliterated, restSurface);		
				setDefinitenessAndInflectPossessive( restTransliterated, restSurface, transliterated2);
				
				gender = "masculine";
				inflectedItem = inflectedItemOrg;
			}	
	
			
			if (type.equals("NNA") &&  gender.equals("masculine")) {
				restTransliterated += "im";
				restSurface += "׳™׳�";
			}
			
			if (!plural.equals("unspecified") && lexiconNumber.equals("singular")) {
				inflectedItem = inflectedItemOrg;
				number = "plural";
				generatePlural(plural, restTransliterated, restSurface);
				generateConstruct(restTransliterated, restSurface);
				setDefinitenessAndInflectPossessive( restTransliterated, restSurface, transliterated2);						
			}	
			
				
			if((gender.equals("masculine")) && (!feminine.equals("unspecified"))){
				inflectedItem = inflectedItemOrg;			
				gender="feminine";
				generatePlural("wt", restTransliterated, restSurface);
				generateConstruct(restTransliterated, restSurface);		
				setDefinitenessAndInflectPossessive( restTransliterated, restSurface, transliterated2);							
				
				gender = "masculine";
			}	
			
	
			setAttributes(surfaceOrg, registerOrg, spellingOrg, inflectedItemOrg,
					genderOrg, numberOrg, PGN_Org, definitenessOrg, dottedOrg, undottedOrg,
					feminineOrg, pluralOrg,	inflectPossessiveSOrg, inflectPossessivePOrg, transliteratedOrg);
			
			//inflect exception type items of action add - if exist
			inflectAddExceptions();
			
		}catch (Exception e) 
		{
		    System.out.println(e.getMessage());
		}

	}

}
