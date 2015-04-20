<%@ page pageEncoding="UTF-8"%>
<%@ page import="lexicon.ShowNumeralInflections"%>
<html>
<HEAD>
	<meta http-equiv="Content-Type" content="text/html; charset="UTF-8">
	<TITLE>פרטי הטיות</TITLE>
</HEAD>
<body>
<div id="Section">
<%
response.setContentType("text/html;charset=UTF-8");
String lexiconId = request.getParameter("lexiconId");
try {
	ShowNumeralInflections s = new ShowNumeralInflections(lexiconId);

	String construct =  s.getConstruct();
	String base =  s.getBase();
	String PGNSingular =  s.getPGNSingular();
	String PGNPlural =  s.getPGNPlural();

	%><dl>
	<% if (!base.equals("")){ %>
		<dt>יחיד/רבים זכר/נקבה</dt>
		<dd><%= base %></dd>
	<%} if (!construct.equals("")){ %>
		<dt>נסמך</dt>
		<dd><%= construct %></dd>
	<%} if (!PGNSingular.equals("")){ %>
		<dt>קניין יחיד</dt>
		<dd><%= PGNSingular %></dd>
	<%} if (!PGNPlural.equals("")){ %>
		<dt>קניין רבים</dt>
		<dd><%= PGNPlural %></dd>
	<%}
	%></dl>
<%
}catch(Exception e) {
	out.println(e.getMessage());
}
%>
</div>
</body></html>


