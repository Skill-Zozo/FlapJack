package flapjack.machine;

import flapjack.builtins.And;
import flapjack.exceptions.EmptyStackException;
import flapjack.exceptions.FJException;
import flapjack.exceptions.MalformedSpecialOperation;
import flapjack.types.FJBoolean;
import flapjack.types.FJStack;
import flapjack.types.FJSymbol;
import flapjack.types.FlapjackObject;

public class Else extends FJSpecialOperator {

	public void flapjackOperation(FJMachine machine) throws FJException {
		if(isCorrect(machine)) {
			machine.popOperandStack();
			FJBoolean evaluate = And.getBoolean(machine);
			if(evaluate.isTrue()) {
				machine.popCurrentInstructionStack();
			} else if (evaluate.isFalse()) {
				FJStack hasToBeStack;
				try {
					machine.popCurrentInstructionStack();
					FlapjackObject x = machine.getCurrentInstructionStack().top();
					hasToBeStack = (FJStack)(x);
					machine.popCurrentInstructionStack();
					hasToBeStack = hasToBeStack.reverse();
					pushToOperandStack(machine, hasToBeStack);
				} catch (ClassCastException e) {
					throw new MalformedSpecialOperation();
				} catch (EmptyStackException e) {
					throw new MalformedSpecialOperation();
				}
				
			}
		} else {
			throw new MalformedSpecialOperation();
		}
	}
	
	private void pushToOperandStack(FJMachine machine, FJStack hasToBeStack) throws EmptyStackException {
		while(hasToBeStack != FJStack.getEmptyStack()) {
			machine.pushCurrentInstructionStack(hasToBeStack.top());
			hasToBeStack = hasToBeStack.rest();
		}
		machine.pushCurrentInstructionStack(FJStack.getEmptyStack());
	}

	private boolean isCorrect (FJMachine machine) throws FJException {
		FlapjackObject hasToBeIfThen;
		FJSymbol ifThen;
		try {
			hasToBeIfThen = machine.getOperandStack().top();
			ifThen = (FJSymbol)(hasToBeIfThen);
		} catch (ClassCastException e) {
			return false;
		} catch (EmptyStackException e) {
			return false;
		}
		return true;
	}
}
