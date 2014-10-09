package flapjack.exceptions;

public class MissingStackEndTokenException extends ReadFailureException {

	public MissingStackEndTokenException() {
	}

	public MissingStackEndTokenException(String message) {
		super(message);
	}

	public MissingStackEndTokenException(Throwable cause) {
		super(cause);
	}

	public MissingStackEndTokenException(String message, Throwable cause) {
		super(message, cause);
	}
}
