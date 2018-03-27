/*
 * Created on 14/11/2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package lexicon.generate;

import java.util.List;
import java.util.StringTokenizer;

import lexicon.contents.exception_types.ModalExceptionType;
import lexicon.contents.types.ItemType;
import lexicon.stringUtils.Translate;

/**
 * @author daliabo
 *
 *         TODO To change the template for this generated type comment go to
 *         Window - Preferences - Java - Code Style - Code Templates
 */
public class ModalGen extends ItemGen {
	static final String suffixList = "h,im,wt";
	static final String genderList = "feminine,masculine,feminine";
	static final String numberList = "singular,plural,plural";
	String inflectionType = "";

	protected void addException() throws Exception {
		String sql = buildSql("add", "modal_exception_type");
		List addExceptionList = handleException(sql);
		if (addExceptionList.size() > 0) {
			analyseExceptionList(addExceptionList);
		}
	}

	private void replaceException() {
		String sql = buildSql("replace", "modal_exception_type");
		replaceExceptionList = handleException(sql);
	}

	private void analyseExceptionList(List exceptionList) throws Exception {
		for (int i = 0; i < exceptionList.size(); i++) {
			ModalExceptionType modalExceptionType = new ModalExceptionType();
			modalExceptionType.open(((Integer) exceptionList.get(i)).intValue());
			inflectedItem = modalExceptionType.getTransliterated();
			surface = modalExceptionType.getUndotted();
			register = modalExceptionType.getRegister();
			spelling = modalExceptionType.getSpelling();
			gender = modalExceptionType.getGender();
			number = modalExceptionType.getNumber();
			tense = modalExceptionType.getTense();

			basePerson = modalExceptionType.getPerson();
			populateDatabase();
			if (tense.equals("beinoni")) {

				inflectedItem = "h" + inflectedItem;
				surface = Translate.Eng2Heb(inflectedItem);
				System.out.println(surface);
				definitnessVal = "s";
				populateDatabase();
			}

			definitnessVal = "unspecified";
		}
	}

	protected boolean replaceExceptionExist() throws Exception {
		boolean match = false;
		for (int i = 0; i < replaceExceptionList.size(); i++) {
			System.out.println("********************************");
			ModalExceptionType modalExceptionType = new ModalExceptionType();
			modalExceptionType.open(((Integer) replaceExceptionList.get(i)).intValue());
			String exceptionGender = modalExceptionType.getGender();
			String exceptionNumber = modalExceptionType.getNumber();
			String exceptionPerson = modalExceptionType.getPerson();
			String exceptionTense = modalExceptionType.getTense();
			System.out.println("exceptionNumber=" + exceptionNumber);
			System.out.println("exceptionPerson=" + exceptionPerson);
			System.out.println("exceptionGender=" + exceptionGender);
			System.out.println("exceptionTense=" + exceptionTense);
			if (exceptionGender.equals(gender) && exceptionNumber.equals(number) && exceptionPerson.equals(basePerson)) {
				inflectedItem = modalExceptionType.getTransliterated();
				surface = modalExceptionType.getUndotted();
				register = modalExceptionType.getRegister();
				populateDatabase();
				match = true;
				break;
			}
		}
		return match;
	}

	public ModalGen(ItemType item) {
		super(item);

	}

	private void analyse() {
		analyseItem();
		definitnessVal = "unspecified";
	}

	public void generateInflects() throws Exception {
		analyse();
		replaceException();
		inflectedItem = transliterated;
		surface = undot;
		gender = item.getModal().getGender();
		number = item.getModal().getNumber();
		inflectionType = item.getModal().getInflectionType();
		tense = item.getModal().getTense();
		if (inflectionType.equals("genderNumberTime"))
			basePerson = "3";
		else if (inflectionType.equals("genderNumber"))
			basePerson = "any";
		else
			basePerson = "unspecified";

		populateDatabase();
		if (tense.equals("beinoni")) {
			inflectedItem = "h" + inflectedItem;
			surface = Translate.Eng2Heb(inflectedItem);
			System.out.println(surface);
			definitnessVal = "s";
			populateDatabase();
		}

		definitnessVal = "unspecified";

		if (inflectionType.equals("genderNumber")) {
			String suffix = "";
			StringTokenizer genderSt = new StringTokenizer(genderList, ",");
			StringTokenizer numberSt = new StringTokenizer(numberList, ",");
			StringTokenizer suffixSt = new StringTokenizer(suffixList, ",");
			while (suffixSt.hasMoreTokens()) {
				suffix = suffixSt.nextToken();
				gender = genderSt.nextToken();
				number = numberSt.nextToken();
				basePerson = "any";
				inflectedItem = transliterated + suffix;
				surface = Translate.Eng2Heb(inflectedItem);
				if (!replaceExceptionExist())
					populateDatabase();
			}

		}

		addException();
	}

}
