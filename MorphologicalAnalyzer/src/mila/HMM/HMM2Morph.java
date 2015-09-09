package mila.HMM;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.PropertyException;
import javax.xml.bind.Unmarshaller;

import mila.generated.AnalysisType;
import mila.generated.ArticleType;
import mila.generated.BaseType;
import mila.generated.ConjunctionType;
import mila.generated.CopulaType;
import mila.generated.Corpus;
import mila.generated.ExistentialType;
import mila.generated.GenderNumberPersonType;
import mila.generated.GenderNumberStatusDefinitenessType;
import mila.generated.InterjectionType;
import mila.generated.InterrogativeType;
import mila.generated.MWEType;
import mila.generated.ModalType;
import mila.generated.NegationType;
import mila.generated.NumeralType;
import mila.generated.ObjectFactory;
import mila.generated.ParagraphType;
import mila.generated.ParticipleType;
import mila.generated.PrefixType;
import mila.generated.PronounType;
import mila.generated.ProperNameType;
import mila.generated.QuantifierType;
import mila.generated.SentenceType;
import mila.generated.SuffixType;
import mila.generated.TitleType;
import mila.generated.TokenType;
import mila.generated.VerbType;
import mila.generated.WprefixType;
import mila.generated.ZVLType;
import mila.lexicon.analyse.Data;
import mila.lexicon.analyse.Str2Num;
import mila.lexicon.analyse.Constants.ENUM_HMMPOS;
import mila.lexicon.dbUtils.PrefixRecord;
import mila.lexicon.utils.PrefixRec;
import mila.lexicon.utils.StringUtils;
import mila.lexicon.utils.Translate;

public class HMM2Morph {
	final String JAXB_PACKAGE = "mila.generated";
	final static String UNSPECIFED = "unspecified";
	String outputFile = "";
	BufferedReader bi = null;

	double MWECounter = 0.0;

	// -----------------------------------------------------------------------------------------------------------------
	public String analyzeHMMLine(final String hmmSentence, ArrayList<String> hmmPrefixList) {
		String hmmToken1 = "";
		String hmmToken2 = "";
		String hmmTranlsiteratedToken = "";
		String hmmPos = "";
		int hmmPrefixNumber = -1;
		StringTokenizer hmmSt1 = new StringTokenizer(hmmSentence, "[");

		if (hmmSt1.hasMoreTokens()) {
			hmmToken1 = hmmSt1.nextToken();
			// System.out.println("hmmTokenPos=" + hmmToken1);
			StringTokenizer hmmSt2 = new StringTokenizer(hmmToken1, "(");

			// handle prefix
			int tokensNum = hmmSt2.countTokens();
			if (tokensNum > 1) {
				int currTokenNum = 0;
				String subPrefix = "";
				StringTokenizer hmmSt3 = null;
				String hmmPrefix = "";
				String hmmTempPrefix1 = "";
				String hmmTempPrefix2 = "";
				while (currTokenNum < (tokensNum - 1)) {
					subPrefix = hmmSt2.nextToken();
					currTokenNum++;
					hmmSt3 = new StringTokenizer(subPrefix);
					hmmPos = hmmSt3.nextToken();
					// if (hmmSt3.hasMoreTokens()) hmmPrefix =
					// hmmSt3.nextToken();
					hmmPrefix = hmmSt3.nextToken();
					hmmTempPrefix1 = hmmPrefix.replace(')', ']');
					hmmTempPrefix2 = hmmTempPrefix1.replaceAll("]", "");
					hmmPrefixList.add(Translate.Eng2Heb(hmmTempPrefix2));
				}
				hmmPrefixNumber = currTokenNum;

			}
			String hmmSubToken = "";
			StringTokenizer hmmSt4 = null;

			String hmmTemp0 = "";
			String hmmTemp1 = "";
			String hmmTemp2 = "";
			String hmmTemp3 = "";
			String hmmTemp4 = "";
			String hmmHebToken = "";

			if (hmmSt2.hasMoreTokens()) {
				hmmSubToken = hmmSt2.nextToken();
				// System.out.println("hmmSubToken ="+ hmmSubToken );
				hmmSt4 = new StringTokenizer(hmmSubToken);
				if (hmmSt4.hasMoreTokens()) {
					hmmPos = (hmmSt4.nextToken()).toLowerCase();
					// System.out.println("hmmPos ="+ hmmPos );
					if (hmmSt4.hasMoreTokens()) {
						hmmTemp0 = hmmSt4.nextToken();
						hmmTemp1 = hmmTemp0.replace(')', ']');
						hmmTemp2 = hmmTemp1.replaceAll("]", "");
						hmmTemp3 = hmmTemp2.replaceAll("A", "'");
						hmmTemp4 = hmmTemp3.replaceAll("U", "\"");
						hmmTranlsiteratedToken = hmmTemp4.replaceAll("O", "%");
						// hmmHebToken = Translate.Eng2Heb(hmmTemp5);
						// System.out.println(hebrewHebToken);
					}
				} else {
					System.out.println("problem for: sentence= " + hmmSentence);
					System.out.println("hmmSubToken= " + hmmSubToken);
				}
			} else {
				System.out.println("problem for: sentence= " + hmmSentence);
				System.out.println("subToken= " + hmmToken1);
			}
		}
		return hmmPos;
	}

