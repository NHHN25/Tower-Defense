package game;

import java.awt.Graphics;

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
		percentageTraveled += 0.01;
		if (percentageTraveled > 1.0)
			percentageTraveled = 0.0;
		
		//Split Snail into half
		updateCount++;
		if (updateCount == 60) {
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
		int intX = (int) this.getLocation().getX();
		int intY = (int) this.getLocation().getY();
		
		//Draw the snail
		view.drawCenteredImage(g, "resources/snail.png", intX, intY);
	}
	

}
