<%@ page 
	contentType ="text/html; charset=utf-8"
	isErrorPage="true"
	pageEncoding="utf-8"
	import="lexicon.contents.User"
	import="lexicon.contents.Content"
	import="java.util.List"	
	import="java.net.*"
	import="lexicon.exceptions.LSecurityException"	
	import="lexicon.exceptions.LAuthorizationException"
	import="java.util.*"
	import="java.io.*"
	import="lexicon.exceptions.*" 
	import="lexicon.tools.*"
	import="javax.servlet.http.*" 
	errorPage="../errors/CriticalError.jsp"
%>
<%
User visitor = null;
javax.servlet.http.HttpSession showMySession = request.getSession(true);
if (showMySession != null)	{
	visitor = (User)showMySession.getAttribute("userObj");
}
Calendar gc2 = Calendar.getInstance();
java.util.Date d2 = gc2.getTime();
String visitorName = "?";
String redirectTo = "../general/Login.jsp?user_error=1";
if (visitor != null)	{
	visitorName = visitor.getString("username");
}

/*****************		Handling Security Exceptions		********************/
if (exception instanceof LPasswordException)	{		
	redirectTo += "&no_user=true";
	response.sendRedirect(redirectTo);	
}else if(exception instanceof LSecurityException){
	//redirectTo += "&";
	LSecurityException qe = (LSecurityException)exception;
	if (qe.getServletRequest() == null)	{
		throw new AException("No ServletRequest was found for QSecurityException");
	}
	if (qe.isDeniedAccess())	{
		throw new AException("Your permissions do not allow access to this resource");
	}
	ServletRequest oldServletRequest = qe.getServletRequest();
	String params = LexiconUtils.renderAllRequestParameters(oldServletRequest, null, 0);
	params = URLEncoder.encode(params, Content.ADD_ENCODING);
%>	
<jsp:forward page="<%=redirectTo%>"> 
<jsp:param name="return_to" value="<%=qe.getPage()%>" /> 
<jsp:param name="params" value="<%=params%>" /> 
</jsp:forward>
<% 
}

/********************	Handling regular exceptions	****************************/
else	{
	String exceptionMessage = "";
	if (exception != null)	{
		if (exception.getMessage() != null)	{
			exceptionMessage = exception.getMessage();
		}
	}
	if (exceptionMessage.equals(""))	{
		exceptionMessage = "General Error was Found";
	}
%>
<html>
<head>
<%@include file="../general/metadata.jsp"%>
<title>טעות</title>
</head>
<body dir="rtl">
<table class="menu_color">
	<tr>
		<td align="center" width="30%" bgcolor="white">
		<b><a href="javascript:self.history.back();">&lt;&lt; חזרה לעמוד הקודם</a></b></td> 
	</tr>
</table>
<br><br>
<h1>תקלה!</h1>
<p>
בדף זה נתגלתה תקלה:<br><br>
<font class=errorfont><strong><%= exceptionMessage %></strong></font>
<p>
<br><br>
<ul>
<li>ניתן לשלוח דואל אל: <a href="mailto:mila@cs.technion.ac.il">סלבה דמנדר</a> 
</ul>
<br><br>
<%
out.println("<textarea cols=\"100\" rows=\"30\">");
out.println(exception.getMessage());
ByteArrayOutputStream baos = new ByteArrayOutputStream();
exception.printStackTrace(new PrintStream(baos));
String stackTrace = baos.toString();
out.print(stackTrace);
stackTrace = stackTrace.substring(0, 400);
exception.printStackTrace();
out.println("</textarea>");
%>
<br><br>
<table class="menu_color">
	<tr>
		<td align="center" width="30%" bgcolor="white">
		<b><a href="javascript:self.history.back();">&lt;&lt; חזרה לעמוד הקודם</a></b></td>
		<td>&nbsp;</td>
	</tr>
</table>
</body>
</html>
<%
}	//End of else - taking care of regular exception
%>
