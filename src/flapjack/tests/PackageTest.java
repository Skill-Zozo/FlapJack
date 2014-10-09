package flapjack.tests;

import static org.junit.Assert.*;

import org.junit.Test;

import flapjack.types.FJPackage;
import flapjack.types.FJSymbol;

public class PackageTest {
	@Test
	public void testInternIdentity() {
		FJPackage pkg = new FJPackage();
		FJSymbol foo = new FJSymbol("foo");
		FJSymbol alsoFoo = pkg.intern("foo"); 
		FJSymbol alsoAlsoFoo = pkg.intern("foo");
		FJSymbol otherFoo = new FJSymbol("foo");

		/* Does the symbol constructor even set the symbol name? */
		assertEquals(foo.getSymbolName(),"foo");
		/* Do the interned symbols have the correct names? */	
		assertEquals(alsoFoo.getSymbolName(),"foo");

		/* These two test make sure that uninterned
		 * symbols foo and Otherfoo may be created
		 * and not be identical to any other symbol.
		 */
		assertTrue(foo != alsoFoo);
		assertTrue(foo != otherFoo);
		assertTrue(otherFoo != alsoFoo);

		/* The follow tests whether intern always returns
		 * the same object if called with the same string.
		 */
		assertTrue(alsoFoo==alsoAlsoFoo);
	}

	@Test
	public void testInternCollection() {
		FJPackage pkg = new FJPackage();
		FJSymbol foo = pkg.intern("foo");
		FJSymbol bar = pkg.intern("bar");
		FJSymbol baz = pkg.intern("baz");
		FJSymbol alsoFoo = pkg.intern("foo");
		FJSymbol alsoBar = pkg.intern("bar");
		FJSymbol alsoBaz = pkg.intern("baz");

		// Do multiple interned symbols work?
		
		assertTrue(foo == alsoFoo);
		assertTrue(bar == alsoBar);
		assertTrue(baz == alsoBaz);
	}
}
