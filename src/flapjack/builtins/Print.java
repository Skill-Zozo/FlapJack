package flapjack.builtins;

import flapjack.exceptions.FJException;
import flapjack.machine.FJMachine;
import flapjack.types.FlapjackObject;

public class Print implements FlapjackObject {

	public void flapjackOperation(FJMachine machine) throws FJException {
		FlapjackObject obj = machine.getOperandStack().top();
		machine.popOperandStack();
		System.out.print(obj.toString());
	}

}
