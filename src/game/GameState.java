/** Header comment needed! */
package game;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Timer;

public class GameState implements MouseListener, MouseMotionListener {
	// Fields
	// Add lifeCounter and boolean isGameOver + getter/setter for counters + List of
	// Animatables (helper methods addAnimatable + removeAnimatable)
	private Path path;
	private GameControl control;
	private int lives;

	private List<Animatable> animatables;
	private List<Animatable> toAdd; // Animatables to be added after updating animatables
	private List<Animatable> toRemove; // Animatables to be remove after updating animatables

	private int frameCount, mouseX, mouseY, money;
	private boolean mouseClicked, isGameOver;
	/* Contracts needed! */

	// Constructor

	public GameState(GameControl control) {
		System.out.println("GameState constructor");

		// Initialize lives and money
		money = 200;
		lives = 10;

		// Build animatable list
		animatables = new ArrayList<Animatable>();
		toAdd = new ArrayList<Animatable>();
		toRemove = new ArrayList<Animatable>();

		this.control = control;

		// Build our path
		Scanner sc;
		try {
			sc = new Scanner(new File("src/resources/path_2.txt"));
			path = new Path(sc);
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			System.out.println(e1);
		}

	}

	// Function to return a path object

	public Path getPath() {
		return path;
	}

	// Function to draw everything

	public void drawAll(Graphics g, GameView view) {
		// Draw all animatables

		for (Animatable a : animatables)
			a.draw(g, view);

		// Draw the path
//
//		path.paint(g);

//		// Draw the ball
//		int intX = (int) path.locatePosition(percentageTraveled).getX();
//		int intY = (int) path.locatePosition(percentageTraveled).getY();
//		g.setColor(Color.BLUE);
//		g.fillRect(intX, intY, 20, 20);

	}

	/**
	 * This method updates every frame of the game. Generates enemy systematically
	 * depending on the amount of frames passed. Keeps track whether the game ended
	 * or not.
	 */
	public void updateAll(double elapsedTime) {

		// Loop through all animatables
		for (Animatable a : animatables) {
			// Stop the game if out of lives
			if (isGameOver) {
				animatables.add(new GameOver(this, 300, 300));
				break;
			}

			else
				a.update(elapsedTime);
		}

		// Add and remove animatables from the main animatable list
		animatables.removeAll(toRemove);
		animatables.addAll(toAdd);

		// Remove all animatables from placeholder list
		toAdd.clear();
		toRemove.clear();

		// Clear mouse click
//		mouseClicked = false;

		// Generate enemies systematically
		if (frameCount == 10)
			animatables.add(new EnemySnail(this));

//		if (frameCount == 20)
//			animatables.add(new EnemySCargo(this));
//
		if (frameCount == 60)
			frameCount = 0;

		frameCount++;
		
		

		if (lives <= 0) {
			isGameOver = true;
		}

	}

	/**
	 * Get the remaining money
	 * 
	 * @return int money
	 */
	public int getMoney() {
		return money;
	}

	/**
	 * Modifies the current money
	 * 
	 * @param int amount to change
	 */
	public void changeMoney(int amount) {
		money = money + amount;
	}

	/**
	 * Get the remaining lives
	 * 
	 * @return lives
	 */
	public int getLives() {
		return lives;
	}

	/**
	 * Modifies the current lives
	 * 
	 * @param lives
	 */
	public void changeLives(int amount) {
		lives = lives + amount;
	}

	/**
	 * Get X coordinate of mouse
	 * 
	 * @return int mouseX
	 */
	public int getMouseX() {
		return mouseX;
	}

	/**
	 * Get Y coordinate of mouse
	 * 
	 * @return int mouseY
	 */
	public int getMouseY() {
		return mouseY;
	}

	/**
	 * Get status of mouse click
	 * 
	 * @return True or False
	 */
	public boolean getMouseClicked() {
		return mouseClicked;
	}

	/**
	 * Return whether the game is over or not
	 * 
	 * @return True or False
	 */
	public boolean getGameOver() {
		return isGameOver;
	}

	/**
	 * Set mouseClicked to false
	 */
	public void consumeClick() {
		mouseClicked = false;
	}

	/**
	 * Add an animatable to the toAdd list
	 * 
	 * @param animatable
	 */
	public void addAnimatable(Animatable a) {
		toAdd.add(a);
	}

	/**
	 * Add an animatable to the toAdd list
	 * 
	 * @param animatable
	 */
	public void removeAnimatable(Animatable a) {
		toRemove.add(a);
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseMoved(MouseEvent e) {
		// TODO Auto-generated method stub
		mouseX = e.getX();
		mouseY = e.getY();

	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		mouseClicked = true;
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	/**
	 * This method finds the EnemySnail nearest to the tower
	 * @param x
	 * @param y
	 * @return EnemySnail closest
	 */
	public EnemySnail findNearestEnemySnail(int x, int y) {
		//
		Point p = new Point(x, y);
		// Initialize closest enemy as null
		EnemySnail closest = null;

		// Loop to find closest enemy
		for (Animatable a : animatables) {
			if (a instanceof EnemySnail) {

				EnemySnail e = (EnemySnail) a;

				if (closest == null)
					closest = e;

				else if (p.distance(e.getLocation()) < p.distance(closest.getLocation()))
					closest = e;
			}
		}

		return closest;
	}
	
	/**
	 * This method finds the EnemySCargo nearest to the tower
	 * @param x
	 * @param y
	 * @return EnemySCargo closest
	 */
	public EnemySCargo findNearestEnemySCargo(int x, int y) {
		//
		Point p = new Point(x, y);
		// Initialize closest enemy as null
		EnemySCargo closest = null;

		// Loop to find closest enemy
		for (Animatable a : animatables) {
			if (a instanceof EnemySCargo) {

				EnemySCargo e = (EnemySCargo) a;

				if (closest == null)
					closest = e;

				else if (p.distance(e.getLocation()) < p.distance(closest.getLocation()))
					closest = e;
			}
		}

		return closest;
	}
}
