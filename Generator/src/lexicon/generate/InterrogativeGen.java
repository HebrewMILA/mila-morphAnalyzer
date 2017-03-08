/*
 * Created on 19/09/2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package lexicon.generate;
import java.util.List;
import lexicon.contents.exception_types.InterrogativeExceptionType;
import lexicon.contents.types.ItemType;

/**
 * @author daliabo
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class InterrogativeGen extends ItemGen 
{
	
	public InterrogativeGen (ItemType item) 
	{
		super(item);
	}
	
	private void replaceException() 
	{
		String sql = buildSql("replace", "interrogative_exception_type");
		replaceExceptionList = handleException(sql);
	}

	
	protected boolean replaceExceptionExist() throws Exception 
	{
		boolean match = false;
		for (int i = 0; i < replaceExceptionList.size(); i++) 
		{
			InterrogativeExceptionType interrogativeExceptionType = new InterrogativeExceptionType();
			interrogativeExceptionType.open(((Integer) replaceExceptionList.get(i))
					.intValue());
				inflectedItem = interrogativeExceptionType.getTransliterated();
				surface = interrogativeExceptionType.getUndotted();
				spelling = interrogativeExceptionType.getSpelling();
				register = interrogativeExceptionType.getRegister();
				match = true;
				populateDatabase();
				break;	
		}
		return match;
	}

	protected void addException() throws Exception 
	{
		String sql = buildSql("add", "interrogative_exception_type");
		List addExceptionList = handleException(sql);
		if (addExceptionList.size() > 0) 
		{
			analyseExceptionList(addExceptionList);
		}
	}

	private void analyseExceptionList(List exceptionList) throws Exception 
	{
		for (int i = 0; i < exceptionList.size(); i++) 
		{
			InterrogativeExceptionType interrogativeExceptionType = new InterrogativeExceptionType();
			interrogativeExceptionType.open(((Integer) exceptionList.get(i)).intValue());
			inflectedItem = interrogativeExceptionType.getTransliterated();
			surface = interrogativeExceptionType.getUndotted();
			spelling = interrogativeExceptionType.getSpelling();
			register = interrogativeExceptionType.getRegister();
			PGN = interrogativeExceptionType.getPersonGenderNumber();
			/*if(!PGN.equals("unspecified"))
			{
				suffixFunction="pronomial";
			}*/
			populateDatabase();
		}
	}

	private void analyse() 
	{
		analyseItem();
		inflectedItem = transliterated;
		surface = undot;
		suffixFunction = "unspecified";
		PGN="unspecified";
		type = item.getInterrogative().getInterrogativeType();
	}

	public void generateInflects() throws Exception 
	{
		//There is no logic for remove functionality
		analyse();
		populateDatabase();
		replaceException();
		addException();
	}
}
