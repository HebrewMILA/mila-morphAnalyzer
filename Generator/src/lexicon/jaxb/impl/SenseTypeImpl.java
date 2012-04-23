//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v1.0.5-b16-fcs 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2010.01.10 at 01:33:09 PM IST 
//


package lexicon.jaxb.impl;

public class SenseTypeImpl implements lexicon.jaxb.SenseType, com.sun.xml.bind.JAXBObject, lexicon.jaxb.impl.runtime.UnmarshallableObject, lexicon.jaxb.impl.runtime.XMLSerializable, lexicon.jaxb.impl.runtime.ValidatableObject
{

    protected java.lang.String _Definition;
    protected java.lang.String _Weight;
    protected com.sun.xml.bind.util.ListImpl _English;
    protected com.sun.xml.bind.util.ListImpl _Synset;
    protected com.sun.xml.bind.util.ListImpl _Example;
    protected java.lang.String _Id;
    public final static java.lang.Class version = (lexicon.jaxb.impl.JAXBVersion.class);
    private static com.sun.msv.grammar.Grammar schemaFragment;

    private final static java.lang.Class PRIMARY_INTERFACE_CLASS() {
        return (lexicon.jaxb.SenseType.class);
    }

    public java.lang.String getDefinition() {
        return _Definition;
    }

    public void setDefinition(java.lang.String value) {
        _Definition = value;
    }

    public java.lang.String getWeight() {
        return _Weight;
    }

    public void setWeight(java.lang.String value) {
        _Weight = value;
    }

    protected com.sun.xml.bind.util.ListImpl _getEnglish() {
        if (_English == null) {
            _English = new com.sun.xml.bind.util.ListImpl(new java.util.ArrayList());
        }
        return _English;
    }

    public java.util.List getEnglish() {
        return _getEnglish();
    }

    protected com.sun.xml.bind.util.ListImpl _getSynset() {
        if (_Synset == null) {
            _Synset = new com.sun.xml.bind.util.ListImpl(new java.util.ArrayList());
        }
        return _Synset;
    }

    public java.util.List getSynset() {
        return _getSynset();
    }

    protected com.sun.xml.bind.util.ListImpl _getExample() {
        if (_Example == null) {
            _Example = new com.sun.xml.bind.util.ListImpl(new java.util.ArrayList());
        }
        return _Example;
    }

    public java.util.List getExample() {
        return _getExample();
    }

    public java.lang.String getId() {
        return _Id;
    }

    public void setId(java.lang.String value) {
        _Id = value;
    }

    public lexicon.jaxb.impl.runtime.UnmarshallingEventHandler createUnmarshaller(lexicon.jaxb.impl.runtime.UnmarshallingContext context) {
        return new lexicon.jaxb.impl.SenseTypeImpl.Unmarshaller(context);
    }

    public void serializeBody(lexicon.jaxb.impl.runtime.XMLSerializer context)
        throws org.xml.sax.SAXException
    {
        int idx3 = 0;
        final int len3 = ((_English == null)? 0 :_English.size());
        int idx4 = 0;
        final int len4 = ((_Synset == null)? 0 :_Synset.size());
        int idx5 = 0;
        final int len5 = ((_Example == null)? 0 :_Example.size());
        while (idx3 != len3) {
            context.startElement("", "english");
            int idx_0 = idx3;
            context.childAsURIs(((com.sun.xml.bind.JAXBObject) _English.get(idx_0 ++)), "English");
            context.endNamespaceDecls();
            int idx_1 = idx3;
            context.childAsAttributes(((com.sun.xml.bind.JAXBObject) _English.get(idx_1 ++)), "English");
            context.endAttributes();
            context.childAsBody(((com.sun.xml.bind.JAXBObject) _English.get(idx3 ++)), "English");
            context.endElement();
        }
        while (idx4 != len4) {
            context.startElement("", "synset");
            int idx_2 = idx4;
            context.childAsURIs(((com.sun.xml.bind.JAXBObject) _Synset.get(idx_2 ++)), "Synset");
            context.endNamespaceDecls();
            int idx_3 = idx4;
            context.childAsAttributes(((com.sun.xml.bind.JAXBObject) _Synset.get(idx_3 ++)), "Synset");
            context.endAttributes();
            context.childAsBody(((com.sun.xml.bind.JAXBObject) _Synset.get(idx4 ++)), "Synset");
            context.endElement();
        }
        while (idx5 != len5) {
            context.startElement("", "example");
            int idx_4 = idx5;
            context.childAsURIs(((com.sun.xml.bind.JAXBObject) _Example.get(idx_4 ++)), "Example");
            context.endNamespaceDecls();
            int idx_5 = idx5;
            context.childAsAttributes(((com.sun.xml.bind.JAXBObject) _Example.get(idx_5 ++)), "Example");
            context.endAttributes();
            context.childAsBody(((com.sun.xml.bind.JAXBObject) _Example.get(idx5 ++)), "Example");
            context.endElement();
        }
    }

