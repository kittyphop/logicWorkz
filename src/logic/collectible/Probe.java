package logic.collectible;

import logic.Player;
import logic.Rectangle;
import logic.RenderableObject;
import ui.DrawingUtility;
import config.ConfigurableOption;

public class Probe extends RenderableObject implements ICollectible {

	private String letter;

	public Probe(int x, int y, String letter) {
		super(x, y);
		this.letter = letter;
		if (letter.equals("K"))
			img = DrawingUtility.probeK;
		if (letter.equals("M"))
			img = DrawingUtility.probeM;
		if (letter.equals("A"))
			img = DrawingUtility.probeA;
		if (letter.equals("P"))
			img = DrawingUtility.probeP;
	}

	public String getLetter() {
		return letter;
	}

	public void move() {
		movingDelayCounter--;
		if (movingDelayCounter > 0)
			return;
		x--;
		movingDelayCounter = ConfigurableOption.MOVING_DELAY;
	}

	public void collect(Player player) {
		destroyed = true;
		player.collectNewProbe(this);
	}

	public Rectangle rectify() {
		return new Rectangle(x, y, img.getWidth(), img.getWidth());
	}

}
