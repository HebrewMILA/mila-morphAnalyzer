package gen;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import lexicon.contents.ConnectedGenerator;
import lexicon.dbUtils.Inflections;
import lexicon.generate.ItemGen;

public class InflectionEntry
{
    String dataBaseUrl = "jdbc:mysql://yeda.cs.technion.ac.il:3306/";
    String lexiconPURL = dataBaseUrl + "lexiconP";
    
    private String  id;
    private String  surface;
    private String  basePos;
    private String  baseTransliteratedLItem;
    private String  baseUndottedLItem;
    private String  root;
    private String  dottedLexiconItem;
    private String  value;
    private String  hebForeign;  
    private String  baseLexiconPointer;
    private String  transliterated;
    private String  baseNumber;
    private String  baseGender;
    private String  basePerson;
    private String  PGN;
    private String  suffixFunction;
    private String  suffixStatus;
    private String  binyan;
    private String  tense;
    private String  baseDefinitness;
    private String  register;
    private String  spelling;
    private String  polarity;
    private String  prefix;
    private String  type;
    
    
    public String getId()
    {
        return id;
    }
    public void setId(String id)
    {
        this.id = id;
    }
    public String getSurface()
    {
        return surface;
    }
    public void setSurface(String surface)
    {
        this.surface = surface;
    }
    public String getBasePos()
    {
        return basePos;
    }
    public void setBasePos(String basePos)
    {
        this.basePos = basePos;
    }
    public String getBaseTransliteratedLItem()
    {
        return baseTransliteratedLItem;
    }
    public void setBaseTransliteratedLItem(String baseTransliteratedLItem)
    {
        this.baseTransliteratedLItem = baseTransliteratedLItem;
    }
    public String getBaseUndottedLItem()
    {
        return baseUndottedLItem;
    }
    public void setBaseUndottedLItem(String baseUndottedLItem)
    {
        this.baseUndottedLItem = baseUndottedLItem;
    }
    public String getRoot()
    {
        return root;
    }
    public void setRoot(String root)
    {
        this.root = root;
    }
    public String getDottedLexiconItem()
    {
        return dottedLexiconItem;
    }
    public void setDottedLexiconItem(String dottedLexiconItem)
    {
        this.dottedLexiconItem = dottedLexiconItem;
    }
    public String getValue()
    {
        return value;
    }
    public void setValue(String value)
    {
        this.value = value;
    }
    public String getHebForeign()
    {
        return hebForeign;
    }
    public void setHebForeign(String hebForeign)
    {
        this.hebForeign = hebForeign;
    }
    public String getBaseLexiconPointer()
    {
        return baseLexiconPointer;
    }
    public void setBaseLexiconPointer(String baseLexiconPointer)
    {
        this.baseLexiconPointer = baseLexiconPointer;
    }
    public String getTransliterated()
    {
        return transliterated;
    }
    public void setTransliterated(String transliterated)
    {
        this.transliterated = transliterated;
    }
    public String getBaseNumber()
    {
        return baseNumber;
    }
    public void setBaseNumber(String baseNumber)
    {
        this.baseNumber = baseNumber;
    }
    public String getBaseGender()
    {
        return baseGender;
    }
    public void setBaseGender(String baseGender)
    {
        this.baseGender = baseGender;
    }
    public String getBasePerson()
    {
        return basePerson;
    }
    public void setBasePerson(String basePerson)
    {
        this.basePerson = basePerson;
    }
    public String getPGN()
    {
        return PGN;
    }
    public void setPGN(String pGN)
    {
        PGN = pGN;
    }
    public String getSuffixFunction()
    {
        return suffixFunction;
    }
    public void setSuffixFunction(String suffixFunction)
    {
        this.suffixFunction = suffixFunction;
    }
    public String getSuffixStatus()
    {
        return suffixStatus;
    }
    public void setSuffixStatus(String suffixStatus)
    {
        this.suffixStatus = suffixStatus;
    }
    public String getBinyan()
    {
        return binyan;
    }
    public void setBinyan(String binyan)
    {
        this.binyan = binyan;
    }
    public String getTense()
    {
        return tense;
    }
    public void setTense(String tense)
    {
        this.tense = tense;
    }
    public String getBaseDefinitness()
    {
        return baseDefinitness;
    }
    public void setBaseDefinitness(String baseDefinitness)
    {
        this.baseDefinitness = baseDefinitness;
    }
    public String getRegister()
    {
        return register;
    }
    public void setRegister(String register)
    {
        this.register = register;
    }
    public String getSpelling()
    {
        return spelling;
    }
    public void setSpelling(String spelling)
    {
        this.spelling = spelling;
    }
    public String getPolarity()
    {
        return polarity;
    }
    public void setPolarity(String polarity)
    {
        this.polarity = polarity;
    }
    public String getPrefix()
    {
        return prefix;
    }
    public void setPrefix(String prefix)
    {
        this.prefix = prefix;
    }
    public String getType()
    {
        return type;
    }
    public void setType(String type)
    {
        this.type = type;
    }
    //------------------------------------------------------------------------------
    public InflectionEntry(ResultSet rs)
    {
        try
        {
            id = rs.getString("id");
            surface = rs.getString("surface");
            basePos = rs.getString("basePos");
            baseTransliteratedLItem = rs.getString("baseTransliteratedLItem");
            baseUndottedLItem = rs.getString("baseUndottedLItem");
            root = rs.getString("root");
            dottedLexiconItem = rs.getString("dottedLexiconItem");
            value = rs.getString("value");
            hebForeign = rs.getString("hebForeign");
            baseLexiconPointer = rs.getString("baseLexiconPointer");
            transliterated = rs.getString("transliterated");
            baseNumber=rs.getString("baseNumber");
            baseGender=rs.getString("baseGender");
            basePerson=rs.getString("basePerson");
            PGN=rs.getString("PGN");
            suffixFunction=rs.getString("suffixFunction");
            suffixStatus=rs.getString("suffixStatus");
            binyan=rs.getString("binyan");
            tense= rs.getString("tense");
            baseDefinitness = rs.getString("baseDefinitness");
            register=rs.getString("register");
            spelling=rs.getString("spelling");
            polarity=rs.getString("polarity");
            prefix=rs.getString("prefix");
            type=rs.getString("type");
        } 
        catch (SQLException e)
        {
            e.printStackTrace();
        }
    }
    //------------------------------------------------------------------------------
    public InflectionEntry(Inflections inflection)
    {   // basicly inflectionEntry is the same as inflection but the inflectionEntry is for the table editInflecion insted of table inflection
        // the table purpose is to provide a way of altering individual inflections - either remove or change them.
        id = null;
        surface = inflection.getSurface();
        basePos = inflection.getBasePos();
        baseTransliteratedLItem = inflection.getBaseTransliteratedLItem();
        baseUndottedLItem = inflection.getBaseundottedLItem();
        root = inflection.getRoot();
        dottedLexiconItem = inflection.getDottedLexiconItem();
        value = inflection.getValue();
        hebForeign = inflection.getHebForeign();
        baseLexiconPointer = inflection.getBaseLexiconPointer();
        transliterated = inflection.getTransliterated();
        baseNumber= inflection.getBaseNumber();
        baseGender= inflection.getBaseGender();
        basePerson= inflection.getBasePerson();
        PGN= inflection.getPGN();
        suffixFunction=inflection.getSuffixFunction();
        suffixStatus=inflection.getSuffixStatus();
        binyan=inflection.getBinyan();
        tense= inflection.getTense();
        baseDefinitness = inflection.getBaseDefinitness();
        register=inflection.getRegister();
        spelling=inflection.getSpelling();
        polarity=inflection.getPolarity();
        prefix=inflection.getPrefix();
        type=inflection.getType();
    }
    //------------------------------------------------------------------------------
    public InflectionEntry(String _baseLexiconPointer, String _transliterated, String _baseNumber,
                           String _baseGender, String _basePerson, String _PGN, String _suffixFunction,
                           String _suffixStatus, String _binyan, String _tense, String _baseDefinitness,
                           String _register, String _spelling, String _polarity, String _prefix,String _type
                           ,String _surface,String _basePos,String _baseTransliteratedLItem,String _baseUndottedLItem,String _root,
                           String _dottedLexiconItem,String _value,String _hebForeign)
    {
        baseLexiconPointer = _baseLexiconPointer;
        transliterated = _transliterated;
        baseNumber=_baseNumber;
        baseGender=_baseGender;
        basePerson=_basePerson;
        PGN=_PGN;
        suffixFunction=_suffixFunction;
        suffixStatus=_suffixStatus;
        binyan=_binyan;
        tense= _tense;
        baseDefinitness = _baseDefinitness;
        register=_register;
        spelling=_spelling;
        polarity=_polarity;
        prefix=_prefix;
        type=_type;
       
        //id = _id;
        surface = _surface;
        basePos = _basePos;
        baseTransliteratedLItem = _baseTransliteratedLItem;
        baseUndottedLItem = _baseUndottedLItem;
        root = _root;
        dottedLexiconItem = _dottedLexiconItem;
        value = _value;
        hebForeign = _hebForeign;
    }
    //------------------------------------------------------------------------------
    public String GetQuery()
    {
        String query = "SELECT * FROM inflectionsEdit WHERE action='remove' and " + GetEntryMatchString();
        return query;
    }
    //------------------------------------------------------------------------------
    public boolean CheckIfInflectionRemoved()
    {
        String query = null;
        try
        {
            query = "SELECT * FROM inflectionsEdit WHERE action='remove' and " + GetEntryMatchString();
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            Connection connection = DriverManager.getConnection(lexiconPURL,"lexiconUser", "!adgj?");
            PreparedStatement statement = connection.prepareStatement(query);
            ResultSet rs = statement.executeQuery();
            if (rs.next() ) // check if entry exist if it doesnt then return false
            {    
                connection.close();
                return true;
            }       
            connection.close();
        } 
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return false;
    }
    
