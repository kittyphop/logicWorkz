package logic.kmap;

import java.awt.Graphics2D;

import logic.gun.*;
import config.ConfigurableOption;
import config.RandomUtility;
import config.SharedData;

public class Kmap {

	private int[][] map = new int[4][4];
	private boolean[][] cover = new boolean[4][4];
	private int time, score, x, y, remainFrame, remainToNextGun;
	private boolean run, returnToGame, gameOver;
	private Gun nextGun;
	private SharedData data;

	public Kmap(SharedData data) {
		this.data = data;
		randomKmap();
		time = ConfigurableOption.MAX_KMAP_TIME;
		score = 0;
		remainFrame = ConfigurableOption.MAX_FRAME;
		setRun(false);
		returnToGame = false;
		gameOver = false;
		x = -1;
		y = -1;
		nextGun = new SpecialGunA(null, 0, 0, 0);
		remainToNextGun = 4;
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
			gameOver = true;
		}
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
		if (score < 4) {
			nextGun = new SpecialGunA(null, 0, 0, 0);
			remainToNextGun = 4 - score;
		} else if (score < 7) {
			nextGun = new SpecialGunB(null, 0, 0, 0);
			remainToNextGun = 7 - score;
		} else if (score < 10) {
			nextGun = new SpecialGunC(null, 0, 0, 0);
			remainToNextGun = 10 - score;
		} else {
			nextGun = null;
			remainToNextGun = 0;
		}
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

	public int getRemainFrame() {
		return remainFrame;
	}

	public void setRemainFrame(int remainFrame) {
		this.remainFrame = remainFrame;
		if (remainFrame <= 0) {
			this.remainFrame = 0;
			gameOver = true;
		}
	}

	public int getRemainToNextGun() {
		return remainToNextGun;
	}

	public void setRun(boolean run) {
		synchronized (data) {
			this.run = run;
			data.notifyAll();
		}
	}

	public boolean isRun() {
		return run;
	}

	public boolean isReturnToGame() {
		return returnToGame;
	}

	public void setReturnToGame(boolean returnToGame) {
		this.returnToGame = returnToGame;
	}

	public boolean isGameOver() {
		return gameOver;
	}

	public void setGameOver(boolean gameOver) {
		this.gameOver = gameOver;
	}

	public Gun getNextGun() {
		return nextGun;
	}

	public void randomKmap() {
		for (int i = 0; i < 4; i++)
			for (int j = 0; j < 4; j++) {
				map[i][j] = RandomUtility.random(0, 2);
				cover[i][j] = false;
			}
	}

	public static boolean isInsideKmap(int x, int y) {
		return x >= 297 && x <= 597 && y >= 160 && y <= 460;
	}

	public boolean ok(Frame f) {
		int x1 = f.getX1(), y1 = f.getY1();
		int x2 = f.getX2(), y2 = f.getY2();

		if (x1 > x2) {
			int t = x1;
			x1 = x2;
			x2 = t;
		}
		if (y1 > y2) {
			int t = y1;
			y1 = y2;
			y2 = t;
		}

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

	public void cover(Frame f) {
		int x1 = f.getX1(), y1 = f.getY1();
		int x2 = f.getX2(), y2 = f.getY2();

		if (x1 > x2) {
			int t = x1;
			x1 = x2;
			x2 = t;
		}
		if (y1 > y2) {
			int t = y1;
			y1 = y2;
			y2 = t;
		}

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
		// render only number on Kmap
		// 0 = 0, 1 = 1, 2 = X
		for (int i = 0; i < 4; i++)
			for (int j = 0; j < 4; j++) {
				String str = "" + map[i][j];
				if (map[i][j] == 2)
					str = "X";
				g2.drawString(str, 400 + 30 * i, 270 + 30 * j);
			}
	}

}
