package game;

import java.awt.Graphics;

abstract public class Tower implements Animatable 
{
	//Fields
	protected int x, y;
	protected GameState state;
	
	//Constructor
	
	public Tower (GameState state, int x , int y) {
		this.state = state;
		this.x = x;
		this.y = y;
	}
	


}
