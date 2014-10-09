package flapjack.tests2;

import flapjack.machine.FJMachine;
import flapjack.types.*;
import flapjack.reader.FJReader;
import flapjack.exceptions.*;
import static org.junit.Assert.*;

public class MachineTester {
	public static void testForException(String code, String exceptionClassName)
	{
		FJMachine machine = new FJMachine();
		FJPackage global = machine.getGlobalPackage();
		FJStack program = null;
		try {
			try {
				program = FJReader.readAllFormsFromString(global, code);
			} catch (SymbolClashException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (ReadFailureException e) {
			fail("Read failure exception: " + e);
		}

		try {
			machine.loadInstructions(program);
		} catch (InstructionAccessViolation e) {
			System.out.println("PANIC: This should never happen. Why has this been called from inside a vm instruction?");
			System.exit(1);
		}
		
		try { 
			machine.execute();
		} catch (Throwable e) {
			String thrownName = e.getClass().getSimpleName();
			if (!thrownName.equals(exceptionClassName)) {
				fail("Wrong exception class thrown, expected " + exceptionClassName + ", got " + thrownName + " instead with message:" + e.getMessage());
			}
			return;
		} 
		fail("No exception thrown.");		
	}
			
	public static void testAtEnd(String code, String finalStacks)
	{
		FJMachine machine = new FJMachine();
		FJPackage global = machine.getGlobalPackage();
		FJStack program = null;
		try {
			try {
				program = FJReader.readAllFormsFromString(global, code);
			} catch (SymbolClashException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (ReadFailureException e) {
			fail("Read failure: " + e.getMessage());
		}
		
		try {
			machine.loadInstructions(program);
		} catch (InstructionAccessViolation e) {
			System.out.println("PANIC: Naughty flapjackOperation, why are you trying to load instructions directly using the public api? That's what lambda's are for.");
			System.exit(1);
		}

		try { 
			machine.execute();
		} catch (FJException e) {
			fail(e.getClass().getSimpleName() + ": " + e.getMessage());
		}
		
		String stacks = machine.dataStacksAsString();
		
		if (!stacks.equals(finalStacks)) {
			fail("Wrong final state:" + stacks);
		}
	}
}
