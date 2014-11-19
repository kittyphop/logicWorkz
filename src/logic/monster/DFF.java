package logic.monster;

import java.util.ArrayList;
import config.ConfigurableOption;
import logic.IRenderable;
import logic.Rectangle;
import logic.bullet.GndBullet;
import ui.DrawingUtility;

public class DFF extends Monster {

	public DFF(int x, int y) {
		super(x, y, ConfigurableOption.DFF_LIFE, ConfigurableOption.DFF_SCORE);
		img = DrawingUtility.dFF;
	}

	public void shoot(ArrayList<IRenderable> list) {
		firingDelayCounter--;
		if (firingDelayCounter > 0)
			return;
		int w = DrawingUtility.gndBullet.getWidth();
		int h = DrawingUtility.gndBullet.getHeight() / 2;
		// y' = 33, 60
		list.add(new GndBullet(x - w, y + 33 - h));
		list.add(new GndBullet(x - w, y + 60 - h));
		firingDelayCounter = ConfigurableOption.FIRING_DELAY;
	}

	public Rectangle rectify() {
		return new Rectangle(x + 15, y + 20, img.getWidth() - 34,
				img.getHeight() - 41);
	}

}
