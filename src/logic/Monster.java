package logic;

import config.ConfigurableOption;

public abstract class Monster extends RenderableObject {

	protected int life, reward, firingDelayCounter;

	public Monster(int x, int y, int life) {
		super(x, y);
		this.life = life;
	}

	public void move() {
		movingDelayCounter--;
		if (movingDelayCounter > 0)
			return;
		x--;
		movingDelayCounter = ConfigurableOption.MOVING_DELAY;
	}

	public abstract Rectangle rectify();

	public abstract boolean isOverlap(Player player);

}
