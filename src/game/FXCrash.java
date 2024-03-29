package game;

import java.awt.Graphics;

public class FXCrash extends FX implements Animatable {
	
	private double age;
	
	public FXCrash(GameState state, int x, int y) {
		super(state, x, y);
	}

	@Override
	public void update(double timeElapsed) {
		age += timeElapsed;
		
		if (age > 0.5)
			state.removeAnimatable(this);

	}

	@Override
	public void draw(Graphics g, GameView view) {
		view.drawCenteredAndSize(g, "resources/crash.png", (int)x, (int)y , 60, 60);

	}

}
