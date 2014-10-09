package flapjack.types;

import flapjack.exceptions.FJException;
import flapjack.machine.FJMachine;


// This is just an interface to bless anything that can be a piece of data in Flapjack.
// flapjackOperation must be implemented to specify what happens when the implementer is
// encountered at the top of the instruction stack. The instruction is popped from the
// top of the instruction stack before flapjackOperation is executed, but the original
// instruction stack state may be restore by calling machine.restore

public interface FlapjackObject {
    public void flapjackOperation(FJMachine machine) throws FJException;
}
