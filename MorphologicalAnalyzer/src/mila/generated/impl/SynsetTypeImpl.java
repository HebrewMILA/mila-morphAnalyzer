//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v1.0.5-b16-fcs 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2008.12.01 at 02:23:12 PM IST 
//

package mila.generated.impl;

public class SynsetTypeImpl implements mila.generated.SynsetType,
		com.sun.xml.bind.JAXBObject,
		mila.generated.impl.runtime.UnmarshallableObject,
		mila.generated.impl.runtime.XMLSerializable,
		mila.generated.impl.runtime.ValidatableObject {

	public class Unmarshaller extends
			mila.generated.impl.runtime.AbstractUnmarshallingEventHandlerImpl {

		public Unmarshaller(mila.generated.impl.runtime.UnmarshallingContext context) {
			super(context, "----");
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
				_Id = com.sun.xml.bind.WhiteSpaceProcessor.collapse(value);
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
				case 3:
					revertToParentFromEnterAttribute(___uri, ___local, ___qname);
					return;
				case 0:
					if (("id" == ___local) && ("" == ___uri)) {
						state = 1;
						return;
					}
					break;
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
				case 3:
					revertToParentFromEnterElement(___uri, ___local, ___qname,
							__atts);
					return;
				case 0:
					attIdx = context.getAttribute("", "id");
					if (attIdx >= 0) {
						final java.lang.String v = context.eatAttribute(attIdx);
						state = 3;
						eatText1(v);
						continue outer;
					}
					break;
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
					case 3:
						revertToParentFromText(value);
						return;
					case 0:
						attIdx = context.getAttribute("", "id");
						if (attIdx >= 0) {
							final java.lang.String v = context
									.eatAttribute(attIdx);
							state = 3;
							eatText1(v);
							continue outer;
						}
						break;
					case 1:
						state = 2;
						eatText1(value);
						return;
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
				case 3:
					revertToParentFromLeaveAttribute(___uri, ___local, ___qname);
					return;
				case 0:
					attIdx = context.getAttribute("", "id");
					if (attIdx >= 0) {
						final java.lang.String v = context.eatAttribute(attIdx);
						state = 3;
						eatText1(v);
						continue outer;
					}
					break;
				case 2:
					if (("id" == ___local) && ("" == ___uri)) {
						state = 3;
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
				case 3:
					revertToParentFromLeaveElement(___uri, ___local, ___qname);
					return;
				case 0:
					attIdx = context.getAttribute("", "id");
					if (attIdx >= 0) {
						final java.lang.String v = context.eatAttribute(attIdx);
						state = 3;
						eatText1(v);
						continue outer;
					}
					break;
				}
				super.leaveElement(___uri, ___local, ___qname);
				break;
			}
		}

		@Override
		public java.lang.Object owner() {
			return mila.generated.impl.SynsetTypeImpl.this;
		}

	}

	protected java.lang.String _Id;
	public final static java.lang.Class version = (mila.generated.impl.JAXBVersion.class);

	private static com.sun.msv.grammar.Grammar schemaFragment;

	private final static java.lang.Class PRIMARY_INTERFACE_CLASS() {
		return (mila.generated.SynsetType.class);
	}

	@Override
	public com.sun.msv.verifier.DocumentDeclaration createRawValidator() {
		if (schemaFragment == null) {
			schemaFragment = com.sun.xml.bind.validator.SchemaDeserializer
					.deserialize(("\u00ac\u00ed\u0000\u0005sr\u0000 com.sun.msv.grammar.AttributeExp\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0001\u0002\u0000\u0002L\u0000\u0003expt\u0000 "
							+ "Lcom/sun/msv/grammar/Expression;L\u0000\tnameClasst\u0000\u001fLcom/sun/msv/"
							+ "grammar/NameClass;xr\u0000\u001ecom.sun.msv.grammar.Expression\u00f8\u0018\u0082\u00e8N5~O"
							+ "\u0002\u0000\u0002L\u0000\u0013epsilonReducibilityt\u0000\u0013Ljava/lang/Boolean;L\u0000\u000bexpandedEx"
							+ "pq\u0000~\u0000\u0001xpppsr\u0000\u001bcom.sun.msv.grammar.DataExp\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0001\u0002\u0000\u0003L\u0000\u0002dtt\u0000\u001f"
							+ "Lorg/relaxng/datatype/Datatype;L\u0000\u0006exceptq\u0000~\u0000\u0001L\u0000\u0004namet\u0000\u001dLcom/"
							+ "sun/msv/util/StringPair;xq\u0000~\u0000\u0003ppsr\u0000\"com.sun.msv.datatype.xsd"
							+ ".TokenType\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0001\u0002\u0000\u0000xr\u0000#com.sun.msv.datatype.xsd.StringType"
							+ "\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0001\u0002\u0000\u0001Z\u0000\risAlwaysValidxr\u0000*com.sun.msv.datatype.xsd.Buil"
							+ "tinAtomicType\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0001\u0002\u0000\u0000xr\u0000%com.sun.msv.datatype.xsd.Concret"
							+ "eType\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0001\u0002\u0000\u0000xr\u0000\'com.sun.msv.datatype.xsd.XSDatatypeImpl\u0000"
							+ "\u0000\u0000\u0000\u0000\u0000\u0000\u0001\u0002\u0000\u0003L\u0000\fnamespaceUrit\u0000\u0012Ljava/lang/String;L\u0000\btypeNameq\u0000~"
							+ "\u0000\u000fL\u0000\nwhiteSpacet\u0000.Lcom/sun/msv/datatype/xsd/WhiteSpaceProces"
							+ "sor;xpt\u0000 http://www.w3.org/2001/XMLSchemat\u0000\u0005tokensr\u00005com.sun"
							+ ".msv.datatype.xsd.WhiteSpaceProcessor$Collapse\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0001\u0002\u0000\u0000xr\u0000"
							+ ",com.sun.msv.datatype.xsd.WhiteSpaceProcessor\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0001\u0002\u0000\u0000xp\u0001s"
							+ "r\u00000com.sun.msv.grammar.Expression$NullSetExpression\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0001\u0002"
							+ "\u0000\u0000xq\u0000~\u0000\u0003ppsr\u0000\u001bcom.sun.msv.util.StringPair\u00d0t\u001ejB\u008f\u008d\u00a0\u0002\u0000\u0002L\u0000\tlocal"
							+ "Nameq\u0000~\u0000\u000fL\u0000\fnamespaceURIq\u0000~\u0000\u000fxpq\u0000~\u0000\u0013q\u0000~\u0000\u0012sr\u0000#com.sun.msv.gra"
							+ "mmar.SimpleNameClass\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0001\u0002\u0000\u0002L\u0000\tlocalNameq\u0000~\u0000\u000fL\u0000\fnamespace"
							+ "URIq\u0000~\u0000\u000fxr\u0000\u001dcom.sun.msv.grammar.NameClass\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0001\u0002\u0000\u0000xpt\u0000\u0002idt"
							+ "\u0000\u0000sr\u0000\"com.sun.msv.grammar.ExpressionPool\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0001\u0002\u0000\u0001L\u0000\bexpTab"
							+ "let\u0000/Lcom/sun/msv/grammar/ExpressionPool$ClosedHash;xpsr\u0000-co"
							+ "m.sun.msv.grammar.ExpressionPool$ClosedHash\u00d7j\u00d0N\u00ef\u00e8\u00ed\u001c\u0003\u0000\u0003I\u0000\u0005cou"
							+ "ntB\u0000\rstreamVersionL\u0000\u0006parentt\u0000$Lcom/sun/msv/grammar/Expressio"
							+ "nPool;xp\u0000\u0000\u0000\u0000\u0001px"));
		}
		return new com.sun.msv.verifier.regexp.REDocumentDeclaration(
				schemaFragment);
	}

	@Override
	public mila.generated.impl.runtime.UnmarshallingEventHandler createUnmarshaller(
			mila.generated.impl.runtime.UnmarshallingContext context) {
		return new mila.generated.impl.SynsetTypeImpl.Unmarshaller(context);
	}

	@Override
	public java.lang.String getId() {
		return _Id;
	}

	@Override
	public java.lang.Class getPrimaryInterface() {
		return (mila.generated.SynsetType.class);
	}

	@Override
	public void serializeAttributes(mila.generated.impl.runtime.XMLSerializer context)
			throws org.xml.sax.SAXException {
		context.startAttribute("", "id");
		try {
			context.text(_Id, "Id");
		} catch (java.lang.Exception e) {
			mila.generated.impl.runtime.Util.handlePrintConversionException(this, e,
					context);
		}
		context.endAttribute();
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
	public void setId(java.lang.String value) {
		_Id = value;
	}

}