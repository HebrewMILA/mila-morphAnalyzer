package features;

import java.util.regex.Pattern;

/**
 * Holds patterns to give information about a specific surface.
 */
public enum WordFeature {
	NONE("$^"),
	ONE_DIGIT("^[0-9]$"),
	TWO_DIGITS("^[0-9][0-9]$"),
	FOUR_DIGITS("^[0-9][0-9][0-9][0-9]$"),
	HOUR("^[0-2][0-9]\\:[0-5][0-9]$"),
	NATURAL_NUM("^[0-9]+$"),
	FLOAT_NUM("^[0-9]+\\.[0-9]+$"),
	NUM_PERCENT("^[0-9]+\\.?[0-9]*\\%$"),
	PERCENT_NUM("^\\%[0-9]+\\.?[0-9]*$"),
	PERCENT("^\\%$"),
	NUMBER_DOT("^([0-9]+\\.[0-9]*)+$"),
	DIGIT_SLASH("^[0-9]+\\/[0-9]+\\/[0-9]+$"),
	DIGIT_DASH("^[0-9]+\\-[0-9]+\\-[0-9]+$"),
	HAS_HYPHEN("-"),
	HAS_BACKSLASH("/"),
	HAS_COMMA(","),
	HAS_PERIOD("\\."),
	HAS_NUMBER("[0-9]"),
	LOAZI("[a-zA-Z]"),
	;
	
	/**
	 * Identify token information, for example: oneDigits, floatingPointNumber,
	 * contains comma, contains number.
	 *
	 * @param surface
	 * @return a string representing the information found
	 */
	public static WordFeature get(String surface) {
		for (WordFeature e : WordFeature.values())
			if (e.match(surface))
				return e;
		return NONE;
	}

	private final Pattern pat;

	private WordFeature(String pat) {
		this.pat = Pattern.compile(pat);
	}

	public boolean match(String w) {
		return pat.matcher(w).find();
	}

}