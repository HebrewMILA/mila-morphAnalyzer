/*
 * Created on 08/08/2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package lexicon.dbUtils;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.StringTokenizer;
import lexicon.contents.ConnectedGenerator;
import lexicon.stringUtils.StrUtils;

/**
 * @author daliabo
 * 
 *         TODO To change the template for this generated type comment go to
 *         Window - Preferences - Java - Code Style - Code Templates
 */
public class rules1 extends ConnectedGenerator {
	String inflectedTransliterated = "";

	String action = "";

	String transliterated = "";

	String inputCondition = "";

	String tInputPattern = "";

	String tInputCondition = "";

	String tInflectedPattern = "";

	String subLast = "";

	String feature = "";

	String featureVal = "";

	int maxLength;

	ResultSet rs = null;

	/**
	 * This method exceute the query for geting the default rules<br>
	 * Possessive rules have a separete handling in
	 * defaultPossessiveRulesHandling()<br>
	 */
	private void getDefaultRule() {
		String sql = "select * from rules1 where  action = '" + action + "' and inputPattern = 'default'";
		rs = getData(sql);
		try {
			while (rs.next()) {
				tInputPattern = rs.getString("inputPattern");
				tInputCondition = rs.getString("inputCondition");
				tInflectedPattern = rs.getString("inflectedPattern");
				// System.out.println("---Default Rule Handling-----");
				// System.out.println("tInputPattern =" + tInputPattern);
				// System.out.println("tInputCondition =" + tInputCondition);

				if (tInputCondition.equals(inputCondition)) {

					if (((tInflectedPattern.indexOf(",")) != -1)) {
						defaultPossessiveRulesHandling();
					} else {
						// System.out.println("found default rule");
						inflectedTransliterated = inflectedTransliterated + tInflectedPattern;
						// System.out.println("inflectedTransliterated ="
						// + inflectedTransliterated);
						// System.out.println("---------------------------");
					}
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
			releaseConnection();
			System.exit(1);
		} catch (Exception e) {
			e.printStackTrace();
			releaseConnection();
			System.exit(1);
		}
	}

	/**
	 * This method handles possessive rules
	 */
	private void defaultPossessiveRulesHandling() {
		StringTokenizer stDefSuff = new StringTokenizer(tInflectedPattern, ",");
		// System.out.println("inflectedTransliterated ="
		// + inflectedTransliterated);
		String base = inflectedTransliterated;
		inflectedTransliterated = "";
		while (stDefSuff.hasMoreTokens()) {
			String defSuffix = stDefSuff.nextToken();
			if (defSuffix.equals("-"))
				defSuffix = "";
			inflectedTransliterated = inflectedTransliterated + base + defSuffix + ",";

		}
	}

	/**
	 * This method scan the result set got from the query exceccuted at getRules<br>
	 * default rules are handled seperatly at getDefaultRule<br>
	 * The non default rules are excecuted on the inflectedItem<br>
	 * There are cases where several rules are exceuted<br>
	 * The default rule is exceuted at the end<br>
	 * The order of exceuting is determined by the max length<br>
	 * from the highest value to the lowest value<br>
	 */
	private void processRules() {
		try {
			while (rs.next()) {
				tInputPattern = rs.getString("inputPattern");
				tInputCondition = rs.getString("inputCondition");
				tInflectedPattern = rs.getString("inflectedPattern");
				// System.out.println("------------------------------");
				// System.out.println("tInputPattern =" + tInputPattern);
				// System.out.println("tInputCondition =" + tInputCondition);
				// get non default rule
				findRule();
			}
			releaseConnection();
			getDefaultRule();
		} catch (SQLException E) {
			E.printStackTrace();
			releaseConnection();
			System.exit(1);
		}

	}

	/**
	 * This method does the actually rule choosing
	 */
	private void findRule() {
		int len = 0;
		for (int i = maxLength; i > 0; i--) {
			// System.out.println("i =" + i);
			// get the length of the transliterated string without
			// the suffix

			len = inflectedTransliterated.length() - i;

			// get the suffix of the transliterated string

			subLast = inflectedTransliterated.substring(len);

			// int index = tInputPattern.indexOf('|');

			// if (index > -1) {
			// if ((subLast.equals(tInputPattern.substring(0, 1)) || subLast
			// .equals(tInputPattern.substring(2, 3)))
			// && (inputCondition.equals(tInputCondition))) {
			// System.out.println(" ---find match1-----" + i);
			// buildMatch(i);
			// return;
			// }
			// }

			// else if (index == -1) {
			if (subLast.equals(tInputPattern) && inputCondition.equals(tInputCondition)) {
				// System.out.println(" ---find match2-----" + i);
				buildMatch(i);
				maxLength = i;
				return;
			}

			// }
		}
	}

	private void buildMatch(int i) {
		// rules are from table lexiconTest.rules1 (yossi 26.4.11)
		// System.out.println("(F) buildMatch("+i+")");
		inflectedTransliterated = StrUtils.chomp(inflectedTransliterated, i) + tInflectedPattern;
		// System.out.println("(F) buildMatch found a rule !!!");
		// System.out.println("(F) buildMatch tInflectedPattern =" + tInflectedPattern);
		// System.out.println("(F) buildMatch inflectedTransliterated =" +
		// inflectedTransliterated);
		/*
		 * try { System.out.println("(F) buildMatch rule= " + rs.getString("comment")
		 * +" id= " + rs.getString("id") ); } catch (SQLException e) {
		 * e.printStackTrace(); releaseConnection(); System.exit(1); }
		 */
	}

	/**
	 * This method is called for each geneartion based on rules<br>
	 * Rules is selected by scanning the item suffix<br>
	 * The scanned suffix length is determined by the parameter max lenght<br>
	 * This parameter is hard coded per generation action per pos<br>
	 * Another parameter is action which defines the generation action<br>
	 * There are cases in which there is a third parameter - condition
	 */
	public void getRules() {
		// System.out.println("(F) getRules()");
		inflectedTransliterated = transliterated;
		int len = transliterated.length();
		if ((len - 1) < maxLength)
			maxLength = len - 1;
		// System.out.println("----------------------");
		// System.out.println("find rule parameters:");
		// System.out.println("action=" + action);
		// System.out.println("transliterated=" + transliterated);
		// System.out.println("inputCondition=" + inputCondition);
		String sql = "select * from rules1 where  action = '" + action
				+ "' and inputPattern != 'default' order by inputSuffixLen DESC";
		// System.out.println("(F) getRules() QUERY = "+sql);
		rs = getData(sql);
		processRules();
		try {
			rs.close();
		} catch (SQLException E) {
			E.printStackTrace();
			System.exit(1);
		} finally {
			releaseConnection();
		}
	}

	public static void main(String[] args) {
	}

	/**
	 * @param action
	 *           The action to set.
	 */
	public void setAction(String action) {
		this.action = action;
	}

	/**
	 * @param inputCondition
	 *           The inputCondition to set.
	 */
	public void setInputCondition(String inputCondition) {
		this.inputCondition = inputCondition;
	}

	/**
	 * @param transliterated
	 *           The transliterated to set.
	 */
	public void setTransliterated(String transliterated) {
		this.transliterated = transliterated;
	}

	/**
	 * @param maxLength
	 *           The maxLength to set.
	 */
	public void setMaxLength(int maxLength) {
		this.maxLength = maxLength;
	}

	/**
	 * @return Returns the inflectedTransliterated.
	 */
	public String getInflectedTransliterated() {
		return inflectedTransliterated;
	}

	/**
	 * @return Returns the feature.
	 */
	public String getFeature() {
		return feature;
	}

	/**
	 * @return Returns the featureVal.
	 */
	public String getFeatureVal() {
		return featureVal;
	}
}
