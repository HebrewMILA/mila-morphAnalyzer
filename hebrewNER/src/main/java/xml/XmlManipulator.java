package xml;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import tagging.Sentence;
import tagging.Token;

public class XmlManipulator implements XmlManipulatorInterface {
	private final static DocumentBuilder db = docBuilder();
	private final static TransformerFactory tFactory = TransformerFactory.newInstance();
	private static final double p = 0.2;

	private final Document doc;

	public XmlManipulator(String filename) {
		this.doc = parseDoc(filename);
	}

	public static List<Element> nodeListToList(NodeList ns) {
		List<Element> res = new ArrayList<>();
		for (int i = 0; i < ns.getLength(); i++)
			if (ns.item(i) instanceof Element)
				res.add((Element) ns.item(i));
		return res;
	}

	public static Document parseDoc(String filename) {
		try {
			return db.parse(new File(filename));
		} catch (SAXException | IOException e) {
			e.printStackTrace();
			return null;
		}
	}

	private static DocumentBuilder docBuilder() {
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		try {
			return dbf.newDocumentBuilder();
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
			return null;
		}
	}

	private Token elementToToken(Element t) {
		List<Analysis> as = nodeListToList(t.getChildNodes()).stream().map(this::elementToAnalysis)
				.collect(Collectors.toList());
		String entity = String.join("|", as.stream().map(x -> x.pos==null?"":x.pos.getValue("type")).collect(Collectors.toList()));
		Analysis selected = as.stream().max((x, y) -> x.score > y.score ? 1 : -1).get();
		if (selected.pos != null)
			((ElementAtt) selected.pos).addAttribute("type", entity);
		return selected.analysisToToken(new ElementAtt(t));
	}

	/* (non-Javadoc)
	 * @see xml.XmlManipulatorInterface#iterSentences()
	 */
	@Override
	public List<Sentence> iterSentences() {
		List<Element> sentences = nodeListToList(doc.getElementsByTagName("sentence"));
		List<Sentence> res = new ArrayList<>();
		for (Element s : sentences) {
			res.add(new Sentence(nodeListToList(s.getElementsByTagName("token")).stream().map(this::elementToToken)
					.collect(Collectors.toList())));
		}
		return res;
	}

	/* (non-Javadoc)
	 * @see xml.XmlManipulatorInterface#getEntities()
	 */
	@Override
	public List<String> getEntities() {
		return nodeListToList(doc.getElementsByTagName("token")).stream().map(t -> this.elementToToken(t).entity)
				.collect(Collectors.toList());
	}

	private Analysis elementToAnalysis(Element e) {
		Analysis a = new Analysis();
		a.prefix = new ElementAtt(e.getElementsByTagName("prefix").item(0));
		Element base = (Element) e.getElementsByTagName("base").item(0);
		if (base == null)
			return a;
		Element pos = nodeListToList(base.getChildNodes()).get(0);
		ElementAtt attpos = new ElementAtt(pos);
		attpos.addAttribute("pos", pos.getTagName());
		a.pos = attpos;
		a.base = new ElementAtt(base);
		a.suffix = new ElementAtt(e.getElementsByTagName("suffix").item(0));
		return a;
	}

	/* (non-Javadoc)
	 * @see xml.XmlManipulatorInterface#updateAnalysis(java.util.Map)
	 */
	@Override
	public void updateAnalysis(Map<Integer, String> m) {
		List<Element> tokens = nodeListToList(doc.getElementsByTagName("token"));
		for (Entry<Integer, String> id_entity : m.entrySet()) {
			NodeList as = tokens.get(id_entity.getKey()).getChildNodes();
			Element selected = select(as, id_entity.getValue());
			if (selected == null)
				continue;
			Node selectedScoreNode = selected.getAttributes().getNamedItem("score");
			double selectedScore = Double.parseDouble(selectedScoreNode.getNodeValue());
			for (int j = 0; j < as.getLength(); j++) {
				Node a = as.item(j);
				if (a.isSameNode(selected) || a.getAttributes() == null)
					continue;
				Node aScore = a.getAttributes().getNamedItem("score");
				double score = Double.parseDouble(aScore.getNodeValue());
				aScore.setNodeValue("" + (score - p * score));
				selectedScore += p * score;
			}
			selectedScoreNode.setNodeValue("" + selectedScore);
		}
	}

	public void writeToFile(OutputStream st) {
		try {
			Transformer transformer = tFactory.newTransformer();
			transformer.transform(new DOMSource(doc), new StreamResult(st));
		} catch (TransformerException e) {
			e.printStackTrace();
		}
	}

	private static Element select(NodeList as, String entity) {
		for (int j = 0; j < as.getLength(); j++) {
			if (!(as.item(j) instanceof Element))
				continue;
			Element a = (Element) as.item(j);
			if (ner.Predictor.nice_name.get(entity).equals(getEntity(a)))
				return a;
		}
		return null;
	}

	private static String getEntity(Element a) {
		Node pn = a.getElementsByTagName("properName").item(0);
		if (pn == null)
			pn = a.getElementsByTagName("MWE").item(0);
		if (pn == null)
			return "";
		return ((Element) pn).getAttribute("type");
	}

	final class ElementAtt implements Att {
		Element e;

		ElementAtt(Node e) {
			if (e != null)
				this.e = (Element) doc.importNode(e, true);
		}

		public void addAttribute(String qName, String value) {
			e.setAttribute(qName, value);
		}

		public String getQName(int i) {
			return e.getTagName();
		}

		public int getLength() {
			return e.getAttributes().getLength();
		}

		public String getValue(int i) {
			return e.getAttributes().item(i).getNodeValue();
		}

		public String getValue(String key) {
			return e == null ? null : e.getAttribute(key);
		}
	}
}
