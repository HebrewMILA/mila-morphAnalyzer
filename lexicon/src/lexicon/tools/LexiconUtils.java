package lexicon.tools;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;
import java.io.UnsupportedEncodingException;
import java.net.*;

import javax.servlet.ServletRequest;

import lexicon.contents.EmptyContent;
import lexicon.contents.SearchItem;

/**
 * @author Danny Shacham
 */
public class LexiconUtils 
{
	protected static final String[] EXCEPTION_TYPES = { "adverb",
			"interjection", "interrogative", "noun", "preposition",
			"quantifier", "verb", "propername", "pronoun", "numeral", "copula","modal","existential" };

	//------------------------------------------------------------------------------------------------
	public static ResultSet GetItem(String sql)
	{   // EDIT : not used
	    // get the item that match the sql query
	    ResultSet rs = null;
	    //int id = -1 ;
	    EmptyContent content = new EmptyContent();
        rs = content.DoQuery(sql);
        return rs;
	}
	//------------------------------------------------------------------------------------------------
	public static int GetNextItemId(int current_id)
	{
	    int id = -1;
	    EmptyContent content = new EmptyContent();
	    String sql = "SELECT * FROM item WHERE id > "+current_id+" and deleted=0 ORDER BY id ASC LIMIT 1";
	    id = content.GetQueryInt(sql,"id"); 
        return id;
	}
	
