//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v1.0.5-b16-fcs 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2009.06.14 at 03:41:34 PM IDT 
//

package generated.impl;

public class VerbTypeImpl implements generated.VerbType,
		com.sun.xml.bind.JAXBObject,
		generated.impl.runtime.UnmarshallableObject,
		generated.impl.runtime.XMLSerializable,
		generated.impl.runtime.ValidatableObject {

	public class Unmarshaller extends
			generated.impl.runtime.AbstractUnmarshallingEventHandlerImpl {

		public Unmarshaller(generated.impl.runtime.UnmarshallingContext context) {
			super(context, "----------------------------");
		}

		protected Unmarshaller(
				generated.impl.runtime.UnmarshallingContext context,
				int startState) {
			this(context);
			state = startState;
		}

		private void eatText1(final java.lang.String value)
				throws org.xml.sax.SAXException {
			try {
				_Register = com.sun.xml.bind.WhiteSpaceProcessor
						.collapse(value);
			} catch (java.lang.Exception e) {
				handleParseConversionException(e);
			}
		}

		private void eatText2(final java.lang.String value)
				throws org.xml.sax.SAXException {
			try {
				_Root = com.sun.xml.bind.WhiteSpaceProcessor.collapse(value);
			} catch (java.lang.Exception e) {
				handleParseConversionException(e);
			}
		}

		private void eatText3(final java.lang.String value)
				throws org.xml.sax.SAXException {
			try {
				_Tense = com.sun.xml.bind.WhiteSpaceProcessor.collapse(value);
			} catch (java.lang.Exception e) {
				handleParseConversionException(e);
			}
		}

		private void eatText4(final java.lang.String value)
				throws org.xml.sax.SAXException {
			try {
				_Expansion = com.sun.xml.bind.WhiteSpaceProcessor
						.collapse(value);
			} catch (java.lang.Exception e) {
				handleParseConversionException(e);
			}
		}

		private void eatText5(final java.lang.String value)
				throws org.xml.sax.SAXException {
			try {
				_Binyan = com.sun.xml.bind.WhiteSpaceProcessor.collapse(value);
			} catch (java.lang.Exception e) {
				handleParseConversionException(e);
			}
		}

		private void eatText6(final java.lang.String value)
				throws org.xml.sax.SAXException {
			try {
				_Number = com.sun.xml.bind.WhiteSpaceProcessor.collapse(value);
			} catch (java.lang.Exception e) {
				handleParseConversionException(e);
			}
		}

		private void eatText7(final java.lang.String value)
				throws org.xml.sax.SAXException {
			try {
				_Gender = com.sun.xml.bind.WhiteSpaceProcessor.collapse(value);
			} catch (java.lang.Exception e) {
				handleParseConversionException(e);
			}
		}

		private void eatText8(final java.lang.String value)
				throws org.xml.sax.SAXException {
			try {
				_Person = com.sun.xml.bind.WhiteSpaceProcessor.collapse(value);
			} catch (java.lang.Exception e) {
				handleParseConversionException(e);
			}
		}

		private void eatText9(final java.lang.String value)
				throws org.xml.sax.SAXException {
			try {
				_Spelling = com.sun.xml.bind.WhiteSpaceProcessor
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
				case 27:
					revertToParentFromEnterAttribute(___uri, ___local, ___qname);
					return;
				case 15:
					if (("register" == ___local) && ("" == ___uri)) {
						state = 16;
						return;
					}
					state = 18;
					continue outer;
				case 18:
					if (("root" == ___local) && ("" == ___uri)) {
						state = 19;
						return;
					}
					state = 21;
					continue outer;
				case 24:
					if (("tense" == ___local) && ("" == ___uri)) {
						state = 25;
						return;
					}
					state = 27;
					continue outer;
				case 3:
					if (("expansion" == ___local) && ("" == ___uri)) {
						state = 4;
						return;
					}
					state = 6;
					continue outer;
				case 0:
					if (("binyan" == ___local) && ("" == ___uri)) {
						state = 1;
						return;
					}
					state = 3;
					continue outer;
				case 9:
					if (("number" == ___local) && ("" == ___uri)) {
						state = 10;
						return;
					}
					state = 12;
					continue outer;
				case 6:
					if (("gender" == ___local) && ("" == ___uri)) {
						state = 7;
						return;
					}
					state = 9;
					continue outer;
				case 12:
					if (("person" == ___local) && ("" == ___uri)) {
						state = 13;
						return;
					}
					state = 15;
					continue outer;
				case 21:
					if (("spelling" == ___local) && ("" == ___uri)) {
						state = 22;
						return;
					}
					state = 24;
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
				case 27:
					revertToParentFromEnterElement(___uri, ___local, ___qname,
							__atts);
					return;
				case 15:
					attIdx = context.getAttribute("", "register");
					if (attIdx >= 0) {
						final java.lang.String v = context.eatAttribute(attIdx);
						state = 18;
						eatText1(v);
						continue outer;
					}
					state = 18;
					continue outer;
				case 18:
					attIdx = context.getAttribute("", "root");
					if (attIdx >= 0) {
						final java.lang.String v = context.eatAttribute(attIdx);
						state = 21;
						eatText2(v);
						continue outer;
					}
					state = 21;
					continue outer;
				case 24:
					attIdx = context.getAttribute("", "tense");
					if (attIdx >= 0) {
						final java.lang.String v = context.eatAttribute(attIdx);
						state = 27;
						eatText3(v);
						continue outer;
					}
					state = 27;
					continue outer;
				case 3:
					attIdx = context.getAttribute("", "expansion");
					if (attIdx >= 0) {
						final java.lang.String v = context.eatAttribute(attIdx);
						state = 6;
						eatText4(v);
						continue outer;
					}
					state = 6;
					continue outer;
				case 0:
					attIdx = context.getAttribute("", "binyan");
					if (attIdx >= 0) {
						final java.lang.String v = context.eatAttribute(attIdx);
						state = 3;
						eatText5(v);
						continue outer;
					}
					state = 3;
					continue outer;
				case 9:
					attIdx = context.getAttribute("", "number");
					if (attIdx >= 0) {
						final java.lang.String v = context.eatAttribute(attIdx);
						state = 12;
						eatText6(v);
						continue outer;
					}
					state = 12;
					continue outer;
				case 6:
					attIdx = context.getAttribute("", "gender");
					if (attIdx >= 0) {
						final java.lang.String v = context.eatAttribute(attIdx);
						state = 9;
						eatText7(v);
						continue outer;
					}
					state = 9;
					continue outer;
				case 12:
					attIdx = context.getAttribute("", "person");
					if (attIdx >= 0) {
						final java.lang.String v = context.eatAttribute(attIdx);
						state = 15;
						eatText8(v);
						continue outer;
					}
					state = 15;
					continue outer;
				case 21:
					attIdx = context.getAttribute("", "spelling");
					if (attIdx >= 0) {
						final java.lang.String v = context.eatAttribute(attIdx);
						state = 24;
						eatText9(v);
						continue outer;
					}
					state = 24;
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
					case 25:
						state = 26;
						eatText3(value);
						return;
					case 27:
						revertToParentFromText(value);
						return;
					case 15:
						attIdx = context.getAttribute("", "register");
						if (attIdx >= 0) {
							final java.lang.String v = context
									.eatAttribute(attIdx);
							state = 18;
							eatText1(v);
							continue outer;
						}
						state = 18;
						continue outer;
					case 1:
						state = 2;
						eatText5(value);
						return;
					case 18:
						attIdx = context.getAttribute("", "root");
						if (attIdx >= 0) {
							final java.lang.String v = context
									.eatAttribute(attIdx);
							state = 21;
							eatText2(v);
							continue outer;
						}
						state = 21;
						continue outer;
					case 24:
						attIdx = context.getAttribute("", "tense");
						if (attIdx >= 0) {
							final java.lang.String v = context
									.eatAttribute(attIdx);
							state = 27;
							eatText3(v);
							continue outer;
						}
						state = 27;
						continue outer;
					case 16:
						state = 17;
						eatText1(value);
						return;
					case 3:
						attIdx = context.getAttribute("", "expansion");
						if (attIdx >= 0) {
							final java.lang.String v = context
									.eatAttribute(attIdx);
							state = 6;
							eatText4(v);
							continue outer;
						}
						state = 6;
						continue outer;
					case 0:
						attIdx = context.getAttribute("", "binyan");
						if (attIdx >= 0) {
							final java.lang.String v = context
									.eatAttribute(attIdx);
							state = 3;
							eatText5(v);
							continue outer;
						}
						state = 3;
						continue outer;
					case 7:
						state = 8;
						eatText7(value);
						return;
					case 10:
						state = 11;
						eatText6(value);
						return;
					case 13:
						state = 14;
						eatText8(value);
						return;
					case 9:
						attIdx = context.getAttribute("", "number");
						if (attIdx >= 0) {
							final java.lang.String v = context
									.eatAttribute(attIdx);
							state = 12;
							eatText6(v);
							continue outer;
						}
						state = 12;
						continue outer;
					case 22:
						state = 23;
						eatText9(value);
						return;
					case 6:
						attIdx = context.getAttribute("", "gender");
						if (attIdx >= 0) {
							final java.lang.String v = context
									.eatAttribute(attIdx);
							state = 9;
							eatText7(v);
							continue outer;
						}
						state = 9;
						continue outer;
					case 4:
						state = 5;
						eatText4(value);
						return;
					case 19:
						state = 20;
						eatText2(value);
						return;
					case 12:
						attIdx = context.getAttribute("", "person");
						if (attIdx >= 0) {
							final java.lang.String v = context
									.eatAttribute(attIdx);
							state = 15;
							eatText8(v);
							continue outer;
						}
						state = 15;
						continue outer;
					case 21:
						attIdx = context.getAttribute("", "spelling");
						if (attIdx >= 0) {
							final java.lang.String v = context
									.eatAttribute(attIdx);
							state = 24;
							eatText9(v);
							continue outer;
						}
						state = 24;
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
				case 27:
					revertToParentFromLeaveAttribute(___uri, ___local, ___qname);
					return;
				case 15:
					attIdx = context.getAttribute("", "register");
					if (attIdx >= 0) {
						final java.lang.String v = context.eatAttribute(attIdx);
						state = 18;
						eatText1(v);
						continue outer;
					}
					state = 18;
					continue outer;
				case 17:
					if (("register" == ___local) && ("" == ___uri)) {
						state = 18;
						return;
					}
					break;
				case 18:
					attIdx = context.getAttribute("", "root");
					if (attIdx >= 0) {
						final java.lang.String v = context.eatAttribute(attIdx);
						state = 21;
						eatText2(v);
						continue outer;
					}
					state = 21;
					continue outer;
				case 2:
					if (("binyan" == ___local) && ("" == ___uri)) {
						state = 3;
						return;
					}
					break;
				case 11:
					if (("number" == ___local) && ("" == ___uri)) {
						state = 12;
						return;
					}
					break;
				case 24:
					attIdx = context.getAttribute("", "tense");
					if (attIdx >= 0) {
						final java.lang.String v = context.eatAttribute(attIdx);
						state = 27;
						eatText3(v);
						continue outer;
					}
					state = 27;
					continue outer;
				case 14:
					if (("person" == ___local) && ("" == ___uri)) {
						state = 15;
						return;
					}
					break;
				case 3:
					attIdx = context.getAttribute("", "expansion");
					if (attIdx >= 0) {
						final java.lang.String v = context.eatAttribute(attIdx);
						state = 6;
						eatText4(v);
						continue outer;
					}
					state = 6;
					continue outer;
				case 0:
					attIdx = context.getAttribute("", "binyan");
					if (attIdx >= 0) {
						final java.lang.String v = context.eatAttribute(attIdx);
						state = 3;
						eatText5(v);
						continue outer;
					}
					state = 3;
					continue outer;
				case 23:
					if (("spelling" == ___local) && ("" == ___uri)) {
						state = 24;
						return;
					}
					break;
				case 9:
					attIdx = context.getAttribute("", "number");
					if (attIdx >= 0) {
						final java.lang.String v = context.eatAttribute(attIdx);
						state = 12;
						eatText6(v);
						continue outer;
					}
					state = 12;
					continue outer;
				case 26:
					if (("tense" == ___local) && ("" == ___uri)) {
						state = 27;
						return;
					}
					break;
				case 6:
					attIdx = context.getAttribute("", "gender");
					if (attIdx >= 0) {
						final java.lang.String v = context.eatAttribute(attIdx);
						state = 9;
						eatText7(v);
						continue outer;
					}
					state = 9;
					continue outer;
				case 5:
					if (("expansion" == ___local) && ("" == ___uri)) {
						state = 6;
						return;
					}
					break;
				case 8:
					if (("gender" == ___local) && ("" == ___uri)) {
						state = 9;
						return;
					}
					break;
				case 12:
					attIdx = context.getAttribute("", "person");
					if (attIdx >= 0) {
						final java.lang.String v = context.eatAttribute(attIdx);
						state = 15;
						eatText8(v);
						continue outer;
					}
					state = 15;
					continue outer;
				case 21:
					attIdx = context.getAttribute("", "spelling");
					if (attIdx >= 0) {
						final java.lang.String v = context.eatAttribute(attIdx);
						state = 24;
						eatText9(v);
						continue outer;
					}
					state = 24;
					continue outer;
				case 20:
					if (("root" == ___local) && ("" == ___uri)) {
						state = 21;
						return;
					}
					break;
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
				case 27:
					revertToParentFromLeaveElement(___uri, ___local, ___qname);
					return;
				case 15:
					attIdx = context.getAttribute("", "register");
					if (attIdx >= 0) {
						final java.lang.String v = context.eatAttribute(attIdx);
						state = 18;
						eatText1(v);
						continue outer;
					}
					state = 18;
					continue outer;
				case 18:
					attIdx = context.getAttribute("", "root");
					if (attIdx >= 0) {
						final java.lang.String v = context.eatAttribute(attIdx);
						state = 21;
						eatText2(v);
						continue outer;
					}
					state = 21;
					continue outer;
				case 24:
					attIdx = context.getAttribute("", "tense");
					if (attIdx >= 0) {
						final java.lang.String v = context.eatAttribute(attIdx);
						state = 27;
						eatText3(v);
						continue outer;
					}
					state = 27;
					continue outer;
				case 3:
					attIdx = context.getAttribute("", "expansion");
					if (attIdx >= 0) {
						final java.lang.String v = context.eatAttribute(attIdx);
						state = 6;
						eatText4(v);
						continue outer;
					}
					state = 6;
					continue outer;
				case 0:
					attIdx = context.getAttribute("", "binyan");
					if (attIdx >= 0) {
						final java.lang.String v = context.eatAttribute(attIdx);
						state = 3;
						eatText5(v);
						continue outer;
					}
					state = 3;
					continue outer;
				case 9:
					attIdx = context.getAttribute("", "number");
					if (attIdx >= 0) {
						final java.lang.String v = context.eatAttribute(attIdx);
						state = 12;
						eatText6(v);
						continue outer;
					}
					state = 12;
					continue outer;
				case 6:
					attIdx = context.getAttribute("", "gender");
					if (attIdx >= 0) {
						final java.lang.String v = context.eatAttribute(attIdx);
						state = 9;
						eatText7(v);
						continue outer;
					}
					state = 9;
					continue outer;
				case 12:
					attIdx = context.getAttribute("", "person");
					if (attIdx >= 0) {
						final java.lang.String v = context.eatAttribute(attIdx);
						state = 15;
						eatText8(v);
						continue outer;
					}
					state = 15;
					continue outer;
				case 21:
					attIdx = context.getAttribute("", "spelling");
					if (attIdx >= 0) {
						final java.lang.String v = context.eatAttribute(attIdx);
						state = 24;
						eatText9(v);
						continue outer;
					}
					state = 24;
					continue outer;
				}
				super.leaveElement(___uri, ___local, ___qname);
				break;
			}
		}

		@Override
		public java.lang.Object owner() {
			return generated.impl.VerbTypeImpl.this;
		}

	}

	protected java.lang.String _Tense;
	protected java.lang.String _Register;
	protected java.lang.String _Gender;
	protected java.lang.String _Number;
	protected java.lang.String _Root;
	protected java.lang.String _Spelling;
	protected java.lang.String _Person;
	protected java.lang.String _Expansion;
	protected java.lang.String _Binyan;
	public final static java.lang.Class version = (generated.impl.JAXBVersion.class);

	private static com.sun.msv.grammar.Grammar schemaFragment;

	private final static java.lang.Class PRIMARY_INTERFACE_CLASS() {
		return (generated.VerbType.class);
	}

	@Override
	public com.sun.msv.verifier.DocumentDeclaration createRawValidator() {
		if (schemaFragment == null) {
			schemaFragment = com.sun.xml.bind.validator.SchemaDeserializer
					.deserialize(("\u00ac\u00ed\u0000\u0005sr\u0000\u001fcom.sun.msv.grammar.SequenceExp\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0001\u0002\u0000\u0000xr\u0000\u001dcom.su"
							+ "n.msv.grammar.BinaryExp\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0001\u0002\u0000\u0002L\u0000\u0004exp1t\u0000 Lcom/sun/msv/gra"
							+ "mmar/Expression;L\u0000\u0004exp2q\u0000~\u0000\u0002xr\u0000\u001ecom.sun.msv.grammar.Expressi"
							+ "on\u00f8\u0018\u0082\u00e8N5~O\u0002\u0000\u0002L\u0000\u0013epsilonReducibilityt\u0000\u0013Ljava/lang/Boolean;L\u0000\u000b"
							+ "expandedExpq\u0000~\u0000\u0002xpppsq\u0000~\u0000\u0000ppsq\u0000~\u0000\u0000ppsq\u0000~\u0000\u0000ppsq\u0000~\u0000\u0000ppsq\u0000~\u0000\u0000pp"
							+ "sq\u0000~\u0000\u0000ppsq\u0000~\u0000\u0000ppsr\u0000\u001dcom.sun.msv.grammar.ChoiceExp\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0001\u0002\u0000\u0000"
							+ "xq\u0000~\u0000\u0001ppsr\u0000 com.sun.msv.grammar.AttributeExp\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0001\u0002\u0000\u0002L\u0000\u0003ex"
							+ "pq\u0000~\u0000\u0002L\u0000\tnameClasst\u0000\u001fLcom/sun/msv/grammar/NameClass;xq\u0000~\u0000\u0003sr"
							+ "\u0000\u0011java.lang.Boolean\u00cd r\u0080\u00d5\u009c\u00fa\u00ee\u0002\u0000\u0001Z\u0000\u0005valuexp\u0000psr\u0000\u001bcom.sun.msv.gr"
							+ "ammar.DataExp\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0001\u0002\u0000\u0003L\u0000\u0002dtt\u0000\u001fLorg/relaxng/datatype/Dataty"
							+ "pe;L\u0000\u0006exceptq\u0000~\u0000\u0002L\u0000\u0004namet\u0000\u001dLcom/sun/msv/util/StringPair;xq\u0000~"
							+ "\u0000\u0003ppsr\u0000)com.sun.msv.datatype.xsd.EnumerationFacet\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0001\u0002\u0000\u0001"
							+ "L\u0000\u0006valuest\u0000\u000fLjava/util/Set;xr\u00009com.sun.msv.datatype.xsd.Data"
							+ "TypeWithValueConstraintFacet\"\u00a7Ro\u00ca\u00c7\u008aT\u0002\u0000\u0000xr\u0000*com.sun.msv.datat"
							+ "ype.xsd.DataTypeWithFacet\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0001\u0002\u0000\u0005Z\u0000\fisFacetFixedZ\u0000\u0012needVa"
							+ "lueCheckFlagL\u0000\bbaseTypet\u0000)Lcom/sun/msv/datatype/xsd/XSDataty"
							+ "peImpl;L\u0000\fconcreteTypet\u0000\'Lcom/sun/msv/datatype/xsd/ConcreteT"
							+ "ype;L\u0000\tfacetNamet\u0000\u0012Ljava/lang/String;xr\u0000\'com.sun.msv.datatyp"
							+ "e.xsd.XSDatatypeImpl\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0001\u0002\u0000\u0003L\u0000\fnamespaceUriq\u0000~\u0000\u001eL\u0000\btypeNa"
							+ "meq\u0000~\u0000\u001eL\u0000\nwhiteSpacet\u0000.Lcom/sun/msv/datatype/xsd/WhiteSpaceP"
							+ "rocessor;xpt\u0000\u0000t\u0000\nBinyanTypesr\u00005com.sun.msv.datatype.xsd.Whit"
							+ "eSpaceProcessor$Collapse\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0001\u0002\u0000\u0000xr\u0000,com.sun.msv.datatype."
							+ "xsd.WhiteSpaceProcessor\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0001\u0002\u0000\u0000xp\u0000\u0000sr\u0000\"com.sun.msv.dataty"
							+ "pe.xsd.TokenType\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0001\u0002\u0000\u0000xr\u0000#com.sun.msv.datatype.xsd.Stri"
							+ "ngType\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0001\u0002\u0000\u0001Z\u0000\risAlwaysValidxr\u0000*com.sun.msv.datatype.xs"
							+ "d.BuiltinAtomicType\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0001\u0002\u0000\u0000xr\u0000%com.sun.msv.datatype.xsd.C"
							+ "oncreteType\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0001\u0002\u0000\u0000xq\u0000~\u0000\u001ft\u0000 http://www.w3.org/2001/XMLSch"
							+ "emat\u0000\u0005tokenq\u0000~\u0000&\u0001q\u0000~\u0000+t\u0000\u000benumerationsr\u0000\u0011java.util.HashSet\u00baD\u0085"
							+ "\u0095\u0096\u00b8\u00b74\u0003\u0000\u0000xpw\f\u0000\u0000\u0000\u0010?@\u0000\u0000\u0000\u0000\u0000\bt\u0000\u000bunspecifiedt\u0000\u0006Nif\'alt\u0000\u0006Hif\'ilt\u0000\bH"
							+ "itpa\'elt\u0000\u0005Pa\'alt\u0000\u0006Huf\'alt\u0000\u0005Pu\'alt\u0000\u0005Pi\'elxsr\u00000com.sun.msv.gra"
							+ "mmar.Expression$NullSetExpression\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0001\u0002\u0000\u0000xq\u0000~\u0000\u0003ppsr\u0000\u001bcom."
							+ "sun.msv.util.StringPair\u00d0t\u001ejB\u008f\u008d\u00a0\u0002\u0000\u0002L\u0000\tlocalNameq\u0000~\u0000\u001eL\u0000\fnamesp"
							+ "aceURIq\u0000~\u0000\u001expq\u0000~\u0000#q\u0000~\u0000\"sr\u0000#com.sun.msv.grammar.SimpleNameCla"
							+ "ss\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0001\u0002\u0000\u0002L\u0000\tlocalNameq\u0000~\u0000\u001eL\u0000\fnamespaceURIq\u0000~\u0000\u001exr\u0000\u001dcom.su"
							+ "n.msv.grammar.NameClass\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0001\u0002\u0000\u0000xpt\u0000\u0006binyanq\u0000~\u0000\"sr\u00000com.su"
							+ "n.msv.grammar.Expression$EpsilonExpression\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0001\u0002\u0000\u0000xq\u0000~\u0000\u0003s"
							+ "q\u0000~\u0000\u0012\u0001q\u0000~\u0000Bsq\u0000~\u0000\rppsq\u0000~\u0000\u000fq\u0000~\u0000\u0013psq\u0000~\u0000\u0014ppq\u0000~\u0000+q\u0000~\u0000:sq\u0000~\u0000;q\u0000~\u0000-"
							+ "q\u0000~\u0000,sq\u0000~\u0000=t\u0000\texpansionq\u0000~\u0000\"q\u0000~\u0000Bsq\u0000~\u0000\rppsq\u0000~\u0000\u000fq\u0000~\u0000\u0013psq\u0000~\u0000\u0014p"
							+ "psq\u0000~\u0000\u0018q\u0000~\u0000\"t\u0000\nGenderTypeq\u0000~\u0000&\u0000\u0000q\u0000~\u0000+q\u0000~\u0000+q\u0000~\u0000.sq\u0000~\u0000/w\f\u0000\u0000\u0000\u0010?"
							+ "@\u0000\u0000\u0000\u0000\u0000\u0005t\u0000\u000bunspecifiedt\u0000\bfemininet\u0000\nirrelevantt\u0000\u0016masculine an"
							+ "d femininet\u0000\tmasculinexq\u0000~\u0000:sq\u0000~\u0000;q\u0000~\u0000Nq\u0000~\u0000\"sq\u0000~\u0000=t\u0000\u0006genderq"
							+ "\u0000~\u0000\"q\u0000~\u0000Bsq\u0000~\u0000\rppsq\u0000~\u0000\u000fq\u0000~\u0000\u0013psq\u0000~\u0000\u0014ppsq\u0000~\u0000\u0018q\u0000~\u0000\"t\u0000\nNumberTyp"
							+ "eq\u0000~\u0000&\u0000\u0000q\u0000~\u0000+q\u0000~\u0000+q\u0000~\u0000.sq\u0000~\u0000/w\f\u0000\u0000\u0000\u0010?@\u0000\u0000\u0000\u0000\u0000\u0006t\u0000\u000bunspecifiedt\u0000\u0013"
							+ "singular and pluralt\u0000\u0004dualt\u0000\u000fdual and pluralt\u0000\bsingulart\u0000\u0006pl"
							+ "uralxq\u0000~\u0000:sq\u0000~\u0000;q\u0000~\u0000\\q\u0000~\u0000\"sq\u0000~\u0000=t\u0000\u0006numberq\u0000~\u0000\"q\u0000~\u0000Bsq\u0000~\u0000\rpps"
							+ "q\u0000~\u0000\u000fq\u0000~\u0000\u0013psq\u0000~\u0000\u0014ppsq\u0000~\u0000\u0018q\u0000~\u0000\"t\u0000\nPersonTypeq\u0000~\u0000&\u0000\u0000q\u0000~\u0000+q\u0000~\u0000+"
							+ "q\u0000~\u0000.sq\u0000~\u0000/w\f\u0000\u0000\u0000\u0010?@\u0000\u0000\u0000\u0000\u0000\u0005t\u0000\u000bunspecifiedt\u0000\u00013t\u0000\u00012t\u0000\u0003anyt\u0000\u00011xq\u0000"
							+ "~\u0000:sq\u0000~\u0000;q\u0000~\u0000kq\u0000~\u0000\"sq\u0000~\u0000=t\u0000\u0006personq\u0000~\u0000\"q\u0000~\u0000Bsq\u0000~\u0000\rppsq\u0000~\u0000\u000fq\u0000"
							+ "~\u0000\u0013psq\u0000~\u0000\u0014ppsq\u0000~\u0000\u0018q\u0000~\u0000\"t\u0000\fRegisterTypeq\u0000~\u0000&\u0000\u0000q\u0000~\u0000+q\u0000~\u0000+q\u0000~\u0000."
							+ "sq\u0000~\u0000/w\f\u0000\u0000\u0000\u0010?@\u0000\u0000\u0000\u0000\u0000\u0003t\u0000\u0006formalt\u0000\binformalt\u0000\u0007archaicxq\u0000~\u0000:sq\u0000~"
							+ "\u0000;q\u0000~\u0000yq\u0000~\u0000\"sq\u0000~\u0000=t\u0000\bregisterq\u0000~\u0000\"q\u0000~\u0000Bsq\u0000~\u0000\rppsq\u0000~\u0000\u000fq\u0000~\u0000\u0013pq"
							+ "\u0000~\u0000Fsq\u0000~\u0000=t\u0000\u0004rootq\u0000~\u0000\"q\u0000~\u0000Bsq\u0000~\u0000\rppsq\u0000~\u0000\u000fq\u0000~\u0000\u0013psq\u0000~\u0000\u0014ppsq\u0000~\u0000"
							+ "\u0018q\u0000~\u0000\"t\u0000\fSpellingTypeq\u0000~\u0000&\u0000\u0000q\u0000~\u0000+q\u0000~\u0000+q\u0000~\u0000.sq\u0000~\u0000/w\f\u0000\u0000\u0000\u0010?@\u0000\u0000\u0000"
							+ "\u0000\u0000\u0002t\u0000\tirregulart\u0000\bstandardxq\u0000~\u0000:sq\u0000~\u0000;q\u0000~\u0000\u0089q\u0000~\u0000\"sq\u0000~\u0000=t\u0000\bspe"
							+ "llingq\u0000~\u0000\"q\u0000~\u0000Bsq\u0000~\u0000\rppsq\u0000~\u0000\u000fq\u0000~\u0000\u0013psq\u0000~\u0000\u0014ppsq\u0000~\u0000\u0018q\u0000~\u0000\"t\u0000\tTen"
							+ "seTypeq\u0000~\u0000&\u0000\u0000q\u0000~\u0000+q\u0000~\u0000+q\u0000~\u0000.sq\u0000~\u0000/w\f\u0000\u0000\u0000\u0010?@\u0000\u0000\u0000\u0000\u0000\bt\u0000\u000bunspecifi"
							+ "edt\u0000\u0004pastt\u0000\nimperativet\u0000\u0007beinonit\u0000\u0007presentt\u0000\ninfinitivet\u0000\u0006fu"
							+ "turet\u0000\u000ebareInfinitivexq\u0000~\u0000:sq\u0000~\u0000;q\u0000~\u0000\u0094q\u0000~\u0000\"sq\u0000~\u0000=t\u0000\u0005tenseq\u0000~"
							+ "\u0000\"q\u0000~\u0000Bsr\u0000\"com.sun.msv.grammar.ExpressionPool\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0001\u0002\u0000\u0001L\u0000\be"
							+ "xpTablet\u0000/Lcom/sun/msv/grammar/ExpressionPool$ClosedHash;xps"
							+ "r\u0000-com.sun.msv.grammar.ExpressionPool$ClosedHash\u00d7j\u00d0N\u00ef\u00e8\u00ed\u001c\u0003\u0000\u0003I"
							+ "\u0000\u0005countB\u0000\rstreamVersionL\u0000\u0006parentt\u0000$Lcom/sun/msv/grammar/Expr"
							+ "essionPool;xp\u0000\u0000\u0000\u0011\u0001pq\u0000~\u0000\fq\u0000~\u0000\u000bq\u0000~\u0000\u0081q\u0000~\u0000Jq\u0000~\u0000\u0085q\u0000~\u0000gq\u0000~\u0000\bq\u0000~\u0000\nq"
							+ "\u0000~\u0000\u0006q\u0000~\u0000Xq\u0000~\u0000\u0007q\u0000~\u0000\u0005q\u0000~\u0000\u0090q\u0000~\u0000uq\u0000~\u0000Dq\u0000~\u0000\tq\u0000~\u0000\u000ex"));
		}
		return new com.sun.msv.verifier.regexp.REDocumentDeclaration(
				schemaFragment);
	}

	@Override
	public generated.impl.runtime.UnmarshallingEventHandler createUnmarshaller(
			generated.impl.runtime.UnmarshallingContext context) {
		return new generated.impl.VerbTypeImpl.Unmarshaller(context);
	}

	@Override
	public java.lang.String getBinyan() {
		if (_Binyan == null) {
			return "unspecified";
		} else {
			return _Binyan;
		}
	}

	@Override
	public java.lang.String getExpansion() {
		return _Expansion;
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
	public java.lang.String getPerson() {
		if (_Person == null) {
			return "unspecified";
		} else {
			return _Person;
		}
	}

	@Override
	public java.lang.Class getPrimaryInterface() {
		return (generated.VerbType.class);
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
	public java.lang.String getRoot() {
		return _Root;
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
	public java.lang.String getTense() {
		if (_Tense == null) {
			return "unspecified";
		} else {
			return _Tense;
		}
	}

	@Override
	public void serializeAttributes(generated.impl.runtime.XMLSerializer context)
			throws org.xml.sax.SAXException {
		if (_Binyan != null) {
			context.startAttribute("", "binyan");
			try {
				context.text(_Binyan, "Binyan");
			} catch (java.lang.Exception e) {
				generated.impl.runtime.Util.handlePrintConversionException(
						this, e, context);
			}
			context.endAttribute();
		}
		if (_Expansion != null) {
			context.startAttribute("", "expansion");
			try {
				context.text(_Expansion, "Expansion");
			} catch (java.lang.Exception e) {
				generated.impl.runtime.Util.handlePrintConversionException(
						this, e, context);
			}
			context.endAttribute();
		}
		if (_Gender != null) {
			context.startAttribute("", "gender");
			try {
				context.text(_Gender, "Gender");
			} catch (java.lang.Exception e) {
				generated.impl.runtime.Util.handlePrintConversionException(
						this, e, context);
			}
			context.endAttribute();
		}
		if (_Number != null) {
			context.startAttribute("", "number");
			try {
				context.text(_Number, "Number");
			} catch (java.lang.Exception e) {
				generated.impl.runtime.Util.handlePrintConversionException(
						this, e, context);
			}
			context.endAttribute();
		}
		if (_Person != null) {
			context.startAttribute("", "person");
			try {
				context.text(_Person, "Person");
			} catch (java.lang.Exception e) {
				generated.impl.runtime.Util.handlePrintConversionException(
						this, e, context);
			}
			context.endAttribute();
		}
		if (_Register != null) {
			context.startAttribute("", "register");
			try {
				context.text(_Register, "Register");
			} catch (java.lang.Exception e) {
				generated.impl.runtime.Util.handlePrintConversionException(
						this, e, context);
			}
			context.endAttribute();
		}
		if (_Root != null) {
			context.startAttribute("", "root");
			try {
				context.text(_Root, "Root");
			} catch (java.lang.Exception e) {
				generated.impl.runtime.Util.handlePrintConversionException(
						this, e, context);
			}
			context.endAttribute();
		}
		if (_Spelling != null) {
			context.startAttribute("", "spelling");
			try {
				context.text(_Spelling, "Spelling");
			} catch (java.lang.Exception e) {
				generated.impl.runtime.Util.handlePrintConversionException(
						this, e, context);
			}
			context.endAttribute();
		}
		if (_Tense != null) {
			context.startAttribute("", "tense");
			try {
				context.text(_Tense, "Tense");
			} catch (java.lang.Exception e) {
				generated.impl.runtime.Util.handlePrintConversionException(
						this, e, context);
			}
			context.endAttribute();
		}
	}

	@Override
	public void serializeBody(generated.impl.runtime.XMLSerializer context)
			throws org.xml.sax.SAXException {
	}

	@Override
	public void serializeURIs(generated.impl.runtime.XMLSerializer context)
			throws org.xml.sax.SAXException {
	}

	@Override
	public void setBinyan(java.lang.String value) {
		_Binyan = value;
	}

	@Override
	public void setExpansion(java.lang.String value) {
		_Expansion = value;
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
	public void setPerson(java.lang.String value) {
		_Person = value;
	}

	@Override
	public void setRegister(java.lang.String value) {
		_Register = value;
	}

	@Override
	public void setRoot(java.lang.String value) {
		_Root = value;
	}

	@Override
	public void setSpelling(java.lang.String value) {
		_Spelling = value;
	}

	@Override
	public void setTense(java.lang.String value) {
		_Tense = value;
	}

}
