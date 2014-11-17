package config;

import java.awt.Dimension;

public class ConfigurableOption {

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
			PLAYPANEL_WIDTH, PLAYPANEL_HEIGHT);

	public static final int MAX_LEVEL = 6;
	public static final int MAX_TIME = 300;
	public static final int MOVING_DELAY = 2;
	public static final int BULLET_MOVING_DELAY = 0;
	public static final int CLOCK_TIME = 10;
	public static final int MIN_NEW_MONSTER = 100;
	public static final int MAX_NEW_MONSTER = 200;
	public static final int MIN_NEW_PROBE = 5000;
	public static final int MAX_NEW_PROBE = 7000;
	public static final int MIN_NEW_CLOCK = 5000;
	public static final int MAX_NEW_CLOCK = 7000;
	public static final int GND_POWER = 10;
	public static final int VDD_POWER = 10;
	public static final int VDD_SPECIAL_POWER = 10;
	public static final int ATTACK = 10;
	public static final int SHOOTING_DELAY = 50;
	public static final int FIRING_DELAY = 200;

	// Score
	public static final int GATE_SCORE = 50;
	public static final int FF_SCORE = 100;
	public static final int HEX_DISPLAY_SCORE = 200;
	public static final int PLA_SCORE = 500;
	public static final int MUX_SCORE = 700;
	public static final int ADDER_SCORE = 1000;
	public static final int ASCII_DISPLAY_SCORE = 1000;
	public static final int IC_SCORE = 5000;

	// And-Or-Not, DFF-JKFF, HexDisplay, PLA, Mux, Adder-AsciiDisplay, IC74163
	public static final int[][] MONSTER_PERCENT = new int[][] {
			{ 0, 0, 0, 0, 0, 0, 0 }, { 80, 100, 0, 0, 0, 0, 0 },
			{ 60, 80, 100, 0, 0, 0, 0 }, { 50, 70, 90, 100, 0, 0, 0 },
			{ 40, 65, 80, 90, 100, 0, 0 }, { 40, 60, 70, 80, 90, 100, 0 },
			{ 0, 0, 0, 0, 0, 0, 100 } };

}
