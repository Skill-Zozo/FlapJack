package flapjack.builtins;

import flapjack.exceptions.EmptyStackException;
import flapjack.exceptions.FJException;
import flapjack.machine.FJMachine;
import flapjack.types.*;

public class IntegerTest implements FlapjackObject {

	public void flapjackOperation (FJMachine machine) throws FJException {
		//if(SetCheck.flapjackOperation(machine, machine.getOperandStack().top())) return;
		try {
			FlapjackObject unknown = machine.getOperandStack().top();
			machine.popOperandStack();
			FJInteger arg1 = (FJInteger)(unknown);
		} catch (EmptyStackException e) {
			throw e;
		}
		catch (ClassCastException e) {
			machine.pushOperandStack(FJBoolean.getFalse());
			return;
		}
		machine.pushOperandStack(FJBoolean.getTrue());
	}
}