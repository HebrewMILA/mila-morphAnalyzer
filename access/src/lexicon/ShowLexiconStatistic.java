package lexicon;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ShowLexiconStatistic {

	String lexiconId="";

	static Connection connection=null;

	static ResultSet rs= null;


	int nounCount=0;
	int adjectiveCount=0;
	int verbCount=0;
	int propernameCount=0;
	int existentialCount=0;
	int interjectionCount =0;
	int interrogativeCount=0;
	int modalCount =0;
	int numeralCount=0;
	int quantifierCount=0;
	int pronounCount=0;
	int prepositionCount=0;
	int titleCount=0;
	int copulaCount=0;
	int negationCount=0;
	int wPrefixCount =0;
	int adverbCount=0;
	int conjunctionCount=0;
	int noun_exception_typeCount =0;
	int adjective_exception_typeCount=0;
	int adverb_exception_typeCount=0;
	int interjection_exception_typeCount =0;
	int interrogative_exception_typeCount =0;
	int modal_exception_typeCount =0;
	int numeral_exception_typeCount = 0;
	int preposition_exception_typeCount=0;
	int pronoun_exception_typeCount =0;
	int propername_exception_typeCount =0;
	int quantifier_exception_typeCount =0;
	int verb_exception_typeCount =0;
	int total =0;
	int exceptionTotal = 0;




	static {
		try {

			connection = null;

			Class.forName("org.mariadb.jdbc.Driver").newInstance();
			connection = DriverManager.getConnection(
					"jdbc:mariadb://yeda.cs.technion.ac.il:3306/lexiconP",
					"maya", "@b^F,Mq2y[j");
		} catch (Exception e) {
			e.printStackTrace();
			System.exit(0);
		}
	}


	public void getStatistic() throws SQLException {
		PreparedStatement statement = null;
		statement = connection.prepareStatement("SELECT *  FROM  noun" );
		rs = statement.executeQuery();
		rs.last();
		nounCount = rs.getRow();


		statement = connection.prepareStatement("SELECT *  FROM  adjective" );
		rs = statement.executeQuery();
		rs.last();
		adjectiveCount = rs.getRow();



		statement = connection.prepareStatement("SELECT *  FROM  verb" );
		rs = statement.executeQuery();
		rs.last();
		verbCount = rs.getRow();




		statement = connection.prepareStatement("SELECT *  FROM  propername" );
		rs = statement.executeQuery();
		rs.last();
		propernameCount = rs.getRow();


		statement = connection.prepareStatement("SELECT *  FROM  existential" );
		rs = statement.executeQuery();
		rs.last();
		existentialCount = rs.getRow();


		statement = connection.prepareStatement("SELECT *  FROM  interjection" );
		rs = statement.executeQuery();
		rs.last();
		interjectionCount = rs.getRow();


		statement = connection.prepareStatement("SELECT *  FROM  interrogative" );
		rs = statement.executeQuery();
		rs.last();
		interrogativeCount = rs.getRow();


		statement = connection.prepareStatement("SELECT *  FROM  modal" );
		rs = statement.executeQuery();
		rs.last();
		modalCount = rs.getRow();


		statement = connection.prepareStatement("SELECT *  FROM  numeral" );
		rs = statement.executeQuery();
		rs.last();
		numeralCount = rs.getRow();


		statement = connection.prepareStatement("SELECT *  FROM  quantifier" );
		rs = statement.executeQuery();
		rs.last();
		quantifierCount = rs.getRow();


		statement = connection.prepareStatement("SELECT *  FROM  pronoun" );
		rs = statement.executeQuery();
		rs.last();
		pronounCount = rs.getRow();

		statement = connection.prepareStatement("SELECT *  FROM  preposition" );
		rs = statement.executeQuery();
		rs.last();
		prepositionCount = rs.getRow();

		statement = connection.prepareStatement("SELECT *  FROM  title" );
		rs = statement.executeQuery();
		rs.last();
		titleCount = rs.getRow();


		statement = connection.prepareStatement("SELECT *  FROM  copula" );
		rs = statement.executeQuery();
		rs.last();
		copulaCount = rs.getRow();


		statement = connection.prepareStatement("SELECT *  FROM  item where pos='negation'" );
		rs = statement.executeQuery();
		rs.last();
		negationCount = rs.getRow();



		statement = connection.prepareStatement("SELECT *  FROM  item where pos='wPrefix'" );
		rs = statement.executeQuery();
		rs.last();
		wPrefixCount = rs.getRow();


		statement = connection.prepareStatement("SELECT *  FROM  adverb" );
		rs = statement.executeQuery();
		rs.last();
		adverbCount = rs.getRow();


		statement = connection.prepareStatement("SELECT *  FROM  conjunction" );
		rs = statement.executeQuery();
		rs.last();
		conjunctionCount = rs.getRow();


		statement = connection.prepareStatement("SELECT *  FROM  item,noun_exception_type where item.id=noun_exception_type.id and item.pos='noun'" );
		rs.last();
		rs = statement.executeQuery();
		noun_exception_typeCount = rs.getRow();


		statement = connection.prepareStatement("SELECT *  FROM  item,noun_exception_type where item.id=noun_exception_type.id and item.pos='adjective'" );
		rs = statement.executeQuery();
		rs.last();
		adjective_exception_typeCount = rs.getRow();


		statement = connection.prepareStatement("SELECT *  FROM  adverb_exception_type" );
		rs = statement.executeQuery();
		rs.last();
		adverb_exception_typeCount = rs.getRow();


		statement = connection.prepareStatement("SELECT *  FROM  interjection_exception_type" );
		rs = statement.executeQuery();
		rs.last();
		interjection_exception_typeCount = rs.getRow();


		statement = connection.prepareStatement("SELECT *  FROM  interrogative_exception_type" );
		rs = statement.executeQuery();
		rs.last();
		interrogative_exception_typeCount = rs.getRow();


		statement = connection.prepareStatement("SELECT *  FROM  modal_exception_type" );
		rs = statement.executeQuery();
		rs.last();
		modal_exception_typeCount = rs.getRow();


		statement = connection.prepareStatement("SELECT *  FROM  numeral_exception_type" );
		rs = statement.executeQuery();
		rs.last();
		numeral_exception_typeCount = rs.getRow();


		statement = connection.prepareStatement("SELECT *  FROM  preposition_exception_type" );
		rs = statement.executeQuery();
		rs.last();
		preposition_exception_typeCount = rs.getRow();


		statement = connection.prepareStatement("SELECT *  FROM  pronoun_exception_type" );
		rs = statement.executeQuery();
		rs.last();
		pronoun_exception_typeCount = rs.getRow();


		statement = connection.prepareStatement("SELECT *  FROM  propername_exception_type" );
		rs = statement.executeQuery();
		rs.last();
		propername_exception_typeCount = rs.getRow();


		statement = connection.prepareStatement("SELECT *  FROM  quantifier_exception_type" );
		rs = statement.executeQuery();
		rs.last();
		quantifier_exception_typeCount = rs.getRow();


		statement = connection.prepareStatement("SELECT *  FROM  verb_exception_type" );
		rs = statement.executeQuery();
		rs.last();
		verb_exception_typeCount = rs.getRow();


		total = nounCount + adjectiveCount + adverbCount + conjunctionCount + existentialCount + interjectionCount +
		interrogativeCount + modalCount + negationCount + numeralCount + pronounCount + propernameCount + prepositionCount +
		quantifierCount + verbCount + wPrefixCount;

		exceptionTotal = adjective_exception_typeCount + adverb_exception_typeCount + interjection_exception_typeCount +
		interrogative_exception_typeCount + modal_exception_typeCount + noun_exception_typeCount + numeral_exception_typeCount +
		pronoun_exception_typeCount + preposition_exception_typeCount + propername_exception_typeCount + quantifier_exception_typeCount + verb_exception_typeCount;


	}




 public static void releaseConnection() {

		 try {
			rs.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	 }


	public static void main(String[] args) {
		ShowLexiconStatistic s = new ShowLexiconStatistic();

		try {
			s.getStatistic();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

//		ShowInflections s = new ShowInflections();
//		Vector infinitiveVec = new Vector();
//		s.getBeinoniInflections("4241", infinitiveVec);
//		System.out.println();
	}






	public String getLexiconId() {
		return lexiconId;
	}






	public static Connection getConnection() {
		return connection;
	}






	public static ResultSet getRs() {
		return rs;
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
