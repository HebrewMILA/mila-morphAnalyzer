package gen;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

//import com.sun.java_cup.internal.Main;

public class TestGeneration 
{
    String dataBaseUrl = "jdbc:mariadb://yeda.cs.technion.ac.il:3306/";
//    String lexiconTestURL = dataBaseUrl + "lexiconTest";
    String lexiconPURL = dataBaseUrl + "lexiconP";

    String lexiconTestURL = dataBaseUrl + "lexiconTest";
	public void deleteInflections() 
	{
	    Connection connection = null;
        PreparedStatement statement = null;
		try 
		{
			Class.forName("org.mariadb.jdbc.Driver").newInstance();
			connection = DriverManager.getConnection(lexiconTestURL,"maital", "AnaXAd3Ke@aJ8F");
			statement = connection.prepareStatement("delete from inflections");
			statement.execute();
			connection.close();
		} 
		catch (Exception e) 
		{
			System.out.println(e.getMessage());
		}
	}
	//---------------------------------------------------------------------------------------------------------------------------
	public String DispatchAction(String action,InflectionEntry iEntry,String id)
	{
	    if (action != null)
	    {
	        if (action.equals("remove")) return DispatchRemove(iEntry);
	        else if (action.equals("restore")) return DispatchRestore(id);
	    }
	    return null;
	}
	//---------------------------------------------------------------------------------------------------------------------------
	public String DispatchRemove(InflectionEntry iEntry)
	{
	    String query = AddRemoveInflectedEntry(iEntry);
	    return query;
	}
	//---------------------------------------------------------------------------------------------------------------------------
	public String DispatchRestore(String id)
    {
        String query = RestoreRemoveInflectedEntry(id);
        return query;
    }
    //---------------------------------------------------------------------------------------------------------------------------
    public String RestoreRemoveInflectedEntry(String id)
    {
        Connection connection = null;
        Statement statement = null;
        String query = "empty";
        
        try
        {
            query = "delete FROM inflectionsEdit WHERE id='"+id+"'";
            Class.forName("org.mariadb.jdbc.Driver").newInstance();
            connection = DriverManager.getConnection(lexiconPURL,"lexiconUser", "!adgj?");
            statement = connection.createStatement();
            statement.execute(query);
            connection.close();
        } 
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return query;
    }
	//---------------------------------------------------------------------------------------------------------------------------
	
