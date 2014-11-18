package logic.monster;

import java.util.ArrayList;

import logic.IRenderable;
import logic.Player;
import logic.Rectangle;
import logic.RenderableObject;
import config.ConfigurableOption;

public abstract class Monster extends RenderableObject {

	protected int life, reward, firingDelayCounter;

	public Monster(int x, int y, int life, int reward) {
		super(x, y);
		this.life = life;
		this.reward = reward;
		firingDelayCounter = ConfigurableOption.FIRING_DELAY;
	}

	public void move() {
		movingDelayCounter--;
		if (movingDelayCounter > 0)
			return;
		x--;
		movingDelayCounter = ConfigurableOption.MOVING_DELAY;
	}

	public abstract void shoot(ArrayList<IRenderable> list);

	public abstract Rectangle rectify();

	public void isHit(int attack, Player player) {
		life -= attack;
		if (life <= 0) {
			destroyed = true;
			player.setScore(player.getScore() + reward);
			player.setTime(player.getTime() + 10);
		}
	}

}