	// -----------------------------------------------------------------------------------------------------------------
	public int checkPos(final AnalysisType analysis, final String hmmPos, int scoreCounter) throws Exception {
		BaseType base = analysis.getBase();
		if (base.getPunctuation() != null) {
			analysis.setScore(1.0);
			scoreCounter++;
		} else if (base.getMWE() != null) {
			MWECounter++;
			analysis.setScore(1.0);
			scoreCounter++;
		} else {
			ENUM_HMMPOS hmmPosi = Str2Num.str2numHMMPOS(hmmPos, hmmPos, hmmPos);
			switch (hmmPosi) {
			case UNKNOWN:
				if (base.getUnknown() != null) {
					analysis.setScore(1.0);
					scoreCounter++;
				}
				break;
			case ADVERB:
				if (base.getAdverb() != null) {
					analysis.setScore(1.0);
					scoreCounter++;
				}
				break;
			case CONJUNCTION:
				if (base.getConjunction() != null) {
					analysis.setScore(1.0);
					scoreCounter++;
				}
				break;
			case COPULA:
				if (base.getCopula() != null) {
					analysis.setScore(1.0);
					scoreCounter++;
				}
				break;
			case EXISTENTIAL:
				if (base.getExistential() != null) {
					analysis.setScore(1.0);
					scoreCounter++;
				}
				break;
			case FOREIGN:
				if (base.getForeign() != null) {
					analysis.setScore(1.0);
					scoreCounter++;
				}
				break;
			case INTERJECTION:
				if (base.getInterjection() != null) {
					analysis.setScore(1.0);
					scoreCounter++;
				}
				break;
			case INTERROGATIVE:
				if (base.getInterrogative() != null) {
					analysis.setScore(1.0);
					scoreCounter++;
				}
				break;
			case MODAL:
				if (base.getModal() != null) {
					analysis.setScore(1.0);
					scoreCounter++;
				}
				break;
			case NEGATION:
				if (base.getNegation() != null) {
					analysis.setScore(1.0);
					scoreCounter++;
				}
				break;
			case NUMBER_EXPRESSION:
				if (base.getNumberExpression() != null) {
					analysis.setScore(1.0);
					scoreCounter++;
				}
				break;
			case PREPOSITION:
				if (base.getPreposition() != null) {
					analysis.setScore(1.0);
					scoreCounter++;
				}
				break;
			case PRONOUN:
				if (base.getPronoun() != null) {
					analysis.setScore(1.0);
					scoreCounter++;
				}
				break;
			case QUANTIFIER:
				if (base.getQuantifier() != null) {
					analysis.setScore(1.0);
					scoreCounter++;
				}
				break;
			case TITLE:
				if (base.getTitle() != null) {
					analysis.setScore(1.0);
					scoreCounter++;
				}
				break;
			case URL:
				if (base.getUrl() != null) {
					analysis.setScore(1.0);
					scoreCounter++;
				}
				break;
			case WPREFIX:
				if (base.getWPrefix() != null) {
					analysis.setScore(1.0);
					scoreCounter++;
				}
				break;
			case MULTIWORD:
				if (base.getMWE() != null) {
					analysis.setScore(1.0);
					scoreCounter++;
				}
				break;
			case NUMERAL:
				if (base.getNumeral() != null) {
					analysis.setScore(1.0);
					scoreCounter++;
				}
				break;
			case VERBINF:
				if (base.getVerb() != null) {
					VerbType verb = base.getVerb();
					String tense = verb.getTense();
					if (tense != null && tense.charAt(0) == 'i' && tense.charAt(1) == 'n') {
						analysis.setScore(1.0);
						scoreCounter++;
					}
				}
				break;
			case VERB:
				if (base.getVerb() != null) {
					VerbType verb = base.getVerb();
					String tense = verb.getTense();
					if (!(tense.charAt(0) == 'i' && tense.charAt(1) == 'n')) {
						analysis.setScore(1.0);
						scoreCounter++;
					}
				} else if (base.getParticiple() != null) {
					ParticipleType participle = base.getParticiple();
					String participleTypeType = participle.getType();

					if (participleTypeType != null && participleTypeType.charAt(0) == 'v') {
						analysis.setScore(1.0);
						scoreCounter++;
					}
				}
				break;

			case PROPERNAMEDEF:
				if (base.getProperName() != null) {
					ProperNameType propername = base.getProperName();
					String definiteness = propername.getDefiniteness();
					if (definiteness != null && definiteness.charAt(0) == 't') {
						analysis.setScore(1.0);
						scoreCounter++;
					}
				}
				break;
			case PROPERNAME:
				if (base.getProperName() != null) {
					ProperNameType propername = base.getProperName();
					String definiteness = propername.getDefiniteness();
					if (definiteness.charAt(0) == 'f') {
						analysis.setScore(1.0);
						scoreCounter++;
					}
				}
				break;
			case NOUNCONST:
				if (analysis.getSuffix() == null && base.getNoun() != null) {
					GenderNumberStatusDefinitenessType noun = base.getNoun();
					String status = noun.getStatus();
					if (status != null && status.charAt(0) == 'c') {
						analysis.setScore(1.0);
						scoreCounter++;
					}
				} else if (analysis.getSuffix() == null && base.getParticiple() != null) {
					ParticipleType participle = base.getParticiple();
					String participleType = participle.getType();
					if (participleType.charAt(0) == 'n') {
						String status = participle.getStatus();
						if (status != null && status.charAt(0) == 'c') {
							analysis.setScore(1.0);
							scoreCounter++;
						}
					}
				}
				break;
			case NOUNDEF:
				if (analysis.getSuffix() == null && base.getNoun() != null) {
					GenderNumberStatusDefinitenessType noun = base.getNoun();
					String definiteness = noun.getDefiniteness();
					if (definiteness != null && definiteness.charAt(0) == 't') {
						analysis.setScore(1.0);
						scoreCounter++;
					}
				} else if (analysis.getSuffix() == null && base.getParticiple() != null) {
					ParticipleType participle = base.getParticiple();
					String participleType = participle.getType();
					if (participleType.charAt(0) == 'n') {
						String definiteness = participle.getDefiniteness();
						if (definiteness != null && definiteness.charAt(0) == 't') {
							analysis.setScore(1.0);
							scoreCounter++;
						}
					}
				}
				break;
			case NOUNPOSSESSIVE:
				if (base.getNoun() != null && analysis.getSuffix() != null) {
					analysis.setScore(1.0);
					scoreCounter++;

				} else if (base.getParticiple() != null && analysis.getSuffix() != null) {
					analysis.setScore(1.0);
					scoreCounter++;
				}
				break;
			case NOUN:
				if (analysis.getSuffix() == null && base.getNoun() != null) {
					GenderNumberStatusDefinitenessType noun = base.getNoun();
					String status = noun.getStatus();
					String definiteness = noun.getDefiniteness();
					if (status != null && definiteness != null && status.charAt(0) == 'a'
							&& definiteness.charAt(0) == 'f' && analysis.getSuffix() == null) {
						analysis.setScore(1.0);
						scoreCounter++;
					}
				} else if (analysis.getSuffix() == null && base.getParticiple() != null) {
					ParticipleType participle = base.getParticiple();
					String participleType = participle.getType();
					String status = participle.getStatus();
					String definiteness = participle.getDefiniteness();
					if (participleType.charAt(0) == 'n') {
						if (status != null && definiteness != null && status.charAt(0) == 'a'
								&& definiteness.charAt(0) == 'f') {
							analysis.setScore(1.0);
							scoreCounter++;
						}
					}

				}

				break;
			case ADJECTIVEDEF:
				if (base.getAdjective() != null) {
					GenderNumberStatusDefinitenessType adjective = base.getAdjective();
					String definiteness = adjective.getDefiniteness();
					if (definiteness != null && definiteness.charAt(0) == 't') {
						analysis.setScore(1.0);
						scoreCounter++;
					}
				} else if (base.getParticiple() != null) {
					ParticipleType participle = base.getParticiple();
					String participleType = participle.getType();
					if (participleType.charAt(0) == 'a') {
						String definiteness = participle.getDefiniteness();
						if (definiteness != null && definiteness.charAt(0) == 't') {
							analysis.setScore(1.0);
							scoreCounter++;
						}
					}
				}
				break;
			case ADJECTIVECONST:
				if (base.getAdjective() != null) {
					GenderNumberStatusDefinitenessType adjective = base.getAdjective();
					String status = adjective.getStatus();
					if (status != null && status.charAt(0) == 'c') {
						analysis.setScore(1.0);
						scoreCounter++;
					}
				} else if (base.getParticiple() != null) {
					ParticipleType participle = base.getParticiple();
					String participleType = participle.getType();
					if (participleType.charAt(0) == 'a') {
						String status = participle.getStatus();
						if (status != null && status.charAt(0) == 'c') {
							analysis.setScore(1.0);
							scoreCounter++;
						}
					}

				}
				break;
			case ADJECTIVE:
				if (base.getAdjective() != null) {
					GenderNumberStatusDefinitenessType adjective = base.getAdjective();
					String status = adjective.getStatus();
					String definiteness = adjective.getDefiniteness();
					if (status != null && definiteness != null && status.charAt(0) == 'a' // absolute
							&& definiteness.charAt(0) == 'f') {
						analysis.setScore(1.0);
						scoreCounter++;
					}
				} else if (base.getParticiple() != null) {
					ParticipleType participle = base.getParticiple();
					String participleType = participle.getType();
					String status = participle.getStatus();
					String definiteness = participle.getDefiniteness();
					if (participleType.charAt(0) == 'a') {
						if (status != null && definiteness != null && status.charAt(0) == 'a' // absolute
								&& definiteness.charAt(0) == 'f') {
							analysis.setScore(1.0);
							scoreCounter++;
						}
					}

				}
				break;
			case PARTICIPLEDEF:
				String definiteness = "";
				if (analysis.getSuffix() == null && base.getParticiple() != null
						&& (base.getParticiple()).getType().charAt(0) != 'v') {
					ParticipleType participle = base.getParticiple();
					definiteness = participle.getDefiniteness();
					if (definiteness != null && definiteness.charAt(0) == 't') {
						analysis.setScore(1.0);
						scoreCounter++;
					}
				} else if (analysis.getSuffix() == null && base.getNoun() != null) {
					GenderNumberStatusDefinitenessType noun = base.getNoun();
					definiteness = noun.getDefiniteness();
					if (definiteness != null && definiteness.charAt(0) == 't') {
						analysis.setScore(1.0);
						scoreCounter++;
					}
				} else if (base.getAdjective() != null) {
					GenderNumberStatusDefinitenessType adjective = base.getAdjective();
					definiteness = adjective.getDefiniteness();
					if (definiteness != null && definiteness.charAt(0) == 't') {
						analysis.setScore(1.0);
						scoreCounter++;
					}
				}
				break;
			case PARTICIPLECONST:
				if (analysis.getSuffix() == null && base.getParticiple() != null) {
					ParticipleType participle = base.getParticiple();
					String participleTypeType = participle.getType();
					if (participleTypeType != null && participleTypeType.charAt(0) != 'v') {
						String status = participle.getStatus();
						if (participleTypeType != null && status.charAt(0) == 'c') {
							analysis.setScore(1.0);
							scoreCounter++;
						}
					}
				} else if (analysis.getSuffix() == null && base.getNoun() != null) {
					GenderNumberStatusDefinitenessType noun = base.getNoun();
					String status = noun.getStatus();
					if (status != null && status.charAt(0) == 'c') {
						analysis.setScore(1.0);
						scoreCounter++;
					}
				} else if (base.getAdjective() != null) {
					GenderNumberStatusDefinitenessType adjective = base.getAdjective();
					String status = adjective.getStatus();
					if (status != null && status.charAt(0) == 'c') {
						analysis.setScore(1.0);
						scoreCounter++;
					}
				}
				break;
			case PARTICIPLE:
				if (analysis.getSuffix() == null && base.getParticiple() != null) {
					ParticipleType participle = base.getParticiple();
					String participleTypeType = participle.getType();
					if (participleTypeType != null && participleTypeType.charAt(0) != 'v') {
						definiteness = participle.getDefiniteness();
						if (participleTypeType != null && definiteness.charAt(0) == 'f') {
							analysis.setScore(1.0);
							scoreCounter++;
						}
					}
				} else if (analysis.getSuffix() == null && base.getNoun() != null) {
					GenderNumberStatusDefinitenessType noun = base.getNoun();
					String status = noun.getStatus();
					if (status != null && status.charAt(0) == 'a') {
						analysis.setScore(1.0);
						scoreCounter++;
					}
				} else if (base.getAdjective() != null) {
					GenderNumberStatusDefinitenessType adjective = base.getAdjective();
					String status = adjective.getStatus();
					if (status != null && status.charAt(0) == 'a') {
						analysis.setScore(1.0);
						scoreCounter++;
					}
				}
				break;
			}
		}
		return scoreCounter;
	}

	// -----------------------------------------------------------------------------------------------------------------
	private boolean checkPrefix(final AnalysisType analysis, final ArrayList<String> hmmPrefixList) {
		boolean rt = false;
		int morphPrefixListSize = 0;
		int hmmPrefixListSize = 0;
		@SuppressWarnings("unchecked")
		List<PrefixType> morphPrefixList = (List<PrefixType>) analysis.getPrefix();
		if (morphPrefixList != null)
			morphPrefixListSize = morphPrefixList.size();
		if (hmmPrefixList != null)
			hmmPrefixListSize = hmmPrefixList.size();
		if (hmmPrefixListSize == morphPrefixListSize)
			rt = true;
		return rt;
	}

