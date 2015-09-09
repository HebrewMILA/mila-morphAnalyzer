package mila.lexicon.utils;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

import mila.lexicon.dbUtils.MWE1record;
import mila.lexicon.dbUtils.MWEinflectionsRecord;
import mila.lexicon.dbUtils.MWErecord;

/*
 * Created on 28/11/2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */

/**
 * @author daliabo
 * 
 *         use sort of unix to create dindlections.data and dprefixes.data sort
 *         inflections.data > dinflections.data sort prefixes.data >
 *         dprefixes.data TODO To change the template for this generated type
 *         comment go to Window - Preferences - Java - Code Style - Code
 *         Templates
 */
public class Load2memory {

	private static Inflections inflections = null;

	private static MWinflections mwinflections = null;

	private static MWE1records mwe1 = null;

	private static MWErecords mwe = null;
	private static MWErecords mwe2 = null;
	private static MWErecords mwe3 = null;
	private static MWErecords mwe4 = null;

	private static Prefixes prefixes = null;

	private static Gimatria gimatria = null;

	private static boolean sortFile = true;

	// ---------------------------------------------------------------------------------
	public static Gimatria loadGimatria(String gimatriaFile) {
		// System.out.println("(F) Load2memory:loadGimatria");
		int counter = 0;
		// we already know the number of gimatria entries - improves performance
		gimatria = new Gimatria(1044);
		FileInputStream fileInputStream = null;
		BufferedReader in = null;
		String line = "";
		String transliterated = "";
		String val = "";
		try {
			fileInputStream = new FileInputStream(gimatriaFile);
			in = new BufferedReader(new InputStreamReader(fileInputStream));
			while ((line = in.readLine()) != null) {
				StringTokenizer gimatriaFields = new StringTokenizer(line, "|");
				transliterated = gimatriaFields.nextToken();
				transliterated = transliterated.replaceAll("%22", "\"");
				val = gimatriaFields.nextToken();
				counter++;
				gimatria.put(transliterated, val);
			}
			in.close();
		} catch (Exception e) {
			System.err.println("gimatria file not found");
			System.err.println("Exiting...");
			System.exit(1);
		}
		return gimatria;
	}

	// -------------------------------------------------------------------------------------------------------
	public static Inflections loadInflections(String dinflectionsFile) {
		// System.out.println("(F) Load2memory:loadInflections()");
		inflections = new Inflections(840000, 1);
		FileInputStream fileInputStream = null;
		BufferedReader in = null;
		int counter = 0;

		try {
			fileInputStream = new FileInputStream(dinflectionsFile);
			in = new BufferedReader(new InputStreamReader(fileInputStream));
			String decodedInflectionRecord = "";
			String transliterated = "";
			ArrayList<String> chain = null;
			boolean first = true;
			String currentTransliterated = "";
			while ((decodedInflectionRecord = in.readLine()) != null) {
				StringTokenizer inflectionsFields = new StringTokenizer(decodedInflectionRecord, "|");
				transliterated = inflectionsFields.nextToken();
				counter++;

				// System.out.println("counter = " + counter);
				// System.out.println("transliterated =" + transliterated);
				if (!sortFile)
					inflections.put(transliterated, decodedInflectionRecord);
				else {
					if (first) {
						chain = new ArrayList<String>();
						first = false;
						chain.add(decodedInflectionRecord);
						currentTransliterated = transliterated;
					} else {
						if (currentTransliterated.equals(transliterated))
							chain.add(decodedInflectionRecord);
						else {
							inflections.sput(currentTransliterated, chain);
							chain = new ArrayList<String>();
							chain.add(decodedInflectionRecord);
							currentTransliterated = transliterated;
						}
					}
				}
			}
			// for the last one
			inflections.sput(currentTransliterated, chain);
			// System.out.println("load: End Loading Inflections file ");
			in.close();
		} catch (Exception e) {
			// e.printStackTrace();
			System.err.println("dinflections file not found");
			System.err.println("Exiting...");
			System.exit(1);
		}
		return inflections;
	}

