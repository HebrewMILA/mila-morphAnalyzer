/**
 * This class is being inherited by all the pos generation classes <br>
 * It contains methods shared by all the generation classes unless they are
 * overriden by them
 * <p>
 * 
 * @author daliabo
 * @version 1.0
 */
package lexicon.generate;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;
import lexicon.contents.EmptyContent;
import lexicon.contents.types.ItemType;
import lexicon.dbUtils.Inflections;
import lexicon.dbUtils.rules1;
import lexicon.stringUtils.Translate;

public abstract class ItemGen implements ItemInterface {
	protected ItemType item = null;
	protected String transliterated = "";
	protected String plural = "";
	protected String undot = "";
	protected String register = "";
	protected String spelling = "";
	protected String gender = "";
	protected String number = "";
	protected String feminine = "";
	protected String id = "";
	protected String alternateId = "";
	protected String basePos = "";
	protected int pluralSuffixMaxLength;
	protected List list = null;
	protected String table = "";
	private String inflectPossessive = "1";
	protected String suffixes = "";
	protected String PGNTokens = "";

	// these variables are for adverb, preposition
	protected String inflectionBase = "";
	protected String caseSt = "";
	protected String PGN = "";
	protected String inflectedItem = "";
	protected String surface = "";
	protected String suffixFunction = "unspecified";

	// these variables for properName, conjunction, interjection

	protected String definitness = "";
	protected String definitnessVal = "f";
	protected String type = "";
	protected String direction = "";
	protected String basePerson = "";
	protected boolean inflect = false;

	protected List replaceExceptionList = null;
	protected List removeExceptionList = null;
	protected List addExceptionList = null;
	protected String construct = "";
	protected String lexiconGender = "";
	protected String lexiconNumber = "";
	protected String root = "";
	protected String tense = "";
	protected String baseTransliteratedItem = "";
	protected String dottedLexiconItem = "";
	protected String polarityVal = "u";
	protected boolean hebForeign = false;
	protected String value = "";
	protected String prefix = "unspecified";
	
	//TODO changes
	protected List<Inflections> infList;

	// ----------------------------------------------------------------------------------
	public ItemType getItem() {
		return item;
	}

	public void setItem(ItemType item) {
		this.item = item;
	}

	public String getTransliterated() {
		return transliterated;
	}

	public void setTransliterated(String transliterated) {
		this.transliterated = transliterated;
	}

	public String getPlural() {
		return plural;
	}

	public void setPlural(String plural) {
		this.plural = plural;
	}

	public String getUndot() {
		return undot;
	}

	public void setUndot(String undot) {
		this.undot = undot;
	}

	public String getRegister() {
		return register;
	}

	public void setRegister(String register) {
		this.register = register;
	}

	public String getSpelling() {
		return spelling;
	}

