/*
 * Created on 19/09/2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package lexicon.generate;

import java.util.List;
import java.util.StringTokenizer;

import lexicon.contents.exception_types.InterjectionExceptionType;
import lexicon.contents.exception_types.PrepositionExceptionType;
import lexicon.contents.types.ItemType;
import lexicon.stringUtils.Translate;

/**
 * @author daliabo
 * 
 * TODO To change the template for this generated type comment go to Window -
 * Preferences - Java - Code Style - Code Templates
 */
public class PrepositionGen extends ItemGen {

	//		final static String PGNPRONOMIALTokens10 =
	//	 "1p/MF/Sg,2p/M/Sg,2p/F/Sg,3p/M/Sg,3p/F/Sg,1p/MF/Pl,2p/M/Pl,2p/F/Pl,3p/M/Pl,3p/F/Pl";
	//
	//	final static String personTokens10 = "1,2,2,3,3,1,2,2,3,3";
	//
	//	final static String numberTokens10 =
	// "singular,singular,singular,singular,singular,plural,plural,plural,plural,plural";
	//
	//	final static String genderTokens10 = "masculine and
	// feminine,masculine,feminine,masculine,feminine,masculine and
	// feminine,masculine,feminine,masculine,feminine";

	public PrepositionGen(ItemType item) {
		super(item);
	}

	private void replaceException() {
		String sql = buildSql("replace", "preposition_exception_type");
		replaceExceptionList = handleException(sql);
	}

	private void removeException() {
		String sql = buildSql("remove", "preposition_exception_type");
		removeExceptionList = handleException(sql);
	}

	protected void addException() throws Exception {
		super.getException("add");
		if (addExceptionList.size() > 0) {
			analyseExceptionList(addExceptionList);
		}
	}

	protected void handleBaseForm() throws Exception {
		boolean removeBaseForm = false;
		int size = removeExceptionList.size();
		if (size == 0)
			populateDatabase();
		else {
			for (int i = 0; i < size; i++) {
				PrepositionExceptionType prepositionExceptionType = new PrepositionExceptionType();
				prepositionExceptionType.open(((Integer) removeExceptionList
						.get(i)).intValue());
				String exceptionPGN = prepositionExceptionType
						.getPersonGenderNumber();
				String action = prepositionExceptionType.getAction();
				if (exceptionPGN.equals("unspecified")
						&& action.equals("remove"))
					removeBaseForm = true;
			}

			if(!removeBaseForm)
				populateDatabase();

		}
	}

	protected boolean exceptionHandle(List exceptionList, String action)
			throws Exception {
		int size = exceptionList.size();
		boolean match = false;
		for (int i = 0; i < size; i++) {
			PrepositionExceptionType prepositionExceptionType = new PrepositionExceptionType();
			prepositionExceptionType.open(((Integer) exceptionList.get(i))
					.intValue());
			String exceptionPGN = prepositionExceptionType
					.getPersonGenderNumber();
			String exceptionAction = prepositionExceptionType.getAction();

			if ((!exceptionPGN.equals("unspecified"))
					&& (action.equals("replace"))) {
				PGN = exceptionPGN;
				inflectedItem = prepositionExceptionType.getTransliterated();
				surface = prepositionExceptionType.getUndotted();
				register = prepositionExceptionType.getRegister();
				spelling = prepositionExceptionType.getSpelling();
				match = true;
				populateDatabase();
				break;
			}
		}
		return match;
	}

	//For add exception list
	private void analyseExceptionList(List exceptionList) throws Exception {
		System.out.println("---------analyseExceptionList-----------");
		for (int i = 0; i < exceptionList.size(); i++) {
			PrepositionExceptionType prepositionExceptionType = new PrepositionExceptionType();
			prepositionExceptionType.open(((Integer) exceptionList.get(i))
					.intValue());
			inflectedItem = prepositionExceptionType.getTransliterated();
			System.out.println("inflectedItem =" + inflectedItem);
			surface = prepositionExceptionType.getUndotted();
			register = prepositionExceptionType.getRegister();
			spelling = prepositionExceptionType.getSpelling();
			PGN = prepositionExceptionType.getPersonGenderNumber();
			suffixFunction = "unspecified";
			if (!PGN.equals("unspecified")) {
				suffixFunction = "pronomial";
				//populateAcussativeAttribues();
			}
			populateDatabase();
		}
	}

	protected boolean replaceExceptionExist() throws Exception {
		boolean match = false;
		for (int i = 0; i < replaceExceptionList.size(); i++) {
			PrepositionExceptionType prepositionExceptionType = new PrepositionExceptionType();
			prepositionExceptionType.open(((Integer) replaceExceptionList
					.get(i)).intValue());
			String exceptionPGN = prepositionExceptionType
					.getPersonGenderNumber();
			if (exceptionPGN.equals(PGN)) {
				inflectedItem = prepositionExceptionType.getTransliterated();
				surface = prepositionExceptionType.getUndotted();
				register = prepositionExceptionType.getRegister();
				spelling = prepositionExceptionType.getSpelling();
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
			PrepositionExceptionType prepositionExceptionType = new PrepositionExceptionType();
			prepositionExceptionType
					.open(((Integer) removeExceptionList.get(i)).intValue());
			String exceptionPGN = prepositionExceptionType
					.getPersonGenderNumber();
			if (exceptionPGN.equals(PGN)) {
				match = true;
				break;
			}
		}
		return match;
	}

	private void analyse() {
		analyseItem();

		inflectedItem = transliterated;
		inflectionBase = item.getPreposition().getInflectionBase();
		surface = undot;
		construct = "unspecified";
		suffixFunction = "unspecified";
		caseSt = item.getPreposition().getCase();
		PGN = "unspecified";
		dottedLexiconItem = item.getDotted();
	}

	public void inflect() throws Exception {
		analyse();
		getException("replace");
		getException("remove");
		//Handle base form
		handleBaseForm();

		//handle inflections
		//if (replaceExceptionList.size()>0 &&
		// !exceptionHandle(replaceExceptionList, "replace"))
		//populateDatabase();
		//if there is a legal possessive inflection - inflectionBase is filled
		// and if not
		//it will remail empty - an agreement with the lexikograph
		if (!inflectionBase.equals("")) {
			inflectionBaseHandling();
			suffixes = "i,k,k,w,h,nw,km,kn,m,n";
			spelling = STANDARD_SPELLING;
			register = FORMAL_REGISTER;
			inflectPronomial("pronomial");
		}
		addException();

	}

}
