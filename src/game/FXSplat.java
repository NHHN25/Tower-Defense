package game;

import java.awt.Graphics;

public class FXSplat extends FX implements Animatable {
	
	private double age;
	
	public FXSplat(GameState state, int x, int y) {
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
		view.drawCenteredAndSize(g, "resources/splat.png", (int)x, (int)y , (int)(200* age), (int)(200* age));

	}

}
