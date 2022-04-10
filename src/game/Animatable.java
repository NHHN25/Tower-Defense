package game;

import java.awt.Graphics;

/**
 * @author Nam Nguyen and Minh Pham April 10th, 2022
 * This interface implements methods for Animatable objects
 *
 */
public interface Animatable {
	public void update(double timeElapsed);

	public void draw(Graphics g, GameView view);

}
