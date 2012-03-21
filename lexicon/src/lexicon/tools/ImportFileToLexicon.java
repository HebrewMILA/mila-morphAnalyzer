/*
 * Created on 23/05/2006
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package lexicon.tools;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.StringTokenizer;
import java.util.Vector;

import lexicon.contents.Connected;

/**
 * @author daliabo
 * 
 *         TODO To change the template for this generated type comment go to
 *         Window - Preferences - Java - Code Style - Code Templates
 */
public class ImportFileToLexicon extends Connected {
	public static void main(String[] args) {
		final ImportFileToLexicon i = new ImportFileToLexicon();
		try {
			i.populateGematria();
			// i.populateNoun();
			// i.populateAdjective();
			// i.populateAdverb();
			// i.populateSense();
			// i.checkExistense();
			// i.checkAdjectiveExistense();
			// i.checkNounExistense();
			// i.get();
			System.out
					.println("**********************finished*****************");
			System.exit(1);
		} catch (final IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.exit(1);
		} catch (final Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.exit(1);
		}

	}

	BufferedReader bi = null;

	String inputFile = "";

	private String apostropheHandling(String strIn) {
		String strOut = strIn;
		// case of single apostrophe
		final int index1 = strIn.indexOf('\'');
		if (index1 != -1) {
			if (index1 == 1) {
				strOut = strIn.charAt(0) + "\\" + strIn.substring(index1);
				// System.out.println("strOut = " + strOut);
			} else {
				strOut = strIn.substring(0, index1 - 1) + "\\"
						+ strIn.substring(index1);
				// System.out.println("strOut1 = " + strOut);
			}
			// case of two apostrophe
			// int index2 = (strIn.substring(index1 + 1)).indexOf('\'');
			// if (index2 != -1) {
			// index2 = strOut.lastIndexOf('\'');
			// //System.out.println("index2 = " + index2);
			// strOut = strOut.substring(0, index2) + "\\"
			// + strOut.substring(index2);
			// //System.out.println("strOut2 = " + strOut);
		}
		// } else
		// strOut = strIn;
		return strOut;
	}

	public void checkAdjectiveExistense() throws IOException {
		this.inputFile = "C:\\Documents and Settings\\daliabo\\Desktop\\adjective1.txt";
		this.inputFileHandling();
		String line = "";
		String undotted = "";
		String pos = "";
		String gender = "";
		String plural = "";
		String entry = "";
		String feminine = "";
		String feminineh = "";
		String transliterated = "";
		ResultSet rs = null;
		int id;
		line = this.bi.readLine();
		while ((line != null) && !line.equals("")) {
			// System.out.println("line=" + line);
			final StringTokenizer st = new StringTokenizer(line, "\t");
			undotted = st.nextToken();
			undotted = URLEncoder.encode(undotted, "UTF8");
			// System.out.println("undotted=" + undotted);
			transliterated = LexiconUtils.getTransliteration(undotted);
			pos = st.nextToken();
			st.nextToken();
			// System.out.println("translation=" + translation);
			gender = st.nextToken();
			// System.out.println("gender =" + gender);
			if (gender.equals("זכר")) {
				gender = "masculine";
			} else {
				gender = "feminine";
			}
			st.nextToken();
			st.nextToken();
			// System.out.println("number =" + number);
			if (!gender.equals("נקבה")) {
				feminine = st.nextToken();
				// System.out.println("feminine =" + feminine);
			}
			if (feminine.equals("1") || feminine.equals("0")) {
				entry = feminine;
				feminine = "";
				feminineh = "";
				plural = "";
			} else {
				feminineh = st.nextToken();
				// System.out.println("feminineh =" + feminineh);
				if (feminineh.equals("1") || feminineh.equals("0")) {
					entry = feminineh;
					feminineh = "";
					plural = "";
				} else {
					plural = st.nextToken();
					// System.out.println("plural =" + plural);
					if (plural.equals("1") || plural.equals("0")) {
						entry = plural;
						plural = "";
					} else {
						entry = st.nextToken();
						plural = URLEncoder.encode(plural, "UTF8");
						plural = LexiconUtils.getTransliteration(plural);
					}
				}
			}
			if (entry.equals("1")) {
				// checking whether the file entry already exist in item table
				final String sqlCheckExistence = "select * from item where transliterated='"
						+ transliterated
						+ "'and pos='"
						+ pos
						+ "' and deleted=0";
				rs = null;
				boolean exist = false;
				try {
					rs = this.getData(sqlCheckExistence);
					if (rs.next()) {
						id = rs.getInt("id");
						System.out.println("Already in item table -"
								+ transliterated + " " + id);
						System.exit(1);
						exist = true;
					}
					rs.close();
					// In the future, we need to change that to adding a random
					// int
				} catch (final SQLException E) {
					E.printStackTrace();
					System.exit(1);
				} finally {
					this.releaseConnection();
				}
				if (!exist) {
					final Vector<Integer> ids = new Vector<Integer>();
					// checking whether the file entry already exist in the
					// relevant exception table
					final String sqlCheckExceptionExistence = "select * from "
							+ "noun_exception_type " + "where transliterated='"
							+ transliterated + "'";
					rs = null;
					try {
						rs = this.getData(sqlCheckExceptionExistence);
						while (rs.next()) {
							ids.add(new Integer(rs.getInt(1)));
							id = rs.getInt(1);
							System.out.println("Already in exception table -"
									+ transliterated + " ");
							System.exit(1);
							exist = true;
						}
						rs.close();
						// In the future, we need to change that to adding a
						// random int
						if (ids.size() > 1) {
							System.out.println("size=" + ids.size());
							System.exit(1);
						}

					} catch (final SQLException E) {
						E.printStackTrace();
						System.exit(1);
					} finally {
						this.releaseConnection();
					}
				}
			}
			line = this.bi.readLine();
		}
	}

