<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<h1>Log-in failure</h1>
<p>Something went wrong. Please try again.</p>
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
