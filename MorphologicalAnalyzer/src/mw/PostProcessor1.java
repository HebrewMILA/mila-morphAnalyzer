package mw;

import generated.AnalysisType;
import generated.ArticleType;
import generated.BaseType;
import generated.MWEType;
import generated.ObjectFactory;
import generated.ParagraphType;
import generated.PrefixType;
import generated.ProperNameType;
import generated.SentenceType;
import generated.SuffixType;
import generated.TokenType;
import generated.UnknownType;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Vector;

import javax.xml.bind.JAXBException;

import lexicon.dbUtils.Connected;
import lexicon.dbUtils.MWE1record;
import lexicon.dbUtils.MWErecord;
import lexicon.utils.StringUtils;
import lexicon.utils.Translate;
import dataStructures.CustomResult;

public class PostProcessor1 extends Connected {
	public static void main(String[] args) throws Exception {
		PostProcessor1 postProcessor = new PostProcessor1();
		String inputFile = "C:\\Documents and Settings\\daliabo\\My Documents\\lexicon\\diffTests\\shulyOutputFolder\\90.xml";
		String outputFile = "C:\\Documents and Settings\\daliabo\\My Documents\\lexicon\\diffTests\\shulyCleanFiles\\90.xml";
		postProcessor.process(inputFile, outputFile);

	}

	boolean useDataFiles = false; // like webFlag = true
	boolean removeAnalysisFlag = false;
	boolean inMultyWord = false;
	int candidateForRemove = -1;
	String multiWord = "";
	Vector vec = new Vector();

	int lastMWTokenIndex = -1;

	public PostProcessor1() // and an empty one for deafult
	{
	}

	public PostProcessor1(boolean _useDataFiles) {
		useDataFiles = _useDataFiles;
	}

	public void arrangeAnalysisIdAfterRemove(TokenType token) {
		List analysisList = token.getAnalysis();
		int analysisListSize = analysisList.size();
		for (int i = 0; i < analysisListSize; i++) {
			AnalysisType analysis = (AnalysisType) analysisList.get(i);
			int newId = i + 1;
			analysis.setId(String.valueOf(newId));
			token.getAnalysis().set(i, analysis);

		}

	}

