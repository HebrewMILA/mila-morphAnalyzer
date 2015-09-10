package lexicon;

import static sql.InflectionFilters.*;
import static sql.InflectionFilters.Pos.*;
import static sql.InflectionFilters.Tense.*;
import static sql.InflectionFilters.Type.*;

import java.util.Arrays;
import java.util.Vector;
import java.util.stream.Collectors;

import sql.Inflection;
import sql.InflectionTable;

public class ShowVerbInflections {
	final InflectionTable inflections;
	
	public ShowVerbInflections(String lexiconId) {
		inflections = InflectionTable.fetch(lexiconId);
	}

	private String buildPGN(String gender, String number, String person) {
		return person
				+ "p"
				+ "/"
				+ gender.replace("masculine", "M").replace("feminine", "F")
						.replace(" and ", "") + "/"
				+ number.replace("singular", "Sg").replace("plural", "Pl");
	}

	private String getTenseInflections(Vector<String> outVec, Tense t) {
		String[] outArr = new String[10];
		for (Inflection x : inflections.where(tense(t))) {
			String PGN = buildPGN(x.baseGender, x.baseNumber, x.basePerson);
			outArr[pgnToIndex(PGN)] = x.surface;
		}
		outVec.addAll(Arrays.asList(outArr));
		return outVec.stream().collect(Collectors.joining(", "));
	}

	private String getInfinitiveInflections(Vector<String> outVec, char prefix,
			boolean eq) {
		return inflections.where(
				tense(infinitive),
				x->x.suffixFunction.equals("unspecified") == eq,
				x->x.transliterated.charAt(0) == prefix,
				x->!(x.baseTransliteratedLItem.charAt(0) == prefix) || x.transliterated.startsWith(""+prefix+prefix)
				).joinSurface(outVec);
	}

	public String getBeinoniInflections(Vector<String> outVec) {
		return inflections.where(tense(beinoni), definit(true, false), suffix(false), type(verb), pos(participle)).joinSurface(outVec);
	}

	public String getPassiveInflections(Vector<String> outVec) {
		return inflections.where(tense(beinoni), definit(true, false), suffix(false), type(verb), pos(passiveParticiple)).joinSurface(outVec);
	}

	public String getImperativeInflections(Vector<String> outVec) {
		return inflections.where(tense(imperative)).joinSurface(outVec);
	}

	public String getDecodedBareInfinitive() {
		return inflections.where(tense(bareInfinitive)).joinSurface(new Vector<>());
	}

	public String getInfinitiveInflections(Vector<String> resVec) {
		return getInfinitiveInflections(resVec, 'b', false);
	}

	public String getDecodedInfinitive() {
		return getInfinitiveInflections(new Vector<>(), 'l', true);
	}

	private int pgnToIndex(String PGN) {
		int res = Arrays.asList("1p/MF/Sg", "2p/M/Sg", "2p/F/Sg", "3p/M/Sg", "3p/F/Sg", "1p/MF/Pl", "2p/M/Pl", "2p/F/Pl", "3p/MF/Pl", "3p/F/Pl", "3p/M/Pl").indexOf(PGN);
		if (res < 0)
			res = Arrays.asList("", "", "", "", "", "", "2p/MF/Pl", "", "", "3p/M/Pl").indexOf(PGN);
		return res;
	}

	public String getPastInflections(Vector<String> outVec) {
		String res = getTenseInflections(outVec, past);
		if (outVec.get(9) == null) // הם = הן
			outVec.add(9, outVec.get(8));
		return res;
	}

	public String getFutureInflections(Vector<String> outVec) {
		return getTenseInflections(outVec, future);
	}

	public Vector<String> getFutureInflections() {
		Vector<String> res = new Vector<>();
		getFutureInflections(res);
		return res;
	}

	public static void releaseConnection() {
		//backward compatibility
	}

	public static void main(String[] args) {
		ShowVerbInflections s = new ShowVerbInflections("4241");
		Vector<String> pastVec = new Vector<>();
		s.getFutureInflections();
		s.getPastInflections(pastVec);
		System.out.println();
	}
}