<%@ page 
	language="java"
	contentType ="text/html; charset=utf-8"
	import="lexicon.contents.types.ItemType"
	import="lexicon.contents.Content"
	import="lexicon.contents.User"
	import="lexicon.contents.Log"
	import="lexicon.exceptions.*"
	import="lexicon.tools.Names"
	import="java.net.*"
	import="java.util.*"
	pageEncoding="utf-8"
	errorPage="../errors/Error.jsp"
%>
<% 
User visitor = null;
String removeMsg = ""; 
String addingMsg = "";
String notRemoveMsg = ""; 
String notAddingMsg = "";
boolean newItem = false;
HttpSession showMySession = request.getSession(true);
if (showMySession != null)	{
	visitor = (User)showMySession.getAttribute("userObj");
}
String undotted = "";
String dotted = "";
String register = "";
String spelling="";
String comment="";
String pos = "";
String searchUrl = "";
int id=0;
int aid=0;
int removedID = 0;
String actionDone = "";
List changeGroup = null; 
String posFormButton = "שמירת פריט חדש";
String actionFormButton = "הוספת פעולה חדשה";
ItemType item = new ItemType();
String toDel = "חבל";
if (request.getParameter("id") != null) {
	id = Integer.parseInt(request.getParameter("id"));
	removedID = id;
	item.open(id);
	pos = item.getPos();
	posFormButton = "עדכון פריט"; 
}
if (request.getParameter("pos") != null) {
	pos = request.getParameter("pos");
	if (visitor == null)	{
		throw new LSecurityException("No user object was found", request);
	}
}
if (request.getParameter("delete_action") != null) {
	if (visitor == null)	{
		throw new LSecurityException("No user object was found", request);
	}
}
if (request.getParameter("delete_item") != null) {
	if (visitor == null)	{
		throw new LSecurityException("No user object was found", request);
	}
}
if (request.getParameter("action_pos") != null) {
	if (visitor == null)	{
		throw new LSecurityException("No user object was found", request);
	}
}
if (request.getParameter("aid") != null) {
	aid = Integer.parseInt(request.getParameter("aid"));
	if (aid != 0) {
		actionFormButton = "עדכון הפעולה";
	}
}
if (request.getParameter("undotted") != null) {
	undotted = request.getParameter("undotted").trim();
}
if (request.getParameter("dotted") != null) {
	dotted = request.getParameter("dotted").trim();
}
if (request.getParameter("register") != null) {
	register = request.getParameter("register");
}
if (request.getParameter("spelling") != null) {
        spelling = request.getParameter("spelling");
}



if (request.getParameter("comment") != null) {
	comment = request.getParameter("comment").trim();
}
session.setAttribute("item", item);
session.setAttribute("changeGroup",changeGroup);
session.setAttribute("id", String.valueOf(id));
session.setAttribute("newItem", String.valueOf(newItem));

//  DOING DELETE
if (request.getParameter("delete_action") !=null || request.getParameter("delete_item") !=null)	{
	if (visitor == null)	{
		throw new LSecurityException("No user object was found", request);
	}
}
if (request.getParameter("delete_item") !=null)	{
	//Showing confirmation message
	int feedback = item.remove();
	if (feedback > 0)	{
		actionDone = "remove";
		item = new ItemType();
		pos = "";
		id = 0;
		removeMsg = "<font color=blue><b>הפריט נמחק בהצלחה</b>";
	}
	else	{
		notRemoveMsg="<font color=red><b>הפעולה לא נמחקה</b></font><br>";
	}
}	//End of doing DELETE
/******* Adding or  updating the item */
if (undotted.length()>0 && pos.length()>0) {
	item.setDotted(dotted);
	item.setUndotted(undotted);
	item.setRegister(register);
	item.setSpelling(spelling);
	item.setComment(comment);
	item.set("pos", pos);
	if (id == 0) {
		actionDone = "add";
		id = item.add();
		if (id >0) { 
			addingMsg = "<font color=blue><b>התווסף פריט חדש</b>";
		}  else {
			notAddingMsg = "<font color=red><b>הפריט לא התווסף></b>";
		}
		removedID = id;
		newItem = true;
		session.setAttribute("newItem", String.valueOf(newItem));
	} else if(request.getParameter("actionButton")==null) {
		actionDone = "update";
		int feedback = item.update();
		if (feedback >0) {
			addingMsg = "<font color=blue><b>הפריט התעדכן בהצלחה</b>";
		} else {
			notAddingMsg = "<font color=red><b>הפריט לא התעדכן!></b>";
		}
	}	
	item.open(item.getID());
} else {
	if (request.getParameter("pos") != null) {
		notAddingMsg = "<font color=red><b>הפריט לא התווסף></b>";
	}
}
List searchedItems = null;
%>
<%@include file="actions/adjectiveExceptionJ.jspf"%> 
<%@include file="actions/adverbExceptionJ.jspf"%>
<%@include file="actions/interjectionExceptionJ.jspf"%>
<%@include file="actions/interrogativeExceptionJ.jspf"%>
<%@include file="actions/nounExceptionJ.jspf"%>
<%@include file="actions/acronymExceptionJ.jspf"%>
<%@include file="actions/prepositionExceptionJ.jspf"%>
<%@include file="actions/quantifierExceptionJ.jspf"%>
<%@include file="actions/numeralExceptionJ.jspf"%>
<%@include file="actions/pronounExceptionJ.jspf"%>
<%@include file="actions/verbExceptionJ.jspf"%> 
<%@include file="actions/properNameExceptionJ.jspf"%>
<%@include file="actions/modalExceptionJ.jspf"%> 
<%@include file="actions/copulaExceptionJ.jspf"%>
<%@include file="actions/existentialExceptionJ.jspf"%>
<%@include file="actions/wprefixExceptionJ.jspf"%>
<%@include file="actions/multiWordFrozenExceptionJ.jspf"%>
<%@include file="actions/multiWordNounExceptionJ.jspf"%>

