package flapjack.builtins;

import flapjack.exceptions.FJException;
import flapjack.machine.FJMachine;
import flapjack.types.FJBoolean;
import flapjack.types.FJSymbol;
import flapjack.types.FlapjackObject;

public class Not extends FJSymbol implements FlapjackObject{

	public void flapjackOperation(FJMachine machine) throws FJException {
		// TODO Auto-generated method stub

		//if(SetCheck.flapjackOperation(machine, machine.getOperandStack().top())) return;
		FJBoolean arg1 = And.getBoolean(machine);
		if(arg1.isTrue()) machine.pushOperandStack(FJBoolean.getFalse());
		else machine.pushOperandStack(FJBoolean.getTrue());
	}
}
