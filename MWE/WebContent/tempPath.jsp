<%@ page language = "java"
	import = "java.io.IOException"
	import = "java.io.File"
	import = "java.nio.file.Files"
	import = "java.nio.file.Paths"
	import = "java.nio.file.Path"
	import = "java.util.regex.Pattern"
%>
<%!
private static final Path mainTempDir = Paths.get(System.getProperty("java.io.tmpdir")+"/online_tagger");
private static final boolean runEntities = false;

static Path makeTempPath() throws IOException {
	Path res = Files.createTempDirectory(mainTempDir, null);
	res.toFile().deleteOnExit();
	return res;
}

static Path getTempPath(String id) {
	if (!Pattern.matches("^\\d+$", id))
		id="0";
	return mainTempDir.resolve(""+id);
}

String extractID(Path tempdir) {
	String id = mainTempDir.relativize(tempdir).toString();
	if (!Pattern.matches("^\\d+$", id))
		id="38808";
	return id;
}

%>
