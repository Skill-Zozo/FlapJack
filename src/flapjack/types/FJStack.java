package flapjack.types;

//import java.util.EmptyStackException;

import flapjack.exceptions.*;
//import flapjack.machine.FJMachine;
import flapjack.machine.FJMachine;
import flapjack.reader.FJLookaheadReader;
import flapjack.reader.FJReader;

/* NOTE: Project part 1 does not need most of this class,
 look at the very end for the bit that's needed */

public class FJStack implements FlapjackObject {
	/*
	 * TODO: not needed in project part 1 Instance / static variables
	 * representing 1) The stack's top 2) The stack's rest 3) The empty stack
	 * 
	 * 
	 * NBNBNB: FJStacks must be immutable!
	 */
	private static FJStack empty;
	private FlapjackObject topOfStack;
	private FJStack restOfStack;
	private int sizeOfStack = -1; 
	
	private FJStack() {
		// Other classes can't run this.
	}

	public static synchronized FJStack getEmptyStack() {
		// TODO: Not needed in project part 1
		// Either return the empty stack if it has already been created,
		// or create it for the first time and returning it (make sure
		// to store this new stack so that it can be returned in subsequent
		// calls.
		if (empty == null) {
			empty = new FJStack();
		} 
		return empty;
	}

	public FJStack(FlapjackObject top, FJStack rest) {
		// TODO: Not needed in project part 1
		// Make a stack with given rest, and set top.
		this.topOfStack = top;
		this.restOfStack = rest;
	}
	
	public FJStack pushed(FlapjackObject newTop) {
		// TODO: Not needed in project part 1
		// Create a new stack that has this stack as its rest, newTop as
		// its top and return this new stack.
		return new FJStack(newTop, this);
	}

	public boolean isEmpty() {
		// TODO: Not needed in project part 1
		// Returns true if this stack is the empty stack
		return this == getEmptyStack();
	}

	public int getSize() {
		// TODO: Not needed in project part 1
		// Returns the size of this stack (number of elements in it)
		if(this.sizeOfStack == -1) {
			this.sizeOfStack = 0;
			for (FJStack x = this; !x.isEmpty(); x = x.restOfStack) {
				sizeOfStack = sizeOfStack + 1;;
			}
			//if(this.sizeOfStack == -1) return 0;
		}
		return sizeOfStack;
	}

	public FlapjackObject top() throws flapjack.exceptions.EmptyStackException {
		// TODO: Not needed in project part 1
		// Returns the top of the stack
		
		if (this.topOfStack == null) throw new flapjack.exceptions.EmptyStackException();
		return this.topOfStack;
	}

	public FJStack rest() throws flapjack.exceptions.EmptyStackException {
		// TODO: Not needed in project part 1
		// Returns the rest of the stack
		if (this.restOfStack == null) throw new flapjack.exceptions.EmptyStackException();
		return this.restOfStack;
	}

	public String toString() {
		// TODO: Not needed in project part 1
		// Returns a string representation of this stack.
		String stack = "{";
		FlapjackObject x = this.topOfStack;
		for(FJStack printedStack = this; printedStack != null; printedStack = printedStack.restOfStack) {
			if(printedStack.restOfStack != null) {
				if(printedStack.restOfStack.topOfStack != null) {
					stack = stack + printedStack.topOfStack.toString() + " ";
				} else {
					x = printedStack.topOfStack;//stack = stack + printedStack.topOfStack.toString() + "";
					break;
				}
			} 
		}
		if(this.topOfStack != null) stack = stack + x;
		return stack + "}";
	}

	public static void checkType(FlapjackObject obj) throws FJTypeException {
		// TODO: Not needed in project part 1
		// Checks if obj is an FJStack, throws an exception if not.
		try {
			FJStack temp = (FJStack)(obj);
		} catch(Exception e) {
			throw new FJTypeException("FJStack.checktype found that the object with address: " + obj.toString() + " is not a FJStack. System will exit." );
		}
		
	}

	public FJStack reverse() {//throws EmptyStackException {
		// TODO: Not needed in Project Part 1
		// Returns a new stack that has all the elements of this stack
		// in reverse order.
		FJStack reversedStack = getEmptyStack();
		if(isEmpty()) return reversedStack;
		FJStack finalStack = new FJStack(this.topOfStack, getEmptyStack());
		for (reversedStack = this.restOfStack; !reversedStack.isEmpty(); reversedStack = reversedStack.restOfStack) {
			if(reversedStack.topOfStack != null) {
				finalStack = finalStack.pushed(reversedStack.topOfStack);
			}
		}
		return finalStack;
	}

	
	public void flapjackOperation(FJMachine machine) { // TODO: Not needed in
		machine.pushOperandStack(this);
	}

	public static FJStack readForm(FJPackage pkg, FJLookaheadReader reader)
			throws ReadFailureException, SymbolClashException {
		/* NEEDED IN PROJECT PART 1
		 * 1) Check if the current character of the reader is an opening brace {
		 * , if not throw an exception. 2) Consume the opening brace { 3) Read
		 * in forms using FJReader.readForm until a closing brace } is
		 * encounter, consume it and then return 3 b) In part 2 of the project,
		 * you must keep all the forms read in, and at the end return a stack
		 * containing them 4) If the end of the file is reached before a closing
		 * brace } is encountered, throw an exception.
		 */
		FJStack readAllForms = FJStack.getEmptyStack();
		if (reader.getCurrentChar() == '{') {
			FlapjackObject obj = null;
			if(!reader.consumeChar()) return getEmptyStack();
			FJReader.consumeWhitespace(reader);
			
			if(reader.getCurrentChar() == '}') 
				return getEmptyStack();
			if (reader.hasNextChar()) {
				obj = FJReader.readForm(pkg, reader);
				readAllForms  = new FJStack(obj, readAllForms);
			}
			FJReader.consumeWhitespace(reader);
			while (reader.hasNextChar() && reader.getCurrentChar() != '}') {
				
				obj = FJReader.readForm(pkg, reader);
					readAllForms = readAllForms.pushed(obj);
					
					FJReader.consumeWhitespace(reader);
					char c = reader.getCurrentChar();
					if (reader.getCurrentChar() == '}' ) {
					readAllForms = readAllForms.reverse();
					return readAllForms;
				}
				
				if (obj == null && reader.hasNextChar()) {
					break;
				}
			}
		} else {
			throw new ReadFailureException("Compound form did not start with opening curly brace. System will exit");
		}
		return readAllForms;
	}
}