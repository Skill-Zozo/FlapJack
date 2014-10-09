package flapjack.builtins;

import flapjack.exceptions.EmptyStackException;
import flapjack.exceptions.FJException;
import flapjack.exceptions.FJTypeException;
import flapjack.machine.FJMachine;
import flapjack.types.FJDouble;
import flapjack.types.FJInteger;
import flapjack.types.FJLong;
import flapjack.types.FJSymbol;
import flapjack.types.FlapjackObject;

public class MultiplyFunction extends FJSymbol implements FlapjackObject {

	public void flapjackOperation(FJMachine machine) throws FJException {
		// TODO Auto-generated method stub

		//if(SetCheck.flapjackOperation(machine, machine.getOperandStack().top())) return;
		try {
			FJInteger arg1 = new FJInteger(0);
			arg1 = 	arg1.isFJInteger(machine.getOperandStack().top());
			machine.popOperandStack();
			FJInteger arg2 = new FJInteger(0);
			arg2 = arg2.isFJInteger(machine.getOperandStack().top());
			machine.popOperandStack();
			if(!arg1.getClass().equals(arg2.getClass())) throw new FJTypeException("These numbers are not of the same data type. System will exit");
			if(arg1 instanceof FJLong) {
				FJLong longArg1 = (FJLong)arg1;
				FJLong longArg2 = (FJLong)arg2;
				machine.pushOperandStack(new FJLong(longArg1.getIntValue() * longArg2.getIntValue()));
				return;
			}
			if(arg1 instanceof FJDouble) {
				FJDouble doubleArg1 = (FJDouble)arg1;
				FJDouble doubleArg2 = (FJDouble)arg2;
				machine.pushOperandStack(new FJDouble (doubleArg1.doubleValue() * doubleArg2.doubleValue()));
				return;
			}
			FJInteger prod = new FJInteger(arg2.intValue() * arg1.intValue());
			machine.pushOperandStack(prod);
		} catch (EmptyStackException exception) {
			throw exception;
		} catch (FJTypeException exception) {
			throw exception;
		} catch (FJException e) {
			throw e;
		}
	}
}
