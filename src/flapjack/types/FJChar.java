package flapjack.types;

import flapjack.exceptions.FJException;
import flapjack.machine.FJMachine;
import flapjack.reader.FJLookaheadReader;

public class FJChar implements FlapjackObject {

	private char FJcharacter; 
	
	public FJChar(char c) {
		FJcharacter = c;
	}
	
	public char getCharacter() {
		return FJcharacter;
	}
	
	public int getintValue() {
		return (int)(FJcharacter);
	}
	
	public static FJChar readForm(FJLookaheadReader reader) {
		return new FJChar(reader.getCurrentChar());
	}
	
	public String toString() {
		return "'" + FJcharacter + "'";
	}
	@Override
	public void flapjackOperation(FJMachine machine) throws FJException {
		// TODO Auto-generated method stub
		machine.pushOperandStack(this);
	}

}
