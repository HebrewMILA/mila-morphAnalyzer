/*
 * Created on 10/10/2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package lexicon.dbUtils;

/**
 * @author daliabo
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class PrefixRecord {
	String prefix = "";
	String description ="";
	boolean definiteArticleTag;
	boolean defArtHE;
	boolean relHE;
	boolean adverbKAF;
	boolean subConOrRelSHIN;
	boolean tempSubConKAFSHIN;
	boolean tempSubConMEMSHIN;
	boolean tempSubConLAMEDKAFSHIN;
	boolean relativizerTag;
	boolean temporalSubConjTag;
	boolean subordinatingConjunctionTag;
	boolean prefPartUnit;
	boolean prepBET;
	boolean prepKAF;
	boolean prepLAMED;
	boolean prepMEM;
	boolean prepositionTag;
	boolean conjunctionTag;
	
	/**
	 * @return Returns the prepositionTag.
	 */
	public boolean isPrepositionTag() {
		return prepositionTag;
	}
	/**
	 * @param prepositionTag The prepositionTag to set.
	 */
	public void setPrepositionTag(boolean prepositionTag) {
		this.prepositionTag = prepositionTag;
	}
		/**
	 * @return Returns the adverbKAF.
	 */
	public boolean isAdverbKAF() {
		return adverbKAF;
	}
	/**
	 * @param adverbKAF The adverbKAF to set.
	 */
	public void setAdverbKAF(boolean adverbKAF) {
		this.adverbKAF = adverbKAF;
	}
	/**
	 * @return Returns the defArtHE.
	 */
	public boolean isDefArtHE() {
		return defArtHE;
	}
	/**
	 * @param defArtHE The defArtHE to set.
	 */
	public void setDefArtHE(boolean defArtHE) {
		this.defArtHE = defArtHE;
	}
	/**
	 * @return Returns the definiteArticleTag.
	 */
	public boolean isDefiniteArticleTag() {
		return definiteArticleTag;
	}
	/**
	 * @param definiteArticleTag The definiteArticleTag to set.
	 */
	public void setDefiniteArticleTag(boolean definiteArticleTag) {
		this.definiteArticleTag = definiteArticleTag;
	}
	
	/**
	 * @return Returns the prefPartUnit.
	 */
	public boolean isPrefPartUnit() {
		return prefPartUnit;
	}
	/**
	 * @param prefPartUnit The prefPartUnit to set.
	 */
	public void setPrefPartUnit(boolean prefPartUnit) {
		this.prefPartUnit = prefPartUnit;
	}
	/**
	 * @return Returns the prepBET.
	 */
	public boolean isPrepBET() {
		return prepBET;
	}
	/**
	 * @param prepBET The prepBET to set.
	 */
	public void setPrepBET(boolean prepBET) {
		this.prepBET = prepBET;
	}
	/**
	 * @return Returns the prepKAF.
	 */
	public boolean isPrepKAF() {
		return prepKAF;
	}
	/**
	 * @param prepKAF The prepKAF to set.
	 */
	public void setPrepKAF(boolean prepKAF) {
		this.prepKAF = prepKAF;
	}
	/**
	 * @return Returns the prepLAMED.
	 */
	public boolean isPrepLAMED() {
		return prepLAMED;
	}
	/**
	 * @param prepLAMED The prepLAMED to set.
	 */
	public void setPrepLAMED(boolean prepLAMED) {
		this.prepLAMED = prepLAMED;
	}
	/**
	 * @return Returns the prepMEM.
	 */
	public boolean isPrepMEM() {
		return prepMEM;
	}
	/**
	 * @param prepMEM The prepMEM to set.
	 */
	public void setPrepMEM(boolean prepMEM) {
		this.prepMEM = prepMEM;
	}
	/**
	 * @return Returns the relativizerTag.
	 */
	public boolean isRelativizerTag() {
		return relativizerTag;
	}
	/**
	 * @param relativizerTag The relativizerTag to set.
	 */
	public void setRelativizerTag(boolean relativizerTag) {
		this.relativizerTag = relativizerTag;
	}
	/**
	 * @return Returns the relHE.
	 */
	public boolean isRelHE() {
		return relHE;
	}
	/**
	 * @param relHE The relHE to set.
	 */
	public void setRelHE(boolean relHE) {
		this.relHE = relHE;
	}
	/**
	 * @return Returns the subConOrRelSHIN.
	 */
	public boolean isSubConOrRelSHIN() {
		return subConOrRelSHIN;
	}
	/**
	 * @param subConOrRelSHIN The subConOrRelSHIN to set.
	 */
	public void setSubConOrRelSHIN(boolean subConOrRelSHIN) {
		this.subConOrRelSHIN = subConOrRelSHIN;
	}
	/**
	 * @return Returns the subordinatingConjunctionTag.
	 */
	public boolean isSubordinatingConjunctionTag() {
		return subordinatingConjunctionTag;
	}
	/**
	 * @param subordinatingConjunctionTag The subordinatingConjunctionTag to set.
	 */
	public void setSubordinatingConjunctionTag(
			boolean subordinatingConjunctionTag) {
		this.subordinatingConjunctionTag = subordinatingConjunctionTag;
	}
	/**
	 * @return Returns the temporalSubConjTag.
	 */
	public boolean isTemporalSubConjTag() {
		return temporalSubConjTag;
	}
	/**
	 * @param temporalSubConjTag The temporalSubConjTag to set.
	 */
	public void setTemporalSubConjTag(boolean temporalSubConjTag) {
		this.temporalSubConjTag = temporalSubConjTag;
	}
	/**
	 * @return Returns the tempSubConKAFSHIN.
	 */
	public boolean isTempSubConKAFSHIN() {
		return tempSubConKAFSHIN;
	}
	/**
	 * @param tempSubConKAFSHIN The tempSubConKAFSHIN to set.
	 */
	public void setTempSubConKAFSHIN(boolean tempSubConKAFSHIN) {
		this.tempSubConKAFSHIN = tempSubConKAFSHIN;
	}
	/**
	 * @return Returns the tempSubConLAMEDKAFSHIN.
	 */
	public boolean isTempSubConLAMEDKAFSHIN() {
		return tempSubConLAMEDKAFSHIN;
	}
	/**
	 * @param tempSubConLAMEDKAFSHIN The tempSubConLAMEDKAFSHIN to set.
	 */
	public void setTempSubConLAMEDKAFSHIN(boolean tempSubConLAMEDKAFSHIN) {
		this.tempSubConLAMEDKAFSHIN = tempSubConLAMEDKAFSHIN;
	}
	/**
	 * @return Returns the tempSubConMEMSHIN.
	 */
	public boolean isTempSubConMEMSHIN() {
		return tempSubConMEMSHIN;
	}
	/**
	 * @param tempSubConMEMSHIN The tempSubConMEMSHIN to set.
	 */
	public void setTempSubConMEMSHIN(boolean tempSubConMEMSHIN) {
		this.tempSubConMEMSHIN = tempSubConMEMSHIN;
	}
	/**
	 * @return Returns the description.
	 */
	public String getDescription() {
		return description;
	}
	/**
	 * @param description The description to set.
	 */
	public void setDescription(String description) {
		this.description = description;
	}
	/**
	 * @return Returns the prefix.
	 */
	public String getPrefix() {
		return prefix;
	}
	/**
	 * @param prefix The prefix to set.
	 */
	public void setPrefix(String prefix) {
		this.prefix = prefix;
	}
	/**
	 * @return Returns the conjunctionTag.
	 */
	public boolean isConjunctionTag() {
		return conjunctionTag;
	}
	/**
	 * @param conjunctionTag The conjunctionTag to set.
	 */
	public void setConjunctionTag(boolean conjunctionTag) {
		this.conjunctionTag = conjunctionTag;
	}
}