package audio;

import java.applet.Applet;
import java.applet.AudioClip;

public class AudioUtility implements Runnable {

	private int event;

	public static final int COLLECT_CLOCK = 1;
	public static final int COLLECT_PROBE = 2;
	public static final int SHOOT = 3;
	public static final int GAME_OVER = 4;
	public static final int PAUSE = 5;
	public static final int ENEMY_DIE = 6;
	public static final int DAMAGED = 7;
	public static final int KMAP_CORRECT = 8;
	public static final int KMAP_INCORRECT = 9;
	public static final int KMAP_TIME = 10;
	public static final int KMAP_COMPLETE = 11;
	public static final int WIN = 12;

	public AudioUtility(int event) {
		this.event = event;
	}

	private static AudioClip getAudio(String directory) {
		ClassLoader loader = AudioUtility.class.getClassLoader();
		try {
			return Applet.newAudioClip((loader.getResource(directory)).toURI()
					.toURL());
		} catch (Exception e) {
			return null;
		}
	}

	private static AudioClip acCollectClock = getAudio("res/sound/collect_clock.wav");
	private static AudioClip acCollectProbe = getAudio("res/sound/collect_probe.wav");
	private static AudioClip acShoot = getAudio("res/sound/shoot.wav");
	private static AudioClip acPause = getAudio("res/sound/pause.wav");
	private static AudioClip acEnemyDie = getAudio("res/sound/enemy_die.wav");
	private static AudioClip acDamaged = getAudio("res/sound/damaged.wav");
	private static AudioClip acKmapCorrect = getAudio("res/sound/kmap_correct.wav");
	private static AudioClip acKmapIncorrect = getAudio("res/sound/kmap_incorrect.wav");
	private static AudioClip acGameOver = getAudio("res/sound/game_over.wav");
	private static AudioClip acKmapTime = getAudio("res/sound/kmap_time.wav");
	private static AudioClip acWin = getAudio("res/sound/win.wav");
	private static AudioClip acKmapComplete = getAudio("res/sound/kmap_complete.wav");

	public static AudioClip acMenuBg = getAudio("res/sound/menu_bg.wav");
	public static AudioClip acGameBg = getAudio("res/sound/game_bg.wav");
	public static AudioClip acKmapBg = getAudio("res/sound/kmap_bg.wav");

	public void run() {
		AudioClip ac = null;
		if (event == COLLECT_CLOCK)
			ac = acCollectClock;
		if (event == COLLECT_PROBE)
			ac = acCollectProbe;
		if (event == SHOOT)
			ac = acShoot;
		if (event == PAUSE)
			ac = acPause;
		if (event == ENEMY_DIE)
			ac = acEnemyDie;
		if (event == DAMAGED)
			ac = acDamaged;
		if (event == KMAP_CORRECT)
			ac = acKmapCorrect;
		if (event == KMAP_INCORRECT)
			ac = acKmapIncorrect;
		if (event == GAME_OVER)
			ac = acGameOver;
		if (event == KMAP_TIME)
			ac = acKmapTime;
		if (event == WIN)
			ac = acWin;
		if (event == KMAP_COMPLETE)
			ac = acKmapComplete;
		ac.play();
	}
}
