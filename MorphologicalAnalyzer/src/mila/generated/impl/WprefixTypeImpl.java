//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v1.0.5-b16-fcs 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2009.06.14 at 03:41:34 PM IDT 
//

package mila.generated.impl;

public class WprefixTypeImpl implements mila.generated.WprefixType,
		com.sun.xml.bind.JAXBObject,
		mila.generated.impl.runtime.UnmarshallableObject,
		mila.generated.impl.runtime.XMLSerializable,
		mila.generated.impl.runtime.ValidatableObject {

	public class Unmarshaller extends
			mila.generated.impl.runtime.AbstractUnmarshallingEventHandlerImpl {

		public Unmarshaller(mila.generated.impl.runtime.UnmarshallingContext context) {
			super(context, "-------------------");
		}

		protected Unmarshaller(
				mila.generated.impl.runtime.UnmarshallingContext context,
				int startState) {
			this(context);
			state = startState;
		}

		private void eatText1(final java.lang.String value)
				throws org.xml.sax.SAXException {
			try {
				_Spelling = com.sun.xml.bind.WhiteSpaceProcessor
						.collapse(value);
			} catch (java.lang.Exception e) {
				handleParseConversionException(e);
			}
		}

		private void eatText2(final java.lang.String value)
				throws org.xml.sax.SAXException {
			try {
				_Register = com.sun.xml.bind.WhiteSpaceProcessor
						.collapse(value);
			} catch (java.lang.Exception e) {
				handleParseConversionException(e);
			}
		}

		private void eatText3(final java.lang.String value)
				throws org.xml.sax.SAXException {
			try {
				_Gender = com.sun.xml.bind.WhiteSpaceProcessor.collapse(value);
			} catch (java.lang.Exception e) {
				handleParseConversionException(e);
			}
		}

		private void eatText4(final java.lang.String value)
				throws org.xml.sax.SAXException {
			try {
				_Definiteness = com.sun.xml.bind.WhiteSpaceProcessor
						.collapse(value);
			} catch (java.lang.Exception e) {
				handleParseConversionException(e);
			}
		}

		private void eatText5(final java.lang.String value)
				throws org.xml.sax.SAXException {
			try {
				_Number = com.sun.xml.bind.WhiteSpaceProcessor.collapse(value);
			} catch (java.lang.Exception e) {
				handleParseConversionException(e);
			}
		}

		private void eatText6(final java.lang.String value)
				throws org.xml.sax.SAXException {
			try {
				_Polarity = com.sun.xml.bind.WhiteSpaceProcessor
						.collapse(value);
			} catch (java.lang.Exception e) {
				handleParseConversionException(e);
			}
		}

		@Override
		public void enterAttribute(java.lang.String ___uri,
				java.lang.String ___local, java.lang.String ___qname)
				throws org.xml.sax.SAXException {
			int attIdx;
			outer: while (true) {
				switch (state) {
				case 15:
					if (("spelling" == ___local) && ("" == ___uri)) {
						state = 16;
						return;
					}
					state = 18;
					continue outer;
				case 12:
					if (("register" == ___local) && ("" == ___uri)) {
						state = 13;
						return;
					}
					state = 15;
					continue outer;
				case 3:
					if (("gender" == ___local) && ("" == ___uri)) {
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
				case 18:
					revertToParentFromEnterAttribute(___uri, ___local, ___qname);
					return;
				case 6:
					if (("number" == ___local) && ("" == ___uri)) {
						state = 7;
						return;
					}
					state = 9;
					continue outer;
				case 9:
					if (("polarity" == ___local) && ("" == ___uri)) {
						state = 10;
						return;
					}
					state = 12;
					continue outer;
				}
				super.enterAttribute(___uri, ___local, ___qname);
				break;
			}
		}

		@Override
		public void enterElement(java.lang.String ___uri,
				java.lang.String ___local, java.lang.String ___qname,
				org.xml.sax.Attributes __atts) throws org.xml.sax.SAXException {
			int attIdx;
			outer: while (true) {
				switch (state) {
				case 15:
					attIdx = context.getAttribute("", "spelling");
					if (attIdx >= 0) {
						final java.lang.String v = context.eatAttribute(attIdx);
						state = 18;
						eatText1(v);
						continue outer;
					}
					state = 18;
					continue outer;
				case 12:
					attIdx = context.getAttribute("", "register");
					if (attIdx >= 0) {
						final java.lang.String v = context.eatAttribute(attIdx);
						state = 15;
						eatText2(v);
						continue outer;
					}
					state = 15;
					continue outer;
				case 3:
					attIdx = context.getAttribute("", "gender");
					if (attIdx >= 0) {
						final java.lang.String v = context.eatAttribute(attIdx);
						state = 6;
						eatText3(v);
						continue outer;
					}
					state = 6;
					continue outer;
				case 0:
					attIdx = context.getAttribute("", "definiteness");
					if (attIdx >= 0) {
						final java.lang.String v = context.eatAttribute(attIdx);
						state = 3;
						eatText4(v);
						continue outer;
					}
					state = 3;
					continue outer;
				case 18:
					revertToParentFromEnterElement(___uri, ___local, ___qname,
							__atts);
					return;
				case 6:
					attIdx = context.getAttribute("", "number");
					if (attIdx >= 0) {
						final java.lang.String v = context.eatAttribute(attIdx);
						state = 9;
						eatText5(v);
						continue outer;
					}
					state = 9;
					continue outer;
				case 9:
					attIdx = context.getAttribute("", "polarity");
					if (attIdx >= 0) {
						final java.lang.String v = context.eatAttribute(attIdx);
						state = 12;
						eatText6(v);
						continue outer;
					}
					state = 12;
					continue outer;
				}
				super.enterElement(___uri, ___local, ___qname, __atts);
				break;
			}
		}

		@Override
		public void handleText(final java.lang.String value)
				throws org.xml.sax.SAXException {
			int attIdx;
			outer: while (true) {
				try {
					switch (state) {
					case 16:
						state = 17;
						eatText1(value);
						return;
					case 15:
						attIdx = context.getAttribute("", "spelling");
						if (attIdx >= 0) {
							final java.lang.String v = context
									.eatAttribute(attIdx);
							state = 18;
							eatText1(v);
							continue outer;
						}
						state = 18;
						continue outer;
					case 4:
						state = 5;
						eatText3(value);
						return;
					case 12:
						attIdx = context.getAttribute("", "register");
						if (attIdx >= 0) {
							final java.lang.String v = context
									.eatAttribute(attIdx);
							state = 15;
							eatText2(v);
							continue outer;
						}
						state = 15;
						continue outer;
					case 1:
						state = 2;
						eatText4(value);
						return;
					case 13:
						state = 14;
						eatText2(value);
						return;
					case 10:
						state = 11;
						eatText6(value);
						return;
					case 3:
						attIdx = context.getAttribute("", "gender");
						if (attIdx >= 0) {
							final java.lang.String v = context
									.eatAttribute(attIdx);
							state = 6;
							eatText3(v);
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
							eatText4(v);
							continue outer;
						}
						state = 3;
						continue outer;
					case 7:
						state = 8;
						eatText5(value);
						return;
					case 18:
						revertToParentFromText(value);
						return;
					case 6:
						attIdx = context.getAttribute("", "number");
						if (attIdx >= 0) {
							final java.lang.String v = context
									.eatAttribute(attIdx);
							state = 9;
							eatText5(v);
							continue outer;
						}
						state = 9;
						continue outer;
					case 9:
						attIdx = context.getAttribute("", "polarity");
						if (attIdx >= 0) {
							final java.lang.String v = context
									.eatAttribute(attIdx);
							state = 12;
							eatText6(v);
							continue outer;
						}
						state = 12;
						continue outer;
					}
				} catch (java.lang.RuntimeException e) {
					handleUnexpectedTextException(value, e);
				}
				break;
			}
		}

		@Override
		public void leaveAttribute(java.lang.String ___uri,
				java.lang.String ___local, java.lang.String ___qname)
				throws org.xml.sax.SAXException {
			int attIdx;
			outer: while (true) {
				switch (state) {
				case 15:
					attIdx = context.getAttribute("", "spelling");
					if (attIdx >= 0) {
						final java.lang.String v = context.eatAttribute(attIdx);
						state = 18;
						eatText1(v);
						continue outer;
					}
					state = 18;
					continue outer;
				case 12:
					attIdx = context.getAttribute("", "register");
					if (attIdx >= 0) {
						final java.lang.String v = context.eatAttribute(attIdx);
						state = 15;
						eatText2(v);
						continue outer;
					}
					state = 15;
					continue outer;
				case 17:
					if (("spelling" == ___local) && ("" == ___uri)) {
						state = 18;
						return;
					}
					break;
				case 3:
					attIdx = context.getAttribute("", "gender");
					if (attIdx >= 0) {
						final java.lang.String v = context.eatAttribute(attIdx);
						state = 6;
						eatText3(v);
						continue outer;
					}
					state = 6;
					continue outer;
				case 0:
					attIdx = context.getAttribute("", "definiteness");
					if (attIdx >= 0) {
						final java.lang.String v = context.eatAttribute(attIdx);
						state = 3;
						eatText4(v);
						continue outer;
					}
					state = 3;
					continue outer;
				case 11:
					if (("polarity" == ___local) && ("" == ___uri)) {
						state = 12;
						return;
					}
					break;
				case 18:
					revertToParentFromLeaveAttribute(___uri, ___local, ___qname);
					return;
				case 5:
					if (("gender" == ___local) && ("" == ___uri)) {
						state = 6;
						return;
					}
					break;
				case 14:
					if (("register" == ___local) && ("" == ___uri)) {
						state = 15;
						return;
					}
					break;
				case 6:
					attIdx = context.getAttribute("", "number");
					if (attIdx >= 0) {
						final java.lang.String v = context.eatAttribute(attIdx);
						state = 9;
						eatText5(v);
						continue outer;
					}
					state = 9;
					continue outer;
				case 2:
					if (("definiteness" == ___local) && ("" == ___uri)) {
						state = 3;
						return;
					}
					break;
				case 8:
					if (("number" == ___local) && ("" == ___uri)) {
						state = 9;
						return;
					}
					break;
				case 9:
					attIdx = context.getAttribute("", "polarity");
					if (attIdx >= 0) {
						final java.lang.String v = context.eatAttribute(attIdx);
						state = 12;
						eatText6(v);
						continue outer;
					}
					state = 12;
					continue outer;
				}
				super.leaveAttribute(___uri, ___local, ___qname);
				break;
			}
		}

		@Override
		public void leaveElement(java.lang.String ___uri,
				java.lang.String ___local, java.lang.String ___qname)
				throws org.xml.sax.SAXException {
			int attIdx;
			outer: while (true) {
				switch (state) {
				case 15:
					attIdx = context.getAttribute("", "spelling");
					if (attIdx >= 0) {
						final java.lang.String v = context.eatAttribute(attIdx);
						state = 18;
						eatText1(v);
						continue outer;
					}
					state = 18;
					continue outer;
				case 12:
					attIdx = context.getAttribute("", "register");
					if (attIdx >= 0) {
						final java.lang.String v = context.eatAttribute(attIdx);
						state = 15;
						eatText2(v);
						continue outer;
					}
					state = 15;
					continue outer;
				case 3:
					attIdx = context.getAttribute("", "gender");
					if (attIdx >= 0) {
						final java.lang.String v = context.eatAttribute(attIdx);
						state = 6;
						eatText3(v);
						continue outer;
					}
					state = 6;
					continue outer;
				case 0:
					attIdx = context.getAttribute("", "definiteness");
					if (attIdx >= 0) {
						final java.lang.String v = context.eatAttribute(attIdx);
						state = 3;
						eatText4(v);
						continue outer;
					}
					state = 3;
					continue outer;
				case 18:
					revertToParentFromLeaveElement(___uri, ___local, ___qname);
					return;
				case 6:
					attIdx = context.getAttribute("", "number");
					if (attIdx >= 0) {
						final java.lang.String v = context.eatAttribute(attIdx);
						state = 9;
						eatText5(v);
						continue outer;
					}
					state = 9;
					continue outer;
				case 9:
					attIdx = context.getAttribute("", "polarity");
					if (attIdx >= 0) {
						final java.lang.String v = context.eatAttribute(attIdx);
						state = 12;
						eatText6(v);
						continue outer;
					}
					state = 12;
					continue outer;
				}
				super.leaveElement(___uri, ___local, ___qname);
				break;
			}
		}

		@Override
		public java.lang.Object owner() {
			return mila.generated.impl.WprefixTypeImpl.this;
		}

	}

	protected java.lang.String _Register;
	protected java.lang.String _Gender;
	protected java.lang.String _Number;
	protected java.lang.String _Polarity;
	protected java.lang.String _Spelling;
	protected java.lang.String _Definiteness;
	public final static java.lang.Class version = (mila.generated.impl.JAXBVersion.class);

	private static com.sun.msv.grammar.Grammar schemaFragment;

	private final static java.lang.Class PRIMARY_INTERFACE_CLASS() {
		return (mila.generated.WprefixType.class);
	}

	@Override
	public com.sun.msv.verifier.DocumentDeclaration createRawValidator() {
		if (schemaFragment == null) {
			schemaFragment = com.sun.xml.bind.validator.SchemaDeserializer
					.deserialize(("\u00ac\u00ed\u0000\u0005sr\u0000\u001fcom.sun.msv.grammar.SequenceExp\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0001\u0002\u0000\u0000xr\u0000\u001dcom.su"
							+ "n.msv.grammar.BinaryExp\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0001\u0002\u0000\u0002L\u0000\u0004exp1t\u0000 Lcom/sun/msv/gra"
							+ "mmar/Expression;L\u0000\u0004exp2q\u0000~\u0000\u0002xr\u0000\u001ecom.sun.msv.grammar.Expressi"
							+ "on\u00f8\u0018\u0082\u00e8N5~O\u0002\u0000\u0002L\u0000\u0013epsilonReducibilityt\u0000\u0013Ljava/lang/Boolean;L\u0000\u000b"
							+ "expandedExpq\u0000~\u0000\u0002xpppsq\u0000~\u0000\u0000ppsq\u0000~\u0000\u0000ppsq\u0000~\u0000\u0000ppsq\u0000~\u0000\u0000ppsr\u0000\u001dcom."
							+ "sun.msv.grammar.ChoiceExp\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0001\u0002\u0000\u0000xq\u0000~\u0000\u0001ppsr\u0000 com.sun.msv."
							+ "grammar.AttributeExp\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0001\u0002\u0000\u0002L\u0000\u0003expq\u0000~\u0000\u0002L\u0000\tnameClasst\u0000\u001fLco"
							+ "m/sun/msv/grammar/NameClass;xq\u0000~\u0000\u0003sr\u0000\u0011java.lang.Boolean\u00cd r\u0080\u00d5"
							+ "\u009c\u00fa\u00ee\u0002\u0000\u0001Z\u0000\u0005valuexp\u0000psr\u0000\u001bcom.sun.msv.grammar.DataExp\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0001\u0002\u0000\u0003"
							+ "L\u0000\u0002dtt\u0000\u001fLorg/relaxng/datatype/Datatype;L\u0000\u0006exceptq\u0000~\u0000\u0002L\u0000\u0004name"
							+ "t\u0000\u001dLcom/sun/msv/util/StringPair;xq\u0000~\u0000\u0003ppsr\u0000)com.sun.msv.data"
							+ "type.xsd.EnumerationFacet\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0001\u0002\u0000\u0001L\u0000\u0006valuest\u0000\u000fLjava/util/S"
							+ "et;xr\u00009com.sun.msv.datatype.xsd.DataTypeWithValueConstraintF"
							+ "acet\"\u00a7Ro\u00ca\u00c7\u008aT\u0002\u0000\u0000xr\u0000*com.sun.msv.datatype.xsd.DataTypeWithFace"
							+ "t\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0001\u0002\u0000\u0005Z\u0000\fisFacetFixedZ\u0000\u0012needValueCheckFlagL\u0000\bbaseTypet"
							+ "\u0000)Lcom/sun/msv/datatype/xsd/XSDatatypeImpl;L\u0000\fconcreteTypet\u0000"
							+ "\'Lcom/sun/msv/datatype/xsd/ConcreteType;L\u0000\tfacetNamet\u0000\u0012Ljava"
							+ "/lang/String;xr\u0000\'com.sun.msv.datatype.xsd.XSDatatypeImpl\u0000\u0000\u0000\u0000"
							+ "\u0000\u0000\u0000\u0001\u0002\u0000\u0003L\u0000\fnamespaceUriq\u0000~\u0000\u001bL\u0000\btypeNameq\u0000~\u0000\u001bL\u0000\nwhiteSpacet\u0000.L"
							+ "com/sun/msv/datatype/xsd/WhiteSpaceProcessor;xpt\u0000\u0000t\u0000\u0010Definit"
							+ "enessTypesr\u00005com.sun.msv.datatype.xsd.WhiteSpaceProcessor$Co"
							+ "llapse\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0001\u0002\u0000\u0000xr\u0000,com.sun.msv.datatype.xsd.WhiteSpaceProc"
							+ "essor\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0001\u0002\u0000\u0000xp\u0000\u0000sr\u0000\"com.sun.msv.datatype.xsd.TokenType\u0000\u0000"
							+ "\u0000\u0000\u0000\u0000\u0000\u0001\u0002\u0000\u0000xr\u0000#com.sun.msv.datatype.xsd.StringType\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0001\u0002\u0000\u0001Z"
							+ "\u0000\risAlwaysValidxr\u0000*com.sun.msv.datatype.xsd.BuiltinAtomicTyp"
							+ "e\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0001\u0002\u0000\u0000xr\u0000%com.sun.msv.datatype.xsd.ConcreteType\u0000\u0000\u0000\u0000\u0000\u0000\u0000"
							+ "\u0001\u0002\u0000\u0000xq\u0000~\u0000\u001ct\u0000 http://www.w3.org/2001/XMLSchemat\u0000\u0005tokenq\u0000~\u0000#\u0001q"
							+ "\u0000~\u0000(t\u0000\u000benumerationsr\u0000\u0011java.util.HashSet\u00baD\u0085\u0095\u0096\u00b8\u00b74\u0003\u0000\u0000xpw\f\u0000\u0000\u0000\u0010?@"
							+ "\u0000\u0000\u0000\u0000\u0000\u0003t\u0000\u000bunspecifiedt\u0000\u0004truet\u0000\u0005falsexsr\u00000com.sun.msv.grammar."
							+ "Expression$NullSetExpression\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0001\u0002\u0000\u0000xq\u0000~\u0000\u0003ppsr\u0000\u001bcom.sun.m"
							+ "sv.util.StringPair\u00d0t\u001ejB\u008f\u008d\u00a0\u0002\u0000\u0002L\u0000\tlocalNameq\u0000~\u0000\u001bL\u0000\fnamespaceUR"
							+ "Iq\u0000~\u0000\u001bxpq\u0000~\u0000 q\u0000~\u0000\u001fsr\u0000#com.sun.msv.grammar.SimpleNameClass\u0000\u0000\u0000"
							+ "\u0000\u0000\u0000\u0000\u0001\u0002\u0000\u0002L\u0000\tlocalNameq\u0000~\u0000\u001bL\u0000\fnamespaceURIq\u0000~\u0000\u001bxr\u0000\u001dcom.sun.msv"
							+ ".grammar.NameClass\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0001\u0002\u0000\u0000xpt\u0000\fdefinitenessq\u0000~\u0000\u001fsr\u00000com.s"
							+ "un.msv.grammar.Expression$EpsilonExpression\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0001\u0002\u0000\u0000xq\u0000~\u0000\u0003"
							+ "sq\u0000~\u0000\u000f\u0001q\u0000~\u0000:sq\u0000~\u0000\nppsq\u0000~\u0000\fq\u0000~\u0000\u0010psq\u0000~\u0000\u0011ppsq\u0000~\u0000\u0015q\u0000~\u0000\u001ft\u0000\nGender"
							+ "Typeq\u0000~\u0000#\u0000\u0000q\u0000~\u0000(q\u0000~\u0000(q\u0000~\u0000+sq\u0000~\u0000,w\f\u0000\u0000\u0000\u0010?@\u0000\u0000\u0000\u0000\u0000\u0005t\u0000\u000bunspecified"
							+ "t\u0000\bfemininet\u0000\nirrelevantt\u0000\u0016masculine and femininet\u0000\tmasculin"
							+ "exq\u0000~\u00002sq\u0000~\u00003q\u0000~\u0000@q\u0000~\u0000\u001fsq\u0000~\u00005t\u0000\u0006genderq\u0000~\u0000\u001fq\u0000~\u0000:sq\u0000~\u0000\nppsq\u0000~"
							+ "\u0000\fq\u0000~\u0000\u0010psq\u0000~\u0000\u0011ppsq\u0000~\u0000\u0015q\u0000~\u0000\u001ft\u0000\nNumberTypeq\u0000~\u0000#\u0000\u0000q\u0000~\u0000(q\u0000~\u0000(q\u0000~"
							+ "\u0000+sq\u0000~\u0000,w\f\u0000\u0000\u0000\u0010?@\u0000\u0000\u0000\u0000\u0000\u0006t\u0000\u000bunspecifiedt\u0000\u0013singular and pluralt\u0000"
							+ "\u0004dualt\u0000\u000fdual and pluralt\u0000\bsingulart\u0000\u0006pluralxq\u0000~\u00002sq\u0000~\u00003q\u0000~\u0000N"
							+ "q\u0000~\u0000\u001fsq\u0000~\u00005t\u0000\u0006numberq\u0000~\u0000\u001fq\u0000~\u0000:sq\u0000~\u0000\nppsq\u0000~\u0000\fq\u0000~\u0000\u0010psq\u0000~\u0000\u0011ppsq"
							+ "\u0000~\u0000\u0015q\u0000~\u0000\u001ft\u0000\fPolarityTypeq\u0000~\u0000#\u0000\u0000q\u0000~\u0000(q\u0000~\u0000(q\u0000~\u0000+sq\u0000~\u0000,w\f\u0000\u0000\u0000\u0010?@"
							+ "\u0000\u0000\u0000\u0000\u0000\u0003t\u0000\u000bunspecifiedt\u0000\bnegativet\u0000\bpositivexq\u0000~\u00002sq\u0000~\u00003q\u0000~\u0000]q"
							+ "\u0000~\u0000\u001fsq\u0000~\u00005t\u0000\bpolarityq\u0000~\u0000\u001fq\u0000~\u0000:sq\u0000~\u0000\nppsq\u0000~\u0000\fq\u0000~\u0000\u0010psq\u0000~\u0000\u0011pps"
							+ "q\u0000~\u0000\u0015q\u0000~\u0000\u001ft\u0000\fRegisterTypeq\u0000~\u0000#\u0000\u0000q\u0000~\u0000(q\u0000~\u0000(q\u0000~\u0000+sq\u0000~\u0000,w\f\u0000\u0000\u0000\u0010?"
							+ "@\u0000\u0000\u0000\u0000\u0000\u0003t\u0000\u0006formalt\u0000\binformalt\u0000\u0007archaicxq\u0000~\u00002sq\u0000~\u00003q\u0000~\u0000iq\u0000~\u0000\u001fs"
							+ "q\u0000~\u00005t\u0000\bregisterq\u0000~\u0000\u001fq\u0000~\u0000:sq\u0000~\u0000\nppsq\u0000~\u0000\fq\u0000~\u0000\u0010psq\u0000~\u0000\u0011ppsq\u0000~\u0000\u0015"
							+ "q\u0000~\u0000\u001ft\u0000\fSpellingTypeq\u0000~\u0000#\u0000\u0000q\u0000~\u0000(q\u0000~\u0000(q\u0000~\u0000+sq\u0000~\u0000,w\f\u0000\u0000\u0000\u0010?@\u0000\u0000\u0000\u0000"
							+ "\u0000\u0002t\u0000\tirregulart\u0000\bstandardxq\u0000~\u00002sq\u0000~\u00003q\u0000~\u0000uq\u0000~\u0000\u001fsq\u0000~\u00005t\u0000\bspel"
							+ "lingq\u0000~\u0000\u001fq\u0000~\u0000:sr\u0000\"com.sun.msv.grammar.ExpressionPool\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0001"
							+ "\u0002\u0000\u0001L\u0000\bexpTablet\u0000/Lcom/sun/msv/grammar/ExpressionPool$ClosedH"
							+ "ash;xpsr\u0000-com.sun.msv.grammar.ExpressionPool$ClosedHash\u00d7j\u00d0N\u00ef"
							+ "\u00e8\u00ed\u001c\u0003\u0000\u0003I\u0000\u0005countB\u0000\rstreamVersionL\u0000\u0006parentt\u0000$Lcom/sun/msv/gramm"
							+ "ar/ExpressionPool;xp\u0000\u0000\u0000\u000b\u0001pq\u0000~\u0000qq\u0000~\u0000<q\u0000~\u0000\u0007q\u0000~\u0000\u0006q\u0000~\u0000\bq\u0000~\u0000Yq\u0000~\u0000"
							+ "Jq\u0000~\u0000\u0005q\u0000~\u0000\u000bq\u0000~\u0000eq\u0000~\u0000\tx"));
		}
		return new com.sun.msv.verifier.regexp.REDocumentDeclaration(
				schemaFragment);
	}

	@Override
	public mila.generated.impl.runtime.UnmarshallingEventHandler createUnmarshaller(
			mila.generated.impl.runtime.UnmarshallingContext context) {
		return new mila.generated.impl.WprefixTypeImpl.Unmarshaller(context);
	}

	@Override
	public java.lang.String getDefiniteness() {
		if (_Definiteness == null) {
			return "false";
		} else {
			return _Definiteness;
		}
	}

	@Override
	public java.lang.String getGender() {
		if (_Gender == null) {
			return "unspecified";
		} else {
			return _Gender;
		}
	}

	@Override
	public java.lang.String getNumber() {
		if (_Number == null) {
			return "unspecified";
		} else {
			return _Number;
		}
	}

	@Override
	public java.lang.String getPolarity() {
		if (_Polarity == null) {
			return "unspecified";
		} else {
			return _Polarity;
		}
	}

	@Override
	public java.lang.Class getPrimaryInterface() {
		return (mila.generated.WprefixType.class);
	}

	@Override
	public java.lang.String getRegister() {
		if (_Register == null) {
			return "formal";
		} else {
			return _Register;
		}
	}

	@Override
	public java.lang.String getSpelling() {
		if (_Spelling == null) {
			return "standard";
		} else {
			return _Spelling;
		}
	}

	@Override
	public void serializeAttributes(mila.generated.impl.runtime.XMLSerializer context)
			throws org.xml.sax.SAXException {
		if (_Definiteness != null) {
			context.startAttribute("", "definiteness");
			try {
				context.text(_Definiteness, "Definiteness");
			} catch (java.lang.Exception e) {
				mila.generated.impl.runtime.Util.handlePrintConversionException(
						this, e, context);
			}
			context.endAttribute();
		}
		if (_Gender != null) {
			context.startAttribute("", "gender");
			try {
				context.text(_Gender, "Gender");
			} catch (java.lang.Exception e) {
				mila.generated.impl.runtime.Util.handlePrintConversionException(
						this, e, context);
			}
			context.endAttribute();
		}
		if (_Number != null) {
			context.startAttribute("", "number");
			try {
				context.text(_Number, "Number");
			} catch (java.lang.Exception e) {
				mila.generated.impl.runtime.Util.handlePrintConversionException(
						this, e, context);
			}
			context.endAttribute();
		}
		if (_Polarity != null) {
			context.startAttribute("", "polarity");
			try {
				context.text(_Polarity, "Polarity");
			} catch (java.lang.Exception e) {
				mila.generated.impl.runtime.Util.handlePrintConversionException(
						this, e, context);
			}
			context.endAttribute();
		}
		if (_Register != null) {
			context.startAttribute("", "register");
			try {
				context.text(_Register, "Register");
			} catch (java.lang.Exception e) {
				mila.generated.impl.runtime.Util.handlePrintConversionException(
						this, e, context);
			}
			context.endAttribute();
		}
		if (_Spelling != null) {
			context.startAttribute("", "spelling");
			try {
				context.text(_Spelling, "Spelling");
			} catch (java.lang.Exception e) {
				mila.generated.impl.runtime.Util.handlePrintConversionException(
						this, e, context);
			}
			context.endAttribute();
		}
	}

	@Override
	public void serializeBody(mila.generated.impl.runtime.XMLSerializer context)
			throws org.xml.sax.SAXException {
	}

	@Override
	public void serializeURIs(mila.generated.impl.runtime.XMLSerializer context)
			throws org.xml.sax.SAXException {
	}

	@Override
	public void setDefiniteness(java.lang.String value) {
		_Definiteness = value;
	}

	@Override
	public void setGender(java.lang.String value) {
		_Gender = value;
	}

	@Override
	public void setNumber(java.lang.String value) {
		_Number = value;
	}

	@Override
	public void setPolarity(java.lang.String value) {
		_Polarity = value;
	}

	@Override
	public void setRegister(java.lang.String value) {
		_Register = value;
	}

	@Override
	public void setSpelling(java.lang.String value) {
		_Spelling = value;
	}

}