	public void checkAdverbExistense() throws IOException {
		this.inputFile = "C:\\Documents and Settings\\daliabo\\Desktop\\adverb1.txt";
		this.inputFileHandling();
		String line = "";
		String undotted = "";
		String pos = "";
		String entry = "";
		String transliterated = "";
		ResultSet rs = null;
		int id;
		line = this.bi.readLine();
		while (line != null) {
			// System.out.println("line=" + line);
			final StringTokenizer st = new StringTokenizer(line, "\t");
			undotted = st.nextToken();
			undotted = URLEncoder.encode(undotted, "UTF8");
			// System.out.println("undotted=" + undotted);
			transliterated = LexiconUtils.getTransliteration(undotted);
			pos = st.nextToken();
			st.nextToken();
			// System.out.println("translation=" + translation);
			entry = st.nextToken();
			// System.out.println("************************");
			if (entry.equals("1")) {
				// checking whether the file entry already exist in item table
				final String sqlCheckExistence = "select * from item where transliterated='"
						+ transliterated
						+ "'and pos='"
						+ pos
						+ "' and deleted=0";
				rs = null;
				boolean exist = false;
				try {
					rs = this.getData(sqlCheckExistence);
					if (rs.next()) {
						id = rs.getInt("id");
						System.out.println("Already in item table -"
								+ transliterated + " " + id);
						System.exit(1);
						exist = true;
					}
					rs.close();
					// In the future, we need to change that to adding a random
					// int
				} catch (final SQLException E) {
					E.printStackTrace();
					System.exit(1);
				} finally {
					this.releaseConnection();
				}
				if (!exist) {
					final Vector<Integer> ids = new Vector<Integer>();
					// checking whether the file entry already exist in the
					// relevant exception table
					final String sqlCheckExceptionExistence = "select * from "
							+ pos + "_exception_type "
							+ "where transliterated='" + transliterated + "'";
					rs = null;
					try {
						rs = this.getData(sqlCheckExceptionExistence);
						while (rs.next()) {
							ids.add(new Integer(rs.getInt(1)));
							id = rs.getInt(1);
							System.out.println("Already in exception table -"
									+ transliterated + " ");
							System.exit(1);
							exist = true;
						}
						rs.close();
						// In the future, we need to change that to adding a
						// random int
						if (ids.size() > 1) {
							System.out.println("size=" + ids.size());
							System.exit(1);
						}

					} catch (final SQLException E) {
						E.printStackTrace();
						System.exit(1);
					} finally {
						this.releaseConnection();
					}
				}
			}
			line = this.bi.readLine();
		}
	}

