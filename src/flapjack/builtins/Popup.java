package flapjack.builtins;

import flapjack.exceptions.FJException;
import flapjack.exceptions.FJTypeException;
import flapjack.machine.FJMachine;
import flapjack.types.FJStack;
import flapjack.types.FlapjackObject;

public class Popup implements FlapjackObject {

	public void flapjackOperation(FJMachine machine) throws FJException {
		FJStack poppedUp;
		try {
			poppedUp = StackTest.getStack(machine);
		} catch (ClassCastException e) {
			throw new FJTypeException();
		}
		FlapjackObject top = poppedUp.top();
		poppedUp = poppedUp.rest();
		machine.pushOperandStack(poppedUp);
		machine.pushOperandStack(top);
	}

}
