/*
 * Created on 23/11/2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package lexicon.contents;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

/**
 * @author daliabo
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class PropertiesUtils {
	static String inputFile = "";
	static String outputFile ="";
	static String lexiconDatabaseOutput = "";
	static String dir = "C:\\Documents and Settings\\daliabo\\My Documents\\lexicon\\diffTests\\";
	//String dir ="";
	static String mysqlUser="";
	static String mysqlPassword="";
	
	public static void handlePropertiesFile() {
		Properties properties = new Properties();
		try {
			System.out.println("analyzer properties:");
			System.out.println("--------------------");
			properties.load(new FileInputStream(dir + "./analyzer.properties"));
			inputFile = properties.getProperty("inputFile");
			System.out.println("inputFile=" + inputFile);
			outputFile = properties.getProperty("outputFile");
			System.out.println("outputFile=" + outputFile);
			dir = properties.getProperty("dir");
			System.out.println("dir=" + dir);
			lexiconDatabaseOutput = properties.getProperty("lexiconDatabaseOutput");
			System.out.println("lexiconDatabaseOutput=" + lexiconDatabaseOutput);
			mysqlUser = properties.getProperty("mysqlUser");
			System.out.println("mysqlUser=" + mysqlUser);
			mysqlPassword = properties.getProperty("mysqlPassword");
			System.out.println("mysqlPassword=" + mysqlPassword);
			
			System.out.println("--------------------");
			System.out.println();
			System.out.println("analyzer output:");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
	}
	/**
	 * @return Returns the inputFile.
	 */
	public String getInputFile() {
		return inputFile;
	}
	/**
	 * @param inputFile The inputFile to set.
	 */
	public void setInputFile(String inputFile) {
		this.inputFile = inputFile;
	}
	/**
	 * @return Returns the outputFile.
	 */
	public static String getOutputFile() {
		return outputFile;
	}
	/**
	 * @param outputFile The outputFile to set.
	 */
	public void setOutputFile(String outputFile) {
		this.outputFile = outputFile;
	}
	
	/**
	 * @return Returns the directory.
	 */
	public static String getDir() {
		return dir;
	}
	/**
	 * @param directory The directory to set.
	 */
	public void setDir(String directory) {
		this.dir = directory;
	}
	/**
	 * @return Returns the lexiconDatabaseOutput.
	 */
	public static String getLexiconDatabaseOutput() {
		return lexiconDatabaseOutput;
	}
	/**
	 * @param lexiconDatabaseOutput The lexiconDatabaseOutput to set.
	 */
	public void setLexiconDatabaseOutput(String lexiconDatabaseOutput) {
		this.lexiconDatabaseOutput = lexiconDatabaseOutput;
	}
}
