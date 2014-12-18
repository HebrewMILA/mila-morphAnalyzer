<%@ page language="java" contentType ="text/html; charset=utf-8"%>
<%@ page import="lexicon.contents.types.ItemType"%>
<%@ page 
	import="lexicon.contents.types.AbbreviationType"
	import="lexicon.contents.types.AbbreviationListType"
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
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
User visitor = null;
String removeMsg = ""; 
String addingMsg = "";
String notRemoveMsg = ""; 
String notAddingMsg = "";
boolean newItem = false;

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
List<?> changeGroup = null; 
String posFormButton = "שמירת פריט חדש";
String actionFormButton = "הוספת פעולה חדשה";
ItemType item = new ItemType();
String toDel = "חבל";

HttpSession showMySession = request.getSession(true);
if (showMySession != null)	
{
	visitor = (User)showMySession.getAttribute("userObj");
}

if (request.getParameter("id") != null) 
{
	id = Integer.parseInt(request.getParameter("id"));
	removedID = id;
	item.open(id);
	pos = item.getPos();
	posFormButton = "עדכן פריט"; 
}
if (request.getParameter("pos") != null) 
{
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
	if (id == 0) 
	{
		actionDone = "add";
		id = item.add();
		if (id >0) 
		{ 
			addingMsg = "<font color=blue><b>התווסף פריט חדש</b>";
		}  
		else 
		{
			notAddingMsg = "<font color=red><b>הפריט לא התווסף></b>";
		}
		removedID = id;
		newItem = true;
		session.setAttribute("newItem", String.valueOf(newItem));
	} 
	else if(request.getParameter("actionButton")==null) 
	{
		actionDone = "update";
		int feedback = item.update();
		if (feedback >0) 
		{
			addingMsg = "<font color=blue><b>הפריט התעדכן בהצלחה</b> (return code " + feedback + ")";
		} 
		else 
		{
			notAddingMsg = "<font color=red><b>הפריט לא התעדכן!></b>";
		}
	}	
	item.open(item.getID());
} else {
	if (request.getParameter("pos") != null) {
		notAddingMsg = "<font color=red><b>הפריט לא התווסף></b>";
	}
}
List<SearchItem> searchedItems = null;
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
<%@include file="actions/multiWordNounAdjectiveExceptionJ.jspf"%>

