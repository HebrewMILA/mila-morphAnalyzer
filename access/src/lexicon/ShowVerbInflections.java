package lexicon;

import java.net.URLDecoder;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

public class ShowVerbInflections {

    String lexiconId = "";

    static Connection connection = null;

    static ResultSet rs = null;

    static {
	try {

	    connection = null;

	    Class.forName("org.mariadb.jdbc.Driver").newInstance();
	    // connection = DriverManager.getConnection(
	    // "jdbc:mariadb://yeda.cs.technion.ac.il:3306/generatorTest",
	    // "dandy", "yachuF6baqetREJa");
	    // connection = DriverManager.getConnection(
	    // "jdbc:mariadb://yeda.cs.technion.ac.il:3306/generator",
	    // "dummy1", "health&happiness");
	} catch (Exception e) {
	    e.printStackTrace();
	    System.exit(0);
	}
    }

    public ShowVerbInflections(String lexiconId) {
	this.lexiconId = lexiconId;

    }

    private String buildPGN(String gender, String number, String person) {
	String PGN = "";
	if (gender.equals("masculine and feminine")
		&& number.equals("singular") && person.equals("1")) {
	    PGN = "1p/MF/Sg";
	} else if (gender.equals("masculine") && number.equals("singular")
		&& person.equals("2")) {
	    PGN = "2p/M/Sg";
	} else if (gender.equals("feminine") && number.equals("singular")
		&& person.equals("2")) {
	    PGN = "2p/F/Sg";
	} else if (gender.equals("masculine") && number.equals("singular")
		&& person.equals("3")) {
	    PGN = "3p/M/Sg";
	} else if (gender.equals("feminine") && number.equals("singular")
		&& person.equals("3")) {
	    PGN = "3p/F/Sg";
	} else if (gender.equals("masculine and feminine")
		&& number.equals("plural") && person.equals("1")) {
	    PGN = "1p/MF/Pl";
	} else if (gender.equals("masculine") && number.equals("plural")
		&& person.equals("2")) {
	    PGN = "2p/M/Pl";
	} else if (gender.equals("feminine") && number.equals("plural")
		&& person.equals("2")) {
	    PGN = "2p/F/Pl";
	} else if (gender.equals("masculine and feminine")
		&& number.equals("plural") && person.equals("2")) {
	    PGN = "2p/MF/Pl";
	} else if (gender.equals("masculine and feminine")
		&& number.equals("plural") && person.equals("3")) {
	    PGN = "3p/MF/Pl";
	} else if (gender.equals("feminine") && number.equals("plural")
		&& person.equals("3")) {
	    PGN = "3p/F/Pl";
	}

	return PGN;
    }

