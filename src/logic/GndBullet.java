package logic;

import java.awt.Graphics2D;

import config.ConfigurableOption;

public class GndBullet extends Bullet {

	public GndBullet(int x, int y) {
		super(x, y);
		this.z = (int) Math.random();
		movingDelayCounter = ConfigurableOption.MOVING_DELAY;
		destroyed = false;
	}

	public void move() {
		movingDelayCounter--;
		if (movingDelayCounter > 0)
			return;
		x--;
	}

	public void hit(Player player) {
		player.setTime(player.getTime() - power);
	}

	public void render(Graphics2D g2) {

	}

}
