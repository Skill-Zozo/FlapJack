package flapjack.builtins;

import flapjack.exceptions.FJException;
import flapjack.exceptions.FJTypeException;
import flapjack.machine.FJMachine;
import flapjack.types.FJDouble;
import flapjack.types.FJInteger;
import flapjack.types.FJLong;
import flapjack.types.FlapjackObject;

public final class PlusFunction implements FlapjackObject
{
   private FJInteger arg1 = new FJInteger(0);
   private FJInteger arg2 = new FJInteger(0);
	
   public void flapjackOperation(FJMachine machine) throws FJException {
		this.arg1 = getFJInt(machine);
		this.arg2 = getFJInt(machine);
		
		FJLong longTest = new FJLong(0);
		FJDouble doubleTest = new FJDouble(0);
		//if(!this.arg1.getClass().equals(this.arg2.getClass())) throw new FJTypeException();
		if(this.arg1.getClass().equals(longTest.getClass())) {
			longTest = (FJLong)(this.arg1);
			FJLong long2 = (FJLong)(this.arg2);
			machine.pushOperandStack(new FJLong(longTest.getIntValue() + long2.getIntValue()));
			return;
		}
		if(this.arg1.getClass().equals(doubleTest.getClass())) {
			FJDouble d = (FJDouble)(this.arg1);
			doubleTest = (FJDouble)(this.arg2);
			machine.pushOperandStack(new FJDouble (d.doubleValue() + doubleTest.doubleValue()));
			return;
		}
		pushSumToMachine(machine, arg1.intValue(), arg2.intValue());
    }
    
	public static FJInteger getFJInt(FJMachine machine) throws FJException {
		FlapjackObject pop1 = machine.getOperandStack().top();
		machine.popOperandStack();
		if(coherent(pop1)) {
			if(pop1 instanceof FJLong) return (FJLong)(pop1);
			if(pop1 instanceof FJDouble) return (FJDouble)(pop1);
			return (FJInteger)(pop1);
		} else throw new FJTypeException();
	}
	
	public static void pushSumToMachine (FJMachine machine, int x, int y){
		machine.pushOperandStack(new FJInteger(x + y));
	}
	
    private static boolean coherent(FlapjackObject x) {
    	try {
    		FJInteger.checkType(x);
    	} catch (FJTypeException e) {
    		return false;
    	}
    	return true;
    }
}


