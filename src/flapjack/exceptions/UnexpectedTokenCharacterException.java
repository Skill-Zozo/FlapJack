package flapjack.exceptions;

public class UnexpectedTokenCharacterException extends ReadFailureException {

	public UnexpectedTokenCharacterException() {
	}

	public UnexpectedTokenCharacterException(String message) {
		super(message);
	}

	public UnexpectedTokenCharacterException(Throwable cause) {
		super(cause);
	}

	public UnexpectedTokenCharacterException(String message, Throwable cause) {
		super(message, cause);
	}
}
