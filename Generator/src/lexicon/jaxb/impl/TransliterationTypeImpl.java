//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v1.0.5-b16-fcs 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2010.01.10 at 01:33:09 PM IST 
//


package lexicon.jaxb.impl;

public class TransliterationTypeImpl implements lexicon.jaxb.TransliterationType, com.sun.xml.bind.JAXBObject, lexicon.jaxb.impl.runtime.UnmarshallableObject, lexicon.jaxb.impl.runtime.XMLSerializable, lexicon.jaxb.impl.runtime.ValidatableObject
{

    protected com.sun.xml.bind.util.ListImpl _String;
    protected java.lang.String _From;
    protected java.lang.String _To;
    public final static java.lang.Class version = (lexicon.jaxb.impl.JAXBVersion.class);
    private static com.sun.msv.grammar.Grammar schemaFragment;

    protected com.sun.xml.bind.util.ListImpl _getString() {
        if (_String == null) {
            _String = new com.sun.xml.bind.util.ListImpl(new java.util.ArrayList());
        }
        return _String;
    }

    public java.util.List getString() {
        return _getString();
    }

    public java.lang.String getFrom() {
        return _From;
    }

    public void setFrom(java.lang.String value) {
        _From = value;
    }

    public java.lang.String getTo() {
        return _To;
    }

    public void setTo(java.lang.String value) {
        _To = value;
    }

    public lexicon.jaxb.impl.runtime.UnmarshallingEventHandler createUnmarshaller(lexicon.jaxb.impl.runtime.UnmarshallingContext context) {
        return new lexicon.jaxb.impl.TransliterationTypeImpl.Unmarshaller(context);
    }

    public void serializeBody(lexicon.jaxb.impl.runtime.XMLSerializer context)
        throws org.xml.sax.SAXException
    {
        int idx1 = 0;
        final int len1 = ((_String == null)? 0 :_String.size());
        while (idx1 != len1) {
            context.startElement("", "string");
            int idx_0 = idx1;
            context.childAsURIs(((com.sun.xml.bind.JAXBObject) _String.get(idx_0 ++)), "String");
            context.endNamespaceDecls();
            int idx_1 = idx1;
            context.childAsAttributes(((com.sun.xml.bind.JAXBObject) _String.get(idx_1 ++)), "String");
            context.endAttributes();
            context.childAsBody(((com.sun.xml.bind.JAXBObject) _String.get(idx1 ++)), "String");
            context.endElement();
        }
    }

    public void serializeAttributes(lexicon.jaxb.impl.runtime.XMLSerializer context)
        throws org.xml.sax.SAXException
    {
        int idx1 = 0;
        final int len1 = ((_String == null)? 0 :_String.size());
        context.startAttribute("", "from");
        try {
            context.text(((java.lang.String) _From), "From");
        } catch (java.lang.Exception e) {
            lexicon.jaxb.impl.runtime.Util.handlePrintConversionException(this, e, context);
        }
        context.endAttribute();
        context.startAttribute("", "to");
        try {
            context.text(((java.lang.String) _To), "To");
        } catch (java.lang.Exception e) {
            lexicon.jaxb.impl.runtime.Util.handlePrintConversionException(this, e, context);
        }
        context.endAttribute();
        while (idx1 != len1) {
            idx1 += 1;
        }
    }

    public void serializeURIs(lexicon.jaxb.impl.runtime.XMLSerializer context)
        throws org.xml.sax.SAXException
    {
        int idx1 = 0;
        final int len1 = ((_String == null)? 0 :_String.size());
        while (idx1 != len1) {
            idx1 += 1;
        }
    }

    public java.lang.Class getPrimaryInterface() {
        return (lexicon.jaxb.TransliterationType.class);
    }

