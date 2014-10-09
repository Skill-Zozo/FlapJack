package flapjack.builtins;

import flapjack.exceptions.FJException;
import flapjack.machine.FJMachine;
import flapjack.types.FlapjackObject;

public class Call implements FlapjackObject {

	public void flapjackOperation(FJMachine machine) throws FJException {
		FlapjackObject top = machine.getOperandStack().top();
		machine.popOperandStack();
		top.flapjackOperation(machine);
	}

}
