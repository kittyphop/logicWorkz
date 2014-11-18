package logic.gun;

import java.util.ArrayList;

import logic.IRenderable;
import logic.Player;
import logic.Rectangle;
import logic.bullet.VddBullet;
import ui.DrawingUtility;

public class SpecialGunB extends Gun {

	public SpecialGunB(Player player, int x, int y, int bullet) {
		super(player, x, y, bullet);
		img = DrawingUtility.hexKeyboard;
	}

	public void shoot(Player player, ArrayList<IRenderable> list) {
		int h = DrawingUtility.vddBullet.getHeight() / 2;
		int l = img.getHeight() / 8;
		list.add(new VddBullet(x + img.getWidth(), y + l - h, true));
		list.add(new VddBullet(x + img.getWidth(), y + 3 * l - h, true));
		list.add(new VddBullet(x + img.getWidth(), y + 5 * l - h, true));
		list.add(new VddBullet(x + img.getWidth(), y + 7 * l - h, true));
		bullet--;
		if (bullet == 0) {
			destroyed = true;
			NormalGun g = new NormalGun(player, x, y, 0);
			player.setCurrentGun(g);
			list.add(g);
		}
	}

	public Rectangle rectify() {
		return new Rectangle(x, y, img.getHeight(), img.getHeight());
	}

}