<%@ page language = "java"
	import = "java.lang.StringBuilder"
	import = "java.io.StringWriter"
	import = "java.io.PrintWriter"
	import = "java.io.ByteArrayInputStream"
	import = "java.io.IOException"
	import = "java.io.File"
	import = "java.io.BufferedReader"
	import = "java.io.InputStreamReader"
	import = "java.io.InputStream"
	import = "java.nio.file.Files"
	import = "java.nio.file.Paths"
	import = "java.nio.file.StandardCopyOption"
%>
<%@include file="tempPath.jsp"%>
<%!
//tl;dr : 
String prepareXML(String rawText, final String tempdir) throws Exception {
		long startTime = System.nanoTime();
	final String xmlAnalyzed = tokenizeAndAnalyze(rawText);
		System.out.println("@time tokenizeAndAnalyze: "+ ((System.nanoTime() - startTime)/1000000) );
	final String xmlTaggedFilename = runTagger(xmlAnalyzed, tempdir);
		System.out.println("@time runTagger: "+ ((System.nanoTime() - startTime)/1000000) );
	final String EntityTaggedFilename = addEntities(xmlTaggedFilename, tempdir);
		System.out.println("@time addEntities: "+ ((System.nanoTime() - startTime)/1000000) );
	return EntityTaggedFilename;
}

String tokenizeAndAnalyze(String rawText) throws Exception {
    StringWriter sw = new StringWriter();
	try (InputStream ins = new ByteArrayInputStream(rawText.getBytes("UTF-8"))) {
		new mila.mw.MWXMLTokenizer().tokenizeAndAnalyze(ins, new PrintWriter(sw));
	}
    StringWriter ppSW = new StringWriter();
	try (InputStream ins = new ByteArrayInputStream(sw.toString().getBytes("UTF-8"))) {
		new mila.mw.PostProcessor1().process(ins,  new PrintWriter(ppSW));
	}
    return ppSW.toString();
}

String runTagger(String xmlAnalyzed, String _tempDirectoryPath) throws Exception {
    final String taggerFormat = new mila.HMM.MorphMult2TaggerFormat()
                            .myWEBMorp2Tagger(xmlAnalyzed, _tempDirectoryPath);
    final String roydir = "/data/tagger/royTagger/";
	final String probabilityDir = "/data/tagger/taggerLearningOutputFile/";
    final String taggedFilename = _tempDirectoryPath+"/tagging-" + taggerFormat.substring(1);
    execAndLog(new String[]{roydir+"MTTest.pl",
             "-dir", roydir + "workdir/",
             "-rmtmp", _tempDirectoryPath + taggerFormat,
			 probabilityDir + "corpus.lm",
             probabilityDir + "corpus.lex.prob",
             taggedFilename}); 
    
	final String xmlTaggedFilename = _tempDirectoryPath + "/tagged.xml";
    PrintWriter pw = new PrintWriter(xmlTaggedFilename);
    new mila.HMM.HMM2Morph().process(xmlAnalyzed, taggedFilename, pw);
    return xmlTaggedFilename;    
}

String addEntities(String xmlTaggedFilename, String _tempDirectoryPath) throws Exception {
	String entitiesFilename = _tempDirectoryPath + "/tagged_with_entities.xml";
	if (!runEntities) {
		Files.copy(Paths.get(xmlTaggedFilename),  Paths.get(entitiesFilename), StandardCopyOption.REPLACE_EXISTING);
		return entitiesFilename;
	}
	try {		
		long startTime = System.nanoTime();
		execAndLog(new String[]{ "python3", "src/python",
								xmlTaggedFilename, entitiesFilename},
								"/home/s7395779/hebrewNER/");
		System.out.println("@time execAndLog: "+ ((System.nanoTime() - startTime)/1000000) );
		return entitiesFilename;
	} catch (IOException ex) {
		ex.printStackTrace();
	}
	return xmlTaggedFilename;
}

static void execAndLog(String[] command) throws Exception {
    execAndLog(command, System.getProperty("user.dir"));
}

static void execAndLog(String[] command, String dir) throws Exception {
    Process process = Runtime.getRuntime().exec(command, new String[]{}, new File(dir));
    try (BufferedReader br = new BufferedReader(new InputStreamReader(process.getErrorStream()))) {
		long startTime = System.nanoTime();
		for (String line; (line = br.readLine()) != null; )
			System.out.println(line);
		System.out.println("@time printing: "+ ((System.nanoTime() - startTime)/1000000) );
	}
    process.waitFor();
}

%><%
request.setCharacterEncoding("UTF-8");
final String raw_text = request.getParameter("input_text");
if (raw_text == null || "".equals(raw_text.trim())) {
	response.sendRedirect("error.xml");
	return;
}
final Path localTempdir = makeTempPath();
response.sendRedirect(String.format("view.xml?id=%s", extractID(localTempdir)));
final String entityTaggedFilename = prepareXML(raw_text.trim(), localTempdir.toString());
%>