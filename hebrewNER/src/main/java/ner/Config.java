package ner;


public final class Config {
	private static final String SERVER_BASE = true?"":"../webapps/MWEplay/";
	private static final String RESOURCES_DIR = SERVER_BASE + "resources/";

	// "Examples" is *not* part of the API
	// public static final String EXAMPLES_DIR = RESOURCES_DIR + "examples/";
	public static final String LISTS_DIR = RESOURCES_DIR + "lists/";
	public static final String TAGGED_DIR = RESOURCES_DIR + "input_tagged/";
	public static final String TRAINSET_DIR = RESOURCES_DIR + "trainset/full/";

	public static final String POS_LIST = LISTS_DIR + "pos_list.txt";

	public static class Models {
		private static final String MODELS_DIR = RESOURCES_DIR + "models/";

		private static String getPathForModel(String filename) {
			return MODELS_DIR + filename + ".model";
		}

		public static final String hmm = getPathForModel("hmm");
		public static final String maxent = getPathForModel("maxent");
		public static final String baseline = getPathForModel("baseline");
	}

	public static final String MIDDLE = "B_";
	public static final String START = "I_";
	private Config(){}
	


	public static void debug(String string) {
		System.out.println("Elazar: " + string);
	}
	
	public static void log(String string) {
		System.out.println("LOG (hebrewNER): " + string);
	}
}