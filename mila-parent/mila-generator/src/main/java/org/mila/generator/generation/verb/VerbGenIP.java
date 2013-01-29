package org.mila.generator.generation.verb;

import static ch.lambdaj.Lambda.filter;

import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

import javax.persistence.EntityManager;

import org.hamcrest.Matchers;
import org.mila.entities.corpus.BinyanType;
import org.mila.entities.corpus.DefinitenessType;
import org.mila.entities.corpus.GenderType;
import org.mila.entities.corpus.NumberType;
import org.mila.entities.corpus.PolarityType;
import org.mila.entities.corpus.RegisterType;
import org.mila.entities.corpus.SpellingType;
import org.mila.entities.corpus.SuffixFunctionType;
import org.mila.entities.corpus.TenseType;
import org.mila.entities.corpus.TriStateType;
import org.mila.entities.inflections.Inflection;
import org.mila.entities.inflections.PersistableInflection;
import org.mila.entities.lexicon.Valence;
import org.mila.entities.lexicon.VerbException;
import org.mila.entities.lexicon.VerbExceptionAdd;
import org.mila.entities.lexicon.VerbExceptionRemove;
import org.mila.entities.lexicon.VerbExceptionReplace;
import org.mila.entities.lexicon.VerbLexicon;
import org.mila.generator.utils.Transliteration;

import edu.emory.mathcs.backport.java.util.Collections;

public class VerbGenIP implements VerbInterface {
	private static enum ExcpType {
		ADD, REPLACE, REMOVE
	}

	private String root = "";
	private String inflectedVerb = null;
	private String surface = "";
	private RegisterType register;
	private SpellingType spelling;
	private String baseTransliterated = "";
	private String baseUndot = "";
	private String baseLexiconPointer = "";
	private TenseType tense;
	private BinyanType binyan;
	private String possessive = "";
	private String hebRoot = "";
	private TriStateType construct;
	private String pos = "verb"; 
	private DefinitenessType baseDefiniteness = DefinitenessType.FALSE;
	private NumberType baseNumber;
	private GenderType baseGender;
	private String basePerson;
	private Valence valence;
	private String dottedLexiconItem;
	private String participleType = "unspecified";

	private int intInflectedPattern;
	StringTokenizer stPGN10 = null;
	StringTokenizer stPerson10 = null;
	StringTokenizer stGender10 = null;
	StringTokenizer stNumber10 = null;
	StringTokenizer stGender4 = null;
	StringTokenizer stNumber4 = null;
	StringTokenizer stPerson4 = null;
	StringTokenizer stPGNBeinoni = null;

	StringTokenizer stPGNImperative = null;

	public SuffixFunctionType suffixFunction = SuffixFunctionType.UNSPECIFIED;
	boolean inflectInfinitivel;
	boolean inflectInfinitiveb;
	boolean inflectBeinoni;
	boolean inflectBeinoniPossessive = true;
	boolean inflectBeinoniConstruct = true;
	boolean inflectPast = true;
	boolean inflectFuture = true;
	boolean inflectImperative = true;
	boolean inflectInfinitive = true;
	boolean inflectBareInfinitive = true;
	boolean replaceRemoveInfinitive = false;
	boolean inflectInfinitiveIndependent = false;
	boolean exceptionInflectBeinoniPossessive = false;

	public boolean bareInfinitiveException = false;
	String table = "";
	protected EntityManager inflections;

	protected EntityManager lexicon;
	private VerbLexicon verb;
	private List<PersistableInflection> generatedInflections;

	@SuppressWarnings("unchecked")
	public List<PersistableInflection> getGeneratedInflections() {
		return Collections.unmodifiableList(generatedInflections);
	}

	public VerbGenIP(final String root, final VerbLexicon verb,
			final EntityManager inflections, final EntityManager lexicon) {
		this.root = root;
		this.inflections = inflections;
		this.lexicon = lexicon;
		this.verb = verb;
		this.generatedInflections = new ArrayList<PersistableInflection>();
	}

	/**
	 * This methos is used for handling add exceptions <br>
	 * It scans the addExceptionList and populate the database with each of the
	 * values
	 * 
	 * @param exceptionList
	 *            - addExceptionList
	 * @throws Exception
	 */
	public void addException(final VerbException excp) {
		this.pos = "verb";
		this.baseGender = GenderType.fromValue(excp.getGender().value());
		this.baseNumber = NumberType.fromValue(excp.getNumber().value());
		this.basePerson = excp.getPerson();
		this.spelling = SpellingType.fromValue(excp.getSpelling().value());
		this.register = RegisterType.fromValue(excp.getRegister().value());
		this.surface = excp.getUndotted();
		// System.out.println(surface);
		this.inflectedVerb = excp.getTransliterated();
		this.tense = TenseType.fromValue(excp.getTense().value());
		this.possessive = excp.getPossessive();
		this.suffixFunction = SuffixFunctionType.UNSPECIFIED;
		final boolean isBeinoniConstruct = excp.isBeinoniConstruct();
		// החלטנו לא לייצר מקור
		if (this.tense == TenseType.BARE_INFINITIVE)
			return;
		if (this.tense == TenseType.BEINONI) {
			this.pos = "participle";
			if (isBeinoniConstruct) {
				this.suffixFunction = SuffixFunctionType.UNSPECIFIED;
				this.baseDefiniteness = DefinitenessType.FALSE;
				this.participleType = "noun";
				this.construct = TriStateType.TRUE;
			} else if (!this.possessive.equals("unspecified")) {
				this.suffixFunction = SuffixFunctionType.POSSESSIVE;
				this.baseDefiniteness = DefinitenessType.TRUE;
				this.participleType = "noun";
				this.construct = TriStateType.UNSPECIFIED;

			} else if (this.possessive.equals("unspecified")) {
				this.construct = TriStateType.FALSE;
				this.suffixFunction = SuffixFunctionType.UNSPECIFIED;
				this.participleType = "noun";
				this.populateDBTable();
				this.participleType = "adjective";
				this.populateDBTable();
				this.participleType = "verb";
				this.populateDBTable();
			}

		} else if (this.tense == TenseType.INFINITIVE) {
			this.construct = TriStateType.UNSPECIFIED;
			this.participleType = "unspecified";
			// handle inflected infinitive
			if (!this.possessive.equals("unspecified")) {
				this.suffixFunction = SuffixFunctionType.ACCUSATIVE_OR_NOMINATIVE;
				this.baseDefiniteness = DefinitenessType.FALSE;

				// handle non inflected infinitive
			} else if (this.possessive.equals("unspecified")) {
				this.suffixFunction = SuffixFunctionType.UNSPECIFIED;
				this.baseDefiniteness = DefinitenessType.FALSE;
			}
			this.populateDBTable();
		} else if (this.tense == TenseType.PASSIVE_PARTICIPLE) {
			this.pos = "passiveParticiple";
			this.tense = TenseType.BEINONI;
			this.basePerson = "any";
			this.possessive = "unspecified";
			this.participleType = "adjective";
			if (isBeinoniConstruct) {
				this.baseDefiniteness = DefinitenessType.FALSE;
				this.construct = TriStateType.TRUE;
			} else {
				this.construct = TriStateType.FALSE;
			}
			this.populateDBTable();
		} else {
			this.populateDBTable();
		}

		final boolean exceptioniBeinoniDefiniteness = excp
				.isBeinoniDefiniteness();
		if (exceptioniBeinoniDefiniteness) {
			this.inflectedVerb = "h" + excp.getTransliterated();
			this.surface = Transliteration.toHebrew(this.inflectedVerb);
			// System.out.println("exception transliterated  ="+
			// inflectedVerb);
			this.baseDefiniteness = DefinitenessType.TRUE;
			this.participleType = "noun";
			this.populateDBTable();
			this.participleType = "adjective";
			this.populateDBTable();
			this.participleType = "verb";
			this.baseDefiniteness = DefinitenessType.UNSPECIFIED;
			this.populateDBTable();
		}
	}

