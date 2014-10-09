package flapjack.machine;
import flapjack.builtins.Get;
import flapjack.builtins.Set;
import flapjack.exceptions.EmptyStackException;
import flapjack.exceptions.FJException;
import flapjack.exceptions.FJTypeException;
import flapjack.exceptions.IllegalRedefinitionException;
import flapjack.exceptions.UnboundSymbolException;
import flapjack.machine.FJMachine;
import flapjack.types.FJPackage;
import flapjack.types.FJStack;
import flapjack.types.FJSymbol;
import flapjack.types.FlapjackObject;


public class SetCheck {

	public static boolean flapjackOperation(FJMachine machine) throws FJException {
			FJSymbol maybeSet;
			FJSymbol builtinFunction;
			FlapjackObject function = machine.getCurrentInstructionStack().top();;
			try {
				if (!objectIsToBeRedefined(machine)) {
					throw new IllegalRedefinitionException();
				}
				machine.popCurrentInstructionStack();
				function = machine.getCurrentInstructionStack().top();
				maybeSet = (FJSymbol)(function);
				/*function = machine.getCurrentInstructionStack().top();
				machine.popCurrentInstructionStack();
				FlapjackObject possiblySet= machine.getOperandStack().top();
				builtinFunction = (FJSymbol)(function);
				maybeSet = (FJSymbol)(possiblySet);
				builtinFunction.setValue(obj);
				//y = (FJSymbol)(function);*/
			} catch(ClassCastException e) {
				if(function != null) {
					machine.pushCurrentInstructionStack(function);
				}
				return false;
			} catch(EmptyStackException e) {
				if(function != null) {
					machine.pushCurrentInstructionStack(function);
				}
				return false;
			}
			if(maybeSet.getSymbolName().equalsIgnoreCase("set")) {
				/*FJPackage pkg = machine.getGlobalPackage();
				builtinFunction = new FJSymbol(builtinFunction.getSymbolName());
				builtinFunction.setValue(obj);
				pkg.getList().changeVal(builtinFunction.getSymbolName(), builtinFunction);
				machine.popOperandStack();*/
				machine.pushCurrentInstructionStack(function);
				return true;
			}
			else return false;
			
	}
	
	private static boolean objectIsToBeRedefined(FJMachine machine) {
		FlapjackObject function;
		try {
			function = machine.getOperandStack().top();
			FJSymbol x = (FJSymbol)(function);
			String s = x.getSymbolName();
			if(s.equals("defun") || s.equals("while") || s.equals("eval") || s.equals("ifthen") || s.equals("qoute") || s.equals("lambda")) {
				return false;
			} else return true;
		} catch (EmptyStackException e) {
			return true;
		} catch (ClassCastException e) {
			return true;
		}
	}
	
	public static boolean isNotOkay(FJSymbol x) throws UnboundSymbolException {
		if(!x.isBound()) return false;
		return (x.value() instanceof FJSpecialOperator);
	}

}
