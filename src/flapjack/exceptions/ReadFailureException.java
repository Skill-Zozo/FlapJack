package flapjack.exceptions;

public class ReadFailureException extends FJException {
	public ReadFailureException() {
	}

	public ReadFailureException(String message) {
		super(message);
	}

	public ReadFailureException(Throwable cause) {
		super(cause);
	}

	public ReadFailureException(String message, Throwable cause) {
		super(message, cause);
	}
}
