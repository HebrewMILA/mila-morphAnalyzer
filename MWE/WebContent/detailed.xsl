<xsl:stylesheet xmlns:xsl ="http://www.w3.org/1999/XSL/Transform" xmlns:myObject="java:XSLOBJ" version ="1.0">

 
 <xsl:template match="/">
   <html>
<head>
<title>Hebrew Multi Word Expression Morphological Analyzer -מנתח מורפולוגי לביטויים בעברית</title>
<style>

body 
{ 
	font-family: Verdana, Geneva, Arial, Helvetica, sans-serif;
	font-size: 27px;
	/*background-color: #FFF8DC;*/
	text-align: justify;
	color: black; 
	margin: 0px;
}

h3 { color: #336699; font-size: 26px; font-weight: bold; font-variant: normal; margin: 0px; padding: 5px 0 10px 0; }
p { margin: 0px; padding: 0 0 10px 0; }

#Section 
{
        position: relative;
        top: 0%;
        margin: 0px;
        /*padding: 0% 2% 2% 2%;*/
        width: 100%;
}

#Section td 
{
        font-family: Arial, Helvetica, sans-serif;
        font-size: 93%;
}


#SectionHeader 
{
        background:#2580A2;
        position: relative;
        overflow: hidden;
        /*margin: 2px  0 2px 0;
        padding: 0px;*/
        height: 82px;
        width: 100%;
        border-left: 1px solid #CCCCCC;
        border-right: 1px solid #CCCCCC;
}


#SectionMenuBackground 
{
        font-family: Arial, Helvetica, sans-serif;
        font-size: 20px;
		text-align: left;
        color: white;
		height: 82px;
        /*width: 93%;*/
        /*margin: 10px;
        padding: 10px;*/
}

#SectionMenuBackground a
{
        font-size: 100%;
        text-align: left;
        color: white;
}

#Footer 
{
        clear: both;        
		position: relative;
        overflow: hidden;        
		font-family: Verdana, Geneva, Arial, Helvetica, sans-serif;
        font-size: 12px;
        text-align: center;
        margin: 0px;
		margin-top:10px;
        padding: 0px;
        width: 100%;
}
</style>

</head>
<body>

<form  action="showSentence.jsp">

<div id="Section">
	<div id="SectionHeader">
		<div id="SectionMenuBackground">
			<a href="http://www.mila.cs.technion.ac.il/mila/eng/index.html">
				<img width="878" height="82" border="0" alt="MILA: Knowledge Center for Processing Hebrew" src="logo.png"></img>
			</a>
			<!--
			<img src="images/demos_header.gif" width="100%" height="20"></img>
			<a href='http://yeda.cs.technion.ac.il:8088/MWE/index.html'>חזור</a> |
			<a href='MorphologicalAnalyzer-ReleaseNotes-1.1.doc'> תיעוד מנתח מורפולוגי</a>|
			<a href='http://www.mila.cs.technion.ac.il/'>אתר מילה </a> 
			-->
		</div>
	</div>





<center><h3>פלט המנתח המורפולוגי לביטויים בעברית - תצוגה מפורטת</h3></center>

<center>
<table dir="rtl" >

<tr>
<td align="center" >
 <xsl:variable name="inputpId"><xsl:value-of select="corpus/@pId"/>
                </xsl:variable>

 <xsl:variable name="inputsId"><xsl:value-of select="corpus/@sId"/>
                </xsl:variable>



 <xsl:for-each select="corpus/article/paragraph">
	<xsl:variable name="pId"><xsl:value-of select="@id"/>
                </xsl:variable>
	<xsl:for-each select="sentence">
	 <xsl:variable name="sId"><xsl:value-of select="@id"/>
                </xsl:variable>
	<xsl:if test="($pId=$inputpId and  $sId=$inputsId)">
	<bold><font color="#FF8C00" >פיסקה # <xsl:value-of select="$pId"/></font ></bold><xsl:text >      :      </xsl:text>
  	<bold><font color="#FF8C00" >משפט # <xsl:value-of select="$sId"/></font ></bold>
 		<p>
		<xsl:for-each select="token">
		 <xsl:variable name="tId"><xsl:value-of select="@id"/>
                		</xsl:variable>
		<xsl:variable name="url"><xsl:value-of select="@surface"/>
		</xsl:variable>
		<xsl:variable name="invertedCommas">"</xsl:variable>
		 <xsl:variable name="apos">'</xsl:variable>
		<xsl:choose>
		<xsl:when test="@surface='.' or  @surface='־'  or  @surface='?' or @surface=',' or @surface=':' or  @surface='`' or @surface='~' or  @surface=';' or @surface='!'  or  @surface='(' or  @surface=')' or @surface='-' or  @surface='_' or @surface='+'or  @surface='=' or @surface='/'  ">
		<xsl:value-of select="@surface"/>	
		</xsl:when>
		<xsl:when test="@surface=$apos or @surface=$invertedCommas">
		 <xsl:value-of select="@surface"/>
		</xsl:when>		
		<xsl:otherwise> 
		<font size="5"><a href="#{$tId}"><xsl:value-of select="@surface"/></a></font><xsl:text >            </xsl:text>
		 </xsl:otherwise>
                </xsl:choose>
</xsl:for-each>
		</p>
	 <xsl:variable name="pmax"><xsl:value-of select="count(//corpus/article/paragraph)"/>
                </xsl:variable>
	  <xsl:variable name="smax"><xsl:value-of select="count(//corpus/article/paragraph[@id=$pId]/sentence)"/>
                </xsl:variable>
	<p>
<input type="button" onClick="location='http://yeda.cs.technion.ac.il:8088/MWE/index.html'"  value="התחל"></input>
  <xsl:text >            </xsl:text>
<input type="button" onClick="location='detailedPrevNextSentence.jsp?action=prev|{$pId}|{$sId}|{$pmax}|{$smax}'"  value="הקודם"></input>
 <xsl:text >            </xsl:text>
<input type="button" onClick="location='detailedPrevNextSentence.jsp?action=next|{$pId}|{$sId}|{$pmax}|{$smax}'"  value="הבא"></input>
 <xsl:text >            </xsl:text>
<input type="button" onClick="location='showAll.jsp'"  value="הכל"></input>
 <xsl:text >            </xsl:text>
<input type="button" 
onClick="location='http://yeda.cs.technion.ac.il:8088/MWE/showSentenceLimited.jsp?pid={$pId}|{$sId}'"  value="מקוצר"></input>
	</p>
	</xsl:if>
	</xsl:for-each>
 </xsl:for-each>



 <xsl:for-each select="corpus/article/paragraph">
	<xsl:variable name="pId"><xsl:value-of select="@id"/>
                </xsl:variable>
	<xsl:for-each select="sentence">
	 <xsl:variable name="sId"><xsl:value-of select="@id"/>
                </xsl:variable>
	<xsl:if test="$pId=$inputpId  and $sId=$inputsId">
 		<p>
		<xsl:for-each select="token">
		 <xsl:variable name="tId"><xsl:value-of select="@id"/>
                		</xsl:variable>
		<xsl:variable name="token" select="@surface"></xsl:variable>
		<xsl:variable name="invertedCommas">"</xsl:variable>
		 <xsl:variable name="apos">'</xsl:variable>
		<xsl:choose>
		<xsl:when test="@surface='.' or  @surface='־'  or  @surface='?' or @surface=',' or @surface=':' or  @surface='`' or @surface='~' or  @surface=';' or @surface='!'  or  @surface='(' or  @surface=')' or @surface='-' or  @surface='_' or @surface='+'or  @surface='=' or @surface='/'  ">
		<xsl:value-of select="@surface"/>
		 <xsl:element name="BR"/>	
		</xsl:when>
		<xsl:when test="@surface=$apos or @surface=$invertedCommas">
		 <xsl:value-of select="@surface"/>
		 <xsl:element name="BR"/>
		</xsl:when>		
		<xsl:otherwise><font size="5"> <B>
		<a name="#{$tId}"><xsl:value-of select="@surface"/></a></B></font><xsl:text >            </xsl:text>
		

<table BGCOLOR="white"  align="center"   cellpadding="2%" cellspacing="2%" border="2%">
	<tr BGCOLOR="#FFCC99">
		<td width="1%" align="center"><b>prefix</b></td>
		<td width="1%"  align="center"><b>lexicon <xsl:element name="BR"/> item</b></td>
		<td width="1%"  align="center"><b>lexicon <xsl:element name="BR"/> id</b><xsl:element name="BR"/>link to<xsl:element name="BR"/> inflections </td>
		<td width="1%"  align="center"><b>score</b></td>
		<td width="2%"  align="center"><b>pos</b></td>
		<td width="1%"  align="center"><b>prefix<xsl:element name="BR"/>function</b></td>
		<td width="1%"  align="center"><b>gender</b></td>
		<td width="1%"  align="center"><b>number</b></td>
		<td width="1%"  align="center"><b>construct</b></td>
		<td width="1%"  align="center"><b>ה <xsl:element name="BR"/>definiteness<xsl:element name="BR"/>interrogative<xsl:element name="BR"/>subordinating</b></td>
		<td width="1%"  align="center"><b>person</b></td>
		<td width="1%"  align="center"><b>suffix <xsl:element name="BR"/> gender<xsl:element name="BR"/> number<xsl:element name="BR"/>person</b></td>
		<td width="1%"  align="center"><b>tense</b></td>
		<td width="1%"  align="center"><b>binyan</b></td>
		<td width="2%"  align="center"><b>root</b></td>
		<td width="1%"  align="center"><b>type</b></td>
		<td width="1%"  align="center"><b>polarity</b></td>
		<td width="1%"  align="center"><b>value</b></td>
		<td width="1%"  align="center"><b>register</b></td>
		<td width="1%"  align="center"><b>spelling</b></td>
	</tr>
		
		<xsl:for-each select="analysis">
		 <xsl:sort select="@score" data-type="number" order="descending"/>
		<tr>

		<xsl:choose>
		<xsl:when test="prefix">
		<xsl:variable name="myprefix">
		<xsl:for-each select="prefix">
		<xsl:value-of select="concat(@surface,'')"/>
		</xsl:for-each>
		</xsl:variable>
		<xsl:variable name="prefixLength"  select="string-length($myprefix)" ></xsl:variable>
		<xsl:choose>
		<xsl:when test="base/MWE">
		<xsl:choose>
		<xsl:when test="base/MWE/@type='person'">
		<td  align="right" BGCOLOR="#ADFF2F"><b><xsl:for-each select="prefix"><xsl:value-of select="concat(@surface,'')"/><xsl:element name="BR"/></xsl:for-each></b></td><td align="right" BGCOLOR="#ADFF2F">
<xsl:value-of select="base/@lexiconItem"/> <xsl:element name="BR"/>(<xsl:value-of select="base/MWE/@multiWord"/>)
		</td>
		</xsl:when>
<xsl:otherwise>
		<td  align="right" BGCOLOR="#ADFF2F"><b><xsl:for-each select="prefix"><xsl:value-of select="concat(@surface,'')"/><xsl:element name="BR"/></xsl:for-each></b></td><td align="right" BGCOLOR="#ADFF2F">
<xsl:value-of select="base/@lexiconItem"/> <xsl:element name="BR"/>
		</td>
		</xsl:otherwise>
		</xsl:choose>
		</xsl:when>
		<xsl:when test="base/preposition/@expansion and  base/@lexiconItem">
		<td  align="right" BGCOLOR="#FFCCFF"><b><xsl:for-each select="prefix"><xsl:value-of select="concat(@surface,'')"/><xsl:element name="BR"/></xsl:for-each></b></td><td align="right" BGCOLOR="#FFFFCC">
<xsl:value-of select="base/@lexiconItem"/> <xsl:element name="BR"/>(<xsl:value-of select="base/preposition/@expansion"/>)
		</td>
		</xsl:when>
		<xsl:when test="base/title/@expansion and  base/@lexiconItem">
		<td  align="right" BGCOLOR="#FFCCFF"><b><xsl:for-each select="prefix"><xsl:value-of select="concat(@surface,'')"/><xsl:element name="BR"/></xsl:for-each></b></td><td align="right" BGCOLOR="#FFFFCC">
<xsl:value-of select="base/@lexiconItem"/> <xsl:element name="BR"/>(<xsl:value-of select="base/title/@expansion"/>)
		</td>
		</xsl:when>
		<xsl:when test="base/adverb/@expansion and  base/@lexiconItem">
		<td  align="right" BGCOLOR="#FFCCFF"><b><xsl:for-each select="prefix"><xsl:value-of select="concat(@surface,'')"/><xsl:element name="BR"/></xsl:for-each></b></td><td align="right" BGCOLOR="#FFFFCC">
<xsl:value-of select="base/@lexiconItem"/> <xsl:element name="BR"/>(<xsl:value-of select="base/adverb/@expansion"/>)
		</td>
		</xsl:when>
		<xsl:when test="base/adjective/@expansion and  base/@lexiconItem">
		<td  align="right" BGCOLOR="#FFCCFF"><b><xsl:for-each select="prefix"><xsl:value-of select="concat(@surface,'')"/><xsl:element name="BR"/></xsl:for-each></b></td><td align="right" BGCOLOR="#FFFFCC">
<xsl:value-of select="base/@lexiconItem"/> <xsl:element name="BR"/>(<xsl:value-of select="base/adjective/@expansion"/>)
		</td>
		</xsl:when>
		<xsl:when test="base/noun/@expansion and  base/@lexiconItem">
		<td  align="right" BGCOLOR="#FFCCFF"><b><xsl:for-each select="prefix"><xsl:value-of select="concat(@surface,'')"/><xsl:element name="BR"/></xsl:for-each></b></td><td align="right" BGCOLOR="#FFFFCC">
<xsl:value-of select="base/@lexiconItem"/> <xsl:element name="BR"/>(<xsl:value-of select="base/noun/@expansion"/>)
		</td>
		</xsl:when>
		<xsl:when test="base/conjunction/@expansion and  base/@lexiconItem">
		<td  align="right" BGCOLOR="#FFCCFF"><b><xsl:for-each select="prefix"><xsl:value-of select="concat(@surface,'')"/><xsl:element name="BR"/></xsl:for-each></b></td><td align="right" BGCOLOR="#FFFFCC">
<xsl:value-of select="base/@lexiconItem"/> <xsl:element name="BR"/>(<xsl:value-of select="base/conjunction/@expansion"/>)
		</td>
		
		</xsl:when>
		<xsl:when test="base/@lexiconItem">
		<td  align="center" BGCOLOR="#FFCCFF"><b>
		<xsl:choose> 
		<xsl:when test="prefix/@multiWord='true'">
		
		ביטוי 2
		<xsl:element name="BR"/>
		<xsl:element name="BR"/>
		</xsl:when>
		<xsl:otherwise>
		
		</xsl:otherwise>
		</xsl:choose>
		<xsl:for-each select="prefix"><xsl:value-of select="concat(@surface,'')"/><xsl:element name="BR"/></xsl:for-each></b></td><td align="right" BGCOLOR="#FFFFCC">
<xsl:value-of select="base/@lexiconItem"/> 
		</td>
		</xsl:when>





		<xsl:otherwise>
		<td  align="right" BGCOLOR="#FFCCFF"><b><xsl:for-each select="prefix"><xsl:value-of select="concat(@surface,'')"/><xsl:element name="BR"/></xsl:for-each></b></td><td align="right" BGCOLOR="#FFFFCC"><xsl:value-of select="substring($token,$prefixLength+1)"/>
		</td>
		<td align="center"><xsl:text >- </xsl:text></td>
		</xsl:otherwise>
		</xsl:choose>
		

		</xsl:when>
		<xsl:when test="base/MWE">
		<xsl:choose>
		<xsl:when test="base/MWE/@type='person'">
		<td  align="center" BGCOLOR="#FFCCFF"> <xsl:text></xsl:text></td><td  BGCOLOR="#E3E4FA" align="right">
		<xsl:choose>
		<xsl:when test="base/@dottedLexiconItem">
		<xsl:value-of select="base/@dottedLexiconItem"/>
		</xsl:when>
		<xsl:otherwise>
		<xsl:value-of select="base/@lexiconItem"/>
		</xsl:otherwise>
		</xsl:choose>
		<xsl:element name="BR"/>
		(<xsl:value-of select="base/MWE/@multiWord"/>)
		</td>
		</xsl:when>
		<xsl:otherwise>
		<td  align="center" BGCOLOR="#FFCCFF"> <xsl:text></xsl:text></td><td  BGCOLOR="#FDEEF4" align="right">
		<xsl:choose>
		<xsl:when test="base/@dottedLexiconItem">
		<xsl:value-of select="base/@dottedLexiconItem"/>
		</xsl:when>
		<xsl:otherwise>
		<xsl:value-of select="base/@lexiconItem"/>
		</xsl:otherwise>
		</xsl:choose>
		</td>
		</xsl:otherwise>
		</xsl:choose>
		</xsl:when>
		<xsl:when test="base/noun/@expansion and base/@dottedLexiconItem">
		<td  align="center" BGCOLOR="#FFCCFF"> <xsl:text></xsl:text></td><td  BGCOLOR="#FFFFCC" align="right"><xsl:value-of select="base/@dottedLexiconItem"/>
		<xsl:element name="BR"/>
		(<xsl:value-of select="base/noun/@expansion"/>)
		</td>
		</xsl:when>
		<xsl:when test="base/adjective/@expansion and base/@dottedLexiconItem">
		<td  align="center" BGCOLOR="#FFCCFF"> <xsl:text></xsl:text></td><td  BGCOLOR="#FFFFCC" align="right"><xsl:value-of select="base/@dottedLexiconItem"/>
		<xsl:element name="BR"/>
		(<xsl:value-of select="base/adjective/@expansion"/>)
		</td>
		</xsl:when>
		<xsl:when test="base/title/@expansion and base/@dottedLexiconItem">
		<td  align="center" BGCOLOR="#FFCCFF"> <xsl:text></xsl:text></td><td  BGCOLOR="#FFFFCC" align="right"><xsl:value-of select="base/@dottedLexiconItem"/>
		<xsl:element name="BR"/>
		(<xsl:value-of select="base/title/@expansion"/>)
		</td>
		</xsl:when>
		<xsl:when test="base/adverb/@expansion and base/@dottedLexiconItem">
		<td  align="center" BGCOLOR="#FFCCFF"> <xsl:text></xsl:text></td><td  BGCOLOR="#FFFFCC" align="right"><xsl:value-of select="base/@dottedLexiconItem"/>
		<xsl:element name="BR"/>
		(<xsl:value-of select="base/adverb/@expansion"/>)
		</td>
		</xsl:when>
		<xsl:when test="base/preposition/@expansion and base/@dottedLexiconItem">
		<td  align="center" BGCOLOR="#FFCCFF"> <xsl:text></xsl:text></td><td  BGCOLOR="#FFFFCC" align="right"><xsl:value-of select="base/@dottedLexiconItem"/>
		<xsl:element name="BR"/>
		(<xsl:value-of select="base/preposition/@expansion"/>)
		</td>
		</xsl:when>
		<xsl:when test="base/conjunction/@expansion and base/@dottedLexiconItem">
		<td  align="center" BGCOLOR="#FFCCFF"> <xsl:text></xsl:text></td><td  BGCOLOR="#FFFFCC" align="right"><xsl:value-of select="base/@dottedLexiconItem"/>
		<xsl:element name="BR"/>
		(<xsl:value-of select="base/conjunction/@expansion"/>)
		</td>
		</xsl:when>
		<xsl:when test="base/@dottedLexiconItem">
		<td  align="center" BGCOLOR="#FFCCFF"> <xsl:text></xsl:text></td><td  BGCOLOR="#FFFFCC" align="right"><xsl:value-of select="base/@dottedLexiconItem"/>
		</td>
		</xsl:when>

		
		<xsl:when test="base/@lexiconItem">
		<td  align="center" BGCOLOR="#FFCCFF"> <xsl:text></xsl:text></td><td  BGCOLOR="#FFFFCC" align="right"><xsl:value-of select="base/@lexiconItem"/></td>
		</xsl:when>
		</xsl:choose>
		
		<xsl:choose>
		<xsl:when test="base/@lexiconPointer!='0'">
			<xsl:variable name="lexiconPointer" select="base/@lexiconPointer">
			</xsl:variable>

			<xsl:choose>
			<xsl:when test="base/noun">
			<td align="center">

			<a title="show noun inflections" href="nounInflections.jsp?lexiconId={$lexiconPointer}"> <xsl:value-of select="base/@lexiconPointer"/></a>
			</td>
			</xsl:when>
			<xsl:when test="base/MWE">
                        <td align="center">

                        <a title="show MultiWord inflections" href="multiWordInflections.jsp?lexiconId={$lexiconPointer}"> <xsl:value-of select="base/@lexiconPointer"/></a>
                        </td>
                        </xsl:when>
			 <xsl:when test="base/adjective">
                        <td align="center">

                        <a title="show adjective inflections" href="nounInflections.jsp?lexiconId={$lexiconPointer}"> <xsl:value-of select="base/@lexiconPointer"/></a>
                        </td>
                        </xsl:when>
	     <xsl:when test="base/participle">
                        <td align="center">

                        <a title="show verb and beinoni inflections" href="verbInflections.jsp?lexiconId={$lexiconPointer}"> <xsl:value-of select="base/@lexiconPointer"/></a>
                        </td>
                        </xsl:when>
	     <xsl:when test="base/verb">
                        <td align="center">

                        <a title="show verb inflections" href="verbInflections.jsp?lexiconId={$lexiconPointer}"> <xsl:value-of select="base/@lexiconPointer"/></a>
                        </td>
                        </xsl:when>

			 <xsl:when test="base/preposition">
                        <td align="center">

                        <a title="show title inflections" href="prepositionInflections.jsp?lexiconId={$lexiconPointer}"> <xsl:value-of select="base/@lexiconPointer"/></a>
                        </td>
                        </xsl:when>
			 <xsl:when test="base/numeral">
                        <td align="center">

                        <a title="show numeral inflections" href="numeralInflections.jsp?lexiconId={$lexiconPointer}"> <xsl:value-of select="base/@lexiconPointer"/></a>
                        </td>
                        </xsl:when>

			<xsl:otherwise>
			
			<td  align="center">
				<xsl:value-of select="base/@lexiconPointer"/>
			</td>
			</xsl:otherwise>
			</xsl:choose>
		</xsl:when>
		
		<xsl:when test="base/numeral or base/numberExpression">
		<td align="center"><xsl:text >- </xsl:text></td>
		<td align="center"><xsl:text >-</xsl:text></td>
		<td align="center"><xsl:text >-</xsl:text></td>
		</xsl:when>
		<xsl:when test="not(base/numeral) and not(base/numberExpression) and (base/@lexiconPointer='0')">
			<td  align="center">
				לא
		
		<xsl:element name="BR"/>
				בלקסיקון
			</td>
		</xsl:when>

		<xsl:when test=" base/MWE">
			<td  align="center">
				חלק
		
		<xsl:element name="BR"/>
				מביטוי
			</td>
		</xsl:when>

		</xsl:choose>


		<xsl:choose>
		 <xsl:when test="@score='0.3333333333333333'">
              		  <td align="center" BGCOLOR="#F88017">
              		 <b> 0.33</b>
              		  </td>
                		</xsl:when>
		 <xsl:when test="@score='0.0'">
              		  <td align="center" >
              		  0.0
              		  </td>
                		</xsl:when>
		<xsl:otherwise>
		
		
		  <td align="center" BGCOLOR="#F88017" >
                 		<b><xsl:value-of select="@score"/></b>
                		</td>
		

		</xsl:otherwise>
	        	</xsl:choose>

		<xsl:if test="prefix and not(base)">
		<td align="center"><xsl:text >- </xsl:text></td>
		<td align="center"><xsl:text >- </xsl:text></td>
		<td align="center"><xsl:text >- </xsl:text></td>
		<td align="center"><xsl:text >- </xsl:text></td>
		<td align="center"><xsl:text >- </xsl:text></td>
		<td align="center"><xsl:text >- </xsl:text></td>
		<td align="center"><xsl:text >- </xsl:text></td>
		<td align="center"><xsl:text >- </xsl:text></td>
		<td align="center"><xsl:text >- </xsl:text></td>
		<td align="center"><xsl:text >- </xsl:text></td>
		<td align="center"><xsl:text >- </xsl:text></td>
		<td align="center"><xsl:text >- </xsl:text></td>
		<td align="center"><xsl:text >- </xsl:text></td>
		<td align="center"><xsl:text >- </xsl:text></td>
		<td align="center"><xsl:text >- </xsl:text></td>
		<td align="center"><xsl:text >- </xsl:text></td>
		</xsl:if>
		

		<xsl:if test="base/MWE">
		
		<xsl:choose>
		<xsl:when test="base/@lexiconPointer">
		</xsl:when>		
		</xsl:choose>
		
		<td align="center" bgcolor="#ADFF2F"><b>
		ביטוי<xsl:element name="BR"/><xsl:value-of select="base/MWE/@id"/>
		<xsl:element name="BR"/>
		<xsl:choose>
		<xsl:when test="base/MWE/@pos='noun'">
		שם<xsl:element name="BR"/>עצם
		</xsl:when>
		<xsl:when test="base/MWE/@pos='conjunction'">
		מילת<xsl:element name="BR"/>חיבור
		</xsl:when>
		<xsl:when test="base/MWE/@pos='adverb'">
		תואר<xsl:element name="BR"/>הפועל
		</xsl:when>
		<xsl:when test="base/MWE/@pos='adjective'">
		שם<xsl:element name="BR"/>תואר
		</xsl:when>
		<xsl:when test="base/MWE/@pos='properName'">
		שם<xsl:element name="BR"/>פרטי
		<xsl:element name="BR"/>
		<xsl:choose>
		<xsl:when test="base/MWE/@type='person'">
		(אדם)
		</xsl:when>
		<xsl:when test="base/MWE/@type='town'">
		(יישוב)
		</xsl:when>
		<xsl:when test="base/MWE/@type='organization'">
		(ארגון)
		</xsl:when>
		</xsl:choose>
		</xsl:when>
		</xsl:choose>
		</b></td>
		<xsl:choose>
		<xsl:when test="base/MWE/@gender='masculine'">
		<td align="center" >זכר</td>
		</xsl:when>
		<xsl:when test="base/MWE/@gender='feminine'">
		<td align="center" >נקבה</td>
		</xsl:when>
		<xsl:when test="base/MWE/@gender='masculine and feminine'">
		<td align="center" >זכר וגם נקבה</td>
		</xsl:when>
		<xsl:otherwise>
		<td align="center"><xsl:text >- </xsl:text></td>
		</xsl:otherwise>
		</xsl:choose>
		<xsl:choose>
		<xsl:when test="base/MWE/@number='singular'">
		<td align="center" >יחיד</td>
		</xsl:when>
		<xsl:when test="base/MWE/@number='plural'">
		<td align="center" >רבים</td>
		</xsl:when>
		<xsl:when test="base/MWE/@number='dual'">
		<td align="center" >זוגי</td>
		</xsl:when>
		<xsl:otherwise>
		<td align="center"><xsl:text >- </xsl:text></td>
		</xsl:otherwise>
		</xsl:choose>
		<td align="center"><xsl:text >- </xsl:text></td>
		<td align="center"><xsl:text >- </xsl:text></td>
		<td align="center"><xsl:text >- </xsl:text></td>
		<xsl:choose>
		<xsl:when test="suffix">
		<td align="center" >הטייה
		<xsl:element name="BR"/>
		<xsl:choose>
		<xsl:when test="suffix/@gender='masculine'">
		זכר
		</xsl:when>
		<xsl:when test="suffix/@gender='feminine'">
		נקבה 
		</xsl:when>
		<xsl:when test="suffix/@gender='masculine and feminine'">
		זכר וגם נקבה
		</xsl:when>
		</xsl:choose>
		<xsl:element name="BR"/>
		<xsl:choose>
		<xsl:when test="suffix/@number='singular'">
		יחיד
		</xsl:when>
		<xsl:when test="suffix/@number='plural'">
		רבים
		</xsl:when>
		</xsl:choose>
		<xsl:element name="BR"/>
		<xsl:choose>
		<xsl:when test="suffix/@person='1'">
		גוף ראשון
		</xsl:when>
		<xsl:when test="suffix/@person='2'">
		גוף שני
		</xsl:when>
		<xsl:when test="suffix/@person='3'">
		גוף שלישי
		</xsl:when>
		</xsl:choose>
		</td>
		</xsl:when>
		<xsl:otherwise>
		<td align="center"><xsl:text >- </xsl:text></td>
		</xsl:otherwise>
		</xsl:choose>
		<td align="center"><xsl:text >- </xsl:text></td>
		<td align="center"><xsl:text >- </xsl:text></td>
		<td align="center"><xsl:text >- </xsl:text></td>
		<td align="center"><xsl:text >- </xsl:text></td>
		<xsl:choose>
		<xsl:when test="base/MWE/@type='person'">
		<td align="center"> אדם</td>
		</xsl:when>
		<xsl:when test="base/MWE/@type='town'">
		<td align="center"> יישוב</td> 
		</xsl:when>
		<xsl:when test="base/MWE/@type='organization'">
		<td align="center">ארגון</td> 
		</xsl:when>
		<xsl:otherwise>
		<td align="center"><xsl:text >- </xsl:text></td>
		</xsl:otherwise>
		</xsl:choose>



		<td align="center"><xsl:text >- </xsl:text></td>
		<td align="center"><xsl:text >- </xsl:text></td>
		<xsl:choose>
		<xsl:when test="base/MWE/@register='formal'">
		<td align="center"> תקני</td>
		</xsl:when>
		<xsl:when test="base/MWE/@register='archaic'">
		<td align="center" > ארכאי</td>
		</xsl:when>
		<xsl:when test="base/MWE/@register='informal'">
		<td align="center" >תת <xsl:element name="BR"/>
תקני</td>
		</xsl:when>
		<xsl:when test="base/MWE/@register='unspecified'">
		<td align="center"><xsl:text >-</xsl:text></td>
		</xsl:when>
		<xsl:otherwise>
		<td align="center"><xsl:text >- </xsl:text></td>
		</xsl:otherwise>
		</xsl:choose>


		<xsl:choose>
		<xsl:when test="base/MWE/@spelling='standard'">
		<td align="center">תקני</td>
		</xsl:when>
		<xsl:when test="base/MWE/@spelling='irregular'">
		<td align="center" >תת<xsl:element name="BR"/>תקני </td>
		</xsl:when>
		<xsl:otherwise>
		<td align="center"><xsl:text >- </xsl:text></td>
		</xsl:otherwise>
		</xsl:choose>
		</xsl:if>

		<xsl:if test="base/noun">
		<td align="center" bgcolor="#CCFFFF"><b>
		שם<xsl:element name="BR"/> עצם
		</b></td>
		<xsl:choose>
		<xsl:when test="prefix and base/noun">
		
		<td align="center">
		
		<xsl:for-each select="prefix">
		<xsl:value-of select="concat(@function,'')"/>
		<xsl:element name="BR"/>
		</xsl:for-each>
		
		</td>
		</xsl:when>
		<xsl:otherwise>
		<td align="center"><xsl:text >- </xsl:text></td>
		</xsl:otherwise>
		</xsl:choose>
		<xsl:choose>
		<xsl:when test="base/noun/@gender='masculine'">
		<td align="center" >זכר</td>
		</xsl:when>
		<xsl:when test="base/noun/@gender='feminine'">
		<td align="center" >נקבה</td>
		</xsl:when>
		<xsl:when test="base/noun/@gender='masculine and feminine'">
		<td align="center" >זכר וגם נקבה</td>
		</xsl:when>
		<xsl:otherwise>
		<td align="center"><xsl:text >- </xsl:text></td>
		</xsl:otherwise>
		</xsl:choose>
		<xsl:choose>
		<xsl:when test="base/noun/@number='singular'">
		<td align="center" >יחיד</td>
		</xsl:when>
		<xsl:when test="base/noun/@number='plural'">
		<td align="center" >רבים</td>
		</xsl:when>
		<xsl:when test="base/noun/@number='dual'">
		<td align="center" >זוגי</td>
		</xsl:when>
		<xsl:otherwise>
		<td align="center"><xsl:text >- </xsl:text></td>
		</xsl:otherwise>
		</xsl:choose>

		<xsl:choose>
		<xsl:when  test="base/noun/@status">
		<xsl:choose>
		<xsl:when test="base/noun/@status='construct'">
		<td align="center" >נסמך</td>
		</xsl:when>
		<xsl:otherwise>
		<td align="center">נפרד</td>
		</xsl:otherwise>
		</xsl:choose>
		</xsl:when>
		<xsl:otherwise>
		<td align="center"><xsl:text >- </xsl:text></td>
		</xsl:otherwise>
		</xsl:choose>

		<xsl:choose>
		<xsl:when  test="base/noun/@definiteness">
		<xsl:choose>
		<xsl:when test="base/noun/@definiteness='true'">
		<td align="center" >מיודע</td>
		</xsl:when>
		<xsl:when test="base/noun/@definiteness='false'">
		<td align="center" >לא  

		<xsl:element name="BR"/>
		 מיודע</td>
		</xsl:when>
		<xsl:otherwise>
		<td align="center"><xsl:text >- </xsl:text></td>
		</xsl:otherwise>
		</xsl:choose>
		</xsl:when>
		<xsl:otherwise>
		<td align="center"><xsl:text >- </xsl:text></td>
		</xsl:otherwise>
		</xsl:choose>
		<td align="center"><xsl:text >- </xsl:text></td>
		

		<xsl:choose>
		<xsl:when test="suffix">
		<td align="center" >קניין
		<xsl:element name="BR"/>
		<xsl:choose>
		<xsl:when test="suffix/@gender='masculine'">
		זכר
		</xsl:when>
		<xsl:when test="suffix/@gender='feminine'">
		נקבה 
		</xsl:when>
		<xsl:when test="suffix/@gender='masculine and feminine'">
		זכר וגם נקבה
		</xsl:when>
		</xsl:choose>
		<xsl:element name="BR"/>
		<xsl:choose>
		<xsl:when test="suffix/@number='singular'">
		יחיד
		</xsl:when>
		<xsl:when test="suffix/@number='plural'">
		רבים
		</xsl:when>
		<xsl:when test="suffix/@number='dual'">
		זוגי
		</xsl:when>
		</xsl:choose>
		<xsl:element name="BR"/>
		<xsl:choose>
		<xsl:when test="suffix/@person='1'">
		גוף ראשון
		</xsl:when>
		<xsl:when test="suffix/@person='2'">
		גוף שני
		</xsl:when>
		<xsl:when test="suffix/@person='3'">
		גוף שלישי
		</xsl:when>
		</xsl:choose>
		</td>
		</xsl:when>
		<xsl:otherwise>
		<td align="center"><xsl:text >- </xsl:text></td>
		</xsl:otherwise>
		</xsl:choose>
		
		
		<td align="center"><xsl:text >-</xsl:text></td>
		<td align="center"><xsl:text >-</xsl:text></td>
		<td align="center"><xsl:text >-</xsl:text></td>
		<td align="center"><xsl:text >-</xsl:text></td>
		<td align="center"><xsl:text >-</xsl:text></td>
		<td align="center"><xsl:text >-</xsl:text></td>

		<xsl:choose>
		<xsl:when test="base/noun/@register='formal'">
		<td align="center">תקני</td>
		</xsl:when>
		<xsl:when test="base/noun/@register='archaic'">
		<td align="center" >ארכאי </td>
		</xsl:when>
		<xsl:when test="base/noun/@register='informal'">
		<td align="center" >תת <xsl:element name="BR"/>תקני</td>
		</xsl:when>
		<xsl:when test="base/noun/@register='unspecified'">
		<td align="center"><xsl:text >-</xsl:text></td>
		</xsl:when>
		</xsl:choose>


		<xsl:choose>
		<xsl:when test="base/noun/@spelling='standard'">
		<td align="center">תקני</td>
		</xsl:when>
		<xsl:when test="base/noun/@spelling='irregular'">
		<td align="center" >תת<xsl:element name="BR"/>תקני </td>
		</xsl:when>
		</xsl:choose>
		</xsl:if>




		<xsl:if test="base/adjective">
		<td align="center"  bgcolor="#99CCFF"><b>
		שם<xsl:element name="BR"/> תואר
		</b></td>
		<xsl:choose>
		<xsl:when test="prefix and base/adjective">
		
		<td align="center">
		
		<xsl:for-each select="prefix">
		<xsl:value-of select="concat(@function,'')"/>
		<xsl:element name="BR"/>
		</xsl:for-each>
		
		</td>
		</xsl:when>
		<xsl:otherwise>
		<td align="center"><xsl:text >- </xsl:text></td>
		</xsl:otherwise>
		</xsl:choose>
		<xsl:choose>
		<xsl:when test="base/adjective/@gender='masculine'">
		<td align="center" >זכר</td>
		</xsl:when>
		<xsl:when test="base/adjective/@gender='feminine'">
		<td align="center" >נקבה</td>
		</xsl:when>
		<xsl:when test="base/adjective/@gender='masculine and feminine'">
		<td align="center" >זכר וגם נקבה</td>
		</xsl:when>
		</xsl:choose>
		<xsl:choose>
		<xsl:when test="base/adjective/@number='singular'">
		<td align="center" >יחיד</td>
		</xsl:when>
		<xsl:when test="base/adjective/@number='plural'">
		<td align="center" >רבים</td>
		</xsl:when>
		<xsl:otherwise>
		<td align="center"><xsl:text >- </xsl:text></td>
		</xsl:otherwise>
		</xsl:choose>

		<xsl:choose>
		<xsl:when test="base/adjective/@status='construct'">
		<td align="center" >נסמך</td>
		</xsl:when>
		<xsl:when test="base/adjective/@status='absolute'">
		<td align="center" >נפרד</td>
		</xsl:when>
		<xsl:otherwise>
		<td align="center"><xsl:text >- </xsl:text></td>
		</xsl:otherwise>
		</xsl:choose>

		<xsl:choose>
		<xsl:when  test="base/adjective/@definiteness">
		<xsl:choose>
		<xsl:when test="base/adjective/@definiteness='true'">
		<td align="center" >מיודע</td>
		</xsl:when>
		<xsl:when test="base/adjective/@definiteness='false'">
		<td align="center" >לא  

		<xsl:element name="BR"/>

		 מיודע</td>
		</xsl:when>
		<xsl:otherwise>
		<td align="center"><xsl:text >-</xsl:text></td>
		</xsl:otherwise>	
		</xsl:choose>
		</xsl:when>
		<xsl:otherwise>
		<td align="center"><xsl:text >-</xsl:text></td>
		</xsl:otherwise>
		</xsl:choose>

		<td align="center"><xsl:text >- </xsl:text></td>
		<td align="center"><xsl:text >-</xsl:text></td>
		<td align="center"><xsl:text >-</xsl:text></td>
		<td align="center"><xsl:text >-</xsl:text></td>
		<td align="center"><xsl:text >-</xsl:text></td>
		<td align="center"><xsl:text >-</xsl:text></td>
		<td align="center"><xsl:text >-</xsl:text></td>
		<td align="center"><xsl:text >-</xsl:text></td>
		<xsl:choose>
		<xsl:when test="base/adjective/@register='formal'">
		<td align="center"> תקני</td>
		</xsl:when>
		<xsl:when test="base/adjective/@register='archaic'">
		<td align="center" > ארכאי</td>
		</xsl:when>
		<xsl:when test="base/adjective/@register='informal'">
		<td align="center" >תת <xsl:element name="BR"/>
תקני</td>
		</xsl:when>
		<xsl:when test="base/adjective/@register='unspecified'">
		<td align="center"><xsl:text >-</xsl:text></td>
		</xsl:when>
		</xsl:choose>


		<xsl:choose>
		<xsl:when test="base/adjective/@spelling='standard'">
		<td align="center">תקני</td>
		</xsl:when>
		<xsl:when test="base/adjective/@spelling='irregular'">
		<td align="center" >תת<xsl:element name="BR"/>תקני </td>
		</xsl:when>
		</xsl:choose>
		</xsl:if>




		<xsl:if test="base/title">
		<td align="center" bgcolor="#99CCCC">
		<b>תואר</b>
		</td>
		<xsl:choose>
		<xsl:when test="prefix and base/title">
		
		<td align="center">
		
		<xsl:for-each select="prefix">
		<xsl:value-of select="concat(@function,'')"/>
		<xsl:element name="BR"/>
		</xsl:for-each>
		
		</td>
		</xsl:when>
		<xsl:otherwise>
		<td align="center"><xsl:text >- </xsl:text></td>
		</xsl:otherwise>
		</xsl:choose>
		<xsl:choose>
		<xsl:when test="base/title/@gender='masculine'">
		<td align="center" >זכר</td>
		</xsl:when>
		<xsl:when test="base/title/@gender='feminine'">
		<td align="center" >נקבה</td>
		</xsl:when>
		<xsl:when test="base/title/@gender='masculine and feminine'">
		<td align="center" >זכר וגם נקבה</td>
		</xsl:when>
		</xsl:choose>
		<td align="center"><xsl:text >- </xsl:text></td>
		<xsl:choose>
		<xsl:when test="base/title/@number='singular'">
		<td align="center" >יחיד</td>
		</xsl:when>
		<xsl:when test="base/title/@number='plural'">
		<td align="center" >רבים</td>
		</xsl:when>
		</xsl:choose>
		<xsl:choose>
		<xsl:when test="base/title/@definiteness='true'">
		<td align="center" >מיודע</td>
		</xsl:when>
		<xsl:when test="base/title/@definiteness='false'">
		<td align="center" >לא  

		<xsl:element name="BR"/>

		 מיודע</td>
		</xsl:when>
		</xsl:choose>
		<td align="center"><xsl:text >-</xsl:text></td>
		<td align="center"><xsl:text >-</xsl:text></td>
		<td align="center"><xsl:text >-</xsl:text></td>
		<td align="center"><xsl:text >-</xsl:text></td>
		<td align="center"><xsl:text >-</xsl:text></td>
		<td align="center"><xsl:text >-</xsl:text></td>
		<td align="center"><xsl:text >-</xsl:text></td>
		<td align="center"><xsl:text >-</xsl:text></td>
		<xsl:choose>
		<xsl:when test="base/title/@register='formal'">
		<td align="center">תקני</td>
		</xsl:when>
		<xsl:when test="base/title/@register='archaic'">
		<td align="center" >ארכאי</td>
		</xsl:when>
		<xsl:when test="base/title/@register='informal'">
		<td align="center" >תת<xsl:element name="BR"/> תקני</td>
		</xsl:when>
		<xsl:when test="base/title/@register='unspecified'">
		<td align="center"><xsl:text >-</xsl:text></td>
		</xsl:when>
		</xsl:choose>


		<xsl:choose>
		<xsl:when test="base/title/@spelling='standard'">
		<td align="center">תקני</td>
		</xsl:when>
		<xsl:when test="base/title/@spelling='irregular'">
		<td align="center" >תת<xsl:element name="BR"/>תקני </td>
		</xsl:when>
		</xsl:choose>

		</xsl:if>	



	
		<xsl:if test="base/verb">
		<td align="center" bgcolor="#CC99CC">
		<b>פועל
		</b></td>
		<xsl:choose>
		<xsl:when test="prefix and base/verb">
		
		<td align="center">
		
		<xsl:for-each select="prefix">
		<xsl:value-of select="concat(@function,'')"/>
		<xsl:element name="BR"/>
		</xsl:for-each>
		
		</td>
		</xsl:when>
		<xsl:otherwise>
		<td align="center"><xsl:text >- </xsl:text></td>
		</xsl:otherwise>
		</xsl:choose>
		<xsl:choose>
		<xsl:when test="base/verb/@gender='masculine'">
		<td align="center" >זכר</td>
		</xsl:when>
		<xsl:when test="base/verb/@gender='feminine'">
		<td align="center" >נקבה</td>
		</xsl:when>
		<xsl:when test="base/verb/@gender='masculine and feminine'">
		<td align="center" >זכר וגם נקבה</td>
		</xsl:when>
		<xsl:otherwise>
		<td align="center"><xsl:text >-</xsl:text></td>
		</xsl:otherwise>		


		</xsl:choose>
		<xsl:choose>
		<xsl:when test="base/verb/@number='singular'">
		<td align="center" >יחיד</td>
		</xsl:when>
		<xsl:when test="base/verb/@number='plural'">
		<td align="center" >רבים</td>
		</xsl:when>
		<xsl:otherwise>
		<td align="center"><xsl:text >-</xsl:text></td>
		</xsl:otherwise>	

		</xsl:choose>
		<td align="center"><xsl:text >-</xsl:text></td>
		<td align="center"><xsl:text >-</xsl:text></td>
		<xsl:choose>
		<xsl:when test="base/verb/@person='1'">
		<td align="center">
		גוף<xsl:element name="BR"/> ראשון
		</td>
		</xsl:when>
		<xsl:when test="base/verb/@person='2'">
		<td align="center">
		גוף<xsl:element name="BR"/> שני
		</td>
		</xsl:when>
		<xsl:when test="base/verb/@person='3'">
		<td align="center">
		גוף<xsl:element name="BR"/> שלישי
		</td>
		</xsl:when>
		<xsl:otherwise>
		<td align="center"><xsl:text >-</xsl:text></td>
		</xsl:otherwise>			
		</xsl:choose>
	

		<xsl:choose>
		<xsl:when test="suffix">
		<td align="center" >הטייה
		<xsl:element name="BR"/>
		<xsl:choose>
		<xsl:when test="suffix/@gender='masculine'">
		זכר
		</xsl:when>
		<xsl:when test="suffix/@gender='feminine'">
		נקבה 
		</xsl:when>
		<xsl:when test="suffix/@gender='masculine and feminine'">
		זכר וגם נקבה
		</xsl:when>
		</xsl:choose>
		<xsl:element name="BR"/>
		<xsl:choose>
		<xsl:when test="suffix/@number='singular'">
		יחיד
		</xsl:when>
		<xsl:when test="suffix/@number='plural'">
		רבים
		</xsl:when>
		</xsl:choose>
		<xsl:element name="BR"/>
		<xsl:choose>
		<xsl:when test="suffix/@person='1'">
		גוף ראשון
		</xsl:when>
		<xsl:when test="suffix/@person='2'">
		גוף שני
		</xsl:when>
		<xsl:when test="suffix/@person='3'">
		גוף שלישי
		</xsl:when>
		</xsl:choose>
		</td>
		</xsl:when>
		<xsl:otherwise>
		<td align="center"><xsl:text >- </xsl:text></td>
		</xsl:otherwise>
		</xsl:choose>
		
		<xsl:choose>
		<xsl:when test="base/verb/@tense='past'">
		<td align="center" >עבר</td>
		</xsl:when>
		<xsl:when test="base/verb/@tense='future'">
		<td align="center" >עתיד</td>
		</xsl:when>
		<xsl:when test="base/verb/@tense='imperative'">
		<td align="center" >ציווי</td>
		</xsl:when>
		<xsl:when test="base/verb/@tense='infinitive'">
		<td align="center" >שם פועל</td>
		</xsl:when>
		<xsl:when test="base/verb/@tense='bareInfinitive'">
		<td align="center" >מקור</td>
		</xsl:when>
		<xsl:otherwise>
		<td align="center"><xsl:text >-</xsl:text></td>
		</xsl:otherwise>
		</xsl:choose>
		<xsl:choose>
		<xsl:when test="contains(base/verb/@binyan, 'Pa')">
		<td align="center" >קל</td>
		</xsl:when>
		<xsl:when test="contains(base/verb/@binyan,'Nif')">
		<td align="center" >נפעל</td>
		</xsl:when>
		<xsl:when test="contains(base/verb/@binyan,'Pi')">
		<td align="center" >פיעל</td>
		</xsl:when>
		<xsl:when test="contains(base/verb/@binyan,'Pu')">
		<td align="center" >פועל</td>
		</xsl:when>
		<xsl:when test="contains(base/verb/@binyan,'Hitpa')">
		<td align="center" >התפעל</td>
		</xsl:when>
		<xsl:when test="contains(base/verb/@binyan,'Hif')">
		<td align="center" ><i>הפעיל</i></td>
		</xsl:when>
		<xsl:when test="contains(base/verb/@binyan,'Huf')">
		<td align="center" >הופעל</td>
		</xsl:when>
		<xsl:otherwise>
		<td align="center"><xsl:text >-</xsl:text></td>
		</xsl:otherwise>
		</xsl:choose>
		
		<xsl:choose>
		<xsl:when test="string-length (base/verb/@root)='3'">
		<td align="center">
		 <xsl:value-of select="substring(base/verb/@root,1,1)"/>
		 <xsl:value-of select="substring(base/verb/@root,2,1)"/>
		  <xsl:value-of select="substring(base/verb/@root,3,1)"/>
		</td>
		</xsl:when>
		<xsl:when test="string-length (base/verb/@root)='4'">
		<td align="center">
		 <xsl:value-of select="substring(base/verb/@root,1,1)"/>
		 <xsl:value-of select="substring(base/verb/@root,2,1)"/>
		 <xsl:value-of select="substring(base/verb/@root,3,1)"/>
		 <xsl:value-of select="substring(base/verb/@root,4,1)"/>
		</td>
		</xsl:when>
		<xsl:otherwise>
		<td align="center"><xsl:text >-</xsl:text></td>
		</xsl:otherwise>
		</xsl:choose>
		<td align="center"><xsl:text >-</xsl:text></td>
		<td align="center"><xsl:text >-</xsl:text></td>
		<td align="center"><xsl:text >-</xsl:text></td>
		<xsl:choose>
		<xsl:when test="base/verb/@register='formal'">
		<td align="center">תקני</td>
		</xsl:when>
		<xsl:when test="base/verb/@register='archaic'">
		<td align="center" >ארכאי</td>
		</xsl:when>
		<xsl:when test="base/verb/@register='informal'">
		<td align="center" >תת <xsl:element name="BR"/>תקני</td>
		</xsl:when>
		<xsl:when test="base/verb/@register='unspecified'">
		<td align="center"><xsl:text >-</xsl:text></td>
		</xsl:when>
		</xsl:choose>


		<xsl:choose>
		<xsl:when test="base/verb/@spelling='standard'">
		<td align="center">תקני</td>
		</xsl:when>
		<xsl:when test="base/verb/@spelling='irregular'">
		<td align="center" >תת<xsl:element name="BR"/>תקני </td>
		</xsl:when>
		</xsl:choose>
		</xsl:if>






		<xsl:if test="base/copula">
		<td align="center" bgcolor="#CC99FF"><b>
		אוגד
		</b></td>
		<xsl:choose>
		<xsl:when test="prefix and base/copula">
		
		<td align="center">
		
		<xsl:for-each select="prefix">
		<xsl:value-of select="concat(@function,'')"/>
		<xsl:element name="BR"/>
		</xsl:for-each>
		
		</td>
		</xsl:when>
		<xsl:otherwise>
		<td align="center"><xsl:text >- </xsl:text></td>
		</xsl:otherwise>
		</xsl:choose>
		<xsl:choose>
		<xsl:when test="base/copula/@gender='masculine'">
		<td align="center" >זכר</td>
		</xsl:when>
		<xsl:when test="base/copula/@gender='feminine'">
		<td align="center" >נקבה</td>
		</xsl:when>
		<xsl:when test="base/copula/@gender='masculine and feminine'">
		<td align="center" >זכר <xsl:element name="BR"/>וגם <xsl:element name="BR"/>נקבה</td>
		</xsl:when>
		<xsl:otherwise>
		<td align="center"><xsl:text >-</xsl:text></td>
		</xsl:otherwise>
		</xsl:choose>
		<xsl:choose>
		<xsl:when test="base/copula/@number='singular'">
		<td align="center" >יחיד</td>
		</xsl:when>
		<xsl:when test="base/copula/@number='plural'">
		<td align="center" >רבים</td>
		</xsl:when>
		<xsl:otherwise>
		<td align="center"><xsl:text >-</xsl:text></td>
		</xsl:otherwise>
		</xsl:choose>
		<td align="center"><xsl:text >-</xsl:text></td>
		<td align="center"><xsl:text >-</xsl:text></td>
		<xsl:choose>
		<xsl:when test="base/copula/@person='1'">
		<td align="center">
		גוף<xsl:element name="BR"/> ראשון
		</td>
		</xsl:when>
		<xsl:when test="base/copula/@person='2'">
		<td align="center">
		גוף<xsl:element name="BR"/> שני
		</td>
		</xsl:when>
		<xsl:when test="base/copula/@person='3'">
		<td align="center">
		גוף <xsl:element name="BR"/>שלישי
		</td>
		</xsl:when>
		<xsl:otherwise>
		<td align="center"><xsl:text >-</xsl:text></td>
		</xsl:otherwise>
		</xsl:choose>
		<td align="center"><xsl:text >-</xsl:text></td>
		<xsl:choose>
		<xsl:when test="base/copula/@tense='past'">
		<td align="center" >עבר</td>
		</xsl:when>
		<xsl:when test="base/copula/@tense='future'">
		<td align="center" >עתיד</td>
		</xsl:when>
		<xsl:when test="base/copula/@tense='imperative'">
		<td align="center" >ציווי</td>
		</xsl:when>
		<xsl:when test="base/copula/@tense='infinitive'">
		<td align="center" >שם פועל</td>
		</xsl:when>
		<xsl:when test="base/copula/@tense='bareInfinitive'">
		<td align="center" >מקור</td>
		</xsl:when>
		<xsl:otherwise>
		<td align="center"><xsl:text >-</xsl:text></td>
		</xsl:otherwise>
		</xsl:choose>
		
		<td align="center"><xsl:text >-</xsl:text></td>
		<td align="center"><xsl:text >-</xsl:text></td>
		<td align="center"><xsl:text >-</xsl:text></td>
		
		<xsl:choose>
		<xsl:when test="base/copula/@polarity='positive'">
		<td align="center">
		חיובי
		</td>
		</xsl:when>
		<xsl:when test="base/copula/@polarity='negative'">
		<td align="center">
		שלילי
		</td>
		</xsl:when>
		<xsl:otherwise>
		<td align="center"><xsl:text >-</xsl:text></td>
		</xsl:otherwise>
		</xsl:choose>
		<td align="center"><xsl:text >-</xsl:text></td>
		<xsl:choose>
		<xsl:when test="base/copula/@register='formal'">
		<td align="center">תקני</td>
		</xsl:when>
		<xsl:when test="base/copula/@register='archaic'">
		<td align="center" >ארכאי</td>
		</xsl:when>
		<xsl:when test="base/copula/@register='informal'">
		<td align="center" >תת <xsl:element name="BR"/>תקני</td>
		</xsl:when>
		<xsl:when test="base/copula/@register='unspecified'">
		<td align="center"><xsl:text >-</xsl:text></td>
		</xsl:when>
		</xsl:choose>
		
		<xsl:choose>
		<xsl:when test="base/copula/@spelling='standard'">
		<td align="center">תקני</td>
		</xsl:when>
		<xsl:when test="base/copula/@spelling='irregular'">
		<td align="center" >תת<xsl:element name="BR"/>תקני </td>
		</xsl:when>
		</xsl:choose>
		
		</xsl:if>


		<xsl:if test="base/numeral">
		<td align="center"  bgcolor="#99CC99"><b>
		שם<xsl:element name="BR"/> מספר
		</b></td>
		<xsl:choose>
		<xsl:when test="prefix and base/numeral">
		
		<td align="center">
		
		<xsl:for-each select="prefix">
		<xsl:value-of select="concat(@function,'')"/>
		<xsl:element name="BR"/>
		</xsl:for-each>
		
		</td>
		</xsl:when>
		<xsl:otherwise>
		<td align="center"><xsl:text >- </xsl:text></td>
		</xsl:otherwise>
		</xsl:choose>
		<xsl:choose>
		<xsl:when test="base/numeral/@gender='masculine'">
		<td align="center" >זכר</td>
		</xsl:when>
		<xsl:when test="base/numeral/@gender='feminine'">
		<td align="center" >נקבה</td>
		</xsl:when>
		<xsl:when test="base/numeral/@gender='masculine and feminine'">
		<td align="center" >זכר וגם נקבה</td>
		</xsl:when>
		<xsl:otherwise>
		<td align="center"><xsl:text >-</xsl:text></td>
		</xsl:otherwise>
		</xsl:choose>
		<xsl:choose>
		<xsl:when test="base/numeral/@number='singular'">
		<td align="center" >יחיד</td>
		</xsl:when>
		<xsl:when test="base/numeral/@number='plural'">
		<td align="center" >רבים</td>
		</xsl:when>
		<xsl:otherwise>
		<td align="center"><xsl:text >-</xsl:text></td>
		</xsl:otherwise>
		</xsl:choose>
		<xsl:choose>
		<xsl:when test="base/numeral/@status='construct'">
		<td align="center" >נסמך</td>
		</xsl:when>
		<xsl:when test="base/numeral/@status='absolute'">
		<td align="center" >נפרד</td>
		</xsl:when>
		<xsl:otherwise>
		<td align="center"><xsl:text >-</xsl:text></td>
		</xsl:otherwise>
		
		</xsl:choose>
		<xsl:choose>
		<xsl:when test="base/numeral/@definiteness='true'">
		<td align="center" >מיודע</td>
		</xsl:when>
		<xsl:when test="base/numeral/@definiteness='false'">
		<td align="center" >לא  

		<xsl:element name="BR"/>

		 מיודע</td>
		</xsl:when>
		<xsl:otherwise>
		<td align="center"><xsl:text >-</xsl:text></td>
		</xsl:otherwise>
		</xsl:choose>
		<td align="center"><xsl:text >-</xsl:text></td>
		<td align="center"><xsl:text >-</xsl:text></td>
		<xsl:choose>
		<xsl:when test="suffix">
		<td align="center" >קניין
		<xsl:element name="BR"/>
		<xsl:choose>
		<xsl:when test="suffix/@gender='masculine'">
		זכר
		</xsl:when>
		<xsl:when test="suffix/@gender='feminine'">
		נקבה 
		</xsl:when>
		<xsl:when test="suffix/@gender='masculine and feminine'">
		זכר וגם נקבה
		</xsl:when>
		</xsl:choose>
		<xsl:element name="BR"/>
		<xsl:choose>
		<xsl:when test="suffix/@number='singular'">
		יחיד
		</xsl:when>
		<xsl:when test="suffix/@number='plural'">
		רבים
		</xsl:when>
		<xsl:when test="suffix/@number='dual'">
		זוגי
		</xsl:when>
		</xsl:choose>
		<xsl:element name="BR"/>
		<xsl:choose>
		<xsl:when test="suffix/@person='1'">
		גוף ראשון
		</xsl:when>
		<xsl:when test="suffix/@person='2'">
		גוף שני
		</xsl:when>
		<xsl:when test="suffix/@person='3'">
		גוף שלישי
		</xsl:when>
		</xsl:choose>
		</td>
		</xsl:when>
		<xsl:otherwise>
		<td align="center"><xsl:text >- </xsl:text></td>
		</xsl:otherwise>
		</xsl:choose>
		
		<td align="center"><xsl:text >-</xsl:text></td>
		<td align="center"><xsl:text >-</xsl:text></td>
		
		<xsl:choose>
		<xsl:when test="base/numeral/@type='numeral ordinal'">
		<td align="center" >מספר סודר</td>
		</xsl:when>
		<xsl:when test="base/numeral/@type='numeral fractional'">
		<td align="center" >שבר</td>
		</xsl:when>
		<xsl:when test="base/numeral/@type='numeral cardinal'">
		<td align="center" >מספר מונה</td>
		</xsl:when>
		<xsl:when test="base/numeral/@type='gematria'">
		<td align="center" >גימטריא</td>
		</xsl:when>
		<xsl:when test="base/numeral/@type='literal number'">
		<td align="center" >מספר</td>
		</xsl:when>
		</xsl:choose>
		<td align="center"><xsl:text >-</xsl:text></td>
		<xsl:choose>
		<xsl:when test="base/numeral/@value">
		<td align="center"><xsl:value-of select="base/numeral/@value"/></td>
		</xsl:when>
		<xsl:otherwise>
		<td align="center"><xsl:text >-</xsl:text></td>
		</xsl:otherwise>
		</xsl:choose>
		
		<xsl:choose>
		<xsl:when test="base/numeral/@type!='literal number'  and base/numeral/@register='formal'">
		<td align="center">תקני</td>
		</xsl:when>
		<xsl:when test="base/numeral/@type!='literal number'  and base/numeral/@register='informal'">
		<td align="center" >תת<xsl:element name="BR"/> תקני</td>
		</xsl:when>
		<xsl:when test="base/numeral/@type!='literal number'  and base/numeral/@register='archaic'">
		<td align="center" >ארכאי</td>
		</xsl:when>
		<xsl:otherwise>
		<td align="center"><xsl:text >-</xsl:text></td>
		</xsl:otherwise>
		</xsl:choose>
		
		<xsl:choose>
		<xsl:when test="base/numeral/@spelling='standard'">
		<td align="center">תקני</td>
		</xsl:when>
		<xsl:when test="base/numeral/@spelling='irregular'">
		<td align="center" >תת<xsl:element name="BR"/>תקני </td>
		</xsl:when>
		<xsl:otherwise>
		<td align="center"><xsl:text >-</xsl:text></td>
		</xsl:otherwise>
		</xsl:choose>
		</xsl:if>




		<xsl:if test="base/participle">
		<xsl:choose>
		<xsl:when test="base/participle/@mood">
		<td  BGCOLOR="#33CCCC" align="center"><b>בינוני<xsl:element 		name="BR"/> פעול</b></td>
		</xsl:when>
		<xsl:otherwise>
		<td  BGCOLOR="#33CCCC" align="center"><b>בינוני </b></td>
		</xsl:otherwise>
		</xsl:choose>
		<xsl:choose>
		<xsl:when test="prefix and base/participle">
		
		<td align="center">
		
		<xsl:for-each select="prefix">
		<xsl:value-of select="concat(@function,'')"/>
		<xsl:element name="BR"/>
		</xsl:for-each>
		
		</td>
		</xsl:when>
		<xsl:otherwise>
		<td align="center"><xsl:text >- </xsl:text></td>
		</xsl:otherwise>
		</xsl:choose>
		<xsl:choose>
		<xsl:when test="base/participle/@gender='masculine'">
		<td align="center" >זכר</td>
		</xsl:when>
		<xsl:when test="base/participle/@gender='feminine'">
		<td align="center" >נקבה</td>
		</xsl:when>
		<xsl:when test="base/participle/@gender='masculine and feminine'">
		<td align="center" >זכר<xsl:element name="BR"/> וגם <xsl:element name="BR"/>נקבה</td>
		</xsl:when>
		<xsl:otherwise>
		<td align="center"><xsl:text >-</xsl:text></td>
		</xsl:otherwise>
		</xsl:choose>
		<xsl:choose>
		<xsl:when test="base/participle/@number='singular'">
		<td align="center" >יחיד</td>
		</xsl:when>
		<xsl:when test="base/participle/@number='plural'">
		<td align="center" >רבים</td>
		</xsl:when>
		<xsl:otherwise>
		<td align="center"><xsl:text >-</xsl:text></td>
		</xsl:otherwise>
		</xsl:choose>

		<xsl:choose>
		<xsl:when  test="base/participle/@status">
		<xsl:choose>
		<xsl:when test="base/participle/@status='construct'">
		<td align="center" >נסמך</td>
		</xsl:when>
		<xsl:when test="base/participle/@status='absolute'">
		<td align="center" >נפרד</td>
		</xsl:when>
		<xsl:otherwise>
		<td align="center"><xsl:text >-</xsl:text></td>
		</xsl:otherwise>
		</xsl:choose>
		</xsl:when>
		<xsl:otherwise>
		<td align="center"><xsl:text >-</xsl:text></td>
		</xsl:otherwise>
		</xsl:choose>


		<xsl:choose>
		<xsl:when  test="base/participle/@definiteness">
		<xsl:choose>
		<xsl:when test="base/participle/@definiteness='true'">
		<td align="center" >מיודע</td>
		</xsl:when>
		<xsl:when test="base/participle/@definiteness='false'">
		<td align="center" >לא  

		<xsl:element name="BR"/>

		 מיודע</td>
		</xsl:when>
		<xsl:otherwise>
		<td align="center"><xsl:text >-</xsl:text></td>
		</xsl:otherwise>
		</xsl:choose>
		</xsl:when>
		<xsl:otherwise>
		<td align="center"><xsl:text >-</xsl:text></td>
		</xsl:otherwise>
		</xsl:choose>

		<xsl:choose>
		<xsl:when test="base/participle/@person='1'">
		<td align="center">
		גוף<xsl:element name="BR"/> ראשון
		</td>
		</xsl:when>
		<xsl:when test="base/participle/@person='2'">
		<td align="center">
		גוף<xsl:element name="BR"/> שני
		</td>
		</xsl:when>
		<xsl:when test="base/participle/@person='3'">
		<td align="center">
		גוף <xsl:element name="BR"/>שלישי
		</td>
		</xsl:when>
		<xsl:when test="base/participle/@person='any'">
		<td align="center">
		גוף <xsl:element name="BR"/>כלשהו
		</td>
		</xsl:when>
		<xsl:otherwise>
		<td align="center"><xsl:text >-</xsl:text></td>
		</xsl:otherwise>
		</xsl:choose>


		<xsl:choose>
		<xsl:when test="suffix">
		<td align="center">
		קניין
		<xsl:element name="BR"/>
		<xsl:choose>
		<xsl:when test="suffix/@gender='masculine'">
		זכר
		</xsl:when>
		<xsl:when test="suffix/@gender='feminine'">
		נקבה 
		</xsl:when>
		<xsl:when test="suffix/@gender='masculine and feminine'">
		זכר וגם נקבה
		</xsl:when>
		</xsl:choose>
		<xsl:element name="BR"/>
		<xsl:choose>
		<xsl:when test="suffix/@number='singular'">
		יחיד
		</xsl:when>
		<xsl:when test="suffix/@number='plural'">
		רבים
		</xsl:when>
		</xsl:choose>
		<xsl:element name="BR"/>
		<xsl:choose>
		<xsl:when test="suffix/@person='1'">
		גוף<xsl:element name="BR"/> ראשון
		</xsl:when>
		<xsl:when test="suffix/@person='2'">
		גוף <xsl:element name="BR"/>שני
		</xsl:when>
		<xsl:when test="suffix/@person='3'">
		גוף<xsl:element name="BR"/> שלישי
		</xsl:when>
		
		</xsl:choose>
		</td>
		</xsl:when>
		<xsl:otherwise>
		<td align="center"><xsl:text >-</xsl:text></td>
		</xsl:otherwise>
		</xsl:choose>

		<td align="center"><xsl:text >-</xsl:text></td>

		<xsl:choose>
		<xsl:when test="contains(base/participle/@binyan, 'Pa')">
		<td align="center" >קל</td>
		</xsl:when>
		<xsl:when test="contains(base/participle/@binyan,'Nif')">
		<td align="center" >נפעל</td>
		</xsl:when>
		<xsl:when test="contains(base/participle/@binyan,'Pi')">
		<td align="center" >פיעל</td>
		</xsl:when>
		<xsl:when test="contains(base/participle/@binyan,'Pu')">
		<td align="center" >פועל</td>
		</xsl:when>
		<xsl:when test="contains(base/participle/@binyan,'Hitpa')">
		<td align="center" >התפעל</td>
		</xsl:when>
		<xsl:when test="contains(base/participle/@binyan,'Hif')">
		<td align="center" ><i>הפעיל</i></td>
		</xsl:when>
		<xsl:when test="contains(base/participle/@binyan,'Huf')">
		<td align="center" >הופעל</td>
		</xsl:when>
		<xsl:otherwise>
		<td align="center"><xsl:text >-</xsl:text></td>
		</xsl:otherwise>
		</xsl:choose>

		<xsl:choose>
		<xsl:when test="string-length (base/participle/@root)='3'">
		<td align="center">
		 <xsl:value-of select="substring(base/participle/@root,1,1)"/>
		 <xsl:value-of select="substring(base/participle/@root,2,1)"/>
		  <xsl:value-of select="substring(base/participle/@root,3,1)"/>
		</td>
		</xsl:when>
		<xsl:when test="string-length (base/participle/@root)='4'">
		<td align="center">
		 <xsl:value-of select="substring(base/participle/@root,1,1)"/>
		 <xsl:value-of select="substring(base/participle/@root,2,1)"/>
		  <xsl:value-of select="substring(base/participle/@root,3,1)"/>
		 <xsl:value-of select="substring(base/participle/@root,4,1)"/>
		</td>
		</xsl:when>
		<xsl:otherwise>
		<td align="center"><xsl:text >-</xsl:text></td>
		</xsl:otherwise>
		</xsl:choose>
		<td align="center"><xsl:text >-</xsl:text></td>
		<td align="center"><xsl:text >-</xsl:text></td>
		<td align="center"><xsl:text >-</xsl:text></td>
		
		<xsl:choose>
		<xsl:when test="base/participle/@register='formal'">
		<td align="center">תקני</td>
		</xsl:when>
		<xsl:when test="base/participle/@register='archaic'">
		<td align="center" >ארכאי</td>
		</xsl:when>
		<xsl:when test="base/participle/@register='informal'">
		<td align="center" >תת <xsl:element name="BR"/>תקני</td>
		</xsl:when>
		<xsl:when test="base/participle/@register='unspecified'">
		<td align="center"><xsl:text >-</xsl:text></td>
		</xsl:when>
		</xsl:choose>


		
		<xsl:choose>
		<xsl:when test="base/participle/@spelling='standard'">
		<td align="center">תקני</td>
		</xsl:when>
		<xsl:when test="base/participle/@spelling='irregular'">
		<td align="center" >תת<xsl:element name="BR"/>תקני </td>
		</xsl:when>
		</xsl:choose>
		</xsl:if>




		<xsl:if test="base/pronoun">
		<td align="center" bgcolor="#FFFF66"><b >
		כינוי<xsl:element name="BR"/> גוף
		</b></td>
		<xsl:choose>
		<xsl:when test="prefix and base/pronoun">
		
		<td align="center">
		
		<xsl:for-each select="prefix">
		<xsl:value-of select="concat(@function,'')"/>
		<xsl:element name="BR"/>
		</xsl:for-each>
		
		</td>
		</xsl:when>
		<xsl:otherwise>
		
		<td align="center"><xsl:text >- </xsl:text></td>

		</xsl:otherwise>
		</xsl:choose>
		<xsl:choose>
		<xsl:when test="base/pronoun/@gender='masculine'">
		<td align="center" >זכר</td>
		</xsl:when>
		<xsl:when test="base/pronoun/@gender='feminine'">
		<td align="center" >נקבה</td>
		</xsl:when>
		<xsl:when test="base/pronoun/@gender='masculine and feminine'">
		<td align="center" >זכר וגם נקבה</td>
		</xsl:when>
		<xsl:otherwise>
		<td align="center"><xsl:text >-</xsl:text></td>
		</xsl:otherwise>
		</xsl:choose>
		<xsl:choose>
		<xsl:when test="base/pronoun/@number='singular'">
		<td align="center" >יחיד</td>
		</xsl:when>
		<xsl:when test="base/pronoun/@number='plural'">
		<td align="center" >רבים</td>
		</xsl:when>
		<xsl:otherwise>
		<td align="center"><xsl:text >-</xsl:text></td>
		</xsl:otherwise>
		</xsl:choose>
		<td align="center"><xsl:text >-</xsl:text></td>
		<td align="center"><xsl:text >-</xsl:text></td>
		<xsl:choose>
		<xsl:when test="base/pronoun/@person='1'">
		<td align="center">
		גוף<xsl:element name="BR"/> ראשון
		</td>
		</xsl:when>
		<xsl:when test="base/pronoun/@person='2'">
		<td align="center">
		גוף<xsl:element name="BR"/> שני
		</td>
		</xsl:when>
		<xsl:when test="base/pronoun/@person='3'">
		<td align="center">
		גוף<xsl:element name="BR"/> שלישי
		</td>
		</xsl:when>
		<xsl:otherwise>
		<td align="center"><xsl:text >-</xsl:text></td>
		</xsl:otherwise>
		</xsl:choose>
	
		<xsl:choose>
		<xsl:when test="suffix">
		<td align="center" >הטייה
		<xsl:element name="BR"/>
		<xsl:choose>
		<xsl:when test="suffix/@gender='masculine'">
		זכר
		</xsl:when>
		<xsl:when test="suffix/@gender='feminine'">
		נקבה 
		</xsl:when>
		<xsl:when test="suffix/@gender='masculine and feminine'">
		זכר וגם נקבה
		</xsl:when>
		</xsl:choose>
		<xsl:element name="BR"/>
		<xsl:choose>
		<xsl:when test="suffix/@number='singular'">
		יחיד
		</xsl:when>
		<xsl:when test="suffix/@number='plural'">
		רבים
		</xsl:when>
		<xsl:when test="suffix/@number='dual'">
		זוגי
		</xsl:when>
		</xsl:choose>
		<xsl:element name="BR"/>
		<xsl:choose>
		<xsl:when test="suffix/@person='1'">
		גוף ראשון
		</xsl:when>
		<xsl:when test="suffix/@person='2'">
		גוף שני
		</xsl:when>
		<xsl:when test="suffix/@person='3'">
		גוף שלישי
		</xsl:when>
		</xsl:choose>
		</td>
		</xsl:when>
		<xsl:otherwise>
		<td align="center"><xsl:text >- </xsl:text></td>
		</xsl:otherwise>
		</xsl:choose>
		<td align="center"><xsl:text >-</xsl:text></td>
		<td align="center"><xsl:text >-</xsl:text></td>
		<td align="center"><xsl:text >-</xsl:text></td>
		<xsl:choose>
		<xsl:when test="base/pronoun/@type='personal'">
		<td align="center" >כינוי <xsl:element name="BR"/>נושא</td>
		</xsl:when>
		<xsl:when test="base/pronoun/@type='demonstrative'">
		<td align="center" >כינוי<xsl:element name="BR"/> רומז</td>
		</xsl:when>
		<xsl:when test="base/pronoun/@type='interrogative'">
		<td align="center" >כינוי <xsl:element name="BR"/>שאלה</td>
		</xsl:when>
		<xsl:when test="base/pronoun/@type='relativizer'">
		<td align="center" >כינוי <xsl:element name="BR"/>זיקה</td>
		</xsl:when>
		<xsl:when test="base/pronoun/@type='impersonal'">
		<td align="center" >כינוי<xsl:element name="BR"/> סתמי</td>
		</xsl:when>
		<xsl:when test="base/pronoun/@type='reflexive'">
		<td align="center" >כינוי<xsl:element name="BR"/> חוזר</td>
		</xsl:when>
		<xsl:when test="base/pronoun/@type='unspecified'">
		<td align="center" >לא<xsl:element name="BR"/> מוגדר</td>
		</xsl:when>
		<xsl:otherwise>
		<td align="center"><xsl:text >-</xsl:text></td>
		</xsl:otherwise>
		</xsl:choose>
		<td align="center"><xsl:text >-</xsl:text></td>
		<td align="center"><xsl:text >-</xsl:text></td>
	
		
		<xsl:choose>
		<xsl:when test="base/pronoun/@register='formal'">
		<td align="center">תקני</td>
		</xsl:when>
		<xsl:when test="base/pronoun/@register='archaic'">
		<td align="center" >ארכאי<xsl:element name="BR"/> לא<xsl:element name="BR"/> תקין</td>
		</xsl:when>
		<xsl:when test="base/pronoun/@register='informal'">
		<td align="center" >תת<xsl:element name="BR"/>תקני</td>
		</xsl:when>
		<xsl:when test="base/pronoun/@register='unspecified'">
		<td align="center"><xsl:text >-</xsl:text></td>
		</xsl:when>
		</xsl:choose>

		
		<xsl:choose>
		<xsl:when test="base/pronoun/@spelling='standard'">
		<td align="center">תקני</td>
		</xsl:when>
		<xsl:when test="base/pronoun/@spelling='irregular'">
		<td align="center" >תת<xsl:element name="BR"/>תקני </td>
		</xsl:when>
		</xsl:choose>


		</xsl:if>
		<xsl:if test="base/adverb">
		<td align="center" bgcolor="#FFCC33"><b>
		תואר <xsl:element name="BR"/>הפועל
		</b></td>
		<xsl:choose>
		<xsl:when test="prefix and base/adverb">
		
		<td align="center">
		
		<xsl:for-each select="prefix">
		<xsl:value-of select="concat(@function,'')"/>
		<xsl:element name="BR"/>
		</xsl:for-each>
		
		</td>
		</xsl:when>
		<xsl:otherwise>
		<td align="center"><xsl:text >- </xsl:text></td>
		</xsl:otherwise>
		</xsl:choose>
		<td align="center"><xsl:text >-</xsl:text></td>
		<td align="center"><xsl:text >-</xsl:text></td>
		<td align="center"><xsl:text >-</xsl:text></td>
		<td align="center"><xsl:text >-</xsl:text></td>
	
		<xsl:choose>
<xsl:when test="suffix">
<td align="center" >הטייה
<xsl:element name="BR"/>
<xsl:choose>
<xsl:when test="suffix/@gender='masculine'">
זכר
</xsl:when>
<xsl:when test="suffix/@gender='feminine'">
נקבה
</xsl:when>
<xsl:when test="suffix/@gender='masculine and feminine'">
זכר וגם נקבה
</xsl:when>

</xsl:choose>
<xsl:element name="BR"/>
<xsl:choose>
<xsl:when test="suffix/@number='singular'">
יחיד
</xsl:when>
<xsl:when test="suffix/@number='plural'">
רבים
</xsl:when>
</xsl:choose>
<xsl:element name="BR"/>
גוף  <xsl:value-of select="suffix/@person"/>
</td>
<td align="center"><xsl:text >-</xsl:text></td>
<td align="center"><xsl:text >-</xsl:text></td>
		<td align="center"><xsl:text >-</xsl:text></td>
		<td align="center"><xsl:text >-</xsl:text></td>
</xsl:when>
<xsl:otherwise>
		<td align="center"><xsl:text >-</xsl:text></td>
		<td align="center"><xsl:text >-</xsl:text></td>
		<td align="center"><xsl:text >-</xsl:text></td>
		<td align="center"><xsl:text >-</xsl:text></td>
		<td align="center"><xsl:text >-</xsl:text></td>
		
		
</xsl:otherwise>
</xsl:choose>
		<td align="center"><xsl:text >-</xsl:text></td>
		<td align="center"><xsl:text >-</xsl:text></td>
		<td align="center"><xsl:text >-</xsl:text></td>
		<xsl:choose>
		<xsl:when test="base/adverb/@register='formal'">
		<td align="center">תקני</td>
		</xsl:when>
		<xsl:when test="base/adverb/@register='archaic'">
		<td align="center" >ארכאי</td>
		</xsl:when>
		<xsl:when test="base/adverb/@register='informal'">
		<td align="center" >תת <xsl:element name="BR"/>תקני</td>
		</xsl:when>
		<xsl:when test="base/adverb/@register='unspecified'">
		<td align="center"><xsl:text >-</xsl:text></td>
		</xsl:when>
		</xsl:choose>

		
		<xsl:choose>
		<xsl:when test="base/adverb/@spelling='standard'">
		<td align="center">תקני</td>
		</xsl:when>
		<xsl:when test="base/adverb/@spelling='irregular'">
		<td align="center" >תת<xsl:element name="BR"/>תקני </td>
		</xsl:when>
		</xsl:choose>
		</xsl:if>




		<xsl:if test="base/numberExpression">
		<td align="center"  bgcolor="#996600">
		<b>
		ביטוי<xsl:element name="BR"/> מספרי
		</b></td>
		<td align="center"><xsl:text >-</xsl:text></td>
		<td align="center"><xsl:text >-</xsl:text></td>
		<td align="center"><xsl:text >-</xsl:text></td>
		<td align="center"><xsl:text >-</xsl:text></td>
		<td align="center"><xsl:text >-</xsl:text></td>
		<td align="center"><xsl:text >-</xsl:text></td>
		<td align="center"><xsl:text >-</xsl:text></td>
		<td align="center"><xsl:text >-</xsl:text></td>
		<td align="center"><xsl:text >-</xsl:text></td>
		<xsl:choose>
		<xsl:when test="base/numberExpression/@type='date'">
		<td align="center">תאריך</td>
		</xsl:when>
		<xsl:when test="base/numberExpression/@type='gameScore'">
		<td align="center" >תוצאת<xsl:element name="BR"/> משחק</td>
		</xsl:when>
		<xsl:when test="base/numberExpression/@type='time'">
		<td align="center" >זמן</td>
		</xsl:when>
		</xsl:choose>
		<td align="center"><xsl:text >-</xsl:text></td>
		<td align="center"><xsl:text >-</xsl:text></td>
		<td align="center"><xsl:text >-</xsl:text></td>
		<td align="center"><xsl:text >-</xsl:text></td>
		</xsl:if>


		<xsl:if test="base/negation">
		<td align="center"  bgcolor="#00FFCC"><b>
		מילת <xsl:element name="BR"/>שלילה
		</b></td>
		<xsl:choose>
		<xsl:when test="prefix and base/negation">
		
		<td align="center">
		
		<xsl:for-each select="prefix">
		<xsl:value-of select="concat(@function,'')"/>
		<xsl:element name="BR"/>
		</xsl:for-each>
		
		</td>
		</xsl:when>
		<xsl:otherwise>
		<td align="center"><xsl:text >- </xsl:text></td>
		</xsl:otherwise>
		</xsl:choose>
		<td align="center"><xsl:text >-</xsl:text></td>
		<td align="center"><xsl:text >-</xsl:text></td>
		<td align="center"><xsl:text >-</xsl:text></td>
		<xsl:choose>
		<xsl:when test="base/negation/@definiteness='true'">
		<td align="center" >מיודע</td>
		</xsl:when>
		<xsl:when test="base/negation/@definiteness='false'">
		<td align="center" >לא  

		<xsl:element name="BR"/>

		 מיודע</td>
		</xsl:when>
		<xsl:otherwise>
		<td><xsl:text > </xsl:text></td>
		</xsl:otherwise>
		</xsl:choose>
		<td align="center"><xsl:text >-</xsl:text></td>
		<td align="center"><xsl:text >-</xsl:text></td>
		<td align="center"><xsl:text >-</xsl:text></td>
		<td align="center"><xsl:text >-</xsl:text></td>
		<td align="center"><xsl:text >-</xsl:text></td>
		<td align="center"><xsl:text >-</xsl:text></td>
		<td align="center"><xsl:text >-</xsl:text></td>
		<td align="center"><xsl:text >-</xsl:text></td>
		<xsl:choose>
		<xsl:when test="base/negation/@register='formal'">
		<td align="center">תקני</td>
		</xsl:when>
		<xsl:when test="base/negation/@register='archaic'">
		<td align="center" >ארכאי</td>
		</xsl:when>
		<xsl:when test="base/negation/@register='informal'">
		<td align="center" >תת <xsl:element name="BR"/>תקני</td>
		</xsl:when>
		<xsl:when test="base/negation/@register='unspecified'">
		<td align="center"><xsl:text >-</xsl:text></td>
		</xsl:when>
		</xsl:choose>

		
		<xsl:choose>
		<xsl:when test="base/negation/@spelling='standard'">
		<td align="center">תקני</td>
		</xsl:when>
		<xsl:when test="base/negationb/@spelling='irregular'">
		<td align="center" >תת<xsl:element name="BR"/>תקני </td>
		</xsl:when>
		</xsl:choose>
		</xsl:if>



		<xsl:if test="base/wPrefix">
		<td align="center" bgcolor="#CC33CC"><b>
		מילת
		<xsl:element name="BR"/>
		תחילית
		</b></td>
		<xsl:choose>
		<xsl:when test="prefix and base/wPrefix">
		
		<td align="center">
		
		<xsl:for-each select="prefix">
		<xsl:value-of select="concat(@function,'')"/>
		<xsl:element name="BR"/>
		</xsl:for-each>
		
		</td>
		</xsl:when>
		<xsl:otherwise>
		<td align="center"><xsl:text >- </xsl:text></td>
		</xsl:otherwise>
		</xsl:choose>
		<xsl:choose>
		<xsl:when test="base/wPrefix/@gender='masculine'">
		<td align="center" >זכר</td>
		</xsl:when>
		<xsl:when test="base/wPrefix/@gender='feminine'">
		<td align="center" >נקבה</td>
		</xsl:when>
		<xsl:when test="base/wPrefix/@gender='masculine and feminine'">
		<td align="center" >זכר וגם נקבה</td>
		</xsl:when>
		<xsl:otherwise>
		<td align="center"><xsl:text >- </xsl:text></td>
		</xsl:otherwise>
		</xsl:choose>
		<td align="center"><xsl:text >-</xsl:text></td>
		<td align="center"><xsl:text >-</xsl:text></td>
		<td align="center"><xsl:text >-</xsl:text></td>
		<td align="center"><xsl:text >-</xsl:text></td>
		<td align="center"><xsl:text >-</xsl:text></td>
		<td align="center"><xsl:text >-</xsl:text></td>
		<td align="center"><xsl:text >-</xsl:text></td>
		<td align="center"><xsl:text >-</xsl:text></td>
		<td align="center"><xsl:text >-</xsl:text></td>
		<xsl:choose>
		<xsl:when test="base/wPrefix/@polarity='negative'">
		<td align="center">שלילי</td>
		</xsl:when>
		<xsl:when test="base/wPrefix/@polarity='positive'">
		<td align="center">חיובי</td>
		</xsl:when>
		<xsl:otherwise>
		<td align="center">-</td>
		</xsl:otherwise>
		</xsl:choose>
		<td align="center"><xsl:text >-</xsl:text></td>
		
		<xsl:choose>
		<xsl:when test="base/wPrefix/@register='formal'">
		<td align="center">תקני</td>
		</xsl:when>
		<xsl:when test="base/wPrefix/@register='archaic'">
		<td align="center" >ארכאי</td>
		</xsl:when>
		<xsl:when test="base/wPrefix/@register='informal'">
		<td align="center" >תת <xsl:element name="BR"/>תקני</td>
		</xsl:when>
		<xsl:when test="base/wPrefix/@register='unspecified'">
		<td align="center"><xsl:text >-</xsl:text></td>
		</xsl:when>
		</xsl:choose>

		
		<xsl:choose>
		<xsl:when test="base/wPrefix/@spelling='standard'">
		<td align="center">תקני</td>
		</xsl:when>
		<xsl:when test="base/wPrefix/@spelling='irregular'">
		<td align="center" >תת<xsl:element name="BR"/>תקני </td>
		</xsl:when>
		</xsl:choose>
		</xsl:if>


		<xsl:if test="base/conjunction">
		<td align="center" bgcolor="#CC6699"><b>
		מילת<xsl:element name="BR"/> חיבור
		</b></td>
		<xsl:choose>
		<xsl:when test="prefix and base/conjunction">
		
		<td align="center">
		
		<xsl:for-each select="prefix">
		<xsl:value-of select="concat(@function,'')"/>
		<xsl:element name="BR"/>
		</xsl:for-each>
		
		</td>
		</xsl:when>
		<xsl:otherwise>
		<td align="center"><xsl:text >- </xsl:text></td>
		</xsl:otherwise>
		</xsl:choose>
		<td align="center"><xsl:text >-</xsl:text></td>
		<td align="center"><xsl:text >-</xsl:text></td>
		<td align="center"><xsl:text >-</xsl:text></td>
		<td align="center"><xsl:text >-</xsl:text></td>
		<td align="center"><xsl:text >-</xsl:text></td>
		<td align="center"><xsl:text >-</xsl:text></td>
		<td align="center"><xsl:text >-</xsl:text></td>
		<td align="center"><xsl:text >-</xsl:text></td>
		<td align="center"><xsl:text >-</xsl:text></td>
		
		<xsl:choose>
		<xsl:when test="base/conjunction/@type='coordinating'">
		<td align="center">מילת<xsl:element name="BR"/> קישור</td>
		</xsl:when>
		<xsl:when test="base/conjunction/@type='subordinating'">
		<td align="center">מילת<xsl:element name="BR"/> שיעבוד</td>
		</xsl:when>
		<xsl:when test="base/conjunction/@type='relativizing'">
		<td align="center">מילת<xsl:element name="BR"/> זיקה</td>
		</xsl:when>
		<xsl:otherwise>
		<td align="center"><xsl:text >- </xsl:text></td>
		</xsl:otherwise>
		</xsl:choose>


		<td align="center"><xsl:text >-</xsl:text></td>
		<td align="center"><xsl:text >-</xsl:text></td>
		<xsl:choose>
		<xsl:when test="base/conjunction/@register='formal'">
		<td align="center">תקני</td>
		</xsl:when>
		<xsl:when test="base/conjunction/@register='archaic'">
		<td align="center" >ארכאי</td>
		</xsl:when>
		<xsl:when test="base/conjunction/@register='informal'">
		<td align="center" >תת <xsl:element name="BR"/>תקני</td>
		</xsl:when>
		<xsl:when test="base/conjunction/@register='unspecified'">
		<td align="center"><xsl:text >-</xsl:text></td>
		</xsl:when>
		</xsl:choose>

		
		<xsl:choose>
		<xsl:when test="base/conjunction/@spelling='standard'">
		<td align="center">תקני</td>
		</xsl:when>
		<xsl:when test="base/conjunction/@spelling='irregular'">
		<td align="center" >תת<xsl:element name="BR"/>תקני </td>
		</xsl:when>
		</xsl:choose>
		</xsl:if>


		<xsl:if test="base/interjection">
		<td align="center" bgcolor="#CC6666"><b>
		מילת <xsl:element name="BR"/>קריאה
		</b></td>
		<xsl:choose>
		<xsl:when test="prefix and base/interjection">
		
		<td align="center">
		
		<xsl:for-each select="prefix">
		<xsl:value-of select="concat(@function,'')"/>
		<xsl:element name="BR"/>
		</xsl:for-each>
		
		</td>
		</xsl:when>
		<xsl:otherwise>
		<td align="center"><xsl:text >- </xsl:text></td>
		</xsl:otherwise>
		</xsl:choose>
		<td align="center"><xsl:text >-</xsl:text></td>
		<td align="center"><xsl:text >-</xsl:text></td>
		<td align="center"><xsl:text >-</xsl:text></td>
		<td align="center"><xsl:text >-</xsl:text></td>
		<td align="center"><xsl:text >-</xsl:text></td>
		<xsl:choose>
		<xsl:when test="suffix">
			<td align="center" >הטייה
			<xsl:element name="BR"/>
			<xsl:choose>
				<xsl:when test="suffix/@gender='masculine'">
					זכר
				</xsl:when>
				<xsl:when test="suffix/@gender='feminine'">
					נקבה		
				</xsl:when>
			<xsl:when test="suffix/@gender='masculine and feminine'">
					זכר וגם נקבה
				</xsl:when>

			</xsl:choose>
			<xsl:element name="BR"/>
			<xsl:choose>
				<xsl:when test="suffix/@number='singular'">
					יחיד
				</xsl:when>
				<xsl:when test="suffix/@number='plural'">
					רבים
				</xsl:when>
			</xsl:choose>
			<xsl:element name="BR"/>
			גוף  <xsl:value-of select="suffix/@person"/>
				</td>
			
		</xsl:when>
		<xsl:otherwise>

			<td align="center"><xsl:text >-</xsl:text></td>
		</xsl:otherwise>
		</xsl:choose>
		
		<td align="center"><xsl:text >-</xsl:text></td>
		<td align="center"><xsl:text >-</xsl:text></td>
		<td align="center"><xsl:text >-</xsl:text></td>
		<td align="center"><xsl:text >-</xsl:text></td>
		<td align="center"><xsl:text >-</xsl:text></td>
		<td align="center"><xsl:text >-</xsl:text></td>
		<xsl:choose>
		<xsl:when test="base/interjection/@register='formal'">
		<td align="center">תקני</td>
		</xsl:when>
		<xsl:when test="base/interjection/@register='archaic'">
		<td align="center" >ארכאי</td>
		</xsl:when>
		<xsl:when test="base/interjection/@register='informal'">
		<td align="center" >תת <xsl:element name="BR"/>תקני</td>
		</xsl:when>
		<xsl:when test="base/interjection/@register='unspecified'">
		<td align="center"><xsl:text >-</xsl:text></td>
		</xsl:when>
		</xsl:choose>

		
		<xsl:choose>
		<xsl:when test="base/interjection/@spelling='standard'">
		<td align="center">תקני</td>
		</xsl:when>
		<xsl:when test="base/interjection/@spelling='irregular'">
		<td align="center" >תת<xsl:element name="BR"/>תקני </td>
		</xsl:when>
		</xsl:choose>
		</xsl:if>



		<xsl:if test="base/interrogative">
		<td align="center" bgcolor="#CC3366"><b>
		מילת<xsl:element name="BR"/> שאלה
		</b></td>
		<xsl:choose>
		<xsl:when test="prefix and base/interrogative">
		
		<td align="center">
		
		<xsl:for-each select="prefix">
		<xsl:value-of select="concat(@function,'')"/>
		<xsl:element name="BR"/>
		</xsl:for-each>
		
		</td>
		</xsl:when>
		<xsl:otherwise>
		<td align="center"><xsl:text >- </xsl:text></td>
		</xsl:otherwise>
		</xsl:choose>
		<td align="center"><xsl:text >-</xsl:text></td>
		<td align="center"><xsl:text >-</xsl:text></td>
		<td align="center"><xsl:text >-</xsl:text></td>
		<td align="center"><xsl:text >-</xsl:text></td>
		<td align="center"><xsl:text >-</xsl:text></td>
		<td align="center"><xsl:text >-</xsl:text></td>
		<td align="center"><xsl:text >-</xsl:text></td>
		<td align="center"><xsl:text >-</xsl:text></td>
		<td align="center"><xsl:text >-</xsl:text></td>
		<xsl:choose>
		<xsl:when test="base/interrogative/@type='pronoun'">
		<td align="center">כינוי גוף</td>
		</xsl:when>
		<xsl:when test="base/interrogative/@type='proadverb'">
		<td align="center">תואר הפועל</td>
		</xsl:when>
		<xsl:when test="base/interrogative/@type='prodet'">
		<td align="center">מצביע</td>
		</xsl:when>
		<xsl:when test="base/interrogative/@type='yesno'">
		<td align="center">תמיהה</td>
		</xsl:when>
		<xsl:otherwise>
			<td align="center"><xsl:text >-</xsl:text></td>
		</xsl:otherwise>
		</xsl:choose>
		<td align="center"><xsl:text >-</xsl:text></td>
		<td align="center"><xsl:text >-</xsl:text></td>
		<xsl:choose>
		<xsl:when test="base/interrogative/@register='formal'">
		<td align="center">תקני</td>
		</xsl:when>
		<xsl:when test="base/interrogative/@register='archaic'">
		<td align="center" >ארכאי</td>
		</xsl:when>
		<xsl:when test="base/interrogative/@register='informal'">
		<td align="center" >תת <xsl:element name="BR"/>תקני</td>
		</xsl:when>
		<xsl:when test="base/interrogative/@register='unspecified'">
		<td align="center"><xsl:text >-</xsl:text></td>
		</xsl:when>
		</xsl:choose>

		
		<xsl:choose>
		<xsl:when test="base/interrogative/@spelling='standard'">
		<td align="center">תקני</td>
		</xsl:when>
		<xsl:when test="base/interrogative/@spelling='irregular'">
		<td align="center" >תת<xsl:element name="BR"/>תקני </td>
		</xsl:when>
		</xsl:choose>
		</xsl:if>




		<xsl:if test="base/preposition">
		<td align="center" bgcolor="#C6DEFF"><b>
		מילת<xsl:element name="BR"/> יחס
		</b></td>
		<xsl:choose>
		<xsl:when test="prefix and base/preposition">
		
		<td align="center">
		
		<xsl:for-each select="prefix">
		<xsl:value-of select="concat(@function,'')"/>
		<xsl:element name="BR"/>
		</xsl:for-each>
		
		</td>
		</xsl:when>
		<xsl:otherwise>
		<td align="center"><xsl:text >- </xsl:text></td>
		</xsl:otherwise>
		</xsl:choose>
		<td align="center"><xsl:text >-</xsl:text></td>
		<td align="center"><xsl:text >-</xsl:text></td>
		<td align="center"><xsl:text >-</xsl:text></td>
		<td align="center"><xsl:text >-</xsl:text></td>
		<td align="center"><xsl:text >-</xsl:text></td>
		<xsl:choose>
		<xsl:when test="suffix">
			<td align="center">
				הטייה	
			<xsl:element name="BR"/>
			<xsl:choose>
				<xsl:when test="suffix/@gender='masculine'">
					זכר
				</xsl:when>
				<xsl:when test="suffix/@gender='feminine'">
					נקבה 
				</xsl:when>
				<xsl:when test="suffix/@gender='masculine and feminine'">
				זכר וגם נקבה
				</xsl:when>
			</xsl:choose>
			<xsl:element name="BR"/>
			<xsl:choose>
				<xsl:when test="suffix/@number='singular'">
					יחיד
				</xsl:when>
				<xsl:when test="suffix/@number='plural'">
					רבים
				</xsl:when>
				<xsl:when test="suffix/@number='dual'">
					זוגי
				</xsl:when>
			</xsl:choose>
			<xsl:element name="BR"/>
			<xsl:choose>
				<xsl:when test="suffix/@person='1'">
				גוף ראשון
				</xsl:when>
				<xsl:when test="suffix/@person='2'">
				גוף שני
				</xsl:when>
				<xsl:when test="suffix/@person='3'">
				גוף שלישי
				</xsl:when>
			</xsl:choose>
		</td>
		</xsl:when>
		<xsl:otherwise>
			<td align="center"><xsl:text >-</xsl:text></td>
		</xsl:otherwise>
		</xsl:choose>
		<td align="center"><xsl:text >-</xsl:text></td>
		<td align="center"><xsl:text >-</xsl:text></td>
		<td align="center"><xsl:text >-</xsl:text></td>
		<td align="center"><xsl:text >-</xsl:text></td>
		<td align="center"><xsl:text >-</xsl:text></td>
		<td align="center"><xsl:text >-</xsl:text></td>
		<xsl:choose>
		<xsl:when test="base/preposition/@register='formal'">
		<td align="center">תקני</td>
		</xsl:when>
		<xsl:when test="base/preposition/@register='archaic'">
		<td align="center" >ארכאי</td>
		</xsl:when>
		<xsl:when test="base/preposition/@register='informal'">
		<td align="center" >תת <xsl:element name="BR"/>
תקני</td>
		</xsl:when>
		<xsl:when test="base/preposition/@register='unspecified'">
		<td align="center"><xsl:text >-</xsl:text></td>
		</xsl:when>
		</xsl:choose>

		
		<xsl:choose>
		<xsl:when test="base/preposition/@spelling='standard'">
		<td align="center">תקני</td>
		</xsl:when>
		<xsl:when test="base/preposition/@spelling='irregular'">
		<td align="center" >תת<xsl:element name="BR"/>תקני </td>
		</xsl:when>
		</xsl:choose>
		</xsl:if>
		

		<xsl:if test="base/quantifier">
		<td align="center" bgcolor="#FDEEF4">
		כמת
		</td>
		<xsl:choose>
		<xsl:when test="prefix and base/quantifier">
		
		<td align="center">
		
		<xsl:for-each select="prefix">
		<xsl:value-of select="concat(@function,'')"/>
		<xsl:element name="BR"/>
		</xsl:for-each>
		
		</td>
		</xsl:when>
		<xsl:otherwise>
		<td align="center"><xsl:text >- </xsl:text></td>
		</xsl:otherwise>
		</xsl:choose>
		<td align="center"><xsl:text >-</xsl:text></td>
		<td align="center"><xsl:text >-</xsl:text></td>
		<xsl:choose>
		<xsl:when test="base/quantifier/@status='construct'">
		<td align="center" >נסמך</td>
		</xsl:when>
		<xsl:otherwise>
		<td align="center">נפרד</td>
		</xsl:otherwise>
		</xsl:choose>
		
		<xsl:choose>
		<xsl:when test="base/quantifier/@definiteness='true'">
		<td align="center" >מיודע</td>
		</xsl:when>
		<xsl:when test="base/quantifier/@definiteness='false'">
		<td align="center" >לא  

		<xsl:element name="BR"/>

		 מיודע</td>
		</xsl:when>
		<xsl:otherwise>
		<td align="center"><xsl:text >-</xsl:text></td>
		</xsl:otherwise>
		</xsl:choose>
		<td align="center"><xsl:text >-</xsl:text></td>
		<xsl:choose>
		<xsl:when test="suffix">
		<td align="center" >הטייה
		<xsl:element name="BR"/>
		<xsl:choose>
		<xsl:when test="suffix/@gender='masculine'">
		זכר
		</xsl:when>
		<xsl:when test="suffix/@gender='feminine'">
		נקבה 
		</xsl:when>
		<xsl:when test="suffix/@gender='masculine and feminine'">
		זכר וגם נקבה
		</xsl:when>
		</xsl:choose>
		<xsl:element name="BR"/>
		<xsl:choose>
		<xsl:when test="suffix/@number='singular'">
		יחיד
		</xsl:when>
		<xsl:when test="suffix/@number='plural'">
		רבים
		</xsl:when>
		<xsl:when test="suffix/@number='dual'">
		זוגי
		</xsl:when>
		</xsl:choose>
		<xsl:element name="BR"/>
		<xsl:choose>
		<xsl:when test="suffix/@person='1'">
		גוף ראשון
		</xsl:when>
		<xsl:when test="suffix/@person='2'">
		גוף שני
		</xsl:when>
		<xsl:when test="suffix/@person='3'">
		גוף שלישי
		</xsl:when>
		</xsl:choose>
		</td>
		</xsl:when>
		<xsl:otherwise>
		<td align="center"><xsl:text >- </xsl:text></td>
		</xsl:otherwise>
		</xsl:choose>
		<td align="center"><xsl:text >-</xsl:text></td>
		<td align="center"><xsl:text >-</xsl:text></td>
		<td align="center"><xsl:text >-</xsl:text></td>
		
		<xsl:choose>
		<xsl:when test="base/quantifier/@type='amount'">
		<td align="center" >כמותי</td>
		</xsl:when>
		<xsl:when test="base/quantifier/@type='partitive'">
		<td align="center" >פרטטיבי</td>
		</xsl:when>
		<xsl:when test="base/quantifier/@type='determiner'">
		<td align="center" >מיידע</td>
		</xsl:when>
		<xsl:otherwise>
		<td align="center"><xsl:text >-</xsl:text></td>
		</xsl:otherwise>
		</xsl:choose>



		<td align="center"><xsl:text >-</xsl:text></td>
		<td align="center"><xsl:text >-</xsl:text></td>
		<xsl:choose>
		<xsl:when test="base/quantifier/@register='formal'">
		<td align="center">תקני</td>
		</xsl:when>
		<xsl:when test="base/quantifier/@register='archaic'">
		<td align="center" >ארכאי</td>
		</xsl:when>
		<xsl:when test="base/quantifier/@register='informal'">
		<td align="center" >תת<xsl:element name="BR"/> תקני</td>
		</xsl:when>
		<xsl:when test="base/quantifier/@register='unspecified'">
		<td align="center"><xsl:text >-</xsl:text></td>
		</xsl:when>
		</xsl:choose>

		
		<xsl:choose>
		<xsl:when test="base/quantifier/@spelling='standard'">
		<td align="center">תקני</td>
		</xsl:when>
		<xsl:when test="base/quantifier/@spelling='irregular'">
		<td align="center" >תת<xsl:element name="BR"/>תקני </td>
		</xsl:when>
		</xsl:choose>
		</xsl:if>


		<xsl:if test="base/properName">
			<td align="center" bgcolor="#CCCCCC"><b>
		שם<xsl:element name="BR"/> פרטי
		</b></td>
		<xsl:choose>
		<xsl:when test="prefix and base/properName">
		
		<td align="center">
		
		<xsl:for-each select="prefix">
		<xsl:value-of select="concat(@function,'')"/>
		<xsl:element name="BR"/>
		</xsl:for-each>
		
		</td>
		</xsl:when>
		<xsl:otherwise>
		<td align="center"><xsl:text >- </xsl:text></td>
		</xsl:otherwise>
		</xsl:choose>
		<xsl:choose>
		<xsl:when test="base/properName/@gender='masculine'">
		<td align="center" >זכר</td>
		</xsl:when>
		<xsl:when test="base/properName/@gender='feminine'">
		<td align="center" >נקבה</td>
		</xsl:when>
		<xsl:when test="base/properName/@gender='masculine and feminine'">
		<td align="center" >זכר <xsl:element name="BR"/>וגם<xsl:element 		name="BR"/> נקבה</td>
		</xsl:when>
		<xsl:otherwise>
		<td align="center"><xsl:text >-</xsl:text></td>
		</xsl:otherwise>
		</xsl:choose>
		
		<xsl:choose>
		<xsl:when test="base/properName/@number='singular'">
		<td align="center" >יחיד</td>
		</xsl:when>
		<xsl:when test="base/properName/@number='plural'">
		<td align="center" >רבים</td>
		</xsl:when>
		<xsl:otherwise>
		<td align="center"><xsl:text >-</xsl:text></td>
		</xsl:otherwise>
		</xsl:choose>
		
	
		<td align="center"><xsl:text >-</xsl:text></td>
		<xsl:choose>
		<xsl:when test="base/properName/@definiteness='true'">
		<td align="center" >מיודע</td>
		</xsl:when>
		<xsl:when test="base/properName/@definiteness='false'">
		<td align="center" >לא  

		<xsl:element name="BR"/>

		 מיודע</td>
		</xsl:when>
		<xsl:otherwise>
		<td align="center"><xsl:text >-</xsl:text></td>
		</xsl:otherwise>
		</xsl:choose>

		<td align="center"><xsl:text >-</xsl:text></td>
		<td align="center"><xsl:text >-</xsl:text></td>
		<td align="center"><xsl:text >-</xsl:text></td>
		<td align="center"><xsl:text >-</xsl:text></td>
		<td align="center"><xsl:text >-</xsl:text></td>
		
		
	<xsl:choose>
	<xsl:when test="base/properName/@type='person'">
	<td align="center" >שם 
	<xsl:element name="BR"/>
	של אדם</td>
	</xsl:when>
	<xsl:when test="base/properName/@type='location'">
	<td align="center" >שם 
	<xsl:element name="BR"/>
	של מקום</td>
	</xsl:when>
	<xsl:when test="base/properName/@type='organization'">
	<td align="center" >שם
	<xsl:element name="BR"/>
	 של ארגון</td>
	</xsl:when>
	<xsl:when test="base/properName/@type='product'">
	<td align="center" >שם
	<xsl:element name="BR"/> 
	של מוצר</td>
	</xsl:when>
	<xsl:when test="base/properName/@type='country'">
	<td align="center" >שם 
	<xsl:element name="BR"/>
	של ארץ</td>
	</xsl:when>
	<xsl:when test="base/properName/@type='symbol'">
	<td align="center" >
	אות או תו</td>
	</xsl:when>
	<xsl:when test="base/properName/@type='other'">
	<td align="center" >אחר</td>
	</xsl:when>
	<xsl:when test="base/properName/@type='dateTime'">
	<td align="center" >תאריך</td>
	</xsl:when>
	<xsl:when test="base/properName/@type='language'">
	<td align="center" >שפה</td>
	</xsl:when>
	<xsl:when test="base/properName/@type='town'">
	<td align="center" >יישוב</td>
	</xsl:when>
	<xsl:when test="base/properName/@type='unspecified'">
	<td align="center" >סוג
	<xsl:element name="BR"/> 
	לא מוגדר</td>
	</xsl:when>
	<xsl:otherwise>
		<td align="center"><xsl:text >- </xsl:text></td>
	</xsl:otherwise>
	</xsl:choose>
	<td align="center"><xsl:text >-</xsl:text></td>
	<td align="center"><xsl:text >-</xsl:text></td>

		<xsl:choose>
		<xsl:when test="base/properName/@register='formal'">
		<td align="center">תקני</td>
		</xsl:when>
		<xsl:when test="base/properName/@register='archaic'">
		<td align="center" >ארכאי</td>
		</xsl:when>
		<xsl:when test="base/properName/@register='informal'">
		<td align="center" >תת<xsl:element name="BR"/> תקני</td>
		</xsl:when>
		<xsl:when test="base/properName/@register='unspecified'">
		<td align="center"><xsl:text >-</xsl:text></td>
		</xsl:when>
		</xsl:choose>


		
		<xsl:choose>
		<xsl:when test="base/properName/@spelling='standard'">
		<td align="center">תקני</td>
		</xsl:when>
		<xsl:when test="base/properName/@spelling='irregular'">
		<td align="center" >תת<xsl:element name="BR"/>תקני </td>
		</xsl:when>
		<xsl:otherwise>
		<td align="center"><xsl:text >-</xsl:text></td>
		</xsl:otherwise>
		</xsl:choose>
		</xsl:if>

		<xsl:if test="base/existential">
		<td align="center" bgcolor="#EBDDE2"><b>
		כמת<xsl:element name="BR"/> יישי
		</b></td>
		<xsl:choose>
		<xsl:when test="prefix and base/existential">
		
		<td align="center">
		
		<xsl:for-each select="prefix">
		<xsl:value-of select="concat(@function,'')"/>
		<xsl:element name="BR"/>
		</xsl:for-each>
		
		</td>
		</xsl:when>
		<xsl:otherwise>
		<td align="center"><xsl:text >- </xsl:text></td>
		</xsl:otherwise>
		</xsl:choose>
		<xsl:choose>
		<xsl:when test="base/existential/@gender='masculine'">
		<td align="center" >זכר</td>
		</xsl:when>
		<xsl:when test="base/existential/@gender='feminine'">
		<td align="center" >נקבה</td>
		</xsl:when>
		<xsl:when test="base/existential/@gender='masculine and feminine'">
		<td align="center" >זכר וגם נקבה</td>
		</xsl:when>
		<xsl:otherwise>
		<td align="center"><xsl:text >- </xsl:text></td>
		</xsl:otherwise>
		</xsl:choose>
		<xsl:choose>
		<xsl:when test="base/existential/@number='singular'">
		<td align="center" >יחיד</td>
		</xsl:when>
		<xsl:when test="base/existential/@number='plural'">
		<td align="center" >רבים</td>
		</xsl:when>
		<xsl:otherwise>
		<td align="center"><xsl:text >- </xsl:text></td>
		</xsl:otherwise>
		</xsl:choose>
		<td align="center"><xsl:text >-</xsl:text></td>

		<xsl:choose>
		<xsl:when test="base/existential/@interrogative='true'">
		<td align="center" >ה'
		<xsl:element name="BR"/>
		השאלה
		</td>
		</xsl:when>
		<xsl:when test="base/existential/@interrogative='false'">
		<td align="center"><xsl:text >-</xsl:text></td>
		</xsl:when>
		<xsl:otherwise>
		<td align="center"><xsl:text >-</xsl:text></td>
		</xsl:otherwise>
		</xsl:choose>
		<td align="center"><xsl:text >-</xsl:text></td>
		
		<xsl:choose>
		<xsl:when test="suffix">
			<td align="center">
				הטייה	
			<xsl:element name="BR"/>
			<xsl:choose>
				<xsl:when test="suffix/@gender='masculine'">
					זכר
				</xsl:when>
				<xsl:when test="suffix/@gender='feminine'">
					נקבה 
				</xsl:when>
				<xsl:when test="suffix/@gender='masculine and feminine'">
				זכר וגם נקבה
				</xsl:when>
			</xsl:choose>
			<xsl:element name="BR"/>
			<xsl:choose>
				<xsl:when test="suffix/@number='singular'">
					יחיד
				</xsl:when>
				<xsl:when test="suffix/@number='plural'">
					רבים
				</xsl:when>
				<xsl:when test="suffix/@number='dual'">
					זוגי
				</xsl:when>
			</xsl:choose>
			<xsl:element name="BR"/>
			<xsl:choose>
				<xsl:when test="suffix/@person='1'">
				גוף ראשון
				</xsl:when>
				<xsl:when test="suffix/@person='2'">
				גוף שני
				</xsl:when>
				<xsl:when test="suffix/@person='3'">
				גוף שלישי
				</xsl:when>
			</xsl:choose>
		</td>
		</xsl:when>
		<xsl:otherwise>
			<td align="center"><xsl:text >-</xsl:text></td>
		</xsl:otherwise>
		</xsl:choose>
		
		<xsl:choose>
		<xsl:when test="base/existential/@tense='past'">
		<td align="center" >עבר</td>
		</xsl:when>
		<xsl:when test="base/existential/@tense='future'">
		<td align="center" >עתיד</td>
		</xsl:when>
		<xsl:otherwise>
			<td align="center"><xsl:text >-</xsl:text></td>
		</xsl:otherwise>
		</xsl:choose>
		<td align="center"><xsl:text >-</xsl:text></td>
		<td align="center"><xsl:text >-</xsl:text></td>
		<td align="center"><xsl:text >-</xsl:text></td>
		<xsl:choose>
		<xsl:when test="base/existential/@polarity='negative'">
		<td align="center">שלילי</td>
		</xsl:when>
		<xsl:when test="base/existential/@polarity='positive'">
		<td align="center">חיובי</td>
		</xsl:when>
		<xsl:otherwise>
		<td align="center"><xsl:text >-</xsl:text></td>
		</xsl:otherwise>
		</xsl:choose>
		<td align="center"><xsl:text >-</xsl:text></td>
		<xsl:choose>
		<xsl:when test="base/existential/@register='formal'">
		<td align="center">תקני</td>
		</xsl:when>
		<xsl:when test="base/existential/@register='archaic'">
		<td align="center" >ארכאי</td>
		</xsl:when>
		<xsl:when test="base/existential/@register='informal'">
		<td align="center" >תת<xsl:element name="BR"/> תקני</td>
		</xsl:when>
		<xsl:when test="base/existential/@register='unspecified'">
		<td align="center"><xsl:text >-</xsl:text></td>
		</xsl:when>
		</xsl:choose>

		
		<xsl:choose>
		<xsl:when test="base/existential/@spelling='standard'">
		<td align="center">תקני</td>
		</xsl:when>
		<xsl:when test="base/existential/@spelling='irregular'">
		<td align="center" >תת<xsl:element name="BR"/>תקני </td>
		</xsl:when>
		</xsl:choose>
		</xsl:if>
		<xsl:if test="base/modal">
		<td align="center" bgcolor="#C8BBBE">
		<b>מודאל</b> 
		</td>
		<xsl:choose>
		<xsl:when test="prefix and base/modal">
		
		<td align="center">
		
		<xsl:for-each select="prefix">
		<xsl:value-of select="concat(@function,'')"/>
		<xsl:element name="BR"/>
		</xsl:for-each>
		
		</td>
		</xsl:when>
		<xsl:otherwise>
		<td align="center"><xsl:text >- </xsl:text></td>
		</xsl:otherwise>
		</xsl:choose>
		<xsl:choose>
		<xsl:when test="base/modal/@gender='masculine'">
			<td align="center" >זכר</td>
		</xsl:when>
		<xsl:when test="base/modal/@gender='feminine'">
			<td align="center" >נקבה</td>
			</xsl:when>
		<xsl:when test="base/modal/@gender='masculine and feminine'">
			<td align="center" > זכר וגם נקבה </td>
		</xsl:when>
		<xsl:otherwise>
			<td align="center"><xsl:text >-</xsl:text></td>
		</xsl:otherwise>
		</xsl:choose>
		<xsl:choose>
		<xsl:when test="base/modal/@number='singular'">
			<td align="center" >יחיד</td>
		</xsl:when>
		<xsl:when test="base/modal/@number='plural'">
			<td align="center" >רבים</td>
		</xsl:when>
		<xsl:when test="base/modal/@number='dual'">
			<td align="center" >זוגי</td>
		</xsl:when>
			<xsl:otherwise>
			<td align="center"><xsl:text >-</xsl:text></td>
		</xsl:otherwise>
		</xsl:choose>
		<xsl:choose>
			<xsl:when test="base/modal/@tense='past'">
				<td align="center" >עבר</td>
			</xsl:when>
			<xsl:when test="base/modal/@tense='future'">
				<td align="center" >עתיד</td>
			</xsl:when>
			<xsl:when test="base/modal/@tense='imperative'">
				<td align="center" >ציווי</td>
			</xsl:when>
			<xsl:when test="base/modal/@tense='infinitive'">
				<td align="center" >שם <xsl:element name="BR"/>פועל</td>
			</xsl:when>
			<xsl:when test="base/modal/@tense='bareInfinitive'">
				<td align="cenetr" >מקור</td>
			</xsl:when>
			<xsl:otherwise>
				<td align="center"><xsl:text >-</xsl:text></td>
			</xsl:otherwise>
			</xsl:choose>
		<td align="center"><xsl:text >-</xsl:text></td>
		<td align="center"><xsl:text >-</xsl:text></td>
		<td align="center"><xsl:text >-</xsl:text></td>
		<td align="center"><xsl:text >-</xsl:text></td>
		<td align="center"><xsl:text >-</xsl:text></td>
		<td align="center"><xsl:text >-</xsl:text></td>
		<td align="center"><xsl:text >-</xsl:text></td>
		<td align="center"><xsl:text >-</xsl:text></td>
		<td align="center"><xsl:text >-</xsl:text></td>
		<xsl:choose>
		<xsl:when test="base/modal/@register='formal'">
		<td align="center">תקני</td>
		</xsl:when>
		<xsl:when test="base/modal/@register='archaic'">
		<td align="center" >ארכאי</td>
		</xsl:when>
		<xsl:when test="base/modal/@register='informal'">
		<td align="center" >תת <xsl:element name="BR"/>תקני</td>
		</xsl:when>
		<xsl:when test="base/modal/@register='unspecified'">
		<td align="center"><xsl:text >-</xsl:text></td>
		</xsl:when>
		</xsl:choose>
		
		
		<xsl:choose>
		<xsl:when test="base/modal/@spelling='standard'">
		<td align="center">תקני</td>
		</xsl:when>
		<xsl:when test="base/modal/@spelling='irregular'">
		<td align="center" >תת<xsl:element name="BR"/>תקני </td>
		</xsl:when>
		</xsl:choose>
		</xsl:if>



		<xsl:if test="base/foreign">
		<td align="center"><xsl:text >-</xsl:text></td>
		<td align="center"><xsl:text >-</xsl:text></td>
		<td align="center"><xsl:text >-</xsl:text></td>
		<td>
		לועזית
		</td>
		<td align="center"><xsl:text >-</xsl:text></td>
		<td align="center"><xsl:text >-</xsl:text></td>
		<td align="center"><xsl:text >-</xsl:text></td>
		<td align="center"><xsl:text >-</xsl:text></td>
		<td align="center"><xsl:text >-</xsl:text></td>
		<td align="center"><xsl:text >-</xsl:text></td>
		<td align="center"><xsl:text >-</xsl:text></td>
		<td align="center"><xsl:text >-</xsl:text></td>
		<td align="center"><xsl:text >-</xsl:text></td>
		<td align="center"><xsl:text >-</xsl:text></td>
		<td align="center"><xsl:text >-</xsl:text></td>
		<td align="center"><xsl:text >-</xsl:text></td>
		<td align="center"><xsl:text >-</xsl:text></td>
		<td align="center"><xsl:text >-</xsl:text></td>
		</xsl:if>
		</tr>
		</xsl:for-each>
		</table>
		
 </xsl:otherwise>
                </xsl:choose>








 
		
		</xsl:for-each>
	
		</p>

 <xsl:variable name="pmax"><xsl:value-of select="count(//corpus/article/paragraph)"/>
                </xsl:variable>
	  <xsl:variable name="smax"><xsl:value-of select="count(//corpus/article/paragraph[@id=$pId]/sentence)"/>
                </xsl:variable>
	<p>
	 <input type="button" onClick="location='http://yeda.cs.technion.ac.il:8088/MWE/index.html'"  value="התחל"></input>
  <xsl:text >            </xsl:text>
<input type="button" onClick="location='detailedPrevNextSentence.jsp?action=prev|{$pId}|{$sId}|{$pmax}|{$smax}'"  value="הקודם"></input>
 <xsl:text >            </xsl:text>
<input type="button" onClick="location='detailedPrevNextSentence.jsp?action=next|{$pId}|{$sId}|{$pmax}|{$smax}'"  value="הבא"></input>
 <xsl:text >            </xsl:text>
<input type="button" onClick="location='showAll.jsp'"  value="הכל"></input>
 <xsl:text >            </xsl:text>
<input type="button" 
onClick="location='http://yeda.cs.technion.ac.il:8088/MWE/showSentenceLimited.jsp?pid=1|1'"  value="מקוצר"></input>
	</p>










	</xsl:if>
	</xsl:for-each>

 </xsl:for-each>
</td>
</tr>
<tr>
<td>
</td>
</tr>
</table>


</center>
</div>
<div id="Footer"> כל הזכויות שמורות ל<a href="http://www.mila.cs.technion.ac.il">מילה</a> </div>
 </form>   
 </body>
   </html>
  </xsl:template>
 </xsl:stylesheet>

