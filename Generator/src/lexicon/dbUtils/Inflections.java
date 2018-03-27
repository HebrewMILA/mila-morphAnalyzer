/*
 * Created on 07/07/2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package lexicon.dbUtils;

import gen.InflectionEntry;

import java.io.FileOutputStream;
import java.io.PrintStream;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import lexicon.contents.ConnectedGenerator;
import lexicon.stringUtils.Translate;

/**
 * @author daliabo
 * 
 *         TODO To change the template for this generated type comment go to
 *         Window - Preferences - Java - Code Style - Code Templates
 */
public class Inflections extends ConnectedGenerator {
	private String surface;
	private String transliterated;
	private String register;
	private String spelling;
	private String basePos;
	private String baseTransliteratedLItem;
	private String baseUndottedLItem;
	private String baseLexiconPointer;
	private String baseAlternatePointer;
	private String binyan = "";
	private String root = "";
	private String tense = "";
	private String baseGender;
	private String baseNumber;
	private String basePerson;
	private String suffixSurface;
	private String suffixFunction;
	private String suffixStatus;
	private String PGN = "";
	private String type = "";
	private String baseDefinitness = "";
	private String dottedLexiconItem;
	private String polarity;
	private String value;
	private String prefix = "unspecified";
	private boolean consecutive;
	private int subId;
	private int multiWordLen;

	/**
	 * @return Returns the consecutive.
	 */
	public boolean isConsecutive() {
		return consecutive;
	}

	/**
	 * @param consecutive
	 *           The consecutive to set.
	 */
	public void setConsecutive(boolean consecutive) {
		this.consecutive = consecutive;
	}

	/**
	 * @return Returns the pInflections.
	 */
	public PrintStream getPInflections() {
		return pInflections;
	}

	/**
	 * @param inflections
	 *           The pInflections to set.
	 */
	public void setPInflections(PrintStream inflections) {
		pInflections = inflections;
	}

	/**
	 * @return Returns the pOut.
	 */
	public PrintStream getPOut() {
		return pOut;
	}

	/**
	 * @param out
	 *           The pOut to set.
	 */
	public void setPOut(PrintStream out) {
		pOut = out;
	}

	/**
	 * @return Returns the baseUndottedLItem.
	 */
	public String getBaseUndottedLItem() {
		return baseUndottedLItem;
	}

	/**
	 * @return Returns the binyan.
	 */
	public String getBinyan() {
		return binyan;
	}

	/**
	 * @return Returns the hebForeign.
	 */
	public String getHebForeign() {
		return hebForeign;
	}

	/**
	 * @return Returns the pGN.
	 */
	public String getPGN() {
		return PGN;
	}

	/**
	 * @return Returns the root.
	 */
	public String getRoot() {
		return root;
	}

	/**
	 * @return Returns the script.
	 */
	public String getRegister() {
		return register;
	}

	/**
	 * @return Returns the suffixStatus.
	 */
	public String getSuffixStatus() {
		return suffixStatus;
	}

	/**
	 * @return Returns the tense.
	 */
	public String getTense() {
		return tense;
	}

	/**
	 * @return Returns the type.
	 */
	public String getType() {
		return type;
	}

	/**
	 * @param hebForeign
	 *           The hebForeign to set.
	 */
	public void setHebForeign(String hebForeign) {
		this.hebForeign = hebForeign;
	}

	private String hebForeign = "";

	// private int baseId;

	// private String comment;

	PrintStream pOut = null; // declare a print stream object

	PrintStream pInflections = null; // declare a print stream object

	public static void main(String[] args) {
		Inflections inflections = new Inflections();
		inflections.getWholeNouns();

	}

	// public void insertItem() {
	// transliterated = apostropheHandling(transliterated);
	// baseTransliteratedLItem = apostropheHandling(baseTransliteratedLItem);
	//
	// //Hebrew in saved in the database in "ISO-8859-1"
	// try {
	// setSurface(URLEncoder.encode(getSurface(), "UTF-8"));
	// setBaseundottedLItem(URLEncoder.encode(getBaseundottedLItem(),
	// "UTF-8"));
	// //setSuffixSurface(URLEncoder.encode(suffixSurface, "UTF-8"));
	// setRoot(URLEncoder.encode(root, "UTF-8"));
	// setDottedLexiconItem(URLEncoder.encode(dottedLexiconItem, "UTF-8"));
	// String sql = "INSERT INTO inflections "
	// + "(surface, transliterated, basePos, baseTransliteratedLItem," +
	// "baseLexiconPointer,baseUndottedLItem,script,PGN," +
	// "baseNamedEntityType,baseGender,baseNumber," +
	// "baseDefinitness,baseConjunctionType,baseQuantifierType," +
	// "baseNumeralType,basePronounType,basePerson,suffixStatus" +
	// ",suffixGender,suffixNumber,suffixFunction, " +
	// "suffixTransliterated, suffixPerson,binyan,tense," +
	// "root,accusativeSuffix,accusativeNominativeNumber, " +
	// "accusativeNominativeGender,accusativeNominativePerson ," +
	// "accusativeNominativePGN, dottedLexiconItem,polarity,hebForeign" +
	// ", consecutive,subId,multiWordLen" +
	// ") VALUES ("
	// + "'" + surface + "','" + transliterated + "','" + basePos
	// + "','" + baseTransliteratedLItem + "','" + baseLexiconPointer
	// + "','" + baseUndottedLItem + "','" + script + "','" + PGN
	// + "','" + baseNamedEntityType + "','" + baseGender + "','"
	// + baseNumber + "','" + baseDefinitness + "','"
	// + baseConjunctionType + "','" + baseQuantifierType + "','"
	// + baseNumeralType + "','"
	// + basePronounType + "','" + basePerson + "','" + suffixStatus
	// + "','" + suffixGender + "','" + suffixNumber + "','"
	// + suffixFunction + "','" + suffixTransliterated + "','"
	// + suffixPerson + "','" + binyan + "','"+ tense + "','"
	// + root + "','" + accusativeSuffix + "','" + accusativeNominativeNumber +
	// "','" + accusativeNominativeGender + "','" + accusativeNominativePerson +
	// "','" + accusativeNominativePGN + "','" + dottedLexiconItem + "','" +
	// polarity + "','" + hebForeign + "'" +
	// "," + consecutive+ "," + subId+"," + multiWordLen+
	// ")";
	// System.out.println("sql="+sql);
	// int feedback = execute(sql);
	// } catch (Exception e) {
	// e.printStackTrace();
	// System.exit(0);
	// }
	//
	// }

