package audio;

import java.applet.AudioClip;

import ui.WindowManager;
import config.SharedData;

public class AudioBackGround implements Runnable {

	private SharedData data;
	private AudioClip ac = AudioUtility.acMenuBg;
	private int currentClip = -1;

	public AudioBackGround(SharedData data) {
		this.data = data;
	}

	public void run() {
		while (true) {
			if (data.getKmap().isRun()) {
				if (!data.getKmap().isGameOver() && currentClip != 2) {
					currentClip = 2;
					ac.stop();
					ac = AudioUtility.acKmapBg;
					ac.loop();
				}
				if (data.getKmap().isGameOver()) {
					currentClip = -1;
					ac.stop();
				}
			} else if (!data.getPlayer().isGameOver()) {
				if (!data.getPlayer().isPause() && currentClip != 1) {
					currentClip = 1;
					ac.stop();
					ac = AudioUtility.acGameBg;
					ac.loop();
				}
				if (data.getPlayer().isPause()) {
					currentClip = -1;
					ac.stop();
				}
			} else if (data.getPlayer().isGameOver() && currentClip == 1) {
				currentClip = -1;
				ac.stop();
			} else if (WindowManager.getStatus() == WindowManager.MENU_STATUS) {
				if (currentClip != 0) {
					currentClip = 0;
					ac.stop();
					ac = AudioUtility.acMenuBg;
					ac.loop();
				}
			}
		}
	}
}
