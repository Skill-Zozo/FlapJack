package flapjack.types;

import flapjack.exceptions.FJException;
import flapjack.machine.FJMachine;

public class FJLong extends FJInteger {
	
	private long longFJ;
	
	public FJLong(long f) {
		super(0);
		this.longFJ = f;
	}
	
	public long getIntValue() {
		return this.longFJ;
	}
	
	public String toString() {
		return longFJ + "";
	}
	
	@Override
	public void flapjackOperation(FJMachine machine) throws FJException {
		// TODO Auto-generated method stub
		machine.pushOperandStack(this);
	}
}
