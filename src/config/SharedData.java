package config;

import java.util.ArrayList;

import logic.IRenderable;
import logic.Player;

public class SharedData {

	private Player player;
	ArrayList<IRenderable> list;

	public SharedData() {
		player = new Player(100, ConfigurableOption.PLAYPANEL_HEIGHT / 2);
		list = new ArrayList<IRenderable>();
		list.add(player.getCurrentGun());
	}

	public Player getPlayer() {
		return player;
	}

	public ArrayList<IRenderable> getList() {
		return list;
	}

}
