package flapjack.machine;

import flapjack.exceptions.FJException;
import flapjack.types.FJStack;
import flapjack.types.FlapjackObject;

public class ToLet implements FlapjackObject {

	@Override
	public void flapjackOperation(FJMachine machine) throws FJException {
		// TODO Auto-generated method stub
		FJStack x = machine.getCurrentInstructionStack();
		x = x.rest();
		x = x.rest();
		FJStack.checkType(x.top());
		Param parameters = new Param((FJStack)x.top());
		machine.signalRetainInstructionTop();
		machine.popCurrentInstructionStack();
		FJStack.checkType(machine.getCurrentInstructionStack().top());
		FJStack var = (FJStack)machine.getCurrentInstructionStack().top();
		machine.popCurrentInstructionStack();
		machine.popCurrentInstructionStack();
		machine.pushCurrentInstructionStack(var);
		machine.pushCurrentInstructionStack(parameters);
		
	}

}
