package logic;

import java.awt.Dimension;
import java.util.ArrayList;
import java.util.Comparator;

import config.ConfigurableOption;

public class GameLogic {

	private Player player;
	ArrayList<IRenderable> list;
	private int newMonsterDelayCounter;

	public GameLogic() {
		player = new Player(100, ConfigurableOption.PLAYPANEL_HEIGHT / 2);
		list = new ArrayList<IRenderable>();
		list.add(player.getCurrentGun());
		setCounter();
	}

	public void update() {
		// check if pause

		// check if player move or shoot and gun change

		// delete destroyed object
		for (IRenderable i : list) {
			if (i.isDestroyed())
				list.remove(i);
		}

		// sort objects by z
		list.sort(new Comparator<IRenderable>() {
			public int compare(IRenderable arg0, IRenderable arg1) {
				if (arg0.getZ() < arg1.getZ())
					return -1;
				return 1;
			}
		});

		// move
		for (IRenderable i : list) {
			i.move();
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
					&& ((ICollectible) i).isOverlap(player))
				((ICollectible) i).collect(player);

			// check GND hits player
			if (i instanceof GndBullet && ((GndBullet) i).isOverlap(player))
				((GndBullet) i).hit(player);

			// check VDD hits monster
			if (i instanceof VddBullet) {
				for (IRenderable j : list) {
					if (j instanceof Monster
							&& ((VddBullet) i).isOverlap((Monster) j))
						((VddBullet) i).hit((Monster) j);
				}
			}

			// check monster hits player
			if (i instanceof Monster) {
				// if monster.isHit(player)
				// player.hitByMonster(monster);
			}
		}
	}

	public void setCounter() {
		newMonsterDelayCounter = (int) (Math.random() * (ConfigurableOption.MAX_NEW_MONSTER
				- ConfigurableOption.MIN_NEW_MONSTER + 1))
				+ ConfigurableOption.MIN_NEW_MONSTER;
	}

	public void newMonster() {
		// new monster code here
		// don't forget to check player.getLevel();
	}

}
