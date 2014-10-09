package flapjack.machine;

import flapjack.exceptions.EmptyStackException;
import flapjack.exceptions.FJException;
import flapjack.exceptions.MalformedSpecialOperation;
import flapjack.types.*;

public class Qoute extends FJSpecialOperator{

	public void flapjackOperation(FJMachine machine) throws FJException {
		try {
			machine.popCurrentInstructionStack();
			FlapjackObject current = machine.getCurrentInstructionStack().top();
			machine.pushOperandStack(current);
		} catch (EmptyStackException e) {
			throw new MalformedSpecialOperation();
		}
	}
}
