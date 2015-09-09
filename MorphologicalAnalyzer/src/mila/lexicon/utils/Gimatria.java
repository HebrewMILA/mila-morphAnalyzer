package mila.lexicon.utils;

import java.util.HashMap;

/**
 * Reference implementation of the Chain interface, using a HashMap as the
 * underlying structure.
 */
public class Gimatria {
	static private HashMap<String, String> map = null;

	public Gimatria(int initialCapacity) {
		// Define initial capacity improve performance
		map = new HashMap<String, String>(initialCapacity, 1);
	}

	public void clear() {
		if (!map.isEmpty()) {
			map.clear();
		}
	}

	// there can be only a single matching value
	public int get(String key) {
		int val = -1;
		String stVal = null;
		stVal = (String) map.get(key);
		if (stVal != null)
			val = (Integer.valueOf(stVal)).intValue();
		return val;
	}

	public void put(String key, String g) {
		if (g == null)
			throw new RuntimeException("Bad idea! You tried to insert "
					+ "a null object into a Chain!");
		map.put(key, g);
	}

}
