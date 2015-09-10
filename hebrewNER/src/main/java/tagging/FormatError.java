package tagging;

public class FormatError extends Exception {
	private static final long serialVersionUID = -359749453974827682L;

	public FormatError(int i) {
		super("Input format error in line " + i);
	}

}
