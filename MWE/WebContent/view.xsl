<xsl:stylesheet xmlns:xsl ="http://www.w3.org/1999/XSL/Transform" xmlns:myObject="java:XSLOBJ" version="1.0">
<xsl:template name="header">
<xsl:text disable-output-escaping="yes">&lt;!DOCTYPE html&gt;</xsl:text>
</xsl:template>

<xsl:template match="/">
<!--  <xsl:call-template name="header"/>  -->
<html>
<head>
	<title>ניתוח טקסט</title>
	<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
	
	<link rel="stylesheet" type="text/css" href="js/w2ui-1.4.2.min.css" />
	<link rel="stylesheet" href="js/details-shim.css"/>
	
	<link rel="stylesheet" href="view.css"/>
	
	<script type="text/javascript" src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.3/jquery.min.js"></script>
	<script type="text/javascript" src="https://ajax.googleapis.com/ajax/libs/angularjs/1.3.15/angular.min.js"></script>
	
	<script type="text/javascript" src="js/w2ui-1.4.2.min.js"></script>
	<script type="text/javascript" src="js/details-shim.js"></script>
	
	<script type="text/javascript" src="cookies.js"></script>
	<script type="text/javascript" src="showhide.js"></script>
	<script type="text/javascript" src="layout.js"></script>

</head>

