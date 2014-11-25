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

	public static final int MAX_LEVEL = 10;
	public static final int MAX_TIME = 300;
	public static final int MIN_MOVING_DELAY = 2;
	public static final int MAX_MOVING_DELAY = 5;
	public static final int BULLET_MOVING_DELAY = 0;
	public static final int CLOCK_TIME = 10;
	public static final int MIN_NEW_MONSTER = 300;
	public static final int MAX_NEW_MONSTER = 600;
	public static final int MIN_NEW_PROBE = 2000;
	public static final int MAX_NEW_PROBE = 3000;
	public static final int MIN_NEW_CLOCK = 7000;
	public static final int MAX_NEW_CLOCK = 10000;
	public static final int GND_POWER = 10;
	public static final int VDD_POWER = 10;
	public static final int VDD_SPECIAL_POWER = 50;
	public static final int ATTACK = 10;
	public static final int SHOOTING_DELAY = 50;
	public static final int MIN_FIRING_DELAY = 150;
	public static final int MAX_FIRING_DELAY = 250;
	public static final int DAMAGED_DELAY = 350;
	public static final int RENDER_DELAY = 7;
	public static final int TIME_DELAY = 500;
	public static final int MAX_KMAP_TIME = 10 * TIME_DELAY;
	public static final int MAX_FRAME = 30;
	public static final int MONSTER_TO_NEXT_LEVEL = 10;

	// Life
	public static final int NOT_LIFE = 10;
	public static final int OR_LIFE = 20;
	public static final int AND_LIFE = 30;
	public static final int DFF_LIFE = 50;
	public static final int JKFF_LIFE = 60;
	public static final int HEX_DISPLAY_LIFE = 80;
	public static final int PLA_LIFE = 100;
	public static final int MUX_LIFE = 130;
	public static final int ASCII_DISPLAY_LIFE = 150;
	public static final int ADDER_LIFE = 200;
	public static final int IC74163_LIFE = 500;

	// Score
	public static final int NOT_SCORE = 50;
	public static final int OR_SCORE = 50;
	public static final int AND_SCORE = 50;
	public static final int DFF_SCORE = 100;
	public static final int JKFF_SCORE = 100;
	public static final int HEX_DISPLAY_SCORE = 200;
	public static final int PLA_SCORE = 500;
	public static final int MUX_SCORE = 700;
	public static final int ASCII_DISPLAY_SCORE = 1000;
	public static final int ADDER_SCORE = 2000;
	public static final int IC74163_SCORE = 5000;
	public static final int PROBE_SCORE = 100;
	public static final int CLOCK_SCORE = 200;

	// not, or, and, dFF, jkFF, hexDisplay, pla, mux, asciiDisplay, adder,
	// ic74163
	public static final int[][] MONSTER_PERCENT = new int[][] {
			{ 100, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 }, // level 0
			{ 40, 100, 0, 0, 0, 0, 0, 0, 0, 0, 0 }, // level 1
			{ 30, 50, 100, 0, 0, 0, 0, 0, 0, 0, 0 }, // level 2
			{ 15, 30, 50, 100, 0, 0, 0, 0, 0, 0, 0 }, // level 3
			{ 10, 25, 35, 50, 100, 0, 0, 0, 0, 0, 0 }, // level 4
			{ 0, 10, 25, 35, 50, 100, 0, 0, 0, 0, 0 }, // level 5
			{ 0, 0, 10, 25, 35, 50, 100, 0, 0, 0, 0 }, // level 6
			{ 0, 0, 10, 20, 30, 40, 50, 100, 0, 0, 0 }, // level 7
			{ 0, 0, 5, 10, 20, 30, 40, 50, 100, 0, 0 }, // level 8
			{ 0, 0, 0, 5, 10, 20, 30, 40, 50, 100, 0 }, // level 9
			{ 0, 0, 0, 0, 5, 10, 20, 30, 40, 50, 100 }, }; // level BOSS (10)

}
