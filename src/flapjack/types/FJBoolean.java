package flapjack.types;

import flapjack.exceptions.FJException;
import flapjack.exceptions.FJTypeException;
import flapjack.machine.FJMachine;

public class FJBoolean extends FJSymbol {
	private static FJBoolean trueVal = null;
	private static FJBoolean falseVal = null;

	private FJBoolean(String name) {
		super(name);
	}

	public static void checkType(FlapjackObject obj) throws FJTypeException {
		// TODO: Check that the flapjack object is of the correct type. Throw an
		// exception if not.
		try {
			FJBoolean check = (FJBoolean) (obj);
		} catch (Exception e) {
			FJTypeException exceptions = new FJTypeException(
					"Not a boolean. Program will exit.");
			throw exceptions; // FJReader(lets say it called it) will now exit
								// purely because there was a throws up there
								// and there's a down here
		}
	}

	private synchronized static void possiblyAllocateBooleans() {
		// TODO: Instantiate the boolean variables
		if (trueVal == null && falseVal == null) {
			trueVal = new FJBoolean("true");
			falseVal = new FJBoolean("false");
		}
	}

	public static FJBoolean getTrue() {
		// TODO: Return the true variable
		possiblyAllocateBooleans();
		return trueVal;
	}

	public static FJBoolean getFalse() {

		// TODO: Return the false variable
		possiblyAllocateBooleans();
		return falseVal;
	}

	public boolean isTrue() {
		// TODO: Evaluate if this boolean is true
		possiblyAllocateBooleans();
		return this == trueVal;
	}

	public static boolean isTrue(FJBoolean val) {
		// TODO: Evaluate if the true object is the same as the parameter
		if (val == trueVal) {
			return true;
		} else {
			return false;
		}
	}

	public static boolean isNotTrue(FlapjackObject val) {
		// TODO: Evaluate if the true object is not the same as the parameter.
		// The distinction is that the parameter can be ANY
		// flapjack.types.FlapjackObject
		possiblyAllocateBooleans();
		return val != trueVal;
	}

	public boolean isFalse() {
		// TODO: Evaluate if this boolean is false
		return this == falseVal;
	}

	public static boolean isFalse(FJBoolean val) {
		// TODO: Evaluate if the false object is the same as the parameter
		if (val == falseVal) {
			return true;
		} else {
			return false;
		}
	}

	public static boolean isNotFalse(FlapjackObject val) {
		// TODO: Evaluate if the false object is not the same as the parameter.
		// The distinction is that the parameter can be ANY
		// flapjack.types.FlapjackObject
		possiblyAllocateBooleans();
		return val != falseVal;
	}
	
	public void flapjackOperation(FJMachine machine) throws FJException { //
		machine.pushOperandStack(this);
	}
	 
}
