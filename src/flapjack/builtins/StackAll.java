package flapjack.builtins;

import flapjack.exceptions.FJException;
import flapjack.machine.FJMachine;
import flapjack.types.FJStack;
import flapjack.types.FlapjackObject;

public class StackAll implements FlapjackObject {
	public void flapjackOperation(FJMachine machine) throws FJException {
		FJStack stack = machine.getOperandStack();
		machine.setOperandStack(FJStack.getEmptyStack());
		machine.pushOperandStack(stack);
	}

}
