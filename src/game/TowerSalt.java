package game;

import java.awt.Graphics;
import java.awt.Point;

public class TowerSalt extends Tower 
{
	private double timeSinceLastShot;
	public TowerSalt(GameState state, GameControl control, int x, int y) {
		super(state, control, x, y);
		timeSinceLastShot = 0;
		// TODO Auto-generated constructor stub
	}

	@Override
	public void update(double timeElapsed) {
		
		timeSinceLastShot += timeElapsed;
		
		if (timeSinceLastShot < 1.0)
			return;
		
		//	
		EnemySnail e = state.findNearestEnemySnail(x, y);
		Point p = new Point(x,y);
		
		try {
		int deltaX = e.getLocation().x - x;
		int deltaY = e.getLocation().y - y;
		
		if(e.getLocation().distance(p) < 90)
			state.addAnimatable(new FXSalt(state, x, y, deltaX, deltaY));
			timeSinceLastShot = 0;
		} catch (NullPointerException n) {
			System.out.println("No snail found.");
		}

	}

	@Override
	public void draw(Graphics g, GameView view) {
		// TODO Auto-generated method stub
		view.drawCenteredImage(g, "resources/salt.png", x, y);
	}
	

}
