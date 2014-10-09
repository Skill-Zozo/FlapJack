package flapjack.builtins;

import flapjack.exceptions.FJException;
import flapjack.machine.FJMachine;
import flapjack.types.FJStack;
import flapjack.types.FlapjackObject;

public class ReplaceAux implements FlapjackObject{
	public void flapjackOperation(FJMachine machine) throws FJException {
		FJStack stack = StackTest.getStack(machine);
		machine.setAuxiliaryStack(stack);
		
	}

}
