package flapjack.exceptions;

public class InstructionAccessViolation extends FJException {
	/**
	 * 
	 */
	private static final long serialVersionUID = 2000646908812450195L;

	public InstructionAccessViolation() {
	}

	public InstructionAccessViolation(String message) {
		super(message);
	}

	public InstructionAccessViolation(Throwable cause) {
		super(cause);
	}

	public InstructionAccessViolation(String message, Throwable cause) {
		super(message, cause);
	}
}
