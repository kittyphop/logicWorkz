package logic.gun;

import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import config.InputUtility;
import logic.IRenderable;
import logic.Player;
import logic.Rectangle;
import logic.bullet.VddBullet;
import ui.DrawingUtility;

public class NormalGun extends Gun {

	private BufferedImage img0, img1;

	public NormalGun(int x, int y, int bullet) {
		super(x, y, bullet);
		img = DrawingUtility.binarySwitch0;
		img0 = DrawingUtility.binarySwitch0;
		img1 = DrawingUtility.binarySwitch1;
	}

	public void shoot(Player player, ArrayList<IRenderable> list) {
		int h = DrawingUtility.vddBullet.getHeight() / 2;
		list.add(new VddBullet(x + img.getWidth(), y + img.getHeight() / 2 - h,
				false));
	}

	public Rectangle rectify() {
		return new Rectangle(x, y, img.getWidth(), img.getHeight());
	}

	public void render(Graphics2D g2) {
		if (InputUtility.getKeyPressed(KeyEvent.VK_SPACE))
			g2.drawImage(img1, null, x, y);
		else
			g2.drawImage(img0, null, x, y);
	}

}