	public void addExceptions(final List<VerbException> addExceptions) {
		for (final VerbException excp : addExceptions) {
			this.addException(excp);
		}

	}

	/**
	 * This method generate the possessive form out of the 123/M/Sg form
	 * 
	 * @param base
	 *            - the base form from which the possessive form will be created
	 * @throws Exception
	 */
	public void generateAccusativeNominative(final String base) {

		StringTokenizer stSuff = null;
		String suff;

		this.setAttributes(this.tense, this.pos, "unspecified",
				this.basePerson, this.baseGender, this.baseNumber,
				TriStateType.FALSE, "unspecified",
				SuffixFunctionType.ACCUSATIVE_OR_NOMINATIVE, "unspecified",
				"unspecified", "unspecified", DefinitenessType.FALSE);
		this.stPGN10 = new StringTokenizer(PGNTokens10, ",");

		if (this.baseGender == GenderType.FEMININE
				&& this.baseNumber == NumberType.PLURAL) {
			suff = suffixFPlural;
		} else if (this.baseGender == GenderType.MASCULINE
				&& this.baseNumber == NumberType.PLURAL) {
			suff = suffixMPlural;
		} else {
			suff = suffixSingular;
		}
		// System.out.println("suff=" + suff);
		stSuff = new StringTokenizer(suff, ",");
		String suffPossessive;
		while (this.stPGN10.hasMoreTokens()) {
			this.possessive = this.stPGN10.nextToken();
			suffPossessive = stSuff.nextToken();
			if (suffPossessive.equals("-")) {
				suffPossessive = "";
			}
			this.inflectedVerb = base + suffPossessive;

			if (this.inflectInfinitiveb) {
				if (this.inflectInfinitiveIndependent) {
					this.pos = "indInf";
					// System.out.println(inflectedVerb);
					this.surface = Transliteration.toHebrew(this.inflectedVerb);
					// System.out.print("surface =" + surface);
					// System.out.println();
					this.populateDBTable();
					this.pos = "verb";
				}
			}

			// generate l + inflectedInfinitive just for יוצא or עומד with
			// inflectInfinitive = true
			if (this.inflectInfinitivel) {
				this.inflectedVerb = "l" + this.inflectedVerb;
				// System.out.println(inflectedVerb);
				this.surface = Transliteration.toHebrew(this.inflectedVerb);
				this.populateDBTable();
			}

			// generate b+ inflectedInfinitive for all verbs
			if (this.inflectInfinitiveb) {
				if (this.inflectInfinitivel) {
					this.inflectedVerb = "b" + this.inflectedVerb.substring(1);
				} else {
					this.inflectedVerb = "b" + this.inflectedVerb;
				}
				// System.out.println(inflectedVerb);
				this.surface = Transliteration.toHebrew(this.inflectedVerb);
				this.populateDBTable();
			}
		}
	}

	public void generateBareInfinitive(final String prefix,
			final String inside, final int index1, final int index2,
			final int index3, final int index4, final String suffix) {
		// System.out.println("(F) VerbGenIP: generateBareInfinitive()");
		if (this.inflectBareInfinitive) {
			// Bare infinitive is like 'to infinitive' with out the 'to':
			// - by shuly definition לשמור -- שמור
			this.setAttributes(TenseType.BARE_INFINITIVE, "verb",
					"unspecified", "unspecified", GenderType.UNSPECIFIED,
					NumberType.UNSPECIFIED, TriStateType.UNSPECIFIED,
					"unspecified", SuffixFunctionType.UNSPECIFIED,
					"unspecified", "unspecified", "unspecified",
					DefinitenessType.FALSE);
			// System.out.println();
			// System.out.println("generate bare infinitive");
			StringBuffer inflected = null;

			inflected = new StringBuffer().append(prefix)
					.append(this.root.substring(index1, index2)).append(inside)
					.append(this.root.substring(index3, index4)).append(suffix);

			this.inflectedVerb = inflected.toString();
			this.surface = Transliteration.toHebrew(this.inflectedVerb);
			if (!this.replaceRemoveException(this.getReplaceRemoveExceptions())) {
				this.populateDBTable();
			}
			// used for inflectPattern58() for נמס - if we enter exception type
			// of tense bareInfinitive we will create duplicates
			else {
				this.bareInfinitiveException = true;
			}

		} else {
			final StringBuffer inflected = new StringBuffer().append(prefix)
					.append(this.root.substring(index1, index2)).append(inside)
					.append(this.root.substring(index3, index4)).append(suffix);

			this.inflectedVerb = inflected.toString();
			// System.out.println("(F) VerbGenIP: generateBareInfinitive() -- "+inflectedVerb);
			this.surface = Transliteration.toHebrew(this.inflectedVerb);
		}
	};

	/**
	 * This method generate the beinoni paul (only for binyan Paal)
	 * 
	 * @param passiveBase
	 *            - the base form on which the generation works
	 * @throws Exception
	 */
	@Override
	public void generateBeinoniPassive(final String passiveBase) {
		if (this.valence != Valence.INTRANSITIVE_WITHOUT_PAUL
				&& this.valence != Valence.TRANSITIVE_WITHOUT_PAUL) {

			StringTokenizer stSuff = null;
			StringTokenizer stNumber4 = null;
			StringTokenizer stGender4 = null;

			final String passivSuff = "-,h,im,wt";
			stSuff = new StringTokenizer(passivSuff, ",");
			stNumber4 = new StringTokenizer(numberTokens4, ",");
			stGender4 = new StringTokenizer(genderTokens4, ",");
			boolean constructFlag = false;

			while (stSuff.hasMoreTokens()) {
				this.setAttributes(TenseType.BEINONI, "passiveParticiple",
						"unspecified", "any", GenderType.UNSPECIFIED,
						NumberType.UNSPECIFIED, TriStateType.FALSE,
						"unspecified", SuffixFunctionType.UNSPECIFIED,
						"unspecified", "unspecified", "unspecified",
						DefinitenessType.FALSE);
				this.baseNumber = NumberType.fromValue(stNumber4.nextToken());
				this.baseGender = GenderType.fromValue(stGender4.nextToken());
				String suffix = stSuff.nextToken();
				if (suffix.equals("-")) {
					suffix = "";
				}
				final StringBuffer inflect = new StringBuffer().append(
						passiveBase).append(suffix);
				this.inflectedVerb = inflect.toString();
				this.surface = Transliteration.toHebrew(this.inflectedVerb);

				this.participleType = "adjective";

				this.populateDBTable();

				this.inflectedVerb = "h" + this.inflectedVerb;
				this.setAttributes(TenseType.BEINONI, "passiveParticiple",
						"unspecified", "any", this.baseGender, this.baseNumber,
						TriStateType.FALSE, "unspecified",
						SuffixFunctionType.UNSPECIFIED, "unspecified",
						"unspecified", "unspecified", DefinitenessType.TRUE);
				this.surface = Transliteration.toHebrew(this.inflectedVerb);

				this.participleType = "noun";
				this.populateDBTable();
				this.participleType = "adjective";
				this.populateDBTable();
				this.participleType = "verb";
				this.baseDefiniteness = DefinitenessType.UNSPECIFIED; // subcoordination
				this.populateDBTable();

				// System.out.println("h+ participle =" + inflectedVerb);
				this.setAttributes(TenseType.BEINONI, "passiveParticiple",
						"unspecified", this.basePerson, this.baseGender,
						this.baseNumber, TriStateType.FALSE, "unspecified",
						SuffixFunctionType.UNSPECIFIED, "unspecified",
						"unspecified", "unspecified", DefinitenessType.FALSE);
				this.inflectedVerb = inflect.toString();
				this.surface = Transliteration.toHebrew(this.inflectedVerb);

				if (!constructFlag) {
					// morphological changes
					String base = "";
					if (this.root.charAt(1) == 'w'
							|| this.root.charAt(1) == 'i') {
						base = passiveBase;
					} else if (this.root.charAt(2) == 'h'
							|| this.root.charAt(2) == 'i') {
						base = this.root.substring(0, 2) + "wi";
					} else if (this.root.charAt(2) == 'a'
							&& passiveBase.charAt(3) == 'a') {
						base = this.root.substring(0, 2) + "w"
								+ this.root.substring(2);
					} else if (this.root.charAt(2) == 'a'
							&& passiveBase.charAt(3) == 'i') {
						base = this.root.substring(0, 2) + "wi";
					} else {
						base = this.root.substring(0, 2) + "w"
								+ this.root.substring(2);
					}
					this.generateConstructAndPossessive(base, false);
					constructFlag = true;
				}
			}
		}
	}

