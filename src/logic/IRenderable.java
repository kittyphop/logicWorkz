package logic;

import java.awt.Graphics2D;

public interface IRenderable {

	public int getZ();

	public boolean isDestroyed();

	public void move();

	public void render(Graphics2D g2);

}
