package logic.monster;

import java.util.ArrayList;
import config.ConfigurableOption;
import logic.IRenderable;
import logic.Rectangle;
import logic.bullet.GndBullet;
import ui.DrawingUtility;

public class IC74163 extends Monster {

	private boolean up = false;

	public IC74163(int x, int y) {
		super(x, y, ConfigurableOption.IC74163_LIFE,
				ConfigurableOption.IC74163_SCORE);
		img = DrawingUtility.ic74163;
	}

	public void shoot(ArrayList<IRenderable> list) {
		firingDelayCounter--;
		if (firingDelayCounter > 0)
			return;
		int w = DrawingUtility.gndBullet.getWidth();
		int h = DrawingUtility.gndBullet.getHeight() / 2;
		// y' = 8, 22, 42, 62, 75, 88, 102, 122, 142
		list.add(new GndBullet(x - w, y + 8 - h));
		list.add(new GndBullet(x - w, y + 22 - h));
		list.add(new GndBullet(x - w, y + 42 - h));
		list.add(new GndBullet(x - w, y + 62 - h));
		list.add(new GndBullet(x - w, y + 75 - h));
		list.add(new GndBullet(x - w, y + 88 - h));
		list.add(new GndBullet(x - w, y + 102 - h));
		list.add(new GndBullet(x - w, y + 122 - h));
		list.add(new GndBullet(x - w, y + 142 - h));
		firingDelayCounter = ConfigurableOption.FIRING_DELAY;
	}

	public Rectangle rectify() {
		return new Rectangle(x + 20, y, img.getWidth() - 34, img.getHeight());
	}

	public void move() {
		movingDelayCounter--;
		if (movingDelayCounter > 0)
			return;
		movingDelayCounter = ConfigurableOption.MOVING_DELAY;
		if (x > 2 * ConfigurableOption.PLAYPANEL_WIDTH / 3)
			x--;
		else {
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
	}

}
