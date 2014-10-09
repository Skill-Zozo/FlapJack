package flapjack.machine;

import flapjack.exceptions.FJException;
import flapjack.exceptions.FJTypeException;
import flapjack.exceptions.MalformedSpecialOperation;
import flapjack.types.FJStack;
import flapjack.types.FJSymbol;

public class ParamDefun extends FJSpecialOperator{

	@Override
	public void flapjackOperation(FJMachine machine) throws FJException {
		// TODO Auto-generated method stub
		try {
			machine.popCurrentInstructionStack();
			FJSymbol functionName = (FJSymbol)machine.getCurrentInstructionStack().top();
			machine.popCurrentInstructionStack();
			machine.popCurrentInstructionStack();
			FJStack.checkType(machine.getCurrentInstructionStack().top());
			machine.getGlobalPackage().register(new Param((FJStack)machine.getCurrentInstructionStack().top()), functionName.getSymbolName());
		} catch (FJTypeException E) {
			throw new MalformedSpecialOperation();
		}
	}

}
