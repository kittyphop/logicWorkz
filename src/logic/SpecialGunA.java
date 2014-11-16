package logic;

import ui.DrawingUtility;

public class SpecialGunA extends Gun {

	public SpecialGunA(int x, int y, int bullet) {
		super(x, y, bullet);
		img = DrawingUtility.pushButton;
	}

	public Rectangle rectify() {
		return new Rectangle(x, y, img.getWidth(), img.getHeight());
	}

}
