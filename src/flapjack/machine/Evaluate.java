package flapjack.machine;

import flapjack.builtins.StackTest;
import flapjack.exceptions.FJException;
import flapjack.types.FJStack;

public class Evaluate extends FJSpecialOperator {

	@Override
	public void flapjackOperation(FJMachine machine) throws FJException {
		// TODO Auto-generated method stub
		FJStack stackFromOperand = StackTest.getStack(machine);
		machine.secureLoadInstructions(stackFromOperand);
	}

}
