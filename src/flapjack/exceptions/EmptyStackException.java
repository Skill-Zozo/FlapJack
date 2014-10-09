package flapjack.exceptions;

public class EmptyStackException extends FJException {

	public EmptyStackException() {
	}

	public EmptyStackException(String message) {
		super(message);
	}

	public EmptyStackException(Throwable cause) {
		super(cause);
	}

	public EmptyStackException(String message, Throwable cause) {
		super(message, cause);
	}
}
