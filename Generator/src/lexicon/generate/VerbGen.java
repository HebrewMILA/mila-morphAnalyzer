/*
 * Created on 11/09/2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package lexicon.generate;

import lexicon.contents.types.ItemType;
import lexicon.stringUtils.Translate;

/**
 * @author daliabo
 * 
 * TODO To change the template for this generated type comment go to Window -
 * Preferences - Java - Code Style - Code Templates
 */
public class VerbGen extends ItemGen 
{
	String originalSpelling = "";
	String root = "";
	String binyan = "";
	String inflectedPattern = "";

	boolean inflectInfinitive = true;
	boolean inflectInfinitivel = true;
	boolean inflectInfinitiveb = true;
	boolean inflectPast = true;
	boolean inflectBeinoni = true;
	boolean inflectBeinoniPossessive = true;
	boolean inflectBeinoniConstruct = true;
	boolean inflectFuture = true;
	boolean inflectImperative = true;
	boolean inflectBareInfinitive = true;
	boolean inflectInfinitiveIndependent = false;
	int intInflectedPattern;

	String valence = "";
	String inflectionsTable = "inflections";
	VerbGenIP vgIP = null;

	public VerbGen(ItemType item) 
	{
		super(item);
	}

	public void setInflectionsTable(String _table)
	{
		inflectionsTable = _table;
	}
	
	private void analyseVerb() 
	{
	    //System.out.println("(F) VerbGen:analyseVerb()");
		super.analyseItem();
		root = item.getVerb().getRoot();
		//root = root.replaceAll("&#39;", "'");
		root = Translate.Heb2Eng(root);
		System.out.println("1111111111111:"+root);
		inflectedPattern = item.getVerb().getInflectionPattern();
		System.out.println("1111111111111:"+inflectedPattern);
		intInflectedPattern = Integer.parseInt(inflectedPattern);
		register = item.getRegister();
		spelling = item.getSpelling();
		valence = item.getVerb().getValence();
		inflectInfinitive = item.getVerb().isInflectInfinitive();
		inflectInfinitivel = item.getVerb().isInflectInfinitivel();
		inflectInfinitiveb = item.getVerb().isInflectInfinitiveb();
		inflectPast = item.getVerb().isInflectPast();
		inflectBeinoni = item.getVerb().isInflectBeinoni();
		inflectBeinoniPossessive = item.getVerb().isInflectBeinoniPossessive();
		inflectBeinoniConstruct = item.getVerb().isInflectBeinoniConstruct();
		inflectFuture = item.getVerb().isInflectFuture();
		inflectImperative = item.getVerb().isInflectImperative();
		inflectBareInfinitive = item.getVerb().isInflectOrigin();
		inflectInfinitiveIndependent = item.getVerb().isInflectInfinitiveIndependent();
		originalSpelling = spelling;
		//System.out.println("InflectedPattern=" + intInflectedPattern);
	}

	private void setGenInflection() 
	{
	    //System.out.println("(F) VerbGen:setGenInflection()");
		vgIP = new VerbGenIP(root);
		vgIP.setTable(inflectionsTable);  // set table to either inflections or to tempInflection for the multiwordverbPhrase
		vgIP.setHebRoot(item.getVerb().getRoot());
		vgIP.setBaseTransliterated(transliterated);
		vgIP.setBaseUndot(undot);
		vgIP.setBaseLexiconPointer(id);
		vgIP.setBinyan(binyan);
		vgIP.setRegister(register);
		vgIP.setSpelling(spelling);
		vgIP.setReplaceExceptionList(replaceExceptionList);
		vgIP.setRemoveExceptionList(removeExceptionList);
		vgIP.setIntInflectedPattern(intInflectedPattern);
		vgIP.setValence(valence);
		vgIP.setDottedLexiconItem(dottedLexiconItem);
		vgIP.setInflectInfinitivel(inflectInfinitivel);
		//vgIP.setInflectInfinitive(inflectInfinitive);
		vgIP.setInflectInfinitiveb(inflectInfinitiveb);
		vgIP.setInflectBeinoni(inflectBeinoni);
		//vgIP.setInflectInfinitiveIndependent(inflectInfinitiveIndependent);
		vgIP.setInflectBeinoniPossessive(inflectBeinoniPossessive);
		vgIP.setInflectBeinoniConstruct(inflectBeinoniConstruct);
		vgIP.setInflectPast(inflectPast);
		vgIP.setInflectImperative(inflectImperative);
		vgIP.setInflectFuture(inflectFuture);
		vgIP.setInflectBareInfinitive(inflectBareInfinitive);
	}

	private void inflectPattern1() throws Exception 
	{
	    //System.out.println("(F) VerbGen:inflectPattern1()");
		setGenInflection();
		vgIP.generateBareInfinitive("", "w", 0, 2, 2, 3, "");
		vgIP.generateInfinitive("", "w", 0, 1, 1, 3, "");
		vgIP.generatePast("", "ti,t,t,-,h,nw,tm,tn,w", "", 0, 3, 3, 3);
		vgIP.generatePresent("", "-,t,im,wt", "w,w,w,w", 0, 1, 1, 3);
		vgIP.generateFuture("a,t,t,i,t,n,t,t,i,t", "-,-,i,-,-,-,w,nh,w,nh","w,w,-,w,w,w,-,w,-,w", 0, 2, 2, 3);
		vgIP.generateImperative("", "-,i,w,nh", "w,-,-,w", 0, 2, 2, 3);
		vgIP.generateBeinoniPassive((new StringBuffer().append(root.substring(0, 2)).append("w").append(root.substring(2, 3))).toString());
	}

	private void inflectPattern2() throws Exception 
	{
		setGenInflection();
		//		ע הפועל גרונית שורה- 13
		// ל גרונית שורות 14 15
		// פ גרונית שורה 16
		if (root.charAt(1) == 'w') 
		{
			vgIP.generateBareInfinitive("", "w", 0, 2, 2, 3, "");
			vgIP.generateInfinitive("", "w", 0, 1, 1, 3, "");
			vgIP.generatePast("", "ti,t,t,-,h,nw,tm,tn,w", "w,w,w,w,w,w,w,w,w",0, 1, 1, 3);
			vgIP.generatePresent("", "-,t,im,wt", "w,w,w,w", 0, 1, 1, 3);
			vgIP.generateFuture("a,t,t,i,t,n,t,t,i,t", "-,-,i,-,-,-,w,nh,w,nh","w,w,w,w,w,w,w,w,w,w", 0, 1, 1, 3);
			vgIP.generateImperative("", "-,i,w,nh", "w,w,w,w", 0, 1, 1, 3);
			vgIP.setSpelling(IRREGULAR_SPELLING);
			vgIP.generatePast("", "ti,t,t,-,h,nw,tm,tn,w", "", 0, 1, 1, 3);
			vgIP.generateFuture("a,t,t,i,t,n,t,t,i,t", "-,-,i,-,-,-,w,nh,w,nh","", 0, 1, 1, 3);
			vgIP.generateImperative("", "-,i,w,nh", "", 0, 1, 1, 3);
		} 
		else if (root.charAt(1) == 'a' || root.charAt(1) == 'h'
				|| root.charAt(1) == 'x' || root.charAt(1) == 'y'
				|| root.charAt(2) == 'a' || root.charAt(2) == 'h'
				|| root.charAt(2) == 'x' || root.charAt(2) == 'y'
				|| root.charAt(0) == 'a' || root.charAt(0) == 'h'
				|| root.charAt(0) == 'x' || root.charAt(0) == 'y') 
		{
			vgIP.generateBareInfinitive("", "w", 0, 2, 2, 3, "");
			vgIP.generateInfinitive("", "w", 0, 1, 1, 3, "");
			vgIP.generatePast("", "ti,t,t,-,h,nw,tm,tn,w", "", 0, 3, 3, 3);
			vgIP.generatePresent("", "-,t,im,wt", "w,w,w,w", 0, 1, 1, 3);
			vgIP.generateFuture("a,t,t,i,t,n,t,t,i,t", "-,-,i,-,-,-,w,nh,w,nh","-,-,-,-,-,-,-,-,-,-", 0, 2, 2, 3);
			vgIP.generateImperative("", "-,i,w,nh", "-,-,-,-", 0, 2, 2, 3);
		} 
		else if (root.charAt(0) == 'i') 
		{
			vgIP.generateBareInfinitive("", "w", 0, 2, 2, 3, "");
			vgIP.generateInfinitive("", "w", 0, 1, 1, 3, "");
			vgIP.generatePast("", "ti,t,t,-,h,nw,tm,tn,w", "", 0, 3, 3, 3);
			vgIP.generatePresent("", "-,t,im,wt", "w,w,w,w", 0, 1, 1, 3);
			vgIP.generateFuture("a,t,t,i,t,n,t,t,i,t", "-,-,i,-,-,-,w,nh,w,nh","-,-,-,-,-,-,-,-,-,-", 0, 2, 2, 3);
			vgIP.generateImperative("", "-,i,w,nh", "-,-,-,-", 0, 2, 2, 3);
		} 
		else 
		{
			vgIP.generateBareInfinitive("", "w", 0, 2, 2, 3, "");
			vgIP.generateInfinitive("", "w", 0, 1, 1, 3, "");
			vgIP.generatePast("", "ti,t,t,-,h,nw,tm,tn,w", "", 0, 3, 3, 3);
			vgIP.generatePresent("", "-,t,im,wt", "w,w,w,w", 0, 1, 1, 3);
			vgIP.generateFuture("a,t,t,i,t,n,t,t,i,t", "-,-,i,-,-,-,w,nh,w,nh","-,-,-,-,-,-,-,-,-,-", 0, 2, 2, 3);
			if (root.charAt(2) == 'n')
				vgIP.generateImperative("", "-,i,w,h", "-,-,-,-", 0, 2, 2, 3);
			else
				vgIP.generateImperative("", "-,i,w,nh", "-,-,-,-", 0, 2, 2, 3);
		}
		//if ends with a - two alternatives
		//עבור אלה שמסתיימים בא - מייצרים את הצורה עם סיומת י' ע"י humth supi
		// ckexheui
		if (root.charAt(2) != 'a') 
		{
			//			vgIP.generateBeinoniPassive((new StringBuffer().append(root
			//					.substring(0, 2)).append("wi")).toString());
			//			vgIP.generateBeinoniPassive((new StringBuffer().append(
			//					root.substring(0, 2)).append("w").append(root.substring(2,
			//					3))).toString());
			//		}else
			vgIP.generateBeinoniPassive((new StringBuffer().append(root.substring(0, 2)).append("w").append(root.substring(2,3))).toString());
		}

	}

	private void inflectPattern3() throws Exception 
	{
		setGenInflection();
		int len = root.length();
		vgIP.generateBareInfinitive("", "w", 0, len - 1, len - 1, len, "");
		vgIP.generateInfinitive("", "w", 0, 1, 1, len, "");
		if (root.charAt(1) == 'i') {vgIP.generatePast("", "ti,t,t,-,h,nw,tm,tn,w", "i,i,i,i,i,i,i,i,i",0, 2, 2, len);
		} 
		else
			vgIP.generatePast("", "ti,t,t,-,h,nw,tm,tn,w", "-,-,-,-,-,-,-,-,-",0, 2, 2, len);
		vgIP.generatePresent("", "-,h,im,wt", "", 0, 1, 1, len);
		if (root.charAt(2) == 'n') 
		{
			vgIP.generateFuture("a,t,t,i,t,n,t,t,i,t", "-,-,i,-,-,-,w,h,w,h","", 0, len, len, len);
			vgIP.generateImperative("", "-,i,w,h", "", 0, len, len, len);
		} 
		else 
		{
			vgIP.generateFuture("a,t,t,i,t,n,t,t,i,t", "-,-,i,-,-,-,w,nh,w,nh","", 0, len, len, len);
			vgIP.generateImperative("", "-,i,w,nh", "", 0, len, len, len);
		}
		vgIP.generateBeinoniPassive((new StringBuffer().append(root.substring(0, len - 1)).append("w").append(root.substring(len - 1, len))).toString());
	}

	private void inflectPattern4() throws Exception 
	{
		setGenInflection();
		vgIP.generateBareInfinitive("", "w", 0, 2, 2, 3, "");
		vgIP.generateInfinitive("", "w", 0, 1, 1, 3, "");
		vgIP.generatePast("", "ti,t,t,-,h,nw,tm,tn,w", "", 0, 3, 3, 3);
		vgIP.generatePresent("", "-,h,im,wt", "i,i,i,i", 0, 2, 2, 3);
		vgIP.generateFuture("a,t,t,i,t,n,t,t,i,t", "-,-,i,-,-,-,w,nh,w,nh", "",0, 3, 3, 3);
		vgIP.generateImperative("", "-,i,w,nh", "", 0, 3, 3, 3);
		vgIP.generateBeinoniPassive((new StringBuffer().append(root.substring(0, 2)).append("w").append(root.substring(2, 3))).toString());
	}

	private void inflectPattern5() throws Exception 
	{
		setGenInflection();
		vgIP.generateBareInfinitive("", "w", 0, 2, 2, 3, "");
		vgIP.generateInfinitive("", "w", 0, 1, 1, 3, "");
		vgIP.generatePast("", "ti,t,t,-,h,nw,tm,tn,w", "", 0, 3, 3, 3);
		vgIP.generatePresent("", "-,t,im,wt", "w,w,w,w", 0, 1, 1, 3);
		vgIP.generateFuture("-,t,t,i,t,n,t,t,i,t", "-,-,i,-,-,-,w,nh,w,nh","w,-,-,-,-,-,-,-,-,-", 0, 1, 1, 3);
		vgIP.generateImperative("", "-,i,w,nh", "w,-,-,w", 0, 2, 2, 3);
		vgIP.generateBeinoniPassive((new StringBuffer().append(root.substring(0, 2)).append("w").append(root.substring(2, 3))).toString());
	}

	private void inflectPattern6() throws Exception 
	{
		setGenInflection();
		vgIP.generateBareInfinitive("", "", 1, 3, 3, 3, "t");
		vgIP.generateInfinitive("", "", 1, 3, 3, 3, "t");
		vgIP.generatePast("", "ti,t,t,-,h,nw,tm,tn,w", "", 0, 3, 3, 3);
		vgIP.generatePresent("", "-,t,im,wt", "w,w,w,w", 0, 1, 1, 3);
		vgIP.generateFuture("a,t,t,i,t,n,t,t,i,t", "-,-,i,-,-,-,w,nh,w,nh", "",1, 3, 3, 3);
		vgIP.generateImperative("", "-,i,w,nh", "-,-,-,-,", 1, 2, 2, 3);
		vgIP.generateBeinoniPassive((new StringBuffer().append(root.substring(0, 2)).append("w").append(root.substring(2, 3))).toString());
	}

	private void inflectPattern7() throws Exception 
	{
		setGenInflection();
		vgIP.generateBareInfinitive("", "w", 0, 2, 2, 3, "");
		vgIP.generateInfinitive("", "w", 0, 1, 1, 3, "");
		vgIP.generatePast("", "ti,t,t,-,h,nw,tm,tn,w", "", 0, 3, 3, 3);
		vgIP.generatePresent("", "-,t,im,wt", "w,w,w,w", 0, 1, 1, 3);
		//Shlomo doesn't have it
		//vgIP.generateFuture("a,t,t,i,t,n,t,t,i,t","-,-,i,-,-,-,w,nh,w,nh","",1,3,3);
		vgIP.generateFuture("a,ti,ti,ii,ti,ni,ti,ti,ii,ti","-,-,i,-,-,-,w,nh,w,nh", "w,w,-,w,w,w,-,w,-,w", 1, 2, 2, 3);
		vgIP.generateImperative("", "-,i,w,nh", "w,-,-,w", 0, 2, 2, 3);
		vgIP.generateBeinoniPassive((new StringBuffer().append(root.substring(0, 2)).append("w").append(root.substring(2, 3))).toString());
	}

	private void inflectPattern8() throws Exception 
	{
		setGenInflection();
		vgIP.generateBareInfinitive("", "w", 0, 2, 2, 3, "");
		vgIP.generateInfinitive("", "w", 0, 1, 1, 3, "");
		vgIP.generatePast("", "ti,t,t,-,h,nw,tm,tn,w", "", 0, 3, 3, 3);
		vgIP.generatePresent("", "-,t,im,wt", "w,w,w,w", 0, 1, 1, 3);
		vgIP.generateFuture("a,ti,ti,ii,ti,ni,ti,ti,ii,ti","-,-,i,-,-,-,w,nh,w,nh", "w,w,-,w,w,w,-,w,-,w", 1, 2, 2, 3);
		vgIP.generateImperative("", "-,i,w,nh", "w,-,-,w", 0, 2, 2, 3);
		vgIP.generateBeinoniPassive((new StringBuffer().append(root.substring(0, 2)).append("w").append(root.substring(2, 3))).toString());
	}

	private void inflectPattern9() throws Exception 
	{
		setGenInflection();
		vgIP.generateBareInfinitive("", "w", 0, 2, 2, 3, "");
		vgIP.generateInfinitive("", "w", 0, 1, 1, 3, "");
		vgIP.generatePast("", "ti,t,t,-,h,nw,tm,tn,w", "", 0, 3, 3, 3);
		vgIP.generatePresent("", "-,t,im,wt", "w,w,w,w", 0, 1, 1, 3);
		vgIP.generateFuture("a,ti,ti,ii,ti,ni,ti,ti,ii,ti","-,-,i,-,-,-,w,nh,w,nh", "", 1, 3, 3, 3);
		vgIP.generateImperative("", "-,i,w,nh", "-,-,-,-,", 1, 2, 2, 3);
		vgIP.generateBeinoniPassive((new StringBuffer().append(root.substring(0, 2)).append("w").append(root.substring(2, 3))).toString());
	}

	private void inflectPattern10() throws Exception 
	{
		setGenInflection();
		vgIP.generateBareInfinitive("", "w", 0, 2, 2, 3, "");
		vgIP.generateInfinitive("", "w", 0, 1, 1, 3, "");
		vgIP.generatePast("", "ti,t,t,-,h,nw,tm,tn,w", "", 0, 3, 3, 3);
		vgIP.generatePresent("", "-,t,im,wt", "w,w,w,w", 0, 1, 1, 3);
		vgIP.generateFuture("a,ti,ti,ii,ti,ni,ti,ti,ii,ti","-,-,i,-,-,-,w,nh,w,nh", "", 1, 3, 3, 3);
		vgIP.setSpelling(IRREGULAR_SPELLING);
		vgIP.generateFuture("ai,t,t,i,t,n,t,t,i,t", "-,-,i,-,-,-,w,nh,w,nh","", 1, 3, 3, 3);
		vgIP.setSpelling(originalSpelling);
		vgIP.generateImperative("", "-,i,w,nh", "-,-,-,-,", 1, 2, 2, 3);
		vgIP.generateBeinoniPassive((new StringBuffer().append(root.substring(0, 2)).append("w").append(root.substring(2, 3))).toString());
	}

	private void inflectPattern11() throws Exception 
	{
		setGenInflection();
		int len = root.length();
		if (root.charAt(1) == 'w') 
		{
			vgIP.setSpelling(IRREGULAR_SPELLING);

			vgIP.generatePast("", "ti,t,t,h,th,nw,tm,tn,w","i,i,i,-,-,i,i,i,-", 0, len - 1, len, len);
			vgIP.generateFuture("a,t,t,i,t,n,t,t,i,t","h,h,i,h,h,h,ww,inh,ww,inh", "-,-,-,-,-,-,-,-,-,-", 0,len - 1, len, len);

			vgIP.setSpelling(originalSpelling);

			root = root.replaceAll("w", "ww");
			setGenInflection();
			vgIP.generateBareInfinitive("", "", 0, len, len, len, "t");
			vgIP.generateInfinitive("", "", 0, len, len, len, "t");
			vgIP.generatePast("", "ti,t,t,h,th,nw,tm,tn,-","i,i,i,-,-,i,i,i,-", 0, len, len, len);
			vgIP.generatePresent("", "h,h,im,wt", "w,w,w,w", 0, len - 2,len - 2, len - 1);
			vgIP.generateFuture("a,t,t,i,t,n,t,t,i,t","h,h,i,h,h,h,w,inh,-,inh", "-,-,-,-,-,-,-,-,-,-", 0, len,len, len);
			vgIP.generateImperative("", "h,i,-,inh", "", 0, len, len, len);
			vgIP.generateBeinoniPassive((new StringBuffer().append(root.substring(0, len - 1)).append("wi")).toString());
		} 
		else 
		{
			vgIP.generateBareInfinitive("", "w", 0, len - 1, len, len, "t");
			vgIP.generateInfinitive("", "w", 0, len - 1, len, len, "t");
			vgIP.generatePast("", "ti,t,t,h,th,nw,tm,tn,w","i,i,i,-,-,i,i,i,-", 0, len - 1, len, len);
			vgIP.generatePresent("", "h,h,im,wt", "w,w,w,w", 0, len - 2,len - 2, len - 1);
			vgIP.generateFuture("a,t,t,i,t,n,t,t,i,t","h,h,i,h,h,h,w,inh,w,inh", "-,-,-,-,-,-,-,-,-,-", 0,len - 1, len, len);
			vgIP.generateImperative("", "h,i,w,inh", "", 0, len - 1, len, len);
			vgIP.generateBeinoniPassive((new StringBuffer().append(root.substring(0, len - 1)).append("wi")).toString());
		}
	}

	private void inflectPattern12() throws Exception 
	{
		setGenInflection();
		vgIP.generateBareInfinitive("", "w", 0, 2, 3, 3, "t");
		vgIP.generateInfinitive("", "w", 0, 2, 3, 3, "t");
		vgIP.generatePast("", "ti,t,t,h,th,nw,tm,tn,w", "i,i,i,-,-,i,i,i,-", 0,2, 3, 3);
		vgIP.generatePresent("", "h,h,im,wt", "w,w,w,w", 0, 1, 1, 2);
		vgIP.generateFuture("a,t,t,i,t,n,t,t,i,t", "h,h,i,h,h,h,w,inh,w,inh","-,-,-,-,-,-,-,-,-,-", 1, 2, 3, 3);
		vgIP.generateImperative("", "h,i,w,inh", "", 0, 2, 3, 3);
		vgIP.generateBeinoniPassive((new StringBuffer().append(root.substring(0, 2)).append("w").append(root.substring(2, 3))).toString());
	}

