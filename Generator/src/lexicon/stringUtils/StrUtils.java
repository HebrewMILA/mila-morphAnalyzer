/*
 * Created on 04/07/2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package lexicon.stringUtils;

import java.util.HashMap;
import java.util.Vector;

/**
 * @author daliabo
 *
 *         TODO To change the template for this generated type comment go to
 *         Window - Preferences - Java - Code Style - Code Templates
 */
public class StrUtils {
	static Vector vec = new Vector();

	public static String chomp(String origSt, int num) {
		int len = origSt.length();
		String chopedSt = origSt.substring(0, len - num);
		// System.out.println("chopedS=" + chopedSt);
		return chopedSt;
	}

	private static HashMap genderMap;
	private static HashMap numberMap;

	private static void initGenderMap() {
		genderMap = new HashMap();
		genderMap.put("F", "feminine");
		genderMap.put("M", "masculine");
		genderMap.put("MF", "masculine and feminine");
	}

	private static void initNumberMap() {
		numberMap = new HashMap();
		numberMap.put("Sg", "singular");
		numberMap.put("Pl", "plural");
	}

	public static String getGenderMap(String gender) {
		if (genderMap == null) {
			initGenderMap();
		}
		return (String) genderMap.get(gender);
	}

	public static String getNumberMap(String number) {
		if (numberMap == null) {
			initNumberMap();
		}
		return (String) numberMap.get(number);
	}

}