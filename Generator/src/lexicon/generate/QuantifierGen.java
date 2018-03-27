/*
 * Created on 18/09/2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package lexicon.generate;

import java.util.List;
import lexicon.contents.exception_types.QuantifierExceptionType;
import lexicon.contents.types.ItemType;
import lexicon.stringUtils.Translate;

/**
 * @author daliabo
 * 
 *         TODO To change the template for this generated type comment go to
 *         Window - Preferences - Java - Code Style - Code Templates
 */
public class QuantifierGen extends ItemGen {
	String personTokens = "";

	String numberTokens = "";

	String genderTokens = "";

	public QuantifierGen(ItemType item) {
		super(item);
	}

	private void replaceException() {
		String sql = buildSql("replace", "quantifier_exception_type");
		replaceExceptionList = handleException(sql);
	}

	private void removeException() {
		String sql = buildSql("remove", "quantifier_exception_type");
		removeExceptionList = handleException(sql);
	}

	protected void addException() throws Exception {
		String sql = buildSql("add", "quantifier_exception_type");
		List addExceptionList = handleException(sql);
		if (addExceptionList.size() > 0) {
			analyseExceptionList(addExceptionList);
		}
	}

	protected boolean replaceExceptionExist() throws Exception {
		boolean match = false;
		for (int i = 0; i < replaceExceptionList.size(); i++) {
			System.out.println("********************************");
			QuantifierExceptionType quantifierExceptionType = new QuantifierExceptionType();
			quantifierExceptionType.open(((Integer) replaceExceptionList.get(i)).intValue());
			String exceptionPGN = quantifierExceptionType.getPgn();
			if (exceptionPGN.equals(PGN)) {
				PGN = exceptionPGN;
				inflectedItem = quantifierExceptionType.getTransliterated();
				surface = quantifierExceptionType.getUndotted();
				spelling = quantifierExceptionType.getSpelling();
				register = quantifierExceptionType.getRegister();
				match = true;
				populateDatabase();
				break;
			}
		}
		return match;
	}

	protected boolean removeExceptionExist() throws Exception {
		boolean match = false;
		for (int i = 0; i < removeExceptionList.size(); i++) {
			System.out.println("********************************");
			QuantifierExceptionType quantifierExceptionType = new QuantifierExceptionType();
			quantifierExceptionType.open(((Integer) removeExceptionList.get(i)).intValue());
			// String exceptionGender = quantifierExceptionType.getGender();
			String exceptionPGN = quantifierExceptionType.getPgn();
			if (exceptionPGN.equals(PGN)) {
				PGN = exceptionPGN;
				inflectedItem = quantifierExceptionType.getTransliterated();
				surface = quantifierExceptionType.getUndotted();
				spelling = quantifierExceptionType.getSpelling();
				register = quantifierExceptionType.getRegister();
				construct = quantifierExceptionType.getConstruct();
				match = true;
			}
		}
		return match;
	}

	private void analyseExceptionList(List exceptionList) throws Exception {
		for (int i = 0; i < exceptionList.size(); i++) {
			QuantifierExceptionType quantifierExceptionType = new QuantifierExceptionType();
			quantifierExceptionType.open(((Integer) exceptionList.get(i)).intValue());
			inflectedItem = quantifierExceptionType.getTransliterated();
			surface = quantifierExceptionType.getUndotted();
			spelling = quantifierExceptionType.getSpelling();
			register = quantifierExceptionType.getRegister();
			PGN = quantifierExceptionType.getPgn();
			construct = quantifierExceptionType.getConstruct();

			if (!PGN.equals("unspecified")) {
				suffixFunction = "pronomial";
				construct = "unspecified";
			}
			if (!construct.equals("true") && type.equals("partitive") && PGN.equals("unspecified")) {
				definitnessVal = "tf";
			}
			// else if(PGN.equals("unspecified") && construct.equals("false") &&
			// !definitness.equals("prohibited")){
			// addH();
			// }else
			populateDatabase();
			if (!construct.equals("true") && type.equals("partitive") && PGN.equals("unspecified")) {
				construct = "unspecified";
				addH();

			}
		}
	}

	private void analyse() {
		analyseItem();
		type = item.getQuantifier().getType();
		inflect = item.getQuantifier().isInflect();
		inflectionBase = item.getQuantifier().getInflectionBase();
		inflectedItem = transliterated;
		surface = undot;
		suffixFunction = "unspecified";
		construct = "false";
		PGN = "unspecified";
		definitness = item.getQuantifier().getDefiniteness();

	}

	