	private void inflectPattern13() throws Exception 
	{
		setGenInflection();
		vgIP.generateBareInfinitive("", "w", 0, 2, 2, 3, "");
		vgIP.generateInfinitive("", "w", 0, 1, 1, 3, "");
		vgIP.generatePast("", "ti,t,t,-,h,w,tm,tn,w", "", 0, 3, 3, 3);
		vgIP.generatePresent("", "-,t,im,wt", "w,w,w,w", 0, 1, 1, 3);
		vgIP.generateFuture("a,t,t,i,t,n,t,t,i,t", "-,-,i,-,-,-,w,h,w,h","w,w,-,w,w,w,-,w,-,w", 0, 2, 2, 3);
		vgIP.generateImperative("", "-,i,w,h", "w,-,-,w", 0, 2, 2, 3);
		vgIP.generateBeinoniPassive((new StringBuffer().append(root.substring(0, 2)).append("w").append(root.substring(2, 3))).toString());
	}

	private void inflectPattern14() throws Exception 
	{
		setGenInflection();
		vgIP.generateBareInfinitive("", "w", 0, 2, 2, 3, "");
		vgIP.generateInfinitive("", "w", 0, 1, 1, 3, "");
		vgIP.generatePast("", "ti,t,t,-,h,w,tm,tn,w", "", 0, 3, 3, 3);
		vgIP.generatePresent("", "-,h,im,wt", "", 0, 1, 1, 3);
		vgIP.generateFuture("a,t,t,i,t,n,t,t,i,t", "-,-,i,-,-,-,w,h,w,h", "",0, 3, 3, 3);
		vgIP.generateImperative("", "-,i,w,h", "", 0, 3, 3, 3);
		vgIP.generateBeinoniPassive((new StringBuffer().append(root.substring(0, 2)).append("w").append(root.substring(2, 3))).toString());
	}

	private void inflectPattern15() throws Exception 
	{
		setGenInflection();
		vgIP.generateBareInfinitive("", "w", 0, 2, 2, 3, "");
		vgIP.generateInfinitive("", "w", 0, 1, 1, 3, "");
		vgIP.generatePast("", "ti,t,t,-,h,w,tm,tn,w", "", 0, 3, 3, 3);
		vgIP.generatePresent("", "-,t,im,wt", "w,w,w,w", 0, 1, 1, 3);
		vgIP.generateFuture("a,t,t,i,t,n,t,t,i,t", "-,-,i,-,-,-,w,h,w,h", "",0, 3, 3, 3);
		vgIP.generateImperative("", "-,i,w,h", "", 0, 3, 3, 3);
		vgIP.generateBeinoniPassive((new StringBuffer().append(root.substring(0, 2)).append("w").append(root.substring(2, 3))).toString());
	}

	private void inflectPattern16() throws Exception 
	{
		setGenInflection();
		vgIP.generateBareInfinitive("", "w", 0, 2, 2, 3, "");
		vgIP.generateInfinitive("", "w", 0, 1, 1, 3, "");
		vgIP.generatePast("", "ti,t,t,t,th,tnw,tm,tn,tw", "-,-,-,-,-,-,-,-,-",0, 2, 3, 3);
		vgIP.generatePresent("", "-,t,im,wt", "w,w,w,w", 0, 1, 1, 3);
		vgIP.generateFuture("a,t,t,i,t,n,t,t,i,t", "-,-,i,-,-,-,w,nh,w,nh","w,w,-,w,w,w,-,w,-,w", 0, 2, 2, 3);
		vgIP.generateImperative("", "-,i,w,nh", "w,-,-,w", 0, 2, 2, 3);
		vgIP.generateBeinoniPassive((new StringBuffer().append(root.substring(0, 2)).append("w").append(root.substring(2, 3))).toString());
	}

	private void inflectPattern17() throws Exception 
	{
		setGenInflection();
		vgIP.generateBareInfinitive("", "w", 0, 2, 2, 3, "");
		vgIP.generateInfinitive("", "w", 0, 1, 1, 3, "");
		vgIP.generatePast("", "ti,t,t,t,th,tnw,tm,tn,tw", "-,-,-,-,-,-,-,-,-",0, 2, 3, 3);
		vgIP.generatePresent("", "-,t,im,wt", "w,w,w,w", 0, 1, 1, 3);
		vgIP.generateFuture("a,t,t,i,t,n,t,t,i,t", "-,-,i,-,-,-,w,nh,w,nh", "",0, 3, 3, 3);
		vgIP.generateImperative("", "-,i,w,nh", "", 0, 3, 3, 3);
		vgIP.generateBeinoniPassive((new StringBuffer().append(root.substring(0, 2)).append("w").append(root.substring(2, 3))).toString());
	}

	private void inflectPattern18() throws Exception 
	{
		setGenInflection();
		vgIP.generateBareInfinitive("", "", 0, 2, 2, 3, "");
		vgIP.generateInfinitive("", "", 0, 2, 2, 3, "");
		if (root.charAt(2) == 't')
			vgIP.generatePast("", "i,-,-,-,h,nw,m,n,w", "", 0, 1, 2, 3);
		else
			vgIP.generatePast("", "ti,t,t,-,h,nw,tm,tn,w", "", 0, 1, 2, 3);

		vgIP.generatePresent("", "-,h,im,wt", "", 0, 1, 2, 3);

		vgIP.generateFuture("a,t,t,i,t,n,t,t,i,t", "-,-,i,-,-,-,w,nh,w,nh", "",0, 3, 3, 3);
		vgIP.generateImperative("", "-,i,w,nh", "", 0, 3, 3, 3);
		vgIP.generateBeinoniPassive(root);

	}

	private void inflectPattern19() throws Exception 
	{
		setGenInflection();
		vgIP.generateBareInfinitive("", "", 0, 3, 3, 3, "");
		vgIP.generateInfinitive("", "", 0, 3, 3, 3, "");
		vgIP.generatePast("", "ti,t,t,-,h,nw,tm,tn,w", "", 0, 1, 2, 3);
		vgIP.generatePresent("", "-,h,im,wt", "", 0, 1, 2, 3);
		vgIP.generateFuture("a,t,t,i,t,n,t,t,i,t", "-,-,i,-,-,-,w,nh,w,nh","w,w,w,w,w,w,w,-,w,-", 0, 1, 2, 3);
		vgIP.generateImperative("", "-,i,w,nh", "w,w,w,-", 0, 1, 2, 3);
		vgIP.generateBeinoniPassive(root);
	}

	private void inflectPattern20() throws Exception 
	{
		setGenInflection();
		vgIP.generateBareInfinitive("", "", 0, 3, 3, 3, "");
		vgIP.generateInfinitive("", "", 0, 3, 3, 3, "");
		vgIP.generatePast("", "ti,t,t,-,h,w,tm,tn,w", "", 0, 1, 2, 3);
		vgIP.generatePresent("", "-,h,im,wt", "", 0, 1, 2, 3);
		vgIP.generateFuture("a,t,t,i,t,n,t,t,i,t", "-,-,i,-,-,-,w,h,w,h", "",0, 3, 3, 3);
		vgIP.generateImperative("", "-,i,w,h", "", 0, 3, 3, 3);
		vgIP.generateBeinoniPassive(root);
	}

	private void inflectPattern21() throws Exception 
	{
		setGenInflection();
		vgIP.generateBareInfinitive("", "", 0, 3, 3, 3, "");
		vgIP.generateInfinitive("", "", 0, 3, 3, 3, "");
		if (root.charAt(2) == 't')
			vgIP.generatePast("", "i,-,-,-,h,nw,m,n,w", "", 0, 1, 2, 3);
		else
			vgIP.generatePast("", "ti,t,t,-,h,nw,tm,tn,w", "", 0, 1, 2, 3);
		vgIP.generatePresent("", "-,h,im,wt", "", 0, 1, 2, 3);
		vgIP.generateFuture("a,t,t,i,t,n,t,t,i,t", "-,-,i,-,-,-,w,nh,w,nh","i,i,i,i,i,i,i,-,i,-", 0, 1, 2, 3);
		vgIP.generateImperative("", "-,i,w,nh", "i,i,i,-", 0, 1, 2, 3);
		vgIP.generateBeinoniPassive(root.substring(0, 1) + "w"+ root.substring(2));
	}

	private void inflectPattern22() throws Exception 
	{
	    //System.out.println("(F) VerbGen:inflectPattern22()");
		setGenInflection();
		vgIP.generateBareInfinitive("", "w", 0, 1, 2, 3, "");
		vgIP.generateInfinitive("", "w", 0, 1, 2, 3, "");
		String suffixes = "ti,t,t," + root.charAt(2) + "," + root.charAt(2)+ "h,nw,tm,tn," + root.charAt(2) + "w";
		vgIP.generatePast("", suffixes, "w,w,w,-,-,w,w,w,-", 0, 2, 3, 3);
		String inside1 = "w" + root.charAt(2) + ",";
		String inside2 = inside1 + inside1 + inside1 + inside1;
		vgIP.generatePresent("", "-,t,im,wt", "w,w,w,w", 0, 1, 1, 3);
		vgIP.generateFuture("a,ti,ti,ii,ti,ni,ti,ti,ii,ti","-,-,i,-,-,-,w,nh,w,nh", "w,w,w,w,w,w,w,w,w,w", 0, 1, 2, 3);
		vgIP.setSpelling(IRREGULAR_SPELLING);
		vgIP.generateFuture("ai,t,t,i,t,n,t,t,i,t", "-,-,i,-,-,-,w,nh,w,nh","w,w,w,w,w,w,w,w,w,w", 0, 1, 2, 3);
		vgIP.setSpelling(originalSpelling);
		vgIP.generateImperative("", "-,i,w,nh", "w,w,w,w", 0, 1, 2, 3);
	}

	private void inflectPattern23() throws Exception 
	{
		int index;
		setGenInflection();
		int len = root.length();
		//   יש להכפיל את הי' כאשר היא מופיעה בפ' הפועל למשל: יישב
		if (root.charAt(0) == 'i') 
		{
			vgIP.generateBareInfinitive("i", "", 0, len, len, len, "");
			vgIP.generateInfinitive("i", "", 0, len, len, len, "");
			vgIP.generatePast("", "ti,t,t,-,h,nw,tm,tn,w", "i,i,i,i,i,i,i,i,i",0, 1, 1, len);
			vgIP.generatePresent("mi,mi,mi,mi", "-,t,im,wt", "", 0, len, len,len);
			vgIP.generateFuture("ai,ti,ti,i,ti,ni,ti,ti,i,ti","-,-,i,-,-,-,w,nh,w,nh", "", 0, len, len, len);
			vgIP.generateImperative("i", "-,i,w,nh", "", 0, len, len, len);

			vgIP.setSpelling(IRREGULAR_SPELLING);
			vgIP.generateBareInfinitive("", "", 0, len, len, len, "");
			vgIP.generateInfinitive("", "", 0, len, len, len, "");
			vgIP.generatePast("", "ti,t,t,-,h,nw,tm,tn,w", "", 0, 1, 1, len);
			vgIP.generatePresent("m,m,m,m", "-,t,im,wt", "", 0, len, len, len);
			vgIP.generateFuture("a,t,t,i,t,n,t,t,i,t", "-,-,i,-,-,-,w,nh,w,nh","", 0, len, len, len);
			vgIP.generateImperative("", "-,i,w,nh", "", 0, len, len, len);
		} 
		else if (root.charAt(1) == 'w' || root.charAt(2) == 'w') 
		{
			if (len == 3) 
			{
				vgIP.generateBareInfinitive("", "w", 0, 1, 1, len, "");
				vgIP.generateInfinitive("", "w", 0, 1, 1, len, "");
			} 
			else 
			{
				vgIP.generateBareInfinitive("", "w", 0, 2, 2, len, "");
				vgIP.generateInfinitive("", "w", 0, 2, 2, len, "");
			}
			vgIP.setSpelling(IRREGULAR_SPELLING);
			vgIP.generateBareInfinitive("", "", 0, len, len, len, "");
			vgIP.generateInfinitive("", "", 0, len, len, len, "");
			vgIP.setSpelling(originalSpelling);
			if (len == 3) 
			{
				vgIP.generatePast("", "ti,t,t,-,h,nw,tm,tn,w","iw,iw,iw,iw,iw,iw,iw,iw,iw", 0, 1, 1, 3);
				vgIP.setSpelling(IRREGULAR_SPELLING);
				vgIP.generatePast("", "ti,t,t,-,h,nw,tm,tn,w","i,i,i,i,i,i,i,i,i", 0, 1, 1, 3);
				vgIP.setSpelling(originalSpelling);
			} 
			else 
			{
				vgIP.setSpelling(IRREGULAR_SPELLING);
				vgIP.generatePast("", "ti,t,t,-,h,nw,tm,tn,w","i,i,i,i,i,i,i,i,i", 0, 1, 1, len);
				String temp = root.charAt(0) + "i" + root.substring(1, 2) + "w"+ root.substring(2);
				String appendedTemp = temp + ",";
				String totAppendedTemp = appendedTemp + appendedTemp + appendedTemp + appendedTemp + appendedTemp + appendedTemp + appendedTemp + appendedTemp + temp;
				vgIP.generatePast("", "ti,t,t,-,h,nw,tm,tn,w", totAppendedTemp,len, len, len, len);
				vgIP.setSpelling(originalSpelling);
				vgIP.generatePast("", "ti,t,t,-,h,nw,tm,tn,w","w,w,w,w,w,w,w,w,w", 0, 2, 2, len);
			}
			if (len == 3)
				vgIP.generatePresent("m,m,m,m", "-,t,im,wt", "w,w,w,w", 0, 1,1, len);
			else
				vgIP.generatePresent("m,m,m,m", "-,t,im,wt", "w,w,w,w", 0, 2,2, len);
			vgIP.setSpelling(IRREGULAR_SPELLING);
			vgIP.generatePresent("m,m,m,m", "-,t,im,wt", "", 0, len, len, len);
			vgIP.setSpelling(originalSpelling);
			if (len == 3)
				vgIP.generateFuture("a,t,t,i,t,n,t,t,i,t","-,-,i,-,-,-,w,nh,w,nh", "w,w,w,w,w,w,w,w,w,w", 0, 1,1, len);
			else
				vgIP.generateFuture("a,t,t,i,t,n,t,t,i,t","-,-,i,-,-,-,w,nh,w,nh", "w,w,w,w,w,w,w,w,w,w", 0, 2,2, len);
			vgIP.setSpelling(IRREGULAR_SPELLING);
			vgIP.generateFuture("a,t,t,i,t,n,t,t,i,t", "-,-,i,-,-,-,w,nh,w,nh","", 0, len, len, len);
			vgIP.setSpelling(originalSpelling);
			if (len == 3)
				vgIP.generateImperative("", "-,i,w,nh", "w,w,w,w", 0, 1, 1,len);
			else
				vgIP.generateImperative("", "-,i,w,nh", "w,w,w,w", 0, 2, 2,len);
			vgIP.setSpelling(IRREGULAR_SPELLING);
			vgIP.generateImperative("", "-,i,w,nh", "", 0, len, len, len);

		} 
		else if (((index = root.indexOf("i")) != -1) && index != 0) 
		{
			vgIP.generateBareInfinitive("", "i", 0, index + 1, index + 1, len,"");
			vgIP.generateInfinitive("", "i", 0, index + 1, index + 1, len, "");
			if (root.charAt(len - 1) == 'n')
				vgIP.generatePast("", "ti,t,t,-,h,w,tm,tn,w","i,i,i,i,i,i,i,i,i", 0, index + 1, index + 1, len);
			else
				vgIP.generatePast("", "ti,t,t,-,h,nw,tm,tn,w","i,i,i,i,i,i,i,i,i", 0, index + 1, index + 1, len);

			vgIP.generatePresent("m,m,m,m", "-,t,im,wt", "i,i,i,i", 0,index + 1, index + 1, len);
			if (root.charAt(len - 1) == 'n')
				vgIP.generateFuture("a,t,t,i,t,n,t,t,i,t","-,-,i,-,-,-,w,h,w,h", "i,i,i,i,i,i,i,i,i,i", 0,index + 1, index + 1, len);
			else
				vgIP.generateFuture("a,t,t,i,t,n,t,t,i,t","-,-,i,-,-,-,w,nh,w,nh", "i,i,i,i,i,i,i,i,i,i", 0,index + 1, index + 1, len);

			if (root.charAt(len - 1) == 'n')
				vgIP.generateImperative("", "-,i,w,h", "i,i,i,i", 0, index + 1,index + 1, len);
			else
				vgIP.generateImperative("", "-,i,w,nh", "i,i,i,i", 0,index + 1, index + 1, len);

			vgIP.setSpelling(IRREGULAR_SPELLING);
			vgIP.generateBareInfinitive("", "", 0, index + 1, index + 1, len,"");
			vgIP.generateInfinitive("", "", 0, index + 1, index + 1, len, "");

			if (root.charAt(len - 1) == 'n')
					vgIP.generatePast("", "ti,t,t,-,h,w,tm,tn,w", "", 0, index + 1,index + 1, len);
			else
				vgIP.generatePast("", "ti,t,t,-,h,nw,tm,tn,w", "", 0,index + 1, index + 1, len);
			vgIP.generatePresent("m,m,m,m", "-,t,im,wt", "", 0, index + 1,index + 1, len);
			if (root.charAt(len - 1) == 'n')
				vgIP.generateFuture("a,t,t,i,t,n,t,t,i,t","-,-,i,-,-,-,w,h,w,h", "", 0, 1, 1, len);
			else
				vgIP.generateFuture("a,t,t,i,t,n,t,t,i,t","-,-,i,-,-,-,w,nh,w,nh", "", 0, 1, 1, len);

			if (root.charAt(len - 1) == 'n')
				vgIP.generateImperative("", "-,i,w,h", "", 0, index + 1,index + 1, len);
			else
				vgIP.generateImperative("", "-,i,w,nh", "", 0, index + 1,index + 1, len);

			//מילים שמתחילות ב-ו כמו וידא
		} 
		else if (root.charAt(0) == 'w') 
		{
			vgIP.generateBareInfinitive("", "", 0, len, len, len, "");
			StringBuffer inflectedVerb = new StringBuffer().append("w").append(root.substring(0, len));
			vgIP.setInflectedVerb(inflectedVerb.toString());
			vgIP.generateInfinitive("", "", 0, 0, 0, 0, "");
			vgIP.generatePast("", "ti,t,t,-,h,nw,tm,tn,w", "i,i,i,i,i,i,i,i,i",0, 1, 1, len);
			vgIP.generatePresent("mw,mw,mw,mw", "-,t,im,wt", "", 0, len, len,len);
			vgIP.generateFuture("aw,tw,tw,iw,tw,nw,tw,tw,iw,tw","-,-,i,-,-,-,w,nh,w,nh", "", 0, len, len, len);
			vgIP.setSpelling(IRREGULAR_SPELLING);
			inflectedVerb = new StringBuffer().append(root.substring(0, len));
			vgIP.setInflectedVerb(inflectedVerb.toString());
			vgIP.generateInfinitive("", "", 0, 0, 0, 0, "");
			vgIP.generatePresent("m,m,m,m", "-,t,im,wt", "", 0, len, len, len);
			vgIP.generateFuture("a,t,t,i,t,n,t,t,i,t", "-,-,i,-,-,-,w,nh,w,nh","", 0, len, len, len);
			vgIP.setSpelling(originalSpelling);
			vgIP.generateImperative("", "-,i,w,nh", "", 0, len, len, len);
		} 
		else 
		{
			vgIP.generateBareInfinitive("", "", 0, len, len, len, "");
			vgIP.generateInfinitive("", "", 0, len, len, len, "");
//			if (len == 4) {
//				vgIP.generatePast("", "ti,t,t,-,h,nw,tm,tn,w", "", 0, len, len,
//						len);
//				vgIP.setSpelling(IRREGULAR_SPELLING);
//				vgIP.generatePast("", "ti,t,t,-,h,nw,tm,tn,w",
//						"i,i,i,i,i,i,i,i,i", 0, 1, 1, len);
//				vgIP.setSpelling(originalSpelling);
//			} else if (len == 3) {
//				vgIP.generatePast("", "ti,t,t,-,h,nw,tm,tn,w",
//						"i,i,i,i,i,i,i,i,i", 0, 1, 1, len);
//				vgIP.setSpelling(IRREGULAR_SPELLING);
//				vgIP.generatePast("", "ti,t,t,-,h,nw,tm,tn,w", "", 0, len, len,
//						len);
//				vgIP.setSpelling(originalSpelling);
			//}else{
				vgIP.generatePast("", "ti,t,t,-,h,nw,tm,tn,w", "", 0, len, len,len);
				vgIP.setSpelling(IRREGULAR_SPELLING);
				vgIP.generatePast("", "ti,t,t,-,h,nw,tm,tn,w","i,i,i,i,i,i,i,i,i", 0, 1, 1, len);
				vgIP.setSpelling(originalSpelling);
			//}
				
			vgIP.generatePresent("m,m,m,m", "-,t,im,wt", "", 0, len, len, len);

			vgIP.generateFuture("a,t,t,i,t,n,t,t,i,t", "-,-,i,-,-,-,w,nh,w,nh","", 0, len, len, len);
			vgIP.generateImperative("", "-,i,w,nh", "", 0, len, len, len);
			//וויתר-ויתר ווידא-וידא
		}
	}