	// -------------------------------------------------------------------------------------------------------
	public static MWE1records LoadMwe1Records(String dmwe1File) {
		// System.out.println("(F) Load2memory:LoadMwe1Records()");
		mwe1 = new MWE1records(5000, 1);
		FileInputStream fileInputStream = null;
		BufferedReader in = null;

		try {
			fileInputStream = new FileInputStream(dmwe1File);
			in = new BufferedReader(new InputStreamReader(fileInputStream));

			String decodedMwe1Record = "";

			String id = "";
			String transliterated = "";
			String surface = "";
			String pos = "";
			String consecutive = "";
			String type = "";

			while ((decodedMwe1Record = in.readLine()) != null) {
				MWE1record mwe1Rec = new MWE1record();
				StringTokenizer inflectionsFields = new StringTokenizer(decodedMwe1Record, "|"); // tokenize

				transliterated = inflectionsFields.nextToken(); // extract data
				id = inflectionsFields.nextToken(); // extract data
				surface = inflectionsFields.nextToken(); // extract data
				pos = inflectionsFields.nextToken(); // extract data
				consecutive = inflectionsFields.nextToken(); // extract data
				type = inflectionsFields.nextToken(); // extract data

				mwe1Rec.setTransliterated(transliterated); // create record
				mwe1Rec.setSurface(surface);
				mwe1Rec.setPos(pos);
				mwe1Rec.setId(id);
				mwe1Rec.setType(type);
				mwe1Rec.setConsecutive(consecutive);

				mwe1.put(transliterated, mwe1Rec); // add record to hashmap

				/*
				 * // checking the insertion went well ArrayList mweRecordChain
				 * = mwe1.get(transliterated); System.out.println(
				 * "(F) Load2memory:LoadMwe1Records(): transliterated = " +
				 * transliterated); Iterator<MWE1record> itr =
				 * mweRecordChain.iterator(); while (itr.hasNext()) {
				 * System.out. println(
				 * "(F) Load2memory:LoadMwe1Records():            pos = " +
				 * ((MWE1record)itr.next()).getPos()); }
				 */
			}

			in.close();
		} catch (Exception e) {
			e.printStackTrace();
			System.err.println("dmwe1 file not found");
			System.err.println("Exiting...");
			System.exit(1);
		}
		return mwe1;
	}

