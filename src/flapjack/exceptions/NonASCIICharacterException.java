package flapjack.exceptions;

public class NonASCIICharacterException extends ReadFailureException {
	public NonASCIICharacterException() {
	}

	public NonASCIICharacterException(String message) {
		super(message);
	}

	public NonASCIICharacterException(Throwable cause) {
		super(cause);
	}

	public NonASCIICharacterException(String message, Throwable cause) {
		super(message, cause);
	}
}
