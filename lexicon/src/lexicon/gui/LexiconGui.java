package lexicon.gui;

import java.io.File;
import java.util.Date;

import lexicon.contents.Content;
import lexicon.contents.exception_types.AdjectiveExceptionType;
import lexicon.contents.exception_types.AdverbExceptionType;
import lexicon.contents.exception_types.CopulaExceptionType;
import lexicon.contents.exception_types.ExistentialExceptionType;
import lexicon.contents.exception_types.InterjectionExceptionType;
import lexicon.contents.exception_types.InterrogativeExceptionType;
import lexicon.contents.exception_types.ModalExceptionType;
import lexicon.contents.exception_types.MultiWordFrozenExceptionType;
import lexicon.contents.exception_types.MultiWordNounAdjectiveExceptionType;
import lexicon.contents.exception_types.MultiWordNounExceptionType;
import lexicon.contents.exception_types.MultiWordVerbPhraseExceptionType;
import lexicon.contents.exception_types.NounExceptionType;
import lexicon.contents.exception_types.NumeralExceptionType;
import lexicon.contents.exception_types.PrepositionExceptionType;
import lexicon.contents.exception_types.PronounExceptionType;
import lexicon.contents.exception_types.ProperNameExceptionType;
import lexicon.contents.exception_types.QuantifierExceptionType;
import lexicon.contents.exception_types.VerbExceptionType;
import lexicon.contents.types.ItemType;

public class LexiconGui {

	private int id;
	private int aid; // exception id
	private String pos = "";
	private String exceptionHtml = "";
	private String lastModified = "";
	ItemType item = new ItemType();

	// --------------------------------------------------------------------------------------------------------------------
	public LexiconGui() {
		this.id = -1;
		this.aid = -1;
		this.setLastModified();
	}

	// --------------------------------------------------------------------------------------------------------------------
	private void CreatetExceptionHtml() { // create an html table to show the
											// fields for that exception
		Content mwException = null;

		if (this.pos.equals("multiWordFrozen")) {
			mwException = new MultiWordFrozenExceptionType();
		} else if (this.pos.equals("multiWordNoun")) {
			mwException = new MultiWordNounExceptionType();
		} else if (this.pos.equals("adverb")) {
			mwException = new AdverbExceptionType();
		} else if (this.pos.equals("adjective")) {
			mwException = new AdjectiveExceptionType();
		} else if (this.pos.equals("noun")) {
			mwException = new NounExceptionType();
		} else if (this.pos.equals("interjection")) {
			mwException = new InterjectionExceptionType();
		} else if (this.pos.equals("preposition")) {
			mwException = new PrepositionExceptionType();
		} else if (this.pos.equals("pronoun")) {
			mwException = new PronounExceptionType();
		} else if (this.pos.equals("properName")) {
			mwException = new ProperNameExceptionType();
		} else if (this.pos.equals("modal")) {
			mwException = new ModalExceptionType();
		} else if (this.pos.equals("quantifier")) {
			mwException = new QuantifierExceptionType();
		} else if (this.pos.equals("verb")) {
			mwException = new VerbExceptionType();
		} else if (this.pos.equals("existential")) {
			mwException = new ExistentialExceptionType();
		} else if (this.pos.equals("copula")) {
			mwException = new CopulaExceptionType();
		} else if (this.pos.equals("numeral")) {
			mwException = new NumeralExceptionType();
		} else if (this.pos.equals("multiWordNounAdjective")) {
			mwException = new MultiWordNounAdjectiveExceptionType();
		} else if (this.pos.equals("interrogative")) {
			mwException = new InterrogativeExceptionType();
		} else if (this.pos.equals("multiWordVerbPhrase")) {
			mwException = new MultiWordVerbPhraseExceptionType();
		} else
			return;

		if (this.aid > 0) {
			mwException.open(this.aid); // if there is an aid then populate with
										// data
		}
		this.setExceptionHtml(mwException.GetExceptionHtml()); // get the
																// exceptions
																// fields html
		this.exceptionHtml += "<tr><td>&nbsp</td></tr>";
		if (this.aid == -1) {
			this.exceptionHtml += "<tr><td colspan=2><div class='exception_button'><input type=Submit value='הוספת פעולה חדשה'></div></td></tr>"; // adding
																																					// button
		} else {
			this.exceptionHtml += "<tr><td colspan=2><div class='exception_button'><input type=Submit value='עדכון הפעולה'></div></td></tr>";
		}
	}

	public int getAid() {
		return this.aid;
	}

	// --------------------------------------------------------------------------------------------------------------------
	public String getExceptionHtml() { // return the html for the exception ,
										// prepend and append the table and div
										// tags.
		String html = "<table width='100%'>";
		html += this.exceptionHtml;
		html += "</table></div>";
		return html;
	}

	// --------------------------------------------------------------------------------------------------------------------
	public int getId() {
		return this.id;
	}

	// --------------------------------------------------------------------------------------------------------------------
	public String getLastModified() {
		return this.lastModified;
	}

	// --------------------------------------------------------------------------------------------------------------------
	public String getPos() {
		return this.pos;
	}

	// --------------------------------------------------------------------------------------------------------------------
	public void setAid(int aid) {
		this.aid = aid;
		this.CreatetExceptionHtml();
	}

	// --------------------------------------------------------------------------------------------------------------------
	public void setExceptionHtml(String exceptionHtml) {
		this.exceptionHtml = exceptionHtml;
	}

	// --------------------------------------------------------------------------------------------------------------------
	public void setId(int id) {
		this.id = id;
		this.item.open(id); // open item -> populate with info
		this.setPos(this.item.getPos()); // put right pos
		this.CreatetExceptionHtml();
	}

	// --------------------------------------------------------------------------------------------------------------------
	public void setLastModified() {
		Long lastModifiedL = 0L;
		final File file = new File(
				"/usr/local/apache-tomcat-5.5.12/webapps/milaLexiconTest/jsp/general/DoItem.jsp"); // Get
																									// the
																									// last
																									// modification
																									// information
																									// for
																									// the
																									// DoItem.jsp
																									// file.
		lastModifiedL = file.lastModified();
		// Create a new date object and pass last modified information
		// to the date object.
		final Date date = new Date(lastModifiedL);
		// We know when the last time the file was modified.
		this.lastModified = new String(date.toString());
	}

	// --------------------------------------------------------------------------------------------------------------------

	// --------------------------------------------------------------------------------------------------------------------
	public void setPos(String pos) {
		this.pos = pos;
	}

}
