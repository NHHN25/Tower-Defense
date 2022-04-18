package game;

import java.awt.Graphics;
import java.awt.Point;

public class SCargo implements Animatable {
	private Path path;
	private GameState state;
	private double percentageTraveled;
	
	/**
	 * Constructor for Snail object
	 * @param state
	 * @param percentageTraveled
	 */
	public SCargo (GameState state) {
		this.state = state;
		this.percentageTraveled = 0;
	}
	
	public void update(double timeElapsed) {
		//Update the snail location
		percentageTraveled += 0.01;
		if (percentageTraveled > 1.0)
			percentageTraveled = 0.0;

	}

	/**
	 * This method calculates the position of the snail on the path and draws it
	 */
	public void draw(Graphics g, GameView view) {
		//Get the path
		path = state.getPath();
		
		//Calculate the location of the snail on the path according to the percentage traveled
		int intX = (int) path.locatePosition(percentageTraveled).getX();
		int intY = (int) path.locatePosition(percentageTraveled).getY();
		
		//Draw the snail
		view.drawCenteredImage(g, "resources/scargo.png", intX, intY);
	}

}
