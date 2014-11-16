package logic;

import ui.DrawingUtility;

public class NormalGun extends Gun {

	public NormalGun(int x, int y, int bullet) {
		super(x, y, bullet);
		img = DrawingUtility.binarySwitch;
	}

	public Rectangle rectify() {
		return new Rectangle(x, y, img.getWidth(), img.getHeight());
	}

}
