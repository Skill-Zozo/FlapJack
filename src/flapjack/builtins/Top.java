package flapjack.builtins;

import flapjack.exceptions.FJException;
import flapjack.machine.FJMachine;
import flapjack.types.FJStack;
import flapjack.types.FlapjackObject;

public class Top implements FlapjackObject {

	public void flapjackOperation(FJMachine machine) throws FJException {
		FJStack  stack = StackTest.getStack(machine);
		machine.pushOperandStack(stack.top());
	}

}
