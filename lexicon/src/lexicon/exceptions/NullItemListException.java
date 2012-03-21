package lexicon.exceptions;

/**
 * @author Danny Shacham
 */
public class NullItemListException extends AException {
	/**
	 * 
	 */
	private static final long serialVersionUID = 4234482419048880353L;

	public NullItemListException() {
		super();
	}

	public NullItemListException(String msg) {
		super(msg);
	}

}
