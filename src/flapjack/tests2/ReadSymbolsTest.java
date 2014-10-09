package flapjack.tests2;

import flapjack.types.*;
import flapjack.reader.*;
import flapjack.exceptions.*;

import org.junit.Test;
import static org.junit.Assert.*;


/**
 * Tests whether a simple sequence of symbols gets read into a FJStack correctly.
 */
public class ReadSymbolsTest { 
  @Test 
  public void testReadSingleSymbol() throws EmptyStackException, ReadFailureException {
    FJStack items=null;
    
    try {
		items = FJReader.readAllFormsFromString(new FJPackage(), "foo");
	} catch (SymbolClashException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
    assertFalse(items.isEmpty());
    
    FJSymbol theSym = (FJSymbol) items.top();
    assertTrue(theSym.getSymbolName().equals("foo"));
    assertTrue(items.rest().isEmpty());
  }
  
  @Test 
  public void testReadLotsOfSymbols() throws EmptyStackException, ReadFailureException, SymbolClashException {
    String[] testSymbolNames = {"foo","bar","baz"};    
    FJStack items=null;
    
    items = FJReader.readAllFormsFromString(new FJPackage(), "foo bar baz");  

    FJSymbol theSym;
    
    
    
    for (int i = 0 ; i < testSymbolNames.length ; i++) {
      assertFalse(items.isEmpty());
      
    
      theSym = (FJSymbol) items.top();
      assertTrue(theSym.getSymbolName().equals(testSymbolNames[i]));   
      items = items.rest();
    }
    
    assertTrue(items.isEmpty());
  }

  @Test 
  public void testReaderIsInterning() throws EmptyStackException, ReadFailureException, SymbolClashException {
	    FJStack items=null;
	    
	    items = FJReader.readAllFormsFromString(new FJPackage(), "foo bar bar foo");  

	    FJSymbol firstFoo = (FJSymbol)items.top();
	    items = items.rest();
	    
	    FJSymbol firstBar = (FJSymbol)items.top();
	    items = items.rest();
	    
	    FJSymbol secondBar = (FJSymbol)items.top();
	    items = items.rest();
	    
	    FJSymbol secondFoo = (FJSymbol)items.top();
	    items = items.rest();
	    
	    assertTrue(firstFoo.getSymbolName().equals("foo"));
	    assertTrue(firstBar.getSymbolName().equals("bar"));
	    assertTrue(firstFoo == secondFoo);
	    assertTrue(firstBar == secondBar);
	  }
}
