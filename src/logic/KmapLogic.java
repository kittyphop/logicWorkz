package logic;

import ui.WindowManager;
import config.ConfigurableOption;
import config.InputUtility;
import config.SharedData;

public class KmapLogic implements Runnable {

	private SharedData data;
	private int timeCounter;

	public KmapLogic(SharedData data) {
		this.data = data;
		timeCounter = ConfigurableOption.TIME_DELAY;
	}

	public void run() {
		while (true) {
			if (WindowManager.getStatus() == WindowManager.GAME_STATUS
					&& data.getPlayer().isKmap()) {
				while (data.getPlayer().isKmap()) {
					try {
						Thread.sleep(5);
					} catch (InterruptedException e) {
					}
					update();
					InputUtility.postUpdate();
				}
				if (data.getKmap().isReturnToGame())
					WindowManager.setStatus(WindowManager.GAME_STATUS);
				else
					WindowManager.setStatus(WindowManager.MENU_STATUS);
				data.getPlayer().clearCollectedProbe();
				InputUtility.reset();
			}
		}

	}

	public void update() {
		Kmap map = data.getKmap();

		if (map.isEnd())
			return;

		// decrease time
		timeCounter--;
		if (timeCounter == 0) {
			map.setTime(map.getTime() - 1);
			timeCounter = ConfigurableOption.TIME_DELAY;
		}

		// check if player click

		// check if player release
	}

}
