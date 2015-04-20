<%@ page import="lexicon.ShowVerbInflections"%>
<%@ page import="java.util.*"%>
<%@ page pageEncoding="UTF-8"%>

<html>
<HEAD>
	<meta http-equiv="Content-Type" content="text/html; charset="UTF-8">
	<TITLE>פרטי הטיות</TITLE>
</HEAD>
<body style="direction: rtl;">
<div id="Section">
<%
try {
	String lexiconId = request.getParameter("lexiconId");

	Vector<String> pronounVec = new Vector<>();
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

	Vector<String> pronoun4Vec = new Vector<>();
	pronoun4Vec.add("זכר יחיד");
	pronoun4Vec.add("נקבה יחידה");
	pronoun4Vec.add("זכר רבים");
	pronoun4Vec.add("נקבה רבים");

	Vector<String> accusativeVec = new Vector<>();
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

	Vector<String> pastVec = new Vector<>();
	s.getPastInflections(pastVec);
	Vector<String> futureVec = s.getFutureInflections();

	Vector<String> imperativeVec = new Vector<>();
	s.getImperativeInflections(imperativeVec);
	
	int[] accToImper = new int[accusativeVec.size()];
	for (int i=0; i < accToImper.length; i++) accToImper[i]=-1;
	accToImper[1]=0; accToImper[2]=1; accToImper[6]=2; accToImper[7]=3;
	
	Vector<String> beinoniVec = new Vector<>();
	s.getBeinoniInflections(beinoniVec);
	int[] accToBeinoni = new int[accusativeVec.size()];
	accToBeinoni[0]=accToBeinoni[5]=-1;
	accToBeinoni[1]=accToBeinoni[3]=0;
	accToBeinoni[2]=accToBeinoni[4]=1;
	accToBeinoni[6]=accToBeinoni[8]=2;
	accToBeinoni[7]=accToBeinoni[9]=3;	
	
	Vector<String> passiveVec = new Vector<>();
	s.getPassiveInflections(passiveVec);

	Vector<String> infinitiveVec = new Vector<>();

	s.getInfinitiveInflections(infinitiveVec);

	String decodedInfinitive = s.getDecodedInfinitive();
	String decodedBareInfinitive = s.getDecodedBareInfinitive();
		
%>
	<table>
		<thead>
		<tr>
			<%if (infinitiveVec.size() > 0){%>	<th>שם פועל</th> <%}%>
			<th></th>
			<%if (pastVec.size() > 0) {%>		<th>עבר</th><%}%>
			<%if (beinoniVec.size() > 0) {%>	<th>הווה</th><%}%>
			<%if (futureVec.size() > 0) {%>		<th>עתיד</th><%}%>
			<%if (imperativeVec.size() > 0) {%>	<th>ציווי</th><%}%>
			<%if (infinitiveVec.size() > 0) {%>	<th>שם פועל מוטה</th><%}%>
		</tr>
		</thead>
		<%for (int j = 0; j < 10; j++) {%>
		<tr>
			<%if (infinitiveVec.size() > 0){%>	
				<td><%=j == 0 ? decodedInfinitive : ""%></td>
			<%}  if (pronounVec.size() > 0) {%>
				<td style="font-weight: bold;"><%=pronounVec.get(j)%></td>
			<%} if (pastVec.size() > 0) {%>
				<td><%=pastVec.get(j)%></td>
			<%} if (beinoniVec.size() > 0) {%>
				<td><%=accToBeinoni[j] < 0 ? "" : beinoniVec.get(accToBeinoni[j])%></td>
			<%} if (futureVec.size() > 0) {%>
				<td><%=futureVec.get(j)%></td>
			<%} if (imperativeVec.size() > 0) { %>
				<td><%=accToImper[j] < 0 ? "" : imperativeVec.get(accToImper[j])%></td>
			<%} if (infinitiveVec.size() > 0) {%>
				<td><%=infinitiveVec.get(j)%></td>
			<%} %>
		</tr>
		<%}%>
	</table>
	<!--
	<table>
		<tr>
		<th></th>
		<%if (passiveVec.size() > 0) {%>
			<th>בינוני פעול</th>
		<%} if (imperativeVec.size() > 0) {%>
			<th>ציווי</th>
		<%} %>
			<th>הווה</th>
		</tr>
		<%for (int j = 0; j < pronoun4Vec.size(); j++) {%>
			<tr>
			<td><%=pronoun4Vec.get(j)%></td>
			<%if (passiveVec.size() > 0) {%>
			<td><%=passiveVec.get(j)%></td>
			<%} if (imperativeVec.size() > 0) { %>
			<td><%=imperativeVec.get(j)%></td>
			<%} if (beinoniVec.size() > 0) {%>
			<td><%=beinoniVec.get(j)%></td>
			<%} else {%>
			<td></td>
			<%}%>
			</tr>
		<%}%>
	</table>
	-->
<%} catch (Exception e) {
	e.printStackTrace();
}%>
</table>
</div>
</body>
</html>


