package org.mila;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.spi.PersistenceUnitTransactionType;
import javax.xml.bind.JAXBException;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;
import org.eclipse.persistence.config.PersistenceUnitProperties;
import org.eclipse.persistence.config.TargetServer;
import org.eclipse.persistence.expressions.ExpressionBuilder;
import org.eclipse.persistence.jpa.JpaEntityManager;
import org.eclipse.persistence.queries.ReadAllQuery;
import org.eclipse.persistence.queries.ReportQuery;
import org.mila.entities.corpus.GenderType;
import org.mila.entities.lexicon.Item;
import org.mila.lexicon.ItemHelper;
import org.mila.lexicon.XMLReader;

/**
 * Hello world!
 * 
 */
public class App {
    public static void main(String[] args) {
	BasicConfigurator.configure();
	Logger log = Logger.getRootLogger();
	Map<String, String> props = new HashMap<String, String>();

	props.put(PersistenceUnitProperties.TRANSACTION_TYPE,
		PersistenceUnitTransactionType.RESOURCE_LOCAL.name());

	// Configure the internal EclipseLink connection pool
	props.put(PersistenceUnitProperties.JDBC_DRIVER,
		"org.postgresql.Driver");
	props.put(PersistenceUnitProperties.JDBC_URL,
		"jdbc:postgresql://127.0.0.1:5432/lexicon");
	props.put(PersistenceUnitProperties.JDBC_USER, "lexiconuser");
	props.put(PersistenceUnitProperties.JDBC_PASSWORD, "!m#w@e$");
	props.put(PersistenceUnitProperties.JDBC_READ_CONNECTIONS_MIN, "1");
	props.put(PersistenceUnitProperties.JDBC_WRITE_CONNECTIONS_MIN, "1");
	props.put(PersistenceUnitProperties.DDL_GENERATION, "create-tables");

	// Configure logging. FINE ensures all SQL is shown
	props.put(PersistenceUnitProperties.LOGGING_LEVEL, "INFO");

	// Ensure that no server-platform is configured
	props.put(PersistenceUnitProperties.TARGET_SERVER, TargetServer.None);

	props.put("provider", "org.eclipse.persistence.jpa.PersistenceProvider");

	EntityManagerFactory emf = Persistence.createEntityManagerFactory(
		"org.mila.entities.corpus:org.mila.entities.lexicon", props);
	EntityManager em = emf.createEntityManager();

	log.info("Loading XML from: " + args[0]);
	try {
	    XMLReader rdr = new XMLReader(new FileInputStream(args[0]));
	    em.getTransaction().begin();
	    for (Item item : rdr.getItems()) {
		em.persist(item);
	    }
	    em.getTransaction().commit();
	} catch (FileNotFoundException e) {
	    log.error("Couldn't open XML file", e);
	} catch (JAXBException e) {
	    log.error("Couldn't parse XML file", e);
	}
	log.info("Done loading XML");
	
	ExpressionBuilder eb = new ExpressionBuilder();
	ReadAllQuery dbq = new ReadAllQuery(Item.class, eb);
	dbq.setSelectionCriteria(eb.get("noun").get("gender").equal(GenderType.MASCULINE_AND_FEMININE));
	Query q = ((JpaEntityManager)em.getDelegate()).createQuery(dbq);
	
	@SuppressWarnings("unchecked")
	List<Item> res = q.getResultList();
	
	log.debug("Printing search results");
	for (Item i : res) {
	    log.debug(ItemHelper.getSubItemEnum(i));
	}
	log.debug("End search results");
	
	@SuppressWarnings("unused")
	ReportQuery rq = new ReportQuery(Item.class, eb);
    }
}
