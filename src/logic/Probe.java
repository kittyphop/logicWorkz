package logic;

import java.awt.Graphics2D;
import config.ConfigurableOption;

public class Probe extends RenderableObject implements ICollectible {

	private String letter;

	public Probe(int x, int y, String letter) {
		this.x = x;
		this.y = y;
		this.z = (int) Math.random();
		movingDelayCounter = ConfigurableOption.MOVING_DELAY;
		destroyed = false;
		this.letter = letter;
	}

	public String getLetter() {
		return letter;
	}

	public void collect(Player player) {
		player.collectNewProbe(this);
	}

	public void move() {
		movingDelayCounter--;
		if (movingDelayCounter > 0)
			return;
		x--;
		movingDelayCounter = ConfigurableOption.MOVING_DELAY;
	}

	public void render(Graphics2D g2) {

	}

}
