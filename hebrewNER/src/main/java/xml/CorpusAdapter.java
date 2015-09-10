package xml;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.function.Consumer;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import static java.util.stream.Collectors.toList;

import java.io.InputStream;
import java.io.OutputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import mila.generated.*;
import utils.IO;
import static ner.Config.debug;

public class CorpusAdapter implements XmlManipulatorInterface {
	private static final double p = 0.2;
	mila.generated.Corpus corpus;
	private final List<String> entities = new ArrayList<>();
	private final List<TokenType> milaTokens = new ArrayList<>();
	private final static JAXBContext context = createContext();
	public CorpusAdapter(Corpus corpus) {
		this.corpus = corpus;
	}
	public static CorpusAdapter read(String filename) {
		debug(filename);
		return new CorpusAdapter((Corpus)IO.inputStream(filename, CorpusAdapter::unmarshal));
	}
	@Override
	public List<tagging.Sentence> iterSentences() {
		List<tagging.Sentence> ts = new ArrayList<>();
		collectTokenList(tokenTypeList -> ts.add(tokenTypeListToSentence(tokenTypeList)));
		return ts;
	}

	private void collectTokenList(Consumer<List<TokenType>> f) {
		final List<ParagraphType> paragraphTypeList = ((List<ArticleType>) corpus.getArticle()).get(0).getParagraph();
		for (ParagraphType paragraph : paragraphTypeList) {
			final List<SentenceType> sentenceTypeList = paragraph.getSentence();
			for (SentenceType aSentenceTypeList : sentenceTypeList) {
				final List<TokenType> tokenTypeList = aSentenceTypeList.getToken();
				f.accept(tokenTypeList);
			}
		}
	}

	private tagging.Sentence tokenTypeListToSentence(List<TokenType> tokenTypeList) {
		return new tagging.Sentence(tokenTypeList.stream().map(this::jaxbTokenToToken).collect(toList()));
	}

	private tagging.Token jaxbTokenToToken(TokenType token) {
		this.milaTokens.add(token);
		List<AnalysisType> analysisList = token.getAnalysis();
		AnalysisType chosen = analysisList.stream().max(CorpusAdapter::compareScores).get();
		List<PrefixType> pref = chosen.getPrefix();
		BaseType base = chosen.getBase();
		tagging.Token tagToken; 
		if (base == null)
			tagToken = tagging.Token.EMPTY;
		else {
			Proxy child = new Proxy(getBaseChild(base));
			tagToken = new tagging.Token(Integer.parseInt(token.getId()), token.getSurface(),
				base.getLexiconItem(), 
				String.join("", pref.stream().map(p -> p.getFunction()).collect(toList())),
				child.property("definiteness", ""),
				child.property("status", ""),
				toDash(getPos(child)),
				getEntity(chosen),
				"No one will use HMMPos anyway so why bother");
		}
		this.entities.add(tagToken.entity);
		return tagToken;
	}

	public static String getPos(Proxy child) {
		String clsName = child.ClassName();
		if (clsName.equals("MWEType"))
			return ((MWEType) child).getPos();
		return clsName.substring(0, clsName.length() - "Type".length());
	}

	private static int compareScores(AnalysisType a1, AnalysisType a2) {
		return a1.getScore() - a2.getScore() > 0 ? 1 : -1;
	}

	private static Object getBaseChild(BaseType base) {
		Class<? extends BaseType> cls = base.getClass();
		for (Method m : cls.getDeclaredMethods()) {
			if (!m.getName().startsWith("get"))
				continue;
			try {
				Object res = m.invoke(base);
				if (res != null)
					return res;
			} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
				e.printStackTrace();
			}
		}
		return null;
	}

	@Override
	public List<String> getEntities() {
		return entities;
	}

	@Override
	public void updateAnalysis(Map<Integer, String> m) {
		for (Entry<Integer, String> id_entity : m.entrySet()) {
			List<AnalysisType> as = milaTokens.get(id_entity.getKey() - 1).getAnalysis();
			String niceName = ner.Predictor.nice_name.get(id_entity.getValue());
			as.stream().filter(a -> niceName.equals(getEntity(a))).findFirst().ifPresent(selected -> {
				for (int j = 0; j < as.size(); j++) {
					AnalysisType a = as.get(j);
					if (a == selected)
						continue;
					double score = a.getScore();
					a.setScore(score - p * score);
					selected.setScore(selected.getScore() + p * score);
				}
			});
		}
	}

	private static String getEntity(AnalysisType a) {
		BaseType base = a.getBase();
		if (base != null) {
			{
				ProperNameType pN = base.getProperName();
				if (pN != null)
					return pN.getType();
			}
			{
				MWEType mwe = base.getMWE();
				if (mwe != null)
					return mwe.getType();
			}
		}
		return "-";
	}

	private static String toDash(String s) {
		return s.isEmpty() ? "-" : s;
	}
	@Override
	public void writeToFile(OutputStream stream) {
		// TODO Auto-generated method stub
		
	}
	

	private  static JAXBContext createContext() {
		try {
			return JAXBContext.newInstance("mila.generated");
		} catch (JAXBException e) {
			throw new RuntimeException(e);
		}
	}
	
	private static Object unmarshal(InputStream in) {
		try {
			Unmarshaller jc = context.createUnmarshaller();
			return jc.unmarshal(in);
		} catch (JAXBException e) {
			throw new RuntimeException(e);
		}
	}
}

class Proxy {
	final private Object obj;
	final private Class<?> cls;

	Proxy(Object obj) {
		this.obj = obj;
		this.cls = obj.getClass();
	}

	Object call(String name) {
		try {
			return cls.getDeclaredMethod(name).invoke(obj);
		} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException | SecurityException e) {
			throw new RuntimeException(e);
		} catch (NoSuchMethodException e) {
			return null;
		}
	}

	String property(String name) {
		return (String) call("get" + Character.toUpperCase(name.charAt(0)) + name.substring(1));
	}

	String property(String name, String def) {
		String res = property(name);
		if (res == null)
			return def;
		return res;
	}

	String ClassName() {
		return cls.getSimpleName();
	}
}