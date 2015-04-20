<%@ page language = "java"
	import = "java.nio.file.Files"
	import = "java.nio.file.Paths"
%>
<%@include file="tempPath.jsp"%>
<%@ page pageEncoding="UTF-8" %>
<%
final String id = request.getParameter("id");
final boolean isRaw = null != request.getParameter("raw");
final boolean isDownload = null != request.getParameter("download");
if (id == null) {
	%><jsp:forward page="." /><%
	return;
}
try {	
	request.setCharacterEncoding("UTF-8");
	String rawXML = new String(Files.readAllBytes(getTempPath(id).resolve("tagged_with_entities.xml")));
	out.clearBuffer();
	if (isDownload) { 
		response.setContentType("application/octet-stream");
		response.setHeader("Content-Disposition", "attachment;filename=analysis.xml");
	} else {
		response.setContentType("text/xml;charset=UTF-8");
	}
	rawXML = rawXML.substring(rawXML.indexOf("<corpus"));
	%><?xml version="1.0" encoding="UTF-8"?>
<%
	if (!isRaw) {
%><?xml-stylesheet type="text/xsl" href="view.xsl"?>
<%
	}
	%><%=rawXML%><%
} catch (Exception ex) {
	ex.printStackTrace();
	response.sendRedirect("error.xml");
	return;
}%>