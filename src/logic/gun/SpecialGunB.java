package logic.gun;

import java.util.ArrayList;

import audio.AudioUtility;
import config.SharedData;
import logic.IRenderable;
import logic.Rectangle;
import logic.bullet.VddBullet;
import ui.DrawingUtility;

public class SpecialGunB extends Gun {

	public SpecialGunB(SharedData data, int x, int y, int bullet) {
		super(data, x, y, bullet);
		img = DrawingUtility.oneShot;
		img0 = img;
		img1 = img;
	}

	public void shoot() {
		new Thread(new AudioUtility(AudioUtility.SHOOT)).start();
		int h = DrawingUtility.vddBullet.getHeight() / 2;
		ArrayList<IRenderable> list = data.getGameList();
		list.add(new VddBullet(x + img.getWidth(), y + 13 - h, true));
		list.add(new VddBullet(x + img.getWidth(), y + 33 - h, true));
		bullet--;
		if (bullet == 0) {
			destroyed = true;
			NormalGun g = new NormalGun(data, x, y, 0);
			data.getPlayer().setCurrentGun(g);
			list.add(g);
		}
	}

	public Rectangle rectify() {
		return new Rectangle(x + 15, y, img.getWidth() - 35,
				img.getHeight() - 21);
	}

}
