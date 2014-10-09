package flapjack.builtins;

import flapjack.exceptions.FJException;
import flapjack.machine.FJMachine;
import flapjack.types.FlapjackObject;

public class Dup implements FlapjackObject{

	public void flapjackOperation(FJMachine machine) throws FJException {
		FlapjackObject obj = machine.getOperandStack().top();
		machine.pushOperandStack(obj);
	}

}
