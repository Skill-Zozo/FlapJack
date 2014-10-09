package flapjack.types;

import flapjack.exceptions.FJException;
import flapjack.machine.FJMachine;
import flapjack.reader.FJLookaheadReader;

public class FJDouble extends FJInteger {

	private double FJdouble;
	
	public FJDouble(double d) {
		super(0);
		this.FJdouble = d;
	}
	
	public double doubleValue() {
		return this.FJdouble;
	}
	
	public void isNegative() {
		this.FJdouble = this.FJdouble*-1;
	}
	
	public String toString() {
		return this.FJdouble + "";
	}
	
	public static FJDouble convertToFJDouble(long result, FJLookaheadReader reader) {
		double current = (double) result;
		double afterDecimal = 0.0;
		double assist = 0.1;	//to help me calculate the wanted double 
		while(reader.consumeChar() && FJInteger.stillValidNumber(reader.getCurrentChar())) {
			afterDecimal += assist * ((int)reader.getCurrentChar() - (int)('0'));
			assist = assist /10;
		}
		return new FJDouble(current + afterDecimal);
	}
	
	@Override
	public void flapjackOperation(FJMachine machine) throws FJException {
		// TODO Auto-generated method stub
		machine.pushOperandStack(this);
	}

}
