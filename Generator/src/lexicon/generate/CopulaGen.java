/*
 * Created on 14/11/2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package lexicon.generate;

import java.util.List;
import java.util.StringTokenizer;

import lexicon.contents.exception_types.CopulaExceptionType;
import lexicon.contents.types.ItemType;
import lexicon.stringUtils.Translate;

/**
 * @author daliabo
 * 
 *         TODO To change the template for this generated type comment go to
 *         Window - Preferences - Java - Code Style - Code Templates
 */
public class CopulaGen extends ItemGen {
	String definiteness = "";

	public CopulaGen(ItemType item) {
		super(item);

	}

	protected void addException() throws Exception {
		String sql = buildSql("add", "copula_exception_type");
		List addExceptionList = handleException(sql);
		if (addExceptionList.size() > 0) {
			analyseExceptionList(addExceptionList);
		}
	}

	private void analyseExceptionList(List exceptionList) throws Exception {
		for (int i = 0; i < exceptionList.size(); i++) {
			CopulaExceptionType copulaExceptionType = new CopulaExceptionType();
			copulaExceptionType.open(((Integer) exceptionList.get(i)).intValue());
			inflectedItem = copulaExceptionType.getTransliterated();
			surface = copulaExceptionType.getUndotted();
			spelling = copulaExceptionType.getSpelling();
			register = copulaExceptionType.getRegister();
			gender = copulaExceptionType.getGender();
			number = copulaExceptionType.getNumber();
			basePerson = copulaExceptionType.getPerson();
			tense = copulaExceptionType.getTense();
			populateDatabase();
			if (tense.equals("bareInfinitive"))
				try {
					String base = inflectedItem;
					generatebklInfinitive(base);
					generateInflectedInfinitive(base);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		}
	}

	public String generatePGN(String gender, String number, String person) {
		String pgn = "";
		if (gender.equals("masculine"))
			gender = "M";
		else if (gender.equals("feminine"))
			gender = "F";
		else if (gender.equals("masculine and feminine"))
			gender = "MF";
		if (number.equals("singular"))
			number = "Sg";
		else if (number.equals("plural"))
			number = "Pl";
		pgn = person + "p/" + gender + "/" + number;
		return pgn;
	}

	public void generateInflectedInfinitive(String base) throws Exception {
		String suffix = "";
		tense = "infinitive";
		suffixFunction = "accusative or nominative";
		StringTokenizer stPerson = new StringTokenizer(personTokens10, ",");
		StringTokenizer stNumber = new StringTokenizer(numberTokens10, ",");
		StringTokenizer stGender = new StringTokenizer(genderTokens10, ",");
		StringTokenizer stSuffix = new StringTokenizer("i,k,k,w,h,nw,km,kn,m,n,", ",");
		while (stSuffix.hasMoreTokens()) {
			String number = stNumber.nextToken();
			String person = stPerson.nextToken();
			String gender = stGender.nextToken();
			PGN = generatePGN(gender, number, person);
			suffix = stSuffix.nextToken();
			inflectedItem = "l" + base + suffix;
			surface = Translate.Eng2Heb(inflectedItem);
			populateDatabase();
			inflectedItem = "b" + base + suffix;
			surface = Translate.Eng2Heb(inflectedItem);
			populateDatabase();
			inflectedItem = "k" + base + suffix;
			surface = Translate.Eng2Heb(inflectedItem);
			populateDatabase();
			inflectedItem = "m" + base + suffix;
			surface = Translate.Eng2Heb(inflectedItem);
			populateDatabase();
		}
		suffixFunction = "unspecified";
		PGN = "unspecified";
	}

	public void generatebklInfinitive(String base) throws Exception {
		tense = "infinitive";
		inflectedItem = "l" + base;
		surface = Translate.Eng2Heb(inflectedItem);
		populateDatabase();
		inflectedItem = "b" + base;
		surface = Translate.Eng2Heb(inflectedItem);
		populateDatabase();
		inflectedItem = "k" + base;
		surface = Translate.Eng2Heb(inflectedItem);
		populateDatabase();
		inflectedItem = "m" + base;
		surface = Translate.Eng2Heb(inflectedItem);
	}

	public void inflectItem() {
		if (tense.equals("bareInfinitive"))
			try {
				String base = inflectedItem;
				generatebklInfinitive(base);
				generateInflectedInfinitive(base);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}

	private void analyse() {
		analyseItem();
		gender = item.getCopula().getGender();
		number = item.getCopula().getNumber();
		basePerson = item.getCopula().getPerson();
		tense = item.getCopula().getTense();
		PGN = "unspecified";

		String polarity = item.getCopula().getPolarity();
		if (polarity.equals("positive"))
			polarityVal = "p";
		else if (polarity.equals("negative"))
			polarityVal = "n";
		else

			polarityVal = "u";

	}

	public void generateInflects() throws Exception {
		try {
			analyse();
			inflectedItem = transliterated;
			surface = undot;
			populateDatabase();
			inflectItem();
			addException();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
