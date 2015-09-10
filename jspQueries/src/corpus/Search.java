package corpus;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.StringTokenizer;
import java.util.Vector;

import sql.SqlDB;

public class Search {

	static final String[] prefixesNonDefiniteNoun = { "%D7%91",// b
			"%D7%A9",// e
			"%D7%A9%D7%91",// eb
			"%D7%A9%D7%9B",// ek
			"%D7%A9%D7%9B%D7%A9",// eke
			"%D7%A9%D7%9B%D7%A9%D7%91",// ekeb
			// ekek
			"%D7%A9%D7%9B%D7%A9%D7%9C",// ekel
			"%D7%A9%D7%9B%D7%A9%D7%9E",// ekem
			"%D7%A9%D7%9C",// el
			"%D7%A9%D7%9E",// em
			"%D7%A9%D7%9E%D7%A9",// eme
			"%D7%A9%D7%9E%D7%A9%D7%91",// emeb
			"%D7%A9%D7%9E%D7%A9%D7%9B",// emek
			"%D7%A9%D7%9E%D7%A9%D7%9C",// emel
			"%D7%A9%D7%9E%D7%A9%D7%9E",// emem
			"%D7%9B",// k
			"%D7%9B%D7%A9",// ke
			"%D7%9B%D7%A9%D7%91",// keb
			"%D7%9B%D7%A9%D7%9B",// kek
			"%D7%9B%D7%A9%D7%9C",// kel
			"%D7%9B%D7%A9%D7%9E",// kem
			"%D7%9C",// l
			// lk
			"%D7%9C%D7%9B%D7%A9",// lke
			"%D7%9C%D7%9B%D7%A9%D7%91",// lkeb
			"%D7%9C%D7%9B%D7%A9%D7%9B",// lkek
			"%D7%9C%D7%9B%D7%A9%D7%9C",// lkel
			"%D7%9C%D7%9B%D7%A9%D7%9E",// lkem
			"%D7%9E",// m
			"%D7%9E%D7%A9",// me
			"%D7%9E%D7%A9%D7%91",// meb
			"%D7%9E%D7%A9%D7%9B",// mek
			"%D7%9E%D7%A9%D7%9C",// mel
			"%D7%9E%D7%A9%D7%9E",// mem
			"%D7%95",// w
			"%D7%95%D7%91",// wb
			"%D7%95%D7%A9",// we
			"%D7%95%D7%A9%D7%91",// web
			"%D7%95%D7%A9%D7%9B",// wek
			"%D7%95%D7%A9%D7%9B%D7%A9",// weke
			"%D7%95%D7%A9%D7%9B%D7%A9%D7%91",// wekeb
			// wekek
			"%D7%95%D7%A9%D7%9B%D7%A9%D7%9C",// wekel
			"%D7%95%D7%A9%D7%9B%D7%A9%D7%9E",// wekem
			"%D7%95%D7%A9%D7%9C",// wel
			"%D7%95%D7%A9%D7%9E",// wem
			"%D7%95%D7%A9%D7%9E%D7%A9",// weme
			"%D7%95%D7%A9%D7%9E%D7%A9%D7%91",// wemeb
			"%D7%95%D7%A9%D7%9E%D7%A9%D7%9B",// wemek
			"%D7%95%D7%A9%D7%9E%D7%A9%D7%9C",// wemel
			"%D7%95%D7%A9%D7%9E%D7%A9%D7%9E",// wemem
			"%D7%95%D7%A9%D7%9E%D7%9B",// wemk
			"%D7%95%D7%9B",// wk
			"%D7%95%D7%9B%D7%A9",// wke
			"%D7%95%D7%9B%D7%A9%D7%91",// wkeb
			"%D7%95%D7%9B%D7%A9%D7%9B",// wkek
			"%D7%95%D7%9B%D7%A9%D7%9C",// wkel
			"%D7%95%D7%9B%D7%A9%D7%9E",// wkem
			"%D7%95%D7%9C",// wl
			"%D7%95%D7%9C%D7%9B%D7%A9",// wlke
			"%D7%95%D7%9C%D7%9B%D7%A9%D7%91",// wlkeb
			"%D7%95%D7%9C%D7%9B%D7%A9%D7%9B",// wlkek
			"%D7%95%D7%9C%D7%9B%D7%A9%D7%9C",// wlkel
			"%D7%95%D7%9C%D7%9B%D7%A9%D7%9E",// wlkem
			"%D7%95%D7%9E",// wm
			"%D7%95%D7%9E%D7%A9",// wme
			"%D7%95%D7%9E%D7%A9%D7%91",// wmeb
			"%D7%95%D7%9E%D7%A9%D7%9B",// wmek
			"%D7%95%D7%9E%D7%A9%D7%9C",// wmel
			"%D7%95%D7%9E%D7%A9%D7%9C%D7%9B"// wmelk
	// wmelm

	};

	static final int prefixesNonDefiniteSize = prefixesNonDefiniteNoun.length;