    public String getPastInflections(Vector<String> pastVec) {
	String decodedPast = "";

	try {
	    // initializeVec(10, pastVec);
	    connection = DriverManager.getConnection(
		    "jdbc:mariadb://yeda.cs.technion.ac.il:3306/generator",
		    "dummy1", "health&happiness");
	    PreparedStatement statement = null;
	    statement = connection
		    .prepareStatement("SELECT *  FROM inflections where register='formal' and spelling = 'standard' and baseLexiconPointer= ? and tense='past' order by id");
	    statement.setObject(1, lexiconId);
	    rs = statement.executeQuery();

	    while (rs.next()) {
		String surface = rs.getString("surface");
		// System.out.println("surface=" + surface);
		String number = rs.getString("baseNumber");
		String gender = rs.getString("baseGender");
		String person = rs.getString("basePerson");
		String PGN = buildPGN(gender, number, person);
		// System.out.println("PGN="+PGN);
		if (PGN.equals("1p/MF/Sg")) {
		    String temp = "";
		    if (pastVec.size() >= 1) {
				temp = pastVec.elementAt(0);
			}
		    if (pastVec.size() == 0) {
				pastVec.add(0, URLDecoder.decode(surface, "UTF-8"));
			} else {
				pastVec.set(
					0,
					temp + ","
						+ URLDecoder.decode(surface, "UTF-8"));
			}
		} else if (PGN.equals("2p/M/Sg")) {
			pastVec.add(1, URLDecoder.decode(surface, "UTF-8"));
		} else if (PGN.equals("2p/F/Sg")) {
			pastVec.add(2, URLDecoder.decode(surface, "UTF-8"));
		} else if (PGN.equals("3p/M/Sg")) {
			pastVec.add(3, URLDecoder.decode(surface, "UTF-8"));
		} else if (PGN.equals("3p/F/Sg")) {
			pastVec.add(4, URLDecoder.decode(surface, "UTF-8"));
		} else if (PGN.equals("1p/MF/Pl")) {
			pastVec.add(5, URLDecoder.decode(surface, "UTF-8"));
		} else if (PGN.equals("2p/M/Pl")) {
			pastVec.add(6, URLDecoder.decode(surface, "UTF-8"));
		} else if (PGN.equals("2p/F/Pl")) {
			pastVec.add(7, URLDecoder.decode(surface, "UTF-8"));
		} else if (PGN.equals("3p/MF/Pl")) {
		    pastVec.add(8, URLDecoder.decode(surface, "UTF-8"));
		    pastVec.add(9, URLDecoder.decode(surface, "UTF-8"));
		}

		if (!decodedPast.equals("")) {
			decodedPast = decodedPast + ", "
			    + URLDecoder.decode(surface, "UTF-8");
		} else {
			decodedPast = URLDecoder.decode(surface, "UTF-8");
		}
	    }
	    rs.close();
	    statement.close();
	    connection.close();

	} catch (Exception e) {
	    System.out.println(e.getMessage());
	}

	return decodedPast;
    }