	// -----------------------------------------------------------------------------------------------------------------
	public AnalysisType handleAdjectiveAnalysis(String surface, String analysisId) {
		AnalysisType analysis = null;
		try {
			ObjectFactory objFactory = null;
			objFactory = new ObjectFactory();

			analysis = objFactory.createAnalysisType();

			analysis.setId(analysisId);
			BaseType base = null;
			base = objFactory.createBaseType();
			setBase(base, surface);
			GenderNumberStatusDefinitenessType adjective = null;
			adjective = objFactory.createGenderNumberStatusDefinitenessType();
			adjective.setDefiniteness("false");
			adjective.setStatus("absolute");
			base.setAdjective(adjective);
			analysis.setBase(base);
			analysis.setScore(1.0);
		} catch (JAXBException e) {
			e.printStackTrace();
		}
		return analysis;
	}

	// -----------------------------------------------------------------------------------------------------------------
	public AnalysisType handleAdjectiveConstAnalysis(String surface, String analysisId) {
		AnalysisType analysis = null;
		try {
			ObjectFactory objFactory = null;
			objFactory = new ObjectFactory();

			analysis = objFactory.createAnalysisType();

			analysis.setId(analysisId);
			BaseType base = null;
			base = objFactory.createBaseType();
			GenderNumberStatusDefinitenessType adjective = null;
			adjective = objFactory.createGenderNumberStatusDefinitenessType();
			adjective.setStatus("construct");
			base.setAdjective(adjective);
			analysis.setBase(base);
			analysis.setScore(1.0);
		} catch (JAXBException e) {
			e.printStackTrace();
		}
		return analysis;
	}

	// -----------------------------------------------------------------------------------------------------------------
	public AnalysisType handleAdjectiveDefAnalysis(String surface, String analysisId) {
		AnalysisType analysis = null;
		try {
			ObjectFactory objFactory = null;
			objFactory = new ObjectFactory();

			analysis = objFactory.createAnalysisType();

			analysis.setId(analysisId);
			BaseType base = null;
			base = objFactory.createBaseType();
			setBase(base, surface);
			GenderNumberStatusDefinitenessType adjective = null;
			adjective = objFactory.createGenderNumberStatusDefinitenessType();
			adjective.setDefiniteness("true");
			adjective.setStatus("absolute");
			base.setAdjective(adjective);
			analysis.setBase(base);
			analysis.setScore(1.0);
		} catch (JAXBException e) {
			e.printStackTrace();
		}
		return analysis;
	}

	// -----------------------------------------------------------------------------------------------------------------
	public AnalysisType handleAdverbAnalysis(String surface, String analysisId) {
		AnalysisType analysis = null;
		try {
			ObjectFactory objFactory = null;
			objFactory = new ObjectFactory();

			analysis = objFactory.createAnalysisType();

			analysis.setId(analysisId);
			BaseType base = null;
			base = objFactory.createBaseType();
			GenderNumberPersonType adverb = null;
			adverb = objFactory.createGenderNumberPersonType();
			base.setAdverb(adverb);
			analysis.setBase(base);
			analysis.setScore(1.0);
		} catch (JAXBException e) {
			e.printStackTrace();
		}
		return analysis;
	}

	// -----------------------------------------------------------------------------------------------------------------
	public AnalysisType handleConjunctionAnalysis(String surface, String analysisId) {
		AnalysisType analysis = null;
		try {
			ObjectFactory objFactory = null;
			objFactory = new ObjectFactory();

			analysis = objFactory.createAnalysisType();

			analysis.setId(analysisId);
			BaseType base = null;
			base = objFactory.createBaseType();
			setBase(base, surface);
			ConjunctionType conjunction = null;
			conjunction = objFactory.createConjunctionType();
			base.setConjunction(conjunction);
			analysis.setBase(base);
			analysis.setScore(1.0);
		} catch (JAXBException e) {
			e.printStackTrace();
		}
		return analysis;
	}

	// -----------------------------------------------------------------------------------------------------------------
	public AnalysisType handleCopulaAnalysis(String surface, String analysisId) {
		AnalysisType analysis = null;
		try {
			ObjectFactory objFactory = null;
			objFactory = new ObjectFactory();

			analysis = objFactory.createAnalysisType();

			analysis.setId(analysisId);
			BaseType base = null;
			base = objFactory.createBaseType();
			setBase(base, surface);
			CopulaType copula = null;

			copula = objFactory.createCopulaType();

			base.setCopula(copula);

			analysis.setBase(base);
			analysis.setScore(1.0);
		} catch (JAXBException e) {
			e.printStackTrace();
		}
		return analysis;
	}

	// -----------------------------------------------------------------------------------------------------------------
	public AnalysisType handleExistentialAnalysis(String surface, String analysisId) {
		AnalysisType analysis = null;
		try {
			ObjectFactory objFactory = null;
			objFactory = new ObjectFactory();

			analysis = objFactory.createAnalysisType();

			analysis.setId(analysisId);
			BaseType base = null;
			base = objFactory.createBaseType();
			setBase(base, surface);
			ExistentialType existential = null;

			existential = objFactory.createExistentialType();

			base.setExistential(existential);

			analysis.setBase(base);
			analysis.setScore(1.0);
		} catch (JAXBException e) {
			e.printStackTrace();
		}
		return analysis;
	}

	// -----------------------------------------------------------------------------------------------------------------
	public AnalysisType handleInterjectionAnalysis(String surface, String analysisId) {
		AnalysisType analysis = null;
		try {
			ObjectFactory objFactory = null;
			objFactory = new ObjectFactory();

			analysis = objFactory.createAnalysisType();

			analysis.setId(analysisId);
			BaseType base = null;
			base = objFactory.createBaseType();
			setBase(base, surface);
			InterjectionType interjection = null;
			interjection = objFactory.createInterjectionType();
			base.setInterjection(interjection);
			analysis.setBase(base);
			analysis.setScore(1.0);
		} catch (JAXBException e) {
			e.printStackTrace();
		}
		return analysis;
	}

	// -----------------------------------------------------------------------------------------------------------------
	public AnalysisType handleInterrogativeAnalysis(String surface, String analysisId) {
		AnalysisType analysis = null;
		try {
			ObjectFactory objFactory = null;
			objFactory = new ObjectFactory();

			analysis = objFactory.createAnalysisType();

			analysis.setId(analysisId);
			BaseType base = null;
			base = objFactory.createBaseType();
			setBase(base, surface);
			InterrogativeType interrogative = null;
			interrogative = objFactory.createInterrogativeType();
			base.setInterrogative(interrogative);
			analysis.setBase(base);
			analysis.setScore(1.0);
		} catch (JAXBException e) {
			e.printStackTrace();
		}
		return analysis;
	}

	// -----------------------------------------------------------------------------------------------------------------
	public AnalysisType handleModalAnalysis(String surface, String analysisId) {
		AnalysisType analysis = null;
		try {
			ObjectFactory objFactory = null;
			objFactory = new ObjectFactory();

			analysis = objFactory.createAnalysisType();

			analysis.setId(analysisId);
			BaseType base = null;
			base = objFactory.createBaseType();
			setBase(base, surface);
			ModalType modal = null;

			modal = objFactory.createModalType();
			base.setModal(modal);

			analysis.setBase(base);
			analysis.setScore(1.0);
		} catch (JAXBException e) {
			e.printStackTrace();
		}
		return analysis;
	}

	// -----------------------------------------------------------------------------------------------------------------
	/**
	 * added by yossi jacob 12.4.11 for handling the multiword
	 * 
	 * @param surface
	 * @param analysisId
	 * @return
	 */
	public AnalysisType handleMWEAnalysis(String surface, String analysisId) {
		AnalysisType analysis = null;
		try {
			ObjectFactory objFactory = null;
			objFactory = new ObjectFactory();
			analysis = objFactory.createAnalysisType();

			analysis.setId(analysisId);
			BaseType base = null;
			base = objFactory.createBaseType();
			MWEType mwe = null;
			mwe = objFactory.createMWEType();
			base.setMWE(mwe);
			analysis.setBase(base);
			analysis.setScore(1.0);
		} catch (JAXBException e) {
			e.printStackTrace();
		}
		return analysis;
	}

	// -----------------------------------------------------------------------------------------------------------------
	public AnalysisType handleNegationAnalysis(String surface, String analysisId) {
		AnalysisType analysis = null;
		try {
			ObjectFactory objFactory = null;
			objFactory = new ObjectFactory();

			analysis = objFactory.createAnalysisType();

			analysis.setId(analysisId);
			BaseType base = null;
			base = objFactory.createBaseType();
			setBase(base, surface);
			NegationType negation = null;
			negation = objFactory.createNegationType();
			base.setNegation(negation);
			analysis.setBase(base);
			analysis.setScore(1.0);
		} catch (JAXBException e) {
			e.printStackTrace();
		}
		return analysis;
	}

	// -----------------------------------------------------------------------------------------------------------------
	public AnalysisType handleNounAnalysis(String surface, String analysisId) {
		AnalysisType analysis = null;
		try {
			ObjectFactory objFactory = null;
			objFactory = new ObjectFactory();
			analysis = objFactory.createAnalysisType();
			analysis.setId(analysisId);
			BaseType base = null;
			base = objFactory.createBaseType();
			GenderNumberStatusDefinitenessType noun = null;
			noun = objFactory.createGenderNumberStatusDefinitenessType();
			noun.setDefiniteness("false");
			noun.setStatus("absolute");
			base.setNoun(noun);
			analysis.setBase(base);
			analysis.setScore(1.0);
		} catch (JAXBException e) {
			e.printStackTrace();
		}
		return analysis;
	}

