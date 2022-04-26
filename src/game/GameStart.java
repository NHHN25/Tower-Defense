package game;

import java.awt.Graphics;

public class GameStart extends FX {
	
	public GameStart (GameState state, int x, int y) {
		super(state, x ,y);
	}
	public void update(double timeElapsed) {

	}

	public void draw(Graphics g, GameView view) {
		view.drawCenteredImage(g, "resources/start.png", 300, 300);
	}

}
