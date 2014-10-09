package flapjack.builtins;

import flapjack.exceptions.FJException;
import flapjack.machine.FJMachine;
import flapjack.types.FJBoolean;
import flapjack.types.FlapjackObject;

public class Or implements FlapjackObject{

	public void flapjackOperation(FJMachine machine) throws FJException {
		//if(SetCheck.flapjackOperation(machine, machine.getOperandStack().top())) return;
		FJBoolean arg1 = And.getBoolean(machine);
		FJBoolean arg2 = And.getBoolean(machine);
		if(arg1.isTrue() || arg2.isTrue()) machine.pushOperandStack(FJBoolean.getTrue());
		else machine.pushOperandStack(FJBoolean.getFalse());
	}

}