	// // replace exceptions are not defined for possessive for quantifier only
	// for
	// // construct - so it has it's own inflectPossessive version
	// protected void inflectPossessive() throws Exception {
	// StringTokenizer stSuff = new StringTokenizer(suffixes, ",");
	// StringTokenizer stPGN = new StringTokenizer(PGNTokens, ",");
	// StringTokenizer stPerson = new StringTokenizer(personTokens, ",");
	// StringTokenizer stNumber = new StringTokenizer(numberTokens, ",");
	// StringTokenizer stGender = new StringTokenizer(genderTokens, ",");
	// suffixFunction = "possessive";
	// if (!inflectionBase.equals("")) {
	// try {
	// inflectionBase = URLDecoder.decode(inflectionBase, "UTF-8");
	// } catch (UnsupportedEncodingException e) {
	// // TODO Auto-generated catch block
	// e.printStackTrace();
	// }
	// inflectionBase = Translate.Heb2Eng(inflectionBase);
	// System.out.println("inflectionBase =" + inflectionBase);
	// }
	// while (stSuff.hasMoreTokens()) {
	// String suffix = stSuff.nextToken();
	// if (!inflectionBase.equals(""))
	// inflectedItem = inflectionBase + suffix;
	// else
	// inflectedItem = transliterated + suffix;
	// System.out.println();
	// System.out.println("inflectedItem =" + inflectedItem);
	// surface = Translate.Eng2Heb(inflectedItem);
	// System.out.println("surface =" + surface);
	// System.out.println();
	//
	// PGN = stPGN.nextToken();
	// suffixPerson = stPerson.nextToken();
	// suffixNumber = stNumber.nextToken();
	// suffixGender = stGender.nextToken();
	// populateDatabase();
	//
	// }
	// }

	// private void generatePlural() {
	// if (gender.equals("feminine")) {
	// inflectedItem = transliterated + "wt";
	// surface = Translate.Eng2Heb(inflectedItem);
	// } else if (gender.equals("masculine")) {
	// inflectedItem = transliterated + "im";
	//
	// } else if (gender.equals("masculine and feminine")) {
	// inflectedItem = transliterated + "im";
	//
	// }
	// surface = Translate.Eng2Heb(inflectedItem);
	// populateDatabase();
	//
	// generateConstruct(inflectedItem);
	// }

	private void generatePronomial() throws Exception {
		if (inflect) {
			inflectionBaseHandling();
			suffixes = "i,k,k,w,h,nw,km,kn,hm,hn";
			inflectPronomial("pronomial");
			suffixFunction = "unspecified";
			PGN = "unspecified";
			// if (gender.equals("unspecified") ) {
			// suffixes = "i,k,k,w,h,nw,km,kn,m,n";
			// inflectPronomial();
			// } else if (gender.equals("feminine")) {
			// suffixes = "nw,kn,hn";
			// inflectPronomial();
			// } else if (gender.equals("masculine")) {
			// suffixes = "nw,km,m";
			// inflectPronomial();
			// } else if (gender.equals("masculine and feminine")) {
			// suffixes = "i,k,k,w,h,nw,km,kn,hm,hn";
			// inflectPronomial();
			// }
		}
	}

	protected void addH() throws Exception {
		definitnessVal = "tt";
		super.addH();
		definitnessVal = "unspecified";
	}

	public void generateInflects() throws Exception {
		// לא נייצר לכמתים צורת נפרד רק מיודע (אם אפשר ונסמך)
		// הטיות מתווספות ע"י הוספת יוצאי דופן
		analyse();
		removeException();
		replaceException();

		if (type.equals("amount")) {
			definitnessVal = "unspecified";
			construct = "true";
			populateDatabase();
			construct = "false";
			populateDatabase();
		} else if (type.equals("partitive")) {
			definitnessVal = "tf";
			construct = "true";
			populateDatabase();
			definitnessVal = "tf";
			construct = "false";
			populateDatabase();
			construct = "unspecified";
			addH();
			// עבור יוצאי דופן כמו רוב- שמייצר רובם רובן
			if (inflect) {
				definitnessVal = "unspecified";
				inflectionBaseHandling();
				suffixes = "i,k,k,w,h,nw,km,kn,hm,hn";
				inflectPronomial("pronomial");
				suffixFunction = "unspecified";
				PGN = "unspecified";
			}
		} else if (type.equals("determiner")) {
			definitnessVal = "unspecified";
			construct = "true";
			populateDatabase();
			generatePronomial();
		}

		// if (definitness.equals("required")) {
		// definitnessVal = "tt";
		// inflectedItem = "h" + inflectedItem;
		// surface = Translate.Eng2Heb(inflectedItem);
		// if(!removeExceptionExist())
		// populateDatabase();
		//
		// addException();
		//
		// } else {
		//
		// if (definitness.equals("optional"))
		// definitnessVal = "tf";
		// else
		// definitnessVal = "f";
		//
		// generateConstruct(transliterated);
		// if(!removeExceptionExist())
		// populateDatabase();
		//
		// construct = "false";
		// if (!(definitness.equals("prohibited"))){
		// addH();
		// }
		//
		// generatePronomial();
		addException();
		// //generatePlural();
		// }

	}
}
