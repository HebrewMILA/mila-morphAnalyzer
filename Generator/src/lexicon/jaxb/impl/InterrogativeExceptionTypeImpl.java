//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v1.0.5-b16-fcs 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2010.01.10 at 01:33:09 PM IST 
//


package lexicon.jaxb.impl;

public class InterrogativeExceptionTypeImpl implements lexicon.jaxb.InterrogativeExceptionType, com.sun.xml.bind.JAXBObject, lexicon.jaxb.impl.runtime.UnmarshallableObject, lexicon.jaxb.impl.runtime.XMLSerializable, lexicon.jaxb.impl.runtime.ValidatableObject
{

    protected java.lang.String _Value;
    protected java.lang.String _Register;
    protected java.lang.String _PersonGenderNumber;
    protected java.lang.String _Undotted;
    protected java.lang.String _Dotted;
    protected java.lang.String _Spelling;
    protected java.lang.String _Transliterated;
    public final static java.lang.Class version = (lexicon.jaxb.impl.JAXBVersion.class);
    private static com.sun.msv.grammar.Grammar schemaFragment;

    public java.lang.String getValue() {
        return _Value;
    }

    public void setValue(java.lang.String value) {
        _Value = value;
    }

    public java.lang.String getRegister() {
        if (_Register == null) {
            return "formal";
        } else {
            return _Register;
        }
    }

    public void setRegister(java.lang.String value) {
        _Register = value;
    }

    public java.lang.String getPersonGenderNumber() {
        if (_PersonGenderNumber == null) {
            return "unspecified";
        } else {
            return _PersonGenderNumber;
        }
    }

    public void setPersonGenderNumber(java.lang.String value) {
        _PersonGenderNumber = value;
    }

    public java.lang.String getUndotted() {
        return _Undotted;
    }

    public void setUndotted(java.lang.String value) {
        _Undotted = value;
    }

    public java.lang.String getDotted() {
        if (_Dotted == null) {
            return "";
        } else {
            return _Dotted;
        }
    }

    public void setDotted(java.lang.String value) {
        _Dotted = value;
    }

    public java.lang.String getSpelling() {
        if (_Spelling == null) {
            return "standard";
        } else {
            return _Spelling;
        }
    }

    public void setSpelling(java.lang.String value) {
        _Spelling = value;
    }

    public java.lang.String getTransliterated() {
        return _Transliterated;
    }

    public void setTransliterated(java.lang.String value) {
        _Transliterated = value;
    }

    public lexicon.jaxb.impl.runtime.UnmarshallingEventHandler createUnmarshaller(lexicon.jaxb.impl.runtime.UnmarshallingContext context) {
        return new lexicon.jaxb.impl.InterrogativeExceptionTypeImpl.Unmarshaller(context);
    }

    public void serializeBody(lexicon.jaxb.impl.runtime.XMLSerializer context)
        throws org.xml.sax.SAXException
    {
        try {
            context.text(((java.lang.String) _Value), "Value");
        } catch (java.lang.Exception e) {
            lexicon.jaxb.impl.runtime.Util.handlePrintConversionException(this, e, context);
        }
    }

