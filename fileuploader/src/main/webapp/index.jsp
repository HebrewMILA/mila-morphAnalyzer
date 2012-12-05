<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Tag Request</title>
<style type="text/css">
table {
	font-family: "Lucida Sans Unicode", "Lucida Grande", Sans-Serif;
	font-size: 12px;
	background: #fff;
	width: 480px;
	border-collapse: collapse;
	text-align: left;
	margin: 20px;
}

th {
	font-size: 14px;
	font-weight: normal;
	color: #039;
	border-bottom: 2px solid #6678b1;
	padding: 10px 8px;
}

td {
	border-bottom: 1px solid #ccc;
	color: #669;
	padding: 6px 8px;
}

tbody tr:hover td {
	color: #009;
}
</style>
</head>
<body>
	<h1>MILA Tagging Request Service</h1>
	<p>
		Currently logged in as
		<s:property value="username" />
		<s:url id="logout" action="logout" />
		(
		<s:a href="%{logout}">logout...</s:a>
		)
	</p>
	<h3>Submit new tagging request</h3>
	<p>
		<s:form action="doUpload" method="post" enctype="multipart/form-data">
			<s:file name="upload" label="File" />
			<s:submit />
		</s:form>
	</p>
	<h3>Previously submitted tagging requests</h3>
	<table>
		<thead>
			<tr>
				<th>ID</th>
				<th>Status</th>
				<th>Time Added</th>
				<th>Original Name</th>
				<th>Link</th>
			</tr>
		</thead>
		<tbody>
			<s:iterator value="tagRequests" var="req" status="stat">

				<tr>
					<s:url id="dl_link" action="download">
						<s:param name="tagRequestID" value="#req.id" />
					</s:url>
					<td><s:property value="#req.id" /></td>
					<td><s:property value="#req.state" /></td>
					<td><s:date name="#req.timestamp" nice="" /></td>
					<td><s:property value="#req.uploadedFilename" /></td>
					<td><s:if test='#req.state.toString() == "FINISHED" '>
							<s:a href="%{dl_link}">Download...</s:a>
						</s:if> <s:else>---</s:else></td>
				</tr>
			</s:iterator>
		</tbody>
	</table>
</body>
</html>
