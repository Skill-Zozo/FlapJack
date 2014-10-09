package flapjack.builtins;

import flapjack.exceptions.FJException;
import flapjack.exceptions.FJTypeException;
import flapjack.machine.FJMachine;
import flapjack.types.FJBoolean;
import flapjack.types.FJStack;
import flapjack.types.FlapjackObject;

public class StackIsEmpty implements FlapjackObject {
	public void flapjackOperation(FJMachine machine) throws FJException {
		FlapjackObject top = machine.getOperandStack().top();
		FJStack isStack;
		try {
			isStack = (FJStack)(top);
			machine.popOperandStack();
			if(isStack == FJStack.getEmptyStack()) {
				machine.pushOperandStack(FJBoolean.getTrue());
			} else {
				machine.pushOperandStack(FJBoolean.getFalse());
			}
		} catch (ClassCastException e) {
			throw new FJTypeException();
		}
	}

}
