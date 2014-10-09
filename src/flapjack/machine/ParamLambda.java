package flapjack.machine;

import flapjack.exceptions.EmptyStackException;
import flapjack.exceptions.FJException;
import flapjack.types.FJStack;
import flapjack.types.FJSymbol;
import flapjack.types.FlapjackObject;

public class ParamLambda extends FJSpecialOperator{

	private FJSymbol[] parameters;
	private FJStack body;
	private int location;
	
	public void fillUp(FJSymbol sym, FlapjackObject symValue) {
			parameters[location] = new FJSymbol(sym.getSymbolName());
			parameters[location++].setValue(symValue);
	}
	
	public ParamLambda(FJStack stack, int max) {
		this.body = stack;
		this.parameters = new FJSymbol[max];
		this.location = 0;
	}
	
	@Override
	public void flapjackOperation(FJMachine machine) throws FJException {
		// We take replace the symbols with same name as the parameters with the parameters themselves
		FJStack newBody = FJStack.getEmptyStack();
		for(int i = 0; i < parameters.length; i++) {
			newBody = replace(this.body,parameters[i]);
			this.body = newBody;
		}
		this.body = newBody;
		machine.secureLoadInstructions(this.body);
	}
	
	private FJStack replace(FJStack original, FJSymbol wanted) throws EmptyStackException {
		FJStack newBody = FJStack.getEmptyStack();
		for (FJStack x = original; !x.isEmpty(); x = x.rest()) {
			boolean newSymbolPushed = false;
			if(x.top() instanceof FJStack) {
				FJStack innerStack = replace((FJStack)x.top(),wanted);
				if(!innerStack.isEmpty()) {
					newSymbolPushed = true;
					newBody = newBody.pushed(innerStack);
				}	
			} else if(x.top() instanceof FJSymbol) {
				FJSymbol currentSymbol = (FJSymbol)x.top();
				if(wanted.getSymbolName().equals(currentSymbol.getSymbolName())) {
					newSymbolPushed = true;
					newBody = newBody.pushed(wanted);
				}
			} 
			if(!newSymbolPushed){
				newBody = newBody.pushed(x.top());
			}
		}
		newBody = newBody.reverse();
		return newBody;
	}

}
