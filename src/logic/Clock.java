package logic;

import ui.DrawingUtility;
import config.ConfigurableOption;

public class Clock extends RenderableObject implements ICollectible {

	public Clock(int x, int y) {
		super(x, y);
		img = DrawingUtility.clock;
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

	public Rectangle rectify() {
		return new Rectangle(x, y, img.getWidth() - 15, img.getHeight());
	}

	public boolean isOverlap(Player player) {
		return rectify().isOverlap(player.getCurrentGun().rectify());
	}

}