    public com.sun.msv.verifier.DocumentDeclaration createRawValidator() {
        if (schemaFragment == null) {
            schemaFragment = com.sun.xml.bind.validator.SchemaDeserializer.deserialize((
 "\u00ac\u00ed\u0000\u0005sr\u0000\u001fcom.sun.msv.grammar.SequenceExp\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0001\u0002\u0000\u0000xr\u0000\u001dcom.su"
+"n.msv.grammar.BinaryExp\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0001\u0002\u0000\u0002L\u0000\u0004exp1t\u0000 Lcom/sun/msv/gra"
+"mmar/Expression;L\u0000\u0004exp2q\u0000~\u0000\u0002xr\u0000\u001ecom.sun.msv.grammar.Expressi"
+"on\u00f8\u0018\u0082\u00e8N5~O\u0002\u0000\u0002L\u0000\u0013epsilonReducibilityt\u0000\u0013Ljava/lang/Boolean;L\u0000\u000b"
+"expandedExpq\u0000~\u0000\u0002xpppsq\u0000~\u0000\u0000ppsr\u0000 com.sun.msv.grammar.OneOrMor"
+"eExp\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0001\u0002\u0000\u0000xr\u0000\u001ccom.sun.msv.grammar.UnaryExp\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0001\u0002\u0000\u0001L\u0000"
+"\u0003expq\u0000~\u0000\u0002xq\u0000~\u0000\u0003ppsr\u0000\'com.sun.msv.grammar.trex.ElementPattern"
+"\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0001\u0002\u0000\u0001L\u0000\tnameClasst\u0000\u001fLcom/sun/msv/grammar/NameClass;xr\u0000"
+"\u001ecom.sun.msv.grammar.ElementExp\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0001\u0002\u0000\u0002Z\u0000\u001aignoreUndeclare"
+"dAttributesL\u0000\fcontentModelq\u0000~\u0000\u0002xq\u0000~\u0000\u0003pp\u0000sq\u0000~\u0000\u0000ppsq\u0000~\u0000\npp\u0000sr\u0000"
+"\u001dcom.sun.msv.grammar.ChoiceExp\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0001\u0002\u0000\u0000xq\u0000~\u0000\u0001ppsq\u0000~\u0000\u0007sr\u0000\u0011j"
+"ava.lang.Boolean\u00cd r\u0080\u00d5\u009c\u00fa\u00ee\u0002\u0000\u0001Z\u0000\u0005valuexp\u0000psr\u0000 com.sun.msv.gramm"
+"ar.AttributeExp\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0001\u0002\u0000\u0002L\u0000\u0003expq\u0000~\u0000\u0002L\u0000\tnameClassq\u0000~\u0000\u000bxq\u0000~\u0000\u0003"
+"q\u0000~\u0000\u0014psr\u00002com.sun.msv.grammar.Expression$AnyStringExpression"
+"\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0001\u0002\u0000\u0000xq\u0000~\u0000\u0003sq\u0000~\u0000\u0013\u0001q\u0000~\u0000\u0018sr\u0000 com.sun.msv.grammar.AnyName"
+"Class\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0001\u0002\u0000\u0000xr\u0000\u001dcom.sun.msv.grammar.NameClass\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0001\u0002\u0000\u0000"
+"xpsr\u00000com.sun.msv.grammar.Expression$EpsilonExpression\u0000\u0000\u0000\u0000\u0000\u0000"
+"\u0000\u0001\u0002\u0000\u0000xq\u0000~\u0000\u0003q\u0000~\u0000\u0019q\u0000~\u0000\u001esr\u0000#com.sun.msv.grammar.SimpleNameClass"
+"\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0001\u0002\u0000\u0002L\u0000\tlocalNamet\u0000\u0012Ljava/lang/String;L\u0000\fnamespaceURIq"
+"\u0000~\u0000 xq\u0000~\u0000\u001bt\u0000+lexicon.jaxb.TransliterationType.StringTypet\u0000+h"
+"ttp://java.sun.com/jaxb/xjc/dummy-elementssq\u0000~\u0000\u0010ppsq\u0000~\u0000\u0015q\u0000~\u0000"
+"\u0014psr\u0000\u001bcom.sun.msv.grammar.DataExp\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0001\u0002\u0000\u0003L\u0000\u0002dtt\u0000\u001fLorg/rel"
+"axng/datatype/Datatype;L\u0000\u0006exceptq\u0000~\u0000\u0002L\u0000\u0004namet\u0000\u001dLcom/sun/msv/"
+"util/StringPair;xq\u0000~\u0000\u0003ppsr\u0000\"com.sun.msv.datatype.xsd.QnameTy"
+"pe\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0001\u0002\u0000\u0000xr\u0000*com.sun.msv.datatype.xsd.BuiltinAtomicType\u0000"
+"\u0000\u0000\u0000\u0000\u0000\u0000\u0001\u0002\u0000\u0000xr\u0000%com.sun.msv.datatype.xsd.ConcreteType\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0001\u0002"
+"\u0000\u0000xr\u0000\'com.sun.msv.datatype.xsd.XSDatatypeImpl\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0001\u0002\u0000\u0003L\u0000\fn"
+"amespaceUriq\u0000~\u0000 L\u0000\btypeNameq\u0000~\u0000 L\u0000\nwhiteSpacet\u0000.Lcom/sun/msv"
+"/datatype/xsd/WhiteSpaceProcessor;xpt\u0000 http://www.w3.org/200"
+"1/XMLSchemat\u0000\u0005QNamesr\u00005com.sun.msv.datatype.xsd.WhiteSpacePr"
+"ocessor$Collapse\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0001\u0002\u0000\u0000xr\u0000,com.sun.msv.datatype.xsd.Whit"
+"eSpaceProcessor\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0001\u0002\u0000\u0000xpsr\u00000com.sun.msv.grammar.Expressi"
+"on$NullSetExpression\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0001\u0002\u0000\u0000xq\u0000~\u0000\u0003ppsr\u0000\u001bcom.sun.msv.util."
+"StringPair\u00d0t\u001ejB\u008f\u008d\u00a0\u0002\u0000\u0002L\u0000\tlocalNameq\u0000~\u0000 L\u0000\fnamespaceURIq\u0000~\u0000 xp"
+"q\u0000~\u00001q\u0000~\u00000sq\u0000~\u0000\u001ft\u0000\u0004typet\u0000)http://www.w3.org/2001/XMLSchema-i"
+"nstanceq\u0000~\u0000\u001esq\u0000~\u0000\u001ft\u0000\u0006stringt\u0000\u0000sq\u0000~\u0000\u0015ppsq\u0000~\u0000&ppsr\u0000%com.sun.ms"
+"v.datatype.xsd.LanguageType\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0001\u0002\u0000\u0000xr\u0000\"com.sun.msv.dataty"
+"pe.xsd.TokenType\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0001\u0002\u0000\u0000xr\u0000#com.sun.msv.datatype.xsd.Stri"
+"ngType\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0001\u0002\u0000\u0001Z\u0000\risAlwaysValidxq\u0000~\u0000+q\u0000~\u00000t\u0000\blanguageq\u0000~\u00004"
+"\u0000q\u0000~\u00006sq\u0000~\u00007q\u0000~\u0000Eq\u0000~\u00000sq\u0000~\u0000\u001ft\u0000\u0004fromq\u0000~\u0000>sq\u0000~\u0000\u0015ppq\u0000~\u0000@sq\u0000~\u0000\u001ft"
+"\u0000\u0002toq\u0000~\u0000>sr\u0000\"com.sun.msv.grammar.ExpressionPool\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0001\u0002\u0000\u0001L\u0000"
+"\bexpTablet\u0000/Lcom/sun/msv/grammar/ExpressionPool$ClosedHash;x"
+"psr\u0000-com.sun.msv.grammar.ExpressionPool$ClosedHash\u00d7j\u00d0N\u00ef\u00e8\u00ed\u001c\u0003\u0000"
+"\u0003I\u0000\u0005countB\u0000\rstreamVersionL\u0000\u0006parentt\u0000$Lcom/sun/msv/grammar/Ex"
+"pressionPool;xp\u0000\u0000\u0000\u0007\u0001pq\u0000~\u0000\u0012q\u0000~\u0000\u0006q\u0000~\u0000\u0011q\u0000~\u0000\u0005q\u0000~\u0000$q\u0000~\u0000\tq\u0000~\u0000\u000ex"));
        }
        return new com.sun.msv.verifier.regexp.REDocumentDeclaration(schemaFragment);
    }