	// //without multiWord
	public void insertItem() {
		transliterated = apostropheHandling(transliterated);
		baseTransliteratedLItem = apostropheHandling(baseTransliteratedLItem);
		// Hebrew in saved in the database in "ISO-8859-1"
		try {
			setSurface(URLEncoder.encode(getSurface(), "UTF-8"));
			setBaseundottedLItem(URLEncoder.encode(getBaseundottedLItem(), "UTF-8"));
			// setSuffixSurface(URLEncoder.encode(suffixSurface, "UTF-8"));
			setRoot(URLEncoder.encode(root, "UTF-8"));
			setDottedLexiconItem(URLEncoder.encode(dottedLexiconItem, "UTF-8"));
			if (!value.equals(""))
				setValue(URLEncoder.encode(value, "UTF-8"));

			// check that this inflection is not removed
			InflectionEntry iEntry = new InflectionEntry(this);
			if (iEntry.CheckIfInflectionRemoved())
				return; // if it in the removed list then dont add it

			String sql = "INSERT INTO inflections " + "(surface, transliterated, basePos, baseTransliteratedLItem,"
					+ "baseLexiconPointer,baseUndottedLItem,register,spelling,PGN," + "baseGender,baseNumber,"
					+ "baseDefinitness," + "type,basePerson,suffixStatus" + ",suffixFunction, " + "binyan,tense,"
					+ "root, dottedLexiconItem,polarity,value, hebForeign, prefix, baseAlternatePointer" +
					// ", consecutive,subId" +
					") VALUES (" + "'" + surface + "','" + transliterated + "','" + basePos + "','" + baseTransliteratedLItem
					+ "','" + baseLexiconPointer + "','" + baseUndottedLItem + "','" + register + "','" + spelling + "','"
					+ PGN + "','" + baseGender + "','" + baseNumber + "','" + baseDefinitness + "','" + type + "','"
					+ basePerson + "','" + suffixStatus + "','" + suffixFunction + "','" + binyan + "','" + tense + "','"
					+ root + "','" + dottedLexiconItem + "','" + polarity + "','" + value + "','" + hebForeign + "','"
					+ prefix + "','" + baseAlternatePointer + "'" +
					// "," + consecutive+ "," + subId+
					")";
			execute(sql);
		} catch (Exception e) {
			e.printStackTrace();
			System.exit(0);
		}

	}

	// public void insertVerbInflection() {
	// try {
	// setSurface(URLEncoder.encode(getSurface(), "UTF-8"));
	// setBaseundottedLItem(URLEncoder.encode(getBaseundottedLItem(),
	// "UTF-8"));
	// setRoot(URLEncoder.encode(root, "UTF-8"));
	// String sql = "INSERT INTO inflections "
	// + "(surface, transliterated, basePos,baseUndottedLItem,
	// baseTransliteratedLItem,baseLexiconPointer,binyan,root,PGN,tense,script,suffixStatus)
	// "
	// + "VALUES (" + "'" + surface + "','" + transliterated + "','"
	// + basePos + "','" + baseUndottedLItem + "','"
	// + baseTransliteratedLItem + "','" + baseLexiconPointer + "','"
	// + binyan + "','" + root + "','" + PGN + "','" + tense + "','"
	// + script + "','" + suffixStatus + "')";
	// int feedback = execute(sql);
	//
	// } catch (Exception e) {
	// // TODO Auto-generated catch block
	// e.printStackTrace();
	// System.exit(0);
	// }
	// }

