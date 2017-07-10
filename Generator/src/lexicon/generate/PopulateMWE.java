/*
 * Created on 12/11/2008
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package lexicon.generate;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.StringTokenizer;

import lexicon.contents.ConnectedGenerator;
import lexicon.stringUtils.Translate;

/**
 * @author daliabo
 * 
 *         TODO To change the template for this generated type comment go to
 *         Window - Preferences - Java - Code Style - Code Templates
 */
public class PopulateMWE extends ConnectedGenerator {

	// **************************************************************************************************************
	public void popualteMWEInflections1(String transliterated, String pos, String surface, String type, boolean prefix)
			throws SQLException {
		String mweinflectionsId = "";
		String sqlSelect = "";
		String sqlInsert = "";
		char consecutive = '1';

		transliterated = transliterated.replaceAll("'", "\\\\'");

		// check if there is need to insert into mwinflections
		sqlSelect = "select id from mweinflections where transliterated='" + transliterated + "' and pos='" + pos
				+ "' and  mweId=1 and type='" + type + "'";

		ResultSet rs = getData(sqlSelect);

		while (rs.next())
			mweinflectionsId = rs.getString("id");
		releaseConnection();

		if (mweinflectionsId.equals("")) {
			sqlInsert = "insert into mweinflections" + " (mweId , surface,transliterated,consecutive,pos,type,prefix)"
					+ " values (1,'" + surface + "','" + transliterated + "','" + consecutive + "','" + pos + "','" + type
					+ "'," + prefix + ")";
			System.out.println(sqlInsert);
			execute(sqlInsert);
			releaseConnection();
		}
	}

	// **************************************************************************************************************
	public String populateMwe1(String transliterated1, String pos, String surface1, String type) throws SQLException {
		char consecutive = '1';
		String mw1id = "";
		transliterated1 = transliterated1.replaceAll("'", "\\\\'");
		String sqlSelect = "select * from mwe1 where transliterated='" + transliterated1 + "' and pos='" + pos
				+ "' and type='" + type + "'";

		System.out.println(sqlSelect);
		ResultSet rs = getData(sqlSelect);

		while (rs.next())
			mw1id = rs.getString("id");
		releaseConnection();
		if (mw1id.equals("")) {
			// if item1 doesn't exist in mw1 insert into mwe1
			String sql = "insert into mwe1" + " (surface,transliterated,consecutive,pos,type)" + " values ('" + surface1
					+ "','" + transliterated1 + "','" + consecutive + "','" + pos + "','" + type + "')";
			System.out.println(sql);
			execute(sql);

			releaseConnection();

			// get the id in mwe1
			sqlSelect = "select * from mwe1 where transliterated='" + transliterated1 + "' and pos='" + pos
					+ "' and type='" + type + "'";

			rs = getData(sqlSelect);

			while (rs.next())
				mw1id = rs.getString("id");
			releaseConnection();

		}
		return mw1id;

	}

	// **************************************************************************************************************
	// added by yossi jacob 12.10.10
	// this function aim is to add a record to one of the mwe tables (2-4)

