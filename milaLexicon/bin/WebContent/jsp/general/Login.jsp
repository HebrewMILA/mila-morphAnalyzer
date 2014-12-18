<%@ page 
	contentType ="text/html; charset=utf-8"
	pageEncoding="utf-8"
	import="lexicon.contents.User"	
	import="lexicon.contents.Content"	
	import="java.util.List"	 
	import="java.net.*"
%>
<%
User visitor = null;
HttpSession mySession = request.getSession(true);
String getUsername = null;
String getPassword = null;
getUsername = request.getParameter("username");
getPassword = request.getParameter("password");
String returnTo = request.getParameter("return_to");
if (returnTo == null) {
	returnTo = "";
}
int uid = 0;
if ((getUsername != null) && (getPassword != null) && !getUsername.trim().equals("") && !getPassword.trim().equals(""))	{
    User user = new User();
    uid = user.validateAndOpen(getUsername, getPassword);
    if (uid != 0)    {
       	mySession.setAttribute("userObj", user);
       	if (request.getParameter("return_to") != null && !request.getParameter("return_to").equals(""))	{
			String queryString = request.getParameter("params");
			if (queryString == null) {
				queryString = "";
			}
			if (!queryString.equals("")) {
				String params = queryString.substring(1);
				params = URLDecoder.decode(params, Content.ADD_ENCODING);
				queryString = "?"+ params;
			}
            response.sendRedirect("/lexicon/"+request.getParameter("return_to") + queryString);
			return;
		}
		response.sendRedirect("../general/DoItem.jsp");
		return;
	}
}
mySession.setAttribute("userObj", null);
String contextPath = request.getContextPath();
%>
<html>
<head>
<%@include file="../general/metadata.jsp"%>
<title>לקסיקון - כניסת משתמשים</title>
</head>
<body dir='rtl'>
<%
if (request.getParameter("username") != null && uid==0) {
%>
<font color=red>שם המשתמש או הסיסמא אינם נכונים, נא לנסות שנית!</font><br>
<%
}
%>
<form name='login_form' action="<%=contextPath%>/jsp/general/Login.jsp" method="post">
<input type='hidden' name='return_to' value='<%=returnTo%>'>
<input type='hidden' name='params' value='<%=request.getParameter("params")%>'>
<table border=0 cellpadding="2" cellspacing="2">
<tr>
	<td width="100">שם משתמש:</td>
	<td><input type="text" name="username" size=20></td>
</tr>
<tr>
	<td width="100">סיסמא:</td>
	<td><input type="password" name="password" size=20></td>
</tr>
<tr>
	<td width="100" colspan='2'>
		<input type='Submit' value='כניסה'>
	</td>
</tr>
</table>
</form>
<%@include file="../general/footer.jspf"%>
</body>
</html>
