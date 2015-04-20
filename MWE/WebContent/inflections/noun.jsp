<%@ page pageEncoding="UTF-8"%>
<%@ page import="java.sql.*"%>
<%@ page import="lexicon.ShowNounInflections"%>
<html>
<HEAD>
	<!--meta http-equiv="Content-Type" content="text/html; charset="UTF-8"-->
	<TITLE>פרטי הטיות</TITLE>
</HEAD>
<body>
<div id="Section">
<%
//request.setCharacterEncoding("UTF-8") ;
response.setContentType("text/html;charset=UTF-8");
try{
	String lexiconId = request.getParameter("lexiconId");
	ShowNounInflections s = new ShowNounInflections(lexiconId);
	String dual =  s.getDual();
	String MasculineSingularConstruct =  s.getMasculineSingularConstruct();
	String MasculinePluralConstruct =  s.getMasculinePluralConstruct();
	String FeminineSingularConstruct =  s.getFeminineSingularConstruct();
	String FemininePluralConstruct =  s.getFemininePluralConstruct();
	String PGNMasculineSingular =  s.getPGNMasculineSingular();
	String PGNFeminineSingular =  s.getPGNFeminineSingular();
	String PGNMasculinePlural =  s.getPGNMasculinePlural();
	String PGNFemininePlural =  s.getPGNFemininePlural();
	%>
	<dl>
	<% if (!MasculineSingularConstruct.equals("")){ %>
		<dt>זכר -  יחיד -  נפרד/נסמך </dt>
		<dd><%= MasculineSingularConstruct %></dd>
	<%  } if (!MasculinePluralConstruct.equals("")){ %>
		<dt>זכר -  רבים - נפרד/נסמך </dt>
		<dd><%= MasculinePluralConstruct %></dd>
	<%} if (!FeminineSingularConstruct.equals("")){ %>
		<dt>נקבה -  יחיד - נפרד/נסמך</dt>
		<dd><%= FeminineSingularConstruct %></dd>
	<%} if (!FemininePluralConstruct.equals("")){ %>
		<dt>נקבה -  רבים -  נפרד/נסמך</dt>
		<dd><%= FemininePluralConstruct %></dd>
	<%} if (!dual.equals("")){ %>
		<dt>זוגי  -  נפרד/נסמך</dt>
		<dd><%=dual  %></dd>
	<%} if (!PGNMasculineSingular.equals("")){ %>
		<dt>קניין יחיד זכר</dt>
		<dd><%= PGNMasculineSingular %></dd>
	<% } if (!PGNFeminineSingular.equals("")){%>
		<dt>קניין יחיד נקבה</dt>
		<dd><%= PGNFeminineSingular %></dd>
	<% } if (!PGNMasculinePlural.equals("")){%>
		<dt>קניין רבים זכר</dt>
		<dd><%= PGNMasculinePlural %></dd>
	<% } if (!PGNFemininePlural.equals("")){%>
		<dt>קניין רבים נקבה</dt>
		<dd><%= PGNFemininePlural %></dd>
	<% } %>
	</dl>
	<%
}catch(Exception e)
{
e.printStackTrace();
}
%>
</div>
</body></html>


