package flapjack.builtins;

import flapjack.exceptions.FJException;
import flapjack.machine.FJMachine;
import flapjack.types.FJStack;
import flapjack.types.FlapjackObject;

public class Rest implements FlapjackObject {

	public void flapjackOperation(FJMachine machine) throws FJException {
		FJStack rest = StackTest.getStack(machine);
		machine.pushOperandStack(rest.rest());
	}

}
