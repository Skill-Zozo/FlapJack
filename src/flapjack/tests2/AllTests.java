package flapjack.tests2;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ BaseBuiltinTest.class, LogicBuiltinTest.class,
		MathBuiltinTest.class, PackageTest.class, ReadIntegersTest.class,
		ReadStacksTest.class, ReadSymbolsTest.class, SpecialOpTest.class,
		StackBuiltinTest.class, SymbolBuiltinTest.class })
public class AllTests {

}
