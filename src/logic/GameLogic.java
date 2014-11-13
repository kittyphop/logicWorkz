package logic;

import java.util.ArrayList;
import config.ConfigurableOption;

public class GameLogic {

	private Player player;
	ArrayList<IRenderable> list;
	private int newMonsterDelayCounter;

	public GameLogic() {
		player = new Player();
		list.clear();
		setCounter();
	}

	public void update() {
		// delete destroyed object
		for (IRenderable i : list) {
			if (i.isDestroyed())
				list.remove(i);
		}
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
			if (i instanceof ICollectible) // and i.canCollect(player)
				((ICollectible) i).collect(player);

			// check GND hits player
			if (i instanceof GndBullet) // and i.isHit(player)
				((GndBullet) i).hit(player);

			// check VDD hits monster
			if (i instanceof VddBullet) {
				for (IRenderable j : list) {
					if (j instanceof Monster) // and i.isHit(j)
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
