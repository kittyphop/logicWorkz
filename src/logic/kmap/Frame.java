package logic.kmap;

import java.awt.Color;
import java.awt.Graphics2D;

public class Frame {

	private int x1, y1, x2, y2;

	public Frame(int x1, int y1, int x2, int y2) {
		this.x1 = x1;
		this.y1 = y1;
		this.x2 = x2;
		this.y2 = y2;
	}

	public Frame(Frame f) {
		x1 = f.x1;
		y1 = f.y1;
		x2 = f.x2;
		y2 = f.y2;
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

	public boolean hasMinus() {
		return x1 == -1 || y1 == -1 || x2 == -1 || y2 == -1;
	}

	public void render(Graphics2D g2) {
		if (hasMinus())
			return;

		int a = x1, b = y1, c = x2, d = y2;
		int[] p1, p2, q1, q2;

		if (a > c) {
			int t = a;
			a = c;
			c = t;
		}
		if (b > d) {
			int t = b;
			b = d;
			d = t;
		}

		if (c < 387) {
			a += 120;
			c += 120;
		}
		if (c > 507) {
			a -= 120;
			c -= 120;
		}

		if (d < 250) {
			b += 120;
			d += 120;
		}
		if (d > 370) {
			b -= 120;
			d -= 120;
		}

		if (a >= 387 && a <= 507) {
			p1 = new int[] { a, c, 0 };
			p2 = new int[] { 0, 0, 0 };
		} else {
			p1 = new int[] { 387, c, 1 };
			p2 = new int[] { a + 120, 507, 1 };
		}

		if (b >= 250 && b <= 370) {
			q1 = new int[] { b, d, 0 };
			q2 = new int[] { 0, 0, 0 };
		} else {
			q1 = new int[] { 250, d, 1 };
			q2 = new int[] { b + 120, 370, 1 };
		}

		Color cc = g2.getColor();
		g2.setColor(Color.RED);

		// p1-q1
		g2.drawLine(p1[1], q1[0], p1[1], q1[1]);
		g2.drawLine(p1[0], q1[1], p1[1], q1[1]);
		if (p1[2] == 0)
			g2.drawLine(p1[0], q1[0], p1[0], q1[1]);
		if (q1[2] == 0)
			g2.drawLine(p1[0], q1[0], p1[1], q1[0]);

		// p2-q1
		if (p2[0] != 0) {
			g2.drawLine(p2[0], q1[0], p2[0], q1[1]);
			g2.drawLine(p2[0], q1[1], p2[1], q1[1]);
			if (p2[2] == 0)
				g2.drawLine(p2[1], q1[0], p2[1], q1[1]);
			if (q1[2] == 0)
				g2.drawLine(p2[0], q1[0], p2[1], q1[0]);
		}

		// p1-q2
		if (q2[0] != 0) {
			g2.drawLine(p1[0], q2[0], p1[1], q2[0]);
			g2.drawLine(p1[1], q2[0], p1[1], q2[1]);
			if (p1[2] == 0)
				g2.drawLine(p1[0], q2[0], p1[0], q2[1]);
			if (q2[2] == 0)
				g2.drawLine(p1[0], q2[1], p1[1], q2[1]);
		}

		// p2-q2
		if (p2[0] != 0 && q2[0] != 0) {
			g2.drawLine(p2[0], q2[0], p2[1], q2[0]);
			g2.drawLine(p2[0], q2[0], p2[0], q2[1]);
			if (p2[2] == 0)
				g2.drawLine(p2[1], q2[0], p2[1], q2[1]);
			if (q2[2] == 0)
				g2.drawLine(p2[0], q2[1], p2[1], q2[1]);
		}

		g2.setColor(cc);
	}

}
