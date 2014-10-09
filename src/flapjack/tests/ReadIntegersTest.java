package flapjack.tests;

import org.junit.Test;
import static org.junit.Assert.*;
import flapjack.types.*;
import flapjack.reader.*;
import flapjack.exceptions.*;

/**
 * Tests whether a simple sequence of integers gets read into a FJStack correctly.
 */
public class ReadIntegersTest {
  @Test 
  public void testReadSingleInteger() throws EmptyStackException, ReadFailureException {
    FJStack items=null;
    
    try {
		items = FJReader.readAllFormsFromString(new FJPackage(), "1234");
	} catch (SymbolClashException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
    assertFalse(items.isEmpty());
    
    FJInteger theInt = (FJInteger) items.top();
    assertEquals(theInt.intValue(),1234);
    assertTrue(items.rest().isEmpty());
  }
  
  @Test 
  public void testReadTwoIntegers() throws EmptyStackException, ReadFailureException {
    FJStack items=null;
    
    try {
		items = FJReader.readAllFormsFromString(new FJPackage(), "1234 12345");
	} catch (SymbolClashException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
    assertFalse(items.isEmpty());
    
    FJInteger theInt = (FJInteger) items.top();
    assertEquals(theInt.intValue(),1234);   
    items = items.rest();
    
    theInt = (FJInteger) items.top();
    assertEquals(theInt.intValue(),12345);
    assertTrue(items.rest().isEmpty());
  }

  @Test 
  public void testReadLotsOfIntegers() throws EmptyStackException, ReadFailureException {
    int[] testIntegers = {1234, -1234, 1, -1, 50000, -50000};    
    FJStack items=null;
    
    try {
		items = FJReader.readAllFormsFromString(new FJPackage(), "1234 -1234 1 -1 50000 -50000");
	} catch (SymbolClashException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}  

    FJInteger theInt;
    
    for (int i = 0 ; i < testIntegers.length ; i++) {
      assertFalse(items.isEmpty());
    
      theInt = (FJInteger) items.top();
      assertEquals(theInt.intValue(),testIntegers[i]);   
      items = items.rest();
    }
    
    assertTrue(items.isEmpty());
  }
}
