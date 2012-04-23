package org.mila;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.spi.PersistenceUnitTransactionType;

import org.eclipse.persistence.config.PersistenceUnitProperties;
import org.eclipse.persistence.config.TargetServer;
import org.eclipse.persistence.expressions.ExpressionBuilder;
import org.eclipse.persistence.jpa.JpaEntityManager;
import org.eclipse.persistence.queries.ReadAllQuery;
import org.eclipse.persistence.queries.ReportQuery;
import org.mila.lexicon.entities.GenderType;
import org.mila.lexicon.entities.ItemType;
import org.mila.lexicon.entities.NounLexiconType;
import org.mila.lexicon.entities.ObjectFactory;
import org.mila.lexicon.entities.TwoStateType;
import org.mila.lexicon.helpers.ItemHelper;

/**
 * Hello world!
 * 
 */
public class App {
    public static void main(String[] args) {

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
	//props.put(PersistenceUnitProperties.DDL_GENERATION, "create-tables");

	// Configure logging. FINE ensures all SQL is shown
	props.put(PersistenceUnitProperties.LOGGING_LEVEL, "INFO");

	// Ensure that no server-platform is configured
	props.put(PersistenceUnitProperties.TARGET_SERVER, TargetServer.None);

	props.put("provider", "org.eclipse.persistence.jpa.PersistenceProvider");

	EntityManagerFactory emf = Persistence.createEntityManagerFactory(
		"org.mila.corpus.entities:org.mila.lexicon.entities", props);
	EntityManager em = emf.createEntityManager();

	ObjectFactory of = new ObjectFactory();
	ItemType item = of.createItemType();
	item.setUndotted("שלום");
	item.setTransliterated("slom");
	NounLexiconType noun = of.createNounLexiconType();
	noun.setDirection(TwoStateType.TRUE);
	noun.setGender(GenderType.MASCULINE_AND_FEMININE);
	noun.setDual(false);
	noun.isDual();
	item.setNoun(noun);

	
	em.getTransaction().begin();
	em.persist(item);
	em.getTransaction().commit();
	
	ExpressionBuilder eb = new ExpressionBuilder();
	ReadAllQuery dbq = new ReadAllQuery(ItemType.class, eb);
	dbq.setSelectionCriteria(eb.get("noun").get("gender").equal(GenderType.MASCULINE_AND_FEMININE));
	Query q = ((JpaEntityManager)em.getDelegate()).createQuery(dbq);
	
	List<ItemType> res = q.getResultList();
	
	for (ItemType i : res) {
	    System.out.println(ItemHelper.getSubItemEnum(i).toString());
	}
	
	ReportQuery rq = new ReportQuery(ItemType.class, eb);
	
	
	// server.save(item);
	// System.out.println(server.find(ItemType.class).getTotalHits());
    }
}
