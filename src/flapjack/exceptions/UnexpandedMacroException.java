package flapjack.exceptions;

public class UnexpandedMacroException extends FJException {

	public UnexpandedMacroException() {
	}

	public UnexpandedMacroException(String message) {
		super(message);
	}

	public UnexpandedMacroException(Throwable cause) {
		super(cause);
	}

	public UnexpandedMacroException(String message, Throwable cause) {
		super(message, cause);
	}
}
