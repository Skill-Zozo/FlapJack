package flapjack.builtins;

import flapjack.exceptions.EmptyStackException;
import flapjack.exceptions.FJException;
import flapjack.exceptions.FJTypeException;
import flapjack.machine.FJMachine;
import flapjack.types.FJDouble;
import flapjack.types.FJInteger;
import flapjack.types.FJSymbol;
import flapjack.types.FlapjackObject;

public class NegFunction extends FJSymbol implements FlapjackObject{

	public void flapjackOperation(FJMachine machine) throws FJException {
		// TODO Auto-generated method stub

		//if(SetCheck.flapjackOperation(machine, machine.getOperandStack().top())) return;
		try {
			FlapjackObject unknown = machine.getOperandStack().top();
			machine.popOperandStack();
			FJInteger arg1 = new FJInteger(0);
			FJInteger.checkType(unknown);
			FJInteger arg2 = (FJInteger)unknown;
			if(unknown instanceof FJDouble) {
				FJDouble x = (FJDouble)(arg2);
				machine.pushOperandStack(new FJDouble(x.doubleValue() * -1.0));
				return;
			}
			arg1 = new FJInteger(arg2.intValue() * -1);
			machine.pushOperandStack(arg1);
		} catch (EmptyStackException e) {
			throw e;
		} catch (FJTypeException e)  {
			throw e;
		} catch (FJException e) {
			throw e;
		}
	}
}
