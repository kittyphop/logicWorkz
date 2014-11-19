package config;

import java.util.ArrayList;

import logic.IRenderable;
import logic.Player;
import logic.kmap.Kmap;
import logic.kmap.Line;

public class SharedData {

	private volatile Player player;
	private volatile ArrayList<IRenderable> gameList;
	private volatile Kmap kmap;
	private volatile Line temp;
	private volatile ArrayList<Line> kmapList;

	public SharedData() {
		player = new Player(100, ConfigurableOption.PLAYPANEL_HEIGHT / 2);
		gameList = new ArrayList<IRenderable>();
		gameList.add(player.getCurrentGun());
		kmap = new Kmap();
		temp = new Line(-1, -1, -1, -1);
		kmapList = new ArrayList<Line>();
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

	public Line getTemp() {
		return temp;
	}

	public ArrayList<Line> getKmapList() {
		return kmapList;
	}

	public void resetGame() {
		player = new Player(100, ConfigurableOption.PLAYPANEL_HEIGHT / 2);
		gameList = new ArrayList<IRenderable>();
		gameList.add(player.getCurrentGun());
	}

	public void resetKmap() {
		kmap = new Kmap();
	}

}
