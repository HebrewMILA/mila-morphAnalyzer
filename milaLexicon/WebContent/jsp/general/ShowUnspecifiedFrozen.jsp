<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql"%>
<%@taglib uri="http://mila.technion.ac.il/helpers" prefix="hlp"%>
<sql:setDataSource driver="com.mysql.jdbc.Driver" user="tommy"
	password="tammy2010!)"
	dataSource="jdbc:mysql://yeda.cs.technion.ac.il:3306/lexiconP?useUnicode=yes&characterEncoding=UTF-8" />
<sql:query var="bad_words"
	sql="SELECT multiWord.id AS id, item.undotted AS undotted, multiWord.pos AS pos
		 FROM multiWord, item
		 WHERE multiWord.id = item.id AND
		 multiWord.pos = 'unspecified';" />

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html dir="rtl">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<style type="text/css">
tr td {
	margin: 2px 5px 0px 5px;
}
tr.evenRow td {
	background-color: #DEDEDE;
	color: black;
}

tr.oddRow td {
	background-color: #BABABA;
	color: black;
}

tr th {
	background-color: #1A1A1A;
	color: white;
	size: 14pt;
}

td.english {
	text-align: left;
}

td.index {
	color: #202020;
}

body {
	background-color: #EEFFFF;
}
</style>
</head>
<body>
	<h1>ביטויים קפואים בעלי חלק דיבר לא חוקי בלקסיקון</h1>
	<h2>כל הביטויים</h2>
	<table border="0" align="center">
		<tr>
			<th>#</th>
			<th>מזהה</th>
			<th>ביטוי</th>
			<th>חלק דיבר</th>
		</tr>
		<c:forEach var="row" varStatus="loop" items="${bad_words.rows}">
			<tr id="row_${loop.index}" class="${loop.index % 2 == 0 ? 'evenRow' : 'oddRow'}">
				<td class="index"><c:out value="${loop.index}" /></td>
				<td><a href="DoItem.jsp?id=<c:out value="${row.id}" />"> <c:out
						value="${row.id}" /></a></td>
				<td><c:out value="${hlp:urldecode(row.undotted)}" /></td>
				<td class="english"><c:out value="${row.pos}" /></td>
			</tr>
		</c:forEach>
	</table>
</body>
</html>