	private void inflectPattern25() throws Exception 
	{
		setGenInflection();
		if (root.charAt(1) == 'w') 
		{
			vgIP.generateBareInfinitive("", "w", 0, 2, 3, 3, "t");
			vgIP.generateInfinitive("", "w", 0, 2, 3, 3, "t");
			vgIP.generatePast("", "ti,t,t,h,th,nw,tm,tn,w","iwwi,iwwi,iwwi,iww,iww,iwwi,iwwi,iwwi,iw", 0, 1, 3, 3);
			vgIP.setSpelling(IRREGULAR_SPELLING);
			vgIP.generatePast("", "ti,t,t,h,th,nw,tm,tn,w","iwi,iwi,iwi,iw,iw,iwi,iwi,iwi,iw", 0, 1, 3, 3);
			vgIP.setSpelling(originalSpelling);
			vgIP.generateFuture("a,t,t,i,t,n,t,t,i,t","h,h,i,h,h,h,w,inh,w,inh", "w,w,w,w,w,w,-,w,-,w", 0, 1, 1,2);
			vgIP.setSpelling(IRREGULAR_SPELLING);
			vgIP.generateFuture("a,t,t,i,t,n,t,t,i,t","h,h,i,h,h,h,w,inh,w,inh", "", 0, 1, 1, 2);
			vgIP.setSpelling(originalSpelling);
			vgIP.generatePresent("m,m,m,m", "h,h,im,wt", "w,w,w,-", 0, 2, 2, 2);
			//vgIP.setScript("colloquial");
			//vgIP.generatePresent("m,m,m,m", "h,h,im,wt", "", 0, 2, 2, 2);
			//vgIP.setRegister("standard");
			vgIP.generateImperative("", "h,i,w,inh", "w,w,-,w", 0, 2, 3, 3);
			//vgIP.setScript("colloquial");
			//vgIP.generateImperative("", "h,i,w,inh", "", 0, 2, 3, 3);
			//vgIP.setRegister("standard");
		} 
		else if (root.charAt(0) == 'i' && root.charAt(2) == 'h') 
		{
			vgIP.generateBareInfinitive("i", "w", 0, 2, 3, 3, "t");
			vgIP.generateInfinitive("i", "w", 0, 2, 3, 3, "t");
			String inside1 = "i" + root.charAt(1) + "i,";
			String inside2 = inside1 + inside1 + inside1 + "i" + root.charAt(1)
					+ "," + "i" + root.charAt(1) + "," + inside1 + inside1
					+ inside1 + ",i" + root.charAt(1) + ",i" + root.charAt(1);
			vgIP.generatePast("", "ti,t,t,h,th,nw,tm,tn,w", inside2, 0, 1,3, 3);
			vgIP.generatePresent("mi,mi,mi,mi", "h,h,im,wt", "", 0, 2, 2, 2);
			vgIP.generateFuture("ai,ti,ti,i,ti,ni,ti,ti,i,ti","h,h,i,h,h,h,w,inh,w,inh", "", 0, 0, 0, 2);
			vgIP.generateImperative("i", "h,i,w,inh", "", 0, 2, 3, 3);

			vgIP.setSpelling(IRREGULAR_SPELLING);
			vgIP.generateBareInfinitive("", "w", 0, 2, 3, 3, "t");
			vgIP.generateInfinitive("", "w", 0, 2, 3, 3, "t");
			inside1 = root.charAt(1) + "i,";
			inside2 = inside1 + inside1 + inside1 + root.charAt(1) + ","
					+ root.charAt(1) + "," + inside1 + inside1 + inside1 + ","
					+ root.charAt(1) + "," + root.charAt(1);
			vgIP.generatePast("", "ti,t,t,h,th,nw,tm,tn,w", inside2, 0, 1,3, 3);

			vgIP.generatePresent("m,m,m,m", "h,h,im,wt", "", 0, 2, 2, 2);
			vgIP.generateFuture("a,t,t,i,t,n,t,t,i,t","h,h,i,h,h,h,w,inh,w,inh", "", 0, 0, 0, 2);
			vgIP.generateImperative("", "h,i,w,inh", "", 0, 2, 3, 3);
		} 
		else 
		{
			vgIP.generateBareInfinitive("", "w", 0, 2, 3, 3, "t");
			vgIP.generateInfinitive("", "w", 0, 2, 3, 3, "t");
			String inside1 = "i" + root.charAt(1) + "i,";
			String inside2 = inside1 + inside1 + inside1 + "i" + root.charAt(1)
					+ "," + "i" + root.charAt(1) + "," + inside1 + inside1
					+ inside1 + ",i" + root.charAt(1) + ",i" + root.charAt(1);
			vgIP.generatePast("", "ti,t,t,h,th,nw,tm,tn,w", inside2, 0, 1,3, 3);
			vgIP.generateFuture("a,t,t,i,t,n,t,t,i,t","h,h,i,h,h,h,w,inh,w,inh", "", 0, 0, 0, 2);
			vgIP.generatePresent("m,m,m,m", "h,h,im,wt", "", 0, 2, 2, 2);
			vgIP.generateImperative("", "h,i,w,inh", "", 0, 2, 3, 3);
		}
	}

	private void inflectPattern26() throws Exception 
	{
		setGenInflection();
		vgIP.generateBareInfinitive("", "w", 0, 2, 3, 3, "t");
		vgIP.generateInfinitive("", "w", 0, 2, 3, 3, "t");
		vgIP.generatePast("", "ti,t,t,h,th,nw,tm,tn,w", "i,i,i,-,-,i,i,i,-", 0,2, 3, 3);
		vgIP.generatePresent("m,m,m,m", "h,h,im,wt", "", 0, 2, 2, 2);
		vgIP.generateFuture("a,t,t,i,t,n,t,t,i,t", "h,h,i,h,h,h,w,inh,w,inh","-,-,-,-,-,-,-,-,-,-", 0, 2, 3, 3);
		vgIP.generateImperative("", "h,i,w,inh", "", 0, 2, 3, 3);
	}

	private void inflectPattern27() throws Exception 
	{
		setGenInflection();
		int len = root.length();
		if (root.charAt(1) == 'i' && len == 3) 
		{
			vgIP.generateBareInfinitive("", "i", 0, 1, 1, len, "");
			vgIP.generateInfinitive("", "i", 0, 1, 1, len, "");
			vgIP.generatePast("", "ti,t,t,-,h,w,tm,tn,w", "i,i,i,i,i,i,i,i,i",0, 1, 1, len);
			vgIP.generatePresent("m,m,m,m", "-,t,im,wt", "i,i,i,i", 0, 1, 1,len);
			vgIP.generateFuture("a,t,t,i,t,n,t,t,i,t", "-,-,i,-,-,-,w,h,w,h","i,i,i,i,i,i,i,i,i,i", 0, 1, 1, len);
			vgIP.generateImperative("", "-,i,w,h", "i,i,i,i", 0, 1, 1, len);
		} 
		else 
		{
			vgIP.generateBareInfinitive("", "", 0, len, len, len, "");
			vgIP.generateInfinitive("", "", 0, len, len, len, "");
			vgIP.setSpelling(IRREGULAR_SPELLING);
			vgIP.generatePast("", "ti,t,t,-,h,w,tm,tn,w", "i,i,i,i,i,i,i,i,i",0, 1, 1, len);
			vgIP.setSpelling(originalSpelling);
			vgIP.generatePast("", "ti,t,t,-,h,w,tm,tn,w", "", 0, len, len, len);
			vgIP.generatePresent("m,m,m,m", "-,t,im,wt", "", 0, len, len, len);
			vgIP.generateFuture("a,t,t,i,t,n,t,t,i,t", "-,-,i,-,-,-,w,h,w,h","", 0, len, len, len);
			vgIP.generateImperative("", "-,i,w,h", "", 0, len, len, len);
		}
	}

	//הפועל מיאן
	private void inflectPattern28() throws Exception 
	{
		setGenInflection();
		vgIP.generateBareInfinitive("", "", 0, 3, 3, 3, "");
		vgIP.generateInfinitive("", "", 0, 3, 3, 3, "");
		vgIP.generatePast("", "ti,t,t,-,h,w,tm,tn,w", "i,i,i,i,i,i,i,i,i", 0,1, 1, 3);
		vgIP.setSpelling(IRREGULAR_SPELLING);
		vgIP.generatePast("", "ti,t,t,-,h,w,tm,tn,w", "", 0, 3, 3, 3);
		vgIP.setSpelling(originalSpelling);
		vgIP.generatePresent("m,m,m,m", "-,t,im,wt", "", 0, 3, 3, 3);
		vgIP.generateFuture("a,t,t,i,t,n,t,t,i,t", "-,-,i,-,-,-,w,h,w,h", "",0, 3, 3, 3);
		vgIP.generateImperative("", "-,i,w,h", "", 0, 3, 3, 3);
	}

	private void inflectPattern29() throws Exception 
	{
		setGenInflection();
		if (root.charAt(1) == 'i' && root.length() == 3) 
		{
			vgIP.generateBareInfinitive("", "i", 0, 1, 1, 3, "");
			vgIP.generateInfinitive("", "i", 0, 1, 1, 3, "");
			vgIP.generatePast("", "i,-,-,-,h,nw,m,n,w", "i,i,i,i,i,i,i,i,i", 0,1, 1, 3);
			vgIP.generatePresent("m,m,m,m", "-,t,im,wt", "i,i,i,i", 0, 1, 1, 3);
			vgIP.generateFuture("a,t,t,i,t,n,t,t,i,t", "-,-,i,-,-,-,w,nh,w,nh","i,i,i,i,i,i,i,i,i,i", 0, 1, 1, 3);
			vgIP.generateImperative("", "-,i,w,nh", "i,i,i,i", 0, 1, 1, 3);
		} 
		else 
		{
			vgIP.generateBareInfinitive("", "", 0, 3, 3, 3, "");
			vgIP.generateInfinitive("", "", 0, 3, 3, 3, "");
			vgIP.generatePast("", "i,-,-,-,h,nw,m,n,w", "i,i,i,i,i,i,i,i,i", 0,1, 1, 3);
			vgIP.generatePresent("m,m,m,m", "-,t,im,wt", "", 0, 3, 3, 3);
			vgIP.generateFuture("a,t,t,i,t,n,t,t,i,t", "-,-,i,-,-,-,w,nh,w,nh","", 0, 3, 3, 3);
			vgIP.generateImperative("", "-,i,w,nh", "", 0, 3, 3, 3);
		}
	}

	//שרת
	private void inflectPattern30() throws Exception 
	{
		setGenInflection();
		vgIP.generateBareInfinitive("", "", 0, 3, 3, 3, "");
		vgIP.generateInfinitive("", "", 0, 3, 3, 3, "");
		vgIP.generatePast("", "i,-,-,-,h,nw,m,n,w", "i,i,i,i,i,i,i,i,i", 0, 1,1, 3);
		vgIP.setSpelling(IRREGULAR_SPELLING);
		vgIP.generatePast("", "i,-,-,-,h,nw,m,n,w", "", 0, 1, 1, 3);
		vgIP.setSpelling(originalSpelling);
		vgIP.generatePresent("m,m,m,m", "-,t,im,wt", "", 0, 3, 3, 3);
		vgIP.generateFuture("a,t,t,i,t,n,t,t,i,t", "-,-,i,-,-,-,w,nh,w,nh", "",0, 3, 3, 3);
		vgIP.generateImperative("", "-,i,w,nh", "", 0, 3, 3, 3);
	}

	//	 לקומם - קום
	private void inflectPattern31() throws Exception 
	{
		setGenInflection();
		//כפולים
		if (root.charAt(1) == root.charAt(2)) 
		{
			vgIP.generateBareInfinitive("", "w", 0, 1, 2, 3, String.valueOf(root.charAt(2)));
			vgIP.generateInfinitive("", "w", 0, 1, 2, 3, String.valueOf(root.charAt(2)));
		} 
		else 
		{
			if (root.charAt(1) == 'i') 
			{
				root = root.charAt(0) + "w" + root.substring(2);
			}
			String inside = root + String.valueOf(root.charAt(2));
			vgIP.generateBareInfinitive("", "", 0, 0, 0, 0, inside);
			vgIP.generateInfinitive("", "", 0, 0, 0, 0, inside);
		}
		String suffix1 = root.substring(2);
		String suffix2 = "";
		if (root.charAt(2) == 't') 
		{
			suffix2 = suffix1 + "i," + suffix1 + "," + suffix1 + "," + suffix1
					+ "," + suffix1 + "h," + suffix1 + "nw," + suffix1 + "m,"
					+ suffix1 + "n," + suffix1 + "w";
			vgIP.generatePast("", suffix2, "w,w,w,w,w,w,w,w,w", 0, 1, 2, 3);
		} 
		else if (root.charAt(2) == 'n') 
		{
			suffix2 = suffix1 + "ti," + suffix1 + "t," + suffix1 + "t,"
					+ suffix1 + "," + suffix1 + "h," + suffix1 + "w," + suffix1
					+ "tm," + suffix1 + "tn," + suffix1 + "w";
			vgIP.generatePast("", suffix2, "w,w,w,w,w,w,w,w,w", 0, 1, 2, 3);
		} 
		else 
		{
			suffix2 = suffix1 + "ti," + suffix1 + "t," + suffix1 + "t,"
					+ suffix1 + "," + suffix1 + "h," + suffix1 + "nw,"
					+ suffix1 + "tm," + suffix1 + "tn," + suffix1 + "w";
			vgIP.generatePast("", suffix2, "w,w,w,w,w,w,w,w,w", 0, 1, 2, 3);
		}
		//כפולים
		if (root.charAt(1) == root.charAt(2)) 
		{
			if (root.charAt(2) == 'n') 
			{
				vgIP.generatePresent("m,m,m,m", "-,t,im,wt", "w,w,w,w", 0, 1,1, 3);
				vgIP.generateFuture("a,t,t,i,t,n,t,t,i,t","-,-,i,-,-,-,w,h,w,h", "w,w,w,w,w,w,w,w,w,w", 0, 1, 1,3);
				vgIP.generateImperative("", "-,i,w,h", "w,w,w,w", 0, 1, 1, 3);
			} 
			else 
			{
				vgIP.generatePresent("m,m,m,m", "-,t,im,wt", "w,w,w,w", 0, 1,1, 3);
				vgIP.generateFuture("a,t,t,i,t,n,t,t,i,t","-,-,i,-,-,-,w,nh,w,nh", "w,w,w,w,w,w,w,w,w,w", 0, 1,1, 3);
				vgIP.generateImperative("", "-,i,w,nh", "w,w,w,w", 0, 1, 1, 3);
			}
			//לא כפולים
		} 
		else 
		{
			if (root.charAt(2) == 't') 
			{
				vgIP.generatePresent("m,m,m,m", "t,tt,tim,twt", "w,w,w,w", 0,1, 2, 3);
				vgIP.generateFuture("a,t,t,i,t,n,t,t,i,t","t,t,ti,t,t,t,tw,tnh,tw,tnh", "w,w,w,w,w,w,w,w,w,w", 0,1, 2, 3);
				vgIP.generateImperative("", "t,ti,tw,tnh", "w,w,w,w", 0, 1, 2,3);
			} 
			else if (root.charAt(2) == 'n') 
			{
				vgIP.generatePresent("m,m,m,m", "n,nt,nim,nwt", "w,w,w,w", 0,1, 2, 3);
				vgIP.generateFuture("a,t,t,i,t,n,t,t,i,t","n,n,ni,n,n,n,nw,nh,nw,nh", "w,w,w,w,w,w,w,w,w,w", 0,1, 2, 3);
				vgIP.generateImperative("", "n,ni,nw,nh", "w,w,w,w", 0, 1,2, 3);
			} 
			else 
			{
				// עבור המקרה של שורר - שהשורש שלו הוא שיר
				if (root.charAt(1) == 'i') 
				{
					root = root.charAt(0) + "w" + root.substring(2);
				}

				String inside = root + root.charAt(2) + ",";
				String inside1 = inside + inside + inside + root+ root.charAt(2);
				vgIP.generatePresent("m,m,m,m", "-,t,im,wt", inside1, 0, 0, 0,0);

				String inside2 = inside + inside + inside + inside + inside+ inside + inside + inside + inside + inside;
				vgIP.generateFuture("a,t,t,i,t,n,t,t,i,t","-,-,i,-,-,-,w,nh,w,nh", inside2, 0, 0, 0, 0);

				vgIP.generateImperative("", "-,i,w,nh", inside1, 0, 0, 0, 0);
			}
		}
	}

	//	 לקים - קום
	private void inflectPattern32() throws Exception 
	{
		setGenInflection();
		vgIP.generateBareInfinitive("", "ii", 0, 1, 2, 3, "");
		vgIP.generateInfinitive("", "ii", 0, 1, 2, 3, "");
		if (root.charAt(2) == 't') 
		{
			vgIP.generatePast("", "i,-,-,-,h,nw,m,n,w","ii,ii,ii,ii,ii,ii,ii,ii,ii", 0, 1, 2, 3);
		}
		vgIP.generatePast("", "ti,t,t,-,h,nw,tm,tn,w","ii,ii,ii,ii,ii,ii,ii,ii,ii", 0, 1, 2, 3);
		vgIP.generatePresent("m,m,m,m", "-,t,im,wt", "ii,ii,ii,ii", 0, 1, 2, 3);
		vgIP.generateFuture("a,t,t,i,t,n,t,t,i,t", "-,-,i,-,-,-,w,nh,w,nh","ii,ii,ii,ii,ii,ii,ii,ii,ii,ii", 0, 1, 2, 3);
		vgIP.generateImperative("", "-,i,w,nh", "ii,ii,ii,ii", 0, 1, 2, 3);
	}

	//	לכוון - כון
	private void inflectPattern33() throws Exception 
	{
		setGenInflection();
		vgIP.generateBareInfinitive("", "w", 0, 2, 2, 3, "");
		vgIP.generateInfinitive("", "w", 0, 2, 2, 3, "");
		if (root.charAt(2) == 'n')
			vgIP.generatePast("", "ti,t,t,-,h,w,tm,tn,w","iw,iw,iw,iw,iw,iw,iw,iw,iw", 0, 1, 1, 3);
		else
			vgIP.generatePast("", "ti,t,t,-,h,nw,tm,tn,w","iw,iw,iw,iw,iw,iw,iw,iw,iw", 0, 1, 1, 3);
		vgIP.generatePresent("m,m,m,m", "-,t,im,wt", "w,w,w,w", 0, 1, 1, 3);

		if (root.charAt(2) == 'n') 
		{
			vgIP.generateFuture("a,t,t,i,t,n,t,t,i,t", "-,-,i,-,-,-,w,h,w,h","w,w,w,w,w,w,w,w,w,w", 0, 2, 2, 3);
			vgIP.generateImperative("", "-,i,w,h", "w,w,w,w", 0, 2, 2, 3);
		} 
		else 
		{
			vgIP.generateFuture("a,t,t,i,t,n,t,t,i,t", "-,-,i,-,-,-,w,nh,w,nh","w,w,w,w,w,w,w,w,w,w", 0, 2, 2, 3);
			vgIP.generateImperative("", "-,i,w,nh", "w,w,w,w", 0, 2, 2, 3);
		}
	}

	//לצחצח-צחח
	private void inflectPattern34() throws Exception 
	{
		setGenInflection();
		vgIP.generateBareInfinitive("", "", 0, 2, 0, 2, "");
		vgIP.generateInfinitive("", "", 0, 2, 0, 2, "");
		vgIP.generatePast("", "ti,t,t,-,h,nw,tm,tn,w", "", 0, 2, 0, 2);
		vgIP.generatePresent("m,m,m,m", "-,t,im,wt", "", 0, 2, 0, 2);
		vgIP.generateFuture("a,t,t,i,t,n,t,t,i,t", "-,-,i,-,-,-,w,nh,w,nh", "",0, 2, 0, 2);
		vgIP.generateImperative("", "-,i,w,nh", "", 0, 2, 0, 2);
	}

	//לענין-ענין
	private void inflectPattern35() throws Exception 
	{
		setGenInflection();
		if (root.charAt(1) == 'i') 
		{
			vgIP.generateBareInfinitive("", "i", 0, 1, 1, 4, "");
			vgIP.generateInfinitive("", "i", 0, 1, 1, 4, "");
			vgIP.generatePast("", "ti,t,t,-,h,w,tm,tn,w", "i,i,i,i,i,i,i,i,i",0, 1, 1, 4);
			vgIP.generatePresent("m,m,m,m", "-,t,im,wt", "i,i,i,i", 0, 1, 1, 4);
			vgIP.generateFuture("a,t,t,i,t,n,t,t,i,t", "-,-,i,-,-,-,w,h,w,h","", 0, 2, 1, 4);
			vgIP.generateImperative("", "-,i,w,h", "i,i,i,i", 0, 1, 1, 4);
		} 
		else if (root.charAt(2) == 'i') 
		{
			vgIP.generateBareInfinitive("", "i", 0, 2, 2, 4, "");
			vgIP.generateInfinitive("", "i", 0, 2, 2, 4, "");
			vgIP.generatePast("", "ti,t,t,-,h,w,tm,tn,w", "i,i,i,i,i,i,i,i,i",0, 2, 2, 4);
			vgIP.generatePresent("m,m,m,m", "-,t,im,wt", "i,i,i,i", 0, 2, 2, 4);
			vgIP.generateFuture("a,t,t,i,t,n,t,t,i,t", "-,-,i,-,-,-,w,h,w,h","i,i,i,i,i,i,i,i,i,i", 0, 2, 2, 4);
			vgIP.generateImperative("", "-,i,w,h", "i,i,i,i", 0, 2, 2, 4);
			//			vgIP.setScript("colloquial");
			//			vgIP.generateBareInfinitive("", "", 0, 2, 2, 4, "");
			//			vgIP.generateInfinitive("", "", 0, 2, 2, 4, "");
			//			vgIP.generatePast("", "ti,t,t,-,h,w,tm,tn,w", "", 0, 2, 2, 4);
			//			vgIP.generatePresent("m,m,m,m", "-,t,im,wt", "", 0, 2, 2, 4);
			//			vgIP.generateFuture("a,t,t,i,t,n,t,t,i,t", "-,-,i,-,-,-,w,h,w,h",
			//					"", 0, 2, 2, 4);
			//			vgIP.generateImperative("", "-,i,w,h", "", 0, 2, 2, 4);
		} 
		else 
		{
			vgIP.generateBareInfinitive("", "", 0, 1, 1, 4, "");
			vgIP.generateInfinitive("", "", 0, 1, 1, 4, "");
			vgIP.generatePast("", "ti,t,t,-,h,w,tm,tn,w", "", 0, 1, 1, 4);
			vgIP.setSpelling(IRREGULAR_SPELLING);
			vgIP.generatePast("", "ti,t,t,-,h,w,tm,tn,w", "i,i,i,i,i,i,i,i,i",0, 1, 1, 4);
			vgIP.setSpelling(originalSpelling);
			vgIP.generatePresent("m,m,m,m", "-,t,im,wt", "", 0, 1, 1, 4);
			vgIP.generateFuture("a,t,t,i,t,n,t,t,i,t", "-,-,i,-,-,-,w,h,w,h","", 0, 4, 4, 4);
			vgIP.generateImperative("", "-,i,w,h", "", 0, 1, 1, 4);
		}
	}

