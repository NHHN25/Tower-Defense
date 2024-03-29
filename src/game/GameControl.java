/**
 * Tower defense 'control' class.  This class encapsulates all the logic and setup
 * for the game, including creation of state and view classes, listener creation,
 * timers, game start and game over logic, etc.
 * 
 * @author Nam Nguyen (add your name if you make changes)
 * @version Spring 2022
 */
package game;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.*;
import javax.swing.Timer;

import javax.swing.SwingUtilities;

public class GameControl implements Runnable, ActionListener {
	// Fields

	private GameView view;
	private GameState state;
	private Timer timer;
	private Map<String, BufferedImage> images;
	private long previousTime;

	// Constructor

	/**
	 * Starts the game initialization process.
	 */
	public GameControl() {
		System.out.println("Starting the game...");

		// The constructor is called from main, so it is executing in the main thread.
		// All GUI work needs to be done by the GUI thread. Make a call to ask the
		// GUI thread to run this object, then simply return to main. The remainder
		// of the game setup will take place when the 'run' method is called by
		// the GUI thread (later).

		SwingUtilities.invokeLater(this);

		// Nothing else for the main thread to do -- simply return.
	}

	/**
	 * This is the first function invoked (called) by the GUI thread. Set up the
	 * game.
	 */
	public void run() {
		System.out.println("Hey -- I'm the GUI thread executing this code.");
		
		this.images = new HashMap<String, BufferedImage>();


		// Set up game objects.

		state = new GameState(this);
		view = new GameView(state, this);
		
		//Build background and menu
		state.addAnimatable(new Background());
		state.addAnimatable(new Menu(this, state));
		//Draw Salt tower on menu
		state.addAnimatable(new TowerSaltMenu(state, this, 650, 200));
		state.addAnimatable(new TowerBeerMenu(state, this, 650, 300));
		state.addAnimatable(new TowerTurretMenu(state, this, 650, 400));
		
		//Add mouse listeners
		view.addMouseListener(state);
		view.addMouseMotionListener(state);
		
		//Iniatilize the previous time
		previousTime = System.currentTimeMillis();
		
		// Initialize a timer
		timer = new Timer(32, this);
		timer.start();
	}

	public void actionPerformed(ActionEvent e)
	{
		if (state.getGameStart()) {
			long currentTime = System.currentTimeMillis();
			double elapsedTime = (currentTime - previousTime) / 1000.0;
			previousTime = currentTime;
			state.updateAll(elapsedTime);
			view.repaint();
		} else 
			return;
		
	}

	/**
	 * This method takes a String and return a BufferedImage object. It will save the image into a Map and retrieve the image from the Map the second time the function is called
	 * @param s
	 * @return a BufferedImage object
	 */
	public BufferedImage loadImage(String s) {
		// Initialize the image cache
		ClassLoader loader = this.getClass().getClassLoader();
		InputStream is = loader.getResourceAsStream(s);
		BufferedImage image = null;
		try {
			image = javax.imageio.ImageIO.read(is);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//Check if the image exists in the map or not
		if (images.containsKey(s))
			image = images.get(s);
		
		//If the image doesn't exists yet, put it in the map
		else images.put(s, image);
	
		return image;

		
	}

}
