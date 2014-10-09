package flapjack.machine;

import flapjack.builtins.And;
import flapjack.exceptions.EmptyStackException;
import flapjack.exceptions.FJException;
import flapjack.exceptions.MalformedSpecialOperation;
import flapjack.types.FJBoolean;
import flapjack.types.FJStack;
import flapjack.types.FJSymbol;
import flapjack.types.FlapjackObject;

public class While extends FJSpecialOperator {

	@Override
	public void flapjackOperation(FJMachine machine) throws FJException {
		// TODO Auto-generated method stub
		FJBoolean topOfOperand = And.getBoolean(machine);
		if(topOfOperand.isFalse()) {
			stackTest(machine.getCurrentInstructionStack());
			machine.popCurrentInstructionStack();
		} else {
			FJStack instructionStack = machine.getCurrentInstructionStack();
			machine.pushCurrentInstructionStack(new FJSymbol("while"));
			FJStack newInstructionStack = stackTest(instructionStack);
			machine.secureLoadInstructions(instructionStack);
			machine.setCurrentInstructionStack(newInstructionStack);
		}
	}
	
	private FJStack stackTest (FJStack x) throws FJException {
		FlapjackObject top = x.top();
		FJStack ret;
		try {
			x = x.rest();
			FlapjackObject unknown = x.top();
			x = (FJStack)(unknown);
			ret = x;
		} catch (ClassCastException e) {
			throw new MalformedSpecialOperation();
		} catch (EmptyStackException e) {
			throw new MalformedSpecialOperation();
		}
		x = x.pushed(top);
		return ret;
	}

}
