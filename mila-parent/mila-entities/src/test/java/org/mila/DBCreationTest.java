package org.mila;

import java.util.Properties;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.junit.Before;
import org.junit.Test;

public class DBCreationTest {
    private Properties inflections_props = new Properties();
    private Properties generator_props = new Properties();
    private Properties lexicon_props = new Properties();

    @Before
    public void setUp() throws Exception {
	inflections_props.load(this.getClass().getClassLoader()
		.getResourceAsStream("test_inflections.props"));
	generator_props.load(this.getClass().getClassLoader()
		.getResourceAsStream("test_generator.props"));
	lexicon_props.load(this.getClass().getClassLoader()
		.getResourceAsStream("test_lexicon.props"));
    }

    @Test
    public void test() {
	EntityManagerFactory inflections_emf = Persistence
		.createEntityManagerFactory("org.mila.entities.inflections",
			inflections_props);
	EntityManagerFactory generator_emf = Persistence
		.createEntityManagerFactory("org.mila.entities.generator",
			generator_props);
	EntityManagerFactory lexicon_emf = Persistence
		.createEntityManagerFactory(
			"org.mila.entities.corpus:org.mila.entities.lexicon",
			lexicon_props);
	inflections_emf.createEntityManager();
	generator_emf.createEntityManager();
	lexicon_emf.createEntityManager();
    }
}