	/**
	 * This method generates the construct form out of the 123/M/Sg form
	 * 
	 * @param base
	 *            - the 123/M/Sg form
	 * @param possessiveFlag
	 *            - for Nifal,Pual,Hufal - false - because we will not generete
	 *            the possessive form
	 * @throws Exception
	 */
	@Override
	public void generateConstructAndPossessive(String base,
			final boolean possessiveFlag) {
		if (this.inflectBeinoniConstruct) {
			String passivSuff = "";
			// System.out.println();
			// System.out.println("generateConstructAndPossessive");
			StringTokenizer stSuff = null;
			if (base.endsWith("wwh")) {
				passivSuff = "h,it,i,t";
			} else if (base.endsWith("h")) {
				passivSuff = "h,t,i,wt";
			} else {
				passivSuff = "-,t,i,wt";
			}
			this.stNumber4 = new StringTokenizer(numberTokens4, ",");
			this.stGender4 = new StringTokenizer(genderTokens4, ",");
			this.stPerson4 = new StringTokenizer(personTokens4, ",");
			// StringTokenizer stPGNTokensBeinoni = new StringTokenizer(
			// PGNTokensBeinoni, ",");
			stSuff = new StringTokenizer(passivSuff, ",");

			// Morphological changes:
			if (base.endsWith("h")) {
				base = base.substring(0, base.length() - 1);
			}
			while (this.stNumber4.hasMoreTokens()) {
				this.setAttributes(TenseType.BEINONI, this.pos, "unspecified",
						this.basePerson, this.baseGender, this.baseNumber,
						TriStateType.TRUE, "unspecified",
						SuffixFunctionType.UNSPECIFIED, "unspecified",
						"unspecified", "unspecified", DefinitenessType.FALSE);
				this.baseNumber = NumberType.fromValue(this.stNumber4
						.nextToken());
				this.baseGender = GenderType.fromValue(this.stGender4
						.nextToken());
				this.basePerson = this.stPerson4.nextToken();
				// possessive = stPGNTokensBeinoni.nextToken();
				String suff = stSuff.nextToken();
				if (suff.equals("-")) {
					suff = "";
				}

				final StringBuffer inflected = new StringBuffer().append(base)
						.append(suff);
				this.inflectedVerb = inflected.toString();
				// System.out.print("inflectedVerb =" + inflectedVerb);
				this.surface = Transliteration.toHebrew(this.inflectedVerb);
				// System.out.print("surface =" + surface);
				// System.out.println();

				boolean rtRemove;
				boolean rtReplace;
				rtRemove = rtReplace = this.replaceRemoveException(this
						.getReplaceRemoveExceptions());

				if (!rtRemove && !rtReplace) {
					// עבור צורות הנסמך תכונת היידוע לא רלבנטית
					this.baseDefiniteness = DefinitenessType.UNSPECIFIED;
					// רק שם עצם יכול להיות נסמך

					if (this.surface.equals("קטוע")
							|| this.surface.equals("נטול")) {
						this.participleType = "adjective";
					} else {
						this.participleType = "noun";
					}

					this.populateDBTable();
					this.baseDefiniteness = DefinitenessType.FALSE;
					// possessive flag is false for nifal, hufal and pual - the
					// possessive form will
					// not be created for these binyanim
					if (possessiveFlag && this.inflectBeinoniPossessive) {
						this.generatePossessive(this.inflectedVerb);
					}
					// exceptionInflectBeinoniPossessive still doesn't work well
					// - to do someday
				} else if (rtReplace && possessiveFlag
						&& this.exceptionInflectBeinoniPossessive) {
					this.generatePossessive(this.inflectedVerb);
				}
			}
		}
	}

	/**
	 * This method generates the future form
	 * 
	 * @param prefix
	 *            - concatenate to the begining of the root
	 * @param suffix
	 *            - concatenate to the end of the root
	 * @param inside
	 *            - concatenate to the middle of the root
	 * @param index1
	 *            - substringing the root from index1 to index2 for
	 *            concatenation of the inside part
	 * @param index2
	 *            - substringing the root from index1 to index2 for
	 *            concatenation of the inside part
	 * @param index3
	 *            - substringing the root from index3 to index4 for
	 *            concatenation of the inside part
	 * @param index4
	 *            - substringing the root from index3 to index4 for
	 *            concatenation of the inside part
	 * @throws Exception
	 */
	@Override
	public void generateFuture(final String prefix, final String suffix,
			final String inside, final int index1, final int index2,
			final int index3, final int index4) {
		if (this.inflectFuture) {
			StringTokenizer stIn = null;
			final StringTokenizer stPre = new StringTokenizer(prefix, ",");
			final StringTokenizer stSuff = new StringTokenizer(suffix, ",");
			final String genderTokens10 = "masculine and feminine,masculine,feminine,masculine,feminine,masculine and feminine,masculine and feminine,feminine,masculine and feminine,feminine";
			// final String PGNTokens10 =
			// "1p/MF/Sg,2p/M/Sg,2p/F/Sg,3p/M/Sg,3p/F/Sg,1p/MF/Pl,2p/MF/Pl,2p/F/Pl,3p/MF/Pl,3p/F/Pl";
			this.stPerson10 = new StringTokenizer(personTokens10, ",");
			this.stGender10 = new StringTokenizer(genderTokens10, ",");
			this.stNumber10 = new StringTokenizer(numberTokens10, ",");

			if (!inside.equals("")) {
				stIn = new StringTokenizer(inside, ",");
			}
			this.stPGN10 = new StringTokenizer(PGNTokens10, ",");

			String linside = "";
			String lpreffix = "";
			String lsuffix = "";

			// System.out.println();
			// System.out.println("generate future");

			this.setAttributes(TenseType.FUTURE, "verb", "unspecified", "any",
					GenderType.UNSPECIFIED, NumberType.UNSPECIFIED,
					TriStateType.FALSE, "unspecified",
					SuffixFunctionType.UNSPECIFIED, "unspecified",
					"unspecified", "unspecified", DefinitenessType.FALSE);
			while (stPre.hasMoreTokens()) {
				lpreffix = stPre.nextToken();
				if (lpreffix.equals("-")) {
					lpreffix = "";
				}
				lsuffix = stSuff.nextToken();
				if (lsuffix.equals("-")) {
					lsuffix = "";
				}
				if (stIn != null) {
					linside = stIn.nextToken();
					if (linside.equals("-")) {
						linside = "";
					}
				}

				final StringBuffer inflected = new StringBuffer()
						.append(lpreffix)
						.append(this.root.substring(index1, index2))
						.append(linside)
						.append(this.root.substring(index3, index4))
						.append(lsuffix);
				this.inflectedVerb = inflected.toString();
				// System.out.println("inflectedVerb =" + inflectedVerb);
				this.surface = Transliteration.toHebrew(this.inflectedVerb);
				// System.out.println("surface =" + surface);
				// System.out.println();

				this.possessive = "unspecified";
				this.basePerson = this.stPerson10.nextToken();
				this.baseNumber = NumberType.fromValue(this.stNumber10
						.nextToken());
				this.baseGender = GenderType.fromValue(this.stGender10
						.nextToken());
				if (!this.replaceRemoveException(this
						.getReplaceRemoveExceptions())) {
					this.populateDBTable();
				}
				// ייצור צורת עתיד יחיד או רבים גוף שלישי עם י אחת במקום שתיים
				if (this.binyan == BinyanType.NIF_AL) {
					if (this.baseGender == GenderType.MASCULINE
							&& this.baseNumber == NumberType.SINGULAR
							&& this.basePerson.equals("3")
							|| this.baseGender == GenderType.MASCULINE_AND_FEMININE
							&& this.baseNumber == NumberType.PLURAL
							&& this.basePerson.equals("3")) {
						if (!this.replaceRemoveException(this
								.getReplaceRemoveExceptions())) {
							this.inflectedVerb = this.inflectedVerb
									.substring(1);
							// inflectedVerb = inflected.toString();
							final SpellingType originalSpalling = this.spelling;
							this.spelling = SpellingType.IRREGULAR;
							this.surface = Transliteration
									.toHebrew(this.inflectedVerb);
							this.populateDBTable();
							this.spelling = originalSpalling;
						}
					}
				}
			}
		}
	}