    public static class StringTypeImpl implements lexicon.jaxb.TransliterationType.StringType, com.sun.xml.bind.JAXBObject, lexicon.jaxb.impl.runtime.UnmarshallableObject, lexicon.jaxb.impl.runtime.XMLSerializable, lexicon.jaxb.impl.runtime.ValidatableObject
    {

        protected java.lang.String _Latin;
        protected java.lang.String _Hebrew;
        public final static java.lang.Class version = (lexicon.jaxb.impl.JAXBVersion.class);
        private static com.sun.msv.grammar.Grammar schemaFragment;

        public java.lang.String getLatin() {
            return _Latin;
        }

        public void setLatin(java.lang.String value) {
            _Latin = value;
        }

        public java.lang.String getHebrew() {
            return _Hebrew;
        }

        public void setHebrew(java.lang.String value) {
            _Hebrew = value;
        }

        public lexicon.jaxb.impl.runtime.UnmarshallingEventHandler createUnmarshaller(lexicon.jaxb.impl.runtime.UnmarshallingContext context) {
            return new lexicon.jaxb.impl.TransliterationTypeImpl.StringTypeImpl.Unmarshaller(context);
        }

        public void serializeBody(lexicon.jaxb.impl.runtime.XMLSerializer context)
            throws org.xml.sax.SAXException
        {
        }

