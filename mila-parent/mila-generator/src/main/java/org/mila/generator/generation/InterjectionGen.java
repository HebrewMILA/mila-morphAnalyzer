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
import org.mila.entities.inflections.Inflection;
import org.mila.entities.lexicon.InterjectionException;
import org.mila.entities.lexicon.InterjectionExceptionAdd;
import org.mila.entities.lexicon.InterjectionExceptionRemove;
import org.mila.entities.lexicon.InterjectionExceptionReplace;
import org.mila.entities.lexicon.InterjectionLexicon;
import org.mila.entities.lexicon.Item;

public class InterjectionGen extends ItemGen {
	private InterjectionLexicon interjection;

	public InterjectionGen(Item item, EntityManager lexicon,
			EntityManager generator, EntityManager inflections) {
		super(item, lexicon, generator, inflections);
		interjection = (InterjectionLexicon) item.getSubitem();
	}

	protected List<InterjectionException> getReplaceExceptions() {
		return filter(Matchers.instanceOf(InterjectionExceptionReplace.class),
				interjection.getExceptions());
	}

	protected List<InterjectionException> getRemoveExceptions() {
		return filter(Matchers.instanceOf(InterjectionExceptionRemove.class),
				interjection.getExceptions());
	}

	protected boolean replaceExceptionExist() {
		for (InterjectionException ex : getReplaceExceptions()) {
			String exceptionPGN = ex.getPossessive();

			if (exceptionPGN.equals(PGN)) {
				inflectedItem = ex.getTransliterated();
				surface = ex.getUndotted();
				spelling = SpellingType.fromValue(ex.getSpelling().value());
				register = RegisterType.fromValue(ex.getRegister().value());
				// populateAcussativeAttribues();
				populateDatabase();
				return true;
			}
		}
		return false;
	}

	protected boolean removeExceptionExist() {
		for (InterjectionException ex : getRemoveExceptions()) {
			if (!ex.getPossessive().equals("unspecified"))
				suffixFunction = SuffixFunctionType.PRONOMIAL;
			if (ex.getPossessive().equals(PGN)) {
				return true;
			}
		}
		return false;
	}

	protected void addException() {
		analyseAddExceptionList(filter(
				Matchers.instanceOf(InterjectionExceptionAdd.class),
				interjection.getExceptions()));
	}

	private void analyseAddExceptionList(List<InterjectionException> exceptions) {
		for (InterjectionException ex : exceptions) {
			inflectedItem = ex.getTransliterated();
			surface = ex.getUndotted();
			spelling = SpellingType.fromValue(ex.getSpelling().value());
			register = RegisterType.fromValue(ex.getRegister().value());
			PGN = ex.getPossessive();
			if (!PGN.equals("unspecified"))
				suffixFunction = SuffixFunctionType.PRONOMIAL;
			populateDatabase();
		}
	}

	private void analyse() {
		analyseItem();
		inflect = interjection.isInflect();
		inflectedItem = transliterated;
		surface = undot;
		suffixFunction = SuffixFunctionType.UNSPECIFIED;
		PGN = "unspecified";
	}

	public List<Inflection> inflect() {
		analyse();
		populateDatabase();
		addException();
		if (inflect) {
			suffixes = "i,k,k,w,h,nw,km,kn,hm,hn";
			// PGNTokens =
			// "1p/MF/Sg,2p/F/Sg,2p/M/Sg,3p/M/Sg,3p/F/Sg,1p/MF/Pl,2p/F/Pl,2p/M/Pl,3p/F/Pl,3p/M/Pl";
			inflectPronomial(SuffixFunctionType.PRONOMIAL);
		}
		return this.getGeneratedInflections();
	}
}
