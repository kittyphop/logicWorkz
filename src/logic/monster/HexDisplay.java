package logic.monster;

import java.util.ArrayList;
import config.ConfigurableOption;
import logic.IRenderable;
import logic.Rectangle;
import logic.bullet.GndBullet;
import ui.DrawingUtility;

public class HexDisplay extends Monster {

	public HexDisplay(int x, int y) {
		super(x, y, ConfigurableOption.HEX_DISPLAY_LIFE,
				ConfigurableOption.HEX_DISPLAY_SCORE);
		img = DrawingUtility.hexDisplay;
	}

	public void shoot(ArrayList<IRenderable> list) {
		firingDelayCounter--;
		if (firingDelayCounter > 0)
			return;
		int w = DrawingUtility.gndBullet.getWidth();
		int h = DrawingUtility.gndBullet.getHeight() / 2;
		int l = img.getHeight() / 8;
		list.add(new GndBullet(x - w, y + l - h));
		list.add(new GndBullet(x - w, y + 3 * l - h));
		list.add(new GndBullet(x - w, y + 5 * l - h));
		list.add(new GndBullet(x - w, y + 7 * l - h));
		firingDelayCounter = firingDelay;
	}

	public Rectangle rectify() {
		return new Rectangle(x + 13, y, img.getHeight() / 2, img.getHeight());
	}

}
