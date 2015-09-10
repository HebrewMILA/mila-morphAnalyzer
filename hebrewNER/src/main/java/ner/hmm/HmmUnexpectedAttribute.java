package ner.hmm;

/**
 * This exception is raised when <tt>Config.POS_LIST</tt> file does not contain
 * all the possible HmmPos.
 *
 * <p>
 * The quick fix is to run <tt>filetype_transformer.make_poslist()</tt>
 * </p>
 */
final class HmmUnexpectedAttribute extends RuntimeException {
	public HmmUnexpectedAttribute(String pos) {
		super(pos + "\nPlease run filetype_transformer.make_poslist()");
	}

	private static final long serialVersionUID = 3184728049057165106L;
}