    public void serializeAttributes(lexicon.jaxb.impl.runtime.XMLSerializer context)
        throws org.xml.sax.SAXException
    {
        int idx3 = 0;
        final int len3 = ((_English == null)? 0 :_English.size());
        int idx4 = 0;
        final int len4 = ((_Synset == null)? 0 :_Synset.size());
        int idx5 = 0;
        final int len5 = ((_Example == null)? 0 :_Example.size());
        if (_Definition!= null) {
            context.startAttribute("", "definition");
            try {
                context.text(((java.lang.String) _Definition), "Definition");
            } catch (java.lang.Exception e) {
                lexicon.jaxb.impl.runtime.Util.handlePrintConversionException(this, e, context);
            }
            context.endAttribute();
        }
        context.startAttribute("", "id");
        try {
            context.text(((java.lang.String) _Id), "Id");
        } catch (java.lang.Exception e) {
            lexicon.jaxb.impl.runtime.Util.handlePrintConversionException(this, e, context);
        }
        context.endAttribute();
        if (_Weight!= null) {
            context.startAttribute("", "weight");
            try {
                context.text(((java.lang.String) _Weight), "Weight");
            } catch (java.lang.Exception e) {
                lexicon.jaxb.impl.runtime.Util.handlePrintConversionException(this, e, context);
            }
            context.endAttribute();
        }
        while (idx3 != len3) {
            idx3 += 1;
        }
        while (idx4 != len4) {
            idx4 += 1;
        }
        while (idx5 != len5) {
            idx5 += 1;
        }
    }

    public void serializeURIs(lexicon.jaxb.impl.runtime.XMLSerializer context)
        throws org.xml.sax.SAXException
    {
        int idx3 = 0;
        final int len3 = ((_English == null)? 0 :_English.size());
        int idx4 = 0;
        final int len4 = ((_Synset == null)? 0 :_Synset.size());
        int idx5 = 0;
        final int len5 = ((_Example == null)? 0 :_Example.size());
        while (idx3 != len3) {
            idx3 += 1;
        }
        while (idx4 != len4) {
            idx4 += 1;
        }
        while (idx5 != len5) {
            idx5 += 1;
        }
    }

    public java.lang.Class getPrimaryInterface() {
        return (lexicon.jaxb.SenseType.class);
    }

