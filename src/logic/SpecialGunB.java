package logic;

import ui.DrawingUtility;

public class SpecialGunB extends Gun {

	public SpecialGunB(int x, int y, int bullet) {
		super(x, y, bullet);
		img = DrawingUtility.hexKeyboard;
	}

	public Rectangle rectify() {
		return new Rectangle(x, y, img.getHeight(), img.getHeight());
	}

}
