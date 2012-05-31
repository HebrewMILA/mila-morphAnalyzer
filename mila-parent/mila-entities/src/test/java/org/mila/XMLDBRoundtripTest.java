package org.mila;

import static org.junit.Assert.fail;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;
import java.util.Properties;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.xml.bind.JAXBException;

import org.apache.commons.io.output.ByteArrayOutputStream;
import org.custommonkey.xmlunit.DetailedDiff;
import org.custommonkey.xmlunit.Diff;
import org.custommonkey.xmlunit.Difference;
import org.custommonkey.xmlunit.XMLUnit;
import org.eclipse.persistence.expressions.ExpressionBuilder;
import org.eclipse.persistence.jpa.JpaEntityManager;
import org.eclipse.persistence.queries.ReadAllQuery;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mila.entities.lexicon.Item;
import org.mila.lexicon.XMLReader;
import org.mila.lexicon.XMLWriter;
import org.w3c.dom.Node;
import org.xml.sax.SAXException;

public class XMLDBRoundtripTest {
    private Properties lexicon_props = new Properties();
    private EntityManagerFactory lexicon_emf = null;
    final String XMLFilename = "lexicon_20120515_fixed.xml";
    private XMLReader rdr = null;

    @Before
    public void setUp() throws Exception {
	lexicon_props.load(this.getClass().getClassLoader()
		.getResourceAsStream("lexicon.props"));

	lexicon_emf = Persistence.createEntityManagerFactory(
		"org.mila.entities.corpus:org.mila.entities.lexicon",
		lexicon_props);
	rdr = new XMLReader(this.getClass().getClassLoader()
		.getResourceAsStream(XMLFilename));

    }

    @After
    public void tearDown() throws Exception {
	lexicon_emf.close();
    }

    @SuppressWarnings("unchecked")
    @Test
    public void test() throws SAXException, IOException, JAXBException {
	EntityManager em = lexicon_emf.createEntityManager();
	em.getTransaction().begin();
	for (Item item : rdr.getItems()) {
	    em.persist(item);
	}
	em.getTransaction().commit();
	
	ExpressionBuilder eb = new ExpressionBuilder();
	ReadAllQuery dbq = new ReadAllQuery(Item.class, eb);
	Query q = ((JpaEntityManager)em.getDelegate()).createQuery(dbq);
	List<Item> l = q.getResultList();
	
	XMLWriter wrtr = new XMLWriter(rdr.getMetadata());
	ByteArrayOutputStream os = new ByteArrayOutputStream();
	wrtr.write(os, l);
	
	rdr = new XMLReader(new ByteArrayInputStream(
		os.toByteArray()));
	for (Item item : rdr.getItems()) {
	    Item item2 = em.find(Item.class, item.getId());
	    Assert.assertEquals(item, item2);
	}
	rdr = new XMLReader(this.getClass().getClassLoader()
		.getResourceAsStream(XMLFilename));
	for (Item item : rdr.getItems()) {
	    Item item2 = em.find(Item.class, item.getId());
	    Assert.assertEquals(item, item2);
	}
	em.close();
    }

}
