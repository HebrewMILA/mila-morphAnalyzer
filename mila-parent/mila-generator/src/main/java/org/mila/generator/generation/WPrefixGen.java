/*
 * Created on 14/11/2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package org.mila.generator.generation;

import java.util.List;

import lexicon.contents.exception_types.PronounExceptionType;
import lexicon.contents.exception_types.WprefixExceptionType;
import lexicon.contents.types.ItemType;

/**
 * @author daliabo
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class WPrefixGen extends ItemGen {
	
	public WPrefixGen(ItemType item) {
		super(item);
		
	}

	protected void addException() throws Exception {
		String sql = buildSql("add", "wprefix_exception_type");
		List addExceptionList = handleException(sql);
		if (addExceptionList.size() > 0) {
			analyseExceptionList(addExceptionList);
		}
	}
	
	private void analyseExceptionList(List exceptionList) throws Exception {
		for (int i = 0; i < exceptionList.size(); i++) {
			WprefixExceptionType wprefixExceptionType = new WprefixExceptionType();
			wprefixExceptionType.open(((Integer) exceptionList.get(i))
					.intValue());
			definitnessVal = "tf";
			inflectedItem = wprefixExceptionType.getTransliterated();
			surface = wprefixExceptionType.getUndotted();
			spelling = wprefixExceptionType.getSpelling();
			register = wprefixExceptionType.getRegister();
			gender = wprefixExceptionType.getGender();
			number = wprefixExceptionType.getNumber();
			populateDatabase();
			definitnessVal = "tt";
			addH();
		}
	}

	private void analyse() {
		analyseItem();
		gender = item.getWPrefix().getGender();
		number = item.getWPrefix().getNumber();
		String polarity = item.getWPrefix().getPolarity();
		if (polarity.equals("positive"))
			polarityVal = "p";
		else if (polarity.equals("negative"))
			polarityVal = "n";
		else

			polarityVal = "u";
	}

	public void inflect() throws Exception {
		analyse();
		definitnessVal = "tf";
		inflectedItem = transliterated;
		surface = undot;
		populateDatabase();
		definitnessVal = "tt";
		addH();
		addException();
	}


}
