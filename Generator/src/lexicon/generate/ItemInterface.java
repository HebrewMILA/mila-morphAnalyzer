/*
 * Created on 08/02/2006
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package lexicon.generate;

/**
 * @author daliabo
 *
 *         TODO To change the template for this generated type comment go to
 *         Window - Preferences - Java - Code Style - Code Templates
 */
public interface ItemInterface {
	final static String PGNTokens10 = "1p/MF/Sg,2p/M/Sg,2p/F/Sg,3p/M/Sg,3p/F/Sg,1p/MF/Pl,2p/M/Pl,2p/F/Pl,3p/M/Pl,3p/F/Pl";

	final static String PGNPRONOMIALTokens10 = "1p/MF/Sg,2p/M/Sg,2p/F/Sg,3p/M/Sg,3p/F/Sg,1p/MF/Pl,2p/M/Pl,2p/F/Pl,3p/M/Pl,3p/F/Pl";

	final static String personTokens10 = "1,2,2,3,3,1,2,2,3,3";

	final static String numberTokens10 = "singular,singular,singular,singular,singular,plural,plural,plural,plural,plural";

	final static String genderTokens10 = "masculine and feminine,masculine,feminine,masculine,feminine,masculine and feminine,masculine,feminine,masculine,feminine";

	final static int REPLACE = 1;

	final static int REMOVE = 2;

	final static String UNSPECIFIED = "unspecified";

	final static String IRREGULAR_SPELLING = "irregular";
	final static String STANDARD_SPELLING = "standard";
	final static String FORMAL_REGISTER = "formal";

}
