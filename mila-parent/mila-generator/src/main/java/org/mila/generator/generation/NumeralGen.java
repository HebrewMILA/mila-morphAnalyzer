/*
 * Created on 18/09/2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package org.mila.generator.generation;

import java.util.List;
import java.util.StringTokenizer;

import lexicon.contents.exception_types.NumeralExceptionType;
import lexicon.contents.types.ItemType;
import lexicon.stringUtils.Translate;

/**
 * @author daliabo
 * 
 * TODO To change the template for this generated type comment go to Window -
 * Preferences - Java - Code Style - Code Templates
 */
public class NumeralGen extends ItemGen {
	String personTokens = "";

	String numberTokens = "";

	String genderTokens = "";

	String definiteness = "";

	public NumeralGen(ItemType item) {
		super(item);
	}

	private void replaceException() {
		String sql = buildSql("replace", "numeral_exception_type");
		replaceExceptionList = handleException(sql);
	}

	protected void addException() throws Exception {
		String sql = buildSql("add", "numeral_exception_type");
		List addExceptionList = handleException(sql);
		if (addExceptionList.size() > 0) {
			analyseExceptionList(addExceptionList);
		}
	}

	protected boolean replaceExceptionExist() throws Exception {
		boolean match = false;
		for (int i = 0; i < replaceExceptionList.size(); i++) {
			//System.out.println("********************************");
			NumeralExceptionType numeralExceptionType = new NumeralExceptionType();
			numeralExceptionType.open(((Integer) replaceExceptionList.get(i))
					.intValue());
			String exceptionGender = numeralExceptionType.getGender();
			String exceptionConstruct = numeralExceptionType.getConstruct();
			String exceptionNumber = numeralExceptionType.getNumber();
			//System.out.println("exceptionNumber=" + exceptionNumber);
			//System.out.println("exceptionConstruct=" + exceptionConstruct);
			//System.out.println("exceptionGender=" + exceptionGender);
			if (exceptionGender.equals(gender)
					&& exceptionConstruct.equals(construct)
					&& exceptionNumber.equals(number)) {
				inflectedItem = numeralExceptionType.getTransliterated();
				surface = numeralExceptionType.getUndotted();
				register = numeralExceptionType.getRegister();
				spelling = numeralExceptionType.getSpelling();
				populateDatabase();
				match = true;
				break;
			}
		}
		return match;
	}

	private void analyseExceptionList(List exceptionList) throws Exception {
		for (int i = 0; i < exceptionList.size(); i++) {
			NumeralExceptionType numeralExceptionType = new NumeralExceptionType();
			numeralExceptionType.open(((Integer) exceptionList.get(i))
					.intValue());
			inflectedItem = numeralExceptionType.getTransliterated();
			surface = numeralExceptionType.getUndotted();
			register = numeralExceptionType.getRegister();
			spelling = numeralExceptionType.getSpelling();
			PGN = numeralExceptionType.getPgn();
			construct = numeralExceptionType.getConstruct();
			gender = numeralExceptionType.getGender();
			number = numeralExceptionType.getNumber();
			definiteness = item.getNumeral().getDefiniteness();
			suffixFunction = "unspecified";
		
			if (definiteness.equals("prohibited"))
				definitnessVal = "f";
			else
				definitnessVal = "tf";
			populateDatabase();
			if (construct.equals("false") && ( PGN.equals("unspecified") || PGN.equals(""))){
				setAttributes(gender, "plural", "false", "tt", inflectedItem,
						"unspecified", "unspecified");

				addH();
				

			}
		}
	}

	private void analyse() {
		analyseItem();
		PGN = "unspecified";
		type = item.getNumeral().getType();
		gender = item.getNumeral().getGender();
		number = item.getNumeral().getNumber();
		inflect = item.getNumeral().isInflect();
		inflectedItem = transliterated;
		surface = undot;
		definiteness = item.getNumeral().getDefiniteness();
		if (definiteness.equals("prohibited"))
			definitnessVal = "f";
		else
			definitnessVal = "tf";
		construct = "false";
		value = item.getNumeral().getValue();
	}

