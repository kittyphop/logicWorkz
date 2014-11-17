package logic;

public class KmapLogic {

	private int[][] kmap = new int[4][4];
	private int time, score;
	private boolean end;

	public KmapLogic() {
		randomKmap();
		time = 30;
		score = 0;
		end = false;
	}

	public int getTime() {
		return time;
	}

	public int getScore() {
		return score;
	}

	public void setEnd(boolean end) {
		this.end = end;
	}

	public boolean isEnd() {
		return end;
	}

	public void update() {
		// update logic here
		if (end)
			return;
		time--;
		if (time <= 0)
			end = true;
		score++;
	}

	public int random(int a, int b) {
		return (int) (Math.random() * (b - a + 1)) + a;
	}

	public void randomKmap() {
		for (int i = 0; i < 4; i++)
			for (int j = 0; j < 4; j++)
				kmap[i][j] = random(0, 1);
	}

}
