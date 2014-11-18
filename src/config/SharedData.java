package config;

import java.util.ArrayList;

import logic.IRenderable;
import logic.Kmap;
import logic.Player;

public class SharedData {

	private volatile Player player;
	private volatile ArrayList<IRenderable> list;
	private volatile Kmap kmap;

	public SharedData() {
		player = new Player(100, ConfigurableOption.PLAYPANEL_HEIGHT / 2);
		list = new ArrayList<IRenderable>();
		list.add(player.getCurrentGun());
		kmap = new Kmap();
	}

	public Player getPlayer() {
		return player;
	}

	public ArrayList<IRenderable> getList() {
		return list;
	}

	public Kmap getKmap() {
		return kmap;
	}

	public void reset() {
		player = new Player(100, ConfigurableOption.PLAYPANEL_HEIGHT / 2);
		list = new ArrayList<IRenderable>();
		list.add(player.getCurrentGun());
		kmap = new Kmap();
	}

}
