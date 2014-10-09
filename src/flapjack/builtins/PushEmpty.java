package flapjack.builtins;

import flapjack.exceptions.FJException;
import flapjack.machine.FJMachine;
import flapjack.types.FJStack;
import flapjack.types.FlapjackObject;

public class PushEmpty implements FlapjackObject{

	public void flapjackOperation(FJMachine machine) throws FJException {
		machine.pushOperandStack(FJStack.getEmptyStack());
	}

}
