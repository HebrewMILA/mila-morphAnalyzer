/*
 * Created on 14/11/2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */

package lexicon.generate;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import lexicon.contents.Connected;
import lexicon.contents.exception_types.MultiWordFrozenExceptionType;
import lexicon.contents.types.ItemType;
import lexicon.stringUtils.Translate;

/**
 * @author daliabo
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class MultiWordFrozenGen extends ItemGen 
{
	boolean definiteness;
	String mwPos="";
	String type="";
	String undottedLexiconItem="";
	boolean prefix=false;
	boolean acceptDefiniteness=false;
	
	public MultiWordFrozenGen(ItemType item) 
	{
		super(item);	
	}

/*
	public void generateExternalDefiniteness() throws Exception {
		String mwUndotted = " �" + undottedLexiconItem;
		String mwTransliterated = "h" + transliterated;				

		//definitnessVal = "true";		
		popualteMWE.popualteMWETables( mwTransliterated, mwUndotted, '1' , mwPos , dottedLexiconItem, id, type, spelling, register, prefix, definitnessVal);		

	}
*/
	private void analyse() 
	{
		analyseItem();
		mwPos = item.getMultiWordFrozen().getMwPos();
		undottedLexiconItem = undot;
		type= item.getMultiWordFrozen().getType();
		prefix=item.getMultiWordFrozen().isPrefix();
		definiteness  = item.getMultiWordFrozen().isDefiniteness();
		if(definiteness)
			definitnessVal = "true";
		else 
			definitnessVal = "false";
		
		acceptDefiniteness  = item.getMultiWordFrozen().isAcceptDefiniteness();
			
//		gender = item.getTitle().getGender();
//		number = item.getTitle().getNumber();
//		definiteness  = item.getTitle().isDefiniteness();
//		
//		System.out.println(definiteness);
//		if(definiteness)
//			definitnessVal = "tt";
//		else 
//			definitnessVal = "tf";
//		inflectedItem = transliterated;
	}

	public void inflect() throws Exception 
	{
	    System.out.println("MultiWordFrozenGen:inflect()");
		analyse();
		PopulateMWE popualteMWE = new PopulateMWE();
		popualteMWE.popualteMWETables( transliterated, undottedLexiconItem, '1' , mwPos , dottedLexiconItem, id, type, spelling, register, prefix, definitnessVal);
		
		if (acceptDefiniteness)
		{
			definitnessVal = "true";
			//construct = "unspecified";
			register = "informal";
			String mwUndotted = " ה" + undottedLexiconItem;
			String mwTransliterated = "h" + transliterated;				
			popualteMWE.popualteMWETables( mwTransliterated, mwUndotted, '1' , mwPos , dottedLexiconItem, id, type, spelling, register, prefix, definitnessVal);
			//generateExternalDefiniteness();
		}
		
		// GENERATE EXCEPTION
		// need to get a list of aid for this item
		// then iterate thrugh them and for each do an open
		Connected conn = new Connected();  // create a connected class in order to connect to the DB
		ResultSet rs = null;  // create a result set to get the query result
		int exception_count = 0;
		String sql = "SELECT count(*) FROM lexiconP.multiWordFrozen_exception_type where id=" + item.id; // i want to find out how many exception this item have
		System.out.println("MultiWordFrozenGen:inflect(): sql = " + sql);
		rs = conn.getData(sql); // do sql query
		if (rs != null)
		{
			if (rs.next())
			{
				System.out.print("EXCEPTION RECORD COUNT: ");
				System.out.println(rs.getInt("count(*)"));
				exception_count = rs.getInt("count(*)");  // now i have the exception count
				if (exception_count != 0)  // if we have one or more exceptions
				{
					MultiWordFrozenExceptionType multi_word_frozen_exception[] = new MultiWordFrozenExceptionType[exception_count];  // creating array of exception one for each exception
					for (int i=0; i < exception_count; i++)
					{   // and now create the array objects
						multi_word_frozen_exception[i] = new MultiWordFrozenExceptionType();
					}
					String sql2 = "SELECT aid FROM lexiconP.multiWordFrozen_exception_type where id=" + item.id; // i want to get the aid for exceptions this item have
					rs = conn.getData(sql2); // do sql query
					if (rs != null)
					{
						int index = 0;
						while (rs.next())
						{
							System.out.print("CREATING EXCEPTION ID: ");
							System.out.println(rs.getInt("aid")); 
							multi_word_frozen_exception[index].open(rs.getInt("aid")); // for each expcetion populate it with the proper data
							// we now have an array of multi word exception each with data for one exception
							// now we are going to add them into the inflections DB
							String _transliterated = multi_word_frozen_exception[index].getTransliterated();
							String _undotted = multi_word_frozen_exception[index].getUndotted();
							String _register = multi_word_frozen_exception[index].getRegister();
							String _dotted= multi_word_frozen_exception[index].getDotted();
							String _spelling= multi_word_frozen_exception[index].getSpelling();
							definitnessVal = "false";
							popualteMWE.popualteMWETablesOneWord( _transliterated, _undotted, '1' , mwPos , _dotted, id, type, _spelling, _register, prefix, definitnessVal);
	
							acceptDefiniteness  = multi_word_frozen_exception[index].isAcceptDefiniteness();
							if (acceptDefiniteness) // do we need to add definitness ?
							{
								definitnessVal = "true";
								register = "informal";
								String mwUndotted = " ה" + _undotted;
								String mwTransliterated = "h" + _transliterated;				
								popualteMWE.popualteMWETablesOneWord( mwTransliterated, mwUndotted, '1' , mwPos , _dotted, id, type, _spelling, _register, prefix, definitnessVal);
							}
							//popualteMWE.popualteMWETables( _transliterated, _undotted, '1' , mwPos , _dotted, id, type, _spelling, _register, prefix, definitnessVal);		
							index++;
						}
					}
					else // rs == null
					{
						System.out.println("content query failed");
					}
				}
			}
		}
		else // rs == null
		{
			System.out.println("count query failed");
		}
		Connected.releaseConnection();  // relase the conncection
		//multi_word_frozen_exception.open();
		//popualteMWE.popualteMWETables( "test", "test", '1' , "test" , "test", "test", "test", "test", "test", true, "test");		
	}

}
