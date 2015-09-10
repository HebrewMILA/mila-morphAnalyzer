package xml;

import java.io.OutputStream;
import java.util.List;
import java.util.Map;

import tagging.Sentence;

public interface XmlManipulatorInterface {

	List<Sentence> iterSentences();

	List<String> getEntities();

	void updateAnalysis(Map<Integer, String> m);

	void writeToFile(OutputStream stream);

}