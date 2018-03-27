/*
 * Created on 07/02/2006
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
public interface VerbInterface extends ItemInterface {

	final static String PGNTokensBeinoni = "123p/M/Sg,123p/F/Sg,123p/M/Pl,123p/F/Pl";

	final static String numberTokens4 = "singular,singular,plural,plural";

	final static String genderTokens4 = "masculine,feminine,masculine,feminine";

	final static String personTokens4 = "any,any,any,any";

	final static String PGNTokensImperative = "2p/M/Sg,2p/F/Sg,2p/MF/Pl,2p/F/Pl";

	final static String suffixSingular = "i,k,k,w,h,nw,km,kn,m,n";

	final static String suffixMPlural = "i,k,ik,w,h,nw,km,kn,hm,hn";

	final static String suffixFPlural = "ii,ik,iik,iw,ih,inw,ikm,ikn,ihm,ihn";

	final static String personTokens9 = "1,2,2,3,3,1,2,2,3";

	final static String numberTokens9 = "singular,singular,singular,singular,singular,plural,plural,plural,plural";

	final static String genderTokens9 = "masculine and feminine,masculine,feminine,masculine,feminine,masculine and feminine,masculine,feminine,masculine and feminine";

	final static String PGNTokens9 = "1p/MF/Sg,2p/M/Sg,2p/F/Sg,3p/M/Sg,3p/F/Sg,1p/MF/Pl,2p/M/Pl,2p/F/Pl,3p/MF/Pl";

	public void generateInfinitive(String prefix, String inside, int index1, int index2, int index3, int index4,
			String suffix) throws Exception;

	public void generatePast(String prefix, String suffix, String inside, int index1, int index2, int index3, int index4)
			throws Exception;

	public void generatePresent(String prefix, String suffix, String inside, int index1, int index2, int index3,
			int index4) throws Exception;

	public void generateFuture(String prefix, String suffix, String inside, int index1, int index2, int index3,
			int index4) throws Exception;

	public void generateImperative(String prefix, String suffix, String inside, int index1, int index2, int index3,
			int index4) throws Exception;

	public void generateBeinoniPassive(String passiveBase) throws Exception;

	public void generateConstructAndPossessive(String base, boolean possessiveFlag) throws Exception;

	public void generatePossessive(String base) throws Exception;

}
