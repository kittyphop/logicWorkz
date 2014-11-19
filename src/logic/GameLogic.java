package logic;

import java.awt.event.KeyEvent;
import java.util.*;
import ui.DrawingUtility;
import ui.HighScoreUtility;
import ui.WindowManager;
import logic.bullet.*;
import logic.collectible.*;
import logic.monster.*;
import config.*;

public class GameLogic implements Runnable {

	private SharedData data;
	private int newMonsterDelayCounter, newProbeDelayCounter,
			newClockDelayCounter, shootingDelayCounter, damagedDelayCounter,
			timeCounter;

	public GameLogic(SharedData data) {
		this.data = data;
		newMonsterDelayCounter = random(ConfigurableOption.MIN_NEW_MONSTER,
				ConfigurableOption.MAX_NEW_MONSTER);
		newProbeDelayCounter = random(ConfigurableOption.MIN_NEW_PROBE,
				ConfigurableOption.MAX_NEW_PROBE);
		newClockDelayCounter = random(ConfigurableOption.MIN_NEW_CLOCK,
				ConfigurableOption.MAX_NEW_CLOCK);
		shootingDelayCounter = 1;
		damagedDelayCounter = 1;
		timeCounter = ConfigurableOption.TIME_DELAY;
	}

	public void run() {
		while (true) {
			if (WindowManager.getStatus() == WindowManager.GAME_STATUS
					&& !data.getPlayer().isGameOver()) {
				while (!data.getPlayer().isGameOver()) {
					try {
						Thread.sleep(5);
					} catch (InterruptedException e) {
					}
					update();
					InputUtility.postUpdate();
				}
				HighScoreUtility.recordHighScore(data.getPlayer().getScore());
				WindowManager.setStatus(WindowManager.MENU_STATUS);
				data.resetGame();
				InputUtility.reset();
			}
		}
	}

