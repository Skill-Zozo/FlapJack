package flapjack.draw;

import java.awt.Color;

import flapjack.exceptions.FJException;
import flapjack.machine.FJMachine;
import flapjack.types.FlapjackObject;

public class RandomPenColor implements FlapjackObject{


	public void setRandomColor() {
		StdDraw.setPenColor(new Color((int)(256*Math.random()),(int)(256*Math.random()),(int)(256*Math.random())));
	}

	@Override
	public void flapjackOperation(FJMachine machine) throws FJException {
		// TODO Auto-generated method stub
		setRandomColor();
	}
}
