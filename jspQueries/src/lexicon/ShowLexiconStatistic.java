package lexicon;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;

import sql.SqlDB;

public class ShowLexiconStatistic {
	private final int nounCount;
	private final int adjectiveCount;
	private final int verbCount;
	private final int propernameCount;
	private final int existentialCount;
	private final int interjectionCount;
	private final int interrogativeCount;
	private final int modalCount;
	private final int numeralCount;
	private final int quantifierCount;
	private final int pronounCount;
	private final int prepositionCount;
	private final int titleCount;
	private final int copulaCount;
	private final int negationCount;
	private final int wPrefixCount;
	private final int adverbCount;
	private final int conjunctionCount;
	private final int noun_exception_typeCount;
	private final int adjective_exception_typeCount;
	private final int adverb_exception_typeCount;
	private final int interjection_exception_typeCount;
	private final int interrogative_exception_typeCount;
	private final int modal_exception_typeCount;
	private final int numeral_exception_typeCount;
	private final int preposition_exception_typeCount;
	private final int pronoun_exception_typeCount;
	private final int propername_exception_typeCount;
	private final int quantifier_exception_typeCount;
	private final int verb_exception_typeCount;
	private final int total;
	private final int exceptionTotal;

	int count(Connection conn, String q) {
		try (PreparedStatement statement = conn.prepareStatement(q);
				ResultSet rs = statement.executeQuery()) {
			rs.last();
			return rs.getRow();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}

	ShowLexiconStatistic() {
		Connection conn = SqlDB.lexiconP.getConnection(SqlDB.User.maya);
		nounCount = count(conn, "SELECT * FROM noun");
		adjectiveCount = count(conn, "SELECT * FROM adjective");
		verbCount = count(conn, "SELECT * FROM verb");
		propernameCount = count(conn, "SELECT * FROM propername");
		existentialCount = count(conn, "SELECT * FROM existential");
		interjectionCount = count(conn, "SELECT * FROM interjection");
		interrogativeCount = count(conn, "SELECT * FROM interrogative");
		modalCount = count(conn, "SELECT * FROM modal");
		numeralCount = count(conn, "SELECT * FROM numeral");
		quantifierCount = count(conn, "SELECT * FROM quantifier");
		pronounCount = count(conn, "SELECT * FROM pronoun");
		prepositionCount = count(conn, "SELECT * FROM preposition");
		titleCount = count(conn, "SELECT * FROM title");
		copulaCount = count(conn, "SELECT * FROM copula");
		negationCount = count(conn, "SELECT * FROM item where pos='negation'");
		wPrefixCount = count(conn, "SELECT * FROM item where pos='wPrefix'");
		adverbCount = count(conn, "SELECT * FROM adverb");
		conjunctionCount = count(conn, "SELECT * FROM conjunction");
		noun_exception_typeCount = count(conn,
				"SELECT * FROM item,noun_exception_type where item.id=noun_exception_type.id and item.pos='noun'");
		adjective_exception_typeCount = count(conn,
				"SELECT * FROM item,noun_exception_type where item.id=noun_exception_type.id and item.pos='adjective'");
		adverb_exception_typeCount = count(conn,
				"SELECT * FROM adverb_exception_type");
		interjection_exception_typeCount = count(conn,
				"SELECT * FROM interjection_exception_type");
		interrogative_exception_typeCount = count(conn,
				"SELECT * FROM interrogative_exception_type");
		modal_exception_typeCount = count(conn,
				"SELECT * FROM modal_exception_type");
		numeral_exception_typeCount = count(conn,
				"SELECT * FROM numeral_exception_type");
		preposition_exception_typeCount = count(conn,
				"SELECT * FROM preposition_exception_type");
		pronoun_exception_typeCount = count(conn,
				"SELECT * FROM pronoun_exception_type");
		propername_exception_typeCount = count(conn,
				"SELECT * FROM propername_exception_type");
		quantifier_exception_typeCount = count(conn,
				"SELECT * FROM quantifier_exception_type");
		verb_exception_typeCount = count(conn,
				"SELECT * FROM verb_exception_type");

		total = nounCount + adjectiveCount + adverbCount + conjunctionCount
				+ existentialCount + interjectionCount + interrogativeCount
				+ modalCount + negationCount + numeralCount + pronounCount
				+ propernameCount + prepositionCount + quantifierCount
				+ verbCount + wPrefixCount;

		exceptionTotal = adjective_exception_typeCount
				+ adverb_exception_typeCount + interjection_exception_typeCount
				+ interrogative_exception_typeCount + modal_exception_typeCount
				+ noun_exception_typeCount + numeral_exception_typeCount
				+ pronoun_exception_typeCount + preposition_exception_typeCount
				+ propername_exception_typeCount
				+ quantifier_exception_typeCount + verb_exception_typeCount;

		try {
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void getStatistic() throws SQLException {
	}

	public static void releaseConnection() {
	}

	public static void main(String[] args) {
		ShowLexiconStatistic s = new ShowLexiconStatistic();

		try {
			s.getStatistic();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// ShowInflections s = new ShowInflections();
		// Vector infinitiveVec = new Vector();
		// s.getBeinoniInflections("4241", infinitiveVec);
		// System.out.println();
	}

	public int getNounCount() {
		return nounCount;
	}

	public int getAdjectiveCount() {
		return adjectiveCount;
	}

	public int getVerbCount() {
		return verbCount;
	}

	public int getPropernameCount() {
		return propernameCount;
	}

	public int getExistentialCount() {
		return existentialCount;
	}

	public int getInterjectionCount() {
		return interjectionCount;
	}

	public int getInterrogativeCount() {
		return interrogativeCount;
	}

	public int getModalCount() {
		return modalCount;
	}

	public int getNumeralCount() {
		return numeralCount;
	}

	public int getQuantifierCount() {
		return quantifierCount;
	}

	public int getPronounCount() {
		return pronounCount;
	}

	public int getPrepositionCount() {
		return prepositionCount;
	}

	public int getTitleCount() {
		return titleCount;
	}

	public int getCopulaCount() {
		return copulaCount;
	}

	public int getNegationCount() {
		return negationCount;
	}

	public int getWPrefixCount() {
		return wPrefixCount;
	}

	public int getAdverbCount() {
		return adverbCount;
	}

	public int getConjunctionCount() {
		return conjunctionCount;
	}

	public int getNoun_exception_typeCount() {
		return noun_exception_typeCount;
	}

	public int getAdjective_exception_typeCount() {
		return adjective_exception_typeCount;
	}

	public int getAdverb_exception_typeCount() {
		return adverb_exception_typeCount;
	}

	public int getInterjection_exception_typeCount() {
		return interjection_exception_typeCount;
	}

	public int getInterrogative_exception_typeCount() {
		return interrogative_exception_typeCount;
	}

	public int getModal_exception_typeCount() {
		return modal_exception_typeCount;
	}

	public int getNumeral_exception_typeCount() {
		return numeral_exception_typeCount;
	}

	public int getPreposition_exception_typeCount() {
		return preposition_exception_typeCount;
	}

	public int getPronoun_exception_typeCount() {
		return pronoun_exception_typeCount;
	}

	public int getPropername_exception_typeCount() {
		return propername_exception_typeCount;
	}

	public int getQuantifier_exception_typeCount() {
		return quantifier_exception_typeCount;
	}

	public int getVerb_exception_typeCount() {
		return verb_exception_typeCount;
	}

	public int getTotal() {
		return total;
	}

	public int getExceptionTotal() {
		return exceptionTotal;
	}

}
