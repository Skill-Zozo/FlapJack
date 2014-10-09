package flapjack.tests;

import org.junit.Test;

public class StackBuiltinTest {
	@Test
	public void testDup() {
		MachineTester.testAtEnd("-1 14 dup", "opr:{14 14 -1} aux:{}");	
		
		MachineTester.testForException("dup", "EmptyStackException");
	}

	@Test
	public void testPushEmpty() {
		MachineTester.testAtEnd("-1 pushempty", "opr:{{} -1} aux:{}");		
	}

	@Test
	public void testFromAux() {
		MachineTester.testAtEnd("{1 2 3} replaceaux aux-> aux->", "opr:{2 1} aux:{3}");		

		MachineTester.testForException("aux->", "EmptyStackException");
	}

	@Test
	public void testIsEmpty() {
		MachineTester.testAtEnd("-1 pushempty empty?", "opr:{true -1} aux:{}");		
		MachineTester.testAtEnd("-1 quote {1 2 3} empty?", "opr:{false -1} aux:{}");		

		MachineTester.testForException("empty?", "EmptyStackException");
		MachineTester.testForException("1 empty?", "FJTypeException");
	}

	@Test
	public void testIsStack() {
		MachineTester.testAtEnd("-1 {} stack?", "opr:{true -1} aux:{}");		
		MachineTester.testAtEnd("-1 {1 2 3} stack?", "opr:{true -1} aux:{}");		
		MachineTester.testAtEnd("-1 555 stack?", "opr:{false -1} aux:{}");		
		MachineTester.testAtEnd("-1 quote foo stack?", "opr:{false -1} aux:{}");		
		MachineTester.testAtEnd("-1 true stack?", "opr:{false -1} aux:{}");		
		MachineTester.testForException("stack?", "EmptyStackException");
	}
	@Test
	public void testPopDown() {
		MachineTester.testAtEnd("-1 quote {1 2 3} popdown", "opr:{{2 3} 1 -1} aux:{}");		

		MachineTester.testForException("popdown", "EmptyStackException");
		MachineTester.testForException("1 popdown", "FJTypeException");
	}

	@Test
	public void testPop() {
		MachineTester.testAtEnd("-1 1 2 3 pop", "opr:{2 1 -1} aux:{}");		

		MachineTester.testForException("pop", "EmptyStackException");
	}

	@Test
	public void testPopAux() {
		MachineTester.testAtEnd("-1 1 2 3 ->aux ->aux popaux", "opr:{1 -1} aux:{3}");		

		MachineTester.testForException("popaux", "EmptyStackException");
	}

	@Test
	public void testPopUp() {
		MachineTester.testAtEnd("-1 quote {1 2 3} popup", "opr:{1 {2 3} -1} aux:{}");		

		MachineTester.testForException("popup", "EmptyStackException");
		MachineTester.testForException("1 popup", "FJTypeException");
	}

	@Test
	public void testPushDown() {
		MachineTester.testAtEnd("-1 {3 2 1} 4 pushdown", "opr:{{4 3 2 1} -1} aux:{}");		
		MachineTester.testAtEnd("-1 {} 4 pushdown", "opr:{{4} -1} aux:{}");		

		MachineTester.testForException("pushdown", "EmptyStackException");
		MachineTester.testForException("5 pushdown", "EmptyStackException");
		MachineTester.testForException("1 5 pushdown", "FJTypeException");
		MachineTester.testForException("false 5 pushdown", "FJTypeException");
	}

	@Test
	public void testPushUp() {
		MachineTester.testAtEnd("-1 4 {3 2 1} pushup", "opr:{{4 3 2 1} -1} aux:{}");		
		MachineTester.testAtEnd("-1 4 {} pushup", "opr:{{4} -1} aux:{}");		
		MachineTester.testAtEnd("-1 1 2 3 {} pushup pushup pushup", "opr:{{1 2 3} -1} aux:{}");		

		MachineTester.testForException("pushup", "EmptyStackException");
		MachineTester.testForException("5 pushup", "FJTypeException");
		MachineTester.testForException("{1 2} pushup", "EmptyStackException");
	}

	@Test
	public void testReplaceAux() {
		MachineTester.testAtEnd("-1 quote {1 2 3} replaceaux", "opr:{-1} aux:{1 2 3}");		

		MachineTester.testForException("replaceaux", "EmptyStackException");
		MachineTester.testForException("1 replaceaux", "FJTypeException");
	}

	@Test
	public void testReplaceStack() {
		MachineTester.testAtEnd("-1 quote {1 2 3} replacestack", "opr:{1 2 3} aux:{}");		

		MachineTester.testForException("replacestack", "EmptyStackException");
		MachineTester.testForException("1 replacestack", "FJTypeException");
	}

	
	@Test
	public void testRest() {
		MachineTester.testAtEnd("-1 {1 2 3} rest", "opr:{{2 3} -1} aux:{}");		
		MachineTester.testForException("-1 {} rest", "EmptyStackException");
		MachineTester.testForException("rest", "EmptyStackException");
	}	

	@Test
	public void testReverse() {
		MachineTester.testAtEnd("-1 quote {1 2 3} reverse", "opr:{{3 2 1} -1} aux:{}");		

		MachineTester.testForException("reverse", "EmptyStackException");
		MachineTester.testForException("1 reverse", "FJTypeException");
	}

	@Test
	public void testStackAll() {
		MachineTester.testAtEnd("-1 1 2 3 stackall", "opr:{{3 2 1 -1}} aux:{}");		
	}

	@Test
	public void testStackAux() {
		MachineTester.testAtEnd("quote {1 2 3} replaceaux stackaux", "opr:{{1 2 3}} aux:{1 2 3}");		
	}

	@Test
	public void testToAux() {
		MachineTester.testAtEnd("-1 1 2 3 ->aux ->aux", "opr:{1 -1} aux:{2 3}");		
		MachineTester.testForException("->aux", "EmptyStackException");
	}

	@Test
	public void testTop() {
		MachineTester.testAtEnd("-1 {1 2 3} top", "opr:{1 -1} aux:{}");		
		MachineTester.testForException("-1 {} top", "EmptyStackException");
		MachineTester.testForException("top", "EmptyStackException");
	}	
}
