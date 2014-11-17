package logic;

import logic.collectible.Probe;
import logic.gun.Gun;
import logic.gun.NormalGun;
import config.ConfigurableOption;

public class Player {

	private int time, score, level;
	private boolean gameOver, pause;
	private Gun currentGun;
	private boolean[] collectedProbe;

	public Player(int x, int y) {
		time = ConfigurableOption.MAX_TIME;
		score = 0;
		level = 5;
		gameOver = false;
		pause = false;
		currentGun = new NormalGun(x, y, 0);
		collectedProbe = new boolean[4];
	}

	public int getTime() {
		return time;
	}

	public void setTime(int time) {
		this.time = time;
		if (this.time > ConfigurableOption.MAX_TIME)
			this.time = ConfigurableOption.MAX_TIME;
		if (this.time <= 0)
			setGameOver(true);
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public boolean isGameOver() {
		if (time <= 0)
			setGameOver(true);
		return gameOver;
	}

	public void setGameOver(boolean gameOver) {
		this.gameOver = gameOver;
	}

	public boolean isPause() {
		return pause;
	}

	public void setPause(boolean pause) {
		this.pause = pause;
	}

	public boolean isKmap() {
		for (int i = 0; i < collectedProbe.length; i++)
			if (!collectedProbe[i])
				return false;
		return true;
	}

	public Gun getCurrentGun() {
		return currentGun;
	}

	public void setCurrentGun(Gun currentGun) {
		this.currentGun = currentGun;
	}

	public boolean[] getCollectedProbe() {
		return collectedProbe;
	}

	public void collectNewProbe(Probe probe) {
		String s = probe.getLetter();
		if (s.equals("K"))
			collectedProbe[0] = true;
		if (s.equals("M"))
			collectedProbe[1] = true;
		if (s.equals("A"))
			collectedProbe[2] = true;
		if (s.equals("P"))
			collectedProbe[3] = true;
	}

	public void clearCollectedProbe() {
		for (int i = 0; i < collectedProbe.length; i++)
			collectedProbe[i] = false;
	}

	public void isHit(int attack) {
		time -= attack;
		if (time <= 0) {
			time = 0;
			gameOver = true;
		}
	}

}