    //------------------------------------------------------------------------------
    public String GetEntryMatchString()
    {
        String result = " baseLexiconPointer='"+ baseLexiconPointer +"' and " + 
                        "transliterated='"+ transliterated +"' and " +
                        "baseNumber='"+ baseNumber +"' and " +
                        "baseGender='"+ baseGender +"' and " +
                        "basePerson='"+ basePerson +"' and " +
                        "PGN='"+ PGN +"' and " +
                        "suffixFunction='"+ suffixFunction +"' and " +
                        "suffixStatus='"+ suffixStatus +"' and " +
                        "binyan='"+ binyan +"' and " +
                        "tense='"+ tense +"' and " +
                        "baseDefinitness='"+ baseDefinitness +"' and " +
                        "register='"+ register +"' and " +
                        "spelling='"+ spelling +"' and " +
                        "polarity='"+ polarity +"' and " +
                        "prefix='"+ prefix +"' and " +
                        "type='"+ type +"' and " +
                        "surface='"+ surface+"' and " + 
                        "basePos='"+basePos+"' and " + 
                        "baseTransliteratedLItem='"+baseTransliteratedLItem+"' and " +
                        "baseUndottedLItem='"+baseUndottedLItem+"' and " +
                        "root='"+ root+"' and " + 
                        "dottedLexiconItem='"+ dottedLexiconItem +"' and " +
                        "value='"+ value + "' and " + 
                        "hebForeign='"+ hebForeign+"' ";
                        
        return result;
    }
    //--------------------------------------------------------------------------------------------------
    // this is the (col1,col2,...) VALUES ('val1','val2',...)  part
    public String GetInsertQueryDetails()
    {
        String result ="(baseLexiconPointer, transliterated, baseNumber, baseGender, basePerson, PGN, suffixFunction, suffixStatus," +
        		       " binyan, tense, baseDefinitness, register, spelling, polarity, prefix,type," +
        		       "surface,basePos,baseTransliteratedLItem,baseUndottedLItem,root,dottedLexiconItem,value,hebForeign,action)" +
        		       " VALUES('"+baseLexiconPointer+"','"+transliterated+"','"+baseNumber+"','"+baseGender+"','"+basePerson+"','"+PGN+"'," +
        		       "'"+suffixFunction+"','"+suffixStatus+"','"+binyan+"','"+tense+"','"+baseDefinitness+"','"+register+"'," +
        		       "'"+spelling+"','"+polarity+"','"+prefix+"','"+type+"','"+surface+"','"+basePos+"','"+baseTransliteratedLItem+"','"+baseUndottedLItem+"'," +
        		       "'"+root+"','"+dottedLexiconItem+"','"+value+"','"+hebForeign+"','remove')";
        
        return result;
    }
    //--------------------------------------------------------------------------------------------------
    // now creating the remove button
    public String GetFormButton(String url,String action)
    {// http://yeda.cs.technion.ac.il:8088/generatorViewer/testGeneration.jsp?lexiconId="+lexiconId+
        String result ="<form name='myform' action='"+url+"' method='POST'>" +
        "<input type='hidden' name='action' value='"+action+"'>" +
        "<input type='hidden' name='baseLexiconPointer' value='"+baseLexiconPointer+"'>" +
        "<input type='hidden' name='transliterated' value='"+transliterated+"'>" +
        "<input type='hidden' name='baseNumber' value='"+baseNumber+"'>" +
        "<input type='hidden' name='baseGender' value='"+baseGender+"'>" +
        "<input type='hidden' name='basePerson' value='"+basePerson+"'>" +
        "<input type='hidden' name='PGN' value='"+PGN+"'>" +
        "<input type='hidden' name='suffixFunction' value='"+suffixFunction+"'>" +
        "<input type='hidden' name='suffixStatus' value='"+suffixStatus+"'>" +
        "<input type='hidden' name='binyan' value='"+binyan+"'>" +
        "<input type='hidden' name='tense' value='"+tense+"'>" +
        "<input type='hidden' name='baseDefinitness' value='"+baseDefinitness+"'>" +
        "<input type='hidden' name='register' value='"+register+"'>" +
        "<input type='hidden' name='spelling' value='"+spelling+"'>" +
        "<input type='hidden' name='polarity' value='"+polarity+"'>" +
        "<input type='hidden' name='prefix' value='"+prefix+"'>" +
        "<input type='hidden' name='type' value='"+type+"'>" +
        "<input type='hidden' name='id' value='"+id+"'>" +
        "<input type='hidden' name='surface' value='"+surface+"'>" +
        "<input type='hidden' name='basePos' value='"+basePos+"'>" +
        "<input type='hidden' name='baseTransliteratedLItem' value='"+baseTransliteratedLItem+"'>" +
        "<input type='hidden' name='baseUndottedLItem' value='"+baseUndottedLItem+"'>" +
        "<input type='hidden' name='root' value='"+root+"'>" +
        "<input type='hidden' name='dottedLexiconItem' value='"+dottedLexiconItem+"'>" +
        "<input type='hidden' name='value' value='"+value+"'>" +
        "<input type='hidden' name='hebForeign' value='"+hebForeign+"'>" +
        "<input type='Submit' value='"+action+"'>"+
        "</form>";
        return result;
    }
}
