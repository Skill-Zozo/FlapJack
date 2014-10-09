package flapjack.builtins;

import flapjack.types.*;
import flapjack.machine.*;
import flapjack.exceptions.*;

public class Equal extends FJSymbol implements FlapjackObject{

	public void flapjackOperation(FJMachine machine) throws FJException{
		//if(SetCheck.flapjackOperation(machine, machine.getOperandStack().top())) return; 
		FJInteger arg1 = new FJInteger(0);
		FJInteger arg2 = new FJInteger(0);
		arg1 = PlusFunction.getFJInt(machine); 
		arg2 = PlusFunction.getFJInt(machine);
		if (arg1.intValue() == arg2.intValue()) machine.pushOperandStack(FJBoolean.getTrue());
		else machine.pushOperandStack(FJBoolean.getFalse());
	}
}
