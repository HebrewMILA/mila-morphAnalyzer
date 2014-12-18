/*
 * Created on 22/06/2006
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package lexicon.tools;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.StringTokenizer;

import lexicon.contents.Connected;
import lexicon.contents.ConnectedGenerator;
import lexicon.contents.Log;
import lexicon.generate.PopulateMWE;
import lexicon.stringUtils.Translate;

/**
 * @author daliabo
 * 
 * TODO To change the template for this generated type comment go to Window -
 * Preferences - Java - Code Style - Code Templates
 */
public class AutoActivities extends ConnectedGenerator 
{
	String dir = "C:\\Documents and Settings\\daliabo\\My Documents\\lexicon\\diffTests\\";
	BufferedWriter bw;

	protected void outputFileHandling(String outputFile) 
	{
		FileOutputStream out;
		try 
		{
			out = new FileOutputStream(outputFile);
			OutputStreamWriter pOut = new OutputStreamWriter(out);
			bw = new BufferedWriter(pOut);
		} 
		catch (FileNotFoundException e) 
		{
			System.err.println("output File not found:  " + outputFile);
			e.printStackTrace();
			System.exit(1);
		}
	}

	public void deleteTrasliteratedProblemEntries() 
	{
		String sqlSelect = "select * from item where item.undotted like '%+%'  ";

		ResultSet rs = getData(sqlSelect);
		try 
		{
			while (rs.next()) 
			{
				String id = rs.getString("id");
				Log log = new Log(1000005, Integer.parseInt(id), 0,"Auto Remove lexicon entries which are multy token words ");
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
		String sql = " delete  from propername where id in(select id from item where  undotted like '%+%')";
		execute(sql);
		sql = "delete  from noun where id in(select id from item where  undotted like '%+%')";
		execute(sql);
		sql = "delete  from item  where  undotted like '%+%'";
		execute(sql);

		System.out.println("finished");
		System.exit(1);
	}
	
	
	public void addNiqudToItemTable() throws UnsupportedEncodingException,IOException 
	{					
			String id = "";
			String line = "";		
			String undotted = "";
			String dotted = "";
								
			BufferedReader bi = new BufferedReader(
					new InputStreamReader(
							new FileInputStream(
									"c:\\Documents and Settings\\gtabajah\\My Documents\\work\\GeneratorAddNiqud\\Input files\\Niqud_3_Columns_Final.txt")));
			
			while ((line = bi.readLine()) != null) 
			{
				StringTokenizer t = new StringTokenizer(line);
				undotted = t.nextToken();
				id = t.nextToken();
				dotted = t.nextToken();				
								
				undotted = URLEncoder.encode(undotted, "UTF-8");
				System.out.println("undotted =" + undotted);				
				
				System.out.println("id =" + id);
										
				dotted = URLEncoder.encode(dotted, "UTF-8");				
				System.out.println("dotted =" + dotted);
				String sql = "update lexiconP.item set dotted='"
						+ dotted
						+"' where id="
						+ id;
								
				execute(sql);
				releaseConnection();						
			}
			System.out.println("finish");
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

	public void updateTransitiveVerbs() throws NumberFormatException,IOException, SQLException 
	{
		BufferedReader bi = new BufferedReader(new InputStreamReader(new FileInputStream("intransitive.txt"), "utf8"));
		String line = "";
		String id = "";
		String transitiveWithoutPaul = "";
		String transitiveWithPaul = "";
		String verb = "";
		while ((line = bi.readLine()) != null) 
		{
			//System.out.println("line=" + line);
			StringTokenizer st = new StringTokenizer(line, ",");
			System.out.println("***********************");
			verb = st.nextToken();
			System.out.println("verb=" + verb);
			id = st.nextToken();
			System.out.println("id=" + id);
			//String transliterated = Translate.Heb2Eng(verb);
			//System.out.println("transliterated="+ transliterated);
			//int num = Integer.valueOf(id).intValue();
			//id=id.substring(0,id.length()-2);
			//System.out.println(id);
			//String sqlSelect = "select * from verb where id="+ id;
			//ResultSet rs = getData(sqlSelect);
			//String valence="";
			//try {
			//	while (rs.next()) {
			//		 valence = rs.getString("valence");
			//	}
			//	rs.close();
			//		if (!valence.equals("transitive") &&
			// !valence.equals("unspecified"))
			//			System.out.println(id +" valence="+valence);
			String sql = "update verb set valence='intransitiveWithoutPaul' where id="
					+ id;
			execute(sql);
			releaseConnection();

		}
		System.out.println("finished");
		System.exit(1);
	}

	public void copyNathionalityToNouns() throws NumberFormatException,IOException, SQLException 
	{
		int counter = 24362;
		BufferedReader bi = new BufferedReader(new InputStreamReader(new FileInputStream("adjectivesi1.txt"), "utf8"));
		String line = "";
		String id = "";

		while ((id = bi.readLine()) != null) 
		{
			//			String sql = "insert into item
			// (id,pos,undotted,transliterated,dotted, script) select " +
			// counter +" ,'noun',item.undotted,item.transliterated, dotted,
			// script from item where id = "
			//					+ id;

			//			String sql = "insert into noun
			// (id,gender,number,feminine,plural,is_dual,deverbal,acronym,definiteness
			// ,direction,lexicalLink,root
			// ,pattern,inflectionPattern,ipSource,inflectionBase ) select " +
			// counter
			// +",gender,number,feminine,plural,0,'unspecified','false','true','false','','','','','',''
			// from adjective where adjective.id="
			//			+ id;

			String sql = "update noun_exception_type set script='colloquial' where  gender='masculine' and number='plural' and id=" + id;
			counter++;

			execute(sql);
			releaseConnection();
		}
		System.out.println("finished");
		System.exit(1);
	}

	public void checkExistQountries() throws NumberFormatException,IOException, SQLException 
	{
		outputFileHandling("C:\\Documents and Settings\\daliabo\\My Documents\\lexicon\\diffTests\\citiesTotalOut.txt");
		BufferedReader bi = new BufferedReader(
				new InputStreamReader(
						new FileInputStream(
								"C:\\Documents and Settings\\daliabo\\My Documents\\lexicon\\diffTests\\citiesOut.txt")));
		String line = "";
		String transliterated = "";
		String id = "";

		while ((line = bi.readLine()) != null) 
		{
			transliterated = Translate.Heb2Eng(line);
			String sql = "select * from item, propername where transliterated = '" + transliterated
					+ "' and item.pos='properName' and item.id=propername.id ";
			//
			//			String sql = "select * from propername_exception_type, item where
			// item.transliterated = '"
			//				+ transliterated + "' and item.id=propername_exception_type.id ";

			ResultSet rs = null;
			rs = getData(sql);
			id = "";
			transliterated = "";
			while (rs.next()) 
			{
				id = rs.getString("id");
				transliterated = rs.getString("transliterated");
			}
			//if (id.equals("")){
			System.out.println(line + " " + id);
			bw.write(line);
			bw.newLine();
			//}
			//			System.out.println(id);
			//			System.out.println(transliterated);
			//			System.out.println("****************************");
			releaseConnection();
		}
		bw.close();
		System.out.println("finished");
		System.exit(1);
	}

	public void addQountries() throws NumberFormatException, IOException,SQLException 
	{
		BufferedReader bi = new BufferedReader(
				new InputStreamReader(
						new FileInputStream(
								"C:\\Documents and Settings\\daliabo\\My Documents\\lexicon\\diffTests\\capitalOut.txt")));
		String line = "";
		String transliterated = "";
		String id = "";
		String undotted = "";

		while ((line = bi.readLine()) != null) 
		{
			line = line.trim();
			line = line.replaceAll("'", "&#39;");
			transliterated = Translate.Heb2Eng(line);
			undotted = URLEncoder.encode(line, "UTF-8");
			System.out.println(undotted);
			String sql = "insert into item (pos,undotted,transliterated,script) values ("
					+ "'properName','"
					+ undotted
					+ "','"
					+ transliterated
					+ "','formal')";
			System.out.println(sql);

			execute(sql);
			releaseConnection();

			sql = "select LAST_INSERT_ID() as last";

			ResultSet rs = null;
			rs = getData(sql);
			String last = "";

			while (rs.next()) 
			{
				last = rs.getString("last");
			}
			releaseConnection();
			sql = "insert into propername (id,gender,number,type,definiteness,direction) values ("
					+ Integer.parseInt(last)
					+ ",'masculine', 'singular', 'town', 'prohibited', 'false')";
			execute(sql);
			releaseConnection();

			Log log = new Log(1000005, Integer.parseInt(last), 0,"Auto adding of cities ");
			log.add();
		}
		System.out.println("finished");
		System.exit(1);
	}

	public void addMWQountries() throws NumberFormatException, IOException,SQLException 
	{
		BufferedReader bi = new BufferedReader(
				new InputStreamReader(
						new FileInputStream(
								"C:\\Documents and Settings\\daliabo\\My Documents\\lexicon\\diffTests\\mwtown.txt")));
		String line = "";
		String transliterated = "";
		String id = "";
		String undotted = "";
		String t1="";
		String t2="";
		while ((line = bi.readLine()) != null) 
		{
			line = line.trim();
			line = line.replaceAll("'", "&#39;");
			StringTokenizer st = new StringTokenizer(line);
			while(st.hasMoreTokens())
			{
				 t1 = st.nextToken();
				 t2= st.nextToken();
			}
			undotted = URLEncoder.encode(t1, "UTF-8") +"+" + URLEncoder.encode(t2, "UTF-8");
			
			transliterated = Translate.Heb2Eng(t1) + "+" + Translate.Heb2Eng(t2);
			
			System.out.println(undotted);

			String sql = "insert into lexiconP.item (pos,undotted,transliterated,register, spelling) values ("
					+ "'multiWord','"
					+ undotted
					+ "','"
					+ transliterated
					+ "','formal','standard')";
			System.out.println(sql);
			execute(sql);
			releaseConnection();

			sql = "select LAST_INSERT_ID() as last";

			ResultSet rs = null;
			rs = getData(sql);
			String last = "";

			while (rs.next()) 
			{
				last = rs.getString("last");
			}

			releaseConnection();			
			sql = "insert into lexiconP.multiWord (id,pos,type,consecutive) values ("
					+ Integer.parseInt(last)
					+ ",'properName', 'town', '1')";
			
		execute(sql);
		releaseConnection();

		Log log = new Log(1000005, Integer.parseInt(last), 0,"Auto adding of multi word town ");
		log.add();

		}
		System.out.println("finished");
		System.exit(1);
	}

	public void addExceptions() throws UnsupportedEncodingException,IOException 
	{
		int aid = 1210;
		String id = "";
		String line = "";
		String transliterated = "";
		String undotted = "";

		BufferedReader bi = new BufferedReader(new InputStreamReader(
				new FileInputStream("endsWithh.txt")));

		while ((line = bi.readLine()) != null) 
		{
			StringTokenizer t = new StringTokenizer(line);
			undotted = t.nextToken();
			id = t.nextToken();
			transliterated = Translate.Heb2Eng(undotted);
			undotted = URLEncoder.encode(undotted, "UTF-8");
			System.out.println(undotted);
			String sql = "insert into noun_exception_type"
					+ "(aid,id,undotted,transliterated,script,gender, number, feminine,plural, possessive,is_dual,construct,action, inflectConstructS , inflectPossessiveS, inflectConstructP) values"
					+ " ("
					+ aid
					+ ","
					+ id
					+ ",'"
					+ undotted
					+ "','"
					+ transliterated
					+ "', 'formal', 'masculine', 'singular','irrelevant','irrelevant','2p/M/Sg','false','false','add')";
			System.out.println(sql);
			execute(sql);
			releaseConnection();

			Log log = new Log(
					1000005,
					Integer.parseInt(id),
					aid,
					"Auto adding PGN - 3P/M/SG ends with hw in addition to ends with w to nouns nasculine singular ends with h ");
			log.add();

			aid++;
		}

	}

	public void addVerbExceptions() throws UnsupportedEncodingException,IOException 
	{
		int aid = 898;
		String id = "";
		String line = "";
		String transliterated = "";
		String undotted = "";

		BufferedReader bi = new BufferedReader(
				new InputStreamReader(
						new FileInputStream(
								"C:\\Documents and Settings\\daliabo\\My Documents\\lexicon\\diffTests\\verbs.txt")));

		while ((line = bi.readLine()) != null) 
		{
			StringTokenizer t = new StringTokenizer(line);
			undotted = t.nextToken();
			id = t.nextToken();
			transliterated = Translate.Heb2Eng(undotted);
			undotted = URLEncoder.encode(undotted, "UTF-8");
			System.out.println(undotted);
			String sql = "insert into verb_exception_type"
					+ "(aid,id,undotted,transliterated,script,tense, pgn,accusativeSuffix,action) values"
					+ " ("
					+ aid
					+ ","
					+ id
					+ ",'"
					+ undotted
					+ "','"
					+ transliterated
					+ "', 'formal', 'bareInfinitive', 'unspecified','unspecified','add')";
			System.out.println(sql);
			execute(sql);
			releaseConnection();

			Log log = new Log(10276, Integer.parseInt(id), aid,
					"Auto adding foraml bareInfinitive  to paal ends with h ");
			log.add();

			aid++;
		}
		System.out.println("finish");
	}

	public void translate() throws UnsupportedEncodingException, IOException 
	{
		int aid = 898;
		String id = "";
		String line = "";
		String english = "";
		String hebrew = "";

		BufferedReader bi = new BufferedReader(
				new InputStreamReader(
						new FileInputStream(
								"C:\\Documents and Settings\\daliabo\\My Documents\\lexicon\\diffTests\\translations.txt")));

		while ((line = bi.readLine()) != null) 
		{
			StringTokenizer t = new StringTokenizer(line);
			english = t.nextToken();
			hebrew = t.nextToken();
			hebrew = hebrew.replaceAll("%22", "\"");
			String transliterated = Translate.Eng2Heb(hebrew);
			System.out.print(english);
			System.out.print("\t" + hebrew);
			System.out.print("\t" + transliterated);
			System.out.println();
		}

		System.out.println("finish");

	}

	public void findLemma() throws SQLException 
	{
		String line = "";
		String transliterated = "";
		String undotted = "";
		BufferedReader bi = null;
		try 
		{
			bi = new BufferedReader(
					new InputStreamReader(
							new FileInputStream(
									"C:\\Documents and Settings\\daliabo\\My Documents\\lexicon\\diffTests\\sortedList.txt")));
		} 
		catch (FileNotFoundException e1) 
		{
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try 
		{
			while ((line = bi.readLine()) != null) 
			{
				transliterated = Translate.Heb2Eng(line);
				String sql = "select * from inflections where transliterated = '"
						+ transliterated + "' and basePos='verb'";

				ResultSet rs = null;
				rs = getData(sql);

				transliterated = "";
				while (rs.next()) 
				{
					String baseTransliteratedLItem = rs.getString("baseTransliteratedLItem");
					baseTransliteratedLItem = Translate.Eng2Heb(baseTransliteratedLItem);
					System.out.print(line);
					System.out.print("\t");
					System.out.print(baseTransliteratedLItem);
					System.out.println();
				}
				releaseConnection();
			}
		} 
		catch (IOException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void saebtranslate() throws IOException 
	{
		BufferedReader bi = null;
		String line = "";
		try 
		{
			bi = new BufferedReader(new InputStreamReader(new FileInputStream(
					"C:\\Documents and Settings\\daliabo\\Desktop\\saeb1.txt")));
		} 
		catch (FileNotFoundException e1) 
		{
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		while ((line = bi.readLine()) != null) 
		{
			StringTokenizer st = new StringTokenizer(line, "\t");
			st.nextToken();
			String token = st.nextToken();
			String hebrew = Translate.saebEng2Heb(token);
			System.out.print(hebrew);
			st.nextToken();
			token = st.nextToken();
			System.out.print("\t" + token);
			System.out.println();
		}
	}

	public static void main(String[] args) throws IOException, SQLException 
	{
		AutoActivities a = new AutoActivities();
		try 
		{
			//a.MoveFromQuantifier();
			//a.deleteTrasliteratedProblemEntries();
			//a.updateTransitiveVerbs();
			//a.copyNathionalityToNouns();
			//a.addQountries();
			//for (int i=24362; i< 24463 ; i++){
			//Log log = new Log(1000005, i, 0,
			//"Auto adding nouns nathionality from adverb nathionality ");
			//log.add();
			//a.addNiqudToItemTable();
			a.addMWQountries();
		} 
		catch (NumberFormatException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
