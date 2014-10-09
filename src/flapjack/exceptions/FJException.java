package flapjack.exceptions;

public class FJException extends Exception {
	public FJException() {		
	}
	
	public FJException(String message) {
		super(message);
	} 
	
	public FJException(Throwable cause) {
		super(cause);
	}
	
	public FJException(String message, Throwable cause) {
		super(message, cause);
	}
}
