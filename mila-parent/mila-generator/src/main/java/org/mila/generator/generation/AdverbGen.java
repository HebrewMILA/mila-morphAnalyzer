/*
 * Created on 19/09/2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package org.mila.generator.generation;

import static ch.lambdaj.Lambda.filter;

import java.util.List;

import javax.persistence.EntityManager;

import org.hamcrest.Matchers;
import org.mila.entities.corpus.RegisterType;
import org.mila.entities.corpus.SpellingType;
import org.mila.entities.corpus.SuffixFunctionType;
import org.mila.entities.inflections.PersistableInflection;
import org.mila.entities.lexicon.AdverbException;
import org.mila.entities.lexicon.AdverbExceptionAdd;
import org.mila.entities.lexicon.AdverbExceptionRemove;
import org.mila.entities.lexicon.AdverbExceptionReplace;
import org.mila.entities.lexicon.AdverbLexicon;
import org.mila.entities.lexicon.Item;
import org.mila.generator.utils.Transliteration;

/**
 * @author daliabo
 * 
 *         TODO To change the template for this generated type comment go to
 *         Window - Preferences - Java - Code Style - Code Templates
 */
public class AdverbGen extends ItemGen {

	public AdverbGen(Item item, EntityManager lexicon, EntityManager generator,
			EntityManager inflections) {
		super(item, lexicon, generator, inflections);
		this.adverb = (AdverbLexicon) item.getSubitem();
	}

	private AdverbLexicon adverb;

	protected boolean replaceExceptionExist() {
		for (AdverbException ex : adverb.getExceptions()) {
			if (!(ex instanceof AdverbExceptionReplace))
				continue;

			String exceptionPGN = ex.getPossessive();

			if (exceptionPGN.equals(PGN)) {
				PGN = exceptionPGN;
				inflectedItem = ex.getTransliterated();
				surface = ex.getUndotted();
				register = RegisterType.fromValue(ex.getRegister().value());
				spelling = SpellingType.fromValue(ex.getSpelling().value());
				// populateAcussativeAttribues();
				populateDatabase();
				return true;
			}
		}
		return false;
	}

	private List<AdverbException> getRemoveExceptionList() {
		return filter(Matchers.instanceOf(AdverbExceptionRemove.class),
				adverb.getExceptions());
	}

	protected boolean removeExceptionExist() {
		for (AdverbException ex : getRemoveExceptionList()) {
			if (ex.getPossessive().equals(PGN)) {
				return true;
			}
		}
		return false;
	}

	protected void addException() {
		List<AdverbException> l = filter(
				Matchers.instanceOf(AdverbExceptionAdd.class),
				adverb.getExceptions());
		if (l.size() > 0)
			analyseAddExceptionList(l);
	}

	private void analyseAddExceptionList(List<AdverbException> l) {
		for (AdverbException ex : l) {
			inflectedItem = ex.getTransliterated();
			surface = ex.getUndotted();
			spelling = SpellingType.fromValue(ex.getSpelling().value());
			register = RegisterType.fromValue(ex.getRegister().value());
			PGN = ex.getPossessive();
			if (!PGN.equals("unspecified")) {
				suffixFunction = SuffixFunctionType.PRONOMIAL;
				// populateAcussativeAttribues();
			}
			populateDatabase();
		}
	}

	private void analyse() {
		analyseItem();
		inflectedItem = transliterated;
		surface = undot;
		inflect = this.adverb.isInflect();
		inflectionBase = this.adverb.getInflectionBase();
		suffixFunction = SuffixFunctionType.UNSPECIFIED;
		PGN = "unspecified";
	}

	protected void handleBaseForm() {
		/* XXX: Replace exceptions are ignored */
		if (!this.removeExceptionExist())
			populateDatabase();
	}

	public List<PersistableInflection> inflect() {
		analyse();
		handleBaseForm();
		addException();

		if (inflect) {
			if (transliterated.charAt(transliterated.length() - 1) == 'i')
				suffixes = "i,k,k,w,h,nw,km,kn,hm,hn";
			else
				suffixes = "i,k,k,w,h,nw,km,kn,m,n";
			// PGNTokens =
			// "1p/MF/Sg,2p/M/Sg,2p/F/Sg,3p/M/Sg,3p/F/Sg,1p/MF/Pl,2p/M/Pl,2p/F/Pl,3p/M/Pl,3p/F/Pl";
			if (!inflectionBase.equals(""))
				inflectionBase = Transliteration.toEnglish(inflectionBase);
			inflectPronomial(SuffixFunctionType.PRONOMIAL);
		}
		return this.getGeneratedInflections();
	}

}
