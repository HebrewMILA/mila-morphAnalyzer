package org.mila.generator;

import java.lang.reflect.Constructor;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.eclipse.persistence.expressions.ExpressionBuilder;
import org.eclipse.persistence.jpa.JpaEntityManager;
import org.eclipse.persistence.queries.ReadAllQuery;
import org.mila.entities.inflections.PersistableInflection;
import org.mila.entities.lexicon.Item;
import org.mila.generator.exceptions.ItemGenerationException;
import org.mila.generator.exceptions.ItemInflectionException;
import org.mila.generator.generation.ItemGen;

/**
 * Class in charge of managing generation of word inflections.
 * 
 * @author Matan Peled
 * 
 */
public class Generation {
	private EntityManager lexicon, generator, inflections;

	/**
	 * @param lexicon
	 *            EntityManager for the lexicon DB
	 * @param generator
	 *            EntityManager for generator inflection rules DB
	 * @param inflections
	 *            EntityManager for inflections DB
	 */
	public Generation(EntityManager lexicon, EntityManager generator,
			EntityManager inflections) {
		this.lexicon = lexicon;
		this.generator = generator;
		this.inflections = inflections;
	}

	/**
	 * @param item
	 *            The item to for which to generate inflections.
	 * @throws ItemGenerationException
	 *             if a generator for the item could not be instantiated.
	 * @throws ItemInflectionException
	 *             if the item could not be inflected.
	 */
	public void generateItem(Item item) throws ItemGenerationException,
			ItemInflectionException {
		ItemGen gen = getGenerator(item, lexicon, generator, inflections);
		this.inflections.getTransaction().begin();
		try {
			try {
				this.removeInflectionsFor(item);
			} catch (Exception e) {
				throw new ItemInflectionException(
						"Failed to remove old inflections for " + item.getId(),
						e);
			}
			try {
				for (PersistableInflection inf : gen.inflect()) {
					this.inflections.persist(inf);
				}
			} catch (Exception e) {
				throw new ItemInflectionException("Failed to inflect "
						+ item.getId() + ", \"" + item.getTransliterated()
						+ "\"", e);
			}
		} catch (ItemInflectionException e) {
			this.inflections.getTransaction().rollback();
			throw e;
		}
		this.inflections.getTransaction().commit();
		this.inflections.clear(); /* good voodoo to make things go faster! */
	}

	public static ItemGen getGenerator(Item item, EntityManager lexicon,
			EntityManager generator, EntityManager inflections)
			throws ItemGenerationException {
		final String className = item.getSubitem().getClass().getSimpleName()
				.replaceFirst("Lexicon$", "Gen");
		StringBuffer sb = new StringBuffer("org.mila.generator.generation.")
				.append(className);

		ItemGen gen = null;

		try {
			@SuppressWarnings("unchecked")
			Constructor<ItemGen> cons = (Constructor<ItemGen>) Class.forName(
					sb.toString()).getConstructor(Item.class,
					EntityManager.class, EntityManager.class,
					EntityManager.class);
			gen = cons.newInstance(item, lexicon, generator, inflections);
		} catch (Exception e) {
			throw new ItemGenerationException(
					"Failed to instantiate generator", e);
		}
		return gen;
	}

	public void removeInflectionsFor(Item item) {
		Query q = inflections
				.createQuery("DELETE FROM Inflection i WHERE i.baseLexiconPointer = :id");
		q.setParameter("id", item.getId());
		q.executeUpdate();
	}

	public void generateItems(List<Item> items) throws ItemGenerationException,
			ItemInflectionException {
		for (Item item : items) {
			/*
			 * TODO: Handle the exceptions here, and return a list of failed
			 * items
			 */
			this.generateItem(item);
		}
	}

	public void generateAll() throws ItemGenerationException,
			ItemInflectionException {
		ExpressionBuilder eb = new ExpressionBuilder();
		ReadAllQuery q = new ReadAllQuery(Item.class, eb);
		@SuppressWarnings("unchecked")
		List<Item> l = ((JpaEntityManager) lexicon.getDelegate())
				.createQuery(q).getResultList();
		this.generateItems(l);
	}

	public void generateByIds(List<String> ids) throws ItemGenerationException,
			ItemInflectionException {
		ExpressionBuilder eb = new ExpressionBuilder();
		ReadAllQuery q = new ReadAllQuery(Item.class, eb);
		q.setSelectionCriteria(eb.get("id").in(ids));
		@SuppressWarnings("unchecked")
		List<Item> l = ((JpaEntityManager) lexicon.getDelegate())
				.createQuery(q).getResultList();
		this.generateItems(l);
	}
}