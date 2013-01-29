/**
 * This class is being inherited by all the pos generation classes <br>
 * It contains methods shared by all the generation classes unless they are
 * overriden by them
 * <p>
 * 
 * @author daliabo
 * @version 1.0
 */
package org.mila.generator.generation;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

import javax.persistence.EntityManager;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.text.StrTokenizer;
import org.eclipse.persistence.expressions.Expression;
import org.eclipse.persistence.expressions.ExpressionBuilder;
import org.eclipse.persistence.jpa.JpaEntityManager;
import org.eclipse.persistence.queries.ReadAllQuery;
import org.mila.entities.corpus.DefinitenessType;
import org.mila.entities.corpus.GenderType;
import org.mila.entities.corpus.NumberType;
import org.mila.entities.corpus.PolarityType;
import org.mila.entities.corpus.PrefixTransliteratedType;
import org.mila.entities.corpus.RegisterType;
import org.mila.entities.corpus.SpellingType;
import org.mila.entities.corpus.SuffixFunctionType;
import org.mila.entities.corpus.TenseType;
import org.mila.entities.corpus.TriStateType;
import org.mila.entities.generator.InflectionRule;
import org.mila.entities.inflections.Inflection;
import org.mila.entities.inflections.PersistableInflection;
import org.mila.entities.lexicon.Definitness;
import org.mila.entities.lexicon.Feminine;
import org.mila.entities.lexicon.Gender;
import org.mila.entities.lexicon.Item;
import org.mila.entities.lexicon.Number;
import org.mila.entities.lexicon.Plural;
import org.mila.generator.utils.Transliteration;

import edu.emory.mathcs.backport.java.util.Collections;

public abstract class ItemGen implements ItemInterface {
	protected Item item = null;
	protected EntityManager lexicon = null;
	protected EntityManager generator = null;
	protected EntityManager inflections = null;

	/* really needed .. ? */
	protected String transliterated = "";
	protected Plural plural = Plural.UNSPECIFIED;
	protected String undot = "";
	protected RegisterType register;
	protected SpellingType spelling;
	protected GenderType gender;
	protected NumberType number;
	protected Feminine feminine = Feminine.UNSPECIFIED;
	protected String id;
	protected String basePos = "";
	protected int pluralSuffixMaxLength;
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
	protected SuffixFunctionType suffixFunction = SuffixFunctionType.UNSPECIFIED;

	// these variables for properName, conjunction, interjection

	protected Definitness definitness;
	protected DefinitenessType definitnessVal = DefinitenessType.FALSE;
	protected String type = "unspecified";
	protected String direction = "";
	protected String basePerson = "";
	protected boolean inflect = false;

	protected TriStateType construct = TriStateType.UNSPECIFIED;
	protected Gender lexiconGender = Gender.UNSPECIFIED;
	protected Number lexiconNumber = Number.UNSPECIFIED;
	protected String root = "";
	protected TenseType tense = TenseType.UNSPECIFIED;
	protected String baseTransliteratedItem = "";
	protected String dottedLexiconItem = "";
	protected PolarityType polarityVal = PolarityType.UNSPECIFIED;
	protected boolean hebForeign = false;
	protected String value = "";
	protected PrefixTransliteratedType prefix = PrefixTransliteratedType.UNSPECIFIED;

	protected List<PersistableInflection> generatedInflections;
	
	// ----------------------------------------------------------------------------------
	public Item getItem() {
		return item;
	}

	public void setItem(Item item) {
		this.item = item;
	}

	public String getTransliterated() {
		return transliterated;
	}

	public void setTransliterated(String transliterated) {
		this.transliterated = transliterated;
	}

	public Plural getPlural() {
		return plural;
	}

	public void setPlural(Plural plural) {
		this.plural = plural;
	}

	public String getUndot() {
		return undot;
	}

	public void setUndot(String undot) {
		this.undot = undot;
	}

	public RegisterType getRegister() {
		return register;
	}

	public void setRegister(RegisterType register) {
		this.register = register;
	}

	public SpellingType getSpelling() {
		return spelling;
	}

	public void setSpelling(SpellingType spelling) {
		this.spelling = spelling;
	}

	public GenderType getGender() {
		return gender;
	}

	public void setGender(GenderType gender) {
		this.gender = gender;
	}

	public NumberType getNumber() {
		return number;
	}

	public void setNumber(NumberType number) {
		this.number = number;
	}

	public Feminine getFeminine() {
		return feminine;
	}

