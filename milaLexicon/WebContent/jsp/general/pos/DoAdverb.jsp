<%@ page 
	contentType ="text/html; charset=utf-8"
	pageEncoding="utf-8"
	import="lexicon.contents.pos.AdverbLexiconType"
	 import="lexicon.contents.types.ItemType"
         import="java.util.List"

%>
<%
AdverbLexiconType adverb = new AdverbLexiconType();
List changeGroup = (List)session.getAttribute("changeGroup");
ItemType item = (ItemType)session.getAttribute("item");
int id = Integer.parseInt((String)session.getAttribute("id"));
boolean newItem = Boolean.valueOf((String)session.getAttribute("newItem"));


if (!newItem && item.getAdverb() != null && id != 0) {
	adverb.open(id);
	changeGroup = adverb.getContents("adverb_exception_type", "id", id);
	 session.setAttribute("changeGroup", changeGroup);
}

if (request.getParameter("pos")!= null) {
	if (request.getParameter("pos").equals("adverb")) {
		if (request.getParameter("adverb_interrogative")!= null) {
			adverb.setInterrogative(request.getParameter("adverb_interrogative").equals("1"));
		}
		if (request.getParameter("adverb_modifiesVerb")!= null) {
			adverb.setModifiesVerb(request.getParameter("adverb_modifiesVerb"));
		}
		if (request.getParameter("adverb_modifiesAdjective")!= null) {
			adverb.setModifiesAdjective(request.getParameter("adverb_modifiesAdjective"));
		}
		if (request.getParameter("adverb_modifiesAdverb")!= null) {
			adverb.setModifiesAdverb(request.getParameter("adverb_modifiesAdverb"));
		}
		if (request.getParameter("adverb_position")!= null) {
			adverb.setPosition(request.getParameter("adverb_position"));
		}
		if (request.getParameter("adverb_inflect")!= null) {
			adverb.setInflect(request.getParameter("adverb_inflect").equals("1"));
		}
		if (request.getParameter("adverb_inflectionBase")!= null) {
			adverb.setInflectionBase(request.getParameter("adverb_inflectionBase").trim());
		}
		if (request.getParameter("adverb_type")!= null) {
                        adverb.setType(request.getParameter("adverb_type"));
                }

		if (newItem) {
			adverb.add(item.getID());
		} else {
			adverb.update();
		}
		item.open(item.getID());
		adverb.open(item.getID());
	}
}
%>
<div id='adverb_div' style="display:none;">
<table>
	 <tr>
                <td>
                        סוג:
                </td>
                <td>
                        <select name='adverb_type'>
                                <option value='focus' <%if (adverb.getType().equals("focus")) out.print("SELECTED");%>>מיקוד</option>
                                <option value='unspecified' <%if (adverb.getType().equals("unspecified")) out.print("SELECTED");%>>לא מוגדר</option>
                        </select>
                </td>
        </tr>

	<tr>
		<td>
			האם מילת שאלה?
		</td>
		<td>
			<select name='adverb_interrogative'>
				<option value='0' <%if (!adverb.isInterrogative()) out.print("SELECTED");%>>לא</option>
				<option value='1' <%if (adverb.isInterrogative()) out.print("SELECTED");%>>כן</option>
			</select>				
		</td> 
	</tr>	
	<tr>
		<td>
			האם משנה את בסיס נטיית הפועל?  
		</td>
		<td>
			<select name='adverb_modifiesVerb'>
				<option value='unspecified' <%if (adverb.getModifiesVerb().equals("unspecified")) out.print("SELECTED");%>>לא מוגדר</option>
				<option value='true' <%if (adverb.getModifiesVerb().equals("true")) out.print("SELECTED");%>>כן</option>
				<option value='false' <%if (adverb.getModifiesVerb().equals("false")) out.print("SELECTED");%>>לא</option>			
			</select>				
		</td>
	</tr>	
	<tr>
		<td>
			האם משנה את בסיס נטיית שם התואר?  
		</td>
		<td>
			<select name='adverb_modifiesAdjective'>
				<option value='unspecified' <%if (adverb.getModifiesAdjective().equals("unspecified")) out.print("SELECTED");%>>לא מוגדר</option>
				<option value='true' <%if (adverb.getModifiesAdjective().equals("true")) out.print("SELECTED");%>>כן</option>
				<option value='false' <%if (adverb.getModifiesAdjective().equals("false")) out.print("SELECTED");%>>לא</option>	
			</select>				
		</td>
	</tr>		
	<tr>
		<td>
			האם משנה את בסיס נטיית תואר הפועל?  
		</td>
		<td>
			<select name='adverb_modifiesAdverb'>
				<option value='unspecified' <%if (adverb.getModifiesAdverb().equals("unspecified")) out.print("SELECTED");%>>לא מוגדר</option>
				<option value='true' <%if (adverb.getModifiesAdverb().equals("true")) out.print("SELECTED");%>>כן</option>
				<option value='false' <%if (adverb.getModifiesAdverb().equals("false")) out.print("SELECTED");%>>לא</option>	
			</select>				
		</td>
	</tr>	
	<tr>
		<td>
			מיקום של תואר הפועל במשפט לעומת המילה אותה הוא מתאר/משנה:
		</td>
		<td>
			<select name='adverb_position'>
				<option value='unspecified' <%if (adverb.getPosition().equals("unspecified")) out.print("SELECTED");%>>לא מוגדר</option>
				<option value='pre' <%if (adverb.getPosition().equals("pre")) out.print("SELECTED");%>>לפני</option>
				<option value='post' <%if (adverb.getPosition().equals("post")) out.print("SELECTED");%>>אחרי</option> 			
				<option value='pre and post' <%if (adverb.getPosition().equals("pre and post")) out.print("SELECTED");%>>גם לפני וגם אחרי</option> 
			</select>				
		</td>
	</tr>	
	<tr>
		<td>
			 האם לייצר הטיות שייכות חבורה ?
		</td>
		<td>
			<select name='adverb_inflect'>
				<option value='0' <%if (!adverb.isInflect()) out.print("SELECTED");%>>לא</option>
				<option value='1' <%if (adverb.isInflect()) out.print("SELECTED");%>>כן</option>
			</select>				
		</td> 
	</tr>	
	<tr>
		<td>
			בסיס לנטיית גוף/מין/מספר אם שונה מהמילה:  
		</td>
		<td>
			<input type=text name='adverb_inflectionBase' size=15 value='<%=adverb.getInflectionBase().trim()%>'>				
		</td>
	</tr>				
</table> 
</div>		