        public void serializeAttributes(lexicon.jaxb.impl.runtime.XMLSerializer context)
            throws org.xml.sax.SAXException
        {
            context.startAttribute("", "hebrew");
            try {
                context.text(((java.lang.String) _Hebrew), "Hebrew");
            } catch (java.lang.Exception e) {
                lexicon.jaxb.impl.runtime.Util.handlePrintConversionException(this, e, context);
            }
            context.endAttribute();
            context.startAttribute("", "latin");
            try {
                context.text(((java.lang.String) _Latin), "Latin");
            } catch (java.lang.Exception e) {
                lexicon.jaxb.impl.runtime.Util.handlePrintConversionException(this, e, context);
            }
            context.endAttribute();
        }

        public void serializeURIs(lexicon.jaxb.impl.runtime.XMLSerializer context)
            throws org.xml.sax.SAXException
        {
        }

        public java.lang.Class getPrimaryInterface() {
            return (lexicon.jaxb.TransliterationType.StringType.class);
        }

        public com.sun.msv.verifier.DocumentDeclaration createRawValidator() {
            if (schemaFragment == null) {
                schemaFragment = com.sun.xml.bind.validator.SchemaDeserializer.deserialize((
 "\u00ac\u00ed\u0000\u0005sr\u0000\u001fcom.sun.msv.grammar.SequenceExp\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0001\u0002\u0000\u0000xr\u0000\u001dcom.su"
+"n.msv.grammar.BinaryExp\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0001\u0002\u0000\u0002L\u0000\u0004exp1t\u0000 Lcom/sun/msv/gra"
+"mmar/Expression;L\u0000\u0004exp2q\u0000~\u0000\u0002xr\u0000\u001ecom.sun.msv.grammar.Expressi"
+"on\u00f8\u0018\u0082\u00e8N5~O\u0002\u0000\u0002L\u0000\u0013epsilonReducibilityt\u0000\u0013Ljava/lang/Boolean;L\u0000\u000b"
+"expandedExpq\u0000~\u0000\u0002xpppsr\u0000 com.sun.msv.grammar.AttributeExp\u0000\u0000\u0000\u0000"
+"\u0000\u0000\u0000\u0001\u0002\u0000\u0002L\u0000\u0003expq\u0000~\u0000\u0002L\u0000\tnameClasst\u0000\u001fLcom/sun/msv/grammar/NameCl"
+"ass;xq\u0000~\u0000\u0003ppsr\u0000\u001bcom.sun.msv.grammar.DataExp\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0001\u0002\u0000\u0003L\u0000\u0002dtt"
+"\u0000\u001fLorg/relaxng/datatype/Datatype;L\u0000\u0006exceptq\u0000~\u0000\u0002L\u0000\u0004namet\u0000\u001dLco"
+"m/sun/msv/util/StringPair;xq\u0000~\u0000\u0003ppsr\u0000#com.sun.msv.datatype.x"
+"sd.StringType\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0001\u0002\u0000\u0001Z\u0000\risAlwaysValidxr\u0000*com.sun.msv.data"
+"type.xsd.BuiltinAtomicType\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0001\u0002\u0000\u0000xr\u0000%com.sun.msv.datatyp"
+"e.xsd.ConcreteType\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0001\u0002\u0000\u0000xr\u0000\'com.sun.msv.datatype.xsd.XS"
+"DatatypeImpl\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0001\u0002\u0000\u0003L\u0000\fnamespaceUrit\u0000\u0012Ljava/lang/String;L"
+"\u0000\btypeNameq\u0000~\u0000\u0011L\u0000\nwhiteSpacet\u0000.Lcom/sun/msv/datatype/xsd/Whi"
+"teSpaceProcessor;xpt\u0000 http://www.w3.org/2001/XMLSchemat\u0000\u0006str"
+"ingsr\u00005com.sun.msv.datatype.xsd.WhiteSpaceProcessor$Preserve"
+"\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0001\u0002\u0000\u0000xr\u0000,com.sun.msv.datatype.xsd.WhiteSpaceProcessor\u0000"
+"\u0000\u0000\u0000\u0000\u0000\u0000\u0001\u0002\u0000\u0000xp\u0001sr\u00000com.sun.msv.grammar.Expression$NullSetExpre"
+"ssion\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0001\u0002\u0000\u0000xq\u0000~\u0000\u0003ppsr\u0000\u001bcom.sun.msv.util.StringPair\u00d0t\u001ejB"
+"\u008f\u008d\u00a0\u0002\u0000\u0002L\u0000\tlocalNameq\u0000~\u0000\u0011L\u0000\fnamespaceURIq\u0000~\u0000\u0011xpq\u0000~\u0000\u0015q\u0000~\u0000\u0014sr\u0000#c"
+"om.sun.msv.grammar.SimpleNameClass\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0001\u0002\u0000\u0002L\u0000\tlocalNameq\u0000~"
+"\u0000\u0011L\u0000\fnamespaceURIq\u0000~\u0000\u0011xr\u0000\u001dcom.sun.msv.grammar.NameClass\u0000\u0000\u0000\u0000\u0000"
+"\u0000\u0000\u0001\u0002\u0000\u0000xpt\u0000\u0006hebrewt\u0000\u0000sq\u0000~\u0000\u0006ppq\u0000~\u0000\fsq\u0000~\u0000\u001dt\u0000\u0005latinq\u0000~\u0000!sr\u0000\"com."
+"sun.msv.grammar.ExpressionPool\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0001\u0002\u0000\u0001L\u0000\bexpTablet\u0000/Lcom/"
+"sun/msv/grammar/ExpressionPool$ClosedHash;xpsr\u0000-com.sun.msv."
+"grammar.ExpressionPool$ClosedHash\u00d7j\u00d0N\u00ef\u00e8\u00ed\u001c\u0003\u0000\u0003I\u0000\u0005countB\u0000\rstrea"
+"mVersionL\u0000\u0006parentt\u0000$Lcom/sun/msv/grammar/ExpressionPool;xp\u0000\u0000"
+"\u0000\u0001\u0001pq\u0000~\u0000\u0005x"));
            }
            return new com.sun.msv.verifier.regexp.REDocumentDeclaration(schemaFragment);
        }

