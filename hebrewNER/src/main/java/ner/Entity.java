package ner;

/**
 * The set of possible entities.
 * <p>
 * An Entity (which is not an "O") starts with a @Config.START, unless it immediately
 * preceded different entity of the same type, in which case it starts with  @Config.MIDDLE.
 * </p>
 */
public enum Entity {
	PERS, LOC, ORG, MONEY, TIME, PERCENT, DATE, MISC_ENT, MISC_AFF, MISC_EVENT, NUM, QUARTER,
	O {
		@Override
		public String[] getPrefixes() {
			return empty;
		}
	};
	static final private String[] empty = new String[] { "" };

	@SuppressWarnings("static-method")
	public String[] getPrefixes() {
		return new String[] { Config.START, Config.MIDDLE };
	}
	
	public static Entity fromPrefixed(String str) {
		if ("O".equals(str))
			return O;
		return valueOf(str.substring(2));
	}
}
