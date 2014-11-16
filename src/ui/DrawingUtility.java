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
	public static final List<Image> icons;

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

	public static final BufferedImage menu_background = getImage("res/img/menu_temp.png");
	public static final BufferedImage game_background = getImage("res/img/game_background.png");
	public static final BufferedImage credit_background = getImage("res/img/credit_background.png");
	public static final BufferedImage play_panel = getImage("res/img/play_panel.png");

	public static final BufferedImage clock = getImage("res/img/clock.png");
	public static final BufferedImage probeK = getImage("res/img/probe_k.png");
	public static final BufferedImage probeM = getImage("res/img/probe_m.png");
	public static final BufferedImage probeA = getImage("res/img/probe_a.png");
	public static final BufferedImage probeP = getImage("res/img/probe_p.png");
	public static final BufferedImage gndBullet = getImage("res/img/gnd.png");
	public static final BufferedImage vddBullet = getImage("res/img/vdd.png");
	public static final BufferedImage binarySwitch = getImage("res/img/binary_switch.png");
	public static final BufferedImage pushButton = getImage("res.img/push_button.png");
	public static final BufferedImage hexKeyboard = getImage("res/img/hex_keyboard.png");
}
