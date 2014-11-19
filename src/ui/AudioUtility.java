package ui;

import java.applet.Applet;
import java.applet.AudioClip;

public class AudioUtility {

	private static AudioClip getAudio(String directory) {
		ClassLoader loader = AudioUtility.class.getClassLoader();
		try {
			return Applet.newAudioClip((loader.getResource(directory)).toURI()
					.toURL());
		} catch (Exception e) {
			return null;
		}
	}

	private static AudioClip acShoot;
	private static AudioClip acCollect;

	public static void playSound(String identifier) {
		AudioClip ac;
		if (identifier.equalsIgnoreCase("shoot"))
			ac = acShoot;
		else
			ac = acCollect;
		ac.play();
	}
}
