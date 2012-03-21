package lexicon.contents.types;
import java.sql.ResultSet;
import java.sql.SQLException;

import lexicon.contents.Connected;


public class AbbreviationType extends Connected{
	/*
	 * defines an Abbreviation Type and the actions he can make
	 */
	
	protected int id =-1;  // id in the Abbreviation table
	protected int item_id = -1; // id in the item table
	protected String surface =""; // Abbreviation surface (undotted)
	protected String definiteness =""; // Abbreviation definiteness

//-----------------------------------------------------------------------------------------------------	
	public AbbreviationType()
	{
		
	}

//-----------------------------------------------------------------------------------------------------
	/*
	 * Load - load an Abbreviation from the abbreviation table
	 */
	public void Load(int _id)
	{
		id = _id;
		if (id > 0)
		{
			String query = "SELECT * from abbreviation WHERE id = "+ id ;
			ResultSet rs = getData(query);   // get the query results
			if (rs != null)
			{
			try 
				{
				if (rs.next())  
					{
						item_id = rs.getInt("item_id");    // get item id
						surface = rs.getString("surface");  // get surface
						definiteness = rs.getString("definiteness");
					
					}
				} 
			catch (SQLException e) 
				{
					e.printStackTrace();
				}
			}
			releaseConnection();
		}
	}

//-----------------------------------------------------------------------------------------------------
	/*
	 * Add - adds an Abbreviation to the table
	 */
	public int Add()
	{
		if (item_id < 0) // if not set
		{
			System.out.println("Adding Abbreviation failed - there is no item id");
			return -1;
		}
		String query = "INSERT into abbreviation VALUES (null,"+item_id+",'"+surface+"','"+definiteness+"')";	
		int result = execute(query);
		return result;
	}
	
//-----------------------------------------------------------------------------------------------------	
	/*
	 * Delete - delete Abbreviation from the table
	 */
	public void Delete()
	{
		
	}
//-----------------------------------------------------------------------------------------------------		
	public int getId()
	{
		return id;
	}
//-----------------------------------------------------------------------------------------------------	
	public int getItemId()
	{
		return item_id;
	}
//-----------------------------------------------------------------------------------------------------	
	public int SetItemId(int _item_id)
	{
		return item_id = _item_id;
	}
//-----------------------------------------------------------------------------------------------------	
	public String getSurface()
	{
		return surface;
	}
//-----------------------------------------------------------------------------------------------------	
	public void setSurface(String _surface)
	{
		surface = _surface;
	}
//-----------------------------------------------------------------------------------------------------	
	public String getDefiniteness()
	{
		return definiteness;
	}
//-----------------------------------------------------------------------------------------------------	
	public void setDefinitenessNone()
	{
		definiteness= "none";
	}
//-----------------------------------------------------------------------------------------------------
	public void setDefinitenessExternal()
	{
		definiteness= "external";
	}
//-----------------------------------------------------------------------------------------------------	
	public void setDefinitenessInternal()
	{
		definiteness= "internal";
	}
//-----------------------------------------------------------------------------------------------------
	public void setDefinitenessDual()
	{	// both internal and external
		definiteness= "dual";
	}

}
