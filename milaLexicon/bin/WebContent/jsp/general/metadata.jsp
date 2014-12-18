<%
response.setHeader("cache-control","no-store"); //Directs caches not to store the page under any circumstance
response.setHeader("Cache-Control","no-cache"); //Forces caches to obtain a new copy of the page from the origin server
response.setHeader("Pragma","no-cache"); //HTTP 1.0 backward compatibility
response.setDateHeader("Expires" , 0); //Causes the proxy cache to see the page as "stale"
%>
<meta http-equiv="content-type" content="text/html;charset=UTF-8">
<meta name="Keywords" content="nlp, root, pattern, hebrew">
<meta name="Description" content="NLP options">
<!--meta name="Creators" content="Danny Shacham"-->
<link rel="stylesheet" href="../jsp/general/generic2.css" type="text/css" />

