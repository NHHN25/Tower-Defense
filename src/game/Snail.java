package game;

import java.awt.Graphics;
import java.awt.Point;

public class Snail implements Animatable {
	private Path path;
	private GameState state;
	private double percentageTraveled;
	private int updateCount;
	
	/**
	 * Constructor for Snail object
	 * @param state
	 * @param percentageTraveled
	 */
	public Snail (GameState state) {
		this.state = state;
		this.percentageTraveled = 0;
		this.updateCount = 0;
	}
	
	public void update(double timeElapsed) {
		//Update the snail location
		percentageTraveled += 0.01;
		if (percentageTraveled > 1.0)
			percentageTraveled = 0.0;
		
		//Split Snail into half
		updateCount++;
		if (updateCount == 60) {
			Snail s = new Snail(state);
			s.percentageTraveled = percentageTraveled - 0.01;
			s.updateCount = 60;
			state.addAnimatable(s);
		}

	}

	/**
	 * This method calculates the position of the snail on the path and draws it
	 */
	public void draw(Graphics g, GameView view) {
		//Get the path
		path = state.getPath();
		
		//Calculate the location of the snail on the path according to the percentage traveled
		int intX = (int) path.locate(percentageTraveled).getX();
		int intY = (int) path.locate(percentageTraveled).getY();
		
		//Draw the snail
		view.drawCenteredImage(g, "resources/snail.png", intX, intY);
	}

}