	public void populateMweX(int x, String transliterated, String pos, String undottedLexiconItem, String mw1id,
			StringTokenizer st, String dottedLexiconItem, String id, int len, String spelling, String register,
			boolean prefix, String definitnessVal) throws SQLException, UnsupportedEncodingException {
		String sqlInsert = "";
		char consecutive = '1';
		dottedLexiconItem = URLEncoder.encode(dottedLexiconItem, "UTF-8");
		undottedLexiconItem = URLEncoder.encode(undottedLexiconItem, "UTF-8");
		int i = 1;

		String mweLastId = mw1id;

		String surface = URLEncoder.encode(Translate.Eng2Heb(transliterated), "UTF-8");
		/*
		 * while (st.hasMoreTokens()) { String checkedId = ""; i++; len--; String
		 * nextTransliterated = st.nextToken(); String nextSurface =
		 * URLEncoder.encode(Translate .Eng2Heb(nextTransliterated), "UTF-8");
		 * 
		 * nextTransliterated = nextTransliterated.replaceAll("'","\\\\'");
		 * if(transliterated.indexOf("\\\'")==-1) transliterated =
		 * transliterated.replaceAll("'","\\\\'");
		 * 
		 * 
		 * sqlSelect = "select aid from mwe" + i + " where transliterated='" +
		 * nextTransliterated + "' and formerItemId= " + mweLastId +
		 * " and consecutive='1' and spelling='" +spelling +"' and register='"+ register
		 * +"'";
		 * 
		 * 
		 * System.out.println(sqlSelect); rs = getData(sqlSelect);
		 * 
		 * while (rs.next()) checkedId = rs.getString("aid");
		 * 
		 * releaseConnection(); if (checkedId.equals("")) { if (len == 0)
		 */
		sqlInsert = "insert into mwe" + x
				+ " (surface,transliterated,consecutive,id, formerItemId, lexiconId, transliteratedLexiconItem, dottedLexiconItem,"
				+ "undottedLexiconItem, spelling, register,mwTransliterated, mwUndotted, definiteness )" + " values ('"
				+ surface + "','" + transliterated + "','" + consecutive + "'," + i + "," + mweLastId + "," + id + ",'"
				+ transliterated + "','" + dottedLexiconItem + "','" + undottedLexiconItem + "','" + spelling + "','"
				+ register + "','" + transliterated + "','" + undottedLexiconItem + "','" + definitnessVal + "')";
		/*
		 * else sqlInsert = "insert into mwe" + i +
		 * " (surface,transliterated,consecutive,id, formerItemId)" + " values ('" +
		 * nextSurface + "','" + nextTransliterated + "','" + consecutive + "'," + i +
		 * "," + mweLastId + ")";
		 */
		System.out.println(sqlInsert);
		execute(sqlInsert);
		releaseConnection();
		/*
		 * sqlSelect = "select LAST_INSERT_ID() as last";
		 * 
		 * rs = getData(sqlSelect);
		 * 
		 * while (rs.next()) mweLastId = rs.getString("last");
		 * 
		 * releaseConnection();
		 * 
		 * sqlSelect = "select id from mweinflections" + " where transliterated='" +
		 * nextTransliterated + "'" + " and mweId=" + i + " and consecutive='1' ";
		 * 
		 * System.out.println(sqlSelect); rs = getData(sqlSelect);
		 * 
		 * mweinflectionsId=""; while (rs.next()) mweinflectionsId = rs.getString("id");
		 * 
		 * System.out.println("mweinflectionsId=" + mweinflectionsId);
		 * releaseConnection(); if (mweinflectionsId.equals("")) { sqlInsert =
		 * "insert into mweinflections" +
		 * " (mweId ,mwePointer, surface,transliterated,consecutive,pos,prefix)" +
		 * " values (" + i + "," + mweLastId + ",'" + nextSurface + "','" +
		 * nextTransliterated + "','" + consecutive + "','" + pos + "'," + prefix +")";
		 * execute(sqlInsert); releaseConnection(); }
		 * 
		 * } else mweLastId = checkedId;
		 * 
		 * }
		 */

	}

