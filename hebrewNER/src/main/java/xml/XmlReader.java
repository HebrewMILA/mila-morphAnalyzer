package xml;

import java.io.*;
import java.util.*;
import static java.util.stream.Collectors.toList;

import javax.xml.parsers.*;
import org.xml.sax.*;
import org.xml.sax.helpers.*;

import tagging.Sentence;
import tagging.Token;

public class XmlReader extends DefaultHandler {
	List<Token> sentence;
	List<List<Token>> article;

	Analysis a = new Analysis();
	AttImpl token;
	boolean in_base;

	@Override
	public void startDocument() throws SAXException {
		article = new LinkedList<>();
		a.update = false;
	}

	@Override
	public void startElement(String uri, String localName, String qName, Attributes _attributes) throws SAXException {
		startElement(qName, new AttImpl(_attributes));
	}

	public void startElement(String qName, AttImpl attributes) {
		switch (qName) {
		case "paragraph":
		case "sentence":
			sentence = new ArrayList<>();
			break;
		case "token":
			token = attributes;
			a = new Analysis();
			break;
		case "analysis":
			double score = Double.parseDouble(attributes.getValue("score"));
			if (a.score < score) {
				a = new Analysis();
				a.score = score;
			}
			break;
		case "prefix":
			if (a.update)
				a.prefix = attributes;
			break;
		case "suffix":
			if (a.update)
				a.suffix = attributes;
			break;
		case "base":
			if (a.update) {
				a.base = attributes;
				in_base = true;
			}
			break;
		default:
			if (a.update && in_base) {
				a.pos = attributes;
				attributes.addAttribute("pos", qName);
			}
			break;
		}
	}

	@Override
	public void endElement(String uri, String localName, String qName) throws SAXException {
		switch (qName) {
		case "analysis":
			a.update = false;
			break;
		case "token":
			sentence.add(a.analysisToToken(token));
			break;
		case "base":
			in_base = false;
			break;
		case "sentence":
			article.add(sentence);
			sentence = null;
			break;
		}
	}

	public static void main(String[] args) throws Exception {
		String filename = "resources\\examples\\xml\\test.xml";
		for (Iterable<Token> sent : readFromFile(filename)) {
			for (Token t : sent) {
				System.out.println(t.toTsv());
			}
		}
	}

	static SAXParserFactory factory = SAXParserFactory.newInstance();
	public static Set<String> pos_list = new HashSet<>();

	public static List<List<Token>> readFromFile(String filename) {
		try {
			InputSource is = new InputSource(new InputStreamReader(new FileInputStream(filename), "UTF-8"));
			is.setEncoding("UTF-8");
			SAXParser saxParser = factory.newSAXParser();
			XmlReader x = new XmlReader();
			saxParser.parse(is, x);
			x.article.stream()
					.forEach(c -> pos_list.addAll(c.stream().map(t -> t.HMMPos).collect(toList())));
			return x.article;
		} catch (ParserConfigurationException | SAXException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	public static List<Sentence> readSentenceListFromFile(String filename) {
		return readFromFile(filename).stream().map(ts -> new Sentence(ts)).collect(toList());
	}
}

final class AttImpl implements Att {
	AttributesImpl attributes;
	AttImpl(Attributes att) {
		attributes = new AttributesImpl(att);
	}
	AttImpl() {
		attributes = new AttributesImpl();
	}
	public void addAttribute(String qName, String value) {
		attributes.addAttribute("", "", qName, "string", value);
	}
	public String getQName(int i) { return attributes.getQName(i); }

	public int getLength() { return attributes.getLength(); }

	public String getValue(int i) { return attributes.getValue(i); }

	public String getValue(String key) { return attributes.getValue(key); }
}