package flapjack.tests2;

import static org.junit.Assert.assertTrue;
import flapjack.exceptions.EmptyStackException;
import flapjack.types.FJBoolean;
import flapjack.types.FJInteger;
import flapjack.types.FJStack;
import flapjack.types.FJSymbol;

/* This is a utility class that allows the testing of the contents of stacks in detail.
 * Arrays of the form Object[] are used as templates against which the stacks are tested.
 * For example, the array new Object[]{1, foo, new Object[]{5,6}, true} tests for the
 * stack {1 foo {5 6} true} while testing each object whether it is a FJStack, FJSymbol,
 * FJInteger or FJBoolean according to the type of Object in the template array. This is
 * a much more stringent test than testing against the toString().
 */

public class StackTester {
	public static void assertIntegerTop(FJStack stack, int value) throws EmptyStackException
	{
		assertTrue("Expected integer " + value + " but stack was empty.",!stack.isEmpty());
		assertTrue("Expected integer " + value + " but stack top was " + stack.top()
					+ " of type " + stack.top().getClass().getSimpleName(), stack.top() instanceof FJInteger);
		int itemValue = ((FJInteger)stack.top()).intValue();
		assertTrue("Expected value " + value + " but stack top had value " + itemValue, itemValue  == value);
	}

	public static void assertSymbolTop(FJStack stack, String name) throws EmptyStackException
	{
		assertTrue("Expected symbol " + name + " but stack was empty.",!stack.isEmpty());
		assertTrue("Expected symbol " + name + " but stack top was " + stack.top() +
					" of type " + stack.top().getClass().getSimpleName(), stack.top() instanceof FJSymbol);
		String itemValue = ((FJSymbol)stack.top()).getSymbolName();
		assertTrue("Expected symbol name " + name + " but stack top had name " + itemValue, itemValue.equals(name));
	}

	public static void assertBooleanTop(FJStack stack, boolean bool) throws EmptyStackException
	{
		assertTrue("Expected boolean " + bool + " but stack was empty.",!stack.isEmpty());
		assertTrue("Expected boolean " + bool + " but stack top was " + stack.top() + 
				   " of type " + stack.top().getClass().getSimpleName(), 
				   stack.top() instanceof FJBoolean);
		FJBoolean item = (FJBoolean) stack.top();

		if (bool) 
			assertTrue("Expected boolean true.",FJBoolean.getTrue() == item);
		else
			assertTrue("Expected boolean false.",FJBoolean.getFalse() == item);					
	}

	public static void assertStackTop(FJStack stack, Object[] arr) throws EmptyStackException
	{
		assertTrue("Expected the stack " + arr + " at top, but stack was empty.",!stack.isEmpty());
		assertTrue("Expected the stack " + arr + " at top, but stack top was the non-stack " +stack.top()+
					" of type " + stack.top().getClass().getSimpleName(), stack.top() instanceof FJStack);
		assertStackFromTemplate((FJStack) stack.top(), arr);	
	}
	
	public static void assertStackFromTemplate(FJStack stack, Object[] arr) throws EmptyStackException
	{
		for (Object o : arr) {
			assertTrue("Expected the object " + o + ", but no objects left in stack.",!stack.isEmpty());
			if (o instanceof Integer) assertIntegerTop(stack, ((Integer) o).intValue());
			if (o instanceof String) assertSymbolTop(stack, ((String) o));
			if (o instanceof Boolean) assertBooleanTop(stack, ((Boolean) o).booleanValue());
			if (o instanceof Object[]) assertStackTop(stack, (Object[]) o);
			stack = stack.rest();
		}
	}
}