	// -------------------------------------------------------------------------------------------------------
	/**
	 * loads the mwe 2 3 and 4 to a MWErecords structure
	 * 
	 * @param dmweFile
	 * @return
	 */
	public static MWErecords LoadMwe3Records(String dmweFile) {
		MWErecords mwe3 = new MWErecords(5000, 1);
		// mwe3 = new MWErecords(5000, 1);
		FileInputStream fileInputStream = null;
		BufferedReader in = null;

		try {
			fileInputStream = new FileInputStream(dmweFile);
			in = new BufferedReader(new InputStreamReader(fileInputStream));

			String decodedMweRecord = "";

			String aid = "";
			String id = "";
			String formerItemId = "";
			String surface = "";
			String transliterated = "";
			String consecutive = "";
			String lexiconId = "";
			String transliteratedLexiconItem = "";
			String dottedLexiconItem = "";
			String undottedLexiconItem = "";
			String mwTransliterated = "";
			String mwUndotted = "";
			String PGN = "";
			String spelling = "";
			String register = "";
			String gender = "";
			String number = "";
			String definiteness = "";

			while ((decodedMweRecord = in.readLine()) != null) {
				MWErecord mweRec = new MWErecord();
				// StringTokenizer inflectionsFields = new
				// StringTokenizer(decodedMweRecord, "|"); // tokenize
				String[] array = decodedMweRecord.split("\\|", -1); // cause
																	// stringtokenizer
																	// cant get
																	// empty
																	// tokens
				int index = 0;

				transliterated = array[index++]; // extract data
				aid = array[index++]; // extract data
				id = array[index++]; // extract data
				formerItemId = array[index++]; // extract data
				surface = array[index++]; // extract data
				consecutive = array[index++]; // extract data
				lexiconId = array[index++]; // extract data
				transliteratedLexiconItem = array[index++]; // extract data
				dottedLexiconItem = array[index++]; // extract data
				undottedLexiconItem = array[index++]; // extract data
				mwTransliterated = array[index++]; // extract data
				mwUndotted = array[index++]; // extract data
				PGN = array[index++]; // extract data
				spelling = array[index++]; // extract data
				register = array[index++]; // extract data
				gender = array[index++]; // extract data
				number = array[index++]; // extract data
				definiteness = array[index++]; // extract data
				/*
				 * transliterated = inflectionsFields.nextToken(); // extract
				 * data aid = inflectionsFields.nextToken(); // extract data id
				 * = inflectionsFields.nextToken(); // extract data formerItemId
				 * = inflectionsFields.nextToken(); // extract data surface =
				 * inflectionsFields.nextToken(); // extract data consecutive =
				 * inflectionsFields.nextToken(); // extract data lexiconId =
				 * inflectionsFields.nextToken(); // extract data
				 * transliteratedLexiconItem = inflectionsFields.nextToken(); //
				 * extract data dottedLexiconItem =
				 * inflectionsFields.nextToken(); // extract data
				 * undottedLexiconItem = inflectionsFields.nextToken(); //
				 * extract data mwTransliterated =
				 * inflectionsFields.nextToken(); // extract data mwUndotted =
				 * inflectionsFields.nextToken(); // extract data PGN =
				 * inflectionsFields.nextToken(); // extract data spelling =
				 * inflectionsFields.nextToken(); // extract data register =
				 * inflectionsFields.nextToken(); // extract data gender =
				 * inflectionsFields.nextToken(); // extract data number =
				 * inflectionsFields.nextToken(); // extract data definiteness =
				 * inflectionsFields.nextToken(); // extract data
				 */
				mweRec.setTransliterated(transliterated); // create record
				mweRec.setAid(aid);
				mweRec.setId(id);
				mweRec.setFormerItemId(formerItemId);
				mweRec.setSurface(surface);
				mweRec.setConsecutive(consecutive);
				mweRec.setLexiconId(lexiconId);
				mweRec.setTransliteratedLexiconItem(transliteratedLexiconItem);
				mweRec.setDottedLexiconItem(dottedLexiconItem);
				mweRec.setUndottedLexiconItem(undottedLexiconItem);
				mweRec.setMwTransliterated(mwTransliterated);
				mweRec.setMwUndotted(mwUndotted);
				mweRec.setPGN(PGN);
				mweRec.setSpelling(spelling);
				mweRec.setRegister(register);
				mweRec.setGender(gender);
				mweRec.setNumber(number);
				mweRec.setDefiniteness(definiteness);

				mwe3.put(transliterated, mweRec); // add record to hashmap

				/*
				 * // checking the insertion went well ArrayList mweRecordChain
				 * = mwe.get(transliterated); System.out.println(
				 * "(F) Load2memory:LoadMweRecords(): transliterated = " +
				 * transliterated); Iterator<MWErecord> itr =
				 * mweRecordChain.iterator(); while (itr.hasNext()) {
				 * System.out. println(
				 * "(F) Load2memory:LoadMweRecords():            surface = " +
				 * ((MWErecord)itr.next()).getSurface()); }
				 */
			}

			in.close();
		} catch (Exception e) {
			e.printStackTrace();
			System.err.println("dmwe file not found");
			System.err.println("Exiting...");
			System.exit(1);
		}

		return mwe3;
	}