	static final String[] prefixesDefiniteNoun = { 
			"%D7%A9",// e
			"%D7%A9%D7%9B%D7%A9",// eke
			"%D7%A9%D7%9B%D7%A9%D7%9E",// ekem
			"%D7%A9%D7%9E",// em
			"%D7%A9%D7%9E%D7%A9",// eme
			"%D7%A9%D7%9E%D7%A9%D7%9E",// emem
			"%D7%9B%D7%A9",// ke
			"%D7%9B%D7%A9%D7%9E",// kem
			"%D7%9C%D7%9B%D7%A9",// lke
			"%D7%9C%D7%9B%D7%A9%D7%9E",// lkem
			"%D7%9E",// m
			"%D7%9E%D7%A9",// me
			"%D7%9E%D7%A9%D7%9E",// mem
			"%D7%95",// w
			"%D7%95%D7%A9",// we
			"%D7%95%D7%A9%D7%9B%D7%A9",// weke
			"%D7%95%D7%A9%D7%9B%D7%A9%D7%9E",// wekem
			"%D7%95%D7%A9%D7%9E",// wem
			"%D7%95%D7%A9%D7%9E%D7%A9",// weme
			"%D7%95%D7%A9%D7%9E%D7%A9%D7%9E",// wemem
			"%D7%95%D7%9B%D7%A9",// wke
			"%D7%95%D7%9B%D7%A9%D7%9E",// wkem
			"%D7%95%D7%9C%D7%9B%D7%A9",// wlke
			"%D7%95%D7%9C%D7%9B%D7%A9%D7%9E",// wlkem
			"%D7%95%D7%9E",// wm
			"%D7%95%D7%9E%D7%A9",// wme
	};

	static final int prefixesDefiniteSize = prefixesDefiniteNoun.length;

	public static int countApprox(String corpus, String encodedSurface) throws SQLException {
		return countSimple(corpus, encodedSurface, ">=");
	}

