package org.mila;

import javax.xml.bind.JAXBException;

import org.junit.Test;
import org.mila.entities.lexicon.Item;
import org.mila.entities.lexicon.Lexicon;
import org.mila.entities.lexicon.ObjectFactory;
import org.mila.entities.lexicon.XMWEAnything;
import org.mila.entities.lexicon.XMWEAtom;
import org.mila.entities.lexicon.XMWEAtomList;
import org.mila.entities.lexicon.XMWEAtomPointer;
import org.mila.entities.lexicon.XMWELexicon;
import org.mila.entities.lexicon.XMWETemplate;
import org.mila.entities.lexicon.XMWETemplateList;
import org.mila.lexicon.XMLReader;
import org.mila.lexicon.XMLWriter;
import org.xml.sax.SAXException;

public class XMWETest {
    final String XMLFilename = "lexicon_20120515_fixed.xml";

    @Test
    public void generateArbitraryXMWE() throws JAXBException, SAXException {
	ObjectFactory lexobjfactory = new ObjectFactory();
	Lexicon lexicon = lexobjfactory.createLexicon();
	/* Load the current lexicon */
	XMLReader rdr = new XMLReader(this.getClass().getClassLoader()
		.getResourceAsStream(XMLFilename));

	/* Add some stuff we're going to reference from the old lexicon */
	Item i8442 = null;
	Item i3382 = null;
	Item i4917 = null;
	Item i608 = null;

	for (Item item : rdr.getItems()) {
	    switch (Integer.valueOf(item.getId().substring(1))) {
	    case 8442:
		if (item.getId().equals("I8442"))
		    i8442 = item;
	    case 3382:
		if (item.getId().equals("I3382"))
		    i3382 = item;
	    case 4917:
		if (item.getId().equals("I4917"))
		    i4917 = item;
	    case 608:
		if (item.getId().equals("I608"))
		    i608 = item;
		System.out.println(item.getId());
		lexicon.getItem().add(item);
		break;
	    default:
	    }
	}
	/* I need it to be final so Java passes it to the lower scopes. Annoying */
	final Item fi8442 = i8442;
	final Item fi3382 = i3382;
	final Item fi4917 = i4917;
	final Item fi608 = i608;

	/* Create our new stuff */
	lexicon.getItem().add(new Item() {
	    private static final long serialVersionUID = 1L;

	    {
		setId("I23986"); /*
				  * The ID can't actually be an integer.
				  * Annoying
				  */
		setTransliterated("akl at + bli mlx");
		setSubitem(new XMWELexicon() {
		    private static final long serialVersionUID = 1L;
		    {
			setAtoms(new XMWEAtomList() {
			    private static final long serialVersionUID = 1L;
			    {
				getAtom().add(new XMWEAtom() {
				    private static final long serialVersionUID = 1L;
				    {
					setId("I23986X1");
					setPointer(fi8442);
				    }
				});
				getAtom().add(new XMWEAtom() {
				    private static final long serialVersionUID = 1L;
				    {
					setId("I23986X2");
					setPointer(fi3382);
				    }
				});
				getAtom().add(new XMWEAtom() {
				    private static final long serialVersionUID = 1L;
				    {
					setId("I23986X4");
					setPointer(fi4917);
				    }
				});
				getAtom().add(new XMWEAtom() {
				    private static final long serialVersionUID = 1L;
				    {
					setId("I23986X5");
					setPointer(fi608);
				    }
				});
			    }
			});
			setTemplates(new XMWETemplateList() {
			    private static final long serialVersionUID = 1L;
			    {
				/* First template */
				getTemplate().add(new XMWETemplate() {
				    private static final long serialVersionUID = 1L;
				    {
					getAtomOrAny().add(
						new XMWEAtomPointer() {
						    private static final long serialVersionUID = 1L;
						    {
							setPointer(getAtoms()
								.getAtom().get(
									0));
						    }
						});
					getAtomOrAny().add(
						new XMWEAtomPointer() {
						    private static final long serialVersionUID = 1L;
						    {
							setPointer(getAtoms()
								.getAtom().get(
									1));
						    }
						});
					getAtomOrAny().add(
						new XMWEAtomPointer() {
						    private static final long serialVersionUID = 1L;
						    {
							setPointer(getAtoms()
								.getAtom().get(
									2));
						    }
						});
					getAtomOrAny().add(
						new XMWEAtomPointer() {
						    private static final long serialVersionUID = 1L;
						    {
							setPointer(getAtoms()
								.getAtom().get(
									3));
						    }
						});

				    }
				});

				/* Second template */
				getTemplate().add(new XMWETemplate() {
				    private static final long serialVersionUID = 1L;
				    {
					getAtomOrAny().add(
						new XMWEAtomPointer() {
						    private static final long serialVersionUID = 1L;
						    {
							setPointer(getAtoms()
								.getAtom().get(
									1));
						    }
						});
					getAtomOrAny().add(
						new XMWEAtomPointer() {
						    private static final long serialVersionUID = 1L;
						    {
							setPointer(getAtoms()
								.getAtom().get(
									0));
						    }
						});
					getAtomOrAny().add(
						new XMWEAtomPointer() {
						    private static final long serialVersionUID = 1L;
						    {
							setPointer(getAtoms()
								.getAtom().get(
									2));
						    }
						});
					getAtomOrAny().add(
						new XMWEAtomPointer() {
						    private static final long serialVersionUID = 1L;
						    {
							setPointer(getAtoms()
								.getAtom().get(
									3));
						    }
						});

				    }
				});

				/* Third template */
				getTemplate().add(new XMWETemplate() {
				    private static final long serialVersionUID = 1L;
				    {
					getAtomOrAny().add(
						new XMWEAtomPointer() {
						    private static final long serialVersionUID = 1L;
						    {
							setPointer(getAtoms()
								.getAtom().get(
									0));
						    }
						});
					getAtomOrAny().add(
						new XMWEAtomPointer() {
						    private static final long serialVersionUID = 1L;
						    {
							setPointer(getAtoms()
								.getAtom().get(
									1));
						    }
						});
					getAtomOrAny().add(new XMWEAnything());
					getAtomOrAny().add(
						new XMWEAtomPointer() {
						    private static final long serialVersionUID = 1L;
						    {
							setPointer(getAtoms()
								.getAtom().get(
									2));
						    }
						});
					getAtomOrAny().add(
						new XMWEAtomPointer() {
						    private static final long serialVersionUID = 1L;
						    {
							setPointer(getAtoms()
								.getAtom().get(
									3));
						    }
						});

				    }
				});
			    }
			});
		    }
		});

	    }
	});

	/* Awesome test, eh? */
	XMLWriter writer = new XMLWriter(null);
	writer.write(System.out, lexicon.getItem());
	return;
    }
}
