package logic.collectible;

import audio.AudioUtility;
import logic.Player;
import logic.Rectangle;
import logic.RenderableObject;
import ui.DrawingUtility;
import config.ConfigurableOption;

public class Probe extends RenderableObject implements ICollectible {

	private String letter;
	private boolean up = false;

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
		movingDelayCounter = movingDelay;
		x--;
		if (up) {
			if (y > 10)
				y--;
			else
				up = !up;
		} else {
			if (y + img.getHeight() + 10 < ConfigurableOption.PLAYPANEL_HEIGHT)
				y++;
			else
				up = !up;
		}
	}

	public void collect(Player player) {
		new Thread(new AudioUtility(AudioUtility.COLLECT_PROBE)).start();
		destroyed = true;
		player.collectNewProbe(this);
		player.setScore(player.getScore() + ConfigurableOption.PROBE_SCORE);
	}

	public Rectangle rectify() {
		return new Rectangle(x, y, img.getWidth(), img.getWidth());
	}

}