	// -------------------------------------------------------------------------------------------------------
	/**
	 * loads the mwe 2 3 and 4 to a MWErecords structure
	 * 
	 * @param dmweFile
	 * @return
	 */
	public static MWErecords LoadMwe4Records(String dmweFile) {
		// MWErecords mwe = new MWErecords(5000, 1);
		mwe4 = new MWErecords(5000, 1);
		FileInputStream fileInputStream = null;
		BufferedReader in = null;

		try {
			fileInputStream = new FileInputStream(dmweFile);
			in = new BufferedReader(new InputStreamReader(fileInputStream));

			String decodedMweRecord = "";

			String aid = "";
			String id = "";
			String formerItemId = "";
			String surface = "";
			String transliterated = "";
			String consecutive = "";
			String lexiconId = "";
			String transliteratedLexiconItem = "";
			String dottedLexiconItem = "";
			String undottedLexiconItem = "";
			String mwTransliterated = "";
			String mwUndotted = "";
			String PGN = "";
			String spelling = "";
			String register = "";
			String gender = "";
			String number = "";
			String definiteness = "";

			while ((decodedMweRecord = in.readLine()) != null) {
				MWErecord mweRec = new MWErecord();
				// StringTokenizer inflectionsFields = new
				// StringTokenizer(decodedMweRecord, "|"); // tokenize
				String[] array = decodedMweRecord.split("\\|", -1); // cause
																	// stringtokenizer
																	// cant get
																	// empty
																	// tokens
				int index = 0;

				transliterated = array[index++]; // extract data
				aid = array[index++]; // extract data
				id = array[index++]; // extract data
				formerItemId = array[index++]; // extract data
				surface = array[index++]; // extract data
				consecutive = array[index++]; // extract data
				lexiconId = array[index++]; // extract data
				transliteratedLexiconItem = array[index++]; // extract data
				dottedLexiconItem = array[index++]; // extract data
				undottedLexiconItem = array[index++]; // extract data
				mwTransliterated = array[index++]; // extract data
				mwUndotted = array[index++]; // extract data
				PGN = array[index++]; // extract data
				spelling = array[index++]; // extract data
				register = array[index++]; // extract data
				gender = array[index++]; // extract data
				number = array[index++]; // extract data
				definiteness = array[index++]; // extract data
				/*
				 * transliterated = inflectionsFields.nextToken(); // extract
				 * data aid = inflectionsFields.nextToken(); // extract data id
				 * = inflectionsFields.nextToken(); // extract data formerItemId
				 * = inflectionsFields.nextToken(); // extract data surface =
				 * inflectionsFields.nextToken(); // extract data consecutive =
				 * inflectionsFields.nextToken(); // extract data lexiconId =
				 * inflectionsFields.nextToken(); // extract data
				 * transliteratedLexiconItem = inflectionsFields.nextToken(); //
				 * extract data dottedLexiconItem =
				 * inflectionsFields.nextToken(); // extract data
				 * undottedLexiconItem = inflectionsFields.nextToken(); //
				 * extract data mwTransliterated =
				 * inflectionsFields.nextToken(); // extract data mwUndotted =
				 * inflectionsFields.nextToken(); // extract data PGN =
				 * inflectionsFields.nextToken(); // extract data spelling =
				 * inflectionsFields.nextToken(); // extract data register =
				 * inflectionsFields.nextToken(); // extract data gender =
				 * inflectionsFields.nextToken(); // extract data number =
				 * inflectionsFields.nextToken(); // extract data definiteness =
				 * inflectionsFields.nextToken(); // extract data
				 */
				mweRec.setTransliterated(transliterated); // create record
				mweRec.setAid(aid);
				mweRec.setId(id);
				mweRec.setFormerItemId(formerItemId);
				mweRec.setSurface(surface);
				mweRec.setConsecutive(consecutive);
				mweRec.setLexiconId(lexiconId);
				mweRec.setTransliteratedLexiconItem(transliteratedLexiconItem);
				mweRec.setDottedLexiconItem(dottedLexiconItem);
				mweRec.setUndottedLexiconItem(undottedLexiconItem);
				mweRec.setMwTransliterated(mwTransliterated);
				mweRec.setMwUndotted(mwUndotted);
				mweRec.setPGN(PGN);
				mweRec.setSpelling(spelling);
				mweRec.setRegister(register);
				mweRec.setGender(gender);
				mweRec.setNumber(number);
				mweRec.setDefiniteness(definiteness);

				mwe4.put(transliterated, mweRec); // add record to hashmap

				/*
				 * // checking the insertion went well ArrayList mweRecordChain
				 * = mwe.get(transliterated); System.out.println(
				 * "(F) Load2memory:LoadMweRecords(): transliterated = " +
				 * transliterated); Iterator<MWErecord> itr =
				 * mweRecordChain.iterator(); while (itr.hasNext()) {
				 * System.out. println(
				 * "(F) Load2memory:LoadMweRecords():            surface = " +
				 * ((MWErecord)itr.next()).getSurface()); }
				 */
			}

			in.close();
		} catch (Exception e) {
			e.printStackTrace();
			System.err.println("dmwe file not found");
			System.err.println("Exiting...");
			System.exit(1);
		}

		return mwe4;
	}

