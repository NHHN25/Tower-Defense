package game;

import java.awt.Graphics;

public class TowerSaltMenu extends Tower 
{

	public TowerSaltMenu(GameState state, int x, int y) {
		super(state, x, y);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void update(double timeElapsed) {
		// TODO Auto-generated method stub
		//Gather change in x and y of the mouse after click
		if (state.getMouseClicked()) {
			int dX = Math.abs(x - state.getMouseX());
			int dY = Math.abs(y - state.getMouseY());
			
			//Consume click and attach salt tower to mouse
			if (dX < 20 && dY < 20) {
				state.consumeClick();
				state.addAnimatable(new TowerSaltMoving(state, state.getMouseX(), state.getMouseY()));
			}
		}
	}

	@Override
	public void draw(Graphics g, GameView view) {
		// TODO Auto-generated method stub
		view.drawCenteredImage(g, "resources/salt.png", x, y);
	}
	

}