	/**
	 * This method generates the imperative form
	 * 
	 * @param suffix
	 *            - concatenate to the end of the root
	 * @param inside
	 *            - concatenate to the middle of the root
	 * @param index1
	 *            - substringing the root from index1 to index2 for
	 *            concatenation of the inside part
	 * @param index2
	 *            - substringing the root from index1 to index2 for
	 *            concatenation of the inside part
	 * @param index3
	 *            - substringing the root from index3 to index4 for
	 *            concatenation of the inside part
	 * @param index4
	 *            - substringing the root from index3 to index4 for
	 *            concatenation of the inside part
	 * @throws Exception
	 */
	@Override
	public void generateImperative(final String prefix, final String suffix,
			final String inside, final int index1, final int index2,
			final int index3, final int index4) {
		if (this.inflectImperative) {
			StringTokenizer stIn = null;
			StringTokenizer stSuff = null;

			final String genderImperative = "masculine,feminine,masculine and feminine,feminine";

			this.stGender4 = new StringTokenizer(genderImperative, ",");
			this.stNumber4 = new StringTokenizer(numberTokens4, ",");
			// stPGNImperative = new StringTokenizer(PGNTokensImperative, ",");
			if (!inside.equals("")) {
				stIn = new StringTokenizer(inside, ",");
			}
			stSuff = new StringTokenizer(suffix, ",");

			String linside = "";
			String lsuffix = "";
			// System.out.println();
			// System.out.println("generate imperative");

			this.setAttributes(TenseType.IMPERATIVE, "verb", "unspecified",
					"2", GenderType.UNSPECIFIED, NumberType.UNSPECIFIED,
					TriStateType.FALSE, "unspecified",
					SuffixFunctionType.UNSPECIFIED, "unspecified",
					"unspecified", "unspecified", DefinitenessType.FALSE);
			while (stSuff.hasMoreTokens()) {
				if (stIn != null) {
					linside = stIn.nextToken();
					if (linside.equals("-")) {
						linside = "";
					}
				}
				lsuffix = stSuff.nextToken();
				if (lsuffix.equals("-")) {
					lsuffix = "";
				}

				final StringBuffer inflected = new StringBuffer()
						.append(prefix)
						.append(this.root.substring(index1, index2))
						.append(linside)
						.append(this.root.substring(index3, index4))
						.append(lsuffix);
				this.inflectedVerb = inflected.toString();
				// System.out.println("inflectedVerb =" + inflectedVerb);
				this.surface = Transliteration.toHebrew(this.inflectedVerb);
				// System.out.println("surface =" + surface);
				// System.out.println();

				// possessive = stPGNImperative.nextToken();
				this.baseNumber = NumberType
						.fromValue(this.stNumber4.nextToken());
				this.baseGender = GenderType
						.fromValue(this.stGender4.nextToken());
				this.basePerson = "2";

				if (!this.replaceRemoveException(this
						.getReplaceRemoveExceptions())) {
					this.populateDBTable();
				}
			}
		}
	}

	/**
	 * This method generate the infinitive form <br>
	 * It creates (1) infinitive + possessive forms <br>
	 * (2) l+ infinitive <br>
	 * The infinitive is created from the prefix + root(index1,index2) + inside
	 * + root(index3,index4) +suffix parts <br>
	 * The indexes define the substringing of the root
	 * 
	 * @param prefix
	 *            - concatenate to the begining of the root
	 * @param inside
	 *            - concatenate to the middle of the root
	 * @param index1
	 *            - substringing the root from index1 to index2 for
	 *            concatenation of the inside part
	 * @param index2
	 *            - substringing the root from index1 to index2 for
	 *            concatenation of the inside part
	 * @param index3
	 *            - substringing the root from index3 to index4 for
	 *            concatenation of the inside part
	 * @param index4
	 *            - substringing the root from index3 to index4 for
	 *            concatenation of the inside part
	 * @param suffix
	 *            - concatenate to the end of the root
	 * @throws Exception
	 */
	@Override
	public void generateInfinitive(final String prefix, final String inside,
			final int index1, final int index2, final int index3,
			final int index4, final String suffix) {
		// Bare infinitive is like 'to infinitive' with out the 'to':
		// - by shuly definition לשמור -- שמור
		// /////////////////bklm+non inflected infinitive//////////////

		if (this.inflectInfinitive) {
			// System.out.println("(F)generateInfinitive generate l+infinitive");
			this.setAttributes(TenseType.INFINITIVE, "verb", "unspecified",
					"unspecified", GenderType.UNSPECIFIED,
					NumberType.UNSPECIFIED, TriStateType.UNSPECIFIED,
					"unspecified", SuffixFunctionType.UNSPECIFIED,
					"unspecified", "unspecified", "unspecified",
					DefinitenessType.FALSE);

			StringBuffer inflected = new StringBuffer().append("l").append(
					this.inflectedVerb);

			this.inflectedVerb = inflected.toString();
			// System.out.println("(F)generateInfinitive inflectedVerb =" +
			// inflectedVerb);
			this.surface = Transliteration.toHebrew(this.inflectedVerb);
			// System.out.println("(F)generateInfinitive surface =" + surface);
			// System.out.println();

			if (!this.replaceRemoveException(this.getReplaceRemoveExceptions())) {
				this.populateDBTable();
			}

			// System.out.println("generate b+infinitive");
			this.setAttributes(TenseType.INFINITIVE, "verb", "unspecified",
					"unspecified", GenderType.UNSPECIFIED,
					NumberType.UNSPECIFIED, TriStateType.UNSPECIFIED,
					"unspecified", SuffixFunctionType.UNSPECIFIED,
					"unspecified", "unspecified", "unspecified",
					DefinitenessType.FALSE);

			inflected = new StringBuffer().append("b").append(
					this.inflectedVerb.substring(1));

			this.inflectedVerb = inflected.toString();
			// System.out.println("(F)generateInfinitive inflectedVerb =" +
			// inflectedVerb);
			this.surface = Transliteration.toHebrew(this.inflectedVerb);
			// System.out.println("(F)generateInfinitive surface =" + surface);
			// System.out.println();

			if (!this.replaceRemoveException(this.getReplaceRemoveExceptions())) {
				this.populateDBTable();
			}

			// System.out.println("(F)generateInfinitive generate m+infinitive");
			this.setAttributes(TenseType.INFINITIVE, "verb", "unspecified",
					"unspecified", GenderType.UNSPECIFIED,
					NumberType.UNSPECIFIED, TriStateType.UNSPECIFIED,
					"unspecified", SuffixFunctionType.UNSPECIFIED,
					"unspecified", "unspecified", "unspecified",
					DefinitenessType.FALSE);

			inflected = new StringBuffer().append("m").append(
					this.inflectedVerb.substring(1));

			this.inflectedVerb = inflected.toString();
			// System.out.println("(F)generateInfinitive inflectedVerb =" +
			// inflectedVerb);
			this.surface = Transliteration.toHebrew(this.inflectedVerb);
			// System.out.println("(F)generateInfinitive surface =" + surface);
			// System.out.println();

			if (!this.replaceRemoveException(this.getReplaceRemoveExceptions())) {
				this.populateDBTable();
			}

			// System.out.println("(F)generateInfinitive generate k+infinitive");
			this.setAttributes(TenseType.INFINITIVE, "verb", "unspecified",
					"unspecified", GenderType.UNSPECIFIED,
					NumberType.UNSPECIFIED, TriStateType.UNSPECIFIED,
					"unspecified", SuffixFunctionType.UNSPECIFIED,
					"unspecified", "unspecified", "unspecified",
					DefinitenessType.FALSE);

			inflected = new StringBuffer().append("k").append(
					this.inflectedVerb.substring(1));

			this.inflectedVerb = inflected.toString();
			// System.out.println("(F)generateInfinitive inflectedVerb =" +
			// inflectedVerb);
			this.surface = Transliteration.toHebrew(this.inflectedVerb);
			// System.out.println("(F)generateInfinitive surface =" + surface);
			// System.out.println();

			if (!this.replaceRemoveException(this.getReplaceRemoveExceptions())) {
				this.populateDBTable();
			}
		}

		// /////////////////////////////////////////////////////////////////////////////
		if (this.binyan.equals("Pa\\'al") && !this.replaceRemoveInfinitive) {
			// System.out.println("generate infinitive without prefix l - for bkm prefixes");
			final StringBuffer inflected = new StringBuffer().append(prefix)
					.append(this.root.substring(index1, index2)).append(inside)
					.append(this.root.substring(index3, index4)).append(suffix);

			this.inflectedVerb = inflected.toString();
		} else if (this.inflectInfinitive) {
			this.inflectedVerb = this.inflectedVerb.substring(1);
		}

		this.setAttributes(TenseType.INFINITIVE, "verb", "unspecified",
				"unspecified", GenderType.UNSPECIFIED, NumberType.UNSPECIFIED,
				TriStateType.UNSPECIFIED, "unspecified",
				SuffixFunctionType.POSSESSIVE, "unspecified", "unspecified",
				"unspecified", DefinitenessType.FALSE);
		this.generateAccusativeNominative(this.inflectedVerb);

		// ////////////////////////////////////////////////////////////
		// System.out.println();
	}

