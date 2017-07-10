/*
 * Created on 10/10/2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package lexicon.dbUtils;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import lexicon.contents.ConnectedGenerator;

/**
 * @author daliabo
 * 
 *         TODO To change the template for this generated type comment go to
 *         Window - Preferences - Java - Code Style - Code Templates
 */
public class Prefixes extends ConnectedGenerator {
	PrefixRecord pr = null;

	public List getPrefixesList(String input) throws UnsupportedEncodingException {
		ArrayList result = new ArrayList();
		String sql = "SELECT * FROM prefixes where prefix ='" + input + "'";
		ResultSet rs = null;
		try {
			rs = getData(sql);
			if (rs != null) {

				while (rs.next()) {
					pr = new PrefixRecord();
					pr.setAdverbKAF(rs.getBoolean("adverbKAF"));
					pr.setDefArtHE(rs.getBoolean("defArtHE"));
					pr.setDefiniteArticleTag(rs.getBoolean("definiteArticleTag"));
					pr.setDescription(rs.getString("description"));
					pr.setPrefix(rs.getString("prefix"));
					pr.setPrefPartUnit(rs.getBoolean("prefPartUnit"));
					pr.setRelativizerTag(rs.getBoolean("relativizerTag"));
					pr.setRelHE(rs.getBoolean("relHE"));
					pr.setSubConOrRelSHIN(rs.getBoolean("subConOrRelSHIN"));
					pr.setSubordinatingConjunctionTag(rs.getBoolean("subordinatingConjunctionTag"));
					pr.setTemporalSubConjTag(rs.getBoolean("temporalSubConjTag"));
					pr.setTempSubConKAFSHIN(rs.getBoolean("tempSubConKAFSHIN"));
					pr.setTempSubConMEMSHIN(rs.getBoolean("tempSubConMEMSHIN"));
					pr.setTempSubConLAMEDKAFSHIN(rs.getBoolean("tempSubConLAMEDKAFSHIN"));
					pr.setPrepositionTag(rs.getBoolean("prepositionTag"));
					pr.setConjunctionTag(rs.getBoolean("conjunctionTag"));
					result.add(pr);
				}
				rs.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			releaseConnection();
		}
		return result;
	}

	private void preparePrefixesData() {

		String prefix = "";
		String description = "";
		boolean definiteArticleTag;
		// defArtHE [ [ definiteArticleTag ] ה ]
		boolean defArtHE;
		boolean relHE;
		boolean adverbKAF;
		boolean subConOrRelSHIN;
		// define tempSubConKAFSHIN [ [ temporalSubConjTag ] [ KAFSHIN ] ];
		// define tempSubConLAMEDKAFSHIN [ [ temporalSubConjTag ] [ LAMEDKAFSHIN ] ];
		// define tempSubConMEMSHIN [ [ temporalSubConjTag ] [ MEMSHIN ] ];
		boolean tempSubConKAFSHIN;
		boolean tempSubConMEMSHIN;
		boolean tempSubConLAMEDKAFSHIN;

		// define relHE [ [ relativizerTag ] [ HE ] ];
		// define relSHIN [ [ relativizerTag ] [ SHIN ] ];
		boolean relativizerTag;

		boolean temporalSubConjTag;
		boolean subordinatingConjunctionTag;
		boolean prefPartUnit;
		boolean conjunctionTag;
		boolean prepBET;
		boolean prepKAF;
		boolean prepLAMED;
		boolean prepMEM;
		boolean prepositionTag;

		try {
			prefix = "'w'";
			description = "'[+conjunction]" + URLEncoder.encode("ו", "UTF-8") + "'";
			definiteArticleTag = false;
			defArtHE = false;
			relHE = false;
			////////////////
			adverbKAF = false;
			subConOrRelSHIN = false;
			tempSubConKAFSHIN = false; //
			tempSubConMEMSHIN = false;
			tempSubConLAMEDKAFSHIN = false;
			relativizerTag = false;
			temporalSubConjTag = false;
			subordinatingConjunctionTag = false;
			prefPartUnit = true;
			conjunctionTag = true;
			prepBET = false;
			prepKAF = false;
			prepLAMED = false;
			prepMEM = false;
			prepositionTag = false;

			populatePrefixes(1, prefix, description, definiteArticleTag, defArtHE, relHE, adverbKAF, subConOrRelSHIN,
					tempSubConKAFSHIN, tempSubConMEMSHIN, tempSubConLAMEDKAFSHIN, relativizerTag, temporalSubConjTag,
					subordinatingConjunctionTag, prefPartUnit, prepBET, prepKAF, prepLAMED, prepMEM, prepositionTag,
					conjunctionTag);

			///////////////////////////////////////////////////////////////
			prefix = "'e'";
			description = "'[+relativizer/subordinatingConjunction]" + URLEncoder.encode("ש", "UTF-8") + "'";
			definiteArticleTag = false;
			defArtHE = false;
			relHE = false;
			////////////////
			adverbKAF = false;
			subConOrRelSHIN = true;
			tempSubConKAFSHIN = false;
			tempSubConMEMSHIN = false;
			tempSubConLAMEDKAFSHIN = false;
			relativizerTag = true;
			temporalSubConjTag = false;
			subordinatingConjunctionTag = false;
			prefPartUnit = true;
			conjunctionTag = false;
			prepBET = false;
			prepKAF = false;
			prepLAMED = false;
			prepMEM = false;
			prepositionTag = false;

			populatePrefixes(2, prefix, description, definiteArticleTag, defArtHE, relHE, adverbKAF, subConOrRelSHIN,
					tempSubConKAFSHIN, tempSubConMEMSHIN, tempSubConLAMEDKAFSHIN, relativizerTag, temporalSubConjTag,
					subordinatingConjunctionTag, prefPartUnit, prepBET, prepKAF, prepLAMED, prepMEM, prepositionTag,
					conjunctionTag);

			///////////////////////////////////////////////////////////////
			prefix = "'we'";
			description = "'[+conjunction]" + URLEncoder.encode("ו", "UTF-8") + "[+relativizer/subordinatingConjunction]"
					+ URLEncoder.encode("ש", "UTF-8") + "'";
			definiteArticleTag = false;
			defArtHE = false;
			relHE = false;
			adverbKAF = false;
			///////////////
			subConOrRelSHIN = true;
			tempSubConKAFSHIN = false;
			tempSubConMEMSHIN = false;
			tempSubConLAMEDKAFSHIN = false;
			relativizerTag = true;
			temporalSubConjTag = false;
			subordinatingConjunctionTag = false;
			prefPartUnit = true;
			conjunctionTag = true;
			prepBET = false;
			prepKAF = false;
			prepLAMED = false;
			prepMEM = false;
			prepositionTag = false;

			populatePrefixes(3, prefix, description, definiteArticleTag, defArtHE, relHE, adverbKAF, subConOrRelSHIN,
					tempSubConKAFSHIN, tempSubConMEMSHIN, tempSubConLAMEDKAFSHIN, relativizerTag, temporalSubConjTag,
					subordinatingConjunctionTag, prefPartUnit, prepBET, prepKAF, prepLAMED, prepMEM, prepositionTag,
					conjunctionTag);

			///////////////////////////////////////////////////////////////
			prefix = "'ke'";
			description = "'[+temporalSubConj]" + URLEncoder.encode("כש", "UTF-8") + "'";
			definiteArticleTag = false;
			defArtHE = false;
			relHE = false;
			///////////////
			adverbKAF = false;
			subConOrRelSHIN = false;
			tempSubConKAFSHIN = true;
			tempSubConMEMSHIN = false;
			tempSubConLAMEDKAFSHIN = false;
			relativizerTag = false;
			temporalSubConjTag = true;
			subordinatingConjunctionTag = false;
			prefPartUnit = true;
			conjunctionTag = false;
			prepBET = false;
			prepKAF = false;
			prepLAMED = false;
			prepMEM = false;
			prepositionTag = false;

			populatePrefixes(4, prefix, description, definiteArticleTag, defArtHE, relHE, adverbKAF, subConOrRelSHIN,
					tempSubConKAFSHIN, tempSubConMEMSHIN, tempSubConLAMEDKAFSHIN, relativizerTag, temporalSubConjTag,
					subordinatingConjunctionTag, prefPartUnit, prepBET, prepKAF, prepLAMED, prepMEM, prepositionTag,
					conjunctionTag);

			///////////////////////////////////////////////////////////////

			prefix = "'wke'";
			description = "'[+conjunction]" + URLEncoder.encode("ו", "UTF-8") + "[+temporalSubConj]"
					+ URLEncoder.encode("כש", "UTF-8") + "'";
			definiteArticleTag = false;
			defArtHE = false;
			relHE = false;
			////////////////
			adverbKAF = false;
			subConOrRelSHIN = false;
			tempSubConKAFSHIN = true;
			tempSubConMEMSHIN = false;
			tempSubConLAMEDKAFSHIN = false;
			relativizerTag = false;
			temporalSubConjTag = true;
			subordinatingConjunctionTag = false;
			prefPartUnit = true;
			conjunctionTag = true;
			prepBET = false;
			prepKAF = false;
			prepLAMED = false;
			prepMEM = false;
			prepositionTag = false;

			populatePrefixes(5, prefix, description, definiteArticleTag, defArtHE, relHE, adverbKAF, subConOrRelSHIN,
					tempSubConKAFSHIN, tempSubConMEMSHIN, tempSubConLAMEDKAFSHIN, relativizerTag, temporalSubConjTag,
					subordinatingConjunctionTag, prefPartUnit, prepBET, prepKAF, prepLAMED, prepMEM, prepositionTag,
					conjunctionTag);

			///////////////////////////////////////////////////////////////
			prefix = "'weke'";
			description = "'[+conjunction]" + URLEncoder.encode("ו", "UTF-8") + "[+relativizer/subordinatingConjunction]"
					+ URLEncoder.encode("ש", "UTF-8") + "[+temporalSubConj]" + URLEncoder.encode("כש", "UTF-8") + "'";
			definiteArticleTag = false;
			defArtHE = false;
			relHE = false;
			///////////////
			adverbKAF = false;
			subConOrRelSHIN = true;
			tempSubConKAFSHIN = true;
			tempSubConMEMSHIN = false;
			tempSubConLAMEDKAFSHIN = false;
			relativizerTag = true;
			temporalSubConjTag = true;
			subordinatingConjunctionTag = false;
			prefPartUnit = true;
			conjunctionTag = true;
			prepBET = false;
			prepKAF = false;
			prepLAMED = false;
			prepMEM = false;
			prepositionTag = false;

			populatePrefixes(6, prefix, description, definiteArticleTag, defArtHE, relHE, adverbKAF, subConOrRelSHIN,
					tempSubConKAFSHIN, tempSubConMEMSHIN, tempSubConLAMEDKAFSHIN, relativizerTag, temporalSubConjTag,
					subordinatingConjunctionTag, prefPartUnit, prepBET, prepKAF, prepLAMED, prepMEM, prepositionTag,
					conjunctionTag);

			///////////////////////////////////////////////////////////////
			prefix = "'eke'";
			description = "'[+relativizer/subordinatingConjunction]" + URLEncoder.encode("ש", "UTF-8")
					+ "[+temporalSubConj]" + URLEncoder.encode("כש", "UTF-8") + "'";
			definiteArticleTag = false;
			defArtHE = false;
			relHE = false;
			///////////////
			adverbKAF = false;
			subConOrRelSHIN = true;
			tempSubConKAFSHIN = true;
			tempSubConMEMSHIN = false;
			tempSubConLAMEDKAFSHIN = false;
			relativizerTag = true;
			temporalSubConjTag = true;
			subordinatingConjunctionTag = false;
			prefPartUnit = true;
			conjunctionTag = false;
			prepBET = false;
			prepKAF = false;
			prepLAMED = false;
			prepMEM = false;
			prepositionTag = false;

			populatePrefixes(7, prefix, description, definiteArticleTag, defArtHE, relHE, adverbKAF, subConOrRelSHIN,
					tempSubConKAFSHIN, tempSubConMEMSHIN, tempSubConLAMEDKAFSHIN, relativizerTag, temporalSubConjTag,
					subordinatingConjunctionTag, prefPartUnit, prepBET, prepKAF, prepLAMED, prepMEM, prepositionTag,
					conjunctionTag);

			///////////////////////////////////////////////////////////////
			prefix = "'me'";
			description = "'[+temporalSubConj]" + URLEncoder.encode("מש", "UTF-8") + "'";
			definiteArticleTag = false;
			defArtHE = false;
			relHE = false;
			/////////////////
			adverbKAF = false;
			subConOrRelSHIN = false;
			tempSubConKAFSHIN = false;
			tempSubConMEMSHIN = true;
			tempSubConLAMEDKAFSHIN = false;
			relativizerTag = false;
			temporalSubConjTag = true;
			subordinatingConjunctionTag = false;
			prefPartUnit = true;
			conjunctionTag = false;
			prepBET = false;
			prepKAF = false;
			prepLAMED = false;
			prepMEM = false;
			prepositionTag = false;

			populatePrefixes(8, prefix, description, definiteArticleTag, defArtHE, relHE, adverbKAF, subConOrRelSHIN,
					tempSubConKAFSHIN, tempSubConMEMSHIN, tempSubConLAMEDKAFSHIN, relativizerTag, temporalSubConjTag,
					subordinatingConjunctionTag, prefPartUnit, prepBET, prepKAF, prepLAMED, prepMEM, prepositionTag,
					conjunctionTag);

			///////////////////////////////////////////////////////////////
			prefix = "'wme'";
			description = "'[+conjunction]" + URLEncoder.encode("ו", "UTF-8") + "[+temporalSubConj]"
					+ URLEncoder.encode("מש", "UTF-8") + "'";
			definiteArticleTag = false;
			defArtHE = false;
			relHE = false;
			/////////////////
			adverbKAF = false;
			subConOrRelSHIN = false;
			tempSubConKAFSHIN = false;
			tempSubConMEMSHIN = true;
			tempSubConLAMEDKAFSHIN = false;
			relativizerTag = true;
			temporalSubConjTag = true;
			subordinatingConjunctionTag = false;
			prefPartUnit = true;
			conjunctionTag = true;
			prepBET = false;
			prepKAF = false;
			prepLAMED = false;
			prepMEM = false;
			prepositionTag = false;

			populatePrefixes(9, prefix, description, definiteArticleTag, defArtHE, relHE, adverbKAF, subConOrRelSHIN,
					tempSubConKAFSHIN, tempSubConMEMSHIN, tempSubConLAMEDKAFSHIN, relativizerTag, temporalSubConjTag,
					subordinatingConjunctionTag, prefPartUnit, prepBET, prepKAF, prepLAMED, prepMEM, prepositionTag,
					conjunctionTag);

			///////////////////////////////////////////////////////////////
			prefix = "'weme'";
			description = "'[+conjunction]" + URLEncoder.encode("ו", "UTF-8") + "[+relativizer/subordinatingConjunction]"
					+ URLEncoder.encode("ש", "UTF-8") + "[+temporalSubConj]" + URLEncoder.encode("מש", "UTF-8") + "'";
			definiteArticleTag = false;
			defArtHE = false;
			relHE = false;
			////////////////
			adverbKAF = false;
			subConOrRelSHIN = true;
			tempSubConKAFSHIN = false;
			tempSubConMEMSHIN = true;
			tempSubConLAMEDKAFSHIN = false;
			relativizerTag = true;
			temporalSubConjTag = true;
			subordinatingConjunctionTag = false;
			prefPartUnit = true;
			conjunctionTag = true;
			prepBET = false;
			prepKAF = false;
			prepLAMED = false;
			prepMEM = false;
			prepositionTag = false;

			populatePrefixes(10, prefix, description, definiteArticleTag, defArtHE, relHE, adverbKAF, subConOrRelSHIN,
					tempSubConKAFSHIN, tempSubConMEMSHIN, tempSubConLAMEDKAFSHIN, relativizerTag, temporalSubConjTag,
					subordinatingConjunctionTag, prefPartUnit, prepBET, prepKAF, prepLAMED, prepMEM, prepositionTag,
					conjunctionTag);

			///////////////////////////////////////////////////////////////
			prefix = "'eme'";
			description = "'[+relativizer/subordinatingConjunction]" + URLEncoder.encode("ש", "UTF-8")
					+ "[+temporalSubConj]" + URLEncoder.encode("מש", "UTF-8") + "'";
			definiteArticleTag = false;
			defArtHE = false;
			relHE = false;
			////////////////
			adverbKAF = false;
			subConOrRelSHIN = true;
			tempSubConKAFSHIN = false;
			tempSubConMEMSHIN = true;
			tempSubConLAMEDKAFSHIN = false;
			relativizerTag = true;
			temporalSubConjTag = true;
			subordinatingConjunctionTag = false;
			prefPartUnit = true;
			conjunctionTag = false;
			prepBET = false;
			prepKAF = false;
			prepLAMED = false;
			prepMEM = false;
			prepositionTag = false;

			populatePrefixes(11, prefix, description, definiteArticleTag, defArtHE, relHE, adverbKAF, subConOrRelSHIN,
					tempSubConKAFSHIN, tempSubConMEMSHIN, tempSubConLAMEDKAFSHIN, relativizerTag, temporalSubConjTag,
					subordinatingConjunctionTag, prefPartUnit, prepBET, prepKAF, prepLAMED, prepMEM, prepositionTag,
					conjunctionTag);
			///////////////////////////////////////////////////////////////
			prefix = "'lke'";
			description = "'[+temporalSubConj]" + URLEncoder.encode("לכש", "UTF-8") + "'";
			definiteArticleTag = false;
			defArtHE = false;
			relHE = false;
			///////////////
			adverbKAF = false;
			subConOrRelSHIN = false;
			tempSubConKAFSHIN = false;
			tempSubConMEMSHIN = false;
			tempSubConLAMEDKAFSHIN = true;
			relativizerTag = false;
			temporalSubConjTag = true;
			subordinatingConjunctionTag = false;
			prefPartUnit = true;
			conjunctionTag = false;
			prepBET = false;
			prepKAF = false;
			prepLAMED = false;
			prepMEM = false;
			prepositionTag = false;

			populatePrefixes(12, prefix, description, definiteArticleTag, defArtHE, relHE, adverbKAF, subConOrRelSHIN,
					tempSubConKAFSHIN, tempSubConMEMSHIN, tempSubConLAMEDKAFSHIN, relativizerTag, temporalSubConjTag,
					subordinatingConjunctionTag, prefPartUnit, prepBET, prepKAF, prepLAMED, prepMEM, prepositionTag,
					conjunctionTag);

			///////////////////////////////////////////////////////////////
			prefix = "'wlke'";
			description = "'[+conjunction]" + URLEncoder.encode("ו", "UTF-8") + "[+temporalSubConj]"
					+ URLEncoder.encode("לכש", "UTF-8") + "'";
			definiteArticleTag = false;
			defArtHE = false;
			relHE = false;
			///////////////
			adverbKAF = false;
			subConOrRelSHIN = false;
			tempSubConKAFSHIN = false;
			tempSubConMEMSHIN = false;
			tempSubConLAMEDKAFSHIN = true;
			relativizerTag = false;
			temporalSubConjTag = true;
			subordinatingConjunctionTag = false;
			prefPartUnit = true;
			conjunctionTag = true;
			prepBET = false;
			prepKAF = false;
			prepLAMED = false;
			prepMEM = false;
			prepositionTag = false;

			populatePrefixes(13, prefix, description, definiteArticleTag, defArtHE, relHE, adverbKAF, subConOrRelSHIN,
					tempSubConKAFSHIN, tempSubConMEMSHIN, tempSubConLAMEDKAFSHIN, relativizerTag, temporalSubConjTag,
					subordinatingConjunctionTag, prefPartUnit, prepBET, prepKAF, prepLAMED, prepMEM, prepositionTag,
					conjunctionTag);

			///////////////////////////////////////////////////////////////
			prefix = "'welke'";
			description = "'[+conjunction]" + URLEncoder.encode("ו", "UTF-8") + "[+relativizer/subordinatingConjunction]"
					+ URLEncoder.encode("ש", "UTF-8") + "[+temporalSubConj]" + URLEncoder.encode("לכש", "UTF-8") + "'";
			definiteArticleTag = false;
			defArtHE = false;
			relHE = false;
			//////////////
			adverbKAF = false;
			subConOrRelSHIN = true;
			tempSubConKAFSHIN = false;
			tempSubConMEMSHIN = false;
			tempSubConLAMEDKAFSHIN = true;
			relativizerTag = true;
			temporalSubConjTag = true;
			subordinatingConjunctionTag = false;
			prefPartUnit = true;
			conjunctionTag = true;
			prepBET = false;
			prepKAF = false;
			prepLAMED = false;
			prepMEM = false;
			prepositionTag = false;

			populatePrefixes(14, prefix, description, definiteArticleTag, defArtHE, relHE, adverbKAF, subConOrRelSHIN,
					tempSubConKAFSHIN, tempSubConMEMSHIN, tempSubConLAMEDKAFSHIN, relativizerTag, temporalSubConjTag,
					subordinatingConjunctionTag, prefPartUnit, prepBET, prepKAF, prepLAMED, prepMEM, prepositionTag,
					conjunctionTag);

			///////////////////////////////////////////////////////////////
			prefix = "'welke'";
			description = "'[+conjunction]" + URLEncoder.encode("ו", "UTF-8") + "[+relativizer/subordinatingConjunction]"
					+ URLEncoder.encode("ש", "UTF-8") + "[+temporalSubConj]" + URLEncoder.encode("לכש", "UTF-8") + "'";
			definiteArticleTag = false;
			defArtHE = false;
			relHE = false;
			//////////////////
			adverbKAF = false;
			subConOrRelSHIN = true;
			tempSubConKAFSHIN = false;
			tempSubConMEMSHIN = false;
			tempSubConLAMEDKAFSHIN = true;
			relativizerTag = true;
			temporalSubConjTag = true;
			subordinatingConjunctionTag = false;
			prefPartUnit = true;
			conjunctionTag = true;
			prepBET = false;
			prepKAF = false;
			prepLAMED = false;
			prepMEM = false;
			prepositionTag = false;

			populatePrefixes(15, prefix, description, definiteArticleTag, defArtHE, relHE, adverbKAF, subConOrRelSHIN,
					tempSubConKAFSHIN, tempSubConMEMSHIN, tempSubConLAMEDKAFSHIN, relativizerTag, temporalSubConjTag,
					subordinatingConjunctionTag, prefPartUnit, prepBET, prepKAF, prepLAMED, prepMEM, prepositionTag,
					conjunctionTag);

			///////////////////////////////////////////////////////////////
			prefix = "'elke'";
			description = "'[+relativizer/subordinatingConjunction]" + URLEncoder.encode("ש", "UTF-8")
					+ "[+temporalSubConj]" + URLEncoder.encode("לכש", "UTF-8") + "'";
			definiteArticleTag = false;
			defArtHE = false;
			relHE = false;
			////////////////////
			adverbKAF = false;
			subConOrRelSHIN = false;
			tempSubConKAFSHIN = false;
			tempSubConMEMSHIN = false;
			tempSubConLAMEDKAFSHIN = true;
			relativizerTag = true;
			temporalSubConjTag = true;
			subordinatingConjunctionTag = false;
			prefPartUnit = true;
			conjunctionTag = false;
			prepBET = false;
			prepKAF = false;
			prepLAMED = false;
			prepMEM = false;
			prepositionTag = false;

			populatePrefixes(16, prefix, description, definiteArticleTag, defArtHE, relHE, adverbKAF, subConOrRelSHIN,
					tempSubConKAFSHIN, tempSubConMEMSHIN, tempSubConLAMEDKAFSHIN, relativizerTag, temporalSubConjTag,
					subordinatingConjunctionTag, prefPartUnit, prepBET, prepKAF, prepLAMED, prepMEM, prepositionTag,
					conjunctionTag);

			///////////////////////////////////////////////////////////////
			prefix = "'b'";
			description = "'[+preposition]" + URLEncoder.encode("ב", "UTF-8") + "'";
			definiteArticleTag = false;
			defArtHE = false;
			relHE = false;
			//////////////////
			adverbKAF = false;
			subConOrRelSHIN = false;
			tempSubConKAFSHIN = false;
			tempSubConMEMSHIN = false;
			tempSubConLAMEDKAFSHIN = false;
			relativizerTag = false;
			temporalSubConjTag = false;
			subordinatingConjunctionTag = false;
			prefPartUnit = true;
			conjunctionTag = false;
			prepBET = true;
			prepKAF = false;
			prepLAMED = false;
			prepMEM = false;
			prepositionTag = true;

			populatePrefixes(17, prefix, description, definiteArticleTag, defArtHE, relHE, adverbKAF, subConOrRelSHIN,
					tempSubConKAFSHIN, tempSubConMEMSHIN, tempSubConLAMEDKAFSHIN, relativizerTag, temporalSubConjTag,
					subordinatingConjunctionTag, prefPartUnit, prepBET, prepKAF, prepLAMED, prepMEM, prepositionTag,
					conjunctionTag);
			///////////////////////////////////////////////////////////////
			prefix = "'b'";
			description = "'[+preposition]" + URLEncoder.encode("ב", "UTF-8") + "[+definiteArticle]" + "'";
			definiteArticleTag = true;
			defArtHE = false;
			relHE = false;
			////////////////
			adverbKAF = false;
			subConOrRelSHIN = false;
			tempSubConKAFSHIN = false;
			tempSubConMEMSHIN = false;
			tempSubConLAMEDKAFSHIN = false;
			relativizerTag = false;
			temporalSubConjTag = false;
			subordinatingConjunctionTag = false;
			prefPartUnit = true;
			conjunctionTag = false;
			prepBET = true;
			prepKAF = false;
			prepLAMED = false;
			prepMEM = false;
			prepositionTag = true;

			populatePrefixes(18, prefix, description, definiteArticleTag, defArtHE, relHE, adverbKAF, subConOrRelSHIN,
					tempSubConKAFSHIN, tempSubConMEMSHIN, tempSubConLAMEDKAFSHIN, relativizerTag, temporalSubConjTag,
					subordinatingConjunctionTag, prefPartUnit, prepBET, prepKAF, prepLAMED, prepMEM, prepositionTag,
					conjunctionTag);

			///////////////////////////////////////////////////////////////
			prefix = "'wb'";
			description = "'[+conjunction]" + URLEncoder.encode("ו", "UTF-8") + "[+preposition]"
					+ URLEncoder.encode("ב", "UTF-8") + "'";
			definiteArticleTag = false;
			defArtHE = false;
			relHE = false;
			////////////////////
			adverbKAF = false;
			subConOrRelSHIN = false;
			tempSubConKAFSHIN = false;
			tempSubConMEMSHIN = false;
			tempSubConLAMEDKAFSHIN = false;
			relativizerTag = false;
			temporalSubConjTag = false;
			subordinatingConjunctionTag = false;
			prefPartUnit = true;
			conjunctionTag = true;
			prepBET = true;
			prepKAF = false;
			prepLAMED = false;
			prepMEM = false;
			prepositionTag = true;

			populatePrefixes(19, prefix, description, definiteArticleTag, defArtHE, relHE, adverbKAF, subConOrRelSHIN,
					tempSubConKAFSHIN, tempSubConMEMSHIN, tempSubConLAMEDKAFSHIN, relativizerTag, temporalSubConjTag,
					subordinatingConjunctionTag, prefPartUnit, prepBET, prepKAF, prepLAMED, prepMEM, prepositionTag,
					conjunctionTag);

			///////////////////////////////////////////////////////////////
			prefix = "'wb'";
			description = "'[+conjunction]" + URLEncoder.encode("ו", "UTF-8") + "[+preposition]"
					+ URLEncoder.encode("ב", "UTF-8") + "[+definiteArticle]" + "'";
			definiteArticleTag = true;
			defArtHE = false;
			relHE = false;
			/////////////////
			adverbKAF = false;
			subConOrRelSHIN = false;
			tempSubConKAFSHIN = false;
			tempSubConMEMSHIN = false;
			tempSubConLAMEDKAFSHIN = false;
			relativizerTag = false;
			temporalSubConjTag = false;
			subordinatingConjunctionTag = false;
			prefPartUnit = true;
			conjunctionTag = true;
			prepBET = true;
			prepKAF = false;
			prepLAMED = false;
			prepMEM = false;
			prepositionTag = true;

			populatePrefixes(20, prefix, description, definiteArticleTag, defArtHE, relHE, adverbKAF, subConOrRelSHIN,
					tempSubConKAFSHIN, tempSubConMEMSHIN, tempSubConLAMEDKAFSHIN, relativizerTag, temporalSubConjTag,
					subordinatingConjunctionTag, prefPartUnit, prepBET, prepKAF, prepLAMED, prepMEM, prepositionTag,
					conjunctionTag);

			//////////////////////////////////////////////////////////////
			prefix = "'eb'";
			description = "'[+relativizer/subordinatingConjunction]" + URLEncoder.encode("ש", "UTF-8") + "[+preposition]"
					+ URLEncoder.encode("ב", "UTF-8") + "'";
			definiteArticleTag = false;
			defArtHE = false;
			relHE = false;
			adverbKAF = false;
			//////////////////////
			subConOrRelSHIN = true;
			tempSubConKAFSHIN = false;
			tempSubConMEMSHIN = false;
			tempSubConLAMEDKAFSHIN = false;
			relativizerTag = true;
			temporalSubConjTag = false;
			subordinatingConjunctionTag = false;
			prefPartUnit = true;
			conjunctionTag = false;
			prepBET = true;
			prepKAF = false;
			prepLAMED = false;
			prepMEM = false;
			prepositionTag = true;

			populatePrefixes(21, prefix, description, definiteArticleTag, defArtHE, relHE, adverbKAF, subConOrRelSHIN,
					tempSubConKAFSHIN, tempSubConMEMSHIN, tempSubConLAMEDKAFSHIN, relativizerTag, temporalSubConjTag,
					subordinatingConjunctionTag, prefPartUnit, prepBET, prepKAF, prepLAMED, prepMEM, prepositionTag,
					conjunctionTag);

			///////////////////////////////////////////////////////////////
			prefix = "'eb'";
			description = "'[+relativizer/subordinatingConjunction]" + URLEncoder.encode("ש", "UTF-8") + "[+preposition]"
					+ URLEncoder.encode("ב", "UTF-8") + "[+definiteArticle]" + "'";
			definiteArticleTag = true;
			defArtHE = false;
			relHE = false;
			///////////////////
			adverbKAF = false;
			subConOrRelSHIN = true;
			tempSubConKAFSHIN = false;
			tempSubConMEMSHIN = false;
			tempSubConLAMEDKAFSHIN = false;
			relativizerTag = true;
			temporalSubConjTag = false;
			subordinatingConjunctionTag = false;
			prefPartUnit = true;
			conjunctionTag = false;
			prepBET = true;
			prepKAF = false;
			prepLAMED = false;
			prepMEM = false;
			prepositionTag = true;

			populatePrefixes(22, prefix, description, definiteArticleTag, defArtHE, relHE, adverbKAF, subConOrRelSHIN,
					tempSubConKAFSHIN, tempSubConMEMSHIN, tempSubConLAMEDKAFSHIN, relativizerTag, temporalSubConjTag,
					subordinatingConjunctionTag, prefPartUnit, prepBET, prepKAF, prepLAMED, prepMEM, prepositionTag,
					conjunctionTag);

			//////////////////////////////////////////////////////////////
			prefix = "'web'";
			description = "'[+conjunction]" + URLEncoder.encode("ו", "UTF-8") + "[+relativizer/subordinatingConjunction]"
					+ URLEncoder.encode("ש", "UTF-8") + "[+preposition]" + URLEncoder.encode("ב", "UTF-8") + "'";
			definiteArticleTag = false;
			defArtHE = false;
			relHE = false;
			/////////////////////
			adverbKAF = false;
			subConOrRelSHIN = true;
			tempSubConKAFSHIN = false;
			tempSubConMEMSHIN = false;
			tempSubConLAMEDKAFSHIN = false;
			relativizerTag = true;
			temporalSubConjTag = false;
			subordinatingConjunctionTag = false;
			prefPartUnit = true;
			conjunctionTag = true;
			prepBET = true;
			prepKAF = false;
			prepLAMED = false;
			prepMEM = false;
			prepositionTag = true;

			populatePrefixes(23, prefix, description, definiteArticleTag, defArtHE, relHE, adverbKAF, subConOrRelSHIN,
					tempSubConKAFSHIN, tempSubConMEMSHIN, tempSubConLAMEDKAFSHIN, relativizerTag, temporalSubConjTag,
					subordinatingConjunctionTag, prefPartUnit, prepBET, prepKAF, prepLAMED, prepMEM, prepositionTag,
					conjunctionTag);

			///////////////////////////////////////////////////////////////
			prefix = "'web'";
			description = "'[+conjunction]" + URLEncoder.encode("ו", "UTF-8") + "[+relativizer/subordinatingConjunction]"
					+ URLEncoder.encode("ש", "UTF-8") + "[+preposition]" + URLEncoder.encode("ב", "UTF-8")
					+ "[+definiteArticle]" + "'";
			definiteArticleTag = true;
			defArtHE = false;
			relHE = false;
			///////////////////////
			adverbKAF = false;
			subConOrRelSHIN = true;
			tempSubConKAFSHIN = false;
			tempSubConMEMSHIN = false;
			tempSubConLAMEDKAFSHIN = false;
			relativizerTag = true;
			temporalSubConjTag = true;
			subordinatingConjunctionTag = false;
			prefPartUnit = true;
			conjunctionTag = true;
			prepBET = true;
			prepKAF = false;
			prepLAMED = false;
			prepMEM = false;
			prepositionTag = true;

			populatePrefixes(24, prefix, description, definiteArticleTag, defArtHE, relHE, adverbKAF, subConOrRelSHIN,
					tempSubConKAFSHIN, tempSubConMEMSHIN, tempSubConLAMEDKAFSHIN, relativizerTag, temporalSubConjTag,
					subordinatingConjunctionTag, prefPartUnit, prepBET, prepKAF, prepLAMED, prepMEM, prepositionTag,
					conjunctionTag);

			//////////////////////////////////////////////////////////////
			prefix = "'keb'";
			description = "'[+temporalSubConj]" + URLEncoder.encode("כש", "UTF-8") + "[+preposition]"
					+ URLEncoder.encode("ב", "UTF-8") + "'";
			definiteArticleTag = false;
			defArtHE = false;
			relHE = false;
			/////////////////
			adverbKAF = false;
			subConOrRelSHIN = false;
			tempSubConKAFSHIN = true;
			tempSubConMEMSHIN = false;
			tempSubConLAMEDKAFSHIN = false;
			relativizerTag = false;
			temporalSubConjTag = true;
			subordinatingConjunctionTag = false;
			prefPartUnit = true;
			conjunctionTag = false;
			prepBET = true;
			prepKAF = false;
			prepLAMED = false;
			prepMEM = false;
			prepositionTag = true;

			populatePrefixes(25, prefix, description, definiteArticleTag, defArtHE, relHE, adverbKAF, subConOrRelSHIN,
					tempSubConKAFSHIN, tempSubConMEMSHIN, tempSubConLAMEDKAFSHIN, relativizerTag, temporalSubConjTag,
					subordinatingConjunctionTag, prefPartUnit, prepBET, prepKAF, prepLAMED, prepMEM, prepositionTag,
					conjunctionTag);

			///////////////////////////////////////////////////////////////
			prefix = "'keb'";
			description = "'[+temporalSubConj]" + URLEncoder.encode("כש", "UTF-8") + "[+preposition]"
					+ URLEncoder.encode("ב", "UTF-8") + "[+definiteArticle]" + "'";
			definiteArticleTag = true;
			defArtHE = false;
			relHE = false;
			////////////////////////
			adverbKAF = false;
			subConOrRelSHIN = false;
			tempSubConKAFSHIN = true;
			tempSubConMEMSHIN = false;
			tempSubConLAMEDKAFSHIN = false;
			relativizerTag = false;
			temporalSubConjTag = true;
			subordinatingConjunctionTag = false;
			prefPartUnit = true;
			conjunctionTag = false;
			prepBET = true;
			prepKAF = false;
			prepLAMED = false;
			prepMEM = false;
			prepositionTag = true;

			populatePrefixes(26, prefix, description, definiteArticleTag, defArtHE, relHE, adverbKAF, subConOrRelSHIN,
					tempSubConKAFSHIN, tempSubConMEMSHIN, tempSubConLAMEDKAFSHIN, relativizerTag, temporalSubConjTag,
					subordinatingConjunctionTag, prefPartUnit, prepBET, prepKAF, prepLAMED, prepMEM, prepositionTag,
					conjunctionTag);

			//////////////////////////////////////////////////////////////
			prefix = "'wkeb'";
			description = "'[+conjunction]" + URLEncoder.encode("ו", "UTF-8") + "[+temporalSubConj]"
					+ URLEncoder.encode("כש", "UTF-8") + "[+preposition]" + URLEncoder.encode("ב", "UTF-8") + "'";
			definiteArticleTag = false;
			defArtHE = false;
			relHE = false;
			//////////////////
			adverbKAF = false;
			subConOrRelSHIN = false;
			tempSubConKAFSHIN = true;
			tempSubConMEMSHIN = false;
			tempSubConLAMEDKAFSHIN = false;
			relativizerTag = false;
			temporalSubConjTag = true;
			subordinatingConjunctionTag = false;
			prefPartUnit = true;
			conjunctionTag = true;
			prepBET = true;
			prepKAF = false;
			prepLAMED = false;
			prepMEM = false;
			prepositionTag = true;

			populatePrefixes(27, prefix, description, definiteArticleTag, defArtHE, relHE, adverbKAF, subConOrRelSHIN,
					tempSubConKAFSHIN, tempSubConMEMSHIN, tempSubConLAMEDKAFSHIN, relativizerTag, temporalSubConjTag,
					subordinatingConjunctionTag, prefPartUnit, prepBET, prepKAF, prepLAMED, prepMEM, prepositionTag,
					conjunctionTag);

			///////////////////////////////////////////////////////////////
			prefix = "'wkeb'";
			description = "'[+conjunction]" + URLEncoder.encode("ו", "UTF-8") + "[+temporalSubConj]"
					+ URLEncoder.encode("כש", "UTF-8") + "[+preposition]" + URLEncoder.encode("ב", "UTF-8")
					+ "[+definiteArticle]" + "'";
			definiteArticleTag = true;
			defArtHE = false;
			relHE = false;
			/////////////////
			adverbKAF = false;
			subConOrRelSHIN = false;
			tempSubConKAFSHIN = true;
			tempSubConMEMSHIN = false;
			tempSubConLAMEDKAFSHIN = false;
			relativizerTag = false;
			temporalSubConjTag = true;
			subordinatingConjunctionTag = false;
			prefPartUnit = true;
			conjunctionTag = true;
			prepBET = true;
			prepKAF = false;
			prepLAMED = false;
			prepMEM = false;
			prepositionTag = true;

			populatePrefixes(28, prefix, description, definiteArticleTag, defArtHE, relHE, adverbKAF, subConOrRelSHIN,
					tempSubConKAFSHIN, tempSubConMEMSHIN, tempSubConLAMEDKAFSHIN, relativizerTag, temporalSubConjTag,
					subordinatingConjunctionTag, prefPartUnit, prepBET, prepKAF, prepLAMED, prepMEM, prepositionTag,
					conjunctionTag);

			//////////////////////////////////////////////////////////////
			prefix = "'ekeb'";
			description = "'[+relativizer/subordinatingConjunction]" + URLEncoder.encode("ש", "UTF-8")
					+ "[+temporalSubConj]" + URLEncoder.encode("כש", "UTF-8") + "[+preposition]"
					+ URLEncoder.encode("ב", "UTF-8") + "'";
			definiteArticleTag = false;
			defArtHE = false;
			relHE = false;
			/////////////////////
			adverbKAF = false;
			subConOrRelSHIN = true;
			tempSubConKAFSHIN = true;
			tempSubConMEMSHIN = false;
			tempSubConLAMEDKAFSHIN = false;
			relativizerTag = true;
			temporalSubConjTag = true;
			subordinatingConjunctionTag = false;
			prefPartUnit = true;
			conjunctionTag = false;
			prepBET = true;
			prepKAF = false;
			prepLAMED = false;
			prepMEM = false;
			prepositionTag = true;

			populatePrefixes(29, prefix, description, definiteArticleTag, defArtHE, relHE, adverbKAF, subConOrRelSHIN,
					tempSubConKAFSHIN, tempSubConMEMSHIN, tempSubConLAMEDKAFSHIN, relativizerTag, temporalSubConjTag,
					subordinatingConjunctionTag, prefPartUnit, prepBET, prepKAF, prepLAMED, prepMEM, prepositionTag,
					conjunctionTag);

			///////////////////////////////////////////////////////////////
			prefix = "'ekeb'";
			description = "'[+relativizer/subordinatingConjunction]" + URLEncoder.encode("ש", "UTF-8")
					+ "[+temporalSubConj]" + URLEncoder.encode("כש", "UTF-8") + "[+preposition]"
					+ URLEncoder.encode("ב", "UTF-8") + "[+definiteArticle]" + "'";
			definiteArticleTag = true;
			defArtHE = false;
			relHE = false;
			//////////////////
			adverbKAF = false;
			subConOrRelSHIN = true;
			tempSubConKAFSHIN = true;
			tempSubConMEMSHIN = false;
			tempSubConLAMEDKAFSHIN = false;
			relativizerTag = true;
			temporalSubConjTag = true;
			subordinatingConjunctionTag = false;
			prefPartUnit = true;
			conjunctionTag = false;
			prepBET = true;
			prepKAF = false;
			prepLAMED = false;
			prepMEM = false;
			prepositionTag = true;

			populatePrefixes(30, prefix, description, definiteArticleTag, defArtHE, relHE, adverbKAF, subConOrRelSHIN,
					tempSubConKAFSHIN, tempSubConMEMSHIN, tempSubConLAMEDKAFSHIN, relativizerTag, temporalSubConjTag,
					subordinatingConjunctionTag, prefPartUnit, prepBET, prepKAF, prepLAMED, prepMEM, prepositionTag,
					conjunctionTag);

			//////////////////////////////////////////////////////////////
			prefix = "'wekeb'";
			description = "'[+conjunction]" + URLEncoder.encode("ו", "UTF-8") + "[+relativizer/subordinatingConjunction]"
					+ URLEncoder.encode("ש", "UTF-8") + "[+temporalSubConj]" + URLEncoder.encode("כש", "UTF-8")
					+ "[+preposition]" + URLEncoder.encode("ב", "UTF-8") + "'";
			definiteArticleTag = false;
			defArtHE = false;
			relHE = false;
			////////////////////
			adverbKAF = false;
			subConOrRelSHIN = true;
			tempSubConKAFSHIN = true;
			tempSubConMEMSHIN = false;
			tempSubConLAMEDKAFSHIN = false;
			relativizerTag = true;
			temporalSubConjTag = true;
			subordinatingConjunctionTag = false;
			prefPartUnit = true;
			conjunctionTag = true;
			prepBET = true;
			prepKAF = false;
			prepLAMED = false;
			prepMEM = false;
			prepositionTag = true;

			populatePrefixes(31, prefix, description, definiteArticleTag, defArtHE, relHE, adverbKAF, subConOrRelSHIN,
					tempSubConKAFSHIN, tempSubConMEMSHIN, tempSubConLAMEDKAFSHIN, relativizerTag, temporalSubConjTag,
					subordinatingConjunctionTag, prefPartUnit, prepBET, prepKAF, prepLAMED, prepMEM, prepositionTag,
					conjunctionTag);

			//////////////////////////////////////////////////////////////
			prefix = "'meb'";
			description = "'[+temporalSubConj]" + URLEncoder.encode("מש", "UTF-8") + "[+preposition]"
					+ URLEncoder.encode("ב", "UTF-8") + "'";
			definiteArticleTag = false;
			defArtHE = false;
			relHE = false;
			//////////////////
			adverbKAF = false;
			subConOrRelSHIN = false;
			tempSubConKAFSHIN = false;
			tempSubConMEMSHIN = true;
			tempSubConLAMEDKAFSHIN = false;
			relativizerTag = false;
			temporalSubConjTag = true;
			subordinatingConjunctionTag = false;
			prefPartUnit = true;
			conjunctionTag = false;
			prepBET = true;
			prepKAF = false;
			prepLAMED = false;
			prepMEM = false;
			prepositionTag = true;

			populatePrefixes(33, prefix, description, definiteArticleTag, defArtHE, relHE, adverbKAF, subConOrRelSHIN,
					tempSubConKAFSHIN, tempSubConMEMSHIN, tempSubConLAMEDKAFSHIN, relativizerTag, temporalSubConjTag,
					subordinatingConjunctionTag, prefPartUnit, prepBET, prepKAF, prepLAMED, prepMEM, prepositionTag,
					conjunctionTag);

			///////////////////////////////////////////////////////////////
			prefix = "'meb'";
			description = "'[+temporalSubConj]" + URLEncoder.encode("מש", "UTF-8") + "[+preposition]"
					+ URLEncoder.encode("ב", "UTF-8") + "[+definiteArticle]" + "'";
			definiteArticleTag = true;
			defArtHE = false;
			relHE = false;
			/////////////////////
			adverbKAF = false;
			subConOrRelSHIN = false;
			tempSubConKAFSHIN = false;
			tempSubConMEMSHIN = true;
			tempSubConLAMEDKAFSHIN = false;
			relativizerTag = false;
			temporalSubConjTag = true;
			subordinatingConjunctionTag = false;
			prefPartUnit = true;
			conjunctionTag = false;
			prepBET = true;
			prepKAF = false;
			prepLAMED = false;
			prepMEM = false;
			prepositionTag = true;

			populatePrefixes(34, prefix, description, definiteArticleTag, defArtHE, relHE, adverbKAF, subConOrRelSHIN,
					tempSubConKAFSHIN, tempSubConMEMSHIN, tempSubConLAMEDKAFSHIN, relativizerTag, temporalSubConjTag,
					subordinatingConjunctionTag, prefPartUnit, prepBET, prepKAF, prepLAMED, prepMEM, prepositionTag,
					conjunctionTag);

			//////////////////////////////////////////////////////////////
			prefix = "'wmeb'";
			description = "'[+conjunction]" + URLEncoder.encode("ו", "UTF-8") + "[+temporalSubConj]"
					+ URLEncoder.encode("מש", "UTF-8") + "[+preposition]" + URLEncoder.encode("ב", "UTF-8") + "'";
			definiteArticleTag = false;
			defArtHE = false;
			relHE = false;
			/////////////////////
			adverbKAF = false;
			subConOrRelSHIN = false;
			tempSubConKAFSHIN = false;
			tempSubConMEMSHIN = true;
			tempSubConLAMEDKAFSHIN = false;
			relativizerTag = false;
			temporalSubConjTag = true;
			subordinatingConjunctionTag = false;
			prefPartUnit = true;
			conjunctionTag = true;
			prepBET = true;
			prepKAF = false;
			prepLAMED = false;
			prepMEM = false;
			prepositionTag = true;

			populatePrefixes(35, prefix, description, definiteArticleTag, defArtHE, relHE, adverbKAF, subConOrRelSHIN,
					tempSubConKAFSHIN, tempSubConMEMSHIN, tempSubConLAMEDKAFSHIN, relativizerTag, temporalSubConjTag,
					subordinatingConjunctionTag, prefPartUnit, prepBET, prepKAF, prepLAMED, prepMEM, prepositionTag,
					conjunctionTag);

			///////////////////////////////////////////////////////////////
			prefix = "'wmeb'";
			description = "'[+conjunction]" + URLEncoder.encode("ו", "UTF-8") + "[+temporalSubConj]"
					+ URLEncoder.encode("מש", "UTF-8") + "[+preposition]" + URLEncoder.encode("ב", "UTF-8")
					+ "[+definiteArticle]" + "'";
			definiteArticleTag = true;
			defArtHE = false;
			relHE = false;
			///////////////////////
			adverbKAF = false;
			subConOrRelSHIN = false;
			tempSubConKAFSHIN = false;
			tempSubConMEMSHIN = true;
			tempSubConLAMEDKAFSHIN = false;
			relativizerTag = false;
			temporalSubConjTag = true;
			subordinatingConjunctionTag = false;
			prefPartUnit = true;
			conjunctionTag = true;
			prepBET = true;
			prepKAF = false;
			prepLAMED = false;
			prepMEM = false;
			prepositionTag = true;

			populatePrefixes(36, prefix, description, definiteArticleTag, defArtHE, relHE, adverbKAF, subConOrRelSHIN,
					tempSubConKAFSHIN, tempSubConMEMSHIN, tempSubConLAMEDKAFSHIN, relativizerTag, temporalSubConjTag,
					subordinatingConjunctionTag, prefPartUnit, prepBET, prepKAF, prepLAMED, prepMEM, prepositionTag,
					conjunctionTag);

			//////////////////////////////////////////////////////////////
			prefix = "'emeb'";
			description = "'[+relativizer/subordinatingConjunction]" + URLEncoder.encode("ש", "UTF-8")
					+ "[+temporalSubConj]" + URLEncoder.encode("מש", "UTF-8") + "[+preposition]"
					+ URLEncoder.encode("ב", "UTF-8") + "'";
			definiteArticleTag = false;
			defArtHE = false;
			relHE = false;
			///////////////////////
			adverbKAF = false;
			subConOrRelSHIN = true;
			tempSubConKAFSHIN = false;
			tempSubConMEMSHIN = true;
			tempSubConLAMEDKAFSHIN = false;
			relativizerTag = true;
			temporalSubConjTag = true;
			subordinatingConjunctionTag = false;
			prefPartUnit = true;
			conjunctionTag = false;
			prepBET = true;
			prepKAF = false;
			prepLAMED = false;
			prepMEM = false;
			prepositionTag = true;

			populatePrefixes(37, prefix, description, definiteArticleTag, defArtHE, relHE, adverbKAF, subConOrRelSHIN,
					tempSubConKAFSHIN, tempSubConMEMSHIN, tempSubConLAMEDKAFSHIN, relativizerTag, temporalSubConjTag,
					subordinatingConjunctionTag, prefPartUnit, prepBET, prepKAF, prepLAMED, prepMEM, prepositionTag,
					conjunctionTag);

			///////////////////////////////////////////////////////////////
			prefix = "'emeb'";
			description = "'[+relativizer/subordinatingConjunction]" + URLEncoder.encode("ש", "UTF-8")
					+ "[+temporalSubConj]" + URLEncoder.encode("מש", "UTF-8") + "[+preposition]"
					+ URLEncoder.encode("ב", "UTF-8") + "[+definiteArticle]" + "'";
			definiteArticleTag = true;
			defArtHE = false;
			relHE = false;
			///////////////////////
			adverbKAF = false;
			subConOrRelSHIN = true;
			tempSubConKAFSHIN = false;
			tempSubConMEMSHIN = true;
			tempSubConLAMEDKAFSHIN = false;
			relativizerTag = true;
			temporalSubConjTag = true;
			subordinatingConjunctionTag = false;
			prefPartUnit = true;
			conjunctionTag = false;
			prepBET = true;
			prepKAF = false;
			prepLAMED = false;
			prepMEM = false;
			prepositionTag = true;

			populatePrefixes(38, prefix, description, definiteArticleTag, defArtHE, relHE, adverbKAF, subConOrRelSHIN,
					tempSubConKAFSHIN, tempSubConMEMSHIN, tempSubConLAMEDKAFSHIN, relativizerTag, temporalSubConjTag,
					subordinatingConjunctionTag, prefPartUnit, prepBET, prepKAF, prepLAMED, prepMEM, prepositionTag,
					conjunctionTag);

			//////////////////////////////////////////////////////////////
			prefix = "'wemeb'";
			description = "'[+conjunction]" + URLEncoder.encode("ו", "UTF-8") + "[+relativizer/subordinatingConjunction]"
					+ URLEncoder.encode("ש", "UTF-8") + "[+temporalSubConj]" + URLEncoder.encode("מש", "UTF-8")
					+ "[+preposition]" + URLEncoder.encode("ב", "UTF-8") + "'";
			definiteArticleTag = false;
			defArtHE = false;
			relHE = false;
			////////////////////
			adverbKAF = false;
			subConOrRelSHIN = true;
			tempSubConKAFSHIN = false;
			tempSubConMEMSHIN = true;
			tempSubConLAMEDKAFSHIN = false;
			relativizerTag = true;
			temporalSubConjTag = true;
			subordinatingConjunctionTag = false;
			prefPartUnit = true;
			conjunctionTag = true;
			prepBET = true;
			prepKAF = false;
			prepLAMED = false;
			prepMEM = false;
			prepositionTag = true;

			populatePrefixes(39, prefix, description, definiteArticleTag, defArtHE, relHE, adverbKAF, subConOrRelSHIN,
					tempSubConKAFSHIN, tempSubConMEMSHIN, tempSubConLAMEDKAFSHIN, relativizerTag, temporalSubConjTag,
					subordinatingConjunctionTag, prefPartUnit, prepBET, prepKAF, prepLAMED, prepMEM, prepositionTag,
					conjunctionTag);

			///////////////////////////////////////////////////////////////
			prefix = "'wemeb'";
			description = "'[+conjunction]" + URLEncoder.encode("ו", "UTF-8") + "[+relativizer/subordinatingConjunction]"
					+ URLEncoder.encode("ש", "UTF-8") + "[+temporalSubConj]" + URLEncoder.encode("מש", "UTF-8")
					+ "[+preposition]" + URLEncoder.encode("ב", "UTF-8") + "[+definiteArticle]" + "'";
			definiteArticleTag = true;
			defArtHE = false;
			relHE = false;
			////////////////////
			adverbKAF = false;
			subConOrRelSHIN = true;
			tempSubConKAFSHIN = false;
			tempSubConMEMSHIN = false;
			tempSubConLAMEDKAFSHIN = true;
			relativizerTag = true;
			temporalSubConjTag = true;
			subordinatingConjunctionTag = false;
			prefPartUnit = true;
			conjunctionTag = true;
			prepBET = true;
			prepKAF = false;
			prepLAMED = false;
			prepMEM = false;
			prepositionTag = true;

			populatePrefixes(40, prefix, description, definiteArticleTag, defArtHE, relHE, adverbKAF, subConOrRelSHIN,
					tempSubConKAFSHIN, tempSubConMEMSHIN, tempSubConLAMEDKAFSHIN, relativizerTag, temporalSubConjTag,
					subordinatingConjunctionTag, prefPartUnit, prepBET, prepKAF, prepLAMED, prepMEM, prepositionTag,
					conjunctionTag);

			//////////////////////////////////////////////////////////////
			prefix = "'lkeb'";
			description = "'[+temporalSubConj]" + URLEncoder.encode("לכש", "UTF-8") + "[+preposition]"
					+ URLEncoder.encode("ב", "UTF-8") + "'";
			definiteArticleTag = false;
			defArtHE = false;
			relHE = false;
			////////////////////////
			adverbKAF = false;
			subConOrRelSHIN = false;
			tempSubConKAFSHIN = false;
			tempSubConMEMSHIN = false;
			tempSubConLAMEDKAFSHIN = true;
			relativizerTag = false;
			temporalSubConjTag = true;
			subordinatingConjunctionTag = false;
			prefPartUnit = true;
			conjunctionTag = false;
			prepBET = true;
			prepKAF = false;
			prepLAMED = false;
			prepMEM = false;
			prepositionTag = true;

			populatePrefixes(41, prefix, description, definiteArticleTag, defArtHE, relHE, adverbKAF, subConOrRelSHIN,
					tempSubConKAFSHIN, tempSubConMEMSHIN, tempSubConLAMEDKAFSHIN, relativizerTag, temporalSubConjTag,
					subordinatingConjunctionTag, prefPartUnit, prepBET, prepKAF, prepLAMED, prepMEM, prepositionTag,
					conjunctionTag);

			///////////////////////////////////////////////////////////////
			prefix = "'lkeb'";
			description = "'[+temporalSubConj]" + URLEncoder.encode("לכש", "UTF-8") + "[+preposition]"
					+ URLEncoder.encode("ב", "UTF-8") + "[+definiteArticle]" + "'";
			definiteArticleTag = true;
			defArtHE = false;
			relHE = false;
			//////////////////////////
			adverbKAF = false;
			subConOrRelSHIN = false;
			tempSubConKAFSHIN = true;
			tempSubConMEMSHIN = false;
			tempSubConLAMEDKAFSHIN = false;
			relativizerTag = false;
			temporalSubConjTag = true;
			subordinatingConjunctionTag = false;
			prefPartUnit = true;
			conjunctionTag = false;
			prepBET = true;
			prepKAF = false;
			prepLAMED = false;
			prepMEM = false;
			prepositionTag = true;

			populatePrefixes(42, prefix, description, definiteArticleTag, defArtHE, relHE, adverbKAF, subConOrRelSHIN,
					tempSubConKAFSHIN, tempSubConMEMSHIN, tempSubConLAMEDKAFSHIN, relativizerTag, temporalSubConjTag,
					subordinatingConjunctionTag, prefPartUnit, prepBET, prepKAF, prepLAMED, prepMEM, prepositionTag,
					conjunctionTag);

			//////////////////////////////////////////////////////////////
			prefix = "'wlkeb'";
			description = "'[+conjunction]" + URLEncoder.encode("ו", "UTF-8") + "[+temporalSubConj]"
					+ URLEncoder.encode("לכש", "UTF-8") + "[+preposition]" + URLEncoder.encode("ב", "UTF-8") + "'";
			definiteArticleTag = false;
			defArtHE = false;
			relHE = false;
			///////////////////////
			adverbKAF = false;
			subConOrRelSHIN = false;
			tempSubConKAFSHIN = false;
			tempSubConMEMSHIN = false;
			tempSubConLAMEDKAFSHIN = true;
			relativizerTag = false;
			temporalSubConjTag = true;
			subordinatingConjunctionTag = false;
			prefPartUnit = true;
			conjunctionTag = true;
			prepBET = true;
			prepKAF = false;
			prepLAMED = false;
			prepMEM = false;
			prepositionTag = true;

			populatePrefixes(43, prefix, description, definiteArticleTag, defArtHE, relHE, adverbKAF, subConOrRelSHIN,
					tempSubConKAFSHIN, tempSubConMEMSHIN, tempSubConLAMEDKAFSHIN, relativizerTag, temporalSubConjTag,
					subordinatingConjunctionTag, prefPartUnit, prepBET, prepKAF, prepLAMED, prepMEM, prepositionTag,
					conjunctionTag);

			///////////////////////////////////////////////////////////////
			prefix = "'wlkeb'";
			description = "'[+conjunction]" + URLEncoder.encode("ו", "UTF-8") + "[+temporalSubConj]"
					+ URLEncoder.encode("לכש", "UTF-8") + "[+preposition]" + URLEncoder.encode("ב", "UTF-8")
					+ "[+definiteArticle]" + "'";
			definiteArticleTag = true;
			defArtHE = false;
			relHE = false;
			//////////////////////
			adverbKAF = false;
			subConOrRelSHIN = false;
			tempSubConKAFSHIN = false;
			tempSubConMEMSHIN = false;
			tempSubConLAMEDKAFSHIN = true;
			relativizerTag = false;
			temporalSubConjTag = true;
			subordinatingConjunctionTag = false;
			prefPartUnit = true;
			conjunctionTag = true;
			prepBET = true;
			prepKAF = false;
			prepLAMED = false;
			prepMEM = false;
			prepositionTag = true;

			populatePrefixes(44, prefix, description, definiteArticleTag, defArtHE, relHE, adverbKAF, subConOrRelSHIN,
					tempSubConKAFSHIN, tempSubConMEMSHIN, tempSubConLAMEDKAFSHIN, relativizerTag, temporalSubConjTag,
					subordinatingConjunctionTag, prefPartUnit, prepBET, prepKAF, prepLAMED, prepMEM, prepositionTag,
					conjunctionTag);

			//////////////////////////////////////////////////////////////
			prefix = "'elkeb'";
			description = "'[+relativizer/subordinatingConjunction]" + URLEncoder.encode("ש", "UTF-8")
					+ "[+temporalSubConj]" + URLEncoder.encode("לכש", "UTF-8") + "[+preposition]"
					+ URLEncoder.encode("ב", "UTF-8") + "'";
			definiteArticleTag = false;
			defArtHE = false;
			relHE = false;
			/////////////////
			adverbKAF = false;
			subConOrRelSHIN = true;
			tempSubConKAFSHIN = false;
			tempSubConMEMSHIN = false;
			tempSubConLAMEDKAFSHIN = true;
			relativizerTag = true;
			temporalSubConjTag = true;
			subordinatingConjunctionTag = false;
			prefPartUnit = true;
			conjunctionTag = false;
			prepBET = true;
			prepKAF = false;
			prepLAMED = false;
			prepMEM = false;
			prepositionTag = true;

			populatePrefixes(45, prefix, description, definiteArticleTag, defArtHE, relHE, adverbKAF, subConOrRelSHIN,
					tempSubConKAFSHIN, tempSubConMEMSHIN, tempSubConLAMEDKAFSHIN, relativizerTag, temporalSubConjTag,
					subordinatingConjunctionTag, prefPartUnit, prepBET, prepKAF, prepLAMED, prepMEM, prepositionTag,
					conjunctionTag);

			///////////////////////////////////////////////////////////////
			prefix = "'elkeb'";
			description = "'[+relativizer/subordinatingConjunction]" + URLEncoder.encode("ש", "UTF-8")
					+ "[+temporalSubConj]" + URLEncoder.encode("לכש", "UTF-8") + "[+preposition]"
					+ URLEncoder.encode("ב", "UTF-8") + "[+definiteArticle]" + "'";
			definiteArticleTag = true;
			defArtHE = false;
			relHE = false;
			////////////////
			adverbKAF = false;
			subConOrRelSHIN = true;
			tempSubConKAFSHIN = false;
			tempSubConMEMSHIN = false;
			tempSubConLAMEDKAFSHIN = true;
			relativizerTag = true;
			temporalSubConjTag = true;
			subordinatingConjunctionTag = false;
			prefPartUnit = true;
			conjunctionTag = false;
			prepBET = true;
			prepKAF = false;
			prepLAMED = false;
			prepMEM = false;
			prepositionTag = true;

			populatePrefixes(46, prefix, description, definiteArticleTag, defArtHE, relHE, adverbKAF, subConOrRelSHIN,
					tempSubConKAFSHIN, tempSubConMEMSHIN, tempSubConLAMEDKAFSHIN, relativizerTag, temporalSubConjTag,
					subordinatingConjunctionTag, prefPartUnit, prepBET, prepKAF, prepLAMED, prepMEM, prepositionTag,
					conjunctionTag);

			//////////////////////////////////////////////////////////////
			prefix = "'welkeb'";
			description = "'[+conjunction]" + URLEncoder.encode("ו", "UTF-8") + "[+relativizer/subordinatingConjunction]"
					+ URLEncoder.encode("ש", "UTF-8") + "[+temporalSubConj]" + URLEncoder.encode("לכש", "UTF-8")
					+ "[+preposition]" + URLEncoder.encode("ב", "UTF-8") + "'";
			definiteArticleTag = false;
			defArtHE = false;
			relHE = false;
			////////////////////
			adverbKAF = false;
			subConOrRelSHIN = true;
			tempSubConKAFSHIN = false;
			tempSubConMEMSHIN = false;
			tempSubConLAMEDKAFSHIN = true;
			relativizerTag = true;
			temporalSubConjTag = true;
			subordinatingConjunctionTag = false;
			prefPartUnit = true;
			conjunctionTag = true;
			prepBET = true;
			prepKAF = false;
			prepLAMED = false;
			prepMEM = false;
			prepositionTag = true;

			populatePrefixes(47, prefix, description, definiteArticleTag, defArtHE, relHE, adverbKAF, subConOrRelSHIN,
					tempSubConKAFSHIN, tempSubConMEMSHIN, tempSubConLAMEDKAFSHIN, relativizerTag, temporalSubConjTag,
					subordinatingConjunctionTag, prefPartUnit, prepBET, prepKAF, prepLAMED, prepMEM, prepositionTag,
					conjunctionTag);

			///////////////////////////////////////////////////////////////
			prefix = "'welkeb'";
			description = "'[+conjunction]" + URLEncoder.encode("ו", "UTF-8") + "[+relativizer/subordinatingConjunction]"
					+ URLEncoder.encode("ש", "UTF-8") + "[+temporalSubConj]" + URLEncoder.encode("לכש", "UTF-8")
					+ "[+preposition]" + URLEncoder.encode("ב", "UTF-8") + "[+definiteArticle]" + "'";
			definiteArticleTag = true;
			defArtHE = false;
			relHE = false;
			////////////////////
			adverbKAF = false;
			subConOrRelSHIN = true;
			tempSubConKAFSHIN = false;
			tempSubConMEMSHIN = false;
			tempSubConLAMEDKAFSHIN = true;
			relativizerTag = true;
			temporalSubConjTag = true;
			subordinatingConjunctionTag = false;
			prefPartUnit = true;
			conjunctionTag = true;
			prepBET = true;
			prepKAF = false;
			prepLAMED = false;
			prepMEM = false;
			prepositionTag = true;

			populatePrefixes(48, prefix, description, definiteArticleTag, defArtHE, relHE, adverbKAF, subConOrRelSHIN,
					tempSubConKAFSHIN, tempSubConMEMSHIN, tempSubConLAMEDKAFSHIN, relativizerTag, temporalSubConjTag,
					subordinatingConjunctionTag, prefPartUnit, prepBET, prepKAF, prepLAMED, prepMEM, prepositionTag,
					conjunctionTag);

			///////////////////////////////////////////////////////////////
			prefix = "'l'";
			description = "'[+preposition]" + URLEncoder.encode("ל", "UTF-8") + "'";
			definiteArticleTag = false;
			defArtHE = false;
			relHE = false;
			/////////////////////
			adverbKAF = false;
			subConOrRelSHIN = false;
			tempSubConKAFSHIN = true;
			tempSubConMEMSHIN = false;
			tempSubConLAMEDKAFSHIN = false;
			relativizerTag = false;
			temporalSubConjTag = false;
			subordinatingConjunctionTag = false;
			prefPartUnit = true;
			conjunctionTag = false;
			prepBET = false;
			prepKAF = false;
			prepLAMED = true;
			prepMEM = false;
			prepositionTag = true;

			populatePrefixes(49, prefix, description, definiteArticleTag, defArtHE, relHE, adverbKAF, subConOrRelSHIN,
					tempSubConKAFSHIN, tempSubConMEMSHIN, tempSubConLAMEDKAFSHIN, relativizerTag, temporalSubConjTag,
					subordinatingConjunctionTag, prefPartUnit, prepBET, prepKAF, prepLAMED, prepMEM, prepositionTag,
					conjunctionTag);
			///////////////////////////////////////////////////////////////
			prefix = "'l'";
			description = "'[+preposition]" + URLEncoder.encode("ל", "UTF-8") + "[+definiteArticle]" + "'";
			definiteArticleTag = true;
			defArtHE = false;
			relHE = false;
			//////////////////////
			adverbKAF = false;
			subConOrRelSHIN = false;
			tempSubConKAFSHIN = false;
			tempSubConMEMSHIN = false;
			tempSubConLAMEDKAFSHIN = false;
			relativizerTag = false;
			temporalSubConjTag = false;
			subordinatingConjunctionTag = false;
			prefPartUnit = true;
			conjunctionTag = false;
			prepBET = false;
			prepKAF = false;
			prepLAMED = true;
			prepMEM = false;
			prepositionTag = true;

			populatePrefixes(50, prefix, description, definiteArticleTag, defArtHE, relHE, adverbKAF, subConOrRelSHIN,
					tempSubConKAFSHIN, tempSubConMEMSHIN, tempSubConLAMEDKAFSHIN, relativizerTag, temporalSubConjTag,
					subordinatingConjunctionTag, prefPartUnit, prepBET, prepKAF, prepLAMED, prepMEM, prepositionTag,
					conjunctionTag);

			///////////////////////////////////////////////////////////////
			prefix = "'wl'";
			description = "'[+conjunction]" + URLEncoder.encode("ו", "UTF-8") + "[+preposition]"
					+ URLEncoder.encode("ל", "UTF-8") + "'";
			definiteArticleTag = false;
			defArtHE = false;
			relHE = false;
			////////////////////
			adverbKAF = false;
			subConOrRelSHIN = false;
			tempSubConKAFSHIN = false;
			tempSubConMEMSHIN = false;
			tempSubConLAMEDKAFSHIN = false;
			relativizerTag = false;
			temporalSubConjTag = true;
			subordinatingConjunctionTag = false;
			prefPartUnit = true;
			conjunctionTag = true;
			prepBET = false;
			prepKAF = false;
			prepLAMED = true;
			prepMEM = false;
			prepositionTag = true;

			populatePrefixes(51, prefix, description, definiteArticleTag, defArtHE, relHE, adverbKAF, subConOrRelSHIN,
					tempSubConKAFSHIN, tempSubConMEMSHIN, tempSubConLAMEDKAFSHIN, relativizerTag, temporalSubConjTag,
					subordinatingConjunctionTag, prefPartUnit, prepBET, prepKAF, prepLAMED, prepMEM, prepositionTag,
					conjunctionTag);

			///////////////////////////////////////////////////////////////
			prefix = "'wl'";
			description = "'[+conjunction]" + URLEncoder.encode("ו", "UTF-8") + "[+preposition]"
					+ URLEncoder.encode("ל", "UTF-8") + "[+definiteArticle]" + "'";
			definiteArticleTag = true;
			defArtHE = false;
			relHE = false;
			////////////////
			adverbKAF = false;
			subConOrRelSHIN = false;
			tempSubConKAFSHIN = false;
			tempSubConMEMSHIN = false;
			tempSubConLAMEDKAFSHIN = false;
			relativizerTag = false;
			temporalSubConjTag = false;
			subordinatingConjunctionTag = false;
			prefPartUnit = true;
			conjunctionTag = true;
			prepBET = false;
			prepKAF = false;
			prepLAMED = true;
			prepMEM = false;
			prepositionTag = true;

			populatePrefixes(52, prefix, description, definiteArticleTag, defArtHE, relHE, adverbKAF, subConOrRelSHIN,
					tempSubConKAFSHIN, tempSubConMEMSHIN, tempSubConLAMEDKAFSHIN, relativizerTag, temporalSubConjTag,
					subordinatingConjunctionTag, prefPartUnit, prepBET, prepKAF, prepLAMED, prepMEM, prepositionTag,
					conjunctionTag);

			//////////////////////////////////////////////////////////////
			prefix = "'el'";
			description = "'[+relativizer/subordinatingConjunction]" + URLEncoder.encode("ש", "UTF-8") + "[+preposition]"
					+ URLEncoder.encode("ל", "UTF-8") + "'";
			definiteArticleTag = false;
			defArtHE = false;
			relHE = false;
			///////////////////////
			adverbKAF = false;
			subConOrRelSHIN = true;
			tempSubConKAFSHIN = false;
			tempSubConMEMSHIN = false;
			tempSubConLAMEDKAFSHIN = false;
			relativizerTag = true;
			temporalSubConjTag = false;
			subordinatingConjunctionTag = false;
			prefPartUnit = true;
			conjunctionTag = false;
			prepBET = false;
			prepKAF = false;
			prepLAMED = true;
			prepMEM = false;
			prepositionTag = true;

			populatePrefixes(53, prefix, description, definiteArticleTag, defArtHE, relHE, adverbKAF, subConOrRelSHIN,
					tempSubConKAFSHIN, tempSubConMEMSHIN, tempSubConLAMEDKAFSHIN, relativizerTag, temporalSubConjTag,
					subordinatingConjunctionTag, prefPartUnit, prepBET, prepKAF, prepLAMED, prepMEM, prepositionTag,
					conjunctionTag);

			///////////////////////////////////////////////////////////////
			prefix = "'el'";
			description = "'[+relativizer/subordinatingConjunction]" + URLEncoder.encode("ש", "UTF-8") + "[+preposition]"
					+ URLEncoder.encode("ב", "UTF-8") + "[+definiteArticle]" + "'";
			definiteArticleTag = true;
			defArtHE = false;
			relHE = false;
			//////////////////////////
			adverbKAF = false;
			subConOrRelSHIN = true;
			tempSubConKAFSHIN = true;
			tempSubConMEMSHIN = false;
			tempSubConLAMEDKAFSHIN = false;
			relativizerTag = true;
			temporalSubConjTag = false;
			subordinatingConjunctionTag = false;
			prefPartUnit = true;
			conjunctionTag = false;
			prepBET = false;
			prepKAF = false;
			prepLAMED = true;
			prepMEM = false;
			prepositionTag = true;

			populatePrefixes(54, prefix, description, definiteArticleTag, defArtHE, relHE, adverbKAF, subConOrRelSHIN,
					tempSubConKAFSHIN, tempSubConMEMSHIN, tempSubConLAMEDKAFSHIN, relativizerTag, temporalSubConjTag,
					subordinatingConjunctionTag, prefPartUnit, prepBET, prepKAF, prepLAMED, prepMEM, prepositionTag,
					conjunctionTag);

			//////////////////////////////////////////////////////////////
			prefix = "'wel'";
			description = "'[+conjunction]" + URLEncoder.encode("ו", "UTF-8") + "[+relativizer/subordinatingConjunction]"
					+ URLEncoder.encode("ש", "UTF-8") + "[+preposition]" + URLEncoder.encode("ב", "UTF-8") + "'";
			definiteArticleTag = false;
			defArtHE = false;
			relHE = false;
			////////////////
			adverbKAF = false;
			subConOrRelSHIN = true;
			tempSubConKAFSHIN = false;
			tempSubConMEMSHIN = false;
			tempSubConLAMEDKAFSHIN = false;
			relativizerTag = true;
			temporalSubConjTag = false;
			subordinatingConjunctionTag = false;
			prefPartUnit = true;
			conjunctionTag = true;
			prepBET = false;
			prepKAF = false;
			prepLAMED = true;
			prepMEM = false;
			prepositionTag = true;

			populatePrefixes(55, prefix, description, definiteArticleTag, defArtHE, relHE, adverbKAF, subConOrRelSHIN,
					tempSubConKAFSHIN, tempSubConMEMSHIN, tempSubConLAMEDKAFSHIN, relativizerTag, temporalSubConjTag,
					subordinatingConjunctionTag, prefPartUnit, prepBET, prepKAF, prepLAMED, prepMEM, prepositionTag,
					conjunctionTag);

			///////////////////////////////////////////////////////////////
			prefix = "'wel'";
			description = "'[+conjunction]" + URLEncoder.encode("ו", "UTF-8") + "[+relativizer/subordinatingConjunction]"
					+ URLEncoder.encode("ש", "UTF-8") + "[+preposition]" + URLEncoder.encode("ל", "UTF-8")
					+ "[+definiteArticle]" + "'";
			definiteArticleTag = true;
			defArtHE = false;
			relHE = false;
			/////////////////
			adverbKAF = false;
			subConOrRelSHIN = true;
			tempSubConKAFSHIN = false;
			tempSubConMEMSHIN = false;
			tempSubConLAMEDKAFSHIN = false;
			relativizerTag = true;
			temporalSubConjTag = false;
			subordinatingConjunctionTag = false;
			prefPartUnit = true;
			conjunctionTag = true;
			prepBET = false;
			prepKAF = false;
			prepLAMED = true;
			prepMEM = false;
			prepositionTag = true;

			populatePrefixes(56, prefix, description, definiteArticleTag, defArtHE, relHE, adverbKAF, subConOrRelSHIN,
					tempSubConKAFSHIN, tempSubConMEMSHIN, tempSubConLAMEDKAFSHIN, relativizerTag, temporalSubConjTag,
					subordinatingConjunctionTag, prefPartUnit, prepBET, prepKAF, prepLAMED, prepMEM, prepositionTag,
					conjunctionTag);

			//////////////////////////////////////////////////////////////
			prefix = "'kel'";
			description = "'[+temporalSubConj]" + URLEncoder.encode("כש", "UTF-8") + "[+preposition]"
					+ URLEncoder.encode("ל", "UTF-8") + "'";
			definiteArticleTag = false;
			defArtHE = false;
			relHE = false;
			//////////////
			adverbKAF = false;
			subConOrRelSHIN = false;
			tempSubConKAFSHIN = true;
			tempSubConMEMSHIN = false;
			tempSubConLAMEDKAFSHIN = false;
			relativizerTag = false;
			temporalSubConjTag = true;
			subordinatingConjunctionTag = false;
			prefPartUnit = true;
			conjunctionTag = false;
			prepBET = false;
			prepKAF = false;
			prepLAMED = true;
			prepMEM = false;
			prepositionTag = true;

			populatePrefixes(57, prefix, description, definiteArticleTag, defArtHE, relHE, adverbKAF, subConOrRelSHIN,
					tempSubConKAFSHIN, tempSubConMEMSHIN, tempSubConLAMEDKAFSHIN, relativizerTag, temporalSubConjTag,
					subordinatingConjunctionTag, prefPartUnit, prepBET, prepKAF, prepLAMED, prepMEM, prepositionTag,
					conjunctionTag);

			///////////////////////////////////////////////////////////////
			prefix = "'kel'";
			description = "'[+temporalSubConj]" + URLEncoder.encode("כש", "UTF-8") + "[+preposition]"
					+ URLEncoder.encode("ל", "UTF-8") + "[+definiteArticle]" + "'";
			definiteArticleTag = true;
			defArtHE = false;
			relHE = false;
			///////////////////
			adverbKAF = false;
			subConOrRelSHIN = false;
			tempSubConKAFSHIN = true;
			tempSubConMEMSHIN = false;
			tempSubConLAMEDKAFSHIN = false;
			relativizerTag = false;
			temporalSubConjTag = true;
			subordinatingConjunctionTag = false;
			prefPartUnit = true;
			conjunctionTag = false;
			prepBET = false;
			prepKAF = false;
			prepLAMED = true;
			prepMEM = false;
			prepositionTag = true;

			populatePrefixes(58, prefix, description, definiteArticleTag, defArtHE, relHE, adverbKAF, subConOrRelSHIN,
					tempSubConKAFSHIN, tempSubConMEMSHIN, tempSubConLAMEDKAFSHIN, relativizerTag, temporalSubConjTag,
					subordinatingConjunctionTag, prefPartUnit, prepBET, prepKAF, prepLAMED, prepMEM, prepositionTag,
					conjunctionTag);

			//////////////////////////////////////////////////////////////
			prefix = "'wkel'";
			description = "'[+conjunction]" + URLEncoder.encode("ו", "UTF-8") + "[+temporalSubConj]"
					+ URLEncoder.encode("כש", "UTF-8") + "[+preposition]" + URLEncoder.encode("ל", "UTF-8") + "'";
			definiteArticleTag = false;
			defArtHE = false;
			relHE = false;
			//////////////////
			adverbKAF = false;
			subConOrRelSHIN = false;
			tempSubConKAFSHIN = true;
			tempSubConMEMSHIN = false;
			tempSubConLAMEDKAFSHIN = false;
			relativizerTag = false;
			temporalSubConjTag = true;
			subordinatingConjunctionTag = false;
			prefPartUnit = true;
			conjunctionTag = true;
			prepBET = false;
			prepKAF = false;
			prepLAMED = true;
			prepMEM = false;
			prepositionTag = true;

			populatePrefixes(59, prefix, description, definiteArticleTag, defArtHE, relHE, adverbKAF, subConOrRelSHIN,
					tempSubConKAFSHIN, tempSubConMEMSHIN, tempSubConLAMEDKAFSHIN, relativizerTag, temporalSubConjTag,
					subordinatingConjunctionTag, prefPartUnit, prepBET, prepKAF, prepLAMED, prepMEM, prepositionTag,
					conjunctionTag);

			///////////////////////////////////////////////////////////////
			prefix = "'wkel'";
			description = "'[+conjunction]" + URLEncoder.encode("ו", "UTF-8") + "[+temporalSubConj]"
					+ URLEncoder.encode("כש", "UTF-8") + "[+preposition]" + URLEncoder.encode("ל", "UTF-8")
					+ "[+definiteArticle]" + "'";
			definiteArticleTag = true;
			defArtHE = false;
			relHE = false;
			/////////////////////
			adverbKAF = false;
			subConOrRelSHIN = false;
			tempSubConKAFSHIN = true;
			tempSubConMEMSHIN = false;
			tempSubConLAMEDKAFSHIN = false;
			relativizerTag = false;
			temporalSubConjTag = true;
			subordinatingConjunctionTag = false;
			prefPartUnit = true;
			conjunctionTag = true;
			prepBET = false;
			prepKAF = false;
			prepLAMED = true;
			prepMEM = false;
			prepositionTag = true;

			populatePrefixes(60, prefix, description, definiteArticleTag, defArtHE, relHE, adverbKAF, subConOrRelSHIN,
					tempSubConKAFSHIN, tempSubConMEMSHIN, tempSubConLAMEDKAFSHIN, relativizerTag, temporalSubConjTag,
					subordinatingConjunctionTag, prefPartUnit, prepBET, prepKAF, prepLAMED, prepMEM, prepositionTag,
					conjunctionTag);

			//////////////////////////////////////////////////////////////
			prefix = "'ekel'";
			description = "'[+relativizer/subordinatingConjunction]" + URLEncoder.encode("ש", "UTF-8")
					+ "[+temporalSubConj]" + URLEncoder.encode("בכש", "UTF-8") + "[+preposition]"
					+ URLEncoder.encode("ל", "UTF-8") + "'";
			definiteArticleTag = false;
			defArtHE = false;
			relHE = false;
			//////////////////////////
			adverbKAF = false;
			subConOrRelSHIN = true;
			tempSubConKAFSHIN = true;
			tempSubConMEMSHIN = false;
			tempSubConLAMEDKAFSHIN = false;
			relativizerTag = true;
			temporalSubConjTag = true;
			subordinatingConjunctionTag = false;
			prefPartUnit = true;
			conjunctionTag = false;
			prepBET = false;
			prepKAF = false;
			prepLAMED = true;
			prepMEM = false;
			prepositionTag = true;

			populatePrefixes(61, prefix, description, definiteArticleTag, defArtHE, relHE, adverbKAF, subConOrRelSHIN,
					tempSubConKAFSHIN, tempSubConMEMSHIN, tempSubConLAMEDKAFSHIN, relativizerTag, temporalSubConjTag,
					subordinatingConjunctionTag, prefPartUnit, prepBET, prepKAF, prepLAMED, prepMEM, prepositionTag,
					conjunctionTag);

			///////////////////////////////////////////////////////////////
			prefix = "'ekel'";
			description = "'[+relativizer/subordinatingConjunction]" + URLEncoder.encode("ש", "UTF-8")
					+ "[+temporalSubConj]" + URLEncoder.encode("בכש", "UTF-8") + "[+preposition]"
					+ URLEncoder.encode("ל", "UTF-8") + "[+definiteArticle]" + "'";
			definiteArticleTag = true;
			defArtHE = false;
			relHE = false;
			//////////////////////
			adverbKAF = false;
			subConOrRelSHIN = true;
			tempSubConKAFSHIN = true;
			tempSubConMEMSHIN = false;
			tempSubConLAMEDKAFSHIN = false;
			relativizerTag = true;
			temporalSubConjTag = true;
			subordinatingConjunctionTag = false;
			prefPartUnit = true;
			conjunctionTag = false;
			prepBET = false;
			prepKAF = false;
			prepLAMED = true;
			prepMEM = false;
			prepositionTag = true;

			populatePrefixes(62, prefix, description, definiteArticleTag, defArtHE, relHE, adverbKAF, subConOrRelSHIN,
					tempSubConKAFSHIN, tempSubConMEMSHIN, tempSubConLAMEDKAFSHIN, relativizerTag, temporalSubConjTag,
					subordinatingConjunctionTag, prefPartUnit, prepBET, prepKAF, prepLAMED, prepMEM, prepositionTag,
					conjunctionTag);

			//////////////////////////////////////////////////////////////
			prefix = "'wekel'";
			description = "'[+conjunction]" + URLEncoder.encode("ו", "UTF-8") + "[+relativizer/subordinatingConjunction]"
					+ URLEncoder.encode("ש", "UTF-8") + "[+temporalSubConj]" + URLEncoder.encode("כש", "UTF-8")
					+ "[+preposition]" + URLEncoder.encode("ל", "UTF-8") + "'";
			definiteArticleTag = false;
			defArtHE = false;
			relHE = false;
			////////////////////////////
			adverbKAF = false;
			subConOrRelSHIN = true;
			tempSubConKAFSHIN = true;
			tempSubConMEMSHIN = false;
			tempSubConLAMEDKAFSHIN = false;
			relativizerTag = true;
			temporalSubConjTag = true;
			subordinatingConjunctionTag = false;
			prefPartUnit = true;
			conjunctionTag = true;
			prepBET = false;
			prepKAF = false;
			prepLAMED = true;
			prepMEM = false;
			prepositionTag = true;

			populatePrefixes(63, prefix, description, definiteArticleTag, defArtHE, relHE, adverbKAF, subConOrRelSHIN,
					tempSubConKAFSHIN, tempSubConMEMSHIN, tempSubConLAMEDKAFSHIN, relativizerTag, temporalSubConjTag,
					subordinatingConjunctionTag, prefPartUnit, prepBET, prepKAF, prepLAMED, prepMEM, prepositionTag,
					conjunctionTag);

			///////////////////////////////////////////////////////////////
			prefix = "'wekel'";
			description = "'[+conjunction]" + URLEncoder.encode("ו", "UTF-8") + "[+relativizer/subordinatingConjunction]"
					+ URLEncoder.encode("ש", "UTF-8") + "[+temporalSubConj]" + URLEncoder.encode("כש", "UTF-8")
					+ "[+preposition]" + URLEncoder.encode("ל", "UTF-8") + "[+definiteArticle]" + "'";
			definiteArticleTag = true;
			defArtHE = false;
			relHE = false;
			///////////////////////
			adverbKAF = false;
			subConOrRelSHIN = true;
			tempSubConKAFSHIN = true;
			tempSubConMEMSHIN = false;
			tempSubConLAMEDKAFSHIN = false;
			relativizerTag = true;
			temporalSubConjTag = true;
			subordinatingConjunctionTag = false;
			prefPartUnit = true;
			conjunctionTag = true;
			prepBET = false;
			prepKAF = false;
			prepLAMED = true;
			prepMEM = false;
			prepositionTag = true;

			populatePrefixes(64, prefix, description, definiteArticleTag, defArtHE, relHE, adverbKAF, subConOrRelSHIN,
					tempSubConKAFSHIN, tempSubConMEMSHIN, tempSubConLAMEDKAFSHIN, relativizerTag, temporalSubConjTag,
					subordinatingConjunctionTag, prefPartUnit, prepBET, prepKAF, prepLAMED, prepMEM, prepositionTag,
					conjunctionTag);

			//////////////////////////////////////////////////////////////
			prefix = "'mel'";
			description = "'[+temporalSubConj]" + URLEncoder.encode("מש", "UTF-8") + "[+preposition]"
					+ URLEncoder.encode("ל", "UTF-8") + "'";
			definiteArticleTag = false;
			defArtHE = false;
			relHE = false;
			//////////////////////////
			adverbKAF = false;
			subConOrRelSHIN = false;
			tempSubConKAFSHIN = false;
			tempSubConMEMSHIN = true;
			tempSubConLAMEDKAFSHIN = false;
			relativizerTag = false;
			temporalSubConjTag = true;
			subordinatingConjunctionTag = false;
			prefPartUnit = true;
			conjunctionTag = false;
			prepBET = false;
			prepKAF = false;
			prepLAMED = true;
			prepMEM = false;
			prepositionTag = true;

			populatePrefixes(65, prefix, description, definiteArticleTag, defArtHE, relHE, adverbKAF, subConOrRelSHIN,
					tempSubConKAFSHIN, tempSubConMEMSHIN, tempSubConLAMEDKAFSHIN, relativizerTag, temporalSubConjTag,
					subordinatingConjunctionTag, prefPartUnit, prepBET, prepKAF, prepLAMED, prepMEM, prepositionTag,
					conjunctionTag);

			///////////////////////////////////////////////////////////////
			prefix = "'mel'";
			description = "'[+temporalSubConj]" + URLEncoder.encode("מש", "UTF-8") + "[+preposition]"
					+ URLEncoder.encode("ל", "UTF-8") + "[+definiteArticle]" + "'";
			definiteArticleTag = true;
			defArtHE = false;
			relHE = false;
			////////////////////////
			adverbKAF = false;
			subConOrRelSHIN = false;
			tempSubConKAFSHIN = false;
			tempSubConMEMSHIN = true;
			tempSubConLAMEDKAFSHIN = false;
			relativizerTag = false;
			temporalSubConjTag = true;
			subordinatingConjunctionTag = false;
			prefPartUnit = true;
			conjunctionTag = false;
			prepBET = false;
			prepKAF = false;
			prepLAMED = true;
			prepMEM = false;
			prepositionTag = true;

			populatePrefixes(66, prefix, description, definiteArticleTag, defArtHE, relHE, adverbKAF, subConOrRelSHIN,
					tempSubConKAFSHIN, tempSubConMEMSHIN, tempSubConLAMEDKAFSHIN, relativizerTag, temporalSubConjTag,
					subordinatingConjunctionTag, prefPartUnit, prepBET, prepKAF, prepLAMED, prepMEM, prepositionTag,
					conjunctionTag);

			//////////////////////////////////////////////////////////////
			prefix = "'wmel'";
			description = "'[+conjunction]" + URLEncoder.encode("ו", "UTF-8") + "[+temporalSubConj]"
					+ URLEncoder.encode("מש", "UTF-8") + "[+preposition]" + URLEncoder.encode("ל", "UTF-8") + "'";
			definiteArticleTag = false;
			defArtHE = false;
			relHE = false;
			//////////////////////
			adverbKAF = false;
			subConOrRelSHIN = false;
			tempSubConKAFSHIN = false;
			tempSubConMEMSHIN = true;
			tempSubConLAMEDKAFSHIN = false;
			relativizerTag = false;
			temporalSubConjTag = true;
			subordinatingConjunctionTag = false;
			prefPartUnit = true;
			conjunctionTag = true;
			prepBET = false;
			prepKAF = false;
			prepLAMED = true;
			prepMEM = false;
			prepositionTag = true;

			populatePrefixes(67, prefix, description, definiteArticleTag, defArtHE, relHE, adverbKAF, subConOrRelSHIN,
					tempSubConKAFSHIN, tempSubConMEMSHIN, tempSubConLAMEDKAFSHIN, relativizerTag, temporalSubConjTag,
					subordinatingConjunctionTag, prefPartUnit, prepBET, prepKAF, prepLAMED, prepMEM, prepositionTag,
					conjunctionTag);

			///////////////////////////////////////////////////////////////
			prefix = "'wmel'";
			description = "'[+conjunction]" + URLEncoder.encode("ו", "UTF-8") + "[+temporalSubConj]"
					+ URLEncoder.encode("מש", "UTF-8") + "[+preposition]" + URLEncoder.encode("ל", "UTF-8")
					+ "[+definiteArticle]" + "'";
			definiteArticleTag = true;
			defArtHE = false;
			relHE = false;
			////////////////////
			adverbKAF = false;
			subConOrRelSHIN = false;
			tempSubConKAFSHIN = false;
			tempSubConMEMSHIN = true;
			tempSubConLAMEDKAFSHIN = false;
			relativizerTag = false;
			temporalSubConjTag = true;
			subordinatingConjunctionTag = false;
			prefPartUnit = true;
			conjunctionTag = true;
			prepBET = false;
			prepKAF = false;
			prepLAMED = true;
			prepMEM = false;
			prepositionTag = true;

			populatePrefixes(68, prefix, description, definiteArticleTag, defArtHE, relHE, adverbKAF, subConOrRelSHIN,
					tempSubConKAFSHIN, tempSubConMEMSHIN, tempSubConLAMEDKAFSHIN, relativizerTag, temporalSubConjTag,
					subordinatingConjunctionTag, prefPartUnit, prepBET, prepKAF, prepLAMED, prepMEM, prepositionTag,
					conjunctionTag);

			//////////////////////////////////////////////////////////////
			prefix = "'emel'";
			description = "'[+relativizer/subordinatingConjunction]" + URLEncoder.encode("ש", "UTF-8")
					+ "[+temporalSubConj]" + URLEncoder.encode("מש", "UTF-8") + "[+preposition]"
					+ URLEncoder.encode("ל", "UTF-8") + "'";
			definiteArticleTag = false;
			defArtHE = false;
			relHE = false;
			///////////////////////
			adverbKAF = false;
			subConOrRelSHIN = true;
			tempSubConKAFSHIN = false;
			tempSubConMEMSHIN = true;
			tempSubConLAMEDKAFSHIN = false;
			relativizerTag = true;
			temporalSubConjTag = true;
			subordinatingConjunctionTag = false;
			prefPartUnit = true;
			conjunctionTag = false;
			prepBET = false;
			prepKAF = false;
			prepLAMED = true;
			prepMEM = false;
			prepositionTag = true;

			populatePrefixes(69, prefix, description, definiteArticleTag, defArtHE, relHE, adverbKAF, subConOrRelSHIN,
					tempSubConKAFSHIN, tempSubConMEMSHIN, tempSubConLAMEDKAFSHIN, relativizerTag, temporalSubConjTag,
					subordinatingConjunctionTag, prefPartUnit, prepBET, prepKAF, prepLAMED, prepMEM, prepositionTag,
					conjunctionTag);

			///////////////////////////////////////////////////////////////
			prefix = "'emel'";
			description = "'[+relativizer/subordinatingConjunction]" + URLEncoder.encode("ש", "UTF-8")
					+ "[+temporalSubConj]" + URLEncoder.encode("מש", "UTF-8") + "[+preposition]"
					+ URLEncoder.encode("ל", "UTF-8") + "[+definiteArticle]" + "'";
			definiteArticleTag = true;
			defArtHE = false;
			relHE = false;
			//////////////////////
			adverbKAF = false;
			subConOrRelSHIN = true;
			tempSubConKAFSHIN = false;
			tempSubConMEMSHIN = true;
			tempSubConLAMEDKAFSHIN = false;
			relativizerTag = true;
			temporalSubConjTag = true;
			subordinatingConjunctionTag = false;
			prefPartUnit = true;
			conjunctionTag = false;
			prepBET = false;
			prepKAF = false;
			prepLAMED = true;
			prepMEM = false;
			prepositionTag = true;

			populatePrefixes(70, prefix, description, definiteArticleTag, defArtHE, relHE, adverbKAF, subConOrRelSHIN,
					tempSubConKAFSHIN, tempSubConMEMSHIN, tempSubConLAMEDKAFSHIN, relativizerTag, temporalSubConjTag,
					subordinatingConjunctionTag, prefPartUnit, prepBET, prepKAF, prepLAMED, prepMEM, prepositionTag,
					conjunctionTag);

			//////////////////////////////////////////////////////////////
			prefix = "'wemel'";
			description = "'[+conjunction]" + URLEncoder.encode("ו", "UTF-8") + "[+relativizer/subordinatingConjunction]"
					+ URLEncoder.encode("ש", "UTF-8") + "[+temporalSubConj]" + URLEncoder.encode("מש", "UTF-8")
					+ "[+preposition]" + URLEncoder.encode("ל", "UTF-8") + "'";
			definiteArticleTag = false;
			defArtHE = false;
			relHE = false;
			/////////////////
			adverbKAF = false;
			subConOrRelSHIN = true;
			tempSubConKAFSHIN = false;
			tempSubConMEMSHIN = true;
			tempSubConLAMEDKAFSHIN = false;
			relativizerTag = true;
			temporalSubConjTag = true;
			subordinatingConjunctionTag = false;
			prefPartUnit = true;
			conjunctionTag = true;
			prepBET = false;
			prepKAF = false;
			prepLAMED = true;
			prepMEM = false;
			prepositionTag = true;

			populatePrefixes(71, prefix, description, definiteArticleTag, defArtHE, relHE, adverbKAF, subConOrRelSHIN,
					tempSubConKAFSHIN, tempSubConMEMSHIN, tempSubConLAMEDKAFSHIN, relativizerTag, temporalSubConjTag,
					subordinatingConjunctionTag, prefPartUnit, prepBET, prepKAF, prepLAMED, prepMEM, prepositionTag,
					conjunctionTag);

			///////////////////////////////////////////////////////////////
			prefix = "'wemel'";
			description = "'[+conjunction]" + URLEncoder.encode("ו", "UTF-8") + "[+relativizer/subordinatingConjunction]"
					+ URLEncoder.encode("ש", "UTF-8") + "[+temporalSubConj]" + URLEncoder.encode("מש", "UTF-8")
					+ "[+preposition]" + URLEncoder.encode("ל", "UTF-8") + "[+definiteArticle]" + "'";
			definiteArticleTag = true;
			defArtHE = false;
			relHE = false;
			////////////////////
			adverbKAF = false;
			subConOrRelSHIN = true;
			tempSubConKAFSHIN = false;
			tempSubConMEMSHIN = true;
			tempSubConLAMEDKAFSHIN = false;
			relativizerTag = true;
			temporalSubConjTag = true;
			subordinatingConjunctionTag = false;
			prefPartUnit = true;
			conjunctionTag = true;
			prepBET = false;
			prepKAF = false;
			prepLAMED = true;
			prepMEM = false;
			prepositionTag = true;

			populatePrefixes(72, prefix, description, definiteArticleTag, defArtHE, relHE, adverbKAF, subConOrRelSHIN,
					tempSubConKAFSHIN, tempSubConMEMSHIN, tempSubConLAMEDKAFSHIN, relativizerTag, temporalSubConjTag,
					subordinatingConjunctionTag, prefPartUnit, prepBET, prepKAF, prepLAMED, prepMEM, prepositionTag,
					conjunctionTag);

			//////////////////////////////////////////////////////////////
			prefix = "'lkel'";
			description = "'[+temporalSubConj]" + URLEncoder.encode("לכש", "UTF-8") + "[+preposition]"
					+ URLEncoder.encode("ל", "UTF-8") + "'";
			definiteArticleTag = false;
			defArtHE = false;
			relHE = false;
			///////////////////////
			adverbKAF = false;
			subConOrRelSHIN = false;
			tempSubConKAFSHIN = false;
			tempSubConMEMSHIN = false;
			tempSubConLAMEDKAFSHIN = true;
			relativizerTag = false;
			temporalSubConjTag = true;
			subordinatingConjunctionTag = false;
			prefPartUnit = true;
			conjunctionTag = false;
			prepBET = false;
			prepKAF = false;
			prepLAMED = true;
			prepMEM = false;
			prepositionTag = true;

			populatePrefixes(73, prefix, description, definiteArticleTag, defArtHE, relHE, adverbKAF, subConOrRelSHIN,
					tempSubConKAFSHIN, tempSubConMEMSHIN, tempSubConLAMEDKAFSHIN, relativizerTag, temporalSubConjTag,
					subordinatingConjunctionTag, prefPartUnit, prepBET, prepKAF, prepLAMED, prepMEM, prepositionTag,
					conjunctionTag);

			///////////////////////////////////////////////////////////////
			prefix = "'lkel'";
			description = "'[+temporalSubConj]" + URLEncoder.encode("לכש", "UTF-8") + "[+preposition]"
					+ URLEncoder.encode("ל", "UTF-8") + "[+definiteArticle]" + "'";
			definiteArticleTag = true;
			defArtHE = false;
			relHE = false;
			///////////////////////
			adverbKAF = false;
			subConOrRelSHIN = false;
			tempSubConKAFSHIN = false;
			tempSubConMEMSHIN = false;
			tempSubConLAMEDKAFSHIN = true;
			relativizerTag = false;
			temporalSubConjTag = true;
			subordinatingConjunctionTag = false;
			prefPartUnit = true;
			conjunctionTag = false;
			prepBET = false;
			prepKAF = false;
			prepLAMED = true;
			prepMEM = false;
			prepositionTag = true;

			populatePrefixes(74, prefix, description, definiteArticleTag, defArtHE, relHE, adverbKAF, subConOrRelSHIN,
					tempSubConKAFSHIN, tempSubConMEMSHIN, tempSubConLAMEDKAFSHIN, relativizerTag, temporalSubConjTag,
					subordinatingConjunctionTag, prefPartUnit, prepBET, prepKAF, prepLAMED, prepMEM, prepositionTag,
					conjunctionTag);

			//////////////////////////////////////////////////////////////
			prefix = "'wlkel'";
			description = "'[+conjunction]" + URLEncoder.encode("ו", "UTF-8") + "[+temporalSubConj]"
					+ URLEncoder.encode("לכש", "UTF-8") + "[+preposition]" + URLEncoder.encode("ל", "UTF-8") + "'";
			definiteArticleTag = false;
			defArtHE = false;
			relHE = false;
			///////////////////////////
			adverbKAF = false;
			subConOrRelSHIN = false;
			tempSubConKAFSHIN = false;
			tempSubConMEMSHIN = false;
			tempSubConLAMEDKAFSHIN = true;
			relativizerTag = false;
			temporalSubConjTag = true;
			subordinatingConjunctionTag = false;
			prefPartUnit = true;
			conjunctionTag = true;
			prepBET = false;
			prepKAF = false;
			prepLAMED = true;
			prepMEM = false;
			prepositionTag = true;

			populatePrefixes(75, prefix, description, definiteArticleTag, defArtHE, relHE, adverbKAF, subConOrRelSHIN,
					tempSubConKAFSHIN, tempSubConMEMSHIN, tempSubConLAMEDKAFSHIN, relativizerTag, temporalSubConjTag,
					subordinatingConjunctionTag, prefPartUnit, prepBET, prepKAF, prepLAMED, prepMEM, prepositionTag,
					conjunctionTag);

			///////////////////////////////////////////////////////////////
			prefix = "'wlkel'";
			description = "'[+conjunction]" + URLEncoder.encode("ו", "UTF-8") + "[+temporalSubConj]"
					+ URLEncoder.encode("לכש", "UTF-8") + "[+preposition]" + URLEncoder.encode("ל", "UTF-8")
					+ "[+definiteArticle]" + "'";
			definiteArticleTag = true;
			defArtHE = false;
			relHE = false;
			//////////////////////////
			adverbKAF = false;
			subConOrRelSHIN = false;
			tempSubConKAFSHIN = false;
			tempSubConMEMSHIN = false;
			tempSubConLAMEDKAFSHIN = true;
			relativizerTag = false;
			temporalSubConjTag = true;
			subordinatingConjunctionTag = false;
			prefPartUnit = true;
			conjunctionTag = true;
			prepBET = false;
			prepKAF = false;
			prepLAMED = true;
			prepMEM = false;
			prepositionTag = true;

			populatePrefixes(76, prefix, description, definiteArticleTag, defArtHE, relHE, adverbKAF, subConOrRelSHIN,
					tempSubConKAFSHIN, tempSubConMEMSHIN, tempSubConLAMEDKAFSHIN, relativizerTag, temporalSubConjTag,
					subordinatingConjunctionTag, prefPartUnit, prepBET, prepKAF, prepLAMED, prepMEM, prepositionTag,
					conjunctionTag);

			//////////////////////////////////////////////////////////////
			prefix = "'elkel'";
			description = "'[+relativizer/subordinatingConjunction]" + URLEncoder.encode("ש", "UTF-8")
					+ "[+temporalSubConj]" + URLEncoder.encode("לכש", "UTF-8") + "[+preposition]"
					+ URLEncoder.encode("ל", "UTF-8") + "'";
			definiteArticleTag = false;
			defArtHE = false;
			relHE = false;
			/////////////////////////////
			adverbKAF = false;
			subConOrRelSHIN = true;
			tempSubConKAFSHIN = false;
			tempSubConMEMSHIN = false;
			tempSubConLAMEDKAFSHIN = true;
			relativizerTag = true;
			temporalSubConjTag = true;
			subordinatingConjunctionTag = false;
			prefPartUnit = true;
			conjunctionTag = false;
			prepBET = false;
			prepKAF = false;
			prepLAMED = true;
			prepMEM = false;
			prepositionTag = true;

			populatePrefixes(77, prefix, description, definiteArticleTag, defArtHE, relHE, adverbKAF, subConOrRelSHIN,
					tempSubConKAFSHIN, tempSubConMEMSHIN, tempSubConLAMEDKAFSHIN, relativizerTag, temporalSubConjTag,
					subordinatingConjunctionTag, prefPartUnit, prepBET, prepKAF, prepLAMED, prepMEM, prepositionTag,
					conjunctionTag);

			///////////////////////////////////////////////////////////////
			prefix = "'elkel'";
			description = "'[+relativizer/subordinatingConjunction]" + URLEncoder.encode("ש", "UTF-8")
					+ "[+temporalSubConj]" + URLEncoder.encode("לכש", "UTF-8") + "[+preposition]"
					+ URLEncoder.encode("ל", "UTF-8") + "[+definiteArticle]" + "'";
			definiteArticleTag = true;
			defArtHE = false;
			relHE = false;
			///////////////////////////
			adverbKAF = false;
			subConOrRelSHIN = true;
			tempSubConKAFSHIN = false;
			tempSubConMEMSHIN = false;
			tempSubConLAMEDKAFSHIN = true;
			relativizerTag = true;
			temporalSubConjTag = true;
			subordinatingConjunctionTag = false;
			prefPartUnit = true;
			conjunctionTag = false;
			prepBET = false;
			prepKAF = false;
			prepLAMED = true;
			prepMEM = false;
			prepositionTag = true;

			populatePrefixes(78, prefix, description, definiteArticleTag, defArtHE, relHE, adverbKAF, subConOrRelSHIN,
					tempSubConKAFSHIN, tempSubConMEMSHIN, tempSubConLAMEDKAFSHIN, relativizerTag, temporalSubConjTag,
					subordinatingConjunctionTag, prefPartUnit, prepBET, prepKAF, prepLAMED, prepMEM, prepositionTag,
					conjunctionTag);

			//////////////////////////////////////////////////////////////
			prefix = "'welkel'";
			description = "'[+conjunction]" + URLEncoder.encode("ו", "UTF-8") + "[+relativizer/subordinatingConjunction]"
					+ URLEncoder.encode("ש", "UTF-8") + "[+temporalSubConj]" + URLEncoder.encode("לכש", "UTF-8")
					+ "[+preposition]" + URLEncoder.encode("ל", "UTF-8") + "'";
			definiteArticleTag = false;
			defArtHE = false;
			relHE = false;
			////////////////////////
			adverbKAF = false;
			subConOrRelSHIN = true;
			tempSubConKAFSHIN = false;
			tempSubConMEMSHIN = false;
			tempSubConLAMEDKAFSHIN = true;
			relativizerTag = true;
			temporalSubConjTag = true;
			subordinatingConjunctionTag = false;
			prefPartUnit = true;
			conjunctionTag = true;
			prepBET = false;
			prepKAF = false;
			prepLAMED = true;
			prepMEM = false;
			prepositionTag = true;

			populatePrefixes(79, prefix, description, definiteArticleTag, defArtHE, relHE, adverbKAF, subConOrRelSHIN,
					tempSubConKAFSHIN, tempSubConMEMSHIN, tempSubConLAMEDKAFSHIN, relativizerTag, temporalSubConjTag,
					subordinatingConjunctionTag, prefPartUnit, prepBET, prepKAF, prepLAMED, prepMEM, prepositionTag,
					conjunctionTag);

			///////////////////////////////////////////////////////////////
			prefix = "'welkel'";
			description = "'[+conjunction]" + URLEncoder.encode("ו", "UTF-8") + "[+relativizer/subordinatingConjunction]"
					+ URLEncoder.encode("ש", "UTF-8") + "[+temporalSubConj]" + URLEncoder.encode("לכש", "UTF-8")
					+ "[+preposition]" + URLEncoder.encode("ל", "UTF-8") + "[+definiteArticle]" + "'";
			definiteArticleTag = true;
			defArtHE = false;
			relHE = false;
			//////////////////////////
			adverbKAF = false;
			subConOrRelSHIN = true;
			tempSubConKAFSHIN = true;
			tempSubConMEMSHIN = false;
			tempSubConLAMEDKAFSHIN = true;
			relativizerTag = true;
			temporalSubConjTag = true;
			subordinatingConjunctionTag = false;
			prefPartUnit = true;
			conjunctionTag = true;
			prepBET = false;
			prepKAF = false;
			prepLAMED = true;
			prepMEM = false;
			prepositionTag = true;

			populatePrefixes(80, prefix, description, definiteArticleTag, defArtHE, relHE, adverbKAF, subConOrRelSHIN,
					tempSubConKAFSHIN, tempSubConMEMSHIN, tempSubConLAMEDKAFSHIN, relativizerTag, temporalSubConjTag,
					subordinatingConjunctionTag, prefPartUnit, prepBET, prepKAF, prepLAMED, prepMEM, prepositionTag,
					conjunctionTag);

			///////////////////////////////////////////////////////////////
			prefix = "'k'";
			description = "'[+preposition]" + URLEncoder.encode("כ", "UTF-8") + "'";
			definiteArticleTag = false;
			defArtHE = false;
			relHE = false;
			///////////////////////
			adverbKAF = false;
			subConOrRelSHIN = false;
			tempSubConKAFSHIN = false;
			tempSubConMEMSHIN = false;
			tempSubConLAMEDKAFSHIN = false;
			relativizerTag = false;
			temporalSubConjTag = false;
			subordinatingConjunctionTag = false;
			prefPartUnit = true;
			conjunctionTag = false;
			prepBET = false;
			prepKAF = true;
			prepLAMED = false;
			prepMEM = false;
			prepositionTag = true;

			populatePrefixes(81, prefix, description, definiteArticleTag, defArtHE, relHE, adverbKAF, subConOrRelSHIN,
					tempSubConKAFSHIN, tempSubConMEMSHIN, tempSubConLAMEDKAFSHIN, relativizerTag, temporalSubConjTag,
					subordinatingConjunctionTag, prefPartUnit, prepBET, prepKAF, prepLAMED, prepMEM, prepositionTag,
					conjunctionTag);
			///////////////////////////////////////////////////////////////
			prefix = "'k'";
			description = "'[+preposition]" + URLEncoder.encode("כ", "UTF-8") + "[+definiteArticle]" + "'";
			definiteArticleTag = true;
			defArtHE = false;
			relHE = false;
			////////////////////////
			adverbKAF = false;
			subConOrRelSHIN = false;
			tempSubConKAFSHIN = false;
			tempSubConMEMSHIN = false;
			tempSubConLAMEDKAFSHIN = false;
			relativizerTag = false;
			temporalSubConjTag = false;
			subordinatingConjunctionTag = false;
			prefPartUnit = true;
			conjunctionTag = false;
			prepBET = false;
			prepKAF = true;
			prepLAMED = false;
			prepMEM = false;
			prepositionTag = true;

			populatePrefixes(82, prefix, description, definiteArticleTag, defArtHE, relHE, adverbKAF, subConOrRelSHIN,
					tempSubConKAFSHIN, tempSubConMEMSHIN, tempSubConLAMEDKAFSHIN, relativizerTag, temporalSubConjTag,
					subordinatingConjunctionTag, prefPartUnit, prepBET, prepKAF, prepLAMED, prepMEM, prepositionTag,
					conjunctionTag);

			///////////////////////////////////////////////////////////////
			prefix = "'wk'";
			description = "'[+conjunction]" + URLEncoder.encode("ו", "UTF-8") + "[+preposition]"
					+ URLEncoder.encode("כ", "UTF-8") + "'";
			definiteArticleTag = false;
			defArtHE = false;
			relHE = false;
			//////////////////////
			adverbKAF = false;
			subConOrRelSHIN = false;
			tempSubConKAFSHIN = false;
			tempSubConMEMSHIN = false;
			tempSubConLAMEDKAFSHIN = false;
			relativizerTag = false;
			temporalSubConjTag = false;
			subordinatingConjunctionTag = false;
			prefPartUnit = true;
			conjunctionTag = true;
			prepBET = false;
			prepKAF = true;
			prepLAMED = false;
			prepMEM = false;
			prepositionTag = true;

			populatePrefixes(83, prefix, description, definiteArticleTag, defArtHE, relHE, adverbKAF, subConOrRelSHIN,
					tempSubConKAFSHIN, tempSubConMEMSHIN, tempSubConLAMEDKAFSHIN, relativizerTag, temporalSubConjTag,
					subordinatingConjunctionTag, prefPartUnit, prepBET, prepKAF, prepLAMED, prepMEM, prepositionTag,
					conjunctionTag);

			///////////////////////////////////////////////////////////////
			prefix = "'wk'";
			description = "'[+conjunction]" + URLEncoder.encode("ו", "UTF-8") + "[+preposition]"
					+ URLEncoder.encode("כ", "UTF-8") + "[+definiteArticle]" + "'";
			definiteArticleTag = true;
			defArtHE = false;
			relHE = false;
			///////////////////////
			adverbKAF = false;
			subConOrRelSHIN = false;
			tempSubConKAFSHIN = false;
			tempSubConMEMSHIN = false;
			tempSubConLAMEDKAFSHIN = false;
			relativizerTag = false;
			temporalSubConjTag = false;
			subordinatingConjunctionTag = false;
			prefPartUnit = true;
			conjunctionTag = true;
			prepBET = false;
			prepKAF = true;
			prepLAMED = false;
			prepMEM = false;
			prepositionTag = true;

			populatePrefixes(84, prefix, description, definiteArticleTag, defArtHE, relHE, adverbKAF, subConOrRelSHIN,
					tempSubConKAFSHIN, tempSubConMEMSHIN, tempSubConLAMEDKAFSHIN, relativizerTag, temporalSubConjTag,
					subordinatingConjunctionTag, prefPartUnit, prepBET, prepKAF, prepLAMED, prepMEM, prepositionTag,
					conjunctionTag);

			//////////////////////////////////////////////////////////////
			prefix = "'ek'";
			description = "'[+relativizer/subordinatingConjunction]" + URLEncoder.encode("ש", "UTF-8") + "[+preposition]"
					+ URLEncoder.encode("כ", "UTF-8") + "'";
			definiteArticleTag = false;
			defArtHE = false;
			relHE = false;
			//////////////////////
			adverbKAF = false;
			subConOrRelSHIN = true;
			tempSubConKAFSHIN = false;
			tempSubConMEMSHIN = false;
			tempSubConLAMEDKAFSHIN = false;
			relativizerTag = true;
			temporalSubConjTag = false;
			subordinatingConjunctionTag = false;
			prefPartUnit = true;
			conjunctionTag = false;
			prepBET = false;
			prepKAF = true;
			prepLAMED = false;
			prepMEM = false;
			prepositionTag = true;

			populatePrefixes(85, prefix, description, definiteArticleTag, defArtHE, relHE, adverbKAF, subConOrRelSHIN,
					tempSubConKAFSHIN, tempSubConMEMSHIN, tempSubConLAMEDKAFSHIN, relativizerTag, temporalSubConjTag,
					subordinatingConjunctionTag, prefPartUnit, prepBET, prepKAF, prepLAMED, prepMEM, prepositionTag,
					conjunctionTag);

			///////////////////////////////////////////////////////////////
			prefix = "'ek'";
			description = "'[+relativizer/subordinatingConjunction]" + URLEncoder.encode("ש", "UTF-8") + "[+preposition]"
					+ URLEncoder.encode("כ", "UTF-8") + "[+definiteArticle]" + "'";
			definiteArticleTag = true;
			defArtHE = false;
			relHE = false;
			//////////////////////
			adverbKAF = false;
			subConOrRelSHIN = true;
			tempSubConKAFSHIN = false;
			tempSubConMEMSHIN = false;
			tempSubConLAMEDKAFSHIN = false;
			relativizerTag = true;
			temporalSubConjTag = false;
			subordinatingConjunctionTag = false;
			prefPartUnit = true;
			conjunctionTag = false;
			prepBET = false;
			prepKAF = true;
			prepLAMED = false;
			prepMEM = false;
			prepositionTag = true;

			populatePrefixes(86, prefix, description, definiteArticleTag, defArtHE, relHE, adverbKAF, subConOrRelSHIN,
					tempSubConKAFSHIN, tempSubConMEMSHIN, tempSubConLAMEDKAFSHIN, relativizerTag, temporalSubConjTag,
					subordinatingConjunctionTag, prefPartUnit, prepBET, prepKAF, prepLAMED, prepMEM, prepositionTag,
					conjunctionTag);

			//////////////////////////////////////////////////////////////
			prefix = "'wek'";
			description = "'[+conjunction]" + URLEncoder.encode("ו", "UTF-8") + "[+relativizer/subordinatingConjunction]"
					+ URLEncoder.encode("ש", "UTF-8") + "[+preposition]" + URLEncoder.encode("כ", "UTF-8") + "'";
			definiteArticleTag = false;
			defArtHE = false;
			relHE = false;
			//////////////////////
			adverbKAF = false;
			subConOrRelSHIN = true;
			tempSubConKAFSHIN = false;
			tempSubConMEMSHIN = false;
			tempSubConLAMEDKAFSHIN = false;
			relativizerTag = true;
			temporalSubConjTag = false;
			subordinatingConjunctionTag = false;
			prefPartUnit = true;
			conjunctionTag = true;
			prepBET = false;
			prepKAF = true;
			prepLAMED = false;
			prepMEM = false;
			prepositionTag = true;

			populatePrefixes(87, prefix, description, definiteArticleTag, defArtHE, relHE, adverbKAF, subConOrRelSHIN,
					tempSubConKAFSHIN, tempSubConMEMSHIN, tempSubConLAMEDKAFSHIN, relativizerTag, temporalSubConjTag,
					subordinatingConjunctionTag, prefPartUnit, prepBET, prepKAF, prepLAMED, prepMEM, prepositionTag,
					conjunctionTag);

			///////////////////////////////////////////////////////////////
			prefix = "'wek'";
			description = "'[+conjunction]" + URLEncoder.encode("ו", "UTF-8") + "[+relativizer/subordinatingConjunction]"
					+ URLEncoder.encode("ש", "UTF-8") + "[+preposition]" + URLEncoder.encode("כ", "UTF-8")
					+ "[+definiteArticle]" + "'";
			definiteArticleTag = true;
			defArtHE = false;
			relHE = false;
			////////////////////////
			adverbKAF = false;
			subConOrRelSHIN = true;
			tempSubConKAFSHIN = false;
			tempSubConMEMSHIN = false;
			tempSubConLAMEDKAFSHIN = false;
			relativizerTag = true;
			temporalSubConjTag = false;
			subordinatingConjunctionTag = false;
			prefPartUnit = true;
			conjunctionTag = true;
			prepBET = false;
			prepKAF = true;
			prepLAMED = false;
			prepMEM = false;
			prepositionTag = true;

			populatePrefixes(88, prefix, description, definiteArticleTag, defArtHE, relHE, adverbKAF, subConOrRelSHIN,
					tempSubConKAFSHIN, tempSubConMEMSHIN, tempSubConLAMEDKAFSHIN, relativizerTag, temporalSubConjTag,
					subordinatingConjunctionTag, prefPartUnit, prepBET, prepKAF, prepLAMED, prepMEM, prepositionTag,
					conjunctionTag);

			//////////////////////////////////////////////////////////////
			prefix = "'kek'";
			description = "'[+temporalSubConj]" + URLEncoder.encode("כש", "UTF-8") + "[+preposition]"
					+ URLEncoder.encode("כ", "UTF-8") + "'";
			definiteArticleTag = false;
			defArtHE = false;
			relHE = false;
			////////////////
			adverbKAF = false;
			subConOrRelSHIN = false;
			tempSubConKAFSHIN = true;
			tempSubConMEMSHIN = false;
			tempSubConLAMEDKAFSHIN = false;
			relativizerTag = false;
			temporalSubConjTag = true;
			subordinatingConjunctionTag = false;
			prefPartUnit = true;
			conjunctionTag = false;
			prepBET = false;
			prepKAF = true;
			prepLAMED = false;
			prepMEM = false;
			prepositionTag = true;

			populatePrefixes(89, prefix, description, definiteArticleTag, defArtHE, relHE, adverbKAF, subConOrRelSHIN,
					tempSubConKAFSHIN, tempSubConMEMSHIN, tempSubConLAMEDKAFSHIN, relativizerTag, temporalSubConjTag,
					subordinatingConjunctionTag, prefPartUnit, prepBET, prepKAF, prepLAMED, prepMEM, prepositionTag,
					conjunctionTag);

			///////////////////////////////////////////////////////////////
			prefix = "'kek'";
			description = "'[+temporalSubConj]" + URLEncoder.encode("כש", "UTF-8") + "[+preposition]"
					+ URLEncoder.encode("כ", "UTF-8") + "[+definiteArticle]" + "'";
			definiteArticleTag = true;
			defArtHE = false;
			relHE = false;
			///////////////////////
			adverbKAF = false;
			subConOrRelSHIN = false;
			tempSubConKAFSHIN = true;
			tempSubConMEMSHIN = false;
			tempSubConLAMEDKAFSHIN = false;
			relativizerTag = false;
			temporalSubConjTag = true;
			subordinatingConjunctionTag = false;
			prefPartUnit = true;
			conjunctionTag = false;
			prepBET = false;
			prepKAF = true;
			prepLAMED = false;
			prepMEM = false;
			prepositionTag = true;

			populatePrefixes(90, prefix, description, definiteArticleTag, defArtHE, relHE, adverbKAF, subConOrRelSHIN,
					tempSubConKAFSHIN, tempSubConMEMSHIN, tempSubConLAMEDKAFSHIN, relativizerTag, temporalSubConjTag,
					subordinatingConjunctionTag, prefPartUnit, prepBET, prepKAF, prepLAMED, prepMEM, prepositionTag,
					conjunctionTag);

			//////////////////////////////////////////////////////////////
			prefix = "'wkek'";
			description = "'[+conjunction]" + URLEncoder.encode("ו", "UTF-8") + "[+temporalSubConj]"
					+ URLEncoder.encode("כש", "UTF-8") + "[+preposition]" + URLEncoder.encode("כ", "UTF-8") + "'";
			definiteArticleTag = false;
			defArtHE = false;
			relHE = false;
			//////////////////////
			adverbKAF = false;
			subConOrRelSHIN = false;
			tempSubConKAFSHIN = true;
			tempSubConMEMSHIN = false;
			tempSubConLAMEDKAFSHIN = false;
			relativizerTag = false;
			temporalSubConjTag = true;
			subordinatingConjunctionTag = false;
			prefPartUnit = true;
			conjunctionTag = true;
			prepBET = false;
			prepKAF = true;
			prepLAMED = false;
			prepMEM = false;
			prepositionTag = true;

			populatePrefixes(91, prefix, description, definiteArticleTag, defArtHE, relHE, adverbKAF, subConOrRelSHIN,
					tempSubConKAFSHIN, tempSubConMEMSHIN, tempSubConLAMEDKAFSHIN, relativizerTag, temporalSubConjTag,
					subordinatingConjunctionTag, prefPartUnit, prepBET, prepKAF, prepLAMED, prepMEM, prepositionTag,
					conjunctionTag);

			///////////////////////////////////////////////////////////////
			prefix = "'wkek'";
			description = "'[+conjunction]" + URLEncoder.encode("ו", "UTF-8") + "[+temporalSubConj]"
					+ URLEncoder.encode("כש", "UTF-8") + "[+preposition]" + URLEncoder.encode("כ", "UTF-8")
					+ "[+definiteArticle]" + "'";
			definiteArticleTag = true;
			defArtHE = false;
			relHE = false;
			////////////////////////
			adverbKAF = false;
			subConOrRelSHIN = false;
			tempSubConKAFSHIN = true;
			tempSubConMEMSHIN = false;
			tempSubConLAMEDKAFSHIN = false;
			relativizerTag = false;
			temporalSubConjTag = true;
			subordinatingConjunctionTag = false;
			prefPartUnit = true;
			conjunctionTag = true;
			prepBET = false;
			prepKAF = true;
			prepLAMED = false;
			prepMEM = false;
			prepositionTag = true;

			populatePrefixes(92, prefix, description, definiteArticleTag, defArtHE, relHE, adverbKAF, subConOrRelSHIN,
					tempSubConKAFSHIN, tempSubConMEMSHIN, tempSubConLAMEDKAFSHIN, relativizerTag, temporalSubConjTag,
					subordinatingConjunctionTag, prefPartUnit, prepBET, prepKAF, prepLAMED, prepMEM, prepositionTag,
					conjunctionTag);

			//////////////////////////////////////////////////////////////
			prefix = "'ekek'";
			description = "'[+relativizer/subordinatingConjunction]" + URLEncoder.encode("ש", "UTF-8")
					+ "[+temporalSubConj]" + URLEncoder.encode("בכש", "UTF-8") + "[+preposition]"
					+ URLEncoder.encode("כ", "UTF-8") + "'";
			definiteArticleTag = false;
			defArtHE = false;
			relHE = false;
			////////////////////
			adverbKAF = false;
			subConOrRelSHIN = true;
			tempSubConKAFSHIN = true;
			tempSubConMEMSHIN = false;
			tempSubConLAMEDKAFSHIN = false;
			relativizerTag = true;
			temporalSubConjTag = true;
			subordinatingConjunctionTag = false;
			prefPartUnit = true;
			conjunctionTag = false;
			prepBET = false;
			prepKAF = true;
			prepLAMED = false;
			prepMEM = false;
			prepositionTag = true;

			populatePrefixes(93, prefix, description, definiteArticleTag, defArtHE, relHE, adverbKAF, subConOrRelSHIN,
					tempSubConKAFSHIN, tempSubConMEMSHIN, tempSubConLAMEDKAFSHIN, relativizerTag, temporalSubConjTag,
					subordinatingConjunctionTag, prefPartUnit, prepBET, prepKAF, prepLAMED, prepMEM, prepositionTag,
					conjunctionTag);

			///////////////////////////////////////////////////////////////
			prefix = "'ekek'";
			description = "'[+relativizer/subordinatingConjunction]" + URLEncoder.encode("ש", "UTF-8")
					+ "[+temporalSubConj]" + URLEncoder.encode("בכש", "UTF-8") + "[+preposition]"
					+ URLEncoder.encode("כ", "UTF-8") + "[+definiteArticle]" + "'";
			definiteArticleTag = true;
			defArtHE = false;
			relHE = false;
			////////////////////
			adverbKAF = false;
			subConOrRelSHIN = true;
			tempSubConKAFSHIN = true;
			tempSubConMEMSHIN = false;
			tempSubConLAMEDKAFSHIN = false;
			relativizerTag = true;
			temporalSubConjTag = true;
			subordinatingConjunctionTag = false;
			prefPartUnit = true;
			conjunctionTag = false;
			prepBET = false;
			prepKAF = true;
			prepLAMED = false;
			prepMEM = false;
			prepositionTag = true;

			populatePrefixes(94, prefix, description, definiteArticleTag, defArtHE, relHE, adverbKAF, subConOrRelSHIN,
					tempSubConKAFSHIN, tempSubConMEMSHIN, tempSubConLAMEDKAFSHIN, relativizerTag, temporalSubConjTag,
					subordinatingConjunctionTag, prefPartUnit, prepBET, prepKAF, prepLAMED, prepMEM, prepositionTag,
					conjunctionTag);

			//////////////////////////////////////////////////////////////
			prefix = "'wekek'";
			description = "'[+conjunction]" + URLEncoder.encode("ו", "UTF-8") + "[+relativizer/subordinatingConjunction]"
					+ URLEncoder.encode("ש", "UTF-8") + "[+temporalSubConj]" + URLEncoder.encode("כש", "UTF-8")
					+ "[+preposition]" + URLEncoder.encode("כ", "UTF-8") + "'";
			definiteArticleTag = false;
			defArtHE = false;
			relHE = false;
			///////////////////////
			adverbKAF = false;
			subConOrRelSHIN = true;
			tempSubConKAFSHIN = true;
			tempSubConMEMSHIN = false;
			tempSubConLAMEDKAFSHIN = false;
			relativizerTag = true;
			temporalSubConjTag = true;
			subordinatingConjunctionTag = false;
			prefPartUnit = true;
			conjunctionTag = true;
			prepBET = false;
			prepKAF = true;
			prepLAMED = false;
			prepMEM = false;
			prepositionTag = true;

			populatePrefixes(95, prefix, description, definiteArticleTag, defArtHE, relHE, adverbKAF, subConOrRelSHIN,
					tempSubConKAFSHIN, tempSubConMEMSHIN, tempSubConLAMEDKAFSHIN, relativizerTag, temporalSubConjTag,
					subordinatingConjunctionTag, prefPartUnit, prepBET, prepKAF, prepLAMED, prepMEM, prepositionTag,
					conjunctionTag);

			///////////////////////////////////////////////////////////////
			prefix = "'wekek'";
			description = "'[+conjunction]" + URLEncoder.encode("ו", "UTF-8") + "[+relativizer/subordinatingConjunction]"
					+ URLEncoder.encode("ש", "UTF-8") + "[+temporalSubConj]" + URLEncoder.encode("כש", "UTF-8")
					+ "[+preposition]" + URLEncoder.encode("כ", "UTF-8") + "[+definiteArticle]" + "'";
			definiteArticleTag = true;
			defArtHE = false;
			relHE = false;
			/////////////////////
			adverbKAF = false;
			subConOrRelSHIN = true;
			tempSubConKAFSHIN = true;
			tempSubConMEMSHIN = false;
			tempSubConLAMEDKAFSHIN = false;
			relativizerTag = true;
			temporalSubConjTag = true;
			subordinatingConjunctionTag = false;
			prefPartUnit = true;
			conjunctionTag = true;
			prepBET = false;
			prepKAF = true;
			prepLAMED = false;
			prepMEM = false;
			prepositionTag = true;

			populatePrefixes(96, prefix, description, definiteArticleTag, defArtHE, relHE, adverbKAF, subConOrRelSHIN,
					tempSubConKAFSHIN, tempSubConMEMSHIN, tempSubConLAMEDKAFSHIN, relativizerTag, temporalSubConjTag,
					subordinatingConjunctionTag, prefPartUnit, prepBET, prepKAF, prepLAMED, prepMEM, prepositionTag,
					conjunctionTag);

			//////////////////////////////////////////////////////////////
			prefix = "'mek'";
			description = "'[+temporalSubConj]" + URLEncoder.encode("מש", "UTF-8") + "[+preposition]"
					+ URLEncoder.encode("כ", "UTF-8") + "'";
			definiteArticleTag = false;
			defArtHE = false;
			relHE = false;
			/////////////////////
			adverbKAF = false;
			subConOrRelSHIN = false;
			tempSubConKAFSHIN = false;
			tempSubConMEMSHIN = true;
			tempSubConLAMEDKAFSHIN = false;
			relativizerTag = false;
			temporalSubConjTag = true;
			subordinatingConjunctionTag = false;
			prefPartUnit = true;
			conjunctionTag = false;
			prepBET = false;
			prepKAF = true;
			prepLAMED = false;
			prepMEM = false;
			prepositionTag = true;

			populatePrefixes(97, prefix, description, definiteArticleTag, defArtHE, relHE, adverbKAF, subConOrRelSHIN,
					tempSubConKAFSHIN, tempSubConMEMSHIN, tempSubConLAMEDKAFSHIN, relativizerTag, temporalSubConjTag,
					subordinatingConjunctionTag, prefPartUnit, prepBET, prepKAF, prepLAMED, prepMEM, prepositionTag,
					conjunctionTag);

			///////////////////////////////////////////////////////////////
			prefix = "'mek'";
			description = "'[+temporalSubConj]" + URLEncoder.encode("מש", "UTF-8") + "[+preposition]"
					+ URLEncoder.encode("כ", "UTF-8") + "[+definiteArticle]" + "'";
			definiteArticleTag = true;
			defArtHE = false;
			relHE = false;
			/////////////////
			adverbKAF = false;
			subConOrRelSHIN = false;
			tempSubConKAFSHIN = false;
			tempSubConMEMSHIN = true;
			tempSubConLAMEDKAFSHIN = false;
			relativizerTag = false;
			temporalSubConjTag = true;
			subordinatingConjunctionTag = false;
			prefPartUnit = true;
			conjunctionTag = false;
			prepBET = false;
			prepKAF = true;
			prepLAMED = false;
			prepMEM = false;
			prepositionTag = true;

			populatePrefixes(98, prefix, description, definiteArticleTag, defArtHE, relHE, adverbKAF, subConOrRelSHIN,
					tempSubConKAFSHIN, tempSubConMEMSHIN, tempSubConLAMEDKAFSHIN, relativizerTag, temporalSubConjTag,
					subordinatingConjunctionTag, prefPartUnit, prepBET, prepKAF, prepLAMED, prepMEM, prepositionTag,
					conjunctionTag);

			//////////////////////////////////////////////////////////////
			prefix = "'wmek'";
			description = "'[+conjunction]" + URLEncoder.encode("ו", "UTF-8") + "[+temporalSubConj]"
					+ URLEncoder.encode("מש", "UTF-8") + "[+preposition]" + URLEncoder.encode("כ", "UTF-8") + "'";
			definiteArticleTag = false;
			defArtHE = false;
			relHE = false;
			/////////////////////
			adverbKAF = false;
			subConOrRelSHIN = false;
			tempSubConKAFSHIN = false;
			tempSubConMEMSHIN = true;
			tempSubConLAMEDKAFSHIN = false;
			relativizerTag = false;
			temporalSubConjTag = true;
			subordinatingConjunctionTag = false;
			prefPartUnit = true;
			conjunctionTag = true;
			prepBET = false;
			prepKAF = true;
			prepLAMED = false;
			prepMEM = false;
			prepositionTag = true;

			populatePrefixes(99, prefix, description, definiteArticleTag, defArtHE, relHE, adverbKAF, subConOrRelSHIN,
					tempSubConKAFSHIN, tempSubConMEMSHIN, tempSubConLAMEDKAFSHIN, relativizerTag, temporalSubConjTag,
					subordinatingConjunctionTag, prefPartUnit, prepBET, prepKAF, prepLAMED, prepMEM, prepositionTag,
					conjunctionTag);

			///////////////////////////////////////////////////////////////
			prefix = "'wmek'";
			description = "'[+conjunction]" + URLEncoder.encode("ו", "UTF-8") + "[+temporalSubConj]"
					+ URLEncoder.encode("מש", "UTF-8") + "[+preposition]" + URLEncoder.encode("כ", "UTF-8")
					+ "[+definiteArticle]" + "'";
			definiteArticleTag = true;
			defArtHE = false;
			relHE = false;
			///////////////////////
			adverbKAF = false;
			subConOrRelSHIN = false;
			tempSubConKAFSHIN = false;
			tempSubConMEMSHIN = true;
			tempSubConLAMEDKAFSHIN = false;
			relativizerTag = false;
			temporalSubConjTag = true;
			subordinatingConjunctionTag = false;
			prefPartUnit = true;
			conjunctionTag = true;
			prepBET = false;
			prepKAF = true;
			prepLAMED = false;
			prepMEM = false;
			prepositionTag = true;

			populatePrefixes(100, prefix, description, definiteArticleTag, defArtHE, relHE, adverbKAF, subConOrRelSHIN,
					tempSubConKAFSHIN, tempSubConMEMSHIN, tempSubConLAMEDKAFSHIN, relativizerTag, temporalSubConjTag,
					subordinatingConjunctionTag, prefPartUnit, prepBET, prepKAF, prepLAMED, prepMEM, prepositionTag,
					conjunctionTag);

			//////////////////////////////////////////////////////////////
			prefix = "'emek'";
			description = "'[+relativizer/subordinatingConjunction]" + URLEncoder.encode("ש", "UTF-8")
					+ "[+temporalSubConj]" + URLEncoder.encode("מש", "UTF-8") + "[+preposition]"
					+ URLEncoder.encode("כ", "UTF-8") + "'";
			definiteArticleTag = false;
			defArtHE = false;
			relHE = false;
			///////////////////////
			adverbKAF = false;
			subConOrRelSHIN = true;
			tempSubConKAFSHIN = false;
			tempSubConMEMSHIN = true;
			tempSubConLAMEDKAFSHIN = false;
			relativizerTag = true;
			temporalSubConjTag = false;
			subordinatingConjunctionTag = false;
			prefPartUnit = true;
			conjunctionTag = false;
			prepBET = false;
			prepKAF = true;
			prepLAMED = false;
			prepMEM = false;
			prepositionTag = true;

			populatePrefixes(101, prefix, description, definiteArticleTag, defArtHE, relHE, adverbKAF, subConOrRelSHIN,
					tempSubConKAFSHIN, tempSubConMEMSHIN, tempSubConLAMEDKAFSHIN, relativizerTag, temporalSubConjTag,
					subordinatingConjunctionTag, prefPartUnit, prepBET, prepKAF, prepLAMED, prepMEM, prepositionTag,
					conjunctionTag);

			///////////////////////////////////////////////////////////////
			prefix = "'emek'";
			description = "'[+relativizer/subordinatingConjunction]" + URLEncoder.encode("ש", "UTF-8")
					+ "[+temporalSubConj]" + URLEncoder.encode("מש", "UTF-8") + "[+preposition]"
					+ URLEncoder.encode("כ", "UTF-8") + "[+definiteArticle]" + "'";
			definiteArticleTag = true;
			defArtHE = false;
			relHE = false;
			/////////////////////
			adverbKAF = false;
			subConOrRelSHIN = true;
			tempSubConKAFSHIN = false;
			tempSubConMEMSHIN = true;
			tempSubConLAMEDKAFSHIN = false;
			relativizerTag = true;
			temporalSubConjTag = true;
			subordinatingConjunctionTag = false;
			prefPartUnit = true;
			conjunctionTag = false;
			prepBET = false;
			prepKAF = true;
			prepLAMED = false;
			prepMEM = false;
			prepositionTag = true;

			populatePrefixes(102, prefix, description, definiteArticleTag, defArtHE, relHE, adverbKAF, subConOrRelSHIN,
					tempSubConKAFSHIN, tempSubConMEMSHIN, tempSubConLAMEDKAFSHIN, relativizerTag, temporalSubConjTag,
					subordinatingConjunctionTag, prefPartUnit, prepBET, prepKAF, prepLAMED, prepMEM, prepositionTag,
					conjunctionTag);

			//////////////////////////////////////////////////////////////
			prefix = "'wemek'";
			description = "'[+conjunction]" + URLEncoder.encode("ו", "UTF-8") + "[+relativizer/subordinatingConjunction]"
					+ URLEncoder.encode("ש", "UTF-8") + "[+temporalSubConj]" + URLEncoder.encode("מש", "UTF-8")
					+ "[+preposition]" + URLEncoder.encode("כ", "UTF-8") + "'";
			definiteArticleTag = false;
			defArtHE = false;
			relHE = false;
			////////////////////////
			adverbKAF = false;
			subConOrRelSHIN = true;
			tempSubConKAFSHIN = false;
			tempSubConMEMSHIN = true;
			tempSubConLAMEDKAFSHIN = false;
			relativizerTag = true;
			temporalSubConjTag = true;
			subordinatingConjunctionTag = false;
			prefPartUnit = true;
			conjunctionTag = true;
			prepBET = false;
			prepKAF = true;
			prepLAMED = false;
			prepMEM = false;
			prepositionTag = true;

			populatePrefixes(103, prefix, description, definiteArticleTag, defArtHE, relHE, adverbKAF, subConOrRelSHIN,
					tempSubConKAFSHIN, tempSubConMEMSHIN, tempSubConLAMEDKAFSHIN, relativizerTag, temporalSubConjTag,
					subordinatingConjunctionTag, prefPartUnit, prepBET, prepKAF, prepLAMED, prepMEM, prepositionTag,
					conjunctionTag);

			///////////////////////////////////////////////////////////////
			prefix = "'wemek'";
			description = "'[+conjunction]" + URLEncoder.encode("ו", "UTF-8") + "[+relativizer/subordinatingConjunction]"
					+ URLEncoder.encode("ש", "UTF-8") + "[+temporalSubConj]" + URLEncoder.encode("מש", "UTF-8")
					+ "[+preposition]" + URLEncoder.encode("כ", "UTF-8") + "[+definiteArticle]" + "'";
			definiteArticleTag = true;
			defArtHE = false;
			relHE = false;
			////////////////////////
			adverbKAF = false;
			subConOrRelSHIN = true;
			tempSubConKAFSHIN = false;
			tempSubConMEMSHIN = true;
			tempSubConLAMEDKAFSHIN = false;
			relativizerTag = true;
			temporalSubConjTag = true;
			subordinatingConjunctionTag = false;
			prefPartUnit = true;
			conjunctionTag = true;
			prepBET = false;
			prepKAF = true;
			prepLAMED = false;
			prepMEM = false;
			prepositionTag = true;

			populatePrefixes(104, prefix, description, definiteArticleTag, defArtHE, relHE, adverbKAF, subConOrRelSHIN,
					tempSubConKAFSHIN, tempSubConMEMSHIN, tempSubConLAMEDKAFSHIN, relativizerTag, temporalSubConjTag,
					subordinatingConjunctionTag, prefPartUnit, prepBET, prepKAF, prepLAMED, prepMEM, prepositionTag,
					conjunctionTag);

			//////////////////////////////////////////////////////////////
			prefix = "'lkek'";
			description = "'[+temporalSubConj]" + URLEncoder.encode("לכש", "UTF-8") + "[+preposition]"
					+ URLEncoder.encode("כ", "UTF-8") + "'";
			definiteArticleTag = false;
			defArtHE = false;
			relHE = false;
			//////////////////////
			adverbKAF = false;
			subConOrRelSHIN = false;
			tempSubConKAFSHIN = false;
			tempSubConMEMSHIN = false;
			tempSubConLAMEDKAFSHIN = true;
			relativizerTag = false;
			temporalSubConjTag = true;
			subordinatingConjunctionTag = false;
			prefPartUnit = true;
			conjunctionTag = false;
			prepBET = false;
			prepKAF = true;
			prepLAMED = false;
			prepMEM = false;
			prepositionTag = true;

			populatePrefixes(105, prefix, description, definiteArticleTag, defArtHE, relHE, adverbKAF, subConOrRelSHIN,
					tempSubConKAFSHIN, tempSubConMEMSHIN, tempSubConLAMEDKAFSHIN, relativizerTag, temporalSubConjTag,
					subordinatingConjunctionTag, prefPartUnit, prepBET, prepKAF, prepLAMED, prepMEM, prepositionTag,
					conjunctionTag);

			///////////////////////////////////////////////////////////////
			prefix = "'lkek'";
			description = "'[+temporalSubConj]" + URLEncoder.encode("לכש", "UTF-8") + "[+preposition]"
					+ URLEncoder.encode("כ", "UTF-8") + "[+definiteArticle]" + "'";
			definiteArticleTag = true;
			defArtHE = false;
			relHE = false;
			//////////////////////
			adverbKAF = false;
			subConOrRelSHIN = false;
			tempSubConKAFSHIN = false;
			tempSubConMEMSHIN = false;
			tempSubConLAMEDKAFSHIN = true;
			relativizerTag = false;
			temporalSubConjTag = true;
			subordinatingConjunctionTag = false;
			prefPartUnit = true;
			conjunctionTag = false;
			prepBET = false;
			prepKAF = true;
			prepLAMED = false;
			prepMEM = false;
			prepositionTag = true;

			populatePrefixes(106, prefix, description, definiteArticleTag, defArtHE, relHE, adverbKAF, subConOrRelSHIN,
					tempSubConKAFSHIN, tempSubConMEMSHIN, tempSubConLAMEDKAFSHIN, relativizerTag, temporalSubConjTag,
					subordinatingConjunctionTag, prefPartUnit, prepBET, prepKAF, prepLAMED, prepMEM, prepositionTag,
					conjunctionTag);

			//////////////////////////////////////////////////////////////
			prefix = "'wlkek'";
			description = "'[+conjunction]" + URLEncoder.encode("ו", "UTF-8") + "[+temporalSubConj]"
					+ URLEncoder.encode("לכש", "UTF-8") + "[+preposition]" + URLEncoder.encode("כ", "UTF-8") + "'";
			definiteArticleTag = false;
			defArtHE = false;
			relHE = false;
			///////////////////////
			adverbKAF = false;
			subConOrRelSHIN = false;
			tempSubConKAFSHIN = false;
			tempSubConMEMSHIN = false;
			tempSubConLAMEDKAFSHIN = true;
			relativizerTag = false;
			temporalSubConjTag = true;
			subordinatingConjunctionTag = false;
			prefPartUnit = true;
			conjunctionTag = true;
			prepBET = false;
			prepKAF = true;
			prepLAMED = false;
			prepMEM = false;
			prepositionTag = true;

			populatePrefixes(107, prefix, description, definiteArticleTag, defArtHE, relHE, adverbKAF, subConOrRelSHIN,
					tempSubConKAFSHIN, tempSubConMEMSHIN, tempSubConLAMEDKAFSHIN, relativizerTag, temporalSubConjTag,
					subordinatingConjunctionTag, prefPartUnit, prepBET, prepKAF, prepLAMED, prepMEM, prepositionTag,
					conjunctionTag);

			///////////////////////////////////////////////////////////////
			prefix = "'wlkek'";
			description = "'[+conjunction]" + URLEncoder.encode("ו", "UTF-8") + "[+temporalSubConj]"
					+ URLEncoder.encode("לכש", "UTF-8") + "[+preposition]" + URLEncoder.encode("כ", "UTF-8")
					+ "[+definiteArticle]" + "'";
			definiteArticleTag = true;
			defArtHE = false;
			relHE = false;
			///////////////////////
			adverbKAF = false;
			subConOrRelSHIN = false;
			tempSubConKAFSHIN = false;
			tempSubConMEMSHIN = false;
			tempSubConLAMEDKAFSHIN = true;
			relativizerTag = false;
			temporalSubConjTag = true;
			subordinatingConjunctionTag = false;
			prefPartUnit = true;
			conjunctionTag = true;
			prepBET = false;
			prepKAF = true;
			prepLAMED = false;
			prepMEM = false;
			prepositionTag = true;

			populatePrefixes(108, prefix, description, definiteArticleTag, defArtHE, relHE, adverbKAF, subConOrRelSHIN,
					tempSubConKAFSHIN, tempSubConMEMSHIN, tempSubConLAMEDKAFSHIN, relativizerTag, temporalSubConjTag,
					subordinatingConjunctionTag, prefPartUnit, prepBET, prepKAF, prepLAMED, prepMEM, prepositionTag,
					conjunctionTag);

			//////////////////////////////////////////////////////////////
			prefix = "'elkek'";
			description = "'[+relativizer/subordinatingConjunction]" + URLEncoder.encode("ש", "UTF-8")
					+ "[+temporalSubConj]" + URLEncoder.encode("לכש", "UTF-8") + "[+preposition]"
					+ URLEncoder.encode("כ", "UTF-8") + "'";
			definiteArticleTag = false;
			defArtHE = false;
			relHE = false;
			////////////////////////
			adverbKAF = false;
			subConOrRelSHIN = true;
			tempSubConKAFSHIN = false;
			tempSubConMEMSHIN = false;
			tempSubConLAMEDKAFSHIN = true;
			relativizerTag = true;
			temporalSubConjTag = true;
			subordinatingConjunctionTag = false;
			prefPartUnit = true;
			conjunctionTag = false;
			prepBET = false;
			prepKAF = true;
			prepLAMED = false;
			prepMEM = false;
			prepositionTag = true;

			populatePrefixes(109, prefix, description, definiteArticleTag, defArtHE, relHE, adverbKAF, subConOrRelSHIN,
					tempSubConKAFSHIN, tempSubConMEMSHIN, tempSubConLAMEDKAFSHIN, relativizerTag, temporalSubConjTag,
					subordinatingConjunctionTag, prefPartUnit, prepBET, prepKAF, prepLAMED, prepMEM, prepositionTag,
					conjunctionTag);

			///////////////////////////////////////////////////////////////
			prefix = "'elkek'";
			description = "'[+relativizer/subordinatingConjunction]" + URLEncoder.encode("ש", "UTF-8")
					+ "[+temporalSubConj]" + URLEncoder.encode("לכש", "UTF-8") + "[+preposition]"
					+ URLEncoder.encode("כ", "UTF-8") + "[+definiteArticle]" + "'";
			definiteArticleTag = true;
			defArtHE = false;
			relHE = false;
			////////////////////
			adverbKAF = false;
			subConOrRelSHIN = true;
			tempSubConKAFSHIN = false;
			tempSubConMEMSHIN = false;
			tempSubConLAMEDKAFSHIN = true;
			relativizerTag = true;
			temporalSubConjTag = true;
			subordinatingConjunctionTag = false;
			prefPartUnit = true;
			conjunctionTag = false;
			prepBET = false;
			prepKAF = true;
			prepLAMED = false;
			prepMEM = false;
			prepositionTag = true;

			populatePrefixes(110, prefix, description, definiteArticleTag, defArtHE, relHE, adverbKAF, subConOrRelSHIN,
					tempSubConKAFSHIN, tempSubConMEMSHIN, tempSubConLAMEDKAFSHIN, relativizerTag, temporalSubConjTag,
					subordinatingConjunctionTag, prefPartUnit, prepBET, prepKAF, prepLAMED, prepMEM, prepositionTag,
					conjunctionTag);

			//////////////////////////////////////////////////////////////
			prefix = "'welkek'";
			description = "'[+conjunction]" + URLEncoder.encode("ו", "UTF-8") + "[+relativizer/subordinatingConjunction]"
					+ URLEncoder.encode("ש", "UTF-8") + "[+temporalSubConj]" + URLEncoder.encode("לכש", "UTF-8")
					+ "[+preposition]" + URLEncoder.encode("כ", "UTF-8") + "'";
			definiteArticleTag = false;
			defArtHE = false;
			relHE = false;
			////////////////
			adverbKAF = false;
			subConOrRelSHIN = true;
			tempSubConKAFSHIN = false;
			tempSubConMEMSHIN = false;
			tempSubConLAMEDKAFSHIN = true;
			relativizerTag = true;
			temporalSubConjTag = true;
			subordinatingConjunctionTag = false;
			prefPartUnit = true;
			conjunctionTag = true;
			prepBET = false;
			prepKAF = true;
			prepLAMED = false;
			prepMEM = false;
			prepositionTag = true;

			populatePrefixes(111, prefix, description, definiteArticleTag, defArtHE, relHE, adverbKAF, subConOrRelSHIN,
					tempSubConKAFSHIN, tempSubConMEMSHIN, tempSubConLAMEDKAFSHIN, relativizerTag, temporalSubConjTag,
					subordinatingConjunctionTag, prefPartUnit, prepBET, prepKAF, prepLAMED, prepMEM, prepositionTag,
					conjunctionTag);

			///////////////////////////////////////////////////////////////
			prefix = "'welkek'";
			description = "'[+conjunction]" + URLEncoder.encode("ו", "UTF-8") + "[+relativizer/subordinatingConjunction]"
					+ URLEncoder.encode("ש", "UTF-8") + "[+temporalSubConj]" + URLEncoder.encode("לכש", "UTF-8")
					+ "[+preposition]" + URLEncoder.encode("כ", "UTF-8") + "[+definiteArticle]" + "'";
			definiteArticleTag = true;
			defArtHE = false;
			relHE = false;
			//////////////////////////////
			adverbKAF = false;
			subConOrRelSHIN = true;
			tempSubConKAFSHIN = false;
			tempSubConMEMSHIN = false;
			tempSubConLAMEDKAFSHIN = true;
			relativizerTag = true;
			temporalSubConjTag = true;
			subordinatingConjunctionTag = false;
			prefPartUnit = true;
			conjunctionTag = true;
			prepBET = false;
			prepKAF = true;
			prepLAMED = false;
			prepMEM = false;
			prepositionTag = true;

			populatePrefixes(112, prefix, description, definiteArticleTag, defArtHE, relHE, adverbKAF, subConOrRelSHIN,
					tempSubConKAFSHIN, tempSubConMEMSHIN, tempSubConLAMEDKAFSHIN, relativizerTag, temporalSubConjTag,
					subordinatingConjunctionTag, prefPartUnit, prepBET, prepKAF, prepLAMED, prepMEM, prepositionTag,
					conjunctionTag);

			///////////////////////////////////////////////////////////////
			prefix = "'m'";
			description = "'[+preposition]" + URLEncoder.encode("מ", "UTF-8") + "'";
			definiteArticleTag = false;
			defArtHE = false;
			relHE = false;
			///////////////////////////
			adverbKAF = false;
			subConOrRelSHIN = false;
			tempSubConKAFSHIN = false;
			tempSubConMEMSHIN = false;
			tempSubConLAMEDKAFSHIN = false;
			relativizerTag = false;
			temporalSubConjTag = false;
			subordinatingConjunctionTag = false;
			prefPartUnit = true;
			conjunctionTag = false;
			prepBET = false;
			prepKAF = false;
			prepLAMED = false;
			prepMEM = true;
			prepositionTag = true;

			populatePrefixes(113, prefix, description, definiteArticleTag, defArtHE, relHE, adverbKAF, subConOrRelSHIN,
					tempSubConKAFSHIN, tempSubConMEMSHIN, tempSubConLAMEDKAFSHIN, relativizerTag, temporalSubConjTag,
					subordinatingConjunctionTag, prefPartUnit, prepBET, prepKAF, prepLAMED, prepMEM, prepositionTag,
					conjunctionTag);

			///////////////////////////////////////////////////////////////
			prefix = "'wm'";
			description = "'[+conjunction]" + URLEncoder.encode("ו", "UTF-8") + "[+preposition]"
					+ URLEncoder.encode("מ", "UTF-8") + "'";
			definiteArticleTag = false;
			defArtHE = false;
			relHE = false;
			adverbKAF = false;
			subConOrRelSHIN = false;
			tempSubConKAFSHIN = false;
			tempSubConMEMSHIN = false;
			tempSubConLAMEDKAFSHIN = false;
			relativizerTag = false;
			temporalSubConjTag = false;
			subordinatingConjunctionTag = false;
			prefPartUnit = true;
			conjunctionTag = true;
			prepBET = false;
			prepKAF = false;
			prepLAMED = false;
			prepMEM = true;
			prepositionTag = true;

			populatePrefixes(115, prefix, description, definiteArticleTag, defArtHE, relHE, adverbKAF, subConOrRelSHIN,
					tempSubConKAFSHIN, tempSubConMEMSHIN, tempSubConLAMEDKAFSHIN, relativizerTag, temporalSubConjTag,
					subordinatingConjunctionTag, prefPartUnit, prepBET, prepKAF, prepLAMED, prepMEM, prepositionTag,
					conjunctionTag);

			//////////////////////////////////////////////////////////////
			prefix = "'em'";
			description = "'[+relativizer/subordinatingConjunction]" + URLEncoder.encode("ש", "UTF-8") + "[+preposition]"
					+ URLEncoder.encode("מ", "UTF-8") + "'";
			definiteArticleTag = false;
			defArtHE = false;
			relHE = false;
			//////////////////////
			adverbKAF = false;
			subConOrRelSHIN = true;
			tempSubConKAFSHIN = false;
			tempSubConMEMSHIN = false;
			tempSubConLAMEDKAFSHIN = false;
			relativizerTag = true;
			temporalSubConjTag = false;
			subordinatingConjunctionTag = false;
			prefPartUnit = true;
			conjunctionTag = false;
			prepBET = false;
			prepKAF = false;
			prepLAMED = false;
			prepMEM = true;
			prepositionTag = true;

			populatePrefixes(116, prefix, description, definiteArticleTag, defArtHE, relHE, adverbKAF, subConOrRelSHIN,
					tempSubConKAFSHIN, tempSubConMEMSHIN, tempSubConLAMEDKAFSHIN, relativizerTag, temporalSubConjTag,
					subordinatingConjunctionTag, prefPartUnit, prepBET, prepKAF, prepLAMED, prepMEM, prepositionTag,
					conjunctionTag);

			///////////////////////////////////////////////////////////////
			prefix = "'wem'";
			description = "'[+conjunction]" + URLEncoder.encode("ו", "UTF-8") + "[+relativizer/subordinatingConjunction]"
					+ URLEncoder.encode("ש", "UTF-8") + "[+preposition]" + URLEncoder.encode("מ", "UTF-8") + "'";
			definiteArticleTag = false;
			defArtHE = false;
			relHE = false;
			//////////////////////
			adverbKAF = false;
			subConOrRelSHIN = true;
			tempSubConKAFSHIN = false;
			tempSubConMEMSHIN = false;
			tempSubConLAMEDKAFSHIN = false;
			relativizerTag = true;
			temporalSubConjTag = false;
			subordinatingConjunctionTag = false;
			prefPartUnit = true;
			conjunctionTag = true;
			prepBET = false;
			prepKAF = false;
			prepLAMED = false;
			prepMEM = true;
			prepositionTag = true;

			populatePrefixes(117, prefix, description, definiteArticleTag, defArtHE, relHE, adverbKAF, subConOrRelSHIN,
					tempSubConKAFSHIN, tempSubConMEMSHIN, tempSubConLAMEDKAFSHIN, relativizerTag, temporalSubConjTag,
					subordinatingConjunctionTag, prefPartUnit, prepBET, prepKAF, prepLAMED, prepMEM, prepositionTag,
					conjunctionTag);

			//////////////////////////////////////////////////////////////
			prefix = "'kem'";
			description = "'[+temporalSubConj]" + URLEncoder.encode("כש", "UTF-8") + "[+preposition]"
					+ URLEncoder.encode("מ", "UTF-8") + "'";
			definiteArticleTag = false;
			defArtHE = false;
			relHE = false;
			///////////////////////
			adverbKAF = false;
			subConOrRelSHIN = false;
			tempSubConKAFSHIN = true;
			tempSubConMEMSHIN = false;
			tempSubConLAMEDKAFSHIN = false;
			relativizerTag = false;
			temporalSubConjTag = true;
			subordinatingConjunctionTag = false;
			prefPartUnit = true;
			conjunctionTag = false;
			prepBET = false;
			prepKAF = false;
			prepLAMED = false;
			prepMEM = true;
			prepositionTag = true;

			populatePrefixes(118, prefix, description, definiteArticleTag, defArtHE, relHE, adverbKAF, subConOrRelSHIN,
					tempSubConKAFSHIN, tempSubConMEMSHIN, tempSubConLAMEDKAFSHIN, relativizerTag, temporalSubConjTag,
					subordinatingConjunctionTag, prefPartUnit, prepBET, prepKAF, prepLAMED, prepMEM, prepositionTag,
					conjunctionTag);

			//////////////////////////////////////////////////////////////
			prefix = "'wkem'";
			description = "'[+conjunction]" + URLEncoder.encode("ו", "UTF-8") + "[+temporalSubConj]"
					+ URLEncoder.encode("כש", "UTF-8") + "[+preposition]" + URLEncoder.encode("מ", "UTF-8") + "'";
			definiteArticleTag = false;
			defArtHE = false;
			relHE = false;
			//////////////////////////////
			adverbKAF = false;
			subConOrRelSHIN = false;
			tempSubConKAFSHIN = true;
			tempSubConMEMSHIN = false;
			tempSubConLAMEDKAFSHIN = false;
			relativizerTag = false;
			temporalSubConjTag = true;
			subordinatingConjunctionTag = false;
			prefPartUnit = true;
			conjunctionTag = true;
			prepBET = false;
			prepKAF = false;
			prepLAMED = false;
			prepMEM = true;
			prepositionTag = true;

			populatePrefixes(119, prefix, description, definiteArticleTag, defArtHE, relHE, adverbKAF, subConOrRelSHIN,
					tempSubConKAFSHIN, tempSubConMEMSHIN, tempSubConLAMEDKAFSHIN, relativizerTag, temporalSubConjTag,
					subordinatingConjunctionTag, prefPartUnit, prepBET, prepKAF, prepLAMED, prepMEM, prepositionTag,
					conjunctionTag);

			///////////////////////////////////////////////////////////////

			prefix = "'ekem'";
			description = "'[+relativizer/subordinatingConjunction]" + URLEncoder.encode("ש", "UTF-8")
					+ "[+temporalSubConj]" + URLEncoder.encode("בכש", "UTF-8") + "[+preposition]"
					+ URLEncoder.encode("מ", "UTF-8") + "'";
			definiteArticleTag = false;
			defArtHE = false;
			relHE = false;
			/////////////////////////////
			adverbKAF = false;
			subConOrRelSHIN = true;
			tempSubConKAFSHIN = true;
			tempSubConMEMSHIN = false;
			tempSubConLAMEDKAFSHIN = false;
			relativizerTag = true;
			temporalSubConjTag = true;
			subordinatingConjunctionTag = false;
			prefPartUnit = true;
			conjunctionTag = false;
			prepBET = false;
			prepKAF = false;
			prepLAMED = false;
			prepMEM = true;
			prepositionTag = true;

			populatePrefixes(120, prefix, description, definiteArticleTag, defArtHE, relHE, adverbKAF, subConOrRelSHIN,
					tempSubConKAFSHIN, tempSubConMEMSHIN, tempSubConLAMEDKAFSHIN, relativizerTag, temporalSubConjTag,
					subordinatingConjunctionTag, prefPartUnit, prepBET, prepKAF, prepLAMED, prepMEM, prepositionTag,
					conjunctionTag);

			///////////////////////////////////////////////////////////////
			prefix = "'wekem'";
			description = "'[+conjunction]" + URLEncoder.encode("ו", "UTF-8") + "[+relativizer/subordinatingConjunction]"
					+ URLEncoder.encode("ש", "UTF-8") + "[+temporalSubConj]" + URLEncoder.encode("כש", "UTF-8")
					+ "[+preposition]" + URLEncoder.encode("מ", "UTF-8") + "'";
			definiteArticleTag = false;
			defArtHE = false;
			relHE = false;
			/////////////////////
			adverbKAF = false;
			subConOrRelSHIN = true;
			tempSubConKAFSHIN = true;
			tempSubConMEMSHIN = false;
			tempSubConLAMEDKAFSHIN = false;
			relativizerTag = true;
			temporalSubConjTag = true;
			subordinatingConjunctionTag = false;
			prefPartUnit = true;
			conjunctionTag = true;
			prepBET = false;
			prepKAF = false;
			prepLAMED = false;
			prepMEM = true;
			prepositionTag = true;

			populatePrefixes(121, prefix, description, definiteArticleTag, defArtHE, relHE, adverbKAF, subConOrRelSHIN,
					tempSubConKAFSHIN, tempSubConMEMSHIN, tempSubConLAMEDKAFSHIN, relativizerTag, temporalSubConjTag,
					subordinatingConjunctionTag, prefPartUnit, prepBET, prepKAF, prepLAMED, prepMEM, prepositionTag,
					conjunctionTag);

			///////////////////////////////////////////////////////////////

			prefix = "'mem'";
			description = "'[+temporalSubConj]" + URLEncoder.encode("מש", "UTF-8") + "[+preposition]"
					+ URLEncoder.encode("מ", "UTF-8") + "'";
			definiteArticleTag = false;
			defArtHE = false;
			relHE = false;
			////////////////////////
			adverbKAF = false;
			subConOrRelSHIN = false;
			tempSubConKAFSHIN = false;
			tempSubConMEMSHIN = true;
			tempSubConLAMEDKAFSHIN = false;
			relativizerTag = false;
			temporalSubConjTag = true;
			subordinatingConjunctionTag = false;
			prefPartUnit = true;
			conjunctionTag = false;
			prepBET = false;
			prepKAF = false;
			prepLAMED = false;
			prepMEM = true;
			prepositionTag = true;

			populatePrefixes(122, prefix, description, definiteArticleTag, defArtHE, relHE, adverbKAF, subConOrRelSHIN,
					tempSubConKAFSHIN, tempSubConMEMSHIN, tempSubConLAMEDKAFSHIN, relativizerTag, temporalSubConjTag,
					subordinatingConjunctionTag, prefPartUnit, prepBET, prepKAF, prepLAMED, prepMEM, prepositionTag,
					conjunctionTag);

			///////////////////////////////////////////////////////////////
			prefix = "'wmem'";
			description = "'[+conjunction]" + URLEncoder.encode("ו", "UTF-8") + "[+temporalSubConj]"
					+ URLEncoder.encode("מש", "UTF-8") + "[+preposition]" + URLEncoder.encode("מ", "UTF-8") + "'";
			definiteArticleTag = false;
			defArtHE = false;
			relHE = false;
			////////////////////////
			adverbKAF = false;
			subConOrRelSHIN = false;
			tempSubConKAFSHIN = false;
			tempSubConMEMSHIN = true;
			tempSubConLAMEDKAFSHIN = false;
			relativizerTag = false;
			temporalSubConjTag = true;
			subordinatingConjunctionTag = false;
			prefPartUnit = true;
			conjunctionTag = true;
			prepBET = false;
			prepKAF = false;
			prepLAMED = false;
			prepMEM = true;
			prepositionTag = true;

			populatePrefixes(123, prefix, description, definiteArticleTag, defArtHE, relHE, adverbKAF, subConOrRelSHIN,
					tempSubConKAFSHIN, tempSubConMEMSHIN, tempSubConLAMEDKAFSHIN, relativizerTag, temporalSubConjTag,
					subordinatingConjunctionTag, prefPartUnit, prepBET, prepKAF, prepLAMED, prepMEM, prepositionTag,
					conjunctionTag);

			///////////////////////////////////////////////////////////////

			prefix = "'emem'";
			description = "'[+relativizer/subordinatingConjunction]" + URLEncoder.encode("ש", "UTF-8")
					+ "[+temporalSubConj]" + URLEncoder.encode("מש", "UTF-8") + "[+preposition]"
					+ URLEncoder.encode("מ", "UTF-8") + "'";
			definiteArticleTag = false;
			defArtHE = false;
			relHE = false;
			////////////////////////////
			adverbKAF = false;
			subConOrRelSHIN = true;
			tempSubConKAFSHIN = false;
			tempSubConMEMSHIN = true;
			tempSubConLAMEDKAFSHIN = false;
			relativizerTag = true;
			temporalSubConjTag = true;
			subordinatingConjunctionTag = false;
			prefPartUnit = true;
			conjunctionTag = false;
			prepBET = false;
			prepKAF = false;
			prepLAMED = false;
			prepMEM = true;
			prepositionTag = true;

			populatePrefixes(124, prefix, description, definiteArticleTag, defArtHE, relHE, adverbKAF, subConOrRelSHIN,
					tempSubConKAFSHIN, tempSubConMEMSHIN, tempSubConLAMEDKAFSHIN, relativizerTag, temporalSubConjTag,
					subordinatingConjunctionTag, prefPartUnit, prepBET, prepKAF, prepLAMED, prepMEM, prepositionTag,
					conjunctionTag);

			///////////////////////////////////////////////////////////////
			prefix = "'wemem'";
			description = "'[+conjunction]" + URLEncoder.encode("ב", "UTF-8") + "[+relativizer/subordinatingConjunction]"
					+ URLEncoder.encode("ש", "UTF-8") + "[+temporalSubConj]" + URLEncoder.encode("מש", "UTF-8")
					+ "[+preposition]" + URLEncoder.encode("מ", "UTF-8") + "'";
			definiteArticleTag = false;
			defArtHE = false;
			relHE = false;
			/////////////////////
			adverbKAF = false;
			subConOrRelSHIN = true;
			tempSubConKAFSHIN = false;
			tempSubConMEMSHIN = true;
			tempSubConLAMEDKAFSHIN = false;
			relativizerTag = true;
			temporalSubConjTag = true;
			subordinatingConjunctionTag = false;
			prefPartUnit = true;
			conjunctionTag = true;
			prepBET = false;
			prepKAF = false;
			prepLAMED = false;
			prepMEM = true;
			prepositionTag = true;

			populatePrefixes(125, prefix, description, definiteArticleTag, defArtHE, relHE, adverbKAF, subConOrRelSHIN,
					tempSubConKAFSHIN, tempSubConMEMSHIN, tempSubConLAMEDKAFSHIN, relativizerTag, temporalSubConjTag,
					subordinatingConjunctionTag, prefPartUnit, prepBET, prepKAF, prepLAMED, prepMEM, prepositionTag,
					conjunctionTag);

			///////////////////////////////////////////////////////////////
			prefix = "'lkem'";
			description = "'[+temporalSubConj]" + URLEncoder.encode("לכש", "UTF-8") + "[+preposition]"
					+ URLEncoder.encode("מ", "UTF-8") + "'";
			definiteArticleTag = false;
			defArtHE = false;
			relHE = false;
			//////////////////////
			adverbKAF = false;
			subConOrRelSHIN = false;
			tempSubConKAFSHIN = false;
			tempSubConMEMSHIN = false;
			tempSubConLAMEDKAFSHIN = true;
			relativizerTag = false;
			temporalSubConjTag = true;
			subordinatingConjunctionTag = false;
			prefPartUnit = true;
			conjunctionTag = false;
			prepBET = false;
			prepKAF = false;
			prepLAMED = false;
			prepMEM = true;
			prepositionTag = true;

			populatePrefixes(126, prefix, description, definiteArticleTag, defArtHE, relHE, adverbKAF, subConOrRelSHIN,
					tempSubConKAFSHIN, tempSubConMEMSHIN, tempSubConLAMEDKAFSHIN, relativizerTag, temporalSubConjTag,
					subordinatingConjunctionTag, prefPartUnit, prepBET, prepKAF, prepLAMED, prepMEM, prepositionTag,
					conjunctionTag);

			///////////////////////////////////////////////////////////////
			prefix = "'wlkem'";
			description = "'[+conjunction]" + URLEncoder.encode("ו", "UTF-8") + "[+temporalSubConj]"
					+ URLEncoder.encode("לכש", "UTF-8") + "[+preposition]" + URLEncoder.encode("מ", "UTF-8") + "'";
			definiteArticleTag = false;
			defArtHE = false;
			relHE = false;
			////////////////////////
			adverbKAF = false;
			subConOrRelSHIN = false;
			tempSubConKAFSHIN = false;
			tempSubConMEMSHIN = false;
			tempSubConLAMEDKAFSHIN = true;
			relativizerTag = false;
			temporalSubConjTag = true;
			subordinatingConjunctionTag = false;
			prefPartUnit = true;
			conjunctionTag = true;
			prepBET = false;
			prepKAF = false;
			prepLAMED = false;
			prepMEM = true;
			prepositionTag = true;

			populatePrefixes(127, prefix, description, definiteArticleTag, defArtHE, relHE, adverbKAF, subConOrRelSHIN,
					tempSubConKAFSHIN, tempSubConMEMSHIN, tempSubConLAMEDKAFSHIN, relativizerTag, temporalSubConjTag,
					subordinatingConjunctionTag, prefPartUnit, prepBET, prepKAF, prepLAMED, prepMEM, prepositionTag,
					conjunctionTag);

			///////////////////////////////////////////////////////////////
			prefix = "'elkem'";
			description = "'[+relativizer/subordinatingConjunction]" + URLEncoder.encode("ש", "UTF-8")
					+ "[+temporalSubConj]" + URLEncoder.encode("לכש", "UTF-8") + "[+preposition]"
					+ URLEncoder.encode("מ", "UTF-8") + "'";
			definiteArticleTag = false;
			defArtHE = false;
			relHE = false;
			///////////////////////
			adverbKAF = false;
			subConOrRelSHIN = true;
			tempSubConKAFSHIN = false;
			tempSubConMEMSHIN = false;
			tempSubConLAMEDKAFSHIN = true;
			relativizerTag = true;
			temporalSubConjTag = true;
			subordinatingConjunctionTag = false;
			prefPartUnit = true;
			conjunctionTag = false;
			prepBET = false;
			prepKAF = false;
			prepLAMED = false;
			prepMEM = true;
			prepositionTag = true;

			populatePrefixes(128, prefix, description, definiteArticleTag, defArtHE, relHE, adverbKAF, subConOrRelSHIN,
					tempSubConKAFSHIN, tempSubConMEMSHIN, tempSubConLAMEDKAFSHIN, relativizerTag, temporalSubConjTag,
					subordinatingConjunctionTag, prefPartUnit, prepBET, prepKAF, prepLAMED, prepMEM, prepositionTag,
					conjunctionTag);

			///////////////////////////////////////////////////////////////
			prefix = "'welkem'";
			description = "'[+conjunction]" + URLEncoder.encode("ו", "UTF-8") + "[+relativizer/subordinatingConjunction]"
					+ URLEncoder.encode("ש", "UTF-8") + "[+temporalSubConj]" + URLEncoder.encode("לכש", "UTF-8")
					+ "[+preposition]" + URLEncoder.encode("מ", "UTF-8") + "'";
			definiteArticleTag = false;
			defArtHE = false;
			relHE = false;
			////////////////////
			adverbKAF = false;
			subConOrRelSHIN = false;
			tempSubConKAFSHIN = false;
			tempSubConMEMSHIN = false;
			tempSubConLAMEDKAFSHIN = true;
			relativizerTag = true;
			temporalSubConjTag = true;
			subordinatingConjunctionTag = false;
			prefPartUnit = true;
			conjunctionTag = true;
			prepBET = false;
			prepKAF = false;
			prepLAMED = false;
			prepMEM = true;
			prepositionTag = true;

			populatePrefixes(129, prefix, description, definiteArticleTag, defArtHE, relHE, adverbKAF, subConOrRelSHIN,
					tempSubConKAFSHIN, tempSubConMEMSHIN, tempSubConLAMEDKAFSHIN, relativizerTag, temporalSubConjTag,
					subordinatingConjunctionTag, prefPartUnit, prepBET, prepKAF, prepLAMED, prepMEM, prepositionTag,
					conjunctionTag);

			///////////////////////////////////////////////////////////////
			// prefix = "'h'";
			// description = "'[+definiteArticle]"
			// + URLEncoder.encode("ה", "UTF-8") + "'";
			// definiteArticleTag = true;
			// defArtHE = true;
			// relHE = false;
			// //////////////////////
			// adverbKAF = false;
			// subConOrRelSHIN = false;
			// tempSubConKAFSHIN = false;
			// tempSubConMEMSHIN = false;
			// tempSubConLAMEDKAFSHIN = false;
			// relativizerTag = false;
			// temporalSubConjTag = false;
			// subordinatingConjunctionTag = false;
			// prefPartUnit = true;
			// conjunctionTag = false;
			// prepBET = false;
			// prepKAF = false;
			// prepLAMED = false;
			// prepMEM = false;
			// prepositionTag = false;
			//
			// populatePrefixes(130, prefix, description, definiteArticleTag,
			// defArtHE, relHE, adverbKAF, subConOrRelSHIN,
			// tempSubConKAFSHIN, tempSubConMEMSHIN,
			// tempSubConLAMEDKAFSHIN, relativizerTag, temporalSubConjTag,
			// subordinatingConjunctionTag, prefPartUnit, prepBET,
			// prepKAF, prepLAMED, prepMEM,prepositionTag,conjunctionTag);

			///////////////////////////////////////////////////////////////
			prefix = "'wh'";
			description = "'[+conjunction]" + URLEncoder.encode("ו", "UTF-8") + "[+definiteArticle]"
					+ URLEncoder.encode("ה", "UTF-8") + "'";
			definiteArticleTag = true;
			defArtHE = true;
			relHE = false;
			//////////////////////
			adverbKAF = false;
			subConOrRelSHIN = false;
			tempSubConKAFSHIN = true;
			tempSubConMEMSHIN = false;
			tempSubConLAMEDKAFSHIN = false;
			relativizerTag = false;
			temporalSubConjTag = false;
			subordinatingConjunctionTag = false;
			prefPartUnit = true;
			conjunctionTag = true;
			prepBET = false;
			prepKAF = false;
			prepLAMED = false;
			prepMEM = false;
			prepositionTag = false;

			populatePrefixes(131, prefix, description, definiteArticleTag, defArtHE, relHE, adverbKAF, subConOrRelSHIN,
					tempSubConKAFSHIN, tempSubConMEMSHIN, tempSubConLAMEDKAFSHIN, relativizerTag, temporalSubConjTag,
					subordinatingConjunctionTag, prefPartUnit, prepBET, prepKAF, prepLAMED, prepMEM, prepositionTag,
					conjunctionTag);

			///////////////////////////////////////////////////////////////
			prefix = "'eh'";
			description = "'[+relativizer/subordinatingConjunction]" + URLEncoder.encode("ש", "UTF-8")
					+ "[+definiteArticle]" + URLEncoder.encode("ה", "UTF-8") + "'";
			definiteArticleTag = true;
			defArtHE = true;
			relHE = false;
			////////////////////
			adverbKAF = false;
			subConOrRelSHIN = true;
			tempSubConKAFSHIN = false;
			tempSubConMEMSHIN = false;
			tempSubConLAMEDKAFSHIN = false;
			relativizerTag = true;
			temporalSubConjTag = true;
			subordinatingConjunctionTag = false;
			prefPartUnit = true;
			conjunctionTag = false;
			prepBET = false;
			prepKAF = false;
			prepLAMED = false;
			prepMEM = false;
			prepositionTag = false;

			populatePrefixes(132, prefix, description, definiteArticleTag, defArtHE, relHE, adverbKAF, subConOrRelSHIN,
					tempSubConKAFSHIN, tempSubConMEMSHIN, tempSubConLAMEDKAFSHIN, relativizerTag, temporalSubConjTag,
					subordinatingConjunctionTag, prefPartUnit, prepBET, prepKAF, prepLAMED, prepMEM, prepositionTag,
					conjunctionTag);

			///////////////////////////////////////////////////////////////
			prefix = "'weh'";
			description = "'[+conjunction]" + URLEncoder.encode("ו", "UTF-8") + "[+relativizer/subordinatingConjunction]"
					+ URLEncoder.encode("ש", "UTF-8") + "[+definiteArticle]" + URLEncoder.encode("ה", "UTF-8") + "'";
			definiteArticleTag = true;
			defArtHE = true;
			relHE = false;
			/////////////////////
			adverbKAF = false;
			subConOrRelSHIN = true;
			tempSubConKAFSHIN = false;
			tempSubConMEMSHIN = false;
			tempSubConLAMEDKAFSHIN = false;
			relativizerTag = true;
			temporalSubConjTag = false;
			subordinatingConjunctionTag = false;
			prefPartUnit = true;
			conjunctionTag = true;
			prepBET = false;
			prepKAF = false;
			prepLAMED = false;
			prepMEM = false;
			prepositionTag = false;

			populatePrefixes(133, prefix, description, definiteArticleTag, defArtHE, relHE, adverbKAF, subConOrRelSHIN,
					tempSubConKAFSHIN, tempSubConMEMSHIN, tempSubConLAMEDKAFSHIN, relativizerTag, temporalSubConjTag,
					subordinatingConjunctionTag, prefPartUnit, prepBET, prepKAF, prepLAMED, prepMEM, prepositionTag,
					conjunctionTag);

			// ///////////////////////////////////////////////////////////////
			prefix = "'keh'";
			description = "'[+temporalSubConj]" + URLEncoder.encode("כש", "UTF-8") + "[+definiteArticle]"
					+ URLEncoder.encode("ה", "UTF-8") + "'";
			definiteArticleTag = true;
			defArtHE = true;
			relHE = false;
			/////////////////////
			adverbKAF = false;
			subConOrRelSHIN = false;
			tempSubConKAFSHIN = true;
			tempSubConMEMSHIN = false;
			tempSubConLAMEDKAFSHIN = false;
			relativizerTag = false;
			temporalSubConjTag = true;
			subordinatingConjunctionTag = false;
			prefPartUnit = true;
			conjunctionTag = false;
			prepBET = false;
			prepKAF = false;
			prepLAMED = false;
			prepMEM = false;
			prepositionTag = false;

			populatePrefixes(134, prefix, description, definiteArticleTag, defArtHE, relHE, adverbKAF, subConOrRelSHIN,
					tempSubConKAFSHIN, tempSubConMEMSHIN, tempSubConLAMEDKAFSHIN, relativizerTag, temporalSubConjTag,
					subordinatingConjunctionTag, prefPartUnit, prepBET, prepKAF, prepLAMED, prepMEM, prepositionTag,
					conjunctionTag);

			// ///////////////////////////////////////////////////////////////
			prefix = "'wkeh'";
			description = "'[+conjunction]" + URLEncoder.encode("ו", "UTF-8") + "[+temporalSubConj]"
					+ URLEncoder.encode("כש", "UTF-8") + "[+definiteArticle]" + URLEncoder.encode("ה", "UTF-8") + "'";
			definiteArticleTag = true;
			defArtHE = true;
			relHE = false;
			/////////////////////
			adverbKAF = false;
			subConOrRelSHIN = false;
			tempSubConKAFSHIN = true;
			tempSubConMEMSHIN = false;
			tempSubConLAMEDKAFSHIN = false;
			relativizerTag = false;
			temporalSubConjTag = true;
			subordinatingConjunctionTag = false;
			prefPartUnit = true;
			conjunctionTag = true;
			prepBET = false;
			prepKAF = false;
			prepLAMED = false;
			prepMEM = false;
			prepositionTag = false;

			populatePrefixes(135, prefix, description, definiteArticleTag, defArtHE, relHE, adverbKAF, subConOrRelSHIN,
					tempSubConKAFSHIN, tempSubConMEMSHIN, tempSubConLAMEDKAFSHIN, relativizerTag, temporalSubConjTag,
					subordinatingConjunctionTag, prefPartUnit, prepBET, prepKAF, prepLAMED, prepMEM, prepositionTag,
					conjunctionTag);

			// ///////////////////////////////////////////////////////////////
			prefix = "'ekeh'";
			description = "'[+relativizer/subordinatingConjunction]" + URLEncoder.encode("ש", "UTF-8")
					+ "[+temporalSubConj]" + URLEncoder.encode("כש", "UTF-8") + "[+definiteArticle]"
					+ URLEncoder.encode("ה", "UTF-8") + "'";
			definiteArticleTag = true;
			defArtHE = true;
			relHE = false;
			/////////////////////
			adverbKAF = false;
			subConOrRelSHIN = false;
			tempSubConKAFSHIN = true;
			tempSubConMEMSHIN = false;
			tempSubConLAMEDKAFSHIN = false;
			relativizerTag = true;
			temporalSubConjTag = true;
			subordinatingConjunctionTag = false;
			prefPartUnit = true;
			conjunctionTag = false;
			prepBET = false;
			prepKAF = false;
			prepLAMED = false;
			prepMEM = false;
			prepositionTag = false;

			populatePrefixes(136, prefix, description, definiteArticleTag, defArtHE, relHE, adverbKAF, subConOrRelSHIN,
					tempSubConKAFSHIN, tempSubConMEMSHIN, tempSubConLAMEDKAFSHIN, relativizerTag, temporalSubConjTag,
					subordinatingConjunctionTag, prefPartUnit, prepBET, prepKAF, prepLAMED, prepMEM, prepositionTag,
					conjunctionTag);

			// ///////////////////////////////////////////////////////////////
			prefix = "'wekeh'";
			description = "'[+conjunction]" + URLEncoder.encode("ו", "UTF-8") + "[+relativizer/subordinatingConjunction]"
					+ URLEncoder.encode("ש", "UTF-8") + "[+temporalSubConj]" + URLEncoder.encode("כש", "UTF-8")
					+ "[+definiteArticle]" + URLEncoder.encode("ה", "UTF-8") + "'";
			definiteArticleTag = true;
			defArtHE = true;
			relHE = false;
			/////////////////////
			adverbKAF = false;
			subConOrRelSHIN = false;
			tempSubConKAFSHIN = false;
			tempSubConMEMSHIN = true;
			tempSubConLAMEDKAFSHIN = false;
			relativizerTag = true;
			temporalSubConjTag = true;
			subordinatingConjunctionTag = false;
			prefPartUnit = true;
			conjunctionTag = true;
			prepBET = false;
			prepKAF = false;
			prepLAMED = false;
			prepMEM = false;
			prepositionTag = false;

			populatePrefixes(137, prefix, description, definiteArticleTag, defArtHE, relHE, adverbKAF, subConOrRelSHIN,
					tempSubConKAFSHIN, tempSubConMEMSHIN, tempSubConLAMEDKAFSHIN, relativizerTag, temporalSubConjTag,
					subordinatingConjunctionTag, prefPartUnit, prepBET, prepKAF, prepLAMED, prepMEM, prepositionTag,
					conjunctionTag);

			// ///////////////////////////////////////////////////////////////
			prefix = "'meh'";
			description = "'[+temporalSubConj]" + URLEncoder.encode("מש", "UTF-8") + "[+definiteArticle]"
					+ URLEncoder.encode("ה", "UTF-8") + "'";
			definiteArticleTag = true;
			defArtHE = true;
			relHE = false;
			/////////////////////
			adverbKAF = false;
			subConOrRelSHIN = false;
			tempSubConKAFSHIN = false;
			tempSubConMEMSHIN = true;
			tempSubConLAMEDKAFSHIN = false;
			relativizerTag = false;
			temporalSubConjTag = true;
			subordinatingConjunctionTag = false;
			prefPartUnit = true;
			conjunctionTag = false;
			prepBET = false;
			prepKAF = false;
			prepLAMED = false;
			prepMEM = false;
			prepositionTag = false;

			populatePrefixes(138, prefix, description, definiteArticleTag, defArtHE, relHE, adverbKAF, subConOrRelSHIN,
					tempSubConKAFSHIN, tempSubConMEMSHIN, tempSubConLAMEDKAFSHIN, relativizerTag, temporalSubConjTag,
					subordinatingConjunctionTag, prefPartUnit, prepBET, prepKAF, prepLAMED, prepMEM, prepositionTag,
					conjunctionTag);

			// ///////////////////////////////////////////////////////////////
			prefix = "'wmeh'";
			description = "'[+conjunction]" + URLEncoder.encode("ו", "UTF-8") + "[+temporalSubConj]"
					+ URLEncoder.encode("מש", "UTF-8") + "[+definiteArticle]" + URLEncoder.encode("ה", "UTF-8") + "'";
			definiteArticleTag = true;
			defArtHE = true;
			relHE = false;
			/////////////////////
			adverbKAF = false;
			subConOrRelSHIN = false;
			tempSubConKAFSHIN = false;
			tempSubConMEMSHIN = true;
			tempSubConLAMEDKAFSHIN = false;
			relativizerTag = false;
			temporalSubConjTag = true;
			subordinatingConjunctionTag = false;
			prefPartUnit = true;
			conjunctionTag = true;
			prepBET = false;
			prepKAF = false;
			prepLAMED = false;
			prepMEM = false;
			prepositionTag = false;

			populatePrefixes(139, prefix, description, definiteArticleTag, defArtHE, relHE, adverbKAF, subConOrRelSHIN,
					tempSubConKAFSHIN, tempSubConMEMSHIN, tempSubConLAMEDKAFSHIN, relativizerTag, temporalSubConjTag,
					subordinatingConjunctionTag, prefPartUnit, prepBET, prepKAF, prepLAMED, prepMEM, prepositionTag,
					conjunctionTag);

			// ///////////////////////////////////////////////////////////////
			prefix = "'emeh'";
			description = "'[+relativizer/subordinatingConjunction]" + URLEncoder.encode("ש", "UTF-8")
					+ "[+temporalSubConj]" + URLEncoder.encode("מש", "UTF-8") + "[+definiteArticle]"
					+ URLEncoder.encode("ה", "UTF-8") + "'";
			definiteArticleTag = true;
			defArtHE = true;
			relHE = false;
			/////////////////////
			adverbKAF = false;
			subConOrRelSHIN = true;
			tempSubConKAFSHIN = false;
			tempSubConMEMSHIN = true;
			tempSubConLAMEDKAFSHIN = false;
			relativizerTag = true;
			temporalSubConjTag = true;
			subordinatingConjunctionTag = false;
			prefPartUnit = true;
			conjunctionTag = false;
			prepBET = false;
			prepKAF = false;
			prepLAMED = false;
			prepMEM = false;
			prepositionTag = false;

			populatePrefixes(140, prefix, description, definiteArticleTag, defArtHE, relHE, adverbKAF, subConOrRelSHIN,
					tempSubConKAFSHIN, tempSubConMEMSHIN, tempSubConLAMEDKAFSHIN, relativizerTag, temporalSubConjTag,
					subordinatingConjunctionTag, prefPartUnit, prepBET, prepKAF, prepLAMED, prepMEM, prepositionTag,
					conjunctionTag);

			// ///////////////////////////////////////////////////////////////
			prefix = "'wemeh'";
			description = "'[+conjunction]" + URLEncoder.encode("ו", "UTF-8") + "[+relativizer/subordinatingConjunction]"
					+ URLEncoder.encode("ש", "UTF-8") + "[+temporalSubConj]" + URLEncoder.encode("מש", "UTF-8")
					+ "[+definiteArticle]" + URLEncoder.encode("ה", "UTF-8") + "'";
			definiteArticleTag = true;
			defArtHE = true;
			relHE = false;
			/////////////////////
			adverbKAF = false;
			subConOrRelSHIN = false;
			tempSubConKAFSHIN = false;
			tempSubConMEMSHIN = true;
			tempSubConLAMEDKAFSHIN = false;
			relativizerTag = true;
			temporalSubConjTag = true;
			subordinatingConjunctionTag = false;
			prefPartUnit = true;
			conjunctionTag = true;
			prepBET = false;
			prepKAF = false;
			prepLAMED = false;
			prepMEM = false;
			prepositionTag = false;

			populatePrefixes(141, prefix, description, definiteArticleTag, defArtHE, relHE, adverbKAF, subConOrRelSHIN,
					tempSubConKAFSHIN, tempSubConMEMSHIN, tempSubConLAMEDKAFSHIN, relativizerTag, temporalSubConjTag,
					subordinatingConjunctionTag, prefPartUnit, prepBET, prepKAF, prepLAMED, prepMEM, prepositionTag,
					conjunctionTag);

			// ///////////////////////////////////////////////////////////////
			prefix = "'lkeh'";
			description = "'[+temporalSubConj]" + URLEncoder.encode("לכש", "UTF-8") + "[+definiteArticle]"
					+ URLEncoder.encode("ה", "UTF-8") + "'";
			definiteArticleTag = true;
			defArtHE = true;
			relHE = false;
			/////////////////////
			adverbKAF = false;
			subConOrRelSHIN = false;
			tempSubConKAFSHIN = false;
			tempSubConMEMSHIN = true;
			tempSubConLAMEDKAFSHIN = false;
			relativizerTag = false;
			temporalSubConjTag = true;
			subordinatingConjunctionTag = false;
			prefPartUnit = true;
			conjunctionTag = false;
			prepBET = false;
			prepKAF = false;
			prepLAMED = false;
			prepMEM = false;
			prepositionTag = false;

			populatePrefixes(142, prefix, description, definiteArticleTag, defArtHE, relHE, adverbKAF, subConOrRelSHIN,
					tempSubConKAFSHIN, tempSubConMEMSHIN, tempSubConLAMEDKAFSHIN, relativizerTag, temporalSubConjTag,
					subordinatingConjunctionTag, prefPartUnit, prepBET, prepKAF, prepLAMED, prepMEM, prepositionTag,
					conjunctionTag);

			// ///////////////////////////////////////////////////////////////
			prefix = "'wlkeh'";
			description = "'[+conjunction]" + URLEncoder.encode("ו", "UTF-8") + "[+temporalSubConj]"
					+ URLEncoder.encode("לכש", "UTF-8") + "[+definiteArticle]" + URLEncoder.encode("ה", "UTF-8") + "'";
			definiteArticleTag = true;
			defArtHE = true;
			relHE = false;
			/////////////////////
			adverbKAF = false;
			subConOrRelSHIN = false;
			tempSubConKAFSHIN = false;
			tempSubConMEMSHIN = false;
			tempSubConLAMEDKAFSHIN = true;
			relativizerTag = false;
			temporalSubConjTag = true;
			subordinatingConjunctionTag = false;
			prefPartUnit = true;
			conjunctionTag = true;
			prepBET = false;
			prepKAF = false;
			prepLAMED = false;
			prepMEM = false;
			prepositionTag = false;

			populatePrefixes(143, prefix, description, definiteArticleTag, defArtHE, relHE, adverbKAF, subConOrRelSHIN,
					tempSubConKAFSHIN, tempSubConMEMSHIN, tempSubConLAMEDKAFSHIN, relativizerTag, temporalSubConjTag,
					subordinatingConjunctionTag, prefPartUnit, prepBET, prepKAF, prepLAMED, prepMEM, prepositionTag,
					conjunctionTag);

			// ///////////////////////////////////////////////////////////////
			prefix = "'elkeh'";
			description = "'[+relativizer/subordinatingConjunction]" + URLEncoder.encode("ש", "UTF-8")
					+ "[+temporalSubConj]" + URLEncoder.encode("לכש", "UTF-8") + "[+definiteArticle]"
					+ URLEncoder.encode("ה", "UTF-8") + "'";
			definiteArticleTag = true;
			defArtHE = true;
			relHE = false;
			/////////////////////
			adverbKAF = false;
			subConOrRelSHIN = true;
			tempSubConKAFSHIN = true;
			tempSubConMEMSHIN = false;
			tempSubConLAMEDKAFSHIN = true;
			relativizerTag = true;
			temporalSubConjTag = true;
			subordinatingConjunctionTag = false;
			prefPartUnit = true;
			conjunctionTag = false;
			prepBET = false;
			prepKAF = false;
			prepLAMED = false;
			prepMEM = false;
			prepositionTag = false;

			populatePrefixes(144, prefix, description, definiteArticleTag, defArtHE, relHE, adverbKAF, subConOrRelSHIN,
					tempSubConKAFSHIN, tempSubConMEMSHIN, tempSubConLAMEDKAFSHIN, relativizerTag, temporalSubConjTag,
					subordinatingConjunctionTag, prefPartUnit, prepBET, prepKAF, prepLAMED, prepMEM, prepositionTag,
					conjunctionTag);

			// ///////////////////////////////////////////////////////////////
			prefix = "'welkeh'";
			description = "'[+conjunction]" + URLEncoder.encode("ו", "UTF-8") + "[+relativizer/subordinatingConjunction]"
					+ URLEncoder.encode("ש", "UTF-8") + "[+temporalSubConj]" + URLEncoder.encode("לכש", "UTF-8")
					+ "[+definiteArticle]" + URLEncoder.encode("ה", "UTF-8") + "'";
			definiteArticleTag = true;
			defArtHE = true;
			relHE = false;
			/////////////////////
			adverbKAF = false;
			subConOrRelSHIN = true;
			tempSubConKAFSHIN = true;
			tempSubConMEMSHIN = false;
			tempSubConLAMEDKAFSHIN = true;
			relativizerTag = true;
			temporalSubConjTag = true;
			subordinatingConjunctionTag = false;
			prefPartUnit = true;
			conjunctionTag = true;
			prepBET = false;
			prepKAF = false;
			prepLAMED = false;
			prepMEM = false;
			prepositionTag = false;

			populatePrefixes(145, prefix, description, definiteArticleTag, defArtHE, relHE, adverbKAF, subConOrRelSHIN,
					tempSubConKAFSHIN, tempSubConMEMSHIN, tempSubConLAMEDKAFSHIN, relativizerTag, temporalSubConjTag,
					subordinatingConjunctionTag, prefPartUnit, prepBET, prepKAF, prepLAMED, prepMEM, prepositionTag,
					conjunctionTag);

			// ///////////////////////////////////////////////////////////////
			prefix = "'welkeh'";
			description = "'[+conjunction]" + URLEncoder.encode("ו", "UTF-8") + "[+relativizer/subordinatingConjunction]"
					+ URLEncoder.encode("ש", "UTF-8") + "[+temporalSubConj]" + URLEncoder.encode("לכש", "UTF-8")
					+ "[+definiteArticle]" + URLEncoder.encode("ה", "UTF-8") + "'";
			definiteArticleTag = true;
			defArtHE = true;
			relHE = false;
			/////////////////////
			adverbKAF = false;
			subConOrRelSHIN = true;
			tempSubConKAFSHIN = false;
			tempSubConMEMSHIN = false;
			tempSubConLAMEDKAFSHIN = true;
			relativizerTag = true;
			temporalSubConjTag = true;
			subordinatingConjunctionTag = false;
			prefPartUnit = true;
			conjunctionTag = true;
			prepBET = false;
			prepKAF = false;
			prepLAMED = false;
			prepMEM = false;
			prepositionTag = false;

			populatePrefixes(146, prefix, description, definiteArticleTag, defArtHE, relHE, adverbKAF, subConOrRelSHIN,
					tempSubConKAFSHIN, tempSubConMEMSHIN, tempSubConLAMEDKAFSHIN, relativizerTag, temporalSubConjTag,
					subordinatingConjunctionTag, prefPartUnit, prepBET, prepKAF, prepLAMED, prepMEM, prepositionTag,
					conjunctionTag);

			// ///////////////////////////////////////////////////////////////
			prefix = "'welkeh'";
			description = "'[+conjunction]" + URLEncoder.encode("ו", "UTF-8") + "[+relativizer/subordinatingConjunction]"
					+ URLEncoder.encode("ש", "UTF-8") + "[+temporalSubConj]" + URLEncoder.encode("לכש", "UTF-8")
					+ "[+definiteArticle]" + URLEncoder.encode("ה", "UTF-8") + "'";
			definiteArticleTag = true;
			defArtHE = true;
			relHE = false;
			/////////////////////
			adverbKAF = false;
			subConOrRelSHIN = false;
			tempSubConKAFSHIN = false;
			tempSubConMEMSHIN = false;
			tempSubConLAMEDKAFSHIN = true;
			relativizerTag = true;
			temporalSubConjTag = true;
			subordinatingConjunctionTag = false;
			prefPartUnit = true;
			conjunctionTag = true;
			prepBET = false;
			prepKAF = false;
			prepLAMED = false;
			prepMEM = false;
			prepositionTag = false;

			populatePrefixes(147, prefix, description, definiteArticleTag, defArtHE, relHE, adverbKAF, subConOrRelSHIN,
					tempSubConKAFSHIN, tempSubConMEMSHIN, tempSubConLAMEDKAFSHIN, relativizerTag, temporalSubConjTag,
					subordinatingConjunctionTag, prefPartUnit, prepBET, prepKAF, prepLAMED, prepMEM, prepositionTag,
					conjunctionTag);

			///////////////////////////////////////////////////////////////
			prefix = "'k'";
			description = "'[+adverb]" + URLEncoder.encode("כ", "UTF-8") + "'";
			definiteArticleTag = false;
			defArtHE = false;
			relHE = false;
			///////////////
			adverbKAF = true;
			subConOrRelSHIN = false;
			tempSubConKAFSHIN = false;
			tempSubConMEMSHIN = false;
			tempSubConLAMEDKAFSHIN = false;
			relativizerTag = false;
			temporalSubConjTag = false;
			subordinatingConjunctionTag = false;
			prefPartUnit = true;
			conjunctionTag = false;
			prepBET = false;
			prepKAF = false;
			prepLAMED = false;
			prepMEM = false;
			prepositionTag = false;

			populatePrefixes(148, prefix, description, definiteArticleTag, defArtHE, relHE, adverbKAF, subConOrRelSHIN,
					tempSubConKAFSHIN, tempSubConMEMSHIN, tempSubConLAMEDKAFSHIN, relativizerTag, temporalSubConjTag,
					subordinatingConjunctionTag, prefPartUnit, prepBET, prepKAF, prepLAMED, prepMEM, prepositionTag,
					conjunctionTag);

			///////////////////////////////////////////////////////////////
			prefix = "'wk'";
			description = "'[+conjunction]" + URLEncoder.encode("ו", "UTF-8") + "[+adverb]"
					+ URLEncoder.encode("כ", "UTF-8") + "'";
			definiteArticleTag = false;
			defArtHE = false;
			relHE = false;
			/////////////////
			adverbKAF = true;
			subConOrRelSHIN = false;
			tempSubConKAFSHIN = false;
			tempSubConMEMSHIN = false;
			tempSubConLAMEDKAFSHIN = false;
			relativizerTag = false;
			temporalSubConjTag = false;
			subordinatingConjunctionTag = false;
			prefPartUnit = true;
			conjunctionTag = true;
			prepBET = false;
			prepKAF = false;
			prepLAMED = false;
			prepMEM = false;
			prepositionTag = false;

			populatePrefixes(149, prefix, description, definiteArticleTag, defArtHE, relHE, adverbKAF, subConOrRelSHIN,
					tempSubConKAFSHIN, tempSubConMEMSHIN, tempSubConLAMEDKAFSHIN, relativizerTag, temporalSubConjTag,
					subordinatingConjunctionTag, prefPartUnit, prepBET, prepKAF, prepLAMED, prepMEM, prepositionTag,
					conjunctionTag);

			///////////////////////////////////////////////////////////////
			prefix = "'ek'";
			description = "'[+relativizer/subordinatingConjunction]" + URLEncoder.encode("ש", "UTF-8") + "[+adverb]"
					+ URLEncoder.encode("כ", "UTF-8") + "'";
			definiteArticleTag = false;
			defArtHE = false;
			relHE = false;
			////////////////
			adverbKAF = true;
			subConOrRelSHIN = true;
			tempSubConKAFSHIN = false;
			tempSubConMEMSHIN = false;
			tempSubConLAMEDKAFSHIN = false;
			relativizerTag = true;
			temporalSubConjTag = false;
			subordinatingConjunctionTag = false;
			prefPartUnit = true;
			conjunctionTag = false;
			prepBET = false;
			prepKAF = false;
			prepLAMED = false;
			prepMEM = false;
			prepositionTag = false;

			populatePrefixes(150, prefix, description, definiteArticleTag, defArtHE, relHE, adverbKAF, subConOrRelSHIN,
					tempSubConKAFSHIN, tempSubConMEMSHIN, tempSubConLAMEDKAFSHIN, relativizerTag, temporalSubConjTag,
					subordinatingConjunctionTag, prefPartUnit, prepBET, prepKAF, prepLAMED, prepMEM, prepositionTag,
					conjunctionTag);

			///////////////////////////////////////////////////////////////
			prefix = "'wek'";
			description = "'[+conjunction]" + URLEncoder.encode("ו", "UTF-8") + "[+relativizer/subordinatingConjunction]"
					+ URLEncoder.encode("ש", "UTF-8") + "[+adverb]" + URLEncoder.encode("כ", "UTF-8") + "'";
			definiteArticleTag = false;
			defArtHE = false;
			relHE = false;
			///////////////
			adverbKAF = true;
			subConOrRelSHIN = false;
			tempSubConKAFSHIN = true;
			tempSubConMEMSHIN = false;
			tempSubConLAMEDKAFSHIN = false;
			relativizerTag = true;
			temporalSubConjTag = false;
			subordinatingConjunctionTag = false;
			prefPartUnit = true;
			conjunctionTag = true;
			prepBET = false;
			prepKAF = false;
			prepLAMED = false;
			prepMEM = false;
			prepositionTag = false;

			populatePrefixes(151, prefix, description, definiteArticleTag, defArtHE, relHE, adverbKAF, subConOrRelSHIN,
					tempSubConKAFSHIN, tempSubConMEMSHIN, tempSubConLAMEDKAFSHIN, relativizerTag, temporalSubConjTag,
					subordinatingConjunctionTag, prefPartUnit, prepBET, prepKAF, prepLAMED, prepMEM, prepositionTag,
					conjunctionTag);
			///////////////////////////////////////////////////////////////
			prefix = "'kek'";
			description = "'[+temporalSubConj]" + URLEncoder.encode("כש", "UTF-8") + "[+adverb]"
					+ URLEncoder.encode("כ", "UTF-8") + "'";
			definiteArticleTag = false;
			defArtHE = false;
			relHE = false;
			//////////////
			adverbKAF = true;
			subConOrRelSHIN = false;
			tempSubConKAFSHIN = true;
			tempSubConMEMSHIN = false;
			tempSubConLAMEDKAFSHIN = false;
			relativizerTag = false;
			temporalSubConjTag = true;
			subordinatingConjunctionTag = false;
			prefPartUnit = true;
			conjunctionTag = false;
			prepBET = false;
			prepKAF = false;
			prepLAMED = false;
			prepMEM = false;
			prepositionTag = false;

			populatePrefixes(152, prefix, description, definiteArticleTag, defArtHE, relHE, adverbKAF, subConOrRelSHIN,
					tempSubConKAFSHIN, tempSubConMEMSHIN, tempSubConLAMEDKAFSHIN, relativizerTag, temporalSubConjTag,
					subordinatingConjunctionTag, prefPartUnit, prepBET, prepKAF, prepLAMED, prepMEM, prepositionTag,
					conjunctionTag);

			///////////////////////////////////////////////////////////////
			prefix = "'wkek'";
			description = "'[+conjunction]" + URLEncoder.encode("ו", "UTF-8") + "[+temporalSubConj]"
					+ URLEncoder.encode("כש", "UTF-8") + "[+adverb]" + URLEncoder.encode("כ", "UTF-8") + "'";
			definiteArticleTag = false;
			defArtHE = false;
			relHE = false;
			/////////////////
			adverbKAF = true;
			subConOrRelSHIN = false;
			tempSubConKAFSHIN = true;
			tempSubConMEMSHIN = false;
			tempSubConLAMEDKAFSHIN = false;
			relativizerTag = false;
			temporalSubConjTag = true;
			subordinatingConjunctionTag = false;
			prefPartUnit = true;
			conjunctionTag = true;
			prepBET = false;
			prepKAF = false;
			prepLAMED = false;
			prepMEM = false;
			prepositionTag = false;

			populatePrefixes(153, prefix, description, definiteArticleTag, defArtHE, relHE, adverbKAF, subConOrRelSHIN,
					tempSubConKAFSHIN, tempSubConMEMSHIN, tempSubConLAMEDKAFSHIN, relativizerTag, temporalSubConjTag,
					subordinatingConjunctionTag, prefPartUnit, prepBET, prepKAF, prepLAMED, prepMEM, prepositionTag,
					conjunctionTag);

			///////////////////////////////////////////////////////////////
			prefix = "'ekek'";
			description = "'[+relativizer/subordinatingConjunction]" + URLEncoder.encode("ש", "UTF-8")
					+ "[+temporalSubConj]" + URLEncoder.encode("כש", "UTF-8") + "[+adverb]" + URLEncoder.encode("כ", "UTF-8")
					+ "'";
			definiteArticleTag = false;
			defArtHE = false;
			relHE = false;
			/////////////////
			adverbKAF = true;
			subConOrRelSHIN = false;
			tempSubConKAFSHIN = true;
			tempSubConMEMSHIN = false;
			tempSubConLAMEDKAFSHIN = false;
			relativizerTag = true;
			temporalSubConjTag = true;
			subordinatingConjunctionTag = false;
			prefPartUnit = true;
			conjunctionTag = false;
			prepBET = false;
			prepKAF = false;
			prepLAMED = false;
			prepMEM = false;
			prepositionTag = false;

			populatePrefixes(154, prefix, description, definiteArticleTag, defArtHE, relHE, adverbKAF, subConOrRelSHIN,
					tempSubConKAFSHIN, tempSubConMEMSHIN, tempSubConLAMEDKAFSHIN, relativizerTag, temporalSubConjTag,
					subordinatingConjunctionTag, prefPartUnit, prepBET, prepKAF, prepLAMED, prepMEM, prepositionTag,
					conjunctionTag);

			///////////////////////////////////////////////////////////////
			prefix = "'wekek'";
			description = "'[+conjunction]" + URLEncoder.encode("ו", "UTF-8") + "[+relativizer/subordinatingConjunction]"
					+ URLEncoder.encode("ש", "UTF-8") + "[+temporalSubConj]" + URLEncoder.encode("כש", "UTF-8") + "[+adverb]"
					+ URLEncoder.encode("כ", "UTF-8") + "'";
			definiteArticleTag = false;
			defArtHE = false;
			relHE = false;
			////////////////
			adverbKAF = true;
			subConOrRelSHIN = true;
			tempSubConKAFSHIN = true;
			tempSubConMEMSHIN = false;
			tempSubConLAMEDKAFSHIN = false;
			relativizerTag = true;
			temporalSubConjTag = true;
			subordinatingConjunctionTag = false;
			prefPartUnit = true;
			conjunctionTag = true;
			prepBET = false;
			prepKAF = false;
			prepLAMED = false;
			prepMEM = false;
			prepositionTag = false;

			populatePrefixes(155, prefix, description, definiteArticleTag, defArtHE, relHE, adverbKAF, subConOrRelSHIN,
					tempSubConKAFSHIN, tempSubConMEMSHIN, tempSubConLAMEDKAFSHIN, relativizerTag, temporalSubConjTag,
					subordinatingConjunctionTag, prefPartUnit, prepBET, prepKAF, prepLAMED, prepMEM, prepositionTag,
					conjunctionTag);

			///////////////////////////////////////////////////////////////
			prefix = "'mek'";
			description = "'[+temporalSubConj]" + URLEncoder.encode("מש", "UTF-8") + "[+adverb]"
					+ URLEncoder.encode("כ", "UTF-8") + "'";
			definiteArticleTag = false;
			defArtHE = false;
			relHE = false;
			/////////////////
			adverbKAF = true;
			subConOrRelSHIN = false;
			tempSubConKAFSHIN = false;
			tempSubConMEMSHIN = true;
			tempSubConLAMEDKAFSHIN = false;
			relativizerTag = false;
			temporalSubConjTag = true;
			subordinatingConjunctionTag = false;
			prefPartUnit = true;
			conjunctionTag = false;
			prepBET = false;
			prepKAF = false;
			prepLAMED = false;
			prepMEM = false;
			prepositionTag = false;

			populatePrefixes(156, prefix, description, definiteArticleTag, defArtHE, relHE, adverbKAF, subConOrRelSHIN,
					tempSubConKAFSHIN, tempSubConMEMSHIN, tempSubConLAMEDKAFSHIN, relativizerTag, temporalSubConjTag,
					subordinatingConjunctionTag, prefPartUnit, prepBET, prepKAF, prepLAMED, prepMEM, prepositionTag,
					conjunctionTag);

			///////////////////////////////////////////////////////////////
			prefix = "'wmek'";
			description = "'[+conjunction]" + URLEncoder.encode("ו", "UTF-8") + "[+temporalSubConj]"
					+ URLEncoder.encode("מש", "UTF-8") + "[+adverb]" + URLEncoder.encode("כ", "UTF-8") + "'";
			definiteArticleTag = false;
			defArtHE = false;
			relHE = false;
			//////////////////
			adverbKAF = true;
			subConOrRelSHIN = false;
			tempSubConKAFSHIN = false;
			tempSubConMEMSHIN = true;
			tempSubConLAMEDKAFSHIN = false;
			relativizerTag = false;
			temporalSubConjTag = true;
			subordinatingConjunctionTag = false;
			prefPartUnit = true;
			conjunctionTag = true;
			prepBET = false;
			prepKAF = false;
			prepLAMED = false;
			prepMEM = false;
			prepositionTag = false;

			populatePrefixes(157, prefix, description, definiteArticleTag, defArtHE, relHE, adverbKAF, subConOrRelSHIN,
					tempSubConKAFSHIN, tempSubConMEMSHIN, tempSubConLAMEDKAFSHIN, relativizerTag, temporalSubConjTag,
					subordinatingConjunctionTag, prefPartUnit, prepBET, prepKAF, prepLAMED, prepMEM, prepositionTag,
					conjunctionTag);

			///////////////////////////////////////////////////////////////
			prefix = "'emek'";
			description = "'[+relativizer/subordinatingConjunction]" + URLEncoder.encode("ש", "UTF-8")
					+ "[+temporalSubConj]" + URLEncoder.encode("מש", "UTF-8") + "[+adverb]" + URLEncoder.encode("כ", "UTF-8")
					+ "'";
			definiteArticleTag = false;
			defArtHE = false;
			relHE = false;
			/////////////////////
			adverbKAF = true;
			subConOrRelSHIN = true;
			tempSubConKAFSHIN = false;
			tempSubConMEMSHIN = true;
			tempSubConLAMEDKAFSHIN = false;
			relativizerTag = true;
			temporalSubConjTag = true;
			subordinatingConjunctionTag = false;
			prefPartUnit = true;
			conjunctionTag = false;
			prepBET = false;
			prepKAF = false;
			prepLAMED = false;
			prepMEM = false;
			prepositionTag = false;

			populatePrefixes(158, prefix, description, definiteArticleTag, defArtHE, relHE, adverbKAF, subConOrRelSHIN,
					tempSubConKAFSHIN, tempSubConMEMSHIN, tempSubConLAMEDKAFSHIN, relativizerTag, temporalSubConjTag,
					subordinatingConjunctionTag, prefPartUnit, prepBET, prepKAF, prepLAMED, prepMEM, prepositionTag,
					conjunctionTag);

			///////////////////////////////////////////////////////////////
			prefix = "'wemek'";
			description = "'[+conjunction]" + URLEncoder.encode("ו", "UTF-8") + "[+relativizer/subordinatingConjunction]"
					+ URLEncoder.encode("ש", "UTF-8") + "[+temporalSubConj]" + URLEncoder.encode("מש", "UTF-8") + "[+adverb]"
					+ URLEncoder.encode("כ", "UTF-8") + "'";
			definiteArticleTag = false;
			defArtHE = false;
			relHE = false;
			/////////////////////
			adverbKAF = true;
			subConOrRelSHIN = true;
			tempSubConKAFSHIN = false;
			tempSubConMEMSHIN = true;
			tempSubConLAMEDKAFSHIN = false;
			relativizerTag = true;
			temporalSubConjTag = true;
			subordinatingConjunctionTag = false;
			prefPartUnit = true;
			conjunctionTag = true;
			prepBET = false;
			prepKAF = false;
			prepLAMED = false;
			prepMEM = false;
			prepositionTag = false;

			populatePrefixes(159, prefix, description, definiteArticleTag, defArtHE, relHE, adverbKAF, subConOrRelSHIN,
					tempSubConKAFSHIN, tempSubConMEMSHIN, tempSubConLAMEDKAFSHIN, relativizerTag, temporalSubConjTag,
					subordinatingConjunctionTag, prefPartUnit, prepBET, prepKAF, prepLAMED, prepMEM, prepositionTag,
					conjunctionTag);
			///////////////////////////////////////////////////////////////
			// prefix = "'h'";
			// description = "'[relativizerTag]" + URLEncoder.encode("ה", "UTF-8") + "'";
			// definiteArticleTag = false;
			// defArtHE = false;
			// relHE = true;
			// /////////////////////
			// adverbKAF = false;
			// subConOrRelSHIN = false;
			// tempSubConKAFSHIN = false;
			// tempSubConMEMSHIN = false;
			// tempSubConLAMEDKAFSHIN = false;
			// relativizerTag = true;
			// temporalSubConjTag = false;
			// subordinatingConjunctionTag = false;
			// prefPartUnit = true;
			// conjunctionTag = false;
			// prepBET = false;
			// prepKAF = false;
			// prepLAMED = false;
			// prepMEM = false;
			// prepositionTag = false;
			//
			// populatePrefixes(174, prefix, description, definiteArticleTag,
			// defArtHE, relHE, adverbKAF, subConOrRelSHIN,
			// tempSubConKAFSHIN, tempSubConMEMSHIN,
			// tempSubConLAMEDKAFSHIN, relativizerTag, temporalSubConjTag,
			// subordinatingConjunctionTag, prefPartUnit, prepBET,
			// prepKAF, prepLAMED, prepMEM,prepositionTag,conjunctionTag);
			//
			/////////////////////////////////////////////////////////////////
			// prefix = "'wh'";
			// description = "'[+conjunction]" + URLEncoder.encode("ו", "UTF-8")+
			// "[relativizerTag]" + URLEncoder.encode("ה", "UTF-8") + "'";
			// definiteArticleTag = false;
			// defArtHE = false;
			// relHE = true;
			// ////////////////////
			// adverbKAF = false;
			// subConOrRelSHIN = false;
			// tempSubConKAFSHIN = false;
			// tempSubConMEMSHIN = false;
			// tempSubConLAMEDKAFSHIN = false;
			// relativizerTag = true;
			// temporalSubConjTag = false;
			// subordinatingConjunctionTag = false;
			// prefPartUnit = true;
			// conjunctionTag = true;
			// prepBET = false;
			// prepKAF = false;
			// prepLAMED = false;
			// prepMEM = false;
			// prepositionTag = false;
			//
			// populatePrefixes(175, prefix, description, definiteArticleTag,
			// defArtHE, relHE, adverbKAF, subConOrRelSHIN,
			// tempSubConKAFSHIN, tempSubConMEMSHIN,
			// tempSubConLAMEDKAFSHIN, relativizerTag, temporalSubConjTag,
			// subordinatingConjunctionTag, prefPartUnit, prepBET,
			// prepKAF, prepLAMED, prepMEM,prepositionTag,conjunctionTag);
			//
			///////////////////////////////////////////////////////////////
			// prefix = "'mh'";
			// description = "'[+preposition]" + URLEncoder.encode("מ", "UTF-8")
			// + "[+definiteArticle]" + URLEncoder.encode("ה", "UTF-8")+ "'";
			// definiteArticleTag = true;
			// defArtHE = true;
			// relHE = false;
			// ///////////////////////
			// adverbKAF = false;
			// subConOrRelSHIN = false;
			// tempSubConKAFSHIN = false;
			// tempSubConMEMSHIN = false;
			// tempSubConLAMEDKAFSHIN = false;
			// relativizerTag = false;
			// temporalSubConjTag = false;
			// subordinatingConjunctionTag = false;
			// prefPartUnit = true;
			// conjunctionTag = false;
			// prepBET = false;
			// prepKAF = false;
			// prepLAMED = false;
			// prepMEM = true;
			// prepositionTag = true;
			//
			// populatePrefixes(160, prefix, description, definiteArticleTag,
			// defArtHE, relHE, adverbKAF, subConOrRelSHIN,
			// tempSubConKAFSHIN, tempSubConMEMSHIN,
			// tempSubConLAMEDKAFSHIN, relativizerTag, temporalSubConjTag,
			// subordinatingConjunctionTag, prefPartUnit, prepBET,
			// prepKAF, prepLAMED, prepMEM,prepositionTag,conjunctionTag);

			///////////////////////////////////////////////////////////////

		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void populatePrefixes(int i, String prefix, String description, boolean definiteArticleTag, boolean defArtHE,
			boolean relHE, boolean adverbKAF, boolean subConOrRelSHIN, boolean tempSubConKAFSHIN,
			boolean tempSubConMEMSHIN, boolean tempSubConLAMEDKAFSHIN, boolean relativizerTag, boolean temporalSubConjTag,
			boolean subordinatingConjunctionTag, boolean prefPartUnit, boolean prepBET, boolean prepKAF, boolean prepLAMED,
			boolean prepMEM, boolean prepositionTag, boolean conjunctionTag) {

		String sql = "insert into prefixes values (" + i + "," + prefix + "," + description + "," + definiteArticleTag
				+ "," + defArtHE + "," + relHE + "," + adverbKAF + "," + subConOrRelSHIN + "," + tempSubConKAFSHIN + ","
				+ tempSubConMEMSHIN + "," + tempSubConLAMEDKAFSHIN + "," + relativizerTag + "," + temporalSubConjTag + ","
				+ subordinatingConjunctionTag + "," + prefPartUnit + "," + prepBET + "," + prepKAF + "," + prepLAMED + ","
				+ prepMEM + "," + prepositionTag + "," + conjunctionTag + ")";

		execute(sql);
	}

	public static void main(String[] args) {
		Prefixes p = new Prefixes();
		p.preparePrefixesData();
		System.exit(1);
	}
}
