/*
 * Created on 28/11/2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package lexicon.dbUtils;

import java.io.BufferedOutputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.sql.ResultSet;
import java.sql.SQLException;

import lexicon.contents.Connected;
import lexicon.contents.ConnectedGenerator;

import lexicon.stringUtils.Translate;

/**
 * @author daliabo
 * 
 *         TODO To change the template for this generated type comment go to
 *         Window - Preferences - Java - Code Style - Code Templates
 */
public class Download2File extends ConnectedGenerator {

	private static boolean pc = false;

	private static String dir = "";

	static FileOutputStream out = null;

	static PrintStream pOut = null;

	// //////////////////////////////////////////////
	static final String POS_QUANTIFIER = "1";

	static final String POS_INTERJECTION = "2";

	static final String POS_INTERROGATIVE = "3";

	static final String POS_CONJUNCTION = "4";

	static final String POS_ADVERB = "5";

	static final String POS_PREPOSITION = "6";

	static final String POS_NOUN = "7";

	static final String POS_ADJECTIVE = "8";

	static final String POS_PRONOUN = "9";

	static final String POS_PROPERNAME = "10";

	static final String POS_VERB = "11";

	static final String POS_NEGATION = "12";

	static final String POS_PARTICIPLE = "13";

	static final String POS_NUMERAL = "14";

	static final String POS_EXISTENTIAL = "15";

	static final String POS_IMPERSONAL = "16";

	static final String POS_MODAL = "17";

	static final String POS_WPREFIX = "18";

	static final String POS_PREFIXES = "19";

	static final String POS_PASSIVEPARTICIPLE = "20";

	static final String POS_INDEPENDENTINFINITIVE = "21";

	static final String POS_COPULA = "22";

	static final String POS_TITLE = "23";

	static final String POS_MULTIWORD = "24";

	static final String POS_PUNCTUATION = "25";

	static final String POS_URL = "26";

	static final String POS_FOREIGN = "27";

	static final String POS_UNKNOWN = "28";

	static final String POS_NUMBER_EXPRESSION = "29";

	static final String POS_ZEVEL = "30";

	static final String UNSPECIFIED = "31";

	// /////////////////////////////////////////////////////

	static final String CONSTRUCT_TRUE = "1";

	static final String CONSTRUCT_FALSE = "2";

	static final String CONSTRUCT_UNDEFINED = "3";

	// ///////////////////////////////////////////////////
	static final String PARTICIPLE_TYPE_NOUN = "1";

	static final String PARTICIPLE_TYPE_ADJECTIVE = "2";

	static final String PARTICIPLE_TYPE_VERB = "3";

	static final String PARTICIPLE_TYPE_UNSPECIFIED = "4";

	// //////////////////////////////////////////////////
	static final String CONJUNCTION_TYPE_COORDINATING = "1";

	static final String CONJUNCTION_TYPE_SUBORDINATING = "2";

	static final String CONJUNCTION_TYPE_RELATIVIZING = "3";

	// ///////////////////////////////////////////////////
	static final String INTERROGATIVE_TYPE_PRONOUN = "1";

	static final String INTERROGATIVE_TYPE_PROADVERB = "2";

	static final String INTERROGATIVE_TYPE_PRODET = "3";

	static final String INTERROGATIVE_TYPE_YESNO = "4";

	static final String INTERROGATIVE_TYPE_UNSPECIFIED = "5";

	// //////////////////////////////////////////////////

	static final String QUANTIFIER_TYPE_AMOUNT = "1";
	static final String QUANTIFIER_TYPE_PARTITIVE = "2";
	static final String QUANTIFIER_TYPE_DETERMINER = "3";

	// /////////////////////////////////////////////////////

	// --------------------------------------------------------------------------------------
	public void WriteMWE_2File(int num) throws SQLException,
			FileNotFoundException {
		ResultSet rs = null;
		StringBuffer mwe = null;

		out = new FileOutputStream(dir + "mwe" + num + ".data");
		pOut = new PrintStream(out);

		String sql = "select * from mwe" + num;
		rs = getData(sql);
		if (rs != null) {
			while (rs.next()) {
				String aid, id, formerItemId, surface, transliterated, consecutive, lexiconId;
				String transliteratedLexiconItem, dottedLexiconItem, undottedLexiconItem, mwTransliterated;
				String mwUndotted, PGN, spelling, register, gender, number, definiteness;

				aid = rs.getString("aid");
				id = rs.getString("id");
				formerItemId = rs.getString("formerItemId");
				surface = rs.getString("surface");
				transliterated = rs.getString("transliterated");
				consecutive = rs.getString("consecutive");
				lexiconId = rs.getString("lexiconId");
				transliteratedLexiconItem = rs
						.getString("transliteratedLexiconItem");
				dottedLexiconItem = rs.getString("dottedLexiconItem");
				undottedLexiconItem = rs.getString("undottedLexiconItem");
				mwTransliterated = rs.getString("mwTransliterated");
				mwUndotted = rs.getString("mwUndotted");
				PGN = rs.getString("PGN");
				spelling = rs.getString("spelling");
				register = rs.getString("register");
				gender = rs.getString("gender");
				number = rs.getString("number");
				definiteness = rs.getString("definiteness");

				mwe = new StringBuffer();
				mwe.append(transliterated);
				mwe.append("|");
				mwe.append(aid);
				mwe.append("|");
				mwe.append(id);
				mwe.append("|");
				mwe.append(formerItemId);
				mwe.append("|");
				mwe.append(surface);
				mwe.append("|");
				mwe.append(consecutive);
				mwe.append("|");
				mwe.append(lexiconId);
				mwe.append("|");
				mwe.append(transliteratedLexiconItem);
				mwe.append("|");
				mwe.append(dottedLexiconItem);
				mwe.append("|");
				mwe.append(undottedLexiconItem);
				mwe.append("|");
				mwe.append(mwTransliterated);
				mwe.append("|");
				mwe.append(mwUndotted);
				mwe.append("|");
				mwe.append(PGN);
				mwe.append("|");
				mwe.append(spelling);
				mwe.append("|");
				mwe.append(register);
				mwe.append("|");
				mwe.append(gender);
				mwe.append("|");
				mwe.append(number);
				mwe.append("|");
				mwe.append(definiteness);

				pOut.println(mwe);

			}
			rs.close();
			pOut.close();
			System.out.println("end mwe1 download to file");
		}
	}