	/**
	 * This method generate the paste form <br>
	 * The past form is created from the prefix + root(index1,index2) + inside +
	 * root(index3,index4) +suffix parts <br>
	 * The indexes define the substringing of the root
	 * 
	 * @param prefix
	 *            - concatenate to the begining of the root
	 * @param suffix
	 *            - concatenate to the end of the root
	 * @param inside
	 *            - concatenate to the middle of the root
	 * @param index1
	 *            - substringing the root from index1 to index2 for
	 *            concatenation of the inside part
	 * @param index2
	 *            - substringing the root from index1 to index2 for
	 *            concatenation of the inside part
	 * @param index3
	 *            - substringing the root from index3 to index4 for
	 *            concatenation of the inside part
	 * @param index4
	 *            - substringing the root from index3 to index4 for
	 *            concatenation of the inside part
	 * @throws Exception
	 */
	@Override
	public void generatePast(final String prefix, final String suffix,
			final String inside, final int index1, final int index2,
			final int index3, final int index4) {
		if (this.inflectPast) {
			this.possessive = "unspecified";
			StringTokenizer stIn1 = null;
			StringTokenizer stPre = null;
			StringTokenizer stSuff = null;

			stSuff = new StringTokenizer(suffix, ",");
			final StringTokenizer stPerson9 = new StringTokenizer(
					personTokens9, ",");
			final StringTokenizer stGender9 = new StringTokenizer(
					genderTokens9, ",");
			final StringTokenizer stNumber9 = new StringTokenizer(
					numberTokens9, ",");

			if (!inside.equals("")) {
				stIn1 = new StringTokenizer(inside, ",");
			}
			if (!prefix.equals("")) {
				stPre = new StringTokenizer(prefix, ",");
			}

			String linside = "";
			String lprefix = "";
			String lsuffix = "";

			// System.out.println();
			// System.out.println("generate  past");

			while (stSuff.hasMoreTokens()) {
				lsuffix = stSuff.nextToken();
				if (lsuffix.equals("-")) {
					lsuffix = "";
				}

				if (stIn1 != null) {
					linside = stIn1.nextToken();
					if (linside.equals("-")) {
						linside = "";
					}
				}

				if (stPre != null) {
					lprefix = stPre.nextToken();
					if (lprefix.equals("-")) {
						lprefix = "";
					}
				}
				this.setAttributes(TenseType.PAST, "verb", "unspecified",
						"unspecified", GenderType.UNSPECIFIED,
						NumberType.UNSPECIFIED, TriStateType.UNSPECIFIED,
						"unspecified", SuffixFunctionType.UNSPECIFIED,
						"unspecified", "unspecified", "unspecified",
						DefinitenessType.FALSE);
				final StringBuffer inflected = new StringBuffer()
						.append(lprefix)
						.append(this.root.substring(index1, index2))
						.append(linside)
						.append(this.root.substring(index3, index4))
						.append(lsuffix);
				this.inflectedVerb = inflected.toString();
				// System.out.println("inflectedVerb =" + inflectedVerb);
				this.surface = Transliteration.toHebrew(this.inflectedVerb);
				// System.out.println("surface =" + surface);
				// System.out.println();
				this.basePerson = stPerson9.nextToken();
				this.baseNumber = NumberType.fromValue(stNumber9.nextToken());
				this.baseGender = GenderType.fromValue(stGender9.nextToken());

				if (!this.replaceRemoveException(this
						.getReplaceRemoveExceptions())) {
					this.populateDBTable();
				}
			}
		}
	}

	public void generatePastFromBaseForm(final String baseForm,
			final String suffix) {
		this.possessive = "unspecified";
		StringTokenizer stSuff = null;
		String lsuffix = "";

		stSuff = new StringTokenizer(suffix, ",");
		final StringTokenizer stPerson9 = new StringTokenizer(personTokens9,
				",");
		final StringTokenizer stGender9 = new StringTokenizer(genderTokens9,
				",");
		final StringTokenizer stNumber9 = new StringTokenizer(numberTokens9,
				",");

		while (stSuff.hasMoreTokens()) {
			lsuffix = stSuff.nextToken();
			if (lsuffix.equals("-")) {
				lsuffix = "";
			}

			this.setAttributes(TenseType.PAST, "verb", "unspecified",
					"unspecified", GenderType.UNSPECIFIED,
					NumberType.UNSPECIFIED, TriStateType.UNSPECIFIED,
					"unspecified", SuffixFunctionType.UNSPECIFIED,
					"unspecified", "unspecified", "unspecified",
					DefinitenessType.FALSE);
			final StringBuffer inflected = new StringBuffer().append(baseForm)
					.append(lsuffix);
			this.inflectedVerb = inflected.toString();
			this.surface = Transliteration.toHebrew(this.inflectedVerb);
			this.basePerson = stPerson9.nextToken();
			this.baseNumber = NumberType.fromValue(stNumber9.nextToken());
			this.baseGender = GenderType.fromValue(stGender9.nextToken());
			if (!this.replaceRemoveException(this.getReplaceRemoveExceptions())) {
				this.populateDBTable();
			}
		}
	}

