package org.mila;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Properties;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;

import org.apache.commons.beanutils.BeanComparator;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang.text.StrBuilder;
import org.junit.After;
import org.junit.Test;
import org.mila.entities.inflections.PersistableInflection;
import org.mila.entities.inflections.XMWEInflection;
import org.mila.entities.inflections.XMWExpression;
import org.mila.entities.lexicon.Item;
import org.mila.entities.lexicon.Number;
import org.mila.entities.lexicon.Pos;
import org.mila.entities.lexicon.SuffixFunction;
import org.mila.entities.lexicon.XMWEAtom;
import org.mila.entities.lexicon.XMWEAtomList;
import org.mila.entities.lexicon.XMWEAtomPointer;
import org.mila.entities.lexicon.XMWEConstraint;
import org.mila.entities.lexicon.XMWELexicon;
import org.mila.entities.lexicon.XMWETemplate;
import org.mila.entities.lexicon.XMWETemplateList;
import org.mila.generator.Generation;
import org.mila.generator.exceptions.ItemGenerationException;
import org.mila.generator.exceptions.ItemInflectionException;
import org.mila.generator.generation.ItemGen;

public class GenerationTest {

	private Properties lexicon_props = new Properties();
	private EntityManagerFactory lexicon_emf = null;
	private Properties inflections_props = new Properties();
	private EntityManagerFactory inflections_emf = null;
	private Properties generator_props = new Properties();
	private EntityManagerFactory generator_emf = null;
	static private boolean once = true;

	@org.junit.Before
	public void setUp() throws Exception {
		lexicon_props.load(this.getClass().getClassLoader()
				.getResourceAsStream("test_postgres_lexicon.props"));

		lexicon_emf = Persistence.createEntityManagerFactory(
				"org.mila.entities.corpus:org.mila.entities.lexicon",
				lexicon_props);
		inflections_props.load(this.getClass().getClassLoader()
				.getResourceAsStream("test_postgres_inflections.props"));

		inflections_emf = Persistence.createEntityManagerFactory(
				"org.mila.entities.inflections", inflections_props);
		generator_props.load(this.getClass().getClassLoader()
				.getResourceAsStream("test_generator.props"));

		generator_emf = Persistence.createEntityManagerFactory(
				"org.mila.entities.generator", generator_props);

		if (once)
			RulesLoader.LoadRules(generator_emf.createEntityManager());
		once = false;
	}

	@After
	public void tearDown() throws Exception {
		lexicon_emf.close();
		inflections_emf.close();
		generator_emf.close();
	}

	@Test
	public void testGeneration() {
		new Generation(lexicon_emf.createEntityManager(),
				generator_emf.createEntityManager(),
				inflections_emf.createEntityManager());
	}

	// @Test
	public void testGenerateAll() throws ItemGenerationException,
			ItemInflectionException {

		Generation gen = new Generation(lexicon_emf.createEntityManager(),
				generator_emf.createEntityManager(),
				inflections_emf.createEntityManager());
		gen.generateAll();
	}

