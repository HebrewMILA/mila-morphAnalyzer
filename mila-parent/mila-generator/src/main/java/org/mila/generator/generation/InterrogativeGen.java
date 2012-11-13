package org.mila.generator.generation;

import static ch.lambdaj.Lambda.filter;

import java.util.List;

import javax.persistence.EntityManager;

import org.hamcrest.Matchers;
import org.mila.entities.corpus.RegisterType;
import org.mila.entities.corpus.SpellingType;
import org.mila.entities.corpus.SuffixFunctionType;
import org.mila.entities.inflections.Inflection;
import org.mila.entities.lexicon.InterrogativeException;
import org.mila.entities.lexicon.InterrogativeExceptionAdd;
import org.mila.entities.lexicon.InterrogativeExceptionRemove;
import org.mila.entities.lexicon.InterrogativeExceptionReplace;
import org.mila.entities.lexicon.InterrogativeLexicon;
import org.mila.entities.lexicon.Item;

public class InterrogativeGen extends ItemGen {
	InterrogativeLexicon interrogative;

	public InterrogativeGen(Item item, EntityManager lexicon,
			EntityManager generator, EntityManager inflections) {
		super(item, lexicon, generator, inflections);
		interrogative = (InterrogativeLexicon) item.getSubitem();
	}

	protected List<InterrogativeException> getReplaceExceptions() {
		return filter(Matchers.instanceOf(InterrogativeExceptionReplace.class),
				interrogative.getExceptions());
	}

	protected List<InterrogativeException> getRemoveExceptions() {
		return filter(Matchers.instanceOf(InterrogativeExceptionRemove.class),
				interrogative.getExceptions());
	}

	protected boolean replaceExceptionExist() {
		for (InterrogativeException ex : getReplaceExceptions()) {

			inflectedItem = ex.getTransliterated();
			surface = ex.getUndotted();
			spelling = SpellingType.fromValue(ex.getSpelling().value());
			register = RegisterType.fromValue(ex.getRegister().value());
			populateDatabase();
			return true;
		}
		return false;
	}

	protected void addException() {
		analyseAddExceptionList(filter(
				Matchers.instanceOf(InterrogativeExceptionAdd.class),
				interrogative.getExceptions()));
	}

	private void analyseAddExceptionList(List<InterrogativeException> exceptions) {
		for (InterrogativeException ex : exceptions) {
			inflectedItem = ex.getTransliterated();
			surface = ex.getUndotted();
			spelling = SpellingType.fromValue(ex.getSpelling().value());
			register = RegisterType.fromValue(ex.getRegister().value());
			PGN = ex.getPossessive();
			/*
			 * if(!PGN.equals("unspecified")) { suffixFunction="pronomial"; }
			 */
			populateDatabase();
		}
	}

	private void analyse() {
		analyseItem();
		inflectedItem = transliterated;
		surface = undot;
		suffixFunction = SuffixFunctionType.UNSPECIFIED;
		PGN = "unspecified";
		type = interrogative.getInterrogativeType().value();
	}

	public List<Inflection> inflect() {
		/* XXX: There is no logic for remove functionality */
		analyse();
		populateDatabase();
		addException();
		return this.getGeneratedInflections();
	}
}
