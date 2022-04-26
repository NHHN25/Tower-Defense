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
		
		//Retrieve location of the snail
		EnemySnail e = state.findNearestEnemySnail(p.x, p.y);
		
		//If EnemySnail is within a certain distance to the salt, remove it from the list and increment money
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
