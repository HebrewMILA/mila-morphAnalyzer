package lexicon.utils;

import java.util.HashMap;

public class MWEinflections {
	static private HashMap map = null;

	public MWEinflections(int initialCapacity) {
		// Define initial capacity improve performance
		map = new HashMap(initialCapacity, 1);
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
			throw new RuntimeException(
					"Bad idea! You tried to insert a null object into a Chain!");
		map.put(key, g);
	}

}