	//לכבד-כבד
	//שוחרר-שחרר
	private void inflectPattern36() throws Exception 
	{
		setGenInflection();
		int len = root.length();
		if (len<3){
			int i=0;
		}
		if ((root.charAt(len - 1) == 'h') || (root.charAt(len - 1) == 'i')) 
		{
			//vgIP.generatePresent("-,m,-,-", "-,h,-,-", "-,w,-,-", 0, 1, 1,
			//		len - 1);

			if (root.endsWith("wh")) 
			{
				vgIP.generatePast("", "iti,it,it,h,th,inw,itm,itn,-","w,w,w,w,w,w,w,w,w", 0, 1, 1, len - 1);
				vgIP.generatePresent("m,m,m,m", "h,it,im,wt", "w,w,w,-", 0, 1,1, len - 1);
				vgIP.generateFuture("a,t,t,i,t,n,t,t,i,t","h,h,i,h,h,h,-,inh,-,inh", "w,w,w,w,w,w,w,w,w,-", 0, 1,1, len - 1);
			} 
			else 
			{
				vgIP.generatePast("", "iti,it,it,h,th,inw,itm,itn,w","w,w,w,w,w,w,w,w,w", 0, 1, 1, len - 1);
				vgIP.generatePresent("m,m,m,m", "h,it,im,wt", "w,w,w,w", 0, 1,1, len - 1);
				vgIP.generateFuture("a,t,t,i,t,n,t,t,i,t","h,h,i,h,h,h,w,inh,w,inh", "w,w,w,w,w,w,w,w,w,w", 0, 1,1, len - 1);
			}
		} 
		else if (root.charAt(len - 1) == 'n' && root.charAt(len - 2) == 'i') 
		{
			vgIP.generatePast("", "ti,t,t,-,h,w,tm,tn,w", "w,w,w,w,w,w,w,w,w",0, 1, 1, len);
			vgIP.generatePresent("m,m,m,m", "-,t,im,wt", "w,w,w,w", 0, 1, 1,len);
			vgIP.generateFuture("a,t,t,i,t,n,t,t,i,t", "-,-,i,-,-,-,w,h,w,h","w,w,w,w,w,w,w,w,w,w", 0, 1, 1, len);
			vgIP.setSpelling(IRREGULAR_SPELLING);
			vgIP.generatePast("", "ti,t,t,-,h,w,tm,tn,w","wi,wi,wi,wi,wi,wi,wi,wi,wi", 0, 1, 1, len);
			vgIP.generatePresent("m,m,m,m", "-,t,im,wt", "wi,wi,wi,wi", 0, 1,1, len);

			vgIP.generateFuture("a,t,t,i,t,n,t,t,i,t", "-,-,i,-,-,-,w,h,w,h","wi,wi,wi,wi,wi,wi,wi,wi,wi,wi", 0, 1, 1, len);

		} 
		else if (root.charAt(len - 1) == 'n') 
		{
			vgIP.generatePast("", "ti,t,t,-,h,w,tm,tn,w", "w,w,w,w,w,w,w,w,w",0, 1, 1, len);
			vgIP.generatePresent("m,m,m,m", "-,t,im,wt", "w,w,w,w", 0, 1, 1,len);

			vgIP.generateFuture("a,t,t,i,t,n,t,t,i,t", "-,-,i,-,-,-,w,h,w,h","w,w,w,w,w,w,w,w,w,w", 0, 1, 1, len);
		} 
		else if (root.charAt(len - 1) == 't') 
		{
			vgIP.generatePast("", "i,-,-,-,h,w,m,n,w", "w,w,w,w,w,w,w,w,w", 0,1, 1, len);
			vgIP.generatePresent("m,m,m,m", "-,t,im,wt", "w,w,w,w", 0, 1, 1,len);

			vgIP.generateFuture("a,t,t,i,t,n,t,t,i,t", "-,-,i,-,-,-,w,nh,w,nh","w,w,w,w,w,w,w,w,w,w", 0, 1, 1, len);
		} 
		else if (root.charAt(0) == 'h') 
		{
			vgIP.generatePast("", "ti,t,t,-,h,nw,tm,tn,w", "w,w,w,w,w,w,w,w,w",0, 1, 1, len);
			vgIP.generatePresent("m,m,m,m", "-,t,im,wt", "w,w,w,w", 0, 1, 1,len);
			vgIP.generateFuture("a,t,t,i,t,n,t,t,i,t", "-,-,i,-,-,-,w,nh,w,nh","w,w,w,w,w,w,w,w,w,w", 0, 1, 1, len);
		} 
		else if (root.charAt(1) == 'i') 
		{
			vgIP.setSpelling(IRREGULAR_SPELLING);
			vgIP.generatePast("", "ti,t,t,-,h,nw,tm,tn,w", "wi,wi,wi,wi,wi,wi,wi,wi,wi",0, 1, 1, len);
			vgIP.generatePresent("m,m,m,m", "-,t,im,wt", "wi,wi,wi,wi", 0, 1, 1,len);
			vgIP.generateFuture("a,t,t,i,t,n,t,t,i,t", "-,-,i,-,-,-,w,nh,w,nh","wi,wi,wi,wi,wi,wi,wi,wi,wi,wi", 0, 1, 1, len);
			vgIP.setSpelling(originalSpelling);
			vgIP.generatePast("", "ti,t,t,-,h,nw,tm,tn,w", "w,w,w,w,w,w,w,w,w",0, 1, 1, len);
			vgIP.generatePresent("m,m,m,m", "-,t,im,wt", "w,w,w,w", 0, 1, 1,len);
			vgIP.generateFuture("a,t,t,i,t,n,t,t,i,t", "-,-,i,-,-,-,w,nh,w,nh","w,w,w,w,w,w,w,w,w,w", 0, 1, 1, len);

		} 
		else 
		{
			vgIP.generatePast("", "ti,t,t,-,h,nw,tm,tn,w", "w,w,w,w,w,w,w,w,w",0, 1, 1, len);
			vgIP.generatePresent("m,m,m,m", "-,t,im,wt", "w,w,w,w", 0, 1, 1,len);
			vgIP.generateFuture("a,t,t,i,t,n,t,t,i,t", "-,-,i,-,-,-,w,nh,w,nh","w,w,w,w,w,w,w,w,w,w", 0, 1, 1, len);
		}
	}

	private void inflectPattern37() throws Exception 
	{
		setGenInflection();
		if (root.charAt(2) == 't') 
		{
			vgIP.generatePast("", "i,-,-,-,h,nw,m,n,w", "", 0, 3, 2, 3);
			vgIP.generatePresent("m,m,m,m", "t,tt,tim,twt", "w,w,w,w", 0, 1, 2,3);
			vgIP.generateFuture("a,t,t,i,t,n,t,t,i,t","t,t,ti,t,t,t,tw,tnh,tw,tnh", "w,w,w,w,w,w,w,w,w,w", 0, 1,2, 3);
		} 
		else 
		{
			vgIP.generatePast("", "ti,t,t,-,h,nw,tm,tn,w", "", 0, 3, 2, 3);
			vgIP.generatePresent("m,m,m,m", "-,t,im,wt", "w,w,w,w", 0, 1, 1, 3);
			vgIP.generateFuture("a,t,t,i,t,n,t,t,i,t", "-,-,i,-,-,-,w,h,w,h","w,w,w,w,w,w,w,w,w,w", 0, 1, 1, 3);
		}
	}

	//קויימתי-קום
	//id = 8364
	private void inflectPattern38() throws Exception 
	{
		setGenInflection();
		vgIP.generatePast("", "ti,t,t,-,h,nw,tm,tn,w","wii,wii,wii,wii,wii,wii,wii,wii,wii", 0, 1, 2, 3);
		vgIP.generatePresent("m,m,m,m", "-,t,im,wt", "wii,wii,wii,wii", 0, 1,2, 3);

		vgIP.generateFuture("a,t,t,i,t,n,t,t,i,t", "-,-,i,-,-,-,w,nh,w,nh","wii,wii,wii,wii,wii,wii,wii,wii,wii,wii", 0, 1, 2, 3);

		vgIP.generatePast("", "ti,t,t,-,h,nw,tm,tn,w","wi,wi,wi,wi,wi,wi,wi,wi,wi", 0, 1, 2, 3);
		vgIP.generatePresent("m,m,m,m", "-,t,im,wt", "wi,wi,wi,wi", 0, 1, 2, 3);
		vgIP.generateFuture("a,t,t,i,t,n,t,t,i,t", "-,-,i,-,-,-,w,nh,w,nh","wi,wi,wi,wi,wi,wi,wi,wi,wi,wi", 0, 1, 2, 3);
	}

	//גולגלתי-גלל
	//id = 9270
	private void inflectPattern39() throws Exception 
	{
		setGenInflection();
		int len = root.length();
		//		if (root.charAt(len-1) == root.charAt(len-2)){
		//			
		//		}
		if (root.charAt(len - 1) == 't') 
		{
			vgIP.generatePast("", "i,-,-,-,h,nw,m,n,w", "w,w,w,w,w,w,w,w,w", 0,1, 1, len);
			vgIP.generatePresent("m,m,m,m", "-,t,im,wt", "w,w,w,w", 0, 1, 1,len);
			vgIP.generateFuture("a,t,t,i,t,n,t,t,i,t", "-,-,i,-,-,-,w,nh,w,nh","w,w,w,w,w,w,w,w,w,w", 0, 1, 1, len);
		} 
		else if (root.charAt(len - 1) == 'n') 
		{
			if (root.charAt(len - 2) == 'i') 
			{
				vgIP.generatePast("", "inti,int,int,in,inh,inw,intm,intn,inw","w,w,w,w,w,w,w,w,w", 0, 1, 1, len - 1);
				vgIP.generatePresent("m,m,m,m", "in,int,inim,inwt", "w,w,w,w",0, 1, 1, len - 1);
				vgIP.generateFuture("a,t,t,i,t,n,t,t,i,t","in,in,ini,in,in,in,inw,inh,inw,inh","w,w,w,w,w,w,w,w,w,w", 0, 1, 1, len - 1);
			} 
			else 
			{
				vgIP.generatePast("", "ti,t,t,-,h,w,tm,tn,w","w,w,w,w,w,w,w,w,w", 0, 1, 1, len);
				vgIP.generatePresent("m,m,m,m", "-,t,im,wt", "w,w,w,w", 0, 1,1, len);

				vgIP.generateFuture("a,t,t,i,t,n,t,t,i,t","-,-,i,-,-,-,w,h,w,h", "w,w,w,w,w,w,w,w,w,w", 0, 1, 1,len);
			}
		} 
		else 
		{
			vgIP.generatePast("", "ti,t,t,-,h,nw,tm,tn,w", "w,w,w,w,w,w,w,w,w",0, 1, 1, len);
			vgIP.generatePresent("m,m,m,m", "-,t,im,wt", "w,w,w,w", 0, 1, 1,len);
			vgIP.generateFuture("a,t,t,i,t,n,t,t,i,t", "-,-,i,-,-,-,w,nh,w,nh","w,w,w,w,w,w,w,w,w,w", 0, 1, 1, len);
		}
	}

	//עוניינתי-ענה
	//id = 18339
	//ברב מילים מופיעים כשורשים מרובעים - יצא מכתב לשירה לנוון
	//	private void inflectPattern41() {
	//		String newRoot = root.substring(0,2) + "i" ;
	//		VerbGenIP vgIP = new VerbGenIP(newRoot);
	//		String suffix1 = newRoot.substring(1,2)+ "ii" + newRoot.substring(1,2);
	//		String suffix2 = suffix1 + "ti," + suffix1 + "t,"+ suffix1+ "t,"+
	// suffix1+ "," + suffix1+"h,"+ suffix1+ "w,"+ suffix1 + "tm,"+ suffix1 +
	// "tn,"+ newRoot.substring(1) + "nw," + newRoot.substring(1) + "nw";
	//		vgIP.generatePast("",suffix2, "w,w,w,w,w,w,w,w,w,w" ,0,1,3,3);
	//		
	//		suffix1 = newRoot.substring(1,2)+ "i" + newRoot.substring(1,2);
	//		suffix2 = suffix1 + "ti," + suffix1 + "t,"+ suffix1+ "t,"+ suffix1+ "," +
	// suffix1+"h,"+ suffix1+ "w,"+ suffix1 + "tm,"+ suffix1 + "tn,"+
	// newRoot.substring(1) + "nw," + newRoot.substring(1) + "nw";
	//		vgIP.generatePast("",suffix2, "w,w,w,w,w,w,w,w,w,w" ,0,1,3,3);
	//		
	//		vgIP.generatePresent("m,m,m,m","n,nt,nim,nwt","w,w,w,w", 0, 1,1,3);
	//		vgIP.generatePresent("m,m,m,m","in,int,inim,inwt","w,w,w,w", 0, 1,1,3);
	//		vgIP.generateFuture("a,t,t,i,t,n,t,t,i,t","n,n,ni,n,n,n,nw,nh,nw,nh","w,w,w,w,w,w,w,w,w,w",0,1,1,3);
	//		vgIP.generateFuture("a,t,t,i,t,n,t,t,i,t","in,in,ini,in,in,in,inw,inh,inw,inh","w,w,w,w,w,w,w,w,w,w",0,1,1,3);
	//	}

	/////////////////////////////////////////////////////////////////////////
	///Hitpa'el
	/////////////////////////////////////////////////////////////////////////

