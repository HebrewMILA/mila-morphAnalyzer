package lexicon;

import sql.Inflection;
import sql.InflectionTable;
import sql.Inflection.Where;
import static sql.InflectionFilters.*;
import static sql.InflectionFilters.Gender.*;
import static sql.InflectionFilters.Number.*;

public class ShowNounInflections {

	private final String dualStatus;
	private final String masculineSingularConstruct;
	private final String masculinePluralConstruct;
	private final String feminineSingularConstruct;
	private final String femininePluralConstruct;
	private final String PGNMasculineSingular;
	private final String PGNFeminineSingular;
	private final String PGNMasculinePlural;
	private final String PGNFemininePlural;

	public ShowNounInflections(String lexiconId) {
		Inflection.Where isNotSingularOrDual = Where.not(Where.or(number(singular), number(dual)));
		Inflection.Where isNonPGN = Where.and(PGN(false),Where.not(definit(true, true)));
		InflectionTable inflections = InflectionTable.fetch(lexiconId);
		
		dualStatus 					= inflections.where(isNonPGN, definit(true, false), x-> x.baseNumber.contains("dual")).joinSurface();
		masculineSingularConstruct	= inflections.where(isNonPGN, gender(masculine), number(singular)).joinSurface();
		masculinePluralConstruct	= inflections.where(isNonPGN, gender(masculine), isNotSingularOrDual).joinSurface();
		feminineSingularConstruct	= inflections.where(isNonPGN, gender(feminine),  number(singular)).joinSurface();
		femininePluralConstruct 	= inflections.where(isNonPGN, gender(feminine),  isNotSingularOrDual).joinSurface();
		PGNMasculineSingular		= inflections.where(PGN(true), gender(masculine), number(singular)).joinSurface();
		PGNMasculinePlural			= inflections.where(PGN(true), gender(masculine), x-> x.baseNumber.contains("plural")).joinSurface();
		PGNFeminineSingular			= inflections.where(PGN(true), gender(feminine),  number(singular)).joinSurface();
		PGNFemininePlural			= inflections.where(PGN(true), gender(feminine), number(plural)).joinSurface();
	}
	
	public String getDual() {
		return dualStatus;
	}
	
	public String getMasculineSingularConstruct() {
		return masculineSingularConstruct;
	}
	
	public String getMasculinePluralConstruct() {
		return masculinePluralConstruct;
	}
	
	public String getFeminineSingularConstruct() {
		return feminineSingularConstruct;
	}
	
	public String getFemininePluralConstruct() {
		return femininePluralConstruct;
	}
	
	public String getPGNMasculineSingular() {
		return PGNMasculineSingular;
	}
	
	public String getPGNFeminineSingular() {
		return PGNFeminineSingular;
	}
	
	public String getPGNMasculinePlural() {
		return PGNMasculinePlural;
	}
	
	public String getPGNFemininePlural() {
		return PGNFemininePlural;
	}
	
	public static void releaseConnection() {
	}
}
