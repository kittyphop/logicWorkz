package logic;

import java.awt.Graphics2D;

import config.ConfigurableOption;

public class VddBullet extends Bullet {

	public VddBullet(int x, int y) {
		super(x, y);
		this.z = (int) Math.random();
		movingDelayCounter = ConfigurableOption.MOVING_DELAY;
		destroyed = false;
	}

	public void move() {
		movingDelayCounter--;
		if (movingDelayCounter > 0)
			return;
		x++;
	}

	public void hit(Monster monster) {

	}

	public void render(Graphics2D g2) {

	}

}