	//התקשר-קשר
	//id = 11204
	private void inflectPattern42() throws Exception 
	{
		setGenInflection();
		if (root.indexOf("i") != -1)
			vgIP.setSpelling(IRREGULAR_SPELLING);
		int len = root.length();
		if (len<3){
			int i=0;
		}
		//הפועל התרוקן הוא יצא דופן - צריך להטות אותו כמקרה פרטי
		if (root.equals("rqn")) 
		{
			vgIP.generateBareInfinitive("ht", "w", 0, 1, 1, len, "");
			vgIP.generateInfinitive("ht", "", 0, 1, 1, len, "");
			vgIP.generatePast("ht,ht,ht,ht,ht,ht,ht,ht,ht","ti,t,t,-,h,w,tm,tn,w", "w,w,w,w,w,w,w,w,w", 0, 1, 1, len);
			vgIP.generatePresent("mt,mt,mt,mt", "-,t,im,wt", "w,w,w,w", 0, 1,1, len);
			vgIP.generateFuture("at,tt,tt,it,tt,nt,tt,tt,it,tt","-,-,i,-,-,-,w,h,w,h", "w,w,w,w,w,w,w,w,w,w", 0, 0, 1, len);
			vgIP.generateImperative("ht", "-,i,w,h", "w,w,w,w", 0, 1, 1, len);
		}
		//שורות 33-39
		//ז שורקת - ז
		else if (root.charAt(0) == 'z' && root.charAt(len - 1) == 'n') 
		{
			vgIP.generateBareInfinitive("hzd", "", 0, 0, 1, len, "");
			vgIP.generateInfinitive("hzd", "", 0, 0, 1, len, "");
			vgIP.generatePast("hzd,hzd,hzd,hzd,hzd,hzd,hzd,hzd,hzd","ti,t,t,-,h,w,tm,tn,w", "", 0, 0, 1, len);
			vgIP.generatePresent("mzd,mzd,mzd,mzd", "-,t,im,wt", "", 0, 0, 1,len);
			vgIP.generateFuture("azd,tzd,tzd,izd,tzd,nzd,tzd,tzd,izd,tzd","-,-,i,-,-,-,w,h,w,h", "", 0, 0, 1, len);
			vgIP.generateImperative("hzd", "-,i,w,h", "-,-,-,-", 0, 0, 1, len);

			//43
			//ז שורקת - ז + ל'נ
		} 
		else if (root.charAt(0) == 'z') 
		{
			vgIP.generateBareInfinitive("hzd", "", 0, 0, 1, len, "");
			vgIP.generateInfinitive("hzd", "", 0, 0, 1, len, "");
			vgIP.generatePast("hzd,hzd,hzd,hzd,hzd,hzd,hzd,hzd,hzd","ti,t,t,-,h,nw,tm,tn,w", "", 0, 0, 1, len);
			vgIP.generatePresent("mzd,mzd,mzd,mzd", "-,t,im,wt", "", 0, 0, 1,len);
			vgIP.generateFuture("azd,tzd,tzd,izd,tzd,nzd,tzd,tzd,izd,tzd","-,-,i,-,-,-,w,nh,w,nh", "", 0, 0, 1, len);
			vgIP.generateImperative("hzd", "-,i,w,nh", "-,-,-,-", 0, 0, 1, len);
		}

		else if (root.charAt(0) == 'c' && root.charAt(len - 1) == 'n') 
		{
			vgIP.generateBareInfinitive("hcv", "", 0, 0, 1, len, "");
			vgIP.generateInfinitive("hcv", "", 0, 0, 1, len, "");
			vgIP.generatePast("hcv,hcv,hcv,hcv,hcv,hcv,hcv,hcv,hcv","ti,t,t,-,h,w,tm,tn,w", "", 0, 0, 1, len);
			vgIP.generatePresent("mcv,mcv,mcv,mcv", "-,t,im,wt", "", 0, 0, 1,len);
			vgIP.generateFuture("acv,tcv,tcv,icv,tcv,ncv,tcv,tcv,icv,tcv","-,-,i,-,-,-,w,h,w,h", "", 0, 0, 1, len);
			vgIP.generateImperative("hcv", "-,i,w,h", "-,-,-,-", 0, 0, 1, len);
		} 
		else if (root.charAt(0) == 'c') 
		{
			vgIP.generateBareInfinitive("hcv", "", 0, 0, 1, len, "");
			vgIP.generateInfinitive("hcv", "", 0, 0, 1, len, "");
			vgIP.generatePast("hcv,hcv,hcv,hcv,hcv,hcv,hcv,hcv,hcv","ti,t,t,-,h,nw,tm,tn,w", "", 0, 0, 1, len);
			vgIP.generatePresent("mcv,mcv,mcv,mcv", "-,t,im,wt", "", 0, 0, 1,len);
			vgIP.generateFuture("acv,tcv,tcv,icv,tcv,ncv,tcv,tcv,icv,tcv","-,-,i,-,-,-,w,nh,w,nh", "", 0, 0, 1, len);
			vgIP.generateImperative("hcv", "-,i,w,nh", "-,-,-,-", 0, 0, 1, len);
		}
		//ס
		//38 ל'נ + ס שורקת
		else if (root.charAt(0) == 's' && root.charAt(len - 1) == 'n') 
		{
			vgIP.generateBareInfinitive("hst", "", 0, 0, 1, len, "");
			vgIP.generateInfinitive("hst", "", 0, 0, 1, len, "");
			vgIP.generatePast("hst,hst,hst,hst,hst,hst,hst,hst,hst","ti,t,t,-,h,w,tm,tn,w", "", 0, 0, 1, len);
			vgIP.generatePresent("mst,mst,mst,mst", "-,t,im,wt", "", 0, 0, 1,len);
			vgIP.generateFuture("ast,tst,tst,ist,tst,nst,tst,tst,ist,tst","-,-,i,-,-,-,w,h,w,h", "", 0, 0, 1, len);
			vgIP.generateImperative("hst", "-,i,w,h", "-,-,-,-", 0, 0, 1, len);
		} 
		else if (root.charAt(0) == 's') 
		{
			vgIP.generateBareInfinitive("hst", "", 0, 0, 1, len, "");
			vgIP.generateInfinitive("hst", "", 0, 0, 1, len, "");
			vgIP.generatePast("hst,hst,hst,hst,hst,hst,hst,hst,hst","ti,t,t,-,h,nw,tm,tn,w", "", 0, 0, 1, len);
			vgIP.generatePresent("mst,mst,mst,mst", "-,t,im,wt", "", 0, 0, 1,len);
			vgIP.generateFuture("ast,tst,tst,ist,tst,nst,tst,tst,ist,tst","-,-,i,-,-,-,w,nh,w,nh", "", 0, 0, 1, len);
			vgIP.generateImperative("hst", "-,i,w,nh", "-,-,-,-", 0, 0, 1, len);
		}
		//ש
		//39 ל'נ + ש שורקת
		else if (root.charAt(0) == 'e' && root.charAt(len - 1) == 'n') 
		{
			vgIP.generateBareInfinitive("het", "", 0, 0, 1, len, "");
			vgIP.generateInfinitive("het", "", 0, 0, 1, len, "");
			vgIP.generatePast("het,het,het,het,het,het,het,het,het","ti,t,t,-,h,w,tm,tn,w", "", 0, 0, 1, len);
			vgIP.generatePresent("met,met,met,met", "-,t,im,wt", "", 0, 0, 1,len);
			vgIP.generateFuture("aet,tet,tet,iet,tet,net,tet,tet,iet,tet","-,-,i,-,-,-,w,h,w,h", "", 0, 0, 1, len);
			vgIP.generateImperative("het", "-,i,w,h", "-,-,-,-", 0, 0, 1, len);

		} 
		else if (root.charAt(0) == 'e') 
		{
			vgIP.generateBareInfinitive("het", "", 0, 0, 1, len, "");
			vgIP.generateInfinitive("het", "", 0, 0, 1, len, "");
			vgIP.generatePast("het,het,het,het,het,het,het,het,het","ti,t,t,-,h,nw,tm,tn,w", "", 0, 0, 1, len);
			vgIP.generatePresent("met,met,met,met", "-,t,im,wt", "", 0, 0, 1,len);
			vgIP.generateFuture("aet,tet,tet,iet,tet,net,tet,tet,iet,tet","-,-,i,-,-,-,w,nh,w,nh", "", 0, 0, 1, len);
			vgIP.generateImperative("het", "-,i,w,nh", "-,-,-,-", 0, 0, 1, len);
		}
		//		 ט ת - פ הפועל
		//40
		else if (root.charAt(0) == 'v' || root.charAt(0) == 't') 
		{
			vgIP.generateBareInfinitive("h", "", 0, 0, 0, len, "");
			vgIP.generateInfinitive("h", "", 0, 0, 0, len, "");
			vgIP.generatePast("h,h,h,h,h,h,h,h,h", "ti,t,t,-,h,nw,tm,tn,w", "",0, 0, 0, len);
			vgIP.generatePresent("m,m,m,m", "-,t,im,wt", "", 0, 0, 0, len);
			vgIP.generateFuture("a,t,t,i,t,n,t,t,i,t", "-,-,i,-,-,-,w,nh,w,nh","", 0, 0, 0, len);
			vgIP.generateImperative("h", "-,i,w,nh", "-,-,-,-", 0, 0, 0, len);
		} 
		else if (root.charAt(0) == 'd' && transliterated.charAt(1) == 'i') 
		{
			vgIP.generateBareInfinitive("hi", "", 0, 0, 0, len - 1, "wt");
			vgIP.generateInfinitive("hi", "", 0, 0, 0, len, "");
			vgIP.generatePast("hi,hi,hi,hi,hi,hi,hi,hi,hi","ti,t,t,-,h,nw,tm,tn,w", "i,i,i,i,i,i,i,i,i", 0, 2, 2,len - 1);
			vgIP.generatePresent("mi,mi,mi,mi", "-,t,im,wt", "", 0, 0, 0,len - 1);
			vgIP.generateFuture("a,t,t,i,t,n,t,t,i,t", "-,-,i,-,-,-,w,nh,w,nh","i,i,i,i,i,i,i,i,i,i", 0, 0, 0, len);
			vgIP.generateImperative("hi", "-,i,w,nh", "-,-,-,-", 0, 0, 0, len);
		}
		//43 26 ל'נ
		else if (root.charAt(len - 1) == 'n') 
		{
			vgIP.generateBareInfinitive("ht", "", 0, len, len, len, "");
			vgIP.generateInfinitive("ht", "", 0, len, len, len, "");
			vgIP.generatePast("ht,ht,ht,ht,ht,ht,ht,ht,ht","ti,t,t,-,h,w,tm,tn,w", "", 0, len, len, len);
			vgIP.generatePresent("mt,mt,mt,mt", "-,t,im,wt", "", 0, 1, 1, len);
			vgIP.generateFuture("at,tt,tt,it,tt,nt,tt,tt,it,tt","-,-,i,-,-,-,w,h,w,h", "", 0, 2, 2, len);
			vgIP.generateImperative("ht", "-,i,w,h", "-,-,-,-", 0, 2, 2, len);
		}
		//		27 ל'ת
		else if (root.charAt(len - 1) == 't') 
		{
			vgIP.generateBareInfinitive("ht", "", 0, len, len, len, "");
			vgIP.generateInfinitive("ht", "", 0, len, len, len, "");
			vgIP.generatePast("ht,ht,ht,ht,ht,ht,ht,ht,ht","i,-,-,-,h,nw,m,n,w", "", 0, len, len, len);
			vgIP.generatePresent("mt,mt,mt,mt", "-,t,im,wt", "", 0, 1, 1, len);
			vgIP.generateFuture("at,tt,tt,it,tt,nt,tt,tt,it,tt","-,-,i,-,-,-,w,nh,w,nh", "", 0, 2, 2, len);
			vgIP.generateImperative("ht", "-,i,w,nh", "-,-,-,-", 0, 2, 2, len);
		} 
		else if (root.charAt(1) == 'w') 
		{
			vgIP.generateBareInfinitive("ht", "w", 0, 2, 2, len, "");
			vgIP.generateInfinitive("ht", "w", 0, 2, 2, len, "");
			vgIP.generatePast("ht,ht,ht,ht,ht,ht,ht,ht,ht","ti,t,t,-,h,w,tm,tn,w", "w,w,w,w,w,w,w,w,w,w", 0,2, 2, len);
			vgIP.generatePresent("mt,mt,mt,mt", "-,t,im,wt", "w,w,w,w", 0, 2,2, len);
			vgIP.generateFuture("at,tt,tt,it,tt,nt,tt,tt,it,tt","-,-,i,-,-,-,w,nh,w,nh", "w,w,w,w,w,w,w,w,w,w", 0, 2, 2,len);
			vgIP.generateImperative("ht", "-,i,w,nh", "w,w,w,w", 0, 2, 2, len);
		} 
		else 
		{
			vgIP.generateBareInfinitive("ht", "", 0, len, len, len, "");
			vgIP.generateInfinitive("ht", "", 0, len, len, len, "");
			vgIP.generatePast("ht,ht,ht,ht,ht,ht,ht,ht,ht","ti,t,t,-,h,nw,tm,tn,w", "", 0, len, len, len);
			vgIP.generatePresent("mt,mt,mt,mt", "-,t,im,wt", "", 0, 1, 1, len);
			vgIP.generateFuture("at,tt,tt,it,tt,nt,tt,tt,it,tt","-,-,i,-,-,-,w,nh,w,nh", "", 0, 2, 2, len);
			vgIP.generateImperative("ht", "-,i,w,nh", "-,-,-,-", 0, 2, 2, len);
		}

		//הכפלת י אם השורש מכיל י'
		if (root.indexOf("i") != -1) 
		{
			root = root.replaceAll("i", "ii");
			setGenInflection();
			len = root.length();
			//שורות 33-39
			//ז שורקת - ז
			if (root.charAt(0) == 'z' && root.charAt(len - 1) == 'n') 
			{
				vgIP.generateBareInfinitive("hzd", "", 0, 0, 1, len, "");
				vgIP.generateInfinitive("hzd", "", 0, 0, 1, len, "");
				vgIP.generatePast("hzd,hzd,hzd,hzd,hzd,hzd,hzd,hzd,hzd","ti,t,t,-,h,w,tm,tn,w", "", 0, 0, 1, len);
				vgIP.generatePresent("mzd,mzd,mzd,mzd", "-,t,im,wt", "", 0, 0,1, len);
				vgIP.generateFuture("azd,tzd,tzd,izd,tzd,nzd,tzd,tzd,izd,tzd","-,-,i,-,-,-,w,h,w,h", "", 0, 0, 1, len);
				vgIP.generateImperative("hzd", "-,i,w,h", "-,-,-,-", 0, 0, 1,len);

				//43
				//ז שורקת - ז + ל'נ
			} 
			else if (root.charAt(0) == 'z') 
			{
				vgIP.generateBareInfinitive("hzd", "", 0, 0, 1, len, "");
				vgIP.generateInfinitive("hzd", "", 0, 0, 1, len, "");
				vgIP.generatePast("hzd,hzd,hzd,hzd,hzd,hzd,hzd,hzd,hzd","ti,t,t,-,h,nw,tm,tn,w", "", 0, 0, 1, len);
				vgIP.generatePresent("mzd,mzd,mzd,mzd", "-,t,im,wt", "", 0, 0,1, len);
				vgIP.generateFuture("azd,tzd,tzd,izd,tzd,nzd,tzd,tzd,izd,tzd","-,-,i,-,-,-,w,nh,w,nh", "", 0, 0, 1, len);
				vgIP.generateImperative("hzd", "-,i,w,nh", "-,-,-,-", 0, 0, 1,len);
			}

			else if (root.charAt(0) == 'c' && root.charAt(len - 1) == 'n') 
			{
				vgIP.generateBareInfinitive("hcv", "", 0, 0, 1, len, "");
				vgIP.generateInfinitive("hcv", "", 0, 0, 1, len, "");
				vgIP.generatePast("hcv,hcv,hcv,hcv,hcv,hcv,hcv,hcv,hcv","ti,t,t,-,h,w,tm,tn,w", "", 0, 0, 1, len);
				vgIP.generatePresent("mcv,mcv,mcv,mcv", "-,t,im,wt", "", 0, 0,1, len);
				vgIP.generateFuture("acv,tcv,tcv,icv,tcv,ncv,tcv,tcv,icv,tcv","-,-,i,-,-,-,w,h,w,h", "", 0, 0, 1, len);
				vgIP.generateImperative("hcv", "-,i,w,h", "-,-,-,-", 0, 0, 1,len);
			} 
			else if (root.charAt(0) == 'c') 
			{
				vgIP.generateBareInfinitive("hcv", "", 0, 0, 1, len, "");
				vgIP.generateInfinitive("hcv", "", 0, 0, 1, len, "");
				vgIP.generatePast("hcv,hcv,hcv,hcv,hcv,hcv,hcv,hcv,hcv","ti,t,t,-,h,nw,tm,tn,w", "", 0, 0, 1, len);
				vgIP.generatePresent("mcv,mcv,mcv,mcv", "-,t,im,wt", "", 0, 0,1, len);
				vgIP.generateFuture("acv,tcv,tcv,icv,tcv,ncv,tcv,tcv,icv,tcv","-,-,i,-,-,-,w,nh,w,nh", "", 0, 0, 1, len);
				vgIP.generateImperative("hcv", "-,i,w,nh", "-,-,-,-", 0, 0, 1,len);
			}
			//ס
			//38 ל'נ + ס שורקת
			else if (root.charAt(0) == 's' && root.charAt(len - 1) == 'n') 
			{
				vgIP.generateBareInfinitive("hst", "", 0, 0, 1, len, "");
				vgIP.generateInfinitive("hst", "", 0, 0, 1, len, "");
				vgIP.generatePast("hst,hst,hst,hst,hst,hst,hst,hst,hst","ti,t,t,-,h,w,tm,tn,w", "", 0, 0, 1, len);
				vgIP.generatePresent("mst,mst,mst,mst", "-,t,im,wt", "", 0, 0,1, len);
				vgIP.generateFuture("ast,tst,tst,ist,tst,nst,tst,tst,ist,tst","-,-,i,-,-,-,w,h,w,h", "", 0, 0, 1, len);
				vgIP.generateImperative("hst", "-,i,w,h", "-,-,-,-", 0, 0, 1,len);
			} 
			else if (root.charAt(0) == 's') 
			{
				vgIP.generateBareInfinitive("hst", "", 0, 0, 1, len, "");
				vgIP.generateInfinitive("hst", "", 0, 0, 1, len, "");
				vgIP.generatePast("hst,hst,hst,hst,hst,hst,hst,hst,hst","ti,t,t,-,h,nw,tm,tn,w", "", 0, 0, 1, len);
				vgIP.generatePresent("mst,mst,mst,mst", "-,t,im,wt", "", 0, 0,1, len);
				vgIP.generateFuture("ast,tst,tst,ist,tst,nst,tst,tst,ist,tst","-,-,i,-,-,-,w,nh,w,nh", "", 0, 0, 1, len);
				vgIP.generateImperative("hst", "-,i,w,nh", "-,-,-,-", 0, 0, 1,len);
			}
			//ש
			//39 ל'נ + ש שורקת
			else if (root.charAt(0) == 'e' && root.charAt(len - 1) == 'n') 
			{
				vgIP.generateBareInfinitive("het", "", 0, 0, 1, len, "");
				vgIP.generateInfinitive("het", "", 0, 0, 1, len, "");
				vgIP.generatePast("het,het,het,het,het,het,het,het,het","ti,t,t,-,h,w,tm,tn,w", "", 0, 0, 1, len);
				vgIP.generatePresent("met,met,met,met", "-,t,im,wt", "", 0, 0,1, len);
				vgIP.generateFuture("aet,tet,tet,iet,tet,net,tet,tet,iet,tet","-,-,i,-,-,-,w,h,w,h", "", 0, 0, 1, len);
				vgIP.generateImperative("het", "-,i,w,h", "-,-,-,-", 0, 0, 1,len);

			} 
			else if (root.charAt(0) == 'e') 
			{
				vgIP.generateBareInfinitive("het", "", 0, 0, 1, len, "");
				vgIP.generateInfinitive("het", "", 0, 0, 1, len, "");
				vgIP.generatePast("het,het,het,het,het,het,het,het,het","ti,t,t,-,h,nw,tm,tn,w", "", 0, 0, 1, len);
				vgIP.generatePresent("met,met,met,met", "-,t,im,wt", "", 0, 0,1, len);
				vgIP.generateFuture("aet,tet,tet,iet,tet,net,tet,tet,iet,tet","-,-,i,-,-,-,w,nh,w,nh", "", 0, 0, 1, len);
				vgIP.generateImperative("het", "-,i,w,nh", "-,-,-,-", 0, 0, 1,len);
			}
			//		 ט ת - פ הפועל
			//40
			else if (root.charAt(0) == 'v' || root.charAt(0) == 't') 
			{
				vgIP.generateBareInfinitive("h", "", 0, 0, 0, len, "");
				vgIP.generateInfinitive("h", "", 0, 0, 0, len, "");
				vgIP.generatePast("h,h,h,h,h,h,h,h,h", "ti,t,t,-,h,nw,tm,tn,w","", 0, 0, 0, len);
				vgIP.generatePresent("m,m,m,m", "-,t,im,wt", "", 0, 0, 0, len);
				vgIP.generateFuture("a,t,t,i,t,n,t,t,i,t","-,-,i,-,-,-,w,nh,w,nh", "", 0, 0, 0, len);
				vgIP.generateImperative("h", "-,i,w,nh", "-,-,-,-", 0, 0, 0,len);
			}
			//43 26 ל'נ
			else if (root.charAt(len - 1) == 'n') 
			{
				vgIP.generateBareInfinitive("ht", "", 0, len, len, len, "");
				vgIP.generateInfinitive("ht", "", 0, len, len, len, "");
				vgIP.generatePast("ht,ht,ht,ht,ht,ht,ht,ht,ht","ti,t,t,-,h,w,tm,tn,w", "", 0, len, len, len);
				vgIP.generatePresent("mt,mt,mt,mt", "-,t,im,wt", "", 0, 1, 1,len);
				vgIP.generateFuture("at,tt,tt,it,tt,nt,tt,tt,it,tt","-,-,i,-,-,-,w,h,w,h", "", 0, 2, 2, len);
				vgIP.generateImperative("ht", "-,i,w,h", "-,-,-,-", 0, 2, 2,len);
			}
			//		27 ל'ת
			else if (root.charAt(len - 1) == 't') 
			{
				vgIP.generateBareInfinitive("ht", "", 0, len, len, len, "");
				vgIP.generateInfinitive("ht", "", 0, len, len, len, "");
				vgIP.generatePast("ht,ht,ht,ht,ht,ht,ht,ht,ht","i,-,-,-,h,nw,m,n,w", "", 0, len, len, len);
				vgIP.generatePresent("mt,mt,mt,mt", "-,t,im,wt", "", 0, 1, 1,len);
				vgIP.generateFuture("at,tt,tt,it,tt,nt,tt,tt,it,tt","-,-,i,-,-,-,w,nh,w,nh", "", 0, 2, 2, len);
				vgIP.generateImperative("ht", "-,i,w,nh", "-,-,-,-", 0, 2, 2,len);

			} 
			else 
			{
				vgIP.generateBareInfinitive("ht", "", 0, len, len, len, "");
				vgIP.generateInfinitive("ht", "", 0, len, len, len, "");
				vgIP.generatePast("ht,ht,ht,ht,ht,ht,ht,ht,ht","ti,t,t,-,h,nw,tm,tn,w", "", 0, len, len, len);
				vgIP.generatePresent("mt,mt,mt,mt", "-,t,im,wt", "", 0, 1, 1,len);
				vgIP.generateFuture("at,tt,tt,it,tt,nt,tt,tt,it,tt","-,-,i,-,-,-,w,nh,w,nh", "", 0, 2, 2, len);
				vgIP.generateImperative("ht", "-,i,w,nh", "-,-,-,-", 0, 2, 2,len);
			}
		}
	}

	//ברב מילים שורש וכח בברקלי ובלקסיקון שורש יכח - לברר
	//עפ"י רב מילים כתיב מלא תיקני וו
	//התוכח-יכח
	//id = 3725
	private void inflectPattern43() throws Exception 
	{
		setGenInflection();
		//String newRoot = "w" + root.substring(1,3);
		//VerbGenIP vgIP = new VerbGenIP(newRoot);
		//vgIP.generateBase("htw","",1,3,3,3, "");
		vgIP.generateBareInfinitive("htww", "", 1, 3, 3, 3, "");
		vgIP.generateInfinitive("htww", "", 1, 3, 3, 3, "");
		//vgIP.generatePast("htw,htw,htw,htw,htw,htw,htw,htw,htw,htw","ti,t,t,-,h,nw,tm,tn,w,w",
		// "",1,3,3,3);
		vgIP.generatePast("htww,htww,htww,htww,htww,htww,htww,htww,htww",
				"ti,t,t,-,h,nw,tm,tn,w", "", 1, 3, 3, 3);
		//vgIP.generatePresent("mtw,mtw,mtw,mtw","-,t,im,wt", "",1,3,3,3);
		vgIP.generatePresent("mtww,mtww,mtww,mtww", "-,t,im,wt", "", 1, 3,3, 3);
		//vgIP.generateFuture("atw,ttw,ttw,itw,ttw,ntw,ttw,ttw,itw,ttw","-,-,i,-,-,-,w,nh,w,nh","",0,2,2,3
		// );
		vgIP.generateFuture(
				"atww,ttww,ttww,itww,ttww,ntww,ttww,ttww,itww,ttww",
				"-,-,i,-,-,-,w,nh,w,nh", "", 1, 3, 3, 3);
		//vgIP.generateImperative("htw","-,i,w,nh","-,-,-,-",1,3,3,3);
		vgIP.generateImperative("htww", "-,i,w,nh", "-,-,-,-", 1, 3, 3, 3);
	}

