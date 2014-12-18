<%@ page 
	contentType ="text/html; charset=utf-8"
	pageEncoding="utf-8"
	import="lexicon.contents.pos.MultiWordVerbPhraseLexiconType"
	import="lexicon.contents.types.ItemType"
    import="java.util.List"
	import="java.util.StringTokenizer"
	import="java.sql.*"
	import ="java.net.URLDecoder"
%>
<jsp:useBean id="db" scope="request" class="autoFeed.AutoFeedBean" /> 
<jsp:setProperty name="db" property="*" />
<%
MultiWordVerbPhraseLexiconType mwVerb = new MultiWordVerbPhraseLexiconType();
ItemType item = (ItemType)session.getAttribute("item");
int id = Integer.parseInt((String)session.getAttribute("id"));
boolean newItem = Boolean.valueOf((String)session.getAttribute("newItem"));
List changeGroup = null;

if (!newItem && item.getMultiWordVerbPhrase() != null && id != 0) 
{
	mwVerb.open(id);
	changeGroup = mwVerb.getContents("verb_exception_type", "id", id);
	session.setAttribute("changeGroup", changeGroup);
}

if (request.getParameter("pos")!= null) 
{
	if (request.getParameter("pos").equals("multiWordVerbPhrase")) 
	{
		if (request.getParameter("multiWordVerbPhrase_inflectOrigin")!= null) 
		{
			mwVerb.setInflectOrigin(request.getParameter("verb_inflectOrigin").equals("1"));
		}
		if (request.getParameter("multiWordVerbPhrase_inflectInfinitive")!= null) 
		{
            mwVerb.setInflectInfinitive(request.getParameter("multiWordVerbPhrase_inflectInfinitive").equals("1"));
        }
		if (request.getParameter("multiWordVerbPhrase_inflectInfinitivel")!= null) 
		{
			mwVerb.setInflectInfinitivel(request.getParameter("multiWordVerbPhrase_inflectInfinitivel").equals("1"));
		}
		if (request.getParameter("multiWordVerbPhrase_inflectInfinitiveb")!= null) 
		{
            mwVerb.setInflectInfinitiveb(request.getParameter("multiWordVerbPhrase_inflectInfinitiveb").equals("1"));
        }
		if (request.getParameter("multiWordVerbPhrase_inflectPast")!= null) 
		{
			mwVerb.setInflectPast(request.getParameter("multiWordVerbPhrase_inflectPast").equals("1"));
		}
		if (request.getParameter("multiWordVerbPhrase_inflectBeinoni")!= null) 
		{
			mwVerb.setInflectBeinoni(request.getParameter("multiWordVerbPhrase_inflectBeinoni").equals("1"));
		}
		if (request.getParameter("multiWordVerbPhrase_inflectFuture")!= null) 
		{
			mwVerb.setInflectFuture(request.getParameter("multiWordVerbPhrase_inflectFuture").equals("1"));
		}
		if (request.getParameter("multiWordVerbPhrase_inflectImperative")!= null) 
		{
			mwVerb.setInflectImperative(request.getParameter("multiWordVerbPhrase_inflectImperative").equals("1"));
		}
		if (request.getParameter("multiWordVerbPhrase_inflectBeinoniConstruct")!= null) 
		{
			mwVerb.setInflectBeinoniConstruct(request.getParameter("multiWordVerbPhrase_inflectBeinoniConstruct").equals("1"));
		}
		if (request.getParameter("multiWordVerbPhrase_inflectBeinoniPossessive")!= null) 
		{
			mwVerb.setInflectBeinoniPossessive(request.getParameter("multiWordVerbPhrase_inflectBeinoniPossessive").equals("1"));
		}
		if (request.getParameter("multiWordVerbPhrase_inflectInfinitiveIndependent")!= null) 
		{
            mwVerb.setInflectInfinitiveIndependent(request.getParameter("multiWordVerbPhrase_inflectInfinitiveIndependent").equals("1"));
        }
		if (request.getParameter("multiWordVerbPhrase_foreign")!= null) 
		{
            mwVerb.setForeign(request.getParameter("multiWordVerbPhrase_foreign").equals("1"));
        }
		if (request.getParameter("multiWordVerbPhrase_feminine")!= null) 
		{
			mwVerb.setFeminine(request.getParameter("multiWordVerbPhrase_feminine"));
		}
		if (request.getParameter("multiWordVerbPhrase_valence")!= null) 
		{
			mwVerb.setValence(request.getParameter("multiWordVerbPhrase_valence"));
		}	
		if (request.getParameter("multiWordVerbPhrase_root")!= null) 
		{
			mwVerb.setRoot(request.getParameter("multiWordVerbPhrase_root").trim());
		}
		if (request.getParameter("multiWordVerbPhrase_binyan")!= null) 
		{
			mwVerb.setBinyan(request.getParameter("multiWordVerbPhrase_binyan"));
		}
		if (request.getParameter("multiWordVerbPhrase_type")!= null) 
		{
			mwVerb.setType(request.getParameter("multiWordVerbPhrase_type"));
		}
		if (request.getParameter("multiWordVerbPhrase_inflectionPattern")!= null) 
		{
			System.out.println(request.getParameter("multiWordVerbPhrase_inflectionPattern"));
			mwVerb.setInflectionPattern(request.getParameter("multiWordVerbPhrase_inflectionPattern"));
		}
		//if (request.getParameter("verb_ipSource")!= null) {
		//	verb.setIpSource(request.getParameter("verb_ipSource"));
		//} 
		//verb.setIpSource("");
		
		if(request.getParameter("actionButton")!=null)
		{
			 String transliterated= item.getTransliterated();
			 StringTokenizer st = new StringTokenizer(transliterated);
			 String transliterated1=st.nextToken();  // get first word
			 Connection connection=null;
			 String feedId="";
			 //String pos= verb.getMwPos();							 
			 ResultSet rs= null;
			 try 
			 {			
				connection = db.connect(); 	
				PreparedStatement pstatement = null;				
				
				/*if(pos.equals("adjective"))
				{	
					multiWordNoun.setType("AN");
					multiWordNoun.setDefiniteness("internal and external");							
					pstatement = connection.prepareStatement("SELECT *  FROM item, adjective where item.id=adjective.id and transliterated=? and pos='adjective' ");
					pstatement.setObject(1, transliterated1);				
				}
				else
				{*/
				
				pstatement = connection.prepareStatement("SELECT *  FROM item, verb where item.id=verb.id and transliterated=? and pos='verb' ");
				pstatement.setObject(1, transliterated1);
				
				//}		
				rs = pstatement.executeQuery();
	
				while (rs.next()) 
				{
					/*String gender = rs.getString("gender");
					String number = rs.getString("number");
					String feminine = rs.getString("feminine");
					String plural= rs.getString("plural");*/
					
					feedId = rs.getString("id");
					
					mwVerb.setBinyan(rs.getString("binyan"));
					mwVerb.setForeign((rs.getInt("hebForeign") == 1) ? true : false);
					mwVerb.setFeminine(rs.getString("feminine"));
					mwVerb.setValence(rs.getString("valence"));
					mwVerb.setRoot(URLDecoder.decode(rs.getString("root"),"ISO-8859-1"));
					//mwVerb.setRoot(rs.getString("root"));
					
					mwVerb.setInflectionPattern(rs.getString("inflectionPattern"));
					mwVerb.setIpSource(rs.getString("ipSource"));
					mwVerb.setInflectOrigin((rs.getInt("inflectOrigin") == 1) ? true : false);
					mwVerb.setInflectInfinitive((rs.getInt("inflectInfinitive") == 1) ? true : false);
					mwVerb.setInflectInfinitivel((rs.getInt("inflectInfinitivel") == 1) ? true : false);
					mwVerb.setInflectInfinitiveb((rs.getInt("inflectInfinitiveb") == 1) ? true : false);
					mwVerb.setInflectPast((rs.getInt("inflectPast") == 1) ? true : false);
					mwVerb.setInflectBeinoni((rs.getInt("inflectBeinoni")== 1) ? true : false);
					mwVerb.setInflectFuture((rs.getInt("inflectFuture")==1) ? true : false);
					mwVerb.setInflectImperative((rs.getInt("inflectImperative") == 1) ? true : false);
					mwVerb.setInflectBeinoniConstruct((rs.getInt("inflectBeinoniConstruct") == 1) ? true : false);
					mwVerb.setInflectBeinoniPossessive((rs.getInt("inflectBeinoniPossessive")==1) ? true : false);
					
					mwVerb.setInflectInfinitiveIndependent((rs.getInt("inflectInfinitiveIndependent")== 1) ? true : false);
					
					/*multiWordNoun.setGender(gender);
					multiWordNoun.setNumber(number); 	
					multiWordNoun.setFeminine(feminine);
					multiWordNoun.setPlural(plural);*/
					//multiWordNoun.setInflectPossessiveS(rs.getInt("inflectPossessiveS")==1);
					//multiWordNoun.setInflectPossessiveP(rs.getInt("inflectPossessiveP")==1);							
				}
				
				    // get exception
					pstatement = connection.prepareStatement("SELECT *  FROM verb_exception_type  where verb_exception_type.id=? and construct=0");
                    pstatement.setObject(1, feedId);
                    rs = pstatement.executeQuery();
                    while (rs.next()) 
                    {
                    	String exceptionUndotted = rs.getString("undotted");
                    	String exceptionTransliterated = rs.getString("transliterated");
						String exceptionDotted =  rs.getString("dotted");
						String exceptionRegister = rs.getString("register");
						String exceptionSpelling = rs.getString("spelling");
						String exceptionTense = rs.getString("tense");
						String exceptionGender= rs.getString("gender");
						String exceptionNumber= rs.getString("number");
						String exceptionPerson = rs.getString("person");
						String exceptionPgn = rs.getString("pgn");
						String exceptionBeinoniConstruct = rs.getString("beinoniConstruct");
						String exceptionBeinoniDefiniteness = rs.getString("beinoniDefiniteness");
						String exceptionInflectBeinoniPossessive = rs.getString("inflectBeinoniPossessive");
						String exceptionAction = rs.getString("action");
							
			 			pstatement = connection.prepareStatement("SELECT *  FROM   multiWordNoun_exception_type where multiWordNoun_exception_type.id=? and undotted=? and transliterated=? and dotted=? and register=? and spelling=? and tense=? and gender=? and number=? and person=? and pgn=? and beinoniConstruct=? and beinoniDefiniteness=? and inflectBeinoniPossessive=? and action=?");
						pstatement.setInt(1, id);
                        pstatement.setString(2, exceptionUndotted);
                        pstatement.setString(3, exceptionTransliterated);
                        pstatement.setString(4, exceptionDotted);
                        pstatement.setString(5, exceptionRegister);
                        pstatement.setString(6, exceptionSpelling);
                        pstatement.setString(7, exceptionTense);
                        pstatement.setString(8, exceptionGender);
                        pstatement.setString(9, exceptionNumber);
                        pstatement.setString(10, exceptionPerson);
                        pstatement.setString(11, exceptionPgn);
                        pstatement.setString(12, exceptionBeinoniConstruct);
                        pstatement.setString(13, exceptionBeinoniDefiniteness);
                        pstatement.setString(14, exceptionInflectBeinoniPossessive);
                        pstatement.setString(15, exceptionAction );

                        rs = pstatement.executeQuery();
                        if(!rs.next()) 
                        {
                        	pstatement = connection.prepareStatement("insert into multiWordVerbPhrase_exception_type (aid, id, undotted, transliterated, dotted, register, spelling, tense, gender, number, person, pgn, beinoniConstruct, beinoniDefiniteness, inflectBeinoniPossessive, action) values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
							String sqlInsert="insert into multiWordVerbPhrase_exception_type (aid, id, undotted, transliterated, dotted, register, spelling, tense, gender, number, person, pgn, beinoniConstruct, beinoniDefiniteness, inflectBeinoniPossessive, action) values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
							/*pstatement.setInt(1, 0);
							pstatement.setInt(2, id);
							pstatement.setString(3, exceptionUndotted);
							pstatement.setString(4, exceptionTransliterated);
							pstatement.setString(5, exceptionDotted);
							pstatement.setString(6, exceptionRegister);
							pstatement.setString(7, exceptionSpelling);
							pstatement.setString(8, exceptionGender);
							pstatement.setString(9, exceptionNumber);
							pstatement.setString(10, exceptionFeminine);
							pstatement.setString(11, exceptionPlural);
							pstatement.setString(12, exceptionAction );*/
							
							pstatement.setInt(1, 0);
							pstatement.setInt(2, id);
	                        pstatement.setString(3, exceptionUndotted);
	                        pstatement.setString(4, exceptionTransliterated);
	                        pstatement.setString(5, exceptionDotted);
	                        pstatement.setString(6, exceptionRegister);
	                        pstatement.setString(7, exceptionSpelling);
	                        pstatement.setString(8, exceptionTense);
	                        pstatement.setString(9, exceptionGender);
	                        pstatement.setString(10, exceptionNumber);
	                        pstatement.setString(11, exceptionPerson);
	                        pstatement.setString(12, exceptionPgn);
	                        pstatement.setString(13, exceptionBeinoniConstruct);
	                        pstatement.setString(14, exceptionBeinoniDefiniteness);
	                        pstatement.setString(15, exceptionInflectBeinoniPossessive);
	                        pstatement.setString(16, exceptionAction );
							
							pstatement.executeUpdate();
						}
					}
			connection.close();
			} 
		    catch (Exception e) 
		    {
				System.out.println(e.getMessage());
			}
		}
				
		if (newItem) 
		{
			mwVerb.add(item.getID());
		} 
		else 
		{
			mwVerb.update();
		}
		item.open(item.getID());
		mwVerb.open(item.getID());
	}
}
%>
<div id='multiWordVerbPhrase_div' style="display:none;">
<table>
	
	<tr>
		<td>
            <input  name="actionButton" type=Submit value="הזן תכונות אוטומטית" onClick="alert('יש להכנס שוב לפריט על מנת לראות את יוצאי הדופן במידה וקיימים'); return true;">						
        </td>
   </tr>
    
	<tr>
		<td>
			סוג:
		</td>
		<td>
			<select name='multiWordVerbPhrase_type'>
				<option value='halfFrozen' <%if (mwVerb.getType().equals("halfFrozen")) out.print("SELECTED");%>>חצי קפוא</option> 								
			</select>				
		</td>
	</tr>
	<tr>
		<td>
			צורן נקבה:
		</td>
		<td>
			<select name='multiWordVerbPhrase_feminine'>
				<option value='h' <%if (mwVerb.getFeminine().equals("h")) out.print("SELECTED");%>>ה</option>
				<option value='t' <%if (mwVerb.getFeminine().equals("t")) out.print("SELECTED");%>>ת</option>
				<option value='it' <%if (mwVerb.getFeminine().equals("it")) out.print("SELECTED");%>>ית</option>
				<option value='irrelevant' <%if (mwVerb.getFeminine().equals("irrelevant")) out.print("SELECTED");%>>לא רלוונטי</option>
				<option value='unspecified' <%if (mwVerb.getFeminine().equals("unspecified")) out.print("SELECTED");%>>לא מוגדר</option>								
			</select>				
		</td>
	</tr>
	<tr>
		<td>
			ערכיות:  
		</td>
		<td>
			<select name='multiWordVerbPhrase_valence'>
				<option value='unspecified' <%if (mwVerb.getValence().equals("unspecified")) out.print("SELECTED");%>>לא מוגדר</option>
				<option value='intransitiveWithPaul' <%if (mwVerb.getValence().equals("intransitiveWithPaul")) out.print("SELECTED");%>>פועל עומד שנוצר ממנו פעול</option>
				<option value='intransitiveWithoutPaul' <%if (mwVerb.getValence().equals("intransitiveWithoutPaul")) out.print("SELECTED");%>>פועל עומד שלא נוצר ממנו פעול</option>
				<option value='transitiveWithPaul' <%if (mwVerb.getValence().equals("transitiveWithPaul")) out.print("SELECTED");%>>פועל יוצא שנוצר ממנו פעול</option> 	
				<option value='transitiveWithoutPaul' <%if (mwVerb.getValence().equals("transitiveWithoutPaul")) out.print("SELECTED");%>>פועל יוצא שלא נוצר ממנו פעול</option> 
				<option value='intransitive' <%if (mwVerb.getValence().equals("intransitive")) out.print("SELECTED");%>>פועל עומד</option>
				<option value='transitive' <%if (mwVerb.getValence().equals("transitive")) out.print("SELECTED");%>>פועל יוצא</option> 
				<option value='ditransitive' <%if (mwVerb.getValence().equals("ditransitive")) out.print("SELECTED");%>>דיטרנזיטיבי</option> 	
				
			</select>				
		</td>
	</tr>	
	<tr>
		<td>
			שורש:
		</td>
		<td>
			<input type=text name='multiWordVerbPhrase_root' size=15 value='<%=mwVerb.getRoot().trim()%>'>			
		</td>
	</tr>	
	<tr>
		<td>
			בניין:  
		</td>
		<td>
			<select name='multiWordVerbPhrase_binyan'>
				<option value='unspecified'>לא מוגדר</option>
				<option value="Pa'al">בניין פעל</option>
				<option value="Nif'al">בניין נפעל</option> 	
				<option value="Pi'el">בניין פיעל</option> 
				<option value="Pu'al">בניין פועל</option>
				<option value="Hif'il">בניין הפעיל</option>
				<option value="Huf'al">בניין הופעל</option> 	
				<option value="Hitpa'el">בניין התפעל</option> 				
			</select>				
		</td>
	</tr>
	 <tr>
                <td>
                        האם מילה לועזית?
                </td>
                <td>
                        <select name='multiWordVerbPhrase_foreign'>
                                <option value='1' <%if (mwVerb.isForeign()) out.print("SELECTED");%>>כן</option>                        
                                <option value='0' <%if (!mwVerb.isForeign()) out.print("SELECTED");%>>לא</option>                       
                        </select>
                </td>
        </tr>
								
	 <tr>
                <td>
                        תבנית נטייה:
                </td>
                <td>
                <select name='multiWordVerbPhrase_inflectionPattern'>
                                <OPTION VALUE="1" <%if (mwVerb.getInflectionPattern().equals("1")) out.print("SELECTED");%>>(1) פעל - 1,2,3,6,7,8</OPTION>
                                <OPTION VALUE="2" <%if (mwVerb.getInflectionPattern().equals("2")) out.print("SELECTED");%>>(2) פעל - 4,13,15,16,17,26,27,29 </OPTION>
                                <OPTION VALUE="3"  <%if (mwVerb.getInflectionPattern().equals("3")) out.print("SELECTED");%>>(3) 5,10,11,14,18,28 - פעל</OPTION>
                                <OPTION value="4"  <%if (mwVerb.getInflectionPattern().equals("4")) out.print("SELECTED");%>>(4) 9 - פעל</OPTION>
                                <OPTION value="5"  <%if (mwVerb.getInflectionPattern().equals("5")) out.print("SELECTED");%>>(5) 12 - פעל</OPTION>
                                <OPTION value="6"  <%if (mwVerb.getInflectionPattern().equals("6")) out.print("SELECTED");%>>(6) 19,20 - פעל</OPTION>
                                <OPTION value="7"  <%if (mwVerb.getInflectionPattern().equals("7")) out.print("SELECTED");%>>(7) 21 - פעל</OPTION>
                                <OPTION value="8"  <%if (mwVerb.getInflectionPattern().equals("8")) out.print("SELECTED");%>>(8) פעל - 22 </OPTION>
                                <OPTION value="9"  <%if (mwVerb.getInflectionPattern().equals("9")) out.print("SELECTED");%>>(9) 23,24 - פעל</OPTION>
                                <OPTION value="10"  <%if (mwVerb.getInflectionPattern().equals("10")) out.print("SELECTED");%>>(10) 25,30 - פעל</OPTION>
                                <OPTION value="11"  <%if (mwVerb.getInflectionPattern().equals("11")) out.print("SELECTED");%>>(11) 31,32,33,34,35,36 - פעל</OPTION>
                                <OPTION value="12"  <%if (mwVerb.getInflectionPattern().equals("12")) out.print("SELECTED");%>>(12) 37 - פעל</OPTION>
                                <OPTION value="13"  <%if (mwVerb.getInflectionPattern().equals("13")) out.print("SELECTED");%>>(13) 38,40,41 - פעל</OPTION>
                                <OPTION value="14"  <%if (mwVerb.getInflectionPattern().equals("14")) out.print("SELECTED");%>>(14) פעל - 39 </OPTION>
                                <OPTION value="15"  <%if (mwVerb.getInflectionPattern().equals("15")) out.print("SELECTED");%>>(15) 42 - פעל</OPTION>
                                <OPTION value="16"  <%if (mwVerb.getInflectionPattern().equals("16")) out.print("SELECTED");%>>(16) 43,45 - פעל</OPTION>
                                <OPTION value="17"  <%if (mwVerb.getInflectionPattern().equals("17")) out.print("SELECTED");%>>(17) 44 - פעל</OPTION>
                                <OPTION value="18"  <%if (mwVerb.getInflectionPattern().equals("18")) out.print("SELECTED");%>>(18) 46 - פעל</OPTION>
                                <OPTION value="19"  <%if (mwVerb.getInflectionPattern().equals("19")) out.print("SELECTED");%>>(19) 47 - פעל</OPTION>
                                <OPTION value="20"  <%if (mwVerb.getInflectionPattern().equals("20")) out.print("SELECTED");%>>(20) פעל - 48 </OPTION>
                                <OPTION value="21"  <%if (mwVerb.getInflectionPattern().equals("21")) out.print("SELECTED");%>>(21) 49,50,51 - פעל</OPTION>
                                <OPTION value="22"  <%if (mwVerb.getInflectionPattern().equals("22")) out.print("SELECTED");%>>(22) 52 - פיעל</OPTION>
                                <OPTION value="23"  <%if (mwVerb.getInflectionPattern().equals("23")) out.print("SELECTED");%>>(23) 1,2,3,4,5,6,7,8,9,10,11,12,24,25,26 - פעל</OPTION>
                                <OPTION value="25"  <%if (mwVerb.getInflectionPattern().equals("25")) out.print("SELECTED");%>>(25) 13 - פיעל</OPTION>
                                <OPTION value="26"  <%if (mwVerb.getInflectionPattern().equals("26")) out.print("SELECTED");%>>(26) 14 - פיעל</OPTION>
                                <OPTION value="27"  <%if (mwVerb.getInflectionPattern().equals("27")) out.print("SELECTED");%>>(27) 15- פיעל</OPTION>
                                <OPTION value="28"  <%if (mwVerb.getInflectionPattern().equals("28")) out.print("SELECTED");%>>(28) 16 - פיעל</OPTION>
                                <OPTION value="29"  <%if (mwVerb.getInflectionPattern().equals("29")) out.print("SELECTED");%>>(29) 17 - פיעל</OPTION>
                                <OPTION value="30"  <%if (mwVerb.getInflectionPattern().equals("30")) out.print("SELECTED");%>>(30) 18 - פיעל</OPTION>
                                <OPTION value="31"  <%if (mwVerb.getInflectionPattern().equals("31")) out.print("SELECTED");%>>(31) 19,20,23 - פיעל</OPTION>
                                <OPTION value="32"  <%if (mwVerb.getInflectionPattern().equals("32")) out.print("SELECTED");%>>(32) 21 - פיעל</OPTION>
                                <OPTION value="33"  <%if (mwVerb.getInflectionPattern().equals("33")) out.print("SELECTED");%>>(33) 22- פיעל</OPTION>
                                <OPTION value="34"  <%if (mwVerb.getInflectionPattern().equals("34")) out.print("SELECTED");%>>(34) 27 - פיעל</OPTION>
                                <OPTION value="35"  <%if (mwVerb.getInflectionPattern().equals("35")) out.print("SELECTED");%>>(35) 28 - פיעל</OPTION>

                                <OPTION value="36"  <%if (mwVerb.getInflectionPattern().equals("36")) out.print("SELECTED");%>>(36) 1-21,23,25,26 - פועל</OPTION>
                                <OPTION value="37"  <%if (mwVerb.getInflectionPattern().equals("37")) out.print("SELECTED");%>>(37) 22 -פועל</OPTION>
                                <OPTION value="38"  <%if (mwVerb.getInflectionPattern().equals("38")) out.print("SELECTED");%>>(38) 24 - פועל</OPTION>
                                <OPTION value="39"  <%if (mwVerb.getInflectionPattern().equals("39")) out.print("SELECTED");%>>(39) 27,29- פועל</OPTION>
                                <OPTION value="40"  <%if (mwVerb.getInflectionPattern().equals("40")) out.print("SELECTED");%>>(40) 28- פועל</OPTION>
                                <OPTION value="41"  <%if (mwVerb.getInflectionPattern().equals("41")) out.print("SELECTED");%>>(41) 30 - פועל</OPTION>

                                <OPTION value="42"  <%if (mwVerb.getInflectionPattern().equals("42")) out.print("SELECTED");%>>(42) 1-8,10-23,26,27,33-43 - התפעל</OPTION>
                                <OPTION value="43"  <%if (mwVerb.getInflectionPattern().equals("43")) out.print("SELECTED");%>>(43) 9 -התפעל</OPTION>
                                <OPTION value="44"  <%if (mwVerb.getInflectionPattern().equals("44")) out.print("SELECTED");%>>(44) 24,25 - התפעל</OPTION>
                                <OPTION value="45"  <%if (mwVerb.getInflectionPattern().equals("45")) out.print("SELECTED");%>>(45) 28,29,32- התפעל</OPTION>
                                <OPTION value="46"  <%if (mwVerb.getInflectionPattern().equals("46")) out.print("SELECTED");%>>(46)30- התפעל</OPTION>
                                <OPTION value="47"  <%if (mwVerb.getInflectionPattern().equals("47")) out.print("SELECTED");%>>(47) 31 - התפעל</OPTION>

                                <OPTION value="48"  <%if (mwVerb.getInflectionPattern().equals("48")) out.print("SELECTED");%>>(48) 1-8,11,15-17,23-26,40 - הפעיל</OPTION>
                                <OPTION value="49"  <%if (mwVerb.getInflectionPattern().equals("49")) out.print("SELECTED");%>>(49) 9,10 -הפעיל</OPTION>
                                <OPTION value="50"  <%if (mwVerb.getInflectionPattern().equals("50")) out.print("SELECTED");%>>(50) 12-14 - הפעיל</OPTION>
                                <OPTION value="51"  <%if (mwVerb.getInflectionPattern().equals("51")) out.print("SELECTED");%>>(51) 18-22- הפעיל</OPTION>
                                <OPTION value="52"  <%if (mwVerb.getInflectionPattern().equals("52")) out.print("SELECTED");%>>(52)27-34- הפעיל</OPTION>
                                <OPTION value="53"  <%if (mwVerb.getInflectionPattern().equals("53")) out.print("SELECTED");%>>(53) 35-39 - הפעיל</OPTION>

                                <OPTION value="54"  <%if (mwVerb.getInflectionPattern().equals("54")) out.print("SELECTED");%>>(54)1-12,14,15,19,20,28- הופעל</OPTION>
                                <OPTION value="55"  <%if (mwVerb.getInflectionPattern().equals("55")) out.print("SELECTED");%>>(55) 13 - הופעל</OPTION>
                                <OPTION value="56"  <%if (mwVerb.getInflectionPattern().equals("56")) out.print("SELECTED");%>>(56)1-16-18- הופעל</OPTION>
                                <OPTION value="57"  <%if (mwVerb.getInflectionPattern().equals("57")) out.print("SELECTED");%>>(57) 21-27 - הופעל</OPTION>

                                <OPTION value="58"  <%if (mwVerb.getInflectionPattern().equals("58")) out.print("SELECTED");%>>(58)1-14,17-19- נפעל</OPTION>
                                <OPTION value="59"  <%if (mwVerb.getInflectionPattern().equals("59")) out.print("SELECTED");%>>(59) 15,16,25 -נפעל</OPTION>
                                <OPTION value="60"  <%if (mwVerb.getInflectionPattern().equals("60")) out.print("SELECTED");%>>(60)20-24- נפעל</OPTION>
                                <OPTION value="61"  <%if (mwVerb.getInflectionPattern().equals("61")) out.print("SELECTED");%>>(61) 30-31 - נפעל - נסוג</OPTION>     
				 <OPTION value="62"  <%if (mwVerb.getInflectionPattern().equals("62")) out.print("SELECTED");%>>(62)32-33 - נצוד </OPTION>                      
				<OPTION value='63'  <%if (mwVerb.getInflectionPattern().equals("63")) out.print("SELECTED");%>>(63) משקל נתפעל עבור התפעל - צורת עבר בלבד</OPTION>   
				 <OPTION value='64'  <%if (mwVerb.getInflectionPattern().equals("63")) out.print("SELECTED");%>>(64) משקל הדXX לפעלים בבניין התפעל עם שורש שמתחיל בד'</OPTION>                               
                        </select>
	</td>

        </tr>




	
	<tr>	
		<td>
			<input type=hidden name='multiWordVerbPhrase_ipSource' size=30 value='<%=mwVerb.getIpSource()%>'>
		</td>
	</tr>
	<!--
	<tr>
		<td>
			מקור בו מוצדקות ומוסברות תבניות הנטייה:  
		</td>
		<td>
			<input type=text name='verb_ipSource' size=30 value='<%=mwVerb.getIpSource()%>'>				
		</td>
	</tr>	
	-->
	<tr>
		<td>
			האם לייצר הטיית מקור?  
		</td>
		<td>
			<select name='multiWordVerbPhrase_inflectOrigin'>
				 <option value='0' <%if (!mwVerb.isInflectOrigin()) out.print("SELECTED");%>>לא</option>
				<option value='1' <%if (mwVerb.isInflectOrigin()) out.print("SELECTED");%>>כן</option>												
			</select>				
		</td>
	</tr>
	 <tr>
                <td>
                        האם לייצר הטיית שם פועל לא מוטה עם בכלם (לשמור,בשמור,כשמור,משמור)?
                </td>
                <td>
                        <select name='multiWordVerbPhrase_inflectInfinitive'>
                                 <option value='0' <%if (!mwVerb.isInflectInfinitive()) out.print("SELECTED");%>>לא</option>
                                <option value='1' <%if (mwVerb.isInflectInfinitive()) out.print("SELECTED");%>>כן</option>                        
                        </select>
                </td>
        </tr>

	<tr>
		<td>
			האם לייצר הטיות   ל + שם פועל + קניין?  
		</td>
		<td>
			<select name='multiWordVerbPhrase_inflectInfinitivel'>
				<option value='1' <%if (mwVerb.isInflectInfinitivel()) out.print("SELECTED");%>>כן</option>												
				<option value='0' <%if (!mwVerb.isInflectInfinitivel()) out.print("SELECTED");%>>לא</option>				
			</select>				
		</td>
	</tr>
	 <tr>
                <td>
                        האם לייצר הטיות   ב + שם פועל + קניין?
                </td>
                <td>
                        <select name='multiWordVerbPhrase_inflectInfinitiveb'>
                                <option value='1' <%if (mwVerb.isInflectInfinitiveb()) out.print("SELECTED");%>>כן</option>                   
                                <option value='0' <%if (!mwVerb.isInflectInfinitiveb()) out.print("SELECTED");%>>לא</option>                  
                        </select>
                </td>
        </tr>

	 <tr>
                <td>
                        האם יכול להתקיים שם פועל מוטה ללא בכלם (שומרו) ?
                </td>
                <td>
                        <select name='multiWordVerbPhrase_inflectInfinitiveIndependent'>
                                 <option value='0' <%if (!mwVerb.isInflectInfinitiveIndependent()) out.print("SELECTED");%>>לא</option>
                               <option value='1' <%if (mwVerb.isInflectInfinitiveIndependent()) out.print("SELECTED");%>>כן</option>
                        </select>
                </td>
        </tr>

	<tr>
		<td>
			האם לייצר הטיות עבר?  
		</td>
		<td>
			<select name='multiWordVerbPhrase_inflectPast'>
				<option value='1' <%if (mwVerb.isInflectPast()) out.print("SELECTED");%>>כן</option>												
				<option value='0' <%if (!mwVerb.isInflectPast()) out.print("SELECTED");%>>לא</option>				
			</select>				
		</td>
	</tr>
	<tr>
		<td>
			האם לייצר הטיות בינוני?  
		</td>
		<td>
			<select name='multiWordVerbPhrase_inflectBeinoni'>
				<option value='1' <%if (mwVerb.isInflectBeinoni()) out.print("SELECTED");%>>כן</option>												
				<option value='0' <%if (!mwVerb.isInflectBeinoni()) out.print("SELECTED");%>>לא</option>				
			</select>				
		</td>
	</tr>
	<tr>
		<td>
			האם לייצר הטיות עתיד?  
		</td>
		<td>
			<select name='multiWordVerbPhrase_inflectFuture'>
				<option value='1' <%if (mwVerb.isInflectFuture()) out.print("SELECTED");%>>כן</option>												
				<option value='0' <%if (!mwVerb.isInflectFuture()) out.print("SELECTED");%>>לא</option>				
			</select>				
		</td>
	</tr>
	<tr>
		<td>
			האם לייצר הטיות ציווי?  
		</td>
		<td>
			<select name='multiWordVerbPhrase_inflectImperative'>
				<option value='1' <%if (mwVerb.isInflectImperative()) out.print("SELECTED");%>>כן</option>												
				<option value='0' <%if (!mwVerb.isInflectImperative()) out.print("SELECTED");%>>לא</option>				
			</select>				
		</td>
	</tr>
	<tr>
		<td>
			האם לייצר לבינוני נטיות נסמך?  
		</td>
		<td>
			<select name='multiWordVerbPhrase_inflectBeinoniConstruct'>
				<option value='1' <%if (mwVerb.isInflectBeinoniConstruct()) out.print("SELECTED");%>>כן</option>												
				<option value='0' <%if (!mwVerb.isInflectBeinoniConstruct()) out.print("SELECTED");%>>לא</option>				
			</select>				
		</td>
	</tr>
	<tr>
		<td>
			האם לייצר לבינוני נטיות קניין?  
		</td>
		<td>
			<select name='multiWordVerbPhrase_inflectBeinoniPossessive'>
				<option value='1' <%if (mwVerb.isInflectBeinoniPossessive()) out.print("SELECTED");%>>כן</option>												
				<option value='0' <%if (!mwVerb.isInflectBeinoniPossessive()) out.print("SELECTED");%>>לא</option>				
			</select>				
		</td>
	</tr>								
		
</table>  
</div>		