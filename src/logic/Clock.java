package logic;

import java.awt.Graphics2D;
import config.ConfigurableOption;

public class Clock extends RenderableObject implements ICollectible {

	public Clock(int x, int y) {
		this.x = x;
		this.y = y;
		this.z = (int) Math.random();
		movingDelayCounter = ConfigurableOption.MOVING_DELAY;
		destroyed = false;
	}

	public void move() {
		movingDelayCounter--;
		if (movingDelayCounter > 0)
			return;
		x--;
		movingDelayCounter = ConfigurableOption.MOVING_DELAY;
	}

	public void collect(Player player) {
		destroyed = true;
		player.setTime(player.getTime() + ConfigurableOption.CLOCK_TIME);
	}

	public void render(Graphics2D g2) {

	}

}
