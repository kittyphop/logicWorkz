package logic;

import java.awt.Graphics2D;
import config.ConfigurableOption;

public class Gun extends RenderableObject {

	public Gun(int x, int y) {
		this.x = x;
		this.y = y;
		this.z = Integer.MAX_VALUE;
		movingDelayCounter = ConfigurableOption.MOVING_DELAY;
		destroyed = false;
	}

	public void shoot() {
		// new bullet
	}

	public void move() {
		// check move direction
	}

	public void render(Graphics2D g2) {
	}

}
