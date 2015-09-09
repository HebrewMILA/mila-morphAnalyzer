package mila.lexicon.utils;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Reference implementation of the Chain interface, using a HashMap as the
 * underlying structure.
 */
public class Inflections implements Serializable {
	static private HashMap<String, ArrayList<String>> map = null;

	/** Constructs an empty HashChain. */
	public Inflections(int initialCapacity, int rathio) {
		map = new HashMap<String, ArrayList<String>>(initialCapacity, rathio);
	}

	/** Erases the contents of the current HashChain. */
	public void clear() {
		if (!map.isEmpty()) {
			map.clear();
		}
	}

	public ArrayList<String> get(String key) throws Exception {
		ArrayList<String> existingChain = new ArrayList<>();
		existingChain = map.get(key);
		return existingChain;
	}

	public void put(String key, String inflectRecord) {
		if (inflectRecord == null)
			throw new RuntimeException("Bad idea! You tried to insert "
					+ "a null object into a Chain!");
		// System.out.println("key=" + key);
		// The first element on the chain
		if (!map.containsKey(key)) {
			// System.out.println("The first element on the chain");
			ArrayList<String> chain = new ArrayList<String>();
			chain.add(inflectRecord);
			// System.out.println("chain size=" + chain.size());
			map.put(key, chain);
			// Adding an element to an already existing chain
		} else { // if entry already exist
					// System.out.println("Adding an element to an already
					// existing
					// chain");
			ArrayList<String> existingChain = new ArrayList<String>();
			existingChain = (ArrayList<String>) map.get(key);
			// System.out.println("existingChain size="+ existingChain.size());
			existingChain.add(inflectRecord);
			map.remove(key);
			map.put(key, existingChain);
		}

		System.out.println("size=" + map.size());
	}

	public void sput(String key, ArrayList<String> inflectRecordChain) {
		if (inflectRecordChain == null)
			throw new RuntimeException("Bad idea! You tried to insert "
					+ "a null object into a Chain!");
		map.put(key, inflectRecordChain);
	}

}
