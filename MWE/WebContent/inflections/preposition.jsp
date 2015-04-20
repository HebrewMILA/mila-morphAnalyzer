<%@ page pageEncoding="UTF-8"%>
<%@ page import="lexicon.ShowPrepositionInflections"%>
 
<html>
<HEAD>
	<meta http-equiv="Content-Type" content="text/html; charset="UTF8">
	<TITLE>פרטי הטיות</TITLE>
</style>
</HEAD>
<body>
<div id="Section">
<%
try{
	request.setCharacterEncoding("UTF-8") ;
	response.setContentType("text/html;charset=UTF8");
	String lexiconId = request.getParameter("lexiconId");
	ShowPrepositionInflections s = new ShowPrepositionInflections(lexiconId);

	String PGN =  s.getPGN();
	%>
	<% if (!PGN.equals("")){ %>
		<dl>
			<dt>קניין </dt>
			<dd><%=PGN%></dd>
		</dl>
	<% } else { %>
		<%=".אין למילת היחס הזו הטיות. קיימת רק צורת הבסיס"%>
	<% }
} catch(Exception e) {
	out.println(e.getMessage());
}
%>
</div>
</body></html>


