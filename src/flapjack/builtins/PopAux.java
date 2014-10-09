package flapjack.builtins;

import flapjack.exceptions.FJException;
import flapjack.machine.FJMachine;
import flapjack.types.FlapjackObject;

public class PopAux implements FlapjackObject{

	public void flapjackOperation(FJMachine machine) throws FJException {
		machine.popAuxiliaryStack();
	}

}
