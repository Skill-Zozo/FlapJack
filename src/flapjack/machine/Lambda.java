package flapjack.machine;

import flapjack.exceptions.FJException;
import flapjack.exceptions.MalformedSpecialOperation;
import flapjack.types.FJStack;

public class Lambda extends FJSpecialOperator {

	@Override
	public void flapjackOperation(FJMachine machine) throws FJException {
		// TODO Auto-generated method stu
		try {
			machine.popCurrentInstructionStack();
			FJStack.checkType(machine.getCurrentInstructionStack().top());
		} catch (FJException e) {
			throw new MalformedSpecialOperation();
		}
		FJStack stack = (FJStack)(machine.getCurrentInstructionStack().top());
		FJLambda lambda = new FJLambda(stack);
		machine.pushOperandStack(lambda);
	}

}