<%
if (!actionDone.equals(""))	{
	new Log(visitor.getID(), removedID, aid, actionDone).add();
}
%>
<html>
<head>
<script language="javascript">
	function savedoc(form_name, the_name, the_id, the_aid, searchURL) {
		var to_delete = confirm("האם למחוק ?");
    	if (to_delete) {
    		var theForm = document.getElementById(form_name);
    		if (the_id != null) {
    			theForm.id.value = the_id;
    		}
    		if (the_aid != null) {
    			theForm.aid.value = the_aid;
    		}  
    		theForm.action = theForm.action + "?"+searchURL;
    		return theForm.submit();
    	} else {
	    	return false;
	    }
	}
	var oldDiv = "<%=pos%>";
	var oldDivName = oldDiv+"_div";
	function choosePos() 
	{
		var divName = document.getElementById("edit_item").pos.value + "_div"; 
		document.getElementById(divName).style.display = "";
<%
// the add / replace / remove are shown only if an item was chosen
if (request.getParameter("id") != null) {
%>		
		document.getElementById(divName+'_action').style.display = "";
<%
}
%>		
		oldDivName = oldDiv+"_div";
	   	document.getElementById(oldDivName).style.display = "none";
<%
if (request.getParameter("id") != null) {
%>	   	
	   	document.getElementById(oldDivName+'_action').style.display = "none";
<%
	}
%>	   	
	   	oldDiv = document.getElementById("edit_item").pos.value; 
	}
</script>
	<%@include file="../general/metadata.jsp"%>
    <title>לקסיקון עברי</title>	 
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
%>
<form id="del_item" action="DoItem.jsp" method="post">
	<input type="hidden" name="delete_item" value="<%=item.getPos()%>">
	<input type="hidden" name="confirm" value="1">
	<input type="hidden" name="id" value="<%=id%>"> 
</form>
<form id="del_content" action="DoItem.jsp" method="post">
	<input type="hidden" name="delete_action" value="<%=item.getPos()%>">
	<input type="hidden" name="confirm" value="1">
	<input type="hidden" name="id" value="<%=id%>">
	<input type="hidden" name="aid" value="<%=aid%>">
</form> 
<script>
oldDiv = "<%=pos%>";
oldDivName = oldDiv+"_div";
</script>
<table width='100%'>
	<tr>
		<td width='100%' colspan='2' valign='top'>
			<table width='100%'>
				<tr>
					<td width='70%'>
						<%@include file="searchForm.jspf"%> 
					</td>
					<td width='30%' valign='top'>
						<UL>
							<li><a href="DoItem.jsp">יצירת פריט חדש</a>
							&nbsp;&nbsp;
							<li><a href="DoItem.jsp?id=<%=id%>">יצירת פעולה חדשה</a>
							&nbsp;&nbsp;
							<li><a href="DoSense.jsp?id=<%=id%>">עריכת משמעויות</a>
							 &nbsp;&nbsp;
                            <li><a href="http://yeda.cs.technion.ac.il:8088/statistic/process.jsp?lexiconId=<%=id%>">חיפוש ב-YAHOO</a>
							&nbsp;&nbsp;
							<li><a href="http://yeda.cs.technion.ac.il:8088/generatorViewer/mwTestGeneration.jsp?lexiconId=<%=id%>">בדוק יצירת ביטוי</a>
							&nbsp;&nbsp;
							<li><a href="http://yeda.cs.technion.ac.il:8088/generatorViewer/viewLexiconStatistics.jsp">סטטיסטיקה</a>
							&nbsp;&nbsp;
							 <li><a href="http://yeda.cs.technion.ac.il:8088/generatorViewer/testGeneration.jsp?lexiconId=<%=id%>">בדוק יצירה</a>

						
						</UL>
					</td>
				</tr>
			</table>
		</td>
	</tr>