	/**
	 * this function adds an entry for the removedinflection table
	 * so that this entry wont be inflected anymore
	 * @param iEntry
	 */
	public String AddRemoveInflectedEntry(InflectionEntry iEntry)
	{
	    Connection connection = null;
        Statement statement = null;
        String query = "empty";
        
        try
        {
            query = "INSERT INTO inflectionsEdit "+ iEntry.GetInsertQueryDetails();
            Class.forName("org.mariadb.jdbc.Driver").newInstance();
            connection = DriverManager.getConnection(lexiconPURL,"lexiconUser", "!adgj?");
            statement = connection.createStatement();
            statement.execute(query);
            connection.close();
        } 
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return query;
	}
//---------------------------------------------------------------------------------------------------------------------------
	public String ShowRemoveExceptions(String lexiconId)
	{
	    String output = "";
	    ResultSet rs = null;
	    
	    try 
        {
            Connection connection = null;
            PreparedStatement statement = null;
            Class.forName("org.mariadb.jdbc.Driver").newInstance();
            connection = DriverManager.getConnection(lexiconTestURL,"maital", "AnaXAd3Ke@aJ8F");
            statement = connection.prepareStatement("SELECT * FROM inflections WHERE baseLexiconPointer= ?");
            statement.setObject(1, lexiconId);
            rs = statement.executeQuery();
            connection.close();
        }
        catch (Exception e) 
        {
            System.out.println(e.getMessage());
        }
	    
	    return output;
	}
//---------------------------------------------------------------------------------------------------------------------------
	public String GetFieldFromItemTable(String lexiconId,String fieldName) throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException
	{
	    Connection connection = null;
        PreparedStatement statement = null;
        ResultSet rs = null;

        Class.forName("org.mariadb.jdbc.Driver").newInstance();
        connection = DriverManager.getConnection(lexiconPURL,"lexiconUser", "!adgj?");
        statement = connection.prepareStatement("SELECT * FROM item WHERE id= ?");
        statement.setObject(1, lexiconId);
        rs = statement.executeQuery();
        rs.next();
        String field = rs.getString(fieldName);
        connection.close();
        return field;
	}
//---------------------------------------------------------------------------------------------------------------------------
	public String showInflections(String lexiconId) 
	{
		ResultSet rs = null;
		ResultSet rs2 = null;
		String output = "";
		String basePos= "";
		boolean showVerb = false;
		try 
		{
			Connection connection = null;
			PreparedStatement statement = null;

			Class.forName("org.mariadb.jdbc.Driver").newInstance();
			connection = DriverManager.getConnection(lexiconTestURL,"maital", "AnaXAd3Ke@aJ8F");
			statement = connection.prepareStatement("SELECT * FROM inflections WHERE baseLexiconPointer= ?");
			statement.setObject(1, lexiconId);
			rs = statement.executeQuery();
			if (rs.next())
			{
    			basePos = rs.getString("basePos");
    			if (basePos.equals("verb")) showVerb = true;
    			
    			String lexiconLink = "http://yeda.cs.technion.ac.il:8088/milaLexicon/jsp/general/DoItem.jsp?id="+lexiconId+"&search_id="+lexiconId+"&POS="+basePos;
    			output = "<form style='margin-left:auto;margin-right:auto;width:100px;'><input TYPE='button' VALUE='Back' onClick=\"window.location.href='"+lexiconLink+"'\"></form>";
    			output += "ID: "+lexiconId+"&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;POS: " + basePos  +
    					"&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;LEXICON ITEM: "+rs.getString("baseTransliteratedLItem")+ "</br>";
                rs.previous();
    
    			output += "<table align='center' cellpadding='0' cellspacing='0' width='85%' >" +
                "<tr><th>Inflection</th>" 
    			+ "<th>Trans</th>";
    			if (showVerb) output += "<th>pos</th>";
                //+"<th>lexicon id</th> <th>lexicon item</th>"
                output += "<th>Number</th>"
                + "<th>Gender</th>"
                + "<th>Person</th>"
                + "<th>PGN</th>"
                + "<th>SuffixFunc</th>"
                + "<th>Construct</th>";
    			if (showVerb)
    			{
    			    output+= "<th>binyan</th>"+ "<th>Root</th>" + "<th>tense</th>";
    			}
    			output += "<th>Definitness</th>"
                + "<th>Register</th>" 
                + "<th>Spelling</th>" 
                + "<th>Polarity</th>" 
                + "<th>Prefix</th>" 
                + "<th>Type</th>"
                +"<th>Action</th>"
                 + "</tr>";
    
    			//String decodedRoot = "";
    			while (rs.next())
    			{
    			    InflectionEntry iEntry = new InflectionEntry(rs);
    			    
    				//String surface = rs.getString("surface");
    				//String surface = iEntry.getSurface();
    				//String decodedSurface = URLDecoder.decode(surface, "UTF-8");
    				//String root = rs.getString("root");
    				/*String root = iEntry.getRoot();
    				if (!root.equals("") && root != null)
    					decodedRoot = URLDecoder.decode(root, "UTF-8");*/
    				//String tense = rs.getString("tense");
    				
    				output = output + "<tr>";
     
    				output += GetInflectionHtmlEntry(iEntry, showVerb);
    				
    						// now creating the remove button
    				output += "<td>"+ iEntry.GetFormButton("http://yeda.cs.technion.ac.il:8088/generatorViewer/testGeneration.jsp?lexiconId="+lexiconId,"remove")+"</td>";
    				output += "</tr>";						
    			}
    			output +="</table>";
    			rs.close();
    			
    			// now i want to put here the list of removed inflections.
    			connection = DriverManager.getConnection(lexiconPURL,"lexiconUser", "!adgj?");
    			statement = connection.prepareStatement("SELECT * FROM lexiconP.inflectionsEdit WHERE baseLexiconPointer= ?");
    			statement.setObject(1, lexiconId);
                output +="</br> List of removed inflections</br>";
                
                output += "<table align='center' cellpadding='0' cellspacing='0' width='85%' >" +
                "<tr><th>Inflection</th>" 
                + "<th>Trans</th>";
                if (showVerb) output += "<th>pos</th>";
                //+"<th>lexicon id</th> <th>lexicon item</th>"
                output += "<th>Number</th>"
                + "<th>Gender</th>"
                + "<th>Person</th>"
                + "<th>PGN</th>"
                + "<th>SuffixFunc</th>"
                + "<th>Construct</th>";
                if (showVerb)
                {
                    output+= "<th>binyan</th>"+ "<th>Root</th>" + "<th>tense</th>";
                }
                output += "<th>Definitness</th>"
                + "<th>Register</th>" 
                + "<th>Spelling</th>" 
                + "<th>Polarity</th>" 
                + "<th>Prefix</th>" 
                + "<th>Type</th>"
                +"<th>Action</th>"
                 + "</tr>";
                
                /*
                output +="<table align='center' cellpadding='0' cellspacing='0'  width='85%'>"
                        +"<th>Inflection</th>"
                		+"<th>Transliterated</th>"
                		+"<th>Register</th>"
                		+"<th>Gender</th>"
                		+"<th>Number</th>"
                		+"<th>Feminine</th>"
                		+"<th>Plural</th>"
                		+"<th>Possessive</th>"
                		+"<th>Construct</th>" 
                		+"<th>Action</th>";
                */
                rs2 = statement.executeQuery();
                
                while (rs2.next())
                {
                    InflectionEntry iEntry2 = new InflectionEntry(rs2);
                    //output += GetExceptionEntry(rs2);
                    output +="<tr>";
                    output += GetInflectionHtmlEntry(iEntry2,showVerb);
                    output += "<td>"+ iEntry2.GetFormButton("http://yeda.cs.technion.ac.il:8088/generatorViewer/testGeneration.jsp?lexiconId="+lexiconId,"restore")+"</td>";
                    
                    /*output += "<td>"+
                              "<form name='myform' action='http://yeda.cs.technion.ac.il:8088/generatorViewer/testGeneration.jsp?lexiconId="+lexiconId+"' method='POST'>" +
                              "<input type='hidden' name='Language' value='English'>" +
                              "<input type='Submit' value='restore'>"+
                              "</form>"
                              +"</td>";*/
                    output +="</tr>";
                }
                
                output +="</table>";
    			// get list of removed inflections
    			// add it to table
                rs2.close();
			}
			connection.close();
		} 
		catch (Exception e) 
		{
		    output +="</br>"+e.getMessage();
			System.out.println(e.getMessage());
		}	
		
		return output;
	}
	
