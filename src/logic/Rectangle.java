package logic;

public class Rectangle {

	private int x1, y1, x2, y2;

	public Rectangle(int x, int y, int w, int h) {
		x1 = x;
		y1 = y;
		x2 = x + w;
		y2 = y + h;
	}

	public boolean isOverlap(Rectangle r) {
		if (x2 <= r.x1 || r.x2 <= x1)
			return false;
		if (y2 <= r.y1 || r.y2 <= y1)
			return false;
		return true;
	}
}