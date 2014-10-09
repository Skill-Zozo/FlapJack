package flapjack.exceptions;

public class MalformedSpecialOperation extends FJException {
	/**
	 * 
	 */
	private static final long serialVersionUID = -2866946044297948002L;

	public MalformedSpecialOperation() {
	}

	public MalformedSpecialOperation(String message) {
		super(message);
	}

	public MalformedSpecialOperation(Throwable cause) {
		super(cause);
	}

	public MalformedSpecialOperation(String message, Throwable cause) {
		super(message, cause);
	}
}