	private void generatePossessive() throws Exception {
		String suffixes = "";
		String base = inflectedItem;
		String suffix = "";
		if (type.equals("numeral fractional") && inflect) {
			//System.out.println("|||||||||||||||||||||||||||||||||||||");
			//System.out.println("||||||||||generatePossessive||||||||||||");
			if (number.equals("singular"))
				suffixes = "i,k,k,w,h,nw,km,kn,m,n";
			else if(number.equals("plural") && gender.equals("feminine"))
				suffixes = "ii,ik,iik,iw,ih,inw,ikm,ikn,ihm,ihn";
			else if (number.equals("plural") && gender.equals("masculine"))
				suffixes = "i,k,ik,w,h,nw,km,kn,hm,hn";
			StringTokenizer stPossessive = new StringTokenizer(inflectedItem,
					",");
			StringTokenizer stPGN = new StringTokenizer(PGNTokens10, ",");
			StringTokenizer stPerson = new StringTokenizer(personTokens10, ",");
			StringTokenizer stNumber = new StringTokenizer(numberTokens10, ",");
			StringTokenizer stGender = new StringTokenizer(genderTokens10, ",");
			StringTokenizer stSuffix = new StringTokenizer(suffixes, ",");
			while (stPGN.hasMoreTokens()) {
				PGN = stPGN.nextToken();
				suffix = stSuffix.nextToken();
				inflectedItem = base + suffix;
				//System.out.println();
				//System.out.println("inflectedItem =" + inflectedItem);
				surface = Translate.Eng2Heb(inflectedItem);
				//System.out.println("surface =" + surface);
				//System.out.println();
				populateDatabase();
			}
		}
		inflectedItem = base;
	}

	protected void addH() throws Exception {
		if (!definitness.equals("prohibited")) {
			super.addH();
		}
	}

	protected void generateFeminine() throws Exception {
		//System.out.println("|||||||||||||||||||||||||||||||||||||");
		//System.out.println("||||||||||generateFeminine||||||||||||");
	
			if (replaceExceptionList.size() <= 0) {
				inflectedItem = inflectedItem + "t";
				surface = Translate.Eng2Heb(inflectedItem);
				//System.out.println();
				//System.out.println("inflectedItem =" + inflectedItem);
				//System.out.println("surface =" + surface);
				//System.out.println();
				populateDatabase();
			} else {
				replaceExceptionExist();
			}
			
		
	}

	protected void generateConstruct() throws Exception 
	{
		if (type.equals("numeral fractional")) 
		{
			//System.out.println("|||||||||||||||||||||||||||||||||||||");
			//System.out.println("||||||||||generateConstruct||||||||||||");
			if (number.equals("plural") && gender.equals("masculine"))
				inflectedItem = inflectedItem.substring(0,inflectedItem.length()-1);
			else if(number.equals("plural") && gender.equals("feminine"))
				inflectedItem = inflectedItem.substring(0,inflectedItem.length());
			surface = Translate.Eng2Heb(inflectedItem);
			if(!replaceExceptionExist())
			populateDatabase();
			//System.out.println();
			//System.out.println("inflectedItem =" + inflectedItem);
			//System.out.println("surface =" + surface);
			//System.out.println();
		}else if (type.equals("numeral cardinal") && !inflectedItem.equals("axd") && !inflectedItem.equals("eniim"))
		{
			//System.out.println("|||||||||||||||||||||||||||||||||||||");
			//System.out.println("||||||||||generateConstruct||||||||||||");
			if(gender.equals("masculine"))
			inflectedItem = inflectedItem.substring(0,inflectedItem.length()-1) + "t";
			surface = Translate.Eng2Heb(inflectedItem);
			if(!replaceExceptionExist())
				populateDatabase();
				//System.out.println();
				//System.out.println("inflectedItem =" + inflectedItem);
				//System.out.println("surface =" + surface);
				//System.out.println();
		}
	}

