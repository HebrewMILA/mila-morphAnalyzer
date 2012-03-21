package lexicon.exceptions;

/**
 * @author Danny Shacham
 */
public class NullArticleListException extends AException {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1577347511677988653L;

	public NullArticleListException() {
		super();
	}

	public NullArticleListException(String message) {
		super(message);
	}

}
