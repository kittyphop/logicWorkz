package logic.monster;

import java.util.ArrayList;
import config.ConfigurableOption;
import logic.IRenderable;
import logic.Rectangle;
import logic.bullet.GndBullet;
import ui.DrawingUtility;

public class Mux extends Monster {

	public Mux(int x, int y) {
		super(x, y, ConfigurableOption.MUX_LIFE, ConfigurableOption.MUX_SCORE);
		img = DrawingUtility.mux;
	}

	public void shoot(ArrayList<IRenderable> list) {
		firingDelayCounter--;
		if (firingDelayCounter > 0)
			return;
		int w = DrawingUtility.gndBullet.getWidth();
		int h = DrawingUtility.gndBullet.getHeight() / 2;
		// y' = 14, 34, 47, 67, 80, 94, 107
		list.add(new GndBullet(x - w, y + 14 - h));
		list.add(new GndBullet(x - w, y + 34 - h));
		list.add(new GndBullet(x - w, y + 47 - h));
		list.add(new GndBullet(x - w, y + 67 - h));
		list.add(new GndBullet(x - w, y + 80 - h));
		list.add(new GndBullet(x - w, y + 94 - h));
		list.add(new GndBullet(x - w, y + 107 - h));
		firingDelayCounter = ConfigurableOption.FIRING_DELAY;
	}

	public Rectangle rectify() {
		return new Rectangle(x + 14, y, img.getWidth() - 30, img.getHeight());
	}
}
