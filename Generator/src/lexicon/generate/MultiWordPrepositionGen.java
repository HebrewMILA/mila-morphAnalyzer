/*
 * Created on 14/11/2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package lexicon.generate;

import java.io.UnsupportedEncodingException;
import java.util.NoSuchElementException;
import java.util.StringTokenizer;

import lexicon.contents.types.ItemType;
import lexicon.stringUtils.Translate;

/**
 * @author daliabo
 * 
 *         TODO To change the template for this generated type comment go to
 *         Window - Preferences - Java - Code Style - Code Templates
 */
public class MultiWordPrepositionGen extends ItemGen {
    boolean definiteness;

    String mwPos = "";

    PopulateMWE popualteMWE = new PopulateMWE();

    public MultiWordPrepositionGen(ItemType item) {
	super(item);

    }

    private void analyse() {
	analyseItem();
	mwPos = "preposition";
	surface = undot;
	type = "unspecified";
	number = item.getMultiWordPreposition().getNumber();

    }

    protected void inflectPronomial(String transliteratedLexiconItem,
	    String surfaceLexiconItem, String inflected, String transliterated1)
	    throws UnsupportedEncodingException, Exception {
	String suffixFunction = "pronomial";
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

	    String inflectedItem = transliterated1 + " " + inflected + suffix;
	    String PGN = stPGN.nextToken();
	    // System.out.println();
	    System.out.println("inflectedItem =" + inflectedItem);
	    String surface = Translate.Eng2Heb(transliterated1) + " "
		    + Translate.Eng2Heb(inflected + suffix);
	    if (suffix.equals("k") && PGN.equals("2p/F/Sg")
		    && number.equals("plural"))
		spelling = "irregular";
	    else
		spelling = "standard";

	    // System.out.println("surface =" + surface);
	    // System.out.println();

	    // if(inflectedItem.endsWith("i") && number.equals("plural") &&
	    // PGN.equals("1p/MF/Sg")){
	    // popualteMWE.popualteMWETables(inflectedItem, surface, '1',
	    // "preposition", "unspecified", id, "unspecified", PGN, spelling,
	    // register);
	    // spelling="irregular";
	    // inflectedItem = transliterated;
	    // System.out.println("inflectedItem =" + inflectedItem);
	    // popualteMWE.popualteMWETables(inflectedItem, surface, '1',
	    // "preposition", "unspecified", id, "unspecified", PGN, spelling,
	    // register);
	    // spelling="standard";
	    //
	    // }
	    // else
	    popualteMWE.popualteMWETables(transliteratedLexiconItem,
		    surfaceLexiconItem, '1', "preposition", "unspecified", id,
		    "unspecified", PGN, spelling, register, inflectedItem,
		    surface);

	}
    }

    public void inflect() throws Exception {
	analyse();
	try {
	    StringTokenizer st = new StringTokenizer(transliterated);
	    String transliterated1 = st.nextToken();
	    String transliterated2 = st.nextToken();
	    popualteMWE.popualteMWETables(transliterated, surface, '1', mwPos,
		    dottedLexiconItem, id, type, spelling, register, false);
	    inflectPronomial(transliterated, surface, transliterated2,
		    transliterated1);
	} catch (NoSuchElementException e) {
	    System.err.println("Error: Couldn't tokenize transliterated string! <" + transliterated + "> (" + this.id + ")");
	    throw e;
	}

    }

}