<body ng-app="tableBinder" ng-controller="tokenCtl" onload="rearrangeLayout(); addTips();  document.getElementById('text_to_tag').value = TAgetCookie();">
	
	<div id="layout" style="position: fixed; width: 99%; height: 95%; z-index: 5;">
	</div>
	
	<div id="form">
		<form accept-charset="UTF-8" dir="rtl" action="?" novalidate="">
			<button type="submit" class="btn" formmethod="post" formaction="analysis" name="analyze"
					onclick="document.getElementById('form').cursor='progress'">נתח</button>
			<button type="reset" class="btn" name="reset" onClick="TAdeleteCookie()">נקה</button>
			<xsl:element name="BR"/> 
			<textarea maxlength="4096" id="text_to_tag" tabindex="0" dir="rtl" name="input_text"
				onkeyup="TAsetCookie(this.value)" rows="13" cols="10"
				autofocus="" required="" placeholder="העתק או הקלד קטע טקסט בעברית לא מנוקדת"></textarea>
		</form>
	</div>
	
	<div id="article_tokens" ng-keydown="keydown($event)">
	<article>
		<ol class="paragraphs">
		<xsl:for-each select="corpus/article/paragraph">
		<li>
			<ol class="sents">
			<xsl:for-each select="sentence">
				<li>
				<xsl:variable name="invertedCommas">"</xsl:variable>
				<xsl:variable name="apos">'</xsl:variable>
				<p class="rawSentence">
				<xsl:for-each select="token">
					<xsl:choose>
					<xsl:when test="contains('([?-%', @surface)">
						<xsl:text> </xsl:text><xsl:value-of select="@surface"/>
					</xsl:when>
					<xsl:when test="contains('.?,:]`~;!)_+=/', @surface)">
						<xsl:value-of select="@surface"/>
					</xsl:when>
					<xsl:when test="@surface=$apos or @surface=$invertedCommas">
						<xsl:variable name="SURFACE"><xsl:value-of select="@surface"/></xsl:variable>
						<xsl:variable name="oddity" select="count(preceding::token[@surface=$SURFACE])"></xsl:variable>
						<xsl:if test="$oddity mod 2 = 0"><xsl:text> </xsl:text></xsl:if>
						<xsl:value-of select="@surface"/>
						<xsl:if test="$oddity mod 2 = 1"><xsl:text> </xsl:text></xsl:if>
					</xsl:when>
					<xsl:otherwise>
						<xsl:variable name="tokenIndex"><xsl:value-of select="count(preceding::token) - count(preceding::punctuation)"/></xsl:variable>
						<span ng-click="toggle({$tokenIndex})" ng-style="tokenStyle({$tokenIndex})" index="{$tokenIndex}" class="token">
							<xsl:if test="preceding::token[1][@surface!=$apos and @surface!=$invertedCommas and @surface!='(']"><xsl:text> </xsl:text></xsl:if>
							<span class="tooltip"><img class="callout" src="images/callout_black.gif" /></span>
							<xsl:value-of select="@surface"/>
						</span>
					</xsl:otherwise>
					</xsl:choose>
				</xsl:for-each>
				</p>
				</li>
			</xsl:for-each>
			</ol>
		</li>
		</xsl:for-each>
		</ol>
	</article>
	</div>
	
	<div id="tables">
	<xsl:for-each select="corpus/article/paragraph/sentence">
		<xsl:variable name="sentenceIndex"><xsl:value-of select="count(preceding::sentence)"/></xsl:variable>
		<div id="Sentence_{$sentenceIndex}" class='detailed_table'>
		<xsl:for-each select="token"><xsl:if test="not(analysis/base/punctuation)">
			<xsl:variable name="tokenIndex"><xsl:value-of select="count(preceding::token) - count(preceding::punctuation)"/></xsl:variable>
			<xsl:variable name="invertedCommas">"</xsl:variable>
			<xsl:variable name="apos">'</xsl:variable>
			<table ng-show="isVisible({$tokenIndex})" index="{$tokenIndex}" class="AnalysisTable">
				<xsl:variable name="IS_PREFIX" 			select="(analysis/prefix)"/>
				<xsl:variable name="IS_LEXICON_ITEM"	select="(analysis/base//@dottedLexiconItem or analysis/base//@lexiconItem)"/>
				<xsl:variable name="IS_LEXICON_ID"		select="(analysis/base//@lexiconPointer)"/>
				<xsl:variable name="IS_SCORE"			select="(analysis/@score)"/>
				<xsl:variable name="IS_POS"				select="(true())"/>
				<xsl:variable name="IS_ENTITY"			select="(analysis/base/properName/@type or analysis/base/MWE/@type)"/>
				<xsl:variable name="IS_GENDER"			select="(analysis/base//@gender)"/>
				<xsl:variable name="IS_NUMBER"			select="(analysis/base//@number)"/>
				<xsl:variable name="IS_CONSTRUCT"		select="(analysis/base//@status)"/> 
				<xsl:variable name="IS_DEFINITENESS"	select="(analysis/base//@definiteness)"/>
				<xsl:variable name="IS_INTERROGATE"		select="(analysis/base//*[@interrogative='true'])"/>
				<xsl:variable name="IS_PERSON"			select="(analysis/base//@person or analysis/base/MWE/@person)"/>
				<xsl:variable name="IS_S_GNP"			select="(analysis//suffix)"/>
				<xsl:variable name="IS_TENSE"			select="(analysis/base//@tense)"/>
				<xsl:variable name="IS_BINYAN"			select="(analysis/base//@binyan)"/>
				<xsl:variable name="IS_ROOT"			select="(analysis/base//@root)"/>
				<xsl:variable name="IS_REGISTER"		select="(analysis/base//@register)"/>
				<xsl:variable name="IS_SPELLING"		select="(analysis/base//@spelling)"/>
				<colgroup>
					<xsl:if test="$IS_PREFIX">	<col class="col PREFIX"/>	</xsl:if>
					<xsl:if test="$IS_DEFINITENESS"><col class="col DEFINITENESS"/>	</xsl:if>
					<xsl:if test="$IS_LEXICON_ITEM"><col class="col LEXICON_ITEM"/>	</xsl:if>
				<!-- <xsl:if test="$IS_LEXICON_ID">	<col class="col LEXICON_ID"/>	</xsl:if> -->
					<xsl:if test="$IS_SCORE">	<col class="col SCORE"/>	</xsl:if>
					<xsl:if test="$IS_POS">		<col class="col POS"/>		</xsl:if>
					<xsl:if test="$IS_ENTITY">	<col class="col ENTITY"/>	</xsl:if>
					<xsl:if test="$IS_GENDER">	<col class="col GENDER"/>	</xsl:if>
					<xsl:if test="$IS_NUMBER">	<col class="col NUMBER"/>	</xsl:if>
					<xsl:if test="$IS_CONSTRUCT">	<col class="col CONSTRUCT"/> 	</xsl:if>
					<xsl:if test="$IS_INTERROGATE">	<col class="col INTERROGATE"/>	</xsl:if>
					<xsl:if test="$IS_PERSON">	<col class="col PERSON"/>	</xsl:if>
					<xsl:if test="$IS_S_GNP">	<col class="col S_GNP"/>	</xsl:if>
					<xsl:if test="$IS_TENSE">	<col class="col TENSE"/>	</xsl:if>
					<xsl:if test="$IS_BINYAN">	<col class="col BINYAN"/>	</xsl:if>
					<xsl:if test="$IS_ROOT">	<col class="col ROOT"/>		</xsl:if>
					<xsl:if test="$IS_REGISTER">	<col class="col REGISTER"/>	</xsl:if>
					<xsl:if test="$IS_SPELLING">	<col class="col SPELLING"/>	</xsl:if>
				</colgroup>
				<thead>
				<tr class="Analysisheader">
				<xsl:if test="$IS_PREFIX">	<th class="col PREFIX">		קידומת</th></xsl:if>
				<xsl:if test="$IS_DEFINITENESS"><th class="col DEFINITENESS">	יידוע</th></xsl:if>
				<xsl:if test="$IS_LEXICON_ITEM"><th class="col LEXICON_ITEM">	לקסיקון</th></xsl:if>
			   <!-- <xsl:if test="$IS_LEXICON_ID">	<th class="col LEXICON_ID">	אינדקס</th></xsl:if> -->
				<xsl:if test="$IS_SCORE">	<th class="col SCORE">		סבירות</th></xsl:if>
				<xsl:if test="$IS_POS">		<th class="col POS">		חלק דיבר</th></xsl:if>
				<xsl:if test="$IS_ENTITY">	<th class="col ENTITY">		ישות</th></xsl:if>
				<xsl:if test="$IS_GENDER">	<th class="col GENDER">		מין</th></xsl:if>
				<xsl:if test="$IS_NUMBER">	<th class="col NUMBER">		מספר</th></xsl:if>
				<xsl:if test="$IS_CONSTRUCT">	<th class="col CONSTRUCT"> 	סמיכות</th> </xsl:if>
				<xsl:if test="$IS_INTERROGATE">	<th class="col INTERROGATE">	שאלה</th></xsl:if>
				<xsl:if test="$IS_PERSON">	<th class="col PERSON">		גוף</th></xsl:if>
				<xsl:if test="$IS_S_GNP">	<th class="col S_GNP">סיומת: מין מספר גוף</th></xsl:if>
				<xsl:if test="$IS_TENSE">	<th class="col TENSE">		זמן</th></xsl:if>
				<xsl:if test="$IS_BINYAN">	<th class="col BINYAN">		בניין</th></xsl:if>
				<xsl:if test="$IS_ROOT">	<th class="col ROOT">		שורש</th></xsl:if>
				<xsl:if test="$IS_REGISTER">	<th class="col REGISTER">	משלב</th></xsl:if>
				<xsl:if test="$IS_SPELLING">	<th class="col SPELLING">	איות</th></xsl:if>
				</tr>
				</thead>
				<tbody>
				<xsl:for-each select="analysis">
					<xsl:sort select="@score" data-type="number" order="descending"/>
					<xsl:variable name="PREFIX">
						<xsl:for-each select="prefix">
							<xsl:value-of select="concat(@surface,'')"/>
							<xsl:element name="BR"/>
						</xsl:for-each>
					</xsl:variable>
					<xsl:variable name="DEFINITENESS">
						<xsl:choose>
						<xsl:when test="base//@definiteness='true'">מיודע</xsl:when>
						<xsl:when test="base//@definiteness='false'">לא מיודע</xsl:when>
						</xsl:choose>
					</xsl:variable>
					<xsl:variable name="LEXICON_ITEM">
						<xsl:value-of select="(base/@dottedLexiconItem | base/@lexiconItem)[1]"/>
						<xsl:if test="base/MWE and base/MWE/@type='person'">
							<xsl:element name="BR"/>
							(<xsl:value-of select="base/MWE/@multiWord"/>)
						</xsl:if>
						<xsl:if test="base//@expansion">
							<xsl:element name="BR"/>
							(<xsl:value-of select="base//@expansion"/>)
						</xsl:if>
					</xsl:variable>
					<xsl:variable name="LEXICON_ID">
						<xsl:value-of select="base/@lexiconPointer"/>
					</xsl:variable>
					<xsl:variable name="LEXICON_ID_TEXT">
						<xsl:choose>
						<xsl:when test="$LEXICON_ID != ''"><xsl:value-of select="$LEXICON_ID"/></xsl:when>
						<xsl:otherwise>חלק מביטוי</xsl:otherwise>
						</xsl:choose>
					</xsl:variable>
					<xsl:variable name="pointerType">
						<xsl:choose>
						<xsl:when test="base/noun"	>noun</xsl:when>
						<xsl:when test="base/adjective"	>noun</xsl:when>
						<xsl:when test="base/participle">verb</xsl:when>
						<xsl:when test="base/verb"	>verb</xsl:when>
						<xsl:when test="base/MWE"	>MWE</xsl:when>
						<xsl:when test="base/preposition">preposition</xsl:when>
						<xsl:when test="base/numeral"	>numeral</xsl:when>
						</xsl:choose>
					</xsl:variable>
					<xsl:variable name="SCORE">
					<xsl:choose>
						<xsl:when test="@score='0.3333333333333333'">0.33</xsl:when>
						<xsl:otherwise>	<xsl:value-of select="round(@score*100) div 100"/>	</xsl:otherwise>
					</xsl:choose>
					</xsl:variable>
					<xsl:variable name="POS">
						<xsl:choose>
						<xsl:when test="(base/noun or base/MWE/@pos='noun')"		>שם עצם</xsl:when>
						<xsl:when test="(base/adjective or base/MWE/@pos='adjective')"	>שם תואר</xsl:when>
						<xsl:when test="(base/adverb or base/MWE/@pos='adverb')"	>תואר הפועל</xsl:when>
						<xsl:when test="(base/properName or base/MWE/@pos='properName')">שם פרטי</xsl:when>
						<xsl:when test="(base/conjunction or base/MWE/@pos='conjunction')">מילת חיבור</xsl:when>
						<xsl:when test="(base/verb/@tense='infinitive')">פועל - שם פועל</xsl:when>
						<xsl:when test="(base/verb/@tense='bareInfinitive')">מקור</xsl:when>
						<xsl:when test="(base/MWE)"		>ביטוי</xsl:when>
						<xsl:when test="(base/verb)"		>פועל</xsl:when>
						<xsl:when test="(base/numeral)"		>שם מספר</xsl:when>
						<xsl:when test="(base/quantifier)"	>כמת</xsl:when>
						<xsl:when test="(base/participle)"	>בינוני</xsl:when>
						<xsl:when test="(base/copula)"		>אוגד</xsl:when>
						<xsl:when test="(base/title)"		>תואר</xsl:when>
						<xsl:when test="(base/pronoun)"		>כינוי גוף</xsl:when>
						<xsl:when test="(base/numberExpression)">ביטוי מספרי</xsl:when>
						<xsl:when test="(base/existential)"	>כמת ישיי</xsl:when>
						<xsl:when test="(base/wPrefix)"		>מילת תחילית</xsl:when>
						<xsl:when test="(base/negation)"	>מילת שלילה</xsl:when>
						<xsl:when test="(base/interjection)"	>מילת קריאה</xsl:when>
						<xsl:when test="(base/interrogative)"	>מילת שאלה</xsl:when>
						<xsl:when test="(base/preposition)"	>מילת יחס</xsl:when>
						<xsl:when test="(base/modal)"		>מודאל</xsl:when>
						<xsl:when test="(base/foreign)"		>לועזית</xsl:when>
						<xsl:otherwise>
							<!-- NOTHING -->
						</xsl:otherwise>
						</xsl:choose>
					</xsl:variable>
					<xsl:variable name="ENTITY">
						<xsl:variable name="BASE"><xsl:value-of select="(base/properName/@type|base/MWE/@type)"/></xsl:variable>
						<xsl:choose>
						<xsl:when test="$BASE='person'"		>אדם</xsl:when>
						<xsl:when test="$BASE='town'"		>יישוב</xsl:when>
						<xsl:when test="$BASE='country'"	>ארץ</xsl:when>
						<xsl:when test="$BASE='location'"	>מקום</xsl:when>																			
						<xsl:when test="$BASE='organization'"	>ארגון</xsl:when>
						<xsl:when test="$BASE='language'"	>שפה</xsl:when>
						<xsl:when test="$BASE='product'"	>מוצר</xsl:when>
						<xsl:when test="$BASE='date'"		>תאריך</xsl:when>
						<xsl:when test="$BASE='time'"		>זמן</xsl:when>
						<xsl:when test="$BASE='dateTime'"	>תאריך</xsl:when>
						<xsl:when test="$BASE='money'"		>כסף</xsl:when>																
						<xsl:when test="$BASE='event'"		>ארוע</xsl:when>
						<xsl:when test="$BASE='nation'"		>אומה</xsl:when>
						<xsl:when test="$BASE='symbol'"		>אות או סימן</xsl:when>
						</xsl:choose>
					</xsl:variable>
					<xsl:variable name="GENDER">
						<xsl:choose> 
						<xsl:when test="base//@gender='masculine'">		זכר</xsl:when>
						<xsl:when test="base//@gender='feminine'">		נקבה</xsl:when>
						<xsl:when test="base//@gender='masculine and feminine'">זכר וגם נקבה</xsl:when>
						</xsl:choose>
					</xsl:variable>
					<xsl:variable name="NUMBER">
						<xsl:choose>
						<xsl:when test="base//@number='singular'">יחיד</xsl:when>
						<xsl:when test="base//@number='plural'">רבים</xsl:when>
						<xsl:when test="base//@number='dual'">	זוגי</xsl:when>
						</xsl:choose>
					</xsl:variable>
					<xsl:variable name="CONSTRUCT">
						<xsl:choose>
						<xsl:when test="base//@status='construct'">נסמך</xsl:when>
						<xsl:when test="base//@status='absolute'" >נפרד</xsl:when>
						</xsl:choose>
					</xsl:variable>
					<xsl:variable name="INTERROGATE">
						<xsl:if test="base//@interrogative='true'">ה' השאלה</xsl:if>
					</xsl:variable>
					<xsl:variable name="PERSON">
						<xsl:choose>
						<xsl:when test="base//@person='1'">גוף ראשון</xsl:when>
						<xsl:when test="base//@person='2'">גוף שני</xsl:when>
						<xsl:when test="base//@person='3'">גוף שלישי</xsl:when>
						<xsl:when test="base//@person='any'">כל גוף</xsl:when>
						<xsl:otherwise><xsl:value-of select="base//@person"/></xsl:otherwise>
						</xsl:choose>									
					</xsl:variable>
					<xsl:variable name="SUFFIX">
						<xsl:if test="suffix">קניין</xsl:if>
					</xsl:variable>
					<xsl:variable name="SUFFIX_GENDER">
						<xsl:choose>
						<xsl:when test="suffix/@gender='masculine'"		>זכר</xsl:when>
						<xsl:when test="suffix/@gender='feminine'"		>נקבה</xsl:when>
						<xsl:when test="suffix/@gender='masculine and feminine'">זכר וגם נקבה</xsl:when>
						</xsl:choose>
					</xsl:variable>
					<xsl:variable name="SUFFIX_NUMBER">
						<xsl:choose>
						<xsl:when test="suffix/@number='singular'">יחיד</xsl:when>
						<xsl:when test="suffix/@number='plural'">רבים</xsl:when>
						<xsl:when test="suffix/@number='dual'"	>זוגי</xsl:when>
						</xsl:choose>
					</xsl:variable> 
					<xsl:variable name="SUFFIX_PERSON">
						<xsl:choose>
						<xsl:when test="suffix/@person='1'">גוף ראשון</xsl:when>
						<xsl:when test="suffix/@person='2'">גוף שני</xsl:when>
						<xsl:when test="suffix/@person='3'">גוף שלישי</xsl:when>
						</xsl:choose>
					</xsl:variable>
					<xsl:variable name="TENSE">
						<xsl:choose>
						<xsl:when test="base//@tense='past'">		עבר	</xsl:when>
						<xsl:when test="base//@tense='present'">	הווה	</xsl:when>
						<xsl:when test="base//@tense='beinoni'">	בינוני	</xsl:when>
						<xsl:when test="base//@tense='future'">		עתיד	</xsl:when>
						<xsl:when test="base//@tense='imperative'">	ציווי	</xsl:when>
						<xsl:when test="base//@tense='infinitive'">	שם פועל	</xsl:when>
						<xsl:when test="base//@tense='bareInfinitive'">	מקור	</xsl:when>
						</xsl:choose>
					</xsl:variable>
					<xsl:variable name="BINYAN">
						<xsl:choose>
						<xsl:when test="contains(base//@binyan,'Pa')">קל</xsl:when>
						<xsl:when test="contains(base//@binyan,'Nif')">נִפְעַל</xsl:when>
						<xsl:when test="contains(base//@binyan,'Pi')">פיעל</xsl:when>
						<xsl:when test="contains(base//@binyan,'Pu')">פועל</xsl:when>
						<xsl:when test="contains(base//@binyan,'Hitpa')">הִתְפַּעֵל</xsl:when>
						<xsl:when test="contains(base//@binyan,'Hif')">הִפְעִיל</xsl:when>
						<xsl:when test="contains(base//@binyan,'Huf')">הֻפְעַל</xsl:when>
						</xsl:choose>
					</xsl:variable>
					<xsl:variable name="ROOT" select="base//@root"/>
					<xsl:variable name="REGISTER">
						<xsl:choose>
						<xsl:when test="base//@register='formal'">פורמלי</xsl:when>
						<xsl:when test="base//@register='archaic'">ארכאי</xsl:when>
						<xsl:when test="base//@register='informal'">מדוברת</xsl:when>
						</xsl:choose>
					</xsl:variable> 
					<xsl:variable name="SPELLING">
						<xsl:choose>
						<xsl:when test="base//@spelling='standard'">תקני</xsl:when>
						<xsl:when test="base//@spelling='irregular'">תת תקני</xsl:when>
						</xsl:choose> 
					</xsl:variable>
					
					<!-- Build HTML: -->
					<tr>
						<xsl:if test="$IS_PREFIX"><td class="PREFIX col"><xsl:value-of select="$PREFIX"/>				</td></xsl:if>
						<xsl:if test="$IS_DEFINITENESS"><td class="DEFINITENESS col"><xsl:value-of select="$DEFINITENESS"/>	</td></xsl:if>
						<xsl:if test="$IS_LEXICON_ITEM">
							<td class="LEXICON_ID LEXICON_ITEM col">
							<span class="LEXICON_ID_LINK">
								<xsl:if test="$LEXICON_ID != '' and $pointerType != '' ">
									<xsl:attribute name="onmouseover">attachInflections(this, '<xsl:value-of select="$pointerType"/>', '<xsl:value-of select="$LEXICON_ID"/>')</xsl:attribute>
									<xsl:if test="not(base/MWE)">
										<xsl:attribute name="class">clickable</xsl:attribute>
									</xsl:if>
								</xsl:if>
								<xsl:if test="$LEXICON_ID_TEXT != ''">
									<xsl:attribute name="title"><xsl:value-of select="$LEXICON_ID_TEXT"/></xsl:attribute>
								</xsl:if>
								<xsl:choose>
									<xsl:when test="$IS_LEXICON_ITEM"><xsl:value-of select="$LEXICON_ITEM"/></xsl:when>
									<xsl:otherwise><xsl:value-of select="$LEXICON_ID_TEXT"/></xsl:otherwise>
								</xsl:choose> 
							</span>
							</td>
						</xsl:if>
						<xsl:if test="true() or $IS_SCORE"> <td class="SCORE col"><xsl:value-of select="$SCORE"/>	</td></xsl:if> 
						<xsl:if test="$IS_POS">		<td class="POS col"	><xsl:value-of select="$POS"/>		</td></xsl:if>
						<xsl:if test="$IS_ENTITY">	<td class="ENTITY col"	><xsl:value-of select="$ENTITY"/>	</td></xsl:if>
						<xsl:if test="$IS_GENDER">	<td class="GENDER col"	><xsl:value-of select="$GENDER"/>	</td></xsl:if>
						<xsl:if test="$IS_NUMBER">	<td class="NUMBER col"	><xsl:value-of select="$NUMBER"/>	</td></xsl:if>
						<xsl:if test="$IS_CONSTRUCT">	<td class="CONSTRUCT col"><xsl:value-of select="$CONSTRUCT"/>	</td></xsl:if>
						<xsl:if test="$IS_INTERROGATE">	<td class="INTERROGATE col"><xsl:value-of select="$INTERROGATE"/></td></xsl:if>
						<xsl:if test="$IS_PERSON">	<td class="PERSON col"	><xsl:value-of select="$PERSON"/>	</td></xsl:if>
						<xsl:if test="$IS_S_GNP">	<td class="SUFFIX col"	><xsl:value-of select="$SUFFIX"/>	<xsl:if test="$SUFFIX != ''"> / </xsl:if>
																		<xsl:value-of select="$SUFFIX_GENDER"/><xsl:if test="$SUFFIX_GENDER != ''"> / </xsl:if>
																		<xsl:value-of select="$SUFFIX_NUMBER"/><xsl:if test="$SUFFIX_NUMBER != ''"> / </xsl:if>
																		<xsl:value-of select="$SUFFIX_PERSON"/></td></xsl:if>
						<xsl:if test="$IS_TENSE">	<td class="TENSE col"	><xsl:value-of select="$TENSE"/>	</td></xsl:if>
						<xsl:if test="$IS_BINYAN">	<td class="BINYAN col"	><xsl:value-of select="$BINYAN"/>	</td></xsl:if>
						<xsl:if test="$IS_ROOT">	<td class="ROOT col"	><xsl:value-of select="$ROOT"/>		</td></xsl:if>
						<xsl:if test="$IS_REGISTER">	<td class="REGISTER col"><xsl:value-of select="$REGISTER"/>	</td></xsl:if>
						<xsl:if test="$IS_SPELLING">	<td class="SPELLING col"><xsl:value-of select="$SPELLING"/>	</td></xsl:if>
					</tr>
				</xsl:for-each>
				</tbody>
			</table>
		</xsl:if> </xsl:for-each>
		</div>
	</xsl:for-each>
	</div>
	
	<div id="xml" dir="ltr" style="font-size: 12px;">
		<xsl:for-each select="corpus/article/paragraph">
			<xsl:call-template name="print_xml"/>
		</xsl:for-each>
	</div>
	<footer> כל הזכויות שמורות ל<a href="http://www.mila.cs.technion.ac.il">מילה</a></footer>
