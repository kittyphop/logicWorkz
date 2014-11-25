package logic.gun;

import java.util.ArrayList;

import audio.AudioUtility;
import config.SharedData;
import logic.IRenderable;
import logic.Rectangle;
import logic.bullet.VddBullet;
import ui.DrawingUtility;

public class NormalGun extends Gun {

	public NormalGun(SharedData data, int x, int y, int bullet) {
		super(data, x, y, bullet);
		img = DrawingUtility.binarySwitch0;
		img0 = DrawingUtility.binarySwitch0;
		img1 = DrawingUtility.binarySwitch1;
	}

	public void shoot() {
		new Thread(new AudioUtility(AudioUtility.SHOOT)).start();
		int h = DrawingUtility.vddBullet.getHeight() / 2;
		ArrayList<IRenderable> list = data.getGameList();
		list.add(new VddBullet(x + img.getWidth(), y + img.getHeight() / 2 - h,
				false));
	}

	public Rectangle rectify() {
		return new Rectangle(x, y, img.getWidth(), img.getHeight());
	}

}
