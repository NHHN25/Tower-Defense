package game;

import java.awt.Graphics;

public class TowerSaltMoving extends Tower {

	
	public TowerSaltMoving(GameState state, GameControl control, int x, int y) {
		super(state, control, x, y);
		this.control = control;
		

		// TODO Auto-generated constructor stub
	}

	@Override
	public void update(double timeElapsed) {
		// TODO Auto-generated method stub
		this.x = state.getMouseX();
		this.y = state.getMouseY();
		
		//Check mouse clicked or not
		if (state.getMouseClicked()) {
			
			if(control.loadImage("resources/path_mask.jpg").getRGB(x, y) == 0)
			return;
			
			//Check if player have enough money or not
			if (state.getMoney() >= 50 && x < 600) {
				
				//Consume the click, add the salt tower to the ArrayList, remove the moving tower and deduct money
				state.consumeClick();
				state.addAnimatable(new TowerSalt(state, control, x, y));
				state.removeAnimatable(this);
				state.changeMoney(-50);
			}

		}
	}

	@Override
	public void draw(Graphics g, GameView view) {
		// TODO Auto-generated method stub
		view.drawCenteredImage(g, "resources/salt.png", x, y);
	}

}
