package org.mila.entities;

import java.util.Properties;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class EntityManagerHelper {
    public static EntityManager createLexiconEntityManager(Properties props) {
	EntityManagerFactory emf = Persistence
		.createEntityManagerFactory(
			"org.mila.entities.corpus:org.mila.entities.lexicon",
			props);
	return emf.createEntityManager();
    }
    
    public static EntityManager createGeneratorEntityManager(Properties props) {
	EntityManagerFactory emf = Persistence
		.createEntityManagerFactory(
			"org.mila.entities.generator",
			props);
	return emf.createEntityManager();
    }
    
    public static EntityManager createInflectionsEntityManager(Properties props) {
	EntityManagerFactory emf = Persistence
		.createEntityManagerFactory(
			"org.mila.entities.inflections",
			props);
	return emf.createEntityManager();
    }
}
