/*
 * Created on 18/09/2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package org.mila.generator.generation;

import static ch.lambdaj.Lambda.filter;

import java.util.List;

import javax.persistence.EntityManager;

import org.hamcrest.Matchers;
import org.mila.entities.corpus.DefinitenessType;
import org.mila.entities.corpus.RegisterType;
import org.mila.entities.corpus.SpellingType;
import org.mila.entities.corpus.SuffixFunctionType;
import org.mila.entities.corpus.TriStateType;
import org.mila.entities.inflections.Inflection;
import org.mila.entities.lexicon.Item;
import org.mila.entities.lexicon.QuantifierException;
import org.mila.entities.lexicon.QuantifierExceptionAdd;
import org.mila.entities.lexicon.QuantifierExceptionRemove;
import org.mila.entities.lexicon.QuantifierExceptionReplace;
import org.mila.entities.lexicon.QuantifierLexicon;
import org.mila.entities.lexicon.QuantifierType;

/**
 * @author daliabo
 * 
 *         TODO To change the template for this generated type comment go to
 *         Window - Preferences - Java - Code Style - Code Templates
 */
public class QuantifierGen extends ItemGen {
	QuantifierLexicon quantifier;

	public QuantifierGen(Item item, EntityManager lexicon,
			EntityManager generator, EntityManager inflections) {
		super(item, lexicon, generator, inflections);
		quantifier = (QuantifierLexicon) item.getSubitem();
	}

	private void addExceptions() {
		analyseAddExceptionList(filter(
				Matchers.instanceOf(QuantifierExceptionAdd.class),
				quantifier.getExceptions()));
	}

	protected List<QuantifierException> getReplaceExceptions() {
		return filter(Matchers.instanceOf(QuantifierExceptionReplace.class),
				quantifier.getExceptions());
	}

	protected boolean replaceExceptionExist() {
		for (QuantifierException ex : getReplaceExceptions()) {
			String exPGN = ex.getPossessive();
			if (exPGN.equals(PGN)) {
				PGN = exPGN;
				inflectedItem = ex.getTransliterated();
				surface = ex.getUndotted();
				spelling = SpellingType.fromValue(ex.getSpelling().value());
				register = RegisterType.fromValue(ex.getRegister().value());
				populateDatabase();
				return true;
			}
		}
		return false;
	}

	protected List<QuantifierException> getRemoveExceptions() {
		return filter(Matchers.instanceOf(QuantifierExceptionRemove.class),
				quantifier.getExceptions());
	}

	protected boolean removeExceptionExist() {
		for (QuantifierException ex : getRemoveExceptions())
			if (ex.getPossessive().equals(PGN))
				return true;

		return false;
	}

	private void analyseAddExceptionList(List<QuantifierException> exceptions) {
		for (QuantifierException ex : exceptions) {
			inflectedItem = ex.getTransliterated();
			surface = ex.getUndotted();
			spelling = SpellingType.fromValue(ex.getSpelling().value());
			register = RegisterType.fromValue(ex.getRegister().value());
			PGN = ex.getPossessive();
			construct = TriStateType.fromValue(ex.getConstruct().value());

			if (!PGN.equals("unspecified")) {
				suffixFunction = SuffixFunctionType.PRONOMIAL;
				construct = TriStateType.UNSPECIFIED;
			}
			if (construct != TriStateType.TRUE
					&& quantifier.getType() == QuantifierType.PARTITIVE
					&& PGN.equals("unspecified")) {
				definitnessVal = DefinitenessType.FALSE;
			}

			populateDatabase();
			if (construct != TriStateType.TRUE
					&& quantifier.getType() == QuantifierType.PARTITIVE
					&& PGN.equals("unspecified")) {
				construct = TriStateType.UNSPECIFIED;
				addH();
			}
		}
	}

	private void analyse() {
		analyseItem();
		type = quantifier.getType().value();
		inflect = quantifier.isInflect();
		inflectionBase = quantifier.getInflectionBase();
		inflectedItem = transliterated;
		surface = undot;
		suffixFunction = SuffixFunctionType.UNSPECIFIED;
		construct = TriStateType.FALSE;
		PGN = "unspecified";
		definitness = quantifier.getDefiniteness();
	}

	private void generatePronomial() {
		if (inflect) {
			suffixes = "i,k,k,w,h,nw,km,kn,hm,hn";
			inflectPronomial(SuffixFunctionType.PRONOMIAL);
			suffixFunction = SuffixFunctionType.UNSPECIFIED;
			PGN = "unspecified";
		}
	}

	protected void addH() {
		definitnessVal = DefinitenessType.TRUE;
		super.addH();
		definitnessVal = DefinitenessType.UNSPECIFIED;
	}

	public List<Inflection> inflect() {
		analyse();

		switch (quantifier.getType()) {
		case AMOUNT:
			definitnessVal = DefinitenessType.UNSPECIFIED;
			construct = TriStateType.TRUE;
			populateDatabase();
			construct = TriStateType.FALSE;
			populateDatabase();
			break;
		case PARTITIVE:
			definitnessVal = DefinitenessType.FALSE;
			construct = TriStateType.TRUE;
			populateDatabase();
			definitnessVal = DefinitenessType.TRUE;
			construct = TriStateType.FALSE;
			populateDatabase();
			construct = TriStateType.UNSPECIFIED;
			addH();
			if (inflect) {
				definitnessVal = DefinitenessType.UNSPECIFIED;
				suffixes = "i,k,k,w,h,nw,km,kn,hm,hn";
				inflectPronomial(SuffixFunctionType.PRONOMIAL);
				suffixFunction = SuffixFunctionType.UNSPECIFIED;
				PGN = "unspecified";
			}
			break;
		case DETERMINER:
			definitnessVal = DefinitenessType.UNSPECIFIED;
			construct = TriStateType.TRUE;
			populateDatabase();
			generatePronomial();
			break;
		case UNSPECIFIED:
			/* do nothing */
			break;
		}

		addExceptions();
		return this.getGeneratedInflections();
	}
}
