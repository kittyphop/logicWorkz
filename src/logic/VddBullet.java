package logic;

import ui.DrawingUtility;
import config.ConfigurableOption;

public class VddBullet extends Bullet {

	public VddBullet(int x, int y, boolean special) {
		super(x, y);
		if (special)
			power = ConfigurableOption.VDD_SPECIAL_POWER;
		else
			power = ConfigurableOption.VDD_POWER;
		img = DrawingUtility.vddBullet;
	}

	public void move() {
		movingDelayCounter--;
		if (movingDelayCounter > 0)
			return;
		x++;
		movingDelayCounter = ConfigurableOption.MOVING_DELAY;
	}

	public Rectangle rectify() {
		return new Rectangle(x, y, img.getWidth(), img.getHeight());
	}

	public boolean isOverlap(Monster monster) {
		return rectify().isOverlap(monster.rectify());
	}

	public void hit(Monster monster) {

	}

}
