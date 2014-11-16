package ui;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

public class DrawingUtility {

	// icon

	private static Image getIcon(String directory) {
		ClassLoader loader = DrawingUtility.class.getClassLoader();
		try {
			return (new ImageIcon(loader.getResource(directory)).getImage());
		} catch (Exception e) {
			return null;
		}

	}

	private static Image small_icon = getIcon("res/img/small_icon.png");
	private static Image large_icon = getIcon("res/img/large_icon.png");
	public static List<Image> icons;

	static {
		icons = new ArrayList<Image>();
		icons.add(small_icon);
		icons.add(large_icon);
	}

	// image

	private static BufferedImage getImage(String directory) {
		ClassLoader loader = DrawingUtility.class.getClassLoader();
		try {
			return ImageIO.read(loader.getResource(directory));
		} catch (Exception e) {
			return null;
		}
	}

	// public static BufferedImage menu_background =
	// getImage("res/img/menu_background.png");
	public static BufferedImage menu_background = getImage("res/img/menu_temp.png");
	public static BufferedImage game_background = getImage("res/img/game_background.png");
	public static BufferedImage play_panel = getImage("res/img/play_panel.png");

	public static BufferedImage clock = getImage("res/img/clock.png");
	public static BufferedImage probeK = getImage("res/img/probe_k.png");
	public static BufferedImage probeM = getImage("res/img/probe_m.png");
	public static BufferedImage probeA = getImage("res/img/probe_a.png");
	public static BufferedImage probeP = getImage("res/img/probe_p.png");
	public static BufferedImage gndBullet = getImage("res/img/gnd.png");
	public static BufferedImage vddBullet = getImage("res/img/vdd.png");
	public static BufferedImage binarySwitch = getImage("res/img/binary_switch.png");
	public static BufferedImage pushButton = getImage("res/img/push_button.png");
	public static BufferedImage hexKeyboard = getImage("res/img/hex_keyboard.png");
	public static BufferedImage and = getImage("res/img/and.png");
	public static BufferedImage or = getImage("res/img/or.png");
	public static BufferedImage not = getImage("res/img/not.png");
	public static BufferedImage hexDisplay = getImage("res/img/hex_display.png");
	public static BufferedImage dFF = getImage("res/img/d_ff.png");
	public static BufferedImage pla = getImage("res/img/pla.png");
	public static BufferedImage mux = getImage("res/img/mux.png");
	public static BufferedImage adder = getImage("res/img/adder.png");
	public static BufferedImage ic74163 = getImage("res/img/74163.png");
}
