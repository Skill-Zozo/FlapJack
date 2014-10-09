package flapjack.tests;

import static org.junit.Assert.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.Test;

public class BaseBuiltinTest {
	@Test
	public void testCall() {
		// Can call run builtin functions?
		MachineTester.testAtEnd("3 4 quote + get call", "opr:{7} aux:{}");	
		// Can call something like an FJStack?
		MachineTester.testAtEnd("{1 2 3} call","opr:{{1 2 3}} aux:{}");
		// Can call run lambdas?
		MachineTester.testAtEnd("1 2 lambda {3 4 5} 6 pop call", "opr:{5 4 3 2 1} aux:{}");	
		
		MachineTester.testForException("call", "EmptyStackException");
	}

	@Test
	public void testEq() {
		MachineTester.testAtEnd("false false eq", "opr:{true} aux:{}");	
		MachineTester.testAtEnd("true true eq", "opr:{true} aux:{}");	
		MachineTester.testAtEnd("false true eq", "opr:{false} aux:{}");	
		MachineTester.testAtEnd("pushempty pushempty eq", "opr:{true} aux:{}");	
		MachineTester.testAtEnd("{} pushempty eq", "opr:{true} aux:{}");	
		
		MachineTester.testForException("eq", "EmptyStackException");
		MachineTester.testForException("true eq", "EmptyStackException");
	}

	@Test
	public void testPrint() {
		final ByteArrayOutputStream myOut = new ByteArrayOutputStream();
		System.setOut(new PrintStream(myOut));
		MachineTester.testAtEnd("-1 {1 false true foo} print", "opr:{-1} aux:{}");	

		final String standardOutput = myOut.toString();
		assertEquals(standardOutput,"{1 false true foo}");
	}

	@Test
	public void testPrintln() {
		final ByteArrayOutputStream myOut = new ByteArrayOutputStream();
		System.setOut(new PrintStream(myOut));
		MachineTester.testAtEnd("-1 {1 false true foo} println", "opr:{-1} aux:{}");	
		final String standardOutput = myOut.toString();
		assertEquals(standardOutput,"{1 false true foo}\n");
	}

	@Test
	public void testBadPrintAndPrintln() {
		MachineTester.testForException("print", "EmptyStackException");
		MachineTester.testForException("println", "EmptyStackException");
	}
}