	//-----------------------------------------------------------------------------------------------------------------------
    private String GetInflectionHtmlEntry(InflectionEntry iEntry, boolean showVerb) throws UnsupportedEncodingException
    {
        String output="";
   
        output +="<td id='inflectionCell'>" + URLDecoder.decode(iEntry.getSurface(), "UTF-8") + "</td>";                   
        output+="<td>" + iEntry.getTransliterated() + "</td>";                  
        if (showVerb) output+="<td>" + iEntry.getBasePos() + "</td>";
        output+="<td>" + ShortenUnspecifed(iEntry.getBaseNumber()) + "</td>";
        output+="<td>" + ShortenUnspecifed(iEntry.getBaseGender()) + "</td>";
        output+="<td>" + ShortenUnspecifed(iEntry.getBasePerson()) + "</td>";
        output+="<td>" + ShortenUnspecifed(iEntry.getPGN()) + "</td>";
        output+="<td>" + ShortenUnspecifed(iEntry.getSuffixFunction()) + "</td>";
        output+="<td>" + ShortenUnspecifed(iEntry.getSuffixStatus()) + "</td>";
        //output+="<td>" + iEntry.getSuffixFunction() + "</td>";
        if (showVerb)
        {
            output+="<td>" + iEntry.getBinyan() + "</td>";
            output+="<td>" + URLDecoder.decode(iEntry.getRoot(), "UTF-8") + "</td>";
            output+="<td>" + iEntry.getTense() + "</td>";
        }
        output+="<td>" + iEntry.getBaseDefinitness() + "</td>";
        output+="<td>" + iEntry.getRegister() + "</td>";
        output+="<td>" + iEntry.getSpelling() + "</td>";
        output+="<td>" + iEntry.getPolarity() + "</td>";
        output+="<td>" + ShortenUnspecifed(iEntry.getPrefix()) + "</td>";
        output+="<td>" + ShortenUnspecifed(iEntry.getType()) + "</td>";
        
        return output;
    }
    
	//-----------------------------------------------------------------------------------------------------------------------
	
	private String ShortenUnspecifed(String str)
	{
	    if (str.equals("unspecified")) return "-";
	    return str;
	}
	//--------------------------------------------------------------------------------------------------------------------------
		
	public static void main(String[] args)
    {
	    Connection connection = null;
        Statement statement = null;
        String query = "empty";
        String output = "";
        
        try
        {
            TestGeneration tg = new TestGeneration();
            tg.RestoreRemoveInflectedEntry("13");
            /*query = "INSERT INTO inflectionsEdit (baseLexiconPointer, transliterated, baseNumber, baseGender, basePerson, PGN, suffixFunction, suffixStatus, binyan, tense, baseDefinitness, register, spelling, polarity, prefix,type) VALUES('11926','mrb','singular','masculine','','u','u','true','','','tf','formal','standard','u','u','')";
            Class.forName("org.mariadb.jdbc.Driver").newInstance();
            connection = DriverManager.getConnection("jdbc:mysql://yeda.cs.technion.ac.il:3306/lexiconP","maital", "AnaXAd3Ke@aJ8F");
            statement = connection.createStatement();
            statement.execute(query);
            
            PreparedStatement pStatement;
            pStatement = connection.prepareStatement("SELECT * FROM lexiconP.inflectionsEdit WHERE baseLexiconPointer= ?");
            pStatement.setObject(1,"11926");
            ResultSet rs2 = pStatement.executeQuery();
            
            while (rs2.next())
            {
                output += tg.GetExceptionEntry1(rs2);    
            }
            System.out.println(output);    
            */
        } 
        catch (Exception e)
        {
            e.printStackTrace();
        }
    } 
	
}
