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
public class FXPuddle extends FX implements Animatable {

	private int deltaX, deltaY;
	private double age;
	
	public FXPuddle(GameState state, int x, int y, int deltaX, int deltaY) {
		
		super(state,x,y);
		this.deltaX = deltaX;
		this.deltaY = deltaY;
	}

	@Override
	public void update(double timeElapsed) {
		
		//Remove puddle effect if it stayed too long on the screen

		age += timeElapsed;
		if (age > 0.75)
		{
			state.removeAnimatable(this);
			return;
		}
		
		//Shoot the puddle towards EnemySCargo object
		x = x + deltaX*6 * timeElapsed;
		y = y + deltaY*6 * timeElapsed;
		
		Point p = new Point ((int)x, (int)y);
		
		//Find the Point of the nearest EnemySCargo
		EnemySCargo e = state.findNearestEnemySCargo(p.x, p.y);
		
		//If EnemySCargo object is in a certain distance to the puddle, remove from SCargo from list, increment money
		if (e.getLocation().distance(p) < 30)
		{
			state.removeAnimatable(e);
			state.removeAnimatable(this);
			state.addAnimatable(new FXCrash(state, (int) x, (int) y));
			state.changeMoney(+20);
		}
	}

	@Override
	public void draw(Graphics g, GameView view) {
		
		view.drawCenteredAndSize(g, "resources/puddle.png", (int) x, (int) y, 50, 50);
	}

}
