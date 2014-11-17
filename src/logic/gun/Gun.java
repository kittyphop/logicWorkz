package logic.gun;

import java.util.ArrayList;

import logic.IRenderable;
import logic.Player;
import logic.Rectangle;
import logic.RenderableObject;

public abstract class Gun extends RenderableObject {

	protected int bullet;

	public Gun(int x, int y, int bullet) {
		super(x, y);
		this.bullet = bullet;
	}

	public abstract void shoot(Player player, ArrayList<IRenderable> list);

	public boolean canShoot() {
		return bullet > 0;
	}

	public void move() {
		// implement in GameLogic
	}

	public abstract Rectangle rectify();

	public int getBulletAmount() {
		return bullet;
	}

}
