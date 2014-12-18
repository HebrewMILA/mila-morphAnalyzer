<%@ page import="java.sql.*"
import="java.net.URLDecoder"
import="lexicon.showMultiWordInflections"
 %>
<%@ page 
import="java.net.URLEncoder"
 %>

<%@page pageEncoding="UTF-8"%>

<%
Connection connection = null;
Statement statement = null;
ResultSet rs = null;
%>

<html>
<HEAD>
<!--meta http-equiv="Content-Type" content="text/html; charset="UTF-8"-->
<TITLE>
       Inflections Details 
</TITLE>
<style>

body {
font-family: Verdana, Geneva, Arial, Helvetica, sans-serif; font-size: 5px; background-color: #FFF8DC; text-align: justify; color: black;
}
h3 { color: #336699; font-size: 23px; font-weight: bold; font-variant: normal; margin: 0px; padding: 5px 0 10px 0; }

#Section {
        position: relative;
        top: -3%;
        margin: 0px;
        padding: 0% 2% 2% 2%;
        width: 100%;
        font-size:20px;
}

#Section td {
	font-family: Verdana, Geneva, Arial, Helvetica, sans-serif;
        font-size:20px;
}


#SectionHeader {
        background: #CCCCCC;
        position: relative;
        overflow: hidden;
        margin: 2px  0 2px 0;
        padding: 0px;
        height: 10%;
        width: 100%;
        border-left: 1px solid #CCCCCC;
        border-right: 1px solid #CCCCCC;
}

#SectionMenuBackground {
        font-family: Arial, Helvetica, sans-serif;
        font-size: 20px;
        text-align: right;
        color: white;
        height: 50%x;
        width: 93%;
        margin: 10px;
        padding: 10px;
}

#SectionMenuBackground a{
        font-size: 100%;
        text-align: center;
        color: white;
}


</style>
</HEAD>
<body>
<div id="Section">
                <div id="SectionHeader">
                <div id="SectionMenuBackground">

<img src="images/demos_header.gif" width="100%" height="20"></img>
<a href='http://yeda.cs.technion.ac.il:8088/webViewAnalysis/XMLOutputAnalyzerE.html'>English </a>|
<a href='javascript:history.back()'>חזור</a> |
<a href='http://www.mila.cs.technion.ac.il/'>אתר מילה </a> |
<a href='MorphologicalAnalyzer-ReleaseNotes-1.1.doc'> תיעוד הרקע הבלשני</a>


 </div>
</div>

<%
try{
//request.setCharacterEncoding("UTF-8") ;
response.setContentType("text/html;charset=UTF-8");
String lexiconId = request.getParameter("lexiconId");

String PGNMasculineSingular = "";
String PGNFeminineSingular = "";
String PGNMasculinePlural ="";
String PGNFemininePlural="";
String MasculineSingularConstruct="";
String MasculinePluralConstruct="";
String FeminineSingularConstruct="";
String FemininePluralConstruct="";
//String dual="";


showMultiWordInflections s = new showMultiWordInflections(lexiconId);
//dual =  s.getDual();
MasculineSingularConstruct =  s.getMasculineSingularConstruct(2);
MasculinePluralConstruct =  s.getMasculinePluralConstruct(2);
FeminineSingularConstruct =  s.getFeminineSingularConstruct(2);
FemininePluralConstruct =  s.getFemininePluralConstruct(2);
PGNMasculineSingular =  s.getPGNMasculineSingular(2);
PGNFeminineSingular =  s.getPGNFeminineSingular(2);
PGNMasculinePlural =  s.getPGNMasculinePlural(2);
PGNFemininePlural =  s.getPGNFemininePlural(2);

%>
<center>
<h3>הטיות שם</h3>
</center>
<% if (!MasculineSingularConstruct.equals("")){ %>
<table align="center" cellpadding="2" cellspacing="2" border="1" width="80%" >
<tr><th bgcolor="#E0E0E0">זכר -  יחיד -  נפרד/נסמך </th>
<tr>
<td align="center" bgcolor="#FFFFCC"><%= MasculineSingularConstruct %></td>
</tr>
</table>
<%  } if (!MasculinePluralConstruct.equals("")){ %>
<table align="center" cellpadding="2" cellspacing="2" border="1" width="80%" >
<tr><th bgcolor="#E0E0E0">זכר -  רבים - נפרד/נסמך </th>
<tr>
<td align="center" bgcolor="#FFFFCC"><%= MasculinePluralConstruct %></td>
</tr>
</table>
<%} if (!FeminineSingularConstruct.equals("")){ %><table align="center" cellpadding="2" cellspacing="2" border="1" width="80%" >
<tr><th bgcolor="#E0E0E0">נקבה -  יחיד - נפרד/נסמך</th>
<tr>
<td align="center" bgcolor="#FFFFCC"><%= FeminineSingularConstruct %></td>
</tr>
</table>
<%} if (!FemininePluralConstruct.equals("")){ %><table align="center" cellpadding="2" cellspacing="2" border="1" width="80%" >
<tr><th bgcolor="#E0E0E0">נקבה -  רבים -  נפרד/נסמך</th>
<tr><td align="center" bgcolor="#FFFFCC"><%= FemininePluralConstruct %></td>
</tr>
</table>
<%} if (!PGNMasculineSingular.equals("")){ %>
<table align="center" cellpadding="2" cellspacing="2" border="1" width="80%" >
<tr><th bgcolor="#E0E0E0">קניין יחיד זכר</th>
<tr>
<td align="center" bgcolor="#FFFFCC"><%= PGNMasculineSingular %></td>
</tr>
</table>
<% } if (!PGNFeminineSingular.equals("")){%>
<table align="center" cellpadding="2" cellspacing="2" border="1" width="80%" >
<tr><th bgcolor="#E0E0E0">קניין יחיד נקבה</th>
<tr>
<td align="center" bgcolor="#FFFFCC"><%= PGNFeminineSingular %></td>
</tr>
</table>
<% } if (!PGNMasculinePlural.equals("")){%>
<table align="center" cellpadding="2" cellspacing="2" border="1" width="80%" >
<tr><th bgcolor="#E0E0E0">קניין רבים זכר</th>
<tr>
<td align="center" bgcolor="#FFFFCC"><%= PGNMasculinePlural %></td>
</tr>
</table>
<% } if (!PGNFemininePlural.equals("")){%>
<table align="center" cellpadding="2" cellspacing="2" border="1" width="80%" >
<tr><th bgcolor="#E0E0E0">קניין רבים נקבה</th>
<tr>
<td align="center" bgcolor="#FFFFCC"><%= PGNFemininePlural %></td>
</tr>
</table>
<%
}
s.releaseConnection();

}catch(Exception e)
{
out.println(e.getMessage());
}
%>
</div>
</body></html>


