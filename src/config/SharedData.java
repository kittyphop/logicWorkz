package config;

import java.util.ArrayList;

import logic.IRenderable;
import logic.Player;
import logic.kmap.Kmap;
import logic.kmap.Frame;

public class SharedData {

	private volatile Player player;
	private volatile ArrayList<IRenderable> gameList;
	private volatile Kmap kmap;
	private volatile Frame temp;
	private volatile ArrayList<Frame> kmapList;

	public SharedData() {
		player = new Player(this, 100, ConfigurableOption.PLAYPANEL_HEIGHT / 2);
		gameList = new ArrayList<IRenderable>();
		gameList.add(player.getCurrentGun());
		kmap = new Kmap();
		temp = new Frame(-1, -1, -1, -1);
		kmapList = new ArrayList<Frame>();
	}

	public Player getPlayer() {
		return player;
	}

	public ArrayList<IRenderable> getGameList() {
		return gameList;
	}

	public Kmap getKmap() {
		return kmap;
	}

	public void setTemp(Frame temp) {
		this.temp = temp;
	}

	public Frame getTemp() {
		return temp;
	}

	public ArrayList<Frame> getKmapList() {
		return kmapList;
	}

	public void resetGame() {
		player = new Player(this, 100, ConfigurableOption.PLAYPANEL_HEIGHT / 2);
		gameList = new ArrayList<IRenderable>();
		gameList.add(player.getCurrentGun());
	}

	public void resetKmap() {
		kmap = new Kmap();
		temp = new Frame(-1, -1, -1, -1);
		kmapList = new ArrayList<Frame>();
	}

}
