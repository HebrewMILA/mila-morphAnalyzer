package lexicon.gui;

import lexicon.contents.exception_types.*;
import lexicon.contents.types.*;
import lexicon.contents.*;
import java.io.File;
import java.util.Date;

public class LexiconGui {
	
	private int id;
	private int aid;  // exception id 
	private String pos = "";
	private String exceptionHtml = "";
	private String lastModified="";
	ItemType item = new ItemType();
//--------------------------------------------------------------------------------------------------------------------	
	public LexiconGui()
	{
		id = -1;
		aid = -1;
		setLastModified();
	}
//--------------------------------------------------------------------------------------------------------------------
	public void setPos(String pos) {
		this.pos = pos;
	}
//--------------------------------------------------------------------------------------------------------------------
	public String getPos() {
		return pos;
	}
//--------------------------------------------------------------------------------------------------------------------	
	private void CreatetExceptionHtml()
	{	// create an html table to show the fields for that exception
		Content mwException = null;
		
		if (pos.equals("multiWordFrozen"))
		{
			mwException = new MultiWordFrozenExceptionType();
		}
		else if (pos.equals("multiWordNoun"))
		{
			mwException = new MultiWordNounExceptionType();
		}
		else if (pos.equals("adverb"))
		{
			mwException = new AdverbExceptionType();
		}
		else if (pos.equals("adjective"))
		{
			mwException = new AdjectiveExceptionType();
		}
		else if (pos.equals("noun"))
		{
			mwException = new NounExceptionType();
		}
		else if (pos.equals("interjection"))
		{
			mwException = new InterjectionExceptionType();
		}
		else if (pos.equals("preposition"))
		{
			mwException = new PrepositionExceptionType();
		}
		else if (pos.equals("pronoun"))
		{
			mwException = new PronounExceptionType();
		}
		else if (pos.equals("properName"))
		{
			mwException = new ProperNameExceptionType();
		}
		else if (pos.equals("modal"))
		{
			mwException = new ModalExceptionType();
		}
		else if (pos.equals("quantifier"))
		{
			mwException = new QuantifierExceptionType();
		}
		else if (pos.equals("verb"))
		{
			mwException = new VerbExceptionType();
		}
		else if (pos.equals("existential"))
		{
			mwException = new ExistentialExceptionType();
		}
		else if (pos.equals("copula"))
		{
			mwException = new CopulaExceptionType();
		}
		else if (pos.equals("numeral"))
		{
			mwException = new NumeralExceptionType();
		}
		else if (pos.equals("multiWordNounAdjective"))
		{
			mwException = new MultiWordNounAdjectiveExceptionType();
		}
		else if (pos.equals("interrogative"))
		{
			mwException = new InterrogativeExceptionType();
		}
		else if (pos.equals("multiWordVerbPhrase"))
		{
			mwException = new MultiWordVerbPhraseExceptionType();
		}
		else
		{
			return;
		}
		
		if (aid > 0) mwException.open(aid); // if there is an aid then populate with data
		setExceptionHtml( mwException.GetExceptionHtml());  // get the exceptions fields html
		exceptionHtml +="<tr><td>&nbsp</td></tr>";
		if (aid == -1) exceptionHtml += "<tr><td colspan=2><div class='exception_button'><input type=Submit value='הוספת פעולה חדשה'></div></td></tr>"; // adding button
		else exceptionHtml += "<tr><td colspan=2><div class='exception_button'><input type=Submit value='עדכון הפעולה'></div></td></tr>";
	}
//--------------------------------------------------------------------------------------------------------------------
	public void setAid(int aid) {
		this.aid = aid;
		CreatetExceptionHtml();
	}

	public int getAid() {
		return aid;
	}
//--------------------------------------------------------------------------------------------------------------------
	public void setId(int id) {
		this.id = id;
		item.open(id);  // open item -> populate with info
		setPos(item.getPos());  // put right pos
		CreatetExceptionHtml();
	}
//--------------------------------------------------------------------------------------------------------------------
	public int getId() {
		return id;
	}
//--------------------------------------------------------------------------------------------------------------------
	public void setExceptionHtml(String exceptionHtml) {
		this.exceptionHtml = exceptionHtml;
	}
//--------------------------------------------------------------------------------------------------------------------
	public String getExceptionHtml() 
	{   // return the html for the exception , prepend and append the table and div tags. 
		String html = "<table width='100%'>";
		html += exceptionHtml;
		html += "</table></div>";
		return html;
	}
//--------------------------------------------------------------------------------------------------------------------
	public String getLastModified() {
		return lastModified;
	}
//--------------------------------------------------------------------------------------------------------------------
	public void setLastModified() {
		Long lastModifiedL = 0L;
		File file = new File("/usr/local/apache-tomcat-5.5.12/webapps/milaLexiconTest/jsp/general/DoItem.jsp"); // Get the last modification information for the DoItem.jsp file.
		lastModifiedL = file.lastModified();
		// Create a new date object and pass last modified information
		// to the date object.
		Date date = new Date(lastModifiedL);
		// We know when the last time the file was modified.
		this.lastModified = new String(date.toString());
	}
//--------------------------------------------------------------------------------------------------------------------
	
}
