/**
 * A specialized Security Exception class for the Lexicon system. The class holds special 
 * refernces for the ServletRequest and the page name that were involved in the Exception.
 * @author Danny Shacham
 */
package lexicon.exceptions;

/**
 * A specialized <b>security</b> Exception class for the Lexicon system. The
 * class holds special about the page that was involved in the Exception.
 * 
 * @author Danny Shacham
 */
public class LPasswordException extends AException {
	/**
	 * 
	 */
	private static final long serialVersionUID = -3951794021516671101L;

	/**
	 * Empty constructor
	 */
	public LPasswordException() {
		super("");
	}

	/**
	 * A constructor that saves the reason and the page name
	 * 
	 * @param reason
	 *            What had happened.
	 * @param thePage
	 *            The page where the security problem occured
	 */
	public LPasswordException(String reason) {
		super(reason);
	}

}
