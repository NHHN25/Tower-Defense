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
public class FXSalt extends FX implements Animatable {

	private int deltaX, deltaY;
	private double age;
	
	public FXSalt(GameState state, int x, int y, int deltaX, int deltaY) {
		
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
		
		x = x + deltaX*6 * timeElapsed;
		y = y + deltaX*6 * timeElapsed;
		
		Point p = new Point ((int)x, (int)y);
		
		EnemySnail e = state.findNearestEnemySnail(p.x, p.y);
		
		if (e.getLocation().distance(p) < 30)
		{
			state.removeAnimatable(e);
			state.removeAnimatable(this);
			state.addAnimatable(new FXSplat(state, (int) x, (int) y));
			state.changeMoney(+10);
		}
	}

	@Override
	public void draw(Graphics g, GameView view) {
		
		view.drawCenteredImage(g, "resources/salt_crystals.png", (int) x, (int) y);
	}

}
