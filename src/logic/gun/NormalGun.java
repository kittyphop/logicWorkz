package logic.gun;

import java.util.ArrayList;

import logic.IRenderable;
import logic.Player;
import logic.Rectangle;
import logic.bullet.VddBullet;
import ui.DrawingUtility;

public class NormalGun extends Gun {

	public NormalGun(int x, int y, int bullet) {
		super(x, y, bullet);
		img = DrawingUtility.binarySwitch;
	}

	public void shoot(Player player, ArrayList<IRenderable> list) {
		int h = DrawingUtility.vddBullet.getHeight() / 2;
		list.add(new VddBullet(x + img.getWidth(), y + img.getHeight() / 2 - h,
				false));
	}

	public Rectangle rectify() {
		return new Rectangle(x, y, img.getWidth(), img.getHeight());
	}

}
