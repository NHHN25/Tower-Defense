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
		
		age += timeElapsed;
		if (age > 0.75)
		{
			state.removeAnimatable(this);
			return;
		}
		
		x = x + deltaX*8 * timeElapsed;
		y = y + deltaX*8 * timeElapsed;
		
		Point p = new Point ((int)x, (int)y);
		
		EnemySCargo e = state.findNearestEnemySCargo(p.x, p.y);
		
		if (e.getLocation().distance(p) < 40)
		{
			state.removeAnimatable(e);
			state.removeAnimatable(this);
			state.addAnimatable(new FXCrash(state, (int) x, (int) y));
			state.changeMoney(+20);
		}
	}

	@Override
	public void draw(Graphics g, GameView view) {
		
		view.drawCenteredAndSize(g, "resources/puddle.png", (int) x, (int) y, 75, 75);
	}

}
