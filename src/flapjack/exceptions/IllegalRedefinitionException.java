package flapjack.exceptions;

public class IllegalRedefinitionException extends FJException {
	public IllegalRedefinitionException() {
	}

	public IllegalRedefinitionException(String message) {
		super(message);
	}

	public IllegalRedefinitionException(Throwable cause) {
		super(cause);
	}

	public IllegalRedefinitionException(String message, Throwable cause) {
		super(message, cause);
	}
}
