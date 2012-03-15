/*
 * Created on 10/10/2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package mila.lexicon.dbUtils;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * @author daliabo
 * 
 *         TODO To change the template for this generated type comment go to
 *         Window - Preferences - Java - Code Style - Code Templates
 */
public class Gimatria extends Connected {
	// private String apostropheHandling(String strIn) {
	// String strOut = strIn;
	// //case of single apostrophe
	// int index1 = strIn.indexOf('\'');
	// if (index1 != -1) {
	// if (index1 == 1) {
	// strOut = strIn.charAt(0) + "\\" + strIn.substring(index1);
	// //System.out.println("strOut = " + strOut);
	// } else {
	// strOut = strIn.substring(0, index1) + "\\"
	// + strIn.substring(index1);
	// System.out.println("strOut1 = " + strOut);
	// }
	// }
	// return strOut;
	// }

	public static int get(String input) {
		int val = -1;
		String transliterated = "";
		if (input.indexOf("\"") != -1) {
			input = input.replaceAll("\"", "%22");
		} else if (input.indexOf("\'") != -1) {
			// input = apostropheHandling(input);
			input = input.replaceAll("\'", "\\\\'");
		}
		String sql = "SELECT * FROM gimatria where transliterated ='" + input
				+ "'";
		// System.out.println("sql="+ sql);
		ResultSet rs = null;
		try {
			rs = staticGetData(sql);
			if (rs != null) {
				while (rs.next()) {
					transliterated = rs.getString("transliterated");
					// System.out.println("transliterated ="+transliterated);
					val = rs.getInt("val");
					// System.out.println("val ="+val);
				}
				rs.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			releaseConnection();
		}
		return val;
	}

	public ArrayList getAll() {
		ArrayList array = new ArrayList();
		int val = -1;
		String transliterated = "";

		String sql = "SELECT * FROM gimatria";
		// System.out.println("sql="+ sql);
		ResultSet rs = null;
		try {
			rs = getData(sql);
			if (rs != null) {
				while (rs.next()) {
					transliterated = rs.getString("transliterated");
					array.add(transliterated);
				}
				rs.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			releaseConnection();
		}
		return array;
	}

}
