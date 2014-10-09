package flapjack.tests;

import org.junit.Test;

public class SymbolBuiltinTest {
	@Test
	public void testUnboundSymbols() {
		MachineTester.testForException("1 2 3 foo", "UnboundSymbolException");	
	}		

	@Test
	public void testSetAndGet() {
		MachineTester.testAtEnd("15 quote foo set 1 2 3 4 quote foo get", "opr:{15 4 3 2 1} aux:{}");	
		
		MachineTester.testForException("set", "EmptyStackException");
		MachineTester.testForException("quote foo set", "EmptyStackException");
		MachineTester.testForException("2 2 set", "FJTypeException");

		MachineTester.testForException("get", "EmptyStackException");
		MachineTester.testForException("2 get", "FJTypeException");
	}

	@Test
	public void testLegalRedefinition() {
		MachineTester.testAtEnd("-1 15 quote + set + + +", "opr:{15 15 15 -1} aux:{}");	
		MachineTester.testAtEnd("-1 defun foo {1 2 3} foo 5 quote foo set foo foo", "opr:{5 5 3 2 1 -1} aux:{}");	
	}

	@Test
	public void testBadRedefinition() {
		MachineTester.testForException("15 quote defun set","IllegalRedefinitionException");
		MachineTester.testForException("15 quote eval set","IllegalRedefinitionException");
		MachineTester.testForException("15 quote ifthen set","IllegalRedefinitionException");
		MachineTester.testForException("15 quote lambda set","IllegalRedefinitionException");
		MachineTester.testForException("15 quote quote set","IllegalRedefinitionException");
		MachineTester.testForException("15 quote while set","IllegalRedefinitionException");
	}
	
	@Test
	public void testIsSymbol() {
		MachineTester.testAtEnd("15 symbol?", "opr:{false} aux:{}");	
		MachineTester.testAtEnd("quote foo symbol?", "opr:{true} aux:{}");	
		
		MachineTester.testForException("symbol?", "EmptyStackException");
	}
}
