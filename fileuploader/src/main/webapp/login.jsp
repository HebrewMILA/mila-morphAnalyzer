<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>User Login</title>
</head>
<body>
	<h1>Log-in</h1>
	<p>Please log in to use the tagging request service.</p>
	<p>
		<s:form action="doLogin" method="post">
			<s:textfield name="username" label="Username" />
			<s:password name="password" label="Password" />
			<s:submit />
		</s:form>
	</p>
	<p>
		If you haven't already, please register on the <a
			href="http://mila.cs.technion.ac.il/license.html">License &
			Registration</a> page.
	</p>
</body>
</html>