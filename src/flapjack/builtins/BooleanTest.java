package flapjack.builtins;

import flapjack.exceptions.FJException;
import flapjack.exceptions.FJTypeException;
import flapjack.machine.FJMachine;
import flapjack.machine.SetCheck;
import flapjack.types.FJBoolean;
import flapjack.types.FJSymbol;
import flapjack.types.FlapjackObject;

public class BooleanTest implements FlapjackObject {

	public void flapjackOperation(FJMachine machine) throws FJException {
		//if(SetCheck.flapjackOperation(machine, machine.getOperandStack().top())) return;
		try {
			FJBoolean arg1 = And.getBoolean(machine);
			machine.pushOperandStack(FJBoolean.getTrue());
		} catch (FJTypeException e) {
			machine.pushOperandStack(FJBoolean.getFalse());
		}
	}
}
