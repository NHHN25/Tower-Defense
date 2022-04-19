/** Header comment needed! */
package game;

import java.awt.Color;
import java.awt.Graphics;
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

public class GameState implements MouseListener, MouseMotionListener{
	// Fields
	//Add lifeCounter and boolean isGameOver + getter/setter for counters + List of Animatables (helper methods addAnimatable + removeAnimatable)
	private Path path;
	private GameControl control;
	private int lives;
	
	private List<Animatable> animatables;
	private List<Animatable> toAdd; //Animatables to be added after updating animatables
	private List<Animatable> toRemove; //Animatables to be remove after updating animatables
	
	private int frameCount, mouseX, mouseY;
	private boolean mouseClicked;
	/* Contracts needed! */

	// Constructor

	public GameState(GameControl control) {
		System.out.println("GameState constructor");
		
		//Initialize lives
		lives = 20;
		
		//Build animatable list
		animatables = new ArrayList<Animatable>();
		toAdd = new ArrayList<Animatable>();
		toRemove = new ArrayList<Animatable>();


		
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


	}

	// Function to return a path object

	public Path getPath() {
		return path;
	}

	// Function to draw everything

	public void drawAll(Graphics g, GameView view) {
		// Draw the background

		for (Animatable a : animatables)
			a.draw(g, view);

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
		
		//Loop through all animatables
		for (Animatable a : animatables) {
			a.update(elapsedTime);
		}
		

		//Add and remove animatables from the main animatable list
		animatables.removeAll(toRemove);
		animatables.addAll(toAdd);
		
		
		//Remove all animatables from placeholder list
		toAdd.clear();
		toRemove.clear();
		
		
		
		//Clear mouse click
		mouseClicked = false;
		
		//Generate enemies systematically
		if (frameCount == 10)
			animatables.add(new EnemySnail(this));
		
		
		if (frameCount == 20)
			animatables.add(new EnemySCargo(this));
		
		if (frameCount == 60)
			frameCount = 0;
		
		frameCount++;
	}
	
	/**
	 * Get the remaining lives
	 * @return lives
	 */
	public int getLives() {
		return lives;
	}
	
	/**
	 * Modifies the current lives  
	 * @param lives
	 */
	public void changeLives (int amount) {
		lives = lives + amount;
	}
	
	/**
	 * Get X coordinate of mouse
	 * @return int mouseX
	 */
	public int getMouseX() {
		return mouseX;
	}
	
	/**
	 * Get Y coordinate of mouse
	 * @return int mouseY
	 */
	public int getMouseY() {
		return mouseY;
	}
	
	/**
	 * Get status of mouse click
	 * @return boolean mouseClicked
	 */
	public boolean getMouseClicked() {
		return mouseClicked;
	}
	
	/**
	 * Set mouseClicked to false
	 */
	public void consumeClick() {
		mouseClicked = false;
	}
	/**
	 * Add an animatable to the toAdd list
	 * @param animatable
	 */
	public void addAnimatable (Animatable a) {
		toAdd.add(a);
	}
	
	/**
	 * Add an animatable to the toAdd list
	 * @param animatable
	 */
	public void removeAnimatable (Animatable a) {
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
		mouseClicked = true;
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
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
}