	// **************************************************************************************************************
	public void populateMwe(String transliterated, String pos, String undottedLexiconItem, String mw1id,
			StringTokenizer st, String dottedLexiconItem, String id, int len, String spelling, String register,
			boolean prefix, String definitnessVal) throws SQLException, UnsupportedEncodingException {
		String sqlSelect = "";
		String sqlInsert = "";
		String mweinflectionsId = "";
		char consecutive = '1';
		dottedLexiconItem = URLEncoder.encode(dottedLexiconItem, "UTF-8");
		undottedLexiconItem = URLEncoder.encode(undottedLexiconItem, "UTF-8");
		ResultSet rs = null;
		int i = 1;

		String mweLastId = mw1id;

		while (st.hasMoreTokens()) {
			String checkedId = "";
			i++;
			len--;
			String nextTransliterated = st.nextToken();
			String nextSurface = URLEncoder.encode(Translate.Eng2Heb(nextTransliterated), "UTF-8");

			nextTransliterated = nextTransliterated.replaceAll("'", "\\\\'");
			if (transliterated.indexOf("\\\'") == -1)
				transliterated = transliterated.replaceAll("'", "\\\\'");

			sqlSelect = "select aid from mwe" + i + " where transliterated='" + nextTransliterated + "' and formerItemId= "
					+ mweLastId + " and consecutive='1' and spelling='" + spelling + "' and register='" + register + "'";

			System.out.println(sqlSelect);
			rs = getData(sqlSelect);

			while (rs.next())
				checkedId = rs.getString("aid");

			releaseConnection();
			if (checkedId.equals("")) {
				if (len == 0)
					sqlInsert = "insert into mwe" + i
							+ " (surface,transliterated,consecutive,id, formerItemId, lexiconId, transliteratedLexiconItem, dottedLexiconItem,"
							+ "undottedLexiconItem, spelling, register,mwTransliterated, mwUndotted, definiteness )"
							+ " values ('" + nextSurface + "','" + nextTransliterated + "','" + consecutive + "'," + i + ","
							+ mweLastId + "," + id + ",'" + transliterated + "','" + dottedLexiconItem + "','"
							+ undottedLexiconItem + "','" + spelling + "','" + register + "','" + transliterated + "','"
							+ undottedLexiconItem + "','" + definitnessVal + "')";
				else
					sqlInsert = "insert into mwe" + i + " (surface,transliterated,consecutive,id, formerItemId)"
							+ " values ('" + nextSurface + "','" + nextTransliterated + "','" + consecutive + "'," + i + ","
							+ mweLastId + ")";
				System.out.println(sqlInsert);
				execute(sqlInsert);
				releaseConnection();

				sqlSelect = "select LAST_INSERT_ID() as last";

				rs = getData(sqlSelect);

				while (rs.next())
					mweLastId = rs.getString("last");

				releaseConnection();

				sqlSelect = "select id from mweinflections" + " where transliterated='" + nextTransliterated + "'"
						+ " and mweId=" + i + " and consecutive='1' ";

				System.out.println(sqlSelect);
				rs = getData(sqlSelect);

				mweinflectionsId = "";
				while (rs.next())
					mweinflectionsId = rs.getString("id");

				System.out.println("mweinflectionsId=" + mweinflectionsId);
				releaseConnection();
				if (mweinflectionsId.equals("")) {
					sqlInsert = "insert into mweinflections"
							+ " (mweId ,mwePointer, surface,transliterated,consecutive,pos,prefix)" + " values (" + i + ","
							+ mweLastId + ",'" + nextSurface + "','" + nextTransliterated + "','" + consecutive + "','" + pos
							+ "'," + prefix + ")";
					execute(sqlInsert);
					releaseConnection();
				}

			} else
				mweLastId = checkedId;

		}

	}

