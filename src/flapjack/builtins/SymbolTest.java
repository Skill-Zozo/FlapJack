package flapjack.builtins;

import flapjack.exceptions.EmptyStackException;
import flapjack.exceptions.FJException;
import flapjack.exceptions.FJTypeException;
import flapjack.machine.FJMachine;
import flapjack.types.FJBoolean;
import flapjack.types.FJSymbol;
import flapjack.types.FlapjackObject;

public class SymbolTest implements FlapjackObject {

	public void flapjackOperation(FJMachine machine) throws FJException {
		try {
			FJSymbol possiblySymbol = Get.getSymbol(machine);
			machine.pushOperandStack(FJBoolean.getTrue());
		} catch (FJTypeException e) {
			machine.pushOperandStack(FJBoolean.getFalse());
		} catch (EmptyStackException e) {
			throw e;
		}
	}

}