	// -----------------------------------------------------------------------------------------------------------------------------------
	public boolean checkBackwords(String id, String expectedPrevId,
			String prevId, String transliterated,
			ArrayList prevTransliteratedList, AnalysisType analysis,
			TokenType prevToken, TokenType token)
			throws UnsupportedEncodingException, JAXBException {
		// System.out.println("\n(F) PostProcessor1:checkBackwords(id = "+id+",expecedPrevId = "+expectedPrevId+",prevId = "+prevId+","+transliterated+")");
		boolean rt = false;
		int prevTransliteratedListSize = prevTransliteratedList.size();
		int intId = Integer.parseInt(id);
		if (intId > prevTransliteratedListSize)
			return rt;

		String selectSql = "";
		String lexiconId = "-1";
		String dottedLexiconItem = "";
		String undottedLexiconItem = "";
		String pos = "";
		String type = "";
		String transliteratedLexiconItem = "";
		transliterated = transliterated.replaceAll("'", "\\\\'");
		String PGN = "";
		String lastPGN = "";
		String spelling = "";
		String lastSpelling = "";
		String register = "";
		String gender = "";
		String number = "";
		String mwTransliterated = "";
		String mwUndotted = "";
		ObjectFactory objFactory = null;
		AnalysisType newAnalysis = null;
		String lastLexiconId = "";
		String phraze = "";
		String prevTransliterated = "";
		String definiteness = "";

		int resultSetSize = 0;

		for (int prevTransliteratedListIndex = prevTransliteratedListSize - 2; prevTransliteratedListIndex >= 0; prevTransliteratedListIndex--) {
			prevTransliterated = (String) prevTransliteratedList
					.get(prevTransliteratedListIndex);
			prevTransliterated = prevTransliterated.replaceAll("'", "\\\\'");

			ArrayList<CustomResult> customResultSet = new ArrayList<CustomResult>(); // the
																						// result
																						// set

			if (expectedPrevId.equals("1")) {
				selectSql = " select mwe2.spelling, mwe2.register, mwe2.gender, mwe2.number,mwe2.definiteness,  mwe2.mwTransliterated,  mwe2.mwUndotted, mwe"
						+ id
						+ ".lexiconId,  mwe"
						+ id
						+ ".undottedLexiconItem ,mwe"
						+ id
						+ ".PGN, mwe"
						+ id
						+ ".dottedLexiconItem, mwe"
						+ id
						+ ".transliteratedLexiconItem, mwe1"
						+ ".pos,  mwe1.type"
						+ "  from  mwe"
						+ id
						+ ",  mwe1 where  mwe"
						+ id
						+ ".transliterated='"
						+ transliterated
						+ "' "
						+ "and mwe1.transliterated='"
						+ prevTransliterated
						+ "' and mwe"
						+ id
						+ ".formerItemId=mwe1.id";

				// #####################################
				// this part replaces the mysql part by using data filed insted
				// and simulating the querys
				ArrayList<MWE1record> result = MWE1data
						.getMWE1records(prevTransliterated); // get first
				ArrayList<MWErecord> result2 = MWEdata.getMWErecords(
						transliterated, 2); // get 2nd

				Iterator<MWE1record> itr = result.iterator();
				while (itr.hasNext()) // goind over first list and finding match
										// in 2nd
				{
					MWE1record me1rec = itr.next();
					// System.out.println("(F) PostProcessor1:checkBackwords():  pos = "
					// + me1rec.getPos());
					Iterator<MWErecord> itr2 = result2.iterator();
					while (itr2.hasNext()) {
						MWErecord mwerec = itr2.next();
						// System.out.println("me1rec.getId() = " +
						// me1rec.getId() + " mwerec.getFormerItemId() " +
						// mwerec.getFormerItemId());
						if (me1rec.getId().equals(mwerec.getFormerItemId())) // if
																				// they
																				// have
																				// the
																				// same
																				// id
																				// then
																				// we
																				// have
																				// a
																				// match
						{
							// System.out.println("(F) PostProcessor1:checkBackwords(): found MATCH  trans = "
							// + me1rec.getTransliterated());
							CustomResult cRes = new CustomResult();
							cRes.LoadRecord(me1rec, mwerec); // load values
							customResultSet.add(cRes); // add to result set
						}
					}
				}

				// System.out.println("Printing CustomResultSet size = " +
				// customResultSet.size() );
				Iterator<CustomResult> itr3 = customResultSet.iterator();
				while (itr3.hasNext()) {
					itr3.next();
					// ((CustomResult)itr3.next()).Print();
				}

			}

			else if (expectedPrevId.equals("2")) {

				// System.out.println("expectedPrevId.equals(2) <===================");

				// #####################################
				String firstWord = ((String) prevTransliteratedList
						.get(prevTransliteratedListSize - 3)).replaceAll("'",
						"\\\\'");

				phraze = firstWord
						+ " "
						+ ((String) prevTransliteratedList
								.get(prevTransliteratedListSize - 2))
								.replaceAll("'", "\\\\'")
						+ " "
						+ ((String) prevTransliteratedList
								.get(prevTransliteratedListSize - 1))
								.replaceAll("'", "\\\\'");

				selectSql = " select mwe3.PGN, mwe3.spelling, mwe3.register ,  mwe3.gender, mwe3.number ,mwe3.definiteness,  mwe3.mwTransliterated,  mwe3.mwUndotted, mwe"
						+ id
						+ ".lexiconId, mwe"
						+ id
						+ ".undottedLexiconItem ,"
						+ "mwe"
						+ id
						+ ".dottedLexiconItem, mwe"
						+ id
						+ ".transliteratedLexiconItem, mwe1.type, mwe1.pos"
						+ "  from  mwe"
						+ id
						+ ",  mwe"
						+ expectedPrevId
						+ ", mwe1 where  mwe3.mwTransliterated='" // where
						+ phraze
						+ "' and mwe"
						+ id
						+ ".transliterated='"
						+ transliterated
						+ "' "
						+ "and mwe"
						+ expectedPrevId
						+ ".transliterated='"
						+ prevTransliterated
						+ "' and mwe"
						+ id
						+ ".formerItemId=mwe"
						+ expectedPrevId + ".aid and mwe2.formerItemId=mwe1.id";

				// System.out.println("#######################" + firstWord);
				// System.out.println("#######################" + phraze);

				// this part replaces the mysql part by using data filed insted
				// and simulating the querys

				ArrayList<MWE1record> result = MWE1data
						.getMWE1records(firstWord); // get first
				ArrayList<MWErecord> result2 = MWEdata.getMWErecords(
						prevTransliterated, 2); // get 2nd
				ArrayList<MWErecord> result3 = MWEdata.getMWErecords(
						transliterated, 3); // get 3nd

				Iterator<MWErecord> itr = result3.iterator();
				while (itr.hasNext()) // goind over first list and finding match
										// in 3rd
				{
					MWErecord mwe3rec = itr.next();
					if (mwe3rec.getMwTransliterated().equals(phraze)
							&& (mwe3rec.getTransliterated()
									.equals(transliterated)))
					// MwTransliterated == phraze && mwe3.transliterated =
					// transliterated
					{
						// System.out.println("mwe3rec.getMwTransliterated() = "
						// + mwe3rec.getMwTransliterated()+" ?= "+phraze);
						// System.out.println("mwe3rec.getTransliterated() = " +
						// mwe3rec.getTransliterated()+" ?= " + transliterated);
						Iterator<MWErecord> itr2 = result2.iterator();
						while (itr2.hasNext()) {
							MWErecord mwe2rec = itr2.next();
							// System.out.println("me1rec.getId() = " +
							// me1rec.getId() + " mwerec.getFormerItemId() " +
							// mwerec.getFormerItemId());
							if (mwe2rec.getTransliterated().equals(
									prevTransliterated)
									&& mwe2rec.getAid().equals(
											mwe3rec.getFormerItemId())) // if
																		// they
																		// have
																		// the
																		// same
																		// id
																		// then
																		// we
																		// have
																		// a
																		// match
							{
								// System.out.println("mwe2rec.getTransliterated() = "
								// + mwe2rec.getTransliterated()+" ?= " +
								// prevTransliterated);

								Iterator<MWE1record> itr1 = result.iterator();
								while (itr1.hasNext()) {
									MWE1record mwe1rec = itr1.next();
									if (mwe1rec.getId().equals(
											mwe2rec.getFormerItemId())) {
										// System.out.println("(F) PostProcessor1:checkBackwords(): found MATCH  trans = "
										// + mwe1rec.getTransliterated());
										CustomResult cRes = new CustomResult();
										cRes.LoadRecord(mwe1rec, mwe3rec); // load
																			// values
										customResultSet.add(cRes); // add to
																	// result
																	// set
									}
								}
							}
						}
					}
				}
				resultSetSize = customResultSet.size();
				// System.out.println("Printing CustomResultSet size = " +
				// resultSetSize );

			} else if (expectedPrevId.equals("3")) {

				// ############################################################################

				String firstWord = ((String) prevTransliteratedList
						.get(prevTransliteratedListSize - 4)).replaceAll("'",
						"\\\\'");
				String secondWord = ((String) prevTransliteratedList
						.get(prevTransliteratedListSize - 3)).replaceAll("'",
						"\\\\'");

				phraze = firstWord
						+ " "
						+ secondWord
						+ " "
						+ ((String) prevTransliteratedList
								.get(prevTransliteratedListSize - 2))
								.replaceAll("'", "\\\\'")
						+ " "
						+ ((String) prevTransliteratedList
								.get(prevTransliteratedListSize - 1))
								.replaceAll("'", "\\\\'");
				// System.out.println("*********************" + phraze);

				selectSql = " select mwe4.PGN, mwe4.spelling, mwe4.register  , mwe4.gender, mwe4.number, mwe4.mwTransliterated,  mwe4.definiteness, mwe4.mwUndotted, mwe"
						+ id
						+ ".lexiconId, mwe"
						+ id
						+ ".undottedLexiconItem ,"
						+ "mwe"
						+ id
						+ ".dottedLexiconItem, mwe"
						+ id
						+ ".transliteratedLexiconItem, mwe1.type, mwe1.pos"
						+ "  from  mwe"
						+ id
						+ ",  mwe"
						+ expectedPrevId
						+ ", mwe2 , mwe1 where mwe4.mwTransliterated='"
						+ phraze
						+ "' and mwe"
						+ id
						+ ".transliterated='"
						+ transliterated
						+ "' "
						+ "and mwe"
						+ expectedPrevId
						+ ".transliterated='"
						+ prevTransliterated
						+ "' and mwe"
						+ id
						+ ".formerItemId=mwe"
						+ expectedPrevId
						+ ".aid and "
						+ "mwe3.formerItemId=mwe2.aid and mwe2.formerItemId=mwe1.id";

				ArrayList<MWE1record> result = MWE1data
						.getMWE1records(firstWord); // get first
				ArrayList<MWErecord> result2 = MWEdata.getMWErecords(
						secondWord, 2); // get 2nd
				ArrayList<MWErecord> result3 = MWEdata.getMWErecords(
						prevTransliterated, 3); // get 3nd
				ArrayList<MWErecord> result4 = MWEdata.getMWErecords(
						transliterated, 4); // get 3nd

				Iterator<MWErecord> itr4 = result4.iterator();
				while (itr4.hasNext()) // goind over first list and finding
										// match in 4rd
				{
					MWErecord mwe4rec = itr4.next();
					if (mwe4rec.getMwTransliterated().equals(phraze)
							&& (mwe4rec.getTransliterated()
									.equals(transliterated))) // MwTransliterated
																// == phraze
					{
						Iterator<MWErecord> itr3 = result3.iterator();
						while (itr3.hasNext()) {
							MWErecord mwe3rec = itr3.next();
							// System.out.println("me1rec.getId() = " +
							// me1rec.getId() + " mwerec.getFormerItemId() " +
							// mwerec.getFormerItemId());
							if (mwe3rec.getTransliterated().equals(
									prevTransliterated)
									&& mwe3rec.getAid().equals(
											mwe4rec.getFormerItemId())) // if
																		// they
																		// have
																		// the
																		// same
																		// id
																		// then
																		// we
																		// have
																		// a
																		// match
							{
								Iterator<MWErecord> itr2 = result2.iterator();
								while (itr2.hasNext()) {
									MWErecord mwe2rec = itr2.next();
									// System.out.println("mwe2rec.getaaId() = "
									// + mwe2rec.getAid() +
									// " mwe3rec.getFormerItemId() " +
									// mwe3rec.getFormerItemId());
									if (mwe2rec.getAid().equals(
											mwe3rec.getFormerItemId())) // if
																		// they
																		// have
																		// the
																		// same
																		// id
																		// then
																		// we
																		// have
																		// a
																		// match
									{
										Iterator<MWE1record> itr1 = result
												.iterator();
										while (itr1.hasNext()) {
											MWE1record mwe1rec = itr1.next();
											if (mwe1rec.getId().equals(
													mwe2rec.getFormerItemId())) // if
																				// they
																				// have
																				// the
																				// same
																				// id
																				// then
																				// we
																				// have
																				// a
																				// match
											{
												// System.out.println("(F) PostProcessor1:checkBackwords(): found MATCH  trans = "
												// +
												// mwe1rec.getTransliterated());
												CustomResult cRes = new CustomResult();
												cRes.LoadRecord(mwe1rec,
														mwe4rec); // load values
												customResultSet.add(cRes); // add
																			// to
																			// result
																			// set
											}
										}
									}
								}
							}
						}
					}
				}
				// System.out.println("Printing CustomResultSet size = " +
				// resultSetSize );
			}
			resultSetSize = customResultSet.size();
			// System.out.println("(F) PostProcessor1:checkBackwords SQL = " +
			// selectSql);

			/*
			 * ResultSet rs = null; try { rs = getData(selectSql); try {
			 * rs.last();
			 * 
			 * if (resultSetSize != rs.getRow()) { System.out.println(
			 * "(F) PostProcessor1:checkBackwords THIS IS NOT GOOD !!! expectedPrevId="
			 * +expectedPrevId);
			 * System.out.println("    resultSetSize="+resultSetSize
			 * +" rs.getRow()="+rs.getRow()); System.out.println(selectSql); try
			 * { System.in.read(); } catch (IOException e) {
			 * e.printStackTrace(); } } } catch (SQLException e) {
			 * e.printStackTrace(); } } finally { releaseConnection(); if (rs ==
			 * null) return false; }
			 */

			if (resultSetSize > 0) {
				for (CustomResult cRes : customResultSet) {

					/*
					 * if (expectedPrevId.equals("3")) { resultSetSize--;
					 * System.out.println(
					 * "(F) PostProcessor1:checkBackwords RS is not empty !!!");
					 * if (resultSetSize < 0 ) { System.out.println(
					 * "(F) PostProcessor1:checkBackwords THIS IS NOT GOOD !!!"
					 * ); try { System.in.read(); } catch (IOException e) {
					 * e.printStackTrace(); } } }
					 */

					if (!prevId.equals(expectedPrevId))
						if (!removeAnalysisByPrevId(prevToken, expectedPrevId)) {
							return rt;
						}

					lastLexiconId = lexiconId;
					// lexiconId = rs.getString("lexiconId");
					lexiconId = cRes.getLexiconId();

					if (!lexiconId.equals("0")) {
						// System.out.println("lexiconId=" + lexiconId);
						// transliteratedLexiconItem =
						// rs.getString("transliteratedLexiconItem");
						transliteratedLexiconItem = cRes
								.getTransliteratedLexiconItem();
						// System.out.println("transliteratedLexiconItem="+
						// transliteratedLexiconItem);

						// undottedLexiconItem =
						// rs.getString("undottedLexiconItem");
						undottedLexiconItem = cRes.getUndottedLexiconItem();

						// dottedLexiconItem =
						// rs.getString("dottedLexiconItem");
						dottedLexiconItem = cRes.getDottedLexiconItem();
						// type = rs.getString("type");
						type = cRes.getType();
						// pos = rs.getString("pos");
						pos = cRes.getPos();

						lastSpelling = spelling;
						// spelling = rs.getString("spelling");
						spelling = cRes.getSpelling();
						// register = rs.getString("register");
						register = cRes.getRegister();

						// gender = rs.getString("gender");
						gender = cRes.getGender();
						// number = rs.getString("number");
						number = cRes.getNumber();
						// definiteness = rs.getString("definiteness");
						definiteness = cRes.getDefiniteness();

						// mwTransliterated = rs.getString("mwTransliterated");
						mwTransliterated = cRes.getMwTransliterated();
						// mwUndotted = rs.getString("mwUndotted");
						mwUndotted = cRes.getMwUndotted();

						// System.out.println(spelling);
						BaseType base = analysis.getBase();

						if (dottedLexiconItem != null
								&& dottedLexiconItem.length() > 0
								&& dottedLexiconItem.charAt(0) != 'u')
							base.setDottedLexiconItem(URLDecoder.decode(
									dottedLexiconItem, "UTF-8"));
						base.setLexiconPointer(lexiconId);
						base.setTransliteratedLexiconItem(transliteratedLexiconItem);
						MWEType mwtype = base.getMWE();
						if (type.charAt(0) != 'u')
							mwtype.setType(type);
						mwtype.setPos(pos);

						base.setLexiconItem(URLDecoder.decode(
								undottedLexiconItem, "UTF-8"));
						mwtype.setSpelling(spelling);
						mwtype.setRegister(register);
						if (!number.equals("unspecified"))
							mwtype.setNumber(number);
						if (!gender.equals("unspecified"))
							mwtype.setGender(gender);
						if (!definiteness.equals("unspecified")) {
							if (definiteness.equals("tt")
									|| definiteness.equals("true"))
								definiteness = "true";
							else if (definiteness.equals("tf")
									|| definiteness.equals("f")
									|| definiteness.equals("false"))
								definiteness = "false";
							mwtype.setDefiniteness(definiteness);
						}

						mwtype.setMultiWordTransliterated(mwTransliterated);
						mwtype.setMultiWordUndotted(URLDecoder.decode(
								mwUndotted, "UTF-8"));
						if (expectedPrevId.equals("1")) {
							// pos = rs.getString("pos");
							removeAnalysisByPos(prevToken, pos);
						}
					}

					rt = true;
					lastPGN = PGN;
					// PGN = rs.getString("PGN");
					PGN = cRes.getPGN();

					if (lastPGN.length() > 0 && !lastPGN.equals(PGN)
					// && (lastSpelling.length() > 0 &&
					// !lastSpelling.equals(spelling))
					) {
						int newId = token.getAnalysis().size() + 1;
						objFactory = new ObjectFactory();
						newAnalysis = objFactory.createAnalysisType();

						newAnalysis.setId(String.valueOf(newId));
						BaseType base = null;

						base = objFactory.createBaseType();

						BaseType oldBase = analysis.getBase();
						if (lastLexiconId.length() > 0
								&& !lastLexiconId.equals(lexiconId)) {
							oldBase.setLexiconPointer(lastLexiconId);
						}
						oldBase.getMWE().setSpelling(lastSpelling);
						analysis.setBase(oldBase);

						base.setDottedLexiconItem(oldBase
								.getDottedLexiconItem());
						base.setLexiconItem(oldBase.getLexiconItem());

						if (lastLexiconId.length() > 0
								&& !lastLexiconId.equals(lexiconId))
							base.setLexiconPointer(lexiconId);
						else
							base.setLexiconPointer(oldBase.getLexiconPointer());

						base.setTransliteratedLexiconItem(oldBase
								.getTransliteratedLexiconItem());

						MWEType mwe = null;
						mwe = objFactory.createMWEType();
						mwe.setConsecutive("true");
						mwe.setSpelling(spelling);
						mwe.setPos(pos);
						mwe.setId(id);
						mwe.setConsecutive("true");
						mwe.setRegister(register);
						base.setMWE(mwe);
						newAnalysis.setBase(base);

						if (!PGN.equals("unspecified")) {
							objFactory = new ObjectFactory();
							SuffixType suffix = null;
							try {
								suffix = objFactory.createSuffixType();
							} catch (JAXBException e3) {
								e3.printStackTrace();
							}
							suffix.setFunction("pronomial");
							suffix.setPerson(StringUtils.getPersonPGN(PGN));
							suffix.setGender(StringUtils.getGenderPGN(PGN));
							suffix.setNumber(StringUtils.getNumberPGN(PGN));
							newAnalysis.setSuffix(suffix);
						}
						token.getAnalysis().add(newAnalysis);
					} else if (!PGN.equals("unspecified")) {
						objFactory = new ObjectFactory();
						SuffixType suffix = null;
						try {
							suffix = objFactory.createSuffixType();
						} catch (JAXBException e3) {
							e3.printStackTrace();
						}
						suffix.setFunction("pronomial");
						suffix.setPerson(StringUtils.getPersonPGN(PGN));
						suffix.setGender(StringUtils.getGenderPGN(PGN));
						suffix.setNumber(StringUtils.getNumberPGN(PGN));
						analysis.setSuffix(suffix);
					}
				}
			}
			if (rt)
				return rt;
		}
		return rt;
	}