	// **************************************************************************************************************
	// Added By Gassan on 25/8/2009
	public void populateMwe(String transliterated, String pos, String undottedLexiconItem, String mw1id,
			StringTokenizer st, String dottedLexiconItem, String id, int len, String spelling, String register,
			boolean prefix) throws SQLException, UnsupportedEncodingException {
		String sqlSelect = "";
		String sqlInsert = "";
		String mweinflectionsId = "";
		char consecutive = '1';
		dottedLexiconItem = URLEncoder.encode(dottedLexiconItem, "UTF-8");
		undottedLexiconItem = URLEncoder.encode(undottedLexiconItem, "UTF-8");
		ResultSet rs = null;
		int i = 1;

		String mweLastId = mw1id;

		while (st.hasMoreTokens()) {
			String checkedId = "";
			i++;
			len--;
			String nextTransliterated = st.nextToken();
			String nextSurface = URLEncoder.encode(Translate.Eng2Heb(nextTransliterated), "UTF-8");

			nextTransliterated = nextTransliterated.replaceAll("'", "\\\\'");
			if (transliterated.indexOf("\\\'") == -1)
				transliterated = transliterated.replaceAll("'", "\\\\'");

			sqlSelect = "select aid from mwe" + i + " where transliterated='" + nextTransliterated + "' and formerItemId= "
					+ mweLastId + " and consecutive='1' and spelling='" + spelling + "' and register='" + register + "'";

			System.out.println(sqlSelect);
			rs = getData(sqlSelect);

			while (rs.next())
				checkedId = rs.getString("aid");

			releaseConnection();
			if (checkedId.equals("")) {
				if (len == 0)
					sqlInsert = "insert into mwe" + i
							+ " (surface,transliterated,consecutive,id, formerItemId, lexiconId, transliteratedLexiconItem, dottedLexiconItem,"
							+ "undottedLexiconItem, spelling, register,mwTransliterated, mwUndotted )" + " values ('"
							+ nextSurface + "','" + nextTransliterated + "','" + consecutive + "'," + i + "," + mweLastId + ","
							+ id + ",'" + transliterated + "','" + dottedLexiconItem + "','" + undottedLexiconItem + "','"
							+ spelling + "','" + register + "','" + transliterated + "','" + undottedLexiconItem + "')";
				else
					sqlInsert = "insert into mwe" + i + " (surface,transliterated,consecutive,id, formerItemId)"
							+ " values ('" + nextSurface + "','" + nextTransliterated + "','" + consecutive + "'," + i + ","
							+ mweLastId + ")";
				System.out.println(sqlInsert);
				execute(sqlInsert);
				releaseConnection();

				sqlSelect = "select LAST_INSERT_ID() as last";

				rs = getData(sqlSelect);

				while (rs.next())
					mweLastId = rs.getString("last");

				releaseConnection();

				sqlSelect = "select id from mweinflections" + " where transliterated='" + nextTransliterated + "'"
						+ " and mweId=" + i + " and consecutive='1' ";

				System.out.println(sqlSelect);
				rs = getData(sqlSelect);

				mweinflectionsId = "";
				while (rs.next())
					mweinflectionsId = rs.getString("id");

				System.out.println("mweinflectionsId=" + mweinflectionsId);
				releaseConnection();
				if (mweinflectionsId.equals("")) {
					sqlInsert = "insert into mweinflections"
							+ " (mweId ,mwePointer, surface,transliterated,consecutive,pos,prefix)" + " values (" + i + ","
							+ mweLastId + ",'" + nextSurface + "','" + nextTransliterated + "','" + consecutive + "','" + pos
							+ "'," + prefix + ")";
					execute(sqlInsert);
					releaseConnection();
				}

			} else
				mweLastId = checkedId;

		}

	}

	// **************************************************************************************************************
	public void populateMwe(String transliteratedLexiconItem, String pos, String surfaceLexiconItem, String mw1id,
			StringTokenizer st, String dottedLexiconItem, String id, int len, String PGN, String spelling, String register,
			String mwTransliterated, String mwSurface) throws SQLException, UnsupportedEncodingException {
		String sqlSelect = "";
		String sqlInsert = "";
		String mweinflectionsId = "";
		char consecutive = '1';
		mwSurface = URLEncoder.encode(mwSurface, "UTF-8");
		if (dottedLexiconItem != null && dottedLexiconItem.charAt(0) != 'u')
			dottedLexiconItem = URLEncoder.encode(dottedLexiconItem, "UTF-8");

		ResultSet rs = null;
		int i = 1;

		String mweLastId = mw1id;

		while (st.hasMoreTokens()) {
			String checkedId = "";
			i++;
			len--;
			String nextTransliterated = st.nextToken();
			String nextSurface = URLEncoder.encode(Translate.Eng2Heb(nextTransliterated), "UTF-8");

			nextTransliterated = nextTransliterated.replaceAll("'", "\\\\'");
			mwTransliterated = mwTransliterated.replaceAll("'", "\\\\'");

			sqlSelect = "select aid from mwe" + i + " where transliterated='" + nextTransliterated + "' and formerItemId= "
					+ mweLastId + " and consecutive='1' and PGN='" + PGN + "' and spelling='" + spelling + " 'and register='"
					+ register + "'";

			System.out.println(sqlSelect);
			rs = getData(sqlSelect);

			while (rs.next())
				checkedId = rs.getString("aid");

			releaseConnection();
			if (checkedId.equals("")) {
				if (len == 0)
					sqlInsert = "insert into mwe" + i
							+ " (surface,transliterated,consecutive,id, formerItemId, lexiconId, transliteratedLexiconItem, dottedLexiconItem,"
							+ "undottedLexiconItem,PGN, spelling, register,mwTransliterated, mwUndotted)" + " values ('"
							+ nextSurface + "','" + nextTransliterated + "','" + consecutive + "'," + i + "," + mweLastId + ","
							+ id + ",'" + transliteratedLexiconItem + "','" + dottedLexiconItem + "','" + surfaceLexiconItem
							+ "','" + PGN + "','" + spelling + "','" + register + "','" + mwTransliterated + "','" + mwSurface
							+ "')";
				else
					sqlInsert = "insert into mwe" + i + " (surface,transliterated,consecutive,id, formerItemId)"
							+ " values ('" + nextSurface + "','" + nextTransliterated + "','" + consecutive + "'," + i + ","
							+ mweLastId + ")";
				System.out.println(sqlInsert);
				execute(sqlInsert);
				releaseConnection();

				sqlSelect = "select LAST_INSERT_ID() as last";

				rs = getData(sqlSelect);

				while (rs.next())
					mweLastId = rs.getString("last");

				releaseConnection();
				sqlSelect = "select id from mweinflections" + " where transliterated='" + nextTransliterated + "'"
						+ " and mweId=" + i + " and consecutive='1' ";

				System.out.println(sqlSelect);
				rs = getData(sqlSelect);

				mweinflectionsId = "";
				while (rs.next())
					mweinflectionsId = rs.getString("id");

				System.out.println("mweinflectionsId=" + mweinflectionsId);
				releaseConnection();
				if (mweinflectionsId.equals("")) {
					sqlInsert = "insert into mweinflections" + " (mweId ,mwePointer, surface,transliterated,consecutive,pos)"
							+ " values (" + i + "," + mweLastId + ",'" + nextSurface + "','" + nextTransliterated + "','"
							+ consecutive + "','" + pos + "')";
					execute(sqlInsert);
					releaseConnection();
				}

			} else
				mweLastId = checkedId;

		}

	}

