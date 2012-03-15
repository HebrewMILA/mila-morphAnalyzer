package mila.dataStructures;

import java.util.ArrayList;

public class OutputTokenRecord {
	String surface = "";
	ArrayList outputAnalysisList = new ArrayList();

	public ArrayList<OutputAnalysisRecord> getOutputAnalysisList() {
		return outputAnalysisList;
	}

	public String getSurface() {
		return surface;
	}

	public void setOutputAnalysisList(
			ArrayList<OutputAnalysisRecord> outputAnalysisList) {
		this.outputAnalysisList = outputAnalysisList;
	}

	public void setSurface(String surface) {
		this.surface = surface;
	}

}
