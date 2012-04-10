/*
 * Created on 14/11/2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package lexicon.generate;

import java.util.List;
import java.util.StringTokenizer;

import lexicon.contents.exception_types.ExistentialExceptionType;
import lexicon.contents.types.ItemType;

/**
 * @author daliabo
 * 
 * TODO To change the template for this generated type comment go to Window -
 * Preferences - Java - Code Style - Code Templates
 */
public class ExistentialGen extends ItemGen {
	String definiteness = "";

	public ExistentialGen(ItemType item) {
		super(item);

	}

	protected void addException() throws Exception {
		String sql = buildSql("add", "existential_exception_type");
		List addExceptionList = handleException(sql);
		if (addExceptionList.size() > 0) {
			analyseExceptionList(addExceptionList);
		}
	}

	private void analyseExceptionList(List exceptionList) throws Exception {
		for (int i = 0; i < exceptionList.size(); i++) {
			ExistentialExceptionType existentialExceptionType = new ExistentialExceptionType();
			existentialExceptionType.open(((Integer) exceptionList.get(i))
					.intValue());
			inflectedItem = existentialExceptionType.getTransliterated();
			surface = existentialExceptionType.getUndotted();
			spelling = existentialExceptionType.getSpelling();
			register = existentialExceptionType.getRegister();
			PGN = existentialExceptionType.getPgn();
			gender = existentialExceptionType.getGender();
			number = existentialExceptionType.getNumber();
			tense = existentialExceptionType.getTense();
			if (definiteness.equals("prohibited"))
				definitnessVal = "f";
			else
				definitnessVal = "tf";
			if (!PGN.equals("unspecified")) {
				suffixFunction = "pronomial";
			} else
				suffixFunction = "unspecified";
			populateDatabase();
			if (!definiteness.equals("prohibited")) {
				// הישנם , הישנן
				addH();
			}
		}
	}

	private void analyse() {
		analyseItem();
		gender = item.getExistential().getGender();
		number = item.getExistential().getNumber();
		tense = item.getExistential().getTense();
		System.out.println("tense=" + tense);
		definiteness = item.getExistential().getDefiniteness();
		if (definiteness.equals("prohibited"))
			definitnessVal = "f";
		else
			definitnessVal = "tf";

		System.out.println("definiteness=" + definiteness);

		suffixFunction = "unspecified";

		PGN = item.getExistential().getPgn();
		if (PGN.equals("unspecified"))
			;
		else {
			suffixFunction = "pronomial";
			//populateAcussativeAttribues();
		}
		root = item.getExistential().getRoot();
		String polarity = item.getExistential().getPolarity();
		System.out.println("polarity=" + polarity);
		if (polarity.equals("positive"))
			polarityVal = "p";
		else if (polarity.equals("negative"))
			polarityVal = "n";
		else

			polarityVal = "u";
	}

	protected void addH() throws Exception {
		if (!definitness.equals("prohibited")) {
			definitnessVal = "tt";
			super.addH();
		}
	}

	public void inflect() throws Exception {
		try {
			analyse();
			inflectedItem = transliterated;
			surface = undot;
			populateDatabase();
			if (!definiteness.equals("prohibited"))
				addH();
			addException();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
