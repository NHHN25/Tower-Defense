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
	private BufferedImage background;
	private double percentageTraveled;

	private double squareAngle;

	/* Contracts needed! */

	// Constructor

	public GameState() {
		System.out.println("GameState constructor");

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

		// Load the background jpg

		try {
			ClassLoader loader = this.getClass().getClassLoader();
			InputStream is = loader.getResourceAsStream("resources/path_2.jpg");
			background = javax.imageio.ImageIO.read(is);
		} catch (IOException e) {
			System.out.println("Unable to load background.");
		}
	}

	// Function to return a path object

	public Path getPath() {
		return path;
	}

	// Function to draw everything

	public void drawAll(Graphics g) {
		// Draw the background

		g.drawImage(background, 0, 0, null);

		// Draw the path

		path.paint(g);

		// Draw the ball
		int intX = (int)path.locatePosition(percentageTraveled).getX();
		int intY = (int)path.locatePosition(percentageTraveled).getY();
		g.setColor(Color.BLUE);
		g.fillOval(intX, intY, 20, 20);
	}

	/**
	 * This method updates every frame of the game.
	 */
	public void updateAll() {
		percentageTraveled += 0.001;
		if (percentageTraveled > 1.0)
			percentageTraveled = 0.0;
	}
}
