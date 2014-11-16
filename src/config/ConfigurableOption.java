package config;

import java.awt.Dimension;

public class ConfigurableOption {

<<<<<<< HEAD
	public static final int DIALOG_WIDTH = 482;
	public static final int DIALOG_HEIGHT = 411;
	public static final int WINDOW_WIDTH = 894;
	public static final int WINDOW_HEIGHT = 620;
	public static final int PLAYPANEL_WIDTH = 717;
	public static final int PLAYPANEL_HEIGHT = 356;
	public static final int PLAYPANEL_X = 15;
	public static final int PLAYPANEL_Y = 95;

	public static final Dimension DIALOG_DIMENSION = new Dimension(
			DIALOG_WIDTH, DIALOG_HEIGHT);
	public static final Dimension WINDOW_DIMENSION = new Dimension(
			WINDOW_WIDTH, WINDOW_HEIGHT);
	public static final Dimension PLAYPANEL_DIMENSION = new Dimension(
=======
	private static int DIALOG_WIDTH = 482;
	private static int DIALOG_HEIGHT = 411;
	private static int WINDOW_WIDTH = 894;
	private static int WINDOW_HEIGHT = 620;
	private static int PLAYPANEL_WIDTH = 717;
	private static int PLAYPANEL_HEIGHT = 356;

	private static Dimension dialogDimension = new Dimension(DIALOG_WIDTH,
			DIALOG_HEIGHT);
	private static Dimension windowDimension = new Dimension(WINDOW_WIDTH,
			WINDOW_HEIGHT);
	private static Dimension playPanelDimension = new Dimension(
>>>>>>> d1f91cb0c89b8d9959d4e3c724c89eaa15f2e005
			PLAYPANEL_WIDTH, PLAYPANEL_HEIGHT);

	public static final int MAX_TIME = 300;
	public static final int MOVING_DELAY = 5;
	public static final int CLOCK_TIME = 10;
	public static final int MIN_NEW_MONSTER = 100;
	public static final int MAX_NEW_MONSTER = 200;
	public static final int GND_POWER = 10;
	public static final int VDD_POWER = 10;
	public static final int VDD_SPECIAL_POWER = 10;

<<<<<<< HEAD
=======
	public static Dimension getDialogDimension() {
		return dialogDimension;
	}

	public static Dimension getWindowDimension() {
		return windowDimension;
	}

	public static Dimension getPlayPanelDimension() {
		return playPanelDimension;
	}

>>>>>>> d1f91cb0c89b8d9959d4e3c724c89eaa15f2e005
}