	public void update() {
		Player player = data.getPlayer();
		ArrayList<IRenderable> list = data.getGameList();

		if (player.isKmap())
			data.getKmap().setReturnToGame(true);

		if (player.isGameOver() || player.isKmap())
			return;

		if (InputUtility.getKeyTriggered(KeyEvent.VK_ENTER))
			player.setPause(!player.isPause());

		// check if pause
		if (player.isPause())
			return;

		// decrease time
		timeCounter--;
		if (timeCounter == 0) {
			player.setTime(player.getTime() - 1);
			timeCounter = ConfigurableOption.TIME_DELAY;
		}

		// check if player shoot
		if (InputUtility.getKeyPressed(KeyEvent.VK_SPACE)) {
			shootingDelayCounter--;
			if (shootingDelayCounter == 0) {
				player.getCurrentGun().shoot(player, list);
				shootingDelayCounter = ConfigurableOption.SHOOTING_DELAY;
			}
		} else {
			shootingDelayCounter = 1;
		}

		// delete destroyed object
		for (int i = 0; i < list.size(); i++) {
			IRenderable o = list.get(i);
			if (o.isDestroyed() || o.getX() < -100
					|| o.getX() > ConfigurableOption.PLAYPANEL_WIDTH)
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
			newMonsterDelayCounter = random(ConfigurableOption.MIN_NEW_MONSTER,
					ConfigurableOption.MAX_NEW_MONSTER);
		}

		// new probe
		newProbeDelayCounter--;
		if (newProbeDelayCounter == 0) {
			String[] x = { "K", "M", "A", "P" };
			int r = random(0, 3);
			int y = random(10, ConfigurableOption.PLAYPANEL_HEIGHT
					- DrawingUtility.probeA.getHeight() - 10);
			list.add(new Probe(ConfigurableOption.PLAYPANEL_WIDTH, y, x[r]));
			newProbeDelayCounter = random(ConfigurableOption.MIN_NEW_PROBE,
					ConfigurableOption.MAX_NEW_PROBE);
		}

		// new clock
		newClockDelayCounter--;
		if (newClockDelayCounter == 0) {
			int y = random(10, ConfigurableOption.PLAYPANEL_HEIGHT
					- DrawingUtility.clock.getHeight() - 10);
			list.add(new Clock(ConfigurableOption.PLAYPANEL_WIDTH, y));
			newProbeDelayCounter = random(ConfigurableOption.MIN_NEW_CLOCK,
					ConfigurableOption.MAX_NEW_CLOCK);
		}

		// check gun damaged
		damagedDelayCounter--;
		if (damagedDelayCounter < 0) {
			damagedDelayCounter = 1;
			player.setDamaged(false);
		}

		// check collect/hit
		for (IRenderable i : list) {
			// check collectible object
			if (i instanceof ICollectible
					&& i.isOverlap(player.getCurrentGun()))
				((ICollectible) i).collect(player);

			// check GND hits player
			if (i instanceof GndBullet && i.isOverlap(player.getCurrentGun())
					&& damagedDelayCounter == 0) {
				((GndBullet) i).destroyed = true;
				player.isHit(((GndBullet) i).getPower());
				player.setDamaged(true);
				damagedDelayCounter = ConfigurableOption.DAMAGED_DELAY;
			}

			// check VDD hits monster
			if (i instanceof VddBullet) {
				for (IRenderable j : list) {
					if (j instanceof Monster && i.isOverlap(j)) {
						((VddBullet) i).destroyed = true;
						((Monster) j).isHit(((VddBullet) i).getPower(), player);
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
			if (i instanceof Monster && i.isOverlap(player.getCurrentGun())
					&& damagedDelayCounter == 0) {
				// ((Monster) i).isHit(ConfigurableOption.ATTACK);
				player.isHit(ConfigurableOption.ATTACK);
				player.setDamaged(true);
				damagedDelayCounter = ConfigurableOption.DAMAGED_DELAY;
			}
		}
	}

	public int random(int a, int b) {
		return (int) (Math.random() * (b - a + 1)) + a;
	}

	public void newMonster() {
		Player player = data.getPlayer();
		ArrayList<IRenderable> list = data.getGameList();

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
				int y = random(10, h - DrawingUtility.and.getHeight() - 10);
				list.add(new And(w, y, 100));
			}
			// Or
			if (r == 2) {
				int y = random(10, h - DrawingUtility.or.getHeight() - 10);
				list.add(new Or(w, y, 100));
			}
			// Not
			if (r == 3) {
				int y = random(10, h - DrawingUtility.not.getHeight() - 10);
				list.add(new Not(w, y, 100));
			}
		}

		// DFF-JKFF
		if (i == 1) {
			r = random(1, 2);
			// DFF
			if (r == 1) {
				int y = random(10, h - DrawingUtility.dFF.getHeight() - 10);
				list.add(new DFF(w, y, 100));
			}
			if (r == 2) {
				int y = random(10, h - DrawingUtility.jkFF.getHeight() - 10);
				list.add(new JKFF(w, y, 100));
			}
		}

		// HexDisplay
		if (i == 2) {
			int y = random(10, h - DrawingUtility.hexDisplay.getHeight() - 10);
			list.add(new HexDisplay(w, y, 100));
		}

		// PLA
		if (i == 3) {
			int y = random(10, h - DrawingUtility.pla.getHeight() - 10);
			list.add(new PLA(w, y, 100));
		}

		// Mux
		if (i == 4) {
			int y = random(10, h - DrawingUtility.mux.getHeight() - 10);
			list.add(new Mux(w, y, 100));
		}

		// Adder-AsciiDisplay
		if (i == 5) {
			r = random(1, 2);
			if (r == 1) {
				int y = random(10, h - DrawingUtility.adder.getHeight() - 10);
				list.add(new Adder(w, y, 100));
			}
			if (r == 2) {
				int y = random(10, h - DrawingUtility.asciiDisplay.getHeight()
						- 10);
				list.add(new AsciiDisplay(w, y, 100));
			}
		}

		// IC74163
		if (i == 6) {
			int y = random(10, h - DrawingUtility.ic74163.getHeight() - 10);
			list.add(new IC74163(w, y, 100));
		}
	}
}
