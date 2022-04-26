package game;

import java.awt.Graphics;
import java.awt.Point;

public class EnemySCargo extends Enemy {

	public EnemySCargo(GameState state) {
		super(state);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void update(double timeElapsed) {
		// TODO Auto-generated method stub
		percentageTraveled += 0.055 * timeElapsed;
		if (percentageTraveled > 1.0) {
			// If reach the end, remove from list and deduct life
			state.removeAnimatable(this);
			state.changeLives(-1);
		}

	}

	@Override
	public void draw(Graphics g, GameView view) {
		// TODO Auto-generated method stub

		// Calculate the location of the snail on the path according to the percentage
		// traveled
		try {
			Point p = getLocation();

			// Draw the SCargo
			view.drawCenteredImage(g, "resources/scargo.png", p.x, p.y);

		} catch (NullPointerException e) {
			System.out.println("No SCargo found");
		}

	}

}
