package logic;

public abstract class Bullet extends RenderableObject {

	protected int power;

	public Bullet(int x, int y) {
		super(x, y);
	}

	public abstract void move();

	public abstract Rectangle rectify();

}
