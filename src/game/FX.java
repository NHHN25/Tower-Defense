package game;

import java.awt.Graphics;

abstract public class FX implements Animatable 
{
	protected GameState state;
	protected double x , y;
	
	public FX (GameState state, double x, double y) {
		this.state = state;
		this.x = x;
		this.y = y;
	}


}
