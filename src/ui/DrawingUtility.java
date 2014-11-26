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

	public static final BufferedImage menu_background = getImage("res/img/menu_background.png");
	public static final BufferedImage game_background = getImage("res/img/game_background.png");
	public static final BufferedImage credit_background = getImage("res/img/credit_background.png");
	public static final BufferedImage kmap_background = getImage("res/img/kmap_background.png");
	public static final BufferedImage play_panel = getImage("res/img/play_panel.png");

	public static final BufferedImage time_diagram = getImage("res/img/time_diagram.png");

	public static final BufferedImage side_binary_switch = getImage("res/img/side_binary_switch.png");
	public static final BufferedImage side_hex_keyboard = getImage("res/img/side_hex_keyboard.png");
	public static final BufferedImage side_push_button = getImage("res/img/side_push_button.png");
	public static final BufferedImage side_one_shot = getImage("res/img/side_one_shot.png");

	public static final BufferedImage kmap_frame = getImage("res/img/kmap_frame.png");
	public static final BufferedImage kmap_score = getImage("res/img/kmap_score.png");

	public static final BufferedImage how_to_play_0 = getImage("res/img/how_to_play_0.png");
	public static final BufferedImage how_to_play_1 = getImage("res/img/how_to_play_1.png");

	public static final BufferedImage clock = getImage("res/img/clock.png");
	public static final BufferedImage probeK = getImage("res/img/probe_k.png");
	public static final BufferedImage probeM = getImage("res/img/probe_m.png");
	public static final BufferedImage probeA = getImage("res/img/probe_a.png");
	public static final BufferedImage probeP = getImage("res/img/probe_p.png");
	public static final BufferedImage gndBullet = getImage("res/img/gnd.png");
	public static final BufferedImage vddBullet = getImage("res/img/vdd.png");
	public static final BufferedImage vddBulletSpecial = getImage("res/img/vdd_special.png");
	public static final BufferedImage binarySwitch0 = getImage("res/img/binary_switch_0.png");
	public static final BufferedImage binarySwitch1 = getImage("res/img/binary_switch_1.png");
	public static final BufferedImage pushButton0 = getImage("res/img/push_button_0.png");
	public static final BufferedImage pushButton1 = getImage("res/img/push_button_1.png");
	public static final BufferedImage oneShot = getImage("res/img/one_shot.png");
	public static final BufferedImage hexKeyboard = getImage("res/img/hex_keyboard.png");
	public static final BufferedImage and = getImage("res/img/and.png");
	public static final BufferedImage or = getImage("res/img/or.png");
	public static final BufferedImage not = getImage("res/img/not.png");
	public static final BufferedImage hexDisplay = getImage("res/img/hex_display.png");
	public static final BufferedImage dFF = getImage("res/img/d_ff.png");
	public static final BufferedImage jkFF = getImage("res/img/jk_ff.png");
	public static final BufferedImage pla = getImage("res/img/pla.png");
	public static final BufferedImage mux = getImage("res/img/mux.png");
	public static final BufferedImage adder = getImage("res/img/adder.png");
	public static final BufferedImage asciiDisplay = getImage("res/img/ascii_display.png");
	public static final BufferedImage ic74163 = getImage("res/img/74163.png");

	public static final BufferedImage and_45pix = getImage("res/img/and_45pix.png");
	public static final BufferedImage or_45pix = getImage("res/img/or_45pix.png");
	public static final BufferedImage not_45pix = getImage("res/img/not_45pix.png");
	public static final BufferedImage hexDisplay_45pix = getImage("res/img/hex_display_45pix.png");
	public static final BufferedImage dFF_45pix = getImage("res/img/d_ff_45pix.png");
	public static final BufferedImage jkFF_45pix = getImage("res/img/jk_ff_45pix.png");
	public static final BufferedImage pla_45pix = getImage("res/img/pla_45pix.png");
	public static final BufferedImage mux_45pix = getImage("res/img/mux_45pix.png");
	public static final BufferedImage adder_45pix = getImage("res/img/adder_45pix.png");
	public static final BufferedImage asciiDisplay_45pix = getImage("res/img/ascii_display_45pix.png");
	public static final BufferedImage ic74163_45pix = getImage("res/img/74163_45pix.png");

	public static final BufferedImage pushButton0_45pix = getImage("res/img/push_button_45pix.png");
	public static final BufferedImage oneShot_45pix = getImage("res/img/one_shot_45pix.png");
	public static final BufferedImage hexKeyboard_45pix = getImage("res/img/hex_keyboard_45pix.png");

	public static final BufferedImage[][] probe_array;

	static {
		probe_array = new BufferedImage[4][2];
		probe_array[0][0] = getImage("res/img/probe_k_0.png");
		probe_array[0][1] = getImage("res/img/probe_k.png");
		probe_array[1][0] = getImage("res/img/probe_m_0.png");
		probe_array[1][1] = getImage("res/img/probe_m.png");
		probe_array[2][0] = getImage("res/img/probe_a_0.png");
		probe_array[2][1] = getImage("res/img/probe_a.png");
		probe_array[3][0] = getImage("res/img/probe_p_0.png");
		probe_array[3][1] = getImage("res/img/probe_p.png");
	}

}
