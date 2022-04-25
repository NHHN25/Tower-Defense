package game;

import java.awt.Graphics;
import java.awt.Point;

public class TowerBeer extends Tower 
{
	private double timeSinceLastShot;

	public TowerBeer(GameState state, GameControl control, int x, int y) {
		super(state, control, x, y);
		timeSinceLastShot = 0;
		// TODO Auto-generated constructor stub
	}

	@Override
	public void update(double timeElapsed) {
		// TODO Auto-generated method stub
		timeSinceLastShot += timeElapsed;
		
		if (timeSinceLastShot < 1.0)
			return;
		
		//	
		EnemySCargo e = state.findNearestEnemySCargo(x, y);
		Point p = new Point(x,y);
		
		int deltaX = e.getLocation().x - x;
		int deltaY = e.getLocation().y - y;
		
		if(e.getLocation().distance(p) < 110)
			state.addAnimatable(new FXPuddle(state, x, y, deltaX, deltaY));
			timeSinceLastShot = 0;
	}
		

	@Override
	public void draw(Graphics g, GameView view) {
		// TODO Auto-generated method stub
		view.drawCenteredImage(g, "resources/beer.png", x, y);
	}
	

}
