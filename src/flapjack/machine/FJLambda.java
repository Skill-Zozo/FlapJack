package flapjack.machine;

import flapjack.exceptions.FJException;
//import flapjack.types.FJFunction;
import flapjack.types.FJStack;
import flapjack.types.FlapjackObject;

public class FJLambda implements FlapjackObject //extends FJFunction
{
    private FJStack body;// = FJStack.getEmptyStack();

    @SuppressWarnings("unused")
	private FJLambda()
    {
        // Never use this, Lambdas only make sense if they have an associated block of code
    }

    public FJLambda(FJStack lambdaBody)
    {
        // Store the code block given to the lambda operation
    	body = lambdaBody;
    }

    public void flapjackOperation(FJMachine machine) throws FJException
    {
        // Load the function body
    	machine.secureLoadInstructions(body);
    }
}