	// -------------------------------------------------------------------------------------------------------
	/**
	 * loads the mwe 2 3 and 4 to a MWErecords structure
	 * 
	 * @param dmweFile
	 * @return
	 */
	public static MWErecords LoadMweRecords(String dmweFile, int tableNum) {
		// System.out.println("(F) Load2memory:LoadMwe2Records()");
		// MWErecords mwe = new MWErecords(5000, 1);
		switch (tableNum) {
		case 2: {
			mwe2 = new MWErecords(5000, 1);
			break;
		}
		case 3: {
			mwe3 = new MWErecords(5000, 1);
			break;
		}
		case 4: {
			mwe4 = new MWErecords(5000, 1);
			break;
		}
		}

		FileInputStream fileInputStream = null;
		BufferedReader in = null;

		try {
			fileInputStream = new FileInputStream(dmweFile);
			in = new BufferedReader(new InputStreamReader(fileInputStream));

			String decodedMweRecord = "";

			String aid = "";
			String id = "";
			String formerItemId = "";
			String surface = "";
			String transliterated = "";
			String consecutive = "";
			String lexiconId = "";
			String transliteratedLexiconItem = "";
			String dottedLexiconItem = "";
			String undottedLexiconItem = "";
			String mwTransliterated = "";
			String mwUndotted = "";
			String PGN = "";
			String spelling = "";
			String register = "";
			String gender = "";
			String number = "";
			String definiteness = "";

			while ((decodedMweRecord = in.readLine()) != null) {
				MWErecord mweRec = new MWErecord();
				// StringTokenizer inflectionsFields = new
				// StringTokenizer(decodedMweRecord, "|"); // tokenize
				String[] array = decodedMweRecord.split("\\|", -1); // cause
																	// stringtokenizer
																	// cant get
																	// empty
																	// tokens
				int index = 0;

				transliterated = array[index++]; // extract data
				aid = array[index++]; // extract data
				id = array[index++]; // extract data
				formerItemId = array[index++]; // extract data
				surface = array[index++]; // extract data
				consecutive = array[index++]; // extract data
				lexiconId = array[index++]; // extract data
				transliteratedLexiconItem = array[index++]; // extract data
				dottedLexiconItem = array[index++]; // extract data
				undottedLexiconItem = array[index++]; // extract data
				mwTransliterated = array[index++]; // extract data
				mwUndotted = array[index++]; // extract data
				PGN = array[index++]; // extract data
				spelling = array[index++]; // extract data
				register = array[index++]; // extract data
				gender = array[index++]; // extract data
				number = array[index++]; // extract data
				definiteness = array[index++]; // extract data
				/*
				 * transliterated = inflectionsFields.nextToken(); // extract
				 * data aid = inflectionsFields.nextToken(); // extract data id
				 * = inflectionsFields.nextToken(); // extract data formerItemId
				 * = inflectionsFields.nextToken(); // extract data surface =
				 * inflectionsFields.nextToken(); // extract data consecutive =
				 * inflectionsFields.nextToken(); // extract data lexiconId =
				 * inflectionsFields.nextToken(); // extract data
				 * transliteratedLexiconItem = inflectionsFields.nextToken(); //
				 * extract data dottedLexiconItem =
				 * inflectionsFields.nextToken(); // extract data
				 * undottedLexiconItem = inflectionsFields.nextToken(); //
				 * extract data mwTransliterated =
				 * inflectionsFields.nextToken(); // extract data mwUndotted =
				 * inflectionsFields.nextToken(); // extract data PGN =
				 * inflectionsFields.nextToken(); // extract data spelling =
				 * inflectionsFields.nextToken(); // extract data register =
				 * inflectionsFields.nextToken(); // extract data gender =
				 * inflectionsFields.nextToken(); // extract data number =
				 * inflectionsFields.nextToken(); // extract data definiteness =
				 * inflectionsFields.nextToken(); // extract data
				 */
				mweRec.setTransliterated(transliterated); // create record
				mweRec.setAid(aid);
				mweRec.setId(id);
				mweRec.setFormerItemId(formerItemId);
				mweRec.setSurface(surface);
				mweRec.setConsecutive(consecutive);
				mweRec.setLexiconId(lexiconId);
				mweRec.setTransliteratedLexiconItem(transliteratedLexiconItem);
				mweRec.setDottedLexiconItem(dottedLexiconItem);
				mweRec.setUndottedLexiconItem(undottedLexiconItem);
				mweRec.setMwTransliterated(mwTransliterated);
				mweRec.setMwUndotted(mwUndotted);
				mweRec.setPGN(PGN);
				mweRec.setSpelling(spelling);
				mweRec.setRegister(register);
				mweRec.setGender(gender);
				mweRec.setNumber(number);
				mweRec.setDefiniteness(definiteness);

				switch (tableNum) {
				case 2: {
					mwe2.put(transliterated, mweRec); // add record to hashmap
					break;
				}
				case 3: {
					mwe3.put(transliterated, mweRec); // add record to hashmap
					break;
				}
				case 4: {
					mwe4.put(transliterated, mweRec); // add record to hashmap
					break;
				}
				}

				/*
				 * // checking the insertion went well ArrayList mweRecordChain
				 * = mwe2.get(transliterated); System.out.println(
				 * "(F) Load2memory:LoadMwe2Records(): transliterated = " +
				 * transliterated); Iterator<MWErecord> itr =
				 * mweRecordChain.iterator(); while (itr.hasNext()) {
				 * System.out. println(
				 * "(F) Load2memory:LoadMweRecords():            surface = " +
				 * ((MWErecord)itr.next()).getSurface()); }
				 */
			}

			in.close();
		} catch (Exception e) {
			e.printStackTrace();
			System.err.println("dmwe file not found");
			System.err.println("Exiting...");
			System.exit(1);
		}
		switch (tableNum) {
		case 2: {
			return mwe2;
		}
		case 3: {
			return mwe3;
		}
		case 4: {
			return mwe4;
		}
		}

		return mwe2;
	}

