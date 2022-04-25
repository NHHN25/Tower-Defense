package game;

import java.awt.Graphics;

abstract public class Tower implements Animatable 
{
	//Fields
	protected int x, y;
	protected GameState state;
	protected GameControl control;
	
	//Constructor
	
	public Tower (GameState state, GameControl control, int x , int y) {
		this.state = state;
		this.control = control;
		this.x = x;
		this.y = y;
	}
	


}
