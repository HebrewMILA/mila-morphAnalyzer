<%@ page language="java" import="java.util.*"%>
<%@ page import= "java.lang.StringBuilder"%>
<%@ page import= "java.io.*"%>
<%@ page import= "java.io.BufferedWriter"%>
<%@ page import= "java.io.FileOutputStream"%>
<%@ page import= "java.io.FileInputStream"%>
<%@ page import= "java.io.InputStream"%>
<%@ page import= "java.io.InputStreamReader"%>
<%@ page import= "java.io.OutputStreamWriter"%>
<%@ page import= "java.io.ByteArrayInputStream"%>
<%@ page import= "mila.tokenizers.*"%>
<%@ page import= "mila.mw.*"%>
<%@ page import= "mila.HMM.*"%>
<%!
String tokenizeAndAnalyze(String rawText) throws Exception {
    StringWriter sw = new StringWriter();
    try {
        InputStream in = new ByteArrayInputStream(rawText.getBytes("UTF-8"));
        new MWXMLTokenizer().tokenizeAndAnalyze(in, new PrintWriter(sw));   // tokenize and analyze
    } finally {
        //should close in
    }
    InputStream myin = new ByteArrayInputStream(sw.toString().getBytes("UTF-8"));
    StringWriter ppSW = new StringWriter();
    new PostProcessor1().process(myin,  new PrintWriter(ppSW));
    return ppSW.toString();
}

String makeTempPath() throws Exception {
    //myOutputFile.nf - roy's format before performing uniqe - removing multiple analysis occurence
    try {
        File tempFile = File.createTempFile("mydir","XMLHMMTagger", new File("/tmp"));
        if (!tempFile.delete() || !tempFile.mkdir())
            throw new IOException();
        return tempFile.getAbsolutePath();
    } catch(IOException ex)  {
        System.err.println("Cannot create temp file: " + ex.getMessage());
        return "/tmp/";
    }
}

void execAndLog(String[] command) throws Exception {
    Process process = Runtime.getRuntime().exec(command);
    try {
        BufferedReader bri = new BufferedReader(new InputStreamReader(process.getErrorStream()));
        String line;
        while ((line = bri.readLine()) != null) {
            System.out.println(line);
        }
    } finally {
        //should close in
    }
    process.waitFor();
}

String runTagger(String analyzed) throws Exception {
    String tempDirectoryPath = makeTempPath();
    System.out.println("tempDirectoryPath="+ tempDirectoryPath);
    // convert to HMM and save to temp file
    String taggerFormat = new MorphMult2TaggerFormat().myWEBMorp2Tagger(analyzed, tempDirectoryPath);
    // myOutputFile1.nf - roy's format after performing remove multiple format analysis occurence
    // this name is hard coded in the Java program
    String basedir = "/data/tagger/";
    String roydir = basedir + "royTagger/";
    String workdir =  roydir + "workdir/";
    String outdir = basedir+"taggerLearningOutputFile/";
    String taggedFile = workdir+"tagging-"+taggerFormat.substring(1);
    execAndLog(new String[]{roydir+"MTTest.pl",
             "-dir", roydir + "workdir/",
             "-rmtmp", tempDirectoryPath,
             taggerFormat, outdir +"corpus.lm ",
             outdir+"corpus.lex.prob ",
             taggedFile});
    return taggedFile;
}

String analyzedToXML(String analyzed, String taggedFilename) throws Exception {
    String line;
    StringWriter sw = new StringWriter();
    PrintWriter pw = new PrintWriter(sw);
    new HMM2Morph().process(analyzed, taggedFilename, pw);
    return ("<?xml version=\"1.0\" encoding=\"UTF-8\"?>" + sw.toString().trim().substring(55))
            .replaceAll("<corpus","<corpus pId=\"1\"   sId=\"1\"").trim();
}

String readFile(String filename) throws IOException {
    BufferedReader bufferedReader = new BufferedReader(new FileReader(filename));
    StringBuilder buf = new StringBuilder();
    for (String line; (line = bufferedReader.readLine()) != null;){
        buf.append(line).append("\n");
    }
    return buf.toString();
}

String addEntities(String xml) throws Exception {
	return xml;
    String hebnerDir =  "/home/s7395779/hebrewNER/";
    String filename = hebnerDir + "temp.xml";
    try {
        PrintWriter writer = new PrintWriter(filename, "UTF-8");
        writer.println(xml);
    } finally {
        //should close;
    }
    return xml;
    //String outfile = hebnerDir + "newtemp.xml";
    //execAndLog(new String[]{ hebnerDir+"run.sh", filename}, outfile);
    //return readFile(outfile);
}

%>
<%
request.setCharacterEncoding("UTF-8");
response.setContentType("text/xml;charset=UTF-8");
String rawText = request.getParameter("input_text");
String submitButton = request.getParameter("bsubmit");
if (rawText == null)
        return;
try {
    String analyzed = tokenizeAndAnalyze(rawText);
    String taggedFilename = runTagger(analyzed);
    String xml = analyzedToXML(analyzed, taggedFilename);
    String newxml = addEntities(xml);
    out.clearBuffer();
    out.print(xml);
} catch (Exception e) {
    e.printStackTrace();
    System.exit(-1);
}
%>