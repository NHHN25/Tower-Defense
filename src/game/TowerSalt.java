package game;

import java.awt.Graphics;

public class TowerSalt extends Tower 
{

	public TowerSalt(GameState state, int x, int y) {
		super(state, x, y);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void update(double timeElapsed) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void draw(Graphics g, GameView view) {
		// TODO Auto-generated method stub
		view.drawCenteredImage(g, "resources/salt.png", x, y);
	}
	

}
