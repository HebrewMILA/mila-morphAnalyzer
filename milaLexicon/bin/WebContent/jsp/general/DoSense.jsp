<%@ page 
	language="java"
	contentType ="text/html; charset=utf-8"
	import="lexicon.contents.types.*"
	import="lexicon.contents.Content"
	import="lexicon.contents.User"
	import="lexicon.exceptions.*"
	import="java.net.*"
	import="java.util.*"
	pageEncoding="utf-8"
	errorPage="../errors/Error.jsp"
%>
<% 
User visitor = null;
HttpSession showMySession = request.getSession(true);
if (showMySession != null)	{
	visitor = (User)showMySession.getAttribute("userObj");
}
int id = 0;
int sid = 0;
ItemType item = new ItemType();
SenseType sense = new SenseType();
String dotted = "";
String button = "הוספת משמע חדש";
if (request.getParameter("id") != null) {
	id = Integer.parseInt(request.getParameter("id"));
	item.open(id);
	dotted = item.getDotted();
	if (!dotted.equals("")) {
		dotted = "&nbsp;("+dotted+")";
	}
}
if (request.getParameter("sid") != null && id != 0) {
	sid = Integer.parseInt(request.getParameter("sid"));
	sense.open(sid);
	button = "עידכון המשמע";
} 
// Doing add / update of a sense
if (request.getParameter("definition") != null) {
	if (visitor == null)	{
		throw new LSecurityException("No user object was found", request);
	}
	sense.setDefinition(request.getParameter("definition"));
	sense.setWeight(request.getParameter("weight"));
	if (sid == 0) {
		sid = sense.add(id);
	} else {
		sense.update();
	}
	sense.open(sid);
	item.open(id);
} 

%> 
<html>
<head>
	<title>משמעויות <%=item.getUndotted()+dotted%></title>	 
</head> 
<body dir='rtl'>
<%
if (visitor != null) {
%>
שלום <%=visitor.getName()%> 
<%
} else {
%>
שלום אורח (ניתן להתחבר בקשור למטה)
<%
}
String removeMsg = ""; 
// DELTEING A CONTENT (sense / english / example or synset)
if (request.getParameter("delete_content") !=null)	{
	if (visitor == null)	{
		throw new LSecurityException("No user object was found", request);
	}
	//Showing confirmation message
	if (request.getParameter("confirm")==null)	{
%> 
		<form name="delcontents" action="DoSense.jsp" method="post">
		<table width="500" border="1" cellpadding="5" cellspacing="0" rules=all bordercolor=#DDAD08 bordercolordark=WHITE>
		<tr>
			<td><font class="message">האם למחוק את המשמע?</font><br>
			<br>
			<input type="Submit" value="כן">&nbsp;
			<input type=button value="לא" onclick="history.back()">
			<input type="hidden" name="delete_content" value="<%=request.getParameter("delete_content")%>">
			<input type="hidden" name="content_type" value="<%=request.getParameter("content_type")%>">
			<input type="hidden" name="confirm" value="1">
			<input type="hidden" name="id" value="<%=id%>"> 
			<input type="hidden" name="sid" value="<%=sid%>"> 
			</td>
		</tr>
		</table>
		</form> 
<%
	} else {
		Content toRemove = null;
		if (request.getParameter("content_type") == null || request.getParameter("content_type").equals("null")) {
			toRemove = new SenseType(); 
			sense = new SenseType();
			sid = 0;
		}
		if (request.getParameter("content_type") != null) {
			if (request.getParameter("content_type").equals("english")) {
				toRemove = new EnglishType(); 
			}
			if (request.getParameter("content_type").equals("example")) {
				toRemove = new ExampleType(); 
			}
			if (request.getParameter("content_type").equals("synset")) {
				toRemove = new SynsetType(); 
			}
		}
		toRemove.open(Integer.parseInt(request.getParameter("delete_content")));
		int feedback = toRemove.remove();
		if (feedback > 0)	{
			sense.open(sid);
			removeMsg = "<br><font color=red><b>הפעולה נמחקה בהצלחה</b></font><br>";
		}
		else	{
			removeMsg = "<br><font color=red><b>הפעולה לא נמחקה</b></font><br>";
		}		
		out.println(removeMsg);
	}	//End of doing DELETE
}	 
%>
<table width='100%'>
	<tr>
		<td valign='top' width='37%'>
			<table>
				<tr>
					<td colspan='3'>
						<u><b>משמעויות קיימות למילה:</b></u>
							<a href='DoItem.jsp?id=<%=item.getId()%>'><%=item.getUndotted()+dotted%>&nbsp;[<%=item.getId()%>]</a><br><br>
					</td>
				</tr>			
