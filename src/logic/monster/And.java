package logic.monster;

import java.util.ArrayList;

import config.ConfigurableOption;
import logic.IRenderable;
import logic.Rectangle;
import logic.bullet.GndBullet;
import ui.DrawingUtility;

public class And extends Monster {

	public And(int x, int y) {
		super(x, y, ConfigurableOption.AND_LIFE, ConfigurableOption.AND_SCORE);
		img = DrawingUtility.and;
	}

	public void shoot(ArrayList<IRenderable> list) {
		firingDelayCounter--;
		if (firingDelayCounter > 0)
			return;
		int w = DrawingUtility.gndBullet.getWidth();
		int h = DrawingUtility.gndBullet.getHeight() / 2;
		list.add(new GndBullet(x - w, y + img.getHeight() / 2 - h));
		firingDelayCounter = ConfigurableOption.FIRING_DELAY;
	}

	public Rectangle rectify() {
		return new Rectangle(x + 13, y, img.getWidth() - 26, img.getHeight());
	}
}
