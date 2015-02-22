<?xml version="1.0" encoding="UTF-8" ?>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1" />
<title>Login</title>
</head>
<body>
<div style="margin-left: auto; margin-right: auto;width: 300px;">
	<form action="login.jsp" method="get">
		<br/>
		<br/>
		<div>
			Email:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; <input type="text" name="email"/>
			<br/>
			<br/>
			Password: <input type="text" name="password"/>
			<br/>
			<br/>
			<input type="hidden" value='<%= request.getHeader("Referer") %>'/>
			<input type="submit" value="Login"/>
		</div>
	</form>
</div>
</body>
</html>