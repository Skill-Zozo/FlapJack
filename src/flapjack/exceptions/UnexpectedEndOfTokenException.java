package flapjack.exceptions;

public class UnexpectedEndOfTokenException extends ReadFailureException {

	public UnexpectedEndOfTokenException() {
	}

	public UnexpectedEndOfTokenException(String message) {
		super(message);
	}

	public UnexpectedEndOfTokenException(Throwable cause) {
		super(cause);
	}

	public UnexpectedEndOfTokenException(String message, Throwable cause) {
		super(message, cause);
	}
}