	protected void generatePlural() throws Exception 
	{
		//generate masculine plural
		if (type.equals("numeral ordinal")) 
		{
			//System.out.println("|||||||||||||||||||||||||||||||||||||");
			//System.out.println("||||||||||generatePlural||||||||||||");
			if (gender.equals("masculine")) 
			{
				inflectedItem = transliterated + "im";
			} 
			else if (gender.equals("feminine")) 
			{
				inflectedItem = transliterated + "wt";			
			}
			surface = Translate.Eng2Heb(inflectedItem);
			populateDatabase();

		} 
		else if (type.equals("numeral fractional")) 
		{
			//System.out.println("|||||||||||||||||||||||||||||||||||||");
			//System.out.println("||||||||||generatePlural||||||||||||");
			if (gender.equals("masculine")) {
				inflectedItem = inflectedItem + "im";
				surface = Translate.Eng2Heb(inflectedItem);
			} else if (gender.equals("feminine")) {
				inflectedItem = inflectedItem.substring(0, inflectedItem
						.length() - 1)
						+ "wt";
				surface = Translate.Eng2Heb(inflectedItem);
			}
			//System.out.println();
			//System.out.println("inflectedItem =" + inflectedItem);
			//System.out.println("surface =" + surface);
			//System.out.println();
			if(!replaceExceptionExist())
			populateDatabase();
		}

	}

	private void setAttributes(String gender, String number, String construct,
			String definitnessVal, String inflectedItem, String PGN,
			String suffixFunction) {
		this.inflectedItem = inflectedItem;
		this.number = number;
		this.gender = gender;
		this.construct = construct;
		this.definitnessVal = definitnessVal;
		this.PGN = PGN;
		this.suffixFunction = suffixFunction;
	}

	public void inflect() throws Exception {
		String feminineBase = "";
		String pluralBase = "";
		analyse();
		populateDatabase();
		replaceException();
		//numeral ordinal - ����� ��� ����� �����
		//numeral cardinal - ���,����� ����

		//if (!baseNumeralType.equals("numeral cardinal")) {
			replaceException();

			setAttributes(gender, number, construct, "tt", inflectedItem,
					"unspecified", "unspecified");

			addH();
			
			setAttributes(gender, number, "true", "unspecified", transliterated,
					"unspecified", "unspecified");

			generateConstruct();

			setAttributes(gender, number, "false", "tf", transliterated,
					"unspecified", "possessive");

			generatePossessive();

			
			if (!gender.equals("feminine") && (type.equals("numeral ordinal"))) {

				setAttributes("feminine", "singular", "false", "tf",
						transliterated, "unspecified", "unspecified");

				generateFeminine();
				
				
				feminineBase = inflectedItem;
			

			}

			
			if (type.equals("numeral ordinal") || type.equals("numeral fractional")) 
			if (!number.equals("plural")) {

				setAttributes(item.getNumeral().getGender(), "plural", "false",
						"tf", transliterated, "unspecified", "unspecified");

				generatePlural();

				pluralBase = inflectedItem;
			}

			////////////////////////////////////////////////////////////
			if (!feminineBase.equals("")) {

				number = item.getNumeral().getNumber();

				setAttributes("feminine", number, "false", "tt", feminineBase,
						"unspecified", "unspecified");

				addH();

				setAttributes("feminine", "plural", "false", "tf",
						feminineBase, "unspecified", "unspecified");

				generatePlural();

				setAttributes("feminine", "plural", "false", "tt",
						inflectedItem, "unspecified", "unspecified");

				addH();

				setAttributes("feminine", "plural", "true", "unspecified", feminineBase,
						"unspecified", "unspecified");

				generateConstruct();

				setAttributes("feminine", "plural", "false", "tf",
						feminineBase, "unspecified", "possessive");

				generatePossessive();
			}

			////////////////////////////////////////////////////////////////

			if (!pluralBase.equals("")) {
				gender = item.getNumeral().getGender();

				setAttributes(gender, "plural", "false", "tt", pluralBase,
						"unspecified", "unspecified");

				addH();

				setAttributes(gender, "plural", "true", "unspecified", pluralBase,
						"unspecified", "unspecified");

				generateConstruct();

				setAttributes(gender, "plural", "false", "tf", inflectedItem,
						"unspecified", "possessive");

				generatePossessive();

			}
			/////////////////////////////////////////////////////////////////
//		} 
//		//��� ����� ���
//		else if(baseNumeralType.equals("numeral cardinal") ){
//		
//		//numeral cardinal doesn't accept prefix h	
//		setAttributes(gender, number, "true", "tf", transliterated,
//				"unspecified", "unspecified");
//
//		generateConstruct();
//		}
			addException();
		 
	}
}