    public com.sun.msv.verifier.DocumentDeclaration createRawValidator() {
        if (schemaFragment == null) {
            schemaFragment = com.sun.xml.bind.validator.SchemaDeserializer.deserialize((
 "\u00ac\u00ed\u0000\u0005sr\u0000\u001fcom.sun.msv.grammar.SequenceExp\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0001\u0002\u0000\u0000xr\u0000\u001dcom.su"
+"n.msv.grammar.BinaryExp\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0001\u0002\u0000\u0002L\u0000\u0004exp1t\u0000 Lcom/sun/msv/gra"
+"mmar/Expression;L\u0000\u0004exp2q\u0000~\u0000\u0002xr\u0000\u001ecom.sun.msv.grammar.Expressi"
+"on\u00f8\u0018\u0082\u00e8N5~O\u0002\u0000\u0002L\u0000\u0013epsilonReducibilityt\u0000\u0013Ljava/lang/Boolean;L\u0000\u000b"
+"expandedExpq\u0000~\u0000\u0002xpppsq\u0000~\u0000\u0000ppsq\u0000~\u0000\u0000ppsq\u0000~\u0000\u0000ppsq\u0000~\u0000\u0000ppsr\u0000\u001dcom."
+"sun.msv.grammar.ChoiceExp\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0001\u0002\u0000\u0000xq\u0000~\u0000\u0001ppsr\u0000 com.sun.msv."
+"grammar.OneOrMoreExp\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0001\u0002\u0000\u0000xr\u0000\u001ccom.sun.msv.grammar.Unary"
+"Exp\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0001\u0002\u0000\u0001L\u0000\u0003expq\u0000~\u0000\u0002xq\u0000~\u0000\u0003sr\u0000\u0011java.lang.Boolean\u00cd r\u0080\u00d5\u009c\u00fa\u00ee"
+"\u0002\u0000\u0001Z\u0000\u0005valuexp\u0000psr\u0000\'com.sun.msv.grammar.trex.ElementPattern\u0000\u0000"
+"\u0000\u0000\u0000\u0000\u0000\u0001\u0002\u0000\u0001L\u0000\tnameClasst\u0000\u001fLcom/sun/msv/grammar/NameClass;xr\u0000\u001ec"
+"om.sun.msv.grammar.ElementExp\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0001\u0002\u0000\u0002Z\u0000\u001aignoreUndeclaredA"
+"ttributesL\u0000\fcontentModelq\u0000~\u0000\u0002xq\u0000~\u0000\u0003q\u0000~\u0000\u0010p\u0000sq\u0000~\u0000\u0000ppsq\u0000~\u0000\u0011pp\u0000s"
+"q\u0000~\u0000\nppsq\u0000~\u0000\fq\u0000~\u0000\u0010psr\u0000 com.sun.msv.grammar.AttributeExp\u0000\u0000\u0000\u0000\u0000"
+"\u0000\u0000\u0001\u0002\u0000\u0002L\u0000\u0003expq\u0000~\u0000\u0002L\u0000\tnameClassq\u0000~\u0000\u0012xq\u0000~\u0000\u0003q\u0000~\u0000\u0010psr\u00002com.sun.ms"
+"v.grammar.Expression$AnyStringExpression\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0001\u0002\u0000\u0000xq\u0000~\u0000\u0003sq\u0000"
+"~\u0000\u000f\u0001q\u0000~\u0000\u001csr\u0000 com.sun.msv.grammar.AnyNameClass\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0001\u0002\u0000\u0000xr\u0000\u001d"
+"com.sun.msv.grammar.NameClass\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0001\u0002\u0000\u0000xpsr\u00000com.sun.msv.gr"
+"ammar.Expression$EpsilonExpression\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0001\u0002\u0000\u0000xq\u0000~\u0000\u0003q\u0000~\u0000\u001dq\u0000~\u0000"
+"\"sr\u0000#com.sun.msv.grammar.SimpleNameClass\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0001\u0002\u0000\u0002L\u0000\tlocalN"
+"amet\u0000\u0012Ljava/lang/String;L\u0000\fnamespaceURIq\u0000~\u0000$xq\u0000~\u0000\u001ft\u0000\u0018lexicon"
+".jaxb.EnglishTypet\u0000+http://java.sun.com/jaxb/xjc/dummy-eleme"
+"ntssq\u0000~\u0000\nppsq\u0000~\u0000\u0019q\u0000~\u0000\u0010psr\u0000\u001bcom.sun.msv.grammar.DataExp\u0000\u0000\u0000\u0000\u0000\u0000"
+"\u0000\u0001\u0002\u0000\u0003L\u0000\u0002dtt\u0000\u001fLorg/relaxng/datatype/Datatype;L\u0000\u0006exceptq\u0000~\u0000\u0002L\u0000"
+"\u0004namet\u0000\u001dLcom/sun/msv/util/StringPair;xq\u0000~\u0000\u0003ppsr\u0000\"com.sun.msv"
+".datatype.xsd.QnameType\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0001\u0002\u0000\u0000xr\u0000*com.sun.msv.datatype.x"
+"sd.BuiltinAtomicType\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0001\u0002\u0000\u0000xr\u0000%com.sun.msv.datatype.xsd."
+"ConcreteType\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0001\u0002\u0000\u0000xr\u0000\'com.sun.msv.datatype.xsd.XSDataty"
+"peImpl\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0001\u0002\u0000\u0003L\u0000\fnamespaceUriq\u0000~\u0000$L\u0000\btypeNameq\u0000~\u0000$L\u0000\nwhit"
+"eSpacet\u0000.Lcom/sun/msv/datatype/xsd/WhiteSpaceProcessor;xpt\u0000 "
+"http://www.w3.org/2001/XMLSchemat\u0000\u0005QNamesr\u00005com.sun.msv.data"
+"type.xsd.WhiteSpaceProcessor$Collapse\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0001\u0002\u0000\u0000xr\u0000,com.sun."
+"msv.datatype.xsd.WhiteSpaceProcessor\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0001\u0002\u0000\u0000xpsr\u00000com.sun"
+".msv.grammar.Expression$NullSetExpression\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0001\u0002\u0000\u0000xq\u0000~\u0000\u0003pp"
+"sr\u0000\u001bcom.sun.msv.util.StringPair\u00d0t\u001ejB\u008f\u008d\u00a0\u0002\u0000\u0002L\u0000\tlocalNameq\u0000~\u0000$L"
+"\u0000\fnamespaceURIq\u0000~\u0000$xpq\u0000~\u00005q\u0000~\u00004sq\u0000~\u0000#t\u0000\u0004typet\u0000)http://www.w3"
+".org/2001/XMLSchema-instanceq\u0000~\u0000\"sq\u0000~\u0000#t\u0000\u0007englisht\u0000\u0000q\u0000~\u0000\"sq\u0000"
+"~\u0000\nppsq\u0000~\u0000\fq\u0000~\u0000\u0010psq\u0000~\u0000\u0011q\u0000~\u0000\u0010p\u0000sq\u0000~\u0000\u0000ppsq\u0000~\u0000\u0011pp\u0000sq\u0000~\u0000\nppsq\u0000~\u0000"
+"\fq\u0000~\u0000\u0010psq\u0000~\u0000\u0019q\u0000~\u0000\u0010pq\u0000~\u0000\u001cq\u0000~\u0000 q\u0000~\u0000\"sq\u0000~\u0000#t\u0000\u0017lexicon.jaxb.Syns"
+"etTypeq\u0000~\u0000\'sq\u0000~\u0000\nppsq\u0000~\u0000\u0019q\u0000~\u0000\u0010pq\u0000~\u0000-q\u0000~\u0000=q\u0000~\u0000\"sq\u0000~\u0000#t\u0000\u0006synse"
+"tq\u0000~\u0000Bq\u0000~\u0000\"sq\u0000~\u0000\nppsq\u0000~\u0000\fq\u0000~\u0000\u0010psq\u0000~\u0000\u0011q\u0000~\u0000\u0010p\u0000sq\u0000~\u0000\u0000ppsq\u0000~\u0000\u0011pp"
+"\u0000sq\u0000~\u0000\nppsq\u0000~\u0000\fq\u0000~\u0000\u0010psq\u0000~\u0000\u0019q\u0000~\u0000\u0010pq\u0000~\u0000\u001cq\u0000~\u0000 q\u0000~\u0000\"sq\u0000~\u0000#t\u0000\u0018lex"
+"icon.jaxb.ExampleTypeq\u0000~\u0000\'sq\u0000~\u0000\nppsq\u0000~\u0000\u0019q\u0000~\u0000\u0010pq\u0000~\u0000-q\u0000~\u0000=q\u0000~\u0000"
+"\"sq\u0000~\u0000#t\u0000\u0007exampleq\u0000~\u0000Bq\u0000~\u0000\"sq\u0000~\u0000\nppsq\u0000~\u0000\u0019q\u0000~\u0000\u0010psq\u0000~\u0000*ppsr\u0000\"c"
+"om.sun.msv.datatype.xsd.TokenType\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0001\u0002\u0000\u0000xr\u0000#com.sun.msv."
+"datatype.xsd.StringType\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0001\u0002\u0000\u0001Z\u0000\risAlwaysValidxq\u0000~\u0000/q\u0000~\u0000"
+"4t\u0000\u0005tokenq\u0000~\u00008\u0001q\u0000~\u0000:sq\u0000~\u0000;q\u0000~\u0000eq\u0000~\u00004sq\u0000~\u0000#t\u0000\ndefinitionq\u0000~\u0000B"
+"q\u0000~\u0000\"sq\u0000~\u0000\u0019ppq\u0000~\u0000asq\u0000~\u0000#t\u0000\u0002idq\u0000~\u0000Bsq\u0000~\u0000\nppsq\u0000~\u0000\u0019q\u0000~\u0000\u0010pq\u0000~\u0000as"
+"q\u0000~\u0000#t\u0000\u0006weightq\u0000~\u0000Bq\u0000~\u0000\"sr\u0000\"com.sun.msv.grammar.ExpressionPo"
+"ol\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0001\u0002\u0000\u0001L\u0000\bexpTablet\u0000/Lcom/sun/msv/grammar/ExpressionPo"
+"ol$ClosedHash;xpsr\u0000-com.sun.msv.grammar.ExpressionPool$Close"
+"dHash\u00d7j\u00d0N\u00ef\u00e8\u00ed\u001c\u0003\u0000\u0003I\u0000\u0005countB\u0000\rstreamVersionL\u0000\u0006parentt\u0000$Lcom/sun"
+"/msv/grammar/ExpressionPool;xp\u0000\u0000\u0000\u0019\u0001pq\u0000~\u0000lq\u0000~\u0000\bq\u0000~\u0000\tq\u0000~\u0000\u0018q\u0000~\u0000"
+"Iq\u0000~\u0000Wq\u0000~\u0000\u0017q\u0000~\u0000Hq\u0000~\u0000Vq\u0000~\u0000\u0005q\u0000~\u0000_q\u0000~\u0000\u0007q\u0000~\u0000\u0006q\u0000~\u0000(q\u0000~\u0000\u000eq\u0000~\u0000Mq\u0000~\u0000"
+"\u0015q\u0000~\u0000Fq\u0000~\u0000Dq\u0000~\u0000[q\u0000~\u0000Tq\u0000~\u0000Rq\u0000~\u0000\u000bq\u0000~\u0000Cq\u0000~\u0000Qx"));
        }
        return new com.sun.msv.verifier.regexp.REDocumentDeclaration(schemaFragment);
    }

