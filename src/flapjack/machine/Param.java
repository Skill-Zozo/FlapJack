package flapjack.machine;

import flapjack.exceptions.FJException;
import flapjack.exceptions.MalformedSpecialOperation;
import flapjack.types.FJStack;
import flapjack.types.FJSymbol;

public class Param extends FJSpecialOperator{

	private FJStack body;
	public Param(FJStack body) {
		this.body = body;
	}
	
	@Override
	public void flapjackOperation(FJMachine machine) throws FJException {
		// TODO Auto-generated method stub
		try {
			machine.popCurrentInstructionStack();	//Move the function call
			//Assign the following symbols in the instruction stack parameters to whatever is in the operand 
			FJStack.checkType(machine.getCurrentInstructionStack().top());	
			FJStack variableStack = (FJStack)machine.getCurrentInstructionStack().top();
			
			// Since the top element in the operand stack is the last element in x ^^, we have to reverse x
			variableStack = variableStack.reverse();
			ParamLambda pl = new ParamLambda(body,variableStack.getSize());
			int size = variableStack.getSize();
			for(int i = 0; i < size; i++) {
				FJSymbol.checkType(variableStack.top());
				//Assign local variables
				pl.fillUp((FJSymbol)variableStack.top(), machine.getOperandStack().top());
				machine.popOperandStack();
				variableStack = variableStack.rest();
			}
			machine.popCurrentInstructionStack();
			machine.signalRetainInstructionTop();
			machine.pushCurrentInstructionStack(pl);
		} catch (Exception e) {
			throw new MalformedSpecialOperation();
		}
	}
}
