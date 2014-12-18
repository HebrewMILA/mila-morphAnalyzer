<%@ page language="java" %>
<%@ page isErrorPage="true" %>
<%@ page contentType ="text/html; charset=windows-1255" %>
<%@ page import="java.util.*" %>
<%@ page import="java.io.*" %>
<html>
<head>
<title>QSIA: Critical Error</title>
</head>
<body>
<p>
<font size="+1" color="red">The system had encountered a critical error. </font>
<p>
If the error persist, please notify the system administration using the 
following <a href="mailto:<%=session.getServletContext().getInitParameter("support-email")%>">email address</a>.
<%
Calendar gc2 = Calendar.getInstance();
java.util.Date d2 = gc2.getTime();
String visitorName = "?";
String exceptionMessage = "";
if (exception != null)	{
	if (exception.getMessage() != null)	{
		exceptionMessage = exception.getMessage();
	}
}
if (exceptionMessage.equals(""))	{
	exceptionMessage = "General Error was Found";
}
System.out.println("QSIA *Critical* Error - "+ d2.toString() + " for "+ visitorName +": "+ exceptionMessage);
ByteArrayOutputStream baos = new ByteArrayOutputStream();
//exception.printStackTrace(new PrintStream(baos));
//String stackTrace = baos.toString();
//stackTrace = stackTrace.substring(0, 400);
exception.printStackTrace();
%>
<p>
<br><br>
<br><br>

</body>
</html>
