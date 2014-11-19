package logic.kmap;

import java.awt.Color;
import java.awt.Graphics2D;

public class Frame {

	private int x1, y1, x2, y2;

	public Frame(int x1, int y1, int x2, int y2) {
		super();
		this.x1 = x1;
		this.y1 = y1;
		this.x2 = x2;
		this.y2 = y2;
	}

	public Frame(Frame l) {
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

	public Frame toIndex() {
		// change pixel co-ordinate to table index
		int a = (x1 - 297) / 30 - 3, c = (x2 - 297) / 30 - 3;
		int b = (y1 - 160) / 30 - 3, d = (y2 - 160) / 30 - 3;
		return new Frame(a, b, c, d);
	}

	public void render(Graphics2D g2) {
		if (x1 == -1 || y1 == -1 || x2 == -1 || y2 == -1)
			return;

		Color c = g2.getColor();
		g2.setColor(Color.RED);

		if (Kmap.isInsideKmap(x1, y1) && Kmap.isInsideKmap(x2, y2)) {
			g2.drawLine(x1, y1, x2, y1);
			g2.drawLine(x1, y1, x1, y2);
			g2.drawLine(x2, y1, x2, y2);
			g2.drawLine(x1, y2, x2, y2);
		}
		g2.setColor(c);

		// not yet implemented

	}

}
