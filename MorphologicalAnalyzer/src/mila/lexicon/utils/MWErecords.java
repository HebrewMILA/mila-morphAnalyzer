package mila.lexicon.utils;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;

import mila.lexicon.dbUtils.MWErecord;

public class MWErecords implements Serializable {
	private static final long serialVersionUID = 1737934603049142268L;
	
	private HashMap<String, ArrayList<MWErecord>> map = null;

	/** Constructs an empty HashChain. */
	public MWErecords(int initialCapacity, int rathio) {
		map = new HashMap<String, ArrayList<MWErecord>>(initialCapacity, rathio);
	}

	/** Erases the contents of the current HashChain. */
	public void clear() {
		if (!map.isEmpty()) {
			map.clear();
		}
	}

	// -------------------------------------------------------------------------------------------------------------------
	public ArrayList<MWErecord> get(String key) throws Exception {
		ArrayList<MWErecord> existingChain = new ArrayList<>();
		existingChain = map.get(key);
		return existingChain;
	}

	public int GetSize() {
		return map.size();
	}

	// ----------------------------------------------------------------------------------------------------------------
	public void put(String key, MWErecord mwRecord) {
		if (mwRecord == null) {
			System.out.println("(F) MWinflections:put(): Bad idea! You tried to insert a null object into a Chain!");
			System.exit(1);
		}

		// The first element on the chain
		if (!map.containsKey(key)) {
			ArrayList<MWErecord> chain = new ArrayList<MWErecord>();
			chain.add(mwRecord);
			map.put(key, chain);
			// Adding an element to an already existing chain
		} else {
			ArrayList<MWErecord> existingChain = map.get(key);
			existingChain.add(mwRecord);
			map.remove(key);
			map.put(key, existingChain);
		}
	}

	public void sput(String key, ArrayList<MWErecord> mwe1RecordChain) {
		if (mwe1RecordChain == null)
			throw new RuntimeException("Bad idea! You tried to insert a null object into a Chain!");
		map.put(key, mwe1RecordChain);
	}
}