	// -------------------------------------------------------------------------------------------------------
	public static MWinflections LoadMwInflections(String dmwinflectionsFile) {
		// System.out.println("(F) Load2memory:LoadMwInflections()");
		mwinflections = new MWinflections(5000, 1);
		FileInputStream fileInputStream = null;
		BufferedReader in = null;

		try {
			fileInputStream = new FileInputStream(dmwinflectionsFile);
			in = new BufferedReader(new InputStreamReader(fileInputStream));
			String decodedMwinflectionRecord = "";
			String transliterated = "";
			String surface = "";
			String pos = "";
			String mweId = "";
			String type = "";
			Boolean prefix = false;

			while ((decodedMwinflectionRecord = in.readLine()) != null) {
				MWEinflectionsRecord mwInfRec = new MWEinflectionsRecord();
				StringTokenizer inflectionsFields = new StringTokenizer(decodedMwinflectionRecord, "|"); // tokenize
				transliterated = inflectionsFields.nextToken(); // extract data
				// System.out.println("(F) Load2memory:LoadMwInflections():
				// transliterated = "
				// + transliterated);
				surface = inflectionsFields.nextToken(); // extract data
				pos = inflectionsFields.nextToken(); // extract data
				mweId = inflectionsFields.nextToken(); // extract data
				type = inflectionsFields.nextToken(); // extract data
				prefix = (inflectionsFields.nextToken()).equals("0") ? false : true; // extract
																						// data

				mwInfRec.setTransliterated(transliterated); // create record
				mwInfRec.setSurface(surface);
				mwInfRec.setPos(pos);
				mwInfRec.setMweId(mweId);
				mwInfRec.setType(type);
				mwInfRec.setPrefix(prefix);

				mwinflections.put(transliterated, mwInfRec); // add record to
																// hashmap

				/*
				 * // checking the insertion went well ArrayList
				 * mwinflectRecordChain = mwinflections.get(transliterated);
				 * System.out.println(
				 * "(F) Load2memory:LoadMwInflections(): transliterated = " +
				 * transliterated); Iterator<MWEinflectionsRecord> itr =
				 * mwinflectRecordChain.iterator(); while (itr.hasNext()) {
				 * System.out.println(
				 * "(F) Load2memory:LoadMwInflections():            pos = " +
				 * ((MWEinflectionsRecord)itr.next()).getPos()); }
				 */
			}

			in.close();
		} catch (Exception e) {
			e.printStackTrace();
			System.err.println("dmwinflections file not found");
			System.err.println("Exiting...");
			System.exit(1);
		}
		return mwinflections;
	}

