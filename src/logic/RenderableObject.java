package logic;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import config.ConfigurableOption;
import config.RandomUtility;

public abstract class RenderableObject implements IRenderable {

	protected int x, y, z, movingDelay, movingDelayCounter;
	protected boolean destroyed;
	protected BufferedImage img;

	public RenderableObject(int x, int y) {
		this.x = x;
		this.y = y;
		this.z = RandomUtility.random(1, Integer.MAX_VALUE - 2);
		movingDelay = RandomUtility.random(ConfigurableOption.MIN_MOVING_DELAY,
				ConfigurableOption.MAX_MOVING_DELAY);
		movingDelayCounter = movingDelay;
		destroyed = false;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public int getZ() {
		return z;
	}

	public boolean isDestroyed() {
		return destroyed;
	}

	public void setDestroyed(boolean destroyed) {
		this.destroyed = destroyed;
	}

	public abstract void move();

	public abstract Rectangle rectify();

	public boolean isOverlap(IRenderable o) {
		return rectify().isOverlap(o.rectify());
	}

	public void render(Graphics2D g2) {
		g2.drawImage(img, null, x, y);
	}

}