	// **************************************************************************************************************
	public void populateMwe(String transliterated, String pos, String undottedLexiconItem, String mw1id,
			StringTokenizer st, String dottedLexiconItem, String id, int len, String PGN, String definitenessVal,
			String gender, String number, String spelling, String register, String mwTransliterated, String mwUndotted)
			throws SQLException, UnsupportedEncodingException {
		String sqlSelect = "";
		String sqlInsert = "";
		String mweinflectionsId = "";
		char consecutive = '1';
		// System.out.println("dottedLexiconItem="+dottedLexiconItem);
		if (dottedLexiconItem != null && dottedLexiconItem.length() > 0 && dottedLexiconItem.charAt(0) != 'u')
			dottedLexiconItem = URLEncoder.encode(dottedLexiconItem, "UTF-8");

		ResultSet rs = null;
		int i = 1;

		String mweLastId = mw1id;

		while (st.hasMoreTokens()) {
			String checkedId = "";
			i++;
			len--;
			String nextTransliterated = st.nextToken();
			String nextSurface = URLEncoder.encode(Translate.Eng2Heb(nextTransliterated), "UTF-8");

			nextTransliterated = nextTransliterated.replaceAll("'", "\\\\'");
			transliterated = transliterated.replaceAll("'", "\\\\'");
			mwTransliterated = mwTransliterated.replaceAll("'", "\\\\'"); // remove
			// '
			// from
			// sql
			// query

			sqlSelect = "select aid from mwe" + i + " where transliterated='" + nextTransliterated + "' and formerItemId= "
					+ mweLastId + " and consecutive='1' and PGN='" + PGN + "' and definiteness='" + definitenessVal
					+ "' and gender='" + gender + "'" + "and number='" + number + "' and spelling='" + spelling
					+ "'and register='" + register + "' and  mwTransliterated='" + mwTransliterated + "'";

			System.out.println(sqlSelect);
			rs = getData(sqlSelect);
			while (rs.next())
				checkedId = rs.getString("aid");
			releaseConnection();
			if (checkedId.equals("")) {
				if (len == 0)
					sqlInsert = "insert into mwe" + i
							+ " (surface,transliterated,consecutive,id, formerItemId, lexiconId, transliteratedLexiconItem, dottedLexiconItem,"
							+ "undottedLexiconItem,PGN, definiteness,gender,number,spelling, register, mwTransliterated, mwUndotted)"
							+ " values ('" + nextSurface + "','" + nextTransliterated + "','" + consecutive + "'," + i + ","
							+ mweLastId + "," + id + ",'" + transliterated + "','" + dottedLexiconItem + "','"
							+ undottedLexiconItem + "','" + PGN + "','" + definitenessVal + "','" + gender + "','" + number
							+ "','" + spelling + "','" + register + "','" + mwTransliterated + "','" + mwUndotted + "')";
				else
					sqlInsert = "insert into mwe" + i + " (surface,transliterated,consecutive,id, formerItemId)"
							+ " values ('" + nextSurface + "','" + nextTransliterated + "','" + consecutive + "'," + i + ","
							+ mweLastId + ")";
				System.out.println(sqlInsert);
				execute(sqlInsert);
				releaseConnection();

				sqlSelect = "select LAST_INSERT_ID() as last";

				rs = getData(sqlSelect);

				while (rs.next())
					mweLastId = rs.getString("last");

				releaseConnection();
				sqlSelect = "select id from mweinflections" + " where transliterated='" + nextTransliterated + "'"
						+ " and mweId=" + i + " and consecutive='1' ";

				System.out.println(sqlSelect);
				rs = getData(sqlSelect);

				mweinflectionsId = "";
				while (rs.next())
					mweinflectionsId = rs.getString("id");

				System.out.println("mweinflectionsId=" + mweinflectionsId);
				releaseConnection();
				if (mweinflectionsId.equals("")) {
					sqlInsert = "insert into mweinflections" + " (mweId ,mwePointer, surface,transliterated,consecutive,pos)"
							+ " values (" + i + "," + mweLastId + ",'" + nextSurface + "','" + nextTransliterated + "','"
							+ consecutive + "','" + pos + "')";
					execute(sqlInsert);
					releaseConnection();
				}

			} else
				mweLastId = checkedId;

		}

	}

