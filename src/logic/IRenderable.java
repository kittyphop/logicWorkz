package logic;

import java.awt.Graphics2D;

public interface IRenderable {

	public int getX();

	public int getY();

	public int getZ();

	public boolean isDestroyed();

	public void setDestroyed(boolean destroyed);

	public void move();

	public Rectangle rectify();

	public boolean isOverlap(IRenderable o);

	public void render(Graphics2D g2);

}
