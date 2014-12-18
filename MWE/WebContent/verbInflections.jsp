<%@ page import="java.sql.*" import="java.net.URLDecoder"
	import="lexicon.ShowVerbInflections"%>
<%@ page import="java.net.URLEncoder"%>
<%@ page import="java.util.*"%>

<%@page pageEncoding="UTF-8"%>

<%
	Connection connection = null;
	Statement statement = null;
	ResultSet rs = null;
%>

<html>
<HEAD>
<meta http-equiv="Content-Type" content="text/html; charset="UTF-8">
<TITLE>Inflections Details</TITLE>
<style>
body {
	font-family: Verdana, Geneva, Arial, Helvetica, sans-serif;
	font-size: 5px;
	background-color: #FFF8DC;
	text-align: justify;
	color: black;
}

h3 {
	color: #336699;
	font-size: 23px;
	font-weight: bold;
	font-variant: normal;
	margin: 0px;
	padding: 5px 0 10px 0;
}

#Section {
	position: relative;
	top: -3%;
	margin: 0px;
	padding: 0% 2% 2% 2%;
	width: 100%;
	font-size: 20px;
}

#Section td {
	font-family: Verdana, Geneva, Arial, Helvetica, sans-serif;
	font-size: 20px;
}

#SectionHeader {
	background: #CCCCCC;
	position: relative;
	overflow: hidden;
	margin: 2px 0 2px 0;
	padding: 0px;
	height: 10%;
	width: 100%;
	border-left: 1px solid #CCCCCC;
	border-right: 1px solid #CCCCCC;
}

#SectionMenuBackground {
	font-family: Arial, Helvetica, sans-serif;
	font-size: 20px;
	text-align: right;
	color: white;
	height: 50% x;
	width: 93%;
	margin: 10px;
	padding: 10px;
}

#SectionMenuBackground a {
	font-size: 100%;
	text-align: center;
	color: white;
}
</style>


