package flapjack.exceptions;

public class IntegerLiteralOutOfBoundsException extends ReadFailureException {

	public IntegerLiteralOutOfBoundsException() {
	}

	public IntegerLiteralOutOfBoundsException(String message) {
		super(message);
	}

	public IntegerLiteralOutOfBoundsException(Throwable cause) {
		super(cause);
	}

	public IntegerLiteralOutOfBoundsException(String message, Throwable cause) {
		super(message, cause);
	}
}