    public void serializeAttributes(lexicon.jaxb.impl.runtime.XMLSerializer context)
        throws org.xml.sax.SAXException
    {
        if (_Dotted!= null) {
            context.startAttribute("", "dotted");
            try {
                context.text(((java.lang.String) _Dotted), "Dotted");
            } catch (java.lang.Exception e) {
                lexicon.jaxb.impl.runtime.Util.handlePrintConversionException(this, e, context);
            }
            context.endAttribute();
        }
        if (_PersonGenderNumber!= null) {
            context.startAttribute("", "personGenderNumber");
            try {
                context.text(((java.lang.String) _PersonGenderNumber), "PersonGenderNumber");
            } catch (java.lang.Exception e) {
                lexicon.jaxb.impl.runtime.Util.handlePrintConversionException(this, e, context);
            }
            context.endAttribute();
        }
        if (_Register!= null) {
            context.startAttribute("", "register");
            try {
                context.text(((java.lang.String) _Register), "Register");
            } catch (java.lang.Exception e) {
                lexicon.jaxb.impl.runtime.Util.handlePrintConversionException(this, e, context);
            }
            context.endAttribute();
        }
        if (_Spelling!= null) {
            context.startAttribute("", "spelling");
            try {
                context.text(((java.lang.String) _Spelling), "Spelling");
            } catch (java.lang.Exception e) {
                lexicon.jaxb.impl.runtime.Util.handlePrintConversionException(this, e, context);
            }
            context.endAttribute();
        }
        context.startAttribute("", "transliterated");
        try {
            context.text(((java.lang.String) _Transliterated), "Transliterated");
        } catch (java.lang.Exception e) {
            lexicon.jaxb.impl.runtime.Util.handlePrintConversionException(this, e, context);
        }
        context.endAttribute();
        context.startAttribute("", "undotted");
        try {
            context.text(((java.lang.String) _Undotted), "Undotted");
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
        return (lexicon.jaxb.InterrogativeExceptionType.class);
    }

    public com.sun.msv.verifier.DocumentDeclaration createRawValidator() {
        if (schemaFragment == null) {
            schemaFragment = com.sun.xml.bind.validator.SchemaDeserializer.deserialize((
 "\u00ac\u00ed\u0000\u0005sr\u0000\u001fcom.sun.msv.grammar.SequenceExp\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0001\u0002\u0000\u0000xr\u0000\u001dcom.su"
+"n.msv.grammar.BinaryExp\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0001\u0002\u0000\u0002L\u0000\u0004exp1t\u0000 Lcom/sun/msv/gra"
+"mmar/Expression;L\u0000\u0004exp2q\u0000~\u0000\u0002xr\u0000\u001ecom.sun.msv.grammar.Expressi"
+"on\u00f8\u0018\u0082\u00e8N5~O\u0002\u0000\u0002L\u0000\u0013epsilonReducibilityt\u0000\u0013Ljava/lang/Boolean;L\u0000\u000b"
+"expandedExpq\u0000~\u0000\u0002xpppsq\u0000~\u0000\u0000ppsq\u0000~\u0000\u0000ppsq\u0000~\u0000\u0000ppsq\u0000~\u0000\u0000ppsq\u0000~\u0000\u0000pp"
+"sr\u0000\u001bcom.sun.msv.grammar.DataExp\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0001\u0002\u0000\u0003L\u0000\u0002dtt\u0000\u001fLorg/relax"
+"ng/datatype/Datatype;L\u0000\u0006exceptq\u0000~\u0000\u0002L\u0000\u0004namet\u0000\u001dLcom/sun/msv/ut"
+"il/StringPair;xq\u0000~\u0000\u0003ppsr\u0000\"com.sun.msv.datatype.xsd.TokenType"
+"\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0001\u0002\u0000\u0000xr\u0000#com.sun.msv.datatype.xsd.StringType\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0001\u0002\u0000"
+"\u0001Z\u0000\risAlwaysValidxr\u0000*com.sun.msv.datatype.xsd.BuiltinAtomicT"
+"ype\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0001\u0002\u0000\u0000xr\u0000%com.sun.msv.datatype.xsd.ConcreteType\u0000\u0000\u0000\u0000\u0000"
+"\u0000\u0000\u0001\u0002\u0000\u0000xr\u0000\'com.sun.msv.datatype.xsd.XSDatatypeImpl\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0001\u0002\u0000\u0003"
+"L\u0000\fnamespaceUrit\u0000\u0012Ljava/lang/String;L\u0000\btypeNameq\u0000~\u0000\u0014L\u0000\nwhite"
+"Spacet\u0000.Lcom/sun/msv/datatype/xsd/WhiteSpaceProcessor;xpt\u0000 h"
+"ttp://www.w3.org/2001/XMLSchemat\u0000\u0005tokensr\u00005com.sun.msv.datat"
+"ype.xsd.WhiteSpaceProcessor$Collapse\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0001\u0002\u0000\u0000xr\u0000,com.sun.m"
+"sv.datatype.xsd.WhiteSpaceProcessor\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0001\u0002\u0000\u0000xp\u0001sr\u00000com.sun"
+".msv.grammar.Expression$NullSetExpression\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0001\u0002\u0000\u0000xq\u0000~\u0000\u0003pp"
+"sr\u0000\u001bcom.sun.msv.util.StringPair\u00d0t\u001ejB\u008f\u008d\u00a0\u0002\u0000\u0002L\u0000\tlocalNameq\u0000~\u0000\u0014L"
+"\u0000\fnamespaceURIq\u0000~\u0000\u0014xpq\u0000~\u0000\u0018q\u0000~\u0000\u0017sr\u0000\u001dcom.sun.msv.grammar.Choic"
+"eExp\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0001\u0002\u0000\u0000xq\u0000~\u0000\u0001ppsr\u0000 com.sun.msv.grammar.AttributeExp\u0000"
+"\u0000\u0000\u0000\u0000\u0000\u0000\u0001\u0002\u0000\u0002L\u0000\u0003expq\u0000~\u0000\u0002L\u0000\tnameClasst\u0000\u001fLcom/sun/msv/grammar/Nam"
+"eClass;xq\u0000~\u0000\u0003sr\u0000\u0011java.lang.Boolean\u00cd r\u0080\u00d5\u009c\u00fa\u00ee\u0002\u0000\u0001Z\u0000\u0005valuexp\u0000pq\u0000~"
+"\u0000\u000esr\u0000#com.sun.msv.grammar.SimpleNameClass\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0001\u0002\u0000\u0002L\u0000\tlocal"
+"Nameq\u0000~\u0000\u0014L\u0000\fnamespaceURIq\u0000~\u0000\u0014xr\u0000\u001dcom.sun.msv.grammar.NameCla"
+"ss\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0001\u0002\u0000\u0000xpt\u0000\u0006dottedt\u0000\u0000sr\u00000com.sun.msv.grammar.Expressio"
+"n$EpsilonExpression\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0001\u0002\u0000\u0000xq\u0000~\u0000\u0003sq\u0000~\u0000%\u0001q\u0000~\u0000-sq\u0000~\u0000 ppsq\u0000~"
+"\u0000\"q\u0000~\u0000&psq\u0000~\u0000\u000bppsr\u0000)com.sun.msv.datatype.xsd.EnumerationFace"
+"t\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0001\u0002\u0000\u0001L\u0000\u0006valuest\u0000\u000fLjava/util/Set;xr\u00009com.sun.msv.datat"
+"ype.xsd.DataTypeWithValueConstraintFacet\"\u00a7Ro\u00ca\u00c7\u008aT\u0002\u0000\u0000xr\u0000*com.s"
+"un.msv.datatype.xsd.DataTypeWithFacet\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0001\u0002\u0000\u0005Z\u0000\fisFacetFi"
+"xedZ\u0000\u0012needValueCheckFlagL\u0000\bbaseTypet\u0000)Lcom/sun/msv/datatype/"
+"xsd/XSDatatypeImpl;L\u0000\fconcreteTypet\u0000\'Lcom/sun/msv/datatype/x"
+"sd/ConcreteType;L\u0000\tfacetNameq\u0000~\u0000\u0014xq\u0000~\u0000\u0013q\u0000~\u0000+t\u0000\u0007PGNTypeq\u0000~\u0000\u001b\u0000"
+"\u0000q\u0000~\u0000\u0016q\u0000~\u0000\u0016t\u0000\u000benumerationsr\u0000\u0011java.util.HashSet\u00baD\u0085\u0095\u0096\u00b8\u00b74\u0003\u0000\u0000xpw"
+"\f\u0000\u0000\u0000 ?@\u0000\u0000\u0000\u0000\u0000\u0013t\u0000\t123p/M/Sgt\u0000\u00072p/M/Plt\u0000\u00073p/M/Sgt\u0000\b1p/MF/Sgt\u0000\t1"
+"23p/M/Plt\u0000\b2p/MF/Plt\u0000\u00072p/M/Sgt\u0000\u00071p/F/Sgt\u0000\b3p/MF/Plt\u0000\u00073p/F/Pl"
+"t\u0000\u00071p/M/Sgt\u0000\u000bunspecifiedt\u0000\u00072p/F/Plt\u0000\t123p/F/Plt\u0000\u00073p/M/Plt\u0000\u00073"
+"p/F/Sgt\u0000\t123p/F/Sgt\u0000\b1p/MF/Plt\u0000\u00072p/F/Sgxq\u0000~\u0000\u001dsq\u0000~\u0000\u001eq\u0000~\u00009q\u0000~\u0000"
+"+sq\u0000~\u0000\'t\u0000\u0012personGenderNumberq\u0000~\u0000+q\u0000~\u0000-sq\u0000~\u0000 ppsq\u0000~\u0000\"q\u0000~\u0000&psq"
+"\u0000~\u0000\u000bppsq\u0000~\u00002q\u0000~\u0000+t\u0000\fRegisterTypeq\u0000~\u0000\u001b\u0000\u0000q\u0000~\u0000\u0016q\u0000~\u0000\u0016q\u0000~\u0000:sq\u0000~\u0000;"
+"w\f\u0000\u0000\u0000\u0010?@\u0000\u0000\u0000\u0000\u0000\u0003t\u0000\u0006formalt\u0000\u0006spokent\u0000\u0007archaicxq\u0000~\u0000\u001dsq\u0000~\u0000\u001eq\u0000~\u0000Wq"
+"\u0000~\u0000+sq\u0000~\u0000\'t\u0000\bregisterq\u0000~\u0000+q\u0000~\u0000-sq\u0000~\u0000 ppsq\u0000~\u0000\"q\u0000~\u0000&psq\u0000~\u0000\u000bpps"
+"q\u0000~\u00002q\u0000~\u0000+t\u0000\fSpellingTypeq\u0000~\u0000\u001b\u0000\u0000q\u0000~\u0000\u0016q\u0000~\u0000\u0016q\u0000~\u0000:sq\u0000~\u0000;w\f\u0000\u0000\u0000\u0010?"
+"@\u0000\u0000\u0000\u0000\u0000\u0002t\u0000\tirregulart\u0000\bstandardxq\u0000~\u0000\u001dsq\u0000~\u0000\u001eq\u0000~\u0000cq\u0000~\u0000+sq\u0000~\u0000\'t\u0000"
+"\bspellingq\u0000~\u0000+q\u0000~\u0000-sq\u0000~\u0000\"ppq\u0000~\u0000\u000esq\u0000~\u0000\'t\u0000\u000etransliteratedq\u0000~\u0000+"
+"sq\u0000~\u0000\"ppq\u0000~\u0000\u000esq\u0000~\u0000\'t\u0000\bundottedq\u0000~\u0000+sr\u0000\"com.sun.msv.grammar.E"
+"xpressionPool\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0001\u0002\u0000\u0001L\u0000\bexpTablet\u0000/Lcom/sun/msv/grammar/E"
+"xpressionPool$ClosedHash;xpsr\u0000-com.sun.msv.grammar.Expressio"
+"nPool$ClosedHash\u00d7j\u00d0N\u00ef\u00e8\u00ed\u001c\u0003\u0000\u0003I\u0000\u0005countB\u0000\rstreamVersionL\u0000\u0006parent"
+"t\u0000$Lcom/sun/msv/grammar/ExpressionPool;xp\u0000\u0000\u0000\n\u0001pq\u0000~\u0000/q\u0000~\u0000_q\u0000~"
+"\u0000\tq\u0000~\u0000Sq\u0000~\u0000\nq\u0000~\u0000\u0007q\u0000~\u0000\bq\u0000~\u0000!q\u0000~\u0000\u0006q\u0000~\u0000\u0005x"));
        }
        return new com.sun.msv.verifier.regexp.REDocumentDeclaration(schemaFragment);
    }

    public class Unmarshaller
        extends lexicon.jaxb.impl.runtime.AbstractUnmarshallingEventHandlerImpl
    {


        public Unmarshaller(lexicon.jaxb.impl.runtime.UnmarshallingContext context) {
            super(context, "--------------------");
        }

        protected Unmarshaller(lexicon.jaxb.impl.runtime.UnmarshallingContext context, int startState) {
            this(context);
            state = startState;
        }

        public java.lang.Object owner() {
            return lexicon.jaxb.impl.InterrogativeExceptionTypeImpl.this;
        }

        public void enterElement(java.lang.String ___uri, java.lang.String ___local, java.lang.String ___qname, org.xml.sax.Attributes __atts)
            throws org.xml.sax.SAXException
        {
            int attIdx;
            outer:
            while (true) {
                switch (state) {
                    case  19 :
                        revertToParentFromEnterElement(___uri, ___local, ___qname, __atts);
                        return ;
                    case  15 :
                        attIdx = context.getAttribute("", "undotted");
                        if (attIdx >= 0) {
                            final java.lang.String v = context.eatAttribute(attIdx);
                            state = 18;
                            eatText1(v);
                            continue outer;
                        }
                        break;
                    case  0 :
                        attIdx = context.getAttribute("", "dotted");
                        if (attIdx >= 0) {
                            final java.lang.String v = context.eatAttribute(attIdx);
                            state = 3;
                            eatText2(v);
                            continue outer;
                        }
                        state = 3;
                        continue outer;
                    case  9 :
                        attIdx = context.getAttribute("", "spelling");
                        if (attIdx >= 0) {
                            final java.lang.String v = context.eatAttribute(attIdx);
                            state = 12;
                            eatText3(v);
                            continue outer;
                        }
                        state = 12;
                        continue outer;
                    case  3 :
                        attIdx = context.getAttribute("", "personGenderNumber");
                        if (attIdx >= 0) {
                            final java.lang.String v = context.eatAttribute(attIdx);
                            state = 6;
                            eatText4(v);
                            continue outer;
                        }
                        state = 6;
                        continue outer;
                    case  6 :
                        attIdx = context.getAttribute("", "register");
                        if (attIdx >= 0) {
                            final java.lang.String v = context.eatAttribute(attIdx);
                            state = 9;
                            eatText5(v);
                            continue outer;
                        }
                        state = 9;
                        continue outer;
                    case  12 :
                        attIdx = context.getAttribute("", "transliterated");
                        if (attIdx >= 0) {
                            final java.lang.String v = context.eatAttribute(attIdx);
                            state = 15;
                            eatText6(v);
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
                _Undotted = com.sun.xml.bind.WhiteSpaceProcessor.collapse(value);
            } catch (java.lang.Exception e) {
                handleParseConversionException(e);
            }
        }

        private void eatText2(final java.lang.String value)
            throws org.xml.sax.SAXException
        {
            try {
                _Dotted = com.sun.xml.bind.WhiteSpaceProcessor.collapse(value);
            } catch (java.lang.Exception e) {
                handleParseConversionException(e);
            }
        }

        private void eatText3(final java.lang.String value)
            throws org.xml.sax.SAXException
        {
            try {
                _Spelling = com.sun.xml.bind.WhiteSpaceProcessor.collapse(value);
            } catch (java.lang.Exception e) {
                handleParseConversionException(e);
            }
        }

        private void eatText4(final java.lang.String value)
            throws org.xml.sax.SAXException
        {
            try {
                _PersonGenderNumber = com.sun.xml.bind.WhiteSpaceProcessor.collapse(value);
            } catch (java.lang.Exception e) {
                handleParseConversionException(e);
            }
        }

        private void eatText5(final java.lang.String value)
            throws org.xml.sax.SAXException
        {
            try {
                _Register = com.sun.xml.bind.WhiteSpaceProcessor.collapse(value);
            } catch (java.lang.Exception e) {
                handleParseConversionException(e);
            }
        }

        private void eatText6(final java.lang.String value)
            throws org.xml.sax.SAXException
        {
            try {
                _Transliterated = com.sun.xml.bind.WhiteSpaceProcessor.collapse(value);
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
                    case  19 :
                        revertToParentFromLeaveElement(___uri, ___local, ___qname);
                        return ;
                    case  15 :
                        attIdx = context.getAttribute("", "undotted");
                        if (attIdx >= 0) {
                            final java.lang.String v = context.eatAttribute(attIdx);
                            state = 18;
                            eatText1(v);
                            continue outer;
                        }
                        break;
                    case  0 :
                        attIdx = context.getAttribute("", "dotted");
                        if (attIdx >= 0) {
                            final java.lang.String v = context.eatAttribute(attIdx);
                            state = 3;
                            eatText2(v);
                            continue outer;
                        }
                        state = 3;
                        continue outer;
                    case  9 :
                        attIdx = context.getAttribute("", "spelling");
                        if (attIdx >= 0) {
                            final java.lang.String v = context.eatAttribute(attIdx);
                            state = 12;
                            eatText3(v);
                            continue outer;
                        }
                        state = 12;
                        continue outer;
                    case  3 :
                        attIdx = context.getAttribute("", "personGenderNumber");
                        if (attIdx >= 0) {
                            final java.lang.String v = context.eatAttribute(attIdx);
                            state = 6;
                            eatText4(v);
                            continue outer;
                        }
                        state = 6;
                        continue outer;
                    case  6 :
                        attIdx = context.getAttribute("", "register");
                        if (attIdx >= 0) {
                            final java.lang.String v = context.eatAttribute(attIdx);
                            state = 9;
                            eatText5(v);
                            continue outer;
                        }
                        state = 9;
                        continue outer;
                    case  12 :
                        attIdx = context.getAttribute("", "transliterated");
                        if (attIdx >= 0) {
                            final java.lang.String v = context.eatAttribute(attIdx);
                            state = 15;
                            eatText6(v);
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
                    case  19 :
                        revertToParentFromEnterAttribute(___uri, ___local, ___qname);
                        return ;
                    case  15 :
                        if (("undotted" == ___local)&&("" == ___uri)) {
                            state = 16;
                            return ;
                        }
                        break;
                    case  0 :
                        if (("dotted" == ___local)&&("" == ___uri)) {
                            state = 1;
                            return ;
                        }
                        state = 3;
                        continue outer;
                    case  9 :
                        if (("spelling" == ___local)&&("" == ___uri)) {
                            state = 10;
                            return ;
                        }
                        state = 12;
                        continue outer;
                    case  3 :
                        if (("personGenderNumber" == ___local)&&("" == ___uri)) {
                            state = 4;
                            return ;
                        }
                        state = 6;
                        continue outer;
                    case  6 :
                        if (("register" == ___local)&&("" == ___uri)) {
                            state = 7;
                            return ;
                        }
                        state = 9;
                        continue outer;
                    case  12 :
                        if (("transliterated" == ___local)&&("" == ___uri)) {
                            state = 13;
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
                    case  19 :
                        revertToParentFromLeaveAttribute(___uri, ___local, ___qname);
                        return ;
                    case  15 :
                        attIdx = context.getAttribute("", "undotted");
                        if (attIdx >= 0) {
                            final java.lang.String v = context.eatAttribute(attIdx);
                            state = 18;
                            eatText1(v);
                            continue outer;
                        }
                        break;
                    case  0 :
                        attIdx = context.getAttribute("", "dotted");
                        if (attIdx >= 0) {
                            final java.lang.String v = context.eatAttribute(attIdx);
                            state = 3;
                            eatText2(v);
                            continue outer;
                        }
                        state = 3;
                        continue outer;
                    case  2 :
                        if (("dotted" == ___local)&&("" == ___uri)) {
                            state = 3;
                            return ;
                        }
                        break;
                    case  8 :
                        if (("register" == ___local)&&("" == ___uri)) {
                            state = 9;
                            return ;
                        }
                        break;
                    case  9 :
                        attIdx = context.getAttribute("", "spelling");
                        if (attIdx >= 0) {
                            final java.lang.String v = context.eatAttribute(attIdx);
                            state = 12;
                            eatText3(v);
                            continue outer;
                        }
                        state = 12;
                        continue outer;
                    case  5 :
                        if (("personGenderNumber" == ___local)&&("" == ___uri)) {
                            state = 6;
                            return ;
                        }
                        break;
                    case  3 :
                        attIdx = context.getAttribute("", "personGenderNumber");
                        if (attIdx >= 0) {
                            final java.lang.String v = context.eatAttribute(attIdx);
                            state = 6;
                            eatText4(v);
                            continue outer;
                        }
                        state = 6;
                        continue outer;
                    case  6 :
                        attIdx = context.getAttribute("", "register");
                        if (attIdx >= 0) {
                            final java.lang.String v = context.eatAttribute(attIdx);
                            state = 9;
                            eatText5(v);
                            continue outer;
                        }
                        state = 9;
                        continue outer;
                    case  17 :
                        if (("undotted" == ___local)&&("" == ___uri)) {
                            state = 18;
                            return ;
                        }
                        break;
                    case  12 :
                        attIdx = context.getAttribute("", "transliterated");
                        if (attIdx >= 0) {
                            final java.lang.String v = context.eatAttribute(attIdx);
                            state = 15;
                            eatText6(v);
                            continue outer;
                        }
                        break;
                    case  11 :
                        if (("spelling" == ___local)&&("" == ___uri)) {
                            state = 12;
                            return ;
                        }
                        break;
                    case  14 :
                        if (("transliterated" == ___local)&&("" == ___uri)) {
                            state = 15;
                            return ;
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
                        case  18 :
                            state = 19;
                            eatText7(value);
                            return ;
                        case  19 :
                            revertToParentFromText(value);
                            return ;
                        case  15 :
                            attIdx = context.getAttribute("", "undotted");
                            if (attIdx >= 0) {
                                final java.lang.String v = context.eatAttribute(attIdx);
                                state = 18;
                                eatText1(v);
                                continue outer;
                            }
                            break;
                        case  0 :
                            attIdx = context.getAttribute("", "dotted");
                            if (attIdx >= 0) {
                                final java.lang.String v = context.eatAttribute(attIdx);
                                state = 3;
                                eatText2(v);
                                continue outer;
                            }
                            state = 3;
                            continue outer;
                        case  1 :
                            state = 2;
                            eatText2(value);
                            return ;
                        case  4 :
                            state = 5;
                            eatText4(value);
                            return ;
                        case  7 :
                            state = 8;
                            eatText5(value);
                            return ;
                        case  9 :
                            attIdx = context.getAttribute("", "spelling");
                            if (attIdx >= 0) {
                                final java.lang.String v = context.eatAttribute(attIdx);
                                state = 12;
                                eatText3(v);
                                continue outer;
                            }
                            state = 12;
                            continue outer;
                        case  10 :
                            state = 11;
                            eatText3(value);
                            return ;
                        case  13 :
                            state = 14;
                            eatText6(value);
                            return ;
                        case  3 :
                            attIdx = context.getAttribute("", "personGenderNumber");
                            if (attIdx >= 0) {
                                final java.lang.String v = context.eatAttribute(attIdx);
                                state = 6;
                                eatText4(v);
                                continue outer;
                            }
                            state = 6;
                            continue outer;
                        case  6 :
                            attIdx = context.getAttribute("", "register");
                            if (attIdx >= 0) {
                                final java.lang.String v = context.eatAttribute(attIdx);
                                state = 9;
                                eatText5(v);
                                continue outer;
                            }
                            state = 9;
                            continue outer;
                        case  12 :
                            attIdx = context.getAttribute("", "transliterated");
                            if (attIdx >= 0) {
                                final java.lang.String v = context.eatAttribute(attIdx);
                                state = 15;
                                eatText6(v);
                                continue outer;
                            }
                            break;
                        case  16 :
                            state = 17;
                            eatText1(value);
                            return ;
                    }
                } catch (java.lang.RuntimeException e) {
                    handleUnexpectedTextException(value, e);
                }
                break;
            }
        }

        private void eatText7(final java.lang.String value)
            throws org.xml.sax.SAXException
        {
            try {
                _Value = com.sun.xml.bind.WhiteSpaceProcessor.collapse(value);
            } catch (java.lang.Exception e) {
                handleParseConversionException(e);
            }
        }

    }

}
