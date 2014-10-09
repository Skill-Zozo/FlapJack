package flapjack.draw;

import flapjack.builtins.PlusFunction;
import flapjack.exceptions.FJException;
import flapjack.machine.FJMachine;
import flapjack.types.FJDouble;
import flapjack.types.FlapjackObject;

public class DrawEmptySquare implements FlapjackObject{

	@Override
	public void flapjackOperation(FJMachine machine) throws FJException {
		// TODO Auto-generated method stub
		FJDouble x = (FJDouble)PlusFunction.getFJInt(machine);
		FJDouble y = (FJDouble)PlusFunction.getFJInt(machine);
		FJDouble r = (FJDouble)PlusFunction.getFJInt(machine);
		StdDraw.square(x.doubleValue(), y.doubleValue(), r.doubleValue());
	}

}
