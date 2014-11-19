package logic.kmap;

import java.awt.event.KeyEvent;
import java.util.ArrayList;

import logic.gun.Gun;
import logic.gun.SpecialGunA;
import logic.gun.SpecialGunB;
import ui.WindowManager;
import config.*;

public class KmapLogic implements Runnable {

	private SharedData data;

	public KmapLogic(SharedData data) {
		this.data = data;
	}

	public void run() {
		while (true) {
			if (data.getKmap().isRun()) {
				InputUtility.reset();
				while (data.getKmap().isRun()) {
					try {
						Thread.sleep(5);
					} catch (InterruptedException e) {
					}
					update();
					InputUtility.postUpdate();
				}
				data.getKmap().setRun(true);
				int score = data.getKmap().getScore();
				int x = data.getPlayer().getCurrentGun().getX();
				int y = data.getPlayer().getCurrentGun().getY();
				if (score >= 7) {
					Gun g = data.getPlayer().getCurrentGun();
					data.getGameList().remove(g);
					Gun ng = new SpecialGunB(data, x, y, 99);
					data.getPlayer().setCurrentGun(ng);
					data.getGameList().add(ng);
				} else if (score >= 4
						&& !(data.getPlayer().getCurrentGun() instanceof SpecialGunA)) {
					Gun g = data.getPlayer().getCurrentGun();
					data.getGameList().remove(g);
					Gun ng = new SpecialGunA(data, x, y, 99);
					data.getPlayer().setCurrentGun(ng);
					data.getGameList().add(ng);
				}
				if (!data.getKmap().isReturnToGame())
					WindowManager.setStatus(WindowManager.MENU_STATUS);
				data.resetKmap();
				data.getPlayer().setPause(false);
				data.getPlayer().clearCollectedProbe();
				data.getKmap().setRun(false);
			}
		}

	}

	public void update() {
		Kmap map = data.getKmap();
		ArrayList<Frame> list = data.getKmapList();

		if (InputUtility.getKeyPressed(KeyEvent.VK_ESCAPE))
			map.setRun(false);

		if (!map.isRun())
			return;

		// decrease time
		map.setTime(map.getTime() - 1);

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
			map.setRemainFrame(map.getRemainFrame() - 1);
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