</style>
</HEAD>
<body>
	<div id="Section">
		<div id="SectionHeader">
			<div id="SectionMenuBackground">

				<img src="images/demos_header.gif" width="100%" height="20"></img> <a
					href='http://yeda.cs.technion.ac.il:8088/webViewAnalysis/XMLOutputAnalyzerE.html'>English
				</a>| <a href='javascript:history.back()'>VERBחזור</a> | <a
					href='http://www.mila.cs.technion.ac.il/'>אתר מילה </a> | <a
					href='MorphologicalAnalyzer-ReleaseNotes-1.1.doc'> תיעוד הרקע
					הבלשני</a>


			</div>
		</div>

		<%
			try {
				String lexiconId = request.getParameter("lexiconId");
				String decodedInfinitive = "";
				String decodedBareInfinitive = "";

				Vector pronounVec = new Vector();
				pronounVec.add("אני");
				pronounVec.add("אתה");
				pronounVec.add("את");
				pronounVec.add("הוא");
				pronounVec.add("היא");
				pronounVec.add("אנחנו");
				pronounVec.add("אתם");
				pronounVec.add("אתן");
				pronounVec.add("הם");
				pronounVec.add("הן");

				Vector pronoun4Vec = new Vector();
				pronoun4Vec.add("זכר יחיד");
				pronoun4Vec.add("נקבה יחידה");
				pronoun4Vec.add("זכר רבים");
				pronoun4Vec.add("נקבה רבים");

				Vector accusativeVec = new Vector();
				accusativeVec.add("אני או אותי");
				accusativeVec.add("אתה או אותך");
				accusativeVec.add("את או אותך");
				accusativeVec.add("הוא או אותו");
				accusativeVec.add("היא או אותה");
				accusativeVec.add("אנחנו או אותנו");
				accusativeVec.add("אתם או אותכם");
				accusativeVec.add("אתן או אותכן");
				accusativeVec.add("הם או אותם");
				accusativeVec.add("הן או אותן");

				response.setContentType("text/html;charset=UTF-8");

				ShowVerbInflections s = new ShowVerbInflections(lexiconId);

				Vector pastVec = new Vector();
				s.getPastInflections(pastVec);
				System.out.println("pastVec" + pastVec.size());

				Vector futureVec = new Vector();
				futureVec = s.getFutureInflections();

				Vector imperativeVec = new Vector();
				s.getImperativeInflections(imperativeVec);

				Vector beinoniVec = new Vector();
				s.getBeinoniInflections(beinoniVec);

				Vector passiveVec = new Vector();
				s.getPassiveInflections(passiveVec);

				Vector infinitiveVec = new Vector();

				s.getInfinitiveInflections(infinitiveVec);

				decodedInfinitive = s.getDecodedInfinitive();
				decodedBareInfinitive = s.getDecodedBareInfinitive();

				s.releaseConnection();
		%>
		<center>
			<h3>הטיות הפועל</h3>
		</center>
		<table align="center" cellpadding="2" cellspacing="2" border="1"
			width="80%">
			<tr bgcolor="#E0E0E0">
				<%
					if (futureVec.size() > 0) {
				%>
				<th>עתיד</th>
				<%
					}
				%>
				<th>עבר</th>
				<th></th>
			</tr>
			<%
				for (int j = 0; j < 10; j++) {
			%>
			<tr align="center" bgcolor="#FFFFCC">
				<%
					if (futureVec.size() > 0) {
				%>
				<td><%=(String) futureVec.get(j)%></td>
				<%
					}
				%>
				<%
					if (pastVec.size() > 0) {
				%>
				<td><%=(String) pastVec.get(j)%></td>
				<%
					}
				%>
				<%
					if (pronounVec.size() > 0) {
				%>
				<td bgcolor="#CCCC99"><%=(String) pronounVec.get(j)%></td>
				<%
					}
				%>
			</tr>
			<%
				}
			%>
		</table>
		<br>

		<table align="center" cellpadding="2" cellspacing="2" border="1"
			width="80%">
			<%
				if (passiveVec.size() > 0) {
			%>
			<tr bgcolor="#E0E0E0">
				<th>בינוני פעול</th>
				<th>ציווי</th>
				<th>הווה</th>
				<th></th>
			</tr>
			<%
				} else if (imperativeVec.size() > 0 && passiveVec.size() == 0) {
			%>
			<tr bgcolor="#E0E0E0">
				<th>ציווי</th>
				<th>הווה</th>
				<th></th>
			</tr>
			<%
				} else {
			%>
			<tr bgcolor="#E0E0E0">
				<th>הווה</th>
				<th></th>
			</tr>
			<%
				}
			%>
			<%
				for (int j = 0; j < pronoun4Vec.size(); j++) {
			%>
			<tr align="center" bgcolor="#FFFFCC">
				<%
					if (passiveVec.size() > 0) {
				%>
				<td><%=(String) passiveVec.get(j)%></td>
				<%
					}
							if (imperativeVec.size() > 0) {
				%>
				<td><%=(String) imperativeVec.get(j)%></td>
				<%
					}
							if (beinoniVec.size() > 0) {
				%>
				<td><%=(String) beinoniVec.get(j)%></td>
				<%
					} else {
				%>
				<td></td>
				<%
					}
				%>
				<td bgcolor="#CCCC99"><%=(String) pronoun4Vec.get(j)%></td>

			</tr>
			<%
				}
			%>
		</table>
		<%
			System.out.println("infinitiveVec.size() "
						+ infinitiveVec.size());
				if (infinitiveVec.size() > 0) {
		%>
		<br>
		<table align="center" cellpadding="2" cellspacing="2" border="1"
			width="40%">
			<tr bgcolor="#E0E0E0">
				<th>שם פועל</th>
				<!--th>מקור</th></tr-->
			<tr align="center" bgcolor="#FFFFCC">
				<td><%=decodedInfinitive%></td>
				<!--td><%=decodedBareInfinitive%></td-->
			</tr>
		</table>
		<br>
		<table align="center" cellpadding="2" cellspacing="2" border="1"
			width="40%">
			<tr bgcolor="#E0E0E0">
				<th>שם פועל מוטה</th>
				<th></th>
			</tr>
			<%
				for (int j = 0; j < accusativeVec.size(); j++) {
			%>
			<tr align="center" bgcolor="#FFFFCC">
				<td><%=(String) infinitiveVec.get(j)%></td>
				<td bgcolor="#CCCC99"><%=(String) accusativeVec.get(j)%></td>
			</tr>
			<%
				}
			%>
		</table>
		<%
			}
		%>
		<%
			} catch (Exception e) {
				out.println(e.getMessage());
			}
		%>
		</table>
	</div>
</body>
</html>


