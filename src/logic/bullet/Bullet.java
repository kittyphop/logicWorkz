package logic.bullet;

import config.ConfigurableOption;
import logic.Rectangle;
import logic.RenderableObject;

public abstract class Bullet extends RenderableObject {

	protected int power;

	public Bullet(int x, int y) {
		super(x, y);
		movingDelayCounter = ConfigurableOption.BULLET_MOVING_DELAY;
	}

	public int getPower() {
		return power;
	}

	public abstract void move();

	public abstract Rectangle rectify();

}