	// **************************************************************************************************************
	public void popualteMWETables(String transliterated, String undottedLexiconItem, char consecutive, String pos,
			String dottedLexiconItem, String id, String type, String spelling, String register, boolean prefix,
			String definitnessVal) throws UnsupportedEncodingException, SQLException {
		StringTokenizer st = new StringTokenizer(transliterated);
		int len = st.countTokens();
		String transliterated1 = st.nextToken();
		String surface1 = Translate.Eng2Heb(transliterated1);
		surface1 = URLEncoder.encode(surface1, "UTF-8");
		surface1 = surface1.replaceAll("'", "%27");
		// transliterated = transliterated.replaceAll("'", "%27");
		// transliterated1 = transliterated1.replaceAll("'", "%27");

		popualteMWEInflections1(transliterated1, pos, surface1, type, prefix);
		String mw1id = populateMwe1(transliterated1, pos, surface1, type);

		// ////////////Handle mwe2, mwe3 ...insertions/////////////////////
		populateMwe(transliterated, pos, undottedLexiconItem, mw1id, st, dottedLexiconItem, id, len - 1, spelling,
				register, prefix, definitnessVal);

	}

	// ***********************************************************************************************
	// added by yossi on 12.10.2010
	// this function aim is to add an exception of one word into the mwe2 table
	// so it will be
	// visible , for example if we want to put rashei teivot (acronym) of a
	// pahrse.
	public void popualteMWETablesOneWord(String transliterated, String undottedLexiconItem, char consecutive, String pos,
			String dottedLexiconItem, String id, String type, String spelling, String register, boolean prefix,
			String definitnessVal) throws UnsupportedEncodingException, SQLException {
		StringTokenizer st = new StringTokenizer(transliterated);
		int len = st.countTokens();
		String transliterated1 = st.nextToken();
		String surface1 = Translate.Eng2Heb(transliterated1);
		surface1 = URLEncoder.encode(surface1, "UTF-8");
		surface1 = surface1.replaceAll("'", "%27");
		// transliterated = transliterated.replaceAll("'", "%27");
		// transliterated1 = transliterated1.replaceAll("'", "%27");

		popualteMWEInflections1(transliterated1, pos, surface1, type, prefix); // add
		// to
		// the
		// mweinflections
		// table

		String mw1id = populateMwe1(transliterated1, pos, surface1, type); // add
		// to
		// me1
		// and
		// get
		// the
		// id

		populateMweX(2, transliterated, pos, undottedLexiconItem, mw1id, st, dottedLexiconItem, id, len - 1, spelling,
				register, prefix, definitnessVal); // add to the mwe2 table

		// ////////////Handle mwe2, mwe3 ...insertions/////////////////////
		// populateMwe(transliterated, pos, undottedLexiconItem, mw1id, st,
		// dottedLexiconItem,
		// id, len - 1, spelling, register,prefix, definitnessVal);

	}