</body>
</html>

</xsl:template>

<xsl:template name="print_xml">
	<xsl:choose>
	<xsl:when test="not(*)">	
		<xsl:call-template name="print_attributes">
			<xsl:with-param name="closer" select="'/'"/>
		</xsl:call-template>
	</xsl:when>
	<xsl:otherwise>
		<details>
			<xsl:if test="not(name()='token')">
				<xsl:attribute name="open">open</xsl:attribute>
			</xsl:if>
			<summary>
				<xsl:call-template name="print_attributes">
					<xsl:with-param name="closer" select="''"/>
				</xsl:call-template>
			</summary>
			<xsl:call-template name="print_subitems"/>
			<xsl:call-template name="print_closing"/>
		</details>
	</xsl:otherwise>
	</xsl:choose>
</xsl:template>	

<xsl:template name="print_subitems">
	<ol id="list_{name()}" class="xmlitem list" style="list-style-type:none;">
	<xsl:for-each select="*">
		<li><xsl:call-template name="print_xml"/></li>
	</xsl:for-each>
	</ol>
</xsl:template>

<xsl:template name="print_attributes">
	<xsl:param name="closer"/>
	
	<span class="xml_delim">&lt;</span>
	<span class="xml_tag"><xsl:value-of select="name()"/></span>
	<xsl:for-each select="@*">
		<xsl:text> </xsl:text><span class="xml_att_name"><xsl:value-of select="name()"/></span><span class="xml_delim">="</span><span class="xml_att_val"><xsl:value-of select="."/></span><span class="xml_delim">"</span>
	</xsl:for-each>
	<span class="xml_delim"><xsl:value-of select="$closer"/>&gt;</span>
</xsl:template>	

<xsl:template name="print_closing">
	<span class="xml_delim">&lt;/</span>
		<span class="xml_tag"><xsl:value-of select="name()"/></span>
	<span class="xml_delim">&gt;</span>
</xsl:template>

</xsl:stylesheet>
