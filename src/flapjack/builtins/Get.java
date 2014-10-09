package flapjack.builtins;

import flapjack.exceptions.EmptyStackException;
import flapjack.exceptions.FJException;
import flapjack.exceptions.FJTypeException;
import flapjack.machine.FJMachine;
import flapjack.types.FJSymbol;
import flapjack.types.FlapjackObject;

public class Get implements FlapjackObject{

	public void flapjackOperation(FJMachine machine) throws FJException {
		FJSymbol arg1 = getSymbol(machine);
		machine.pushOperandStack(arg1.value());
	}

	public static FJSymbol getSymbol (FJMachine machine) throws FJException{
		FlapjackObject x = machine.getOperandStack().top();
		FJSymbol arg ;
		try {
			machine.popOperandStack();
			arg = (FJSymbol)(x);
		} catch (ClassCastException e){
			throw new FJTypeException();
		} catch (EmptyStackException e) {
			throw e;	
		}
		return arg;
	}
}
