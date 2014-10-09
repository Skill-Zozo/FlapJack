package flapjack.machine;

import flapjack.builtins.Builtins;
import flapjack.exceptions.*;
import flapjack.types.*;

/**
 * Created with IntelliJ IDEA.
 * User: hdevil
 * Date: 6/16/13
 * Time: 6:54 PM
 * To change this template use File | Settings | File Templates.
 */
public class FJMachine {
    private FJStack currentInstructionStack = FJStack.getEmptyStack();
    private FJStack dormantInstructionStacks = FJStack.getEmptyStack();
    private FJStack operandStack = FJStack.getEmptyStack();
    private FJStack auxiliaryStack = FJStack.getEmptyStack();
    private FJPackage globalPackage = new FJPackage();
    
    private boolean executingStep = false;
    private boolean retainInstructionTop = true;
    
    public FJMachine()
    {
        // Sets up a new machine with empty instruction/operand stack
        // and new global symbol table filled with builtins.
        try {
            Builtins.registerBuiltins(globalPackage);
        } catch (FJException e) {
            System.out.println("Error during builtin initialization:");
            System.out.println(e);
            System.exit(1);
        }
    }

    public FJPackage getGlobalPackage()
    {
        return globalPackage;
    }
   
    public static boolean isSpecialOperator(FlapjackObject obj) {
    	return obj instanceof FJSpecialOperator;
    }
    
    void signalRetainInstructionTop()
    {
    	retainInstructionTop = true;
    }
    
    public boolean executeStep() throws FJException
    {
    	if (executingStep) throw new InstructionAccessViolation("Attempt to cause the execution of instructions while an instruction is executing.");

    	// Executes a single operation and returns true. Returns false if there were no more operations left.
        while (currentInstructionStack.isEmpty()
               && !dormantInstructionStacks.isEmpty())
        {
            // Done with current instruction stack, activate the last dormant stack if there was one.
            currentInstructionStack = (FJStack) dormantInstructionStacks.top();
            dormantInstructionStacks = dormantInstructionStacks.rest();
        }

        if (currentInstructionStack.isEmpty()) return false;

        FlapjackObject currentInstruction = currentInstructionStack.top();
            
        executingStep = true;
        retainInstructionTop = false;

        try {
        	currentInstruction.flapjackOperation(this);
        } finally {
        	executingStep = false; // Make sure that the flag is reset in case of an exception.
        }
        
        // Special operators can prevent this pop by calling signalRetainInstructionTop
        if (!retainInstructionTop) currentInstructionStack = currentInstructionStack.rest();
        
        return true;
    }

    // MANIPULATION OF OPERAND STACK

    public FJStack getOperandStack()
    {
        return operandStack;
    }

    public void setOperandStack(FJStack newOperandStack)
    {
        operandStack = newOperandStack;
    }

    public void pushOperandStack(FlapjackObject obj)
    {
        operandStack = operandStack.pushed(obj);
    }

    public void popOperandStack() throws FJException
    {
        operandStack = operandStack.rest();
    }

    // MANIPULATION OF AUXILIARY STACK

    public FJStack getAuxiliaryStack()
    {
        return auxiliaryStack;
    }

    public void setAuxiliaryStack(FJStack newAuxiliaryStack)
    {
        auxiliaryStack = newAuxiliaryStack;
    }

    public void pushAuxiliaryStack(FlapjackObject obj)
    {
        auxiliaryStack = auxiliaryStack.pushed(obj);
    }

    public void popAuxiliaryStack() throws FJException
    {
        auxiliaryStack = auxiliaryStack.rest();
    }


    // MANIPULATION OF INSTRUCTION STACK: THIS MUST BE PACKAGE PRIVATE SO THAT ONLY SPECIAL OPERATORS CAN USE THIS!

    FJStack getCurrentInstructionStack()
    {
        return currentInstructionStack;
    }

    void setCurrentInstructionStack(FJStack newInstructionStack)
    {
        currentInstructionStack = newInstructionStack;
    }

    void pushCurrentInstructionStack(FlapjackObject obj)
    {
        currentInstructionStack = currentInstructionStack.pushed(obj);
    }

    void popCurrentInstructionStack() throws FJException
    {
        currentInstructionStack = currentInstructionStack.rest();
    }

    // This should be package private, flapjackOperations must not be
    // allowed to assume instructions are represented internally by the
    // VM as stacks (even though it does in this case).
    void secureLoadInstructions(FJStack moreInstructions)
    {  	
    	if (executingStep && !retainInstructionTop) {
    		// If we're going to load instructions while the VM is executing an instruction,
    		// now is the time to pop the top instruction, otherwise the popping later on would
    		// pop the wrong object off the stack.
    		try {
    			currentInstructionStack = currentInstructionStack.rest();
    		} catch (Exception e) {
    			System.out.println("PANIC: Secure loading of instructions while instruction top set not to be retained, yet instruction stack is empty.");
    			System.exit(1);
    		}
    		// Stop the pop of the instruction stack from happening after the instruction has finished
    		// executing.
    		signalRetainInstructionTop();
    	}
    	if (!currentInstructionStack.isEmpty()) {
    		dormantInstructionStacks = dormantInstructionStacks.pushed(currentInstructionStack);
    	}
        currentInstructionStack = moreInstructions;
    }

    // ================================================================

    public void execute() throws FJException
    {
        while (this.executeStep());
    }

    public void loadInstructions(FJStack moreInstructions) throws InstructionAccessViolation
    {
    	// This should only be called by client programs, not flapjackOperations(), this
    	// if prevents it.
    	if (executingStep) throw new InstructionAccessViolation("Attempt to load instructions while instructions are executing.");
    	
    	secureLoadInstructions(moreInstructions);
    }

    public void clearInstructions() throws InstructionAccessViolation
    {
    	// This should only be called by client programs, not flapjackOperations(), this
    	// if prevents it.
    	if (executingStep) throw new InstructionAccessViolation("Attempt to clear instructions while instructions are executing.");
    	
    	dormantInstructionStacks = FJStack.getEmptyStack();
    	currentInstructionStack = FJStack.getEmptyStack();
    }

    public void execute(FJStack moreInstructions) throws FJException
    {
        loadInstructions(moreInstructions);
        execute();
    }

    public String dataStacksAsString()
    {
    	// THIS IS FOR DEBUGGING ONLY
    	return "opr:" + getOperandStack().toString() + 
    			" aux:" + getAuxiliaryStack().toString();
    }

    public String allStacksAsString() throws InstructionAccessViolation
    {
    	if (executingStep) throw new InstructionAccessViolation("Attempt to print instruction stacks while an instruction is executing.");
    	// THIS IS FOR DEBUGGING ONLY
    	return "opr:" + getOperandStack().toString() + 
    			" aux:" + getAuxiliaryStack().toString() +
    			" instr:" + getCurrentInstructionStack().toString() +
    			" dorm:" + dormantInstructionStacks.toString();
    }
}