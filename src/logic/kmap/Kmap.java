package logic.kmap;

import java.awt.Graphics2D;

public class Kmap {

	private int[][] map = new int[4][4];
	private boolean[][] cover = new boolean[4][4];
	private int time, score, x, y;
	private boolean end, returnToGame;

	public Kmap() {
		randomKmap();
		time = 30;
		score = 0;
		end = true;
		returnToGame = false;
		x = -1;
		y = -1;
	}

	public int[][] getMap() {
		return map;
	}

	public int getTime() {
		return time;
	}

	public void setTime(int time) {
		this.time = time;
		if (time <= 0) {
			this.time = 0;
			end = true;
		}
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public void setEnd(boolean end) {
		this.end = end;
	}

	public boolean isEnd() {
		return end;
	}

	public boolean isReturnToGame() {
		return returnToGame;
	}

	public void setReturnToGame(boolean returnToGame) {
		this.returnToGame = returnToGame;
	}

	public int random(int a, int b) {
		return (int) (Math.random() * (b - a + 1)) + a;
	}

	public void randomKmap() {
		for (int i = 0; i < 4; i++)
			for (int j = 0; j < 4; j++) {
				map[i][j] = random(0, 2);
				cover[i][j] = false;
			}
	}

	public static boolean isInsideKmap(int x, int y) {
		// not yet implemented
		return true;
	}

	public boolean ok(Frame l) {
		int x1 = l.getX1(), y1 = l.getY1();
		int x2 = l.getX1(), y2 = l.getY1();
		int dx = x2 - x1 + 1, dy = y2 - y1 + 1;

		// check 1) size is 2^k
		if (dx != 1 && dx != 2 && dx != 4)
			return false;
		if (dy != 1 && dy != 2 && dy != 4)
			return false;

		// check 2) all members are ones
		for (int i = x1; i <= x2; i++)
			for (int j = y1; j <= y2; j++)
				if (map[(i + 4) % 4][(j + 4) % 4] == 0)
					return false;

		return true;
	}

	public void cover(Frame l) {
		int x1 = l.getX1(), y1 = l.getY1();
		int x2 = l.getX1(), y2 = l.getY1();

		for (int i = x1; i <= x2; i++)
			for (int j = y1; j <= y2; j++)
				cover[(i + 4) % 4][(j + 4) % 4] = true;
	}

	public boolean isCoverAllOnes() {
		for (int i = 0; i < 4; i++)
			for (int j = 0; j < 4; j++)
				if (map[i][j] == 1 && !cover[i][j])
					return false;
		return true;
	}

	public void render(Graphics2D g2) {
		// not yet implemented
		// render only number on Kmap
		// 0 = 0, 1 = 1, 2 = X
	}

}
