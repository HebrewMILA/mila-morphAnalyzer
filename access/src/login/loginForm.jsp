<?xml version="1.0" encoding="UTF-8" ?>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<link rel='stylesheet' href='css/lightbox.css' type='text/css'/>
<script src='scripts/prototype.js' type='text/javascript'></script>
<script src='scripts/lightbox.js' type='text/javascript'></script>


<title>Sign In</title>
</head>
<body style="background-color: #2580A2;color:white;">
	<div style="margin-left: auto; margin-right: auto;width: 300px;text-align: center;">
		<h2>Please Sign In</h2>
		<form name="loginForm" action="loginBean.jsp" method="post">
			<br/>
			<div>
				Email:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; <input type="text" name="email" size="30"/>
				<br/>
				<br/>
				Password: <input type="password" name="password" size="30"/>
				<br/>
				<br/>
				<input type="hidden" name="referer" value='<%= request.getHeader("Referer") %>'/>
				<input type="submit" value="Login" />
				
			</div>
		</form>
		<br/>
		Dont have a user name ? <a href="http://www.mila.cs.technion.ac.il/mila/eng/license.html" onclick="javascript:window.opener.parent.document.location.href = 'http://www.mila.cs.technion.ac.il/mila/eng/license.html';window.close();">Signup</a>
		<br/>
		if you forgot your password you can reset it here
		<br/>
		<%--<a href="#" class="lbAction" rel="deactivate" id="closeLink">Close</a> --%>
	</div>
</body>
</html>