/*
 * Created on 19/09/2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package lexicon.generate;


import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.List;
import java.util.StringTokenizer;

import lexicon.contents.exception_types.AdverbExceptionType;
import lexicon.contents.exception_types.InterjectionExceptionType;
import lexicon.contents.exception_types.PrepositionExceptionType;
import lexicon.contents.types.ItemType;
import lexicon.stringUtils.Translate;


/**
 * @author daliabo
 * 
 * TODO To change the template for this generated type comment go to Window -
 * Preferences - Java - Code Style - Code Templates
 */
public class AdverbGen extends ItemGen {

	public AdverbGen(ItemType item) {
		super(item);
	}

	private void replaceException() {
		String sql = buildSql("replace", "adverb_exception_type");
		replaceExceptionList = handleException(sql);
	}

	private void removeException() {
		String sql = buildSql("remove", "adverb_exception_type");
		removeExceptionList = handleException(sql);
	}

	
	
	
	protected boolean replaceExceptionExist() throws Exception {
		boolean match = false;
		for (int i = 0; i < replaceExceptionList.size(); i++) {
			AdverbExceptionType adverbExceptionType = new AdverbExceptionType();
			adverbExceptionType.open(((Integer) replaceExceptionList.get(i))
					.intValue());
			String exceptionPGN = adverbExceptionType.getPossessiveSuffix();
			
			if (exceptionPGN.equals(PGN)) {
				PGN = exceptionPGN;
				inflectedItem = adverbExceptionType.getTransliterated();
				surface = adverbExceptionType.getUndotted();
				register = adverbExceptionType.getRegister();
				spelling = adverbExceptionType.getSpelling();
				//populateAcussativeAttribues();
				match = true;
				populateDatabase();
				break;
			}
		}
		return match;
	}
	
	
	
	
	protected boolean removeExceptionExist() throws Exception {
		boolean match = false;
		for (int i = 0; i < removeExceptionList.size(); i++) {
			AdverbExceptionType adverbExceptionType = new AdverbExceptionType();
			adverbExceptionType.open(((Integer) removeExceptionList.get(i))
					.intValue());
			String exceptionPGN = adverbExceptionType.getPossessiveSuffix();
			if (exceptionPGN.equals(PGN)) {
				match = true;
				break;
			}
		}
		return match;
	}

	protected void addException() throws Exception {
		String sql = buildSql("add", "adverb_exception_type");
		List addExceptionList = handleException(sql);
		if (addExceptionList.size() > 0) {
			analyseExceptionList(addExceptionList);
		}
	}

	private void analyseExceptionList(List exceptionList) throws Exception {
		for (int i = 0; i < exceptionList.size(); i++) {
			AdverbExceptionType adverbExceptionType = new AdverbExceptionType();
			adverbExceptionType.open(((Integer) exceptionList.get(i))
					.intValue());
			inflectedItem = adverbExceptionType.getTransliterated();
			surface = adverbExceptionType.getUndotted();
			spelling = adverbExceptionType.getSpelling();
			register = adverbExceptionType.getRegister();
			PGN = adverbExceptionType.getPossessiveSuffix();
			if (!PGN.equals("unspecified")){
				suffixFunction = "pronomial";
				//populateAcussativeAttribues();
			}
			populateDatabase();
		}
	}

	private void analyse() {
		analyseItem();
		inflectedItem = transliterated;
		surface = undot;
		inflect = item.getAdverb().isInflect();
		inflectionBase = item.getAdverb().getInflectionBase();
		suffixFunction="unspecified";
		PGN="unspecified";
	}
	
	protected void handleBaseForm() throws Exception {
		int size = removeExceptionList.size();
		if (size == 0)
			populateDatabase();
		else {
			for (int i = 0; i < size; i++) {
				AdverbExceptionType adverbExceptionType = new AdverbExceptionType();
				adverbExceptionType.open(((Integer) removeExceptionList.get(i))
						.intValue());
				String exceptionPGN = adverbExceptionType.getPossessiveSuffix();
				String action = adverbExceptionType.getAction();
				if (exceptionPGN.equals("unspecified")
						&& action.equals("remove")) 
					;
				
				else		
					populateDatabase();
				

			}
		}
	}

	@Override
	public void generateInflects() throws Exception {
		analyse();
		replaceException();
		removeException();
		handleBaseForm() ;
		//populateDatabase();
		addException();
		
		if (inflect) {
			if (transliterated.charAt(transliterated.length() - 1) == 'i')
				suffixes = "i,k,k,w,h,nw,km,kn,hm,hn";
			else
				suffixes = "i,k,k,w,h,nw,km,kn,m,n";
			//PGNTokens = "1p/MF/Sg,2p/M/Sg,2p/F/Sg,3p/M/Sg,3p/F/Sg,1p/MF/Pl,2p/M/Pl,2p/F/Pl,3p/M/Pl,3p/F/Pl";
			if (!inflectionBase.equals(""))
				inflectionBaseHandling();
			inflectPronomial("pronomial");
		}		
	}

}