	//------------------------------------------------------------------------------------------------
	public static int GetPrevItemId(int current_id)
    {
	    int id = -1;
	    EmptyContent content = new EmptyContent();
	    String sql = "SELECT * FROM item WHERE id < "+current_id+" and deleted=0 ORDER BY id DESC LIMIT 1";
	    id = content.GetQueryInt(sql,"id");
	    return id;
    }
	//------------------------------------------------------------------------------------------------
	public static String GetItemPos(int id)
	{
	    String pos = "";
	    EmptyContent content = new EmptyContent();
	    String sql = "SELECT * FROM item WHERE id = "+id+" and deleted=0";
	    pos = content.GetQueryString(sql,"pos");
        return pos;
	}
	//------------------------------------------------------------------------------------------------
	public static String GetNextItemPos(int current_id)
	{
	    return GetItemPos(GetNextItemId(current_id));
	}
	//-------------------------------------------------------------------------------------------------
	public static String GetPrevItemPos(int current_id)
    {
	    return GetItemPos(GetPrevItemId(current_id));
    }
    //-------------------------------------------------------------------------------------------------
	public static List<SearchItem> searchForItems(String id, String dotted,String undotted, String transliterated)
	{
	    //undotted = undotted.replaceAll("'","&#39;");
		try 
		{
		    //dotted = URLEncoder.encode(dotted, "UTF-8");
			dotted = URLEncoder.encode(dotted, "ISO-8859-1");
		} 
		catch (Exception e) 
		{
		}
		try 
		{
		    //undotted = URLEncoder.encode(undotted, "UTF-8");
			undotted = URLEncoder.encode(undotted, "ISO-8859-1");
			//undotted = undotted.replaceAll("%26%2339%3B","%27");
		} 
		catch (Exception e) 
		{
		}
		try 
		{
		    //transliterated = URLEncoder.encode(transliterated, "UTF-8");
			transliterated = URLEncoder.encode(transliterated, "ISO-8859-1");
		} 
		catch (Exception e) 
		{
		}
		String sql = "SELECT id, dotted, undotted, transliterated, pos FROM item WHERE deleted=0";
		String cond = "";
		if (!id.equals("")) 
		{
			cond += " id=" + id + " OR";
		} 
		else 
		{
			if (!dotted.equals("")) 
			{
				cond += " dotted='" + dotted + "' OR";
			}
			if (!undotted.equals("")) 
			{
				cond += " undotted='" + undotted + "' OR";
			}
			if (!transliterated.equals("")) 
			{
				cond += " transliterated='" + transliterated + "' OR";
			}
		}
		if (cond.endsWith("OR")) 
		{
			cond = cond.substring(0, cond.length() - 3);
		}
		if (!cond.equals("")) 
		{
			sql = sql + " AND (" + cond + ")";
		}
		EmptyContent content = new EmptyContent();
		//System.out.println("QUERY: " + sql);
		List<SearchItem> result = content.getItems(sql);

		// adding all the words that their exception is like the word we search
		// for
		if (undotted != null && !undotted.equals("")) 
		{
			for (int i = 0; i < EXCEPTION_TYPES.length; i++) 
			{
				String exceptionType = EXCEPTION_TYPES[i];
				sql = "SELECT item.id, item.dotted, item.undotted, item.transliterated, pos";
				sql += " FROM item," + exceptionType + "_exception_type WHERE ";
				sql += exceptionType + "_exception_type.id=item.id AND action='add' AND ";
				sql += exceptionType + "_exception_type.undotted='" + undotted + "'";
				result.addAll(content.getItems(sql));
			}
		}
		return result;
	}
	/*----------------------------------------------------------------------------------------------------*/
	/**
	 * Renders all the parameters from a ServletRequest object to a link or to
	 * hidden fields. The method can omit (skip) parameters from the output by
	 * using a list passed to it.
	 * <p>
	 * The link would be a string with the pattern of "&[name]=[value]..." <br>
	 * Hidden fields would take the pattern of: <br>" <input type="hidden"
	 * name="[name]" value="[value]">..."
	 * <p>
	 * An example of using this method would be: <br>
	 * <code>
	 * TreeSet omit = new TreeSet();<br>
	 * omit.add("username");<br>
	 * omit.add("password");<br>
	 * String queryString = QUtil.renderAllRequestParameters(request, omit, false);<br>
	 * </code>
	 * <p>
	 * 
	 * @param req
	 *            The request object.
	 * @param omitList
	 *            A TreeSet (simple unordered bag) which the skipped names of
	 *            parameters were inserted into.
	 * @param doHiddens
	 *            If set to <code>0</code>, the method would the result as
	 *            queryString If set to <code>1</code>, the method would
	 *            produce hidden If set to <code>2</code>, the method would
	 *            produce the result as jsp:forward parameters fields.
	 *            otherwise, it woudl create a link.
	 * @return
	 */
	public static String renderAllRequestParameters(ServletRequest req,TreeSet omitList, int doHiddens) 
	{
		if (req == null) 
		{
			return "";
		}
		Enumeration params = req.getParameterNames();
		String hiddens = "";
		String[] names = new String[] { "" };
		while (params.hasMoreElements()) 
		{
			String name = (String) params.nextElement();
			names = req.getParameterValues(name);
			for (int i = 0; i < names.length; i++) 
			{
				try 
				{
					if (omitList == null || !omitList.contains(names[i])) 
					{
						String value = names[i];
						if (value != null) 
						{
							value = value.replaceAll("\"", "&quot;");
						}
						if (doHiddens == 0) 
						{
							hiddens += "&";
							hiddens += name + "=" + value;
						}
						if (doHiddens == 1) 
						{
							hiddens += "<input type=\"hidden\" name=\"" + name
									+ "\"";
							hiddens += " value=\"" + value + "\">\n";
						}
						if (doHiddens == 2) 
						{
							hiddens += "<jsp:param name=\"" + name
									+ "\" value=\"" + value + "\"/>";
						}
					}
				} 
				catch (Exception E) 
				{
				}
			}
		}
		return hiddens;
	}
	/*-------------------------------------------------------------------------------------------------------*/
	public static String getTransliteration(String hebUTF) 
	{
		hebUTF= hebUTF.trim();
		if (hebUTF == null || hebUTF.length() == 0) 
		{
			return "";
		}
		StringBuffer result = new StringBuffer();
		String[] heb = hebUTF.substring(1).split("%");
		for (int i = 0; i < heb.length; i++) 
		{
			//System.out.println(heb[i]);
			String eng = "";
			String extra = "";
			String hebToChange = "";
			if(i+1<heb.length)
			{
			if (heb[i + 1].endsWith("."))
				extra = ".";
			else if (heb[i + 1].endsWith("'"))
				extra = "'";
			else if (heb[i + 1].endsWith("+"))
				extra = " ";
			else if (heb[i + 1].endsWith("-"))
				extra = "-";
			}
			//System.out.println("extra =" +extra);
			if (heb[i].startsWith("D")) 
			{
				if (heb[i + 1].length() == 2) 
				{
					hebToChange = "%" + heb[i] + "%" + heb[++i];
					eng = Names.getHebToEng(hebToChange);
				} 
				else 
				{
					String temp = heb[i + 1].substring(0, 2);
					hebToChange = "%" + heb[i++] + "%" + temp;
					eng = Names.getHebToEng(hebToChange);
					//extra = "-";
				}
			} 
			else 
			{
				eng = Names.getHebToEng("%" + heb[i]);
				//System.out.println(eng);
			}
			result.append(eng);
			result.append(extra);
		}
		return result.toString();
	}
	/*-------------------------------------------------------------------------------------------------------*/
	public static void main(String[] args) 
	{
		//String temp = LexiconUtils.getTransliteration("%D7%99-%D7%9D");
		//System.out.println(temp);
	    
	    //int id = LexiconUtils.GetPrevItemId(30355);
	    //System.out.println(id);
	    
	    String undotted = null;
        try
        {
            undotted = "אוברלורד";
            //undotted = "חורבת קרתא";
            //undotted = undotted.replaceAll("'","&#39;");
            //undotted = undotted.replaceAll("'","%27");
            undotted = URLEncoder.encode(undotted, "UTF-8");
            undotted = URLDecoder.decode(undotted, "ISO-8859-1");
            
          
        } catch (UnsupportedEncodingException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
	    List<SearchItem> result = searchForItems("", "",undotted, "");
	    System.out.println(result.size());
	    
	    /*String trans = null;
        try
        {
            trans = getTransliteration(URLEncoder.encode("טום וג'רי", "UTF-8"));
        } catch (UnsupportedEncodingException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
	    System.out.println(trans);*/
	    
	}
}
