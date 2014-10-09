package flapjack.machine;


import flapjack.builtins.And;
import flapjack.exceptions.EmptyStackException;
import flapjack.exceptions.FJException;
import flapjack.exceptions.MalformedSpecialOperation;
import flapjack.types.FJBoolean;
import flapjack.types.FJStack;
import flapjack.types.FJSymbol;
import flapjack.types.FlapjackObject;

public class IfThen extends FJSpecialOperator{

	public void flapjackOperation(FJMachine machine) throws FJException {
			FJStack possiblyStack;
			try {
				machine.popCurrentInstructionStack();
				FlapjackObject unknown = machine.getCurrentInstructionStack().top();
				possiblyStack = (FJStack)(unknown);
			} catch (ClassCastException e) {
				throw new MalformedSpecialOperation("The next object in the instruction stack is not a stack. System will exit.");
			} catch (EmptyStackException e) {
				throw new MalformedSpecialOperation();
			}
			machine.popCurrentInstructionStack();
			possiblyStack = possiblyStack.reverse();
			
			pushIntoOperand(machine, possiblyStack);
	}

	private void pushIntoOperand(FJMachine machine, FJStack possiblyStack) throws FJException {
		FJBoolean topObject = And.getBoolean(machine);
		if(topObject.isTrue()) {
			try {
				FJSymbol isElse = (FJSymbol)machine.getCurrentInstructionStack().top();
				if(isElse.getSymbolName().equals("else")) {
					machine.popCurrentInstructionStack();
					FJStack hasToBeStack = FJStack.getEmptyStack();
					if(!machine.getCurrentInstructionStack().top().getClass().equals(hasToBeStack.getClass())) {
						throw new MalformedSpecialOperation();
					}
					machine.popCurrentInstructionStack();
				}
			} catch (ClassCastException e) {
			
			} catch (EmptyStackException e) {
				
			}
			while(possiblyStack != FJStack.getEmptyStack()) {
				machine.pushCurrentInstructionStack(possiblyStack.top());
				possiblyStack = possiblyStack.rest();
			}
			machine.pushCurrentInstructionStack(FJStack.getEmptyStack());
			return;
		}
		else if(topObject.isFalse()){
			try {
				FlapjackObject possiblyElse = machine.getCurrentInstructionStack().top();
				FJSymbol isElse = (FJSymbol)(possiblyElse);
				machine.pushCurrentInstructionStack(isElse);
				machine.pushOperandStack(FJBoolean.getFalse());
				machine.pushOperandStack(new FJSymbol("ifthen"));
				return;
			} catch (EmptyStackException e) {
				machine.pushCurrentInstructionStack(FJStack.getEmptyStack());
				return;
			} catch (ClassCastException r) {
				machine.pushCurrentInstructionStack(FJStack.getEmptyStack());
				return;
			}
		} 
		try {
			machine.popCurrentInstructionStack();
			FlapjackObject possiblyElse = machine.getCurrentInstructionStack().top();
			FJSymbol isElse = (FJSymbol)(possiblyElse);
			machine.pushCurrentInstructionStack(possiblyStack);
			machine.pushOperandStack(FJBoolean.getTrue());
		} catch (ClassCastException e) {
			machine.pushCurrentInstructionStack(possiblyStack);
			return;
		} catch (EmptyStackException e) {
			machine.pushCurrentInstructionStack(possiblyStack);
			return;
		}
		machine.pushOperandStack(new FJSymbol("ifthen"));
	}

}
