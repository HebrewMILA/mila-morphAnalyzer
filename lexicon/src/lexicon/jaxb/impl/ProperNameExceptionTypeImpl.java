//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v1.0.5-b16-fcs 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2010.12.23 at 10:34:08 AM IST 
//

package lexicon.jaxb.impl;

public class ProperNameExceptionTypeImpl implements
		lexicon.jaxb.ProperNameExceptionType, com.sun.xml.bind.JAXBObject,
		lexicon.jaxb.impl.runtime.UnmarshallableObject,
		lexicon.jaxb.impl.runtime.XMLSerializable,
		lexicon.jaxb.impl.runtime.ValidatableObject {

	protected java.lang.String _Type;
	protected java.lang.String _Direction;
	protected java.lang.String _Value;
	protected java.lang.String _Register;
	protected java.lang.String _Gender;
	protected java.lang.String _Number;
	protected java.lang.String _Undotted;
	protected java.lang.String _Dotted;
	protected java.lang.String _Spelling;
	protected java.lang.String _Transliterated;
	protected java.lang.String _Definiteness;
	public final static java.lang.Class version = (lexicon.jaxb.impl.JAXBVersion.class);
	private static com.sun.msv.grammar.Grammar schemaFragment;

	private final static java.lang.Class PRIMARY_INTERFACE_CLASS() {
		return (lexicon.jaxb.ProperNameExceptionType.class);
	}

	public java.lang.String getType() {
		if (_Type == null) {
			return "person";
		} else {
			return _Type;
		}
	}

	public void setType(java.lang.String value) {
		_Type = value;
	}

	public java.lang.String getDirection() {
		if (_Direction == null) {
			return "unspecified";
		} else {
			return _Direction;
		}
	}

	public void setDirection(java.lang.String value) {
		_Direction = value;
	}

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

	public java.lang.String getGender() {
		if (_Gender == null) {
			return "unspecified";
		} else {
			return _Gender;
		}
	}

	public void setGender(java.lang.String value) {
		_Gender = value;
	}

	public java.lang.String getNumber() {
		if (_Number == null) {
			return "unspecified";
		} else {
			return _Number;
		}
	}

	public void setNumber(java.lang.String value) {
		_Number = value;
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

	public java.lang.String getDefiniteness() {
		if (_Definiteness == null) {
			return "prohibited";
		} else {
			return _Definiteness;
		}
	}

	public void setDefiniteness(java.lang.String value) {
		_Definiteness = value;
	}

	public lexicon.jaxb.impl.runtime.UnmarshallingEventHandler createUnmarshaller(
			lexicon.jaxb.impl.runtime.UnmarshallingContext context) {
		return new lexicon.jaxb.impl.ProperNameExceptionTypeImpl.Unmarshaller(
				context);
	}

	public void serializeBody(lexicon.jaxb.impl.runtime.XMLSerializer context)
			throws org.xml.sax.SAXException {
		try {
			context.text(((java.lang.String) _Value), "Value");
		} catch (java.lang.Exception e) {
			lexicon.jaxb.impl.runtime.Util.handlePrintConversionException(this,
					e, context);
		}
	}

	public void serializeAttributes(
			lexicon.jaxb.impl.runtime.XMLSerializer context)
			throws org.xml.sax.SAXException {
		if (_Definiteness != null) {
			context.startAttribute("", "definiteness");
			try {
				context.text(((java.lang.String) _Definiteness), "Definiteness");
			} catch (java.lang.Exception e) {
				lexicon.jaxb.impl.runtime.Util.handlePrintConversionException(
						this, e, context);
			}
			context.endAttribute();
		}
		if (_Direction != null) {
			context.startAttribute("", "direction");
			try {
				context.text(((java.lang.String) _Direction), "Direction");
			} catch (java.lang.Exception e) {
				lexicon.jaxb.impl.runtime.Util.handlePrintConversionException(
						this, e, context);
			}
			context.endAttribute();
		}
		if (_Dotted != null) {
			context.startAttribute("", "dotted");
			try {
				context.text(((java.lang.String) _Dotted), "Dotted");
			} catch (java.lang.Exception e) {
				lexicon.jaxb.impl.runtime.Util.handlePrintConversionException(
						this, e, context);
			}
			context.endAttribute();
		}
		if (_Gender != null) {
			context.startAttribute("", "gender");
			try {
				context.text(((java.lang.String) _Gender), "Gender");
			} catch (java.lang.Exception e) {
				lexicon.jaxb.impl.runtime.Util.handlePrintConversionException(
						this, e, context);
			}
			context.endAttribute();
		}
		if (_Number != null) {
			context.startAttribute("", "number");
			try {
				context.text(((java.lang.String) _Number), "Number");
			} catch (java.lang.Exception e) {
				lexicon.jaxb.impl.runtime.Util.handlePrintConversionException(
						this, e, context);
			}
			context.endAttribute();
		}
		if (_Register != null) {
			context.startAttribute("", "register");
			try {
				context.text(((java.lang.String) _Register), "Register");
			} catch (java.lang.Exception e) {
				lexicon.jaxb.impl.runtime.Util.handlePrintConversionException(
						this, e, context);
			}
			context.endAttribute();
		}
		if (_Spelling != null) {
			context.startAttribute("", "spelling");
			try {
				context.text(((java.lang.String) _Spelling), "Spelling");
			} catch (java.lang.Exception e) {
				lexicon.jaxb.impl.runtime.Util.handlePrintConversionException(
						this, e, context);
			}
			context.endAttribute();
		}
		context.startAttribute("", "transliterated");
		try {
			context.text(((java.lang.String) _Transliterated), "Transliterated");
		} catch (java.lang.Exception e) {
			lexicon.jaxb.impl.runtime.Util.handlePrintConversionException(this,
					e, context);
		}
		context.endAttribute();
		if (_Type != null) {
			context.startAttribute("", "type");
			try {
				context.text(((java.lang.String) _Type), "Type");
			} catch (java.lang.Exception e) {
				lexicon.jaxb.impl.runtime.Util.handlePrintConversionException(
						this, e, context);
			}
			context.endAttribute();
		}
		context.startAttribute("", "undotted");
		try {
			context.text(((java.lang.String) _Undotted), "Undotted");
		} catch (java.lang.Exception e) {
			lexicon.jaxb.impl.runtime.Util.handlePrintConversionException(this,
					e, context);
		}
		context.endAttribute();
	}

	public void serializeURIs(lexicon.jaxb.impl.runtime.XMLSerializer context)
			throws org.xml.sax.SAXException {
	}

	public java.lang.Class getPrimaryInterface() {
		return (lexicon.jaxb.ProperNameExceptionType.class);
	}

	public com.sun.msv.verifier.DocumentDeclaration createRawValidator() {
		if (schemaFragment == null) {
			schemaFragment = com.sun.xml.bind.validator.SchemaDeserializer
					.deserialize(("\u00ac\u00ed\u0000\u0005sr\u0000\u001fcom.sun.msv.grammar.SequenceExp\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0001\u0002\u0000\u0000xr\u0000\u001dcom.su"
							+ "n.msv.grammar.BinaryExp\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0001\u0002\u0000\u0002L\u0000\u0004exp1t\u0000 Lcom/sun/msv/gra"
							+ "mmar/Expression;L\u0000\u0004exp2q\u0000~\u0000\u0002xr\u0000\u001ecom.sun.msv.grammar.Expressi"
							+ "on\u00f8\u0018\u0082\u00e8N5~O\u0002\u0000\u0002L\u0000\u0013epsilonReducibilityt\u0000\u0013Ljava/lang/Boolean;L\u0000\u000b"
							+ "expandedExpq\u0000~\u0000\u0002xpppsq\u0000~\u0000\u0000ppsq\u0000~\u0000\u0000ppsq\u0000~\u0000\u0000ppsq\u0000~\u0000\u0000ppsq\u0000~\u0000\u0000pp"
							+ "sq\u0000~\u0000\u0000ppsq\u0000~\u0000\u0000ppsq\u0000~\u0000\u0000ppsq\u0000~\u0000\u0000ppsr\u0000\u001bcom.sun.msv.grammar.Data"
							+ "Exp\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0001\u0002\u0000\u0003L\u0000\u0002dtt\u0000\u001fLorg/relaxng/datatype/Datatype;L\u0000\u0006exce"
							+ "ptq\u0000~\u0000\u0002L\u0000\u0004namet\u0000\u001dLcom/sun/msv/util/StringPair;xq\u0000~\u0000\u0003ppsr\u0000\"co"
							+ "m.sun.msv.datatype.xsd.TokenType\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0001\u0002\u0000\u0000xr\u0000#com.sun.msv.d"
							+ "atatype.xsd.StringType\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0001\u0002\u0000\u0001Z\u0000\risAlwaysValidxr\u0000*com.sun"
							+ ".msv.datatype.xsd.BuiltinAtomicType\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0001\u0002\u0000\u0000xr\u0000%com.sun.ms"
							+ "v.datatype.xsd.ConcreteType\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0001\u0002\u0000\u0000xr\u0000\'com.sun.msv.dataty"
							+ "pe.xsd.XSDatatypeImpl\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0001\u0002\u0000\u0003L\u0000\fnamespaceUrit\u0000\u0012Ljava/lang"
							+ "/String;L\u0000\btypeNameq\u0000~\u0000\u0018L\u0000\nwhiteSpacet\u0000.Lcom/sun/msv/datatyp"
							+ "e/xsd/WhiteSpaceProcessor;xpt\u0000 http://www.w3.org/2001/XMLSch"
							+ "emat\u0000\u0005tokensr\u00005com.sun.msv.datatype.xsd.WhiteSpaceProcessor$"
							+ "Collapse\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0001\u0002\u0000\u0000xr\u0000,com.sun.msv.datatype.xsd.WhiteSpacePr"
							+ "ocessor\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0001\u0002\u0000\u0000xp\u0001sr\u00000com.sun.msv.grammar.Expression$Null"
							+ "SetExpression\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0001\u0002\u0000\u0000xq\u0000~\u0000\u0003ppsr\u0000\u001bcom.sun.msv.util.StringP"
							+ "air\u00d0t\u001ejB\u008f\u008d\u00a0\u0002\u0000\u0002L\u0000\tlocalNameq\u0000~\u0000\u0018L\u0000\fnamespaceURIq\u0000~\u0000\u0018xpq\u0000~\u0000\u001cq\u0000"
							+ "~\u0000\u001bsr\u0000\u001dcom.sun.msv.grammar.ChoiceExp\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0001\u0002\u0000\u0000xq\u0000~\u0000\u0001ppsr\u0000 c"
							+ "om.sun.msv.grammar.AttributeExp\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0001\u0002\u0000\u0002L\u0000\u0003expq\u0000~\u0000\u0002L\u0000\tname"
							+ "Classt\u0000\u001fLcom/sun/msv/grammar/NameClass;xq\u0000~\u0000\u0003sr\u0000\u0011java.lang.B"
							+ "oolean\u00cd r\u0080\u00d5\u009c\u00fa\u00ee\u0002\u0000\u0001Z\u0000\u0005valuexp\u0000psq\u0000~\u0000\u000fppsr\u0000)com.sun.msv.datatyp"
							+ "e.xsd.EnumerationFacet\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0001\u0002\u0000\u0001L\u0000\u0006valuest\u0000\u000fLjava/util/Set;"
							+ "xr\u00009com.sun.msv.datatype.xsd.DataTypeWithValueConstraintFace"
							+ "t\"\u00a7Ro\u00ca\u00c7\u008aT\u0002\u0000\u0000xr\u0000*com.sun.msv.datatype.xsd.DataTypeWithFacet\u0000\u0000"
							+ "\u0000\u0000\u0000\u0000\u0000\u0001\u0002\u0000\u0005Z\u0000\fisFacetFixedZ\u0000\u0012needValueCheckFlagL\u0000\bbaseTypet\u0000)L"
							+ "com/sun/msv/datatype/xsd/XSDatatypeImpl;L\u0000\fconcreteTypet\u0000\'Lc"
							+ "om/sun/msv/datatype/xsd/ConcreteType;L\u0000\tfacetNameq\u0000~\u0000\u0018xq\u0000~\u0000\u0017"
							+ "t\u0000\u0000t\u0000\u000fDefinitnessTypeq\u0000~\u0000\u001f\u0000\u0000q\u0000~\u0000\u001aq\u0000~\u0000\u001at\u0000\u000benumerationsr\u0000\u0011java"
							+ ".util.HashSet\u00baD\u0085\u0095\u0096\u00b8\u00b74\u0003\u0000\u0000xpw\f\u0000\u0000\u0000\u0010?@\u0000\u0000\u0000\u0000\u0000\u0006t\u0000\nprohibitedt\u0000\bopti"
							+ "onalt\u0000\brequiredt\u0000\ndefinitedlt\u0000\bexternalt\u0000\binternalxq\u0000~\u0000!sq\u0000~"
							+ "\u0000\"q\u0000~\u00004q\u0000~\u00003sr\u0000#com.sun.msv.grammar.SimpleNameClass\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0001\u0002"
							+ "\u0000\u0002L\u0000\tlocalNameq\u0000~\u0000\u0018L\u0000\fnamespaceURIq\u0000~\u0000\u0018xr\u0000\u001dcom.sun.msv.gramm"
							+ "ar.NameClass\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0001\u0002\u0000\u0000xpt\u0000\fdefinitenessq\u0000~\u00003sr\u00000com.sun.msv"
							+ ".grammar.Expression$EpsilonExpression\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0001\u0002\u0000\u0000xq\u0000~\u0000\u0003sq\u0000~\u0000)"
							+ "\u0001q\u0000~\u0000Dsq\u0000~\u0000$ppsq\u0000~\u0000&q\u0000~\u0000*psq\u0000~\u0000\u000fppsq\u0000~\u0000,q\u0000~\u00003t\u0000\u000eThreeStateTy"
							+ "peq\u0000~\u0000\u001f\u0000\u0000q\u0000~\u0000\u001aq\u0000~\u0000\u001aq\u0000~\u00005sq\u0000~\u00006w\f\u0000\u0000\u0000\u0010?@\u0000\u0000\u0000\u0000\u0000\u0003t\u0000\u000bunspecifiedt\u0000"
							+ "\u0004truet\u0000\u0005falsexq\u0000~\u0000!sq\u0000~\u0000\"q\u0000~\u0000Jq\u0000~\u00003sq\u0000~\u0000?t\u0000\tdirectionq\u0000~\u00003q\u0000"
							+ "~\u0000Dsq\u0000~\u0000$ppsq\u0000~\u0000&q\u0000~\u0000*pq\u0000~\u0000\u0012sq\u0000~\u0000?t\u0000\u0006dottedq\u0000~\u00003q\u0000~\u0000Dsq\u0000~\u0000$p"
							+ "psq\u0000~\u0000&q\u0000~\u0000*psq\u0000~\u0000\u000fppsq\u0000~\u0000,q\u0000~\u00003t\u0000\nGenderTypeq\u0000~\u0000\u001f\u0000\u0000q\u0000~\u0000\u001aq\u0000~"
							+ "\u0000\u001aq\u0000~\u00005sq\u0000~\u00006w\f\u0000\u0000\u0000\u0010?@\u0000\u0000\u0000\u0000\u0000\u0004t\u0000\u000bunspecifiedt\u0000\bfemininet\u0000\u0016mascu"
							+ "line and femininet\u0000\tmasculinexq\u0000~\u0000!sq\u0000~\u0000\"q\u0000~\u0000Zq\u0000~\u00003sq\u0000~\u0000?t\u0000\u0006"
							+ "genderq\u0000~\u00003q\u0000~\u0000Dsq\u0000~\u0000$ppsq\u0000~\u0000&q\u0000~\u0000*psq\u0000~\u0000\u000fppsq\u0000~\u0000,q\u0000~\u00003t\u0000\nNu"
							+ "mberTypeq\u0000~\u0000\u001f\u0000\u0000q\u0000~\u0000\u001aq\u0000~\u0000\u001aq\u0000~\u00005sq\u0000~\u00006w\f\u0000\u0000\u0000\u0010?@\u0000\u0000\u0000\u0000\u0000\u0006t\u0000\u000bunspeci"
							+ "fiedt\u0000\u0013singular and pluralt\u0000\u0004dualt\u0000\u000fdual and pluralt\u0000\bsingul"
							+ "art\u0000\u0006pluralxq\u0000~\u0000!sq\u0000~\u0000\"q\u0000~\u0000gq\u0000~\u00003sq\u0000~\u0000?t\u0000\u0006numberq\u0000~\u00003q\u0000~\u0000Dsq"
							+ "\u0000~\u0000$ppsq\u0000~\u0000&q\u0000~\u0000*psq\u0000~\u0000\u000fppsq\u0000~\u0000,q\u0000~\u00003t\u0000\fRegisterTypeq\u0000~\u0000\u001f\u0000\u0000q"
							+ "\u0000~\u0000\u001aq\u0000~\u0000\u001aq\u0000~\u00005sq\u0000~\u00006w\f\u0000\u0000\u0000\u0010?@\u0000\u0000\u0000\u0000\u0000\u0003t\u0000\u0006formalt\u0000\u0006spokent\u0000\u0007archa"
							+ "icxq\u0000~\u0000!sq\u0000~\u0000\"q\u0000~\u0000vq\u0000~\u00003sq\u0000~\u0000?t\u0000\bregisterq\u0000~\u00003q\u0000~\u0000Dsq\u0000~\u0000$pps"
							+ "q\u0000~\u0000&q\u0000~\u0000*psq\u0000~\u0000\u000fppsq\u0000~\u0000,q\u0000~\u00003t\u0000\fSpellingTypeq\u0000~\u0000\u001f\u0000\u0000q\u0000~\u0000\u001aq\u0000~"
							+ "\u0000\u001aq\u0000~\u00005sq\u0000~\u00006w\f\u0000\u0000\u0000\u0010?@\u0000\u0000\u0000\u0000\u0000\u0002t\u0000\tirregulart\u0000\bstandardxq\u0000~\u0000!sq\u0000~"
							+ "\u0000\"q\u0000~\u0000\u0082q\u0000~\u00003sq\u0000~\u0000?t\u0000\bspellingq\u0000~\u00003q\u0000~\u0000Dsq\u0000~\u0000&ppq\u0000~\u0000\u0012sq\u0000~\u0000?t\u0000"
							+ "\u000etransliteratedq\u0000~\u00003sq\u0000~\u0000$ppsq\u0000~\u0000&q\u0000~\u0000*psq\u0000~\u0000\u000fppsq\u0000~\u0000,q\u0000~\u00003t"
							+ "\u0000\u000fNamedEntityTypeq\u0000~\u0000\u001f\u0000\u0000q\u0000~\u0000\u001aq\u0000~\u0000\u001aq\u0000~\u00005sq\u0000~\u00006w\f\u0000\u0000\u0000\u0010?@\u0000\u0000\u0000\u0000\u0000\u000bt"
							+ "\u0000\u000bunspecifiedt\u0000\u0007productt\u0000\u0005othert\u0000\bdateTimet\u0000\u0007countryt\u0000\u0004townt"
							+ "\u0000\u0006persont\u0000\blocationt\u0000\blanguaget\u0000\u0006symbolt\u0000\forganizationxq\u0000~\u0000!"
							+ "sq\u0000~\u0000\"q\u0000~\u0000\u0090q\u0000~\u00003sq\u0000~\u0000?t\u0000\u0004typeq\u0000~\u00003q\u0000~\u0000Dsq\u0000~\u0000&ppq\u0000~\u0000\u0012sq\u0000~\u0000?t\u0000"
							+ "\bundottedq\u0000~\u00003sr\u0000\"com.sun.msv.grammar.ExpressionPool\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0001"
							+ "\u0002\u0000\u0001L\u0000\bexpTablet\u0000/Lcom/sun/msv/grammar/ExpressionPool$ClosedH"
							+ "ash;xpsr\u0000-com.sun.msv.grammar.ExpressionPool$ClosedHash\u00d7j\u00d0N\u00ef"
							+ "\u00e8\u00ed\u001c\u0003\u0000\u0003I\u0000\u0005countB\u0000\rstreamVersionL\u0000\u0006parentt\u0000$Lcom/sun/msv/gramm"
							+ "ar/ExpressionPool;xp\u0000\u0000\u0000\u0012\u0001pq\u0000~\u0000Vq\u0000~\u0000Fq\u0000~\u0000\bq\u0000~\u0000\u0006q\u0000~\u0000rq\u0000~\u0000\fq\u0000~\u0000"
							+ "\u000eq\u0000~\u0000%q\u0000~\u0000\u0005q\u0000~\u0000\nq\u0000~\u0000~q\u0000~\u0000cq\u0000~\u0000Rq\u0000~\u0000\u000bq\u0000~\u0000\rq\u0000~\u0000\tq\u0000~\u0000\u008cq\u0000~\u0000\u0007x"));
		}
		return new com.sun.msv.verifier.regexp.REDocumentDeclaration(
				schemaFragment);
	}

	public class Unmarshaller extends
			lexicon.jaxb.impl.runtime.AbstractUnmarshallingEventHandlerImpl {

		public Unmarshaller(
				lexicon.jaxb.impl.runtime.UnmarshallingContext context) {
			super(context, "--------------------------------");
		}

		protected Unmarshaller(
				lexicon.jaxb.impl.runtime.UnmarshallingContext context,
				int startState) {
			this(context);
			state = startState;
		}

		public java.lang.Object owner() {
			return lexicon.jaxb.impl.ProperNameExceptionTypeImpl.this;
		}

		public void enterElement(java.lang.String ___uri,
				java.lang.String ___local, java.lang.String ___qname,
				org.xml.sax.Attributes __atts) throws org.xml.sax.SAXException {
			int attIdx;
			outer: while (true) {
				switch (state) {
				case 3:
					attIdx = context.getAttribute("", "direction");
					if (attIdx >= 0) {
						final java.lang.String v = context.eatAttribute(attIdx);
						state = 6;
						eatText1(v);
						continue outer;
					}
					state = 6;
					continue outer;
				case 0:
					attIdx = context.getAttribute("", "definiteness");
					if (attIdx >= 0) {
						final java.lang.String v = context.eatAttribute(attIdx);
						state = 3;
						eatText2(v);
						continue outer;
					}
					state = 3;
					continue outer;
				case 21:
					attIdx = context.getAttribute("", "transliterated");
					if (attIdx >= 0) {
						final java.lang.String v = context.eatAttribute(attIdx);
						state = 24;
						eatText3(v);
						continue outer;
					}
					break;
				case 6:
					attIdx = context.getAttribute("", "dotted");
					if (attIdx >= 0) {
						final java.lang.String v = context.eatAttribute(attIdx);
						state = 9;
						eatText4(v);
						continue outer;
					}
					state = 9;
					continue outer;
				case 15:
					attIdx = context.getAttribute("", "register");
					if (attIdx >= 0) {
						final java.lang.String v = context.eatAttribute(attIdx);
						state = 18;
						eatText5(v);
						continue outer;
					}
					state = 18;
					continue outer;
				case 18:
					attIdx = context.getAttribute("", "spelling");
					if (attIdx >= 0) {
						final java.lang.String v = context.eatAttribute(attIdx);
						state = 21;
						eatText6(v);
						continue outer;
					}
					state = 21;
					continue outer;
				case 12:
					attIdx = context.getAttribute("", "number");
					if (attIdx >= 0) {
						final java.lang.String v = context.eatAttribute(attIdx);
						state = 15;
						eatText7(v);
						continue outer;
					}
					state = 15;
					continue outer;
				case 9:
					attIdx = context.getAttribute("", "gender");
					if (attIdx >= 0) {
						final java.lang.String v = context.eatAttribute(attIdx);
						state = 12;
						eatText8(v);
						continue outer;
					}
					state = 12;
					continue outer;
				case 27:
					attIdx = context.getAttribute("", "undotted");
					if (attIdx >= 0) {
						final java.lang.String v = context.eatAttribute(attIdx);
						state = 30;
						eatText9(v);
						continue outer;
					}
					break;
				case 24:
					attIdx = context.getAttribute("", "type");
					if (attIdx >= 0) {
						final java.lang.String v = context.eatAttribute(attIdx);
						state = 27;
						eatText10(v);
						continue outer;
					}
					state = 27;
					continue outer;
				case 31:
					revertToParentFromEnterElement(___uri, ___local, ___qname,
							__atts);
					return;
				}
				super.enterElement(___uri, ___local, ___qname, __atts);
				break;
			}
		}

		private void eatText1(final java.lang.String value)
				throws org.xml.sax.SAXException {
			try {
				_Direction = com.sun.xml.bind.WhiteSpaceProcessor
						.collapse(value);
			} catch (java.lang.Exception e) {
				handleParseConversionException(e);
			}
		}

		private void eatText2(final java.lang.String value)
				throws org.xml.sax.SAXException {
			try {
				_Definiteness = com.sun.xml.bind.WhiteSpaceProcessor
						.collapse(value);
			} catch (java.lang.Exception e) {
				handleParseConversionException(e);
			}
		}

		private void eatText3(final java.lang.String value)
				throws org.xml.sax.SAXException {
			try {
				_Transliterated = com.sun.xml.bind.WhiteSpaceProcessor
						.collapse(value);
			} catch (java.lang.Exception e) {
				handleParseConversionException(e);
			}
		}

		private void eatText4(final java.lang.String value)
				throws org.xml.sax.SAXException {
			try {
				_Dotted = com.sun.xml.bind.WhiteSpaceProcessor.collapse(value);
			} catch (java.lang.Exception e) {
				handleParseConversionException(e);
			}
		}

		private void eatText5(final java.lang.String value)
				throws org.xml.sax.SAXException {
			try {
				_Register = com.sun.xml.bind.WhiteSpaceProcessor
						.collapse(value);
			} catch (java.lang.Exception e) {
				handleParseConversionException(e);
			}
		}

		private void eatText6(final java.lang.String value)
				throws org.xml.sax.SAXException {
			try {
				_Spelling = com.sun.xml.bind.WhiteSpaceProcessor
						.collapse(value);
			} catch (java.lang.Exception e) {
				handleParseConversionException(e);
			}
		}

		private void eatText7(final java.lang.String value)
				throws org.xml.sax.SAXException {
			try {
				_Number = com.sun.xml.bind.WhiteSpaceProcessor.collapse(value);
			} catch (java.lang.Exception e) {
				handleParseConversionException(e);
			}
		}

		private void eatText8(final java.lang.String value)
				throws org.xml.sax.SAXException {
			try {
				_Gender = com.sun.xml.bind.WhiteSpaceProcessor.collapse(value);
			} catch (java.lang.Exception e) {
				handleParseConversionException(e);
			}
		}

		private void eatText9(final java.lang.String value)
				throws org.xml.sax.SAXException {
			try {
				_Undotted = com.sun.xml.bind.WhiteSpaceProcessor
						.collapse(value);
			} catch (java.lang.Exception e) {
				handleParseConversionException(e);
			}
		}

		private void eatText10(final java.lang.String value)
				throws org.xml.sax.SAXException {
			try {
				_Type = com.sun.xml.bind.WhiteSpaceProcessor.collapse(value);
			} catch (java.lang.Exception e) {
				handleParseConversionException(e);
			}
		}

		public void leaveElement(java.lang.String ___uri,
				java.lang.String ___local, java.lang.String ___qname)
				throws org.xml.sax.SAXException {
			int attIdx;
			outer: while (true) {
				switch (state) {
				case 3:
					attIdx = context.getAttribute("", "direction");
					if (attIdx >= 0) {
						final java.lang.String v = context.eatAttribute(attIdx);
						state = 6;
						eatText1(v);
						continue outer;
					}
					state = 6;
					continue outer;
				case 0:
					attIdx = context.getAttribute("", "definiteness");
					if (attIdx >= 0) {
						final java.lang.String v = context.eatAttribute(attIdx);
						state = 3;
						eatText2(v);
						continue outer;
					}
					state = 3;
					continue outer;
				case 21:
					attIdx = context.getAttribute("", "transliterated");
					if (attIdx >= 0) {
						final java.lang.String v = context.eatAttribute(attIdx);
						state = 24;
						eatText3(v);
						continue outer;
					}
					break;
				case 6:
					attIdx = context.getAttribute("", "dotted");
					if (attIdx >= 0) {
						final java.lang.String v = context.eatAttribute(attIdx);
						state = 9;
						eatText4(v);
						continue outer;
					}
					state = 9;
					continue outer;
				case 15:
					attIdx = context.getAttribute("", "register");
					if (attIdx >= 0) {
						final java.lang.String v = context.eatAttribute(attIdx);
						state = 18;
						eatText5(v);
						continue outer;
					}
					state = 18;
					continue outer;
				case 18:
					attIdx = context.getAttribute("", "spelling");
					if (attIdx >= 0) {
						final java.lang.String v = context.eatAttribute(attIdx);
						state = 21;
						eatText6(v);
						continue outer;
					}
					state = 21;
					continue outer;
				case 12:
					attIdx = context.getAttribute("", "number");
					if (attIdx >= 0) {
						final java.lang.String v = context.eatAttribute(attIdx);
						state = 15;
						eatText7(v);
						continue outer;
					}
					state = 15;
					continue outer;
				case 9:
					attIdx = context.getAttribute("", "gender");
					if (attIdx >= 0) {
						final java.lang.String v = context.eatAttribute(attIdx);
						state = 12;
						eatText8(v);
						continue outer;
					}
					state = 12;
					continue outer;
				case 27:
					attIdx = context.getAttribute("", "undotted");
					if (attIdx >= 0) {
						final java.lang.String v = context.eatAttribute(attIdx);
						state = 30;
						eatText9(v);
						continue outer;
					}
					break;
				case 24:
					attIdx = context.getAttribute("", "type");
					if (attIdx >= 0) {
						final java.lang.String v = context.eatAttribute(attIdx);
						state = 27;
						eatText10(v);
						continue outer;
					}
					state = 27;
					continue outer;
				case 31:
					revertToParentFromLeaveElement(___uri, ___local, ___qname);
					return;
				}
				super.leaveElement(___uri, ___local, ___qname);
				break;
			}
		}

		public void enterAttribute(java.lang.String ___uri,
				java.lang.String ___local, java.lang.String ___qname)
				throws org.xml.sax.SAXException {
			int attIdx;
			outer: while (true) {
				switch (state) {
				case 3:
					if (("direction" == ___local) && ("" == ___uri)) {
						state = 4;
						return;
					}
					state = 6;
					continue outer;
				case 0:
					if (("definiteness" == ___local) && ("" == ___uri)) {
						state = 1;
						return;
					}
					state = 3;
					continue outer;
				case 21:
					if (("transliterated" == ___local) && ("" == ___uri)) {
						state = 22;
						return;
					}
					break;
				case 6:
					if (("dotted" == ___local) && ("" == ___uri)) {
						state = 7;
						return;
					}
					state = 9;
					continue outer;
				case 15:
					if (("register" == ___local) && ("" == ___uri)) {
						state = 16;
						return;
					}
					state = 18;
					continue outer;
				case 18:
					if (("spelling" == ___local) && ("" == ___uri)) {
						state = 19;
						return;
					}
					state = 21;
					continue outer;
				case 12:
					if (("number" == ___local) && ("" == ___uri)) {
						state = 13;
						return;
					}
					state = 15;
					continue outer;
				case 9:
					if (("gender" == ___local) && ("" == ___uri)) {
						state = 10;
						return;
					}
					state = 12;
					continue outer;
				case 27:
					if (("undotted" == ___local) && ("" == ___uri)) {
						state = 28;
						return;
					}
					break;
				case 24:
					if (("type" == ___local) && ("" == ___uri)) {
						state = 25;
						return;
					}
					state = 27;
					continue outer;
				case 31:
					revertToParentFromEnterAttribute(___uri, ___local, ___qname);
					return;
				}
				super.enterAttribute(___uri, ___local, ___qname);
				break;
			}
		}

		public void leaveAttribute(java.lang.String ___uri,
				java.lang.String ___local, java.lang.String ___qname)
				throws org.xml.sax.SAXException {
			int attIdx;
			outer: while (true) {
				switch (state) {
				case 8:
					if (("dotted" == ___local) && ("" == ___uri)) {
						state = 9;
						return;
					}
					break;
				case 5:
					if (("direction" == ___local) && ("" == ___uri)) {
						state = 6;
						return;
					}
					break;
				case 20:
					if (("spelling" == ___local) && ("" == ___uri)) {
						state = 21;
						return;
					}
					break;
				case 3:
					attIdx = context.getAttribute("", "direction");
					if (attIdx >= 0) {
						final java.lang.String v = context.eatAttribute(attIdx);
						state = 6;
						eatText1(v);
						continue outer;
					}
					state = 6;
					continue outer;
				case 23:
					if (("transliterated" == ___local) && ("" == ___uri)) {
						state = 24;
						return;
					}
					break;
				case 0:
					attIdx = context.getAttribute("", "definiteness");
					if (attIdx >= 0) {
						final java.lang.String v = context.eatAttribute(attIdx);
						state = 3;
						eatText2(v);
						continue outer;
					}
					state = 3;
					continue outer;
				case 26:
					if (("type" == ___local) && ("" == ___uri)) {
						state = 27;
						return;
					}
					break;
				case 21:
					attIdx = context.getAttribute("", "transliterated");
					if (attIdx >= 0) {
						final java.lang.String v = context.eatAttribute(attIdx);
						state = 24;
						eatText3(v);
						continue outer;
					}
					break;
				case 6:
					attIdx = context.getAttribute("", "dotted");
					if (attIdx >= 0) {
						final java.lang.String v = context.eatAttribute(attIdx);
						state = 9;
						eatText4(v);
						continue outer;
					}
					state = 9;
					continue outer;
				case 14:
					if (("number" == ___local) && ("" == ___uri)) {
						state = 15;
						return;
					}
					break;
				case 15:
					attIdx = context.getAttribute("", "register");
					if (attIdx >= 0) {
						final java.lang.String v = context.eatAttribute(attIdx);
						state = 18;
						eatText5(v);
						continue outer;
					}
					state = 18;
					continue outer;
				case 29:
					if (("undotted" == ___local) && ("" == ___uri)) {
						state = 30;
						return;
					}
					break;
				case 11:
					if (("gender" == ___local) && ("" == ___uri)) {
						state = 12;
						return;
					}
					break;
				case 18:
					attIdx = context.getAttribute("", "spelling");
					if (attIdx >= 0) {
						final java.lang.String v = context.eatAttribute(attIdx);
						state = 21;
						eatText6(v);
						continue outer;
					}
					state = 21;
					continue outer;
				case 2:
					if (("definiteness" == ___local) && ("" == ___uri)) {
						state = 3;
						return;
					}
					break;
				case 12:
					attIdx = context.getAttribute("", "number");
					if (attIdx >= 0) {
						final java.lang.String v = context.eatAttribute(attIdx);
						state = 15;
						eatText7(v);
						continue outer;
					}
					state = 15;
					continue outer;
				case 9:
					attIdx = context.getAttribute("", "gender");
					if (attIdx >= 0) {
						final java.lang.String v = context.eatAttribute(attIdx);
						state = 12;
						eatText8(v);
						continue outer;
					}
					state = 12;
					continue outer;
				case 27:
					attIdx = context.getAttribute("", "undotted");
					if (attIdx >= 0) {
						final java.lang.String v = context.eatAttribute(attIdx);
						state = 30;
						eatText9(v);
						continue outer;
					}
					break;
				case 24:
					attIdx = context.getAttribute("", "type");
					if (attIdx >= 0) {
						final java.lang.String v = context.eatAttribute(attIdx);
						state = 27;
						eatText10(v);
						continue outer;
					}
					state = 27;
					continue outer;
				case 17:
					if (("register" == ___local) && ("" == ___uri)) {
						state = 18;
						return;
					}
					break;
				case 31:
					revertToParentFromLeaveAttribute(___uri, ___local, ___qname);
					return;
				}
				super.leaveAttribute(___uri, ___local, ___qname);
				break;
			}
		}

		public void handleText(final java.lang.String value)
				throws org.xml.sax.SAXException {
			int attIdx;
			outer: while (true) {
				try {
					switch (state) {
					case 19:
						state = 20;
						eatText6(value);
						return;
					case 3:
						attIdx = context.getAttribute("", "direction");
						if (attIdx >= 0) {
							final java.lang.String v = context
									.eatAttribute(attIdx);
							state = 6;
							eatText1(v);
							continue outer;
						}
						state = 6;
						continue outer;
					case 0:
						attIdx = context.getAttribute("", "definiteness");
						if (attIdx >= 0) {
							final java.lang.String v = context
									.eatAttribute(attIdx);
							state = 3;
							eatText2(v);
							continue outer;
						}
						state = 3;
						continue outer;
					case 22:
						state = 23;
						eatText3(value);
						return;
					case 30:
						state = 31;
						eatText11(value);
						return;
					case 21:
						attIdx = context.getAttribute("", "transliterated");
						if (attIdx >= 0) {
							final java.lang.String v = context
									.eatAttribute(attIdx);
							state = 24;
							eatText3(v);
							continue outer;
						}
						break;
					case 6:
						attIdx = context.getAttribute("", "dotted");
						if (attIdx >= 0) {
							final java.lang.String v = context
									.eatAttribute(attIdx);
							state = 9;
							eatText4(v);
							continue outer;
						}
						state = 9;
						continue outer;
					case 15:
						attIdx = context.getAttribute("", "register");
						if (attIdx >= 0) {
							final java.lang.String v = context
									.eatAttribute(attIdx);
							state = 18;
							eatText5(v);
							continue outer;
						}
						state = 18;
						continue outer;
					case 16:
						state = 17;
						eatText5(value);
						return;
					case 18:
						attIdx = context.getAttribute("", "spelling");
						if (attIdx >= 0) {
							final java.lang.String v = context
									.eatAttribute(attIdx);
							state = 21;
							eatText6(v);
							continue outer;
						}
						state = 21;
						continue outer;
					case 25:
						state = 26;
						eatText10(value);
						return;
					case 12:
						attIdx = context.getAttribute("", "number");
						if (attIdx >= 0) {
							final java.lang.String v = context
									.eatAttribute(attIdx);
							state = 15;
							eatText7(v);
							continue outer;
						}
						state = 15;
						continue outer;
					case 7:
						state = 8;
						eatText4(value);
						return;
					case 9:
						attIdx = context.getAttribute("", "gender");
						if (attIdx >= 0) {
							final java.lang.String v = context
									.eatAttribute(attIdx);
							state = 12;
							eatText8(v);
							continue outer;
						}
						state = 12;
						continue outer;
					case 28:
						state = 29;
						eatText9(value);
						return;
					case 27:
						attIdx = context.getAttribute("", "undotted");
						if (attIdx >= 0) {
							final java.lang.String v = context
									.eatAttribute(attIdx);
							state = 30;
							eatText9(v);
							continue outer;
						}
						break;
					case 1:
						state = 2;
						eatText2(value);
						return;
					case 24:
						attIdx = context.getAttribute("", "type");
						if (attIdx >= 0) {
							final java.lang.String v = context
									.eatAttribute(attIdx);
							state = 27;
							eatText10(v);
							continue outer;
						}
						state = 27;
						continue outer;
					case 4:
						state = 5;
						eatText1(value);
						return;
					case 10:
						state = 11;
						eatText8(value);
						return;
					case 31:
						revertToParentFromText(value);
						return;
					case 13:
						state = 14;
						eatText7(value);
						return;
					}
				} catch (java.lang.RuntimeException e) {
					handleUnexpectedTextException(value, e);
				}
				break;
			}
		}

		private void eatText11(final java.lang.String value)
				throws org.xml.sax.SAXException {
			try {
				_Value = com.sun.xml.bind.WhiteSpaceProcessor.collapse(value);
			} catch (java.lang.Exception e) {
				handleParseConversionException(e);
			}
		}

	}

}
