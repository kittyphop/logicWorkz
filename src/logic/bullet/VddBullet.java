package logic.bullet;

import logic.Rectangle;
import ui.DrawingUtility;
import config.ConfigurableOption;

public class VddBullet extends Bullet {

	private boolean special;

	public VddBullet(int x, int y, boolean special) {
		super(x, y);
		this.special = special;
		if (special) {
			img = DrawingUtility.vddBulletSpecial;
			power = ConfigurableOption.VDD_SPECIAL_POWER;
		} else {
			power = ConfigurableOption.VDD_POWER;
			img = DrawingUtility.vddBullet;
		}
	}

	public boolean isSpecial() {
		return special;
	}

	public void move() {
		movingDelayCounter--;
		if (movingDelayCounter > 0)
			return;
		x++;
		movingDelayCounter = ConfigurableOption.BULLET_MOVING_DELAY;
	}

	public Rectangle rectify() {
		return new Rectangle(x, y, img.getWidth(), img.getHeight());
	}

}
