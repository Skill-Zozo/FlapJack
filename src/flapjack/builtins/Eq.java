package flapjack.builtins;

import flapjack.exceptions.FJException;
import flapjack.machine.FJMachine;
import flapjack.types.FJBoolean;
import flapjack.types.FlapjackObject;

public class Eq implements FlapjackObject{

	public void flapjackOperation(FJMachine machine) throws FJException {
		// TODO Auto-generated method stub
		FlapjackObject a  = machine.getOperandStack().top();
		machine.popOperandStack();
		FlapjackObject b = machine.getOperandStack().top();
		machine.popOperandStack();
		if(a.equals(b)) machine.pushOperandStack(FJBoolean.getTrue());
		else machine.pushOperandStack(FJBoolean.getFalse());
	}

}
