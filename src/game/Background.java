package game;

import java.awt.Graphics;

public class Background implements Animatable {
	
//	public Background(GameState state, int x, int y) {
//		super(state, x, y);
//	}
	
	public void update(double timeElapsed) {

	}

	public void draw(Graphics g, GameView view) {
		view.drawCenteredImage(g, "resources/path_2.jpg", 300, 300);
	}

}
