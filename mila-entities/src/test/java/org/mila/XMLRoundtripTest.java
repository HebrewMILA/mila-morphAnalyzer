package org.mila;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

import javax.xml.bind.JAXBException;
import javax.xml.parsers.ParserConfigurationException;

import org.apache.commons.io.output.ByteArrayOutputStream;
import org.custommonkey.xmlunit.DetailedDiff;
import org.custommonkey.xmlunit.Diff;
import org.custommonkey.xmlunit.Difference;
import org.custommonkey.xmlunit.NodeDetail;
import org.custommonkey.xmlunit.XMLUnit;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mila.lexicon.XMLReader;
import org.mila.lexicon.XMLWriter;
import org.w3c.dom.NamedNodeMap;
import org.xml.sax.SAXException;

public class XMLRoundtripTest {
    final String XMLFilename = "lexicon_20120515_fixed.xml";
    private XMLReader rdr = null;

    @Before
    public void before() {
	try {
	    rdr = new XMLReader(this.getClass().getClassLoader()
		    .getResourceAsStream(XMLFilename));
	} catch (JAXBException e) {
	    // TODO Auto-generated catch block
	    throw new RuntimeException(e);
	}
    }

    protected static void generateNodeText(StringBuilder sb, NodeDetail nd) {
	sb.append("<");
	if (nd.getNode() != null) {
	sb.append(nd.getNode().getNodeName());
	NamedNodeMap nnm = nd.getNode().getAttributes();
	if (null != nnm) {
	    for (int i = 0; i < nnm.getLength(); ++i) {
		sb.append(" ");
		sb.append(nnm.item(i).getNodeName());
		sb.append("=\"");
		sb.append(nnm.item(i).getNodeValue());
		sb.append("\"");
	    }
	}
	} else {
	    sb.append("?");
	}
	sb.append(">");
    }

    @Test
    public void test() throws JAXBException, SAXException, IOException,
	    ParserConfigurationException {
	XMLWriter wrtr = null;
	wrtr = new XMLWriter(rdr.getMetadata());
	ByteArrayOutputStream os = new ByteArrayOutputStream();
	wrtr.write(os, rdr.getItems());
	Diff myDiff = new Diff(new InputStreamReader(new ByteArrayInputStream(
		os.toByteArray())), new InputStreamReader(this.getClass()
		.getClassLoader().getResourceAsStream(XMLFilename)));
	DetailedDiff myDDiff = new DetailedDiff(myDiff);
	XMLUnit.setNormalizeWhitespace(true);
	XMLUnit.setCompareUnmatched(false);
	@SuppressWarnings({ "unchecked" })
	List<Difference> l = myDDiff.getAllDifferences();
	for (Difference d : l) {
	    if (d.isRecoverable())
		continue;
	    StringBuilder cnSb = new StringBuilder();
	    StringBuilder tnSb = new StringBuilder();
	    generateNodeText(cnSb, d.getControlNodeDetail());
	    generateNodeText(tnSb, d.getTestNodeDetail());
	    System.out.format("|%s| != |%s|", cnSb.toString(), tnSb.toString());
	    System.out.println();
	}
	Assert.assertTrue(myDiff.similar());
    }
}
