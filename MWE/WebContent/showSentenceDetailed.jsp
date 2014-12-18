<%@ page language="java"%>

<%@ page import="java.io.*" %>
<%@ page import="java.util.StringTokenizer" %>
<%@ page import= "java.io.OutputStreamWriter"%>
<%@ page import= "java.io.BufferedWriter"%>
<%@ page import= "java.io.FileOutputStream"%>
<%@ page import= "java.io.BufferedReader"%>
<%@ page import= "java.io.FileInputStream"%>
<%@ page import= "java.io.InputStreamReader"%>
<%@ page import= "java.io.StringReader"%>
<%@ page import= "java.net.URLDecoder"%>
<%@ page import= "org.w3c.dom.Document"%>
<%@ page import= "org.xml.sax.SAXException"%>
<%@ page import= "javax.xml.parsers.*"%>
<%@ page import= "javax.xml.xpath.*"%>
<%@ page import= "org.xml.sax.InputSource"%>

<%
request.setCharacterEncoding("UTF-8") ;
response.setContentType("text/xml;charset=UTF-8");
String resultStr="";
resultStr= (String)session.getAttribute("ppoutputAnalysis");
String pIdIn = request.getParameter("pid");
System.out.println(pIdIn);
StringTokenizer st = new StringTokenizer(pIdIn,"|");

String pId= st.nextToken();
String sId = st.nextToken();

try
{

			out.clearBuffer();
			 int index= resultStr.indexOf("<article");
                        String newLine = "<corpus pId=\"" + pId + "\"  sId=\""+ sId + "\" email=\"mila@cs.technion.ac.il\" maintainer=\"Dalia Bojan\" name=\"Analysis Results\">";
                        resultStr =  "<?xml-stylesheet type=\"text/xsl\" href=\"detailed.xsl\"?>" +   newLine + resultStr.substring(index);
			out.write(resultStr);
       }
       catch (Exception e) {
          out.write(""); 
       }

%>
