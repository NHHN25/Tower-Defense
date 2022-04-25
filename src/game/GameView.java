/** Please add a header comment! */
package game;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class GameView extends JPanel {
	// Fields

	private GameState state;
	private GameControl control;

	// Constructor

	/** Please add a contract! */

	public GameView(GameState state, GameControl control) {
		System.out.println("GameView constructor");

		// Keep track of the game's state object.
		this.control = control;
		this.state = state;

		// Build the frame and the panel, then put 'this' object in it.
		// First, use inherited methods to set a new pixel size for this object.

		this.setMinimumSize(new Dimension(600, 600));
		this.setMaximumSize(new Dimension(700, 600));
		this.setPreferredSize(new Dimension(700, 600));

		// Make the JFrame, ask it to exit the application when closed.

		JFrame frame = new JFrame("Defend the Garden");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// Put 'this' JPanel into the frame.

		frame.setContentPane(this);

		// Reallocate frame space (called packing), then show it.

		frame.pack();
		frame.setVisible(true);
	}

	/** Please add a contract! */

	public void paint(Graphics g) {
		state.drawAll(g, this); // We'll do all the work in drawAll in this checkpoint
	}
	
	public void drawCenteredImage (Graphics g, String name, int x, int y) {
		BufferedImage img = control.loadImage(name);
		g.drawImage(img, x-img.getWidth()/2, y-img.getHeight()/2, null);
		
	}
	public void drawCenteredAndSize (Graphics g, String name, int x, int y, int sizeX, int sizeY) {
		BufferedImage img = control.loadImage(name);
		g.drawImage(img, x-img.getWidth()/2, y-img.getHeight()/2, sizeX, sizeY,null);
	}
}