<%
if (!removeMsg.equals("")) {
	out.println("<tr><td colspan='2'><img src='../../images/ok.gif' width='16' height='16'>"+removeMsg+"<br></td></tr>");
} 
if (!addingMsg.equals("")) {
	out.println("<tr><td colspan='2'><img src='../../images/ok.gif' width='16' height='16'>"+addingMsg+"<br></td></tr>");
}
if (!notRemoveMsg.equals("")) {
	out.println("<tr><td colspan='2'><img src='../../images/not_ok.gif' width='16' height='16'>"+notRemoveMsg+"<br></td></tr>");
} 
if (!notAddingMsg.equals("")) {
	out.println("<tr><td colspan='2'><img src='../../images/not_ok.gif' width='16' height='16'>"+notAddingMsg+"<br></td></tr>");
}
%>	
	<tr>
		<td width='50%' valign='top'>
			<!--    the item parameters  -->
			<form id='edit_item' action="DoItem.jsp?<%=searchUrl%>" method="post">
			<input type='hidden' name='id' value='<%=id%>'>
			<table>
				<tr>
					<td>
						מספר פריט:
					</td>
					<td>
						<%
						if (id > 0) {
						%>					
						<a href="DoItem.jsp?id=<%=id-1%>">הקודם</a>&nbsp;
						<%
						}
						%>
						<b><%=id%></b>
						<%
						if (id > 0) {
						%>		
						&nbsp;<a href="DoItem.jsp?id=<%=id+1%>">הבא</a>
						<%
						}
						%>
					</td>
				</tr>
				<tr>
					<td>
						צורה לא מנוקדת:
					</td>
					<td>
						<input type='text' name='undotted' size=20 value='<%=item.getUndotted().trim()%>'>
					</td>
				</tr>
				<tr>
					<td>
						צורה מנוקדת:
					</td>
					<td>
						<input type='text' name='dotted' size=20 value='<%=item.getDotted().trim().trim()%>'>
					</td>
				</tr>
				<tr>
					<td>
						צורת תעתיק:
					</td>
					<td>
						<%=item.getTransliterated()%>
					</td>
				</tr>
				<tr>
					<td>
						משלב:
					</td>
					<td>
						<select name='register'>
							<option value='formal' <%if (item.getRegister().equals("formal")) out.print("SELECTED");%>>תקני</option>
							<option value='archaic' <%if (item.getRegister().equals("archaic")) out.print("SELECTED");%>>ארכאי</option>
							<option value='informal' <%if (item.getRegister().equals("informal")) out.print("SELECTED");%>>תת-תקני</option>
						</select>				
					</td>
				</tr>

				  <tr>
                                        <td>
                                                כתיב:
                                        </td>
                                        <td>
                                                <select name='spelling'>
                                                        <option value='standard' <%if (item.getSpelling().equals("standard"))out.print("SELECTED");%>>תקני</option>
                                                        <option value='irregular' <%if (item.getSpelling().equals("irregular")) out.print("SELECTED");%>>תת-תקני</option>
                                                </select>
                                        </td>
                                </tr>

				<tr>
					<td>
						הערה:
					</td>
					<td>
						<textarea name='comment' cols=30 rows=4><%=item.getComment().trim()%></textarea>
					</td>
				</tr>
				<tr>
					<td>
						חלק דיבר:
					</td>
					<td>
						<form id='pos_form' name='pos_form' action="DoItem2.jsp" method="get">
						<select name='pos' onChange="this.submit()">
							<option value=''></option>
							<option value='adjective' <%if (pos.equals("adjective")) out.print("SELECTED");%>>שם תואר</option>
							<option value='adverb' <%if (pos.equals("adverb")) out.print("SELECTED");%>>תואר הפועל</option>
							<option value='conjunction' <%if (pos.equals("conjunction")) out.print("SELECTED");%>>מילת חיבור</option>
							<option value='interjection' <%if (pos.equals("interjection")) out.print("SELECTED");%>>מילת קריאה</option>
							<option value='interrogative' <%if (pos.equals("interrogative")) out.print("SELECTED");%>>מילת שאלה</option>
							<option value='negation' <%if (pos.equals("negation")) out.print("SELECTED");%>>מילת שלילה</option>
							<option value='title' <%if (pos.equals("title")) out.print("SELECTED");%>>תואר</option>

							<option value='noun' <%if (pos.equals("noun")) out.print("SELECTED");%>>שם עצם</option>
							<option value='preposition' <%if (pos.equals("preposition")) out.print("SELECTED");%>>מילת יחס</option>
							<option value='pronoun' <%if (pos.equals("pronoun")) out.print("SELECTED");%>>כינוי גוף</option>
							<option value='properName' <%if (pos.equals("properName")) out.print("SELECTED");%>>שם פרטי</option>
							 <option value='modal' <%if (pos.equals("modal")) out.print("SELECTED");%>>מודאל</option>
 							<option value='multiWordFrozen' <%if (pos.equals("multiWordFrozen")) out.print("SELECTED");%>>ביטוי קפוא</option>

							<option value='multiWordPreposition' <%if (pos.equals("multiWordPreposition")) out.print("SELECTED");%>>ביטוי יחס</option>

							 <option value='multiWordNoun' <%if (pos.equals("multiWordNoun")) out.print("SELECTED");%>>ביטוי נסמך סומך</option>


							<option value='quantifier' <%if (pos.equals("quantifier")) out.print("SELECTED");%>>כמת</option>
							<option value='verb' <%if (pos.equals("verb")) out.print("SELECTED");%>>פועל</option>
							<option value='existential' <%if (pos.equals("existential")) out.print("SELECTED");%>>כמת יישי</option>	
							<option value='impersonal' <%if (pos.equals("impersonal")) out.print("SELECTED");%>>חסר גוף ומשקל</option>						
							<option value='wPrefix' <%if (pos.equals("wPrefix")) out.print("SELECTED");%>>תחילית</option>
							<option value='copula' <%if (pos.equals("copula")) out.print("SELECTED");%>>אוגד(קופולה)</option>
							<option value='numeral' <%if (pos.equals("numeral")) out.print("SELECTED");%>>שם מספר</option>						 
							 <option value='acronym' <%if (pos.equals("acronym")) out.print("SELECTED");%>>קיצור</option>			
						</select>
						</form>
					</td>
				</tr>	
				<tr>
					<td colspan='2'>&nbsp;</td>
				</tr>
			
				<tr>
					<td colspan='2'>
						<div id='_div' style="display:none;"></div>
						<div id='negation_div' style="display:none;"></div>
						<div id='impersonal_div' style="display:none;"></div>
						<div id='_div_action' style="display:none;"></div>