	public void checkNounExistense() throws IOException {
		this.inputFile = "C:\\Documents and Settings\\daliabo\\Desktop\\noun61.txt";
		this.inputFileHandling();
		String line = "";
		String undotted = "";
		String transliterated = "";
		ResultSet rs = null;
		line = this.bi.readLine();
		while ((line != null) && !line.equals("")) {
			// System.out.println("line=" + line);
			final StringTokenizer st = new StringTokenizer(line, "\t");
			undotted = st.nextToken();
			undotted = URLEncoder.encode(undotted, "UTF8");
			// System.out.println("undotted=" + undotted);
			transliterated = LexiconUtils.getTransliteration(undotted);
			// System.out.println("undotted=" + undotted);
			final Vector<Integer> ids = new Vector<Integer>();
			// checking whether the file entry already exist in the
			// relevant exception table
			final String sqlCheckExceptionExistence = "select * from "
					+ "noun_exception_type " + "where transliterated='"
					+ transliterated + "'";
			rs = null;
			try {
				rs = this.getData(sqlCheckExceptionExistence);
				while (rs.next()) {
					ids.add(new Integer(rs.getInt(1)));
					rs.getInt(1);
					System.out.println("Already in exception table -"
							+ transliterated + " ");
					System.exit(1);
				}
				rs.close();
				// In the future, we need to change that to adding a
				// random int
				if (ids.size() > 1) {
					System.out.println("size=" + ids.size());
					System.exit(1);
				}

			} catch (final SQLException E) {
				E.printStackTrace();
				System.exit(1);
			} finally {
				this.releaseConnection();
			}
			line = this.bi.readLine();
		}

	}