	/**
	 * This method generate the possessive form out of the 123/M/Sg form
	 * 
	 * @param base
	 *            - the base form from which the possessive form will be created
	 * @throws Exception
	 */
	@Override
	public void generatePossessive(String base) {
		StringTokenizer stSuff = null;
		String suff;

		this.setAttributes(this.tense, this.pos, "unspecified",
				this.basePerson, this.baseGender, this.baseNumber,
				TriStateType.FALSE, "unspecified",
				SuffixFunctionType.POSSESSIVE, "unspecified", "unspecified",
				"unspecified", DefinitenessType.FALSE);
		this.stPGN10 = new StringTokenizer(PGNTokens10, ",");

		// morphological changes:
		if (base.endsWith("h")) {
			base = base.substring(0, base.length() - 1);
		}
		// System.out.println("baseNumber = " + baseNumber);
		// System.out.println("baseGender = " + baseGender);
		if (this.baseGender.equals("feminine")
				&& this.baseNumber.equals("plural")) {
			suff = suffixFPlural;
		} else if (this.baseGender.equals("masculine")
				&& this.baseNumber.equals("plural")) {
			suff = suffixMPlural;
		} else {
			suff = suffixSingular;
		}
		// System.out.println("suff=" + suff);
		stSuff = new StringTokenizer(suff, ",");
		String suffPossessive;
		while (this.stPGN10.hasMoreTokens()) {
			this.possessive = this.stPGN10.nextToken();
			suffPossessive = stSuff.nextToken();
			if (suffPossessive.equals("-")) {
				suffPossessive = "";
			}
			this.inflectedVerb = base + suffPossessive;
			if (this.inflectedVerb.endsWith("www")) {
				this.inflectedVerb = this.inflectedVerb.substring(0,
						this.inflectedVerb.length() - 1);
			}
			// System.out.println(inflectedVerb);
			this.surface = Transliteration.toHebrew(this.inflectedVerb);
			// System.out.print("surface =" + surface);
			// System.out.println();
			// רק שם עצם יכול להיות עם קניין

			this.participleType = "noun";
			this.populateDBTable();
			this.generatePossessiveColloquial(base);
		}
	}

	private void generatePossessiveColloquial(final String base) {
		// handling exceptions for feminine, plural
		this.participleType = "noun";
		final SpellingType currentSpelling = this.spelling;
		if (this.baseGender.equals("feminine")
				&& this.baseNumber.equals("plural")) {
			if (this.possessive.equals("1p/MF/Sg")) {
				this.inflectedVerb = base + "i";
				if (currentSpelling.equals("standard")) {
					this.spelling = SpellingType.IRREGULAR;
				}
				this.surface = Transliteration.toHebrew(this.inflectedVerb);
				this.populateDBTable();
			} else if (this.possessive.equals("2p/F/Sg")) {
				this.inflectedVerb = base + "ik";
				if (currentSpelling.equals("standard")) {
					this.spelling = SpellingType.IRREGULAR;
				}
				this.surface = Transliteration.toHebrew(this.inflectedVerb);
				this.populateDBTable();
			}
			// if (currentScript.equals("formal"))
			// script = "formal";
			// else if (currentScript.equals("slang"))
			// script = "slang";
			this.spelling = currentSpelling;

		}
		// handling exceptions for feminine, plural
		if (this.baseGender == GenderType.MASCULINE
				&& this.baseNumber == NumberType.PLURAL) {
			if (this.possessive.equals("1p/MF/Sg")) {
				this.inflectedVerb = base;
				if (currentSpelling == SpellingType.STANDARD) {
					this.spelling = SpellingType.IRREGULAR;
				}
				this.surface = Transliteration.toHebrew(this.inflectedVerb);
				this.populateDBTable();
			} else if (this.possessive.equals("2p/F/Sg")) {
				this.inflectedVerb = base + "k";
				if (currentSpelling == SpellingType.STANDARD) {
					this.spelling = SpellingType.IRREGULAR;
				}
				this.surface = Transliteration.toHebrew(this.inflectedVerb);
				this.populateDBTable();
			}
			this.spelling = currentSpelling;
			// if (currentScript.equals("formal"))
			// script = "formal";
			// else if (currentScript.equals("slang"))
			// script = "slang";
		}
	}

	/**
	 * This method generate the beinoni form <br>
	 * The construct from is generated from the 123/M/Sg form We create the
	 * possessive form from the construct form for all binyans except for <br>
	 * נפעל , פועל ו והופעל
	 * 
	 * @param prefix
	 *            - concatenate to the begining of the root
	 * @param suffix
	 *            - concatenate to the end of the root
	 * @param inside
	 *            - concatenate to the middle of the root
	 * @param index1
	 *            - substringing the root from index1 to index2 for
	 *            concatenation of the inside part
	 * @param index2
	 *            - substringing the root from index1 to index2 for
	 *            concatenation of the inside part
	 * @param index3
	 *            - substringing the root from index3 to index4 for
	 *            concatenation of the inside part
	 * @param index4
	 *            - substringing the root from index3 to index4 for
	 *            concatenation of the inside part
	 * @throws Exception
	 */

	@Override
	public void generatePresent(final String prefix, final String suffix,
			final String inside, final int index1, final int index2,
			final int index3, final int index4) {
		if (this.inflectBeinoni) {
			StringTokenizer stIn = null;
			StringTokenizer stPre = null;
			StringTokenizer stNumber4 = null;
			StringTokenizer stGender4 = null;
			StringTokenizer stPerson4 = null;
			StringTokenizer stSuff = null;

			stGender4 = new StringTokenizer(genderTokens4, ",");
			stNumber4 = new StringTokenizer(numberTokens4, ",");
			stPerson4 = new StringTokenizer(personTokens4, ",");
			stSuff = new StringTokenizer(suffix, ",");

			if (!inside.equals("")) {
				stIn = new StringTokenizer(inside, ",");
			}

			if (!prefix.equals("")) {
				stPre = new StringTokenizer(prefix, ",");
			}

			String linside = "";
			String lprefix = "";
			String lsuffix = "";

			// System.out.println();
			// System.out.println("generate participle");

			boolean constructFlag = false;

			while (stSuff.hasMoreTokens()) {
				lsuffix = stSuff.nextToken();
				if (lsuffix.equals("-")) {
					lsuffix = "";
				}

				if (stIn != null) {
					linside = stIn.nextToken();
					if (linside.equals("-")) {
						linside = "";
					}
				}
				if (stPre != null) {
					lprefix = stPre.nextToken();
					if (lprefix.equals("-")) {
						lprefix = "";
					}
				}

				this.setAttributes(TenseType.BEINONI, this.participleType,
						"unspecified", this.basePerson, this.baseGender,
						this.baseNumber, TriStateType.FALSE, "unspecified",
						SuffixFunctionType.UNSPECIFIED, "unspecified",
						"unspecified", "unspecified", DefinitenessType.FALSE);

				this.baseNumber = NumberType.fromValue(stNumber4.nextToken());
				this.baseGender = GenderType.fromValue(stGender4.nextToken());
				this.basePerson = stPerson4.nextToken();

				// I don't remember why the following commented lines exists
				// ????????????????????
				// if (lsuffix.equals("") && linside.equals("") &&
				// lprefix.equals("")) {
				// constructFlag = true;
				// continue;
				// }

				final StringBuffer inflected = new StringBuffer()
						.append(lprefix)
						.append(this.root.substring(index1, index2))
						.append(linside)
						.append(this.root.substring(index3, index4))
						.append(lsuffix);

				this.inflectedVerb = inflected.toString();
				// System.out.println("inflectedVerb =" + inflectedVerb);
				this.surface = Transliteration.toHebrew(this.inflectedVerb);
				// System.out.println("surface =" + surface);
				// System.out.println();

				// for comparing in the repalceRemove
				// String PGNNumner = "";
				// if (baseNumber.equals("singular"))
				// PGNNumner = "Sg";
				// else
				// PGNNumner = "Pl";
				// if (basePerson.equals("any"))
				// possessive = "123p/" + baseGender.substring(0,
				// 1).toUpperCase()
				// + "/" + PGNNumner;
				// else
				// possessive = basePerson + "/"
				// + baseGender.substring(0, 1).toUpperCase() + "/"
				// + PGNNumner;
				if (!this.replaceRemoveException(this
						.getReplaceRemoveExceptions())) {
					this.possessive = "unspecified";

					// - הכל יכול להיות - לא מיודע, לא קניין , לא נסמך
					this.participleType = "noun";
					this.populateDBTable();
					this.participleType = "verb";
					this.populateDBTable();
					this.participleType = "adjective";
					this.populateDBTable();

				}

				// generate h + beinoni - השומר
				this.inflectedVerb = "h" + this.inflectedVerb;
				this.setAttributes(TenseType.BEINONI, "participle",
						"unspecified", this.basePerson, this.baseGender,
						this.baseNumber, TriStateType.FALSE, "unspecified",
						SuffixFunctionType.UNSPECIFIED, "unspecified",
						"unspecified", "unspecified", DefinitenessType.TRUE);
				this.surface = Transliteration.toHebrew(this.inflectedVerb);

				// //////////////////////////

				this.participleType = "noun";
				this.populateDBTable();
				this.participleType = "adjective";
				this.populateDBTable();
				this.participleType = "verb";
				this.baseDefiniteness = DefinitenessType.UNSPECIFIED;
				this.populateDBTable();

				// /////////////////////////
				// System.out.println("h+ participle =" + inflectedVerb);
				this.setAttributes(TenseType.BEINONI, "participle",
						"unspecified", this.basePerson, this.baseGender,
						this.baseNumber, TriStateType.FALSE, "unspecified",
						SuffixFunctionType.UNSPECIFIED, "unspecified",
						"unspecified", "unspecified", DefinitenessType.FALSE);
				this.inflectedVerb = this.inflectedVerb.substring(1);
				this.surface = Transliteration.toHebrew(this.inflectedVerb);

				// The construct form is created from the 123/M/Sg form

				// the generation of the possessive form is determaind by two
				// values:
				// 1) possessive flag - determined according to the binyan - we
				// will not generate possessive form for binyan nifal, hufal and
				// pual
				// 2) the beinoniPossessive form set by the user in the lexicon
				if (!constructFlag) {
					if (this.intInflectedPattern >= 1
							&& this.intInflectedPattern < 36
							|| this.intInflectedPattern >= 42
							&& this.intInflectedPattern <= 53) {
						this.generateConstructAndPossessive(this.inflectedVerb,
								true);
					} else {
						this.generateConstructAndPossessive(this.inflectedVerb,
								false);
					}
					constructFlag = true;
					this.possessive = "unspecified";
				}
			}
		}
	}

