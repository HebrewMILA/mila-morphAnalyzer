﻿<xsl:stylesheet xmlns:xsl ="http://www.w3.org/1999/XSL/Transform" xmlns:myObject="java:XSLOBJ" version ="1.0">
<xsl:template match="/">

<html>
<head>
<title>Hebrew Tagger - HMM Tagger</title>

<style>
body 
{ 
	font-family: Verdana, Geneva, Arial, Helvetica, sans-serif; 
	font-size: 27px;
	text-align: justify; 
	color: black; 
	margin:0px;
}

h3 { color: #336699; font-size: 26px; font-weight: bold; font-variant: normal; margin: 0px; padding: 5px 0 10px 0; }

p 
{ 
	margin: 0px; 
	padding: 0 0 10px 0;
	font-size: 24px;
}

a:link {color: black; text-decoration: underline; text-transform: none; background:; }
a:visited {color: black; text-decoration:underline; text-transform: none; background: ; }
a:active {text-decoration: underline; text-transform: none; background: ; }


#Section 
{
        position: relative;
        top: 0%;
        margin: 0px;
        padding: 0% 0% 0% 0%;
        width: 100%;
}

#Section td 
{
        font-family: Arial, Helvetica, sans-serif;
        font-size: 26px;
}


#SectionHeader 
{
		background:#2580A2;
        position: relative;
        overflow: hidden;
        padding: 0px;
        height: 82px;
        width: 100%;
}


#SectionMenuBackground 
{
	font-family: Arial, Helvetica, sans-serif;
	font-size: 150%;
    text-align: left;
    color: white;
    height: 82px;
    width: 100%;
    margin: 0px;
    padding: 0px;
}


#SectionMenuBackground a
{
        font-size: 100%;
        text-align: center;
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

<script type="text/javascript" src="wz_tooltip.js"></script>  


<div id="Section">
                <div id="SectionHeader">
					<div id="SectionMenuBackground">
				
					<a href="http://www.mila.cs.technion.ac.il/mila/eng/index.html">
							<img width="878" height="82" border="0" alt="MILA: Knowledge Center for Processing Hebrew" src="logo.png"></img>
					</a>
					<!--<img src="images/demos_header.gif" width="100%" height="20"></img>
					<a     href='http://yeda.cs.technion.ac.il:8088/MWE/index.html'>חזור</a> |
					<a    href='http://www.cs.technion.ac.il/~barhaim/MorphTagger/'> תיעוד מתייג</a>|
					<a   href='MorphologicalAnalyzer-ReleaseNotes-1.1.doc'> תיעוד מנתח מורפולוגי</a>|
					<a    href='http://www.mila.cs.technion.ac.il/'>אתר מילה </a> -->
					</div>
				</div>
</div>


<center>
	<h3> HMM Tagger פלט</h3>
	<p>
	לצפייה בניתוח שנבחר, העבירו את הסמן מעל למילה
	<xsl:element name="BR"/>
	לצפייה בכל הניתוחים המורפולוגיים הקליקו על המשפט המבוקש
	</p>
	
	<input type="button" ALT="חזור" onClick="history.go(-1)"  value="    חזור    "></input>

	<table dir="rtl" >
	<tr>
	<td align="center" > 
	<xsl:for-each select="corpus/article/paragraph">
	<xsl:element name="BR"/>
	<xsl:element name="BR"/>

	<bold> 
	<font color="#006400" size="5"> פיסקה #  <xsl:value-of select="@id" /></font ></bold>
	<xsl:variable name="pId"><xsl:value-of select="@id"/>
    </xsl:variable>
	<xsl:for-each select="sentence">
	<xsl:element name="HR" />
	 <xsl:variable name="sId"><xsl:value-of select="@id"/>
                </xsl:variable>
  	<bold>
		<font color="#FF8C00" size="5">משפט # 
			<a href="http://yeda.cs.technion.ac.il:8088/MWE/showSentenceLimited.jsp?pid={$pId}|{$sId}"><xsl:value-of select="@id" /></a>
		</font >
	</bold>
		 <xsl:element name="BR"/>
 		<p>
			<xsl:for-each select="token">
			<xsl:variable name="url"><xsl:value-of select="@surface"/>
			</xsl:variable>
			<xsl:variable name="invertedCommas">"</xsl:variable>
			<xsl:variable name="apos">'</xsl:variable>
			<xsl:choose>
			<xsl:when test="@surface='.' or  @surface='־'  or  @surface='?' or @surface=',' or @surface=':'  or @surface=']'  or @surface='['  or  @surface='`' or @surface='~' or  @surface=';' or @surface='!'  or  @surface='(' or  @surface=')' or @surface='-' or  @surface='_' or @surface='+'or  @surface='=' or @surface='/'  ">
			<xsl:value-of select="@surface"/>	
			</xsl:when>
			<xsl:when test="@surface=$apos or @surface=$invertedCommas">
			 <xsl:value-of select="@surface"/>
			</xsl:when>		
			<xsl:otherwise> 
				<xsl:choose>
					<xsl:when test="(analysis[@score='1.0']/prefix/../base/noun/../../suffix) or (analysis[@score='0.5']/prefix/../base/noun/../../suffix)   or (analysis[@score='0.25']/prefix/../base/noun/../../suffix) or (analysis[@score='0.3333333333333333']/prefix/../base/noun/../../suffix)
						or  (analysis[@score='0.2']/prefix/../base/noun/../../suffix)">
					<a STYLE="text-decoration:none"       href="#" onmouseover="Tip('תחילית + שם עצם - קניין', FONTSIZE , '25px',BGCOLOR, '#CCFFFF')" onmouseout="UnTip()"><xsl:value-of select="@surface"/></a><xsl:text >  </xsl:text>
					</xsl:when>	


					<xsl:when test="(analysis[@score='1.0']/base/noun/../../suffix) or  (analysis[@score='0.5']/base/noun/../../suffix)  or (analysis[@score='0.25']/base/noun/../../suffix) or (analysis[@score='0.3333333333333333']/base/noun/../../suffix) or (analysis[@score='0.2']/base/noun/../../suffix)">
					<a STYLE="text-decoration:none"      href="#" onmouseover="Tip('שם עצם - קניין', FONTSIZE , '25px',BGCOLOR, '#CCFFFF')" onmouseout="UnTip()"><xsl:value-of select="@surface"/></a><xsl:text >  </xsl:text>
					</xsl:when>	

					<xsl:when test=" (analysis[@score='1.0']/prefix/../base/noun/@definiteness='true') or  (analysis[@score='0.5']/prefix/../base/noun/@definiteness='true')  or (analysis[@score='0.25']/prefix/../base/noun/@definiteness='true') or (analysis[@score='0.3333333333333333']/prefix/../base/noun/@definiteness='true')
					or (analysis[@score='0.2']/prefix/../base/noun/@definiteness='true')">
					<a STYLE="text-decoration:none"      href="#" onmouseover="Tip(' תחילית + שם עצם - מיודע', FONTSIZE , '25px',BGCOLOR, '#CCFFFF')" onmouseout="UnTip()"><xsl:value-of select="@surface"/></a><xsl:text >  </xsl:text>
					</xsl:when>

					<xsl:when test="(analysis[@score='1.0']/base/noun/@definiteness='true') or  (analysis[@score='0.5']/base/noun/@definiteness='true')  or (analysis[@score='0.25']/base/noun/@definiteness='true') or (analysis[@score='0.3333333333333333']/base/noun/@definiteness='true')
					or (analysis[@score='0.2']/base/noun/@definiteness='true')">
					<a STYLE="text-decoration:none"    href="#" onmouseover="Tip(' שם עצם- מיודע', FONTSIZE , '25px',BGCOLOR, '#CCFFFF')" onmouseout="UnTip()"><xsl:value-of select="@surface"/></a><xsl:text >  </xsl:text>
					</xsl:when>	
				
					<xsl:when test="(analysis[@score='1.0']/prefix/../base/noun/@status='construct') or  (analysis[@score='0.5']/prefix/../base/noun/@status='construct')  or (analysis[@score='0.25']/prefix/../base/noun/@status='construct') or (analysis[@score='0.3333333333333333']/prefix/../base/noun/@status='construct')
					or (analysis[@score='0.2']/prefix/../base/noun/@status='construct')">
					<a STYLE="text-decoration:none"    href="#" onmouseover="Tip('תחילית + שם עצם - נסמך' , FONTSIZE , '25px',BGCOLOR, '#CCFFFF')" onmouseout="UnTip()"><xsl:value-of select="@surface"/></a><xsl:text >  </xsl:text>
					</xsl:when>	
				
					<xsl:when test="(analysis[@score='1.0']/base/noun/@status='construct') or  (analysis[@score='0.5']/base/noun/@status='construct')  or (analysis[@score='0.25']/base/noun/@status='construct') or (analysis[@score='0.3333333333333333']/base/noun/@status='construct')
					or (analysis[@score='0.2']/base/noun/@status='construct')">
					<a STYLE="text-decoration:none"    href="#" onmouseover="Tip('שם עצם- נסמך', FONTSIZE , '25px',BGCOLOR, '#CCFFFF')" onmouseout="UnTip()"><xsl:value-of select="@surface"/></a><xsl:text >  </xsl:text>
					</xsl:when>	
				
					<xsl:when test="(analysis[@score='1.0']/prefix/../base/noun/@status='absolute') or  (analysis[@score='0.5']/prefix/../base/noun/@status='absolute')  or (analysis[@score='0.25']/prefix/../base/noun/@status='absolute') or (analysis[@score='0.3333333333333333']/prefix/../base/noun/@status='absolute')
					or (analysis[@score='0.2']/prefix/../base/noun/@status='absolute')">
					<a STYLE="text-decoration:none"    href="#" onmouseover="Tip(' תחילית + שם עצם - נפרד', FONTSIZE , '25px',BGCOLOR, '#CCFFFF')" onmouseout="UnTip()"><xsl:value-of select="@surface"/></a><xsl:text >  </xsl:text>
					</xsl:when>

					<xsl:when test="(analysis[@score='1.0']/base/noun/@status='absolute') or  (analysis[@score='0.5']/base/noun/@status='absolute')  or (analysis[@score='0.25']/base/noun/@status='absolute') or (analysis[@score='0.3333333333333333']/base/noun/@status='absolute')
					or (analysis[@score='0.2']/base/noun/@status='absolute')">
					<a     STYLE="text-decoration:none"   href="#" onmouseover="Tip(' שם עצם- נפרד', FONTSIZE , '25px',BGCOLOR, '#CCFFFF')" onmouseout="UnTip()"><xsl:value-of select="@surface"/></a><xsl:text >  </xsl:text>
					</xsl:when>

					<xsl:when test="(analysis[@score='1.0']/prefix/../base/adjective/@definiteness='true') or  (analysis[@score='0.5']/prefix/../base/adjective/@definiteness='true')  or (analysis[@score='0.25']/prefix/../base/adjective/@definiteness='true') or (analysis[@score='0.3333333333333333']/prefix/../base/adjective/@definiteness='true')
					or (analysis[@score='0.3333333333333333']/prefix/../base/adjective/@definiteness='true')">
					<a STYLE="text-decoration:none"    href="#" onmouseover="Tip(' תחילית + שם תואר - נפרד, מיודע', FONTSIZE , '25px',BGCOLOR, '#99CCFF')" onmouseout="UnTip()"><xsl:value-of select="@surface"/></a><xsl:text >  </xsl:text>
					</xsl:when>	

					<xsl:when test="(analysis[@score='1.0']/base/adjective/@definiteness='true') or  (analysis[@score='0.5']/base/adjective/@definiteness='true')  or (analysis[@score='0.25']/base/adjective/@definiteness='true') or (analysis[@score='0.3333333333333333']/base/adjective/@definiteness='true')
					or (analysis[@score='0.2']/base/adjective/@definiteness='true')">
					<a STYLE="text-decoration:none"    href="#" onmouseover="Tip(' שם תואר- נפרד,מיודע', FONTSIZE , '25px',BGCOLOR, '#99CCFF')" onmouseout="UnTip()"><xsl:value-of select="@surface"/></a><xsl:text >  </xsl:text>
					</xsl:when>	
			
					<xsl:when test="(analysis[@score='1.0']/prefix/../base/adjective/@status='construct') or  (analysis[@score='0.5']/prefix/../base/adjective/@status='construct')  or (analysis[@score='0.25']/prefix/../base/adjective/@status='construct') or (analysis[@score='0.3333333333333333']/prefix/../base/adjective/@status='construct')
					or (analysis[@score='0.2']/prefix/../base/adjective/@status='construct')">
					<a STYLE="text-decoration:none"    href="#" onmouseover="Tip('תחילית + שם תואר - נסמך', FONTSIZE , '25px',BGCOLOR, '#99CCFF')" onmouseout="UnTip()"><xsl:value-of select="@surface"/></a><xsl:text >  </xsl:text>
					</xsl:when>	
			
					<xsl:when test="(analysis[@score='1.0']/base/adjective/@status='construct') or  (analysis[@score='0.5']/base/adjective/@status='construct')  or (analysis[@score='0.25']/base/adjective/@status='construct') or (analysis[@score='0.3333333333333333']/base/adjective/@status='construct')
					or (analysis[@score='0.2']/base/adjective/@status='construct')">
					<a STYLE="text-decoration:none"    href="#" onmouseover="Tip('שם תואר - נסמך', FONTSIZE , '25px',BGCOLOR, '#99CCFF')" onmouseout="UnTip()"><xsl:value-of select="@surface"/></a><xsl:text >  </xsl:text>
					</xsl:when>	
			
					<xsl:when test="(analysis[@score='1.0']/prefix/../base/adjective/@status='absolute') or  (analysis[@score='0.5']/prefix/../base/adjective/@status='absolute')  or (analysis[@score='0.25']/prefix/../base/adjective/@status='absolute') or (analysis[@score='0.3333333333333333']/prefix/../base/adjective/@status='absolute')
					or (analysis[@score='0.2']/prefix/../base/adjective/@status='absolute')">
					<a STYLE="text-decoration:none"    href="#" onmouseover="Tip('תחילית + שם תואר - נפרד', FONTSIZE , '25px',BGCOLOR, '#99CCFF')" onmouseout="UnTip()"><xsl:value-of select="@surface"/></a><xsl:text >  </xsl:text>
					</xsl:when>

					<xsl:when test="(analysis[@score='1.0']/base/adjective/@status='absolute') or  (analysis[@score='0.5']/base/adjective/@status='absolute')  or (analysis[@score='0.25']/base/adjective/@status='absolute') or (analysis[@score='0.3333333333333333']/base/adjective/@status='absolute')
					or (analysis[@score='0.2']/base/adjective/@status='absolute')">
					<a STYLE="text-decoration:none"    href="#" onmouseover="Tip('שם תואר - נפרד', FONTSIZE , '25px',BGCOLOR, '#99CCFF')" onmouseout="UnTip()"><xsl:value-of select="@surface"/></a><xsl:text >  </xsl:text>
					</xsl:when>

					<xsl:when test="(analysis[@score='1.0']/prefix/../base/verb/@tense='infinitive') or  (analysis[@score='0.5']/prefix/../base/verb/@tense='infinitive')  or (analysis[@score='0.25']/prefix/../base/verb/@tense='infinitive') or (analysis[@score='0.3333333333333333']/prefix/../base/verb/@tense='infinitive')
					or (analysis[@score='0.2']/prefix/../base/verb/@tense='infinitive')">
					<a STYLE="text-decoration:none"    href="#" onmouseover="Tip('תחילית + פועל - שם פועל', FONTSIZE , '25px',BGCOLOR, '#CC99CC')" onmouseout="UnTip()"><xsl:value-of select="@surface"/></a><xsl:text >  </xsl:text>
					</xsl:when>

					<xsl:when test="(analysis[@score='1.0']/base/verb/@tense='infinitive') or  (analysis[@score='0.5']/base/verb/@tense='infinitive')  or (analysis[@score='0.25']/base/verb/@tense='infinitive') or (analysis[@score='0.3333333333333333']/base/verb/@tense='infinitive')
					or (analysis[@score='0.2']/base/verb/@tense='infinitive')">
					<a STYLE="text-decoration:none"    href="#" onmouseover="Tip('פועל - שם פועל', FONTSIZE , '25px',BGCOLOR, '#CC99CC')" onmouseout="UnTip()"><xsl:value-of select="@surface"/></a><xsl:text >  </xsl:text>
					</xsl:when>

					<xsl:when test="(analysis[@score='1.0']/base/verb/@tense='bareInfinitive') or  (analysis[@score='0.5']/base/verb/@tense='bareInfinitive')  or (analysis[@score='0.25']/base/verb/@tense='bareInfinitive') or (analysis[@score='0.3333333333333333']/base/verb/@tense='bareInfinitive')
					or (analysis[@score='0.2']/base/verb/@tense='bareInfinitive')">
					<a STYLE="text-decoration:none"    href="#" onmouseover="Tip( 'מקור', FONTSIZE , '25px',BGCOLOR, '#CC99CC')" onmouseout="UnTip()"><xsl:value-of select="@surface"/></a><xsl:text >  </xsl:text>
					</xsl:when>
				
					<xsl:when test="(analysis[@score='1.0']/prefix/../base/verb or  analysis[@score='0.5']/prefix/../base/verb)  or
					(analysis[@score='0.25']/prefix/../base/verb)  or  (analysis[@score='0.3333333333333333']/prefix/../base/verb) 
					or  (analysis[@score='0.2']/prefix/../base/verb)">
					<a STYLE="text-decoration:none"    href="#" onmouseover="Tip('תחילית + פועל סופי', FONTSIZE , '25px',BGCOLOR, '#CC99CC')" onmouseout="UnTip()"><xsl:value-of select="@surface"/></a><xsl:text >  </xsl:text>
					</xsl:when>

					<xsl:when test="(analysis[@score='1.0']/base/verb or  analysis[@score='0.5']/base/verb)  or
					(analysis[@score='0.25']/base/verb)  or  (analysis[@score='0.3333333333333333']/base/verb)
					or  (analysis[@score='0.2']/base/verb)">
					<a STYLE="text-decoration:none"    href="#" onmouseover="Tip('פועל סופי', FONTSIZE , '25px',BGCOLOR, '#CC99CC')" onmouseout="UnTip()"><xsl:value-of select="@surface"/></a><xsl:text >  </xsl:text>
					</xsl:when>

					<xsl:when test="analysis[@score='1.0']/prefix/../base/numeral or  analysis[@score='0.5']/prefix/../base/numeral or
					analysis[@score='0.25']/prefix/../prefix/../base/numeral  or  analysis[@score='0.3333333333333333']/prefix/../base/numeral  or  analysis[@score='0.2']/prefix/../base/numeral">
					<a STYLE="text-decoration:none"    href="#" onmouseover="Tip('תחילית + שם מספר', FONTSIZE , '25px',BGCOLOR, '#99CC99')" onmouseout="UnTip()"><xsl:value-of select="@surface"/></a><xsl:text >  </xsl:text>
					</xsl:when>

					<xsl:when test="analysis[@score='1.0']/base/numeral/@definiteness='true' or  analysis[@score='0.5']/base/numeral/@definiteness='true' or
					analysis[@score='0.25']/base/numeral/@definiteness='true'  or  analysis[@score='0.3333333333333333']/base/numeral/@definiteness='true'   or  analysis[@score='0.2']/base/numeral/@definiteness='true'">
					<a STYLE="text-decoration:none"    href="#" onmouseover="Tip('שם מספר מיודע', FONTSIZE , '25px',BGCOLOR, '#99CC99')" onmouseout="UnTip()"><xsl:value-of select="@surface"/></a><xsl:text >  </xsl:text>
					</xsl:when>

					<xsl:when test="analysis[@score='1.0']/prefix/../base/numeral/@definiteness='true' or  analysis[@score='0.5']/prefix/../base/numeral/@definiteness='true' or
					analysis[@score='0.25']/prefix/../prefix/../base/numeral/@definiteness='true'  or  analysis[@score='0.3333333333333333']/prefix/../base/numeral/@definiteness='true'  or  analysis[@score='0.2']/prefix/../base/numeral/@definiteness='true'">
					<a STYLE="text-decoration:none"    href="#" onmouseover="Tip('תחילית +  שם מספר מיודע', FONTSIZE , '25px',BGCOLOR, '#99CC99')" onmouseout="UnTip()"><xsl:value-of select="@surface"/></a><xsl:text >  </xsl:text>
					</xsl:when>

					<xsl:when test="analysis[@score='1.0']/base/numeral or  analysis[@score='0.5']/base/numeral or
					analysis[@score='0.25']/base/numeral  or  analysis[@score='0.3333333333333333']/base/numeral   or  analysis[@score='0.2']/base/numeral">
					<a STYLE="text-decoration:none"    href="#" onmouseover="Tip('שם מספר', FONTSIZE , '25px',BGCOLOR, '#99CC99')" onmouseout="UnTip()"><xsl:value-of select="@surface"/></a><xsl:text >  </xsl:text>
					</xsl:when>

					<xsl:when test="analysis[@score='1.0']/prefix/../base/quantifier or  analysis[@score='0.5']/prefix/../base/quantifier or
					analysis[@score='0.25']/prefix/../base/quantifier  or  analysis[@score='0.3333333333333333']/prefix/../base/quantifier  or  analysis[@score='0.2']/prefix/../base/quantifier">
					<a STYLE="text-decoration:none"    href="#" onmouseover="Tip('תחילית + כמת', FONTSIZE , '25px',BGCOLOR, '#FDEEF4')" onmouseout="UnTip()"><xsl:value-of select="@surface"/></a><xsl:text >  </xsl:text>
					</xsl:when>

					<xsl:when test="analysis[@score='1.0']/base/quantifier or  analysis[@score='0.5']/base/quantifier or
					analysis[@score='0.25']/base/quantifier  or  analysis[@score='0.3333333333333333']/base/quantifier   or  analysis[@score='0.2']/base/quantifier">
					<a STYLE="text-decoration:none"    href="#" onmouseover="Tip('כמת', FONTSIZE , '25px',BGCOLOR, '#FDEEF4')" onmouseout="UnTip()"><xsl:value-of select="@surface"/></a><xsl:text >  </xsl:text>
					</xsl:when>

					<xsl:when test="(analysis[@score='1.0']/prefix/../base/participle/../../suffix) or  (analysis[@score='0.5']/prefix/../base/participle/../../suffix)  or (analysis[@score='0.25']/prefix/../base/participle/../../suffix) or (analysis[@score='0.3333333333333333']/prefix/../base/participle/../../suffix)  or (analysis[@score='0.2']/prefix/../base/participle/../../suffix)">
					<a STYLE="text-decoration:none"    href="#" onmouseover="Tip('תחילית + בינוני  - קניין', FONTSIZE , '25px',BGCOLOR, '#33CCCC')" onmouseout="UnTip()"><xsl:value-of select="@surface"/></a><xsl:text >  </xsl:text>
					</xsl:when>	

					<xsl:when test="(analysis[@score='1.0']/base/participle/../../suffix) or  (analysis[@score='0.5']/base/participle/../../suffix)  or (analysis[@score='0.25']/base/participle/../../suffix) or (analysis[@score='0.3333333333333333']/base/participle/../../suffix)  or (analysis[@score='0.2']/base/participle/../../suffix)">
					<a STYLE="text-decoration:none"    href="#" onmouseover="Tip('בינוני - קניין', FONTSIZE , '25px',BGCOLOR, '#33CCCC')" onmouseout="UnTip()"><xsl:value-of select="@surface"/></a><xsl:text >  </xsl:text>
					</xsl:when>	

					<xsl:when test="(analysis[@score='1.0']/prefix/../base/participle/@definiteness='true') or  (analysis[@score='0.5']/prefix/../base/participle/@definiteness='true')  or (analysis[@score='0.25']/prefix/../base/participle/@definiteness='true') or (analysis[@score='0.3333333333333333']/prefix/../base/participle/@definiteness='true')  or (analysis[@score='0.2']/prefix/../base/participle/@definiteness='true')">
					<a STYLE="text-decoration:none"    href="#" onmouseover="Tip('תחילית + בינוני- נפרד,מיודע', FONTSIZE , '25px',BGCOLOR, '#33CCCC')" onmouseout="UnTip()"><xsl:value-of select="@surface"/></a><xsl:text >  </xsl:text>
					</xsl:when>	

					<xsl:when test="(analysis[@score='1.0']/base/participle/@definiteness='true') or  (analysis[@score='0.5']/base/participle/@definiteness='true')  or (analysis[@score='0.25']/base/participle/@definiteness='true') or (analysis[@score='0.3333333333333333']/base/participle/@definiteness='true')  or (analysis[@score='0.2']/base/participle/@definiteness='true')">
					<a STYLE="text-decoration:none"    href="#" onmouseover="Tip(' בינוני- נפרד,מיודע', FONTSIZE , '25px',BGCOLOR, '#33CCCC')" onmouseout="UnTip()"><xsl:value-of select="@surface"/></a><xsl:text >  </xsl:text>
					</xsl:when>

					<xsl:when test="(analysis[@score='1.0']/prefix/../base/participle/@status='construct') or  (analysis[@score='0.5']/prefix/../base/participle/@status='construct')  or (analysis[@score='0.25']/prefix/../base/participle/@status='construct') or (analysis[@score='0.3333333333333333']/prefix/../base/participle/@status='construct')  or (analysis[@score='0.2']/prefix/../base/participle/@status='construct')">
					<a STYLE="text-decoration:none"    href="#" onmouseover="Tip('תחילית + בינוני - נסמך', FONTSIZE , '25px',BGCOLOR, '#33CCCC')" onmouseout="UnTip()"><xsl:value-of select="@surface"/></a><xsl:text >  </xsl:text>
					</xsl:when>	
			
					<xsl:when test="(analysis[@score='1.0']/base/participle/@status='construct') or  (analysis[@score='0.5']/base/participle/@status='construct')  or (analysis[@score='0.25']/base/participle/@status='construct') or (analysis[@score='0.3333333333333333']/base/participle/@status='construct')  or (analysis[@score='0.2']/base/participle/@status='construct')">
					<a STYLE="text-decoration:none"    href="#" onmouseover="Tip('בינוני - נסמך', FONTSIZE , '25px',BGCOLOR, '#33CCCC')" onmouseout="UnTip()"><xsl:value-of select="@surface"/></a><xsl:text >  </xsl:text>
					</xsl:when>	
			
					<xsl:when test="(analysis[@score='1.0']/prefix/../base/participle/@status='absolute') or  (analysis[@score='0.5']/prefix/../base/participle/@status='absolute')  or (analysis[@score='0.25']/prefix/../base/participle/@status='absolute') or (analysis[@score='0.3333333333333333']/prefix/../base/participle/@status='absolute')  or (analysis[@score='0.2']/prefix/../base/participle/@status='absolute')">
					<a STYLE="text-decoration:none"    href="#" onmouseover="Tip('תחילית + בינוני - נפרד', FONTSIZE , '25px',BGCOLOR, '#33CCCC')" onmouseout="UnTip()"><xsl:value-of select="@surface"/></a><xsl:text >  </xsl:text>
					</xsl:when>

					<xsl:when test="(analysis[@score='1.0']/base/participle/@status='absolute') or  (analysis[@score='0.5']/base/participle/@status='absolute')  or (analysis[@score='0.25']/base/participle/@status='absolute') or (analysis[@score='0.3333333333333333']/base/participle/@status='absolute')  or (analysis[@score='0.2']/base/participle/@status='absolute')">
					<a STYLE="text-decoration:none"    href="#" onmouseover="Tip('בינוני - נפרד', FONTSIZE , '25px',BGCOLOR, '#33CCCC')" onmouseout="UnTip()"><xsl:value-of select="@surface"/></a><xsl:text >  </xsl:text>
					</xsl:when>

					<xsl:when test="analysis[@score='1.0']/prefix/../base/copula or  analysis[@score='0.5']/prefix/../base/copula or
					analysis[@score='0.25']/prefix/../base/copula  or  analysis[@score='0.3333333333333333']/prefix/../base/copula   or  analysis[@score='0.2']/prefix/../base/copula">
					<a STYLE="text-decoration:none"    href="#" onmouseover="Tip('תחילית +אוגד', FONTSIZE , '25px',BGCOLOR, '#CC99FF')" onmouseout="UnTip()"><xsl:value-of select="@surface"/></a><xsl:text >  </xsl:text>
					</xsl:when>

					<xsl:when test="analysis[@score='1.0']/base/copula or  analysis[@score='0.5']/base/copula or
					analysis[@score='0.25']/base/copula  or  analysis[@score='0.3333333333333333']/base/copula  or  analysis[@score='0.2']/base/copula">
					<a STYLE="text-decoration:none"    href="#" onmouseover="Tip('אוגד', FONTSIZE , '25px',BGCOLOR, '#CC99FF')" onmouseout="UnTip()"><xsl:value-of select="@surface"/></a><xsl:text >  </xsl:text>
					</xsl:when>

					<xsl:when test="analysis[@score='1.0']/prefix/../base/title or  analysis[@score='0.5']/prefix/../base/title or
					analysis[@score='0.25']/prefix/../base/title  or  analysis[@score='0.3333333333333333']/prefix/../base/title   or  analysis[@score='0.2']/prefix/../base/title">
					<a STYLE="text-decoration:none"    href="#" onmouseover="Tip('תחילית + תואר', FONTSIZE , '25px',BGCOLOR, '#99CCCC')" onmouseout="UnTip()"><xsl:value-of select="@surface"/></a><xsl:text >  </xsl:text>
					</xsl:when>

					<xsl:when test="analysis[@score='1.0']/base/title or  analysis[@score='0.5']/base/title or
					analysis[@score='0.25']/base/title  or  analysis[@score='0.3333333333333333']/base/title   or  analysis[@score='0.2']/base/title">
					<a STYLE="text-decoration:none"    href="#" onmouseover="Tip('תואר', FONTSIZE , '25px',BGCOLOR, '#99CCCC')" onmouseout="UnTip()"><xsl:value-of select="@surface"/></a><xsl:text >  </xsl:text>
					</xsl:when>

					<xsl:when test="analysis[@score='1.0']/prefix/../base/pronoun or  analysis[@score='0.5']/prefix/../base/pronoun or
					analysis[@score='0.25']/prefix/../base/pronoun  or  analysis[@score='0.3333333333333333']/prefix/../base/pronoun  or  analysis[@score='0.2']/prefix/../base/pronoun">
					<a STYLE="text-decoration:none"    href="#" onmouseover="Tip('תחילית + כינוי גוף', FONTSIZE , '25px',BGCOLOR, '#FFFF66')" onmouseout="UnTip()"><xsl:value-of select="@surface"/></a><xsl:text >  </xsl:text>
					</xsl:when>

					<xsl:when test="analysis[@score='1.0']/base/pronoun or  analysis[@score='0.5']/base/pronoun or
					analysis[@score='0.25']/base/pronoun  or  analysis[@score='0.3333333333333333']/base/pronoun  or  analysis[@score='0.2']/base/pronoun">
					<a STYLE="text-decoration:none"    href="#" onmouseover="Tip('כינוי גוף', FONTSIZE , '25px',BGCOLOR, '#FFFF66')" onmouseout="UnTip()"><xsl:value-of select="@surface"/></a><xsl:text >  </xsl:text>
					</xsl:when>

					<xsl:when test="analysis[@score='1.0']/prefix/../base/adverb or  analysis[@score='0.5']/prefix/../base/adverb or
					analysis[@score='0.25']/prefix/../base/adverb  or  analysis[@score='0.3333333333333333']/prefix/../base/adverb  or  analysis[@score='0.2']/prefix/../base/adverb">
					<a STYLE="text-decoration:none"    href="#" onmouseover="Tip('תחילית +תואר הפועל', FONTSIZE , '25px',BGCOLOR, '#FFCC33')" onmouseout="UnTip()"><xsl:value-of select="@surface"/></a><xsl:text >  </xsl:text>
					</xsl:when>

					<xsl:when test="analysis[@score='1.0']/base/adverb or  analysis[@score='0.5']/base/adverb or
					analysis[@score='0.25']/base/adverb  or  analysis[@score='0.3333333333333333']/base/adverb  or  analysis[@score='0.2']/base/adverb">
					<a STYLE="text-decoration:none"    href="#" onmouseover="Tip('תואר הפועל', FONTSIZE , '25px',BGCOLOR, '#FFCC33')" onmouseout="UnTip()"><xsl:value-of select="@surface"/></a><xsl:text >  </xsl:text>
					</xsl:when>

					<xsl:when test="analysis[@score='1.0']/base/numberExpression or  analysis[@score='0.5']/base/numberExpression or
					analysis[@score='0.25']/base/numberExpression  or  analysis[@score='0.3333333333333333']/base/numberExpression  or  analysis[@score='0.2']/base/numberExpression">
					<a STYLE="text-decoration:none"    href="#" onmouseover="Tip('ביטוי מספרי', FONTSIZE , '25px',BGCOLOR, '#996600')" onmouseout="UnTip()"><xsl:value-of select="@surface"/></a><xsl:text >  </xsl:text>
					</xsl:when>

					<xsl:when test="analysis[@score='1.0']/prefix/../base/negation or  analysis[@score='0.5']/prefix/../base/negation or
					analysis[@score='0.25']/prefix/../base/negation  or  analysis[@score='0.3333333333333333']/prefix/../base/negation  or  analysis[@score='0.2']/prefix/../base/negation">
					<a STYLE="text-decoration:none"    href="#" onmouseover="Tip('תחילית + מילת שלילה', FONTSIZE , '25px',BGCOLOR, '#00FFCC')" onmouseout="UnTip()"><xsl:value-of select="@surface"/></a><xsl:text >  </xsl:text>
					</xsl:when>

					<xsl:when test="analysis[@score='1.0']/base/negation or  analysis[@score='0.5']/base/negation or
					analysis[@score='0.25']/base/negation  or  analysis[@score='0.3333333333333333']/base/negation   or  analysis[@score='0.2']/base/negation">
					<a STYLE="text-decoration:none"    href="#" onmouseover="Tip('מילת שלילה', FONTSIZE , '25px',BGCOLOR, '#00FFCC')" onmouseout="UnTip()"><xsl:value-of select="@surface"/></a><xsl:text >  </xsl:text>
					</xsl:when>

					<xsl:when test="analysis[@score='1.0']/prefix/../base/existential or  analysis[@score='0.5']/prefix/../base/existential or
					analysis[@score='0.25']/prefix/../base/existential  or  analysis[@score='0.3333333333333333']/prefix/../base/existential  or  analysis[@score='0.2']/prefix/../base/existential">
					<a STYLE="text-decoration:none"    href="#" onmouseover="Tip('תחילית +כמת ישיי', FONTSIZE , '25px',BGCOLOR, '#EBDDE2')" onmouseout="UnTip()"><xsl:value-of select="@surface"/></a><xsl:text >  </xsl:text>
					</xsl:when>

					<xsl:when test="analysis[@score='1.0']/base/existential or  analysis[@score='0.5']/base/existential or
					analysis[@score='0.25']/base/existential  or  analysis[@score='0.3333333333333333']/base/existential  or  analysis[@score='0.2']/base/existential">
					<a STYLE="text-decoration:none"    href="#" onmouseover="Tip('כמת ישיי', FONTSIZE , '25px',BGCOLOR, '#EBDDE2')" onmouseout="UnTip()"><xsl:value-of select="@surface"/></a><xsl:text >  </xsl:text>
					</xsl:when>

					<xsl:when test="analysis[@score='1.0']/base/wPrefix or  analysis[@score='0.5']/base/wPrefix or
					analysis[@score='0.25']/base/wPrefix  or  analysis[@score='0.3333333333333333']/base/wPrefix  or  analysis[@score='0.2']/base/wPrefix">
					<a STYLE="text-decoration:none"    href="#" onmouseover="Tip('מילת תחילית', FONTSIZE , '25px',BGCOLOR, '#CC33CC')" onmouseout="UnTip()"><xsl:value-of select="@surface"/></a><xsl:text >  </xsl:text>
					</xsl:when>

					<xsl:when test="(analysis[@score='1.0']/prefix/../base/properName/@definiteness='true') or  (analysis[@score='0.5']/prefix/../base/properName/@definiteness='true')  or
					(analysis[@score='0.25']/prefix/../base/properName/@definiteness='true')  or  (analysis[@score='0.3333333333333333']/prefix/../base/properName/@definiteness='true')  or  (analysis[@score='0.2']/prefix/../base/properName/@definiteness='true')">
					<a STYLE="text-decoration:none"    href="#" onmouseover="Tip(' תחילית +שם פרטי - מיודע', FONTSIZE , '25px',BGCOLOR, '#FF9966')" onmouseout="UnTip()"><xsl:value-of select="@surface"/></a><xsl:text >  </xsl:text>
					</xsl:when>

					<xsl:when test="(analysis[@score='1.0']/base/properName/@definiteness='true') or  (analysis[@score='0.5']/base/properName/@definiteness='true')  or
					(analysis[@score='0.25']/base/properName/@definiteness='true')  or  (analysis[@score='0.3333333333333333']/base/properName/@definiteness='true')   or  (analysis[@score='0.2']/base/properName/@definiteness='true')">
					<a STYLE="text-decoration:none"    href="#" onmouseover="Tip(' שם פרטי - מיודע', FONTSIZE , '25px',BGCOLOR, '#FF9966')" onmouseout="UnTip()"><xsl:value-of select="@surface"/></a><xsl:text >  </xsl:text>
					</xsl:when>

					<xsl:when test="analysis[@score='1.0']/prefix/../base/properName or  analysis[@score='0.5']/prefix/../base/properName or
					analysis[@score='0.25']/prefix/../base/properName  or  analysis[@score='0.3333333333333333']/prefix/../base/properName  or  analysis[@score='0.2']/prefix/../base/properName">
					<a STYLE="text-decoration:none"    href="#" onmouseover="Tip('תחילית +שם פרטי', FONTSIZE , '25px',BGCOLOR, '#CCCCCC')" onmouseout="UnTip()"><xsl:value-of select="@surface"/></a><xsl:text >  </xsl:text>
					</xsl:when>

					<xsl:when test="analysis[@score='1.0']/base/properName or  analysis[@score='0.5']/base/properName or
					analysis[@score='0.25']/base/properName  or  analysis[@score='0.3333333333333333']/base/properName   or  analysis[@score='0.2']/base/properName">
					<a STYLE="text-decoration:none"    href="#" onmouseover="Tip('שם פרטי', FONTSIZE , '25px',BGCOLOR, '#CCCCCC')" onmouseout="UnTip()"><xsl:value-of select="@surface"/></a><xsl:text >  </xsl:text>
					</xsl:when>

					<xsl:when test="analysis[@score='1.0']/prefix/../base/conjunction or  analysis[@score='0.5']/prefix/../base/conjunction or
					analysis[@score='0.25']/prefix/../base/conjunction  or  analysis[@score='0.3333333333333333']/prefix/../base/conjunction  or  analysis[@score='0.2']/prefix/../base/conjunction">
					<a STYLE="text-decoration:none"    href="#" onmouseover="Tip('תחילית +מילת חיבור', FONTSIZE , '25px',BGCOLOR, '#CC6699')" onmouseout="UnTip()"><xsl:value-of select="@surface"/></a><xsl:text >  </xsl:text>
					</xsl:when>

					<xsl:when test="analysis[@score='1.0']/base/conjunction or  analysis[@score='0.5']/base/conjunction or
					analysis[@score='0.25']/base/conjunction  or  analysis[@score='0.3333333333333333']/base/conjunction  or  analysis[@score='0.2']/base/conjunction">
					<a STYLE="text-decoration:none"    href="#" onmouseover="Tip('מילת חיבור', FONTSIZE , '25px',BGCOLOR, '#CC6699')" onmouseout="UnTip()"><xsl:value-of select="@surface"/></a><xsl:text >  </xsl:text>
					</xsl:when>

					<xsl:when test="analysis[@score='1.0']/prefix/../base/interjection or  analysis[@score='0.5']/prefix/../base/interjection or
					analysis[@score='0.25']/prefix/../base/interjection  or  analysis[@score='0.3333333333333333']/prefix/../base/interjection  or  analysis[@score='0.2']/prefix/../base/interjection">
					<a STYLE="text-decoration:none"    href="#" onmouseover="Tip(' תחילית + מילת קריאה', FONTSIZE , '25px',BGCOLOR, '#CC6666')" onmouseout="UnTip()"><xsl:value-of select="@surface"/></a><xsl:text >  </xsl:text>
					</xsl:when>

					<xsl:when test="analysis[@score='1.0']/base/interjection or  analysis[@score='0.5']/base/interjection or
					analysis[@score='0.25']/base/interjection  or  analysis[@score='0.3333333333333333']/base/interjection  or  analysis[@score='0.2']/base/interjection">
					<a STYLE="text-decoration:none"    href="#" onmouseover="Tip(' מילת קריאה', FONTSIZE , '25px',BGCOLOR, '#CC6666')" onmouseout="UnTip()"><xsl:value-of select="@surface"/></a><xsl:text >  </xsl:text>
					</xsl:when>

					<xsl:when test="analysis[@score='1.0']/prefix/../base/interrogative or  analysis[@score='0.5']/prefix/../base/interrogative or
					analysis[@score='0.25']/prefix/../base/interrogative  or  analysis[@score='0.3333333333333333']/prefix/../base/interrogative  or  analysis[@score='0.2']/prefix/../base/interrogative">
					<a STYLE="text-decoration:none"    href="#" onmouseover="Tip('תחילית + מילת שאלה', FONTSIZE , '25px',BGCOLOR, '#CC3366')" onmouseout="UnTip()"><xsl:value-of select="@surface"/></a><xsl:text >  </xsl:text>
					</xsl:when>

					<xsl:when test="analysis[@score='1.0']/base/interrogative or  analysis[@score='0.5']/base/interrogative or
					analysis[@score='0.25']/base/interrogative  or  analysis[@score='0.3333333333333333']/base/interrogative  or  analysis[@score='0.2']/base/interrogative">
					<a STYLE="text-decoration:none"    href="#" onmouseover="Tip('מילת שאלה', FONTSIZE , '25px',BGCOLOR, '#CC3366')" onmouseout="UnTip()"><xsl:value-of select="@surface"/></a><xsl:text >  </xsl:text>
					</xsl:when>

					<xsl:when test="analysis[@score='1.0']/prefix/../base/preposition or  analysis[@score='0.5']/prefix/../base/preposition or
					analysis[@score='0.25']/prefix/../base/preposition  or  analysis[@score='0.3333333333333333']/prefix/../base/preposition  or  analysis[@score='0.2']/prefix/../base/preposition">
					<a STYLE="text-decoration:none"    href="#" onmouseover="Tip('תחילית + מילת יחס', FONTSIZE , '25px',BGCOLOR, '#C6DEFF')" onmouseout="UnTip()"><xsl:value-of select="@surface"/></a><xsl:text >  </xsl:text>
					</xsl:when>

					<xsl:when test="analysis[@score='1.0']/base/preposition or  analysis[@score='0.5']/base/preposition or
					analysis[@score='0.25']/base/preposition  or  analysis[@score='0.3333333333333333']/base/preposition  or  analysis[@score='0.2']/base/preposition">
					<a STYLE="text-decoration:none"    href="#" onmouseover="Tip('מילת יחס', FONTSIZE , '25px',BGCOLOR, '#C6DEFF')" onmouseout="UnTip()"><xsl:value-of select="@surface"/></a><xsl:text >  </xsl:text>
					</xsl:when>

					<xsl:when test="analysis[@score='1.0']/prefix/../base/modal or  analysis[@score='0.5']/prefix/../base/modal or
					analysis[@score='0.25']/prefix/../base/modal  or  analysis[@score='0.3333333333333333']/prefix/../base/modal  or  analysis[@score='0.2']/prefix/../base/modal">
					<a STYLE="text-decoration:none"    href="#" onmouseover="Tip('תחילית +מודאל', FONTSIZE , '25px',BGCOLOR, '#C8BBBE')" onmouseout="UnTip()"><xsl:value-of select="@surface"/></a><xsl:text >  </xsl:text>
					</xsl:when>

					<xsl:when test="analysis[@score='1.0']/base/modal or  analysis[@score='0.5']/base/modal or
					analysis[@score='0.25']/base/modal  or  analysis[@score='0.3333333333333333']/base/modal  or  analysis[@score='0.2']/base/modal">
					<a STYLE="text-decoration:none"    href="#" onmouseover="Tip('מודאל', FONTSIZE , '25px',BGCOLOR, '#C8BBBE')" onmouseout="UnTip()"><xsl:value-of select="@surface"/></a><xsl:text >  </xsl:text>
					</xsl:when>

					<xsl:when test="analysis[@score='1.0']/base/foreign">
					<a STYLE="text-decoration:none"    href="#" onmouseover="Tip('מילה בלועזית', FONTSIZE , '25px',BGCOLOR, '#FF9966')" onmouseout="UnTip()"><xsl:value-of select="@surface"/></a><xsl:text >  </xsl:text>
					</xsl:when>

					<xsl:when test="analysis[@score='1.0']/prefix and not(analysis/base)">
					<a STYLE="text-decoration:none"    href="#" onmouseover="Tip('תחילית', FONTSIZE , '25px',BGCOLOR, '#FF9966')" onmouseout="UnTip()"><xsl:value-of select="@surface"/></a><xsl:text >  </xsl:text>
					</xsl:when>
				
					<xsl:otherwise>
						<a STYLE="text-decoration:none"      href="#" onmouseover="Tip('Tagger -  לא נבחר ניתוח על ידי  ה', FONTSIZE , '25px',BGCOLOR, '#FF9966')" onmouseout="UnTip()"><xsl:value-of select="@surface"/></a><xsl:text >  </xsl:text>
					</xsl:otherwise>
				</xsl:choose>
			</xsl:otherwise>
			</xsl:choose>
			</xsl:for-each>
		</p>
	</xsl:for-each>
 </xsl:for-each>
</td>
</tr>
</table>

<xsl:element name="BR"/>
<xsl:element name="BR"/>
<xsl:element name="BR"/>
<xsl:element name="BR"/>

	<input type="button" ALT="חזור" onClick="history.go(-1)"  value="    חזור    "></input>
	<br/>
	<br/>
</center>

<div id="Footer"> כל הזכויות שמורות ל<a STYLE="text-decoration:none"    href="http://www.mila.cs.technion.ac.il">מילה</a> </div>
	
</body>
</html>

 </xsl:template>
 </xsl:stylesheet>

