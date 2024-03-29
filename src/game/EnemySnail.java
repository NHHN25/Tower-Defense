package game;

import java.awt.Graphics;
import java.awt.Point;

public class EnemySnail extends Enemy 
{
	private int updateCount;

	public EnemySnail(GameState state) {
		super(state);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void update(double timeElapsed) {
		// TODO Auto-generated method stub
		percentageTraveled += 0.050 * timeElapsed;
		if (percentageTraveled >= 1.0) {
			
			//If reach the end, remove from list and deduct life
			state.removeAnimatable(this);
			state.changeLives(-1);
		}
		
		//Split Snail into half
		updateCount++;
		if (updateCount == 30) {
			EnemySnail s = new EnemySnail(state);
			s.percentageTraveled = percentageTraveled - 0.01;
			s.updateCount = 60;
			state.addAnimatable(s);
		}

		
	}

	@Override
	public void draw(Graphics g, GameView view) {
		// TODO Auto-generated method stub
		
		//Calculate the location of the snail on the path according to the percentage traveled
		Point p = getLocation();
		
		//Draw the snail
		view.drawCenteredImage(g, "resources/snail.png", p.x, p.y);
	}
	

}