	/**
	 * @return Returns the dottedLexiconItem.
	 */
	public String getDottedLexiconItem() {
		return this.dottedLexiconItem;
	}

	/**
	 * @return Returns the hebRoot.
	 */
	public String getHebRoot() {
		return this.hebRoot;
	}

	/**
	 * @return Returns the inflectedVerb.
	 */
	public String getInflectedVerb() {
		return this.inflectedVerb;
	}

	@SuppressWarnings("unchecked")
	public List<VerbException> getReplaceRemoveExceptions() {
		return filter(Matchers.anyOf(
				Matchers.instanceOf(VerbExceptionReplace.class),
				Matchers.instanceOf(VerbExceptionRemove.class)),
				verb.getExceptions());
	}

	/**
	 * @return Returns the valence.
	 */
	public Valence getValence() {
		return this.valence;
	}

	/**
	 * @return Returns the inflectBareInfinitive.
	 */
	public boolean isInflectBareInfinitive() {
		return this.inflectBareInfinitive;
	}

	/**
	 * @return Returns the inflectBeinoni.
	 */
	public boolean isInflectBeinoni() {
		return this.inflectBeinoni;
	}

	/**
	 * @return Returns the inflectBeinoniConstruct.
	 */
	public boolean isInflectBeinoniConstruct() {
		return this.inflectBeinoniConstruct;
	}

	/**
	 * @return Returns the inflectBeinoniPossessive.
	 */
	public boolean isInflectBeinoniPossessive() {
		return this.inflectBeinoniPossessive;
	}

	/**
	 * @return Returns the inflectFuture.
	 */
	public boolean isInflectFuture() {
		return this.inflectFuture;
	}

	/**
	 * @return Returns the inflectImperative.
	 */
	public boolean isInflectImperative() {
		return this.inflectImperative;
	}

	/**
	 * @return Returns the inflectInfinitive.
	 */
	public boolean isInflectInfinitive() {
		return this.inflectInfinitive;
	}

	/**
	 * @return Returns the inflectInfintive.
	 */
	public boolean isInflectInfinitiveb() {
		return this.inflectInfinitiveb;
	}

	/**
	 * @return Returns the independent.
	 */
	public boolean isInflectInfinitiveIndependent() {
		return this.inflectInfinitiveIndependent;
	}

	/**
	 * @return Returns the inflectInfintive.
	 */
	public boolean isInflectInfinitivel() {
		return this.inflectInfinitivel;
	}

	/**
	 * @return Returns the inflectPast.
	 */
	public boolean isInflectPast() {
		return this.inflectPast;
	}

	/**
	 * This methos handles filling the inflection record with values to be
	 * inserted to the database
	 * 
	 * @throws Exception
	 */
	private void populateDBTable() {
		final Inflection inf = new Inflection();
		inf.setRegister(this.register);
		inf.setSpelling(this.spelling);
		inf.setBasePos(this.pos);
		inf.setBaseUndottedLItem(this.baseUndot);
		inf.setBaseTransliteratedLItem(this.baseTransliterated);
		inf.setBaseUndottedLItem(this.baseUndot);
		inf.setBaseLexiconPointer(this.baseLexiconPointer);
		inf.setBinyan(this.binyan);
		inf.setRoot(this.hebRoot);
		inf.setTense(this.tense);
		inf.setPGN(this.possessive);
		inf.setSuffixStatus(this.construct);
		inf.setBaseGender(this.baseGender);
		inf.setBaseNumber(this.baseNumber);
		inf.setBasePerson(this.basePerson);
		inf.setBaseDefiniteness(this.baseDefiniteness);
		inf.setSurface(this.surface);
		inf.setTransliterated(this.inflectedVerb);
		inf.setSuffixFunction(this.suffixFunction);
		inf.setDottedLexiconItem(this.dottedLexiconItem);
		inf.setType(this.participleType);
		inf.setPolarity(PolarityType.UNSPECIFIED);
		inf.setValue("");
		generatedInflections.add(inf);
	}

	/**
	 * This method is used for handling remove and replace exceptions <br>
	 * It checks whether there is a matching value for the current generated
	 * form <br>
	 * The decision is taken by testing the possessive,tense and accusation
	 * values <br>
	 * 
	 * @param possessive
	 *            - person/gender/number value generated by the generation
	 *            program
	 * @param tense
	 *            - the tense of the generated form
	 * @param accusativeSuffix
	 *            - currently we are not handling accusative values
	 * @param exceptionList
	 *            - remove/replace exception list for the current lexicon item
	 * @param action
	 *            - remove/replace
	 * @return - boolean value: true - replacement/removement has been done
	 * @throws Exception
	 */
	public boolean replaceRemoveException(final List<VerbException> exceptions) {
		boolean match = false;
		for (final VerbException excp : exceptions) {
			String ePerson = excp.getPerson();
			final TenseType eTense = TenseType.fromValue(excp.getTense()
					.value());
			final GenderType eGender = GenderType.fromValue(excp.getGender()
					.value());
			final NumberType eNumber = NumberType.fromValue(excp.getNumber()
					.value());
			final String ePossesive = excp.getPossessive();
			if (excp.getPerson().equals("3") && this.tense == TenseType.BEINONI) {
				ePerson = "any";
			}
			this.exceptionInflectBeinoniPossessive = excp
					.isInflectBeinoniPossessive();
			if (!(eTense == this.tense && eGender == this.baseGender
					&& eNumber == this.baseNumber
					&& ePerson.equals(this.basePerson) && ePossesive
						.equals(this.possessive))) {
				/*
				 * the exception doesn't match the current situation - do
				 * nothing
				 */
				continue;
			}

			ExcpType eType = null;
			if (excp instanceof VerbExceptionAdd) {
				eType = ExcpType.ADD;
			}
			if (excp instanceof VerbExceptionRemove) {
				eType = ExcpType.REMOVE;
			}
			if (excp instanceof VerbExceptionReplace) {
				eType = ExcpType.REPLACE;
			}

			switch (eType) {
			case ADD:
				this.addException(excp);
				break;
			case REMOVE:
				break;
			case REPLACE:
				final String oldInflected = this.inflectedVerb;
				this.inflectedVerb = excp.getTransliterated();
				this.surface = Transliteration.toHebrew(this.inflectedVerb);
				switch (eTense) {
				case INFINITIVE:
					if (this.inflectedVerb.charAt(0) == oldInflected.charAt(0)) {
						this.populateDBTable();
						match = true;
					}
					/*
					 * XXX: Original code reset inflectedVerb and surface in the
					 * else
					 */
					break;
				case BEINONI:
					if (ePossesive.equals("unspecified")
							&& !excp.isBeinoniConstruct()
							&& this.baseDefiniteness == DefinitenessType.FALSE) {
						this.participleType = "noun";
						this.populateDBTable();
						this.participleType = "adjective";
						this.populateDBTable();
						this.participleType = "verb";
						this.populateDBTable();
					} else if (!ePossesive.equals("unspecified")
							|| excp.isBeinoniConstruct()) {
						this.participleType = "noun";
						this.populateDBTable();
					} else if (this.baseDefiniteness == DefinitenessType.TRUE) {
						this.participleType = "noun";
						this.populateDBTable();
						this.participleType = "adjective";
						this.populateDBTable();
						this.participleType = "verb";
						this.baseDefiniteness = DefinitenessType.UNSPECIFIED;
						this.populateDBTable();
					}
					break;
				/*
				 * XXX: Commented this out, dunno what a "PASSIVE_PARTICIPLE"
				 * is. Do we have those?
				 */
				// case PASSIVE_PARTICIPLE:
				// if (ePossesive.equals("unspecified") && !beinoniConstruct
				// && baseDefiniteness == DefinitenessType.FALSE) {
				// participleType = "adjective";
				// populateDBTable();
				// participleType = "verb";
				// populateDBTable();
				// } else if (beinoniConstruct) {
				// participleType = "noun";
				// populateDBTable();
				// } else if (baseDefiniteness == DefinitenessType.TRUE) {
				// participleType = "noun";
				// populateDBTable();
				// participleType = "adjective";
				// populateDBTable();
				// participleType = "verb";
				// baseDefiniteness = DefinitenessType.UNSPECIFIED;
				// populateDBTable();
				// }
				default:
					this.populateDBTable();
					match = true;
					break;
				}
				break;
			}
		}

		return match;
	}

