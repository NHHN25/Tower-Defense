/**
 * Tower defense 'control' class.  This class encapsulates all the logic and setup
 * for the game, including creation of state and view classes, listener creation,
 * timers, game start and game over logic, etc.
 * 
 * @author Peter Jensen (add your name if you make changes)
 * @version Spring 2022
 */
package game;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import javax.swing.Timer;

import javax.swing.SwingUtilities;

public class GameControl implements Runnable, ActionListener {
	// Fields

	private GameView view;
	private GameState state;
	private Timer timer;

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

		// Set up game objects.

		state = new GameState();
		view = new GameView(state);
		
		//Initialize a timer
		timer = new Timer(16, this);
		timer.start();
	}

	public void actionPerformed(ActionEvent e) {
		state.updateAll();
		view.repaint();
	}

}
