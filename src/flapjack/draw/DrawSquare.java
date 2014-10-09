package flapjack.draw;

//import java.awt.Color;

import flapjack.builtins.PlusFunction;
import flapjack.exceptions.FJException;
import flapjack.machine.FJMachine;
import flapjack.types.FJDouble;
//import flapjack.types.FJInteger;
import flapjack.types.FlapjackObject;

public class DrawSquare implements FlapjackObject{
	//private boolean canvasHasBeenSet = false;
	@Override
	public void flapjackOperation(FJMachine machine) throws FJException {
		// TODO Auto-generated method stub
		/*if(!this.canvasHasBeenSet) {
			
		}*/
		FJDouble width = (FJDouble)PlusFunction.getFJInt(machine);
		FJDouble height = (FJDouble)PlusFunction.getFJInt(machine);
		FJDouble radius = (FJDouble)PlusFunction.getFJInt(machine);
		drawSquare(width.doubleValue(), height.doubleValue(), radius.doubleValue());
	}
	
	private void drawSquare (double width,double height,double radius) {
		StdDraw.filledSquare(width,height,radius);
	}
}
