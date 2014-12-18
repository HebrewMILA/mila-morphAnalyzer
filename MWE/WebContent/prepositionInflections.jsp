<%@ page import="java.sql.*"
import="java.net.URLDecoder"
import="lexicon.ShowPrepositionInflections"
 %>
<%@ page 
import="java.net.URLEncoder"
 %>
<%@page pageEncoding="UTF-8"%>



<html>
<HEAD>
<meta http-equiv="Content-Type" content="text/html; charset="UTF8">
<TITLE>
       Generator Viewer - database generator 
</TITLE>
<style>
body {
font-family: Verdana, Geneva, Arial, Helvetica, sans-serif; font-size: 5px; background-color: #FFF8DC; text-align: justify; color: black;
}
h3 { color: #336699; font-size:23px; font-weight: bold; font-variant: normal; margin: 0px; padding: 5px 0 10px 0; }



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
request.setCharacterEncoding("UTF-8") ;
response.setContentType("text/html;charset=UTF8");
String lexiconId = request.getParameter("lexiconId");
ShowPrepositionInflections s = new ShowPrepositionInflections(lexiconId);

String PGN = "";

PGN =  s.getPGN();


%>
<center>
<h3>הטיות מילת יחס</h3>
</center>
<% if (!PGN.equals("")){ %>
<table align="center" cellpadding="2" cellspacing="2" border="1" width="80%" >
<tr><th bgcolor="#E0E0E0">קניין </th>
<tr>
<td align="center" bgcolor="#FFFFCC"><font size=4><%=PGN%></font></td>
</tr>
</table>
<%
} else{
%>
<center>
<%
out.println("אין למילת היחס  הזו הטיות - קיימת רק צורת הבסיס");
}
%>
</center>
<%
//s.releaseConnection();

}catch(Exception e)
{
out.println(e.getMessage());
}
%>
</div>
</body></html>


