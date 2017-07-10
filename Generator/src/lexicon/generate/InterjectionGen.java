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
import lexicon.contents.types.ItemType;

/**
 * @author daliabo
 *
 *         TODO To change the template for this generated type comment go to
 *         Window - Preferences - Java - Code Style - Code Templates
 */
public class InterjectionGen extends ItemGen {

	public InterjectionGen(ItemType item) {
		super(item);
	}

	private void replaceException() {
		String sql = buildSql("replace", "interjection_exception_type");
		replaceExceptionList = handleException(sql);
	}

	private void removeException() {
		String sql = buildSql("remove", "interjection_exception_type");
		removeExceptionList = handleException(sql);
	}

	protected boolean replaceExceptionExist() throws Exception {
		boolean match = false;
		for (int i = 0; i < replaceExceptionList.size(); i++) {
			InterjectionExceptionType interjectionExceptionType = new InterjectionExceptionType();
			interjectionExceptionType.open(((Integer) replaceExceptionList.get(i)).intValue());
			String exceptionPGN = interjectionExceptionType.getPersonGenderNumber();

			if (exceptionPGN.equals(PGN)) {
				inflectedItem = interjectionExceptionType.getTransliterated();
				surface = interjectionExceptionType.getUndotted();
				spelling = interjectionExceptionType.getSpelling();
				register = interjectionExceptionType.getRegister();
				// populateAcussativeAttribues();
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
			InterjectionExceptionType interjectionExceptionType = new InterjectionExceptionType();
			interjectionExceptionType.open(((Integer) removeExceptionList.get(i)).intValue());
			String exceptionPGN = interjectionExceptionType.getPersonGenderNumber();
			if (!exceptionPGN.equals("unspecified"))
				suffixFunction = "pronomial";
			if (exceptionPGN.equals(PGN)) {
				match = true;
				break;
			}
		}
		return match;
	}

	protected void addException() throws Exception {
		String sql = buildSql("add", "interjection_exception_type");
		List addExceptionList = handleException(sql);
		if (addExceptionList.size() > 0) {
			analyseExceptionList(addExceptionList);
		}
	}

	private void analyseExceptionList(List exceptionList) throws Exception {
		for (int i = 0; i < exceptionList.size(); i++) {
			InterjectionExceptionType interjectionExceptionType = new InterjectionExceptionType();
			interjectionExceptionType.open(((Integer) exceptionList.get(i)).intValue());
			inflectedItem = interjectionExceptionType.getTransliterated();
			surface = interjectionExceptionType.getUndotted();
			spelling = interjectionExceptionType.getSpelling();
			register = interjectionExceptionType.getRegister();
			PGN = interjectionExceptionType.getPersonGenderNumber();
			if (!PGN.equals("unspecified"))
				suffixFunction = "pronomial";
			populateDatabase();
		}
	}

	private void analyse() {
		analyseItem();
		inflect = item.getInterjection().isInflect();
		inflectedItem = transliterated;
		surface = undot;
		suffixFunction = "unspecified";
		PGN = "unspecified";
	}

	public void generateInflects() throws Exception {
		analyse();
		populateDatabase();
		addException();
		replaceException();
		removeException();
		if (inflect) {
			suffixes = "i,k,k,w,h,nw,km,kn,hm,hn";
			// PGNTokens =
			// "1p/MF/Sg,2p/F/Sg,2p/M/Sg,3p/M/Sg,3p/F/Sg,1p/MF/Pl,2p/F/Pl,2p/M/Pl,3p/F/Pl,3p/M/Pl";
			inflectPronomial("pronomial");
		}
	}
}