	public void setFeminine(Feminine feminine) {
		this.feminine = feminine;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
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

	public SuffixFunctionType getSuffixFunction() {
		return suffixFunction;
	}

	public void setSuffixFunction(SuffixFunctionType suffixFunction) {
		this.suffixFunction = suffixFunction;
	}

	public Definitness getDefinitness() {
		return definitness;
	}

	public void setDefinitness(Definitness definitness) {
		this.definitness = definitness;
	}

	public DefinitenessType getDefinitnessVal() {
		return definitnessVal;
	}

	public void setDefinitnessVal(DefinitenessType definitnessVal) {
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

	public TriStateType getConstruct() {
		return construct;
	}

	public void setConstruct(TriStateType construct) {
		this.construct = construct;
	}

	public Gender getLexiconGender() {
		return lexiconGender;
	}

	public void setLexiconGender(Gender lexiconGender) {
		this.lexiconGender = lexiconGender;
	}

	public Number getLexiconNumber() {
		return lexiconNumber;
	}

	public void setLexiconNumber(Number lexiconNumber) {
		this.lexiconNumber = lexiconNumber;
	}

	public String getRoot() {
		return root;
	}

	public void setRoot(String root) {
		this.root = root;
	}

	public TenseType getTense() {
		return tense;
	}

	public void setTense(TenseType tense) {
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

	public PolarityType getPolarityVal() {
		return polarityVal;
	}

	public void setPolarityVal(PolarityType polarityVal) {
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

	public PrefixTransliteratedType getPrefix() {
		return prefix;
	}

	public void setPrefix(PrefixTransliteratedType prefix) {
		this.prefix = prefix;
	}

	// ----------------------------------------------------------------------------------
	public ItemGen(Item item, EntityManager lexicon, EntityManager generator,
			EntityManager inflections) {
		this.item = item;
		this.lexicon = lexicon;
		this.generator = generator;
		this.inflections = inflections;
		this.generatedInflections = new ArrayList<PersistableInflection>();
	}

	/**
	 * This method is the interface to the rules handling class The relevant
	 * rule is selected by 3 parameters: 1) The action - a text describing the
	 * rule action for example pluralWTnoun <br>
	 * It means that this rule is applicable for generating the plural with the
	 * suffix WT <br>
	 * For the nouns - the actions chosen according to the rules files of xfst <br>
	 * 2) Another parameter which allows us to differentiate in case there is a
	 * similar action <br>
	 * 3) The inputPattern - the relevant suffix that should be found on the
	 * transliterated <br>
	 * 
	 * @param transliterated
	 *            - the word on which rules will take place
	 * @param inputCondition
	 *            - parameter which allows us to differentiate in case there is
	 *            a similar action
	 * @param action
	 *            - each rule is identified by the action and inputCondition
	 * @param maxLen
	 *            - the transliterated suffix length to start comparing to the
	 *            inputPattern
	 * @throws Exception
	 */
	protected void findRule(String transliterated, String inputCondition,
			String action, int maxLen) {
		String inflected = transliterated;
		ExpressionBuilder eb = new ExpressionBuilder();
		ReadAllQuery rq = new ReadAllQuery(InflectionRule.class, eb);
		Expression e = eb.get("inputCondition")
				.equalsIgnoreCase(inputCondition);
		e = e.and(eb.get("action").equalsIgnoreCase(eb.value(action)));
		e = e.and(eb.get("inputPattern").equalsIgnoreCase("default").not());
		rq.setSelectionCriteria(e);
		rq.addOrdering(eb.get("inputSuffixLen").descending());

		@SuppressWarnings("unchecked")
		List<InflectionRule> rules = ((JpaEntityManager) generator
				.getDelegate()).createQuery(rq).getResultList();

		if (maxLen > transliterated.length() - 1)
			maxLen = transliterated.length() - 1;
		
		for (InflectionRule rule : rules) {
			for (int i = maxLen; i > 0; --i) {
				String suffix = inflected.substring(inflected.length() - i);
				if (suffix.equals(rule.getInputPattern())) {
					inflected = StringUtils.substring(inflected, 0, -i)
							+ rule.getInflectedPattern();
					maxLen = i;
					break;
				}
			}
		}

		/* now default rules */
		rq = new ReadAllQuery(InflectionRule.class, eb);
		e = eb.get("inputCondition").equalsIgnoreCase(inputCondition);
		e = e.and(eb.get("action").equalsIgnoreCase(eb.value(action)));
		e = e.and(eb.get("inputPattern").equalsIgnoreCase("default"));
		rq.setSelectionCriteria(e);
		rq.addOrdering(eb.get("inputSuffixLen").descending());

		@SuppressWarnings("unchecked")
		List<InflectionRule> default_rules = ((JpaEntityManager) generator
				.getDelegate()).createQuery(rq).getResultList();

		for (InflectionRule rule : default_rules) {
			String base = inflected;
			if (StringUtils.contains(rule.getInflectedPattern(), ",")) {
				StrTokenizer stSuffix = new StrTokenizer(
						rule.getInflectedPattern(), ",");
				inflected = "";
				while (stSuffix.hasNext()) {
					String suffix = stSuffix.nextToken();
					if (suffix.equals("-")) {
						suffix = "";
					}
					inflected = inflected + base + suffix + ",";
				}
			} else {
				inflected = inflected + rule.getInflectedPattern();
			}
		}

		// inflectedItem - the transliterated after rules have been acting on it
		inflectedItem = inflected;
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
		id = item.getId();
		register = RegisterType.fromValue(item.getRegister().value());
		spelling = SpellingType.fromValue(item.getSpelling().value());
		basePos = item.getSubitem().getClass().getSimpleName()
				.replaceFirst("Lexicon$", "");
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
	protected void populateDatabase() {
		Inflection inf = new Inflection();
		inf.setSurface(this.surface);
		inf.setTransliterated(this.inflectedItem);
		inf.setRegister(this.register);
		inf.setSpelling(this.spelling);
		inf.setBaseLexiconPointer(this.id);
		inf.setBaseTransliteratedLItem(this.baseTransliteratedItem);
		inf.setBaseUndottedLItem(this.undot);
		inf.setBasePos(this.basePos);
		inf.setPGN(this.PGN);
		inf.setBaseGender(gender);
		inf.setBaseNumber(number);
		inf.setType(type);
		inf.setPolarity(polarityVal);
		inf.setBaseDefiniteness(definitnessVal);
		inf.setBasePerson(basePerson);
		inf.setSuffixStatus(construct);
		inf.setSuffixFunction(suffixFunction);
		inf.setRoot(root);
		inf.setTense(tense);
		inf.setDottedLexiconItem(dottedLexiconItem);
		inf.setHebForeign(hebForeign);
		inf.setValue(value);
		inf.setPrefix(prefix);
		this.generatedInflections.add(inf);
	}

	// ----------------------------------------------------------------------------------------------
	/**
	 * This method is used by interjection
	 * 
	 * @throws UnsupportedEncodingException
	 * @throws Exception
	 */
	protected void inflectPronomial(SuffixFunctionType input_suffixFunction) {
		suffixFunction = input_suffixFunction;
		StringTokenizer stSuff = new StringTokenizer(suffixes, ",");
		StringTokenizer stPGN = new StringTokenizer(PGNPRONOMIALTokens10, ",");

		definitnessVal = DefinitenessType.UNSPECIFIED;
		while (stSuff.hasMoreTokens()) {
			String suffix = stSuff.nextToken();
			if (!inflectionBase.equals(""))
				inflectedItem = inflectionBase + suffix;
			else
				inflectedItem = transliterated + suffix;

			surface = Transliteration.toHebrew(inflectedItem);

			PGN = stPGN.nextToken();

			boolean replaceFlag = replaceExceptionExist();
			boolean removeFlag = removeExceptionExist();

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
	 * @param plural2
	 *            - this value is defined for each lexicon item
	 * @return action string - used for applying the rules table
	 */
	protected String identifyPluralAction(Plural plural2) {
		StringBuffer action = new StringBuffer().append("plural")
				.append(plural2.value().toUpperCase()).append(basePos);
		switch (plural2) {
		case IWT:
		case WT:
		case IIM:
			pluralSuffixMaxLength = 3;
			break;
		case AWT:
		case M:
			pluralSuffixMaxLength = 1;
			break;
		case IM:
			pluralSuffixMaxLength = 2;
			break;
		default:
			break;
		}
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
				.append(feminine.value().toUpperCase()).append("Singular")
				.append(basePos);
		return action.toString();
	}

	/**
	 * This method is used by noun, adjective, quantifier,pronoun <br>
	 * It is used to generate h+inflectedItem
	 * 
	 * @throws Exception
	 */
	protected void addH() {
		inflectedItem = "h" + inflectedItem;
		surface = Transliteration.toHebrew(inflectedItem);
		populateDatabase();
	}

	protected boolean replaceExceptionExist() {
		return false;
	}

	protected boolean removeExceptionExist() {
		return false;
	}

	public abstract List<PersistableInflection> inflect();
	
	@SuppressWarnings("unchecked")
	public List<PersistableInflection> getGeneratedInflections() {
		return Collections.unmodifiableList(generatedInflections);
	}


}