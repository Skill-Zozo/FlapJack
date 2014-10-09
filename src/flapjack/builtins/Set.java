package flapjack.builtins;

import flapjack.exceptions.FJException;
import flapjack.exceptions.IllegalRedefinitionException;
import flapjack.machine.FJMachine;
import flapjack.machine.SetCheck;
import flapjack.types.FJSymbol;
import flapjack.types.FlapjackObject;

public class Set implements FlapjackObject{
	public void flapjackOperation(FJMachine machine) throws FJException {
		
		//the symbol toBeAssigned a value is the FJSymbol to be assigned a value
		FJSymbol toBeAssigned = Get.getSymbol(machine);
		if(SetCheck.isNotOkay(toBeAssigned)) {
    		throw new IllegalRedefinitionException();
    	}
		toBeAssigned.setValue(machine.getOperandStack().top());
		toBeAssigned = machine.getGlobalPackage().register(machine.getOperandStack().top(), toBeAssigned.getSymbolName());
		machine.popOperandStack();
		
		/*FJPackage pkg =  machine.getGlobalPackage();
		if (legalSymbolRedef(x)) {
			if(pkg.getList().contains(x.getSymbolName())) {
				FJSymbol change = new FJSymbol(x.getSymbolName());
				change.setValue(machine.getOperandStack().top());
				machine.getGlobalPackage().getList().changeVal(change.getSymbolName(),change);
				machine.popOperandStack();
			} else {
					x = pkg.intern(x.getSymbolName());
					x.setValue(machine.getOperandStack().top());
					machine.popOperandStack();
					pkg.getList().changeVal(x.getSymbolName(), x);
			}
				
		} else throw new IllegalRedefinitionException();*/
		
		
	}
}
