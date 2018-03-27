package lexicon.exceptions;

/**
 * @author Danny Shacham
 */
public class NullArticleListException extends AException {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public NullArticleListException(String message) {
		super(message);
	}

	public NullArticleListException() {
		super();
	}

}
