package flapjack.builtins;

import flapjack.exceptions.FJException;
import flapjack.exceptions.FJTypeException;
import flapjack.machine.FJMachine;
import flapjack.types.FJStack;
import flapjack.types.FlapjackObject;

public class PushDown implements FlapjackObject{

	public void flapjackOperation(FJMachine machine) throws FJException {
		FlapjackObject top = machine.getOperandStack().top();
		machine.popOperandStack();
		FJStack stack;
		try {
			stack = StackTest.getStack(machine);
		} catch (ClassCastException e) {
			throw new FJTypeException();
		}
		stack = stack.pushed(top);
		machine.pushOperandStack(stack);
	}

}
