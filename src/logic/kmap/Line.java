package logic.kmap;

import java.awt.Graphics2D;

public class Line {

	private int x1, y1, x2, y2;

	public Line(int x1, int y1, int x2, int y2) {
		super();
		this.x1 = x1;
		this.y1 = y1;
		this.x2 = x2;
		this.y2 = y2;
	}

	public Line(Line l) {
		x1 = l.x1;
		y1 = l.y1;
		x2 = l.x2;
		y2 = l.y2;
	}

	public int getX1() {
		return x1;
	}

	public int getY1() {
		return y1;
	}

	public int getX2() {
		return x2;
	}

	public int getY2() {
		return y2;
	}

	public Line toIndex() {
		// not yet implemented
		// change pixel co-ordinate to table index
		int a = (x1 - 50) / 100, c = (x2 - 50) / 100;
		int b = (y1 - 60) / 100, d = (y2 - 60) / 100;
		return new Line(a, b, c, d);
	}

	public void render(Graphics2D g2) {
		if (x1 == -1 || y1 == -1 || x2 == -1 || y2 == -1)
			return;

		if (Kmap.isInsideKmap(x1, y1) && Kmap.isInsideKmap(x1, y1)) {
			g2.drawLine(x1, y1, x2, y1);
			g2.drawLine(x1, y1, x1, y2);
			g2.drawLine(x2, y1, x2, y2);
			g2.drawLine(x1, y2, x2, y2);
		}

		// not yet implemented

	}

}
