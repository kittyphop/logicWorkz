package logic;

import java.awt.Graphics2D;

public interface IRenderable {

	public int getZ();

	public boolean isDestroyed();

	public void setDestroyed(boolean destroyed);

	public void move();

	public Rectangle rectify();

	public void render(Graphics2D g2);

}