<div id='negation_div_action' style="display:none;"></div>
<div id='title_div_action' style="display:none;"></div>
<div id='impersonal_div_action' style="display:none;"></div>
<!-- <div id='multiWordFrozen_div_action' style="display:none;"></div>--> <!-- to get the exeception for frozen (yossi-06.10.10)-->
<div id='multiWordPreposition_div_action' style="display:none;"></div>
	
	<!-- pos parameters-->
	<%
	session.setAttribute("item", item);
session.setAttribute("changeGroup",changeGroup);
session.setAttribute("id", String.valueOf(id));
session.setAttribute("newItem",String.valueOf(newItem));

	%>
 	<jsp:include page="pos/DoAdjective.jspf"/>
        <jsp:include page="pos/DoAdverb.jsp" />
	<jsp:include page="pos/DoAcronym.jspf"/>
	 <%@include file="pos/DoConjuction.jspf"%>
        <jsp:include page="pos/DoExistential.jspf"/>
        <jsp:include page="pos/DoInterjection.jspf"/>
        <jsp:include page="pos/DoInterrogative.jspf"/>
        <jsp:include page="pos/DoNoun.jspf"/>
        <jsp:include page="pos/DoPreposition.jspf"/>
	
	<jsp:include page="pos/DoPronoun.jspf" />

	<jsp:include page="pos/DoProperName.jspf"/>
        <jsp:include page="pos/DoModal.jspf"/>
        <jsp:include page="pos/DoQuantifier.jspf"/>
        <jsp:include page="pos/DoVerb.jspf"/>
        <jsp:include page="pos/DoCopula.jspf"/>
        <jsp:include page="pos/DoNumeral.jspf"/>
        <jsp:include page="pos/DoTitle.jspf"/>
        <jsp:include page="pos/DoWPrefix.jspf"/>
	<%@include file="pos/DoMultiWordFrozen.jspf"%>		
	<%@include file="pos/DoMultiWordPreposition.jspf"%>	
	<jsp:include page="pos/DoMultiWordNoun.jspf"/>	

