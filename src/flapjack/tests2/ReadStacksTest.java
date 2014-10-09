package flapjack.tests2;

import org.junit.Test;

import flapjack.exceptions.*;
import flapjack.reader.FJReader;
import flapjack.types.*;

public class ReadStacksTest {

	@Test
	public void testStackReading() throws FJException {
		// Remember, reading the forms {-1234 foo true false} results in {{-1234 foo true false}}
		// hence the array within an array
		StackTester.assertStackFromTemplate(FJReader.readAllFormsFromString(new FJPackage(), "{-1234 foo true false}"),
					            new Object[]{new Object[]{-1234, "foo",true,false}});

		// Do multiple forms including a stack work?
		StackTester.assertStackFromTemplate(FJReader.readAllFormsFromString(new FJPackage(), "1 {1 2 3} 2"),
	            new Object[]{1,new Object[]{1,2,3},2});

		// Does the empty stack work?
		StackTester.assertStackFromTemplate(FJReader.readAllFormsFromString(new FJPackage(), "{} { }"),
	            new Object[]{new Object[]{},new Object[]{}});
		// Does reading a stack over comment boundaries work?
		StackTester.assertStackFromTemplate(FJReader.readAllFormsFromString(new FJPackage(), "{1 2 \\ Comment in stack \n  3}"),
	            new Object[]{new Object[]{1,2,3}});
	}
}
