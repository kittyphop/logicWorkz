package logic.monster;

import java.util.ArrayList;
import config.ConfigurableOption;
import logic.IRenderable;
import logic.Rectangle;
import logic.bullet.GndBullet;
import ui.DrawingUtility;

public class PLA extends Monster {

	public PLA(int x, int y, int life) {
		super(x, y, life, ConfigurableOption.PLA_SCORE);
		img = DrawingUtility.pla;
	}

	public void shoot(ArrayList<IRenderable> list) {
		firingDelayCounter--;
		if (firingDelayCounter > 0)
			return;
		int w = DrawingUtility.gndBullet.getWidth();
		int h = DrawingUtility.gndBullet.getHeight() / 2;
		// y' = 33, 46, 60, 73, 86
		list.add(new GndBullet(x - w, y + 33 - h));
		list.add(new GndBullet(x - w, y + 46 - h));
		list.add(new GndBullet(x - w, y + 60 - h));
		list.add(new GndBullet(x - w, y + 73 - h));
		list.add(new GndBullet(x - w, y + 86 - h));
		firingDelayCounter = ConfigurableOption.FIRING_DELAY;
	}

	public Rectangle rectify() {
		return new Rectangle(x + 14, y, img.getWidth() - 27, img.getHeight());
	}

}