<%
if (!actionDone.equals(""))	
{
	new Log(visitor.getID(), removedID, aid, actionDone).add();
}
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" /> 
<html>
    <head>
    <%--<%@include file="../general/metadata.jsp"%>--%>
    <link rel="stylesheet" type="text/css" href="default.css" />
    <title>לקסיקון עברי</title>
    <script language="javascript" type="text/javascript">
	function savedoc(form_name, the_name, the_id, the_aid, searchURL) {
		var to_delete = confirm("האם למחוק ?");
    	if (to_delete) 
    	{
    		var theForm = document.getElementById(form_name);
    		if (the_id != null) 
    		{
    			theForm.id.value = the_id;
    		}
    		if (the_aid != null) 
    		{
    			theForm.aid.value = the_aid;
    		}  
    		theForm.action = theForm.action + "?"+searchURL;
    		return theForm.submit();
    	} 
    	else 
    	{
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
    </head>
<body dir='rtl'>
    <div class="content">
        <div class="user_header">
<%
if (visitor != null) 
{
	%>
	שלום <%=visitor.getName()%> 
	<%
} 
else 
{
	%>
	שלום אורח (<a href='../general/Login.jsp'>כניסה למערכת</a>)
	<%
}
%>
    </div>
<script language="javascript" type="text/javascript">
oldDiv = "<%=pos%>";
oldDivName = oldDiv+"_div";
</script>
<table width='100%' cellspacing="0">
	<tr>
		<td width='100%' colspan='2' valign='top'>
			<table width='100%'class="header">
				<tr>
					<td width='70%'>
						<%@include file="searchForm.jspf"%> 
					</td>
					<td width='30%' valign='top'>
						<UL>
                                                    <li><a href="DoItem.jsp">יצירת פריט חדש</a></li>
                                                   
                                                    <%--<li><a href="DoItem.jsp?id=<%=id%>">יצירת פעולה חדשה</a>--%>
                                                   
                                                    <li><a href="DoSense.jsp?id=<%=id%>">עריכת משמעויות</a></li>
                                                    
                                                    <li><a href="http://yeda.cs.technion.ac.il:8088/statistic/process.jsp?lexiconId=<%=id%>">חיפוש ב-YAHOO</a></li>
                                                   
                                                    <%--<li><a href="http://yeda.cs.technion.ac.il:8088/generatorViewer/mwTestGeneration.jsp?lexiconId=<%=id%>">בדוק יצירת ביטוי</a></li>--%>
                                                   
                                                    <li><a href="http://yeda.cs.technion.ac.il:8088/generatorViewer/viewLexiconStatistics.jsp">סטטיסטיקה</a></li>
                                                  
                                                    <%--<li><a href="http://yeda.cs.technion.ac.il:8088/generatorViewer/testGeneration.jsp?lexiconId=<%=id%>">בדוק יצירה</a></li>--%>
                                                  
                                                    <c:choose>   <%-- using JSTL to avoid unwanted options - yossi (15.11.10)--%>
                                                      <c:when test="${(param.POS=='multiWordFrozen') || (param.POS =='multiWordNounAdjective') || (param.POS =='multiWordPreposition')|| (param.POS =='multiWordNoun')}">
                                                      <li><a href="http://yeda.cs.technion.ac.il:8088/generatorViewer/mwTestGeneration.jsp?lexiconId=<%=id%>">בדוק יצירה</a></li>
                                                      </c:when>
                                                      <c:otherwise>
                                                       <li><a href="http://yeda.cs.technion.ac.il:8088/generatorViewer/testGeneration.jsp?lexiconId=<%=id%>">בדוק יצירה</a></li>
                                                      </c:otherwise>
                                                    </c:choose>
						</UL>
					</td>
				</tr>
			</table>
		</td>
	</tr>
       
<%
if (!removeMsg.equals("")) 
{
	out.println("<tr><td colspan='2'><img src='../../images/ok.gif' width='16' height='16'>"+removeMsg+"<br></td></tr>");
} 
if (!addingMsg.equals("")) 
{
	out.println("<tr><td colspan='2'><img src='../../images/ok.gif' width='16' height='16'>"+addingMsg+"<br></td></tr>");
}
if (!notRemoveMsg.equals("")) 
{
	out.println("<tr><td colspan='2'><img src='../../images/not_ok.gif' width='16' height='16'>"+notRemoveMsg+"<br></td></tr>");
} 
if (!notAddingMsg.equals("")) 
{
	out.println("<tr><td colspan='2'><img src='../../images/not_ok.gif' width='16' height='16'>"+notAddingMsg+"<br></td></tr>");
}
%>	
	<tr class="white">
		<td width='50%' valign='top'>
			<!--    the item parameters  -->
			<form id='edit_item' action="DoItem.jsp?<%=searchUrl%>&POS=<%=pos%>" method="post">
			<input type='hidden' name='id' value='<%=id%>'>
			<table>
				<tr>
					<td>
						מספר פריט:
					</td>
					<td>
						<%
						String _pos = "";
						if (id > 0) 
						{
							int prevItemId = -1;
							prevItemId = LexiconUtils.GetPrevItemId(id);
							//searchedItems = LexiconUtils.searchForItems(Integer.toString(id-1), "", "", "");
							if (prevItemId != -1)
							{
								//SearchItem searched = (SearchItem)searchedItems.get(0);
								_pos = LexiconUtils.GetItemPos(prevItemId);
							%>					
							<a href="DoItem.jsp?id=<%=prevItemId+"&search_id="+prevItemId+"&POS="+_pos%>">הקודם</a>&nbsp;
							<%
							}
						}
						%>
						<b><%=id%></b>
						<%
						if (id > 0) 
						{
							int nextItemId = -1;
							nextItemId = LexiconUtils.GetNextItemId(id);
							if (nextItemId != -1)
							{
								_pos = LexiconUtils.GetItemPos(nextItemId);
							%>		
							&nbsp;<a href="DoItem.jsp?id=<%=nextItemId+"&search_id="+nextItemId+"&POS="+_pos%>">הבא</a>
							<%
							}
						}
						%>
					</td>
				</tr>
				<tr>
					<td>
						צורה לא מנוקדת:
					</td>
					<td>
						<input type='text' name='undotted' size=20 value='<%=item.getUndotted().trim().trim()%>'>
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
						<select name='pos' onChange="choosePos()">
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
										
							<option value='multiWordNounAdjective' <%if (pos.equals("multiWordNounAdjective")) out.print("SELECTED");%>>ביטוי שם ותוארו</option>
							
							<option value='multiWordVerbPhrase' <%if (pos.equals("multiWordVerbPhrase")) out.print("SELECTED");%>>ביטוי פועלי</option>

							<option value='quantifier' <%if (pos.equals("quantifier")) out.print("SELECTED");%>>כמת</option>
							<option value='verb' <%if (pos.equals("verb")) out.print("SELECTED");%>>פועל</option>
							<option value='existential' <%if (pos.equals("existential")) out.print("SELECTED");%>>כמת יישי</option>	
							<option value='impersonal' <%if (pos.equals("impersonal")) out.print("SELECTED");%>>חסר גוף ומשקל</option>						
							<option value='wPrefix' <%if (pos.equals("wPrefix")) out.print("SELECTED");%>>תחילית</option>
							<option value='copula' <%if (pos.equals("copula")) out.print("SELECTED");%>>אוגד(קופולה)</option>
							<option value='numeral' <%if (pos.equals("numeral")) out.print("SELECTED");%>>שם מספר</option>						 
							 <option value='acronym' <%if (pos.equals("acronym")) out.print("SELECTED");%>>קיצור</option>
						</select>				
					</td>
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
					 	<%@include file="pos/DoAdjective.jspf"%>
					    <jsp:include page="pos/DoAdverb.jsp" />
						<%@include file="pos/DoAcronym.jspf"%>
						<%@include file="pos/DoConjuction.jspf"%>
					    <%@include file="pos/DoExistential.jspf"%>
					   <%@include file="pos/DoInterjection.jspf"%>
					   <%@include file="pos/DoInterrogative.jspf"%>
					   <%@include file="pos/DoNoun.jspf"%>
					    <%@include file="pos/DoPreposition.jspf"%>
						
						<%@include file="pos/DoPronoun.jspf" %>
						<%@include file="pos/DoProperName.jspf"%>
					    <%@include file="pos/DoModal.jspf"%>
					    <%@include file="pos/DoQuantifier.jspf"%>
					    <%@include file="pos/DoVerb.jspf"%>
					    <%@include file="pos/DoCopula.jspf"%>
					    <%@include file="pos/DoNumeral.jspf"%>
					    <%@include file="pos/DoTitle.jspf"%>
					    <%@include file="pos/DoWPrefix.jspf"%>
						<%@include file="pos/DoMultiWordFrozen.jspf"%>		
						<%@include file="pos/DoMultiWordPreposition.jspf"%>	
						<%@include file="pos/DoMultiWordNoun.jspf"%>	
 						<jsp:include page="pos/DoMultiWordNounAdjective.jsp"/>
						<jsp:include page="pos/DoMultiWordVerbPhrase.jsp"/>  

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
            <jsp:useBean id="abbreviationlist" class="lexicon.contents.types.AbbreviationListType"/>
            <jsp:setProperty name="abbreviationlist" property="id"/>
            
            <div id="abbrivations" class="exception_div">
                <table width='100%'>
                    <tr>
                        <td>
                            <div class="exception_table_header">
                            רשימת קיצורים
                            </div>
                        </td>
                    </tr>
					
                    <c:forEach items="${abbreviationlist.list}" var="abbreviation">
                    <tr>
                        <td><c:out value="${abbreviation.surface}"/></td>
                    </tr>
                    </c:forEach>
					
                </table>
            </div>
            

		<div id='exceptions' class='exception_div'>
                <table width='100%'>
		<tr>
			<td>
                        <div class="exception_table_header">
			יוצאי הדופן
                        </div>
                        <br/>
<%
	changeGroup = (List)session.getAttribute("changeGroup");
	if (changeGroup != null) {
		for (int i=0; i<changeGroup.size(); i++) 
		{
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

<img src='/milaLexicon/images/delete.gif' alt="delete" width='16' height='16' style="cursor:pointer" border=0 onClick="savedoc('del_content', 'theName', '<%=id%>', '<%=action.getInt("aid")%>', '<%=searchUrl%>')">
<!-- replacing <%=theName%> with thename-->
<br>
<%
			} 
			catch(Exception e) 
			{
				e.printStackTrace();
			}
		}
	}
%>			
					</td>
				</tr>
				<tr>
				<td>
                                <%-- *** this part is added for using a bean insted of scriplets (yossi 07.11.10)  --%>
                                <jsp:useBean id="gui" class="lexicon.gui.LexiconGui"/>
                                <jsp:setProperty name="gui" property="id"/>
                                <jsp:setProperty name="gui" property="aid"/>
                                
                                <%-- ************ update end (yossi 07.11.10)  --%>
				<form id='edit_actions' action="DoItem.jsp?<%=searchUrl%>&POS=<%=pos%>" method="post">
				<input type='hidden' name='id' value='<%=id%>'>
				<input type='hidden' name='aid' value='<%=aid%>'>
				<input type='hidden' name='action_pos' value='<%=pos%>'>

                                <br/>
                                <jsp:getProperty name="gui" property="exceptionHtml"/>

                                <%--
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
                                   --%>
						</form>	
					</td>		
				</tr>
			</table>
                  </div> <%-- end of exception div--%>
		</td>			
<%
} 
else 
{
	%>		
			<td width='50%' valign='top'>&nbsp;	</td>	
	<%
}
%>		
	</tr>
       
        <tr class="white">
            <td>
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
            </td>
            <td>&nbsp;</td>
        </tr>
</table>

<script language="javascript" type="text/javascript">
	document.getElementById(oldDivName).style.display = "";
	document.getElementById(oldDivName+'_action').style.display = "";
</script>
        
    <%@include file="../general/footer.jspf"%>
    </div> <%-- end of content div--%>
</body>
</html>