	// -----------------------------------------------------------------------------------------------------------------
	public AnalysisType handleNounConstAnalysis(String surface, String analysisId) {
		AnalysisType analysis = null;
		try {
			ObjectFactory objFactory = null;
			objFactory = new ObjectFactory();

			analysis = objFactory.createAnalysisType();

			analysis.setId(analysisId);
			BaseType base = null;
			base = objFactory.createBaseType();
			setBase(base, surface);
			GenderNumberStatusDefinitenessType noun = null;
			noun = objFactory.createGenderNumberStatusDefinitenessType();
			noun.setStatus("construct");
			analysis.setBase(base);
			base.setNoun(noun);
			analysis.setScore(1.0);
		} catch (JAXBException e) {
			e.printStackTrace();
		}
		return analysis;
	}

	// -----------------------------------------------------------------------------------------------------------------
	public AnalysisType handleNounDefAnalysis(String surface, String analysisId) {
		AnalysisType analysis = null;
		try {
			ObjectFactory objFactory = null;
			objFactory = new ObjectFactory();

			analysis = objFactory.createAnalysisType();

			analysis.setId(analysisId);
			BaseType base = null;
			base = objFactory.createBaseType();
			GenderNumberStatusDefinitenessType noun = null;
			noun = objFactory.createGenderNumberStatusDefinitenessType();
			noun.setDefiniteness("true");
			noun.setStatus("absolute");
			base.setNoun(noun);
			analysis.setBase(base);
			analysis.setScore(1.0);
		} catch (JAXBException e) {
			e.printStackTrace();
		}
		return analysis;
	}

	// -----------------------------------------------------------------------------------------------------------------
	public AnalysisType handleNounPossessiveAnalysis(String surface, String analysisId) {
		AnalysisType analysis = null;
		try {
			ObjectFactory objFactory = null;
			objFactory = new ObjectFactory();

			analysis = objFactory.createAnalysisType();

			analysis.setId(analysisId);
			BaseType base = null;
			base = objFactory.createBaseType();
			setBase(base, surface);
			GenderNumberStatusDefinitenessType noun = null;
			noun = objFactory.createGenderNumberStatusDefinitenessType();
			SuffixType suffix = null;
			try {
				suffix = objFactory.createSuffixType();
			} catch (JAXBException e3) {
				e3.printStackTrace();
			}
			suffix.setFunction("possessive");
			analysis.setSuffix(suffix);

			analysis.setBase(base);
			analysis.setScore(1.0);
		} catch (JAXBException e) {
			e.printStackTrace();
		}
		return analysis;
	}

	// -----------------------------------------------------------------------------------------------------------------
	public AnalysisType handleNumeralAnalysis(String surface, String analysisId) {
		AnalysisType analysis = null;
		try {
			ObjectFactory objFactory = null;
			objFactory = new ObjectFactory();

			analysis = objFactory.createAnalysisType();

			analysis.setId(analysisId);
			BaseType base = null;
			base = objFactory.createBaseType();
			setBase(base, surface);
			NumeralType numeral = null;

			numeral = objFactory.createNumeralType();

			base.setNumeral(numeral);

			analysis.setBase(base);
			analysis.setScore(1.0);
		} catch (JAXBException e) {
			e.printStackTrace();
		}
		return analysis;
	}

	// -----------------------------------------------------------------------------------------------------------------
	public AnalysisType handleParticipleAnalysis(String surface, String analysisId) {
		AnalysisType analysis = null;
		try {
			ObjectFactory objFactory = null;
			objFactory = new ObjectFactory();

			analysis = objFactory.createAnalysisType();

			analysis.setId(analysisId);
			BaseType base = null;
			base = objFactory.createBaseType();
			setBase(base, surface);
			ParticipleType participle = null;
			participle = objFactory.createParticipleType();
			participle.setType("verb");
			base.setParticiple(participle);
			analysis.setBase(base);
			analysis.setScore(1.0);
		} catch (JAXBException e) {
			e.printStackTrace();
		}
		return analysis;
	}

	// -----------------------------------------------------------------------------------------------------------------
	public AnalysisType handleParticipleDefAnalysis(String surface, String analysisId) {
		AnalysisType analysis = null;
		try {
			ObjectFactory objFactory = null;
			objFactory = new ObjectFactory();

			analysis = objFactory.createAnalysisType();

			analysis.setId(analysisId);
			BaseType base = null;
			base = objFactory.createBaseType();
			ParticipleType participle = null;
			participle = objFactory.createParticipleType();
			participle.setSubcoordinating("true");
			participle.setType("verb");
			base.setParticiple(participle);
			analysis.setBase(base);
			analysis.setScore(1.0);
		} catch (JAXBException e) {
			e.printStackTrace();
		}
		return analysis;
	}