    public class Unmarshaller
        extends lexicon.jaxb.impl.runtime.AbstractUnmarshallingEventHandlerImpl
    {


        public Unmarshaller(lexicon.jaxb.impl.runtime.UnmarshallingContext context) {
            super(context, "-------------------");
        }

        protected Unmarshaller(lexicon.jaxb.impl.runtime.UnmarshallingContext context, int startState) {
            this(context);
            state = startState;
        }

        public java.lang.Object owner() {
            return lexicon.jaxb.impl.SenseTypeImpl.this;
        }

        public void enterElement(java.lang.String ___uri, java.lang.String ___local, java.lang.String ___qname, org.xml.sax.Attributes __atts)
            throws org.xml.sax.SAXException
        {
            int attIdx;
            outer:
            while (true) {
                switch (state) {
                    case  12 :
                        if (("english" == ___local)&&("" == ___uri)) {
                            context.pushAttributes(__atts, false);
                            state = 10;
                            return ;
                        }
                        if (("synset" == ___local)&&("" == ___uri)) {
                            context.pushAttributes(__atts, false);
                            state = 13;
                            return ;
                        }
                        state = 15;
                        continue outer;
                    case  3 :
                        attIdx = context.getAttribute("", "id");
                        if (attIdx >= 0) {
                            final java.lang.String v = context.eatAttribute(attIdx);
                            state = 6;
                            eatText1(v);
                            continue outer;
                        }
                        break;
                    case  0 :
                        attIdx = context.getAttribute("", "definition");
                        if (attIdx >= 0) {
                            final java.lang.String v = context.eatAttribute(attIdx);
                            state = 3;
                            eatText2(v);
                            continue outer;
                        }
                        state = 3;
                        continue outer;
                    case  10 :
                        attIdx = context.getAttribute("", "id");
                        if (attIdx >= 0) {
                            context.consumeAttribute(attIdx);
                            context.getCurrentHandler().enterElement(___uri, ___local, ___qname, __atts);
                            return ;
                        }
                        break;
                    case  9 :
                        if (("english" == ___local)&&("" == ___uri)) {
                            context.pushAttributes(__atts, false);
                            state = 10;
                            return ;
                        }
                        state = 12;
                        continue outer;
                    case  13 :
                        attIdx = context.getAttribute("", "id");
                        if (attIdx >= 0) {
                            context.consumeAttribute(attIdx);
                            context.getCurrentHandler().enterElement(___uri, ___local, ___qname, __atts);
                            return ;
                        }
                        break;
                    case  6 :
                        attIdx = context.getAttribute("", "weight");
                        if (attIdx >= 0) {
                            final java.lang.String v = context.eatAttribute(attIdx);
                            state = 9;
                            eatText3(v);
                            continue outer;
                        }
                        state = 9;
                        continue outer;
                    case  16 :
                        attIdx = context.getAttribute("", "id");
                        if (attIdx >= 0) {
                            context.consumeAttribute(attIdx);
                            context.getCurrentHandler().enterElement(___uri, ___local, ___qname, __atts);
                            return ;
                        }
                        break;
                    case  18 :
                        if (("example" == ___local)&&("" == ___uri)) {
                            context.pushAttributes(__atts, false);
                            state = 16;
                            return ;
                        }
                        revertToParentFromEnterElement(___uri, ___local, ___qname, __atts);
                        return ;
                    case  15 :
                        if (("synset" == ___local)&&("" == ___uri)) {
                            context.pushAttributes(__atts, false);
                            state = 13;
                            return ;
                        }
                        if (("example" == ___local)&&("" == ___uri)) {
                            context.pushAttributes(__atts, false);
                            state = 16;
                            return ;
                        }
                        state = 18;
                        continue outer;
                }
                super.enterElement(___uri, ___local, ___qname, __atts);
                break;
            }
        }

        private void eatText1(final java.lang.String value)
            throws org.xml.sax.SAXException
        {
            try {
                _Id = com.sun.xml.bind.WhiteSpaceProcessor.collapse(value);
            } catch (java.lang.Exception e) {
                handleParseConversionException(e);
            }
        }

        private void eatText2(final java.lang.String value)
            throws org.xml.sax.SAXException
        {
            try {
                _Definition = com.sun.xml.bind.WhiteSpaceProcessor.collapse(value);
            } catch (java.lang.Exception e) {
                handleParseConversionException(e);
            }
        }

        private void eatText3(final java.lang.String value)
            throws org.xml.sax.SAXException
        {
            try {
                _Weight = com.sun.xml.bind.WhiteSpaceProcessor.collapse(value);
            } catch (java.lang.Exception e) {
                handleParseConversionException(e);
            }
        }

        public void leaveElement(java.lang.String ___uri, java.lang.String ___local, java.lang.String ___qname)
            throws org.xml.sax.SAXException
        {
            int attIdx;
            outer:
            while (true) {
                switch (state) {
                    case  12 :
                        state = 15;
                        continue outer;
                    case  3 :
                        attIdx = context.getAttribute("", "id");
                        if (attIdx >= 0) {
                            final java.lang.String v = context.eatAttribute(attIdx);
                            state = 6;
                            eatText1(v);
                            continue outer;
                        }
                        break;
                    case  0 :
                        attIdx = context.getAttribute("", "definition");
                        if (attIdx >= 0) {
                            final java.lang.String v = context.eatAttribute(attIdx);
                            state = 3;
                            eatText2(v);
                            continue outer;
                        }
                        state = 3;
                        continue outer;
                    case  10 :
                        attIdx = context.getAttribute("", "id");
                        if (attIdx >= 0) {
                            context.consumeAttribute(attIdx);
                            context.getCurrentHandler().leaveElement(___uri, ___local, ___qname);
                            return ;
                        }
                        break;
                    case  9 :
                        state = 12;
                        continue outer;
                    case  11 :
                        if (("english" == ___local)&&("" == ___uri)) {
                            context.popAttributes();
                            state = 12;
                            return ;
                        }
                        break;
                    case  17 :
                        if (("example" == ___local)&&("" == ___uri)) {
                            context.popAttributes();
                            state = 18;
                            return ;
                        }
                        break;
                    case  13 :
                        attIdx = context.getAttribute("", "id");
                        if (attIdx >= 0) {
                            context.consumeAttribute(attIdx);
                            context.getCurrentHandler().leaveElement(___uri, ___local, ___qname);
                            return ;
                        }
                        break;
                    case  6 :
                        attIdx = context.getAttribute("", "weight");
                        if (attIdx >= 0) {
                            final java.lang.String v = context.eatAttribute(attIdx);
                            state = 9;
                            eatText3(v);
                            continue outer;
                        }
                        state = 9;
                        continue outer;
                    case  14 :
                        if (("synset" == ___local)&&("" == ___uri)) {
                            context.popAttributes();
                            state = 15;
                            return ;
                        }
                        break;
                    case  16 :
                        attIdx = context.getAttribute("", "id");
                        if (attIdx >= 0) {
                            context.consumeAttribute(attIdx);
                            context.getCurrentHandler().leaveElement(___uri, ___local, ___qname);
                            return ;
                        }
                        break;
                    case  18 :
                        revertToParentFromLeaveElement(___uri, ___local, ___qname);
                        return ;
                    case  15 :
                        state = 18;
                        continue outer;
                }
                super.leaveElement(___uri, ___local, ___qname);
                break;
            }
        }

        public void enterAttribute(java.lang.String ___uri, java.lang.String ___local, java.lang.String ___qname)
            throws org.xml.sax.SAXException
        {
            int attIdx;
            outer:
            while (true) {
                switch (state) {
                    case  12 :
                        state = 15;
                        continue outer;
                    case  3 :
                        if (("id" == ___local)&&("" == ___uri)) {
                            state = 4;
                            return ;
                        }
                        break;
                    case  0 :
                        if (("definition" == ___local)&&("" == ___uri)) {
                            state = 1;
                            return ;
                        }
                        state = 3;
                        continue outer;
                    case  10 :
                        if (("id" == ___local)&&("" == ___uri)) {
                            _getEnglish().add(((lexicon.jaxb.impl.EnglishTypeImpl) spawnChildFromEnterAttribute((lexicon.jaxb.impl.EnglishTypeImpl.class), 11, ___uri, ___local, ___qname)));
                            return ;
                        }
                        break;
                    case  9 :
                        state = 12;
                        continue outer;
                    case  13 :
                        if (("id" == ___local)&&("" == ___uri)) {
                            _getSynset().add(((lexicon.jaxb.impl.SynsetTypeImpl) spawnChildFromEnterAttribute((lexicon.jaxb.impl.SynsetTypeImpl.class), 14, ___uri, ___local, ___qname)));
                            return ;
                        }
                        break;
                    case  6 :
                        if (("weight" == ___local)&&("" == ___uri)) {
                            state = 7;
                            return ;
                        }
                        state = 9;
                        continue outer;
                    case  16 :
                        if (("id" == ___local)&&("" == ___uri)) {
                            _getExample().add(((lexicon.jaxb.impl.ExampleTypeImpl) spawnChildFromEnterAttribute((lexicon.jaxb.impl.ExampleTypeImpl.class), 17, ___uri, ___local, ___qname)));
                            return ;
                        }
                        break;
                    case  18 :
                        revertToParentFromEnterAttribute(___uri, ___local, ___qname);
                        return ;
                    case  15 :
                        state = 18;
                        continue outer;
                }
                super.enterAttribute(___uri, ___local, ___qname);
                break;
            }
        }

        public void leaveAttribute(java.lang.String ___uri, java.lang.String ___local, java.lang.String ___qname)
            throws org.xml.sax.SAXException
        {
            int attIdx;
            outer:
            while (true) {
                switch (state) {
                    case  12 :
                        state = 15;
                        continue outer;
                    case  3 :
                        attIdx = context.getAttribute("", "id");
                        if (attIdx >= 0) {
                            final java.lang.String v = context.eatAttribute(attIdx);
                            state = 6;
                            eatText1(v);
                            continue outer;
                        }
                        break;
                    case  0 :
                        attIdx = context.getAttribute("", "definition");
                        if (attIdx >= 0) {
                            final java.lang.String v = context.eatAttribute(attIdx);
                            state = 3;
                            eatText2(v);
                            continue outer;
                        }
                        state = 3;
                        continue outer;
                    case  10 :
                        attIdx = context.getAttribute("", "id");
                        if (attIdx >= 0) {
                            context.consumeAttribute(attIdx);
                            context.getCurrentHandler().leaveAttribute(___uri, ___local, ___qname);
                            return ;
                        }
                        break;
                    case  9 :
                        state = 12;
                        continue outer;
                    case  13 :
                        attIdx = context.getAttribute("", "id");
                        if (attIdx >= 0) {
                            context.consumeAttribute(attIdx);
                            context.getCurrentHandler().leaveAttribute(___uri, ___local, ___qname);
                            return ;
                        }
                        break;
                    case  5 :
                        if (("id" == ___local)&&("" == ___uri)) {
                            state = 6;
                            return ;
                        }
                        break;
                    case  6 :
                        attIdx = context.getAttribute("", "weight");
                        if (attIdx >= 0) {
                            final java.lang.String v = context.eatAttribute(attIdx);
                            state = 9;
                            eatText3(v);
                            continue outer;
                        }
                        state = 9;
                        continue outer;
                    case  16 :
                        attIdx = context.getAttribute("", "id");
                        if (attIdx >= 0) {
                            context.consumeAttribute(attIdx);
                            context.getCurrentHandler().leaveAttribute(___uri, ___local, ___qname);
                            return ;
                        }
                        break;
                    case  8 :
                        if (("weight" == ___local)&&("" == ___uri)) {
                            state = 9;
                            return ;
                        }
                        break;
                    case  18 :
                        revertToParentFromLeaveAttribute(___uri, ___local, ___qname);
                        return ;
                    case  2 :
                        if (("definition" == ___local)&&("" == ___uri)) {
                            state = 3;
                            return ;
                        }
                        break;
                    case  15 :
                        state = 18;
                        continue outer;
                }
                super.leaveAttribute(___uri, ___local, ___qname);
                break;
            }
        }

        public void handleText(final java.lang.String value)
            throws org.xml.sax.SAXException
        {
            int attIdx;
            outer:
            while (true) {
                try {
                    switch (state) {
                        case  12 :
                            state = 15;
                            continue outer;
                        case  4 :
                            state = 5;
                            eatText1(value);
                            return ;
                        case  3 :
                            attIdx = context.getAttribute("", "id");
                            if (attIdx >= 0) {
                                final java.lang.String v = context.eatAttribute(attIdx);
                                state = 6;
                                eatText1(v);
                                continue outer;
                            }
                            break;
                        case  0 :
                            attIdx = context.getAttribute("", "definition");
                            if (attIdx >= 0) {
                                final java.lang.String v = context.eatAttribute(attIdx);
                                state = 3;
                                eatText2(v);
                                continue outer;
                            }
                            state = 3;
                            continue outer;
                        case  10 :
                            attIdx = context.getAttribute("", "id");
                            if (attIdx >= 0) {
                                context.consumeAttribute(attIdx);
                                context.getCurrentHandler().text(value);
                                return ;
                            }
                            break;
                        case  9 :
                            state = 12;
                            continue outer;
                        case  13 :
                            attIdx = context.getAttribute("", "id");
                            if (attIdx >= 0) {
                                context.consumeAttribute(attIdx);
                                context.getCurrentHandler().text(value);
                                return ;
                            }
                            break;
                        case  6 :
                            attIdx = context.getAttribute("", "weight");
                            if (attIdx >= 0) {
                                final java.lang.String v = context.eatAttribute(attIdx);
                                state = 9;
                                eatText3(v);
                                continue outer;
                            }
                            state = 9;
                            continue outer;
                        case  16 :
                            attIdx = context.getAttribute("", "id");
                            if (attIdx >= 0) {
                                context.consumeAttribute(attIdx);
                                context.getCurrentHandler().text(value);
                                return ;
                            }
                            break;
                        case  18 :
                            revertToParentFromText(value);
                            return ;
                        case  15 :
                            state = 18;
                            continue outer;
                        case  7 :
                            state = 8;
                            eatText3(value);
                            return ;
                        case  1 :
                            state = 2;
                            eatText2(value);
                            return ;
                    }
                } catch (java.lang.RuntimeException e) {
                    handleUnexpectedTextException(value, e);
                }
                break;
            }
        }

    }

}