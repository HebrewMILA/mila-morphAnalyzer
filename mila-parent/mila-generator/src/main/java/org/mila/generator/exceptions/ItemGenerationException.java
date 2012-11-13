package org.mila.generator.exceptions;

public class ItemGenerationException extends Exception {
	private static final long serialVersionUID = 1L;

	public ItemGenerationException() {
		super();
	}

	public ItemGenerationException(String message, Throwable cause) {
		super(message, cause);
	}

	public ItemGenerationException(String message) {
		super(message);
	}

	public ItemGenerationException(Throwable cause) {
		super(cause);
	}

}
