package game;

import java.awt.Graphics;

public class TowerBeerMenu extends Tower 
{

	public TowerBeerMenu(GameState state, GameControl control, int x, int y) {
		super(state, control, x, y);
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
			if (dX < 40 && dY < 40) {
				state.consumeClick();
				state.addAnimatable(new TowerBeerMoving(state, control, state.getMouseX(), state.getMouseY()));
			}
		}
	}

	@Override
	public void draw(Graphics g, GameView view) {
		// TODO Auto-generated method stub
		view.drawCenteredImage(g, "resources/beer.png", x, y);
	}
	

}
