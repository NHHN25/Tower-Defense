/**
 * 
 */
package game;

import java.awt.Graphics;
import java.awt.Point;

/**
 * @author nguye
 *
 */
public class FXRocket extends FX implements Animatable {

	private int deltaX, deltaY;
	private double age;
	
	public FXRocket(GameState state, int x, int y, int deltaX, int deltaY) {
		
		super(state,x,y);
		this.deltaX = deltaX;
		this.deltaY = deltaY;
	}

	@Override
	public void update(double timeElapsed) {
		
		//Remove salt effect if it stayed too long on the screen
		age += timeElapsed;
		if (age > 0.75)
		{
			state.removeAnimatable(this);
			return;
		}
		
		//Shoot the salt towards EnemySnail object
		x = x + deltaX*6 * timeElapsed;
		y = y + deltaY*6 * timeElapsed;
		
		Point p = new Point ((int)x, (int)y);
		
		//Retrieve location of the snail/Scargo
		EnemySnail snail = state.findNearestEnemySnail(p.x, p.y);
		EnemySCargo scargo = state.findNearestEnemySCargo(p.x, p.y);
		
		//If EnemySnail is within a certain distance to the rocket, shoot salt, remove it from the list and increment money
		try {
			if (snail.getLocation().distance(p) < 30)
			{
				state.removeAnimatable(snail);
				state.removeAnimatable(this);
				state.addAnimatable(new FXSplat(state, (int) x, (int) y));
				state.changeMoney(+10);
			}
			
			if (scargo.getLocation().distance(p) < 30) {
				state.removeAnimatable(scargo);
				state.removeAnimatable(this);
				state.addAnimatable(new FXCrash(state, (int) x, (int) y));
				state.changeMoney(+10);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println("No SCargo/Snail found");
		}
	}

	@Override
	public void draw(Graphics g, GameView view) {
		
		view.drawCenteredImage(g, "resources/rocket.png", (int) x, (int) y);
	}

}
