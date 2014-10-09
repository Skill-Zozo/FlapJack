package flapjack.draw;

import flapjack.exceptions.FJException;
import flapjack.machine.FJMachine;
import flapjack.types.FJDouble;
import flapjack.types.FJInteger;
import flapjack.types.FlapjackObject;

public class SetCanvas implements FlapjackObject {

	@Override
	public void flapjackOperation(FJMachine machine) throws FJException {
		// TODO Auto-generated method stub
		FJInteger.checkType(machine.getOperandStack().top());
		FJDouble int1 = (FJDouble)machine.getOperandStack().top();
		machine.popOperandStack();
		FJInteger.checkType(machine.getOperandStack().top());
		FJDouble int2 = (FJDouble)machine.getOperandStack().top();
		machine.popOperandStack();
		setCanvas(int1.doubleValue(), int2.doubleValue());
	}
	
	public void setCanvas(double min, double max) {
			StdDraw.setXscale(min, max);
			StdDraw.setYscale(min,max);
	}
}
