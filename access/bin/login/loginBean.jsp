<?xml version="1.0" encoding="UTF-8" ?>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">

<%@ page 
	import="java.net.*"
	import="javax.servlet.http.HttpSession"
%>

<jsp:useBean id="loginHandler" class="login.LoginBean" scope="request">
<jsp:setProperty name="loginHandler" property="*"/>
</jsp:useBean>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>Login in</title>
</head>
<body>
<!-- <body onload="window.open('<%=loginHandler.getReferer()%>','Downloading');window.close;"> -->
	<br/>
	<%--<jsp:getProperty name="loginHandler" property="email"/> --%>
	<br/>
	<%-- <jsp:getProperty name="loginHandler" property="password"/>
	<br/>
	<jsp:getProperty name="loginHandler" property="referer"/>--%>
	<% 
	HttpSession mySession = request.getSession(true);
	mySession.setAttribute( "authenticated", "false" );
	mySession.setAttribute( "email", "" );
		int authRes = loginHandler.Authenticate(); // check authentication
		if (authRes== 1 ) // authenitcate user
		{
			mySession.setAttribute( "authenticated", "true" );
			mySession.setAttribute( "email", loginHandler.getEmail() );
			//mySession.setAttribute( "file", loginHandler.getReferer() );	
		 	response.sendRedirect(loginHandler.getReferer());  // redirect to referer
			//out.println("<script language='JavaScript'>window.opener.parent.document.location.href='"+loginHandler.getReferer()+"'; window.close();</script>");
			//out.flush();
		}
		else if (authRes == 0 ) // request for password to be resent was made
		{
		    %>
		    <div style='width:600px;margin-left:auto;margin-right:auto;margin-top:200px;text-align:center;'>
			<h1>
				Your password have been sent to your email: 
				<br/>
				<jsp:getProperty name="loginHandler" property="email"/>
			</h1>
			<h2><a href='javascript:history.go(-1)'>go back</a></h2>
		</div>
		    
		    <%
		}
		else
		{
			%>
			<div style='width:600px;margin-left:auto;margin-right:auto;margin-top:200px;text-align:center;'>
				<h1>
					Signing in failed
					<br/>
					User name or password are wrong
				</h1>
				<h2><a href='javascript:history.go(-1)'>go back</a></h2>
			</div>
			<%
		}
		%>
</body>
</html>