	//ברב מילים שורש גלי בברקלי ובלקסיקון שורש גלה - לברר
	//התגליתי-גלה
	//id = 9086
private void inflectPattern44() throws Exception {
		setGenInflection();
		int len = root.length();
		if (root.charAt(0) == 'z' && root.charAt(2) == 'h') {
			vgIP.generateBareInfinitive("hzd", "", 1, len - 1, len - 1,
					len - 1, "wt");
			vgIP.generateInfinitive("hzd", "", 1, len - 1, len - 1, len - 1,
					"wt");
			vgIP.generatePast("hzd,hzd,hzd,hzd,hzd,hzd,hzd,hzd,hzd",
					"ti,t,t,h,th,nw,tm,tn,w", "i,i,i,-,-,i,i,i,-", 1, len - 1,
					len - 1, len - 1);
			vgIP.generatePresent("mzd,mzd,mzd,mzd", "h,it,im,wt", "", 1,
					len - 1, len - 1, len - 1);
			vgIP
					.generateFuture("azd,tzd,tzd,izd,tzd,nzd,tzd,tzd,izd,tzd",
							"h,h,i,h,h,h,w,inh,w,inh", "", 1, len - 1, len - 1,
							len - 1);
			vgIP.generateImperative("hzd", "h,i,w,inh", "-,-,-,-", 1, len - 1,
					len - 1, len - 1);
		} else if (root.charAt(0) == 'z') {
			vgIP.generateBareInfinitive("hzd", "", 1, len, len, len, "wt");
			vgIP.generateInfinitive("hzd", "", 1, len, len, len, "wt");
			vgIP.generatePast("hzd,hzd,hzd,hzd,hzd,hzd,hzd,hzd,hzd",
					"ti,t,t,h,th,nw,tm,tn,w", "", 1, len, len, len);
			vgIP.generatePresent("mzd,mzd,mzd,mzd", "h,it,im,wt", "", 1, len,
					len, len);
			vgIP.generateFuture("azd,tzd,tzd,izd,tzd,nzd,tzd,tzd,izd,tzd",
					"h,h,i,h,h,h,w,nh,w,nh", "", 1, len, len, len);
			vgIP.generateImperative("hzd", "h,i,w,nh", "-,-,-,-", 1, len, len,
					len);
		} else if (root.charAt(0) == 'e' && root.charAt(1) == 'w'
				&& root.charAt(2) == 'h') {
			vgIP.generateBareInfinitive("het", "", 1, len - 1, len - 1,
					len - 1, "wt");
			vgIP.generateInfinitive("het", "", 2 - 1, len - 1, len - 1,
					len - 1, "wt");
			vgIP.generatePast("hetw,hetw,hetw,hetw,hetw,hetw,hetw,hetw,hetw",
					"iti,it,it,h,th,inw,itm,itn,-", "", 1, len - 1, len - 1,
					len - 1);
			vgIP.generatePresent("metw,metw,metw,metw", "h,it,im,t", "", 1,
					len - 1, len - 1, len - 1);
			vgIP
					.generateFuture(
							"aetw,tetw,tetw,ietw,tetw,netw,tetw,tetw,ietw,tetw",
							"h,h,i,h,h,h,w,inh,-,inh", "", 1, len - 1, len - 1,
							len - 1);
			vgIP.generateImperative("hetw", "h,i,-,inh", "-,-,-,-", 1, len - 1,
					len - 1, len - 1);
		} else if (root.charAt(0) == 'e' && root.charAt(2) == 'h') {
			vgIP.generateBareInfinitive("het", "", 1, len - 1, len - 1,
					len - 1, "wt");
			vgIP.generateInfinitive("het", "", 2 - 1, len - 1, len - 1,
					len - 1, "wt");
			vgIP.generatePast("het,het,het,het,het,het,het,het,het",
					"iti,it,it,h,th,inw,itm,itn,w", "", 1, len - 1, len - 1,
					len - 1);
			vgIP.generatePresent("met,met,met,met", "h,it,im,wt", "", 1,
					len - 1, len - 1, len - 1);
			vgIP
					.generateFuture("aet,tet,tet,iet,tet,net,tet,tet,iet,tet",
							"h,h,i,h,h,h,w,inh,w,inh", "", 1, len - 1, len - 1,
							len - 1);
			vgIP.generateImperative("het", "h,i,w,inh", "-,-,-,-", 1, len - 1,
					len - 1, len - 1);
		} else if (root.charAt(0) == 'e') {
			vgIP.generateBareInfinitive("het", "", 2, len, len, len, "wt");
			vgIP.generateInfinitive("het", "", 2, len, len, len, "wt");
			vgIP.generatePast("het,het,het,het,het,het,het,het,het",
					"ti,t,t,h,th,nw,tm,tn,w", "", 2, len, len, len);
			vgIP.generatePresent("met,met,met,met", "h,it,im,wt", "", 2, len,
					len, len);
			vgIP.generateFuture("aet,tet,tet,iet,tet,net,tet,tet,iet,tet",
					"h,h,i,h,h,h,w,nh,w,nh", "", 2, len, len, len);
			vgIP.generateImperative("het", "h,i,w,nh", "-,-,-,-", 2, len, len,
					len);
		} else if (root.charAt(0) == 'c' && root.charAt(1) == 'w'
				&& root.charAt(2) == 'h') {
			vgIP.generateBareInfinitive("hcvw", "", 0, 0, 0, 0, "wt");
			vgIP.generateInfinitive("hcvw", "", 0, 0, 0, 0, "wt");
			vgIP
					.generatePast(
							"hcvwwi,hcvwwi,hcvwwi,hcvww,hcvww,hcvwwi,hcvwwi,hcvwwi,hcvw",
							"ti,t,t,h,th,nw,tm,tn,w", "", 0, 0, 0, 0);
			vgIP.generatePresent("mcvww,mcvww,mcvww,mcvw", "h,it,im,wt", "", 0,
					0, 0, 0);
			vgIP
					.generateFuture(
							"acvww,tcvww,tcvww,icvww,tcvww,ncvww,tcvww,tcvww,icvww,tcvww",
							"h,h,i,h,h,h,-,nh,-,nh", "", 0, 0, 0, 0);
			vgIP
					.generateImperative("hcvww", "h,i,-,inh", "-,-,-,-", 0, 0,
							0, 0);
		} else if (root.charAt(0) == 'c') {
			vgIP.generateBareInfinitive("hcv", "", 2, len, len, len, "wt");
			vgIP.generateInfinitive("hcv", "", 2, len, len, len, "wt");
			vgIP.generatePast("hcv,hcv,hcv,hcv,hcv,hcv,hcv,hcv,hcv",
					"ti,t,t,h,th,nw,tm,tn,w", "", 2, len, len, len);
			vgIP.generatePresent("mcv,mcv,mcv,mcv", "h,it,im,wt", "", 2, len,
					len, len);
			vgIP.generateFuture("acv,tcv,tcv,icv,tcv,ncv,tcv,tcv,icv,tcv",
					"h,h,i,h,h,h,w,nh,w,nh", "", 2, len, len, len);
			vgIP.generateImperative("hcv", "h,i,w,nh", "-,-,-,-", 2, len, len,
					len);
		} else if (root.charAt(1) == 'w') {
			vgIP.generateBareInfinitive("ht", "", 0, 2, 3, 3, "wt");
			vgIP.generateInfinitive("ht", "", 0, 2, 3, 3, "wt");
			vgIP.generatePast("ht,ht,ht,ht,ht,ht,ht,ht,ht",
					"ti,t,t,h,th,nw,tm,tn,w", "wi,wi,wi,w,w,wi,wi,wi,-", 0, 2,
					3, 3);
			vgIP.generatePresent("mt,mt,mt,mt", "h,h,im,wt", "w,w,w,-", 0, 1,
					1, 2);
			vgIP.generateFuture("at,tt,tt,it,tt,nt,tt,tt,it,tt",
					"wh,wh,wi,wh,wh,wh,w,winh,w,winh", "", 0, 2, 3, 3);
			vgIP
					.generateImperative("ht", "wh,wi,w,winh", "-,-,-,-", 0, 2,
							3, 3);
		} else if (root.charAt(0) == 'i') {
			vgIP.generateBareInfinitive("ht", "i", 0, 1, 1, 2, "wt");
			vgIP.generateInfinitive("ht", "i", 0, 1, 1, 3, "wt");
			vgIP.generatePast("hti,hti,hti,hti,hti,hti,hti,hti,hti",
					"ti,t,t,h,th,nw,tm,tn,w", "i,i,i,-,-,i,i,i,-", 0, 2,
					3, 3);
			vgIP.generatePresent("mt,mt,mt,mt", "h,h,im,wt", "i,i,i,i", 0, 1,
					1, 2);
			vgIP.generateFuture("ati,tti,tti,iti,tti,nti,tti,tti,iti,tti",
					"h,h,i,h,h,h,w,inh,w,inh", "", 0, 2, 3, 3);
			vgIP
					.generateImperative("hti", "h,i,w,inh", "-,-,-,-", 0, 2,
							3, 3);
//			vgIP.setSpelling(IRREGULAR_SPELLING);
//			vgIP.generateBareInfinitive("ht", "", 0, 1, 1, 2, "wt");
//			vgIP.generateInfinitive("ht", "", 0, 1, 1, 3, "wt");
//			vgIP.generatePast("ht,ht,ht,ht,ht,ht,ht,ht,ht",
//					"ti,t,t,h,th,nw,tm,tn,w", "", 0, 2,
//					3, 3);
//			vgIP.generatePresent("mt,mt,mt,mt", "h,h,im,wt", "", 0, 1,
//					1, 2);
//			vgIP.generateFuture("at,tt,tt,it,tt,nt,tt,tt,it,tt",
//					"h,h,i,h,h,h,w,inh,w,inh", "", 0, 2, 3, 3);
//			vgIP
//					.generateImperative("ht", "h,i,w,inh", "-,-,-,-", 0, 2,
//							3, 3);
//			
		
		} else {
			vgIP.generateBareInfinitive("ht", "", 0, 2, 3, 3, "wt");
			vgIP.generateInfinitive("ht", "", 0, 2, 3, 3, "wt");
			vgIP.generatePast("ht,ht,ht,ht,ht,ht,ht,ht,ht",
					"ti,t,t,h,th,nw,tm,tn,w", "i,i,i,-,-,i,i,i,-", 0, 2, 3, 3);
			vgIP.generatePresent("mt,mt,mt,mt", "h,h,im,wt", "", 0, 1, 1, 2);
			vgIP.generateFuture("at,tt,tt,it,tt,nt,tt,tt,it,tt",
					"h,h,i,h,h,h,w,inh,w,inh", "", 0, 2, 3, 3);
			vgIP.generateImperative("ht", "h,i,w,inh", "-,-,-,-", 0, 2, 3, 3);
		}
	}
	//ברב מילים שורש גלי בברקלי ובלקסיקון שורש גלה - לברר
	//קום - התקומם
	//id = 12323
	private void inflectPattern45() throws Exception {
		setGenInflection();
		int len = root.length();

		if (root.charAt(0) == 'e') {
			vgIP.generateBareInfinitive("het", "w", 0, 0, 1, 3, "");
			vgIP.generateInfinitive("het", "w", 0, 0, 1, 3, "");
			vgIP.generatePast("hetw,hetw,hetw,hetw,hetw,hetw,hetw,hetw,hetw",
					"ti,t,t,-,h,nw,tm,tn,w", "", 0, 0, 1, 3);
			vgIP.generatePresent("metw,metw,metw,metw", "-,t,im,wt", "", 0, 0,
					1, 3);
			vgIP.generateFuture(
					"aetw,tetw,tetw,ietw,tetw,netw,tetw,tetw,ietw,tetw",
					"-,-,i,-,-,-,w,nh,w,nh", "", 0, 0, 1, 3);
			vgIP.generateImperative("hetw", "-,i,w,nh", "", 0, 0, 1, 3);

		} else if (root.charAt(0) == 'c') {
			vgIP.generateBareInfinitive("hcv", "w", 0, 0, 1, 3, "");
			vgIP.generateInfinitive("hcv", "w", 0, 0, 1, 3, "");
			vgIP.generatePast("hcvw,hcvw,hcvw,hcvw,hcvw,hcvw,hcvw,hcvw,hcvw",
					"ti,t,t,-,h,nw,tm,tn,w", "", 0, 0, 1, 3);
			vgIP.generatePresent("mcvw,mcvw,mcvw,mcvw", "-,t,im,wt", "", 0, 0,
					1, 3);
			vgIP.generateFuture(
					"acvw,tcvw,tcvw,icvw,tcvw,ncvw,tcvw,tcvw,icvw,tcvw",
					"-,-,i,-,-,-,w,nh,w,nh", "", 0, 0, 1, 3);
			vgIP.generateImperative("hcvw", "-,i,w,nh", "", 0, 0, 1, 3);

		} else if (root.charAt(0) == 's') {
			vgIP.generateBareInfinitive("hst", "w", 0, 0, 1, 3, "");
			vgIP.generateInfinitive("hst", "w", 0, 0, 1, 3, "");
			vgIP.generatePast("hstw,hstw,hstw,hstw,hstw,hstw,hstw,hstw,hstw",
					"ti,t,t,-,h,nw,tm,tn,w", "", 0, 0, 1, 3);
			vgIP.generatePresent("mstw,mstw,mstw,mstw", "-,t,im,wt", "", 0, 0,
					1, 3);
			vgIP.generateFuture(
					"astw,tstw,tstw,istw,tstw,nstw,tstw,tstw,istw,tstw",
					"-,-,i,-,-,-,w,nh,w,nh", "", 0, 0, 1, 3);
			vgIP.generateImperative("hstw", "-,i,w,nh", "", 0, 0, 1, 3);
		}

		else {
			if (root.charAt(len - 2) == 'i' || root.charAt(len - 2) == 'w') {
				vgIP.generateBareInfinitive("ht", "w", 0, 1, 2, 3, String
						.valueOf(root.charAt(len - 1)));
				vgIP.generateInfinitive("ht", "w", 0, 1, 2, 3, String
						.valueOf(root.charAt(len - 1)));
			} else {
				vgIP.generateBareInfinitive("ht", "w", 0, 1, 1, 3, "");
				vgIP.generateInfinitive("ht", "w", 0, 1, 1, 3, "");
			}

			String suffix1 = root.substring(2) + root.substring(2);
			String suffix2 = "";
			if (root.charAt(len - 1) == 'n') {
				suffix2 = suffix1 + "ti," + suffix1 + "t," + suffix1 + "t,"
						+ suffix1 + "," + suffix1 + "h," + suffix1 + "w,"
						+ suffix1 + "tm," + suffix1 + "tn," + suffix1 + "w";
			} else {
				suffix2 = suffix1 + "ti," + suffix1 + "t," + suffix1 + "t,"
						+ suffix1 + "," + suffix1 + "h," + suffix1 + "nw,"
						+ suffix1 + "tm," + suffix1 + "tn," + suffix1 + "w";
			}
			vgIP.generatePast("ht,ht,ht,ht,ht,ht,ht,ht,ht", suffix2,
					"w,w,w,w,w,w,w,w,w", 0, 1, 3, 3);

			if (root.charAt(len - 2) == 'i' || root.charAt(len - 2) == 'w') {
				String suffix3 = suffix1 + "," + suffix1 + "t," + suffix1
						+ "im," + suffix1 + "wt,";
				vgIP.generatePresent("mt,mt,mt,mt", suffix3, "w,w,w,w", 0, 1,
						3, 3);

				String suffix4 = "";
				String suffix5 = "";
				if (root.charAt(len - 1) == 'n') {
					suffix4 = suffix1 + "," + suffix1 + "," + suffix1 + "i,"
							+ suffix1 + "," + suffix1 + "," + suffix1 + ","
							+ suffix1 + "w," + suffix1 + "h," + suffix1 + "w,"
							+ suffix1 + "h,";
					suffix5 = suffix1 + "," + suffix1 + "i," + suffix1 + "w,"
							+ suffix1 + "h,";
				} else {
					suffix4 = suffix1 + "," + suffix1 + "," + suffix1 + "i,"
							+ suffix1 + "," + suffix1 + "," + suffix1 + ","
							+ suffix1 + "w," + suffix1 + "nh," + suffix1 + "w,"
							+ suffix1 + "nh,";
					suffix5 = suffix1 + "," + suffix1 + "i," + suffix1 + "w,"
							+ suffix1 + "nh,";
				}
				vgIP.generateFuture("at,tt,tt,it,tt,nt,tt,tt,it,tt", suffix4,
						"w,w,w,w,w,w,w,w,w,w", 0, 1, 3, 3);
				vgIP.generateImperative("ht", suffix5, "w,w,w,w", 0, 1, 3, 3);

			} else {
				vgIP.generatePresent("mt,mt,mt,mt", "-,t,im,wt", "w,w,w,w", 0,
						1, 1, 3);
				vgIP.generateFuture("at,tt,tt,it,tt,nt,tt,tt,it,tt",
						"-,-,i,-,-,-,w,nh,w,nh", "w,w,w,w,w,w,w,w,w,w", 0, 1,
						1, 3);
				vgIP
						.generateImperative("ht", "-,i,w,nh", "w,w,w,w", 0, 1,
								1, 3);
			}
		}
	}

	//קום - התקיים
	//id = 14695
	private void inflectPattern46() throws Exception {
		setGenInflection();
		if (root.charAt(0) == 's') {
			vgIP.generateBareInfinitive("hst", "ii", 0, 0, 2, 3, "");
			vgIP.generateInfinitive("hst", "ii", 0, 0, 2, 3, "");
			vgIP.generatePast("hst,hst,hst,hst,hst,hst,hst,hst,hst",
					"ti,t,t,-,h,nw,tm,tn,w", "ii,ii,ii,ii,ii,ii,ii,ii,ii", 0,
					0, 2, 3);
			vgIP.generatePresent("mst,mst,mst,mst", "-,t,im,wt", "ii,ii,ii,ii",
					0, 0, 2, 3);
			vgIP.generateFuture("ast,tst,tst,ist,tst,nst,tst,tst,ist,tst",
					"-,-,i,-,-,-,w,nh,w,nh", "ii,ii,ii,ii,ii,ii,ii,ii,ii,ii",
					0, 0, 2, 3);
			vgIP.generateImperative("hst", "-,i,w,nh", "ii,ii,ii,ii", 0, 0, 2,
					3);
		} else {
			vgIP.setSpelling(IRREGULAR_SPELLING);
			vgIP.generateBareInfinitive("ht", "i", 0, 1, 2, 3, "");
			vgIP.generateInfinitive("ht", "i", 0, 1, 2, 3, "");
			vgIP.generatePast("ht,ht,ht,ht,ht,ht,ht,ht,ht",
					"ti,t,t,-,h,nw,tm,tn,w", "i,i,i,i,i,i,i,i,i", 0,
					1, 2, 3);
			vgIP.generatePresent("mt,mt,mt,mt", "-,t,im,wt", "i,i,i,i", 0,
					1, 2, 3);
			vgIP.generateFuture("at,tt,tt,it,tt,nt,tt,tt,it,tt",
					"-,-,i,-,-,-,w,nh,w,nh", "i,i,i,i,i,i,i,i,i,i",
					0, 1, 2, 3);
			vgIP
					.generateImperative("ht", "-,i,w,nh", "i,i,i,i", 0, 1,
							2, 3);
			vgIP.setSpelling(originalSpelling);
			vgIP.generateBareInfinitive("ht", "ii", 0, 1, 2, 3, "");
			vgIP.generateInfinitive("ht", "ii", 0, 1, 2, 3, "");
			vgIP.generatePast("ht,ht,ht,ht,ht,ht,ht,ht,ht",
					"ti,t,t,-,h,nw,tm,tn,w", "ii,ii,ii,ii,ii,ii,ii,ii,ii", 0,
					1, 2, 3);
			vgIP.generatePresent("mt,mt,mt,mt", "-,t,im,wt", "ii,ii,ii,ii", 0,
					1, 2, 3);
			vgIP.generateFuture("at,tt,tt,it,tt,nt,tt,tt,it,tt",
					"-,-,i,-,-,-,w,nh,w,nh", "ii,ii,ii,ii,ii,ii,ii,ii,ii,ii",
					0, 1, 2, 3);
			vgIP
					.generateImperative("ht", "-,i,w,nh", "ii,ii,ii,ii", 0, 1,
							2, 3);
		}
	}

	//כון - התכוון
	//id = 12910
	private void inflectPattern47() throws Exception {
		setGenInflection();
		vgIP.generateBareInfinitive("ht", "w", 0, 2, 2, 3, "");
		vgIP.generateInfinitive("ht", "w", 0, 2, 2, 3, "");
		if (root.charAt(2) == 'n')
			vgIP.generatePast("ht,ht,ht,ht,ht,ht,ht,ht,ht",
					"ti,t,t,-,h, w,tm,tn,w", "ww,ww,ww,ww,ww,ww,ww,ww,ww", 0,
					1, 2, 3);
		else
			vgIP.generatePast("ht,ht,ht,ht,ht,ht,ht,ht,ht",
					"ti,t,t,-,nh, nw,tm,tn,w", "ww,ww,ww,ww,ww,ww,ww,ww,ww", 0,
					1, 2, 3);

		vgIP.generatePresent("mt,mt,mt,mt", "-,t,im,wt", "w,w,w,w", 0, 1, 1, 3);
		if (root.charAt(2) == 'n') {
			vgIP.generateFuture("at,tt,tt,it,tt,nt,tt,tt,it,tt",
					"-,-,i,-,-,-,w,h,w,h", "w,w,w,w,w,w,w,w,w,w", 0, 2, 2, 3);
			vgIP.generateImperative("ht", "-,i,w,h", "w,w,w,w", 0, 2, 2, 3);
		} else {
			vgIP.generateFuture("at,tt,tt,it,tt,nt,tt,tt,it,tt",
					"-,-,i,-,-,-,w,nh,w,nh", "w,w,w,w,w,w,w,w,w,w", 0, 2, 2, 3);
			vgIP.generateImperative("ht", "-,i,w,nh", "w,w,w,w", 0, 2, 2, 3);
		}
	}

	/////////////////////////////////////////////////////////////////////////
	///Hif'il
	/////////////////////////////////////////////////////////////////////////

	//הפקיד-פקד
	//id = 11820
	private void inflectPattern48() throws Exception {
		setGenInflection();
		int len = root.length();
		if (root.charAt(1) == 'w') {
			vgIP.generateBareInfinitive("h", "wi", 0, 2, 2, 3, "");
			vgIP.generateInfinitive("h", "wi", 0, 2, 2, 3, "");
			vgIP.generatePast("h,h,h,h,h,h,h,h,h", "ti,t,t,-,h,nw,tm,tn,w",
					"w,w,w,wi,wi,w,w,w,wi", 0, 2, 2, 3);
			vgIP.generatePresent("m,m,m,m", "-,h,im,wt", "wi,wi,wi,wi", 0, 2,
					2, 3);
			vgIP.generateFuture("a,t,t,i,t,n,t,t,i,t", "-,-,i,-,-,-,w,nh,w,nh",
					"wi,wi,wi,wi,wi,wi,wi,w,wi,w", 0, 2, 2, 3);
			vgIP.generateImperative("h", "-,i,w,nh", "w,wi,wi,w", 0, 2, 2, 3);
			vgIP.setSpelling(IRREGULAR_SPELLING);
			vgIP.generateBareInfinitive("h", "i", 0, 2, 2, 3, "");
			vgIP.generateInfinitive("h", "i", 0, 2, 2, 3, "");
			vgIP.generatePast("h,h,h,h,h,h,h,h,h", "ti,t,t,-,h,nw,tm,tn,w",
					"-,-,-,i,i,-,-,-,i", 0, 2, 2, 3);
			vgIP.generatePresent("m,m,m,m", "-,h,im,wt", "i,i,i,i", 0, 2,
					2, 3);
			vgIP.generateFuture("a,t,t,i,t,n,t,t,i,t", "-,-,i,-,-,-,w,nh,w,nh",
					"i,i,i,i,i,i,i,-,i,-", 0, 2, 2, 3);
			vgIP.generateImperative("h", "-,i,w,nh", "-,i,i,-", 0, 2, 2, 3);
			vgIP.setSpelling(originalSpelling);

		} else {
			vgIP.generateBareInfinitive("h", "i", 0, len - 1, len - 1, len, "");
			vgIP.generateInfinitive("h", "i", 0, len - 1, len - 1, len, "");
			if (root.charAt(2) == 'n') {
				vgIP.generatePast("h,h,h,h,h,h,h,h,h", "ti,t,t,-,h,w,tm,tn,w",
						"-,-,-,i,i,-,-,-,i", 0, 2, 2, 3);

				if ((root.charAt(0) != 'a') && (root.charAt(0) != 'h')
						&& (root.charAt(0) != 'x') && (root.charAt(0) != 'y')) {
					vgIP.setSpelling(IRREGULAR_SPELLING);
					vgIP.generatePast("hi,hi,hi,hi,hi,hi,hi,hi,hi",
							"ti,t,t,-,h,w,tm,tn,w", "-,-,-,i,i,-,-,-,i", 0, 2,
							2, 3);
					vgIP.setSpelling(originalSpelling);
				}
				vgIP.generatePresent("m,m,m,m", "-,h,im,wt", "i,i,i,i", 0, 2,
						2, 3);
				vgIP.generateFuture("a,t,t,i,t,n,t,t,i,t",
						"-,-,i,-,-,-,w,h,w,h", "i,i,i,i,i,i,i,-,i,-", 0, 2, 2,
						3);
				vgIP.generateImperative("h", "-,i,w,h", "-,i,i,-", 0, 2, 2, 3);
			} else if (root.charAt(2) == 't') {
				vgIP.generatePast("h,h,h,h,h,h,h,h,h", "i,-,-,-,h,nw,m,n,w",
						"-,-,-,i,i,-,-,-,i", 0, 2, 2, 3);

				if ((root.charAt(0) != 'a') && (root.charAt(0) != 'h')
						&& (root.charAt(0) != 'x') && (root.charAt(0) != 'y')) {
					vgIP.setSpelling(IRREGULAR_SPELLING);
					vgIP.generatePast("hi,hi,hi,hi,hi,hi,hi,hi,hi",
							"i,-,-,-,h,nw,m,n,w", "-,-,-,i,i,-,-,-,i", 0, 2, 2,
							3);
					vgIP.setSpelling(originalSpelling);
				}
				vgIP.generatePresent("m,m,m,m", "-,h,im,wt", "i,i,i,i", 0, 2,
						2, 3);
				vgIP.generateFuture("a,t,t,i,t,n,t,t,i,t",
						"-,-,i,-,-,-,w,nh,w,nh", "i,i,i,i,i,i,i,-,i,-", 0, 2,
						2, 3);
				vgIP.generateImperative("h", "-,i,w,nh", "-,i,i,-", 0, 2, 2, 3);
			} else {
				vgIP.generatePast("h,h,h,h,h,h,h,h,h", "ti,t,t,-,h,nw,tm,tn,w",
						"-,-,-,i,i,-,-,-,i", 0, len - 1, len - 1, len);
				if ((root.charAt(0) != 'a') && (root.charAt(0) != 'h')
						&& (root.charAt(0) != 'x') && (root.charAt(0) != 'y')) {
					vgIP.setSpelling(IRREGULAR_SPELLING);
					vgIP.generatePast("hi,hi,hi,hi,hi,hi,hi,hi,hi",
							"ti,t,t,-,h,nw,tm,tn,w", "-,-,-,i,i,-,-,-,i", 0, 2,
							2, 3);
					vgIP.setSpelling(originalSpelling);
				}
				vgIP.generatePresent("m,m,m,m", "-,h,im,wt", "i,i,i,i", 0,
						len - 1, len - 1, len);
				vgIP.generateFuture("a,t,t,i,t,n,t,t,i,t",
						"-,-,i,-,-,-,w,nh,w,nh", "i,i,i,i,i,i,i,-,i,-", 0,
						len - 1, len - 1, len);
				vgIP.generateImperative("h", "-,i,w,nh", "-,i,i,-", 0, len - 1,
						len - 1, len);
			}
		}
	}

