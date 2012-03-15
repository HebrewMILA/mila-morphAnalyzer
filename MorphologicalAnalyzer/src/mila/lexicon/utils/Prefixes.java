package mila.lexicon.utils;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Reference implementation of the Chain interface, using a HashMap as the
 * underlying structure.
 */
public class Prefixes {
	static private HashMap map = null;

	public Prefixes(int initialCapacity) {
		// Define initial capacity improve performance
		map = new HashMap(initialCapacity, 1);
	}

	public void clear() {
		if (!map.isEmpty()) {
			map.clear();
		}
	}

	public ArrayList get(String key) {
		// System.out.println("(F) get("+key+")");
		ArrayList existingChain = new ArrayList();
		existingChain = (ArrayList) map.get(key);
		return existingChain;
	}

	public void put(String key, String p) {
		if (p == null)
			throw new RuntimeException("Bad idea! You tried to insert "
					+ "a null object into a Chain!");
		if (!map.containsKey(key)) {
			// System.out.println("The first element on the chain");
			ArrayList chain = new ArrayList();
			chain.add(p);
			// System.out.println("chain size=" + chain.size());
			map.put(key, chain);
		} else {
			// System.out.println("Adding an element to an already existing chain");
			ArrayList existingChain = new ArrayList();
			existingChain = (ArrayList) map.get(key);
			// System.out.println("existingChain size="+ existingChain.size());
			existingChain.add(p);
			map.remove(key);
			map.put(key, existingChain);
		}
	}

	public void sput(String key, ArrayList prefixRecordChain) {
		if (prefixRecordChain == null)
			throw new RuntimeException("Bad idea! You tried to insert "
					+ "a null object into a Chain!");
		map.put(key, prefixRecordChain);
	}

}
