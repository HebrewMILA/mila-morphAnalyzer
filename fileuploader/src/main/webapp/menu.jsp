<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<s:url id="index_link" action="index" />
<ul id="menu">
	<li><a href="http://mila.cs.technion.ac.il/index.html"
		class="menu-head home">Home</a></li>
	<li><a href="http://mila.cs.technion.ac.il/about.html"
		class="menu-head about">About MILA</a></li>
	<li><a href="http://mila.cs.technion.ac.il/resources.html"
		class="menu-head resources">Resources</a></li>
	<li><a href="http://mila.cs.technion.ac.il/resources_corpora.html"
		class="menu-subitem resources_corpora">Corpora</a></li>

	<li><a
		href="http://mila.cs.technion.ac.il/resources_treebank.html"
		class="menu-subitem resources_treebank">Treebank</a></li>
	<li><a
		href="http://mila.cs.technion.ac.il/resources_lexicons.html"
		class="menu-subitem resources_lexicons">Lexicons</a></li>
	<li><a
		href="http://mila.cs.technion.ac.il/resources_standards.html"
		class="menu-subitem resources_standards">Standards</a></li>
	<li><a href="http://mila.cs.technion.ac.il/tools.html"
		class="menu-head tools">Tools</a></li>
	<li><a href="http://mila.cs.technion.ac.il/tools_token.html"
		class="menu-subitem tools_token">Tokenization</a></li>
	<li><a href="http://mila.cs.technion.ac.il/tools_analysis.html"
		class="menu-subitem tools_analysis">Morphological Analysis</a></li>

	<li><a
		href="http://mila.cs.technion.ac.il/tools_disambiguation.html"
		class="menu-subitem tools_disambiguation">Morphological<br>&nbsp;Disambiguation
	</a></li>
	<li><s:a href="%{index_link}"
			cssClass="menu-subitem tools_fileupload">File Tagging<br />&nbsp;Service
	</s:a></li>
	<li><a href="http://mila.cs.technion.ac.il/tools_external.html"
		class="menu-subitem tools_external">External Tools</a></li>
	<li><a href="http://mila.cs.technion.ac.il/license.html"
		class="menu-head license">License &amp; Registration</a></li>
	<li><a href="http://mila.cs.technion.ac.il/links.html"
		class="menu-head links">Links</a></li>
	<li><a href="http://mila.cs.technion.ac.il/contact.html"
		class="menu-head contact">Contact</a></li>

</ul>