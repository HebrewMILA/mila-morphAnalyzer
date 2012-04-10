/*
 * Created on 19/09/2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package lexicon.generate;


import lexicon.contents.exception_types.ProperNameExceptionType;
import lexicon.contents.types.ItemType;
import lexicon.stringUtils.Translate;

/**
 * @author daliabo
 * 
 * TODO To change the template for this generated type comment go to Window -
 * Preferences - Java - Code Style - Code Templates
 */
public class ProperNameGen extends ItemGen 
{
	public ProperNameGen(ItemType item) 
	{
		super(item);
	}

	private void analyse() 
	{
		analyseItem();

		//fix a bug in the lexicon for propername with period like אי.סי.אי
		//remove after fixing lexicon code
		//transliterated = Translate.Heb2Eng(undot);
		//transliterated = transliterated.replaceAll("&#39;", "'");
		//baseTransliteratedItem = transliterated;
		///////////////////////////////////////////////////////////
		gender = item.getProperName().getGender();
		number = item.getProperName().getNumber();
		type = item.getProperName().getType();
		definitness = item.getProperName().getDefiniteness();
		direction = item.getProperName().getDirection();
		surface = undot;
		inflectedItem = transliterated;
		suffixFunction = "unspecified";
		basePos = item.getPos().toLowerCase();
	}

	private void inflectAddExceptions() throws Exception 
	{
		super.getException("add");
		if (addExceptionList.size() > 0) 
		{
			analyseExceptionList();
		}
	}

	private void analyseExceptionList() throws Exception 
	{
		int size = addExceptionList.size();
		System.out.println("addExceptionList size" + size);
		for (int i = 0; i < size; i++) 
		{
			ProperNameExceptionType properNameExceptionType = new ProperNameExceptionType();
			properNameExceptionType.open(((Integer) addExceptionList.get(i)).intValue());
			inflectedItem = properNameExceptionType.getTransliterated();
			//inflectedItem = inflectedItem.replaceAll("&#39;", "'");
			System.out.println("inflectedItem=" + inflectedItem);
			System.out.println("i=" + i);
			surface = properNameExceptionType.getUndotted();
			//suffixGender = nounExceptionType.getGender();
			
			if (properNameExceptionType.getTransliterated().startsWith("w")
					&& !properNameExceptionType.getTransliterated().startsWith("ww")) 
			{
				inflectedItem = "w"+ properNameExceptionType.getTransliterated();
				//inflectedItem = inflectedItem.replaceAll("&#39;", "'");
				surface = "ו" + properNameExceptionType.getUndotted();
				addExceptionListHandling(properNameExceptionType);
			}
			else
				addExceptionListHandling(properNameExceptionType);
		}
	}

	private void addExceptionListHandling(ProperNameExceptionType properNameExceptionType) 
	{
		try 
		{
			gender = lexiconGender = properNameExceptionType.getGender();
			number = lexiconNumber = properNameExceptionType.getNumber();
			spelling = properNameExceptionType.getSpelling();
			register = properNameExceptionType.getRegister();
			type = properNameExceptionType.getType();
			definitness = properNameExceptionType.getDefiniteness();
			handleDefiniteness(inflectedItem);
		} 
		catch (Exception e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private void handleDefiniteness(String item)
	{
		try
		{
    		if (definitness.equals("required")) 
    		{
    			definitnessVal = "rt";
    			populateDatabase();
    			
    			definitnessVal = "tt";
    			inflectedItem = "h" + item;
    			surface = Translate.Eng2Heb(inflectedItem);
    			populateDatabase();
    			
    		} 
    		else if (definitness.equals("optional")) 
    		{
    			definitnessVal = "tf";
    			populateDatabase();
    			definitnessVal = "tt";
    			inflectedItem = "h" + item;
    			System.out.println();
    			System.out.println("inflectedItem =" + inflectedItem);
    			surface = Translate.Eng2Heb(inflectedItem);
    			System.out.println("surface =" + surface);
    			System.out.println();
    			populateDatabase();
    			//שמות פרטיים שאסור שיהיו מיודעים  כמו קולורדו
    		} 
    		else 
    		{
    			definitnessVal = "f";
    			populateDatabase();
    			
    			// this part was commented by me (yossi) because it created definiteness even when it was defined as prohibtied 1.12.11
    			/*definitnessVal = "rf";
    			inflectedItem = "h" + item;
    			surface = Translate.Eng2Heb(inflectedItem);
    			populateDatabase();*/
    		}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}

	public void inflect() throws Exception 
	{
		analyse();
		handleDefiniteness(inflectedItem);
		//טיפול בשמות שמתחילים ב-ו כמו וינה שיזהה גם בווינה
		if (transliterated.startsWith("w") && !transliterated.startsWith("ww")) 
		{
			inflectedItem = "w" + transliterated;
			surface = Translate.Eng2Heb(inflectedItem);
			handleDefiniteness(inflectedItem);
		}
		inflectAddExceptions();
	}
}
