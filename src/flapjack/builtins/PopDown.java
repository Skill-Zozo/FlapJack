package flapjack.builtins;

import flapjack.exceptions.FJException;
import flapjack.machine.FJMachine;
import flapjack.types.FJStack;
import flapjack.types.FlapjackObject;

public class PopDown implements FlapjackObject {

	public void flapjackOperation(FJMachine machine) throws FJException {
		FJStack stack = StackTest.getStack(machine);
		FlapjackObject top = stack.top();
		stack = stack.rest();
		machine.pushOperandStack(top);
		machine.pushOperandStack(stack);
	}

}
