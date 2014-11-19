package logic.monster;

import java.util.ArrayList;
import config.ConfigurableOption;
import logic.IRenderable;
import logic.Rectangle;
import logic.bullet.GndBullet;
import ui.DrawingUtility;

public class AsciiDisplay extends Monster {

	public AsciiDisplay(int x, int y) {
		super(x, y, ConfigurableOption.ASCII_DISPLAY_LIFE,
				ConfigurableOption.ASCII_DISPLAY_SCORE);
		img = DrawingUtility.asciiDisplay;
	}

	public void shoot(ArrayList<IRenderable> list) {
		firingDelayCounter--;
		if (firingDelayCounter > 0)
			return;
		int w = DrawingUtility.gndBullet.getWidth();
		int h = DrawingUtility.gndBullet.getHeight() / 2;
		// y' = 10, 23, 36, 50, 63, 76, 90, 103
		list.add(new GndBullet(x - w, y + 10 - h));
		list.add(new GndBullet(x - w, y + 23 - h));
		list.add(new GndBullet(x - w, y + 36 - h));
		list.add(new GndBullet(x - w, y + 50 - h));
		list.add(new GndBullet(x - w, y + 63 - h));
		list.add(new GndBullet(x - w, y + 76 - h));
		list.add(new GndBullet(x - w, y + 90 - h));
		list.add(new GndBullet(x - w, y + 103 - h));
		firingDelayCounter = ConfigurableOption.FIRING_DELAY;
	}

	public Rectangle rectify() {
		return new Rectangle(x + 13, y, img.getWidth() - 23, img.getHeight());
	}
}
