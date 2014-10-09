package flapjack.builtins;

import flapjack.exceptions.FJException;
import flapjack.machine.FJMachine;
import flapjack.types.FJStack;
import flapjack.types.FlapjackObject;

public class PushUp implements FlapjackObject{

	public void flapjackOperation(FJMachine machine) throws FJException {
		FJStack stack = StackTest.getStack(machine);
		FlapjackObject top = machine.getOperandStack().top();
		machine.popOperandStack();
		stack = stack.pushed(top);
		machine.pushOperandStack(stack);
	}

}
