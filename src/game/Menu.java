package game;

import java.awt.Color;
import java.awt.Graphics;

public class Menu implements Animatable {
	
	public void update(double timeElapsed) {

	}

	public void draw(Graphics g, GameView view) {
		g.setColor(Color.BLACK);
		g.drawString("Menu", 625, 200);

	}
		

}
