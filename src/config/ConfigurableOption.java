package config;

import java.awt.Dimension;

public class ConfigurableOption {

	private static int DIALOG_WIDTH = 482;
	private static int DIALOG_HEIGHT = 411;
	private static int WINDOW_WIDTH = 894;
	private static int WINDOW_HEIGHT = 620;

	private static Dimension dialogDimension = new Dimension(DIALOG_WIDTH,
			DIALOG_HEIGHT);
	private static Dimension windowDimension = new Dimension(WINDOW_WIDTH,
			WINDOW_HEIGHT);

	public static final int MAX_TIME = 300;
	public static final int MOVING_DELAY = 5;
	public static final int CLOCK_TIME = 10;

	public static Dimension getDialogDimension() {
		return dialogDimension;
	}

	public static Dimension getWindowDimension() {
		return windowDimension;
	}

}
