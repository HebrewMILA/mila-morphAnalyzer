package ner;

import java.util.*;

import ner.baseline.BaselineEntityFinder;
import ner.hmm.HmmEntityFinder;
import ner.maxent.MaxentEntityFinder;
import tagging.FormatError;
import tagging.Sentence;
import utils.IO;
import xml.CorpusAdapter;
import xml.XmlManipulatorInterface;

/**
 * The main worker class.<br>
 * Uses NameFinder of choice to find entities in a document.
 */
public final class Predictor {
	final List<Sentence> input;

	Predictor(List<Sentence> input) {
		this.input = input;
	}
	
	private List<String> predictOnly(String ef_name) {
		EntityFinder ef = byName(ef_name);
		List<String> outlist = new ArrayList<>();
		for (Sentence analysed : input)
			outlist.addAll(ef.tag(analysed));
		return outlist;
	}

	private static EntityFinder byName(String name) {
		switch (name.toLowerCase()) {
		case "baseline":
			return new BaselineEntityFinder();
		case "hmm":
			return new HmmEntityFinder();
		case "maxent":
			return new MaxentEntityFinder();
		default:
			throw new RuntimeException("Unknown finder: " + name);
		}
	}

	private static List<String> zip(List<String> a, List<String> b, List<String> c, List<String> mila) {
		List<String> res = new ArrayList<>();
		String prev = "";
		for (int i=0; i < a.size(); i++) {
			String cur = new MergeDecision(a.get(i), b.get(i), c.get(i), prev, mila.get(i)).merge();
			res.add(cur);
			prev = cur;
		}
		return res;
	}

	/**
	 * Usage: java Main source_file<br>
	 * where source_file is Hebrew UTF8 file<br>
	 * Output is in the FULL format
	 *
	 * @throws FormatError
	 * @throws ClassNotFoundException
	 * @throws IllegalAccessException
	 * @throws InstantiationException
	 */
	public static void main(String[] args) throws Exception {
		run(args[0], args[1]);
	}

	public static void run(String filename, String outfilename) {
		XmlManipulatorInterface xml = CorpusAdapter.read(filename);
		xml.updateAnalysis(listToMap(predict(xml)));
		IO.outputStream(outfilename, xml::writeToFile);
	}
	public static void run(mila.generated.Corpus corpus) {
		CorpusAdapter xml = new CorpusAdapter(corpus);
		xml.updateAnalysis(listToMap(predict(xml)));
	}
	public static List<String> predict(XmlManipulatorInterface xml) {
		Predictor p = new Predictor(xml.iterSentences());
		List<String> baseline = p.predictOnly("baseline");
		//List<String> hmm = p.predictOnly("hmm");
		List<String> maxent = p.predictOnly("maxent");
		List<String> entities = zip(baseline, maxent, maxent, xml.getEntities());
		return entities;
	}

	public static Map<Integer, String> listToMap(List<String> entities) {
		Map<Integer, String> m = new HashMap<>();
		for (int i=0; i < entities.size(); i++)
			if (!entities.get(i).isEmpty())
				m.put(i, entities.get(i));
		return m;
	}

	public static List<String> getSurfaces(Predictor p) {
		List<String> res = new ArrayList<>();
		for (Sentence s : p.input)
			res.addAll(s.getSurfaces());
		return res;
	}
	
	public static final Map<String, String> nice_name;

	static {
		Map<String, String> map = new HashMap<>();
		map.put("PERS", "person");
		map.put("LOC", "location");
		map.put("ORG", "organization");
		map.put("MONEY", "money");
		map.put("TIME", "time");
		map.put("PERCENT", "percent");
		map.put("DATE", "date");
		map.put("ENT", "entity");
		map.put("AFF", "nation");
		map.put("EVENT", "event");
		map.put("NUM", "number");
		map.put("O", "");
		map.put("", "");
		nice_name = Collections.unmodifiableMap(map);
	}
}

class MergeDecision {
	String baseline, hmm, maxent;
	String prev, mila;

	MergeDecision(String baseline, String hmm, String maxent, String prev, String mila) {
		this.mila = mila;
		this.baseline = tag(baseline);
		this.hmm = tag(hmm);
		this.maxent = tag(maxent);
		this.prev = prev;
	}

	private String tag(String x) {
		String[] s = x.split("_");
		if (!x.equals("O") && this.mila.contains(Predictor.nice_name.getOrDefault(x, "")))
			return s[s.length-1];
		return "";
	}

	String majority() {
		if (baseline.equals(hmm))
			return baseline;
		if (hmm.equals(maxent))
			return hmm;
		if (maxent.equals(baseline))
			return maxent;
		return "";
	}

	String consistent() {
        for (String t : new String[] {baseline, hmm, maxent}) {
            if (tag(t).equals(prev))
                return tag(t);
        }
        return "";
    }

	String one_vote() {
        if (baseline.equals(hmm) && hmm.equals(maxent))
            return hmm;
        return "";
    }

	String naama() {
		String res = hmm;
		if (baseline.equals("TIME") || maxent.equals("DATE") && (!hmm.equals("DATE")))
			res = baseline;

		if (!res.isEmpty()) {
			if ("LOC ORG MONEY PERS".contains(hmm))
				res = hmm;
			else if (baseline.equals("LOC") || hmm.equals(baseline) && (!hmm.equals("PERS")))
				res = baseline;
		}
		return res;
	}

	String merge() {
		if (prev.equals("SOS")) {
			return maxent;
		}
		return coerce(maxent, naama(), majority(), consistent(), one_vote());
	}

	
	private static String coerce(String... ss) {
		for (String s : ss)
			if (!s.isEmpty())
				return s;
		return "";
	}
}
class MergeZip {
	String baseline, hmm, maxent;
	String prev, mila_entity;

	MergeZip(String baseline, String hmm, String maxent, String prev, String mila_entity) {
		this.baseline = baseline;
		this.hmm = hmm;
		this.maxent = maxent;
		this.mila_entity = mila_entity;
		this.prev = prev;
	}
	String merge() {
		return String.join("\0", baseline, hmm, maxent);
	}
}