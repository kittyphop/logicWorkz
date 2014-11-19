package logic.kmap;

import java.util.ArrayList;
import ui.WindowManager;
import config.*;

public class KmapLogic implements Runnable {

	private SharedData data;
	private int timeCounter;

	public KmapLogic(SharedData data) {
		this.data = data;
		timeCounter = ConfigurableOption.TIME_DELAY;
	}

	public void run() {
		while (true) {
			if (data.getKmap().isRun()) {
				while (data.getKmap().isRun()) {
					try {
						Thread.sleep(5);
					} catch (InterruptedException e) {
					}
					update();
					InputUtility.postUpdate();
				}
				if (data.getKmap().isReturnToGame())
					WindowManager.setStatus(WindowManager.GAME_STATUS);
				else {
					WindowManager.setStatus(WindowManager.MENU_STATUS);
					data.resetGame();
				}
				data.resetKmap();
				data.getPlayer().setPause(false);
				data.getPlayer().clearCollectedProbe();
				InputUtility.reset();
			}
		}

	}

	public void update() {
		Kmap map = data.getKmap();
		ArrayList<Frame> list = data.getKmapList();

		if (!map.isRun())
			return;

		// decrease time
		timeCounter--;
		if (timeCounter == 0) {
			map.setTime(map.getTime() - 1);
			timeCounter = ConfigurableOption.TIME_DELAY;
		}

		// check if cover all ones
		if (map.isCoverAllOnes()) {
			map.setScore(map.getScore() + 1);
			map.randomKmap();
			list.clear();
		}

		// check if player click
		if (InputUtility.isMouseLeftDownTriggered()) {
			if (Kmap.isInsideKmap(InputUtility.getMouseX(),
					InputUtility.getMouseY())) {
				map.setX(InputUtility.getMouseX());
				map.setY(InputUtility.getMouseY());
			} else {
				map.setX(-1);
				map.setY(-1);
			}
		}

		// check if player hold
		if (InputUtility.isMouseLeftDown()) {
			if (Kmap.isInsideKmap(InputUtility.getMouseX(),
					InputUtility.getMouseY())) {
				data.setTemp(new Frame(map.getX(), map.getY(), InputUtility
						.getMouseX(), InputUtility.getMouseY()));
			} else
				data.setTemp(new Frame(map.getX(), map.getY(), -1, -1));
		}

		// check if player release
		if (InputUtility.isMouseLeftUpTriggered()) {
			if (map.ok(data.getTemp().toIndex())) {
				map.cover(data.getTemp().toIndex());
				list.add(new Frame(data.getTemp()));
			}
			map.setX(-1);
			map.setY(-1);
			data.setTemp(new Frame(-1, -1, -1, -1));
		}
	}

}