	private void setAttributes(final TenseType tense, final String pos,
			final String PGN, final String basePerson,
			final GenderType baseGender, final NumberType baseNumber,
			final TriStateType construct, final String accusativeSuffix,
			final SuffixFunctionType suffixFunction, final String suffixNumber,
			final String suffixGender, final String suffixPerson,
			final DefinitenessType baseDefiniteness) {
		this.tense = tense;
		this.possessive = PGN;
		this.pos = pos;
		this.construct = construct;
		this.basePerson = basePerson;
		this.baseGender = baseGender;
		this.baseNumber = baseNumber;
		this.suffixFunction = suffixFunction;
		this.baseDefiniteness = baseDefiniteness;
		this.participleType = "unspecified";
	}

	/**
	 * @param baseLexiconPointer
	 *            The baseLexiconPointer to set.
	 */
	public void setBaseLexiconPointer(final String baseLexiconPointer) {
		this.baseLexiconPointer = baseLexiconPointer;
	}

	/**
	 * @param baseTransliterated
	 *            The baseTransliterated to set.
	 */
	public void setBaseTransliterated(final String baseTransliterated) {
		this.baseTransliterated = baseTransliterated;
	}

	/**
	 * @param baseUndot
	 *            The baseUndot to set.
	 */
	public void setBaseUndot(final String baseUndot) {
		this.baseUndot = baseUndot;
	}

	/**
	 * @param binyan
	 *            The binyan to set.
	 */
	public void setBinyan(final BinyanType binyan) {
		this.binyan = binyan;
	}

	/**
	 * @param dottedLexiconItem
	 *            The dottedLexiconItem to set.
	 */
	public void setDottedLexiconItem(final String dottedLexiconItem) {
		this.dottedLexiconItem = dottedLexiconItem;
	}

	/**
	 * @param hebRoot
	 *            The hebRoot to set.
	 */
	public void setHebRoot(final String hebRoot) {
		this.hebRoot = hebRoot;
	}

	/**
	 * @param inflectBareInfinitive
	 *            The inflectBareInfinitive to set.
	 */
	public void setInflectBareInfinitive(final boolean inflectBareInfinitive) {
		this.inflectBareInfinitive = inflectBareInfinitive;
	}

	/**
	 * @param inflectBeinoni
	 *            The inflectBeinoni to set.
	 */
	public void setInflectBeinoni(final boolean inflectBeinoni) {
		this.inflectBeinoni = inflectBeinoni;
	}

	/**
	 * @param inflectBeinoniConstruct
	 *            The inflectBeinoniConstruct to set.
	 */
	public void setInflectBeinoniConstruct(final boolean inflectBeinoniConstruct) {
		this.inflectBeinoniConstruct = inflectBeinoniConstruct;
	}

	/**
	 * @param inflectBeinoniPossessive
	 *            The inflectBeinoniPossessive to set.
	 */
	public void setInflectBeinoniPossessive(
			final boolean inflectBeinoniPossessive) {
		this.inflectBeinoniPossessive = inflectBeinoniPossessive;
	}

	/**
	 * @param inflectedVerb
	 *            The inflectedVerb to set.
	 */
	public void setInflectedVerb(final String inflectedVerb) {
		this.inflectedVerb = inflectedVerb;
	}

	/**
	 * @param inflectFuture
	 *            The inflectFuture to set.
	 */
	public void setInflectFuture(final boolean inflectFuture) {
		this.inflectFuture = inflectFuture;
	}

	/**
	 * @param inflectImperative
	 *            The inflectImperative to set.
	 */
	public void setInflectImperative(final boolean inflectImperative) {
		this.inflectImperative = inflectImperative;
	}

	/**
	 * @param inflectInfinitive
	 *            The inflectInfinitive to set.
	 */
	public void setInflectInfinitive(final boolean inflectInfinitive) {
		this.inflectInfinitive = inflectInfinitive;
	}

	/**
	 * @param inflectInfintive
	 *            The inflectInfintive to set.
	 */
	public void setInflectInfinitiveb(final boolean inflectInfintiveb) {
		this.inflectInfinitiveb = inflectInfintiveb;
	}

	/**
	 * @param independent
	 *            The independent to set.
	 */
	public void setInflectInfinitiveIndependent(
			final boolean inflectInfinitiveIndependent) {
		this.inflectInfinitiveIndependent = inflectInfinitiveIndependent;
	}

	/**
	 * @param inflectInfintive
	 *            The inflectInfintive to set.
	 */
	public void setInflectInfinitivel(final boolean inflectInfintivel) {
		this.inflectInfinitivel = inflectInfintivel;
	}

	/**
	 * @param inflectPast
	 *            The inflectPast to set.
	 */
	public void setInflectPast(final boolean inflectPast) {
		this.inflectPast = inflectPast;
	}

	/**
	 * @param inflectedPattern
	 *            The inflectedPattern to set.
	 */
	public void setIntInflectedPattern(final int intInflectedPattern) {
		this.intInflectedPattern = intInflectedPattern;
	}

	/**
	 *  
	 */
	public void setRegister(final RegisterType register2) {
		this.register = register2;
	}

	/**
	 * @param replaceRemoveInfinitive
	 *            The replaceRemoveInfinitive to set.
	 */
	public void setReplaceRemoveInfinitive(final boolean replaceRemoveInfinitive) {
		this.replaceRemoveInfinitive = replaceRemoveInfinitive;
	}

	/**
	 * @param root
	 *            The root to set.
	 */
	public void setRoot(final String root) {
		this.root = root;
	}

	/**
	 *  
	 */
	public void setSpelling(final SpellingType spelling2) {
		this.spelling = spelling2;
	}

	public void setTable(final String _table) {
		this.table = _table;
	}

	/**
	 * @param valence2
	 *            The valence to set.
	 */
	public void setValence(final Valence valence2) {
		this.valence = valence2;
	}
}
