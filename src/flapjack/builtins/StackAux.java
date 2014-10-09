package flapjack.builtins;

import flapjack.exceptions.FJException;
import flapjack.machine.FJMachine;
import flapjack.types.FlapjackObject;

public class StackAux implements FlapjackObject {
	public void flapjackOperation(FJMachine machine) throws FJException {
		machine.pushOperandStack(machine.getAuxiliaryStack());
	}

}
