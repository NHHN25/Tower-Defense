package game;

import java.awt.Graphics;
import java.awt.Point;

abstract public class Enemy implements Animatable 
{
	//Fields
	protected Path path;
	protected GameState state;
	protected double percentageTraveled;
	protected Point location;
	
	public Enemy (GameState state) {
		this.state = state;
		this.percentageTraveled = 0;
		this.path = state.getPath();
	}
	
	public Point getLocation() {
		location = path.locate(percentageTraveled);
		return location;
	}

}
