package flapjack.builtins;

import flapjack.exceptions.FJException;
import flapjack.machine.FJMachine;
import flapjack.types.FlapjackObject;

public class PrintLine implements FlapjackObject {

	public void flapjackOperation(FJMachine machine) throws FJException {
		FlapjackObject toBePrinted = machine.getOperandStack().top();
		machine.popOperandStack();
		System.out.println(toBePrinted.toString());
	}

}
