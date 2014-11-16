package logic;

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
		movingDelayCounter = ConfigurableOption.MOVING_DELAY;
	}

	public Rectangle rectify() {
		return new Rectangle(x, y, img.getWidth(), img.getHeight());
	}

	public boolean isOverlap(Player player) {
		return rectify().isOverlap(player.getCurrentGun().rectify());
	}

	public void hit(Player player) {
		player.setTime(player.getTime() - power);
	}

}
