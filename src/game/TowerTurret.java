package game;

import java.awt.Graphics;
import java.awt.Point;

public class TowerTurret extends Tower {
	private double timeSinceLastShot;

	public TowerTurret(GameState state, GameControl control, int x, int y) {
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
		EnemySCargo scargo = state.findNearestEnemySCargo(x, y);
		EnemySnail snail = state.findNearestEnemySnail(x, y);
		Point p = new Point(x, y);

		try {

			if (scargo instanceof EnemySCargo) {
				int deltaX = scargo.getLocation().x - x;
				int deltaY = scargo.getLocation().y - y;
				if (scargo.getLocation().distance(p) < 200)
					state.addAnimatable(new FXRocket(state, x, y, deltaX, deltaY));
				timeSinceLastShot = 0.65;
			} else {
				int deltaX = snail.getLocation().x - x;
				int deltaY = snail.getLocation().y - y;
				if (snail.getLocation().distance(p) < 200)
					state.addAnimatable(new FXRocket(state, x, y, deltaX, deltaY));
				timeSinceLastShot = 0.65;
			}

		} catch (NullPointerException n) {
			System.out.println("No SCargo found");
		}

	}

	@Override
	public void draw(Graphics g, GameView view) {
		// TODO Auto-generated method stub
		view.drawCenteredImage(g, "resources/turret.png", x, y);
	}

}