</td>
				</tr>
				<tr>
					<td colspan='2'>
						<input  type=Submit value='<%=posFormButton%>'>
					</td>
				</tr>	
			</table>
			</form>		
		</td>
<%
// Showing the list of the add / replace / remove Objects
if (id != 0) {
%>		
		<td width='50%' valign='top'>
			<table>
				<tr>
					<td>			
			רשימת יוצאי הדופן<b>:</b><br><br>
<%
	changeGroup = (List)session.getAttribute("changeGroup");
	if (changeGroup != null) {
		for (int i=0; i<changeGroup.size(); i++) {
			try {
				Content action = (Content)changeGroup.get(i);  
				String dottedString = action.getString("dotted");
				if (!dottedString.equals("")) {
					dottedString = "&nbsp;["+dottedString+"]&nbsp;";
				}
				String theName = action.getString("undotted")+dottedString;
				String name ="";	
			
%>
<a href='DoItem.jsp?<%=searchUrl%>&id=<%=id%>&aid=<%=action.getInt("aid")%>'>
	<%=theName%> (<%=Names.getAction(action.getString("action"))%>)
</a>&nbsp;

<img src='/milaLexicon/images/delete.gif' width='16' height='16' style="cursor:'hand'" border=0 onClick="savedoc('del_content', '<%=theName%>', '<%=id%>', '<%=action.getInt("aid")%>', '<%=searchUrl%>')">
<br>
<%
			} catch(Exception e) {
				e.printStackTrace();
			}
		}
	}
%>			
					</td>
				</tr>
				<tr>
					<td>
						<form id='edit_actions' action="DoItem.jsp?<%=searchUrl%>" method="post">
							<input type='hidden' name='id' value='<%=id%>'>
							<input type='hidden' name='aid' value='<%=aid%>'>
							<input type='hidden' name='action_pos' value='<%=pos%>'>
				<!-- adjective add / replace / remove parameters-->
				<%@include file="actions/adjectiveException.jspf"%> 
				<!-- adverb add / replace /  parameters-->
				<%@include file="actions/adverbException.jspf"%>
				<!-- interjectionadd / replace /  parameters-->
				<%@include file="actions/interjectionException.jspf"%>
				<!-- interrogative add / replace /  parameters-->
				<%@include file="actions/interrogativeException.jspf"%>
				<!-- noun add / replace /  parameters-->
				<%@include file="actions/nounException.jspf"%>
				<!-- preposition add / replace /  parameters-->
				<%@include file="actions/prepositionException.jspf"%>
				<!-- quantifier add / replace /  parameters-->
				<%@include file="actions/quantifierException.jspf"%>
				<!-- numeral add / replace /  parameters-->
				<%@include file="actions/numeralException.jspf"%>
				<!-- verb add / replace /  parameters-->
				<%@include file="actions/verbException.jspf"%> 
				<!-- properName add / replace /  parameters-->
				<%@include file="actions/properNameException.jspf"%> 
				 <!-- modal add / replace /  parameters-->
                                <%@include file="actions/modalException.jspf"%>
				 <!-- pronoun add / replace /  parameters-->
                                <%@include file="actions/pronounException.jspf"%>
				 <!-- copula add / replace /  parameters-->
                                <%@include file="actions/copulaException.jspf"%>
				<!-- existential add / replace /  parameters-->
                                <%@include file="actions/existentialException.jspf"%>
				  <!-- wPrefix add / replace /  parameters-->
                                <%@include file="actions/wprefixException.jspf"%>
				   <!-- wPrefix add / replace /  parameters-->
                                <%@include file="actions/acronymException.jspf"%>
				   <!-- wPrefix add / replace /  parameters-->
                                <%@include file="actions/multiWordFrozenException.jspf"%>
				   <!-- wPrefix add / replace /  parameters-->
                                <%@include file="actions/multiWordNounException.jspf"%>				


						</form>	
					</td>		
				</tr>
			</table>								
		</td>			
<%
} else {
%>		
		<td width='50%' valign='top'>&nbsp;	</td>	
<%
}
%>		
	</tr>
</table>
<script>  
	document.getElementById(oldDivName).style.display = "";
	document.getElementById(oldDivName+'_action').style.display = "";
</script>	
<%@include file="../general/footer.jspf"%>	
</body>
