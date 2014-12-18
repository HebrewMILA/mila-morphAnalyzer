<%@ page 
	contentType ="text/html; charset=utf-8"
	pageEncoding="utf-8"
	import="lexicon.contents.User"	
	import="lexicon.contents.Content"	
	import="java.util.List"	
	import="lexicon.exceptions.LSecurityException"	
	import="lexicon.exceptions.LAuthorizationException"
	errorPage="../errors/Error.jsp"
%>
<%
User visitor = null;
HttpSession showMySession = request.getSession(true);
if (showMySession != null)	{
	visitor = (User)session.getAttribute("userObj");
}
if (visitor == null)	{
	throw new LSecurityException("No user object was found", request);
}
if (!visitor.getString("username").equals("dshacham"))	{
	throw new LAuthorizationException("Page not authorized for this User");
}
String buttonName = "הוספת משתמש";
User user = new User();
user.set("username", "");
user.set("password", "");
user.set("firstName", "");
user.set("lastName", "");
user.set("email", "");
user.set("is_editor", 0);
user.set("is_admin", 0);	
int uid = 0;
if (request.getParameter("uid") != null) {
	uid = Integer.parseInt(request.getParameter("uid"));
	user.open(uid);
	buttonName = "עריכת משתמש";
}
String msg = "";
if (request.getParameter("username") != null) {
	msg = "המשתמש לא התווסף, נא בדקו שהוכנסו שם משתמש ודואל";
	if (!request.getParameter("username").equals("") && !request.getParameter("email").equals("")) {
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String firstName = request.getParameter("first_name");
		String lastName = request.getParameter("last_name");
		String email = request.getParameter("email");
		String isEditor = request.getParameter("is_editor");
		String is_admin = request.getParameter("is_admin");
		if (isEditor == null) {
			isEditor = "0";
		}
		if (is_admin == null) {
			is_admin = "0";
		}
		user.set("username", username);
		user.set("password", password);
		user.set("first_name", firstName);
		user.set("last_name", lastName);
		user.set("email", email);
		user.set("is_editor", Integer.parseInt(isEditor));
		user.set("is_admin", Integer.parseInt(is_admin));	
		if (uid == 0) {
			uid = user.add();
			if (uid != 0) {
				msg = "המשתמש התווסף בהצלחה";
			}
		} else {
			int feedback = user.update();
			if (feedback != 0) {
				msg = "המשתמש התעדכן בהצלחה";
			}
		}
		user.open(uid);	
	}				
}	
List users = visitor.getContents("SELECT * FROM users", "uid");
%>
<html>
<head>
	<%@include file="../general/metadata.jsp"%>
    <title>ניהול משתמשים</title>	 
</head> 
<body dir='rtl'>
<%
if (!msg.equals("")) {
%>
<font color=blue size++><%=msg%></font>
<%
}
%>
<table>
	<tr>
		<td valign='top' width='30%'>
			<table>
<%
if (users != null) {
	for (int i=0; i< users.size(); i++) {
		int tempUid = 0;
		tempUid = ((Integer)users.get(i)).intValue();
		User content = new User();
		content.open(tempUid);
%>	
				<tr>
					<td>
						<a href='DoUsers.jsp?uid=<%=content.getInt("uid")%>'>
							<%=content.getString("first_name")%>&nbsp;
							<%=content.getString("last_name")%>		
						</a>
					</td>
				</tr>
<%
	}
}
%>				
			</table>
		</td>
		<td valign='top' width='70%'>
			<form name='add_users_form' action="../administration/DoUsers.jsp" method="post">
			<input type='hidden' name='uid' value='<%=uid%>'>
			<table>
				<tr>
					<td> 
						שם המשתמש:
					</td>
					<td>
						<input type=text size=18 name='username' value='<%=user.getString("username")%>'>
					</td>
				</tr>
				<tr>
					<td> 
						סיסמא:
					</td>
					<td>
						<input type=password  name='password' value='<%=user.getString("password")%>'>
					</td>
				</tr>
				<tr>
					<td> 
						שם פרטי:
					</td>
					<td>
						<input type=text size=18 name='first_name' value='<%=user.getString("first_name")%>'>
					</td>
				</tr>
				<tr>
					<td> 
						שם משפחה:
					</td>
					<td>
						<input type=text size=18 name='last_name' value='<%=user.getString("last_name")%>'>
					</td>
				</tr>
				<tr>
					<td> 
						דואל:
					</td>
					<td>
						<input type=text size=30 name='email' value='<%=user.getString("email")%>'>
					</td>
				</tr>	
				<tr>
					<td> 
						האם עורך?
					</td>
					<td>
						<input type=checkbox name='is_editor' value='1'  <%if (user.getInt("is_editor") == 1) out.print("CHECKED='CHECKED'");%>>
					</td>
				</tr>	
				<tr>
					<td> 
						האם אדמיניסטרטור?
					</td>
					<td>
						<input type=checkbox name='is_admin' value='1' <%if (user.getInt("is_admin") == 1) out.print("CHECKED='CHECKED'");%>>
					</td>
				</tr>	
				<tr>
					<td colspan='2'>
						<input type=Submit value='<%=buttonName%>'>
					</td>
				</tr>
			</table>
			</form>
			<a href='DoUsers.jsp'> הוספת משתמש חדש</a>
		</td>
	</tr>
</table>
</body>
<%@include file="../general/footer.jspf"%>
</html>																							