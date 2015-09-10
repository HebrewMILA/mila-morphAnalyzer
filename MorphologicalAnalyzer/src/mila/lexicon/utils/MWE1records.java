package mila.lexicon.utils;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;

import mila.lexicon.dbUtils.MWE1record;

public class MWE1records implements Serializable {
	private static final long serialVersionUID = 3795100190993784553L;
	
	static private HashMap<String, ArrayList<MWE1record>> map = null;

	/** Constructs an empty HashChain. */
	public MWE1records(int initialCapacity, int rathio) {
		map = new HashMap<String, ArrayList<MWE1record>>(initialCapacity, rathio);
	}

	/** Erases the contents of the current HashChain. */
	public void clear() {
		if (!map.isEmpty()) {
			map.clear();
		}
	}

	// -------------------------------------------------------------------------------------------------------------------
	public ArrayList<MWE1record> get(String key) throws Exception {
		ArrayList<MWE1record> existingChain = new ArrayList<>();
		existingChain = map.get(key);
		return existingChain;
	}

	// ----------------------------------------------------------------------------------------------------------------
	public void put(String key, MWE1record mw1Record) {
		// System.out.println("(F) MWinflections:put()");
		if (mw1Record == null) {
			System.out.println("(F) MWinflections:put(): Bad idea! You tried to insert a null object into a Chain!");
			System.exit(1);
			// throw new
			// RuntimeException("Bad idea! You tried to insert a null object
			// into a Chain!");
		}

		// System.out.println("key=" + key);
		// The first element on the chain
		if (!map.containsKey(key)) {
			// System.out.println("The first element on the chain");
			ArrayList<MWE1record> chain = new ArrayList<MWE1record>();
			chain.add(mw1Record);
			// System.out.println("chain size=" + chain.size());
			map.put(key, chain);
			// Adding an element to an already existing chain
		} else { // if entry already exist
					// System.out.println("Adding an element to an already
					// existing chain");
			ArrayList<MWE1record> existingChain = new ArrayList<MWE1record>();
			existingChain = (ArrayList<MWE1record>) map.get(key);
			// System.out.println("existingChain size="+ existingChain.size());
			existingChain.add(mw1Record);
			map.remove(key);
			map.put(key, existingChain);
		}

		// System.out.println("size="+ map.size());
	}

	public void sput(String key, ArrayList<MWE1record> mwe1RecordChain) {
		if (mwe1RecordChain == null)
			throw new RuntimeException("Bad idea! You tried to insert a null object into a Chain!");
		map.put(key, mwe1RecordChain);
	}
}