	// ---------------------------------------------------------------------------------
	public static Prefixes loadPrefixes(String dprefixesFile) {
		// System.out.println("(F) Load2memory:loadPrefixes");
		int counter = 0;
		prefixes = new Prefixes(174);
		FileInputStream fileInputStream = null;
		BufferedReader in = null;
		try {
			fileInputStream = new FileInputStream(dprefixesFile);
			in = new BufferedReader(new InputStreamReader(fileInputStream));
			String decodedPrefixesRecord = "";
			String prefix = "";
			ArrayList<String> chain = null;
			boolean first = true;
			String currentPrefix = "";
			while ((decodedPrefixesRecord = in.readLine()) != null) {
				StringTokenizer inflectionsFields = new StringTokenizer(decodedPrefixesRecord, "|");
				prefix = inflectionsFields.nextToken();
				counter++;
				// System.out.println("counter = " + counter);
				// System.out.println("prefix =" + prefix);

				if (!sortFile)
					prefixes.put(prefix, decodedPrefixesRecord);
				else {
					if (first) {
						chain = new ArrayList<String>();
						first = false;
						chain.add(decodedPrefixesRecord);
						currentPrefix = prefix;
					} else {
						if (currentPrefix.equals(prefix))
							chain.add(decodedPrefixesRecord);
						else {
							prefixes.sput(currentPrefix, chain);
							chain = new ArrayList<String>();
							chain.add(decodedPrefixesRecord);
							currentPrefix = prefix;
						}
					}
				}
			}
			// for the last one
			prefixes.sput(currentPrefix, chain);
			// System.out.println("load: End Loading Prefixes file ");
			in.close();
		} catch (Exception e) {
			System.err.println("dprefixes file not found");
			System.err.println("Exiting...");
			System.exit(1);
		}
		return prefixes;
	}
}
