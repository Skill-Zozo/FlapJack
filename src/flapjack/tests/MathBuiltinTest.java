package flapjack.tests;

import org.junit.Test;

public class MathBuiltinTest {		
	@Test
	public void testAdd() {
		MachineTester.testAtEnd("9 4 +", "opr:{13} aux:{}");	
		MachineTester.testAtEnd("-9 4 +", "opr:{-5} aux:{}");	
		
		MachineTester.testAtEnd("2147483647 1 +", "opr:{-2147483648} aux:{}");	
		MachineTester.testAtEnd("-2147483644 1 +", "opr:{-2147483643} aux:{}");	
		
		MachineTester.testForException("+", "EmptyStackException");
		MachineTester.testForException("true +", "FJTypeException"); // Test type first before popping second operand
		MachineTester.testForException("1 +", "EmptyStackException");
		MachineTester.testForException("true 2 +", "FJTypeException");
		MachineTester.testForException("2 false +", "FJTypeException");
	}

	@Test
	public void testSub() {
		MachineTester.testAtEnd("9 4 -", "opr:{5} aux:{}");
		MachineTester.testAtEnd("-9 4 -", "opr:{-13} aux:{}");	
		
		MachineTester.testAtEnd("2147483647 1 -", "opr:{2147483646} aux:{}");	
		MachineTester.testAtEnd("-2147483648 1 -", "opr:{2147483647} aux:{}");	
		
		MachineTester.testForException("-", "EmptyStackException");
		MachineTester.testForException("true -", "FJTypeException"); // Test type first before popping second operand
		MachineTester.testForException("1 -", "EmptyStackException");
		MachineTester.testForException("true 2 -", "FJTypeException");
		MachineTester.testForException("2 false -", "FJTypeException");
	}
	
	@Test
	public void testMul() {
		MachineTester.testAtEnd("20 10 *", "opr:{200} aux:{}");

		MachineTester.testForException("*", "EmptyStackException");
		MachineTester.testForException("true *", "FJTypeException"); // Test type first before popping second operand
		MachineTester.testForException("1 *", "EmptyStackException");
		MachineTester.testForException("true 2 *", "FJTypeException");
		MachineTester.testForException("2 false *", "FJTypeException");
	}
	
	@Test
	public void testDiv() {
		MachineTester.testAtEnd("-20 10 /", "opr:{-2} aux:{}");

		MachineTester.testForException("/", "EmptyStackException");
		MachineTester.testForException("true /", "FJTypeException"); // Test type first before popping second operand
		MachineTester.testForException("1 /", "EmptyStackException");
		MachineTester.testForException("true 2 /", "FJTypeException");
		MachineTester.testForException("2 false /", "FJTypeException");
}
	
	@Test
	public void testMod() {
		MachineTester.testAtEnd("8 3 mod", "opr:{2} aux:{}");

		MachineTester.testForException("mod", "EmptyStackException");
		MachineTester.testForException("true mod", "FJTypeException"); // Test type first before popping second operand
		MachineTester.testForException("1 mod", "EmptyStackException");
		MachineTester.testForException("true 2 mod", "FJTypeException");
		MachineTester.testForException("2 false mod", "FJTypeException");
	}
	
	@Test
	public void testNeg() {
		MachineTester.testAtEnd("43 neg", "opr:{-43} aux:{}");

		MachineTester.testForException("neg", "EmptyStackException");
		MachineTester.testForException("true neg", "FJTypeException");
	}
	
	@Test
	public void testLessThan() {
		MachineTester.testAtEnd("5 10 <", "opr:{true} aux:{}");
		MachineTester.testAtEnd("5 5 <", "opr:{false} aux:{}");
		MachineTester.testAtEnd("10 5 <", "opr:{false} aux:{}");

		MachineTester.testForException("<", "EmptyStackException");
		MachineTester.testForException("true <", "FJTypeException"); // Test type first before popping second operand
		MachineTester.testForException("1 <", "EmptyStackException");
		MachineTester.testForException("true 2 <", "FJTypeException");
		MachineTester.testForException("2 false <", "FJTypeException");
		}
	
	@Test
	public void testEqual() {
		MachineTester.testAtEnd("5 10 =", "opr:{false} aux:{}");
		MachineTester.testAtEnd("10 5 =", "opr:{false} aux:{}");
		MachineTester.testAtEnd("10 10 =", "opr:{true} aux:{}");
		
		MachineTester.testForException("=", "EmptyStackException");
		MachineTester.testForException("true =", "FJTypeException"); // Test type first before popping second operand
		MachineTester.testForException("1 =", "EmptyStackException");
		MachineTester.testForException("true 2 =", "FJTypeException");
		MachineTester.testForException("2 false =", "FJTypeException");
	}


	@Test
	public void testIsInteger() {
		MachineTester.testAtEnd("20 integer?", "opr:{true} aux:{}");
		MachineTester.testAtEnd("true integer?", "opr:{false} aux:{}");
		MachineTester.testAtEnd("quote {1 2 3} integer?", "opr:{false} aux:{}");
		MachineTester.testAtEnd("quote foo integer?", "opr:{false} aux:{}");

		MachineTester.testForException("integer?", "EmptyStackException");
	}
}
