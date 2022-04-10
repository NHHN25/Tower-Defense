/** Header comment needed! */
package game;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Scanner;
import java.util.Timer;

public class GameState {
	// Fields

	private Path path;
	private GameControl control;
	private Animatable background;
	private Animatable menu;
	private Animatable snail;

	private double percentageTraveled;

	private double squareAngle;

	/* Contracts needed! */

	// Constructor

	public GameState(GameControl control) {
		System.out.println("GameState constructor");
		
		this.background = new Background();
		this.menu = new Menu();
		this.snail = new Snail(this, percentageTraveled);
		this.control = control;

		// Build our path
		Scanner sc;
		try {
			sc = new Scanner(new File("src/resources/path.txt"));
			path = new Path(sc);
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			System.out.println(e1);
		}

		// Initialized percentage traveled
		percentageTraveled = 0.0;

	}

	// Function to return a path object

	public Path getPath() {
		return path;
	}

	// Function to draw everything

	public void drawAll(Graphics g, GameView view) {
		// Draw the background

		background.draw(g, view);
		menu.draw(g, view);
		snail.draw(g, view);

		// Draw the path

		path.paint(g);

//		// Draw the ball
//		int intX = (int) path.locatePosition(percentageTraveled).getX();
//		int intY = (int) path.locatePosition(percentageTraveled).getY();
//		g.setColor(Color.BLUE);
//		g.fillRect(intX, intY, 20, 20);
	}

	/**
	 * This method updates every frame of the game.
	 */
	public void updateAll(double elapsedTime) {
		percentageTraveled += 0.001;
		if (percentageTraveled > 1.0)
			percentageTraveled = 0.0;
		
		background.update(elapsedTime);
		menu.update(elapsedTime);
		snail.update(elapsedTime);
	}
}
