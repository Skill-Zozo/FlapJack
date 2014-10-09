package flapjack.types;

import java.lang.reflect.Modifier;

import javax.swing.AbstractAction;

import flapjack.builtins.Set;
import flapjack.exceptions.FJException;
import flapjack.exceptions.FJTypeException;
import flapjack.exceptions.IllegalRedefinitionException;
import flapjack.exceptions.SymbolClashException;
import flapjack.exceptions.UnboundSymbolException;
//import flapjack.exceptions.IllegalRedefinitionException;
import flapjack.exceptions.ReadFailureException;
//import flapjack.exceptions.UnboundSymbolException;
import flapjack.machine.FJMachine;
import flapjack.machine.SetCheck;
import flapjack.reader.FJLookaheadReader;

public class FJSymbol implements FlapjackObject {
	// TODO: Static/instance variables as needed
	private String FJSym;
	private FlapjackObject symValue;

	protected FJSymbol() {
	}

	public FJSymbol(String newSymbolName) {
		// TODO: Create a fresh symbol with name newSymbolName. The name should
		// be immutable.
		this.FJSym = newSymbolName;
	}

	public static void checkType(FlapjackObject obj) throws FJTypeException {
		// TODO: Check that the flapjack object is of the correct type. Throw an
		// exception if not.
		try {
			@SuppressWarnings("unused")
			FJSymbol OBJ;
			OBJ = (FJSymbol) (obj);
		} catch (Exception ex) {
			FJTypeException exceptions = new FJTypeException(
					"FJSymbol.checkType checked if: "
							+ obj.toString()
							+ " is a symbol. It is not a symbol. Consequently program will exit.");
			throw exceptions;
		}
	}

	public String getSymbolName() {
		// TODO: Return the symbol name
		return this.FJSym;
	}

	public static boolean isSymbolChar(char c) {
		// TODO: Return true if the character is a valid character that my form
		// part of a symbol name
		boolean itIs = false;
		for (int i = 33; i < 127; i++) {
			if (i == 48) {
				i = 58; // Skip the number region
			}
			if ((int) (c) == i && i != 123 && i != 125) {
				itIs = true;
			}
		}
		return itIs;
	}

	public FlapjackObject value() throws UnboundSymbolException {
		if (this.symValue == null) throw new UnboundSymbolException();
		return this.symValue;
	}
	
	public boolean isBound() {
		return this.symValue != null;
	}
	
	public  void setValue (FlapjackObject obj) //throws IllegalRedefinitionException{
	{	//if(Modifier.toString(obj.getClass().getModifiers()).contains("FJSpecialOperator")) throw new IllegalRedefinitionException(); 
		this.symValue = obj;
	}
	
	public static FJSymbol plus(FJSymbol symbolOne, FJSymbol symbolTwo) {
		return new FJSymbol(symbolOne.getSymbolName()
				+ symbolTwo.getSymbolName());
	}

	public static FJSymbol readForm(FJPackage pkg, FJLookaheadReader reader)
			throws ReadFailureException, SymbolClashException {
		// TODO: Read characters from the stream and process it into a symbol.
		// Note, you may find it needs to be an FJBoolean
		String symbolName = "";
		char currentChar = reader.getCurrentChar(); 
		boolean isValidSymbol = isSymbolChar(currentChar);
		while (isValidSymbol) {
			symbolName += currentChar + "";
			if (reader.consumeChar()) {
				currentChar = reader.getCurrentChar();
			} else {
				break;
			}
			isValidSymbol = isSymbolChar(currentChar) || FJInteger.stillValidNumber(currentChar); 
			/* 
			 * We include numbers here simply because a symbol can contain numbers, only after we have an initial character though.
			 */
		} 
	//	if(symbolName.equalsIgnoreCase("+")) return new PlusFunction("+");
		if (!isValidSymbol
				&& !(currentChar == '\n' || currentChar == '\t'
						|| currentChar == ' ' || currentChar == '{' || currentChar == '}')) {
			throw new ReadFailureException(	"Error in line: " + reader.getNumberOfLines()	+ ".FJSymbol.readForm failed to read symbol. System will exit");
		}
		if (symbolName.equalsIgnoreCase("false")) {
			return FJBoolean.getFalse();
		}
		if (symbolName.equalsIgnoreCase("true")) {
			return FJBoolean.getTrue();
		}
		//FJSymbol newSymbol = new FJSymbol(symbolName);
		
		return pkg.intern(symbolName);
	}

	public String toString() {
		// TODO: Return a string representation of the symbol name
		if (FJBoolean.isNotFalse(this) && FJBoolean.isNotTrue(this)) { 
			return this.FJSym + "";
		}
		return this.FJSym + "";
	}

	public void flapjackOperation(FJMachine machine) throws FJException {
		// TODO Auto-generated method stub
		if(this.isBound())	
			this.symValue.flapjackOperation(machine);
		//else if(SetCheck.flapjackOperation(machine)) machine.pushOperandStack(this);
		else throw new UnboundSymbolException();
	}
}