    public Vector<String> getFutureInflections() {
	Vector<String> futureVec = new Vector<String>();
	// initializeVec(10, futureVec);
	try {
	    connection = DriverManager.getConnection(
		    "jdbc:mariadb://yeda.cs.technion.ac.il:3306/generator",
		    "dummy1", "health&happiness");
	    PreparedStatement statement = null;
	    statement = connection
		    .prepareStatement("SELECT *  FROM inflections where register='formal' and spelling = 'standard' and baseLexiconPointer= ? and tense='future'  order by id");
	    statement.setObject(1, lexiconId);
	    rs = statement.executeQuery();

	    while (rs.next()) {
		String surface = rs.getString("surface");

		String number = rs.getString("baseNumber");
		String gender = rs.getString("baseGender");
		String person = rs.getString("basePerson");
		String PGN = buildPGN(gender, number, person);
		if (PGN.equals("1p/MF/Sg")) {
		    String temp = "";
		    if (futureVec.size() >= 1) {
				temp = futureVec.elementAt(0);
			}
		    if (futureVec.size() == 0) {
				futureVec.add(0, URLDecoder.decode(surface, "UTF-8"));
			} else {
				futureVec.set(
					0,
					temp + ","
						+ URLDecoder.decode(surface, "UTF-8"));
			}
		} else if (PGN.equals("2p/M/Sg")) {
		    String temp = "";
		    if (futureVec.size() >= 2) {
				temp = futureVec.elementAt(1);
			}
		    if (futureVec.size() == 1) {
				futureVec.add(1, URLDecoder.decode(surface, "UTF-8"));
			} else {
				futureVec.set(
					1,
					temp + ","
						+ URLDecoder.decode(surface, "UTF-8"));
			}
		} else if (PGN.equals("2p/F/Sg")) {
		    String temp = "";
		    if (futureVec.size() >= 3) {
				temp = futureVec.elementAt(2);
			}
		    if (futureVec.size() == 2) {
				futureVec.add(2, URLDecoder.decode(surface, "UTF-8"));
			} else {
				futureVec.set(
					2,
					temp + ","
						+ URLDecoder.decode(surface, "UTF-8"));
			}
		} else if (PGN.equals("3p/M/Sg")) {
		    String temp = "";
		    if (futureVec.size() >= 4) {
				temp = futureVec.elementAt(3);
			}
		    if (futureVec.size() == 3) {
				futureVec.add(3, URLDecoder.decode(surface, "UTF-8"));
			} else {
				futureVec.set(
					3,
					temp + ","
						+ URLDecoder.decode(surface, "UTF-8"));
			}
		} else if (PGN.equals("3p/F/Sg")) {
		    String temp = "";
		    if (futureVec.size() >= 5) {
				temp = futureVec.elementAt(4);
			}
		    if (futureVec.size() == 4) {
				futureVec.add(4, URLDecoder.decode(surface, "UTF-8"));
			} else {
				futureVec.set(
					4,
					temp + ","
						+ URLDecoder.decode(surface, "UTF-8"));
			}
		} else if (PGN.equals("1p/MF/Pl")) {
		    String temp = "";
		    if (futureVec.size() >= 6) {
				temp = futureVec.elementAt(5);
			}
		    if (futureVec.size() == 5) {
				futureVec.add(5, URLDecoder.decode(surface, "UTF-8"));
			} else {
				futureVec.set(
					5,
					temp + ","
						+ URLDecoder.decode(surface, "UTF-8"));
			}

		} else if (PGN.equals("2p/MF/Pl")) {
		    String temp = "";
		    if (futureVec.size() >= 7) {
				temp = futureVec.elementAt(6);
			}
		    if (futureVec.size() == 6) {
				futureVec.add(6, URLDecoder.decode(surface, "UTF-8"));
			} else {
				futureVec.set(
					6,
					temp + ","
						+ URLDecoder.decode(surface, "UTF-8"));
			}

		} else if (PGN.equals("2p/F/Pl")) {
		    String temp = "";
		    if (futureVec.size() >= 8) {
				temp = futureVec.elementAt(7);
			}
		    if (futureVec.size() == 7) {
				futureVec.add(7, URLDecoder.decode(surface, "UTF-8"));
			} else {
				futureVec.set(
					7,
					temp + ","
						+ URLDecoder.decode(surface, "UTF-8"));
			}

		} else if (PGN.equals("3p/MF/Pl")) {
		    String temp = "";
		    if (futureVec.size() >= 9) {
				temp = futureVec.elementAt(8);
			}
		    if (futureVec.size() == 8) {
				futureVec.add(8, URLDecoder.decode(surface, "UTF-8"));
			} else {
				futureVec.set(
					8,
					temp + ","
						+ URLDecoder.decode(surface, "UTF-8"));
			}

		} else if (PGN.equals("3p/F/Pl")) {
		    String temp = "";
		    if (futureVec.size() >= 10) {
				temp = futureVec.elementAt(9);
			}
		    if (futureVec.size() == 9) {
				futureVec.add(9, URLDecoder.decode(surface, "UTF-8"));
			} else {
				futureVec.set(
					9,
					temp + ","
						+ URLDecoder.decode(surface, "UTF-8"));
			}
		}
	    }
	    rs.close();
	    statement.close();
	    connection.close();
	} catch (Exception e) {
	    System.out.println(e.getMessage());
	}
	return futureVec;
    }

