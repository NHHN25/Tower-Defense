package game;

import java.awt.Color;
import java.awt.Graphics;

public class Menu extends Effect {
	
	private GameControl control;
	private GameState state;

	
	public Menu (GameControl control, GameState state) {
		this.control = control;
		this.state = state;
	}
	
	public void update(double timeElapsed) {
	}

	public void draw(Graphics g, GameView view) {
		g.setColor(Color.BLACK);
		g.drawString("Menu", 640, 50);
		
		//Draw Lives
		g.setColor(Color.BLACK);
		g.drawString("Lives: " + state.getLives(), 610, 100);
		
		//Draw Money
		g.setColor(Color.BLACK);
		g.drawString("Money left: " + state.getMoney(), 610, 125);
		
		//Draw Salt tower on menu
		state.addAnimatable(new TowerSaltMenu(state, 650, 200));
		
		//Draw Money
		g.setColor(Color.BLACK);
		g.drawString("Cost: 50", 630, 250);
		



	}
		

}
