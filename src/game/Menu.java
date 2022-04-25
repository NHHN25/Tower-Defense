package game;

import java.awt.Color;
import java.awt.Graphics;

public class Menu implements Animatable {
	
	private GameControl control;
	private GameState state;

	
	public Menu (GameControl control, GameState state) {
//		super(state, x , y);
		this.control = control;
		this.state = state;
	}
	
	public void update(double timeElapsed) {

	}

	public void draw(Graphics g, GameView view) {
		g.clearRect(600, 0, 100, 600);
		
		//Draw the menu rectangle
		g.setColor(Color.WHITE);
		g.drawRect(600, 0, 100, 600);
		
		//Draw word Menu
		g.setColor(Color.BLACK);
		g.drawString("Menu", 640, 50);
		
		//Draw Lives
		g.setColor(Color.BLACK);
		g.drawString("Lives: " + state.getLives(), 610, 100);
		
		//Draw Money
		g.setColor(Color.BLACK);
		g.drawString("Money left: " + state.getMoney(), 610, 125);
		
		
		//Draw Money
		g.setColor(Color.BLACK);
		g.drawString("Cost: 50", 630, 250);
		g.drawString("Cost: 100", 630, 350);
		



	}
		

}
