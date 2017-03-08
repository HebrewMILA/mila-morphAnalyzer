/*
 * Created on 19/09/2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package lexicon.generate;

import java.util.List;

import lexicon.contents.exception_types.PronounExceptionType;
import lexicon.contents.types.ItemType;
import lexicon.stringUtils.Translate;

/**
 * @author daliabo
 * 
 * TODO To change the template for this generated type comment go to Window -
 * Preferences - Java - Code Style - Code Templates
 */
public class PronounGen extends ItemGen {
	boolean isDefinite = false;

	public PronounGen(ItemType item) {
		super(item);
	}

	private void analyseExceptionList(List exceptionList) throws Exception {
		for (int i = 0; i < exceptionList.size(); i++) {
			PronounExceptionType pronounExceptionType = new PronounExceptionType();
			pronounExceptionType.open(((Integer) exceptionList.get(i))
					.intValue());
			inflectedItem = pronounExceptionType.getTransliterated();
			surface = pronounExceptionType.getUndotted();
			spelling = pronounExceptionType.getSpelling();
			register = pronounExceptionType.getRegister();
			PGN = pronounExceptionType.getPgn();
			basePerson = pronounExceptionType.getPerson();
			gender = pronounExceptionType.getGender();
			number = pronounExceptionType.getNumber();
			type = item.getPronoun().getType();
			if (!PGN.equals("unspecified")) {
				suffixFunction = "pronomial";
				basePerson = "unspecified";
				definitnessVal = "unspecified";
			} else
				suffixFunction = "unspecified";
			prefix = "unspecified";
			handlePronounType();
			handleDefiniteness(inflectedItem);
			generateBKLMForms(pronounExceptionType.getTransliterated());

		}
	}

	private void replaceException() {
		String sql = buildSql("replace", "pronoun_exception_type");
		replaceExceptionList = handleException(sql);
	}

	private void removeException() {
		String sql = buildSql("remove", "pronoun_exception_type");
		removeExceptionList = handleException(sql);
	}

	protected void addException() throws Exception {
		String sql = buildSql("add", "pronoun_exception_type");
		List addExceptionList = handleException(sql);
		if (addExceptionList.size() > 0) {
			analyseExceptionList(addExceptionList);
		}
	}

	protected void generateBKLMForms(String transliterated) throws Exception {
		if (item.getPronoun().getType().indexOf("with bklm") != -1) {

			inflectedItem = "b" + transliterated;
			surface = Translate.Eng2Heb(inflectedItem);
			prefix = "b";
			if (item.getPronoun().getType().indexOf("with definiteness") != -1) {
				handlePronounType();
				handlebklmDefiniteness(inflectedItem);
			} else {
				definitnessVal = "f";
				handlePronounType();
				populateDatabase();

			}

			inflectedItem = "k" + transliterated;
			surface = Translate.Eng2Heb(inflectedItem);
			prefix = "k";
			handlePronounType();
			if (item.getPronoun().getType().indexOf("with definiteness") != -1) {
				handlePronounType();
				handlebklmDefiniteness(inflectedItem);
			} else {
				definitnessVal = "f";
				handlePronounType();
				populateDatabase();

			}

			inflectedItem = "l" + transliterated;
			surface = Translate.Eng2Heb(inflectedItem);
			prefix = "l";
			handlePronounType();
			if (item.getPronoun().getType().indexOf("with definiteness") != -1) {
				handlePronounType();
				handlebklmDefiniteness(inflectedItem);
			} else {
				definitnessVal = "f";
				handlePronounType();
				populateDatabase();

			}

			inflectedItem = "m" + transliterated;
			surface = Translate.Eng2Heb(inflectedItem);
			prefix = "m";
			handlePronounType();
			if (item.getPronoun().getType().indexOf("with definiteness") != -1) {
				handlePronounType();
				handlebklmDefiniteness(inflectedItem);
			} else {
				definitnessVal = "f";
				handlePronounType();
				populateDatabase();

			}

		} else
			handlePronounType();

	}

	protected void handlePronounType() {
		if (type.endsWith("with bklm without definiteness"))
			type = type.replaceAll("with bklm without definiteness", "");
		else if (type.endsWith("with bklm with definiteness"))
			type = type.replaceAll("with bklm with definiteness", "");
		else if (type.endsWith("with bklm"))
			type = type.replaceAll("with bklm", "");
		else if (type.endsWith("no bklm"))
			type = type.replaceAll("no bklm", "");
	}

