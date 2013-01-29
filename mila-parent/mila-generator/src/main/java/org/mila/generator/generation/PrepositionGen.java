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
import org.mila.entities.corpus.TriStateType;
import org.mila.entities.inflections.PersistableInflection;
import org.mila.entities.lexicon.Item;
import org.mila.entities.lexicon.PrepositionException;
import org.mila.entities.lexicon.PrepositionExceptionAdd;
import org.mila.entities.lexicon.PrepositionExceptionRemove;
import org.mila.entities.lexicon.PrepositionExceptionReplace;
import org.mila.entities.lexicon.PrepositionLexicon;

public class PrepositionGen extends ItemGen {

	private PrepositionLexicon preposition;

	public PrepositionGen(Item item, EntityManager lexicon,
			EntityManager generator, EntityManager inflections) {
		super(item, lexicon, generator, inflections);
		preposition = (PrepositionLexicon) item.getSubitem();
	}

	protected void addException() {
		analyseAddExceptionList(filter(
				Matchers.instanceOf(PrepositionExceptionAdd.class),
				preposition.getExceptions()));
	}

	protected void handleBaseForm() {
		if (!removeExceptionExist())
			populateDatabase();
	}

	private void analyseAddExceptionList(List<PrepositionException> exceptions) {
		for (PrepositionException ex : exceptions) {
			inflectedItem = ex.getTransliterated();
			surface = ex.getUndotted();
			register = RegisterType.fromValue(ex.getRegister().value());
			spelling = SpellingType.fromValue(ex.getSpelling().value());
			PGN = ex.getPossessive();
			suffixFunction = SuffixFunctionType.UNSPECIFIED;
			if (!PGN.equals("unspecified")) {
				suffixFunction = SuffixFunctionType.PRONOMIAL;
			}
			populateDatabase();
		}
	}

	protected List<PrepositionException> getReplaceExceptions() {
		return filter(Matchers.instanceOf(PrepositionExceptionReplace.class),
				preposition.getExceptions());
	}

	protected List<PrepositionException> getRemoveExceptions() {
		return filter(Matchers.instanceOf(PrepositionExceptionRemove.class),
				preposition.getExceptions());
	}

	protected boolean replaceExceptionExist() {
		for (PrepositionException ex : getReplaceExceptions()) {
			String exceptionPGN = ex.getPossessive();
			if (exceptionPGN.equals(PGN)) {
				inflectedItem = ex.getTransliterated();
				surface = ex.getUndotted();
				register = RegisterType.fromValue(ex.getRegister().value());
				spelling = SpellingType.fromValue(ex.getSpelling().value());
				populateDatabase();
				return true;
			}
		}
		return false;
	}

	protected boolean removeExceptionExist() {
		for (PrepositionException ex : getRemoveExceptions())
			if (ex.getPossessive().equals(PGN))
				return true;

		return false;
	}

	private void analyse() {
		analyseItem();

		inflectedItem = transliterated;
		inflectionBase = preposition.getInflectionBase();
		surface = undot;
		construct = TriStateType.UNSPECIFIED;
		suffixFunction = SuffixFunctionType.UNSPECIFIED;
		/* I'm not sure this actually does something... */
		caseSt = preposition.getCase().value();
		PGN = "unspecified";
		dottedLexiconItem = item.getDotted();
	}

	public List<PersistableInflection> inflect() {
		analyse();
		// Handle base form
		handleBaseForm();
		if (!inflectionBase.equals("")) {
			suffixes = "i,k,k,w,h,nw,km,kn,m,n";
			spelling = SpellingType.STANDARD;
			register = RegisterType.FORMAL;
			inflectPronomial(SuffixFunctionType.PRONOMIAL);
		}
		addException();
		return this.getGeneratedInflections();
	}

}
