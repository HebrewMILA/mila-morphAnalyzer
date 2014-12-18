<%@ 
page language="java" 
import="gen.TestGeneration"
import="gen.InflectionEntry"
import="lexicon.generate.*"
import="lexicon.contents.User"
%>
<%

User visitor = null;
String action = null;
String lexiconId = null;


/*String inflection = null;
String transliterated = null;
String pgn = null;
String suffixStatus = null;
String binyan = null;
String decodedRoot = null;
String tense = null;
String polarity = null;
String prefix = null;
String _type = null;
String definiteness = null;
String suffixFunction = null;
String register = null;
String spelling = null;
String gender = null;
String number = null;
String person = null;*/

String output = "Ops, looks like there was an error while trying to create this page";

String  id;
String  surface;
String  basePos;
String  baseTransliteratedLItem;
String  baseUndottedLItem;
String  root;
String  dottedLexiconItem;
String  value;
String  hebForeign;  
String  baseLexiconPointer;
String  transliterated;
String  baseNumber;
String  baseGender;
String  basePerson;
String  PGN;
String  suffixFunction;
String  suffixStatus;
String  binyan;
String  tense;
String  baseDefinitness;
String  register;
String  spelling;
String  polarity;
String  prefix;
String  _type;


HttpSession showMySession = request.getSession(true);

if (showMySession != null)	
{
	visitor = (User)showMySession.getAttribute("userObj");
}

lexiconId = request.getParameter("lexiconId");
action = request.getParameter("action");

id = request.getParameter("id");
surface = request.getParameter("surface");
basePos = request.getParameter("basePos");
baseTransliteratedLItem = request.getParameter("baseTransliteratedLItem");
baseUndottedLItem = request.getParameter("baseUndottedLItem");
root = request.getParameter("root");
dottedLexiconItem = request.getParameter("dottedLexiconItem");
value = request.getParameter("value");
hebForeign = request.getParameter("hebForeign");
baseLexiconPointer = request.getParameter("baseLexiconPointer");
transliterated = request.getParameter("transliterated");
baseNumber = request.getParameter("baseNumber");
baseGender = request.getParameter("baseGender");
basePerson = request.getParameter("basePerson");
PGN = request.getParameter("PGN");
suffixFunction = request.getParameter("suffixFunction");
suffixStatus = request.getParameter("suffixStatus");
binyan = request.getParameter("binyan");
tense = request.getParameter("tense");
baseDefinitness = request.getParameter("baseDefinitness");
register = request.getParameter("register");
spelling = request.getParameter("spelling");
polarity = request.getParameter("polarity");
prefix = request.getParameter("prefix");
_type = request.getParameter("type");


String query = null;


%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" /> 
<html>
<head>
<style type="text/css">
	body
	{
		font-family:Arial, Helvetica, sans-serif;
	}
	table
	{
		empty-cells: show;
		font-size:19px;
		border-style:solid;
		border-width:1px;
		border-color:#5D60AD;
	}
	th
	{
		
		border-style:solid;
		border-width:1px;
		border-color:white;
		background:#5D60AD;
		color:white;
	}
	td 
	{	
		border-style:solid;
		border-width:1px;
		/*border-color:#5D60AD;*/
		border-color:white;
		background:#edf3ff;
		text-align:center
	}
	tr:hover td 
	{
		background-color: #A1C5ED;
	}
	form
	{
		margin:0px;
	}
	
	#inflectionCell
	{
		font-weight:bold;
		color:purple;
	}
</style>
<title>
       Generator Viewer - database generator 
</title>
</head>
<body>
<%
try
{
	response.setContentType("text/html; charset=UTF-8"); 
	TestGeneration g = new TestGeneration();
	
	InflectionEntry iE = new InflectionEntry(baseLexiconPointer,transliterated, baseNumber,
                           baseGender,basePerson, PGN, suffixFunction,
                           suffixStatus, binyan, tense, baseDefinitness,register, spelling, polarity, prefix, _type
                           ,surface,basePos,baseTransliteratedLItem,baseUndottedLItem,root,dottedLexiconItem,value,hebForeign);
	query = g.DispatchAction(action,iE,id);  // there is an action pending so add that inflection to removed inflections list
	g.deleteInflections();
	lexicon.generate.Generation gen = new lexicon.generate.Generation();
	gen.testGenerate(lexiconId);

	output = g.showInflections(lexiconId);

}
catch(Exception e)
{
	out.println(e.getMessage());
}
%>

<!--<%=query%>-->
<%
if (visitor != null) 
{
	%>
	User: <%=visitor.getName()%> 
	<%
} 
else 
{
	%>
	User: Guest
	<%
}
%>

<br/>
<!--
Action: <%=action%> </br>
id: <%=id%> </br>
surface: <%=surface%> </br>
basePos: <%=basePos%> </br>
baseTransliteratedLItem: <%=baseTransliteratedLItem%> </br>
baseUndottedLItem: <%=baseUndottedLItem%> </br>
dottedLexiconItem: <%=dottedLexiconItem%> </br>
value: <%=value%> </br>
hebForeign: <%=hebForeign%> </br>
baseLexiconPointer: <%=baseLexiconPointer%> </br>
transliterated: <%=transliterated%> </br>
baseNumber: <%=baseNumber%> </br>
baseGender: <%=baseGender%> </br>
basePerson: <%=basePerson%> </br>
PGN: <%=PGN%> </br>
suffixFunction: <%=suffixFunction%> </br>
suffixStatus: <%=suffixStatus%> </br>
binyan: <%=binyan%> </br>
tense: <%=tense%> </br>
baseDefinitness: <%=baseDefinitness%> </br>
register: <%=register%> </br>
spelling: <%=spelling%> </br>
polarity: <%=polarity%> </br>
prefix: <%=prefix%> </br>
type: <%=_type%> </br>
-->

<%=output%>

</body>
</html>