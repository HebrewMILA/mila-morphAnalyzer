package org.mila;

import java.util.Properties;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mila.generator.Generation;
import org.mila.generator.exceptions.ItemGenerationException;
import org.mila.generator.exceptions.ItemInflectionException;

public class GenerationTest {

	private Properties lexicon_props = new Properties();
	private EntityManagerFactory lexicon_emf = null;
	private Properties inflections_props = new Properties();
	private EntityManagerFactory inflections_emf = null;
	private Properties generator_props = new Properties();
	private EntityManagerFactory generator_emf = null;

	@Before
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

	@Test
	public void testGenerateAll() throws ItemGenerationException,
			ItemInflectionException {
		EntityManager generator = generator_emf.createEntityManager();
		RulesLoader.LoadRules(generator);
		Generation gen = new Generation(lexicon_emf.createEntityManager(),
				generator, inflections_emf.createEntityManager());
		gen.generateAll();
	}

}
