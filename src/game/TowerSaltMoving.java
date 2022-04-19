package game;

import java.awt.Graphics;

public class TowerSaltMoving extends Tower {

	public TowerSaltMoving(GameState state, int x, int y) {
		super(state, x, y);

		// TODO Auto-generated constructor stub
	}

	@Override
	public void update(double timeElapsed) {
		// TODO Auto-generated method stub
		this.x = state.getMouseX();
		this.y = state.getMouseY();

		if (state.getMouseClicked()) {
			if (state.getMoney() >= 50) {
				state.consumeClick();
				state.addAnimatable(new TowerSalt(state, x, y));
				state.removeAnimatable(this);
				state.changeMoney(-50);
			}

		}
	}

	@Override
	public void draw(Graphics g, GameView view) {
		// TODO Auto-generated method stub
		view.drawCenteredImage(g, "resources/salt.png", x, y);
	}

}
