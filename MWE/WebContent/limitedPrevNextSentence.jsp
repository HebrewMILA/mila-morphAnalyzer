<%@ page language="java"  %>

<%@ page import="java.util.StringTokenizer" %>

<%
request.setCharacterEncoding("UTF-8") ;
response.setContentType("text/xml;charset=UTF-8");
String resultStr="";
resultStr= (String)session.getAttribute("ppoutputAnalysis");
String pIdIn = request.getParameter("action");
System.out.println(pIdIn);
StringTokenizer st = new StringTokenizer(pIdIn,"|");

String action=st.nextToken();
String pId= st.nextToken();
String sId = st.nextToken();
String pmax="";
String smax="";
pmax=  st.nextToken();
smax=  st.nextToken();
try
{
			int pIdInt = Integer.parseInt(pId);
			int pIdMax = Integer.parseInt(pmax);
			int sIdMax = Integer.parseInt(smax);

			int sIdInt=Integer.parseInt(sId);
		
				
			if(action.equals("prev")){
				if(!sId.equals("1")){
				sIdInt = Integer.parseInt(sId) -1;
				sId = String.valueOf(sIdInt);
				}else{
				if(pIdInt > 1)
				pIdInt = pIdInt -1;
				pId = String.valueOf(pIdInt);

				}	
			}else{ //action = next
				if(((sIdInt + 1 )> sIdMax) && ((pIdInt + 1 ) <= pIdMax ) ){
				sId ="1";
				pIdInt = pIdInt +1;
                                pId =  String.valueOf(pIdInt);
				}
				else if((sIdInt+1) == sIdMax){
				  sIdInt =Integer.parseInt(sId) +1;
                                        sId = String.valueOf(sIdInt); 
				}				

				else if((sIdInt + 1 )< sIdMax){
					sIdInt =Integer.parseInt(sId) +1;
					sId = String.valueOf(sIdInt);
				}			
			}

			out.clearBuffer();
			int index= resultStr.indexOf("<article");
			String newLine = "<corpus pId=\"" + pId + "\"  sId=\""+ sId + "\" email=\"mila@cs.technion.ac.il\" maintainer=\"Dalia Bojan\" name=\"Analysis Results\">";
			resultStr =  "<?xml-stylesheet type=\"text/xsl\" href=\"limited.xsl\"?>" +   newLine + resultStr.substring(index);  
			out.write(resultStr);
       }
       catch (Exception e) {
          out.write("קרתה טעות - התחל מחדש"); 
       }

%>
