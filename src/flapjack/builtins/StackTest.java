package flapjack.builtins;

import flapjack.exceptions.EmptyStackException;
import flapjack.exceptions.FJException;
import flapjack.exceptions.FJTypeException;
import flapjack.machine.FJMachine;
import flapjack.types.FJBoolean;
import flapjack.types.FJStack;
import flapjack.types.FlapjackObject;

public class StackTest implements FlapjackObject {
	private FJStack isStack;
	public void flapjackOperation(FJMachine machine) throws FJException {
		try {
			isStack = getStack(machine);
			machine.pushOperandStack(FJBoolean.getTrue());
		} catch (FJTypeException e) {
			machine.pushOperandStack(FJBoolean.getFalse());
		} catch (ClassCastException e) {
			machine.pushOperandStack(FJBoolean.getFalse());
		}
	}
	
	public static FJStack getStack(FJMachine machine) throws FJException {
		FlapjackObject obj = machine.getOperandStack().top();
		machine.popOperandStack();
		FJStack stack;
		try {
			stack =  (FJStack)(obj);
		} catch (ClassCastException e) {
			throw new FJTypeException();
		}
		return stack;
	}

}
