package flapjack.builtins;

import flapjack.exceptions.EmptyStackException;
import flapjack.exceptions.FJException;
import flapjack.exceptions.FJTypeException;
import flapjack.machine.FJMachine;
import flapjack.types.FJBoolean;
import flapjack.types.FJDouble;
import flapjack.types.FJInteger;
import flapjack.types.FJLong;
import flapjack.types.FJSymbol;
import flapjack.types.FlapjackObject;

public class LessThanFunction extends FJSymbol implements FlapjackObject{

	public void flapjackOperation(FJMachine machine) throws FJException {

	//	if(SetCheck.flapjackOperation(machine, machine.getOperandStack().top())) return;
		try {
			FlapjackObject unknown = machine.getOperandStack().top();
			machine.popOperandStack();
			FJSymbol symCheck = new FJSymbol(null); 
			boolean wassup = unknown instanceof FJInteger ; 
			boolean as =  unknown instanceof FJSymbol && !(unknown instanceof FJBoolean);
			if(!(wassup  || as)) throw new FJTypeException(); 
			FlapjackObject unknown2 = machine.getOperandStack().top();
			machine.popOperandStack();
			if (!unknown.getClass().equals(unknown2.getClass())) throw new FJTypeException();
			if (unknown.getClass().equals(symCheck.getClass())) {
				FJSymbol x = (FJSymbol)(unknown);
				FJSymbol y = (FJSymbol)(unknown2);
				int compare = x.getSymbolName().compareTo(y.getSymbolName());
				if(compare < 0) {
					machine.pushOperandStack(FJBoolean.getTrue());
				} else machine.pushOperandStack(FJBoolean.getFalse());
			}
			if (unknown instanceof FJInteger) {
				FJInteger x = (FJInteger)(unknown);
				FJInteger y = (FJInteger)(unknown2);
				if(x instanceof FJDouble) {
					FJDouble doubleArg1 = (FJDouble)x;
					FJDouble doubleArg2 = (FJDouble)y;
					if(doubleArg1.doubleValue() > doubleArg2.doubleValue()) machine.pushOperandStack(FJBoolean.getTrue());
					else {
						machine.pushOperandStack(FJBoolean.getFalse());
					}
					return;
				}
				if(unknown instanceof FJLong) {
					FJLong longX = (FJLong)x;
					FJLong longY = (FJLong)y;
					if(longX.getIntValue() > longY.getIntValue()) machine.pushOperandStack(FJBoolean.getTrue());
					else machine.pushOperandStack(FJBoolean.getFalse());
					return;
				}
				if(x.intValue() > y.intValue())	machine.pushOperandStack(FJBoolean.getTrue());
				else machine.pushOperandStack(FJBoolean.getFalse());
			}
		} catch (EmptyStackException e) {
			throw e;
		} catch (FJTypeException e) {
			throw e;
		} catch (FJException e) {
			throw e;
		}
	}
}
