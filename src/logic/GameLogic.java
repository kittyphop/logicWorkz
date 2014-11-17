package logic;

import java.awt.event.KeyEvent;
import java.util.*;

import ui.DrawingUtility;
import logic.bullet.*;
import logic.collectible.ICollectible;
import logic.monster.*;
import config.*;

public class GameLogic {

	private SharedData data;
	private int newMonsterDelayCounter;

	public GameLogic(SharedData data) {
		this.data = data;
		setCounter();
	}

	public void begin() {
		while (!data.getPlayer().isGameOver()) {
			try {
				Thread.sleep(5);
			} catch (InterruptedException e) {
			}
			update();
		}
		// setHighscore + setWindowStatus
	}

	public void update() {
		Player player = data.getPlayer();
		ArrayList<IRenderable> list = data.getList();

		if (player.isGameOver() || player.isKmap())
			return;

		if (InputUtility.getKeyTriggered(KeyEvent.VK_ENTER))
			player.setPause(!player.isPause());

		// check if pause
		if (player.isPause())
			return;

		// check if player move or shoot
		if (InputUtility.getKeyPressed(KeyEvent.VK_LEFT))
			player.getCurrentGun().x--;
		if (InputUtility.getKeyPressed(KeyEvent.VK_RIGHT))
			player.getCurrentGun().x++;
		if (InputUtility.getKeyPressed(KeyEvent.VK_UP))
			player.getCurrentGun().y--;
		if (InputUtility.getKeyPressed(KeyEvent.VK_DOWN))
			player.getCurrentGun().y++;
		if (InputUtility.getKeyPressed(KeyEvent.VK_SPACE))
			player.getCurrentGun().shoot(player, list);

		// delete destroyed object
		for (int i = 0; i < list.size(); i++) {
			IRenderable o = list.get(i);
			if (o.isDestroyed() || o.getX() < -100)
				list.remove(i);
		}

		// sort objects by z
		Collections.sort(list, new Comparator<IRenderable>() {
			public int compare(IRenderable arg0, IRenderable arg1) {
				if (arg0.getZ() < arg1.getZ())
					return -1;
				return 1;
			}
		});

		// move
		for (int i = 0; i < list.size(); i++) {
			IRenderable o = list.get(i);
			o.move();
			if (o instanceof Monster)
				((Monster) o).shoot(list);
		}

		// new monster
		newMonsterDelayCounter--;
		if (newMonsterDelayCounter == 0) {
			newMonster();
			setCounter();
		}

		// check collect/hit
		for (IRenderable i : list) {
			// check collectible object
			if (i instanceof ICollectible
					&& i.isOverlap(player.getCurrentGun()))
				((ICollectible) i).collect(player);

			// check GND hits player
			if (i instanceof GndBullet && i.isOverlap(player.getCurrentGun())) {
				((GndBullet) i).destroyed = true;
				player.isHit(((GndBullet) i).getPower());
			}

			// check VDD hits monster
			if (i instanceof VddBullet) {
				for (IRenderable j : list) {
					if (j instanceof Monster && i.isOverlap(j)) {
						((VddBullet) i).destroyed = true;
						((Monster) j).isHit(((GndBullet) i).getPower());
					}
				}
			}

			// check GND hits VDD
			if (i instanceof GndBullet) {
				for (IRenderable j : list) {
					if (j instanceof VddBullet && i.isOverlap(j)) {
						((GndBullet) i).destroyed = true;
						if (!((VddBullet) j).isSpecial())
							((VddBullet) j).destroyed = true;
					}
				}
			}

			// check monster hits player
			if (i instanceof Monster && i.isOverlap(player.getCurrentGun())) {
				((Monster) i).isHit(ConfigurableOption.ATTACK);
				player.isHit(ConfigurableOption.ATTACK);
			}
		}
	}

	public void setCounter() {
		newMonsterDelayCounter = (int) (Math.random() * (ConfigurableOption.MAX_NEW_MONSTER
				- ConfigurableOption.MIN_NEW_MONSTER + 1))
				+ ConfigurableOption.MIN_NEW_MONSTER;
	}

	public int random(int a, int b) {
		return (int) (Math.random() * (b - a + 1)) + a;
	}

	public void newMonster() {
		Player player = data.getPlayer();
		ArrayList<IRenderable> list = data.getList();

		int[][] p = ConfigurableOption.MONSTER_PERCENT;
		int level = player.getLevel();
		int r = random(1, 100);
		int i;

		for (i = 0; i < 7; i++) {
			if (r <= p[level][i])
				break;
		}

		int w = ConfigurableOption.PLAYPANEL_WIDTH;
		int h = ConfigurableOption.PLAYPANEL_HEIGHT;

		// And-Or-Not
		if (i == 0) {
			r = random(1, 3);
			// And
			if (r == 1) {
				int y = random(10, h - DrawingUtility.and.getHeight() + 10);
				list.add(new And(w, y, 100));
			}
			// Or
			if (r == 2) {
				int y = random(10, h - DrawingUtility.or.getHeight() + 10);
				list.add(new Or(w, y, 100));
			}
			// Not
			if (r == 3) {
				int y = random(10, h - DrawingUtility.not.getHeight() + 10);
				list.add(new Not(w, y, 100));
			}
		}

		// DFF
		if (i == 1) {
			int y = random(10, h - DrawingUtility.dFF.getHeight() + 10);
			list.add(new DFF(w, y, 100));
		}

		// HexDisplay
		if (i == 2) {
			int y = random(10, h - DrawingUtility.hexDisplay.getHeight() + 10);
			list.add(new HexDisplay(w, y, 100));
		}

		// PLA
		if (i == 3) {
			int y = random(10, h - DrawingUtility.pla.getHeight() + 10);
			list.add(new PLA(w, y, 100));
		}
		// Mux
		if (i == 4) {
			int y = random(10, h - DrawingUtility.mux.getHeight() + 10);
			list.add(new Mux(w, y, 100));
		}

		// Adder
		if (i == 5) {
			int y = random(10, h - DrawingUtility.adder.getHeight() + 10);
			list.add(new Adder(w, y, 100));
		}
		// IC74163
		if (i == 6) {
			int y = random(10, h - DrawingUtility.ic74163.getHeight() + 10);
			list.add(new IC74163(w, y, 100));
		}
	}

}
