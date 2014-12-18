<%@ page language="java" import="java.util.*" %>
<%@ page import="java.io.*" %>
<%@ page import= "java.io.OutputStreamWriter"%>
<%@ page import= "java.io.BufferedWriter"%>
<%@ page import= "java.io.FileOutputStream"%>
<%@ page import= "java.io.InputStream"%>
<%@ page import= "java.io.FileInputStream"%>
<%@ page import= "java.io.InputStreamReader"%>
<%@ page import= "java.net.URLDecoder"%>
<%@ page import= "mila.tokenizers.*"%>
<%@ page import="mila.mw.*" %>
<%@ page import= "mila.HMM.*"%>
<%@ page import="java.util.Random" %>
<%
request.setCharacterEncoding("UTF-8") ;
response.setContentType("text/xml;charset=utf-8");
String textFromTextArea = request.getParameter( "input_text" );
String submitButton = request.getParameter("bsubmit");
String result ="";
if(textFromTextArea!=null){

try
{	
		MWXMLTokenizer mwxmlTokenizer = new MWXMLTokenizer();
		java.io.InputStream in = null;
		try {
			in = new java.io.ByteArrayInputStream(textFromTextArea
					.getBytes("UTF-8"));
			StringWriter sw = new StringWriter();
                        PrintWriter pw = new PrintWriter(sw);
			mwxmlTokenizer.tokenizeAndAnalyze(in,pw);
		        result = sw.toString();
			InputStream myin = new java.io.ByteArrayInputStream(result.getBytes("UTF-8"));
			StringWriter ppSW = new StringWriter();
                        PrintWriter ppPW = new PrintWriter(ppSW);
			PostProcessor1 p = new PostProcessor1();
			p.process(myin,  ppPW);
			String postProcessorResult = ppSW.toString();
                        postProcessorResult=postProcessorResult.trim();


			 MorphMult2TaggerFormat mm = new MorphMult2TaggerFormat();
                         String tempDirectoryPath="";
                        //myOutputFile.nf - roy's format before performing uniqe - removing multiple analysis occurence

                        Random generator = new Random();
                        int randomValue = generator.nextInt();
                        String randomValStr = String.valueOf(randomValue);
                        File tempFile=null;
                        File yedaTempDirectory= new File("/tmp");
                        try{
                                tempFile = File.createTempFile("mydir","XMLHMMTagger",yedaTempDirectory);
                                if (!tempFile.delete())
                                         throw new IOException();
                                if (!tempFile.mkdir())
                                         throw new IOException();

                                tempDirectoryPath=tempFile.getAbsolutePath();
                                System.out.println("tempDirectoryPath="+ tempDirectoryPath);
                        }catch(IOException ex){

                                 System.err.println("Cannot create temp file: " + ex.getMessage());
                        }

                        String taggerFormat = mm.myWEBMorp2Tagger(postProcessorResult, tempDirectoryPath);



                        String taggerInputFile =  tempDirectoryPath + taggerFormat;

			 try {

                        //myOutputFile1.nf - roy's format after performing remove multiple format analysis occurence
                        //this name is hard coded in the Java program

                        String command =  "/usr/local/apache-tomcat-5.5.12/webapps/XMLMWE/royTagger/MTTest.pl -dir /usr/local/apache-tomcat-5.5.12/webapps/XMLMWE/royTagger/workdir  -rmtmp  "+ taggerInputFile  + " /usr/local/apache-tomcat-5.5.12/webapps/XMLMWE/taggerLearningOutputFile/corpus.lm  /usr/local/apache-tomcat-5.5.12/webapps/XMLMWE/taggerLearningOutputFile/corpus.lex.prob";

        //String command = "/usr/local/apache-tomcat-5.5.12/webapps/XMLMWE/royTagger/MTTest.pl -dir /usr/local/apache-tomcat-5.5.12/webapps/XMLHmmTagger/royTagger/workdir  " + taggerInputFile;
                        System.out.println("%%%%%%%%%%%%%%%%%%%%%%%%%%%%"+ command);
                        Process process = Runtime.getRuntime().exec(command);
                        process.waitFor();
                } catch (IOException e3) {
                        e3.printStackTrace();
                }catch (Exception e3) {
                        e3.printStackTrace();
                }


                        HMM2Morph h = new HMM2Morph();

                        String fileName= taggerFormat.substring(1);                        String taggedFile = "/usr/local/apache-tomcat-5.5.12/webapps/XMLMWE/royTagger/workdir"+ "/tagging-"+ fileName;

                        sw = new StringWriter();
                        pw = new PrintWriter(sw);
                        h.process(postProcessorResult,taggedFile,pw);
                        postProcessorResult = sw.toString();
                        postProcessorResult=postProcessorResult.trim();
                        out.clearBuffer();
                        out.print(postProcessorResult);


			  File n = new File(taggedFile);
//                      n.delete();                        //n= new File("/usr/local/apache-tomcat-5.5.12/webapps/XMLMWE/royTagger/workdir/"+fileName + ".analyses.morph.map.sentmap");
                        //n.delete();                        //n= new File( "/usr/local/apache-tomcat-5.5.12/webapps/XMLMWE/royTagger/workdir/"+fileName + ".corpus.morph.lm");
                        //n.delete();                        //n= new File( "/usr/local/apache-tomcat-5.5.12/webapps/XMLMWE/royTagger/workdir/"+fileName + ".analyses.morph.map");
                        //n.delete();                        //n= new File( "/usr/local/apache-tomcat-5.5.12/webapps/XMLMWE/royTagger/workdir/"+fileName + ".analyses.morph.map.sentrevmap");
                        //n.delete();                        //n= new File( "/usr/local/apache-tomcat-5.5.12/webapps/XMLMWE/royTagger/workdir/"+fileName + ".analyses.morph.map.tagging");
                        //n.delete();                        // n= new File( "/usr/local/apache-tomcat-5.5.12/webapps/XMLMWE/royTagger/workdir/"+fileName + ".analyses.morph.revmap");                        //n.delete();


   //delete the temporay directory under /tmp
                        //if too much files under the /tmp there is a point when a new tmp file can't be created
                        //System.out.println("directory to delete"+tempDirectoryPath);
                         n = new File(tempDirectoryPath);
                         File[] files = n.listFiles();
                         for(int i=0; i<files.length; i++) {
                         //files[i].delete();
                        }
                        //n.delete();

	

		} catch (Exception e) {
			e.printStackTrace();
			System.out
					.println("An error occured while moving from String to inputString");
		}
       }
       catch (Exception e) {
           System.out.println("exception happened - here's what I know: ");
           e.printStackTrace();
           System.exit(-1);
       }

}
%>
