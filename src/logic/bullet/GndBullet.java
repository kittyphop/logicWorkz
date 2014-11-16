package logic.bullet;

import logic.Rectangle;
import ui.DrawingUtility;
import config.ConfigurableOption;

public class GndBullet extends Bullet {

	public GndBullet(int x, int y) {
		super(x, y);
		power = ConfigurableOption.GND_POWER;
		img = DrawingUtility.gndBullet;
	}

	public void move() {
		movingDelayCounter--;
		if (movingDelayCounter > 0)
			return;
		x--;
		movingDelayCounter = ConfigurableOption.BULLET_MOVING_DELAY;
	}

	public Rectangle rectify() {
		return new Rectangle(x, y, img.getWidth(), img.getHeight());
	}

}
