<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Tag Request</title>

</head>
<body>
	<h1>MILA Tagging Request Service</h1>
	<p>
		Currently logged in as
		<s:property value="username" />
		<s:url id="logout" action="logout" />
		(<s:a href="%{logout}">logout...</s:a>)
	</p>
	<h3>Submit new tagging request</h3>
	<p>This service accepts either a single Hebrew text file in UTF-8
		encoding, or several Hebrew text files in UTF-8 encoding inside a ZIP
		file.</p>
	<p>Other file formats will not work as expected (or at all).</p>
	<p>
		<s:form action="doUpload" method="post" enctype="multipart/form-data">
			<s:file name="upload" label="File" />
			<s:submit />
		</s:form>
	</p>
	<h3>Previously submitted tagging requests</h3>
	<table id="uploads">
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