	public void setSpelling(String spelling) {
		this.spelling = spelling;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public String getFeminine() {
		return feminine;
	}

	public void setFeminine(String feminine) {
		this.feminine = feminine;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
	
	public String geAlternatetId() {
		return alternateId;
	}

	public void setAlternateId(String alternateId) {
		this.alternateId = alternateId;
	}

	public String getBasePos() {
		return basePos;
	}

	public void setBasePos(String basePos) {
		this.basePos = basePos;
	}

	public int getPluralSuffixMaxLength() {
		return pluralSuffixMaxLength;
	}

	public void setPluralSuffixMaxLength(int pluralSuffixMaxLength) {
		this.pluralSuffixMaxLength = pluralSuffixMaxLength;
	}

	public List getList() {
		return list;
	}

	public void setList(List list) {
		this.list = list;
	}

	public String getTable() {
		return table;
	}

	public void setTable(String table) {
		this.table = table;
	}

	public String getInflectPossessive() {
		return inflectPossessive;
	}

	public void setInflectPossessive(String inflectPossessive) {
		this.inflectPossessive = inflectPossessive;
	}

	public String getSuffixes() {
		return suffixes;
	}

	public void setSuffixes(String suffixes) {
		this.suffixes = suffixes;
	}

	public String getPGNTokens() {
		return PGNTokens;
	}

	public void setPGNTokens(String pGNTokens) {
		PGNTokens = pGNTokens;
	}

	public String getInflectionBase() {
		return inflectionBase;
	}

	public void setInflectionBase(String inflectionBase) {
		this.inflectionBase = inflectionBase;
	}

	public String getCaseSt() {
		return caseSt;
	}

	public void setCaseSt(String caseSt) {
		this.caseSt = caseSt;
	}

	public String getPGN() {
		return PGN;
	}

	public void setPGN(String pGN) {
		PGN = pGN;
	}

	public String getInflectedItem() {
		return inflectedItem;
	}

	public void setInflectedItem(String inflectedItem) {
		this.inflectedItem = inflectedItem;
	}

	public String getSurface() {
		return surface;
	}

	public void setSurface(String surface) {
		this.surface = surface;
	}

	public String getSuffixFunction() {
		return suffixFunction;
	}

	public void setSuffixFunction(String suffixFunction) {
		this.suffixFunction = suffixFunction;
	}

	public String getDefinitness() {
		return definitness;
	}

	public void setDefinitness(String definitness) {
		this.definitness = definitness;
	}

	public String getDefinitnessVal() {
		return definitnessVal;
	}

	public void setDefinitnessVal(String definitnessVal) {
		this.definitnessVal = definitnessVal;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getDirection() {
		return direction;
	}

	public void setDirection(String direction) {
		this.direction = direction;
	}

	public String getBasePerson() {
		return basePerson;
	}

	public void setBasePerson(String basePerson) {
		this.basePerson = basePerson;
	}

	public boolean isInflect() {
		return inflect;
	}

	public void setInflect(boolean inflect) {
		this.inflect = inflect;
	}

	public List getReplaceExceptionList() {
		return replaceExceptionList;
	}

	public void setReplaceExceptionList(List replaceExceptionList) {
		this.replaceExceptionList = replaceExceptionList;
	}

	public List getRemoveExceptionList() {
		return removeExceptionList;
	}

	public void setRemoveExceptionList(List removeExceptionList) {
		this.removeExceptionList = removeExceptionList;
	}

	public List getAddExceptionList() {
		return addExceptionList;
	}

	public void setAddExceptionList(List addExceptionList) {
		this.addExceptionList = addExceptionList;
	}

	public String getConstruct() {
		return construct;
	}

	public void setConstruct(String construct) {
		this.construct = construct;
	}

	public String getLexiconGender() {
		return lexiconGender;
	}

	public void setLexiconGender(String lexiconGender) {
		this.lexiconGender = lexiconGender;
	}

	public String getLexiconNumber() {
		return lexiconNumber;
	}

	public void setLexiconNumber(String lexiconNumber) {
		this.lexiconNumber = lexiconNumber;
	}

	public String getRoot() {
		return root;
	}

	public void setRoot(String root) {
		this.root = root;
	}

	public String getTense() {
		return tense;
	}

	public void setTense(String tense) {
		this.tense = tense;
	}

	public String getBaseTransliteratedItem() {
		return baseTransliteratedItem;
	}

	public void setBaseTransliteratedItem(String baseTransliteratedItem) {
		this.baseTransliteratedItem = baseTransliteratedItem;
	}

	public String getDottedLexiconItem() {
		return dottedLexiconItem;
	}

	public void setDottedLexiconItem(String dottedLexiconItem) {
		this.dottedLexiconItem = dottedLexiconItem;
	}

	public String getPolarityVal() {
		return polarityVal;
	}

	public void setPolarityVal(String polarityVal) {
		this.polarityVal = polarityVal;
	}

	public boolean getHebForeign() {
		return hebForeign;
	}

	public boolean isHebForeign() {
		return hebForeign;
	}

	public void setHebForeign(boolean hebForeign) {
		this.hebForeign = hebForeign;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String getPrefix() {
		return prefix;
	}

	public void setPrefix(String prefix) {
		this.prefix = prefix;
	}

	// ----------------------------------------------------------------------------------
	public ItemGen(ItemType item) {
		this.item = item;
		infList = new ArrayList<Inflections>();
	}

	protected String buildSql(String action, String table) {
		String sql = "select * from " + table + " where action= '" + action
				+ "' and id = " + id;
		return sql;
	}

	protected List handleException(String sql) {
		EmptyContent eContent = new EmptyContent();
		// System.out.println("sql=" + sql);
		List list = eContent.getContents(sql, "aid");
		return list;
	}

	protected void getException(String action) {
		String sql = "";
		if (basePos.equals("adjective"))
			sql = buildSql(action, "noun_exception_type");
		else if (basePos.equals("acronym"))
			sql = buildSql(action, "acronym_exception_type");
		else
			sql = buildSql(action, basePos + "_exception_type");
		if (action.equals("add"))
			addExceptionList = handleException(sql);
		else if (action.equals("remove"))
			removeExceptionList = handleException(sql);
		else if (action.equals("replace"))
			replaceExceptionList = handleException(sql);
	}

	/**
	 * This method is a service method used by nouns and preposition <br>
	 * It is used as a basis for possessive generation, in case the basis is not
	 * the construct form
	 * 
	 * @throws UnsupportedEncodingException
	 */
	protected void inflectionBaseHandling() throws UnsupportedEncodingException {
		inflectionBase = URLDecoder.decode(inflectionBase, "UTF-8");
		inflectionBase = Translate.Heb2Eng(inflectionBase);
	}

	/**
	 * This methos is the interface to the rules handling class The relevant
	 * rule is selected by 3 parameters: <br>
	 * 1) The action - a text describing the rule action for example pluralWTnoun <br>
	 * It means that this rule is applicable for generating the plural with the
	 * suffix WT <br>
	 * For the nouns - the actions chosen according to the rules files of xfst <br>
	 * 2) Another parameter which allows us to differntiate in case there is a
	 * simmilar action <br>
	 * 3) The inputPattern - the relevant suffix that should be found on the
	 * transliterated <br>
	 * 
	 * @param transliterated
	 *            - the word on which rules will take place
	 * @param inputCondition
	 *            - parameter which allows us to differntiate in case there is a
	 *            simmilar action
	 * @param action
	 *            - each rule is identified by the action and inputCondition
	 * @param maxLen
	 *            - the translitered suffix length to start comparing to the
	 *            inputPattern
	 * @throws Exception
	 */
	protected void findRule(String transliterated, String inputCondition,
			String action, int maxLen) throws Exception {
		// System.out.println("(F) findRule("+transliterated+","+inputCondition+","+action+")");
		rules1 rules = new rules1();
		rules.setAction(action);
		rules.setInputCondition(inputCondition);
		rules.setTransliterated(transliterated);
		rules.setMaxLength(maxLen);
		rules.getRules();
		// inflectedItem - the transliterated after rules have been acting on it
		inflectedItem = rules.getInflectedTransliterated();
		// System.out.println("inflectedItem = " + inflectedItem);
	}

	/**
	 * Tis method is used by all the generation classes to get the shared
	 * attributes <br>
	 * Such as: id,script,basePos etc <br>
	 * The specific attributes per pos are got specifically by each of the poses <br>
	 */
	protected void analyseItem() {
		// System.out.println(" analyseItem");
		undot = item.getUndotted();
		// undot = undot.replaceAll("&#39;", "'");
		undot = undot.replaceAll("&#60;", "`");
		// System.out.println("undot = " + undot);
		transliterated = item.getTransliterated();
		// transliterated = transliterated.replaceAll("&#39;", "'");
		transliterated = transliterated.replaceAll("&#60;", "`");
		baseTransliteratedItem = transliterated;
		// System.out.println("transliterated = " + transliterated);
		//TODO I DID THIS
		id = item.getId();
		alternateId = item.getAlternateId();
		register = item.getRegister();
		spelling = item.getSpelling();
		basePos = item.getPos();
		dottedLexiconItem = item.getDotted();
		// dottedLexiconItem = dottedLexiconItem.replaceAll("&#39;", "'");
		dottedLexiconItem = dottedLexiconItem.replaceAll("&#60;", "`");
	}

	/**
	 * This method is used for populating the database with the generated items <br>
	 * The verb has it's own population method
	 * 
	 * @throws Exception
	 */
	protected void populateDatabase() throws Exception {

		Inflections inf = new Inflections();
		// ����� ������ �' ����� ���� ���"�
		if (undot.endsWith("%D7%9D")) {
			if (surface.endsWith("%D7%9E"))
				surface.replaceAll("%D7%9E", "%D7%9D");
		}
		inf.setSurface(this.surface);
		inf.setTransliterated(this.inflectedItem);
		inf.setRegister(this.register);
		inf.setSpelling(this.spelling);
		inf.setBaseLexiconPointer(this.id);
		inf.setBaseTransliteratedLItem(this.baseTransliteratedItem);
		inf.setBaseundottedLItem(this.undot);
		inf.setBasePos(this.basePos);
		inf.setPGN(this.PGN);
		inf.setBaseGender(gender);
		inf.setBaseNumber(number);
		inf.setType(type);
		inf.setPolarity(polarityVal);
		inf.setBaseDefinitness(definitnessVal);
		inf.setBasePerson(basePerson);
		inf.setSuffixStatus(construct);
		inf.setSuffixFunction(suffixFunction);
		inf.setRoot(root);
		inf.setTense(tense);
		inf.setDottedLexiconItem(dottedLexiconItem);
		inf.setHebForeign(String.valueOf((hebForeign) ? 1 : 0));
		inf.setValue(value);
		inf.setPrefix(prefix);
		inf.setBaseAlternatePointer(this.alternateId);
		//TODO inf.insertItem();
		infList.add(inf);
	}
	
	protected void insertToDB(){
		for(Inflections inf : infList)
			inf.insertItem();
	}
	
	public void switchBaseLexicalPointerAndInsert(String newId){
		for(Inflections inf : infList){
			inf.setBaseAlternatePointer(inf.getBaseLexiconPointer());
			inf.setBaseLexiconPointer(newId);
		}
		insertToDB();
	}

	// ----------------------------------------------------------------------------------------------
	/**
	 * This method is used by interjection
	 * 
	 * @throws UnsupportedEncodingException
	 * @throws Exception
	 */
	protected void inflectPronomial(String input_suffixFunction)
			throws UnsupportedEncodingException, Exception {
		suffixFunction = input_suffixFunction;
		StringTokenizer stSuff = new StringTokenizer(suffixes, ",");
		StringTokenizer stPGN = new StringTokenizer(PGNPRONOMIALTokens10, ",");
		StringTokenizer stPerson = new StringTokenizer(personTokens10, ",");
		StringTokenizer stNumber = new StringTokenizer(numberTokens10, ",");
		StringTokenizer stGender = new StringTokenizer(genderTokens10, ",");
		definitnessVal = "unspecified";
		while (stSuff.hasMoreTokens()) {
			boolean removeFlag = false;
			boolean replaceFlag = false;
			String suffix = stSuff.nextToken();
			if (!inflectionBase.equals(""))
				inflectedItem = inflectionBase + suffix;
			else
				inflectedItem = transliterated + suffix;
			// System.out.println();
			// System.out.println("inflectedItem =" + inflectedItem);
			surface = Translate.Eng2Heb(inflectedItem);
			// System.out.println("surface =" + surface);
			// System.out.println();

			PGN = stPGN.nextToken();
			// register="formal";
			int replaceSize = replaceExceptionList.size();
			int removeSize = removeExceptionList.size();
			if (replaceSize > 0) {
				replaceFlag = replaceExceptionExist();

			}
			if (removeSize > 0) {
				removeFlag = removeExceptionExist();

			}
			if (!replaceFlag && !removeFlag)
				populateDatabase();

		}
	}

	// /**
	// * This method is used by interjection
	// *
	// * @throws UnsupportedEncodingException
	// * @throws Exception
	// */
	// protected void inflectPossessive() throws UnsupportedEncodingException,
	// Exception {
	// suffixFunction = "possessive";
	// StringTokenizer stSuff = new StringTokenizer(suffixes, ",");
	// StringTokenizer stPGN = new StringTokenizer(PGNTokens, ",");
	// if (!inflectionBase.equals("")) {
	//
	// inflectionBase = URLDecoder.decode(inflectionBase, "UTF-8");
	//
	// inflectionBase = Translate.Heb2Eng(inflectionBase);
	// //System.out.println("inflectionBase =" + inflectionBase);
	// }
	// while (stSuff.hasMoreTokens()) {
	// String suffix = stSuff.nextToken();
	// if (!inflectionBase.equals(""))
	// inflectedItem = inflectionBase + suffix;
	// else
	// inflectedItem = transliterated + suffix;
	// //System.out.println();
	// //System.out.println("inflectedItem =" + inflectedItem);
	// surface = Translate.Eng2Heb(inflectedItem);
	// //System.out.println("surface =" + surface);
	// //System.out.println();
	//
	// PGN = stPGN.nextToken();
	// if (replaceExceptionList.size() > 0) {
	// if (!replaceExceptionExist())
	// populateDatabase();
	// } else
	// populateDatabase();
	// }
	// }

	/**
	 * This method is a service method used by noun and adjective <br>
	 * The action string is used for applying the rules table The method also
	 * set pluralSuffixMaxLength needed for applying the rules table
	 * 
	 * @param pluralSuffix
	 *            - this value is defined for each lexicon item
	 * @return action string - used for applying the rules table
	 */
	protected String identifyPluralAction(String pluralSuffix) {
		StringBuffer action = new StringBuffer().append("plural")
				.append(pluralSuffix.toUpperCase()).append(basePos);
		if (pluralSuffix.equals("wt")) {
			pluralSuffixMaxLength = 3;
		} else if (pluralSuffix.equals("iwt")) {
			pluralSuffixMaxLength = 3;
		} else if (pluralSuffix.equals("awt")) {
			pluralSuffixMaxLength = 1;
		} else if (pluralSuffix.equals("iim")) {
			pluralSuffixMaxLength = 3;
		} else if (pluralSuffix.equals("im")) {
			pluralSuffixMaxLength = 2;
		} else if (pluralSuffix.equals("m")) {
			pluralSuffixMaxLength = 1;
		}
		// System.out.println("pluralSuffix = " + pluralSuffix);
		// System.out.println("action = " + action);
		return action.toString();
	}

	/**
	 * This method is a service method used by noun and adjective <br>
	 * It builds the action string used for applying the rules table
	 * 
	 * @return action string used for applying the rules table
	 */
	protected String identifyFeminineAction() {
		StringBuffer action = new StringBuffer().append("feminine")
				.append(feminine.toUpperCase()).append("Singular")
				.append(basePos);
		return action.toString();
	}

	/**
	 * This method is used by noun, adjective, quantifier,pronoun <br>
	 * It is used to generate h+inflectedItem
	 * 
	 * @throws Exception
	 */
	protected void addH() throws Exception {
		// System.out.println("|||||||||||||||||||||||||||||||||||||");
		// System.out.println("||||||||||generateHForm||||||||||||");
		inflectedItem = "h" + inflectedItem;
		surface = Translate.Eng2Heb(inflectedItem);
		populateDatabase();

	}

	protected boolean replaceExceptionExist()
			throws UnsupportedEncodingException, Exception {
		return false;
	}

	protected boolean removeExceptionExist()
			throws UnsupportedEncodingException, Exception {
		return false;
	}

	public void inflect() throws Exception{
		generateInflects();
		insertToDB();
	}
	
	public abstract void generateInflects() throws Exception;

}