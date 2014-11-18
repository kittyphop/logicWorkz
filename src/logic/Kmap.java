package logic;

import java.awt.Graphics2D;

public class Kmap {

	private int[][] map = new int[4][4];
	private int time, score, x, y;
	private boolean end, returnToGame;

	public Kmap() {
		randomKmap();
		time = 30;
		score = 0;
		end = false;
		returnToGame = true;
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
			for (int j = 0; j < 4; j++)
				map[i][j] = random(0, 1);
	}

	public void render(Graphics2D g2) {

	}

}
