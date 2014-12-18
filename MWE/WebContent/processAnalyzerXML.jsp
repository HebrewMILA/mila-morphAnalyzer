<%@ page language="java"%>
<%@ page import="java.io.*"%>
<%@ page import="java.io.OutputStreamWriter"%>
<%@ page import="java.io.BufferedWriter"%>
<%@ page import="java.io.FileOutputStream"%>
<%@ page import="java.io.InputStream"%>
<%@ page import="java.io.FileInputStream"%>
<%@ page import="java.io.InputStreamReader"%>
<%@ page import="java.net.URLDecoder"%>
<%@ page import="mila.tokenizers.*"%>
<%@ page import="mila.mw.*"%>
<%@ page import="mila.HMM.*"%>
<%@ page import="java.util.Random"%>
<%
	request.setCharacterEncoding("UTF-8");
	response.setContentType("text/xml;charset=utf-8");
	String textFromTextArea = request.getParameter("input_text");
	String submitButton = request.getParameter("bsubmit");
	String result = "";
	String fileName;
	String taggedFile;
	String line;
	if (textFromTextArea != null) {
		try {
			mila.mw.MWXMLTokenizer mwxmlTokenizer = new MWXMLTokenizer();
			java.io.InputStream in = null;
			try {
				in = new java.io.ByteArrayInputStream(
						textFromTextArea.getBytes("UTF-8")); // get input
				StringWriter sw = new StringWriter();
				PrintWriter pw = new PrintWriter(sw);
				mwxmlTokenizer.tokenizeAndAnalyze(in, pw); // tokenize and analyze
				result = sw.toString();
				InputStream myin = new java.io.ByteArrayInputStream(
						result.getBytes("UTF-8"));
				StringWriter ppSW = new StringWriter();
				PrintWriter ppPW = new PrintWriter(ppSW);
				PostProcessor1 p = new PostProcessor1();
				p.process(myin, ppPW);
				String postProcessorResult = ppSW.toString();

				MorphMult2TaggerFormat mm = new MorphMult2TaggerFormat(); // class for converting to HMM format
				String tempDirectoryPath = "";
				//myOutputFile.nf - roy's format before performing uniqe - removing multiple analysis occurence

				Random generator = new Random(); // create random number for the temp file name
				int randomValue = generator.nextInt();
				String randomValStr = String.valueOf(randomValue);
				File tempFile = null;
				File yedaTempDirectory = new File("/tmp");
				try {
					tempFile = File.createTempFile("mydir",
							"XMLHMMTagger", yedaTempDirectory);
					if (!tempFile.delete())
						throw new IOException();
					if (!tempFile.mkdir())
						throw new IOException();

					tempDirectoryPath = tempFile.getAbsolutePath();
					System.out.println("tempDirectoryPath="
							+ tempDirectoryPath);
				} catch (IOException ex) {

					System.err.println("Cannot create temp file: "
							+ ex.getMessage());
				}

				String taggerFormat = mm.myWEBMorp2Tagger(
						postProcessorResult, tempDirectoryPath); // convert to HMM and save to temp file

				String taggerInputFile = tempDirectoryPath
						+ taggerFormat;
				fileName = taggerFormat.substring(1);
				taggedFile = "/data/tagger/royTagger/workdir"
						+ "/tagging-" + fileName;

				try { // running the pearl code (the tagger)
					//myOutputFile1.nf - roy's format after performing remove multiple format analysis occurence
					//this name is hard coded in the Java program
					String command = "/data/tagger/royTagger/MTTest.pl -dir /data/tagger/royTagger/workdir/  -rmtmp  "
							+ taggerInputFile
							+ " /data/tagger//taggerLearningOutputFile/corpus.lm  /data/tagger/taggerLearningOutputFile/corpus.lex.prob "
							+ taggedFile;
					//String command = "/usr/local/apache-tomcat-5.5.12/webapps/XMLMWE/royTagger/MTTest.pl -dir /usr/local/apache-tomcat-5.5.12/webapps/XMLHmmTagger/royTagger/workdir  " + taggerInputFile;
					System.out.println("%%%%%%%%%%%%%%%%%%%%%%%%%%%%\n"
							+ command);
					Process process = Runtime.getRuntime()
							.exec(command);
					BufferedReader bri = new BufferedReader(
							new InputStreamReader(
									process.getErrorStream()));
					System.out.println("%% S");
					while ((line = bri.readLine()) != null) {
						System.out.println(line);
					}
					bri.close();
					System.out.println("%% F");
					process.waitFor();
				} catch (IOException e3) {
					// TODO Auto-generated catch block
					e3.printStackTrace();
				} catch (Exception e3) {
					// TODO Auto-generated catch block
					e3.printStackTrace();
				}

				HMM2Morph h = new HMM2Morph(); // class for converting back from HMM format
				fileName = taggerFormat.substring(1);
				taggedFile = "/data/tagger/royTagger/workdir"
						+ "/tagging-" + fileName;

				sw = new StringWriter();
				pw = new PrintWriter(sw);
				h.process(postProcessorResult, taggedFile, pw); // convert back
				postProcessorResult = sw.toString();
				postProcessorResult = postProcessorResult.trim();

				//File n = new File(taggedFile);
				//                      n.delete();  
				//n= new File("/usr/local/apache-tomcat-5.5.12/webapps/XMLMWE/royTagger/workdir/"+fileName + ".analyses.morph.map.sentmap");                        
				//n.delete();                        
				//n= new File( "/usr/local/apache-tomcat-5.5.12/webapps/XMLMWE/royTagger/workdir/"+fileName + ".corpus.morph.lm");                        //n.delete();                        
				//n= new File( "/usr/local/apache-tomcat-5.5.12/webapps/XMLMWE/royTagger/workdir/"+fileName + ".analyses.morph.map");                        //n.delete();                        
				//n= new File( "/usr/local/apache-tomcat-5.5.12/webapps/XMLMWE/royTagger/workdir/"+fileName + ".analyses.morph.map.sentrevmap");                        
				//n.delete();                        
				//n= new File( "/usr/local/apache-tomcat-5.5.12/webapps/XMLMWE/royTagger/workdir/"+fileName + ".analyses.morph.map.tagging");                        
				//n.delete();                        
				// n= new File( "/usr/local/apache-tomcat-5.5.12/webapps/XMLMWE/royTagger/workdir/"+fileName + ".analyses.morph.revmap");                        
				//n.delete();

				//delete the temporay directory under /tmp
				//if too much files under the /tmp there is a point when a new tmp file can't be created
				//System.out.println("directory to delete"+tempDirectoryPath);
				// n = new File(tempDirectoryPath);
				// File[] files = n.listFiles();
				// for(int i=0; i<files.length; i++) {
				//files[i].delete();                        }                        
				//n.delete();

				postProcessorResult = postProcessorResult.trim();
				postProcessorResult = "<?xml-stylesheet type=\"text/xsl\" href=\"input1.xsl\"?>"
						+ postProcessorResult.substring(55);
				postProcessorResult = postProcessorResult.replaceAll(
						"<corpus", "<corpus pId=\"1\"   sId=\"1\"");
				postProcessorResult = postProcessorResult.trim();
				session.setAttribute("ppoutputAnalysis",
						postProcessorResult);
				out.clearBuffer();
				out.print(postProcessorResult);

			} catch (Exception e) {
				e.printStackTrace();
				System.out
						.println("An error occured while moving from String to inputString");
			}
		} catch (Exception e) {
			System.out
					.println("exception happened - here's what I know: ");
			e.printStackTrace();
			System.exit(-1);
		}

	}
%>

