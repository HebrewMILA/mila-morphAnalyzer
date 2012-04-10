/*
 * Created on 04/06/2006
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package lexicon.tools;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintStream;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.StringTokenizer;

import lexicon.contents.ConnectedGenerator;
import lexicon.contents.Log;
import lexicon.stringUtils.Translate;

/**
 * @author daliabo
 * 
 * TODO To change the template for this generated type comment go to Window -
 * Preferences - Java - Code Style - Code Templates
 */
public class GenerateGerund extends ConnectedGenerator {

	FileOutputStream gerundsInNoun;

	PrintStream pUpdateGerunds = null;

	PrintStream pGerunds = null;

	String dir = "C:\\Documents and Settings\\daliabo\\My Documents\\lexicon\\diffTests\\";

	FileOutputStream gerunds; // declare a file output object

	FileOutputStream updateGerunds;

	protected BufferedReader bi = null;

	String inputFile = "";

	protected BufferedWriter bw = null;

	protected OutputStreamWriter pOut = null;

	String outputFile;

	protected void outputFileHandling() {
		FileOutputStream out;
		try {
			out = new FileOutputStream(outputFile);
			pOut = new OutputStreamWriter(out);
			bw = new BufferedWriter(pOut);
		} catch (FileNotFoundException e) {
			System.err.println("output File not found:  " + outputFile);
			e.printStackTrace();
			System.exit(1);
		}
	}

	protected void inputFileHandling() {
		try {
			bi = new BufferedReader(new InputStreamReader(new FileInputStream(
					inputFile), "UTF8"));
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			System.err.println("input File not found:  " + inputFile);
			e.printStackTrace();
			System.exit(1);
		}
	}

	public static String formatDateSQL(java.util.Date date) {
		if (date == null) {
			return "";
		}
		Calendar theDate = Calendar.getInstance();
		theDate.setTime(date);
		String output = "";
		output += theDate.get(Calendar.YEAR) + "-";
		int month = theDate.get(Calendar.MONTH) + 1;
		output += month + "-";
		output += theDate.get(Calendar.DATE) + " ";

		output += theDate.get(Calendar.HOUR_OF_DAY) + ":";
		output += theDate.get(Calendar.MINUTE) + ":";
		output += theDate.get(Calendar.SECOND) + "";
		return output;
	}

	public String bgdkft(String inputStr) {
		String outputStr = inputStr;
		if (inputStr.charAt(0) == 'ב' || inputStr.charAt(0) == 'ג'
				|| inputStr.charAt(0) == 'ד' || inputStr.charAt(0) == 'כ'
				|| inputStr.charAt(0) == 'פ' || inputStr.charAt(0) == 'ת') {
			outputStr = inputStr.charAt(0) + "ּ" + inputStr.substring(1);
		}
		return outputStr;
	}

	public String handleShin(String inputStr, String dotted) {
		String outputStr = inputStr;
		int indexd = dotted.indexOf('ש');
		if (indexd != -1) {
			outputStr = inputStr.replaceAll("ש", dotted.substring(indexd,
					indexd + 2));
			//System.out.println(outputStr);
		}

		return outputStr;

	}

