package logic.kmap;

import java.awt.event.KeyEvent;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import logic.gun.*;
import ui.AudioUtility;
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
				data.setTemp(new Frame(-1, -1, -1, -1));
				if (data.getKmap().isCoverAllOnes()) {
					data.getKmap().setScore(data.getKmap().getScore() + 1);
					new Thread(new AudioUtility(AudioUtility.KMAP_COMPLETE))
							.start();
				}
				int score = data.getKmap().getScore();
				if (data.getKmap().isReturnToGame()) {
					int x = data.getPlayer().getCurrentGun().getX();
					int y = data.getPlayer().getCurrentGun().getY();
					Gun g = data.getPlayer().getCurrentGun();
					if (score >= 10) {
						g.setDestroyed(true);
						Gun ng = new SpecialGunC(data, x, y,
								(score - 9) * 5 + 45);
						data.getPlayer().setCurrentGun(ng);
						data.getGameList().add(ng);
					} else if (score >= 7
							&& (!(g instanceof SpecialGunC) || (g instanceof SpecialGunB && g
									.getBulletAmount() < (score - 6) * 5 + 30))) {
						g.setDestroyed(true);
						Gun ng = new SpecialGunB(data, x, y,
								(score - 6) * 5 + 30);
						data.getPlayer().setCurrentGun(ng);
						data.getGameList().add(ng);
					} else if (score >= 4
							&& (!(g instanceof SpecialGunC || g instanceof SpecialGunB) || (g instanceof SpecialGunA && g
									.getBulletAmount() < (score - 3) * 10))) {
						g.setDestroyed(true);
						Gun ng = new SpecialGunA(data, x, y, (score - 3) * 10);
						data.getPlayer().setCurrentGun(ng);
						data.getGameList().add(ng);
					}
					data.getPlayer().clearCollectedProbe();
					data.setRemainWaitingTime();
				} else {
					JOptionPane.showMessageDialog(null,
							"Game over\nYour score is " + score, "Game over",
							JOptionPane.INFORMATION_MESSAGE);
					WindowManager.setStatus(WindowManager.MENU_STATUS);
				}
				data.resetKmap();
				data.getKmap().setRun(false);
			} else {
				synchronized (data) {
					try {
						data.wait();
					} catch (InterruptedException e) {
					}
				}
			}
		}

	}

	public void update() {
		Kmap map = data.getKmap();
		ArrayList<Frame> list = data.getKmapList();

		if (data.getRemainWaitingTime() > 0) {
			data.decreaseWaitingTime();
			return;
		}

		if (InputUtility.getKeyTriggered(KeyEvent.VK_ESCAPE))
			map.setRun(false);

		if (!map.isRun())
			return;

		// decrease time
		map.setTime(map.getTime() - 1);

		// check if cover all ones
		if (map.isCoverAllOnes() || InputUtility.getKeyTriggered(KeyEvent.VK_X)) {
			new Thread(new AudioUtility(AudioUtility.KMAP_COMPLETE)).start();
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
			if (!data.getTemp().hasMinus())
				map.setRemainFrame(map.getRemainFrame() - 1);
			if (map.ok(data.getTemp().toIndex())) {
				new Thread(new AudioUtility(AudioUtility.KMAP_CORRECT)).start();
				map.cover(data.getTemp().toIndex());
				list.add(new Frame(data.getTemp()));
			} else
				new Thread(new AudioUtility(AudioUtility.KMAP_INCORRECT))
						.start();
			map.setX(-1);
			map.setY(-1);
			data.setTemp(new Frame(-1, -1, -1, -1));
		}

	}

}
