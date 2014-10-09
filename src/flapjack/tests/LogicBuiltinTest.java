package flapjack.tests;

import org.junit.Test;

public class LogicBuiltinTest {
	@Test
	public void testAnd() {
		MachineTester.testAtEnd("false false and", "opr:{false} aux:{}");	
		MachineTester.testAtEnd("true false and", "opr:{false} aux:{}");	
		MachineTester.testAtEnd("false true and", "opr:{false} aux:{}");	
		MachineTester.testAtEnd("true true and", "opr:{true} aux:{}");	
		
		MachineTester.testForException("and", "EmptyStackException");
		MachineTester.testForException("6 and", "FJTypeException"); // Test type first before popping second operand
		MachineTester.testForException("true and", "EmptyStackException");
		MachineTester.testForException("true 2 and", "FJTypeException");
		MachineTester.testForException("2 false and", "FJTypeException");
	}

	@Test
	public void testOr() {
		MachineTester.testAtEnd("false false or", "opr:{false} aux:{}");	
		MachineTester.testAtEnd("true false or", "opr:{true} aux:{}");	
		MachineTester.testAtEnd("false true or", "opr:{true} aux:{}");	
		MachineTester.testAtEnd("true true or", "opr:{true} aux:{}");	
		
		MachineTester.testForException("or", "EmptyStackException");
		MachineTester.testForException("6 or", "FJTypeException"); // Test type first before popping second operand
		MachineTester.testForException("true or", "EmptyStackException");
		MachineTester.testForException("true 2 or", "FJTypeException");
		MachineTester.testForException("2 false or", "FJTypeException");
	}

	@Test
	public void testNot() {
		MachineTester.testAtEnd("false not", "opr:{true} aux:{}");	
		MachineTester.testAtEnd("true not", "opr:{false} aux:{}");	
		
		MachineTester.testForException("not", "EmptyStackException");
		MachineTester.testForException("2 not", "FJTypeException");
	}

	@Test
	public void testIsBoolean() {
		MachineTester.testAtEnd("4 boolean?", "opr:{false} aux:{}");	
		MachineTester.testAtEnd("true boolean?", "opr:{true} aux:{}");	
		MachineTester.testAtEnd("false boolean?", "opr:{true} aux:{}");	
		
		MachineTester.testForException("not", "EmptyStackException");
		MachineTester.testForException("2 not", "FJTypeException");
	}
}
