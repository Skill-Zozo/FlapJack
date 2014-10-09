package flapjack.builtins;

import flapjack.exceptions.FJException;
import flapjack.machine.FJMachine;
import flapjack.types.FlapjackObject;

public class Pop implements FlapjackObject {
	public void flapjackOperation(FJMachine machine) throws FJException {
		machine.popOperandStack();
	}

}