    // public Vector<String> getFutureInflections() {
    // Vector<String> futureVec = new Vector<String>();
    // try {
    // connection = DriverManager.getConnection(
    // "jdbc:mariadb://yeda.cs.technion.ac.il:3306/generator",
    // "dummy1", "health&happiness");
    // PreparedStatement statement = null;
    // statement = connection
    // .prepareStatement("SELECT *  FROM inflections where script='formal' and baseLexiconPointer= ? and tense='future'");
    // statement.setObject(1, lexiconId);
    // rs = statement.executeQuery();
    //
    // int count = 0;
    // while (rs.next()) {
    // count++;
    // String surface = rs.getString("surface");
    //
    // String number = rs.getString("baseNumber");
    // String gender = rs.getString("baseGender");
    // String person = rs.getString("basePerson");
    // String PGN = buildPGN(gender, number, person);
    // if (PGN.equals("1p/MF/Sg")) {
    // String temp = "";
    // if (futureVec.size() >= 1)
    // temp = (String) futureVec.elementAt(0);
    // if (futureVec.size() == 0)
    // futureVec.add(0, URLDecoder.decode(surface, "UTF-8"));
    // else
    // futureVec.set(0, temp + ","
    // + URLDecoder.decode(surface, "UTF-8"));
    // } else if (PGN.equals("2p/M/Sg")) {
    // String temp = "";
    // if (futureVec.size() >= 2)
    // temp = (String) futureVec.elementAt(1);
    // if (futureVec.size() == 1)
    // futureVec.add(1, URLDecoder.decode(surface, "UTF-8"));
    // else
    // futureVec.set(1, temp + ","
    // + URLDecoder.decode(surface, "UTF-8"));
    // } else if (PGN.equals("2p/F/Sg")) {
    // String temp = "";
    // if (futureVec.size() >= 3)
    // temp = (String) futureVec.elementAt(2);
    // if (futureVec.size() == 2)
    // futureVec.add(2, URLDecoder.decode(surface, "UTF-8"));
    // else
    // futureVec.set(2, temp + ","
    // + URLDecoder.decode(surface, "UTF-8"));
    // } else if (PGN.equals("3p/M/Sg") || PGN.equals("3p/MF/Sg")) {
    // String temp = "";
    // if (futureVec.size() >= 4)
    // temp = (String) futureVec.elementAt(3);
    // if (futureVec.size() == 3)
    // futureVec.add(3, URLDecoder.decode(surface, "UTF-8"));
    // else
    // futureVec.set(3, temp + ","
    // + URLDecoder.decode(surface, "UTF-8"));
    // } else if (PGN.equals("3p/F/Sg")) {
    // String temp = "";
    // if (futureVec.size() >= 5)
    // temp = (String) futureVec.elementAt(4);
    // if (futureVec.size() == 4)
    // futureVec.add(4, URLDecoder.decode(surface, "UTF-8"));
    // else
    // futureVec.set(4, temp + ","
    // + URLDecoder.decode(surface, "UTF-8"));
    // } else if (PGN.equals("1p/MF/Pl")) {
    // String temp = "";
    // if (futureVec.size() >= 6)
    // temp = (String) futureVec.elementAt(5);
    // if (futureVec.size() == 5)
    // futureVec.add(5, URLDecoder.decode(surface, "UTF-8"));
    // else
    // futureVec.set(5, temp + ","
    // + URLDecoder.decode(surface, "UTF-8"));
    //
    // } else if (PGN.equals("2p/MF/Pl")) {
    // String temp = "";
    // if (futureVec.size() >= 7)
    // temp = (String) futureVec.elementAt(6);
    // if (futureVec.size() == 6)
    // futureVec.add(6, URLDecoder.decode(surface, "UTF-8"));
    // else
    // futureVec.set(6, temp + ","
    // + URLDecoder.decode(surface, "UTF-8"));
    //
    // } else if (PGN.equals("2p/F/Pl")) {
    // String temp = "";
    // if (futureVec.size() >= 8)
    // temp = (String) futureVec.elementAt(7);
    // if (futureVec.size() == 7)
    // futureVec.add(7, URLDecoder.decode(surface, "UTF-8"));
    // else
    // futureVec.set(7, temp + ","
    // + URLDecoder.decode(surface, "UTF-8"));
    //
    // } else if (PGN.equals("3p/MF/Pl")) {
    // String temp = "";
    // if (futureVec.size() >= 9)
    // temp = (String) futureVec.elementAt(8);
    // if (futureVec.size() == 8)
    // futureVec.add(8, URLDecoder.decode(surface, "UTF-8"));
    // else
    // futureVec.set(8, temp + ","
    // + URLDecoder.decode(surface, "UTF-8"));
    //
    // } else if (PGN.equals("3p/F/Pl")) {
    // String temp = "";
    // if (futureVec.size() >= 10)
    // temp = (String) futureVec.elementAt(9);
    // if (futureVec.size() == 9)
    // futureVec.add(9, URLDecoder.decode(surface, "UTF-8"));
    // else
    // futureVec.set(9, temp + ","
    // + URLDecoder.decode(surface, "UTF-8"));
    // }
    // }
    // rs.close();
    // statement.close();
    // connection.close();
    // } catch (Exception e) {
    // System.out.println(e.getMessage());
    // }
    // return futureVec;
    // }

