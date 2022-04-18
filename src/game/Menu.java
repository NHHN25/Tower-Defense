package game;

import java.awt.Color;
import java.awt.Graphics;

public class Menu implements Animatable {
	
	private GameControl control;
	private GameState state;
	
	public Menu (GameControl control, GameState state) {
		this.control = control;
		this.state = state;
	}
	
	public void update(double timeElapsed) {
		state.addAnimatable(new TowerSaltMenu(state, 650, 200));

	}

	public void draw(Graphics g, GameView view) {
		g.setColor(Color.BLACK);
		g.drawString("Menu", 625, 50);
		
		g.setColor(Color.BLACK);
		g.drawString("Lives: " + state.getLives(), 625, 100);

	}
		

}
