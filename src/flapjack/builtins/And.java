package flapjack.builtins;

import flapjack.types.*;
import flapjack.exceptions.FJException;
import flapjack.exceptions.FJTypeException;
import flapjack.machine.*;

public class And implements FlapjackObject {

	public void flapjackOperation(FJMachine machine) throws FJException {
		//if(SetCheck.flapjackOperation(machine, machine.getOperandStack().top())) return;
		FJBoolean arg1 = getBoolean(machine);
		FJBoolean arg2 = getBoolean(machine);
		if(arg1.isFalse() || arg2.isFalse()) machine.pushOperandStack(FJBoolean.getFalse());
		else machine.pushOperandStack(FJBoolean.getTrue());
	}

	public static FJBoolean getBoolean(FJMachine machine) throws FJException {
		FlapjackObject x = machine.getOperandStack().top();
		machine.popOperandStack();
		FJBoolean arg1;
		try {
			arg1 = (FJBoolean)(x);
		} catch (Exception e) {
			throw new FJTypeException();
		}
		return arg1;
	}
}