	// -----------------------------------------------------------------------------------------------------------------------------------
	public boolean checkBackwordsOLD(String id, String expectedPrevId,
			String prevId, String transliterated,
			ArrayList prevTransliteratedList, AnalysisType analysis,
			TokenType prevToken, TokenType token)
			throws UnsupportedEncodingException, JAXBException {
		boolean rt = false;
		int prevTransliteratedListSize = prevTransliteratedList.size();
		int intId = Integer.parseInt(id);
		if (intId > prevTransliteratedListSize)
			return rt;

		String selectSql = "";
		String lexiconId = "-1";
		String dottedLexiconItem = "";
		String undottedLexiconItem = "";
		String pos = "";
		String type = "";
		String transliteratedLexiconItem = "";
		transliterated = transliterated.replaceAll("'", "\\\\'");
		String PGN = "";
		String lastPGN = "";
		String spelling = "";
		String lastSpelling = "";
		String register = "";
		String gender = "";
		String number = "";
		String mwTransliterated = "";
		String mwUndotted = "";
		ObjectFactory objFactory = null;
		AnalysisType newAnalysis = null;
		String lastLexiconId = "";
		String phraze = "";
		String prevTransliterated = "";
		String definiteness = "";

		for (int prevTransliteratedListIndex = prevTransliteratedListSize - 2; prevTransliteratedListIndex >= 0; prevTransliteratedListIndex--) {
			prevTransliterated = (String) prevTransliteratedList
					.get(prevTransliteratedListIndex);
			prevTransliterated = prevTransliterated.replaceAll("'", "\\\\'");

			if (expectedPrevId.equals("1")) {
				phraze = ((String) prevTransliteratedList
						.get(prevTransliteratedListSize - 2)).replaceAll("'",
						"\\\\'")
						+ " "
						+ ((String) prevTransliteratedList
								.get(prevTransliteratedListSize - 1))
								.replaceAll("'", "\\\\'");
				// System.out.println("*********************" + phraze);
				selectSql = " select mwe2.spelling, mwe2.register, mwe2.gender, mwe2.number,mwe2.definiteness,  mwe2.mwTransliterated,  mwe2.mwUndotted, mwe"
						+ id
						+ ".lexiconId,  mwe"
						+ id
						+ ".undottedLexiconItem ,mwe"
						+ id
						+ ".PGN, mwe"
						+ id
						+ ".dottedLexiconItem, mwe"
						+ id
						+ ".transliteratedLexiconItem, mwe1"
						+ ".pos,  mwe1.type"
						+ "  from  mwe"
						+ id
						+ ",  mwe1 where  mwe"
						+ id
						+ ".transliterated='"
						+ transliterated
						+ "' "
						+ "and mwe1.transliterated='"
						+ prevTransliterated
						+ "' and mwe"
						+ id
						+ ".formerItemId=mwe1.id";
			} else if (expectedPrevId.equals("2")) {
				phraze = ((String) prevTransliteratedList
						.get(prevTransliteratedListSize - 3)).replaceAll("'",
						"\\\\'")
						+ " "
						+ ((String) prevTransliteratedList
								.get(prevTransliteratedListSize - 2))
								.replaceAll("'", "\\\\'")
						+ " "
						+ ((String) prevTransliteratedList
								.get(prevTransliteratedListSize - 1))
								.replaceAll("'", "\\\\'");
				// System.out.println("*********************" + phraze);
				selectSql = " select mwe3.PGN, mwe3.spelling, mwe3.register ,  mwe3.gender, mwe3.number ,mwe2.definiteness,  mwe3.mwTransliterated,  mwe3.mwUndotted, mwe"
						+ id
						+ ".lexiconId, mwe"
						+ id
						+ ".undottedLexiconItem ,"
						+ "mwe"
						+ id
						+ ".dottedLexiconItem, mwe"
						+ id
						+ ".transliteratedLexiconItem, mwe1.type, mwe1.pos"
						+ "  from  mwe"
						+ id
						+ ",  mwe"
						+ expectedPrevId
						+ ", mwe1 where  mwe3.mwTransliterated='"
						+ phraze
						+ "' and mwe"
						+ id
						+ ".transliterated='"
						+ transliterated
						+ "' "
						+ "and mwe"
						+ expectedPrevId
						+ ".transliterated='"
						+ prevTransliterated
						+ "' and mwe"
						+ id
						+ ".formerItemId=mwe"
						+ expectedPrevId + ".aid and mwe2.formerItemId=mwe1.id";
			} else if (expectedPrevId.equals("3")) {
				phraze = ((String) prevTransliteratedList
						.get(prevTransliteratedListSize - 4)).replaceAll("'",
						"\\\\'")
						+ " "
						+ ((String) prevTransliteratedList
								.get(prevTransliteratedListSize - 3))
								.replaceAll("'", "\\\\'")
						+ " "
						+ ((String) prevTransliteratedList
								.get(prevTransliteratedListSize - 2))
								.replaceAll("'", "\\\\'")
						+ " "
						+ ((String) prevTransliteratedList
								.get(prevTransliteratedListSize - 1))
								.replaceAll("'", "\\\\'");
				// System.out.println("*********************" + phraze);

				selectSql = " select mwe4.PGN, mwe4.spelling, mwe4.register  , mwe4.gender, mwe4.number, mwe4.mwTransliterated,  mwe4.definiteness, mwe4.mwUndotted, mwe"
						+ id
						+ ".lexiconId, mwe"
						+ id
						+ ".undottedLexiconItem ,"
						+ "mwe"
						+ id
						+ ".dottedLexiconItem, mwe"
						+ id
						+ ".transliteratedLexiconItem, mwe1.type, mwe1.pos"
						+ "  from  mwe"
						+ id
						+ ",  mwe"
						+ expectedPrevId
						+ ", mwe2 , mwe1 where mwe4.mwTransliterated='"
						+ phraze
						+ "' and mwe"
						+ id
						+ ".transliterated='"
						+ transliterated
						+ "' "
						+ "and mwe"
						+ expectedPrevId
						+ ".transliterated='"
						+ prevTransliterated
						+ "' and mwe"
						+ id
						+ ".formerItemId=mwe"
						+ expectedPrevId
						+ ".aid and "
						+ "mwe3.formerItemId=mwe2.aid and mwe2.formerItemId=mwe1.id";

			}
			// System.out.println("prev selectSql =" + selectSql);
			ResultSet rs = null;

			try {
				rs = getData(selectSql);
				if (rs != null) {
					try {
						while (rs.next()) {
							if (!prevId.equals(expectedPrevId))
								if (!removeAnalysisByPrevId(prevToken,
										expectedPrevId)) {
									return rt;
								}

							lastLexiconId = lexiconId;
							lexiconId = rs.getString("lexiconId");

							if (!lexiconId.equals("0")) {
								// System.out.println("lexiconId=" + lexiconId);
								transliteratedLexiconItem = rs
										.getString("transliteratedLexiconItem");
								// System.out.println("transliteratedLexiconItem="+
								// transliteratedLexiconItem);

								undottedLexiconItem = rs
										.getString("undottedLexiconItem");

								dottedLexiconItem = rs
										.getString("dottedLexiconItem");
								type = rs.getString("type");
								pos = rs.getString("pos");

								lastSpelling = spelling;
								spelling = rs.getString("spelling");
								register = rs.getString("register");

								gender = rs.getString("gender");
								number = rs.getString("number");
								definiteness = rs.getString("definiteness");

								mwTransliterated = rs
										.getString("mwTransliterated");
								mwUndotted = rs.getString("mwUndotted");

								// System.out.println(spelling);

								if (dottedLexiconItem != null
										&& dottedLexiconItem.length() > 0
										&& dottedLexiconItem.charAt(0) != 'u')
									analysis.getBase()
											.setDottedLexiconItem(
													URLDecoder.decode(
															dottedLexiconItem,
															"UTF-8"));
								analysis.getBase().setLexiconPointer(lexiconId);
								analysis.getBase()
										.setTransliteratedLexiconItem(
												transliteratedLexiconItem);
								if (type.charAt(0) != 'u')
									analysis.getBase().getMWE().setType(type);
								analysis.getBase().getMWE().setPos(pos);

								analysis.getBase().setLexiconItem(
										URLDecoder.decode(undottedLexiconItem,
												"UTF-8"));
								analysis.getBase().getMWE()
										.setSpelling(spelling);
								analysis.getBase().getMWE()
										.setRegister(register);
								if (!number.equals("unspecified"))
									analysis.getBase().getMWE()
											.setNumber(number);
								if (!gender.equals("unspecified"))
									analysis.getBase().getMWE()
											.setGender(gender);
								if (!definiteness.equals("unspecified")) {
									if (definiteness.equals("tt")
											|| definiteness.equals("true"))
										definiteness = "true";
									else if (definiteness.equals("tf")
											|| definiteness.equals("f")
											|| definiteness.equals("false"))
										definiteness = "false";
									analysis.getBase().getMWE()
											.setDefiniteness(definiteness);
								}

								analysis.getBase()
										.getMWE()
										.setMultiWordTransliterated(
												mwTransliterated);
								analysis.getBase()
										.getMWE()
										.setMultiWordUndotted(
												URLDecoder.decode(mwUndotted,
														"UTF-8"));
								if (expectedPrevId.equals("1")) {
									pos = rs.getString("pos");
									removeAnalysisByPos(prevToken, pos);
								}

							}
							rt = true;
							lastPGN = PGN;
							PGN = rs.getString("PGN");

							if (lastPGN.length() > 0 && !lastPGN.equals(PGN)
							// && (lastSpelling.length() > 0 &&
							// !lastSpelling.equals(spelling))
							) {
								int newId = token.getAnalysis().size() + 1;
								objFactory = new ObjectFactory();
								newAnalysis = objFactory.createAnalysisType();

								newAnalysis.setId(String.valueOf(newId));
								BaseType base = null;

								base = objFactory.createBaseType();

								BaseType oldBase = analysis.getBase();
								if (lastLexiconId.length() > 0
										&& !lastLexiconId.equals(lexiconId)) {
									oldBase.setLexiconPointer(lastLexiconId);
								}
								oldBase.getMWE().setSpelling(lastSpelling);
								analysis.setBase(oldBase);

								base.setDottedLexiconItem(oldBase
										.getDottedLexiconItem());
								base.setLexiconItem(oldBase.getLexiconItem());
								if (lastLexiconId.length() > 0
										&& !lastLexiconId.equals(lexiconId))
									base.setLexiconPointer(lexiconId);
								else
									base.setLexiconPointer(oldBase
											.getLexiconPointer());
								base.setTransliteratedLexiconItem(oldBase
										.getTransliteratedLexiconItem());

								MWEType mwe = null;
								mwe = objFactory.createMWEType();
								mwe.setConsecutive("true");
								mwe.setSpelling(spelling);
								mwe.setPos(pos);
								mwe.setId(id);
								mwe.setConsecutive("true");
								mwe.setRegister(register);

								base.setMWE(mwe);
								newAnalysis.setBase(base);

								if (!PGN.equals("unspecified")) {
									objFactory = new ObjectFactory();
									SuffixType suffix = null;
									try {
										suffix = objFactory.createSuffixType();
									} catch (JAXBException e3) {
										e3.printStackTrace();
									}
									suffix.setFunction("pronomial");
									suffix.setPerson(StringUtils
											.getPersonPGN(PGN));
									suffix.setGender(StringUtils
											.getGenderPGN(PGN));
									suffix.setNumber(StringUtils
											.getNumberPGN(PGN));

									newAnalysis.setSuffix(suffix);
								}
								token.getAnalysis().add(newAnalysis);

							} else if (!PGN.equals("unspecified")) {
								objFactory = new ObjectFactory();
								SuffixType suffix = null;
								try {
									suffix = objFactory.createSuffixType();
								} catch (JAXBException e3) {
									e3.printStackTrace();
								}
								suffix.setFunction("pronomial");
								suffix.setPerson(StringUtils.getPersonPGN(PGN));
								suffix.setGender(StringUtils.getGenderPGN(PGN));
								suffix.setNumber(StringUtils.getNumberPGN(PGN));

								analysis.setSuffix(suffix);
							}
						}
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					if (rt)
						return rt;
				}

			} finally {
				releaseConnection();
			}
		}
		return rt;

	}

	// ----------------------------------------------------------------------------------------------------
	public boolean checkForward(String transliterated,
			String nextTransliterated, String prevTransliterated, int id)
			throws UnsupportedEncodingException {
		boolean rt = false;
		String selectSql = "";
		int nextId = id + 1;
		String nextIdStr = String.valueOf(nextId);
		String phraze = prevTransliterated + " " + transliterated + " "
				+ nextTransliterated;
		selectSql = " select mwe3.mwTransliterated, mwe4.mwTransliterated"
				+ "  from  mwe3, mwe4  where mwe3.mwTransliterated='" + phraze
				+ "' " + "or  mwe4.mwTransliterated like '" + phraze + "%'";

		// System.out.println(selectSql);
		ResultSet rs = null;

		try {
			rs = getData(selectSql);
			if (rs != null) {

				try {
					if (rs.next()) {
						rt = true;
					}
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		} finally {
			releaseConnection();
		}

		return rt;

	}

	/*--------------------------------------------------------------------------------------------------------------------------*/
	public boolean checkForward(String transliterated,
			String nextTransliterated, String pos, String type)
			throws UnsupportedEncodingException {
		// System.out.println("############# (F) PostProcessor1:checkForward(pos="+pos+")");
		boolean rt = false;
		String selectSql = "";
		boolean testB = false;

		transliterated = transliterated.replaceAll("'", "\\\\'"); // update line
																	// added at
																	// 21.11.10
																	// by yossi
		nextTransliterated = nextTransliterated.replaceAll("'", "\\\\'");

		ArrayList<MWE1record> result = MWE1data.getMWE1records(transliterated); // get
																				// first
																				// from
																				// mwe1
		ArrayList<MWErecord> result2 = MWEdata.getMWErecords(
				nextTransliterated, 2); // get 2nd from mwe2

		Iterator<MWE1record> itr = result.iterator();
		while (itr.hasNext()) // goind over first list and finding match in 2nd
		{
			MWE1record mwe1rec = itr.next();
			// System.out.println("(F) PostProcessor1:checkForward():  id = " +
			// mwe1rec.getId());
			// System.out.println("(F) PostProcessor1:checkForward():  if ("+mwe1rec.getPos()+".equals("+pos+"))");
			if (mwe1rec.getPos().equalsIgnoreCase(pos)) // mwe1.pos= pos
			{
				if (!pos.equals("propername")
						|| (mwe1rec.getType().equalsIgnoreCase(type))) // if pos
																		// ==
																		// propername
																		// so
																		// need
																		// also
																		// mwe1.type=type
				{
					// System.out.println("(F) PostProcessor1:checkForward():  pos = "
					// + mwe1rec.getPos());
					Iterator<MWErecord> itr2 = result2.iterator();
					while (itr2.hasNext()) {
						MWErecord mwerec = itr2.next();
						// System.out.println("(F) PostProcessor1:checkForward(): mwe1rec.getId() "
						// + mwe1rec.getId()+" mwerec.getFormerItemId() " +
						// mwerec.getFormerItemId());
						if (mwe1rec.getId().equals(mwerec.getFormerItemId())) // if
																				// they
																				// have
																				// the
																				// same
																				// id
																				// then
																				// we
																				// have
																				// a
																				// match
						{
							return true; // found one so return true
							// testB = true;
							// System.out.println("(F) PostProcessor1:checkForward() testB = true");
						}
					}
				}
			}
		}
		/*
		 * if (pos.equals("propername")) { selectSql = " select mwe1.id" +
		 * "  from  mwe1, mwe2  where mwe1.transliterated='" + transliterated +
		 * "' " + "and mwe2.transliterated='" + nextTransliterated +
		 * "' and mwe1.pos='" + pos +
		 * "' and mwe2.formerItemId=mwe1.id and mwe1.type='" + type + "'"; }
		 * else { selectSql = " select mwe1.id" +
		 * "  from  mwe1, mwe2  where mwe1.transliterated='" + transliterated +
		 * "' " + "and mwe2.transliterated='" + nextTransliterated +
		 * "' and mwe1.pos='" + pos + "' and mwe2.formerItemId=mwe1.id"; }
		 * //System.out.println(selectSql); ResultSet rs = null; try { rs =
		 * getData(selectSql); if (rs != null) { try { if (rs.next()) { rt =
		 * true;
		 *//*
			 * if (testB != true) { System.out.println(
			 * "(F) PostProcessor1:checkForward() huston WE GOT A PROBLEM");
			 * System.out.println(selectSql); try { System.in.read(); } catch
			 * (IOException e) { // TODO Auto-generated catch block
			 * e.printStackTrace(); } }
			 *//*
				 * } } catch (SQLException e) { // TODO Auto-generated catch
				 * block e.printStackTrace(); } } } finally {
				 * releaseConnection(); }
				 */
		return rt;
		// return testB;
	}

	// --------------------------------------------------------------------------------------------------------------------------------------
	public boolean checkForwardOLD(String transliterated,
			String nextTransliterated, String pos, String type)
			throws UnsupportedEncodingException {
		boolean rt = false;
		String selectSql = "";
		transliterated = transliterated.replaceAll("'", "\\\\'"); // update line
																	// added at
																	// 21.11.10
																	// by yossi
		nextTransliterated = nextTransliterated.replaceAll("'", "\\\\'");
		if (pos.equals("propername"))
			selectSql = " select mwe1.id"
					+ "  from  mwe1, mwe2  where mwe1.transliterated='"
					+ transliterated + "' " + "and mwe2.transliterated='"
					+ nextTransliterated + "' and mwe1.pos='" + pos
					+ "' and mwe2.formerItemId=mwe1.id and mwe1.type='" + type
					+ "'";
		else
			selectSql = " select mwe1.id"
					+ "  from  mwe1, mwe2  where mwe1.transliterated='"
					+ transliterated + "' " + "and mwe2.transliterated='"
					+ nextTransliterated + "' and mwe1.pos='" + pos
					+ "' and mwe2.formerItemId=mwe1.id";

		// System.out.println(selectSql);
		ResultSet rs = null;

		try {
			rs = getData(selectSql);
			if (rs != null) {

				try {
					if (rs.next()) {
						rt = true;
					}
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		} finally {
			releaseConnection();
		}
		return rt;
	}

	// -------------------------------------------------------------------------------------------------
	public void checkNextMWExistence(List tokenList, int tokenIndex,
			int tokenListSize) {
		boolean foundNext = false;
		String expectedNextIdStr = "";
		TokenType token = (TokenType) tokenList.get(tokenIndex);
		List analysisList = token.getAnalysis();
		int analysisListSize = analysisList.size();
		for (int analysisIndex = 0; analysisIndex < analysisListSize; analysisIndex++) {
			AnalysisType analysis = (AnalysisType) analysisList
					.get(analysisIndex);
			BaseType base = analysis.getBase();
			if (base != null) {
				MWEType mweType = base.getMWE();
				if (mweType != null) {
					// System.out.println(" tokenIndex =" + tokenIndex);
					// System.out.println("token surface=" +
					// token.getSurface());
					String lexiconPointer = analysis.getBase()
							.getLexiconPointer();

					if (lexiconPointer != null)
						return;
					boolean multiWordPrefixExist = analysis.getBase().getMWE()
							.isMultiWordPrefixExist();
					String id = analysis.getBase().getMWE().getId();
					int expectedNextId = Integer.parseInt(id) + 1;
					expectedNextIdStr = String.valueOf(expectedNextId);
					int nextTokenIndex = tokenIndex + 1;
					if (nextTokenIndex < tokenListSize) {
						TokenType nextToken = (TokenType) tokenList
								.get(nextTokenIndex);
						String nextTokenSurface = nextToken.getSurface();
						if (nextTokenSurface.equals("-")
								&& nextTokenIndex + 1 < tokenListSize)
							nextToken = (TokenType) tokenList
									.get(nextTokenIndex + 1);
						List nextAnalysisList = nextToken.getAnalysis();
						int nextAnalysisListSize = nextAnalysisList.size();
						foundNext = false;

						// System.out.println("next token surface="+
						// nextToken.getSurface());
						for (int nextAnalysisIndex = 0; nextAnalysisIndex < nextAnalysisListSize; nextAnalysisIndex++) {
							AnalysisType nextAnalysis = (AnalysisType) nextAnalysisList
									.get(nextAnalysisIndex);
							if (multiWordPrefixExist) {
								List nextPrefixList = nextAnalysis.getPrefix();
								if (nextPrefixList.size() > 0)
									if (((PrefixType) (nextPrefixList).get(0))
											.isMultiWord()) {
										foundNext = true;
										break;
									}
							}
							BaseType nextBase = nextAnalysis.getBase();
							if (nextBase != null) {
								MWEType nextMweType = nextBase.getMWE();
								if (nextMweType != null) {
									String nextIdStr = nextAnalysis.getBase()
											.getMWE().getId();
									int nextId = Integer.parseInt(nextIdStr);
									String nextLexiconPointer = nextAnalysis
											.getBase().getLexiconPointer();

									if (nextIdStr.equals(expectedNextIdStr)
											&& nextLexiconPointer == null) {
										foundNext = checkNextNext(
												nextTokenIndex, tokenListSize,
												tokenList, nextId + 1);
										if (foundNext)
											break;
									} else if (nextIdStr
											.equals(expectedNextIdStr)
											&& nextLexiconPointer != null) {
										foundNext = true;
										break;
									}
								}
							}
						}
						if (!foundNext) {
							token.getAnalysis().remove(analysisIndex);
							analysisIndex--;
							arrangeAnalysisIdAfterRemove(token);
							analysisListSize = analysisList.size();
						}
					}
				}
			}
		}
	}

	// -------------------------------------------------------------------------------------------------------------
	public boolean checkNextNext(int tokenIndex, int tokenListSize,
			List tokenList, int expectedNextId) {
		boolean foundNext = false;
		MWEType nextMweType = null; // NEW
		String expectedNextIdStr = String.valueOf(expectedNextId);
		int nextTokenIndex = tokenIndex + 1;
		if (nextTokenIndex < tokenListSize) {
			TokenType nextToken = (TokenType) tokenList.get(nextTokenIndex);
			List nextAnalysisList = nextToken.getAnalysis();
			int nextAnalysisListSize = nextAnalysisList.size();

			// System.out.println("next token surface=" +
			// nextToken.getSurface());
			for (int nextAnalysisIndex = 0; nextAnalysisIndex < nextAnalysisListSize; nextAnalysisIndex++) {
				AnalysisType nextAnalysis = (AnalysisType) nextAnalysisList
						.get(nextAnalysisIndex);
				BaseType nextBase = nextAnalysis.getBase();
				if (nextBase != null)
					nextMweType = nextBase.getMWE();
				if (nextMweType != null) {
					String nextId = nextAnalysis.getBase().getMWE().getId();
					String lexiconPointer = nextAnalysis.getBase()
							.getLexiconPointer();
					if (nextId.equals(expectedNextIdStr)
							&& lexiconPointer != null)
						foundNext = true;
				}
			}
		}
		return foundNext;
	}

	// ----------------------------------------------------------------------------------------------------------------------------
	public String checkNextToken(TokenType nextToken, int tokenListSize) {
		StringBuffer nextTokenIdList = new StringBuffer();
		String translierated = Translate.Heb2Eng(nextToken.getSurface());
		List nextTokenAnalysesList = nextToken.getAnalysis();
		int nextTokenAnalysisListSize = nextTokenAnalysesList.size();
		for (int nextTokenAnalysisIndex = 0; nextTokenAnalysisIndex < nextTokenAnalysisListSize; nextTokenAnalysisIndex++) {
			AnalysisType nextTokenAnalysis = (AnalysisType) nextTokenAnalysesList
					.get(nextTokenAnalysisIndex);
			if (nextTokenAnalysis.getBase() != null) {
				MWEType nextTokenMWE = nextTokenAnalysis.getBase().getMWE();
				if (nextTokenMWE != null) {
					String nextTokenMWId = nextTokenMWE.getId();
					nextTokenIdList.append(nextTokenMWId);
					nextTokenIdList.append(",");
				}
			}
		}

		return nextTokenIdList.toString();
	}

	// ---------------------------------------------------------------------------------------------------------------
	public boolean checkPrefixForward(String transliterated,
			TokenType nextToken, String pos, String type)
			throws UnsupportedEncodingException {
		// System.out.println("BAZINGA");
		boolean rt = false;
		boolean testB = false;
		String selectSql = "";

		List analysisList = nextToken.getAnalysis();
		int analysisListSize = analysisList.size();
		for (int i = 0; i < analysisListSize; i++) {
			AnalysisType analysis = (AnalysisType) analysisList.get(i);
			List prefixList = analysis.getPrefix();
			if (prefixList.size() == 1) {
				PrefixType prefix = (PrefixType) prefixList.get(0);
				String prefixTranslietarted = Translate.getHebToEng(prefix
						.getSurface());

				ArrayList<MWE1record> result = MWE1data
						.getMWE1records(transliterated); // get first from mwe1
				ArrayList<MWErecord> result2 = MWEdata.getMWErecords(
						prefixTranslietarted, 2); // get 2nd from mwe2

				Iterator<MWE1record> itr = result.iterator();
				while (itr.hasNext()) // goind over first list and finding match
										// in 2nd
				{
					MWE1record mwe1rec = itr.next();
					// System.out.println("(F) PostProcessor1:checkForward():  id = "
					// + mwe1rec.getId());
					// System.out.println("(F) PostProcessor1:checkForward():  if ("+mwe1rec.getPos()+".equals("+pos+"))");
					if (mwe1rec.getPos().equalsIgnoreCase(pos)) // mwe1.pos= pos
					{
						// System.out.println("(F) PostProcessor1:checkForward():  pos = "
						// + mwe1rec.getPos());
						Iterator<MWErecord> itr2 = result2.iterator();
						while (itr2.hasNext()) {
							MWErecord mwerec = itr2.next();
							// System.out.println("(F) PostProcessor1:checkForward(): mwe1rec.getId() "
							// + mwe1rec.getId()+" mwerec.getFormerItemId() " +
							// mwerec.getFormerItemId());
							if (mwe1rec.getId()
									.equals(mwerec.getFormerItemId())) // if
																		// they
																		// have
																		// the
																		// same
																		// id
																		// then
																		// we
																		// have
																		// a
																		// match
							{
								prefix.setMultiWord(true);
								analysis.getPrefix().set(0, prefix);
								nextToken.getAnalysis().set(i, analysis);
								testB = true;
								// return true; //found one so return true
							}
						}
					}
				}

				selectSql = " select mwe1.id"
						+ "  from  mwe1, mwe2  where mwe1.transliterated='"
						+ transliterated + "' " + "and mwe2.transliterated='"
						+ prefixTranslietarted + "' and mwe1.pos='" + pos
						+ "' and mwe2.formerItemId=mwe1.id";
				// System.out.println(selectSql);
				ResultSet rs = null;

				try {
					rs = getData(selectSql);
					if (rs != null) {
						try {
							if (rs.next()) {
								prefix.setMultiWord(true);
								analysis.getPrefix().set(0, prefix);
								nextToken.getAnalysis().set(i, analysis);
								if (!rt)
									rt = true;
							}
						} catch (SQLException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
				} finally {
					releaseConnection();
				}

			}
		}
		if (testB != rt) {
			System.out
					.println("(F) PostProcessor1:checkPrefixForward() huston WE GOT A PROBLEM");
			System.out.println(selectSql);
			try {
				System.in.read();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return rt;
	}

	public void createMWWAnalysis(TokenType token, BaseType originalBase,
			int originalAnalysisIndex, String multiWord, String id)
			throws JAXBException {
		ObjectFactory objFactory = null;
		objFactory = new ObjectFactory();

		AnalysisType analysis = objFactory.createAnalysisType();
		analysis.setId(String.valueOf(originalAnalysisIndex + 1));

		// analysis.setId(analysisId);
		BaseType base = null;
		base = objFactory.createBaseType();
		base.setLexiconPointer(originalBase.getLexiconPointer());
		base.setLexiconItem(originalBase.getLexiconPointer());
		base.setDottedLexiconItem(originalBase.getDottedLexiconItem());
		base.setTransliteratedLexiconItem(originalBase
				.getTransliteratedLexiconItem());
		MWEType mw = null;
		mw = objFactory.createMWEType();
		mw.setId(id);
		mw.setConsecutive("true");
		mw.setMultiWord(multiWord);
		mw.setPos("properName");
		mw.setGender(originalBase.getProperName().getGender());
		mw.setNumber(originalBase.getProperName().getNumber());
		mw.setType(originalBase.getProperName().getType());
		mw.setRegister(originalBase.getProperName().getRegister());
		base.setMWE(mw);
		analysis.setBase(base);
		token.getAnalysis().remove(originalAnalysisIndex);
		token.getAnalysis().add(originalAnalysisIndex, analysis);
	}

	// ---------------------------------------------------------------------------------------------------------------------------
	public TokenType createUnknownAnalysis(TokenType token,
			final String hebWord, final String transliterated)
			throws JAXBException {
		ObjectFactory objFactory;
		objFactory = new ObjectFactory();

		AnalysisType analysis = objFactory.createAnalysisType();

		analysis.setId("1");
		BaseType base = null;

		base = objFactory.createBaseType();

		UnknownType unknown = null;

		unknown = objFactory.createUnknownType();
		setBase(base, transliterated, hebWord, "0", "");
		base.setUnknown(unknown);
		analysis.setBase(base);

		token.getAnalysis().add(analysis);

		return token;
	}

	public List getProperName(TokenType token, List analysisIndexList) {
		List<BaseType> list = new ArrayList();
		List<AnalysisType> analysisList = token.getAnalysis();
		int size = analysisList.size();
		for (int i = 0; i < size; i++) {
			AnalysisType analysis = analysisList.get(i);
			BaseType base = analysis.getBase();
			if (base != null) {
				ProperNameType properName = base.getProperName();
				if (properName != null) {
					list.add(base);
					analysisIndexList.add(Integer.valueOf(i));
				}
			}
		}
		return list;
	}

	public String MWEAnalysisExist(TokenType token) {
		String id = "";
		List analysesList = token.getAnalysis();
		int analysisListSize = analysesList.size();
		for (int i = 0; i < analysisListSize; i++) {
			AnalysisType analysis = (AnalysisType) analysesList.get(i);
			BaseType base = analysis.getBase();
			if (base != null) {
				MWEType mw = base.getMWE();
				if (mw != null) {
					id = mw.getId();
					break;
				}
			}
		}
		return id;
	}

	// ----------------------------------------------------------------------------------------------------------
	public void postAnalysis(List articles) {
		ArticleType article = null;
		ParagraphType paragraph = null;
		SentenceType sentence = null;
		TokenType token = null;
		AnalysisType analysis = null;
		List paragraphList = null;
		List sentenceList = null;
		List tokenList = null;
		List analysesList = null;
		int tokenIndex = -1;
		ArrayList removeList = new ArrayList();

		article = (ArticleType) articles.get(0); // a);
		paragraphList = article.getParagraph();
		int paragraphListSize = paragraphList.size();
		for (int paragraphIndex = 0; paragraphIndex < paragraphListSize; paragraphIndex++) {
			paragraph = (ParagraphType) paragraphList.get(paragraphIndex); // p);
			sentenceList = paragraph.getSentence();
			int sentenceListSize = sentenceList.size();
			for (int sentenceIndex = 0; sentenceIndex < sentenceListSize; sentenceIndex++) {
				sentence = (SentenceType) sentenceList.get(sentenceIndex); // s);
				tokenList = sentence.getToken();
				int tokenListSize = tokenList.size();

				tokenIndex = 0;
				boolean stopFlag = false;
				while (tokenIndex < tokenListSize) {
					// ///////////////////////////////////////////
					token = (TokenType) tokenList.get(tokenIndex);
					// System.out.println("token surface=" +
					// token.getSurface());
					checkNextMWExistence(tokenList, tokenIndex, tokenListSize);
					tokenIndex++;

				}
			}

		}
	}

	// -------------------------------------------------------------------------------------------------------------
	public void process(InputStream morphXMLanalysis, PrintWriter pw)
			throws SQLException, UnsupportedEncodingException, JAXBException {
		ArticleType article = null;
		ParagraphType paragraph = null;
		SentenceType sentence = null;
		TokenType token = null;
		AnalysisType analysis = null;
		List paragraphList = null;
		int paragraphs = 0;
		List sentenceList = null;
		List tokenList = null;
		List analysesList = null;
		List articles = null;

		int tokenIndex = -1;
		String nextTokenTransliterated = "";
		String nextTokenMWIdList = "";
		String prevId = "";
		String id = "";
		int prevIdInt = -1;
		String lexiconId;
		boolean mwMWExistFlag = false;
		boolean mwprefixExistCurrent = false;

		ArrayList prevTransliteratedList = new ArrayList();

		int firstMWTokenIndexInSentence = -1;

		CorpusAnalysisReader reader = new CorpusAnalysisReader();
		reader.parse(morphXMLanalysis);

		/*
		 * if (reader == null) {
		 * System.out.println("input to postProcessor is null - existing now");
		 * return; }
		 */

		articles = reader.getArticle();

		if (articles == null) // UPDATE 21.11.10 (yossi) - insted of exiting
								// return so it can move to next file
		{
			return;
		}

		article = (ArticleType) articles.get(0); // a);
		paragraphList = article.getParagraph();
		int paragraphListSize = paragraphList.size();
		for (int paragraphIndex = 0; paragraphIndex < paragraphListSize; paragraphIndex++) {
			paragraph = (ParagraphType) paragraphList.get(paragraphIndex); // p);
			sentenceList = paragraph.getSentence();
			int sentenceListSize = sentenceList.size();

			for (int sentenceIndex = 0; sentenceIndex < sentenceListSize; sentenceIndex++) {
				multiWord = "";
				firstMWTokenIndexInSentence = -1;

				sentence = (SentenceType) sentenceList.get(sentenceIndex); // s);
				tokenList = sentence.getToken();
				int tokenListSize = tokenList.size();

				for (tokenIndex = 0; tokenIndex < tokenListSize; tokenIndex++) {
					TokenType prevToken = null;
					TokenType nextToken = null;
					token = (TokenType) tokenList.get(tokenIndex);
					if (((AnalysisType) (token.getAnalysis().get(0))).getBase() != null
							&& ((AnalysisType) (token.getAnalysis().get(0)))
									.getBase().getPunctuation() != null)
						continue;
					// check whether the previous token had mw analysis
					// ////////////////////////////////////////////
					if (tokenIndex > 0) {
						prevToken = (TokenType) tokenList.get(tokenIndex - 1);
						String prevTokenSurface = prevToken.getSurface();
						if (prevTokenSurface.equals("-") && tokenIndex - 1 > 0)
							prevToken = (TokenType) tokenList
									.get(tokenIndex - 2);
						prevId = MWEAnalysisExist(prevToken);

						if (!prevId.equals("")) {
							firstMWTokenIndexInSentence = tokenIndex;
							prevIdInt = Integer.parseInt(prevId);
							// התמנית הקודמת אינה חלק מהביטוי
						} else {
							firstMWTokenIndexInSentence = -1;
							prevIdInt = -1;
						}
					}
					// ///////////////////////////////////////////

					if (tokenIndex + 1 < tokenListSize) {
						nextToken = (TokenType) tokenList.get(tokenIndex + 1);
						String nextSurface = nextToken.getSurface();
						if (nextSurface.equals("-")) {
							if (tokenIndex + 2 < tokenListSize) {
								nextToken = (TokenType) tokenList
										.get(tokenIndex + 2);
								nextSurface = nextToken.getSurface();
							}
						}
						nextTokenTransliterated = Translate
								.Heb2Eng(nextSurface);
						// System.out.println("nextTokenTransliterated ="+
						// nextTokenTransliterated);

						nextTokenMWIdList = checkNextToken(nextToken,
								tokenListSize);
					} else {
						nextTokenMWIdList = "";
						nextTokenTransliterated = "";
					}
					// System.out.println(" tokenIndex =" + tokenIndex);
					// System.out.println("token surface=" +
					// token.getSurface());

					/*
					 * if(token.getSurface().equals("פי")) System.out.println();
					 */

					analysesList = token.getAnalysis();
					int analysisListSize = analysesList.size();

					mwMWExistFlag = false;

					for (int analysisIndex = 0; analysisIndex < analysisListSize; analysisIndex++) {
						analysis = (AnalysisType) analysesList
								.get(analysisIndex);

						BaseType base = analysis.getBase();
						if (base != null) {

							// ////////////////////////////////////////////////////////
							// MultiWord
							// //////////////////////////////////////////////////////

							MWEType mweType = base.getMWE();
							if (mweType != null) {
								if (analysis.getSuffix() != null)
									continue;

								removeAnalysisFlag = false;

								String pos = analysis.getBase().getMWE()
										.getPos();
								String type = analysis.getBase().getMWE()
										.getType();
								// if (pos.equals("properName"))
								// continue;
								id = analysis.getBase().getMWE().getId();

								String transliterated = Translate.Heb2Eng(token
										.getSurface());

								List prefixList = analysis.getPrefix();
								String prefixTransliterated = "";
								int prefixLen = 0;
								if (prefixList.size() > 0) {
									for (int prefixIndex = 0; prefixIndex < prefixList
											.size(); prefixIndex++) {
										prefixTransliterated = ((PrefixType) (prefixList
												.get(prefixIndex)))
												.getSurface();
										prefixLen = prefixLen
												+ prefixTransliterated.length();
									}
								}

								transliterated = transliterated
										.substring(prefixLen);
								// System.out.println(" transliterated ="+
								// transliterated);
								if (!mwMWExistFlag)
									prevTransliteratedList.add(transliterated);

								mwMWExistFlag = true;

								String consecutive = analysis.getBase()
										.getMWE().getConsecutive();

								mwprefixExistCurrent = analysis.getBase()
										.getMWE().isMultiWordPrefixExist();

								int expectedPrevId = Integer.parseInt(id) - 1;
								String expectedPrevIdStr = String
										.valueOf(expectedPrevId);

								// System.out.println(token.getSurface());
								// ביטוי עם מזהה שונה מ1 לא יכול להיות
								// התמנית
								// הראשונה של ביטוי
								if ((firstMWTokenIndexInSentence == -1)
										&& !id.equals("1"))
									removeAnalysisFlag = true;
								// ביטוי עם מזהה שווה ל-1 לא יופיע בסוף משפט
								else if ((tokenIndex == tokenListSize - 1)
										&& id.equals("1")) {
									removeAnalysisFlag = true;

								} else if (consecutive.equals("true")) {

									if (!id.equals("1")) {
										try {
											if (!checkBackwords(id,
													expectedPrevIdStr, prevId,
													transliterated,
													prevTransliteratedList,
													analysis, prevToken, token))
												removeAnalysisFlag = true;

										} catch (UnsupportedEncodingException e) {
											// TODO Auto-generated catch
											// block
											e.printStackTrace();
										}

									} else {
										if (!mwprefixExistCurrent) {
											if (!checkForward(transliterated,
													nextTokenTransliterated,
													pos, type))
												removeAnalysisFlag = true;
										} else if (!checkPrefixForward(
												transliterated, nextToken, pos,
												"unspecified"))

											removeAnalysisFlag = true;

									}
								}
								if (removeAnalysisFlag) {
									token.getAnalysis().remove(analysisIndex);
									analysisIndex--;
									arrangeAnalysisIdAfterRemove(token);
									analysisListSize = analysesList.size();
									if (analysisListSize == 0)
										token = createUnknownAnalysis(token,
												token.getSurface(),
												transliterated);

								}
							}

						}
					}

					if (!mwMWExistFlag)
						prevTransliteratedList = new ArrayList();

				}

			}

		}
		postAnalysis(articles);
		reader.save(pw);
	}

	// public boolean checkMWEBaseForm(String transliterated,
	// String nextTransliterated, String id, String nextId,
	// String nextTokenMWIdList, int tokenIndex) {
	// boolean rt = false;
	// // String sql = "select id from mwe" + id + " where transliterated"
	// // + " ='" + transliterated + "'";
	//
	// String selectSql = "";
	// if (id.equals("1"))
	// selectSql = " select mwe" + id + ".transliterated from mwe" + id
	// + ", mwe" + nextId + " where mwe" + id
	// + ".transliterated='" + transliterated + "' " + "and mwe"
	// + nextId + ".transliterated='" + nextTransliterated
	// + "' and mwe" + id + ".id=mwe" + nextId + ".formerItemId";
	// else
	// selectSql = " select mwe" + id + ".transliterated from mwe" + id
	// + ", mwe" + nextId + " where mwe" + id
	// + ".transliterated='" + transliterated + "' " + "and mwe"
	// + nextId + ".transliterated='" + nextTransliterated
	// + "' and mwe" + id + ".aid=mwe" + nextId + ".formerItemId";
	// System.out.println(selectSql);
	// ResultSet rs = null;
	//
	// try {
	// rs = getData(selectSql);
	//
	// if (rs != null) {
	//
	// try {
	// if (rs.next())
	// rt = true;
	// else
	// candidateForRemove = tokenIndex;
	//
	// // else if (!id.equals("1")) {
	// //
	// // selectSql = " select mwe" + id + ".maxLen from mwe"
	// // + id + " where mwe" + id + ".transliterated='"
	// // + transliterated + "'";
	// // System.out.println(selectSql);
	// //
	// // rs = getData(selectSql);
	// //
	// // while (rs.next()) {
	// // String maxLen = rs.getString("maxLen");
	// // // אף על פי כן אף פעם לא רציתי
	// // if (maxLen.equals(id)) {
	// // rt = true;
	// // }
	// // }
	// //
	// // }
	//
	// } catch (SQLException e) {
	// // TODO Auto-generated catch block
	// e.printStackTrace();
	// }
	// }
	// } finally {
	// releaseConnection();
	// }
	//
	// return rt;
	// }
	//
	// public boolean checkMaxLen(String transliterated, String id,
	// String nextIdStr, String nextTokenMWIdList) throws SQLException {
	// boolean rt = false;
	// if (nextTokenMWIdList.indexOf(nextIdStr) != -1)
	// rt = true;
	// else {
	// String selectSql = " select mwe" + id + ".maxLen from mwe" + id
	// + " where mwe" + id + ".transliterated='" + transliterated
	// + "'";
	// System.out.println(selectSql);
	//
	// ResultSet rs = getData(selectSql);
	//
	// while (rs.next()) {
	// String maxLen = rs.getString("maxLen");
	// // אף על פי כן אף פעם לא רציתי
	// if (maxLen.equals(id))
	// rt = true;
	//
	// }
	// }
	// return rt;
	// }

	public void process(String inputFile, String outputFile)
			throws SQLException, UnsupportedEncodingException, JAXBException {
		ArticleType article = null;
		ParagraphType paragraph = null;
		SentenceType sentence = null;
		TokenType token = null;
		AnalysisType analysis = null;
		List paragraphList = null;
		int paragraphs = 0;
		List sentenceList = null;
		List tokenList = null;
		List analysesList = null;
		List articles = null;

		int tokenIndex = -1;
		String nextTokenTransliterated = "";
		String nextTokenMWIdList = "";
		String prevId = "";
		String id = "";
		String lexiconId = "";
		int prevIdInt = -1;
		ArrayList prevTransliteratedList = new ArrayList();
		boolean mwMWExistFlag = false;
		boolean mwprefixExistCurrent = false;
		int firstMWTokenIndexInSentence = -1;

		CorpusAnalysisReader reader = new CorpusAnalysisReader(inputFile);

		/*
		 * if (reader == null) { System.exit(-1); }
		 */

		articles = reader.getArticle();
		if (articles == null) // UPDATE 21.11.10 (yossi) - insted of exiting
								// return so it can move to next file
		{
			return;
		}

		article = (ArticleType) articles.get(0); // a);
		paragraphList = article.getParagraph();
		int paragraphListSize = paragraphList.size();
		for (int paragraphIndex = 0; paragraphIndex < paragraphListSize; paragraphIndex++) {
			paragraph = (ParagraphType) paragraphList.get(paragraphIndex); // p);
			sentenceList = paragraph.getSentence();
			int sentenceListSize = sentenceList.size();

			for (int sentenceIndex = 0; sentenceIndex < sentenceListSize; sentenceIndex++) {
				multiWord = "";
				firstMWTokenIndexInSentence = -1;

				sentence = (SentenceType) sentenceList.get(sentenceIndex); // s);
				tokenList = sentence.getToken();
				int tokenListSize = tokenList.size();

				for (tokenIndex = 0; tokenIndex < tokenListSize; tokenIndex++) {
					TokenType prevToken = null;
					TokenType nextToken = null;
					token = (TokenType) tokenList.get(tokenIndex);

					if (((AnalysisType) (token.getAnalysis().get(0))).getBase() != null
							&& ((AnalysisType) (token.getAnalysis().get(0)))
									.getBase().getPunctuation() != null)
						continue;
					// check whether the previous token had mw analysis
					// ////////////////////////////////////////////
					if (tokenIndex > 0) {
						prevToken = (TokenType) tokenList.get(tokenIndex - 1);
						String prevTokenSurface = prevToken.getSurface();
						if (prevTokenSurface.equals("-") && tokenIndex - 1 > 0)
							prevToken = (TokenType) tokenList
									.get(tokenIndex - 2);
						prevId = MWEAnalysisExist(prevToken);

						if (!prevId.equals("")) {
							firstMWTokenIndexInSentence = tokenIndex;
							prevIdInt = Integer.parseInt(prevId);
							// התמנית הקודמת אינה חלק מהביטוי
						} else {
							firstMWTokenIndexInSentence = -1;
							prevIdInt = -1;
						}
					}
					// ///////////////////////////////////////////

					if (tokenIndex + 1 < tokenListSize) {
						nextToken = (TokenType) tokenList.get(tokenIndex + 1);
						String nextSurface = nextToken.getSurface();
						if (nextSurface.equals("-")) {
							if (tokenIndex + 2 < tokenListSize) {
								nextToken = (TokenType) tokenList
										.get(tokenIndex + 2);
								nextSurface = nextToken.getSurface();
							}
						}
						nextTokenTransliterated = Translate
								.Heb2Eng(nextSurface);
						// System.out.println("nextTokenTransliterated ="+
						// nextTokenTransliterated);

						nextTokenMWIdList = checkNextToken(nextToken,
								tokenListSize);
					} else {
						nextTokenMWIdList = "";
						nextTokenTransliterated = "";
					}
					// System.out.println(" tokenIndex =" + tokenIndex);
					// System.out.println("token surface=" +
					// token.getSurface());

					if (token.getSurface().equals("בהתחשב"))
						System.out.println();

					analysesList = token.getAnalysis();
					int analysisListSize = analysesList.size();

					mwMWExistFlag = false;

					for (int analysisIndex = 0; analysisIndex < analysisListSize; analysisIndex++) {
						analysis = (AnalysisType) analysesList
								.get(analysisIndex);

						BaseType base = analysis.getBase();
						if (base != null) {
							// ////////////////////////////////////////////////////////
							// MultiWord
							// //////////////////////////////////////////////////////

							MWEType mweType = base.getMWE();
							if (mweType != null) {
								if (analysis.getSuffix() != null)
									continue;

								removeAnalysisFlag = false;

								String pos = analysis.getBase().getMWE()
										.getPos();
								String type = analysis.getBase().getMWE()
										.getType();
								// if (pos.equals("properName"))
								// continue;
								id = analysis.getBase().getMWE().getId();

								String transliterated = Translate.Heb2Eng(token
										.getSurface());

								List prefixList = analysis.getPrefix();
								String prefixTransliterated = "";
								int prefixLen = 0;
								if (prefixList.size() > 0) {
									for (int prefixIndex = 0; prefixIndex < prefixList
											.size(); prefixIndex++) {
										prefixTransliterated = ((PrefixType) (prefixList
												.get(prefixIndex)))
												.getSurface();
										prefixLen = prefixLen
												+ prefixTransliterated.length();
									}
								}

								transliterated = transliterated
										.substring(prefixLen);
								// System.out.println(" transliterated ="+
								// transliterated);
								if (!mwMWExistFlag)
									prevTransliteratedList.add(transliterated);

								mwMWExistFlag = true;

								String consecutive = analysis.getBase()
										.getMWE().getConsecutive();

								mwprefixExistCurrent = analysis.getBase()
										.getMWE().isMultiWordPrefixExist();

								int expectedPrevId = Integer.parseInt(id) - 1;
								String expectedPrevIdStr = String
										.valueOf(expectedPrevId);

								// System.out.println(token.getSurface());
								// ביטוי עם מזהה שונה מ1 לא יכול להיות
								// התמנית
								// הראשונה של ביטוי
								if ((firstMWTokenIndexInSentence == -1)
										&& !id.equals("1"))
									removeAnalysisFlag = true;
								// ביטוי עם מזהה שווה ל-1 לא יופיע בסוף משפט
								else if ((tokenIndex == tokenListSize - 1)
										&& id.equals("1")) {
									removeAnalysisFlag = true;

								} else if (consecutive.equals("true")) {
									if (!id.equals("1")) {
										try {
											if (!checkBackwords(id,
													expectedPrevIdStr, prevId,
													transliterated,
													prevTransliteratedList,
													analysis, prevToken, token))
												removeAnalysisFlag = true;
										} catch (UnsupportedEncodingException e) {
											// TODO Auto-generated catch
											// block
											e.printStackTrace();
										}
									} else {
										if (!mwprefixExistCurrent) {
											if (!checkForward(transliterated,
													nextTokenTransliterated,
													pos, type))
												removeAnalysisFlag = true;
										} else if (!checkPrefixForward(
												transliterated, nextToken, pos,
												"unspecified"))
											removeAnalysisFlag = true;
									}
								}
								if (removeAnalysisFlag) {
									token.getAnalysis().remove(analysisIndex);
									analysisIndex--;
									arrangeAnalysisIdAfterRemove(token);
									analysisListSize = analysesList.size();
									if (analysisListSize == 0)
										token = createUnknownAnalysis(token,
												token.getSurface(),
												transliterated);
								}
							}
						}
					}
					if (!mwMWExistFlag)
						prevTransliteratedList = new ArrayList();
				}
			}
		}
		// reader
		// .save("C:\\Documents and Settings\\daliabo\\My
		// Documents\\lexicon\\diffTests\\cleanoutput3.xml");
		postAnalysis(articles);
		reader.save(outputFile);
		// System.exit(0);
	}

	public void processProperNameToken(int i, int j, List prevBaseList,
			List nextBaseList, BaseType currentBase, TokenType token,
			TokenType prevToken, TokenType nextToken, int currentAnalysisIndex,
			List prevAnalysisIndexList, List nextAnalysisIndexList)
			throws JAXBException {
		int prefixListsize = -1;
		List prefixList = new ArrayList();

		BaseType prevBase = (BaseType) prevBaseList.get(i);
		BaseType nextBase = (BaseType) nextBaseList.get(j);

		String prevProperNameType = prevBase.getProperName().getType();

		String nextProperNameType = nextBase.getProperName().getType();

		String currentProperNameType = currentBase.getProperName().getType();

		String prevLexiconPointer = prevBase.getLexiconItem();
		String nextLexiconPointer = nextBase.getLexiconItem();
		String currentLexiconPointer = currentBase.getLexiconItem();

		if ((prevProperNameType.equals(currentProperNameType) && currentProperNameType
				.equals(nextProperNameType))
				|| prevProperNameType == null
				|| currentProperNameType == null || nextProperNameType == null) {
			multiWord = prevLexiconPointer + " " + currentLexiconPointer + " "
					+ nextLexiconPointer;

			if (((AnalysisType) prevToken.getAnalysis().get(i)).getPrefix()
					.size() > 0) {
				prefixListsize = ((AnalysisType) prevToken.getAnalysis().get(i))
						.getPrefix().size();
				PrefixType pref = null;
				for (int l = 0; l < prefixListsize; l++) {
					pref = (PrefixType) ((AnalysisType) prevToken.getAnalysis()
							.get(i)).getPrefix().get(l);
					prefixList.add(pref);
				}

			}

			createMWWAnalysis(prevToken, prevBase,
					((Integer) prevAnalysisIndexList.get(i)).intValue(),
					multiWord, "1");
			createMWWAnalysis(token, currentBase, currentAnalysisIndex,
					multiWord, "2");
			for (int l = 0; l < prefixListsize; l++) {
				PrefixType pref = (PrefixType) prefixList.get(l);

				((AnalysisType) (prevToken.getAnalysis().get(i))).getPrefix()
						.add(pref);
			}
			createMWWAnalysis(nextToken, nextBase,
					((Integer) nextAnalysisIndexList.get(j)).intValue(),
					multiWord, "3");
		}

	}

	public void ProperNameAnalysisExists(TokenType prevToken, TokenType token,
			TokenType nextToken, int currentAnalysisIndex, BaseType currentBase)
			throws JAXBException {
		String multiWord = "";
		int prefixListsize = -1;
		List nextBaseList = null;
		List prevAnalysisIndexList = new ArrayList();
		List nextAnalysisIndexList = new ArrayList();
		List prevBaseList = getProperName(prevToken, prevAnalysisIndexList);
		List prefixList = new ArrayList();
		if (nextToken != null)
			nextBaseList = getProperName(nextToken, nextAnalysisIndexList);

		for (int i = 0; i < prevBaseList.size(); i++) {
			if (nextToken == null || nextBaseList.size() == 0) {
				BaseType prevBase = (BaseType) prevBaseList.get(i);

				String prevProperNameType = prevBase.getProperName().getType();

				String currentProperNameType = currentBase.getProperName()
						.getType();

				String prevLexiconPointer = prevBase.getLexiconItem();

				String currentLexiconPointer = currentBase.getLexiconItem();

				if (((AnalysisType) prevToken.getAnalysis().get(i)).getPrefix()
						.size() > 0) {
					prefixListsize = ((AnalysisType) prevToken.getAnalysis()
							.get(i)).getPrefix().size();
					PrefixType pref = null;
					for (int l = 0; l < prefixListsize; l++) {
						pref = (PrefixType) ((AnalysisType) prevToken
								.getAnalysis().get(i)).getPrefix().get(l);
					}

				}

				if (prevProperNameType.equals(currentProperNameType)
						|| prevProperNameType == null
						|| currentProperNameType == null) {
					multiWord = prevLexiconPointer + " "
							+ currentLexiconPointer;
					// System.out.println(multiWord);
					createMWWAnalysis(
							prevToken,
							prevBase,
							((Integer) prevAnalysisIndexList.get(i)).intValue(),
							multiWord, "1");
					createMWWAnalysis(token, currentBase, currentAnalysisIndex,
							multiWord, "2");
					if (nextToken != null)
						for (int l = 0; l < prefixListsize; l++) {
							AnalysisType nextAnalysis = (AnalysisType) nextToken
									.getAnalysis().get(currentAnalysisIndex);

							PrefixType pref = (PrefixType) nextAnalysis
									.getPrefix().get(l);

							((AnalysisType) (token.getAnalysis()
									.get(currentAnalysisIndex))).getPrefix()
									.add(pref);
						}

				}
			} else {
				if (nextToken != null) {
					int nextBaseListSize = nextBaseList.size();
					for (int j = 0; j < nextBaseListSize; j++) {
						if (((AnalysisType) nextToken.getAnalysis().get(
								((Integer) nextAnalysisIndexList.get(j))
										.intValue())).getPrefix().size() == 0)
							processProperNameToken(i, j, prevBaseList,
									nextBaseList, currentBase, token,
									prevToken, nextToken, currentAnalysisIndex,
									prevAnalysisIndexList,
									nextAnalysisIndexList);
					}
				}
			}

		}

	}

	// ------------------------------------------------------------------------------------------------------
	public void removeAnalysisByPos(TokenType token, String pos) {
		List analysesList = token.getAnalysis();
		int analysisListSize = analysesList.size();
		for (int i = 0; i < analysisListSize; i++) {
			AnalysisType analysis = (AnalysisType) analysesList.get(i);
			BaseType base = analysis.getBase();
			if (base != null) {
				MWEType mw = base.getMWE();
				if (mw != null) {
					String currentPos = mw.getPos();
					String id = mw.getId();
					if (id.equals("1") && !currentPos.equals(pos)) {
						token.getAnalysis().remove(i);
						break;
					}
				}
			}
		}

	}

	public boolean removeAnalysisByPrevId(TokenType prevToken,
			String expectedPrevId) {
		boolean rt = false;
		List analysesList = prevToken.getAnalysis();
		int analysisListSize = analysesList.size();
		for (int i = 0; i < analysisListSize; i++) {
			AnalysisType analysis = (AnalysisType) analysesList.get(i);
			BaseType base = analysis.getBase();
			if (base != null) {
				MWEType mw = base.getMWE();
				if (mw != null) {
					String id = mw.getId();
					if (id.equals(expectedPrevId)) {
						rt = true;
						break;
					}
				}
			}
		}
		return rt;
	}

	// public void process(InputStream morphXMLanalysis, PrintWriter pw) {
	// System.out.println("starting process");
	// ArticleType article = null;
	// ParagraphType paragraph = null;
	// SentenceType sentence = null;
	// TokenType token = null;
	// AnalysisType analysis = null;
	// List paragraphList = null;
	// int paragraphs = 0;
	// List sentenceList = null;
	// List tokenList = null;
	// List analysesList = null;
	// List articles = null;
	// String nextTokenMWIdList = "";
	// int tokenIndex = -1;
	//
	// CorpusAnalysisReader reader = new CorpusAnalysisReader();
	// reader.parse(morphXMLanalysis);
	//
	// if (reader == null) {
	// System.out.println("input to postProcessor is null - existing now");
	// return;
	// }
	// articles = reader.getArticle();
	// article = (ArticleType) articles.get(0); // a);
	// paragraphList = article.getParagraph();
	// int paragraphListSize = paragraphList.size();
	// System.out.println("paragraphListSize=" + paragraphListSize);
	// for (int paragraphIndex = 0; paragraphIndex < paragraphListSize;
	// paragraphIndex++) {
	// paragraph = (ParagraphType) paragraphList.get(paragraphIndex); // p);
	// sentenceList = paragraph.getSentence();
	// int sentenceListSize = sentenceList.size();
	// for (int sentenceIndex = 0; sentenceIndex < sentenceListSize;
	// sentenceIndex++) {
	// String nextTokenTransliterated = "";
	// sentence = (SentenceType) sentenceList.get(sentenceIndex); // s);
	// tokenList = sentence.getToken();
	// int tokenListSize = tokenList.size();
	// System.out.println("tokenListSize=" + tokenListSize);
	// for (tokenIndex = 0; tokenIndex < tokenListSize; tokenIndex++) {
	// token = (TokenType) tokenList.get(tokenIndex);
	// if (tokenIndex + 1 < tokenListSize) {
	// TokenType nextToken = (TokenType) tokenList
	// .get(tokenIndex + 1);
	// nextTokenTransliterated = Translate.Heb2Eng(nextToken
	// .getSurface());
	// nextTokenMWIdList = checkNextToken(nextToken,
	// tokenListSize);
	// } else {
	// nextTokenMWIdList = "";
	// nextTokenTransliterated = "";
	// }
	// analysesList = token.getAnalysis();
	// int analysisListSize = analysesList.size();
	// for (int analysisIndex = 0; analysisIndex < analysisListSize;
	// analysisIndex++) {
	// analysis = (AnalysisType) analysesList
	// .get(analysisIndex);
	// MWEType mweType = analysis.getBase().getMWE();
	// if (mweType != null) {
	// String pos = analysis.getBase().getMWE().getPos();
	// String id = analysis.getBase().getMWE().getId();
	// String transliterated = Translate.Heb2Eng(token
	// .getSurface());
	// String consecutive = analysis.getBase().getMWE()
	// .getConsecutive();
	//
	// removeAnalysisFlag = false;
	// // System.out.println(token.getSurface());
	// // ביטוי עם מזהה שונה מ1 לא יכול להיות בראש משפט
	// if (tokenIndex == 0 && !id.equals("1"))
	// removeAnalysisFlag = true;
	// // ביטוי עם מזהה שווה ל-1 לא יופיע בסוף משפט
	// else if ((tokenIndex == tokenListSize - 1)
	// && id.equals("1")) {
	// removeAnalysisFlag = true;
	// // אם באמצע משפט יש ביטוי עם מזהה 1 נבדוק האם
	// // בתמנית
	// // הבאה יש ביטוי עם מזהה 2
	// } else if (consecutive.equals("true")) {
	// int nextId = Integer.parseInt(id) + 1;
	// String nextIdStr = String.valueOf(nextId);
	// // if (!nextTokenMWIdList.equals("")){
	// // if( nextTokenMWIdList.indexOf(nextIdStr) ==
	// // -1)
	// // removeAnalysisFlag = true;
	// // else
	// if (!checkMWEBaseForm(transliterated,
	// nextTokenTransliterated, id, nextIdStr,
	// nextTokenMWIdList, tokenIndex))
	// removeAnalysisFlag = true;
	// // }
	// }
	//
	// if (removeAnalysisFlag)
	// token.getAnalysis().remove(analysisIndex);
	// }
	//
	// }
	//
	// }
	// }
	// }
	// reader.save(pw);
	// }

	// ----------------------------------------------------------------------------------------------------------------------------
	private void setBase(BaseType base, String transliteratedLexiocnItem,
			String lexiconItem, String lexiconPointer, String dottedLexiconItem) {
		base.setTransliteratedLexiconItem(transliteratedLexiocnItem);
		base.setLexiconItem(lexiconItem);
		base.setLexiconPointer(lexiconPointer);
		if (dottedLexiconItem != null && dottedLexiconItem.length() > 0)
			base.setDottedLexiconItem(dottedLexiconItem);
		else
			base.setDottedLexiconItem(null);
	}
}
