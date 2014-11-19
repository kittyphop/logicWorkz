package logic;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import config.ConfigurableOption;

public abstract class RenderableObject implements IRenderable {

	protected int x, y, z, movingDelayCounter;
	protected boolean destroyed;
	protected BufferedImage img;

	public RenderableObject(int x, int y) {
		this.x = x;
		this.y = y;
		this.z = (int) (Math.random() * 1000);
		movingDelayCounter = ConfigurableOption.MOVING_DELAY;
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