	// public List getInflectionRecord(String input)
	// throws UnsupportedEncodingException {
	//
	// input = apostropheHandling(input);
	//
	// ArrayList result = new ArrayList();
	// String sql = "SELECT * FROM inflections where transliterated ='"
	// + input + "'";
	// ResultSet rs = null;
	// try {
	// rs = getData(sql);
	// if (rs != null) {
	//
	// while (rs.next()) {
	//
	// InflectRecord infRec = new InflectRecord();
	// infRec.setBaseLexiconPointer(rs
	// .getString("baseLexiconPointer"));
	// infRec.setBasePos(rs.getString("basePos"));
	// infRec.setBaseTransliteratedLItem(rs
	// .getString("baseTransliteratedLItem"));
	// infRec.setScript(rs.getString("script"));
	// infRec.setSuffixFunction(rs.getString("suffixFunction"));
	// infRec.setSuffixGender(rs.getString("suffixGender"));
	// infRec.setSuffixNumber(rs.getString("suffixNumber"));
	// infRec.setSuffixStatus(rs.getString("suffixStatus"));
	// infRec.setSurface(rs.getString("surface"));
	// infRec.setTransliterated(rs.getString("transliterated"));
	// infRec.setPGN(rs.getString("PGN"));
	// infRec.setBaseUndottedLItem(rs
	// .getString("baseUndottedLItem"));
	// infRec.setBinyan(rs.getString("binyan"));
	// infRec.setTense(rs.getString("tense"));
	// infRec.setRoot(rs.getString("root"));
	// infRec.setBaseNumber(rs.getString("baseNumber"));
	// infRec.setBaseGender(rs.getString("baseGender"));
	// infRec.setBaseNamedEntityType(rs
	// .getString("baseNamedEntityType"));
	// infRec.setBaseDefinitness(rs.getString("baseDefinitness"));
	// infRec.setBaseConjunctionType(rs
	// .getString("BaseConjunctionType"));
	// infRec.setBaseQuantifierType(rs
	// .getString("baseQuantifierType"));
	// infRec.setBasePronounType(rs.getString("basePronounType"));
	// infRec.setBasePerson(rs.getString("basePerson"));
	// infRec.setSuffixPerson(rs.getString("suffixPerson"));
	// infRec.setDottedLexiconItem(rs.getString("dottedLexiconItem"));
	// infRec.setPolarity(rs.getString("polarity"));
	// infRec.setConsecutive(rs.getBoolean("consecutive"));
	// infRec.setSubId(rs.getInt("subId"));
	// result.add(infRec);
	// }
	// rs.close();
	// }
	// } catch (SQLException e) {
	// e.printStackTrace();
	// System.exit(1);
	// } finally {
	// releaseConnection();
	// }
	// return result;
	// }
	//