	public void generateGerundPaal() throws FileNotFoundException {
		ResultSet rs = null;
		String gerundNikud = "";
		String gerund = "";
		pGerunds.println("paal");
		pGerunds.println("**********************************");
		try {
			String sql = "select item.dotted, verb.id , item.transliterated , verb.inflectionPattern , verb.root from item, verb "
					+ "where item.id=verb.id and verb.inflectionPattern in(1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,22) and deleted=0";
			String transliterated = "";
			rs = getData(sql);
			try {
				while (rs.next()) {
					transliterated = rs.getString("transliterated");
					String surface = Translate.Eng2Heb(transliterated);
					String id = rs.getString("id");
					String root = rs.getString("root");
					String dotted = rs.getString("dotted");
					dotted = URLDecoder.decode(dotted, "UTF-8");
					root = URLDecoder.decode(root, "UTF-8");
					String transliteratedRoot = Translate.Heb2Eng(root);
					if (transliteratedRoot.charAt(2) != 'h') {
						if (transliteratedRoot.charAt(1) == 'w'
								&& surface.length() == 2) {
							gerund = transliteratedRoot.charAt(0) + "i"
									+ transliteratedRoot.charAt(2) + "h";
						} else if (transliteratedRoot.charAt(1) == 'w'
								&& surface.length() == 4) {
							gerund = transliteratedRoot.substring(0, 2) + "wi"
									+ transliteratedRoot.substring(2) + "h";
						} else
							gerund = transliteratedRoot.substring(0, 2) + "i"
									+ transliteratedRoot.substring(2) + "h";

					} else {
						if (transliteratedRoot.charAt(1) == 'w'
								&& surface.length() == 2) {
							gerund = transliteratedRoot.charAt(0) + "i"
									+ transliteratedRoot.charAt(2) + "h";
						} else if (transliteratedRoot.charAt(1) == 'w'
								&& surface.length() == 4) {
							gerund = transliteratedRoot.substring(0, 2)
									+ "wiih";
						} else
							gerund = transliteratedRoot.substring(0, 2) + "ii"
									+ "h";
					}

					String hebGerund = Translate.Eng2Heb(gerund);
					switch (transliteratedRoot.charAt(2)) {
					case 'h':
						switch (transliteratedRoot.charAt(0)) {
						case 'a':
						case 'h':
						case 'x':
						case 'y':
							if (transliteratedRoot.charAt(1) == 'w'
									&& surface.length() == 2) {
								gerundNikud = hebGerund.charAt(0) + "ִי"
										+ hebGerund.charAt(2) + "ָה";

							} else if (transliteratedRoot.charAt(1) == 'w'
									&& surface.length() == 4) {
								gerundNikud = hebGerund.charAt(0) + "ֲ"
										+ hebGerund.charAt(1) + "ִ" + "י"
										+ hebGerund.substring(4, 5) + "ָה";
							} else
								gerundNikud = hebGerund.charAt(0) + "ֲ"
										+ hebGerund.charAt(1) + "ִ" + "יָּה";
							break;
						default:
							if (transliteratedRoot.charAt(1) == 'w'
									&& surface.length() == 2) {
								gerundNikud = hebGerund.charAt(0) + "ִי"
										+ hebGerund.charAt(2) + "ָה";

							} else if (transliteratedRoot.charAt(1) == 'w'
									&& surface.length() == 4) {
								gerundNikud = hebGerund.charAt(0) + "ְ"
										+ hebGerund.charAt(1) + "ִ" + "יָּה";
							} else
								gerundNikud = hebGerund.charAt(0) + "ְ"
										+ hebGerund.charAt(1) + "ִ" + "יָּה";
							break;
						}
						break;
					default:
						switch (transliteratedRoot.charAt(0)) {
						case 'a':
						case 'h':
						case 'x':
						case 'y':
							if (transliteratedRoot.charAt(1) == 'w'
									&& surface.length() == 2) {
								gerundNikud = hebGerund.charAt(0) + "ִי"
										+ hebGerund.charAt(2) + "ָה";
							} else if (transliteratedRoot.charAt(1) == 'w'
									&& surface.length() == 4) {
								gerundNikud = hebGerund.charAt(0) + "ֲ"
										+ hebGerund.charAt(1) + "ִ" + "י"
										+ hebGerund.substring(4, 5) + "ָה";
							} else
								gerundNikud = hebGerund.charAt(0) + "ֲ"
										+ hebGerund.charAt(1) + "ִ" + "י"
										+ hebGerund.substring(3, 4) + "ָה";
							break;

						default:
							if (transliteratedRoot.charAt(1) == 'w'
									&& surface.length() == 2) {
								gerundNikud = hebGerund.charAt(0) + "ִי"
										+ hebGerund.charAt(2) + "ָה";
							} else if (transliteratedRoot.charAt(1) == 'w'
									&& surface.length() == 4) {
								gerundNikud = hebGerund.charAt(0) + "ְ"
										+ hebGerund.charAt(1) + "ִ" + "י"
										+ hebGerund.substring(4, 5) + "ָה";
							} else
								gerundNikud = hebGerund.charAt(0) + "ְ"
										+ hebGerund.charAt(1) + "ִ" + "י"
										+ hebGerund.substring(3, 4) + "ָה";
							break;
						}

					}

					gerundNikud = handleShin(gerundNikud, dotted);
					gerundNikud = bgdkft(gerundNikud);
					String encodedGerund = URLEncoder.encode(gerundNikud,
							"UTF-8");

					pGerunds.print(id);
					pGerunds.print(" 	" + surface);
					pGerunds.print(" 	" + root);
					pGerunds.print(" 	" + gerund);
					pGerunds.print(" 	" + gerundNikud);
					//pGerunds.print(" " + encodedGerund);
					pGerunds.print(" 	" + hebGerund);
					//pGerunds.println();
					//findInNounTable(encodedGerund, gerund, id);
					//findInExceptionNounTable(encodedGerund, gerund);
				}
				releaseConnection();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			rs.close();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} finally {
			releaseConnection();
			pGerunds.close();
			System.out.println("File is ready");
		}

	}

	public void findInNounTable(String encodedGerundNikudStr,
			String transliteratedGerund, String id)
			throws FileNotFoundException {
		ResultSet rs = null;
		boolean found = false;
		String sql = "";
		String transliterated = "";
		String dottedId = "";
		String undottedId = "";
		java.util.Date rightNow = new java.util.Date();
		try {
			rs = null;
			sql = "select transliterated,id from item where pos='noun' and dotted='"
					+ encodedGerundNikudStr + "' and deleted=0";
			rs = getData(sql);
			while (rs.next()) {
				transliterated = rs.getString("transliterated");
				String surface = Translate.Eng2Heb(transliterated);
				dottedId = rs.getString("id");
				pGerunds.print("	" + dottedId);
				found = true;
			}
			releaseConnection();
			//if the form with nikud was not found perhaps there is an entry
			// without nikud
			//so we only need to update the dotted field and not create a new
			// entry in the noun table
			//if (!found) {
			rs = null;
			sql = "select transliterated,id from item where pos='noun' and transliterated='"
					+ transliteratedGerund + "' and deleted=0";
			rs = getData(sql);
			while (rs.next()) {
				undottedId = rs.getString("id");
				pGerunds.print("	|" + undottedId);
				//if (dottedId.equals(undottedId)){
				if (found) {
					pUpdateGerunds.print(id);
					pUpdateGerunds.print(" " + dottedId);
					pUpdateGerunds.println();
					found = false;
				}
			}
			//}
			//found = false;
			pGerunds.println();
			releaseConnection();
			rs.close();
			//updateNounTable();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			releaseConnection();
		}
	}

	public void updateNounTable() throws IOException {
		BufferedReader bi = new BufferedReader(new InputStreamReader(
				new FileInputStream(dir + "PielGerundVerbListUpdate.txt")));
		String line = "";
		String id = "";
		String dottedId = "";
		while ((line = bi.readLine()) != null) {
			System.out.println("line=" + line);
			StringTokenizer st = new StringTokenizer(line);
			while (st.hasMoreTokens()) {
				id = st.nextToken();
				System.out.println("id =" + id);
				dottedId = st.nextToken();
				System.out.println("dottedId =" + dottedId);
				String sqlUpdate = "update noun set lexicalLink='" + id
						+ "'where id='" + dottedId + "'";
				System.out.println("sqlUpdate =" + sqlUpdate);
				execute(sqlUpdate);
				Log log = new Log(1000005, Integer.parseInt(dottedId), 0,
						"Auto update lexicalLink");
				log.add();
			}

		}
		System.out.println("finish Updating");
	}

	public void generateGerundNifal() throws FileNotFoundException {
		ResultSet rs = null;
		String gerundNikud = "";
		String gerund = "";
		pGerunds.println("Nifal");
		pGerunds.println("**********************************");
		try {
			String sql = "select item.dotted, verb.id , item.transliterated , verb.inflectionPattern , verb.root from item, verb "
					+ "where item.id=verb.id and verb.inflectionPattern in(58,59,60,61) and deleted=0";
			String transliterated = "";
			rs = getData(sql);
			while (rs.next()) {
				transliterated = rs.getString("transliterated");
				String surface = Translate.Eng2Heb(transliterated);
				String id = rs.getString("id");
				String root = rs.getString("root");
				String dotted = rs.getString("dotted");
				dotted = URLDecoder.decode(dotted, "UTF-8");
				root = URLDecoder.decode(root, "UTF-8");
				String transliteratedRoot = Translate.Heb2Eng(root);
				if (transliteratedRoot.charAt(2) == 'h'
						|| transliteratedRoot.charAt(2) == 'i') {
					gerund = "hi" + transliteratedRoot.substring(0, 2) + "wt";
				} else
					gerund = "hi" + transliteratedRoot + "wt";
				String hebGerund = Translate.Eng2Heb(gerund);

				switch (transliteratedRoot.charAt(0)) {
				case 'a':
				case 'h':
				case 'x':
				case 'y':
				case 'r':
					switch (transliteratedRoot.charAt(1)) {
					case 'a':
					case 'h':
					case 'x':
					case 'y':
						gerundNikud = "הֵ" + hebGerund.charAt(2) + "ָ"
								+ hebGerund.charAt(3) + "ֲ"
								+ hebGerund.charAt(4) + "וּת";
						break;
					default:
						gerundNikud = "הֵ" + hebGerund.charAt(2) + "ָ"
								+ hebGerund.charAt(3) + "ְ"
								+ hebGerund.charAt(4) + "וּת";

						break;
					}
					break;
				default:
					switch (transliteratedRoot.charAt(1)) {
					case 'a':
					case 'h':
					case 'x':
					case 'y':
						gerundNikud = "הִ" + hebGerund.charAt(2) + "ָּ"
								+ hebGerund.charAt(3) + "ֲ"
								+ hebGerund.charAt(4) + "וּת";
						break;
					default:
						gerundNikud = "הִ" + hebGerund.charAt(2) + "ָּ"
								+ hebGerund.charAt(3) + "ְ"
								+ hebGerund.charAt(4) + "וּת";
						break;
					}
				}

				if (transliteratedRoot.charAt(2) == 'h'
						|| transliteratedRoot.charAt(2) == 'i') {
					gerundNikud = gerundNikud.substring(0,
							gerundNikud.length() - 5)
							+ "וּת";
				}

				gerundNikud = handleShin(gerundNikud, dotted);
				String encodedGerund = URLEncoder.encode(gerundNikud, "UTF-8");

				pGerunds.print(id);
				pGerunds.print(" 	" + surface);
				pGerunds.print(" 	" + root);
				pGerunds.print(" 	" + gerund);
				pGerunds.print(" 	" + gerundNikud);
				pGerunds.print(" 	" + encodedGerund);
				pGerunds.print(" 	" + hebGerund);
				//pGerunds.println();
				findInNounTable(encodedGerund, gerund, id);
				findInExceptionNounTable(encodedGerund, gerund);
			}
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();

		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			rs.close();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} finally {
			releaseConnection();
		}
		pGerunds.close();
		System.out.println("File is ready");
	}

	public void generateGerundPiel() throws FileNotFoundException {
		ResultSet rs = null;
		String gerundNikud = "";
		String gerund = "";
		pGerunds.println("Nifal");
		pGerunds.println("**********************************");
		try {
			String sql = "select item.dotted, verb.id , item.transliterated , verb.inflectionPattern , verb.root from item, verb "
					+ "where item.id=verb.id and verb.inflectionPattern in(23,25,26,27,28,29,30,31,32,33,34) and deleted=0 and verb.id=2692";
			String transliterated = "";
			rs = getData(sql);
			while (rs.next()) {
				transliterated = rs.getString("transliterated");
				String surface = Translate.Eng2Heb(transliterated);
				String id = rs.getString("id");
				String root = rs.getString("root");
				String dotted = rs.getString("dotted");
				dotted = URLDecoder.decode(dotted, "UTF-8");
				root = URLDecoder.decode(root, "UTF-8");
				String transliteratedRoot = Translate.Heb2Eng(root);
				if (transliteratedRoot.charAt(2) == 'h') {
					gerund = transliteratedRoot.charAt(0) + "i"
							+ transliteratedRoot.charAt(1) + "w" + "i";
				} else
					gerund = transliteratedRoot.charAt(0) + "i"
							+ transliteratedRoot.charAt(1) + "w"
							+ transliteratedRoot.substring(2);
				String hebGerund = Translate.Eng2Heb(gerund);
				switch (transliteratedRoot.charAt(2)) {
				case 'x':
				case 'y':
					switch (transliteratedRoot.charAt(1)) {
					case 'a':
					case 'r':
						gerundNikud = hebGerund.charAt(0) + "ֵ"
								+ hebGerund.charAt(1) + "וּ"
								+ hebGerund.charAt(3) + "ַ";
						break;
					case 'h':
					case 'x':
					case 'y':
						gerundNikud = hebGerund.charAt(0) + "ִ"
								+ hebGerund.charAt(1) + "וּ"
								+ hebGerund.charAt(3) + "ַ";
						break;

					default:
						gerundNikud = hebGerund.charAt(0) + "ִ"
								+ hebGerund.charAt(1) + "ּוּ"
								+ hebGerund.charAt(3) + "ַ";
						break;
					}
					break;
				default:
					switch (transliteratedRoot.charAt(1)) {
					case 'a':
					case 'r':
						gerundNikud = hebGerund.charAt(0) + "ֵ"
								+ hebGerund.charAt(1) + "וּ"
								+ hebGerund.charAt(3);
						break;
					case 'h':
					case 'x':
					case 'y':
						gerundNikud = hebGerund.charAt(0) + "ִ"
								+ hebGerund.charAt(1) + "וּ"
								+ hebGerund.charAt(3);
						break;
					default:
						gerundNikud = hebGerund.charAt(0) + "ִ"
								+ hebGerund.charAt(1) + "ּוּ"
								+ hebGerund.charAt(3);
						break;
					}

				}

				if (transliteratedRoot.charAt(2) == 'h') {
					gerundNikud = gerundNikud.substring(0,
							gerundNikud.length() - 1)
							+ "י";
				}

				int indexd1 = dotted.indexOf('ש');
				int indexd2 = gerundNikud.indexOf('ש');
				if (indexd1 != -1) {
					if (dotted.indexOf('ּ') == indexd1 + 1) {
						gerundNikud = gerundNikud.replaceAll("ש", dotted
								.substring(indexd1, indexd1 + 3));
						gerundNikud = gerundNikud.substring(0, indexd1 + 3)
								+ gerundNikud.substring(indexd1 + 4);
					} else
						gerundNikud = gerundNikud.replaceAll("ש", dotted
								.substring(indexd1, indexd1 + 2));
				}
				String encodedGerund = URLEncoder.encode(gerundNikud, "UTF-8");

				pGerunds.print(id);
				pGerunds.print(" 	" + surface);
				pGerunds.print(" 	" + root);
				pGerunds.print(" 	" + gerund);
				pGerunds.print(" 	" + gerundNikud);
				pGerunds.print(" 	" + encodedGerund);
				pGerunds.print(" 	" + hebGerund);
				findInNounTable(encodedGerund, gerund, id);
			}
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();

		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			rs.close();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} finally {
			releaseConnection();
		}
		pGerunds.close();
		System.out.println("File is ready");
	}

	public void generateGerundHitpael() throws FileNotFoundException {
		ResultSet rs = null;
		String gerundNikud = "";
		String gerund = "";
		int index;
		int rootLen;
		pGerunds.println("Hitpael");
		pGerunds.println("**********************************");
		try {
			String sql = "select item.dotted, verb.id , item.transliterated , verb.inflectionPattern , verb.root from item, verb "
					+ "where item.id=verb.id and verb.inflectionPattern in(42,43,44,45,46,47,58,59,60,61,62) and deleted=0 ";
			String transliterated = "";
			rs = getData(sql);
			while (rs.next()) {
				transliterated = rs.getString("transliterated");
				String surface = Translate.Eng2Heb(transliterated);
				String id = rs.getString("id");
				String root = rs.getString("root");
				String dotted = rs.getString("dotted");
				dotted = URLDecoder.decode(dotted, "UTF-8");
				root = URLDecoder.decode(root, "UTF-8");
				String transliteratedRoot = Translate.Heb2Eng(root);
				rootLen = transliteratedRoot.length();
				int inflectionPattren = Integer.valueOf(
						rs.getString("inflectionPattern")).intValue();
				if (inflectionPattren <= 47) {
					if (transliterated.endsWith("wh"))
						gerund = transliterated.substring(0, transliterated
								.length() - 2)
								+ "wt";
					else if (transliterated.endsWith("h") && rootLen == 3)
						gerund = transliterated.substring(0, transliterated
								.length() - 1)
								+ "wt";
					else
						gerund = transliterated + "wt";
				} else {
					if (transliteratedRoot.charAt(1) == transliteratedRoot.charAt(2))
						gerund = "hi"
							+ transliteratedRoot +  "wt";
					else if (transliterated.startsWith("nw"))
						gerund = "hiw"
								+ transliterated.substring(1) + "wt";
					else if (transliterated.startsWith("ni"))
						gerund = "hin"
								+ transliterated.substring(2) + "wt";
					else if (transliterated.endsWith("wh"))
						gerund = "hi"
								+ transliterated.substring(1, transliterated
										.length() - 1) + "t";
					else if (transliterated.endsWith("h"))
						gerund = "hi"
								+ transliterated.substring(1, transliterated
										.length() - 1) + "wt";
					else
						gerund = "hi" + transliterated.substring(1) + "wt";
				}
				//				switch (transliteratedRoot.charAt(0)) {
				//				case 'z':
				//					gerund = "hzd" + transliteratedRoot.substring(1) + "wt";
				//					break;
				//				case 's':
				//					gerund = "hst" + transliteratedRoot.substring(1) + "wt";
				//					break;
				//				case 'e':
				//					gerund = "het" + transliteratedRoot.substring(1) + "wt";
				//					break;
				//				case 'c':
				//					gerund = "hcv" + transliteratedRoot.substring(1) + "wt";
				//					break;
				//				default:
				//					if (transliteratedRoot.charAt(2) == 'h')
				//						gerund = "ht" + transliteratedRoot.substring(0, 2)
				//								+ "wt";
				//					else if (transliteratedRoot.charAt(1) == transliteratedRoot
				//							.charAt(2))
				//						gerund = "ht" + transliteratedRoot.charAt(0) + "w"
				//								+ transliteratedRoot.substring(1) + "wt";
				//					else if (transliteratedRoot.charAt(1) == 'w' &&
				// transliteratedRoot.length()==4)
				//						gerund = "ht" + transliteratedRoot.charAt(0) + "w"
				//								+ transliteratedRoot.substring(1, 2) + "wt";
				//					else if (transliteratedRoot.charAt(1) == 'w' && rootLen==3)
				//						gerund = "ht" + transliteratedRoot +
				// transliteratedRoot.charAt(2)+ "wt";
				//					else if ((index = transliteratedRoot.indexOf("i")) !=-1)
				//						gerund = "ht" + transliteratedRoot.substring(index) + "i"
				//								+ transliteratedRoot.substring(index) + "wt";
				//					else
				//						gerund = "ht" + transliteratedRoot + "wt";
				//					break;
				//				}

				String hebGerund = Translate.Eng2Heb(gerund);

				if (rootLen == 3) {

					//ע הפועל גרונית
					switch (transliteratedRoot.charAt(1)) {
					case 'a':
					case 'h':
					case 'x':
					case 'y':
						switch (transliteratedRoot.charAt(0)) {
						case 'z':
							gerundNikud = "הִזְדַּ" + hebGerund.charAt(3) + "ֲ"
									+ hebGerund.charAt(4) + "וּת";
							break;
						case 's':
							gerundNikud = "הִסְתַּ" + hebGerund.charAt(3) + "ֲ"
									+ hebGerund.charAt(4) + "וּת";
							break;
						case 'e':
							gerundNikud = "הִשְתַּ" + hebGerund.charAt(3) + "ֲ"
									+ hebGerund.charAt(4) + "וּת";
							break;
						case 'c':
							gerundNikud = "הִצְטַ" + hebGerund.charAt(3) + "ֲ"
									+ hebGerund.charAt(4) + "וּת";
							break;
						case 'a':
						case 'h':
						case 'x':
						case 'r':
						case 'y':
							gerundNikud = "הִתְ" + hebGerund.charAt(2) + "ַ"
									+ hebGerund.charAt(3) + "ֲ"
									+ hebGerund.charAt(4) + "וּת";
							break;
						default:
							gerundNikud = "הִתְ" + hebGerund.charAt(2) + "ַּ"
									+ hebGerund.charAt(3) + "ֲ"
									+ hebGerund.charAt(4) + "וּת";

							break;
						}
						break;
					case 'b':
					case 'g':
					case 'd':
					case 'k':
					case 'p':
					case 't': //ע הפועל בגדכפת
						switch (transliteratedRoot.charAt(0)) {
						case 'z':
							gerundNikud = "הִזְדַּ" + hebGerund.charAt(3)
									+ "ְּ" + hebGerund.charAt(4) + "וּת";
							break;
						case 's':
							gerundNikud = "הִסְתַּ" + hebGerund.charAt(3)
									+ "ְּ" + hebGerund.charAt(4) + "וּת";
							break;
						case 'e':
							gerundNikud = "הִשְתַּ" + hebGerund.charAt(3)
									+ "ְּ" + hebGerund.charAt(4) + "וּת";
							break;
						case 'c':
							gerundNikud = "הִצְטַ" + hebGerund.charAt(3) + "ְּ"
									+ hebGerund.charAt(4) + "וּת";
							break;
						case 'a':
						case 'h':
						case 'x':
						case 'r':
						case 'y':
							if (transliteratedRoot.charAt(2) == 'h')
								gerundNikud = "הִתְ" + hebGerund.charAt(2)
										+ "ַ" + hebGerund.charAt(3) + "ּ"
										+ "וּת";
							else
								gerundNikud = "הִתְ" + hebGerund.charAt(2)
										+ "ַ" + hebGerund.charAt(3) + "ְּ"
										+ hebGerund.charAt(4) + "וּת";
							break;
						default:
							if (transliteratedRoot.charAt(2) == 'h')
								gerundNikud = "הִתְ" + hebGerund.charAt(2)
										+ "ַּ" + hebGerund.charAt(3) + "ּ "
										+ "וּת";
							else
								gerundNikud = "הִתְ" + hebGerund.charAt(2)
										+ "ַּ" + hebGerund.charAt(3) + "ְּ"
										+ hebGerund.charAt(4) + "וּת";
							break;
						}
						break;
					default: //  ע הפועל לא גרונית ולא בגדכפת
						switch (transliteratedRoot.charAt(0)) {
						case 'z':
							gerundNikud = "הִזְדַּ" + hebGerund.charAt(3) + "ְ"
									+ hebGerund.charAt(4) + "וּת";
							break;
						case 's':
							gerundNikud = "הִסְתַּ" + hebGerund.charAt(3) + "ְ"
									+ hebGerund.charAt(4) + "וּת";
							break;
						case 'e':
							gerundNikud = "הִשְתַּ" + hebGerund.charAt(3) + "ְ"
									+ hebGerund.charAt(4) + "וּת";
							break;
						case 'c':
							gerundNikud = "הִצְטַ" + hebGerund.charAt(3) + "ְ"
									+ hebGerund.charAt(4) + "וּת";
							break;
						case 'a':
						case 'h':
						case 'x':
						case 'r':
						case 'y':
							if (transliteratedRoot.charAt(2) == 'h')
								gerundNikud = "הִתְ" + hebGerund.charAt(2)
										+ "ַ" + hebGerund.charAt(3) + "ּ"
										+ "וּת";
							else
								gerundNikud = "הִתְ" + hebGerund.charAt(2)
										+ "ַ" + hebGerund.charAt(3) + "ְ"
										+ hebGerund.charAt(4) + "וּת";
							break;

						default:
							if (transliteratedRoot.charAt(2) == 'h')
								gerundNikud = "הִתְ" + hebGerund.charAt(2)
										+ "ַּ" + hebGerund.charAt(3) + "ּ"
										+ "וּת";
							else
								gerundNikud = "הִתְ" + hebGerund.charAt(2)
										+ "ַּ" + hebGerund.charAt(3) + "ְ"
										+ hebGerund.charAt(4) + "וּת";
							break;
						}
					}

					//ROOT LEN = 4
				} else {
					switch (transliteratedRoot.charAt(1)) {
					case 'a':
					case 'h':
					case 'x':
					case 'y':
						switch (transliteratedRoot.charAt(0)) {
						case 'z':
							gerundNikud = "הִזְדַּ" + hebGerund.charAt(3) + "ֲ"
									+ hebGerund.charAt(4) + "ְ"
									+ hebGerund.charAt(5) + "וּת";
							break;
						case 's':
							gerundNikud = "הִסְתַּ" + hebGerund.charAt(3) + "ֲ"
									+ hebGerund.charAt(4) + "ְ"
									+ hebGerund.charAt(5) + "וּת";
							break;
						case 'e':
							gerundNikud = "הִשְתַּ" + hebGerund.charAt(3) + "ֲ"
									+ hebGerund.charAt(4) + "ְ"
									+ hebGerund.charAt(5) + "וּת";
							break;
						case 'c':
							gerundNikud = "הִצְטַ" + hebGerund.charAt(3) + "ֲ"
									+ hebGerund.charAt(4) + "ְ"
									+ hebGerund.charAt(5) + "וּת";
							break;
						case 'a':
						case 'h':
						case 'x':
						case 'r':
						case 'y':
							gerundNikud = "הִתְ" + hebGerund.charAt(2) + "ַ"
									+ hebGerund.charAt(3) + "ֲ"
									+ hebGerund.charAt(4) + "ְ"
									+ hebGerund.charAt(5) + "וּת";
							break;
						default:
							gerundNikud = "הִתְ" + hebGerund.charAt(2) + "ַּ"
									+ hebGerund.charAt(3) + "ֲ"
									+ hebGerund.charAt(4) + "ְ"
									+ hebGerund.charAt(5) + "וּת";

							break;
						}
						switch (transliteratedRoot.charAt(2)) {
						case 'b':
						case 'g':
						case 'd':
						case 'k':
						case 'p':
						case 't':
							gerundNikud = "הִתְ" + hebGerund.charAt(2) + "ַּ"
									+ hebGerund.charAt(3) + "ֲ"
									+ hebGerund.charAt(4) + "ְּ"
									+ hebGerund.charAt(5) + "וּת";

							break;
						}
						break;
					default:
						switch (transliteratedRoot.charAt(0)) {
						case 'z':
							gerundNikud = "הִזְדַּ" + hebGerund.charAt(3) + "ְ"
									+ hebGerund.charAt(4) + "ְ"
									+ hebGerund.charAt(5) + "וּת";
							break;
						case 's':
							gerundNikud = "הִסְתַּ" + hebGerund.charAt(3) + "ְ"
									+ hebGerund.charAt(4) + "ְ"
									+ hebGerund.charAt(5) + "וּת";
							break;
						case 'e':
							gerundNikud = "הִשְתַּ" + hebGerund.charAt(3) + "ְ"
									+ hebGerund.charAt(4) + "ְ"
									+ hebGerund.charAt(5) + "וּת";
							break;
						case 'c':
							gerundNikud = "הִצְטַ" + hebGerund.charAt(3) + "ְ"
									+ hebGerund.charAt(4) + "ְ"
									+ hebGerund.charAt(5) + "וּת";
							break;
						case 'b':
						case 'g':
						case 'd':
						case 'k':
						case 'p':
						case 't':
							gerundNikud = "הִתְ" + hebGerund.charAt(2) + "ַּ"
									+ hebGerund.charAt(3) + "ְ"
									+ hebGerund.charAt(4) + "ְּ"
									+ hebGerund.charAt(5) + "וּת";
							break;
						case 'a':
						case 'h':
						case 'x':
						case 'r':
						case 'y':
							gerundNikud = "הִתְ" + hebGerund.charAt(2) + "ַ"
									+ hebGerund.charAt(3) + "ְ"
									+ hebGerund.charAt(4) + "ְ"
									+ hebGerund.charAt(5) + "וּת";
							break;
						default:
							gerundNikud = "הִתְ" + hebGerund.charAt(2) + "ַּ"
									+ hebGerund.charAt(3) + "ְ"
									+ hebGerund.charAt(4) + "ְ"
									+ hebGerund.charAt(5) + "וּת";

							break;
						}
					}
				}

				int indexd1 = dotted.indexOf('ש');
				int indexd2 = gerundNikud.indexOf('ש');
				if (indexd1 != -1) {
					if (dotted.indexOf('ּ') == indexd1 + 1) {
						gerundNikud = gerundNikud.replaceAll("ש", dotted
								.substring(indexd1, indexd1 + 3));
						gerundNikud = gerundNikud.substring(0, indexd2 + 1)
								+ gerundNikud.substring(indexd2 + 2);
					} else
						gerundNikud = gerundNikud.replaceAll("ש", dotted
								.substring(indexd1, indexd1 + 2));
				}
				String encodedGerund = URLEncoder.encode(gerundNikud, "UTF-8");

				pGerunds.print(root);
				pGerunds.print(" 	" + id);
				pGerunds.print(" 	" + surface);
				pGerunds.print(" 	" + transliterated);
				pGerunds.print(" 	" + gerund);
				pGerunds.print(" 	" + gerundNikud);
				//pGerunds.print(" " + encodedGerund);
				pGerunds.print(" 	" + hebGerund);
				pGerunds.println();
				///////////////////////////////////////////////////////////////
				//More inflection
				///////////////////////////////////////////////////////////////
				//				switch (transliteratedRoot.charAt(0)) {
				//				case 'd':
				//				case 'v':
				//				case 't':
				//					gerund = transliterated.charAt(0,index) +
				// transliterated.substring(2) + "wt";
				//					hebGerund = Translate.Eng2Heb(gerund);
				//
				//					switch (transliteratedRoot.charAt(1)) {
				//					case 'a':
				//					case 'h':
				//					case 'x':
				//					case 'y':
				//						gerundNikud = "הִ" + hebGerund.charAt(1) + "ַּ"
				//								+ hebGerund.charAt(2) + "ֲ"
				//								+ hebGerund.charAt(3) + "וּת";
				//						break;
				//					default:
				//						gerundNikud = "הִ" + gerundNikud.substring(4);
				//						break;
				//					}
				//				}
				//
				//				if (transliteratedRoot.charAt(0) == 'd'
				//						|| transliteratedRoot.charAt(0) == 'v'
				//						|| transliteratedRoot.charAt(0) == 't') {
				//					// indexd1 = dotted.indexOf('ש');
				//					// indexd2 = gerundNikud.indexOf('ש');
				//					// if (indexd1 != -1) {
				//					// if (dotted.indexOf('ּ') == indexd1 + 1) {
				//					// gerundNikud = gerundNikud.replaceAll("ש", dotted
				//					// .substring(indexd1, indexd1 + 3));
				//					// gerundNikud = gerundNikud.substring(0, indexd1 + 3)
				//					// + gerundNikud.substring(indexd1 + 4);
				//					// } else
				//					// gerundNikud = gerundNikud.replaceAll("ש", dotted
				//					// .substring(indexd1, indexd1 + 2));
				//					// }
				//					// encodedGerund = URLEncoder.encode(gerundNikud, "UTF-8");
				//
				//					pGerunds.print(root);
				//					pGerunds.print(" " + id);
				//					pGerunds.print(" " + surface);
				//					pGerunds.print(" " + transliterated);
				//					pGerunds.print(" " + gerundNikud);
				//					//pGerunds.print(" " + encodedGerund);
				//					pGerunds.print(" " + hebGerund);
				//					pGerunds.println();
				//
				//					//findInNounTable(encodedGerund, gerund, id);
				//				}
			}
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();

		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			rs.close();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} finally {
			releaseConnection();
		}
		pGerunds.close();
		System.out.println("File is ready");
	}

	public void compareToNoun() throws IOException, SQLException {
		ResultSet rs = null;
		String transliterated;
		String id;
		boolean foundFlag = false;

		String sql = "select transliterated, id , pos from item where pos='noun' and transliterated like '%t'";
		rs = getData(sql);
		String line;
		while (rs.next()) {
			id = rs.getString("id");
			transliterated = rs.getString("transliterated");
			inputFileHandling();
			while ((line = bi.readLine()) != null) {
				if (line.equals(transliterated)) {
					foundFlag = true;
					bi.close();
					break;
				}
			}
			if (foundFlag) {
				foundFlag = false;
				continue;
			} else {
				System.out.print(transliterated);
				System.out.print("	" + id);
				System.out.println(id);
				String surface = Translate.Eng2Heb(transliterated);
				bw.write(surface);
				bw.write("	" + id);
				bw.newLine();
			}
		}

	}

	public void findInExceptionNounTable(String encodedGerundNikudStr,
			String transliteratedGerund) {
		ResultSet rs = null;
		boolean found = false;
		String sql = "";
		String transliterated = "";
		try {
			rs = null;
			sql = "select transliterated,id from noun_exception_type where dotted='"
					+ encodedGerundNikudStr + "'";
			rs = getData(sql);
			//System.out.println("sql="+sql);
			while (rs.next()) {
				transliterated = rs.getString("transliterated");
				String surface = Translate.Eng2Heb(transliterated);
				String id = rs.getString("id");
				pGerunds.print("	$" + id);
				found = true;
			}
			rs.close();
			releaseConnection();
			//if the form with nikud was not found perhaps there is an entry
			// without nikud
			//so we only need to update the dotted field and not create a new
			// entry in the noun table
			//if (!found) {
			rs = null;
			sql = "select transliterated,id from noun_exception_type  where transliterated='"
					+ transliteratedGerund + "'";
			//System.out.println("sql="+sql);
			rs = getData(sql);
			while (rs.next()) {
				String id = rs.getString("id");
				pGerunds.print("	$|" + id);
			}
			//}
			found = false;
			pGerunds.println();
			releaseConnection();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			releaseConnection();
		}
	}

	public void generateNounsEndWithTList() {
		inputFile = dir + "PielGerundVerbListAll.txt";
		outputFile = dir + "nounsEndWitht.txt";
		try {
			outputFileHandling();
			compareToNoun();
			bw.close();
			System.out.println("End writing file");
			System.exit(0);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public static void main(String[] args) {
		GenerateGerund g = new GenerateGerund();
		//g.generateNounsEndWithTList();

		//			g.pUpdateGerunds = new PrintStream(g.updateGerunds);

		try {
			//g.generateGerundPaal();
			//g.generateGerundNifal();
			//g.generateGerundPiel();
			g.gerunds = new FileOutputStream(g.dir
					+ "PielGerundVerbListAll.txt");
			g.pGerunds = new PrintStream(g.gerunds);
			//g.generateNounsEndWithTList();
			//g.updateGerunds = new FileOutputStream(g.dir
			//		+ "PielGerundVerbListUpdate.txt");
			g.generateGerundHitpael();
			//g.updateNounTable();
			//g.pUpdateGerunds.close();
			//g.gerunds.close();
			//} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			//	e1.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
