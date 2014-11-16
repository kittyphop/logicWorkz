package logic;

public abstract class Gun extends RenderableObject {

	protected int bullet;

	public Gun(int x, int y, int bullet) {
		super(x, y);
		this.bullet = bullet;
	}

	public void shoot() {
		bullet--;
		if (bullet < 0)
			bullet = 0;
	}

	public boolean canShoot() {
		return bullet > 0;
	}

	public void move() {
		// implement in GameLogic
	}

	public abstract Rectangle rectify();

}