	public void get() {
		PrintStream pInflections = null;
		try {

			final String dir = "C:\\Documents and Settings\\daliabo\\Desktop\\";
			FileOutputStream inflections; // declare a file output object
			try {

				// Create a new file output stream
				// connected to "myfile.txt"
				inflections = new FileOutputStream(dir + "sense.txt");

				// Connect print stream to the output stream
				pInflections = new PrintStream(inflections);

			} catch (final Exception e) {
				System.err.println("Error writing to file");
			}
			// String sql = "select transliterated from inflections order by id
			// ";
			// String sql = "select adjective_copy.id , noun_copy.number,
			// noun_copy.gender, noun_copy.plural,item_copy.transliterated from
			// item_copy, noun_copy "
			// + "where item_copy.id=noun_copy.id ";
			final String sql = "select definition, id from sense";
			String transliterated = "";
			ResultSet rs = null;
			rs = this.getData(sql);
			while (rs.next()) {
				transliterated = rs.getString("definition");
				String surface = Translate.Eng2Heb(transliterated);
				// String gender = rs.getString("gender");
				// String number = rs.getString("number");
				// String plural = rs.getString("plural");
				final String id = rs.getString("id");
				try {
					surface = URLDecoder.decode(surface, "UTF-8");
				} catch (final UnsupportedEncodingException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				pInflections.print(surface);
				pInflections.print("	");
				pInflections.print(id);
				pInflections.print("    |	");
				// pInflections.print(gender);
				// pInflections.print(" | ");
				// pInflections.print(number);
				// pInflections.print(" | ");
				// pInflections.print(plural);
				pInflections.print("    |	");
				pInflections.println();
			}
			rs.close();
		} catch (final SQLException E) {
			E.printStackTrace();
			System.exit(1);
		} finally {
			this.releaseConnection();
			pInflections.close();
			System.out.println("******file is ready**********");
		}
	}

	private void inputFileHandling() {
		try {
			this.bi = new BufferedReader(new InputStreamReader(
					new FileInputStream(this.inputFile)));
		} catch (final FileNotFoundException e) {
			// TODO Auto-generated catch block
			System.err.println("input File not found:  " + this.inputFile);
			e.printStackTrace();
			System.exit(1);
		}
	}

	public void populateAdjective() throws IOException {
		this.inputFile = "C:\\Documents and Settings\\daliabo\\Desktop\\adjective6.txt";
		this.inputFileHandling();
		String line = "";
		String undotted = "";
		String pos = "";
		String gender = "";
		String number = "";
		String plural = "";
		String transliterated = "";
		line = this.bi.readLine();
		while ((line != null) && !line.equals("")) {
			// System.out.println("line=" + line);
			final StringTokenizer st = new StringTokenizer(line, "\t");
			undotted = st.nextToken();
			undotted = URLEncoder.encode(undotted, "UTF8");
			// System.out.println("undotted=" + undotted);
			transliterated = LexiconUtils.getTransliteration(undotted);
			pos = st.nextToken();
			st.nextToken();
			// System.out.println("translation=" + translation);
			gender = st.nextToken();
			// System.out.println("gender =" + gender);
			if (gender.equals("זכר")) {
				gender = "masculine";
			} else {
				gender = "feminine";
			}
			// System.out.println("gender =" + gender);
			number = st.nextToken();
			// System.out.println("number =" + number);
			// feminine = st.nextToken();
			// System.out.println("feminine=" + feminine);
			plural = st.nextToken();

			// System.out.println("plural =" + plural);
			if (number.equals("יחיד")) {
				number = "singular";
			} else {
				number = "plural";
			}
			// feminine = URLEncoder.encode(feminine, "UTF8");
			// System.out.println("feminine=" + feminine);
			// feminine = LexiconUtils.getTransliteration(feminine);
			plural = URLEncoder.encode(plural, "UTF8");
			plural = LexiconUtils.getTransliteration(plural);
			final String sql = " insert into item values(0 ,'" + pos + "','','"
					+ undotted + "','" + transliterated + "','formal','',0)";
			this.execute(sql);
			// System.out.println("sql=" + sql);
			final String sqlF = "insert into adjective( id ,gender,number,plural,feminine"
					+ ") values("
					+ "(SELECT ID FROM ITEM where transliterated = '"
					+ transliterated
					+ "' and pos='adjective')"
					+ ",'"
					+ gender
					+ "','"
					+ number
					+ "','"
					+ plural
					+ "','"
					+ "irrelevant"
					+ "') ";
			this.execute(sqlF);
			line = this.bi.readLine();
		}

	}

	public void populateAdverb() throws IOException {
		this.inputFile = "C:\\Documents and Settings\\daliabo\\Desktop\\adverb1.txt";
		this.inputFileHandling();
		String line = "";
		String undotted = "";
		String pos = "";
		String translation = "";
		String entry = "";
		String transliterated = "";
		line = this.bi.readLine();
		while (line != null) {
			System.out.println("line=" + line);
			final StringTokenizer st = new StringTokenizer(line, "\t");
			undotted = st.nextToken();
			undotted = URLEncoder.encode(undotted, "UTF8");
			System.out.println("undotted=" + undotted);
			transliterated = LexiconUtils.getTransliteration(undotted);
			pos = st.nextToken();
			System.out.println("pos=" + pos);
			translation = st.nextToken();
			System.out.println("translation=" + translation);
			entry = st.nextToken();
			System.out.println("************************");

			if (entry.equals("1")) {
				final String sql = " insert into item values(0 ,'" + pos
						+ "','','" + undotted + "','" + transliterated
						+ "','formal','',0)";
				this.execute(sql);

				final String sqlF = "insert into adverb"
						+ " values("
						+ "(SELECT ID FROM ITEM where transliterated = '"
						+ transliterated
						+ "' and pos='adverb')"
						+ ","
						+ "0,'unspecified','unspecified','unspecified','unspecified',0,'')";
				System.out.println("sql=" + sql);
				this.execute(sqlF);
			}
			line = this.bi.readLine();
		}
	}

	public void populateGematria() throws IOException {
		int i = 0;
		String line = "";
		String undotted = "";
		String transliterated = "";
		String val = "";
		this.inputFile = "C:\\Documents and Settings\\daliabo\\Desktop\\tgimatria.txt";
		this.inputFileHandling();
		line = this.bi.readLine();
		while ((line != null) && !line.equals("")) {
			System.out.println("line=" + line);
			final StringTokenizer st = new StringTokenizer(line, "\t");
			val = undotted = st.nextToken();
			undotted = st.nextToken();
			undotted = URLEncoder.encode(undotted, "UTF8");
			System.out.println("undotted=" + undotted);
			// transliterated = lexicon.tools.Translate.Heb2Eng(undotted);
			transliterated = LexiconUtils.getTransliteration(undotted);
			System.out.println("before transliterated=" + transliterated);
			transliterated.indexOf("\"");
			transliterated = this.apostropheHandling(transliterated);
			if (transliterated.indexOf("\"") != -1) {
				transliterated = transliterated.substring(1,
						transliterated.length() - 1);
				transliterated = transliterated.replaceAll("\"\"", "%22");
			}
			System.out.println("after transliterated=" + transliterated);
			i++;
			final String sql = " insert into gimatria values(" + i + " ,'"
					+ transliterated + "'," + val + ")";
			System.out.println("sql=" + sql);
			this.execute(sql);
			line = this.bi.readLine();
		}
		System.out.println("******finish**********");
	}

	public void populateNoun() throws IOException {
		this.inputFile = "C:\\Documents and Settings\\daliabo\\Desktop\\noun61.txt";
		this.inputFileHandling();
		String line = "";
		String undotted = "";
		String pos = "";
		String gender = "";
		String number = "";
		String plural = "";
		String transliterated = "";

		line = this.bi.readLine();
		while (line != null) {
			// System.out.println("line=" + line);
			final StringTokenizer st = new StringTokenizer(line, "\t");
			undotted = st.nextToken();
			undotted = URLEncoder.encode(undotted, "UTF8");
			// System.out.println("undotted=" + undotted);
			transliterated = LexiconUtils.getTransliteration(undotted);
			pos = st.nextToken();
			st.nextToken();
			// System.out.println("translation=" + translation);
			gender = st.nextToken();
			// System.out.println("gender =" + gender);
			if (gender.equals("זכר")) {
				gender = "masculine";
			} else {
				gender = "feminine";
			}
			number = st.nextToken();
			// System.out.println("number =" + number);
			plural = st.nextToken();
			// System.out.println("plural =" + plural);

			plural = URLEncoder.encode(plural, "UTF8");
			plural = LexiconUtils.getTransliteration(plural);

			if (number.equals("יחיד")) {
				number = "singular";
			} else {
				number = "plural";
			}

			// System.out.println("************************");

			final String sql = " insert into item values(0,'" + pos + "','','"
					+ undotted + "','" + transliterated + "','formal','',0)";
			this.execute(sql);

			final String sqlF = "insert into noun( id ,gender,number,plural,is_dual,definiteness,direction,feminine) "
					+ ""
					+ " values("
					+ "(SELECT ID FROM ITEM where transliterated = '"
					+ transliterated
					+ "' and pos='noun')"
					+ ",'"
					+ gender
					+ "','"
					+ number
					+ "','"
					+ plural
					+ "',0,'false','false','irrelevant') ";
			// System.out.println("sql=" + sql);
			this.execute(sqlF);

			line = this.bi.readLine();
		}
	}

	public void populateSense() throws Exception {
		this.inputFile = "C:\\Documents and Settings\\daliabo\\Desktop\\nounTran1.txt";
		this.inputFileHandling();
		String line = "";
		String undotted = "";
		String pos = "";
		String translation = "";
		String transliterated = "";
		ResultSet rs = null;
		// getting the max id of item table for populating it with more entries
		// with the relevant up going id

		int id = 0;
		line = this.bi.readLine();
		while (line != null) {
			// System.out.println("line=" + line);
			final StringTokenizer st = new StringTokenizer(line, "\t");
			undotted = st.nextToken();
			undotted = URLEncoder.encode(undotted, "UTF8");
			// System.out.println("undotted=" + undotted);
			transliterated = LexiconUtils.getTransliteration(undotted);
			pos = st.nextToken();
			// System.out.println("pos=" + pos);
			translation = st.nextToken();
			System.out.println("translation=" + translation);

			final String sqlCheckExistence = "select * from item where transliterated='"
					+ transliterated + "'and pos='" + pos + "' and deleted=0";
			rs = null;
			try {
				rs = this.getData(sqlCheckExistence);
				if (rs.next()) {
					id = rs.getInt("id");
				}
				rs.close();
			} catch (final SQLException E) {
				E.printStackTrace();
				System.exit(1);
			} finally {
				this.releaseConnection();
			}

			// if we found relevant entry in item table/exception table
			// - we wiil check if the translation already exists

			final StringTokenizer stran = new StringTokenizer(translation, "|");
			String sqlF = "";
			while (stran.hasMoreTokens()) {
				final String trans = stran.nextToken();
				final String sqlS = "insert into sense" + " values(0," + id
						+ ",'" + URLEncoder.encode("אין הגדרה", "UTF8")
						+ "',1)";
				System.out.println("sqlS=" + sqlS);
				this.execute(sqlS);

				int maxID = 0;
				final String sql = "select max(sid) as maxid from sense";
				rs = null;
				try {
					rs = this.getData(sql);
					if (rs == null) {
						System.exit(1);
					}
					if (rs.next()) {
						maxID = rs.getInt("maxid");
					}
					rs.close();
				} catch (final SQLException E) {
					E.printStackTrace();
				} finally {
					this.releaseConnection();
				}
				System.out.println("maxID=" + maxID);

				sqlF = "insert into english" + " values(0," + maxID + "," + "'"
						+ trans + "',1)";
				this.execute(sqlF);
				System.out.println("sqlF=" + sqlF);
			}
			line = this.bi.readLine();
		}
	}
}
