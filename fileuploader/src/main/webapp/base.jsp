<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title><tiles:getAsString name="title" /></title>
<meta http-equiv="content-type" content="text/html;charset=utf-8">
<link rel="shortcut icon"
	href="http://mila.cs.technion.ac.il/images/favicon.ico">
<link rel="stylesheet" href="http://mila.cs.technion.ac.il/styles.css"
	type="text/css">
<script type="text/javascript">
	var _gaq = _gaq || [];
	_gaq.push([ '_setAccount', 'UA-22543000-1' ]);
	_gaq.push([ '_trackPageview' ]);
	(function() {
		var ga = document.createElement('script');
		ga.type = 'text/javascript';
		ga.async = true;
		ga.src = ('https:' == document.location.protocol ? 'https://ssl'
				: 'http://www')
				+ '.google-analytics.com/ga.js';
		var s = document.getElementsByTagName('script')[0];
		s.parentNode.insertBefore(ga, s);
	})();
</script>
<style type="text/css">
#uploads {
	font-family: "Lucida Sans Unicode", "Lucida Grande", Sans-Serif;
	font-size: 12px;
	background: #fff;
	width: auto;
	border-collapse: collapse;
	text-align: left;
	margin: 20px;
}

.wwFormTable td {
	border: none;
}

.wwFormTable tr {
	border: none;
}

#uploads th {
	font-size: 14px;
	font-weight: normal;
	border-bottom: 2px solid #6678b1;
	border-left: none;
	border-right: none;
	padding: 10px 8px;
	border: none;
	border-right: none;
}

#uploads td {
	border-bottom: 1px solid #ccc;
	color: #669;
	padding: 6px 8px;
	border-left: none;
	border-right: none;
}

#uploads tbody tr:hover td {
	color: #009;
}

#fileupload #menu .tools_fileupload {
	background: url("http://mila.cs.technion.ac.il/images/arrow.gif")
		no-repeat scroll left center rgb(37, 128, 162);
}
</style>
</head>

<body id="fileupload" class="home">

	<div id="pagewidth">
		<div id="header">
			<tiles:insertAttribute name="header" />
		</div>

		<div id="wrapper" class="clearfix">

			<div id="leftcol">
				<tiles:insertAttribute name="menu" />
			</div>

			<div id="maincol">
				<tiles:insertAttribute name="body" />
			</div>
		</div>
		<div id="footer">
			<tiles:insertAttribute name="footer" />
		</div>
	</div>
</body>

</html>