package flapjack.types;

import flapjack.exceptions.FJException;
import flapjack.exceptions.FJTypeException;
import flapjack.exceptions.IntegerLiteralOutOfBoundsException;
import flapjack.exceptions.ReadFailureException;
import flapjack.machine.FJMachine;
import flapjack.reader.FJLookaheadReader;
import flapjack.reader.FJReader;

public class FJInteger implements FlapjackObject {
	// TODO: Add instance/static variables as necessary
	private int FJInt = 0;

	public FJInteger(int val) {
		// TODO: Constructor for a new integer with value val
		this.FJInt = val;
	}

	public int intValue() {
		// TODO: Return the value of the integer.
		return this.FJInt;
	}
	
	public static void checkType(FlapjackObject obj) throws FJTypeException {
		if(!(obj instanceof FJInteger)) throw new FJTypeException();
	}

	public static FJInteger readForm(FJLookaheadReader reader)
			throws ReadFailureException {
		long result = 0;
		// TODO: Process an integer from the reader (read the character stream
		// and turn it into an integer).
		if (reader.hasCurrentChar()) {
			FJReader.consumeWhitespace(reader);
			char currentChar = reader.getCurrentChar();
			boolean isNumber = stillValidNumber(currentChar);
			while (isNumber) {
				int temp = (int) (currentChar) - (int) ('0');
				result = 10 * result + temp;
				if (reader.consumeChar()) {
					currentChar = reader.getCurrentChar();
				} else {
					break;
				}
				isNumber = stillValidNumber(currentChar);
				if(!isNumber && (int)reader.getCurrentChar() == 46) {
					return FJDouble.convertToFJDouble(result, reader);
				}
			}
			if (result - 1 > Integer.MAX_VALUE) {
				return new FJLong(result);
			}
			if (!isNumber
					&& !(currentChar == '\n' || currentChar == '\t'
							|| currentChar == ' ' || currentChar == '}' || currentChar == '{')) {
				ReadFailureException crash = new ReadFailureException(	"Error in line: "
								+ reader.getNumberOfLines()
								+ " FJInteger.readForm failed to read in the integer. System will exit");
				throw crash;
			}
			FJInteger FJInt = new FJInteger((int) (result));
			return FJInt;
		} else {
			return null;
		}
	}

	public static boolean stillValidNumber(char c) {
		boolean isNumber = false;
		for (int i = 48; i < 58; i++) {
			if ((int) (c) == i || (int) (c) == 45) {
				isNumber = true;
				break;
			}
		}
		return isNumber;
	}

	static int ihash(int value) {
		return ((value * 11) ^ 123456);
	}

	//Checks whether the FlapjackObject is an integer if so it assigns it to this, else it returns false
	public FJInteger isFJInteger(FlapjackObject x) throws FJTypeException {
		FJInteger temp = null;
		try {
			temp = (FJInteger)(x);
		} catch (Exception e) {
			throw new FJTypeException();
		}
		return temp;
		
	}
	
	public void isNegative() {
		this.FJInt = this.FJInt * -1;
	}

	public String toString() {
		return this.FJInt + "";
	}

	public void flapjackOperation(FJMachine machine) throws FJException {
		  machine.pushOperandStack(this);
	}
}