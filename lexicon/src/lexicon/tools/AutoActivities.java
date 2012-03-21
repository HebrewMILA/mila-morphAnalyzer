/*
 * Created on 22/06/2006
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package lexicon.tools;

import java.sql.ResultSet;
import java.sql.SQLException;

import lexicon.contents.Connected;
import lexicon.contents.Log;
import lexicon.contents.exception_types.CopulaExceptionType;
import lexicon.contents.exception_types.NounExceptionType;
import lexicon.contents.pos.MultiWordNounAdjectiveLexiconType;
import lexicon.contents.pos.MultiWordVerbPhraseLexiconType;
import lexicon.contents.types.*;


/**
 * @author daliabo
 * 
 * TODO To change the template for this generated type comment go to Window -
 * Preferences - Java - Code Style - Code Templates
 */
public class AutoActivities extends Connected 
{
	public void deleteTrasliteratedProblemEntries()
	{
		System.out.println("deleteTrasliteratedProblemEntries");
		String sqlSelect = "select * from item where item.undotted like '%+%'  ";
		System.out.println( sqlSelect);
		ResultSet rs = getData(sqlSelect);
		try {
			while (rs.next()) 
			{
				String id = rs.getString("id");
				System.out.println("id");
				Log log = new Log(1000005, Integer.parseInt(id), 0,
						"Auto Remove lexicon entries which are multy token words ");
				log.add();

			}
		} 
		catch (NumberFormatException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		catch (SQLException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
//		String sql = " delete  from propername where id in(select id from item where  undotted like '%+%')";
//		execute(sql);
//		sql = "delete  from noun where id in(select id from item where  undotted like '%+%')";
//		execute(sql);
//		sql = "delete  from item  where  item.transliterated like '%-%'";
//		execute(sql);
		System.out.println("finished");
		System.exit(1);		
	}
	
	public void deleteMakafEntries()
	{
		String sqlSelect = "select * from item where item.transliterated like '%-%' and undotted like '%-%' ";
		ResultSet rs = getData(sqlSelect);
		try 
		{
			while (rs.next()) 
			{
				String id = rs.getString("id");
				Log log = new Log(1000005, Integer.parseInt(id), 0,"Auto Remove lexicon entries with makaf");
				log.add();
			}
		} 
		catch (NumberFormatException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		catch (SQLException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String sql = "delete from propername where exists (select * from item where item.id=propername.id and item.pos='propername' and item.transliterated like '%-%' and item.undotted like '%-%')";
		execute(sql);
		sql = "delete  from numeral where exists (select * from item where item.id=numeral.id and item.pos='numeral' and item.transliterated like '%-%' and item.undotted like '%-%')";
		execute(sql);
		sql = "delete  from adverb where exists (select * from item where item.id=adverb.id and item.pos='adverb' and item.transliterated like '%-%' and item.undotted like '%-%')";
		execute(sql);
		sql = "delete  from noun where exists (select * from item where item.id=noun.id and item.pos='noun' and item.transliterated like '%-%' and item.undotted like '%-%')";
		execute(sql);
		sql = "delete  from conjunction where exists (select * from item where item.id=conjunction.id and item.pos='conjunction' and item.transliterated like '%-%' and item.undotted like '%-%')";
		execute(sql);
		sql = "delete  from preposition where exists (select * from item where item.id=preposition.id and item.pos='preposition' and item.transliterated like '%-%' and item.undotted like '%-%')";
		execute(sql);
		sql = "delete  from quantifier where exists (select * from item where item.id=quantifier.id and item.pos='quantifier' and item.transliterated like '%-%' and item.undotted like '%-%')";
		execute(sql);
		sql = "delete  from item  where  item.transliterated like '%-%' and item.undotted like '%-%'";
		execute(sql);
		
		System.out.println("finished");
		System.exit(1);		
	}
	
	public void MoveFromQuantifier() throws NumberFormatException, SQLException 
	{
		String sqlSelect = "select * from quantifier where type='numeral fractional'";
		ResultSet rs = getData(sqlSelect);
		while (rs.next()) 
		{
			String id = rs.getString("id");
			Log log = new Log(1000005, Integer.parseInt(id), 0,
					"Auto Remove numeral fractional type entries from quantifier");
			log.add();

		}
		String sql = "delete from quantifier where type = 'numeral fractional'";
		execute(sql);
		System.out.println("finished");
		System.exit(1);
	}

	public static void main(String[] args) 
	{
		AutoActivities a = new AutoActivities();
		//AbbreviationType abb = new AbbreviationType();
		ItemType item = new ItemType();
		
		try 
		{
			//item.open(30516);  // open black market
			//MultiWordNounAdjectiveLexiconType multiWordNounAdjective = new MultiWordNounAdjectiveLexiconType();
			//multiWordNounAdjective.open(30511);
			//multiWordNounAdjective.update();
			
			System.out.println("starting");
			
			/*CopulaExceptionType copulaException = new CopulaExceptionType();
			copulaException.setUndotted("עוצליגוצלי");
			copulaException.add(30534);*/
			
			//NounExceptionType nounException = new NounExceptionType();
			//nounException.setUndotted("עוצליגוצלי");
			//nounException.add(30695);
			
			
			/*MultiWordVerbPhraseLexiconType multiWordVerbPhrase = new MultiWordVerbPhraseLexiconType();
			String binyan = multiWordVerbPhrase.getBinyan();
			System.out.println(binyan);
			if (multiWordVerbPhrase.getBinyan().equals("yossi")) System.out.println("BYay"); 
			String type = multiWordVerbPhrase.getType();
			System.out.println(type);
			if (multiWordVerbPhrase.getType().equals("yossi")) System.out.println("Yay");*/ 
			//multiWordVerbPhrase.add(30516);
			
			/*abb.SetDefinitenessNone();
			abb.SetItemId(100);
			abb.SetSurface("LOL");
			abb.Add();*/
			//abb.Load(1);
			
			//a.MoveFromQuantifier();
			//a.deleteMakafEntries();
			//System.out.println("deleteTrasliteratedProblemEntries");
			//a.deleteTrasliteratedProblemEntries();
		} 
		catch (NumberFormatException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