	// -----------------------------------------------------------------------------------------------------------------
	public void handlePrefixProperNameAnalysis(TokenType token, String morphSurface, double score) {
		AnalysisType analysis = null;
		try {
			int surfaceLen = morphSurface.length();
			for (int i = 1; i < 6 && i < surfaceLen; i++) {
				String properNamePrefix = morphSurface.substring(0, i);

				String transliteratedproperNamePrefix = Translate.Heb2Eng(properNamePrefix);
				if (StringUtils.moshevkaleb(transliteratedproperNamePrefix)) {
					ObjectFactory objFactory = null;
					objFactory = new ObjectFactory();
					analysis = objFactory.createAnalysisType();
					analysis.setId("1");

					BaseType base = null;
					base = objFactory.createBaseType();
					setBase(base, morphSurface.substring(i));
					ProperNameType properName = null;
					properName = objFactory.createProperNameType();
					properName.setDefiniteness("false");
					base.setProperName(properName);
					analysis.setBase(base);
					analysis.setScore(score);

					int prefixListSize = 0;
					try {
						prefixListSize = Data.getPrefixes(transliteratedproperNamePrefix);
					} catch (Exception e) {
						e.printStackTrace();
					}
					for (int prefixIndex = 0; prefixIndex < prefixListSize; prefixIndex++) {
						PrefixRecord pr = Data.analyzePrefixList(prefixIndex);
						boolean definited = pr.isDefiniteArticleTag();
						if (definited)
							continue;
						else {
							String description = pr.getDescription();

							List<PrefixRec> list = null;
							try {
								list = Translate.analyzeMixedHebEng(description);
							} catch (UnsupportedEncodingException e) {
								System.out.println(
										"CreateCorpusXML:setPrefix Exception while analyzeMixedHebEng for description="
												+ description);
								e.printStackTrace();
							}
							int size = list.size();

							for (int prefixesCounter = 0; prefixesCounter < size; prefixesCounter++) {
								PrefixRec prefixRec = (PrefixRec) list.get(prefixesCounter);
								String prefixSurface = prefixRec.getSurface();
								String prefixFunction = prefixRec.getFunction();
								PrefixType pref = objFactory.createPrefixType();
								pref.setId(String.valueOf(prefixesCounter + 1));
								pref.setFunction(prefixFunction);
								pref.setSurface(prefixSurface);
								analysis.getPrefix().add(pref);

								token.getAnalysis().add(analysis);
							}
						}
					}
				} else
					return;
			}
		} catch (JAXBException e) {
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return;
	}

	// -----------------------------------------------------------------------------------------------------------------
	public AnalysisType handlePrepositionAnalysis(String surface, String analysisId) {
		AnalysisType analysis = null;
		try {
			ObjectFactory objFactory = null;
			objFactory = new ObjectFactory();

			analysis = objFactory.createAnalysisType();

			analysis.setId(analysisId);
			BaseType base = null;
			base = objFactory.createBaseType();
			setBase(base, surface);
			GenderNumberPersonType preposition = null;
			preposition = objFactory.createGenderNumberPersonType();
			base.setPreposition(preposition);
			analysis.setBase(base);
			analysis.setScore(1.0);
		} catch (JAXBException e) {
			e.printStackTrace();
		}
		return analysis;
	}

	// -----------------------------------------------------------------------------------------------------------------
	public AnalysisType handlePronounAnalysis(String surface, String analysisId) {
		AnalysisType analysis = null;
		try {
			ObjectFactory objFactory = null;
			objFactory = new ObjectFactory();

			analysis = objFactory.createAnalysisType();

			analysis.setId(analysisId);
			BaseType base = null;
			base = objFactory.createBaseType();
			PronounType pronoun = null;
			pronoun = objFactory.createPronounType();
			base.setPronoun(pronoun);
			analysis.setBase(base);
			analysis.setScore(1.0);
		} catch (JAXBException e) {
			e.printStackTrace();
		}
		return analysis;
	}

	// -----------------------------------------------------------------------------------------------------------------
	public AnalysisType handleProperNameAnalysis(String surface, String analysisId, double score) {
		AnalysisType analysis = null;
		try {
			ObjectFactory objFactory = null;
			objFactory = new ObjectFactory();

			analysis = objFactory.createAnalysisType();

			analysis.setId(analysisId);
			BaseType base = null;
			base = objFactory.createBaseType();
			setBase(base, surface);
			ProperNameType properName = null;
			properName = objFactory.createProperNameType();
			properName.setDefiniteness("false");
			base.setProperName(properName);
			analysis.setBase(base);
			analysis.setScore(score);
			return analysis;
		} catch (JAXBException e) {
			e.printStackTrace();
		}
		return analysis;
	}

	// -----------------------------------------------------------------------------------------------------------------
	public AnalysisType handleProperNameDefAnalysis(String surface, String analysisId) {
		AnalysisType analysis = null;
		try {
			ObjectFactory objFactory = null;
			objFactory = new ObjectFactory();

			analysis = objFactory.createAnalysisType();

			analysis.setId(analysisId);
			BaseType base = null;
			base = objFactory.createBaseType();
			setBase(base, surface.substring(1));
			ProperNameType properName = null;
			properName = objFactory.createProperNameType();
			properName.setDefiniteness("true");
			base.setProperName(properName);
			analysis.setBase(base);
			analysis.setScore(1.0);
			return analysis;
		} catch (JAXBException e) {
			e.printStackTrace();
		}
		return analysis;
	}

	// -----------------------------------------------------------------------------------------------------------------
	public AnalysisType handleQuantifierAnalysis(String surface, String analysisId) {
		AnalysisType analysis = null;
		try {
			ObjectFactory objFactory = null;
			objFactory = new ObjectFactory();

			analysis = objFactory.createAnalysisType();

			analysis.setId(analysisId);
			BaseType base = null;
			base = objFactory.createBaseType();
			setBase(base, surface);
			QuantifierType quantifier = null;

			quantifier = objFactory.createQuantifierType();

			base.setQuantifier(quantifier);

			analysis.setBase(base);
			analysis.setScore(1.0);
		} catch (JAXBException e) {
			e.printStackTrace();
		}
		return analysis;
	}

	// -----------------------------------------------------------------------------------------------------------------
	public AnalysisType handleTitleAnalysis(String surface, String analysisId) {
		AnalysisType analysis = null;
		try {
			ObjectFactory objFactory = null;
			objFactory = new ObjectFactory();

			analysis = objFactory.createAnalysisType();

			analysis.setId(analysisId);
			BaseType base = null;
			base = objFactory.createBaseType();
			setBase(base, surface);
			TitleType title = null;
			title = objFactory.createTitleType();
			base.setTitle(title);

			analysis.setBase(base);
			analysis.setScore(1.0);
		} catch (JAXBException e) {
			e.printStackTrace();
		}
		return analysis;
	}

	// -----------------------------------------------------------------------------------------------------------------
	public AnalysisType handleVerbAnalysis(String surface, String analysisId) {
		AnalysisType analysis = null;
		try {
			ObjectFactory objFactory = null;
			objFactory = new ObjectFactory();

			analysis = objFactory.createAnalysisType();

			analysis.setId(analysisId);
			BaseType base = null;
			base = objFactory.createBaseType();
			VerbType verb = null;
			verb = objFactory.createVerbType();
			base.setVerb(verb);
			analysis.setBase(base);
			analysis.setScore(1.0);
			return analysis;
		} catch (JAXBException e) {
			e.printStackTrace();
		}
		return analysis;
	}

	// -----------------------------------------------------------------------------------------------------------------
	public AnalysisType handleVerbInfAnalysis(String surface, String analysisId) {
		AnalysisType analysis = null;
		try {
			ObjectFactory objFactory = null;
			objFactory = new ObjectFactory();

			analysis = objFactory.createAnalysisType();

			analysis.setId(analysisId);
			BaseType base = null;
			base = objFactory.createBaseType();
			VerbType verb = null;
			verb = objFactory.createVerbType();
			verb.setTense("infinitive");
			base.setVerb(verb);
			analysis.setBase(base);
			analysis.setScore(1.0);
			return analysis;
		} catch (JAXBException e) {
			e.printStackTrace();
		}
		return analysis;
	}

	// -----------------------------------------------------------------------------------------------------------------
	public AnalysisType handleWprefixAnalysis(String surface, String analysisId) {
		AnalysisType analysis = null;
		try {
			ObjectFactory objFactory = null;
			objFactory = new ObjectFactory();

			analysis = objFactory.createAnalysisType();

			analysis.setId(analysisId);
			BaseType base = null;
			base = objFactory.createBaseType();
			setBase(base, surface);
			WprefixType wprefix = null;
			wprefix = objFactory.createWprefixType();
			base.setWPrefix(wprefix);
			analysis.setBase(base);
			analysis.setScore(1.0);
		} catch (JAXBException e) {
			e.printStackTrace();
		}
		return analysis;
	}

	// -----------------------------------------------------------------------------------------------------------------
	public AnalysisType handleZevelAnalysis(String surface, String analysisId) {
		AnalysisType analysis = null;
		try {
			ObjectFactory objFactory = null;
			objFactory = new ObjectFactory();
			analysis = objFactory.createAnalysisType();
			analysis.setId(analysisId);
			BaseType base = null;
			base = objFactory.createBaseType();
			ZVLType zevel = null;
			zevel = objFactory.createZVLType();
			base.setZevel(zevel);
			analysis.setBase(base);
			analysis.setScore(1.0);
		} catch (JAXBException e) {
			e.printStackTrace();
		}
		return analysis;
	}

	// -----------------------------------------------------------------------------------------------------------------
	public void marshalAnalysis(JAXBContext jc, Corpus collection) {
		// System.out.println("(F) HMM2Morph:marshalAnalysis(JAXBContext jc,
		// Corpus collection)");
		FileOutputStream out = null;
		OutputStreamWriter pOut = null;
		try {
			out = new FileOutputStream(outputFile);
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		}
		try {
			pOut = new OutputStreamWriter(out, "UTF8");
		} catch (UnsupportedEncodingException e2) {
			// System.out
			// .println("CreateCorpusXML:printDoc UnsupportedEncodingException
			// while create OutputStreamWriter");
			e2.printStackTrace();
		}
		Marshaller m = null;

		try {
			m = jc.createMarshaller();
		} catch (JAXBException e1) {
			e1.printStackTrace();
		}
		try {
			m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.FALSE);
		} catch (PropertyException e2) {
			e2.printStackTrace();
		}
		try {
			m.marshal(collection, pOut);
		} catch (JAXBException e3) {
			e3.printStackTrace();
		}
	}

