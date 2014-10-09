package flapjack.builtins;

import flapjack.exceptions.FJException;
import flapjack.exceptions.FJTypeException;
import flapjack.machine.FJMachine;
import flapjack.types.FJDouble;
import flapjack.types.FJInteger;
import flapjack.types.FJLong;
import flapjack.types.FJSymbol;
import flapjack.types.FlapjackObject;

public class DivideFunction extends FJSymbol implements FlapjackObject{
	
	public void flapjackOperation(FJMachine machine) throws FJException {
		// TODO Auto-generated method stub
		//if(SetCheck.flapjackOperation(machine, machine.getOperandStack().top())) return; 
		FJInteger arg1 = PlusFunction.getFJInt(machine);
		FJInteger arg2 = PlusFunction.getFJInt(machine);
		if(!arg1.getClass().equals(arg2.getClass())) throw new FJTypeException();
		if(arg1 instanceof FJLong) {
			FJLong longArg1 = (FJLong)arg1;
			FJLong longArg2 = (FJLong)arg2;
			machine.pushOperandStack(new FJLong(longArg2.getIntValue()/longArg1.getIntValue()));
			return;
		}
		if(arg1 instanceof FJDouble) {
			FJDouble doubleArg1 = (FJDouble)arg1;
			FJDouble doubleArg2 = (FJDouble)arg2;
			machine.pushOperandStack(new FJDouble(doubleArg2.doubleValue()/doubleArg1.doubleValue()));
			return;
		}
		machine.pushOperandStack(new FJInteger(arg2.intValue()/arg1.intValue()));
	}
}
