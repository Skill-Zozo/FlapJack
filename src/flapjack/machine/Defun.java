package flapjack.machine;

import flapjack.exceptions.FJException;
import flapjack.exceptions.MalformedSpecialOperation;
import flapjack.types.FJStack;
import flapjack.types.FJSymbol;

public class Defun extends FJSpecialOperator {

	public void flapjackOperation(FJMachine machine) throws FJException {
		// TODO Auto-generated method stub
		try {
			machine.popCurrentInstructionStack();
			FJSymbol.checkType(machine.getCurrentInstructionStack().top());
			FJSymbol functionName = (FJSymbol)(machine.getCurrentInstructionStack().top());
			machine.popCurrentInstructionStack();
			FJStack.checkType(machine.getCurrentInstructionStack().top());
			FJStack stack = (FJStack)(machine.getCurrentInstructionStack().top());
			machine.getGlobalPackage().register(new FJLambda(stack), functionName.getSymbolName());
		} catch (FJException e) {
			throw new MalformedSpecialOperation();
		}
	}

}
