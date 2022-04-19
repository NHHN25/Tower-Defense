package game;

import java.awt.Graphics;

public class Background extends Effect {
	
	public void update(double timeElapsed) {

	}

	public void draw(Graphics g, GameView view) {
		view.drawCenteredImage(g, "resources/path_2.jpg", 300, 300);
	}

}
