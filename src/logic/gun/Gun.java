package logic.gun;

import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import config.ConfigurableOption;
import config.InputUtility;
import config.SharedData;
import logic.IRenderable;
import logic.Player;
import logic.Rectangle;
import logic.RenderableObject;

public abstract class Gun extends RenderableObject {

	protected int bullet;
	protected SharedData data;
	protected int renderDelayCounter;
	protected BufferedImage img0, img1;

	public Gun(SharedData data, int x, int y, int bullet) {
		super(x, y);
		z = Integer.MAX_VALUE - 1;
		this.data = data;
		this.bullet = bullet;
		renderDelayCounter = ConfigurableOption.RENDER_DELAY;
	}

	public abstract void shoot(Player player, ArrayList<IRenderable> list);

	public boolean canShoot() {
		return bullet > 0;
	}

	public void move() {
		Player player = data.getPlayer();

		if (InputUtility.getKeyPressed(KeyEvent.VK_LEFT)) {
			player.getCurrentGun().x--;
			if (player.getCurrentGun().x < 0)
				player.getCurrentGun().x = 0;
		}
		if (InputUtility.getKeyPressed(KeyEvent.VK_RIGHT)) {
			player.getCurrentGun().x++;
			if (player.getCurrentGun().x > ConfigurableOption.PLAYPANEL_WIDTH
					- player.getCurrentGun().img.getWidth())
				player.getCurrentGun().x = ConfigurableOption.PLAYPANEL_WIDTH
						- player.getCurrentGun().img.getWidth();
		}
		if (InputUtility.getKeyPressed(KeyEvent.VK_UP)) {
			player.getCurrentGun().y--;
			if (player.getCurrentGun().y < 0)
				player.getCurrentGun().y = 0;
		}
		if (InputUtility.getKeyPressed(KeyEvent.VK_DOWN)) {
			player.getCurrentGun().y++;
			if (player.getCurrentGun().y > ConfigurableOption.PLAYPANEL_HEIGHT
					- player.getCurrentGun().img.getHeight())
				player.getCurrentGun().y = ConfigurableOption.PLAYPANEL_HEIGHT
						- player.getCurrentGun().img.getHeight();
		}
	}

	public abstract Rectangle rectify();

	public int getBulletAmount() {
		return bullet;
	}

	public void render(Graphics2D g2) {
		if (data.getPlayer().isPause() || data.getKmap().isRun())
			super.render(g2);
		else if (data.getPlayer().isDamaged()) {
			renderDelayCounter--;
			if (renderDelayCounter > ConfigurableOption.RENDER_DELAY / 2)
				return;
			if (InputUtility.getKeyPressed(KeyEvent.VK_SPACE))
				img = img1;
			else
				img = img0;
			if (renderDelayCounter == 0)
				renderDelayCounter = ConfigurableOption.RENDER_DELAY;
		} else {
			if (InputUtility.getKeyPressed(KeyEvent.VK_SPACE))
				img = img1;
			else
				img = img0;
		}
		super.render(g2);
	}

}
