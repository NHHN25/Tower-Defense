package game;

import java.awt.Graphics;

public class EnemySCargo extends Enemy 
{

	public EnemySCargo(GameState state) {
		super(state);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void update(double timeElapsed) {
		// TODO Auto-generated method stub
		percentageTraveled += 0.01;
		if (percentageTraveled > 1.0)
			percentageTraveled = 0.0;
		

		
	}

	@Override
	public void draw(Graphics g, GameView view) {
		// TODO Auto-generated method stub
		
		//Calculate the location of the snail on the path according to the percentage traveled
		int intX = (int) this.getLocation().getX();
		int intY = (int) this.getLocation().getY();
		
		//Draw the snail
		view.drawCenteredImage(g, "resources/scargo.png", intX, intY);
	}
	

}