	// --------------------------------------------------------------------------------------
	public void WriteMWE1_2File() throws SQLException, FileNotFoundException {
		ResultSet rs = null;
		StringBuffer mwe1 = null;

		out = new FileOutputStream(dir + "mwe1.data");
		pOut = new PrintStream(out);

		String sql = "select * from mwe1";
		rs = getData(sql);
		if (rs != null) {
			while (rs.next()) {
				String id, transliterated, surface, pos, consecutive, type;

				id = rs.getString("id");
				transliterated = rs.getString("transliterated");
				surface = rs.getString("surface");
				consecutive = rs.getString("consecutive");
				type = rs.getString("type");
				pos = rs.getString("pos");

				mwe1 = new StringBuffer();
				mwe1.append(transliterated);
				mwe1.append("|");
				mwe1.append(id);
				mwe1.append("|");
				mwe1.append(surface);
				mwe1.append("|");
				mwe1.append(pos);
				mwe1.append("|");
				mwe1.append(consecutive);
				mwe1.append("|");
				mwe1.append(type);

				pOut.println(mwe1);

				System.out.println(transliterated);
			}
			rs.close();
			pOut.close();
			System.out.println("end mwe1 download to file");
		}
	}

	// ------------------------------------------------------------------------------------------------------
	/**
	 * @author yossi jacob this function takes the mwinflections table and
	 *         output it into a file
	 */
	public void WriteMWinflections2File() {
		String transliterated = "";
		String surface = "";
		String pos = "";
		String mweId = "";
		String type = "";
		String prefix = "";

		String sql = "select * from mweinflections";

		StringBuffer mweinflectionRec = null;
		ResultSet rs = null;
		try {
			out = new FileOutputStream(dir + "mwinflections.data");
			pOut = new PrintStream(out);
			rs = getData(sql);
			if (rs != null) {
				while (rs.next()) {
					transliterated = rs.getString("transliterated");
					surface = rs.getString("surface");
					pos = rs.getString("pos");
					mweId = rs.getString("mweId");
					type = rs.getString("type");
					prefix = rs.getString("prefix");

					mweinflectionRec = new StringBuffer();
					mweinflectionRec.append(transliterated);
					mweinflectionRec.append("|");
					mweinflectionRec.append(surface);
					mweinflectionRec.append("|");
					mweinflectionRec.append(pos);
					mweinflectionRec.append("|");
					mweinflectionRec.append(mweId);
					mweinflectionRec.append("|");
					mweinflectionRec.append(type);
					mweinflectionRec.append("|");
					mweinflectionRec.append(prefix);
					pOut.println(mweinflectionRec);
				}
				rs.close();
				pOut.close();
				System.out.println("end mwinflections download to file");
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			releaseConnection();
			System.exit(1);
		} catch (SQLException e) {
			e.printStackTrace();
			releaseConnection();
			System.exit(1);
		} finally {
			releaseConnection();
		}
	}

	// ------------------------------------------------------------------------------------------------------
	public void writeGimatria2File() {

		String transliterated = "";
		String val = "";
		String sql = "select * from gimatria";
		StringBuffer gimatriaRec = null;
		ResultSet rs = null;
		try {
			out = new FileOutputStream(dir + "gimatria.data");
			pOut = new PrintStream(out);
			rs = getData(sql);
			if (rs != null) {
				while (rs.next()) {
					transliterated = rs.getString("transliterated");
					val = rs.getString("val");
					gimatriaRec = new StringBuffer();
					gimatriaRec.append(transliterated);
					gimatriaRec.append("|");
					gimatriaRec.append(val);
					pOut.println(gimatriaRec);
				}
				rs.close();
				pOut.close();
				System.out.println("end gimatria download to file");
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			releaseConnection();
			System.exit(1);
		} catch (SQLException e) {
			e.printStackTrace();
			releaseConnection();
			System.exit(1);
		} finally {
			releaseConnection();
		}
	}

	public void writePrefixes2File() {
		String prefix = "";
		String description = "";
		boolean bdefiniteArticleTag;
		boolean bdefArtHE;
		boolean brelHE;
		boolean badverbKAF;
		boolean bsubConOrRelSHIN;
		boolean btempSubConKAFSHIN;
		boolean btempSubConMEMSHIN;
		boolean btempSubConLAMEDKAFSHIN;
		boolean btempSubConBETSHIN;
		boolean brelativizerTag;
		boolean btemporalSubConjTag;
		boolean bsubordinatingConjunctionTag;
		boolean bprefPartUnit;
		boolean bprepBET;
		boolean bprepKAF;
		boolean bprepLAMED;
		boolean bprepMEM;
		boolean bprepositionTag;
		boolean bconjunctionTag;

		String definiteArticleTag;
		String defArtHE;
		String relHE;
		String adverbKAF;
		String subConOrRelSHIN;
		String tempSubConKAFSHIN;
		String tempSubConMEMSHIN;
		String tempSubConLAMEDKAFSHIN;
		String tempSubConBETSHIN;
		String relativizerTag;
		String temporalSubConjTag;
		String subordinatingConjunctionTag;
		String prefPartUnit;
		String prepBET;
		String prepKAF;
		String prepLAMED;
		String prepMEM;
		String prepositionTag;
		String conjunctionTag;

		StringBuffer decodedPrefixRecord = null;
		BufferedOutputStream bf;
		String sql = "select * from prefixes";
		ResultSet rs = null;
		if (pc)
			dir = "C:\\Documents and Settings\\daliabo\\My Documents\\lexicon\\diffTests\\";
		else
			dir = "";
		try {
			out = new FileOutputStream(dir + "prefixes.data");
			pOut = new PrintStream(out);
			rs = getData(sql);
			if (rs != null) {
				while (rs.next()) {
					prefix = rs.getString("prefix");
					description = rs.getString("description");
					bdefiniteArticleTag = rs.getBoolean("definiteArticleTag");
					if (!bdefiniteArticleTag)
						definiteArticleTag = "-";
					else
						definiteArticleTag = "+";
					bdefArtHE = rs.getBoolean("defArtHE");
					if (!bdefArtHE)
						defArtHE = "-";
					else
						defArtHE = "+";
					brelHE = rs.getBoolean("relHE");
					if (!brelHE)
						relHE = "-";
					else
						relHE = "+";
					badverbKAF = rs.getBoolean("adverbKAF");
					if (!badverbKAF)
						adverbKAF = "-";
					else
						adverbKAF = "+";
					bsubConOrRelSHIN = rs.getBoolean("subConOrRelSHIN");
					if (!bsubConOrRelSHIN)
						subConOrRelSHIN = "-";
					else
						subConOrRelSHIN = "+";
					btempSubConKAFSHIN = rs.getBoolean("tempSubConKAFSHIN");
					if (!btempSubConKAFSHIN)
						tempSubConKAFSHIN = "-";
					else
						tempSubConKAFSHIN = "+";
					btempSubConMEMSHIN = rs.getBoolean("tempSubConMEMSHIN");
					if (!btempSubConMEMSHIN)
						tempSubConMEMSHIN = "-";
					else
						tempSubConMEMSHIN = "+";
					btempSubConBETSHIN = rs.getBoolean("tempSubConBETSHIN");
					if (!btempSubConBETSHIN)
						tempSubConBETSHIN = "-";
					else
						tempSubConBETSHIN = "+";
					btempSubConLAMEDKAFSHIN = rs
							.getBoolean("tempSubConLAMEDKAFSHIN");
					if (!btempSubConLAMEDKAFSHIN)
						tempSubConLAMEDKAFSHIN = "-";
					else
						tempSubConLAMEDKAFSHIN = "+";
					brelativizerTag = rs.getBoolean("relativizerTag");
					if (!brelativizerTag)
						relativizerTag = "-";
					else
						relativizerTag = "+";
					btemporalSubConjTag = rs.getBoolean("temporalSubConjTag");
					if (!btemporalSubConjTag)
						temporalSubConjTag = "-";
					else
						temporalSubConjTag = "+";
					bsubordinatingConjunctionTag = rs
							.getBoolean("subordinatingConjunctionTag");
					if (!bsubordinatingConjunctionTag)
						subordinatingConjunctionTag = "-";
					else
						subordinatingConjunctionTag = "+";
					bprefPartUnit = rs.getBoolean("prefPartUnit");
					if (!bprefPartUnit)
						prefPartUnit = "-";
					else
						prefPartUnit = "+";
					bprepBET = rs.getBoolean("prepBET");
					if (!bprepBET)
						prepBET = "-";
					else
						prepBET = "+";
					bprepKAF = rs.getBoolean("prepKAF");
					if (!bprepKAF)
						prepKAF = "-";
					else
						prepKAF = "+";
					bprepLAMED = rs.getBoolean("prepLAMED");
					if (!bprepLAMED)
						prepLAMED = "-";
					else
						prepLAMED = "+";
					bprepMEM = rs.getBoolean("prepMEM");
					if (!bprepMEM)
						prepMEM = "-";
					else
						prepMEM = "+";
					bprepositionTag = rs.getBoolean("prepositionTag");
					if (!bprepositionTag)
						prepositionTag = "-";
					else
						prepositionTag = "+";
					bconjunctionTag = rs.getBoolean("conjunctionTag");
					if (!bconjunctionTag)
						conjunctionTag = "-";
					else
						conjunctionTag = "+";
					decodedPrefixRecord = new StringBuffer().append(prefix)
							.append("|").append(description).append("|")
							.append(definiteArticleTag).append("|")
							.append(defArtHE).append("|").append(relHE)
							.append("|").append(adverbKAF).append("|")
							.append(subConOrRelSHIN).append("|")
							.append(tempSubConKAFSHIN).append("|")
							.append(tempSubConMEMSHIN).append("|")
							.append(tempSubConLAMEDKAFSHIN).append("|")
							.append(tempSubConBETSHIN).append("|")
							.append(relativizerTag).append("|")
							.append(temporalSubConjTag).append("|")
							.append(subordinatingConjunctionTag).append("|")
							.append(prefPartUnit).append("|").append(prepBET)
							.append("|").append(prepKAF).append("|")
							.append(prepLAMED).append("|").append(prepMEM)
							.append("|").append(prepositionTag).append("|")
							.append(conjunctionTag);
					System.out.println(decodedPrefixRecord);
					System.out.println();
					pOut.println(decodedPrefixRecord);
				}
				rs.close();
				pOut.close();
				System.out.println("end prefixes download to file");
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			releaseConnection();
			System.exit(1);
		} catch (SQLException e) {
			e.printStackTrace();
			releaseConnection();
			System.exit(1);
		} finally {
			releaseConnection();
		}
	}

	public static String str2NumParticipleType(final String type,
			final String lexiconItem, final String word) {
		String participleTypeCode = "";
		if (type != null && type.length() > 0) {
			char typeStartChar = type.charAt(0);
			switch (typeStartChar) {
			case 'a':
				participleTypeCode = PARTICIPLE_TYPE_ADJECTIVE;
				break;
			case 'n':
				participleTypeCode = PARTICIPLE_TYPE_NOUN;
				break;
			case 'v':
				participleTypeCode = PARTICIPLE_TYPE_VERB;
				break;
			// case 'u':
			// participleTypeCode = PARTICIPLE_TYPE_UNSPECIFIED;
			default:
				final String message = "illegal participleType: " + type
						+ " for lexiconItem: " + lexiconItem
						+ " for input token: " + word;
				System.out.println(message);
				System.exit(1);
			}
		}
		return participleTypeCode;
	}

	public static String str2NumInterrogativeType(final String type,
			final String lexiconItem, final String word) {
		String interrogativeTypeCode = "";
		if (type != null && type.length() > 0) {
			char typeStartChar = type.charAt(4);
			switch (typeStartChar) {
			case 'o':
				if (type.length() == 7)
					interrogativeTypeCode = INTERROGATIVE_TYPE_PRONOUN;
				else
					interrogativeTypeCode = INTERROGATIVE_TYPE_PROADVERB;
				break;
			case 'e':
				if (type.length() == 6)
					interrogativeTypeCode = INTERROGATIVE_TYPE_PRODET;

				break;
			case 'd':
				interrogativeTypeCode = INTERROGATIVE_TYPE_YESNO;
				break;
			default:
				final String message = "illegal interrogativeType: " + type
						+ " for lexiconItem: " + lexiconItem
						+ " for input token: " + word;
				System.out.println(message);
				System.exit(1);
			}
		}
		return interrogativeTypeCode;
	}

	public static String str2NumQuantifierType(final String type,
			final String lexiconItem, final String word) {
		String quantifierTypeCode = "";
		if (type != null && type.length() > 0) {
			char typeStartChar = type.charAt(0);
			switch (typeStartChar) {
			case 'a':
				quantifierTypeCode = QUANTIFIER_TYPE_AMOUNT;
				break;
			case 'p':
				quantifierTypeCode = QUANTIFIER_TYPE_PARTITIVE;
				break;
			case 'd':
				quantifierTypeCode = QUANTIFIER_TYPE_DETERMINER;
				break;

			default:
				final String message = "illegal quantifierType: " + type
						+ " for lexiconItem: " + lexiconItem
						+ " for input token: " + word;
				System.out.println(message);
				System.exit(1);
			}
		}
		return quantifierTypeCode;
	}

	private String encodeConstruct(String construct) {
		String strConstruct = "";
		if (construct == null || construct.equals("unspecified")
				|| construct.equals("null") || construct.equals("")) {
			strConstruct = CONSTRUCT_UNDEFINED;
		} else if (construct.equals("construct") || construct.equals("true")) {
			strConstruct = CONSTRUCT_TRUE;
		} else if (construct.equals("absolute") || construct.equals("false")) {
			strConstruct = CONSTRUCT_FALSE;
		} else {
			System.out.println("illegal construct" + construct);
			System.exit(1);
		}
		return strConstruct;
	}

	private String str2NumBasePos(final String basePos,
			final String lexiconItem, final String word) {
		String strBasePos = "0";
		if (basePos.equals("quantifier"))
			strBasePos = POS_QUANTIFIER;
		else if (basePos.equals("interjection"))
			strBasePos = POS_INTERJECTION;
		else if (basePos.equals("interrogative"))
			strBasePos = POS_INTERROGATIVE;
		else if (basePos.equals("conjunction"))
			strBasePos = POS_CONJUNCTION;
		else if (basePos.equals("adverb"))
			strBasePos = POS_ADVERB;
		else if (basePos.equals("preposition"))
			strBasePos = POS_PREPOSITION;
		else if (basePos.equals("noun"))
			strBasePos = POS_NOUN;
		else if (basePos.equals("adjective"))
			strBasePos = POS_ADJECTIVE;
		else if (basePos.equals("pronoun"))
			strBasePos = POS_PRONOUN;
		else if (basePos.equals("propername"))
			strBasePos = POS_PROPERNAME;
		else if (basePos.equals("verb"))
			strBasePos = POS_VERB;
		else if (basePos.equals("negation"))
			strBasePos = POS_NEGATION;
		else if (basePos.equals("participle"))
			strBasePos = POS_PARTICIPLE;
		else if (basePos.equals("numeral"))
			strBasePos = POS_NUMERAL;
		else if (basePos.equals("existential"))
			strBasePos = POS_EXISTENTIAL;
		else if (basePos.equals("impersonal"))
			strBasePos = POS_IMPERSONAL;
		else if (basePos.equals("modal"))
			strBasePos = POS_MODAL;
		else if (basePos.equals("wPrefix"))
			strBasePos = POS_WPREFIX;
		else if (basePos.equals("passiveParticiple"))
			strBasePos = POS_PASSIVEPARTICIPLE;
		else if (basePos.equals("indInf"))
			strBasePos = POS_INDEPENDENTINFINITIVE;
		else if (basePos.equals("copula"))
			strBasePos = POS_COPULA;
		else if (basePos.equals("title"))
			strBasePos = POS_TITLE;
		else {
			final String message = "illegal pos: " + strBasePos
					+ " for lexiconItem: " + lexiconItem + " for input token: "
					+ word;
			System.out.println(message);
			System.exit(1);
		}
		return strBasePos;
	}

	private String encodeBinyan(String binyan) {
		String strBinyan = "0";
		if (binyan.equals("Pa'al"))
			strBinyan = "1";
		else if (binyan.equals("Nif'al"))
			strBinyan = "2";
		else if (binyan.equals("Pi'el"))
			strBinyan = "3";
		else if (binyan.equals("Pu'al"))
			strBinyan = "4";
		else if (binyan.equals("Hif'il"))
			strBinyan = "5";
		else if (binyan.equals("Huf'al"))
			strBinyan = "6";
		else if (binyan.equals("Hitpa'el"))
			strBinyan = "7";
		else {
			System.out.println("illegal binyan: " + binyan);
			System.exit(1);
		}
		return strBinyan;
	}

	private String encodePGN(String PGN) {
		String strPGN = "";
		if (PGN.equals("1p/MF/Sg"))
			strPGN = "1";
		else if (PGN.equals("2p/F/Sg"))
			strPGN = "2";
		else if (PGN.equals("2p/M/Sg"))
			strPGN = "3";
		else if (PGN.equals("3p/M/Sg"))
			strPGN = "4";
		else if (PGN.equals("3p/F/Sg"))
			strPGN = "5";
		else if (PGN.equals("1p/MF/Pl"))
			strPGN = "6";
		else if (PGN.equals("2p/M/Pl"))
			strPGN = "7";
		else if (PGN.equals("2p/F/Pl"))
			strPGN = "8";
		else if (PGN.equals("2p/MF/Pl"))
			strPGN = "9";
		else if (PGN.equals("3p/M/Pl"))
			strPGN = "10";
		else if (PGN.equals("3p/F/Pl"))
			strPGN = "11";
		else if (PGN.equals("3p/MF/Pl"))
			strPGN = "12";
		else if (PGN.equals("123p/M/Sg"))
			strPGN = "13";
		else if (PGN.equals("123p/F/Sg"))
			strPGN = "14";
		else if (PGN.equals("123p/F/Pl"))
			strPGN = "15";
		else if (PGN.equals("123p/M/Pl"))
			strPGN = "16";
		else if (PGN.equals("1p/M/Sg"))
			strPGN = "17";
		else if (PGN.equals("1p/F/Sg"))
			strPGN = "18";
		else if (PGN.equals("unspecified"))
			strPGN = "19";
		else if (PGN.equals("NONE"))
			strPGN = "20";
		else {
			System.out.println("illegal PGN: " + PGN);
			System.exit(1);
		}
		return strPGN;
	}

	private String encodeTense(String tense) {
		String strTense = "";
		if (tense.equals("imperative"))
			strTense = "1";
		else if (tense.equals("past"))
			strTense = "2";
		else if (tense.equals("beinoni"))
			strTense = "3";
		else if (tense.equals("future"))
			strTense = "4";
		else if (tense.equals("infinitive"))
			strTense = "5";
		else if (tense.equals("bareInfinitive"))
			strTense = "6";
		else if (tense.equals("present"))
			strTense = "7";
		else if (tense.equals("unspecified"))
			strTense = "8";
		else {
			System.out.println("illegal tense: " + tense);
			System.exit(1);
		}
		return strTense;
	}

	private String encodeRegister(String script) {
		String strScript = "";
		if (script.equals("formal"))
			strScript = "1";
		else if (script.equals("archaic"))
			strScript = "2";
		else if (script.equals("informal"))
			strScript = "3";
		else {
			System.out.println("illegal register: " + script);
			System.exit(1);
		}
		return strScript;
	}

	private String encodeSpelling(String spelling) {
		String strScript = "";
		if (spelling.equals("standard"))
			strScript = "1";
		else if (spelling.equals("irregular"))
			strScript = "2";
		else {
			System.out.println("illegal spelling: " + spelling);
			System.exit(1);
		}
		return strScript;
	}

	private String encodeNumber(String number) {
		String strNumber = "";
		if (number.equals("singular"))
			strNumber = "1";
		else if (number.equals("singular and plural"))
			strNumber = "2";
		else if (number.equals("plural"))
			strNumber = "3";
		else if (number.equals("dual"))
			strNumber = "4";
		else if (number.equals("dual and plural"))
			strNumber = "5";
		else if (number.equals("unspecified"))
			strNumber = "6";
		else {
			System.out.println("illegal number: " + number);
			System.exit(1);
		}
		return strNumber;
	}

	private String encodeGender(String gender) {
		String strGender = "";
		if (gender.equals("masculine"))
			strGender = "1";
		else if (gender.equals("feminine"))
			strGender = "2";
		else if (gender.equals("masculine and feminine"))
			strGender = "3";
		else if (gender.equals("unspecified"))
			strGender = "4";
		else {
			System.out.println("illegal gender: " + gender);
			System.exit(1);
		}
		return strGender;
	}

	private String encodeBaseDefinitness(String baseDefinitness) {
		String strBaseDefinitness = "";
		if (baseDefinitness.equals("tt"))
			strBaseDefinitness = "1";
		else if (baseDefinitness.equals("tf"))
			strBaseDefinitness = "2";
		else if (baseDefinitness.equals("f"))
			strBaseDefinitness = "3";
		else if (baseDefinitness.equals("rf"))
			strBaseDefinitness = "4";
		else if (baseDefinitness.equals("rt"))
			strBaseDefinitness = "5";
		else if (baseDefinitness.equals("s"))
			strBaseDefinitness = "6";
		else {
			System.out.println("illegal baseDefinitness: " + baseDefinitness);
			System.exit(1);
		}
		return strBaseDefinitness;
	}

	private String str2NumConjunctionType(final String baseConjunctionType,
			final String lexiconItem, final String word) {
		String strBaseConjunctionType = "";
		if (baseConjunctionType.equals("coordinating")) {
			strBaseConjunctionType = CONJUNCTION_TYPE_COORDINATING;
		} else if (baseConjunctionType.equals("subordinating"))
			strBaseConjunctionType = CONJUNCTION_TYPE_SUBORDINATING;
		else if (baseConjunctionType.equals("relativizing"))
			strBaseConjunctionType = CONJUNCTION_TYPE_RELATIVIZING;
		else if (baseConjunctionType.equals("unspecified"))
			strBaseConjunctionType = "-";
		else {
			final String message = "illegal baseConjunctionType: "
					+ baseConjunctionType + " for lexiconItem: " + lexiconItem
					+ " for input token: " + word;
			System.out.println(message);
			System.exit(1);
		}
		return strBaseConjunctionType;
	}

	private String encodeBasePronounType(final String basePronounType,
			final String lexiconItem, final String word) {
		final String bpt = basePronounType.trim();
		String strBasePronounType = "";
		if (bpt.equals("interrogative"))
			strBasePronounType = "1";
		else if (bpt.equals("personal"))
			strBasePronounType = "2";
		else if (bpt.equals("demonstrative"))
			strBasePronounType = "3";
		else if (bpt.equals("impersonal"))
			strBasePronounType = "4";
		else if (bpt.equals("relativizer"))
			strBasePronounType = "5";
		else if (bpt.equals("reflexive"))
			strBasePronounType = "6";
		else {
			final String message = "illegal basePronounType: " + bpt
					+ " for lexiconItem: " + lexiconItem + " for input token: "
					+ word;
			System.out.println(message);
			System.exit(1);
		}
		return strBasePronounType;
	}

	private String encodeBaseNamedEntityType(String baseNamedEntityType) {
		String strBaseNamedEntityType = "";
		if (baseNamedEntityType.equals("location"))
			strBaseNamedEntityType = "1";
		else if (baseNamedEntityType.equals("person"))
			strBaseNamedEntityType = "2";
		else if (baseNamedEntityType.equals("organization"))
			strBaseNamedEntityType = "3";
		else if (baseNamedEntityType.equals("dateTime"))
			strBaseNamedEntityType = "4";
		else if (baseNamedEntityType.equals("other"))
			strBaseNamedEntityType = "5";
		else if (baseNamedEntityType.equals("product"))
			strBaseNamedEntityType = "6";
		else if (baseNamedEntityType.equals("country"))
			strBaseNamedEntityType = "7";
		else if (baseNamedEntityType.equals("symbol"))
			strBaseNamedEntityType = "8";
		else if (baseNamedEntityType.equals("language"))
			strBaseNamedEntityType = "9";
		else if (baseNamedEntityType.equals("town"))
			strBaseNamedEntityType = "10";
		else if (baseNamedEntityType.equals("art"))
			strBaseNamedEntityType = "11";
		else if (baseNamedEntityType.equals("unspecified"))
			strBaseNamedEntityType = "12";
		else {
			System.out.println("illegal baseNamedEntityType: "
					+ baseNamedEntityType);
			System.exit(1);
		}
		return strBaseNamedEntityType;
	}

	private String encodeSuffixFunction(String suffixFunction) {
		String suffixFunctioni = "";
		if (suffixFunction.equals("possessive"))
			suffixFunctioni = "1";
		else if (suffixFunction.equals("accusative or nominative"))
			suffixFunctioni = "2";
		else if (suffixFunction.equals("pronomial"))
			suffixFunctioni = "3";
		else if (suffixFunction.equals("unspecified"))
			suffixFunctioni = "4";
		else {
			System.out.println("illegal suffixFunction: " + suffixFunction);
			System.exit(1);
		}
		return suffixFunctioni;
	}

	private String encodeBaseNumeralType(String baseNumeralType) {
		String baseNumeralTypei = "";
		if (baseNumeralType.equals("numeral ordinal"))
			baseNumeralTypei = "1";
		else if (baseNumeralType.equals("numeral cardinal"))
			baseNumeralTypei = "2";
		else if (baseNumeralType.equals("numeral fractional"))
			baseNumeralTypei = "3";
		else if (baseNumeralType.equals("literal number"))
			baseNumeralTypei = "4";
		else if (baseNumeralType.equals("unspecified"))
			baseNumeralTypei = "5";
		else {
			System.out.println("illegal baseNumeralType: " + baseNumeralType);
			System.exit(1);
		}
		return baseNumeralTypei;
	}

	public void writeInflections2File() throws Exception {
		String transliterated = "";
		String basePos = "";
		String baseLexiconPointer = "";
		String baseTransliteratedLItem = "";
		String register = "";
		String spelling = "";
		String suffixStatus = "";
		String PGN = "";
		String binyan = "";
		String tense = "";
		String root = "";
		String baseNumber = "";
		String baseGender = "";
		String basePerson = "";
		String baseDefinitness = "";
		String baseConjunctionType = "";
		String baseNamedEntityType = "";
		String basePronounType = "";
		String suffixFunction = "";
		String baseNumeralType = "";
		String dottedLexiconItem = "";
		String polarity;
		String foreign = "";
		String prefix = "";
		String value = "";
		String type = "u";
		String participleType = "";
		String interrogativeType = "";
		String baseUndottedLItem = "";
		String quantifierType = "";
		String surface = "";

		String field;
		StringBuffer decodedInflectionRecord = null;
		BufferedOutputStream bf;
		// String sql =
		// "select * from inflections where baseLexiconPointer=10521";
		// String sql =
		// "select * from inflections where baseLexiconPointer=26525";
		String sql = "select * from inflections";
		int counter = 0;
		ResultSet rs = null;
		if (pc)
			dir = "C:\\Documents and Settings\\daliabo\\My Documents\\lexicon\\diffTests\\";
		// dir = "C:\\NLP Project\\sg\\Download2File1\\Output\\";
		else
			dir = "";
		try {
			// bf = new BufferedOutputStream(new FileOutputStream(dir
			// + "inflections.data"));
			out = new FileOutputStream(dir + "./inflections.data");
			pOut = new PrintStream(out);
			rs = getData(sql);
			if (rs != null) {
				while (rs.next()) {
					type = "-";
					// baseLexiconPointer
					field = rs.getString("baseLexiconPointer");
					if (field == null || field.equals("")
							|| field.equals("unspecified"))
						baseLexiconPointer = "-";
					else
						baseLexiconPointer = field;

					// basePos
					field = rs.getString("basePos");
					if (field == null || field.equals("")
							|| field.equals("unspecified"))
						basePos = "-";
					else
						basePos = str2NumBasePos(field,
								baseTransliteratedLItem, transliterated);

					// baseTransliteratedLItem
					field = rs.getString("baseTransliteratedLItem");
					if (field == null || field.equals("")
							|| field.equals("unspecified"))
						baseTransliteratedLItem = "-";
					else
						baseTransliteratedLItem = field;

					// baseUndottedLItem
					field = rs.getString("baseUndottedLItem");
					if (field == null || field.equals("")
							|| field.equals("unspecified"))
						baseUndottedLItem = "-";
					else
						baseUndottedLItem = field;
					baseUndottedLItem = baseUndottedLItem.replaceAll(
							"%26%2339%3B", "%27");

					// surface
					field = rs.getString("surface");
					if (field == null || field.equals("")
							|| field.equals("unspecified"))
						surface = "-";
					else
						surface = field;

					// register
					field = rs.getString("register");
					if (field == null || field.equals("")
							|| field.equals("unspecified"))
						register = "-";
					else
						register = encodeRegister(field);

					// spelling
					field = rs.getString("spelling");
					if (field == null || field.equals("")
							|| field.equals("unspecified"))
						spelling = "-";
					else
						spelling = encodeSpelling(field);

					// suffixStatus
					field = rs.getString("suffixStatus");
					if (field == null || field.equals("")
							|| field.equals("unspecified"))
						suffixStatus = "-";
					else
						suffixStatus = encodeConstruct(field);

					// transliterated
					field = rs.getString("transliterated");
					if (field == null || field.equals("")
							|| field.equals("unspecified"))
						transliterated = "-";
					else
						transliterated = field;

					transliterated = transliterated.replaceAll("&#39;", "'");

					// PGN
					field = rs.getString("PGN");
					if (field == null || field.equals("")
							|| field.equals("unspecified"))
						PGN = "-";
					else
						PGN = encodePGN(field);

					// binyan
					field = rs.getString("binyan");
					if (field == null || field.equals("")
							|| field.equals("unspecified"))
						binyan = "-";
					else
						binyan = encodeBinyan(field);

					// tense
					field = rs.getString("tense");
					if (field == null || field.equals("")
							|| field.equals("unspecified"))
						tense = "-";
					else
						tense = encodeTense(field);

					// root

					field = rs.getString("root");
					if (field == null || field.equals("")
							|| field.equals("unspecified")) {
						root = "-";
						field = rs.getString("root");
					} else {
						root = field;
						// root = URLDecoder.decode(field, "UTF-8"); // EDIT
						// removed so that it will be encoded (yossi 27.12.11)
						// root = Translate.Heb2Eng(root); // EDIT : removed
						// this line so it will be the same as in the mysql
						// version (yossi 21.7.11)
					}
					root = root.replaceAll("&#39;", "'");

					// dottedLexiconItem
					field = rs.getString("dottedLexiconItem");
					if (field == null || field.equals("")
							|| field.equals("unspecified")) {
						dottedLexiconItem = "-";
						field = rs.getString("dottedLexiconItem");
					} else {
						dottedLexiconItem = field;
					}
					dottedLexiconItem = dottedLexiconItem.replaceAll(
							"%26%2339%3B", "%27");

					// baseNumber
					field = rs.getString("baseNumber");
					if (field == null || field.equals("")
							|| field.equals("null")
							|| field.equals("unspecified"))
						baseNumber = "-";
					else
						baseNumber = encodeNumber(field);

					// baseGender
					field = rs.getString("baseGender");
					if (field == null || field.equals("")
							|| field.equals("null")
							|| field.equals("unspecified"))
						baseGender = "-";
					else
						baseGender = encodeGender(field);

					// baseNamedEntityType
					if (basePos.equals(POS_PROPERNAME)) {
						field = rs.getString("type");
						if (field == null || field.equals("")
								|| field.equals("null")
								|| field.equals("unspecified"))
							baseNamedEntityType = "-";
						else
							baseNamedEntityType = encodeBaseNamedEntityType(field);
						type = baseNamedEntityType;
					}

					// baseDefinitness
					field = rs.getString("baseDefinitness");
					if (field == null || field.equals("")
							|| field.equals("unspecified"))
						baseDefinitness = "-";
					else
						baseDefinitness = encodeBaseDefinitness(field);

					// baseConjunctionType
					if (basePos.equals(POS_CONJUNCTION)) {
						field = rs.getString("type");
						if (field == null || field.equals("")
								|| field.equals("null")
								|| field.equals("unspecified"))
							baseConjunctionType = "-";
						else
							baseConjunctionType = str2NumConjunctionType(field,
									baseTransliteratedLItem, transliterated);
						type = baseConjunctionType;
					}

					// basePronounType
					if (basePos.equals(POS_PRONOUN)) {
						field = rs.getString("type");
						if (field == null || field.equals("")
								|| field.equals("null")
								|| field.equals("unspecified"))
							basePronounType = "-";
						else
							basePronounType = encodeBasePronounType(field,
									baseTransliteratedLItem, transliterated);
						type = basePronounType;
					}

					// basePerson
					field = rs.getString("basePerson");
					if (field == null || field.equals("")
							|| field.equals("null")
							|| field.equals("unspecified"))
						basePerson = "-";
					else if (field.equals("any"))
						basePerson = "4";
					else
						basePerson = field;

					// suffix function
					field = rs.getString("suffixFunction");
					if (field == null || field.equals("")
							|| field.equals("null")
							|| field.equals("unspecified"))
						suffixFunction = "-";
					else
						suffixFunction = encodeSuffixFunction(field);

					if (basePos.equals(POS_NUMERAL)) {
						field = rs.getString("type");
						if (field == null || field.equals("")
								|| field.equals("null")
								|| field.equals("unspecified"))
							baseNumeralType = "-";
						else
							baseNumeralType = encodeBaseNumeralType(field);
						type = baseNumeralType;
					}

					if (basePos.equals(POS_QUANTIFIER)) {
						field = rs.getString("type");
						if (field == null || field.equals("")
								|| field.equals("null")
								|| field.equals("unspecified"))
							quantifierType = "-";
						else
							quantifierType = str2NumQuantifierType(field,
									baseTransliteratedLItem, transliterated);
						type = quantifierType;
					}

					if (basePos.equals(POS_INTERROGATIVE)) {
						field = rs.getString("type");
						if (field == null || field.equals("")
								|| field.equals("null")
								|| field.equals("unspecified"))
							interrogativeType = "-";
						else
							interrogativeType = str2NumInterrogativeType(field,
									baseTransliteratedLItem, transliterated);
						type = interrogativeType;

					}

					if (basePos.equals(POS_PARTICIPLE)
							|| basePos.equals(POS_PASSIVEPARTICIPLE)) {

						field = rs.getString("type");
						if (field == null || field.equals("")
								|| field.equals("null")
								|| field.equals("unspecified"))
							participleType = "-";
						else
							participleType = str2NumParticipleType(field,
									baseTransliteratedLItem, transliterated);
						type = participleType;
					}

					field = rs.getString("value");
					if (field.equals("") || field.equals("unspecified"))
						value = "-";
					else
						value = field;
					value = value.replaceAll("%26%2339%3B", "%27");

					field = rs.getString("polarity");
					if (field.equals("2") || field.equals("unspecified"))
						polarity = "-";
					else
						polarity = field;

					field = rs.getString("hebForeign");
					if (field.equals("") || field.equals("unspecified"))
						foreign = "-";
					else
						foreign = field;

					prefix = rs.getString("prefix");
					if (prefix.equals("unspecified"))
						prefix = "-";

					decodedInflectionRecord = new StringBuffer()
							.append(transliterated).append("|").append(basePos)
							.append("|").append(register).append("|")
							.append(spelling).append("|").append(suffixStatus)
							.append("|").append(baseLexiconPointer).append("|")
							.append(PGN).append("|").append(binyan).append("|")
							.append(tense).append("|").append(root).append("|")
							.append(baseNumber).append("|").append(baseGender)
							.append("|").append(baseDefinitness).append("|")
							.append(basePerson).append("|")
							.append(suffixFunction).append("|")
							.append(dottedLexiconItem).append("|")
							.append(baseUndottedLItem).append("|")
							.append(polarity).append("|").append(value)
							.append("|").append(type).append("|")
							.append(foreign).append("|").append(prefix);

					counter++;
					System.out.println(decodedInflectionRecord);
					System.out.println(counter);
					pOut.println(decodedInflectionRecord);
				}
				rs.close();
				pOut.close();
				System.out.println("end inflections download to file");
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			releaseConnection();
			System.exit(1);
		} catch (SQLException e) {
			e.printStackTrace();
			releaseConnection();
			System.exit(1);
		} finally {
			releaseConnection();
		}
	}

	public static void main(String[] args) {
		long startTime = System.currentTimeMillis();
		Download2File st = new Download2File();
		// Download2File.dir = args[0];
		// System.out.println("dir =" + dir);
		try {
			st.writeInflections2File();
			st.WriteMWinflections2File();
			st.writePrefixes2File();
			st.writeGimatria2File();
			st.WriteMWE1_2File();
			st.WriteMWE_2File(2);
			st.WriteMWE_2File(3);
			st.WriteMWE_2File(4);
		} catch (Exception e) {
			e.printStackTrace();
			System.exit(1);
		}

		// st.writePrefixes2File();
		long elapsedTime = System.currentTimeMillis() - startTime;
		System.out.println("Elapsed time = " + elapsedTime + " ms");
		System.exit(0);
	}
}