	// *****************************************************************************************************

	// Added By Gassan on 25/8/2009
	public void popualteMWETables(String transliterated, String undottedLexiconItem, char consecutive, String pos,
			String dottedLexiconItem, String id, String type, String spelling, String register, boolean prefix)
			throws UnsupportedEncodingException, SQLException {
		StringTokenizer st = new StringTokenizer(transliterated);
		int len = st.countTokens();
		String transliterated1 = st.nextToken();
		String surface1 = Translate.Eng2Heb(transliterated1);
		surface1 = URLEncoder.encode(surface1, "UTF-8");
		surface1 = surface1.replaceAll("'", "%27");
		// transliterated = transliterated.replaceAll("'", "%27");
		// transliterated1 = transliterated1.replaceAll("'", "%27");

		popualteMWEInflections1(transliterated1, pos, surface1, type, prefix);
		String mw1id = populateMwe1(transliterated1, pos, surface1, type);

		// ////////////Handle mwe2, mwe3 ...insertions/////////////////////
		populateMwe(transliterated, pos, undottedLexiconItem, mw1id, st, dottedLexiconItem, id, len - 1, spelling,
				register, prefix);

	}

	// **************************************************************************************************************
	public void popualteMWETables(String transliteratedLexiconItem, String surfaceLexiconItem, char consecutive,
			String pos, String dottedLexiconItem, String id, String type, String PGN, String spelling, String register,
			String mwTransliterated, String mwSurface) throws UnsupportedEncodingException, SQLException {
		StringTokenizer st = new StringTokenizer(mwTransliterated);
		int len = st.countTokens();
		String transliterated1 = st.nextToken();
		String surface1 = Translate.Eng2Heb(transliterated1);
		surface1 = URLEncoder.encode(surface1, "UTF-8");
		surface1 = surface1.replaceAll("'", "%27");

		surfaceLexiconItem = URLEncoder.encode(surfaceLexiconItem, "UTF-8");

		// transliterated = transliterated.replaceAll("'", "%27");

		popualteMWEInflections1(transliterated1, pos, surface1, type, false);
		String mw1id = populateMwe1(transliterated1, pos, surface1, type);

		// ////////////Handle mwe2, mwe3 ...insertions/////////////////////
		populateMwe(transliteratedLexiconItem, pos, surfaceLexiconItem, mw1id, st, dottedLexiconItem, id, len - 1, PGN,
				spelling, register, mwTransliterated, mwSurface);

	}

	// **************************************************************************************************************

	public void popualteMWETables(String transliterated, String undottedLexcionItem, char consecutive, String pos,
			String dottedLexiconItem, String id, String type, String PGN, String definitenessVal, String gender,
			String number, String spelling, String register, String mwTransliterated, String mwUndotted)
			throws UnsupportedEncodingException, SQLException {
		StringTokenizer st = new StringTokenizer(mwTransliterated);
		int len = st.countTokens();
		String transliterated1 = st.nextToken();
		String surface1 = Translate.Eng2Heb(transliterated1);
		surface1 = URLEncoder.encode(surface1, "UTF-8");
		surface1 = surface1.replaceAll("'", "%27");
		mwUndotted = URLEncoder.encode(mwUndotted, "UTF-8");

		// mwTransliterated = mwTransliterated.replaceAll("'", "%27");

		popualteMWEInflections1(transliterated1, pos, surface1, type, false);
		String mw1id = populateMwe1(transliterated1, pos, surface1, type);

		// ////////////Handle mwe2, mwe3 ...insertions/////////////////////
		populateMwe(transliterated, pos, undottedLexcionItem, mw1id, st, dottedLexiconItem, id, len - 1, PGN,
				definitenessVal, gender, number, spelling, register, mwTransliterated, mwUndotted);

	}

	public static void main(String[] args) {
	}
}
