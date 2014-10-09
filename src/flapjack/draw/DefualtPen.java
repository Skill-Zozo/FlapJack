package flapjack.draw;

import flapjack.exceptions.FJException;
import flapjack.machine.FJMachine;
import flapjack.types.FlapjackObject;

public class DefualtPen implements FlapjackObject {

	@Override
	public void flapjackOperation(FJMachine machine) throws FJException {
		// TODO Auto-generated method stub
		StdDraw.setPenColor();
	}

}