<%
for (int i=0; i<item.getSense().size(); i++) {
	SenseType senseType = new SenseType((lexicon.jaxb.SenseType)item.getSense().get(i));
	
%>	
				<tr>
					<td valign='top'>&#149;</td>
					<td>
						<a href="DoSense.jsp?id=<%=id%>&sid=<%=senseType.getId()%>"><%=senseType.getDefinition()%></a>
					</td>
					<td>
						<a href="DoSense.jsp?id=<%=id%>&delete_content=<%=senseType.getId()%>"><img 
							src='/lexicon/images/delete.gif' width='16' height='16' border=0>
						</a>
					</td>					
				</tr>
<%
}
%>	
				<tr>
					<td colspan='3'>&nbsp;</td>
				</tr>					
				<tr>
					<td colspan='3'>
						<a href='DoSense.jsp?id=<%=id%>'>הוספת משמע חדש</a>
					</td>
				</tr>
			</table>		
		</td>
		<td width='3%'>
		<td valign='top' width='60%'>
			<form id='edit_sense' action="DoSense.jsp" method="post">
			<input type='hidden' name='id' value='<%=id%>'>
			<input type='hidden' name='sid' value='<%=sid%>'>
			<table width='100%' border='1'  RULES=NONE FRAME=BOX bordercolor='#DDAD08' bordercolordark='WHITE'>
				<tr>
					<td>
						מספר משמע:
					</td>
					<td>
						<%=sense.getId()%>
					</td>
				</tr>
				<tr>
					<td>
						הגדרה:
					</td>
					<td>
						<textarea name='definition' cols=30 rows=3><%=sense.getDefinition()%></textarea>
					</td>
				</tr>
				<tr>	
					<td>
						משקל:
					</td>
					<td>
						<input type='text' size=10 name='weight' value='<%=sense.getWeight()%>'>
					</td>						
				</tr>
				<tr>
					<td colspan=2>
						<input type='Submit' value='<%=button%>'>
					</td>
				</tr>
				<tr>
					<td valign='top' colspan='2'>
						<table>
							<tr>
								<td valign='top'>
									<table>
										<tr>
											<td colspan='2'>
												<b>תרגומים:</b>
											</td>
										</tr>
<%
for (int i=0; i<sense.getEnglish().size(); i++) {
	EnglishType englishType = new EnglishType((lexicon.jaxb.EnglishType)sense.getEnglish().get(i));
	
%>	
										<tr>
											<td valign='top'>&#149;</td>
											<td>
												<a href="DoSense.jsp?id=<%=id%>&sid=<%=sense.getId()%>&english_id=<%=englishType.getId()%>"><%=englishType.getTe()%></a>
											</td>
											<td>
												<a href="DoSense.jsp?id=<%=id%>&sid=<%=sid%>&delete_content=<%=englishType.getId()%>&content_type=english">
													<img src='/lexicon/images/delete.gif' width='16' height='16' border=0>
												</a>	
											</td>										
										</tr>
<%
}
%>	
									</table>
								</td>
								<td>&nbsp;</td>
								<td valign='top'>
									<table>
										<tr>
											<td colspan='2'>
												<b>דוגמאות:</b>
											</td>
										</tr>
<%
for (int i=0; i<sense.getExample().size(); i++) {
	ExampleType exampleType = new ExampleType((lexicon.jaxb.ExampleType)sense.getExample().get(i));
	
%>	
										<tr>
											<td valign='top'>&#149;</td>
											<td>
												<a href="DoSense.jsp?id=<%=id%>&sid=<%=sense.getId()%>&example_id=<%=exampleType.getId()%>"><%=exampleType.getPhrase()%></a>
											</td>
											<td>
												<a href="DoSense.jsp?id=<%=id%>&sid=<%=sid%>&delete_content=<%=exampleType.getId()%>&content_type=example">
													<img src='/lexicon/images/delete.gif' width='16' height='16' border=0>
												</a>	
											</td>													
										</tr>
<%
}
%>	
									</table>
								</td>
								<td>&nbsp;</td>
								<td valign='top'>
									<table>
										<tr>
											<td colspan='2'>
												<b>סינסטים:</b>
											</td>
										</tr>
<%
for (int i=0; i<sense.getSynset().size(); i++) {
	SynsetType synsetType = new SynsetType((lexicon.jaxb.SynsetType)sense.getSynset().get(i));
	
%>	
										<tr>
											<td valign='top'>&#149;</td>
											<td>
												<a href="DoSense.jsp?id=<%=id%>&sid=<%=sense.getId()%>&synset_id=<%=synsetType.getId()%>"><%=synsetType.getId()%></a>
											</td>
											<td>
												<a href="DoSense.jsp?id=<%=id%>&sid=<%=sid%>&delete_content=<%=synsetType.getId()%>&content_type=synset">
													<img src='/lexicon/images/delete.gif' width='16' height='16' border=0>
												</a>	
											</td>													
										</tr>
<%
}
%>	
									</table>
								</td>
							</tr>																
						</table>		
					</td>	
				</tr>
			</table>
			</form>
		</td>
	</tr>
<%
if (sid != 0) {
%>
	<tr>
		<td colspan='3' width='100%'>
			<table width='100%'>
				<tr>
					<td valign='top'>
						<%@include file="senses/DoEnglish.jspf"%>
					</td>
					<td>&nbsp;&nbsp;</td>
					<td valign='top'>
						<%@include file="senses/DoExample.jspf"%>
					</td>
					<td>&nbsp;&nbsp;</td>
					<td valign='top'>
						<%@include file="senses/DoSynset.jspf"%>
					</td>										
				</tr>
			</table>
		</td>
	</tr>
	<tr>
		<td colspan='3'>&nbsp;</td>
	</tr>
	<tr>
		<td colspan='3'>
			<UL>
			<li><a href='DoSense.jsp?id=<%=id%>&sid=<%=sid%>'>יצירת תרגום חדש</a><br>
			<li><a href='DoSense.jsp?id=<%=id%>&sid=<%=sid%>'>יצירת דוגמא חדשה</a><br>
			<li><a href='DoSense.jsp?id=<%=id%>&sid=<%=sid%>'>יצירת סינסט חדש</a>
			</UL>
		</td>				
	</tr>	
<%
}
%>		
</table>
<%@include file="../general/footer.jspf"%>	
</body>