	//הוריד-ירד
	//id = 8852
	private void inflectPattern49() throws Exception {
		setGenInflection();
		vgIP.generateBareInfinitive("hw", "i", 1, 2, 2, 3, "");
		vgIP.generateInfinitive("hw", "i", 1, 2, 2, 3, "");
		vgIP.generatePast("hw,hw,hw,hw,hw,hw,hw,hw,hw",
				"ti,t,t,-,h,nw,tm,tn,w", "-,-,-,i,i,-,-,-,i", 1, 2, 2, 3);
		vgIP.generatePresent("mw,mw,mw,mw", "-,h,im,wt", "i,i,i,i", 1, 2, 2, 3);
		vgIP.generateFuture("aw,tw,tw,iw,tw,nw,tw,tw,iw,tw",
				"-,-,i,-,-,-,w,nh,w,nh", "i,i,i,i,i,i,i,-,i,-", 1, 2, 2, 3);
		vgIP.generateImperative("hw", "-,i,w,nh", "-,i,i,-", 1, 2, 2, 3);
	}

	//הפיל-נפל
	//id = 13106
	private void inflectPattern50() throws Exception {
		setGenInflection();
		vgIP.generateBareInfinitive("h", "i", 1, 2, 2, 3, "");
		vgIP.generateInfinitive("h", "i", 1, 2, 2, 3, "");
		vgIP.generatePast("h,h,h,h,h,h,h,h,h,", "ti,t,t,-,h,nw,tm,tn,w",
				"-,-,-,i,i,-,-,-,i", 1, 2, 2, 3);
		vgIP.generatePresent("m,m,m,m", "-,h,im,wt", "i,i,i,i", 1, 2, 2, 3);
		vgIP.generateFuture("a,t,t,i,t,n,t,t,i,t", "-,-,i,-,-,-,w,nh,w,nh",
				"i,i,i,i,i,i,i,-,i,-", 1, 2, 2, 3);
		vgIP.generateImperative("h", "-,i,w,nh", "-,i,i,-", 1, 2, 2, 3);
	}

	//ברב מילים שורש קני בברקלי ובלקסיקון שורש קנה - לברר
	//הקנה-קנה
	//id = 289
	private void inflectPattern51() throws Exception {
		setGenInflection();
		if (root.charAt(1) == 'w') {
			vgIP.generateBareInfinitive("h", "w", 0, 2, 3, 3, "t");
			vgIP.generateInfinitive("h", "w", 0, 2, 3, 3, "t");
			vgIP.generatePast("h,h,h,h,h,h,h,h,h", "ti,t,t,h,th,nw,tm,tn,w",
					"wi,wi,wi,w,w,wi,wi,wi,-", 0, 2, 3, 3);
			vgIP.setSpelling(IRREGULAR_SPELLING);
			vgIP.generatePast("h,h,h,h,h,h,h,h,h", "ti,t,t,h,th,nw,tm,tn,w",
					"i,i,i,-,-,i,i,i,-", 0, 2, 3, 3);
			vgIP.generatePast("hi,hi,hi,hi,hi,hi,hi,hi,hi", "ti,t,t,h,th,nw,tm,tn,w",
					"wi,wi,wi,w,w,wi,wi,wi,-", 0, 2, 3, 3);
			vgIP.setSpelling(originalSpelling);
			vgIP.generatePresent("m,m,m,m", "wh,wh,wim,wwt", "", 0, 1, 1, 2);
			vgIP.generateFuture("a,t,t,i,t,n,t,t,i,t",
					"wh,wh,wi,wh,wh,wh,w,winh,w,winh", "-,-,-,-,-,-,-,-,-,-",
					0, 2, 3, 3);
			vgIP.generateImperative("h", "wh,wi,w,winh", "", 0, 2, 3, 3);

		} else if (root.charAt(0) == 'i') {
			vgIP.generateBareInfinitive("hw", "w", 1, 2, 3, 3, "t");
			vgIP.generateInfinitive("hw", "w", 1, 2, 3, 3, "t");
			vgIP.generatePast("hw,hw,hw,hw,hw,hw,hw,hw,hw",
					"ti,t,t,h,th,nw,tm,tn,w", "i,i,i,-,-,i,i,i,-", 1, 2, 3, 3);
			vgIP.generatePresent("mw,mw,mw,mw", "h,h,im,wt", "", 1, 2, 3, 3);
			vgIP.generateFuture("aw,tw,tw,iw,tw,nw,tw,tw,iw,tw",
					"h,h,i,h,h,h,w,inh,w,inh", "-,-,-,-,-,-,-,-,-,-", 1, 2, 3,
					3);
			vgIP.generateImperative("hw", "h,i,w,inh", "", 1, 2, 3, 3);
			//עבור הנחה
		} else if (root.equals("nxh")) {
			vgIP.generateBareInfinitive("h", "w", 0, 2, 3, 3, "t");
			vgIP.generateInfinitive("h", "w", 0, 1, 3, 3, "t");
			vgIP.generatePast("h,h,h,h,h,h,h,h,h", "ti,t,t,h,th,nw,tm,tn,w",
					"i,i,i,-,-,i,i,i,-", 0, 2, 3, 3);
			vgIP.setSpelling(IRREGULAR_SPELLING);
			vgIP.generatePast("hi,hi,hi,hi,hi,hi,hi,hi,hi",
					"ti,t,t,h,th,nw,tm,tn,w", "i,i,i,-,-,i,i,i,-", 0, 2, 3, 3);
			vgIP.setSpelling(originalSpelling);
			vgIP.generatePresent("m,m,m,m", "h,h,im,wt", "", 0, 2, 3, 3);
			vgIP.generateFuture("a,t,t,i,t,n,t,t,i,t",
					"h,h,i,h,h,h,w,inh,w,inh", "-,-,-,-,-,-,-,-,-,-", 0, 2, 3,
					3);
			vgIP.generateImperative("h", "h,i,w,inh", "", 0, 2, 3, 3);

			//			הכה - השורש נכההכה - השורש נכה
		} else if (root.charAt(0) == 'n') {
			vgIP.generateBareInfinitive("h", "w", 1, 2, 3, 3, "t");
			vgIP.generateInfinitive("h", "w", 1, 1, 3, 3, "t");
			vgIP.generatePast("h,h,h,h,h,h,h,h,h", "ti,t,t,h,th,nw,tm,tn,w",
					"i,i,i,-,-,i,i,i,-", 1, 2, 3, 3);
			vgIP.setSpelling(IRREGULAR_SPELLING);
			vgIP.generatePast("hi,hi,hi,hi,hi,hi,hi,hi,hi",
					"ti,t,t,h,th,nw,tm,tn,w", "i,i,i,-,-,i,i,i,-", 1, 2, 3, 3);
			vgIP.setSpelling(originalSpelling);
			vgIP.generatePresent("m,m,m,m", "h,h,im,wt", "", 1, 2, 3, 3);
			vgIP.generateFuture("a,t,t,i,t,n,t,t,i,t",
					"h,h,i,h,h,h,w,inh,w,inh", "-,-,-,-,-,-,-,-,-,-", 1, 2, 3,
					3);
			vgIP.generateImperative("h", "h,i,w,inh", "", 1, 2, 3, 3);

		} else {
			vgIP.generateBareInfinitive("h", "w", 0, 2, 3, 3, "t");
			vgIP.generateInfinitive("h", "w", 0, 2, 3, 3, "t");
			vgIP.generatePast("h,h,h,h,h,h,h,h,h", "ti,t,t,h,th,nw,tm,tn,w",
					"i,i,i,-,-,i,i,i,-", 0, 2, 3, 3);
			vgIP.setSpelling(IRREGULAR_SPELLING);
			vgIP.generatePast("hi,hi,hi,hi,hi,hi,hi,hi,hi",
					"ti,t,t,h,th,nw,tm,tn,w", "i,i,i,-,-,i,i,i,-", 0, 2, 3, 3);
			vgIP.setSpelling(originalSpelling);
			vgIP.generatePresent("m,m,m,m", "h,h,im,wt", "", 0, 1, 1, 2);
			vgIP.generateFuture("a,t,t,i,t,n,t,t,i,t",
					"h,h,i,h,h,h,w,inh,w,inh", "-,-,-,-,-,-,-,-,-,-", 0, 2, 3,
					3);
			vgIP.generateImperative("h", "h,i,w,inh", "", 0, 2, 3, 3);
		}
	}

	//הקים-קום
	//id = 8067
	private void inflectPattern52() throws Exception {
		setGenInflection();
		vgIP.generateBareInfinitive("h", "i", 0, 1, 2, 3, "");
		vgIP.generateInfinitive("h", "i", 0, 1, 2, 3, "");
		if (root.charAt(2) == 'n') {
			vgIP.generatePast("h,h,h,h,h,h,h,h,h", "ti,t,t,-,h,w,tm,tn,w",
					"-,-,-,i,i,-,-,-,i", 0, 1, 2, 3);
			vgIP.generatePresent("m,m,m,m", "-,h,im,wt", "i,i,i,i", 0, 1, 2, 3);
			vgIP.generateFuture("a,t,t,i,t,n,t,t,i,t", "-,-,i,-,-,-,w,h,w,h",
					"i,i,i,i,i,i,i,-,i,-", 0, 1, 2, 3);
			vgIP.generateImperative("h", "-,i,w,h", "-,i,i,-", 0, 1, 2, 3);

		} else {

			vgIP.generatePast("h,h,h,h,h,h,h,h,h", "ti,t,t,-,h,nw,tm,tn,w",
					"-,-,-,i,i,-,-,-,i", 0, 1, 2, 3);
			vgIP.generatePresent("m,m,m,m", "-,h,im,wt", "i,i,i,i", 0, 1, 2, 3);
			vgIP.generateFuture("a,t,t,i,t,n,t,t,i,t", "-,-,i,-,-,-,w,nh,w,nh",
					"i,i,i,i,i,i,i,-,i,-", 0, 1, 2, 3);
			vgIP.generateImperative("h", "-,i,w,nh", "-,i,i,-", 0, 1, 2, 3);
		}
	}

	//הסב-סבב
	//id = 10616
	private void inflectPattern53() throws Exception {
		setGenInflection();
		vgIP.generateBareInfinitive("h", "", 0, 2, 3, 3, "");
		vgIP.generateInfinitive("h", "", 0, 2, 3, 3, "");
		vgIP.generatePast("h,h,h,h,h,h,h,h,h", "ti,t,t,-,h,nw,tm,tn,w", "", 0,
				1, 2, 3);
		vgIP.generatePresent("m,m,m,m", "-,h,im,wt", "", 0, 1, 2, 3);
		vgIP.generateFuture("a,t,t,i,t,n,t,t,i,t", "-,-,i,-,-,-,w,nh,w,nh", "",
				0, 1, 2, 3);
		vgIP.generateImperative("h", "-,i,w,nh", "", 0, 1, 2, 3);
	}

	/////////////////////////////////////////////////////////////////////////
	///Huf'al
	/////////////////////////////////////////////////////////////////////////

	//הוזכר-זכר
	//id = 2770
	private void inflectPattern54() throws Exception {
		setGenInflection();
		int len = root.length();
		if (root.charAt(0) == 'i' && root.charAt(2) == 't') {
			vgIP.generatePast("hw,hw,hw,hw,hw,hw,hw,hw,hw",
					"i,-,-,-,h,nw,m,n,w", "", 1, 3, 3, 3);
			vgIP.generatePresent("mw,mw,mw,mw", "-,t,im,wt", "", 1, 2, 2, 3);
			vgIP.generateFuture("aw,tw,tw,iw,tw,nw,tw,tw,iw,tw",
					"-,-,i,-,-,-,w,nh,w,nh", "", 1, 2, 2, 3);
		} else if (root.charAt(0) == 'i' && root.charAt(2) != 't') {
			vgIP.generatePast("hw,hw,hw,hw,hw,hw,hw,hw,hw",
					"ti,t,t,-,h,nw,tm,tn,w", "", 1, 3, 3, 3);
			vgIP.generatePresent("mw,mw,mw,mw", "-,t,im,wt", "", 1, 2, 2, 3);
			vgIP.generateFuture("aw,tw,tw,iw,tw,nw,tw,tw,iw,tw",
					"-,-,i,-,-,-,w,nh,w,nh", "", 1, 2, 2, 3);

		} else if (root.charAt(2) == 't') {
			vgIP.generatePast("hw,hw,hw,hw,hw,hw,hw,hw,hw",
					"i,-,-,-,h,nw,m,n,w", "", 0, 3, 3, 3);
			vgIP.generatePresent("mw,mw,mw,mw", "-,t,im,wt", "", 0, 2, 2, 3);
			vgIP.generateFuture("aw,tw,tw,iw,tw,nw,tw,tw,iw,tw",
					"-,-,i,-,-,-,w,nh,w,nh", "", 0, 2, 2, 3);
		} else if (root.charAt(0) == 'n' && root.charAt(2) == 'h') {
			vgIP.generatePast("hw,hw,hw,hw,hw,hw,hw,hw,hw",
					"iti,it,it,h,th,inw,itm,itn,w", "", 1, 2, 2, 2);
			vgIP.generatePresent("mw,mw,mw,mw", "h,it,im,wt", "", 1, 2, 2, 2);
			vgIP.generateFuture("aw,tw,tw,iw,tw,nw,tw,tw,iw,tw",
					"h,h,i,h,h,h,w,inh,w,inh", "", 1, 2, 2, 2);
		} else if (root.charAt(0) == 'n') {
			vgIP.generatePast("hw,hw,hw,hw,hw,hw,hw,hw,hw",
					"ti,t,t,-,h,nw,tm,tn,w", "", 1, 3, 3, 3);
			vgIP.generatePresent("mw,mw,mw,mw", "-,t,im,wt", "", 1, 2, 2, 3);
			vgIP.generateFuture("aw,tw,tw,iw,tw,nw,tw,tw,iw,tw",
					"-,-,i,-,-,-,w,nh,w,nh", "", 1, 2, 2, 3);
		} else {
			vgIP.generatePast("hw,hw,hw,hw,hw,hw,hw,hw,hw",
					"ti,t,t,-,h,nw,tm,tn,w", "", 0, len, len, len);
			vgIP.generatePresent("mw,mw,mw,mw", "-,t,im,wt", "", 0, len - 1,
					len - 1, len);
			vgIP.generateFuture("aw,tw,tw,iw,tw,nw,tw,tw,iw,tw",
					"-,-,i,-,-,-,w,nh,w,nh", "", 0, len, len, len);
		}
	}

	//הונחל-נחל
	//id = 7328
	private void inflectPattern55() throws Exception {
		setGenInflection();
		vgIP.generatePast("hw,hw,hw,hw,hw,hw,hw,hw,hw",
				"ti,t,t,-,h,nw,tm,tn,w", "", 0, 2, 2, 3);
		vgIP.generatePresent("mw,mw,mw,mw", "-,t,im,wt", "", 0, 2, 2, 3);
		vgIP.generateFuture("aw,tw,tw,iw,tw,nw,tw,tw,iw,tw",
				"-,-,i,-,-,-,w,nh,w,nh", "", 0, 2, 2, 3);
	}

	//הוגלה-גלה
	//id = 4251
	private void inflectPattern56() throws Exception {
		setGenInflection();
		if (root.charAt(1) == 'w') {
			vgIP.generatePast("hw,hw,hw,hw,hw,hw,hw,hw,hw",
					"ti,t,t,h,th,nw,tm,tn,w", "wi,wi,wi,w,w,wi,wi,wi,-", 0, 2,
					3, 3);
			vgIP.generatePresent("mw,mw,mw,mw", "wh,wh,wim,wt", "", 0, 2, 3, 3);
			vgIP.generateFuture("aw,tw,tw,iw,tw,nw,tw,tw,iw,tw",
					"wh,wh,wi,wh,wh,wh,w,nh,w,nh", "-,-,-,-,-,-,-,wi,-,wi", 0,
					2, 3, 3);
		} else {
			vgIP.generatePast("hw,hw,hw,hw,hw,hw,hw,hw,hw",
					"ti,t,t,h,th,nw,tm,tn,w", "i,i,i,-,-,i,i,i,-", 0, 2, 3, 3);
			vgIP.generatePresent("mw,mw,mw,mw", "h,h,im,wt", "", 0, 2, 3, 3);
			vgIP.generateFuture("aw,tw,tw,iw,tw,nw,tw,tw,iw,tw",
					"h,h,i,h,h,h,w,nh,w,nh", "-,-,-,-,-,-,-,i,-,i", 0, 2, 3, 3);
		}
	}

	//הוקם-קום
	//id = 12844
	private void inflectPattern57() throws Exception {
		setGenInflection();
		if (root.charAt(2) == 'n') {
			vgIP.generatePast("hw,hw,hw,hw,hw,hw,hw,hw,hw",
					"ti,t,t,-,h,w,tm,tn,w", "", 0, 1, 2, 3);
			vgIP.generatePresent("mw,mw,mw,mw", "-,t,im,wt", "", 0, 1, 2, 3);
			vgIP.generateFuture("aw,tw,tw,iw,tw,nw,tw,tw,iw,tw",
					"-,-,i,-,-,-,w,h,w,h", "", 0, 1, 2, 3);
		} else {
			vgIP.generatePast("hw,hw,hw,hw,hw,hw,hw,hw,hw",
					"ti,t,t,-,h,nw,tm,tn,w", "", 0, 1, 2, 3);
			vgIP.generatePresent("mw,mw,mw,mw", "-,t,im,wt", "", 0, 1, 2, 3);
			vgIP.generateFuture("aw,tw,tw,iw,tw,nw,tw,tw,iw,tw",
					"-,-,i,-,-,-,w,nh,w,nh", "", 0, 1, 2, 3);
		}
	}

	/////////////////////////////////////////////////////////////////////////
	///Nif'al
	/////////////////////////////////////////////////////////////////////////

