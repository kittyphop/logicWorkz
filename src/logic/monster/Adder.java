package logic.monster;

import java.util.ArrayList;
import config.ConfigurableOption;
import logic.IRenderable;
import logic.Rectangle;
import logic.bullet.GndBullet;
import ui.DrawingUtility;

public class Adder extends Monster {

	public Adder(int x, int y, int life) {
		super(x, y, life, ConfigurableOption.ADDER_SCORE);
		img = DrawingUtility.adder;
	}

	public void shoot(ArrayList<IRenderable> list) {
		firingDelayCounter--;
		if (firingDelayCounter > 0)
			return;
		int w = DrawingUtility.gndBullet.getWidth();
		int h = DrawingUtility.gndBullet.getHeight() / 2;
		// y' = 33, 47, 60, 73, 100, 113, 127, 140
		list.add(new GndBullet(x - w, y + 33 - h));
		list.add(new GndBullet(x - w, y + 47 - h));
		list.add(new GndBullet(x - w, y + 60 - h));
		list.add(new GndBullet(x - w, y + 73 - h));
		list.add(new GndBullet(x - w, y + 100 - h));
		list.add(new GndBullet(x - w, y + 113 - h));
		list.add(new GndBullet(x - w, y + 127 - h));
		list.add(new GndBullet(x - w, y + 140 - h));
		firingDelayCounter = ConfigurableOption.FIRING_DELAY;
	}

	public Rectangle rectify() {
		return new Rectangle(x + 13, y + 13, img.getWidth() - 26,
				img.getHeight() - 26);
	}
}
