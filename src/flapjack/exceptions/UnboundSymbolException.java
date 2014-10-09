package flapjack.exceptions;

public class UnboundSymbolException extends FJException {

	public UnboundSymbolException() {
	}

	public UnboundSymbolException(String message) {
		super(message);
	}

	public UnboundSymbolException(Throwable cause) {
		super(cause);
	}

	public UnboundSymbolException(String message, Throwable cause) {
		super(message, cause);
	}
}