	public List getInflectionRecord(String input) throws UnsupportedEncodingException {

		input = apostropheHandling(input);

		ArrayList result = new ArrayList();
		String sql = "SELECT * FROM inflections where transliterated ='" + input + "'";
		ResultSet rs = null;
		try {
			rs = getData(sql);
			if (rs != null) {

				while (rs.next()) {

					InflectRecord infRec = new InflectRecord();
					infRec.setBaseLexiconPointer(rs.getString("baseLexiconPointer"));
					infRec.setBaseLexiconPointer(rs.getString("baseAlternatePointer"));
					infRec.setBasePos(rs.getString("basePos"));
					infRec.setBaseTransliteratedLItem(rs.getString("baseTransliteratedLItem"));
					infRec.setRegister(rs.getString("register"));
					infRec.setSpelling(rs.getString("spelling"));
					infRec.setSuffixFunction(rs.getString("suffixFunction"));
					infRec.setSuffixStatus(rs.getString("suffixStatus"));
					infRec.setSurface(rs.getString("surface"));
					infRec.setTransliterated(rs.getString("transliterated"));
					infRec.setPGN(rs.getString("PGN"));
					infRec.setBaseUndottedLItem(rs.getString("baseUndottedLItem"));
					infRec.setBinyan(rs.getString("binyan"));
					infRec.setTense(rs.getString("tense"));
					infRec.setRoot(rs.getString("root"));
					infRec.setBaseNumber(rs.getString("baseNumber"));
					infRec.setBaseGender(rs.getString("baseGender"));
					infRec.setType(rs.getString("type"));
					infRec.setBasePerson(rs.getString("basePerson"));
					infRec.setDottedLexiconItem(rs.getString("dottedLexiconItem"));
					infRec.setPolarity(rs.getString("polarity"));
					// infRec.setConsecutive(rs.getBoolean("consecutive"));
					// infRec.setSubId(rs.getInt("subId"));
					result.add(infRec);
				}
				rs.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
			System.exit(1);
		} finally {
			releaseConnection();
		}
		return result;
	}

	public void getWholeInflections() {
		String dir = "C:\\Documents and Settings\\daliabo\\My Documents\\lexicon\\diffTests\\";
		FileOutputStream inflections; // declare a file output object
		try {

			// Create a new file output stream
			// connected to "myfile.txt"
			inflections = new FileOutputStream(dir + "mw.txt");

			// Connect print stream to the output stream
			pInflections = new PrintStream(inflections);

		} catch (Exception e) {
			System.err.println("Error writing to file");
		}
		try {
			// String sql = "select transliterated from inflections order by id
			// ";
			// String sql = "select * from item,adjective,noun_exception_type
			// where item.id=adjective.id and item.id=noun_exception_type.id and
			// adjective.number='singular' and adjective.gender='masculine' and
			// noun_exception_type.transliterated like '%im'";
			// String sql = "select * from item, noun, noun_exception_type " +
			// "where item.id=noun.id and noun.id=noun_exception_type.id " +
			// "and item.transliterated like '%iih' and
			// noun_exception_type.transliterated " +
			//// "like '%ih' and noun_exception_type.script ='colloquial'";
			// String sql = "select * from item, noun " +
			// "where item.id=noun.id " +
			// "and item.transliterated like '%ii%' and item.transliterated not
			// like '%iih' ";
			//
			// sql = "select * from item, noun, noun_exception_type " +
			// "where item.id=noun.id and noun.id=noun_exception_type.id " +
			// "and item.transliterated like '%ii%' and item.transliterated not
			// " +
			// "like '%iih' and noun_exception_type.transliterated " +
			// "like '%i%' and noun_exception_type.script ='colloquial'";

			String sql = "select * from noun";

			String transliterated = "";
			String gender = "";
			String number = "";
			String feminine;
			String plural;
			String id = "";
			ResultSet rs = null;
			rs = getData(sql);
			rs.last();
			rs.getRow();
			while (rs.next()) {
				rs.getRow();
				id = rs.getString("id");
				transliterated = rs.getString("transliterated");
				// undotted = rs.getString("undotted");
				//// dotted = rs.getString("dotted");
				String surface = Translate.Eng2Heb(transliterated);
				//// String description = rs.getString("description");
				//// basePos = rs.getString("basepos");
				gender = rs.getString("gender");
				number = rs.getString("number");
				feminine = rs.getString("feminine");
				plural = rs.getString("plural");
				//// basePerson = rs.getString("basePerson");
				//// baseTransliteratedLItem =
				// rs.getString("baseTransliteratedLItem");
				//// baseLexiconPointer = rs.getString("baseLexiconPointer");
				//// PGN = rs.getString("PGN");
				//// suffixGender= rs.getString("suffixGender");
				//// suffixNumber= rs.getString("suffixNumber");
				//// suffixPerson= rs.getString("suffixPerson");
				//// suffixStatus= rs.getString("suffixStatus");
				//// script = rs.getString("script");
				//// //baseUndottedLItem = rs.getString("baseUndottedLItem");
				//// accusativeNominativePGN =
				// rs.getString("accusativeNominativePGN");
				//// baseNumeralType = rs.getString("baseNumeralType");
				//// basePronounType= rs.getString("basePronounType");
				//// baseConjunctionType= rs.getString("baseConjunctionType");
				//// baseQuantifierType= rs.getString("baseQuantifierType");
				//// basenameEntityType = rs.getString("baseNamedEntityType");
				//// String definitness = rs.getString("baseDefinitness");
				//// root= rs.getString("root");
				//// try {
				//// root = URLDecoder.decode(root, "UTF-8");
				//// } catch (UnsupportedEncodingException e1) {
				//// // TODO Auto-generated catch block
				//// e1.printStackTrace();
				//// }
				//// binyan= rs.getString("binyan");
				//// tense = rs.getString("tense");
				//// try {
				// //surface = URLDecoder.decode(surface, "UTF-8");
				//// undotted = URLDecoder.decode(undotted, "UTF-8");
				//// } catch (UnsupportedEncodingException e) {
				// // TODO Auto-generated catch block
				//// e.printStackTrace();
				//// }
				pInflections.print(surface);
				pInflections.print("	");
				pInflections.print(id);
				pInflections.print("	");
				pInflections.print(gender);
				pInflections.print("	");
				pInflections.print(number);
				pInflections.print("	");
				pInflections.print(feminine);
				pInflections.print("	");
				pInflections.print(plural);
				pInflections.print("	");
				//// pInflections.print(undotted);
				//// pInflections.print(" ");
				//// pInflections.print(transliterated);
				//// pInflections.print(" ");
				//// pInflections.print(dotted);
				//// pInflections.print(" ");
				//
				//// pInflections.print(baseTransliteratedLItem);
				//// pInflections.print(" ");
				//// pInflections.print(baseUndottedLItem);
				////
				//// pInflections.print(root);
				//// pInflections.print(" ");
				//// pInflections.print(binyan);
				//// pInflections.print(" ");
				//// pInflections.print(tense);
				//// pInflections.print(" ");
				//// pInflections.print(baseGender);
				//// pInflections.print(" ");
				//// pInflections.print(baseNumber);
				//// pInflections.print(" ");
				//// pInflections.print(basePerson);
				//// pInflections.print(" ");
				//// pInflections.print(suffixStatus);
				//// pInflections.print(" ");
				//// pInflections.print(accusativeNominativePGN);
				//// pInflections.print(" ");
				//// pInflections.print(PGN);
				//// pInflections.print(" ");
				//// pInflections.print(suffixGender);
				//// pInflections.print(" ");
				//// pInflections.print(suffixNumber);
				//// pInflections.print(" ");
				//// pInflections.print(suffixPerson);
				//// pInflections.print(" ");
				//// pInflections.print(baseNumeralType);
				//// pInflections.print(" ");
				//// pInflections.print(definitness);
				//// pInflections.print(" ");
				////
				////// pInflections.print(baseQuantifierType);
				////// pInflections.print(" ");
				//// pInflections.print(script);
				//// pInflections.print(" ");
				// pInflections.println();
			}
			rs.close();
		} catch (SQLException E) {
			E.printStackTrace();
			System.exit(1);
		} finally {
			releaseConnection();
			pInflections.close();
			System.out.println("******file is ready**********");
		}
	}

	public void getWholeNouns() {
		String dir = "C:\\Documents and Settings\\daliabo\\My Documents\\lexicon\\diffTests\\";
		FileOutputStream inflections; // declare a file output object
		try {

			// Create a new file output stream
			// connected to "myfile.txt"
			inflections = new FileOutputStream(dir + "wPrefix.txt");

			// Connect print stream to the output stream
			pInflections = new PrintStream(inflections);

		} catch (Exception e) {
			System.err.println("Error writing to file");
		}
		try {

			String sql = "  select undotted from " + " lexiconP.item where pos='wPrefix'";

			ResultSet rs = null;
			rs = getData(sql);
			// pInflections.print("num");
			// pInflections.print(" ");
			// pInflections.print("surface");
			// pInflections.print(" ");
			// pInflections.print("gender");
			// pInflections.print(" ");
			// pInflections.print("number");
			// pInflections.println();
			pInflections.println("*************************************");
			pInflections.println();
			while (rs.next()) {
				surface = rs.getString("undotted");
				// String dotted= rs.getString("dotted");
				// String p =rs.getString("p");
				// String type= rs.getString("type");
				// String definiteness= rs.getString("definiteness");
				// String type= rs.getString("basePronounType");
				// String surface= rs.getString("dotted");
				// String id= rs.getString("id");
				// gender = rs.getString("gender");
				// number = rs.getString("number");
				// feminine = rs.getString("feminine");
				// root = rs.getString("root");

				// root = rs.getString("root");
				// String inflectConstructS =
				// rs.getString("inflectConstructS");;
				// String inflectConstructP =
				// rs.getString("inflectConstructP");;
				// System.out.println(transliterated);
				// String gender = rs.getString("gender");
				// //String surface = Translate.Eng2Heb(transliterated);
				// //surface = apostropheHandling(surface);
				// String number = rs.getString("number");
				// String pos = rs.getString("pos");
				// String definiteness = rs.getString("baseDefinitness");
				try {
					// surface = URLDecoder.decode(surface, "UTF-8");
					// dotted = URLDecoder.decode(dotted, "UTF-8");
					// dottedPlural = URLDecoder.decode(dottedPlural, "UTF-8");
					// undotted = URLDecoder.decode(undotted, "UTF-8");
					surface = URLDecoder.decode(surface, "UTF-8");
					// dotted =URLDecoder.decode(dotted, "UTF-8");
				} catch (UnsupportedEncodingException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				// pInflections.print(i);

				// pInflections.print(" ");
				// pInflections.print(id);
				// pInflections.print(" ");

				// pInflections.print(" ");
				pInflections.print(surface);
				pInflections.print("	");
				// pInflections.print(dotted);
				// pInflections.print(" ");
				// pInflections.print(p );
				// pInflections.print(" ");

				// pInflections.print(dottedPlural);
				// pInflections.print(" ");
				// pInflections.print("�" +surface);
				// pInflections.print(" ");
				// pInflections.print("�" +surface);
				// pInflections.print(" ");
				// pInflections.print("�" +surface);
				// pInflections.print(" ");

				// pInflections.print(type);
				// pInflections.print(" ");
				// pInflections.print(number);
				// pInflections.print(" ");
				// pInflections.print(feminine);
				// pInflections.print(" ");
				// pInflections.print(root);
				// pInflections.print(" ");
				// pInflections.print(transliterated);
				// pInflections.print(" ");
				// pInflections.print(definiteness);
				// pInflections.print(root);
				// pInflections.print(" ");
				// pInflections.print(pos);

				// pInflections.print(" ");
				// pInflections.print(" ");
				// pInflections.print(number);
				// pInflections.print(" ");
				// pInflections.print(" ");
				// pInflections.print(person);
				// pInflections.print(" ");
				// pInflections.print(" ");
				// pInflections.print(inflectConstructS);
				// pInflections.print(" ");
				// pInflections.print(inflectConstructP);
				pInflections.println();
			}

			rs.close();
		} catch (SQLException E) {
			E.printStackTrace();
			System.exit(1);
		} finally {
			releaseConnection();
			pInflections.close();
			System.out.println("******file is ready**********");
		}

	}

	public void getWholeVerbs() {
		String dir = "C:\\Documents and Settings\\daliabo\\My Documents\\lexicon\\diffTests\\";
		FileOutputStream inflections; // declare a file output object
		try {

			// Create a new file output stream
			// connected to "myfile.txt"
			inflections = new FileOutputStream(dir + "xfstInput.txt");

			// Connect print stream to the output stream
			pInflections = new PrintStream(inflections);

		} catch (Exception e) {
			System.err.println("Error writing to file");
		}
		try {
			String sql = "select * from verb, item where item.id=verb.id ";
			String transliterated = "";
			ResultSet rs = null;
			rs = getData(sql);
			int i = 0;
			pInflections.print("num");
			pInflections.print("	");
			pInflections.print("surface");
			pInflections.print("	");
			pInflections.print("lexicon id");
			pInflections.println();
			pInflections.println("*************************************");
			pInflections.println();
			while (rs.next()) {
				i++;
				transliterated = rs.getString("transliterated");
				String id = rs.getString("id");
				String surface = Translate.Eng2Heb(transliterated);
				try {
					surface = URLDecoder.decode(surface, "UTF-8");
				} catch (UnsupportedEncodingException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				pInflections.print(i);
				pInflections.print("	");
				pInflections.print(surface);
				pInflections.print("	");
				pInflections.print(id);
				pInflections.println();
			}
			rs.close();
		} catch (SQLException E) {
			E.printStackTrace();
			System.exit(1);
		} finally {
			releaseConnection();
			pInflections.close();
			System.out.println("******file is ready**********");
		}
	}

	public List getWholeSurfaceInflections() {
		ArrayList result = new ArrayList();
		try {
			String sql = "select *, count(surface) from inflections group by surface having count(surface)>=1;";
			String surface = "";
			ResultSet rs = null;
			rs = getData(sql);
			while (rs.next()) {
				surface = rs.getString("surface");
				result.add(surface);
			}
			rs.close();
		} catch (SQLException E) {
			E.printStackTrace();
			System.exit(1);
		} finally {
			releaseConnection();
		}
		return result;
	}

	public boolean checkInflectionExistence(String baseLexiconPointer, String inflectedVerb, String tense, String PGN) {
		boolean exist = false;
		String sql = "SELECT * from inflections where baseLexiconPointer = " + "'" + baseLexiconPointer
				+ "' and transliterated ='" + inflectedVerb + "' and tense='" + tense + "' and PGN='" + PGN + "'";
		System.out.println("sql exist=" + sql);
		ResultSet rs = null;
		rs = getData(sql);
		try {
			while (rs.next()) {
				exist = true;
			}
			rs.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.exit(1);
		} finally {
			releaseConnection();
		}
		return exist;
	}

	private String apostropheHandling(String strIn) {
		String strOut = strIn;
		// case of single apostrophe
		int index1 = strIn.indexOf('\'');
		if (index1 != -1) {
			if (index1 == 1) {
				strOut = strIn.charAt(0) + "\\" + strIn.substring(index1);
				// System.out.println("strOut = " + strOut);
			} else {
				strOut = strIn.substring(0, index1) + "\\" + strIn.substring(index1);
				// System.out.println("strOut1 = " + strOut);
			}
			// case of two apostrophe
			int index2 = (strIn.substring(index1 + 1)).indexOf('\'');
			if (index2 != -1) {
				index2 = strOut.lastIndexOf('\'');
				// System.out.println("index2 = " + index2);
				strOut = strOut.substring(0, index2) + "\\" + strOut.substring(index2);
				// System.out.println("strOut2 = " + strOut);
			}
		} else
			strOut = strIn;
		return strOut;
	}

	/**
	 * @return Returns the baseBinyan.
	 */
	// public String getBaseBinyan() {
	// return baseBinyan;
	// }
	/**
	 * @param baseBinyan
	 *           The baseBinyan to set.
	 */
	// public void setBaseBinyan(String baseBinyan) {
	// this.baseBinyan = baseBinyan;
	// }
	/**
	 * @return Returns the baseConjunctionType.
	 */
	// public String getBaseConjunctionType() {
	// return baseConjunctionType;
	// }
	/**
	 * @param baseConjunctionType
	 *           The baseConjunctionType to set.
	 */
	// public void setBaseConjunctionType(String baseConjunctionType) {
	// this.baseConjunctionType = baseConjunctionType;
	// }
	/**
	 * @return Returns the baseGender.
	 */
	// public String getBaseGender() {
	// return baseGender;
	// }
	/**
	 * @param baseGender
	 *           The baseGender to set.
	 */

	// public void setBaseGender(String baseGender) {
	// this.baseGender = baseGender;
	// }
	/**
	 * @return Returns the baseLexiconPointer.
	 */
	public String getBaseLexiconPointer() {
		return baseLexiconPointer;
	}

	/**
	 * @param baseLexiconPointer
	 *           The baseLexiconPointer to set.
	 */
	public void setBaseLexiconPointer(String baseLexiconPointer) {
		this.baseLexiconPointer = baseLexiconPointer;
	}

	/**
	 * @return Returns the baseAlternatePointer.
	 */
	public String getBaseAlternatePointer() {
		return baseAlternatePointer;
	}

	/**
	 * @param baseAlternatePointer
	 *           The baseAlternatePointer to set.
	 */
	public void setBaseAlternatePointer(String baseAlternatePointer) {
		this.baseAlternatePointer = baseAlternatePointer;
	}

	/**
	 * @return Returns the baseNamedEntityType.
	 */
	// public String getBaseNamedEntityType() {
	// return baseNamedEntityType;
	// }
	/**
	 * @param baseNamedEntityType
	 *           The baseNamedEntityType to set.
	 */
	// public void setBaseNamedEntityType(String baseNamedEntityType) {
	// this.baseNamedEntityType = baseNamedEntityType;
	// }
	/**
	 * @return Returns the baseNumber.
	 */
	// public String getBaseNumber() {
	// return baseNumber;
	// }
	/**
	 * @param baseNumber
	 *           The baseNumber to set.
	 */
	// public void setBaseNumber(String baseNumber) {
	// this.baseNumber = baseNumber;
	// }
	/**
	 * @return Returns the basePerson.
	 */
	// public String getBasePerson() {
	// return basePerson;
	// }
	/**
	 * @param basePerson
	 *           The basePerson to set.
	 */

	// public void setBasePerson(String basePerson) {
	// this.basePerson = basePerson;
	// }
	/**
	 * @return Returns the basePos.
	 */
	public String getBasePos() {
		return basePos;
	}

	/**
	 * @param basePos
	 *           The basePos to set.
	 */
	public void setBasePos(String basePos) {
		this.basePos = basePos;
	}

	/**
	 * @return Returns the basePronounType.
	 */
	// public String getBasePronounType() {
	// return basePronounType;
	// }
	/**
	 * @param basePronounType
	 *           The basePronounType to set.
	 */
	// public void setBasePronounType(String basePronounType) {
	// this.basePronounType = basePronounType;
	// }
	/**
	 * @return Returns the basePunctuationType.
	 */
	// public String getBasePunctuationType() {
	// return basePunctuationType;
	// }
	/**
	 * @param basePunctuationType
	 *           The basePunctuationType to set.
	 */
	// public void setBasePunctuationType(String basePunctuationType) {
	// this.basePunctuationType = basePunctuationType;
	// }
	/**
	 * @return Returns the baseQuantifierType.
	 */
	// public String getBaseQuantifierType() {
	// return baseQuantifierType;
	// }
	/**
	 * @param baseQuantifierType
	 *           The baseQuantifierType to set.
	 */
	// public void setBaseQuantifierType(String baseQuantifierType) {
	// this.baseQuantifierType = baseQuantifierType;
	// }
	/**
	 * @return Returns the baseRoot.
	 */
	// public String getBaseRoot() {
	// return baseRoot;
	// }
	/**
	 * @param baseRoot
	 *           The baseRoot to set.
	 */
	// public void setBaseRoot(String baseRoot) {
	// this.baseRoot = baseRoot;
	// }
	/**
	 * @return Returns the baseStstus.
	 */
	// public String getBaseStstus() {
	// return baseStstus;
	// }
	/**
	 * @param baseStstus
	 *           The baseStstus to set.
	 */
	// public void setBaseStstus(String baseStstus) {
	// this.baseStstus = baseStstus;
	// }
	/**
	 * @return Returns the baseTense.
	 */
	// public String getBaseTense() {
	// return baseTense;
	// }
	/**
	 * @param baseTense
	 *           The baseTense to set.
	 */

	// public void setBaseTense(String baseTense) {
	// this.baseTense = baseTense;
	// }
	/**
	 * @return Returns the baseTransliteratedLItem.
	 */
	public String getBaseTransliteratedLItem() {
		return baseTransliteratedLItem;
	}

	/**
	 * @param baseTransliteratedLItem
	 *           The baseTransliteratedLItem to set.
	 */
	public void setBaseTransliteratedLItem(String baseTransliteratedLItem) {
		this.baseTransliteratedLItem = baseTransliteratedLItem;
	}

	/**
	 * @return Returns the comment.
	 */
	// public String getComment() {
	// return comment;
	// }
	/**
	 * @param comment
	 *           The comment to set.
	 */

	// public void setComment(String comment) {
	// this.comment = comment;
	// }
	/**
	 * @return Returns the suffixFunction.
	 */
	public String getSuffixFunction() {
		return suffixFunction;
	}

	/**
	 * @param suffixFunction
	 *           The suffixFunction to set.
	 */
	public void setSuffixFunction(String suffixFunction) {
		this.suffixFunction = suffixFunction;
	}

	/**
	 * @return Returns the suffixSurface.
	 */
	public String getSuffixSurface() {
		return suffixSurface;
	}

	/**
	 * @param suffixSurface
	 *           The suffixSurface to set.
	 */
	public void setSuffixSurface(String suffixSurface) {
		this.suffixSurface = suffixSurface;
	}

	/**
	 * @return Returns the surface.
	 */
	public String getSurface() {
		return surface;
	}

	/**
	 * @param surface
	 *           The surface to set.
	 */
	public void setSurface(String surface) {
		this.surface = surface;
	}

	/**
	 * @return Returns the transliterated.
	 */
	public String getTransliterated() {
		return transliterated;
	}

	/**
	 * @param transliterated
	 *           The transliterated to set.
	 */
	public void setTransliterated(String transliterated) {
		this.transliterated = transliterated;
	}

	/**
	 * @return Returns the baseId.
	 */
	// public int getBaseId() {
	// return baseId;
	// }
	/**
	 * @param baseId
	 *           The baseId to set.
	 */

	// public void setBaseId(int baseId) {
	// this.baseId = baseId;
	// }
	/**
	 * @return Returns the baseundottedLItem.
	 */
	public String getBaseundottedLItem() {
		return baseUndottedLItem;
	}

	/**
	 * @param baseundottedLItem
	 *           The baseundottedLItem to set.
	 */
	public void setBaseundottedLItem(String baseundottedLItem) {
		this.baseUndottedLItem = baseundottedLItem;
	}

	/**
	 * @param script
	 *           The script to set.
	 */
	public void setRegister(String register) {
		this.register = register;
	}

	/**
	 * @param suffixStatus
	 *           The suffixStatus to set.
	 */
	public void setSuffixStatus(String suffixStatus) {
		this.suffixStatus = suffixStatus;
	}

	public void setTable(String _table) {
	}

	/**
	 * @param pgn
	 *           The pGN to set.
	 */
	public void setPGN(String pgn) {
		PGN = pgn;
	}

	/**
	 * @param baseUndottedLItem
	 *           The baseUndottedLItem to set.
	 */
	public void setBaseUndottedLItem(String baseUndottedLItem) {
		this.baseUndottedLItem = baseUndottedLItem;
	}

	/**
	 * @param binyan
	 *           The binyan to set.
	 */
	public void setBinyan(String binyan) {
		this.binyan = binyan;
	}

	/**
	 * @param root
	 *           The root to set.
	 */
	public void setRoot(String root) {
		this.root = root;
	}

	/**
	 * @param tense
	 *           The tense to set.
	 */
	public void setTense(String tense) {
		this.tense = tense;
	}

	/**
	 * @param type
	 *           The type to set.
	 */
	public void setType(String type) {
		this.type = type;
	}

	/**
	 * @param baseGender
	 *           The baseGender to set.
	 */
	public void setBaseGender(String baseGender) {
		this.baseGender = baseGender;
	}

	/**
	 * @param baseNumber
	 *           The baseNumber to set.
	 */
	public void setBaseNumber(String baseNumber) {
		this.baseNumber = baseNumber;
	}

	/**
	 * @return Returns the baseGender.
	 */
	public String getBaseGender() {
		return baseGender;
	}

	/**
	 * @return Returns the baseNumber.
	 */
	public String getBaseNumber() {
		return baseNumber;
	}

	/**
	 * @return Returns the baseDefinitness.
	 */
	public String getBaseDefinitness() {
		return baseDefinitness;
	}

	/**
	 * @param baseDefinitness
	 *           The baseDefinitness to set.
	 */
	public void setBaseDefinitness(String baseDefinitness) {
		this.baseDefinitness = baseDefinitness;
	}

	/**
	 * @return Returns the basePerson.
	 */
	public String getBasePerson() {
		return basePerson;
	}

	/**
	 * @param basePerson
	 *           The basePerson to set.
	 */
	public void setBasePerson(String basePerson) {
		this.basePerson = basePerson;
	}

	/**
	 * @return Returns the dottedLexiconItem.
	 */
	public String getDottedLexiconItem() {
		return dottedLexiconItem;
	}

	/**
	 * @param dottedLexiconItem
	 *           The dottedLexiconItem to set.
	 */
	public void setDottedLexiconItem(String dottedLexiconItem) {
		this.dottedLexiconItem = dottedLexiconItem;
	}

	/**
	 * @return Returns the polarity.
	 */
	public String getPolarity() {
		return polarity;
	}

	/**
	 * @param polarity
	 *           The polarity to set.
	 */
	public void setPolarity(String polarity) {
		this.polarity = polarity;
	}

	/**
	 * @return Returns the subId.
	 */
	public int getSubId() {
		return subId;
	}

	/**
	 * @param subId
	 *           The subId to set.
	 */
	public void setSubId(int subId) {
		this.subId = subId;
	}

	/**
	 * @return Returns the multiWordLen.
	 */
	public int getMultiWordLen() {
		return multiWordLen;
	}

	/**
	 * @param multiWordLen
	 *           The multiWordLen to set.
	 */
	public void setMultiWordLen(int multiWordLen) {
		this.multiWordLen = multiWordLen;
	}

	/**
	 * @return Returns the acronymMeaning.
	 */
	public String getValue() {
		return value;
	}

	/**
	 * @param acronymMeaning
	 *           The acronymMeaning to set.
	 */
	public void setValue(String value) {
		this.value = value;
	}

	/**
	 * @return Returns the prefix.
	 */
	public String getPrefix() {
		return prefix;
	}

	/**
	 * @param prefix
	 *           The prefix to set.
	 */
	public void setPrefix(String prefix) {
		this.prefix = prefix;
	}

	/**
	 * @param string
	 */
	public String getSpelling() {
		return spelling;
	}

	/**
	 * @param string
	 */
	public void setSpelling(String spelling) {
		this.spelling = spelling;

	}

}
