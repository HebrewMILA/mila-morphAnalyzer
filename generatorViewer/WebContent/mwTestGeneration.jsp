<%@ 
page language="java" 
import="gen.TestMWGeneration"
import="lexicon.generate.*"
%> 

<html>
<HEAD>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<TITLE>
       Multi Word Expressions Generator Viewer - database generator 
</TITLE>
</HEAD>
<body>
<%
try{
String lexiconId = request.getParameter("lexiconId");
response.setContentType("text/html; charset=UTF-8"); 
TestMWGeneration g = new TestMWGeneration();
g.deleteMWInflections();
lexicon.generate.Generation gen = new lexicon.generate.Generation();
gen.testGenerate(lexiconId);

String output = g.showMW2Inflections(lexiconId);
if(output.length()>257){
%>
<table align="center" cellpadding="2" cellspacing="2" border="1" width="80%" bgcolor="#dddddd">
<%=output%>

<%}
 output="";
output = g.showMW3Inflections(lexiconId);
if(output.length()>257){
%>
<table align="center" cellpadding="2" cellspacing="2" border="1" width="80%" bgcolor="#dddddd">
<%=output%>


<%
}
output="";
output = g.showMW4Inflections(lexiconId);
if(output.length()>257){
%>
<table align="center" cellpadding="2" cellspacing="2" border="1" width="80%" bgcolor="#dddddd">
<%=output%>


<%
}
}catch(Exception e)
{
out.println(e.getMessage());
}
%>
</table>

</body></html>


