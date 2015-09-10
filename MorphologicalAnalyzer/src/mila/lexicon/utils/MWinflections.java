package mila.lexicon.utils;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;

import mila.lexicon.dbUtils.MWEinflectionsRecord;

public class MWinflections implements Serializable {
	private static final long serialVersionUID = -7905831899295621590L;
	
	static private HashMap<String, ArrayList<MWEinflectionsRecord>> map = null;

	/** Constructs an empty HashChain. */
	public MWinflections(int initialCapacity, int rathio) {
		map = new HashMap<String, ArrayList<MWEinflectionsRecord>>(initialCapacity, rathio);
	}

	/** Erases the contents of the current HashChain. */
	public void clear() {
		if (!map.isEmpty()) {
			map.clear();
		}
	}

	// -------------------------------------------------------------------------------------------------------------------
	public ArrayList<MWEinflectionsRecord> get(String key) throws Exception {
		ArrayList<MWEinflectionsRecord> existingChain = new ArrayList<>();
		existingChain = map.get(key);
		return existingChain;
	}

	// ----------------------------------------------------------------------------------------------------------------
	public void put(String key, MWEinflectionsRecord mwinflectRecord) {
		if (mwinflectRecord == null) {
			System.out.println("(F) MWinflections:put(): Bad idea! You tried to insert a null object into a Chain!");
			System.exit(1);
		}

		// The first element on the chain
		if (!map.containsKey(key)) {
			ArrayList<MWEinflectionsRecord> chain = new ArrayList<MWEinflectionsRecord>();
			chain.add(mwinflectRecord);
			map.put(key, chain);
			// Adding an element to an already existing chain
		} else { 
			ArrayList<MWEinflectionsRecord> existingChain = new ArrayList<MWEinflectionsRecord>();
			existingChain = map.get(key);
			// System.out.println("existingChain size="+ existingChain.size());
			existingChain.add(mwinflectRecord);
			map.remove(key);
			map.put(key, existingChain);
		}
	}

	public void sput(String key, ArrayList<MWEinflectionsRecord> mwinflectRecordChain) {
		if (mwinflectRecordChain == null)
			throw new RuntimeException("Bad idea! You tried to insert a null object into a Chain!");
		map.put(key, mwinflectRecordChain);
	}
}
