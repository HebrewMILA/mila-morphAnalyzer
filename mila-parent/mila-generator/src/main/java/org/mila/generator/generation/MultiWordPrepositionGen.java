package org.mila.generator.generation;

import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

import javax.persistence.EntityManager;

import org.mila.entities.corpus.NumberType;
import org.mila.entities.corpus.SpellingType;
import org.mila.entities.corpus.SuffixFunctionType;
import org.mila.entities.inflections.PersistableInflection;
import org.mila.entities.lexicon.Item;
import org.mila.entities.lexicon.MultiWordPrepositionLexicon;
import org.mila.entities.lexicon.Pos;
import org.mila.generator.generation.mwe.PopulateMWE;
import org.mila.generator.utils.Transliteration;

public class MultiWordPrepositionGen extends ItemGen {

	public MultiWordPrepositionGen(Item item, EntityManager lexicon,
			EntityManager generator, EntityManager inflections) {
		super(item, lexicon, generator, inflections);
		popMWE = new PopulateMWE(inflections);
		mwprepos = (MultiWordPrepositionLexicon) item.getSubitem();
	}

	boolean definiteness;

	MultiWordPrepositionLexicon mwprepos;
	Pos mwPos = Pos.UNSPECIFIED;

	PopulateMWE popMWE;

	private void analyse() {
		analyseItem();
		mwPos = Pos.PREPOSITION;
		surface = undot;
		type = "unspecified";
		number = NumberType.fromValue(mwprepos.getNumber().value());

	}

	protected void inflectPronomial(String transliteratedLexiconItem,
			String surfaceLexiconItem, String inflected, String transliterated1) {
		suffixFunction = SuffixFunctionType.PRONOMIAL;
		String suffixes = "";
		StringTokenizer stPGN = null;
		if (number.equals("plural")) {
			suffixes = "i,k,k,ik,w,h,nw,km,kn,hm,hn";
			final String PGNPluralTokens10 = "1p/MF/Sg,2p/M/Sg,2p/F/Sg,2p/F/Sg,3p/M/Sg,3p/F/Sg,1p/MF/Pl,2p/M/Pl,2p/F/Pl,3p/M/Pl,3p/F/Pl";
			stPGN = new StringTokenizer(PGNPluralTokens10, ",");
		} else if (number.equals("singular")) {
			stPGN = new StringTokenizer(PGNPRONOMIALTokens10, ",");
			suffixes = "i,k,k,w,h,nw,km,kn,m,n";
		}
		StringTokenizer stSuff = new StringTokenizer(suffixes, ",");

		while (stSuff.hasMoreTokens()) {

			String suffix = stSuff.nextToken();

			inflectedItem = transliterated1 + " " + inflected + suffix;
			PGN = stPGN.nextToken();
			surface = Transliteration.toHebrew(transliterated1) + " "
					+ Transliteration.toHebrew(inflected + suffix);
			if (suffix.equals("k") && PGN.equals("2p/F/Sg")
					&& number.equals("plural"))
				spelling = SpellingType.IRREGULAR;
			else
				spelling = SpellingType.STANDARD;

			this.basePos = this.mwPos.value();
			popMWE.populateMWETables(this, false);
		}
	}

	public List<PersistableInflection> inflect() {
		analyse();
		StringTokenizer st = new StringTokenizer(transliterated);
		String transliterated1 = st.nextToken();
		String transliterated2 = st.nextToken();
		this.basePos = this.mwPos.value();

		popMWE.populateMWETables(this, false);
		inflectPronomial(transliterated, surface, transliterated2,
				transliterated1);

		return Collections.emptyList();
	}

}
