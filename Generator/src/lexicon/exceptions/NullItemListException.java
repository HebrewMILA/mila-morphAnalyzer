package lexicon.exceptions;

/**
 * @author Danny Shacham
 */
public class NullItemListException extends AException {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public NullItemListException() {
		super();
	}

	public NullItemListException(String msg) {
		super(msg);
	}

}
