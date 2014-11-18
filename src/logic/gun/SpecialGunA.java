package logic.gun;

import java.util.ArrayList;
import logic.IRenderable;
import logic.Player;
import logic.Rectangle;
import logic.bullet.VddBullet;
import ui.DrawingUtility;

public class SpecialGunA extends Gun {

	public SpecialGunA(Player player, int x, int y, int bullet) {
		super(player, x, y, bullet);
		img = DrawingUtility.pushButton0;
		img0 = DrawingUtility.pushButton0;
		img1 = DrawingUtility.pushButton1;
	}

	public void shoot(Player player, ArrayList<IRenderable> list) {
		int h = DrawingUtility.vddBullet.getHeight() / 2;
		list.add(new VddBullet(x + img.getWidth(), y + img.getHeight() / 2 + 2
				- h, true));
		bullet--;
		if (bullet == 0) {
			destroyed = true;
			NormalGun g = new NormalGun(player, x, y, 0);
			player.setCurrentGun(g);
			list.add(g);
		}
	}

	public Rectangle rectify() {
		return new Rectangle(x, y, img.getWidth(), img.getHeight());
	}

}
