package org.mila.generator.generation;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.apache.commons.lang.text.StrTokenizer;
import org.mila.entities.inflections.MWElement;
import org.mila.entities.inflections.MWFirstElement;
import org.mila.entities.inflections.MWOtherElement;

public class PopulateMWE {

	private EntityManager inflections = null;

	public PopulateMWE(EntityManager inflections) {
		this.inflections = inflections;
	}

	public void populateMWETables(ItemGen item, boolean prefix) {
		createElementsFor(item, prefix);
	}

	public void populateMWETables(ItemGen item, boolean prefix,
			String mwTransliterated, String mwUndotted) {
		MWOtherElement tail = createElementsFor(item, prefix);
		tail.setMwTransliterated(mwTransliterated);
		tail.setMwUndotted(mwUndotted);
	}

	private MWOtherElement createElementsFor(ItemGen item, boolean prefix) {
		StrTokenizer st = new StrTokenizer(item.getTransliterated());
		if (!st.hasNext())
			return null;

		String word = st.nextToken();
		int seq = 1;
		MWFirstElement first = findOrCreateFirstElement(item, word, seq);
		MWElement last = first;

		while (st.hasNext()) {
			seq += 1;
			word = st.nextToken();
			last = findOrCreateElement(word, seq, last, !st.hasNext());
		}

		/* this takes care of acronym exceptions. yes it's weird */
		if (!(last instanceof MWOtherElement))
			last = findOrCreateElement(word, 2, last, true);

		MWOtherElement tail = (MWOtherElement) last;
		tail.setConsecutive(false);
		tail.setDefiniteness(item.getDefinitnessVal());
		tail.setDottedLexiconItem(item.getDottedLexiconItem());
		tail.setGender(item.getGender());
		tail.setLexiconId(item.getItem().getId());
		tail.setNumber(item.getNumber());
		tail.setPossessive(item.getPGN());
		tail.setRegister(item.getRegister());
		tail.setSpelling(item.getSpelling());
		tail.setTransliteratedLexiconItem(item.getItem().getTransliterated());
		tail.setUndottedLexiconItem(item.getItem().getUndotted());
		tail.setMwTransliterated(item.getItem().getTransliterated());
		tail.setMwUndotted(item.getItem().getUndotted());
		inflections.persist(tail);
		return tail;
	}

	/*
	 * This function does not create the last element in the sequence.
	 */
	private MWOtherElement findOrCreateElement(String word, int seq,
			MWElement last, boolean isLastElement) {
		MWOtherElement curr = new MWOtherElement();

		curr.setTransliterated(word);
		curr.setSurface(org.mila.generator.utils.Transliteration.toHebrew(word));
		curr.setFormerElement(last);
		curr.setSequence(seq);
		curr.setConsecutive(true);
		CriteriaBuilder cb = inflections.getCriteriaBuilder();
		CriteriaQuery<MWOtherElement> cq = cb.createQuery(MWOtherElement.class);
		Root<MWOtherElement> root = cq.from(MWOtherElement.class);
		cq.where(cb.and(
				cb.equal(root.get("transliterated"), curr.getTransliterated()),
				cb.equal(root.get("surface"), curr.getSurface()),
				cb.equal(root.get("formerElement"), curr.getFormerElement()),
				cb.equal(root.get("sequence"), curr.getSequence()),
				cb.equal(root.get("consecutive"), curr.isConsecutive())));
		cq.distinct(true);
		CriteriaQuery<Long> countCq = cb.createQuery(Long.class);
		countCq.where(cq.getRestriction());
		countCq.select(cb.count(root));
		if (inflections.createQuery(countCq).getSingleResult() != 0) {
			curr = inflections.createQuery(cq).getSingleResult();
		} else if (!isLastElement)
			inflections.persist(curr);
		return curr;
	}

	private MWFirstElement findOrCreateFirstElement(ItemGen item, String word,
			int seq) {
		MWFirstElement first = null;

		CriteriaBuilder cb = inflections.getCriteriaBuilder();
		CriteriaQuery<MWFirstElement> cq = cb.createQuery(MWFirstElement.class);
		Root<MWFirstElement> root = cq.from(MWFirstElement.class);
		cq.where(cb.and(cb.equal(root.get("transliterated"), word), cb.equal(
				root.get("surface"),
				org.mila.generator.utils.Transliteration.toHebrew(word)), cb
				.equal(root.get("pos"), item.getBasePos()), cb.equal(
				root.get("type"), item.getType()), cb.equal(
				root.get("sequence"), seq)));
		cq.distinct(true);
		CriteriaQuery<Long> countCq = cb.createQuery(Long.class);
		countCq.where(cq.getRestriction());
		countCq.select(cb.count(root));
		if (inflections.createQuery(countCq).getSingleResult() != 0) {
			first = inflections.createQuery(cq).getSingleResult();
		} else {
			first = new MWFirstElement();
			first.setTransliterated(word);
			first.setSurface(org.mila.generator.utils.Transliteration
					.toHebrew(word));
			first.setPos(item.getBasePos());
			first.setType(item.getType());
			first.setSequence(seq);
			first.setConsecutive(true);
			inflections.persist(first);
		}
		return first;
	}
}
