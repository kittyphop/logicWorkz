package logic.collectible;

import audio.AudioUtility;
import logic.Player;
import logic.Rectangle;
import logic.RenderableObject;
import ui.DrawingUtility;
import config.ConfigurableOption;

public class Clock extends RenderableObject implements ICollectible {

	private boolean up;

	public Clock(int x, int y) {
		super(x, y);
		img = DrawingUtility.clock;
	}

	public void move() {
		movingDelayCounter--;
		if (movingDelayCounter > 0)
			return;
		movingDelayCounter = movingDelay;
		x--;
		if (up) {
			if (y > 10)
				y--;
			else
				up = !up;
		} else {
			if (y + img.getHeight() + 10 < ConfigurableOption.PLAYPANEL_HEIGHT)
				y++;
			else
				up = !up;
		}
	}

	public void collect(Player player) {
		new Thread(new AudioUtility(AudioUtility.COLLECT_CLOCK)).start();
		destroyed = true;
		player.setTime(player.getTime() + ConfigurableOption.CLOCK_TIME);
		player.setScore(player.getScore() + ConfigurableOption.CLOCK_SCORE);
	}

	public Rectangle rectify() {
		return new Rectangle(x, y, img.getWidth() - 15, img.getHeight());
	}

}