	protected void handleBaseForm() throws Exception {
		int size = removeExceptionList.size();
		if (size == 0) {
			if (!PGN.equals("unspecified")) {
				suffixFunction = "pronomial";
				basePerson = "unspecified";
				definitnessVal = "unspecified";
			} else
				suffixFunction = "unspecified";
			handlePronounType();
			handleDefiniteness(inflectedItem);
			generateBKLMForms(transliterated);
		} else {
			for (int i = 0; i < size; i++) {
				PronounExceptionType pronounExceptionType = new PronounExceptionType();
				pronounExceptionType
						.open(((Integer) removeExceptionList.get(i)).intValue());
				String exceptionPGN = pronounExceptionType.getPgn();
				String action = pronounExceptionType.getAction();
				if (exceptionPGN.equals("unspecified")
						&& action.equals("remove"))
					;

				else {
					if (!PGN.equals("unspecified")) {
						suffixFunction = "pronomial";
						basePerson = "unspecified";
						definitnessVal = "unspecified";
					} else
						suffixFunction = "unspecified";
					populateDatabase();
					generateBKLMForms(transliterated);
				}

			}
		}
	}

	private void analyse() {
		analyseItem();
		gender = item.getPronoun().getGender();
		number = item.getPronoun().getNumber();
		basePerson = item.getPronoun().getPerson();
		type = item.getPronoun().getType();
		inflectedItem = transliterated;
		surface = undot;
		definitness = item.getPronoun().getDefiniteness();
		suffixFunction = "unspecified";
		PGN = item.getPronoun().getPgn();
		prefix = "unspecified";
	}

	private void handleDefiniteness(String item) {
		try {
			if (definitness.equals("required")) {
				definitnessVal = "rt";
				populateDatabase();

				definitnessVal = "tt";
				inflectedItem = "h" + item;
				surface = Translate.Eng2Heb(inflectedItem);
				populateDatabase();

			} else if (definitness.equals("optional")) {
				definitnessVal = "tf";
				populateDatabase();
				definitnessVal = "tt";
				inflectedItem = "h" + item;
				System.out.println();
				System.out.println("inflectedItem =" + inflectedItem);
				surface = Translate.Eng2Heb(inflectedItem);
				System.out.println("surface =" + surface);
				System.out.println();
				populateDatabase();
				//שמות פרטיים שאסור שיהיו מיודעים כמו קולורדו
			} else if (definitness.equals("prohibited")) {
				definitnessVal = "f";
				populateDatabase();
			} else if (definitness.equals("definited")) {
				definitnessVal = "rt";
				populateDatabase();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void handlebklmDefiniteness(String item) {
		if (item.charAt(0) != 'm') {
			try {
				if (definitness.equals("required")) {
					definitnessVal = "tt";
					populateDatabase();

				} else if (definitness.equals("optional")) {
					definitnessVal = "tf";
					populateDatabase();
					definitnessVal = "tt";
					populateDatabase();
				} else if (definitness.equals("definited")) {
					inflectedItem = item.charAt(0) + item.substring(2);
					surface = Translate.Eng2Heb(inflectedItem);
					definitnessVal = "tt";
					populateDatabase();
				} else {
					definitnessVal = "f";
					populateDatabase();

				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else {
			item = item.substring(1);
			try {
				if (definitness.equals("required")) {
					inflectedItem = "mh" + item;
					surface = Translate.Eng2Heb(inflectedItem);
					definitnessVal = "tt";
					populateDatabase();

				} else if (definitness.equals("optional")) {
					inflectedItem = "m" + item;
					surface = Translate.Eng2Heb(inflectedItem);
					definitnessVal = "tf";
					populateDatabase();
					inflectedItem = "mh" + item;
					surface = Translate.Eng2Heb(inflectedItem);
					definitnessVal = "tt";
					populateDatabase();
				} else if (definitness.equals("definited")) {
					inflectedItem = "m" + item;
					surface = Translate.Eng2Heb(inflectedItem);
					definitnessVal = "tt";
					populateDatabase();
				} else {
					inflectedItem = "m" + item;
					surface = Translate.Eng2Heb(inflectedItem);
					definitnessVal = "f";
					populateDatabase();

				}
			} catch (Exception e) {
				e.printStackTrace();
			}

		}
	}

	public void generateInflects() throws Exception {
		analyse();
		getException("replace");
		getException("remove");
		handleBaseForm();
		addException();

		//		definitnessVal = "tt";
		//		inflectedItem = "h" + transliterated;
		//		System.out.println();
		//		System.out.println("inflectedItem =" + inflectedItem);
		//		surface = Translate.Eng2Heb(inflectedItem);
		//		System.out.println("surface =" + surface);
		//		System.out.println();
		//		populateDatabase();
	}
}
