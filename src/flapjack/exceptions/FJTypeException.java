package flapjack.exceptions;

public class FJTypeException extends FJException {

	public FJTypeException() {
	}

	public FJTypeException(String message) {
		super(message);
	}

	public FJTypeException(Throwable cause) {
		super(cause);
	}

	public FJTypeException(String message, Throwable cause) {
		super(message, cause);
	}
}