        public class Unmarshaller
            extends lexicon.jaxb.impl.runtime.AbstractUnmarshallingEventHandlerImpl
        {


            public Unmarshaller(lexicon.jaxb.impl.runtime.UnmarshallingContext context) {
                super(context, "-------");
            }

            protected Unmarshaller(lexicon.jaxb.impl.runtime.UnmarshallingContext context, int startState) {
                this(context);
                state = startState;
            }

            public java.lang.Object owner() {
                return lexicon.jaxb.impl.TransliterationTypeImpl.StringTypeImpl.this;
            }

            public void enterElement(java.lang.String ___uri, java.lang.String ___local, java.lang.String ___qname, org.xml.sax.Attributes __atts)
                throws org.xml.sax.SAXException
            {
                int attIdx;
                outer:
                while (true) {
                    switch (state) {
                        case  6 :
                            revertToParentFromEnterElement(___uri, ___local, ___qname, __atts);
                            return ;
                        case  3 :
                            attIdx = context.getAttribute("", "latin");
                            if (attIdx >= 0) {
                                final java.lang.String v = context.eatAttribute(attIdx);
                                state = 6;
                                eatText1(v);
                                continue outer;
                            }
                            break;
                        case  0 :
                            attIdx = context.getAttribute("", "hebrew");
                            if (attIdx >= 0) {
                                final java.lang.String v = context.eatAttribute(attIdx);
                                state = 3;
                                eatText2(v);
                                continue outer;
                            }
                            break;
                    }
                    super.enterElement(___uri, ___local, ___qname, __atts);
                    break;
                }
            }

            private void eatText1(final java.lang.String value)
                throws org.xml.sax.SAXException
            {
                try {
                    _Latin = value;
                } catch (java.lang.Exception e) {
                    handleParseConversionException(e);
                }
            }

            private void eatText2(final java.lang.String value)
                throws org.xml.sax.SAXException
            {
                try {
                    _Hebrew = value;
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
                        case  6 :
                            revertToParentFromLeaveElement(___uri, ___local, ___qname);
                            return ;
                        case  3 :
                            attIdx = context.getAttribute("", "latin");
                            if (attIdx >= 0) {
                                final java.lang.String v = context.eatAttribute(attIdx);
                                state = 6;
                                eatText1(v);
                                continue outer;
                            }
                            break;
                        case  0 :
                            attIdx = context.getAttribute("", "hebrew");
                            if (attIdx >= 0) {
                                final java.lang.String v = context.eatAttribute(attIdx);
                                state = 3;
                                eatText2(v);
                                continue outer;
                            }
                            break;
                    }
                    super.leaveElement(___uri, ___local, ___qname);
                    break;
                }
            }

