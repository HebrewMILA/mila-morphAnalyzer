package lexicon.utils;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;

import lexicon.dbUtils.MWErecord;

public class MWErecords implements Serializable {
	// static private HashMap map = null;
	private HashMap map = null;

	/** Constructs an empty HashChain. */
	public MWErecords(int initialCapacity, int rathio) {
		map = new HashMap(initialCapacity, rathio);
	}

	/** Erases the contents of the current HashChain. */
	public void clear() {
		if (!map.isEmpty()) {
			map.clear();
		}
	}

	// -------------------------------------------------------------------------------------------------------------------
	public ArrayList get(String key) throws Exception {
		ArrayList existingChain = null;
		existingChain = new ArrayList();
		existingChain = (ArrayList) map.get(key);
		return existingChain;
	}

	public int GetSize() {
		return map.size();
	}

	// ----------------------------------------------------------------------------------------------------------------
	public void put(String key, MWErecord mwRecord) {
		// System.out.println("(F) MWinflections:put()");
		if (mwRecord == null) {
			System.out
					.println("(F) MWinflections:put(): Bad idea! You tried to insert a null object into a Chain!");
			System.exit(1);
			// throw new
			// RuntimeException("Bad idea! You tried to insert a null object into a Chain!");
		}

		// System.out.println("key=" + key);
		// The first element on the chain
		if (!map.containsKey(key)) {
			// System.out.println("The first element on the chain");
			ArrayList chain = new ArrayList();
			chain.add(mwRecord);
			// System.out.println("chain size=" + chain.size());
			map.put(key, chain);
			// Adding an element to an already existing chain
		} else { // if entry already exist
					// System.out.println("Adding an element to an already existing chain");
			ArrayList existingChain = new ArrayList();
			existingChain = (ArrayList) map.get(key);
			// System.out.println("existingChain size="+ existingChain.size());
			existingChain.add(mwRecord);
			map.remove(key);
			map.put(key, existingChain);
		}

		// System.out.println("size="+ map.size());
	}

	public void sput(String key, ArrayList mwe1RecordChain) {
		if (mwe1RecordChain == null)
			throw new RuntimeException(
					"Bad idea! You tried to insert a null object into a Chain!");
		map.put(key, mwe1RecordChain);
	}
}
