package logic.monster;

import java.util.ArrayList;
import config.ConfigurableOption;
import logic.IRenderable;
import logic.Rectangle;
import logic.bullet.GndBullet;
import ui.DrawingUtility;

public class JKFF extends Monster {

	public JKFF(int x, int y) {
		super(x, y, ConfigurableOption.JKFF_LIFE, ConfigurableOption.JKFF_SCORE);
		img = DrawingUtility.jkFF;
	}

	public void shoot(ArrayList<IRenderable> list) {
		firingDelayCounter--;
		if (firingDelayCounter > 0)
			return;
		int w = DrawingUtility.gndBullet.getWidth();
		int h = DrawingUtility.gndBullet.getHeight() / 2;
		// y' = 27, 40, 53
		list.add(new GndBullet(x - w, y + 27 - h));
		list.add(new GndBullet(x - w, y + 40 - h));
		list.add(new GndBullet(x - w, y + 53 - h));
		firingDelayCounter = firingDelay;
	}

	public Rectangle rectify() {
		return new Rectangle(x + 15, y + 15, img.getWidth() - 30,
				img.getHeight() - 30);
	}

}
