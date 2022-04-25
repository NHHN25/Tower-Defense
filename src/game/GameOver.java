package game;

import java.awt.Graphics;

public class GameOver extends FX {
	
	public GameOver (GameState state, int x, int y) {
		super(state, x ,y);
	}
	public void update(double timeElapsed) {

	}

	public void draw(Graphics g, GameView view) {
		view.drawCenteredImage(g, "resources/game_over.png", 300, 300);
	}

}