    public String getImperativeInflections(Vector<String> imperativeVec) {
	String decodedImperative = "";

	try {
	    connection = DriverManager.getConnection(
		    "jdbc:mariadb://yeda.cs.technion.ac.il:3306/generator",
		    "dummy1", "health&happiness");
	    PreparedStatement statement = null;
	    statement = connection
		    .prepareStatement("SELECT *  FROM inflections where register='formal' and spelling = 'standard'"
			    + " and baseLexiconPointer= ? and tense='imperative'  order by id");
	    statement.setObject(1, lexiconId);
	    ResultSet rs = statement.executeQuery();
	    while (rs.next()) {
		String surface = rs.getString("surface");
		imperativeVec.add(URLDecoder.decode(surface, "UTF-8"));
		if (!decodedImperative.equals("")) {
			decodedImperative = decodedImperative + ", "
			    + URLDecoder.decode(surface, "UTF-8");
		} else {
			decodedImperative = URLDecoder.decode(surface, "UTF-8");
		}
	    }
	    rs.close();
	    statement.close();
	    connection.close();
	} catch (Exception e) {
	    System.out.println(e.getMessage());
	}
	return decodedImperative;
    }

    public String getBeinoniInflections(Vector<String> beinoniVec) {
	String decodedBeinoni = "";
	try {
	    connection = DriverManager.getConnection(
		    "jdbc:mariadb://yeda.cs.technion.ac.il:3306/generator",
		    "dummy1", "health&happiness");
	    PreparedStatement statement = null;
	    // Dalia לתקן יצירה של יוצאי דופן בינוני - יוצא עם PGN מאוכלס ולכן
	    // השאילתא כמו שהיא
	    statement = connection
		    .prepareStatement("SELECT *  FROM inflections where"
			    + " register='formal' and spelling = 'standard' and  baseDefinitness='tf' and suffixStatus='false' and baseLexiconPointer= ? and tense='beinoni' and "
			    + " (PGN='unspecified' or PGN like '123%') and basePos='participle' and type='verb' order by id");
	    statement.setObject(1, lexiconId);
	    ResultSet rs = statement.executeQuery();
	    while (rs.next()) {
		String surface = rs.getString("surface");
		beinoniVec.add(URLDecoder.decode(surface, "UTF-8"));
		if (!decodedBeinoni.equals("")) {
			decodedBeinoni = decodedBeinoni + ", "
			    + URLDecoder.decode(surface, "UTF-8");
		} else {
			decodedBeinoni = URLDecoder.decode(surface, "UTF-8");
		}
	    }
	    rs.close();
	    statement.close();
	    connection.close();
	} catch (Exception e) {
	    System.out.println(e.getMessage());
	}
	return decodedBeinoni;
    }

    public String getPassiveInflections(Vector<String> passiveVec) {
	String decodedPassive = "";
	try {
	    connection = DriverManager.getConnection(
		    "jdbc:mariadb://yeda.cs.technion.ac.il:3306/generator",
		    "dummy1", "health&happiness");
	    PreparedStatement statement = null;
	    statement = connection
		    .prepareStatement("SELECT *  FROM inflections where  register='formal' and spelling = 'standard' and  "
			    + "baseDefinitness!='tt' and suffixStatus='false' and"
			    + " baseLexiconPointer=? and tense='beinoni' and "
			    + " PGN='unspecified' and basePos='passiveParticiple' order by id");
	    statement.setObject(1, lexiconId);
	    ResultSet rs = statement.executeQuery();
	    while (rs.next()) {
		String surface = rs.getString("surface");
		passiveVec.add(URLDecoder.decode(surface, "UTF-8"));
		if (!decodedPassive.equals("")) {
			decodedPassive = decodedPassive + ", "
			    + URLDecoder.decode(surface, "UTF-8");
		} else {
			decodedPassive = URLDecoder.decode(surface, "UTF-8");
		}
	    }
	    rs.close();
	    statement.close();
	    connection.close();
	} catch (Exception e) {
	    System.out.println(e.getMessage());
	}
	return decodedPassive;
    }