	//נשמר-שמר
	//id = 17811
	//בגזרה זו יש בעייה עם שורשים עם פ"י לפעמים יש להחליף לו' בנטייה ולפעמים לא
	// ואין לי דרך לדעת
	//אני תמיד יהפוך ל--ו במקרים שלא נכון יש לייצר יוצאי דופן
	private void inflectPattern58() throws Exception {
		setGenInflection();
		if (root.charAt(0) == 'i') {
			vgIP.generateBareInfinitive("hiww", "", 1, 2, 3, 3, String
					.valueOf(root.charAt(root.length() - 1)));
			vgIP.generateInfinitive("hiww", "", 1, 1, 3, 3, String.valueOf(root
					.charAt(root.length() - 1)));
			if (root.charAt(root.length() - 1) == 'n')
				vgIP.generatePast("nw,nw,nw,nw,nw,nw,nw,nw,nw",
						"ti,t,t,-,h,w,tm,tn,w", "", 1, 3, 3, 3);
			else if (root.charAt(root.length() - 1) == 't')
				vgIP.generatePast("nw,nw,nw,nw,nw,nw,nw,nw,nw",
						"i,-,-,-,h,nw,tm,n,w", "", 1, 3, 3, 3);
			else
				vgIP.generatePast("nw,nw,nw,nw,nw,nw,nw,nw,nw",
						"ti,t,t,-,h,nw,tm,tn,w", "", 1, 3, 3, 3);
			vgIP.generatePresent("nw,nw,nw,nw", "-,t,im,wt", "", 1, 1, 1, 3);

			if (root.charAt(root.length() - 1) == 'n')
				vgIP.generateFuture(
						"aww,tiww,tiww,iiww,tiww,niww,tiww,tiww,iiww,tiww",
						"-,-,i,-,-,-,w,h,w,h", "", 1, 2, 2, 3);
			else
				vgIP.generateFuture(
						"aww,tiww,tiww,iiww,tiww,niww,tiww,tiww,iiww,tiww",
						"-,-,i,-,-,-,w,nh,w,nh", "", 1, 2, 2, 3);
			if (root.charAt(root.length() - 1) == 'n')
				vgIP.generateImperative("hiww", "-,i,w,h", "", 1, 2, 2, 3);
			else
				vgIP.generateImperative("hiww", "-,i,w,nh", "", 1, 2, 2, 3);

			vgIP.setSpelling(STANDARD_SPELLING);
			//if we dont perform again setGenInflection - we lose the
			// exceptions
			// (remove/replace)
			setGenInflection();
			//עבור פ"י שנוטים עם י ולא עם ו' נוסיף את כל הנטייה כיוצאי דופן
			// יש למנוע עודף יצירה כי השורות הבאות נכונות רק לנוטים עם ו
			int newReplaceExceptionListSize = vgIP.replaceExceptionList.size();
			if (!(newReplaceExceptionListSize > 10)) {
				//ו בודדת באמצע מילה במקום 2 ווים
				vgIP.generateBareInfinitive("hiw", "", 1, 2, 3, 3, String
						.valueOf(root.charAt(root.length() - 1)));
				vgIP.generateInfinitive("hiw", "", 1, 1, 3, 3, String
						.valueOf(root.charAt(root.length() - 1)));
				vgIP.generateFuture("aw,tiw,tiw,iiw,tiw,niw,tiw,tiw,iiw,tiw",
						"-,-,i,-,-,-,w,nh,w,nh", "", 1, 2, 2, 3);
				vgIP.generateImperative("hiw", "-,i,w,nh", "", 1, 2, 2, 3);
			}
			//פ' הפועל וע' הפועל לא מיוחדים - אבל צריך לבדוק את ל' הפועל (מקרים
			// מיוחדים נ ו-ת
		} else {
			vgIP.generateBareInfinitive("hi", "", 0, 2, 3, 3, String
					.valueOf(root.charAt(root.length() - 1)));
			vgIP.generateInfinitive("hi", "", 0, 2, 3, 3, String.valueOf(root
					.charAt(root.length() - 1)));
			vgIP.setSpelling(IRREGULAR_SPELLING);
			if (!vgIP.bareInfinitiveException) {
				vgIP.generateBareInfinitive("h", "", 0, 2, 3, 3, String
						.valueOf(root.charAt(root.length() - 1)));
				vgIP.generateInfinitive("h", "", 0, 2, 3, 3, String
						.valueOf(root.charAt(root.length() - 1)));
				vgIP.setSpelling(originalSpelling);
			}

			if (root.charAt(root.length() - 1) == 'n')
				vgIP.generatePast("n,n,n,n,n,n,n,n,n", "ti,t,t,-,h,w,tm,tn,w",
						"", 0, 3, 3, 3);
			else if (root.charAt(root.length() - 1) == 't')
				vgIP.generatePast("n,n,n,n,n,n,n,n,n", "i,-,-,-,h,nw,m,n,w",
						"", 0, 3, 3, 3);
			else
				vgIP.generatePast("n,n,n,n,n,n,n,n,n", "ti,t,t,-,h,nw,tm,tn,w",
						"", 0, 3, 3, 3);
			vgIP.setSpelling(IRREGULAR_SPELLING);
			if (root.charAt(root.length() - 1) == 'n')
				vgIP.generatePast("ni,ni,ni,ni,ni,ni,ni,ni,ni",
						"ti,t,t,-,h,w,tm,tn,w", "", 0, 3, 3, 3);
			else if (root.charAt(root.length() - 1) == 't')
				vgIP.generatePast("ni,ni,ni,ni,ni,ni,ni,ni,ni",
						"i,-,-,-,h,nw,m,n,w", "", 0, 3, 3, 3);
			else
				vgIP.generatePast("ni,ni,ni,ni,ni,ni,ni,ni,ni",
						"ti,t,t,-,h,nw,tm,tn,w", "", 0, 3, 3, 3);
			vgIP.generatePresent("ni,ni,ni,ni", "-,t,im,wt", "", 0, 1, 1, 3);

			vgIP.setSpelling(originalSpelling);

			vgIP.generatePresent("n,n,n,n", "-,t,im,wt", "", 0, 1, 1, 3);

			if (root.charAt(root.length() - 1) == 'n') {
				vgIP.generateFuture("a,ti,ti,ii,ti,ni,ti,ti,ii,ti",
						"-,-,i,-,-,-,w,h,w,h", "", 0, 2, 2, 3);
			} else {
				vgIP.generateFuture("a,ti,ti,ii,ti,ni,ti,ti,ii,ti",
						"-,-,i,-,-,-,w,nh,w,nh", "", 0, 2, 2, 3);
			}

			if (root.charAt(root.length() - 1) == 'n')
				vgIP.generateImperative("hi", "-,i,w,h", "", 0, 2, 2, 3);
			else
				vgIP.generateImperative("hi", "-,i,w,nh", "", 0, 2, 2, 3);
		}

	}

	//ניגף-נגף
	//id = 13137
	private void inflectPattern59() throws Exception {
		setGenInflection();
		if (root.endsWith("t")) {
			vgIP.generateBareInfinitive("h", "", 0, 2, 2, 3, "");
			vgIP.generateInfinitive("h", "", 0, 2, 2, 3, "");
			vgIP.setSpelling(IRREGULAR_SPELLING);
			vgIP.generateBareInfinitive("hi", "", 0, 2, 2, 3, "");
			vgIP.generateInfinitive("hi", "", 0, 2, 2, 3, "");

			vgIP.setSpelling(originalSpelling);
			vgIP.generatePast("", "i,-,-,-,h,nw,m,n,w", "", 0, 3, 3, 3);
			vgIP.setSpelling(IRREGULAR_SPELLING);
			vgIP.generatePast("", "i,-,-,-,h,nw,m,n,w", "i,i,i,i,i,i,i,i,i", 0,
					1, 1, 3);

			vgIP.setSpelling(originalSpelling);
			vgIP.generatePresent("", "-,t,im,wt", "", 0, 1, 1, 3);
			vgIP.setSpelling(IRREGULAR_SPELLING);
			vgIP.generatePresent("", "-,t,im,wt", "i,i,i,i", 0, 1, 1, 3);

			vgIP.setSpelling(originalSpelling);
			vgIP.generateFuture("a,ti,ti,ii,ti,ni,ti,ti,ii,ti",
					"-,-,i,-,-,-,w,nh,w,nh", "", 0, 2, 2, 3);
			vgIP.setSpelling(IRREGULAR_SPELLING);
			vgIP.generateFuture("a,ti,ti,ii,ti,ni,ti,ti,ii,ti",
					"-,-,i,-,-,-,w,nh,w,nh", "", 0, 2, 2, 3);

			vgIP.setSpelling(originalSpelling);
			vgIP.generateImperative("h", "-,i,w,nh", "", 0, 2, 2, 3);
			vgIP.setSpelling(IRREGULAR_SPELLING);
			vgIP.generateImperative("hi", "-,i,w,nh", "", 0, 2, 2, 3);
			vgIP.setSpelling(originalSpelling);

		} else if (root.endsWith("h")) {
			vgIP.generateBareInfinitive("hi", "", 0, 1, 1, 2, "wt");
			vgIP.generateInfinitive("hi", "", 0, 2, 2, 3, "");
			vgIP.generatePast("", "iti,it,it,h,th,inw,itm,itn,w",
					"i,i,i,i,i,i,i,i,i", 0, 1, 1, 2);
			vgIP.generatePresent("", "h,it,im,wt", "i,i,i,i", 0, 1, 1, 2);
			vgIP.generateFuture("a,ti,ti,ii,ti,ni,ti,ti,ii,ti",
					"h,h,i,h,h,h,w,inh,w,inh", "", 0, 1, 1, 2);
			vgIP.generateImperative("hi", "h,i,w,inh", "", 0, 1, 1, 2);

		} else {
			vgIP.generateBareInfinitive("hi", "", 0, 2, 2, 3, "");
			vgIP.generateInfinitive("hi", "", 0, 2, 2, 3, "");
			//נוצרות כניסות מיותרות במקרה של יוצא דופן לשם פועל למשל ניגש
			//vgIP.setSpelling(STANDARD_SPELLING);
			//vgIP.generateBareInfinitive("h", "", 0, 2, 2, 3, "");
			//vgIP.generateInfinitive("h", "", 0, 2, 2, 3, "");

			vgIP.generatePast("", "ti,t,t,-,h,nw,tm,tn,w", "i,i,i,i,i,i,i,i,i",
					0, 1, 1, 3);
			vgIP.setSpelling(IRREGULAR_SPELLING);
			vgIP.generatePast("", "ti,t,t,-,h,nw,tm,tn,w", "", 0, 3, 3, 3);
			vgIP.setSpelling(originalSpelling);

			vgIP.generatePresent("", "-,t,im,wt", "i,i,i,i", 0, 1, 1, 3);
			vgIP.setSpelling(IRREGULAR_SPELLING);
			vgIP.generatePresent("", "-,t,im,wt", "", 0, 1, 1, 3);
			vgIP.setSpelling(originalSpelling);

			vgIP.generateFuture("a,ti,ti,ii,ti,ni,ti,ti,ii,ti",
					"-,-,i,-,-,-,w,nh,w,nh", "", 0, 2, 2, 3);

			vgIP.generateImperative("hi", "-,i,w,nh", "", 0, 2, 2, 3);
			vgIP.setSpelling(IRREGULAR_SPELLING);
			vgIP.generateImperative("h", "-,i,w,nh", "", 0, 2, 2, 3);
		}
	}

	//נגלה-גלה
	//id = 4052
	private void inflectPattern60() throws Exception {
		setGenInflection();
		if (root.charAt(1) == 'w') {
			vgIP.generateBareInfinitive("hi", "w", 0, 2, 3, 3, "t");
			vgIP.generateInfinitive("hi", "w", 0, 2, 3, 3, "t");
			vgIP.generatePast("n,n,n,n,n,n,n,n,n", "ti,t,t,h,th,nw,tm,tn,w",
					"wi,wi,wi,w,w,wi,wi,wi,-", 0, 2, 3, 3);
			vgIP.generatePresent("n,n,n,n", "wh,wit,wim,wt", "", 0, 1, 1, 2);
			vgIP.generateFuture("a,ti,ti,ii,ti,ni,ti,ti,ii,ti",
					"wh,wh,wi,wh,wh,wh,w,winh,w,winh", "", 0, 2, 3, 3);
			vgIP.generateImperative("h", "wh,wi,w,winh", "-,-,-,-", 0, 2, 3, 3);

			vgIP.setSpelling(IRREGULAR_SPELLING);
			vgIP.generatePast("n,n,n,n,n,n,n,n,n", "ti,t,t,h,th,nw,tm,tn,w",
					"i,i,i,-,-,i,i,i,-", 0, 2, 3, 3);
			vgIP.generatePresent("n,n,n,n", "h,it,im,wt", "", 0, 1, 1, 2);
			vgIP.generateFuture("a,ti,ti,ii,ti,ni,ti,ti,ii,ti",
					"h,h,i,h,h,h,w,inh,w,inh", "", 0, 2, 3, 3);
			vgIP.generateImperative("hi", "h,i,w,inh", "-,-,-,-", 0, 2, 3, 3);

		} else {
			vgIP.generateBareInfinitive("hi", "w", 0, 2, 3, 3, "t");
			vgIP.generateInfinitive("hi", "w", 0, 2, 3, 3, "t");
			vgIP.setSpelling(IRREGULAR_SPELLING);
			vgIP.generateBareInfinitive("h", "w", 0, 2, 3, 3, "t");
			vgIP.generateInfinitive("h", "w", 0, 2, 3, 3, "t");

			vgIP.setSpelling(originalSpelling);
			vgIP.generatePast("n,n,n,n,n,n,n,n,n", "ti,t,t,h,th,nw,tm,tn,w",
					"i,i,i,-,-,i,i,i,-", 0, 2, 3, 3);
			vgIP.generatePresent("n,n,n,n", "h,it,im,wt", "", 0, 1, 1, 2);
			vgIP.generateFuture("ai,ti,ti,ii,ti,ni,ti,ti,ii,ti",
					"h,h,i,h,h,h,w,inh,w,inh", "", 0, 2, 3, 3);
			vgIP.generateImperative("hi", "h,i,w,inh", "-,-,-,-", 0, 2, 3, 3);
		}

	}

	//נפעל נחי ע"ו ע"י
	//נסוג
	private void inflectPattern61() throws Exception {
		setGenInflection();
		String pastPrefix = "";
		String presentPrefix = "";

		vgIP.generateBareInfinitive("hi", "w", 0, 1, 2, 3, "");
		//			ברב מילים להידון ואילו אצל שלמה להדון
		vgIP.generateInfinitive("hi", "w", 0, 1, 2, 3, "");

		//			if (transliterated.charAt(1) != 'i') {
		vgIP.generatePast("n,n,n,n,n,n,n,n,n", "ti,t,t,-,h,nw,tm,tn,w",
				"w,w,w,-,-,w,w,w,-", 0, 3, 3, 3);

		vgIP.setSpelling(IRREGULAR_SPELLING);
		if (root.charAt(2) == 'n') {
			vgIP.generatePast("n,n,n,n,n,n,n,n,n", "ti,t,t,-,h,w,tm,tn,w",
					"-,-,-,-,-,-,-,-,-", 0, 3, 3, 3);
		} else {

			vgIP.generatePast("n,n,n,n,n,n,n,n,n", "ti,t,t,-,h,nw,tm,tn,w",
					"-,-,-,-,-,-,-,-,-", 0, 3, 3, 3);
		}

		vgIP.setSpelling(originalSpelling);

		vgIP.generatePresent("n,n,n,n", "-,h,im,wt", "", 0, 3, 3, 3);

		if (root.charAt(2) == 'n') {
			vgIP.generateFuture("a,ti,ti,ii,ti,ni,ti,ti,ii,ti",
					"-,-,i,-,-,-,w,h,w,h", "", 0, 3, 3, 3);
			vgIP.generateImperative("hi", "-,i,w,h", "", 0, 3, 3, 3);
		} else {
			vgIP.generateFuture("a,ti,ti,ii,ti,ni,ti,ti,ii,ti",
					"-,-,i,-,-,-,w,nh,w,nh", "", 0, 3, 3, 3);
			vgIP.generateImperative("hi", "-,i,w,nh", "", 0, 3, 3, 3);
		}

	}

	//		נפעל נחי ע"ו ע"י
	//נצוד
	private void inflectPattern62() throws Exception {
		setGenInflection();
		String pastPrefix = "";
		String presentPrefix = "";

		vgIP.generateBareInfinitive("hi", "w", 0, 1, 2, 3, "");

		vgIP.generateInfinitive("hi", "w", 0, 1, 2, 3, "");

		if (root.charAt(2) == 'n') {
			vgIP.generatePast("ni,ni,ni,ni,ni,ni,ni,ni,ni",
					"ti,t,t,-,h,w,tm,tn,w", "w,w,w,w,w,w,w,w,w", 0, 1, 2, 3);
		} else if (root.charAt(2) == 't') {
			vgIP.generatePast("ni,ni,ni,ni,ni,ni,ni,ni,ni",
					"i,-,-,-,h,nw,m,n,w", "w,w,w,w,w,w,w,w,w", 0, 1, 2, 3);
		} else {
			vgIP.generatePast("ni,ni,ni,ni,ni,ni,ni,ni,ni",
					"ti,t,t,-,h,nw,tm,tn,w", "w,w,w,w,w,w,w,w,w", 0, 1, 2, 3);
		}

		vgIP.generatePresent("ni,ni,ni,ni", "-,h,im,wt", "", 0, 1, 1, 3);

		if (root.charAt(2) == 'n') {
			vgIP.generateFuture("a,ti,ti,ii,ti,ni,ti,ti,ii,ti",
					"-,-,i,-,-,-,w,h,w,h", "", 0, 3, 3, 3);
			vgIP.generateImperative("hi", "-,i,w,h", "", 0, 3, 3, 3);
		} else {
			vgIP.generateFuture("a,ti,ti,ii,ti,ni,ti,ti,ii,ti",
					"-,-,i,-,-,-,w,nh,w,nh", "", 0, 3, 3, 3);
			vgIP.generateImperative("hi", "-,i,w,nh", "", 0, 3, 3, 3);
		}
	}

	private void inflectPattern63() throws Exception {
		setGenInflection();
		String htBase = "ht" + transliterated.substring(2);
		vgIP.setBaseTransliterated(htBase);
		vgIP.setBaseUndot(Translate.Eng2Heb(htBase));
		String newDottedItem = dottedLexiconItem.replaceFirst("נִ", "הִ");
		vgIP.setDottedLexiconItem(newDottedItem);
		if (transliterated.endsWith("h")) {
			String baseForm = transliterated.substring(0, transliterated
					.length() - 1);
			vgIP.generatePastFromBaseForm(baseForm,
					"iti,it,it,h,th,inw,itm,itn,w");
		} else
			vgIP.generatePastFromBaseForm(transliterated,
					"ti,t,t,-,h,nw,tm,tn,w");
	}

	private void AddException() throws Exception {
		super.getException("add");
		if (addExceptionList.size() > 0) {
			vgIP.AddException(addExceptionList);
		}
	}

	protected void inflect() throws Exception 
	{

		analyseVerb();
		getException("replace");
		getException("remove");

		switch (intInflectedPattern) 
		{
		case 1:
			binyan = "Pa\\'al";
			inflectPattern1();
			break;
		case 2:
			binyan = "Pa\\'al";
			inflectPattern2();
			break;
		case 3:
			binyan = "Pa\\'al";
			inflectPattern3();
			break;
		case 4:
			binyan = "Pa\\'al";
			inflectPattern4();
			break;
		case 5:
			binyan = "Pa\\'al";
			inflectPattern5();
			break;
		case 6:
			binyan = "Pa\\'al";
			inflectPattern6();
			break;
		case 7:
			binyan = "Pa\\'al";
			inflectPattern7();
			break;
		case 8:
			binyan = "Pa\\'al";
			inflectPattern8();
			break;
		case 9:
			binyan = "Pa\\'al";
			inflectPattern9();
			break;
		case 10:
			binyan = "Pa\\'al";
			inflectPattern10();
			break;
		case 11:
			binyan = "Pa\\'al";
			inflectPattern11();
			break;
		case 12:
			binyan = "Pa\\'al";
			inflectPattern12();
			break;
		case 13:
			binyan = "Pa\\'al";
			inflectPattern13();
			break;
		case 14:
			binyan = "Pa\\'al";
			inflectPattern14();
			break;
		case 15:
			binyan = "Pa\\'al";
			inflectPattern15();
			break;
		case 16:
			binyan = "Pa\\'al";
			inflectPattern16();
			break;
		case 17:
			binyan = "Pa\\'al";
			inflectPattern17();
			break;
		case 18:
			binyan = "Pa\\'al";
			inflectPattern18();
			break;
		case 19:
			binyan = "Pa\\'al";
			inflectPattern19();
			break;
		case 20:
			binyan = "Pa\\'al";
			inflectPattern20();
			break;
		case 21:
			binyan = "Pa\\'al";
			inflectPattern21();
			break;
		case 22:
			binyan = "Pa\\'al";
			inflectPattern22();
			//inflectPattern1();
			break;
		case 23:
			binyan = "Pi\\'el";
			inflectPattern23();
			break;
		case 25:
			binyan = "Pi\\'el";
			inflectPattern25();
			break;
		case 26:
			binyan = "Pi\\'el";
			inflectPattern26();
			break;
		case 27:
			binyan = "Pi\\'el";
			inflectPattern27();
			break;
		case 28:
			binyan = "Pi\\'el";
			inflectPattern28();
			break;
		case 29:
			binyan = "Pi\\'el";
			inflectPattern29();
			break;
		case 30:
			binyan = "Pi\\'el";
			inflectPattern30();
			break;
		case 31:
			binyan = "Pi\\'el";
			inflectPattern31();
			break;
		case 32:
			binyan = "Pi\\'el";
			inflectPattern32();
			break;
		case 33:
			binyan = "Pi\\'el";
			inflectPattern33();
			break;
		case 34:
			binyan = "Pi\\'el";
			inflectPattern34();
			break;
		case 35:
			binyan = "Pi\\'el";
			inflectPattern35();
			break;
		case 36:
			binyan = "Pu\\'al";
			inflectPattern36();
			break;
		case 37:
			binyan = "Pu\\'al";
			inflectPattern37();
			break;
		case 38:
			binyan = "Pu\\'al";
			inflectPattern38();
			break;
		case 39:
			binyan = "Pu\\'al";
			inflectPattern39();
			break;
		case 40:
			binyan = "Pu\\'al";
			inflectPattern39();
			break;
		case 41:
			binyan = "Pu\\'al";
			inflectPattern39();
			break;
		case 42:
			binyan = "Hitpa\\'el";
			inflectPattern42();
			break;
		case 43:
			binyan = "Hitpa\\'el";
			inflectPattern43();
			break;
		case 44:
			binyan = "Hitpa\\'el";
			inflectPattern44();
			break;
		case 45:
			binyan = "Hitpa\\'el";
			inflectPattern45();
			break;
		case 46:
			binyan = "Hitpa\\'el";
			inflectPattern46();
			break;
		case 47:
			binyan = "Hitpa\\'el";
			inflectPattern47();
			break;
		case 48:
			binyan = "Hif\\'il";
			inflectPattern48();
			break;
		case 49:
			binyan = "Hif\\'il";
			inflectPattern49();
			break;
		case 50:
			binyan = "Hif\\'il";
			inflectPattern50();
			break;
		case 51:
			binyan = "Hif\\'il";
			inflectPattern51();
			break;
		case 52:
			binyan = "Hif\\'il";
			inflectPattern52();
			break;
		case 53:
			binyan = "Hif\\'il";
			inflectPattern53();
			break;
		case 54:
			binyan = "Huf\\'al";
			inflectPattern54();
			break;
		case 55:
			binyan = "Huf\\'al";
			inflectPattern55();
			break;
		case 56:
			binyan = "Huf\\'al";
			inflectPattern56();
			break;
		case 57:
			binyan = "Huf\\'al";
			inflectPattern57();
			break;
		case 58:
			binyan = "Nif\\'al";
			inflectPattern58();
			break;
		case 59:
			binyan = "Nif\\'al";
			inflectPattern59();
			break;
		case 60:
			binyan = "Nif\\'al";
			inflectPattern60();
			break;
		case 61:
			binyan = "Nif\\'al";
			inflectPattern61();
			break;
		case 62:
			binyan = "Nif\\'al";
			inflectPattern62();
			break;
		case 63:
			binyan = "Hitpa\\'el";
			inflectPattern63();
			break;
		default:
			//System.out.println("ilegal inflectedPattern -" + inflectedPattern);
			break;
		}
		AddException(); // handle verb exception type action = add
		//System.out.println("Done Inflecting Verb");
	}

	public static void main(String[] args) {
	}
}
