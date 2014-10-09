package flapjack.exceptions;

public class SymbolClashException extends FJException {

	public SymbolClashException() {
	}

	public SymbolClashException(String message) {
		super(message);
	}

	public SymbolClashException(Throwable cause) {
		super(cause);
	}

	public SymbolClashException(String message, Throwable cause) {
		super(message, cause);
	}
}