            public void enterAttribute(java.lang.String ___uri, java.lang.String ___local, java.lang.String ___qname)
                throws org.xml.sax.SAXException
            {
                outer:
                while (true) {
                    switch (state) {
                        case  6 :
                            revertToParentFromEnterAttribute(___uri, ___local, ___qname);
                            return ;
                        case  3 :
                            if (("latin" == ___local)&&("" == ___uri)) {
                                state = 4;
                                return ;
                            }
                            break;
                        case  0 :
                            if (("hebrew" == ___local)&&("" == ___uri)) {
                                state = 1;
                                return ;
                            }
                            break;
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
                        case  2 :
                            if (("hebrew" == ___local)&&("" == ___uri)) {
                                state = 3;
                                return ;
                            }
                            break;
                        case  6 :
                            revertToParentFromLeaveAttribute(___uri, ___local, ___qname);
                            return ;
                        case  3 :
                            attIdx = context.getAttribute("", "latin");
                            if (attIdx >= 0) {
                                final java.lang.String v = context.eatAttribute(attIdx);
                                state = 6;
                                eatText1(v);
                                continue outer;
                            }
                            break;
                        case  5 :
                            if (("latin" == ___local)&&("" == ___uri)) {
                                state = 6;
                                return ;
                            }
                            break;
                        case  0 :
                            attIdx = context.getAttribute("", "hebrew");
                            if (attIdx >= 0) {
                                final java.lang.String v = context.eatAttribute(attIdx);
                                state = 3;
                                eatText2(v);
                                continue outer;
                            }
                            break;
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
                            case  4 :
                                state = 5;
                                eatText1(value);
                                return ;
                            case  6 :
                                revertToParentFromText(value);
                                return ;
                            case  1 :
                                state = 2;
                                eatText2(value);
                                return ;
                            case  3 :
                                attIdx = context.getAttribute("", "latin");
                                if (attIdx >= 0) {
                                    final java.lang.String v = context.eatAttribute(attIdx);
                                    state = 6;
                                    eatText1(v);
                                    continue outer;
                                }
                                break;
                            case  0 :
                                attIdx = context.getAttribute("", "hebrew");
                                if (attIdx >= 0) {
                                    final java.lang.String v = context.eatAttribute(attIdx);
                                    state = 3;
                                    eatText2(v);
                                    continue outer;
                                }
                                break;
                        }
                    } catch (java.lang.RuntimeException e) {
                        handleUnexpectedTextException(value, e);
                    }
                    break;
                }
            }

        }

    }

    public class Unmarshaller
        extends lexicon.jaxb.impl.runtime.AbstractUnmarshallingEventHandlerImpl
    {


        public Unmarshaller(lexicon.jaxb.impl.runtime.UnmarshallingContext context) {
            super(context, "----------");
        }

        protected Unmarshaller(lexicon.jaxb.impl.runtime.UnmarshallingContext context, int startState) {
            this(context);
            state = startState;
        }

        public java.lang.Object owner() {
            return lexicon.jaxb.impl.TransliterationTypeImpl.this;
        }

        public void enterElement(java.lang.String ___uri, java.lang.String ___local, java.lang.String ___qname, org.xml.sax.Attributes __atts)
            throws org.xml.sax.SAXException
        {
            int attIdx;
            outer:
            while (true) {
                switch (state) {
                    case  6 :
                        if (("string" == ___local)&&("" == ___uri)) {
                            context.pushAttributes(__atts, false);
                            state = 7;
                            return ;
                        }
                        break;
                    case  7 :
                        attIdx = context.getAttribute("", "hebrew");
                        if (attIdx >= 0) {
                            context.consumeAttribute(attIdx);
                            context.getCurrentHandler().enterElement(___uri, ___local, ___qname, __atts);
                            return ;
                        }
                        break;
                    case  3 :
                        attIdx = context.getAttribute("", "to");
                        if (attIdx >= 0) {
                            final java.lang.String v = context.eatAttribute(attIdx);
                            state = 6;
                            eatText1(v);
                            continue outer;
                        }
                        break;
                    case  9 :
                        if (("string" == ___local)&&("" == ___uri)) {
                            context.pushAttributes(__atts, false);
                            state = 7;
                            return ;
                        }
                        revertToParentFromEnterElement(___uri, ___local, ___qname, __atts);
                        return ;
                    case  0 :
                        attIdx = context.getAttribute("", "from");
                        if (attIdx >= 0) {
                            final java.lang.String v = context.eatAttribute(attIdx);
                            state = 3;
                            eatText2(v);
                            continue outer;
                        }
                        break;
                }
                super.enterElement(___uri, ___local, ___qname, __atts);
                break;
            }
        }

        private void eatText1(final java.lang.String value)
            throws org.xml.sax.SAXException
        {
            try {
                _To = com.sun.xml.bind.WhiteSpaceProcessor.collapse(value);
            } catch (java.lang.Exception e) {
                handleParseConversionException(e);
            }
        }

        private void eatText2(final java.lang.String value)
            throws org.xml.sax.SAXException
        {
            try {
                _From = com.sun.xml.bind.WhiteSpaceProcessor.collapse(value);
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
                    case  7 :
                        attIdx = context.getAttribute("", "hebrew");
                        if (attIdx >= 0) {
                            context.consumeAttribute(attIdx);
                            context.getCurrentHandler().leaveElement(___uri, ___local, ___qname);
                            return ;
                        }
                        break;
                    case  8 :
                        if (("string" == ___local)&&("" == ___uri)) {
                            context.popAttributes();
                            state = 9;
                            return ;
                        }
                        break;
                    case  3 :
                        attIdx = context.getAttribute("", "to");
                        if (attIdx >= 0) {
                            final java.lang.String v = context.eatAttribute(attIdx);
                            state = 6;
                            eatText1(v);
                            continue outer;
                        }
                        break;
                    case  9 :
                        revertToParentFromLeaveElement(___uri, ___local, ___qname);
                        return ;
                    case  0 :
                        attIdx = context.getAttribute("", "from");
                        if (attIdx >= 0) {
                            final java.lang.String v = context.eatAttribute(attIdx);
                            state = 3;
                            eatText2(v);
                            continue outer;
                        }
                        break;
                }
                super.leaveElement(___uri, ___local, ___qname);
                break;
            }
        }

        public void enterAttribute(java.lang.String ___uri, java.lang.String ___local, java.lang.String ___qname)
            throws org.xml.sax.SAXException
        {
            outer:
            while (true) {
                switch (state) {
                    case  7 :
                        if (("hebrew" == ___local)&&("" == ___uri)) {
                            _getString().add(((lexicon.jaxb.impl.TransliterationTypeImpl.StringTypeImpl) spawnChildFromEnterAttribute((lexicon.jaxb.impl.TransliterationTypeImpl.StringTypeImpl.class), 8, ___uri, ___local, ___qname)));
                            return ;
                        }
                        break;
                    case  3 :
                        if (("to" == ___local)&&("" == ___uri)) {
                            state = 4;
                            return ;
                        }
                        break;
                    case  9 :
                        revertToParentFromEnterAttribute(___uri, ___local, ___qname);
                        return ;
                    case  0 :
                        if (("from" == ___local)&&("" == ___uri)) {
                            state = 1;
                            return ;
                        }
                        break;
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
                    case  7 :
                        attIdx = context.getAttribute("", "hebrew");
                        if (attIdx >= 0) {
                            context.consumeAttribute(attIdx);
                            context.getCurrentHandler().leaveAttribute(___uri, ___local, ___qname);
                            return ;
                        }
                        break;
                    case  2 :
                        if (("from" == ___local)&&("" == ___uri)) {
                            state = 3;
                            return ;
                        }
                        break;
                    case  5 :
                        if (("to" == ___local)&&("" == ___uri)) {
                            state = 6;
                            return ;
                        }
                        break;
                    case  3 :
                        attIdx = context.getAttribute("", "to");
                        if (attIdx >= 0) {
                            final java.lang.String v = context.eatAttribute(attIdx);
                            state = 6;
                            eatText1(v);
                            continue outer;
                        }
                        break;
                    case  9 :
                        revertToParentFromLeaveAttribute(___uri, ___local, ___qname);
                        return ;
                    case  0 :
                        attIdx = context.getAttribute("", "from");
                        if (attIdx >= 0) {
                            final java.lang.String v = context.eatAttribute(attIdx);
                            state = 3;
                            eatText2(v);
                            continue outer;
                        }
                        break;
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
                        case  7 :
                            attIdx = context.getAttribute("", "hebrew");
                            if (attIdx >= 0) {
                                context.consumeAttribute(attIdx);
                                context.getCurrentHandler().text(value);
                                return ;
                            }
                            break;
                        case  1 :
                            state = 2;
                            eatText2(value);
                            return ;
                        case  4 :
                            state = 5;
                            eatText1(value);
                            return ;
                        case  3 :
                            attIdx = context.getAttribute("", "to");
                            if (attIdx >= 0) {
                                final java.lang.String v = context.eatAttribute(attIdx);
                                state = 6;
                                eatText1(v);
                                continue outer;
                            }
                            break;
                        case  9 :
                            revertToParentFromText(value);
                            return ;
                        case  0 :
                            attIdx = context.getAttribute("", "from");
                            if (attIdx >= 0) {
                                final java.lang.String v = context.eatAttribute(attIdx);
                                state = 3;
                                eatText2(v);
                                continue outer;
                            }
                            break;
                    }
                } catch (java.lang.RuntimeException e) {
                    handleUnexpectedTextException(value, e);
                }
                break;
            }
        }

    }

}
