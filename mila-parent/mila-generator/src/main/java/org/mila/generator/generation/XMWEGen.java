package org.mila.generator.generation;

import java.util.ArrayList;
import java.util.Collection;
import java.util.EnumSet;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.EntityManager;

import org.mila.entities.inflections.Inflection;
import org.mila.entities.inflections.PersistableInflection;
import org.mila.entities.inflections.XMWEInflection;
import org.mila.entities.inflections.XMWExpression;
import org.mila.entities.lexicon.HomogeneousSet;
import org.mila.entities.lexicon.Item;
import org.mila.entities.lexicon.Pos;
import org.mila.entities.lexicon.XMWEAnything;
import org.mila.entities.lexicon.XMWEAtomPointer;
import org.mila.entities.lexicon.XMWEBase;
import org.mila.entities.lexicon.XMWELexicon;
import org.mila.entities.lexicon.XMWETemplate;
import org.mila.generator.Generation;
import org.mila.generator.exceptions.ItemGenerationException;
import org.mila.generator.generation.xmwe.Constraint;
import org.mila.generator.generation.xmwe.InflectionReflection;

import com.google.common.base.Function;
import com.google.common.base.Predicate;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.Iterables;
import com.google.common.collect.Sets;

public class XMWEGen extends ItemGen {

	private XMWELexicon xmwe;
	private List<PersistableInflection> generated_inflections;

	public XMWEGen(Item item, EntityManager lexicon, EntityManager generator,
			EntityManager inflections) {
		super(item, lexicon, generator, inflections);
		this.xmwe = (XMWELexicon) item.getSubitem();
		generated_inflections = new ArrayList<PersistableInflection>();
	}

	@Override
	public List<PersistableInflection> inflect() {
		for (final XMWETemplate tmplt : xmwe.getTemplates().getTemplate()) {
			long position = 0;
			List<Set<Inflection>> all_inflections = new ArrayList<Set<Inflection>>();
			for (final XMWEBase b : tmplt.getAtomOrAny()) {
				position++;
				if (b instanceof XMWEAnything)
					throw new java.lang.UnsupportedOperationException();
				final XMWEAtomPointer p = (XMWEAtomPointer) b;
				all_inflections.add(new HashSet<Inflection>(
						processGeneratedInflections(position, p)));
			}
			Set<List<Inflection>> cartesianProduct = Sets
					.cartesianProduct(all_inflections);

			for (List<Inflection> candidate : cartesianProduct) {
				if (!checkInflectionList(tmplt.getHomogeneousSet(), candidate))
					continue;
				if (candidate.isEmpty())
					continue;

				XMWExpression expression = new XMWExpression();
				expression.setBaseLexiconPointer(item.getId());
				expression.setPos(xmwe.isSetPos() ? xmwe.getPos().enumValue()
						: Pos.UNSPECIFIED.enumValue());
				generated_inflections.add(expression);

				for (Inflection inf : candidate) {
					XMWEInflection xinf = null;
					try {
						xinf = (XMWEInflection) org.apache.commons.beanutils.BeanUtils
								.cloneBean(inf);
					} catch (Exception e) {
						throw new RuntimeException(
								"Couldn't create new inflection", e);
					}
					xinf.setExpression(expression);
					generated_inflections.add(xinf);
				}
			}

		}
		return java.util.Collections.unmodifiableList(generated_inflections);
	}

	private Collection<Inflection> processGeneratedInflections(
			final long position, final XMWEAtomPointer atom_pointer) {
		/* get the correct generator for the given atom */
		final ItemGen subgen;
		try {
			subgen = Generation.getGenerator(atom_pointer.getPointer()
					.getPointer(), lexicon, generator, inflections);
		} catch (ItemGenerationException e) {
			throw new RuntimeException("Failed to get subgenerator!", e);
		}
		/*
		 * get all the generated inflections for the atom, and remove the ones
		 * not matching the constraint.
		 */
		Iterable<Inflection> l;
		final Constraint constraint = new Constraint(
				atom_pointer.getConstraint());

		l = Iterables.filter(
				Iterables.filter(subgen.inflect(), Inflection.class),
				new Predicate<Inflection>() {
					@Override
					public boolean apply(Inflection inf) {
						System.err.println("Moof: " + constraint.match(inf));
						return constraint.match(inf);
					}
				});
		/*
		 * transform the collection of Inflection instances into a collection of
		 * XMWEInflection instances. Also add the position in the phrase and
		 * anchor status.
		 */
		l = Iterables.transform(l, new Function<Inflection, Inflection>() {
			@Override
			public Inflection apply(Inflection inf) {
				XMWEInflection ret = XMWEInflection.fromInflection(inf);
				ret.setPosition(position);
				ret.setAnchor(atom_pointer.getPointer().isSetAnchor()
						&& atom_pointer.getPointer().isAnchor());
				return ret;
			}
		});
		List<Inflection> ret = ImmutableList.copyOf(l);
		return ret;
	}

	private boolean checkInflectionList(List<HomogeneousSet> checks,
			List<Inflection> candidate) {
		if (checks.isEmpty() || candidate.isEmpty())
			return true; /* vacuous truth */
		for (HomogeneousSet check : checks) {
			Enum<?> property = (Enum<?>) InflectionReflection
					.getFromInflection(candidate.get(0), check.getProperty());
			/* XXX: What to do about IndexOutOfBoundsException? */
			if (!checkInflection(check, candidate, property))
				return false;
		}
		return true;
	}

	private <T extends Enum<T>> boolean checkInflection(HomogeneousSet check,
			List<Inflection> candidate, Enum<T> property) {
		Set<T> checkset = EnumSet.noneOf(property.getDeclaringClass());
		for (Long l : check.getPosition()) {
			Inflection inf = candidate.get(l.intValue());
			/* Java's generics mechanism is... wacky */
			@SuppressWarnings("unchecked")
			T propertyInPosition = (T) InflectionReflection.getFromInflection(
					inf, check.getProperty());
			checkset.add(propertyInPosition);
		}
		if (checkset.size() != 1)
			return false;
		return true;
	}
}
