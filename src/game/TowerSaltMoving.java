package game;

import java.awt.Graphics;

public class TowerSaltMoving extends Tower 
{

	public TowerSaltMoving(GameState state, int x, int y) {
		super(state, x, y);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void update(double timeElapsed) {
		// TODO Auto-generated method stub
		x = state.getMouseX();
		y = state.getMouseY();
		
		if (state.getMouseClicked()) 
		{
			state.consumeClick();
			state.addAnimatable(new TowerSalt(state, x, y));
			state.removeAnimatable(this);
		}
	}

	@Override
	public void draw(Graphics g, GameView view) {
		// TODO Auto-generated method stub
		view.drawCenteredImage(g, "resources/salt.png", x, y);
	}
	

}
