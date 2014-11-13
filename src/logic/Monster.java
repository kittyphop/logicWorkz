package logic;

import java.awt.Graphics2D;
import config.ConfigurableOption;

public class Monster extends RenderableObject {

	protected int life, reward, firingDelayCounter;

	public Monster(int x, int y, int life) {
		this.x = x;
		this.y = y;
		this.z = (int) Math.random();
		movingDelayCounter = ConfigurableOption.MOVING_DELAY;
		destroyed = false;
		this.life = life;
	}

	public void move() {
		movingDelayCounter--;
		if (movingDelayCounter > 0)
			return;
		x--;
		movingDelayCounter = ConfigurableOption.MOVING_DELAY;
	}

	public void render(Graphics2D g2) {

	}

}
