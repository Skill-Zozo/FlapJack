package flapjack.builtins;

import flapjack.exceptions.FJException;
import flapjack.machine.FJMachine;
import flapjack.types.FJStack;
import flapjack.types.FlapjackObject;

public class Reverse implements FlapjackObject {

	public void flapjackOperation(FJMachine machine) throws FJException {
		FJStack reverse = StackTest.getStack(machine);
		machine.pushOperandStack(reverse.reverse());
	}

}
