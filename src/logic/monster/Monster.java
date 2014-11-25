package logic.monster;

import java.util.ArrayList;

import audio.AudioUtility;
import logic.IRenderable;
import logic.Player;
import logic.Rectangle;
import logic.RenderableObject;
import config.ConfigurableOption;
import config.RandomUtility;

public abstract class Monster extends RenderableObject {

	protected int life, reward, firingDelay, firingDelayCounter;

	public Monster(int x, int y, int life, int reward) {
		super(x, y);
		this.life = life;
		this.reward = reward;
		firingDelay = RandomUtility.random(ConfigurableOption.MIN_FIRING_DELAY,
				ConfigurableOption.MAX_FIRING_DELAY);
		firingDelayCounter = firingDelay;
	}

	public void move() {
		movingDelayCounter--;
		if (movingDelayCounter > 0)
			return;
		x--;
		movingDelayCounter = movingDelay;
	}

	public abstract void shoot(ArrayList<IRenderable> list);

	public abstract Rectangle rectify();

	public void isHit(int attack, Player player) {
		life -= attack;
		if (life <= 0) {
			new Thread(new AudioUtility(AudioUtility.ENEMY_DIE)).start();
			destroyed = true;
			player.setScore(player.getScore() + reward);
			boolean mission = false;
			if (player.getLevel() == 0 && this instanceof Not)
				mission = true;
			if (player.getLevel() == 1 && this instanceof Or)
				mission = true;
			if (player.getLevel() == 2 && this instanceof And)
				mission = true;
			if (player.getLevel() == 3 && this instanceof DFF)
				mission = true;
			if (player.getLevel() == 4 && this instanceof JKFF)
				mission = true;
			if (player.getLevel() == 5 && this instanceof HexDisplay)
				mission = true;
			if (player.getLevel() == 6 && this instanceof PLA)
				mission = true;
			if (player.getLevel() == 7 && this instanceof Mux)
				mission = true;
			if (player.getLevel() == 8 && this instanceof AsciiDisplay)
				mission = true;
			if (player.getLevel() == 9 && this instanceof Adder)
				mission = true;
			if (player.getLevel() == 10 && this instanceof IC74163)
				mission = true;
			if (mission)
				player.doMission();
		}
	}

}