	public static int countSimple(String corpus, String encodedSurface, String op)
			throws SQLException {
		StringTokenizer st = new StringTokenizer(encodedSurface, "+");
		String transliterated1 = "";
		String transliterated2 = "";
		try {
			transliterated1 = Translate.Heb2Eng(URLDecoder.decode(st.nextToken(), "UTF-8"));
			transliterated2 = Translate.Heb2Eng(URLDecoder.decode(st.nextToken(), "UTF-8"));
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String query = String.format(
				  " SELECT count(*) AS size"
				+ " FROM %2$s as t1, %2$s as t2, %1$s AS c "
				+ " WHERE t1.transliterated = ? AND t2.transliterated like ? "
				+ " AND t1.corpusId = c.id and t2.corpusId = t1.corpusId AND t2.tokenId %3$s t1.tokenId +1",
				corpus, corpus	+ "Tokens", op);
		return doCount(query,  transliterated1, transliterated2+"%");
	}
	
	public static int doCount(String query, Object... objs) throws SQLException {
		try (Connection connection = SqlDB.corpus.getConnection(SqlDB.User.tmir)) {
			try (PreparedStatement stmt = connection.prepareStatement(query)) {
				for (int i=1; i <= objs.length; i++)
					stmt.setObject(i, objs[i-1]);
				try (ResultSet rs = stmt.executeQuery()){
					rs.next();
					return rs.getInt("size");
				}				
			}
		}
	}
	
	public static int count(String corpus, String encodedSurface) throws SQLException {
		if (new StringTokenizer(encodedSurface, "+").countTokens() > 1) {
			return countSimple(corpus, encodedSurface, "=");
		} 
		String query = String.format(
				" SELECT count(*) AS size "
						+ " FROM %2$s AS t1, %1$s AS t2"
						+ " WHERE t2.id = t1.corpusId AND token = ?",
				corpus, corpus + "Tokens");
		return doCount(query, encodedSurface);
	}

	public static int countMorph(String corpus, String encodedSurface) throws SQLException {
		String query = String.format(
				" SELECT count(*) AS size "
						+ " FROM %2$s AS t1, %1$s AS t2 "
						+ " WHERE t2.id = t1.corpusId AND token IN (?)",
				corpus, corpus + "Tokens");
		return doCount(query, encodedSurface);
	}

	public static Vector<InflectionsSearchResultRec> getPos(String encodedSurface, Vector<String> resVec)
			throws SQLException, UnsupportedEncodingException {
		ResultSet rs = null;
		PreparedStatement statementPos;
		PreparedStatement statement = null;
		Vector<InflectionsSearchResultRec> vec = new Vector<>();

		String tense;
		String suffixFunction;
		String inflections;
		String surface;
		String suffixNumber;
		String baseNumber;
		String baseGender;
		String baseDefinitness;
		String accusativeNominativeNumber;
		StringBuffer searchRes;
		Connection connection = SqlDB.generatorTest.getConnection(SqlDB.User.dandy);
		Connection connectionLexicon = SqlDB.lexiconP.getConnection(SqlDB.User.maya);
		statementPos = connectionLexicon
				.prepareStatement("select undotted, pos, id from item where undotted=?");
		statementPos.setString(1, encodedSurface);
		ResultSet rsPos = statementPos.executeQuery();
		while (rsPos.next()) {
			InflectionsSearchResultRec rc = new InflectionsSearchResultRec();
			String pos = rsPos.getString("pos");
			String lexiconId = rsPos.getString("id");

			switch (pos) {
				case "verb":
				case "participle":

					StringBuilder inflectionsPast = new StringBuilder("<b>עבר:</b> ");
					StringBuilder inflectionsFuture = new StringBuilder(
							"<br><b>עתיד:</b> ");
					StringBuilder inflectionsParticiple = new StringBuilder(
							"<br><b>הווה:</b> ");
					StringBuilder inflectionsInflectedParticipleSingular = new StringBuilder(
							"<br><b> הווה נטוי:</b> ");
					StringBuilder inflectionsInflectedParticiplePlural = new StringBuilder();
					StringBuilder inflectionsImperative = new StringBuilder(
							"<br><b>ציווי:</b> ");
					StringBuilder inflectionsInfinitiveSingular = new StringBuilder(
							"<br><b>שם פועל:</b> ");
					StringBuilder inflectionsInfinitivePlural = new StringBuilder(
							"<br>");
					StringBuilder inflectionsBareInfinitive = new StringBuilder(
							"<br><b>מקור:</b> ");
					searchRes = new StringBuffer();
					statement = connection
							.prepareStatement("select  suffixNumber ,surface, baseLexiconPointer,tense, suffixFunction, accusativeNominativeNumber from inflections where   baseLexiconPointer=? ");
					statement.setString(1, lexiconId);
					rs = statement.executeQuery();
					rc.setPos(pos);
					rc.setLexiconId(lexiconId);
					while (rs.next()) {
						tense = rs.getString("tense");
						suffixFunction = rs.getString("suffixFunction");
						surface = rs.getString("surface");
						suffixNumber = rs.getString("suffixNumber");
						accusativeNominativeNumber = rs
								.getString("accusativeNominativeNumber");
						if (tense.equals("past")) {
							inflectionsPast.append(surface).append(",");
							for (int i = 0; i < prefixesDefiniteSize; i++) {
								if (i % 10 == 0)
									inflectionsPast.append("<br>");
								String prefix = prefixesDefiniteNoun[i];
								searchRes.append(prefix).append(surface)
										.append(", ");
								inflectionsPast.append(prefix).append(surface)
										.append(", ");
							}
							// קניין
						} else if (tense.equals("beinoni")
								&& suffixFunction.equals("unspecified")) {
							inflectionsParticiple.append(surface).append(",");
							for (int i = 0; i < prefixesDefiniteSize; i++) {
								if (i % 10 == 0)
									inflectionsParticiple.append("<br>");
								String prefix = prefixesDefiniteNoun[i];
								searchRes.append(prefix).append(surface)
										.append(", ");
								inflectionsParticiple.append(prefix)
										.append(surface).append(", ");
							}
						} else if (tense.equals("beinoni")
								&& !suffixFunction.equals("unspecified")
								&& suffixNumber.equals("singular")) {
							inflectionsInflectedParticipleSingular.append(surface)
									.append(",");
							for (int i = 0; i < prefixesNonDefiniteSize; i++) {
								if (i % 10 == 0)
									inflectionsInflectedParticipleSingular
											.append("<br>");
								String prefix = prefixesNonDefiniteNoun[i];
								searchRes.append(prefix).append(surface)
										.append(", ");
								inflectionsInflectedParticipleSingular
										.append(prefix).append(surface)
										.append(", ");
							}
						} else if (tense.equals("beinoni")
								&& !suffixFunction.equals("unspecified")
								&& suffixNumber.equals("plural")) {
							inflectionsInflectedParticiplePlural.append(surface)
									.append(",");
							for (int i = 0; i < prefixesNonDefiniteSize; i++) {
								if (i % 10 == 0)
									inflectionsInflectedParticiplePlural
											.append("<br>");
								String prefix = prefixesNonDefiniteNoun[i];
								searchRes.append(prefix).append(surface)
										.append(", ");
								inflectionsInflectedParticiplePlural.append(prefix)
										.append(surface).append(", ");
							}
						} else if (tense.equals("future")) {
							inflectionsFuture.append(surface).append(",");
							for (int i = 0; i < prefixesDefiniteSize; i++) {
								if (i % 10 == 0)
									inflectionsFuture.append("<br>");
								String prefix = prefixesDefiniteNoun[i];
								searchRes.append(prefix).append(surface)
										.append(", ");
								inflectionsFuture.append(prefix).append(surface)
										.append(", ");
							}
						} else if (tense.equals("imperative")) {
							inflectionsImperative.append(surface).append(",");
							for (int i = 0; i < prefixesDefiniteSize; i++) {
								if (i % 10 == 0)
									inflectionsImperative.append("<br>");
								String prefix = prefixesDefiniteNoun[i];
								searchRes.append(prefix).append(surface)
										.append(", ");
								inflectionsImperative.append(prefix)
										.append(surface).append(", ");
							}
						} else if (tense.equals("infinitive")
								&& accusativeNominativeNumber.equals("singular")) {
							inflectionsInfinitiveSingular.append(surface).append(
									",");
							for (int i = 0; i < prefixesDefiniteSize; i++) {
								if (i % 10 == 0)
									inflectionsInfinitiveSingular.append("<br>");
								String prefix = prefixesDefiniteNoun[i];
								searchRes.append(prefix).append(surface)
										.append(", ");
								inflectionsInfinitiveSingular.append(prefix)
										.append(surface).append(", ");
							}
						} else if (tense.equals("infinitive")
								&& accusativeNominativeNumber.equals("plural")) {
							inflectionsInfinitivePlural.append(surface).append(",");
							for (int i = 0; i < prefixesDefiniteSize; i++) {
								if (i % 10 == 0)
									inflectionsInfinitivePlural.append("<br>");
								String prefix = prefixesDefiniteNoun[i];
								searchRes.append(prefix).append(surface)
										.append(", ");
								inflectionsInfinitivePlural.append(prefix)
										.append(surface).append(", ");
							}
						} else if (tense.equals("bareInfinitive")) {
							inflectionsBareInfinitive.append(surface).append(",");
							for (int i = 0; i < prefixesDefiniteSize; i++) {
								if (i % 10 == 0)
									inflectionsBareInfinitive.append("<br>");
								String prefix = prefixesDefiniteNoun[i];
								searchRes.append(prefix).append(surface)
										.append(", ");
								inflectionsBareInfinitive.append(prefix)
										.append(surface).append(", ");
							}
						}
						searchRes.append(surface).append(",");
					}
					String strInflectionsPast = inflectionsPast.toString();
					String strInflectionsFuture = inflectionsFuture.toString();
					String strInflectionsImperative = inflectionsImperative
							.toString();
					String strInflectionsInfinitiveSingular = inflectionsInfinitiveSingular
							.toString();
					String strInflectionsInfinitivePlural = inflectionsInfinitivePlural
							.toString();
					String strInflectionsParticiple = inflectionsParticiple
							.toString();
					String strInflectionsInflectedParticipleSingular = inflectionsInflectedParticipleSingular
							.toString();
					String strInflectionsInflectedParticiplePlural = inflectionsInflectedParticiplePlural
							.toString();
					String strInflectionsBareInfinitive = inflectionsBareInfinitive
							.toString();

					inflections = strInflectionsPast.substring(0,strInflectionsPast.length() - 1)
							+ strInflectionsParticiple.substring(0,strInflectionsParticiple.length() - 1)
							+ strInflectionsInflectedParticipleSingular.substring(0, strInflectionsInflectedParticipleSingular.length() - 1)
							+ strInflectionsInflectedParticiplePlural.substring(0,strInflectionsInflectedParticiplePlural.length() - 1)
							+ strInflectionsFuture.substring(0,strInflectionsFuture.length() - 1)
							+ strInflectionsImperative.substring(0,strInflectionsImperative.length() - 1)
							+ strInflectionsInfinitiveSingular.substring(0,strInflectionsInfinitiveSingular.length() - 1)
							+ strInflectionsInfinitivePlural.substring(0,strInflectionsInfinitivePlural.length() - 1)
							+ strInflectionsBareInfinitive.substring(0,strInflectionsBareInfinitive.length() - 1);

					String searchResults = searchRes.toString();
					searchResults = searchResults.substring(0,
							searchResults.length() - 2);

					rc.setSurface((URLDecoder.decode(inflections.trim(), "UTF-8")));
					vec.add(rc);
					resVec.add(searchResults);

					break;
				case "noun": {
					rc = new InflectionsSearchResultRec();
					StringBuilder inflectionsBaseSingularMasculine = new StringBuilder("<br><b>נפרד/נסמך:</b><br> ");
					StringBuilder inflectionsBaseSingularFeminine = new StringBuilder("<br>");
					StringBuilder inflectionsBasePluralMasculine = new StringBuilder("<br>");
					StringBuilder inflectionsBasePluralFeminine = new StringBuilder("<br>");
					StringBuilder inflectionsPGNSingular = new StringBuilder("<br><b>קניין:</b><br>");
					StringBuilder inflectionsDefinite = new StringBuilder("<br><b>מיודע:</b><br>");
					StringBuilder inflectionsPGNPlural = new StringBuilder("<br>");
					searchRes = new StringBuffer();
					statement = connection
							.prepareStatement("select basePos, surface , baseLexiconPointer, suffixFunction, suffixNumber, baseNumber, baseGender, baseDefinitness, suffixStatus  from inflections where   baseLexiconPointer=? ");
					statement.setString(1, lexiconId);
					rs = statement.executeQuery();
					rc.setPos(pos);
					rc.setLexiconId(lexiconId);
					while (rs.next()) {
						suffixFunction = rs.getString("suffixFunction");
						suffixNumber = rs.getString("suffixNumber");
						baseNumber = rs.getString("baseNumber");
						baseGender = rs.getString("baseGender");
						baseDefinitness = rs.getString("baseDefinitness");
						rs.getString("suffixStatus");
						surface = rs.getString("surface");
						if (suffixFunction.equals("unspecified")
								&& baseDefinitness.equals("tt")) {
							inflectionsDefinite.append(surface).append(",");
							searchRes.append(surface).append(",");
							for (int i = 0; i < prefixesDefiniteSize; i++) {
								if (i % 10 == 0)
									inflectionsDefinite.append("<br>");
								String prefix = prefixesDefiniteNoun[i];
								searchRes.append(prefix).append(surface)
										.append(", ");
								inflectionsDefinite.append(prefix).append(surface)
										.append(", ");
							}
						} else if (suffixFunction.equals("unspecified")
								&& baseDefinitness.equals("tf")) {
							if (baseGender.equals("masculine")
									&& baseNumber.equals("singular")
									&& (inflectionsBaseSingularMasculine
									.indexOf(surface) == -1)) {
								inflectionsBaseSingularMasculine.append(surface)
										.append(", ");
								searchRes.append(surface).append(", ");
								for (int i = 0; i < prefixesNonDefiniteSize; i++) {
									if (i % 10 == 0)
										inflectionsBaseSingularMasculine
												.append("<br>");
									String prefix = prefixesNonDefiniteNoun[i];
									inflectionsBaseSingularMasculine.append(prefix)
											.append(surface).append(",");
									searchRes.append(prefix).append(surface)
											.append(", ");

								}

							} else if (baseGender.equals("feminine")
									&& baseNumber.equals("singular")
									&& (inflectionsBaseSingularFeminine
									.indexOf(surface) == -1)) {
								inflectionsBaseSingularFeminine.append(surface)
										.append(", ");
								searchRes.append(surface).append(",");

								for (int i = 0; i < prefixesNonDefiniteSize; i++) {
									if (i % 10 == 0)
										inflectionsBaseSingularFeminine
												.append("<br>");
									String prefix = prefixesNonDefiniteNoun[i];
									searchRes.append(prefix).append(surface)
											.append(", ");
									inflectionsBaseSingularFeminine.append(prefix)
											.append(surface).append(", ");
								}

							} else if (baseGender.equals("masculine")
									&& baseNumber.equals("plural")
									&& (inflectionsBasePluralMasculine
									.indexOf(surface) == -1)) {
								inflectionsBasePluralMasculine.append(surface)
										.append(", ");
								searchRes.append(surface).append(",");
								for (int i = 0; i < prefixesNonDefiniteSize; i++) {
									if (i % 10 == 0)
										inflectionsBasePluralMasculine
												.append("<br>");
									String prefix = prefixesNonDefiniteNoun[i];
									searchRes.append(prefix).append(surface)
											.append(", ");
									inflectionsBasePluralMasculine.append(prefix)
											.append(surface).append(", ");
								}

							} else if (baseGender.equals("feminine")
									&& baseNumber.equals("plural")
									&& (inflectionsBasePluralFeminine
									.indexOf(surface) == -1)) {
								inflectionsBasePluralFeminine.append(surface)
										.append(", ");
								searchRes.append(surface).append(", ");
								for (int i = 0; i < prefixesNonDefiniteSize; i++) {
									if (i % 10 == 0)
										inflectionsBasePluralFeminine
												.append("<br>");
									String prefix = prefixesNonDefiniteNoun[i];
									searchRes.append(prefix).append(surface)
											.append(", ");
									inflectionsBasePluralFeminine.append(prefix)
											.append(surface).append(", ");
								}
							}

							// PGN
						} else if (!suffixFunction.equals("unspecified")
								&& suffixNumber.equals("singular")) {

							inflectionsPGNSingular.append(surface).append(", ");
							searchRes.append(surface).append(", ");

							for (int i = 0; i < prefixesNonDefiniteSize; i++) {
								if (i % 10 == 0)
									inflectionsPGNSingular.append("<br>");
								String prefix = prefixesNonDefiniteNoun[i];
								searchRes.append(prefix).append(surface)
										.append(", ");
								inflectionsPGNSingular.append(prefix)
										.append(surface).append(", ");
							}
						} else if (!suffixFunction.equals("unspecified")
								&& suffixNumber.equals("plural")) {

							inflectionsPGNPlural.append(surface).append(", ");
							searchRes.append(surface).append(", ");
							for (int i = 0; i < prefixesNonDefiniteSize; i++) {
								if (i % 10 == 0)
									inflectionsPGNPlural.append("<br>");
								String prefix = prefixesNonDefiniteNoun[i];
								searchRes.append(prefix).append(surface)
										.append(", ");
								inflectionsPGNPlural.append(prefix).append(surface)
										.append(", ");
							}
						}
					}

					String strInflectionsBaseSingularMasculine = inflectionsBaseSingularMasculine
							.toString();
					String strInflectionsBaseSingularFeminine = inflectionsBaseSingularFeminine
							.toString();
					String strInflectionsBasePluralMasculine = inflectionsBasePluralMasculine
							.toString();
					String strInflectionsBasePluralFeminine = inflectionsBasePluralFeminine
							.toString();
					String strInflectionsDefinite = inflectionsDefinite.toString();
					String strInflectionsPGNSingular = inflectionsPGNSingular
							.toString();
					String strInflectionsPGNPlural = inflectionsPGNPlural
							.toString();

					inflections = strInflectionsBaseSingularMasculine.substring(0,
							strInflectionsBaseSingularMasculine.length() - 1)
							+ strInflectionsBaseSingularFeminine
							.substring(0,
									strInflectionsBaseSingularFeminine
											.length() - 1)
							+ strInflectionsBasePluralMasculine.substring(0,
							strInflectionsBasePluralMasculine.length() - 1)
							+ strInflectionsBasePluralFeminine.substring(0,
							strInflectionsBasePluralFeminine.length() - 1)
							+ strInflectionsDefinite.substring(0,
							strInflectionsDefinite.length() - 1)
							+ strInflectionsPGNSingular.substring(0,
							strInflectionsPGNSingular.length() - 1)
							+ strInflectionsPGNPlural.substring(0,
							strInflectionsPGNPlural.length() - 1);
					String strSearchResults = searchRes.toString();

					rc.setSurface((URLDecoder.decode(inflections, "UTF-8")));
					vec.add(rc);
					resVec.add(strSearchResults);

					break;
				}
				case "adjective": {
					rc = new InflectionsSearchResultRec();
					StringBuilder inflectionsBase = new StringBuilder(
							"<b>נפרד/נסמך:</b><br> ");
					StringBuilder inflectionsDefinite = new StringBuilder(
							"<br><b>מיודע:</b><br> ");
					searchRes = new StringBuffer();
					statement = connection
							.prepareStatement("select basePos, surface, baseLexiconPointer, baseDefinitness from inflections where  baseLexiconPointer=? ");
					statement.setString(1, lexiconId);
					rs = statement.executeQuery();
					rc.setPos(pos);
					rc.setLexiconId(lexiconId);
					while (rs.next()) {
						baseDefinitness = rs.getString("baseDefinitness");
						surface = rs.getString("surface");
						if (baseDefinitness.equals("tf")) {
							inflectionsBase.append(surface).append(",");
							searchRes.append(surface).append(",");

							for (int i = 0; i < prefixesNonDefiniteSize; i++) {
								if (i % 10 == 0)
									inflectionsBase.append("<br>");
								String prefix = prefixesNonDefiniteNoun[i];
								searchRes.append(prefix).append(surface)
										.append(", ");
								inflectionsBase.append(prefix).append(surface)
										.append(", ");
							}
						} else {
							inflectionsDefinite.append(surface).append(",");
							searchRes.append(surface).append(", ");

							for (int i = 0; i < prefixesDefiniteSize; i++) {
								if (i % 10 == 0)
									inflectionsDefinite.append("<br>");
								String prefix = prefixesDefiniteNoun[i];
								searchRes.append(prefix).append(surface)
										.append(", ");
								inflectionsDefinite.append(prefix).append(surface)
										.append(", ");
							}
						}
					}
					String strInflectionsBase = (inflectionsBase.toString());
					String strInflectionsDefinite = (inflectionsDefinite.toString());
					strInflectionsBase = strInflectionsBase.substring(0,
							strInflectionsBase.length() - 1);
					strInflectionsDefinite = strInflectionsDefinite.substring(0,
							strInflectionsDefinite.length() - 1);
					inflections = strInflectionsBase + strInflectionsDefinite;
					String strSearchResults = searchRes.toString();

					rc.setSurface((URLDecoder.decode(inflections, "UTF-8")));
					vec.add(rc);
					resVec.add(strSearchResults);
					// adver/conjunction
					break;
				}
				default: {
					rc = new InflectionsSearchResultRec();
					StringBuilder inflectionsBase = new StringBuilder();
					StringBuilder inflectionsDefinite = new StringBuilder(
							"<br><b>מיודע:</b><br> ");
					searchRes = new StringBuffer();
					statement = connection
							.prepareStatement("select  surface, baseLexiconPointer, baseDefinitness from inflections where  baseLexiconPointer=? ");
					statement.setString(1, lexiconId);
					rs = statement.executeQuery();
					rc.setPos(pos);
					rc.setLexiconId(lexiconId);
					while (rs.next()) {
						surface = rs.getString("surface");
						baseDefinitness = rs.getString("baseDefinitness");
						if (baseDefinitness.equals("tt")) {
							inflectionsDefinite.append(surface).append(", ");
							searchRes.append(surface).append(", ");
							for (int i = 0; i < prefixesDefiniteSize; i++) {
								if (i % 10 == 0)
									inflectionsDefinite.append("<br>");
								String prefix = prefixesDefiniteNoun[i];
								searchRes.append(prefix).append(surface)
										.append(", ");
								inflectionsDefinite.append(prefix).append(surface)
										.append(", ");
							}
						} else {
							inflectionsBase.append(surface).append(", ");
							searchRes.append(surface).append(", ");
							for (int i = 0; i < prefixesNonDefiniteSize; i++) {
								if (i % 10 == 0)
									inflectionsBase.append("<br>");
								String prefix = prefixesNonDefiniteNoun[i];
								searchRes.append(prefix).append(surface)
										.append(", ");
								inflectionsBase.append(prefix).append(surface)
										.append(", ");
							}
						}
					}
					String strInflectionsBase = (inflectionsBase.toString());
					String strInflectionsDefinite = (inflectionsBase.toString());
					strInflectionsBase = strInflectionsBase.substring(0,
							strInflectionsBase.length() - 1);
					strInflectionsDefinite = strInflectionsDefinite.substring(0,
							strInflectionsDefinite.length() - 1);
					inflections = strInflectionsBase + strInflectionsDefinite;
					String strSearchResults = searchRes.toString();
					rc.setSurface((URLDecoder.decode(inflections, "UTF-8")));
					vec.add(rc);
					resVec.add(strSearchResults);
					break;
				}
			}

		}
		rsPos.close();
		rs.close();
		statementPos.close();
		statement.close();
		connection.close();
		connectionLexicon.close();
		return vec;
	}

	public static Vector<ResultRec> getApprox(String corpus, String encodedSurface,
			int start) throws SQLException, UnsupportedEncodingException {
		assertIsInt(start);
		Vector<ResultRec> vec = new Vector<>();
		StringTokenizer st = new StringTokenizer(encodedSurface, "+");
		st.countTokens();
		String token1 = st.nextToken();
		String token2 = st.nextToken();
		String transliterated1 = Translate.Heb2Eng(URLDecoder.decode(token1,"UTF-8"));
		String transliterated2 = Translate.Heb2Eng(URLDecoder.decode(token2,"UTF-8"));
		
		Connection connection = SqlDB.corpus.getConnection(SqlDB.User.tmir);
		Statement statement = connection.createStatement();
		ResultSet rs = statement
				.executeQuery("select t1.transliterated, t2.transliterated, paragraphId, sentenceId,  "
						+ "fileName, sentence, t1.tokenId as id1 , t2.tokenId as id2"
						+ " from "
						+ corpus
						+ "Tokens as t1, "
						+ corpus
						+ "Tokens as t2, "
						+ corpus
						+ " where t1.transliterated ='"
						+ transliterated1
						+ "'  and t2.transliterated  like '"
						+ transliterated2
						+ "%' and t1.corpusId ="
						+ corpus
						+ ".id and t2.corpusId=t1.corpusId  and t2.tokenId > t1.tokenId limit "
						+ start + ", 100");

		String tokenId1;
		String tokenId2;
		while (rs.next()) {
			ResultRec rc = new ResultRec();

			tokenId1 = rs.getString("id1");
			tokenId2 = rs.getString("id2");
			String sentenceId = rs.getString("sentenceId");
			String paragraphId = rs.getString("paragraphId");
			String file = rs.getString("fileName");
			file = file.replaceAll("//", "/");
			Blob sentence = rs.getBlob("sentence");
			byte[] bdata = sentence.getBytes(1, (int) sentence.length());
			String text = new String(bdata);
			String decodedSentence = URLDecoder.decode(text, "UTF-8");

			String decodedToken1 = URLDecoder.decode(token1, "UTF-8");
			String decodedToken2 = URLDecoder.decode(token2, "UTF-8");

			int index = decodedSentence.indexOf(decodedToken1);

			decodedSentence = (decodedSentence.substring(index)).replaceAll(" "
					+ decodedToken2, "<font color='red'><b> " + " "
					+ decodedToken2 + " </b></font>");

			decodedSentence = decodedSentence.replaceAll(decodedToken1 + " ",
					"<font color='red'><b> " + decodedToken1 + " </b></font>");
			rc.setTokenId1(tokenId1);
			rc.setTokenId2(tokenId2);
			rc.setParagraphId(paragraphId);
			rc.setSentenceId(sentenceId);
			rc.setDecodedSentence(decodedSentence);
			rc.setFile(file);
			vec.add(rc);
		}
		rs.close();
		statement.close();
		connection.close();
		return vec;
	}

	private static void assertIsInt(int start) {
		String stStart = String.valueOf(start);
		try {
			Integer.parseInt(stStart);
		} catch (NumberFormatException e) {
			System.out.println("You are trying to abuse corpus search start="
					+ start);
		}
	}

	public static Vector<ResultRec> get(String corpus, String encodedSurface, int start)
			throws SQLException, UnsupportedEncodingException {
		assertIsInt(start);
		Connection connection = null;
		Statement statement = null;
		ResultSet rs;
		Vector<ResultRec> vec = new Vector<>();
		try {
			
			connection = SqlDB.corpus.getConnection(SqlDB.User.tmir);
		} catch (Exception e) {
			e.printStackTrace();
			System.exit(0);
		}

		StringTokenizer st = new StringTokenizer(encodedSurface, "+");
		int tokensNum = st.countTokens();

		if (tokensNum > 1) {
			String token1 = st.nextToken();
			String token2 = st.nextToken();
			String transliterated1 = Translate.Heb2Eng(URLDecoder.decode(
					token1, "UTF-8"));
			String transliterated2 = Translate.Heb2Eng(URLDecoder.decode(
					token2, "UTF-8"));
			PreparedStatement pStatement = connection
					.prepareStatement("select t1.transliterated ,t2.transliterated   "
							+ ", paragraphId , sentenceId,  fileName, sentence, t1.tokenId as id1 , t2.tokenId as id2"
							+ " from ? as t1, "
							+ "? as t2, ? "
							+ " where t1.transliterated =' ?"
							+ "'  and t2.transliterated  like '?"
							+ "%' and t1.corpusId ="
							+ corpus
							+ ".id and t2.corpusId=t1.corpusId  and (t2.tokenId = t1.tokenId +1 )limit ? , 100");
			pStatement.setObject(1, corpus + "Tokens");
			pStatement.setObject(2, corpus + "Tokens");
			pStatement.setObject(3, corpus);
			pStatement.setObject(4, transliterated1);
			pStatement.setObject(5, transliterated2);
			pStatement.setObject(6, start);
			rs = pStatement.executeQuery();
		} else {
			statement = connection.createStatement();
			switch (corpus) {
				case "haaretz":
					rs = statement
							.executeQuery("SELECT *  FROM haaretzTokens, haaretz where haaretz.id=haaretzTokens.corpusId and token='"
									+ encodedSurface + "' limit " + start + ", 100");
					break;
				case "tapuz":
					rs = statement
							.executeQuery("SELECT *  FROM tapuzTokens, tapuz  where tapuz.id=tapuzTokens.corpusId and token='"
									+ encodedSurface + "' limit " + start + ", 100");
					break;
				case "theMarker":
					rs = statement
							.executeQuery("SELECT *  FROM theMarkerTokens, theMarker  where theMarker.id=theMarkerTokens.corpusId and token='"
									+ encodedSurface + "' limit " + start + ", 100");
					break;
				case "doctors":
					rs = statement
							.executeQuery("SELECT *  FROM doctorsTokens, doctors  where doctors.id=doctorsTokens.corpusId and token='"
									+ encodedSurface + "' limit " + start + ", 100");
					break;
				case "infomed":
					rs = statement
							.executeQuery("SELECT *  FROM infomedTokens, infomed  where infomed.id=infomedTokens.corpusId and token='"
									+ encodedSurface + "' limit " + start + ", 100");
					break;
				case "nature":
					rs = statement
							.executeQuery("SELECT *  FROM natureTokens, nature  where nature.id=natureTokens.corpusId and token='"
									+ encodedSurface + "' limit " + start + ", 100");
					break;
				case "bari":
					rs = statement
							.executeQuery("SELECT *  FROM bariTokens, bari  where bari.id=bariTokens.corpusId and token='"
									+ encodedSurface + "' limit " + start + ", 100");
					break;
				case "a7News":
					rs = statement
							.executeQuery("SELECT *  FROM a7NewsTokens, a7News  where a7News.id=a7NewsTokens.corpusId and token='"
									+ encodedSurface + "' limit " + start + ", 100");
					break;
				case "a7Articles":
					rs = statement
							.executeQuery("SELECT *  FROM a7ArticlesTokens, a7Articles  where a7Articles.id=a7ArticlesTokens.corpusId and token='"
									+ encodedSurface + "' limit " + start + ", 100");
					break;
				case "knesset":
					rs = statement
							.executeQuery("SELECT *  FROM knessetTokens, knesset  where knesset.id=knessetTokens.corpusId and token='"
									+ encodedSurface + "' limit " + start + ", 100");
					break;
				case "ta1":
					rs = statement
							.executeQuery("SELECT *  FROM taEstiTokens, taEsti  where taEsti.id=taEstiTokens.corpusId and token='"
									+ encodedSurface + "' limit " + start + ", 100");
					break;
				case "ta2":
					rs = statement
							.executeQuery("SELECT *  FROM taShlomoTokens, taShlomo  where taShlomo.id=taShlomoTokens.corpusId and token='"
									+ encodedSurface + "' limit " + start + ", 100");
					break;
				default:
					return vec;
			}
		}

		String surface;
		String tokenId = "";
		while (rs.next()) {
			ResultRec rc = new ResultRec();
			if (tokensNum == 1) {
				surface = rs.getString("token");
				URLDecoder.decode(surface, "UTF-8");
				tokenId = rs.getString("tokenId");
			}
			String sentenceId = rs.getString("sentenceId");
			String paragraphId = rs.getString("paragraphId");
			String file = rs.getString("fileName");
			file = file.replaceAll("//", "/");
			Blob sentence = rs.getBlob("sentence");
			byte[] bdata = sentence.getBytes(1, (int) sentence.length());
			String text = new String(bdata);
			String decodedSentence = URLDecoder.decode(text, "UTF-8");

			if (tokensNum == 1)
				rc.setTokenId(tokenId);
			rc.setParagraphId(paragraphId);
			rc.setSentenceId(sentenceId);
			rc.setDecodedSentence(decodedSentence);
			rc.setFile(file);
			vec.add(rc);
		}
		rs.close();
		statement.close();
		connection.close();
		return vec;
	}

	public static Vector<ResultRec> getMorph(String corpus, String encodedSurface,
			int start) throws SQLException, UnsupportedEncodingException {
		assertIsInt(start);
		Connection connection = null;
		Statement statement;
		ResultSet rs;
		Vector<ResultRec> vec = new Vector<>();
		try {
			
			connection = SqlDB.corpus.getConnection(SqlDB.User.tmir);
		} catch (Exception e) {
			e.printStackTrace();
			System.exit(0);
		}
		statement = connection.createStatement();

		switch (corpus) {
			case "haaretz":
				rs = statement
						.executeQuery("SELECT *  FROM haaretzTokens, haaretz where haaretz.id=haaretzTokens.corpusId and token in("
								+ encodedSurface + ") limit " + start + ", 100");
				break;
			case "tapuz":
				rs = statement
						.executeQuery("SELECT *  FROM tapuzTokens, tapuz  where tapuz.id=tapuzTokens.corpusId and token in("
								+ encodedSurface + ") limit " + start + ", 100");
				break;
			case "theMarker":
				rs = statement
						.executeQuery("SELECT *  FROM theMarkerTokens, theMarker  where theMarker.id=theMarkerTokens.corpusId and token in("
								+ encodedSurface + ") limit " + start + ", 100");
				break;
			case "doctors":
				rs = statement
						.executeQuery("SELECT *  FROM doctorsTokens, doctors  where doctors.id=doctorsTokens.corpusId and token in("
								+ encodedSurface + ") limit " + start + ", 100");
				break;
			case "infomed":
				rs = statement
						.executeQuery("SELECT *  FROM infomedTokens, infomed  where infomed.id=infomedTokens.corpusId and token in("
								+ encodedSurface + ") limit " + start + ", 100");
				break;
			case "nature":
				rs = statement
						.executeQuery("SELECT *  FROM natureTokens, nature  where nature.id=natureTokens.corpusId and token in("
								+ encodedSurface + ") limit " + start + ", 100");
				break;
			case "bari":
				rs = statement
						.executeQuery("SELECT *  FROM bariTokens, bari  where bari.id=bariTokens.corpusId and token in("
								+ encodedSurface + ") limit " + start + ", 100");
				break;
			case "a7News":
				rs = statement
						.executeQuery("SELECT *  FROM a7NewsTokens, a7News  where a7News.id=a7NewsTokens.corpusId and token in("
								+ encodedSurface + ") limit " + start + ", 100");
				break;
			case "a7Articles":
				rs = statement
						.executeQuery("SELECT *  FROM a7ArticlesTokens, a7Articles  where a7Articles.id=a7ArticlesTokens.corpusId and token in("
								+ encodedSurface + ") limit " + start + ", 100");
				break;
			case "knesset":
				rs = statement
						.executeQuery("SELECT *  FROM knessetTokens, knesset  where knesset.id=knessetTokens.corpusId and token in("
								+ encodedSurface + ") limit " + start + ", 100");

				break;
			case "ta1":
				rs = statement
						.executeQuery("SELECT *  FROM taEstiTokens, taEsti  where taEsti.id=taEstiTokens.corpusId and token in("
								+ encodedSurface + ") limit " + start + ", 100");
				break;
			case "ta2":
				rs = statement
						.executeQuery("SELECT *  FROM taShlomoTokens, taShlomo  where taShlomo.id=taShlomoTokens.corpusId and token in("
								+ encodedSurface + ") limit " + start + ", 100");
				break;
			default:
				return vec;
		}

		while (rs.next()) {
			ResultRec rc = new ResultRec();
			String surface = rs.getString("token");
			URLDecoder.decode(surface, "UTF-8");
			String tokenId = rs.getString("tokenId");
			String sentenceId = rs.getString("sentenceId");
			String paragraphId = rs.getString("paragraphId");
			String file = rs.getString("fileName");
			file = file.replaceAll("//", "/");
			Blob sentence = rs.getBlob("sentence");
			byte[] bdata = sentence.getBytes(1, (int) sentence.length());
			String text = new String(bdata);
			String decodedSentence = URLDecoder.decode(text, "UTF-8");

			rc.setTokenId(tokenId);
			rc.setParagraphId(paragraphId);
			rc.setSentenceId(sentenceId);
			rc.setDecodedSentence(decodedSentence);
			rc.setFile(file);
			vec.add(rc);
		}
		rs.close();
		statement.close();
		connection.close();
		return vec;
	}

}
