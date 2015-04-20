<%@ page pageEncoding="UTF-8"%>
<%@ page import="java.sql.*"%>
<%@ page import="lexicon.ShowMultiWordInflections"%>
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
	ShowMultiWordInflections s = new ShowMultiWordInflections(lexiconId, 2);
	%>
	<dl>
		<dt>צורות יידוע</dt>
		<dd><%=s.getInflections()%></dd>
	</dl>
	<%
}catch(Exception e)
{
e.printStackTrace();
}
%>
</div>
</body></html>