    public String getInfinitiveInflections(Vector<String> infinitiveVec) {
	String decodedInfinitivePGNSingular = "";
	try {
	    connection = DriverManager.getConnection(
		    "jdbc:mariadb://yeda.cs.technion.ac.il:3306/generator",
		    "dummy1", "health&happiness");
	    PreparedStatement statement = null;
	    statement = connection
		    .prepareStatement("SELECT *  FROM inflections where register='formal' and spelling = 'standard' "
			    + " and suffixFunction!='unspecified' and baseLexiconPointer= ? and tense='infinitive' and transliterated like 'b%'  order by id");
	    statement.setObject(1, lexiconId);

	    ResultSet rs = statement.executeQuery();
	    while (rs.next()) {
		String surface = rs.getString("surface");

		String baseTransliteratedLItem = rs
			.getString("baseTransliteratedLItem");
		String transliterated = rs.getString("transliterated");
		if (baseTransliteratedLItem.startsWith("b") && transliterated
			.startsWith("bb")
			|| !baseTransliteratedLItem.startsWith("b")) {
		    infinitiveVec.add(URLDecoder.decode(surface, "UTF-8"));
		    if (!decodedInfinitivePGNSingular.equals("")) {
				decodedInfinitivePGNSingular = decodedInfinitivePGNSingular
					+ ", " + URLDecoder.decode(surface, "UTF-8");
			} else {
				decodedInfinitivePGNSingular = URLDecoder.decode(
					surface, "UTF-8");
			}
		}
	    }
	    rs.close();
	    statement.close();
	    connection.close();
	} catch (Exception e) {
	    System.out.println(e.getMessage());
	}
	return decodedInfinitivePGNSingular;
    }

    public String getDecodedInfinitive() {
	String decodedInfinitive = "";
	try {
	    connection = DriverManager.getConnection(
		    "jdbc:mariadb://yeda.cs.technion.ac.il:3306/generator",
		    "dummy1", "health&happiness");
	    PreparedStatement statement = null;
	    statement = connection
		    .prepareStatement("SELECT * FROM inflections where "
			    + " register='formal' and spelling = 'standard' and suffixFunction='unspecified' and "
			    + "baseLexiconPointer= ? and tense='infinitive' and "
			    + " transliterated like 'l%' ");
	    statement.setObject(1, lexiconId);
	    ResultSet rs = statement.executeQuery();
	    while (rs.next()) {
		String surface = rs.getString("surface");
		String baseTransliteratedLItem = rs
			.getString("baseTransliteratedLItem");
		String transliterated = rs.getString("transliterated");
		if (baseTransliteratedLItem.startsWith("l") && transliterated
			.startsWith("ll")
			|| !baseTransliteratedLItem.startsWith("l")) {
		    if (!decodedInfinitive.equals("")) {
				decodedInfinitive = decodedInfinitive + ", "
					+ URLDecoder.decode(surface, "UTF-8");
			} else {
				decodedInfinitive = URLDecoder.decode(surface, "UTF-8");
			}
		}
	    }
	    rs.close();
	    statement.close();
	    connection.close();
	} catch (Exception e) {
	    System.out.println(e.getMessage());
	}
	return decodedInfinitive;
    }

    public String getDecodedBareInfinitive() {
	String decodedBareInfinitive = "";
	try {
	    connection = DriverManager.getConnection(
		    "jdbc:mariadb://yeda.cs.technion.ac.il:3306/generator",
		    "dummy1", "health&happiness");
	    PreparedStatement statement = null;
	    statement = connection
		    .prepareStatement("SELECT * FROM inflections where "
			    + " register='formal' and spelling = 'standard' and baseLexiconPointer= ? and tense='bareInfinitive' ");
	    statement.setObject(1, lexiconId);
	    ResultSet rs = statement.executeQuery();
	    while (rs.next()) {
		String surface = rs.getString("surface");
		decodedBareInfinitive = URLDecoder.decode(surface, "UTF-8");
	    }
	    rs.close();
	    statement.close();
	    connection.close();
	} catch (Exception e) {
	    System.out.println(e.getMessage());
	}
	return decodedBareInfinitive;
    }

    public static void releaseConnection() {

	try {
	    rs.close();
	} catch (SQLException e) {
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	}
    }
}
