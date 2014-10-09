package flapjack.tests;

import org.junit.Test;

public class SpecialOpTest {
	@Test
	public void testQuote() {
		MachineTester.testAtEnd("quote 1", "opr:{1} aux:{}");
		MachineTester.testAtEnd("quote foo", "opr:{foo} aux:{}");
		MachineTester.testAtEnd("quote true", "opr:{true} aux:{}");
		MachineTester.testAtEnd("quote {1 2 3}", "opr:{{1 2 3}} aux:{}");
		MachineTester.testAtEnd("quote if", "opr:{if} aux:{}");
	}

	@Test
	public void testBadQuote() {
		// Missing thing to quote
		MachineTester.testForException("1 2 3 quote", "MalformedSpecialOperation");
	}

	@Test
	public void testBasicIfthen() {
		// Does it preserve the rest of the program?
		MachineTester.testAtEnd("5 true ifthen {1} 2",
								"opr:{2 1 5} aux:{}");
		MachineTester.testAtEnd("5 false ifthen {1} 2",
								"opr:{2 5} aux:{}");

		// Can it handle an empty rest of program?
		MachineTester.testAtEnd("5 true ifthen {1}",
								"opr:{1 5} aux:{}");

		MachineTester.testAtEnd("5 false ifthen {1}",
								"opr:{5} aux:{}");
	}

	@Test
	public void testBasicIfthenElseTrue() {
		// Does it preserve the rest of the program?
		MachineTester.testAtEnd("true ifthen {1} else {3} 2",
								"opr:{2 1} aux:{}");
		MachineTester.testAtEnd("false ifthen {1} else {3} 2",
								"opr:{2 3} aux:{}");
		// Can it handle an empty rest of program?
		MachineTester.testAtEnd("true ifthen {1} else {3}",
								"opr:{1} aux:{}");
		MachineTester.testAtEnd("false ifthen {1} else {3}",
								"opr:{3} aux:{}");
	}

	@Test
	public void testBadIfs() {
		// ifthen followed by nothing
		MachineTester.testForException("false ifthen","MalformedSpecialOperation");

		// operand is missing
		MachineTester.testForException("ifthen {}","EmptyStackException");

		// operand is not a boolean
		MachineTester.testForException("5 ifthen {}","FJTypeException");

		// true body is not a stack
		MachineTester.testForException("false ifthen 3","MalformedSpecialOperation");
		
		// false body is not a stack
		MachineTester.testForException("false ifthen {3} else 4","MalformedSpecialOperation"); 

		// false body is missing
		MachineTester.testForException("false ifthen {3} else","MalformedSpecialOperation"); 
	}

    @Test(timeout=500) // If the test takes too long, you're probably in an infinite loop somewhere
    public void testWhile() {
		// Does it preserve the rest of the program?
		MachineTester.testAtEnd("true while {3 false} 4",
								"opr:{4 3} aux:{}");    	
		MachineTester.testAtEnd("false while {3 false} 4",
				"opr:{4} aux:{}");    	
		// Can it handle an empty rest of program?
		MachineTester.testAtEnd("5 true while {3 false}",
				"opr:{3 5} aux:{}");    	
		MachineTester.testAtEnd("5 false while {3 false}",
				"opr:{5} aux:{}");    	

		// Note, dup has to be working for this test to function properly
		// Equivalent to: int x = 0; while (2*x < 10) {x++;} return x;
		MachineTester.testAtEnd("0 true while {1 + dup 2 * 10 <}",
				"opr:{5} aux:{}");    	
    }

	@Test(timeout=500) // If the test takes too long, you're probably in an infinite loop somewhere
	public void testBadWhiles() {
		// operand is missing
		MachineTester.testForException("while {}","EmptyStackException");

		// operand is not a boolean
		MachineTester.testForException("5 while {}","FJTypeException");

		// while followed by nothing
		MachineTester.testForException("false while","MalformedSpecialOperation");
		
		// body is not a stack
		MachineTester.testForException("false while 3","MalformedSpecialOperation");
	}

	@Test
	public void testEval() {
		MachineTester.testAtEnd("1 quote {2 3 4} eval 5",
								"opr:{5 4 3 2 1} aux:{}");    	
		MachineTester.testAtEnd("1 quote {} eval 5",
								"opr:{5 1} aux:{}");    	
	}

	@Test
	public void testBadEval() {
		// eval with empty operand stack
		MachineTester.testForException("eval","EmptyStackException");
		MachineTester.testForException("1 eval","FJTypeException");
		MachineTester.testForException("true eval","FJTypeException");
	}


	@Test
	public void testLambda() {
		MachineTester.testAtEnd("1 lambda {2 3 4} call 5",
								"opr:{5 4 3 2 1} aux:{}");    	
		MachineTester.testAtEnd("1 lambda {} call 5",
								"opr:{5 1} aux:{}");    	
	}

	@Test
	public void testBadLambda() {
		// lambda followed by nothing
		MachineTester.testForException("lambda","MalformedSpecialOperation");

		// body is not a stack
		MachineTester.testForException("lambda 4","MalformedSpecialOperation");
	}

	@Test
	public void testDefun() {
		// Handles nothing after defun
		MachineTester.testAtEnd("1 defun foo {3 4 5}",
								"opr:{1} aux:{}");    	

		// Preserves code after defun
		MachineTester.testAtEnd("1 defun foo {3 4 5} 6",
								"opr:{6 1} aux:{}");    	

		// Calls function successfully 
		MachineTester.testAtEnd("1 defun foo {3 4 5} foo 6",
								"opr:{6 5 4 3 1} aux:{}");    	
		MachineTester.testAtEnd("1 defun foo {3 4 5} 6 foo",
								"opr:{5 4 3 6 1} aux:{}");    	
}

	@Test
	public void testBadDefun() {
		// lambda followed by nothing
		MachineTester.testForException("defun","MalformedSpecialOperation");

		// name is not a symbol
		MachineTester.testForException("defun 5","MalformedSpecialOperation");

		// name is followed by nothing
		MachineTester.testForException("defun foo","MalformedSpecialOperation");

		// body is not a stack
		MachineTester.testForException("defun foo 5","MalformedSpecialOperation");
	}

}