	// -----------------------------------------------------------------------------------------------------------------
	public void marshalAnalysis(JAXBContext jc, Corpus collection, PrintWriter pw) {
		// System.out.println("(F) HMM2Morph:marshalAnalysis(JAXBContext jc,
		// Corpus collection,PrintWriter pw)");
		Marshaller m = null;

		try {
			m = jc.createMarshaller();
		} catch (JAXBException e1) {
			e1.printStackTrace();
		}
		try {
			m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.FALSE);
		} catch (PropertyException e2) {
			e2.printStackTrace();
		}
		try {
			/* well lets see */
			/*
			 * this part is for printing the token in the collection - just for
			 * debuging
			 */

			List<ArticleType> articleTypeList = collection.getArticle();
			ArticleType article = (ArticleType) articleTypeList.get(0);
			List<ParagraphType> paragraphTypeList = article.getParagraph();
			int paragraphTypeListSize = paragraphTypeList.size();
			for (int paragraphIndex = 0; paragraphIndex < paragraphTypeListSize; paragraphIndex++) {
				ParagraphType paragraph = (ParagraphType) paragraphTypeList.get(paragraphIndex);
				List<SentenceType> sentenceTypeList = paragraph.getSentence();
				int sentenceTypeListSize = sentenceTypeList.size();
				for (int sentenceIndex = 0; sentenceIndex < sentenceTypeListSize; sentenceIndex++) {
					SentenceType sentence = (SentenceType) sentenceTypeList.get(sentenceIndex);
					List<TokenType> tokenTypeList = sentence.getToken();
					int tokenTypeListSize = tokenTypeList.size();
					for (int tokenIndex = 0; tokenIndex < tokenTypeListSize; tokenIndex++) {
						System.out.println("Token #################################");
						TokenType token = (TokenType) tokenTypeList.get(tokenIndex);
						List<AnalysisType> analysisTypeList = token.getAnalysis();
						int analysisTypeListSize = analysisTypeList.size();
						for (int analysisIndex = 0; analysisIndex < analysisTypeListSize; analysisIndex++) {
							AnalysisType analysis = (AnalysisType) analysisTypeList.get(analysisIndex);
							if (analysis != null) {

								if (analysis.getBase() != null) {
									System.out.println("LexiconItem = " + analysis.getBase().getLexiconItem());
									System.out.println("Dog Transliterated --> "
											+ analysis.getBase().getTransliteratedLexiconItem());
								} else {
									System.out.println("Fuck me analysis.getBase() is NULL dog !!");
								}
							} else {
								System.out.println("Fuck me analysis is NULL dog !!");
							}
						}
					}
				}
			}

			/*
			 * end of debuggin part seen enough ?
			 */

			m.marshal(collection, pw);
			System.out.println("==========================================================");
		} catch (JAXBException e3) {
			e3.printStackTrace();
		}
	}

	// public AnalysisType handlePassiveParticipleAnalysis(String surface,
	// String analysisId) {
	// AnalysisType analysis = null;
	// try {
	// ObjectFactory objFactory = null;
	// objFactory = new ObjectFactory();
	//
	// analysis = objFactory.createAnalysisType();
	//
	// analysis.setId(analysisId);
	// BaseType base = null;
	// base = objFactory.createBaseType();
	// setBase(base, surface);
	// ParticipleType participle = null;
	// participle = objFactory.createParticipleType();
	// setBase(base, surface);
	// participle.setRegister(UNSPECIFED);
	// participle.setBinyan(UNSPECIFED);
	// participle.setGender(UNSPECIFED);
	// participle.setNumber(UNSPECIFED);
	// participle.setPerson(UNSPECIFED);
	// participle.setStatus(UNSPECIFED);
	// participle.setRoot(UNSPECIFED);
	// participle.setMood("passive");
	// participle.setParticipleType(UNSPECIFED);
	// participle.setDefiniteness(UNSPECIFED);
	// base.setParticiple(participle);
	// analysis.setBase(base);
	// analysis.setScore(1.0);
	// } catch (JAXBException e) {
	// e.printStackTrace();
	// }
	// return analysis;
	// }

	// -----------------------------------------------------------------------------------------------------------------
	public Corpus parseXML(final JAXBContext jc, final InputStream in) throws Exception {
		List<ArticleType> articleTypeList;
		List<ParagraphType> paragraphTypeList;
		List<SentenceType> sentenceTypeList;
		List<TokenType> tokenTypeList;
		List<AnalysisType> analysisTypeList;
		Corpus collection = null;
		String hmmSentence = "";
		Unmarshaller unmarshaller = jc.createUnmarshaller();
		unmarshaller.setValidating(false);
		collection = (Corpus) unmarshaller.unmarshal(in);
		articleTypeList = collection.getArticle();
		ArticleType article = (ArticleType) articleTypeList.get(0);
		String morphSurface = "";
		String hmmPos = "";
		// handle prefix + propername
		// Data.init("C:\\Documents and Settings\\daliabo\\My
		// Documents\\lexicon\\diffTests\\dprefixes.data");
		// Data.webFlag = true;

		paragraphTypeList = (List<ParagraphType>) article.getParagraph();
		int paragraphTypeListSize = paragraphTypeList.size();
		for (int paragraphIndex = 0; paragraphIndex < paragraphTypeListSize; paragraphIndex++) {
			ParagraphType paragraph = (ParagraphType) paragraphTypeList.get(paragraphIndex);
			sentenceTypeList = (List<SentenceType>) paragraph.getSentence();
			int sentenceTypeListSize = sentenceTypeList.size();
			for (int sentenceIndex = 0; sentenceIndex < sentenceTypeListSize; sentenceIndex++) {
				// ///////////////////////////////////////////
				hmmSentence = bi.readLine();
				// ///////////////////////////////////////////
				SentenceType sentence = (SentenceType) sentenceTypeList.get(sentenceIndex);
				tokenTypeList = (List<TokenType>) sentence.getToken();
				int tokenTypeListSize = tokenTypeList.size();
				if (hmmSentence == null) {
					// System.out.println("1111111111 hmmSentence is null");
					continue;
				}
				StringTokenizer hmmstSentence = new StringTokenizer(hmmSentence, "][");
				for (int tokenIndex = 0; tokenIndex < tokenTypeListSize; tokenIndex++) {
					try {
						MWECounter = 0.0;
						TokenType token = (TokenType) tokenTypeList.get(tokenIndex);
						morphSurface = token.getSurface();
						// System.out.println("morphSurface=" + morphSurface);
						if (!hmmstSentence.hasMoreElements())
							continue;
						String hmmToken = hmmstSentence.nextToken();
						// System.out.println("hmmSentence ="+hmmSentence);
						// System.out.println("hmmToken ="+hmmToken);

						ArrayList<String> hmmPrefixList = new ArrayList<String>();
						hmmPos = analyzeHMMLine(hmmToken, hmmPrefixList);
						int hmmPrefixListSize = hmmPrefixList.size();
						// System.out.println("hmmPos = " + hmmPos);
						analysisTypeList = token.getAnalysis();
						int analysisTypeListSize = analysisTypeList.size();
						int scoreCounter = 0;
						boolean unknownFlag = false;
						for (int analysisIndex = 0; analysisIndex < analysisTypeListSize; analysisIndex++) {
							unknownFlag = false;
							AnalysisType analysis = (AnalysisType) analysisTypeList.get(analysisIndex);
							analysis.setScore(0.0);

							if (analysis.getBase() == null && analysis.getPrefix() != null) {
								if (prefixSpecialCases(tokenTypeList, tokenTypeListSize, tokenIndex, analysis,
										analysisIndex, hmmPrefixList, hmmPos))
									scoreCounter++;

							} else if (analysis.getBase().getForeign() != null) {
								analysis.setScore(1.0);
								scoreCounter = 1;
							} else if (analysis.getBase() != null && analysis.getBase().getMWE() != null) {
								scoreCounter = checkPos(analysis, hmmPos, scoreCounter);

							} else if (analysis.getBase().getUnknown() != null) {
								unknownFlag = true;
								token.getAnalysis().remove(analysisIndex);
								analysisTypeListSize--;

							} else if (analysis.getBase() != null && checkPrefix(analysis, hmmPrefixList)) {
								scoreCounter = checkPos(analysis, hmmPos, scoreCounter);
							}
						}
						double score = scoreCounter;
						if (score > 1 && MWECounter == 0.0) {
							score = 1.0 / scoreCounter;
							for (int analysisIndex = 0; analysisIndex < analysisTypeListSize; analysisIndex++) {
								AnalysisType analysis = (AnalysisType) analysisTypeList.get(analysisIndex);
								double scoreValue = analysis.getScore();
								if (scoreValue != 0.0)
									analysis.setScore(score);
							}
						} else if (score > 1 && MWECounter > 0.0) {
							score = 1.0 / MWECounter;
							for (int analysisIndex = 0; analysisIndex < analysisTypeListSize; analysisIndex++) {
								AnalysisType analysis = (AnalysisType) analysisTypeList.get(analysisIndex);
								if ((analysis.getBase() != null && analysis.getBase().getMWE() == null)
										|| analysis.getBase() == null) {
									analysis.setScore(0.0);
								} else
									analysis.setScore(score);
							}
							MWECounter = 0.0;

						} else if (scoreCounter == 0) {
							AnalysisType newAnalysis = null;
							ENUM_HMMPOS hmmPosi = Str2Num.str2numHMMPOS(hmmPos, hmmPos, hmmPos);
							String newAnalysisIndex = String.valueOf(analysisTypeListSize + 1);
							switch (hmmPosi) {
							case PRONOUN:
								newAnalysis = handlePronounAnalysis(morphSurface, newAnalysisIndex);
								break;
							case PROPERNAME:
								if (unknownFlag && morphSurface.charAt(0) == 'ה') {
									AnalysisType newAnalysis1 = handleProperNameDefAnalysis(morphSurface,
											newAnalysisIndex);
									token.getAnalysis().add(newAnalysis1);
								} else if (unknownFlag) {
									if (hmmPrefixListSize > 0)
										score = 1.0;
									else
										score = 0.0;
									handlePrefixProperNameAnalysis(token, morphSurface, score);
								}
								newAnalysis = handleProperNameAnalysis(morphSurface, newAnalysisIndex, 1.0);

								break;

							case PROPERNAMEDEF:
								if (unknownFlag) {
									AnalysisType newAnalysis1 = handleProperNameAnalysis(morphSurface, newAnalysisIndex,
											1.0);
									token.getAnalysis().add(newAnalysis1);
								}
								newAnalysis = handleProperNameDefAnalysis(morphSurface, newAnalysisIndex);
								break;
							case QUANTIFIER:
								newAnalysis = handleQuantifierAnalysis(morphSurface, newAnalysisIndex);
								break;
							case TITLE:
								newAnalysis = handleTitleAnalysis(morphSurface, newAnalysisIndex);
								break;
							case NUMERAL:
								newAnalysis = handleNumeralAnalysis(morphSurface, newAnalysisIndex);
								break;
							case EXISTENTIAL:
								newAnalysis = handleExistentialAnalysis(morphSurface, newAnalysisIndex);
								break;
							case MODAL:
								newAnalysis = handleModalAnalysis(morphSurface, newAnalysisIndex);
								break;
							case COPULA:
								newAnalysis = handleCopulaAnalysis(morphSurface, newAnalysisIndex);
								break;
							case CONJUNCTION:
								newAnalysis = handleConjunctionAnalysis(morphSurface, newAnalysisIndex);
								break;
							case INTERROGATIVE:
								newAnalysis = handleInterrogativeAnalysis(morphSurface, newAnalysisIndex);
								break;
							case INTERJECTION:
								newAnalysis = handleInterjectionAnalysis(morphSurface, newAnalysisIndex);
								break;
							case ADJECTIVE:
								newAnalysis = handleAdjectiveAnalysis(morphSurface, newAnalysisIndex);
								break;
							case ADJECTIVEDEF:
								newAnalysis = handleAdjectiveDefAnalysis(morphSurface, newAnalysisIndex);
								break;
							case ADJECTIVECONST:
								newAnalysis = handleAdjectiveConstAnalysis(morphSurface, newAnalysisIndex);
								break;
							case NOUN:
								newAnalysis = handleNounAnalysis(morphSurface, newAnalysisIndex);
								break;
							case NOUNDEF:
								newAnalysis = handleNounDefAnalysis(morphSurface, newAnalysisIndex);
								break;
							case NOUNCONST:
								newAnalysis = handleNounConstAnalysis(morphSurface, newAnalysisIndex);
								break;
							case NOUNPOSSESSIVE:
								newAnalysis = handleNounPossessiveAnalysis(morphSurface, newAnalysisIndex);
								break;
							case ADVERB:
								newAnalysis = handleAdverbAnalysis(morphSurface, newAnalysisIndex);
								break;
							case PREPOSITION:
								newAnalysis = handlePrepositionAnalysis(morphSurface, newAnalysisIndex);
								break;
							case NEGATION:
								newAnalysis = handleNegationAnalysis(morphSurface, newAnalysisIndex);
								break;
							case WPREFIX:
								newAnalysis = handleWprefixAnalysis(morphSurface, newAnalysisIndex);
								break;
							case PARTICIPLE:
								newAnalysis = handleParticipleAnalysis(morphSurface, newAnalysisIndex);
								break;
							case PARTICIPLEDEF:
								newAnalysis = handleParticipleDefAnalysis(morphSurface, newAnalysisIndex);
								break;
							case VERB:
								newAnalysis = handleVerbAnalysis(morphSurface, newAnalysisIndex);
								break;
							case VERBINF:
								newAnalysis = handleVerbInfAnalysis(morphSurface, newAnalysisIndex);
								break;
							case MULTIWORD:
								newAnalysis = handleMWEAnalysis(morphSurface, newAnalysisIndex);
								break;
							default:
								throw new RuntimeException("switch exc, hmmPosi is " + hmmPosi.toString());
							}

							token.getAnalysis().add(newAnalysis);
						}

					} catch (Exception e) {
						System.out.println("%%% GO GO GADGET BLANKET EXCPETION CATCH %%%");
						e.printStackTrace(System.out);
						System.out.println("%%% END IGNORED ANALYSIS EXCEPTION");
						continue;
					}
				} // end token
			}
			hmmSentence = bi.readLine();
		}
		return collection;
	}

	// -----------------------------------------------------------------------------------------------------------------
	/**
	 * i have not written this function - trying to document it (yossi)
	 * 
	 * @param jc
	 * @param in
	 * @param dprefixesFile
	 * @param taggedFilePath
	 *            - this paramter is not being used in the function excpect for
	 *            printing it
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public Corpus parseXML(final JAXBContext jc, final InputStream in, final String dprefixesFile,
			String taggedFilePath) {
		// System.out.println("(F) HMM2MORPH.parseXML Perl program output file
		// "+
		// taggedFilePath);
		String hmmSentence = "";
		Corpus collection = null;
		List<AnalysisType> articleTypeList;
		List<ParagraphType> paragraphTypeList;
		List<SentenceType> sentenceTypeList;
		List<TokenType> tokenTypeList;
		List<AnalysisType> analysisTypeList;

		Unmarshaller unmarshaller = null;
		try {
			unmarshaller = jc.createUnmarshaller();
		} catch (JAXBException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			unmarshaller.setValidating(false);
		} catch (JAXBException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			collection = (Corpus) unmarshaller.unmarshal(in);
		} catch (JAXBException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		articleTypeList = (List<AnalysisType>) collection.getArticle();
		ArticleType article = (ArticleType) articleTypeList.get(0);
		String morphSurface = "";
		String hmmPos = "";
		Data.init(dprefixesFile);

		paragraphTypeList = (List<ParagraphType>) article.getParagraph();
		int paragraphTypeListSize = paragraphTypeList.size();
		for (int paragraphIndex = 0; paragraphIndex < paragraphTypeListSize; paragraphIndex++) {
			ParagraphType paragraph = (ParagraphType) paragraphTypeList.get(paragraphIndex);
			sentenceTypeList = paragraph.getSentence();
			int sentenceTypeListSize = sentenceTypeList.size();
			for (int sentenceIndex = 0; sentenceIndex < sentenceTypeListSize; sentenceIndex++) {
				// ///////////////////////////////////////////
				try {
					hmmSentence = bi.readLine();
					// System.out.println("(F) HMM2Morph.parseXML() hmmSentence
					// = "+
					// hmmSentence);
				} catch (IOException e) {
					e.printStackTrace();
				}
				// ///////////////////////////////////////////
				SentenceType sentence = (SentenceType) sentenceTypeList.get(sentenceIndex);
				tokenTypeList = sentence.getToken();
				int tokenTypeListSize = tokenTypeList.size();
				if (hmmSentence == null)
					hmmSentence = "";// System.out.println("AURIKA DOG");
				StringTokenizer hmmstSentence = new StringTokenizer(hmmSentence, "][");
				for (int tokenIndex = 0; tokenIndex < tokenTypeListSize; tokenIndex++) {
					try {
						MWECounter = 0.0;
						TokenType token = (TokenType) tokenTypeList.get(tokenIndex);
						// System.out.println(token);
						morphSurface = token.getSurface();
						// System.out.println("morphSurface=" + morphSurface);
						/*
						 * if (morphSurface.equals("מקוצר"))
						 * System.out.println();
						 */
						if (!hmmstSentence.hasMoreElements())
							continue;
						String hmmToken = hmmstSentence.nextToken();
						// System.out.println("hmmSentence ="+hmmSentence);
						// System.out.println("hmmToken ="+hmmToken);

						ArrayList<String> hmmPrefixList = new ArrayList<String>();
						hmmPos = analyzeHMMLine(hmmToken, hmmPrefixList);
						// System.out.println("(F) HMM2Morph.parseXML() hmmPos =
						// "
						// + hmmPos);

						// System.out.println("(F) HMM2Morph.parseXML() hmmToken
						// = "
						// + hmmToken);
						/*
						 * if (hmmPos.equals("mw")) { System.out.println(
						 * "(F) HMM2Morph.parseXML() hmmPos = mw" ); //hmmPos =
						 * "noun"; // <---- TEMP EDIT TO as expected this solved
						 * the problem !!!!!! }
						 */

						int hmmPrefixListSize = hmmPrefixList.size();
						// System.out.println("hmmPos = " + hmmPos);
						analysisTypeList = token.getAnalysis();
						int analysisTypeListSize = analysisTypeList.size();
						int scoreCounter = 0;
						boolean unknownFlag = false;
						for (int analysisIndex = 0; analysisIndex < analysisTypeListSize; analysisIndex++) {
							// System.out.println("(F) HMM2Morph.parseXML()
							// analysisIndex = "
							// + analysisIndex);
							unknownFlag = false;
							AnalysisType analysis = (AnalysisType) analysisTypeList.get(analysisIndex);
							analysis.setScore(0.0);

							if (analysis.getBase() == null && analysis.getPrefix() != null) {
								if (prefixSpecialCases(tokenTypeList, tokenTypeListSize, tokenIndex, analysis,
										analysisIndex, hmmPrefixList, hmmPos))
									scoreCounter++;
							} else if (analysis.getBase().getForeign() != null) {
								analysis.setScore(1.0);
								scoreCounter = 1;
							} else if (analysis.getBase() != null && analysis.getBase().getMWE() != null) {
								try {
									scoreCounter = checkPos(analysis, hmmPos, scoreCounter);
								} catch (Exception e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}
							} else if (analysis.getBase().getUnknown() != null) {
								unknownFlag = true;
								token.getAnalysis().remove(analysisIndex);
								analysisTypeListSize--;
							} else if (analysis.getBase() != null && checkPrefix(analysis, hmmPrefixList)) {
								try {
									scoreCounter = checkPos(analysis, hmmPos, scoreCounter);
								} catch (Exception e) {
									System.out.println("An error occured for hmmSentence =" + hmmSentence
											+ " just in case hmmSentence!=EOF");
									e.printStackTrace();
									// System.exit(1);
								}
							}
						}
						double score = scoreCounter;
						if (score > 1 && MWECounter == 0.0) {
							score = 1.0 / scoreCounter;
							for (int analysisIndex = 0; analysisIndex < analysisTypeListSize; analysisIndex++) {
								AnalysisType analysis = (AnalysisType) analysisTypeList.get(analysisIndex);
								double scoreValue = analysis.getScore();
								if (scoreValue != 0.0)
									analysis.setScore(score);
							}
						} else if (score > 1 && MWECounter > 0.0) {
							score = 1.0 / MWECounter;
							for (int analysisIndex = 0; analysisIndex < analysisTypeListSize; analysisIndex++) {
								AnalysisType analysis = (AnalysisType) analysisTypeList.get(analysisIndex);
								if ((analysis.getBase() != null && analysis.getBase().getMWE() == null)
										|| analysis.getBase() == null) {
									analysis.setScore(0.0);
								} else
									analysis.setScore(score);
							}
							MWECounter = 0.0;

							// hmm suggests an anlysis which the morphological
							// analyzer didn't produce
						} else if (scoreCounter == 0) {
							AnalysisType newAnalysis = null;
							ENUM_HMMPOS hmmPosi = null;
							try {
								hmmPosi = Str2Num.str2numHMMPOS(hmmPos, hmmPos, hmmPos);
								// System.out.println("(F) HMM2Morph.parseXML()
								// hmmPosi = "
								// + hmmPosi);
							} catch (Exception e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
							String newAnalysisIndex = String.valueOf(analysisTypeListSize + 1);
							// System.out.println("(F) HMM2Morph.parseXML()
							// hmmPosi = "+
							// hmmPosi);
							switch (hmmPosi) {
							case MULTIWORD:
								newAnalysis = handleMWEAnalysis(morphSurface, newAnalysisIndex);
								break;
							case PRONOUN:
								newAnalysis = handlePronounAnalysis(morphSurface, newAnalysisIndex);
								break;
							case PROPERNAME:
								if (unknownFlag && morphSurface.charAt(0) == 'ה') {
									AnalysisType newAnalysis1 = handleProperNameDefAnalysis(morphSurface,
											newAnalysisIndex);
									token.getAnalysis().add(newAnalysis1);
								} else if (unknownFlag) {
									if (hmmPrefixListSize > 0)
										score = 1.0;
									else
										score = 0.0;
									handlePrefixProperNameAnalysis(token, morphSurface, score);
								}
								newAnalysis = handleProperNameAnalysis(morphSurface, newAnalysisIndex, 1.0);
								break;

							case PROPERNAMEDEF:
								if (unknownFlag) {
									AnalysisType newAnalysis1 = handleProperNameAnalysis(morphSurface, newAnalysisIndex,
											1.0);
									token.getAnalysis().add(newAnalysis1);
								}
								newAnalysis = handleProperNameDefAnalysis(morphSurface, newAnalysisIndex);
								break;
							case QUANTIFIER:
								newAnalysis = handleQuantifierAnalysis(morphSurface, newAnalysisIndex);
								break;
							case TITLE:
								newAnalysis = handleTitleAnalysis(morphSurface, newAnalysisIndex);
								break;
							case NUMERAL:
								newAnalysis = handleNumeralAnalysis(morphSurface, newAnalysisIndex);
								break;
							case EXISTENTIAL:
								newAnalysis = handleExistentialAnalysis(morphSurface, newAnalysisIndex);
								break;
							case MODAL:
								newAnalysis = handleModalAnalysis(morphSurface, newAnalysisIndex);
								break;
							case COPULA:
								newAnalysis = handleCopulaAnalysis(morphSurface, newAnalysisIndex);
								break;
							case CONJUNCTION:
								newAnalysis = handleConjunctionAnalysis(morphSurface, newAnalysisIndex);
								break;
							case INTERROGATIVE:
								newAnalysis = handleInterrogativeAnalysis(morphSurface, newAnalysisIndex);
								break;
							case INTERJECTION:
								newAnalysis = handleInterjectionAnalysis(morphSurface, newAnalysisIndex);
								break;
							case ADJECTIVE:
								newAnalysis = handleAdjectiveAnalysis(morphSurface, newAnalysisIndex);
								break;
							case ADJECTIVEDEF:
								newAnalysis = handleAdjectiveDefAnalysis(morphSurface, newAnalysisIndex);
								break;
							case ADJECTIVECONST:
								newAnalysis = handleAdjectiveConstAnalysis(morphSurface, newAnalysisIndex);
								break;
							case NOUN:
								newAnalysis = handleNounAnalysis(morphSurface, newAnalysisIndex);
								break;
							case NOUNDEF:
								newAnalysis = handleNounDefAnalysis(morphSurface, newAnalysisIndex);
								break;
							case NOUNCONST:
								newAnalysis = handleNounConstAnalysis(morphSurface, newAnalysisIndex);
								break;
							case NOUNPOSSESSIVE:
								newAnalysis = handleNounPossessiveAnalysis(morphSurface, newAnalysisIndex);
								break;
							case ADVERB:
								newAnalysis = handleAdverbAnalysis(morphSurface, newAnalysisIndex);
								break;
							case PREPOSITION:
								newAnalysis = handlePrepositionAnalysis(morphSurface, newAnalysisIndex);
								break;
							case NEGATION:
								newAnalysis = handleNegationAnalysis(morphSurface, newAnalysisIndex);
								break;
							case WPREFIX:
								newAnalysis = handleWprefixAnalysis(morphSurface, newAnalysisIndex);
								break;
							case PARTICIPLE:
								newAnalysis = handleParticipleAnalysis(morphSurface, newAnalysisIndex);
								break;
							case PARTICIPLEDEF:
								newAnalysis = handleParticipleDefAnalysis(morphSurface, newAnalysisIndex);
								break;
							case VERB:
								newAnalysis = handleVerbAnalysis(morphSurface, newAnalysisIndex);
								break;
							case VERBINF:
								newAnalysis = handleVerbInfAnalysis(morphSurface, newAnalysisIndex);
								break;
							}
							if (newAnalysis != null)
								token.getAnalysis().add(newAnalysis);
						}
					} catch (Exception e) {
						System.err.println("Error for file= " + taggedFilePath + " hmmSentence =" + hmmSentence);
						e.printStackTrace();
						continue;
					}
				}
			}
			try {
				hmmSentence = bi.readLine();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				continue;
			}
		}
		return collection;
	}

	// -----------------------------------------------------------------------------------------------------------------
	private boolean prefixSpecialCases(final List<TokenType> tokenList, final int tokenTypeListSize,
			final int tokenIndex, AnalysisType analysis, final int analysisIndex, final ArrayList<String> hmmPrefixList,
			final String hmmPos) {
		boolean rt = false;
		TokenType nextToken = null;
		int nextTokenIndex = tokenIndex + 1;
		if (nextTokenIndex < tokenTypeListSize) {
			nextToken = (TokenType) tokenList.get(nextTokenIndex);
			@SuppressWarnings("unchecked")
			List<AnalysisType> nextTokenAnalysisTypeList = (List<AnalysisType>) nextToken.getAnalysis();
			int nextTokenAnalysisTypeListSize = nextTokenAnalysisTypeList.size();
			for (int nextTokenAnalysisIndex = 0; nextTokenAnalysisIndex < nextTokenAnalysisTypeListSize; nextTokenAnalysisIndex++) {
				AnalysisType nextTokenAnalysis = (AnalysisType) nextTokenAnalysisTypeList.get(nextTokenAnalysisIndex);
				BaseType base = nextTokenAnalysis.getBase();
				// literal number
				if (base != null)
					if ((base.getNumeral() != null && base.getNumeral().getType().charAt(0) == 'l')
							|| (base.getPunctuation() != null && (nextToken.getSurface().charAt(0) == '-'
									|| nextToken.getSurface().charAt(0) == '"'
									|| nextToken.getSurface().charAt(0) == '\''))) {
						analysis.setScore(1.0);
						rt = true;
					}
			}
		}
		if (!rt)
			if (hmmPrefixList.size() >= 0 && hmmPos.equals("prefix")) {
				analysis.setScore(1.0);
				rt = true;
			}
		return rt;
	}

	// -----------------------------------------------------------------------------------------------------------------
	// used for the web
	public void process(final String XMLMorphStr, final String taggedFilePath, PrintWriter pw) throws Exception {
		InputStream in = null;
		// System.out.println("XMLMorphStr="+XMLMorphStr);
		try {
			JAXBContext jc = JAXBContext.newInstance(JAXB_PACKAGE);
			in = new ByteArrayInputStream(XMLMorphStr.getBytes("UTF-8"));
			readFile(taggedFilePath);
			Corpus collection = parseXML(jc, in);
			marshalAnalysis(jc, collection, pw);
			// parseXML(taggedFilePath);
			// parse(in);
			// setScore(XMLMorphStr, pw);
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	// -----------------------------------------------------------------------------------------------------------------
	// used for stand alone
	public void process(final String XMLMorphStr, final String taggedFilePath, PrintWriter pw,
			final String dprefixesFile) throws Exception {
		InputStream in = null;
		// System.out.println("XMLMorphStr="+XMLMorphStr);
		try {
			JAXBContext jc = JAXBContext.newInstance(JAXB_PACKAGE);
			in = new ByteArrayInputStream(XMLMorphStr.getBytes("UTF-8"));
			readFile(taggedFilePath); // read file into class variable bi
			// System.out.println("(F) HMM2Morph:process reading file -
			// "+taggedFilePath);
			// System.out.println("(F) HMM2Morph:process XMLMorphStr -
			// "+XMLMorphStr);
			Corpus collection = parseXML(jc, in, dprefixesFile, taggedFilePath);
			marshalAnalysis(jc, collection, pw); // put the output XML into pw
			// parseXML(taggedFilePath);
			// parse(in);
			// setScore(XMLMorphStr, pw);
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	// -----------------------------------------------------------------------------------------------------------------
	// used to test the web when developing
	public void process(String inputXMLDir, String inputTaggedFileDir, String outputFileDir, String xmlFile,
			String taggedFile, String outputFile) throws Exception {
		try {
			JAXBContext jc = JAXBContext.newInstance(JAXB_PACKAGE);
			this.outputFile = outputFileDir + outputFile;
			InputStream in = new FileInputStream(new File(inputXMLDir + xmlFile));

			readFile(inputTaggedFileDir + taggedFile);
			Corpus collection = parseXML(jc, in,
					"C:\\Documents and Settings\\daliabo\\My Documents\\lexicon\\diffTests\\dprefixes.data",
					inputTaggedFileDir);
			marshalAnalysis(jc, collection);
			// parse(in);
			// setScore(inputXMLDir + xmlFile);
			// System.exit(0);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	// -----------------------------------------------------------------------------------------------------------------
	/**
	 * read file into class variable bi
	 * 
	 * @param inputFile
	 */
	public void readFile(String inputFile) {
		try {
			bi = new BufferedReader(new InputStreamReader(new FileInputStream(inputFile)));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// -----------------------------------------------------------------------------------------------------------------
	public String readFileToString(String inputFile) {
		String inputStr = "";
		StringBuffer morphAnalysis = new StringBuffer();
		BufferedReader bi = null;

		try {
			bi = new BufferedReader(new InputStreamReader(new FileInputStream(inputFile), "UTF8"));
			while ((inputStr = bi.readLine()) != null)
				morphAnalysis.append(inputStr);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				bi.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return morphAnalysis.toString();
	}

	// -----------------------------------------------------------------------------------------------------------------
	public void setBase(BaseType base, String surface) {
		base.setLexiconPointer("0");
		base.setLexiconItem(surface);
		// base.setDottedLexiconItem(surface);
		base.setTransliteratedLexiconItem(Translate.Heb2Eng(surface));
	}

	// -----------------------------------------------------------------------------------------------------------------
	private int setMWandZeroOtherAnalysis(List<AnalysisType> analysisTypeList, int analysisTypeListSize) {
		int scoreCounter = 0;
		for (int analysisIndex = 0; analysisIndex < analysisTypeListSize; analysisIndex++) {
			AnalysisType analysis = (AnalysisType) analysisTypeList.get(analysisIndex);
			if (analysis.getBase().getMWE() != null) {
				analysis.setScore(1.0);
				scoreCounter++;
			} else
				analysis.setScore(0.0);
		}
		return scoreCounter;
	}

	// -----------------------------------------------------------------------------------------------------------------
	private void setZeroToAllAnalysis(List<AnalysisType> analysisTypeList, int analysisTypeListSize) {
		for (int analysisIndex = 0; analysisIndex < analysisTypeListSize; analysisIndex++) {
			AnalysisType analysis = (AnalysisType) analysisTypeList.get(analysisIndex);
			analysis.setScore(0.0);
		}
	}
}