	@SuppressWarnings("unchecked")
	@Test
	public void testXMWE() throws ItemGenerationException,
			ItemInflectionException, IllegalAccessException,
			InstantiationException, InvocationTargetException,
			NoSuchMethodException {
		final EntityManager inflections_em = inflections_emf
				.createEntityManager();
		final EntityManager generator_em = generator_emf.createEntityManager();
		final EntityManager lexicon_em = lexicon_emf.createEntityManager();

		/* I need it to be final so Java passes it to the lower scopes. Annoying */
		final Item fi8442 = lexicon_em.find(Item.class, "I8442");
		final Item fi3382 = lexicon_em.find(Item.class, "I3382");
		final Item fi4917 = lexicon_em.find(Item.class, "I4917");
		final Item fi608 = lexicon_em.find(Item.class, "I608");

		/* Create our new stuff */
		Item xmwe = (new Item() {
			private static final long serialVersionUID = 1L;

			{
				setId("I23986"); /*
								 * The ID can't actually be an integer. Annoying
								 */
				setTransliterated("akl at + bli mlx");
				setSubitem(new XMWELexicon() {
					private static final long serialVersionUID = 1L;
					{
						setPos(Pos.UNSPECIFIED);
						setAtoms(new XMWEAtomList() {
							private static final long serialVersionUID = 1L;
							{
								getAtom().add(new XMWEAtom() {
									private static final long serialVersionUID = 1L;
									{
										setId("I23986X1");
										setPointer(fi8442);
									}
								});
								getAtom().add(new XMWEAtom() {
									private static final long serialVersionUID = 1L;
									{
										setId("I23986X2");
										setPointer(fi3382);
									}
								});
								getAtom().add(new XMWEAtom() {
									private static final long serialVersionUID = 1L;
									{
										setId("I23986X4");
										setPointer(fi4917);
									}
								});
								getAtom().add(new XMWEAtom() {
									private static final long serialVersionUID = 1L;
									{
										setId("I23986X5");
										setPointer(fi608);
									}
								});
							}
						});
						setTemplates(new XMWETemplateList() {
							private static final long serialVersionUID = 1L;
							{
								/* First template */
								getTemplate().add(new XMWETemplate() {
									private static final long serialVersionUID = 1L;
									{
										getAtomOrAny().add(
												new XMWEAtomPointer() {
													private static final long serialVersionUID = 1L;
													{
														setPointer(getAtoms()
																.getAtom().get(
																		0));

														getConstraint()
																.add(new XMWEConstraint() {
																	private static final long serialVersionUID = 1L;
																	{
																		getPartOfSpeech()
																				.add(Pos.VERB);
																		getPartOfSpeech()
																				.add(Pos.PARTICIPLE);
																		getPartOfSpeech()
																				.add(Pos.PASSIVE_PARTICIPLE);
																		getSuffixFunction()
																				.add(SuffixFunction.ACCUSATIVE_OR_NOMINATIVE);
																		getSuffixFunction()
																				.add(SuffixFunction.UNSPECIFIED);
																	}
																});
													}
												});
										getAtomOrAny().add(
												new XMWEAtomPointer() {
													private static final long serialVersionUID = 1L;
													{
														setPointer(getAtoms()
																.getAtom().get(
																		1));
														getConstraint()
																.add(new XMWEConstraint() {
																	private static final long serialVersionUID = 1L;
																	{
																		getSuffixFunction()
																				.add(SuffixFunction.PRONOMIAL);
																	}
																});
													}
												});
										getAtomOrAny().add(
												new XMWEAtomPointer() {
													private static final long serialVersionUID = 1L;
													{
														setPointer(getAtoms()
																.getAtom().get(
																		2));
													}
												});
										getAtomOrAny().add(
												new XMWEAtomPointer() {
													private static final long serialVersionUID = 1L;
													{
														setPointer(getAtoms()
																.getAtom().get(
																		3));
														getConstraint()
																.add(new XMWEConstraint() {
																	private static final long serialVersionUID = 1L;
																	{
																		getBaseNumber()
																				.add(Number.SINGULAR);

																		getSuffixFunction()
																				.add(SuffixFunction.UNSPECIFIED);

																	}
																});
													}
												});

									}
								});
							}
						});
					}
				});

			}
		});

		/*
		 * this ugly hack is required to make the above declarative syntax play
		 * nice with the reflection dispatch Generation uses.
		 */
		XMWELexicon xmwelex = new XMWELexicon();
		BeanUtils.copyProperties(xmwelex, xmwe.getSubitem());
		xmwe.setSubitem(xmwelex);

		ItemGen gen = Generation.getGenerator(xmwe, lexicon_em, generator_em,
				inflections_em);
		List<PersistableInflection> res = gen.inflect();
		inflections_em.getTransaction().begin();
		inflections_em.createQuery("DELETE FROM XMWEInflection i")
				.executeUpdate();
		inflections_em.createQuery("DELETE FROM XMWExpression i")
				.executeUpdate();
		for (PersistableInflection inf : res) {
			inflections_em.persist(inf);
		}
		inflections_em.flush();
		inflections_em.getTransaction().commit();
		System.out.println("Number of inflections: " + res.size());
		res = null;

		inflections_em.clear();

		CriteriaBuilder cb = inflections_em.getCriteriaBuilder();
		CriteriaQuery<XMWExpression> q = cb.createQuery(XMWExpression.class);

		for (XMWExpression exp : inflections_em.createQuery(q).getResultList()) {
			// System.out.println("Expression id: " + exp.getId() + " pos: "
			// + exp.getPos() + " infs: " + exp.getInflections().size());
			List<XMWEInflection> xinfs = new ArrayList<XMWEInflection>(
					exp.getInflections());
			Collections.sort(xinfs, new BeanComparator("position"));

			StrBuilder sb = new StrBuilder();
			for (XMWEInflection xinf : xinfs) {
				sb.append(xinf.getSurface());
				sb.append(" ");
			}
			System.out.println(sb.toString());
		}

	}
}
